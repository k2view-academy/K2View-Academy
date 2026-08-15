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

# Runbook: Adding a Cassandra Node

## Overview

This runbook describes the end-to-end procedure for adding a new node to an existing Apache Cassandra cluster.

Complete the sections in order: prepare and configure the new node, bring it online, confirm that it joined the cluster, and rebalance data on the pre-existing nodes.

> **Important:** The new node configuration must match the existing Cassandra nodes. Before starting the node, compare `cassandra.yaml`, `cassandra-rackdc.properties`, `jvm.options`, and `cassandra-env.sh` with a current cluster member and align every relevant setting. Only node-specific values, such as `listen_address` and `broadcast_rpc_address`, should differ. Mismatched configuration is a common cause of a node failing to join.

In the examples below:

- `$INSTALL_DIR` is the Cassandra installation directory.
- `$clustername` is the cluster name.
- `$seedslist` is the seed list.
- `$dcname` is the data center name.

Set these values to match your environment before running the commands.

## 1. Prepare the New Node

- Install the same Cassandra version used by the existing cluster.
- Ensure that the hardware specification matches the existing nodes.
- Locate the Cassandra configuration files:

```bash
$INSTALL_DIR/cassandra/conf/
```

## 2. Configure cassandra.yaml

Edit `cassandra.yaml` on the new node so that its settings align with the rest of the cluster.

### Cluster Name

Set `cluster_name` to match the existing cluster name.

```bash
sed -i 's@cluster_name: .*@cluster_name: '$clustername'@' \
  $INSTALL_DIR/cassandra/conf/cassandra.yaml
```

### Seeds

Specify the IP addresses or hostnames of at least a few existing cluster nodes in the `seeds` parameter. These seed nodes allow the new node to discover the existing cluster.

```bash
sed -i s/seeds:.*/"seeds: $seedslist"/g \
  $INSTALL_DIR/cassandra/conf/cassandra.yaml
```

### Network Addresses

Set `listen_address` and `broadcast_rpc_address` to the new node's IP address.

```bash
sed -i s/listen_address:.*/"listen_address: $(hostname -I | awk '{print $1}')"/g \
  $INSTALL_DIR/cassandra/conf/cassandra.yaml

sed -i s/broadcast_rpc_address:.*/"broadcast_rpc_address: $(hostname -I | awk '{print $1}')"/g \
  $INSTALL_DIR/cassandra/conf/cassandra.yaml
```

### Data Directories

Verify that the configured data directory paths match the other nodes in the cluster.

### Snitch

Verify that `endpoint_snitch` matches the existing cluster nodes.

If the cluster uses `GossipingPropertyFileSnitch`, for example:

```bash
sed -i 's@endpoint_snitch:.*@endpoint_snitch: GossipingPropertyFileSnitch@' \
  $INSTALL_DIR/cassandra/conf/cassandra.yaml
```

## 3. Configure cassandra-rackdc.properties

Set the data center name in `cassandra-rackdc.properties` to match the cluster.

```bash
sed -i 's@dc=.*@dc='$dcname'@' \
  $INSTALL_DIR/cassandra/conf/cassandra-rackdc.properties
```

## 4. Review JVM and Environment Settings

Review `jvm.options` and ensure that it matches the other nodes in the cluster:

```text
$INSTALL_DIR/cassandra/conf/
```

Review and modify `cassandra-env.sh` so that it matches the other nodes in the cluster:

```text
$INSTALL_DIR/cassandra/conf/
```

## 5. Start Cassandra on the New Node

Start the Cassandra service on the newly prepared node.

```bash
cassandra
```

Monitor the logs to ensure that the new node successfully joins the cluster.

## 6. Verify the Node Joined the Cluster

From any existing node, run:

```bash
nodetool status
```

Confirm that the new node is listed and reports a status of `UN` (Up / Normal).

## 7. Run Cleanup on the Existing Nodes

After the new node is Up / Normal, run `nodetool cleanup` on the older, pre-existing nodes to remove keys that no longer belong to them after token ranges have been rebalanced.

```bash
nodetool cleanup
```

> **Tip:** Run cleanup one node at a time during a low-traffic window. Cleanup is I/O intensive and can temporarily affect performance.
