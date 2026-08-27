<!--
RUNBOOK STRUCTURE - Future normalization guide

Purpose
Applicability
Prerequisites
Before You Begin
Procedure
Validation
Rollback / Recovery
Operational Considerations
Related Documentation

When maintaining this document, use the sections above where applicable.
Sections may be omitted when they are not relevant.
-->

# Runbook: Kafka Remove Node

## Decommissioning One Broker — Two Nodes to Single Node

<table>
  <tbody>
    <tr>
      <td><strong>Surviving Node</strong></td>
      <td><code>&lt;SURVIVOR_IP&gt;</code> — Broker ID <code>&lt;SURVIVOR_ID&gt;</code></td>
    </tr>
    <tr>
      <td><strong>Node to Remove</strong></td>
      <td><code>&lt;REMOVE_IP&gt;</code> — Broker ID <code>&lt;REMOVE_ID&gt;</code></td>
    </tr>
    <tr>
      <td><strong>Kafka Mode</strong></td>
      <td>ZooKeeper mode (bare metal / manual)</td>
    </tr>
    <tr>
      <td><strong>Kafka Bootstrap</strong></td>
      <td><code>&lt;BOOTSTRAP_IP&gt;:9093</code></td>
    </tr>
    <tr>
      <td><strong>ZooKeeper Connect</strong></td>
      <td><code>&lt;ZK_IP1&gt;:2181,&lt;ZK_IP2&gt;:2181</code></td>
    </tr>
  </tbody>
</table>

## Overview

This runbook walks through the safe decommissioning of one Kafka broker from a two-node cluster, leaving a single healthy broker behind. It covers partition reassignment, ZooKeeper cleanup, service shutdown, and post-migration validation.

Before you start, fill in the placeholder values in the Variables section below and keep this document open throughout the process.

## Before You Begin

- Both brokers must be online and healthy before starting.
- All topic partitions must be reassigned off the node to remove before you stop any service.
- Replication factor is 1 on all topics in this cluster — there is no in-cluster redundancy. Data is not lost as long as the reassignment completes before shutdown.
- Run every command as the kafka OS user unless stated otherwise.
- Read through all steps once before executing anything.

## Variables — Set These First

Replace all placeholder values below before running any command. These are referenced throughout the runbook.

```bash
# ── Fill in before you start ──────────────────────────────────────
export SURVIVOR_IP=<SURVIVOR_IP>              # e.g. 10.170.34.169
export SURVIVOR_BROKER_ID=<ID>                # e.g. 1
export REMOVE_IP=<REMOVE_IP>                  # e.g. 10.170.34.165
export REMOVE_BROKER_ID=<ID>                  # e.g. 2
export BOOTSTRAP=${REMOVE_IP}:9093             # used while both nodes are up
export ZK_REMOVE=${REMOVE_IP}:2181             # ZooKeeper on node being removed
export ZK_SURVIVOR=${SURVIVOR_IP}:2181         # ZooKeeper on surviving node
export KAFKA_BIN=$K2_HOME/kafka/bin            # adjust if different
export KAFKA_CFG=$K2_HOME/kafka                # parent of config/
```

## 1. Confirm Broker IDs

Run this from either node to confirm the broker IDs of both members of the cluster.

```bash
$KAFKA_BIN/kafka-broker-api-versions.sh \
  --bootstrap-server $BOOTSTRAP 2>/dev/null | grep "id:"
```

You should see two lines — one for each broker. Note which IP maps to which broker ID and confirm they match your variables above.

> **⚠ Warning**
>
> If only one broker appears here, one of the nodes is already down. Do not proceed — investigate first.

## 2. Describe Topics and Confirm Partition Distribution

Get a full picture of which partitions live on which broker before making any changes.

```bash
# Save a full topic describe — useful as a rollback reference
$KAFKA_BIN/kafka-topics.sh \
  --bootstrap-server $BOOTSTRAP --describe > /tmp/topics_before.txt

# Quick view: which broker IDs currently hold partitions?
$KAFKA_BIN/kafka-topics.sh \
  --bootstrap-server $BOOTSTRAP --describe \
  | grep -E "Leader|Replicas|Isr" | head -30

# Confirm your ZooKeeper connect string
grep "zookeeper.connect" $KAFKA_CFG/server.properties
```

> **ℹ Note**
>
> Keep /tmp/topics_before.txt — you will need it if you ever need to roll back the partition assignments.

## 3. Build the topics.json File

This file tells Kafka which topics to move. The easiest way is to generate it automatically from the describe output saved in the previous step.

```bash
# Auto-generate topics.json from the describe file
python3 - << 'PYGEN'
import re, json
topics = set()
with open('/tmp/topics_before.txt') as f:
    for line in f:
        m = re.search(r'Topic:\s+(\S+)\s+Partition:', line)
        if m:
            topics.add(m.group(1))
out = {'topics': [{'topic': t} for t in sorted(topics)], 'version': 1}
print(json.dumps(out, indent=2))
PYGEN > /tmp/topics.json

# Verify: check the file has content
wc -l /tmp/topics.json
head -5 /tmp/topics.json
```

> **✔ Tip**
>
> The file should be several KB and contain every topic name. If it is empty, check that /tmp/topics_before.txt was written correctly in Step 2.

## 4. Generate the Partition Reassignment Plan

This command calculates a plan to move all partitions from the node being removed onto the surviving broker. It does not make any changes yet.

> **⚠ Warning**
>
> Older Kafka versions (pre-2.4) require --zookeeper instead of --bootstrap-server for kafka-reassign-partitions.sh. If you get 'Missing required argument [zookeeper]', use the ZooKeeper form below.

Modern Kafka (2.4+):

```bash
$KAFKA_BIN/kafka-reassign-partitions.sh \
  --bootstrap-server $BOOTSTRAP \
  --topics-to-move-json-file /tmp/topics.json \
  --broker-list "$SURVIVOR_BROKER_ID" \
  --generate > /tmp/reassignment_raw.txt
```

Older Kafka (requires --zookeeper):

```bash
$KAFKA_BIN/kafka-reassign-partitions.sh \
  --zookeeper $ZK_REMOVE \
  --topics-to-move-json-file /tmp/topics.json \
  --broker-list "$SURVIVOR_BROKER_ID" \
  --generate > /tmp/reassignment_raw.txt
```

The raw output contains two JSON blocks. Extract only the proposed plan (the second block):

```bash
awk '/Proposed partition reassignment configuration/{found=1; next} found{print}' \
  /tmp/reassignment_raw.txt > /tmp/reassignment.json

# Verify the file has content — should be several KB
ls -lh /tmp/reassignment.json
```

> **⚠ Warning**
>
> Do not use 'tail -n +3' to extract the JSON — it can produce an empty file depending on whitespace in the output. Always use the awk command above.

## 5. Execute the Reassignment

Apply the reassignment plan. This starts moving partition data to the surviving broker in the background.

Modern Kafka:

```bash
$KAFKA_BIN/kafka-reassign-partitions.sh \
  --bootstrap-server $BOOTSTRAP \
  --reassignment-json-file /tmp/reassignment.json \
  --execute
```

Older Kafka (--zookeeper):

```bash
$KAFKA_BIN/kafka-reassign-partitions.sh \
  --zookeeper $ZK_REMOVE \
  --reassignment-json-file /tmp/reassignment.json \
  --execute
```

Monitor progress until every partition reports 'completed successfully':

```bash
# Run this repeatedly until all partitions show 'completed successfully'
$KAFKA_BIN/kafka-reassign-partitions.sh \
  --zookeeper $ZK_REMOVE \
  --reassignment-json-file /tmp/reassignment.json \
  --verify
```

Once verify completes, confirm no partitions remain on the broker being removed:

```bash
# This must return NO output before you proceed
$KAFKA_BIN/kafka-topics.sh \
  --bootstrap-server $BOOTSTRAP --describe \
  | grep "Leader: $REMOVE_BROKER_ID"
```

> **✔ Tip**
>
> Only move to Step 6 when the grep above returns absolutely nothing. Any remaining output means partitions are still on the node you are about to remove.

## 6. Stop Services on the Node Being Removed

> **⚠ Warning**
>
> Run these commands only on the node being removed — NOT on the surviving node.

```bash
ssh kafka@${REMOVE_IP}

# Stop Kafka first and give it time to flush
sudo systemctl stop kafka
sleep 20

# Then stop ZooKeeper
sudo systemctl stop zookeeper
```

## 7. Update Configuration on the Surviving Node

SSH into the surviving node and remove all references to the node being decommissioned from both config files.

Edit server.properties:

```bash
ssh kafka@${SURVIVOR_IP}
nano $KAFKA_CFG/server.properties

# Change zookeeper.connect from:
# zookeeper.connect=<ZK_IP1>:2181,<ZK_IP2>:2181
# To:
# zookeeper.connect=${SURVIVOR_IP}:2181
```

Edit zookeeper.properties:

```bash
nano $KAFKA_CFG/config/zookeeper.properties

# Remove the server.X line for the node being removed
# Keep only the surviving node entry, for example:
# server.1=${SURVIVOR_IP}:2888:3888
```

## 8. Restart Services on the Surviving Node

```bash
sudo systemctl restart zookeeper
sleep 15

$KAFKA_BIN/kafka-server-start.sh -daemon $KAFKA_CFG/server.properties
sleep 15

# Confirm both processes are running
jps

# Expected: QuorumPeerMain, SupportedKafka (or KafkaServer)
```

## 9. Fix Stale ZooKeeper Ephemeral Node (If Kafka Fails to Start)

> **✖ Important**
>
> If Kafka fails to start with the error 'KeeperErrorCode = NodeExists' on /brokers/ids/\<ID\>, the old ZooKeeper session from the removed node still holds that slot. Follow the steps below to clear it.

Open a ZooKeeper shell on the surviving node:

```bash
$KAFKA_BIN/zookeeper-shell.sh $ZK_SURVIVOR
```

Inside the ZooKeeper shell:

```text
# Check what broker IDs are registered
ls /brokers/ids

# Delete the stale entry (replace 1 with your surviving broker ID)
deleteall /brokers/ids/<SURVIVOR_BROKER_ID>

# Confirm it is cleared
ls /brokers/ids    # should return []

quit
```

Restart Kafka after clearing the stale node:

```bash
$KAFKA_BIN/kafka-server-start.sh -daemon $KAFKA_CFG/server.properties
sleep 15
jps
```

## 10. Validate the Single-Node Cluster

Run all of the following checks on the surviving node. All should pass before you proceed to cleanup.

```bash
# 1. Only the surviving broker should appear
$KAFKA_BIN/kafka-broker-api-versions.sh \
  --bootstrap-server ${SURVIVOR_IP}:9093 2>/dev/null | grep "id:"

# 2. ZooKeeper should report standalone mode
echo "stat" | nc ${SURVIVOR_IP} 2181 | grep Mode

# 3. No under-replicated partitions
$KAFKA_BIN/kafka-topics.sh \
  --bootstrap-server ${SURVIVOR_IP}:9093 --describe | grep UnderReplicated

# 4. Broker is registered in ZooKeeper
$KAFKA_BIN/zookeeper-shell.sh $ZK_SURVIVOR ls /brokers/ids

# 5. All topics still present
$KAFKA_BIN/kafka-topics.sh --bootstrap-server ${SURVIVOR_IP}:9093 --list | wc -l
```

<table>
  <thead>
    <tr>
      <th>Check</th>
      <th>Expected Result</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Broker API versions</td>
      <td>Only id: <code>&lt;SURVIVOR_BROKER_ID&gt;</code></td>
    </tr>
    <tr>
      <td>ZooKeeper mode</td>
      <td>standalone</td>
    </tr>
    <tr>
      <td>Under-replicated partitions</td>
      <td>No output (empty)</td>
    </tr>
    <tr>
      <td>ZooKeeper /brokers/ids</td>
      <td>[<code>&lt;SURVIVOR_BROKER_ID&gt;</code>]</td>
    </tr>
    <tr>
      <td>Topic count</td>
      <td>Same count as before migration</td>
    </tr>
  </tbody>
</table>

## 11. Clean Up the Removed Node

> **⚠ Warning**
>
> Only run this after all checks in Step 10 pass. Once you delete Kafka data, rollback is not possible.

```bash
ssh kafka@${REMOVE_IP}

# Disable services from auto-starting on reboot
sudo systemctl disable kafka
sudo systemctl disable zookeeper

# Remove Kafka installation and data
sudo rm -rf $K2_HOME/kafka

# Remove systemd unit files if they were created
sudo rm -f /etc/systemd/system/kafka.service
sudo rm -f /etc/systemd/system/zookeeper.service
sudo systemctl daemon-reload
```

## 12. Update Client Configuration

Update the bootstrap.servers setting in all producers and consumers that pointed to the old two-node cluster.

<table>
  <thead>
    <tr>
      <th>Setting</th>
      <th>Value</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Old bootstrap.servers</td>
      <td><code>&lt;REMOVE_IP&gt;:9093,&lt;SURVIVOR_IP&gt;:9093</code></td>
    </tr>
    <tr>
      <td>New bootstrap.servers</td>
      <td><code>&lt;SURVIVOR_IP&gt;:9093</code></td>
    </tr>
  </tbody>
</table>

> **✔ Tip**
>
> Clients that still reference the removed broker IP will fail to connect for that address but will fall back to the surviving broker if it is still listed. Update them at your earliest opportunity to avoid confusion.

## Execution Checklist

<table>
  <thead>
    <tr>
      <th>✓</th>
      <th>Action</th>
      <th>Run On</th>
      <th>Status</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>☐</td><td>Both brokers confirmed online before starting</td><td>Either node</td><td></td></tr>
    <tr><td>☐</td><td>Broker IDs confirmed and variables exported</td><td>Either node</td><td></td></tr>
    <tr><td>☐</td><td>Full topic describe saved to /tmp/topics_before.txt</td><td>Either node</td><td></td></tr>
    <tr><td>☐</td><td>topics.json generated and verified non-empty</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>Reassignment plan generated (--zookeeper flag if needed)</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>reassignment.json confirmed non-empty (several KB)</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>Partition reassignment executed</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>All partitions verified 'completed successfully'</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>grep 'Leader: &lt;REMOVE_ID&gt;' returns no output</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>Kafka stopped on removed node</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>ZooKeeper stopped on removed node</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>zookeeper.connect updated in server.properties on survivor</td><td>Survivor node</td><td></td></tr>
    <tr><td>☐</td><td>server.X entry removed from zookeeper.properties on survivor</td><td>Survivor node</td><td></td></tr>
    <tr><td>☐</td><td>ZooKeeper restarted on survivor</td><td>Survivor node</td><td></td></tr>
    <tr><td>☐</td><td>Kafka restarted on survivor</td><td>Survivor node</td><td></td></tr>
    <tr><td>☐</td><td>Stale /brokers/ids cleared if Kafka failed to start</td><td>Survivor node</td><td>N/A if not needed</td></tr>
    <tr><td>☐</td><td>Only survivor broker visible in broker-api-versions</td><td>Survivor node</td><td></td></tr>
    <tr><td>☐</td><td>ZooKeeper reports standalone mode</td><td>Survivor node</td><td></td></tr>
    <tr><td>☐</td><td>No under-replicated partitions</td><td>Survivor node</td><td></td></tr>
    <tr><td>☐</td><td>Topic count matches pre-migration count</td><td>Survivor node</td><td></td></tr>
    <tr><td>☐</td><td>Kafka and ZooKeeper disabled and removed from removed node</td><td>Remove node</td><td></td></tr>
    <tr><td>☐</td><td>bootstrap.servers updated in all producers and consumers</td><td>Client systems</td><td></td></tr>
  </tbody>
</table>

## Known Issues & Resolutions

<table>
  <thead>
    <tr>
      <th>Issue Encountered</th>
      <th>Root Cause</th>
      <th>Fix Applied</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>reassignment.json is empty after redirect</td>
      <td>Output format of --generate includes a header block before the JSON. Using tail -n +3 skips too many or too few lines depending on whitespace.</td>
      <td>Use the awk command to extract only the block after 'Proposed partition reassignment configuration'.</td>
    </tr>
    <tr>
      <td>kafka-reassign-partitions.sh fails with 'Missing required argument [zookeeper]'</td>
      <td>Older Kafka versions do not support --bootstrap-server for partition reassignment commands.</td>
      <td>Replace --bootstrap-server with --zookeeper &lt;ZK_IP&gt;:2181 in all kafka-reassign-partitions.sh calls.</td>
    </tr>
    <tr>
      <td>Kafka on survivor fails to start: KeeperErrorCode = NodeExists on /brokers/ids/&lt;ID&gt;</td>
      <td>The removed node's ZooKeeper session had not expired yet, leaving an ephemeral node behind that conflicts with the restarting broker.</td>
      <td>Open zookeeper-shell, run 'deleteall /brokers/ids/&lt;ID&gt;', quit, then start Kafka again.</td>
    </tr>
  </tbody>
</table>

## Rollback

Rollback is only possible before Step 11 (cleanup). Once Kafka files are deleted from the removed node, there is no automated rollback.

- Restart Kafka and ZooKeeper on the removed node.
- Use /tmp/topics_before.txt to regenerate a reassignment plan that moves partitions back to their original brokers.
- Revert server.properties and zookeeper.properties on the surviving node to the original two-node configuration.
- Restart both services on the surviving node.

