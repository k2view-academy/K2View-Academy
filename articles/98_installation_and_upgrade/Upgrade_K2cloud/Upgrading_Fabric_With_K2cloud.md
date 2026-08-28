# Upgrading Fabric and Studio with K2cloud

## Table of Contents

- [Overview](#overview)
  - [Video Walkthrough](#video-walkthrough)
- [Understanding Fabric Images](#understanding-fabric-images)
  - [Customer-managed K2cloud](#customer-managed-k2cloud)
  - [K2cloud SaaS](#k2cloud-saas)
- [Viewing Available Fabric Releases](#viewing-available-fabric-releases)
  - [Customer-managed K2cloud](#customer-managed-k2cloud-1)
  - [K2cloud SaaS](#k2cloud-saas-1)
- [Making a Fabric Release Available in the Customer Registry](#making-a-fabric-release-available-in-the-customer-registry)
- [Project-Level Version Selection](#project-level-version-selection)
- [Versionless Space Profiles](#versionless-space-profiles)
- [Upgrading a Space](#upgrading-a-space)
  - [Monitoring the Upgrade](#monitoring-the-upgrade)
    - [Pod Diagnostics](#pod-diagnostics)
  - [Rolling Upgrade Behavior](#rolling-upgrade-behavior)
- [Rollbacks](#rollbacks)
- [Requesting a Fabric Image Version](#requesting-a-fabric-image-version)
  - [Customer-managed K2cloud](#customer-managed-k2cloud-2)
  - [K2cloud SaaS](#k2cloud-saas-2)
- [Recommended Validation Workflow](#recommended-validation-workflow)

## Overview

K2cloud supports self-service upgrades and rollbacks for both Studio and Fabric Spaces.

Beginning with the November 2025 release, K2cloud introduced:

- Project-level version promotion
- Versionless Space Profiles
- One-click upgrades and rollbacks
- Enhanced runtime observability through the Space Details page

K2view automatically publishes available Fabric releases to the Projects page and makes them available for Space Profile configuration. Customers no longer need to ask K2view to configure each newly available Fabric version for their environment.

The upgrade workflow separates operational responsibilities between:

- **K2view**, which publishes available Fabric releases
- **Project Managers**, who select the appropriate runtime version for Space Profiles
- **Space Owners**, who execute upgrades and rollbacks on individual Spaces

This operational model allows organizations to:

- Access newly available Fabric releases without customer-specific version configuration by K2view
- Validate versions progressively
- Standardize deployment behavior
- Coordinate upgrades safely across development, QA, staging, and production environments

K2cloud supports two deployment models:

<table>
<thead>
<tr>
<th>Deployment Model</th>
<th>Image Distribution</th>
<th>Upgrade</th>
</tr>
</thead>
<tbody>
<tr>
<td><strong>Customer-managed K2cloud</strong></td>
<td>The customer copies the Fabric and Studio images into its container registry at the locations configured for the Site.</td>
<td>The customer selects the version and performs the Space upgrade using K2cloud.</td>
</tr>
<tr>
<td><strong>K2cloud SaaS</strong></td>
<td>K2view manages the container registry and image distribution on the customer's behalf.</td>
<td>The customer selects the version and performs the Space upgrade using K2cloud.</td>
</tr>
</tbody>
</table>

The version-selection, upgrade, monitoring, validation, and rollback workflows are otherwise the same for both deployment models.

> **Note:** This procedure applies to Fabric environments managed through K2cloud. For Fabric installations deployed directly on Linux servers, refer to the Fabric Upgrade Procedure (Linux Installations) in the Upgrade Fabric section.

## Video Walkthrough

You can get an overview of the K2cloud upgrade capabilities at:

https://download.k2view.com/index.php/s/3rgZczMc6eEOU7t



## Understanding Fabric Images

Fabric runtime versions are delivered as Fabric Images for Fabric - the runtime image - and Studio - the development image.

A Fabric Image contains:

- The Fabric runtime
- Runtime dependencies
- Associated platform services
- Operational packaging

K2view publishes available Fabric releases centrally in K2cloud. Once a release is published, it automatically becomes available from the **Fabric Image** selection on the Projects page.

Typical Fabric Image versions may appear as:

```text
8.4.0_174
8.4.1_73
8.4.3_16
```

For each available release, K2cloud provides release information including:

- Version and lifecycle status
- Release date and description
- A link to the corresponding release notes
- The K2view source image location
- The destination image location configured for the Site

### Customer-managed K2cloud

For customer-managed environments, customers do not pull Fabric images directly from the K2view container registry. The Fabric image must be available in the customer's container registry at the destination location configured for the Site.

### K2cloud SaaS

K2view manages the container registry and image distribution for K2cloud SaaS environments. SaaS customers do not need to copy Fabric images to a registry or notify K2view that an image has been pulled.



## Viewing Available Fabric Releases

K2view automatically publishes available Fabric releases, which appear in the **Fabric Image** dropdown when configuring a Space Profile.

From:

**Projects → Space Profile → Fabric Image dropdown**

the Project Manager can select from the Fabric releases currently made available by K2view.

To view additional information about a release, click the information icon (ⓘ) next to the version.

The release details include:

- Release status
- Release date
- Description
- K2view source URL
- Destination URL for the Site
- A direct link to the Fabric release notes

### Customer-managed K2cloud

Before using a release, verify that the **Destination URL** shown for the Site matches the location in the customer's container registry.

> **IMPORTANT:** If you have not populated your registry at these specified locations for both Fabric and Fabric-Studio images, your installation or upgrades will fail to complete.

You can copy the Destination URL from the release details. This is the location from which the K2cloud Agent will initiate the Space upgrade to the selected version.

If the Destination URL does not match the customer's registry configuration, contact K2view before proceeding so that the Site configuration can be corrected.

### K2cloud SaaS

K2view manages the registry configuration and image distribution. SaaS customers do not need to validate or manage the Destination URL as part of the upgrade workflow.



## Making a Fabric Release Available in the Customer Registry

This section applies only to **customer-managed K2cloud environments**.

When a new Fabric release is published in K2cloud, the release can be selected without asking K2view to add or annotate that version for the customer.

The customer must make the corresponding Fabric image available in the customer container registry at the **Destination URL** shown for the Site.

The workflow is:

1. Open the **Fabric Image** dropdown for the Space Profile.
2. Locate the required Fabric release.
3. Click the information icon (ⓘ) to view the release details.
4. Verify the **Destination URL** for the Site.
5. Copy the Fabric image into the customer registry at that destination.
6. Select the Fabric version for the Space Profile.
7. Save the Project configuration.
8. Upgrade the appropriate Spaces.

> **K2cloud SaaS:** K2view manages the registry and image distribution. SaaS customers do not perform the image-copy steps above and do not need to inform K2view that an image has been pulled. Once the release is available in K2cloud, the customer can proceed with the normal version-selection and upgrade workflow.



## Project-Level Version Selection

Fabric version selection is performed by a **Project Manager**. Within the Project editor, the Project Manager selects the Fabric Image version to use for a specific Space Profile.

This is performed from:

**Projects → Space Profile → Fabric Image dropdown**

Because Fabric releases are now published centrally, the version list no longer depends on K2view manually configuring each new Fabric version for the customer.

If the Project Manager overrides the default version, **Advanced Settings** may also require validation of associated non-Fabric images such as:

- Postgres image versions
- Supporting services
- Infrastructure compatibility settings



## Versionless Space Profiles

New K2cloud environments commonly now use versionless Space Profiles.

In this model:

- Deployment behavior
- Runtime sizing
- Scaling topology
- Runtime versions

are managed independently.

This means:

- Runtime versions can change without redefining deployment policy
- Operational profiles remain reusable across lifecycle stages

Example profile names include:

```text
Studio-c4-m32-pg
Fabric-c8-m64-r2-mngd
```

These naming conventions represent:

- CPU
- Memory
- Replica count
- Runtime topology

For a list of available Space Profiles, see: (/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/profiles.md)



# Upgrading a Space

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



# Rollbacks

Rollbacks follow the same workflow as upgrades.

If a prior Fabric Image version remains available:

1. Open the Space tile menu.
2. Select **Upgrade Space...**
3. Select the earlier Fabric Image version.
4. Confirm the rollback.

K2cloud then coordinates the runtime rollback lifecycle operation.



# Requesting a Fabric Image Version

With the August 2026 release, customers no longer need to open a support request simply to have an available K2view Fabric release configured in K2cloud.

Newly published Fabric releases automatically appear in the **Fabric Image** dropdown.

If the required Fabric release does not appear, open a support ticket and provide:

- The Site
- The required Fabric version

### Customer-managed K2cloud

Also open a support ticket if the **Destination URL** shown for the Site does not match the customer's registry configuration.

Include the expected customer registry location.

### K2cloud SaaS

Registry configuration and image distribution are managed by K2view. SaaS customers do not need to request registry-location changes or notify K2view after an image becomes available.



# Recommended Validation Workflow

K2view recommends validating upgrades progressively through development, QA, staging, and production environments.

A common validation workflow is:

1. Confirm that the target release is available in the **Fabric Image** dropdown.
2. Review the release details and release notes.
3. For customer-managed environments, verify the **Destination URL** for the Site.
4. For customer-managed environments, ensure that the Fabric image has been copied to the customer registry at the expected destination.
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