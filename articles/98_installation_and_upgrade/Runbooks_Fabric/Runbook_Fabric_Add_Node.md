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

# Runbook: Adding a Fabric Node to a Cluster

## Purpose

This runbook describes how to add a new node to an existing Fabric cluster.

Follow the steps in the order shown to ensure that the new node joins the cluster with the correct configuration and a unique node identity.

## Prerequisites

- Network access and credentials for the new node.
- Access to the configuration files used on the existing cluster nodes.
- The same Fabric package version that is running on the existing nodes.

## Procedure

### 1. Confirm the Fabric Package Version

Ensure that the new node has the same Fabric package version as the existing nodes in the cluster.

### 2. Extract the Fabric Package

Extract the Fabric package on the new node.

### 3. Match the Configuration Files

Confirm that `config.ini` and `JVM.options` on the new node contain the same configuration as the existing nodes.

### 4. Verify a Unique Node ID

Check the `node.id` file under the storage directory and confirm that the new node has a different `node.id` from the other nodes in the cluster.

### 5. Start Fabric

Start the Fabric service on the new node.

### 6. Verify Cluster Membership

Log in to the Fabric terminal and run:

```text
clusterstatus
```

Confirm that the new node has joined the cluster.

## Verification

The procedure is complete when the `clusterstatus` output lists the new node as an active member of the cluster.

If the new node does not appear, recheck the configuration files in Step 3 and the `node.id` value in Step 4 before restarting Fabric.
