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

# Runbook: Converting a Fabric Cluster to a Single Node

## Purpose

This runbook provides step-by-step instructions for removing Fabric nodes and configuring a single-node Fabric environment safely.

## Prerequisites

- Ensure that an appropriate maintenance window is approved.
- Ensure that all application teams are informed.
- Ensure that backups are completed before any node removal.
- Ensure that SSH access is available to all Fabric and Cassandra nodes.
- Verify that sufficient disk space is available at the backup destination.

## 1. Verify Running Jobs

1. Verify whether any Fabric jobs are running.
2. Stop all running jobs before beginning node maintenance.
3. Confirm that no active processes remain.

Example:

```bash
ps -ef | grep fabric
```

## 2. Back Up the Fabric Data Directory

Create the backup directory:

```bash
mkdir -p /backup/fabric
```

Back up the Fabric installation and data directories:

```bash
tar -czvf /backup/fabric/fabric_backup_$(date +%F).tar.gz /opt/fabric
```

Back up the configuration and data directories:

```bash
cp -rp /opt/fabric/conf /backup/fabric/
cp -rp /opt/fabric/data /backup/fabric/
```

## 3. Remove an Old Fabric Node

For each Fabric node being removed:

1. Stop the Fabric service on the node.
2. Remove the node entry from the Cassandra table.
3. Validate that the node has been removed.
4. Shut down the old node if required.

Remove the Fabric node entry from Cassandra:

```sql
DELETE FROM k2system.nodes
WHERE uid='NODE_UID';
```

Repeat the procedure as required until only the intended Fabric node remains.

## Rollback Plan

If rollback is required:

- Restore the backup tar files.
- Restore the configuration files.
- Restart the old Fabric node if needed.
- Restore the Cassandra node entry if required.
