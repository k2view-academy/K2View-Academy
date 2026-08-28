# Upgrading and Rolling Back a K2cloud Space

## Table of Contents

- [Before You Begin](#before-you-begin)
- [Upgrading a Space](#upgrading-a-space)
- [Monitoring the Upgrade](#monitoring-the-upgrade)
  - [Pod Diagnostics](#pod-diagnostics)
- [Rolling Upgrade Behavior](#rolling-upgrade-behavior)
- [Rollbacks](#rollbacks)
- [Recommended Validation Workflow](#recommended-validation-workflow)

## Before You Begin

Before upgrading a Space:

- Confirm that the target version has been selected for the appropriate Space Profile. See [Selecting a Fabric or Studio Version with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/Selecting_Fabric_Studio_Version_With_K2cloud.md).
- For customer-managed K2cloud, confirm that the required Fabric and Fabric-Studio images are available in the customer container registry at the configured destinations. See [Preparing Images for Customer-Managed K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/Preparing_Images_For_Customer_Managed_K2cloud.md).
- Review the release details and release notes for the target version.

## Upgrading a Space

Once a Project Manager selects a newer Fabric Image version for a Space Profile, eligible Spaces display an upgrade notification directly on the Space tile.

The **Space Owner** initiates the upgrade from the Space tile menu.

1. Open the **Spaces** page.
2. Locate the target Space tile.
3. Open the tile menu (⋮).
4. Select **Upgrade Space...**
5. Review the source version, target version, and upgrade target.
6. Confirm the upgrade.

K2cloud then initiates the runtime upgrade and provides access to the Space Details monitoring workflow.

## Monitoring the Upgrade

After the upgrade begins, K2cloud opens the **Space Details** monitoring workflow.

The Space Details page provides:

- Runtime summary
- Audit history
- Pod visibility
- Lifecycle transitions
- Lightweight diagnostics

The page includes:

- Pod phase/state
- Image path
- Resource requests and limits
- Lifecycle transition history
- Operational status

The Audit Log records upgrades, rollbacks, lifecycle actions, and operational events in chronological order.

### Pod Diagnostics

The Space Details page also exposes lightweight runtime diagnostics.

For each pod, users can review:

- Pod status
- Namespace
- Start time
- Condition transitions
- Resource requests
- Resource limits
- The latest 50 log lines

Users can also:

- Download the raw pod specification
- Refresh runtime state
- Review lifecycle transitions during rollout activity

This provides immediate operational visibility during upgrades, rollbacks, troubleshooting, and runtime validation.

## Rolling Upgrade Behavior

Fabric upgrades use the deployment strategy associated with the selected Space Profile.

In multi-replica deployments, upgrades are typically performed as rolling upgrades:

- One pod at a time
- While minimizing operational impact

Organizations should still plan production upgrades during appropriate maintenance windows when workloads are traffic-sensitive, integrations are active, or operational impact must be minimized.

## Rollbacks

Rollbacks follow the same workflow as upgrades.

If a prior Fabric Image version remains available:

1. Open the Space tile menu.
2. Select **Upgrade Space...**
3. Select the earlier Fabric Image version.
4. Confirm the rollback.

K2cloud then coordinates the runtime rollback lifecycle operation.

## Recommended Validation Workflow

K2view recommends validating upgrades progressively through development, QA, staging, and production environments.

A common validation workflow is:

1. Confirm that the target release is available in the **Fabric Image** dropdown.
2. Review the release details and release notes.
3. For customer-managed environments, verify the **Destination URL** for the Site.
4. For customer-managed environments, ensure that the required Fabric and Fabric-Studio images have been copied to the customer registry at the configured destinations.
5. Select the target version for a test Space Profile.
6. Upgrade a test Space.
7. Confirm healthy pod transitions.
8. Validate APIs and integrations.
9. Verify TDM or Catalog functionality if applicable.
10. Review logs and runtime status.
11. Proceed to production rollout if validation succeeds.

> **K2cloud SaaS:** Steps 3 and 4 are managed by K2view and do not require customer action.

If issues occur:

- Perform rollback when an earlier version remains available
- Collect diagnostics from Space Details
- Open a support ticket with the pod status, raw pod description, and recent logs