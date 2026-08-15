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

# Runbook: Converting a Cassandra Cluster to a Single Node

## Scope

This runbook describes the procedure for reducing a multi-node Cassandra deployment across one or more data centers to a single surviving node.

Before beginning:

- Identify the node that will remain in the Cassandra ring.
- Stop the application before performing the topology change.
- Keep Cassandra running during node decommissioning.
- Run repair and required garbage collection before removing nodes.
- Back up configuration and data directories on every Cassandra node before making changes.

Replace the placeholders in this runbook with values appropriate for the environment.

## 1. Define Environment Values

Identify the following values before starting:

<table>
  <thead>
    <tr>
      <th>Item</th>
      <th>Value</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>Survivor node</td><td><code>&lt;survivor-node&gt;</code></td></tr>
    <tr><td>Survivor data center</td><td><code>&lt;survivor-dc&gt;</code></td></tr>
    <tr><td>Cassandra home</td><td><code>&lt;cassandra-home&gt;</code></td></tr>
    <tr><td>Cassandra bin</td><td><code>&lt;cassandra-bin&gt;</code></td></tr>
    <tr><td>Cassandra conf</td><td><code>&lt;cassandra-conf&gt;</code></td></tr>
    <tr><td>Data directory</td><td><code>&lt;cassandra-data&gt;</code></td></tr>
    <tr><td>Commit log directory</td><td><code>&lt;cassandra-commitlog&gt;</code></td></tr>
    <tr><td>Saved caches directory</td><td><code>&lt;cassandra-saved-caches&gt;</code></td></tr>
    <tr><td>Backup root</td><td><code>/backup/cassandra_single_node_$(date +%Y%m%d_%H%M%S)</code></td></tr>
  </tbody>
</table>

## 2. Run Pre-checks on the Survivor Node

From the Cassandra `bin` directory:

```bash
cd <cassandra-bin>

nodetool status
nodetool describecluster
nodetool netstats
nodetool compactionstats
nodetool tpstats
```

Review keyspace replication:

```bash
cqlsh <survivor-node> 9042 -e \
  "SELECT keyspace_name, replication FROM system_schema.keyspaces;"
```

Proceed only when:

- All nodes are `UN` (Up / Normal).
- No node is joining, leaving, or moving.
- The survivor node has sufficient disk capacity for the complete final data set.

## 3. Back Up Configuration and Data on Every Node

Run this section on **every Cassandra node before any node is decommissioned**.

Do not delete these backups or the old node data directories until final sign-off.

```bash
export BACKUP_ROOT=/backup/cassandra_single_node_$(date +%Y%m%d_%H%M%S)

mkdir -p ${BACKUP_ROOT}/config ${BACKUP_ROOT}/data
```

Back up the Cassandra configuration:

```bash
tar czf ${BACKUP_ROOT}/config/cassandra-config-$(hostname -f).tgz \
  <cassandra-conf> \
  <cassandra-bin> \
  <cassandra-lib> 2>/dev/null || true
```

Back up the Cassandra data:

```bash
tar czf ${BACKUP_ROOT}/data/cassandra-data-$(hostname -f).tgz \
  <cassandra-data> \
  <cassandra-commitlog> \
  <cassandra-saved-caches> 2>/dev/null || true
```

Save the current cluster status and schema from a reachable node:

```bash
cd <cassandra-bin>

nodetool status > ${BACKUP_ROOT}/nodetool_status_before.txt
nodetool describecluster > ${BACKUP_ROOT}/describecluster_before.txt

cqlsh <survivor-node> 9042 -e "DESCRIBE SCHEMA" \
  > ${BACKUP_ROOT}/schema_before.cql

cqlsh <survivor-node> 9042 -e \
  "SELECT keyspace_name, replication FROM system_schema.keyspaces;" \
  > ${BACKUP_ROOT}/keyspace_replication_before.txt
```

## 4. Repair and Garbage Collection Before Node Removal

From the survivor node, run a full repair before starting decommission:

```bash
cd <cassandra-bin>

nodetool repair --full
```

Run garbage collection for the required application keyspaces and tables. Repeat as needed:

```bash
nodetool garbagecollect -g CELL <keyspace> <table>
```

Check cluster activity:

```bash
nodetool compactionstats
nodetool netstats
```

## 5. Prepare Keyspace Replication

Before reducing the cluster to one node, ensure that application keyspaces will not require replicas from data centers that will be removed after the topology change.

> **Important:** Do not change system keyspaces unless required by the Cassandra version and a tested procedure.

After the node removals, application keyspaces should use a replication factor of 1 on the surviving data center only.

For each application keyspace:

```sql
ALTER KEYSPACE <app_keyspace>
WITH replication = {
  'class': 'NetworkTopologyStrategy',
  '<survivor-dc>': '1'
};
```

Verify the replication configuration:

```sql
SELECT keyspace_name, replication
FROM system_schema.keyspaces;
```

## 6. Decommission All Nodes Except the Survivor

Run the decommission commands **from the node being removed**.

Remove **one node at a time**. Do not perform parallel decommissions.

```bash
cd <cassandra-bin>

nodetool disablebinary
nodetool disablethrift 2>/dev/null || true
nodetool drain
nodetool decommission
```

After each node is decommissioned, monitor the cluster from the survivor:

```bash
cd <cassandra-bin>

nodetool status
nodetool netstats
nodetool compactionstats
```

Verify the cluster status and wait for streaming to finish before proceeding to the next node.

Repeat this procedure until only the designated survivor remains.

## 7. Keep Only the Survivor as a Seed

After the ring has been reduced to the survivor node, update the seed list on that node.

Back up `cassandra.yaml` before editing it:

```bash
cd <cassandra-conf>

cp cassandra.yaml cassandra.yaml.before_single_node_$(date +%Y%m%d_%H%M%S)
```

Update the seed configuration so that it contains only the survivor:

```yaml
seed_provider:
  - class_name: org.apache.cassandra.locator.SimpleSeedProvider
    parameters:
      - seeds: "<survivor-node>"
```

Start Cassandra:

```bash
cassandra
```

Alternatively, use the service-management command appropriate for the environment.

Verify the node status:

```bash
cd <cassandra-bin>

nodetool status
```

## 8. Final Validation

From the survivor node:

```bash
cd <cassandra-bin>

nodetool status
nodetool describecluster
nodetool netstats
nodetool compactionstats
```

Review keyspace replication:

```bash
cqlsh <survivor-node> 9042 -e \
  "SELECT keyspace_name, replication FROM system_schema.keyspaces;"
```

Confirm that:

- `nodetool status` shows only the survivor node as `UN`.
- All application keyspaces use RF=1.
- No streaming is active.
- The application is started only after validation succeeds.

## 9. Rollback Options

### Case A: Issue Before Any Node Is Decommissioned

If no node has been decommissioned, restore any configuration that was changed from backup.

No topology rollback is required if no node has been decommissioned.

### Case B: Decommission Fails on a Node

Do not delete data on the affected node.

Capture logs and cluster status first:

```bash
cd <cassandra-bin>

nodetool status
nodetool netstats
```

If the node is still part of the ring, resolve the error and retry the decommission.

If the node is permanently unavailable and must not rejoin, `nodetool removenode` may be used from a healthy node only after approval:

```bash
nodetool removenode <host-id>
```

### Case C: Roll Back After One or More Nodes Were Removed

There is no simple undo after a successful decommission.

Do not restart removed nodes into the cluster without a reviewed rebuild/rejoin plan.

The source procedure identifies restoring from the retained configuration and data backups into the original topology, or rebuilding nodes cleanly, as the rollback approach.

Example restoration sequence:

```bash
# Stop Cassandra on the affected node
systemctl stop cassandra

# Restore configuration and data from backup
rm -rf <cassandra-conf> <cassandra-data> <cassandra-commitlog> <cassandra-saved-caches>

tar xzf <backup>/config/cassandra-config-<host>.tgz -C /
tar xzf <backup>/data/cassandra-data-<host>.tgz -C /

# Start Cassandra only after the topology and seed plan is approved
systemctl start cassandra
```

### Case D: Replication Change Causes a CQL or Quorum Problem

Use the saved `keyspace_replication_before.txt` file to restore the exact previous replication factors.

Example:

```sql
ALTER KEYSPACE <app_keyspace>
WITH replication = {
  'class': 'NetworkTopologyStrategy',
  '<original-dc-1>': '<old-rf>',
  '<original-dc-2>': '<old-rf>'
};
```

## 10. Sign-off Checklist

- Backups completed on every node.
- Repair and required garbage collection completed before removal.
- All nodes except the designated survivor have been decommissioned.
- The survivor is the only configured seed.
- Application keyspaces use RF=1.
- `nodetool status` shows only the survivor as `UN`.
- Rollback backups are retained until business sign-off.
