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

# Runbook: Adding a Kafka Broker to an Existing Cluster

## Overview

This runbook describes how to add a new broker to an existing ZooKeeper-based Kafka cluster. No downtime is required; the new broker joins the running cluster.

Replace `<new-broker-host>`, the ZooKeeper hosts (`zk1`, `zk2`, `zk3`), the existing broker address, broker IDs, and Kafka version and paths with values appropriate for your environment.

Commands assume you are working from the Kafka installation directory.

## 1. Prepare the New Server

Configure the new server consistently with the existing brokers, including the operating system, resources, mount points, and network configuration.

Ensure that the required ports are open:

- `9092` for Kafka clients
- `2181` to reach ZooKeeper

Install the same Kafka version used by the existing cluster.

```bash
# Use the SAME Kafka version as the existing brokers
wget https://downloads.apache.org/kafka/3.7.0/kafka_2.13-3.7.0.tgz
tar -xzf kafka_2.13-3.7.0.tgz
cd kafka_2.13-3.7.0
```

## 2. Configure server.properties

Edit `config/server.properties` on the new node.

Set a unique `broker.id`, point the broker to the same ZooKeeper ensemble, and configure the listener addresses and log directory.

```properties
# Unique ID — must NOT match any existing broker
broker.id=4

# Listener addresses for this node
listeners=PLAINTEXT://0.0.0.0:9092
advertised.listeners=PLAINTEXT://<new-broker-host>:9092

# Data directory — must exist and be empty
log.dirs=/var/lib/kafka/logs

# SAME ZooKeeper connection string as the existing brokers
# Include the chroot path, such as /kafka, if the cluster uses one
zookeeper.connect=zk1:2181,zk2:2181,zk3:2181/kafka
```

## 3. Start the Broker

Start Kafka. The broker registers itself in ZooKeeper and joins the cluster automatically.

```bash
bin/kafka-server-start.sh -daemon config/server.properties

# Tail the log to confirm a clean startup
tail -f logs/server.log
```

## 4. Confirm the Broker Joined

Verify that the new broker ID is registered in ZooKeeper and visible to the cluster.

```bash
# List registered broker IDs in ZooKeeper; the new ID should appear
bin/zookeeper-shell.sh zk1:2181 ls /brokers/ids
```

Alternatively, query the broker directly:

```bash
bin/kafka-broker-api-versions.sh \
  --bootstrap-server <new-broker-host>:9092
```

## 5. Rebalance Partitions onto the New Broker

A new broker holds no data until partitions are moved to it. Generate a reassignment plan, execute it with a throttle, and then verify completion.

### a. Create the Topic List

Create `topics-to-move.json` containing the topics to move:

```json
{"topics": [{"topic": "my-topic"}], "version": 1}
```

### b. Generate a Reassignment Plan

Generate a proposed reassignment across all brokers, including the new broker ID:

```bash
bin/kafka-reassign-partitions.sh \
  --bootstrap-server <existing-broker>:9092 \
  --topics-to-move-json-file topics-to-move.json \
  --broker-list "1,2,3,4" \
  --generate
```

### c. Execute the Reassignment

Save the proposed plan as `reassignment.json`, then execute it with a throttle to limit production impact:

```bash
bin/kafka-reassign-partitions.sh \
  --bootstrap-server <existing-broker>:9092 \
  --reassignment-json-file reassignment.json \
  --throttle 50000000 \
  --execute
```

### d. Verify Completion

Verify progress and rerun the command until the reassignment reports completion:

```bash
bin/kafka-reassign-partitions.sh \
  --bootstrap-server <existing-broker>:9092 \
  --reassignment-json-file reassignment.json \
  --verify
```

Running `--verify` to completion also clears the throttle applied by `--execute`.

## 6. Verify Cluster Health

Confirm that there are no under-replicated partitions and that the new broker is carrying load.

```bash
# Should return nothing when the cluster is healthy
bin/kafka-topics.sh \
  --bootstrap-server <existing-broker>:9092 \
  --describe \
  --under-replicated-partitions
```

Inspect a topic to confirm that the new broker appears in the replica and ISR lists:

```bash
bin/kafka-topics.sh \
  --bootstrap-server <existing-broker>:9092 \
  --describe \
  --topic my-topic
```

## Important Notes

- Run the rebalance in Step 5 during a low-traffic window.
- The `--throttle` value caps replication bandwidth per broker. Raise or lower it to balance migration speed against production impact.
- Always run `--verify` to completion. It confirms that the move has finished and clears the throttle applied by `--execute`.
- No downtime is required; clients continue operating while the broker joins and data is rebalanced.
- Confirm that ZooKeeper is healthy before starting and that all ensemble nodes are reachable and synchronized.
