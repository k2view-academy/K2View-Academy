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

# Runbook: Consolidating Cassandra Data Centers

## Overview

This runbook describes a procedure for consolidating Cassandra nodes from one data center into another without data loss.

The source procedure assumes:

- Six Cassandra nodes are co-located in the same physical location.
- Three nodes are assigned to `DC1`.
- Three nodes are assigned to `DC2`.
- Both data centers use a replication factor of 3 and therefore already hold a complete copy of the data.

Before using this procedure, confirm that these assumptions match the target environment.

## Rules Before You Start

- Move **one source data center node at a time**.
- Never stop two nodes simultaneously.
- Always wait for `UN` (Up / Normal) status before proceeding to the next node.
- Remove the `-Dcassandra.ignore_dc=true` flag immediately after each node successfully joins the target data center.

## Migration Procedure

Repeat Steps 2 through 5 for each node in the source data center, one at a time.

<table>
  <thead>
    <tr>
      <th>Step</th>
      <th>Action</th>
      <th>Command / Note</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>Verify cluster health — all nodes are UN and no repairs or streaming operations are in progress.</td>
      <td><code>nodetool status</code><br><code>nodetool netstats</code></td>
    </tr>
    <tr>
      <td>2</td>
      <td>Stop Cassandra on the source data center node.</td>
      <td>Use the service-management command appropriate for your environment.</td>
    </tr>
    <tr>
      <td>3</td>
      <td>Change <code>dc=</code> to the target data center in <code>cassandra-rackdc.properties</code>.</td>
      <td>For example: <code>dc=DC1</code></td>
    </tr>
    <tr>
      <td>4</td>
      <td>Add the <code>ignore_dc</code> flag to <code>cassandra-env.sh</code>, then start Cassandra.</td>
      <td><code>JVM_OPTS="$JVM_OPTS -Dcassandra.ignore_dc=true"</code></td>
    </tr>
    <tr>
      <td>5</td>
      <td>Wait for UN status, remove the <code>ignore_dc</code> flag, and restart Cassandra.</td>
      <td><code>watch -n5 nodetool status</code></td>
    </tr>
    <tr>
      <td>6</td>
      <td>After all nodes have moved to the target data center, update keyspace replication.</td>
      <td><code>ALTER KEYSPACE ...</code></td>
    </tr>
    <tr>
      <td>7</td>
      <td>Run a full repair on all nodes.</td>
      <td><code>nodetool repair -full</code></td>
    </tr>
    <tr>
      <td>8</td>
      <td>Run cleanup on all nodes.</td>
      <td><code>nodetool cleanup</code></td>
    </tr>
  </tbody>
</table>

The sections below provide the detailed procedure for each step.

### 1. Verify Cluster Health

Confirm that all nodes are `UN` and that no repairs or streaming operations are in progress.

```bash
nodetool status
nodetool netstats
```

### 2. Stop Cassandra on the Source Node

Stop Cassandra on the node being moved.

Use the service-management command appropriate for your environment.

### 3. Change the Data Center

Edit `cassandra-rackdc.properties` on the node and change its data center to the target data center.

For example:

```properties
dc=DC1
rack=rack1
```

### 4. Temporarily Enable ignore_dc

Add the following option to `cassandra-env.sh`:

```bash
JVM_OPTS="$JVM_OPTS -Dcassandra.ignore_dc=true"
```

Start Cassandra.

### 5. Wait for Up / Normal Status

Monitor cluster status:

```bash
watch -n5 nodetool status
```

Wait until the node reports `UN`.

Remove the temporary `-Dcassandra.ignore_dc=true` option from `cassandra-env.sh`, and restart Cassandra.

Repeat Steps 2 through 5 for each remaining source data center node.

### 6. Update Keyspace Replication

After all nodes have moved to the target data center, update the replication configuration for each applicable keyspace.

Example:

```sql
ALTER KEYSPACE your_keyspace
WITH REPLICATION = {'class': 'NetworkTopologyStrategy', 'DC1': '3'};
```

The source procedure also identifies the following system keyspaces for replication updates:

```sql
ALTER KEYSPACE system_auth
WITH REPLICATION = {'class': 'NetworkTopologyStrategy', 'DC1': '3'};

ALTER KEYSPACE system_distributed
WITH REPLICATION = {'class': 'NetworkTopologyStrategy', 'DC1': '3'};

ALTER KEYSPACE system_traces
WITH REPLICATION = {'class': 'NetworkTopologyStrategy', 'DC1': '3'};
```

### 7. Run a Full Repair

Run a full repair on the nodes:

```bash
nodetool repair -full
```

### 8. Run Cleanup

Run cleanup:

```bash
nodetool cleanup
```

## Rollback

If a node fails to join the target data center:

1. Stop Cassandra.
2. Change `dc` in `cassandra-rackdc.properties` back to the original data center.
3. Remove `-Dcassandra.ignore_dc=true` from `cassandra-env.sh`.
4. Start Cassandra.

Do not proceed to another node until cluster health has been restored.
