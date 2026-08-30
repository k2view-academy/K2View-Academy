# Fabric Versions and Upgrades

## Overview

K2cloud provides self-service capabilities for managing the Fabric and Studio versions used by Spaces.

K2view publishes available Fabric releases to K2cloud. Project Managers select the appropriate Fabric Image for a Space Profile, and eligible Spaces can then be upgraded to the selected version.

This separates two activities:

- **Version selection** — the Project Manager selects the Fabric Image associated with a Space Profile.
- **Space upgrade** — the Space Owner initiates the upgrade of an eligible Space.

Detailed upgrade procedures are maintained with the other Fabric installation and upgrade documentation.

For complete instructions, see [Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md).

## Fabric Images

A **Fabric Image** identifies the Fabric or Studio version available for deployment to a Space.

K2view publishes available releases centrally. These releases become available to Project Managers when configuring the Fabric Image associated with a Space Profile.

The Space Profile continues to define the runtime topology and resources, while the Fabric Image defines the version of the software running within that topology.

This allows the Fabric version to change over time without requiring a different Space Profile.


## How an Upgrade Becomes Available

Selecting a newer Fabric Image for a Space Profile does not immediately upgrade its existing Spaces.

Instead, eligible Spaces using that profile are identified as having an upgrade available.

The Space Owner can then initiate the upgrade from the Space tile at an appropriate time.

This separation allows the Project Manager to make a version available while retaining control over when individual Spaces are upgraded.

![K2cloud Space showing an available Fabric upgrade](/articles/80_k2cloud/images/02_fabric_versions_upgrade_available.png)

## K2cloud SaaS and Self-Hosted

The version-selection and Space-upgrade workflow is similar for K2cloud SaaS and K2cloud Self-Hosted, but image distribution differs.

With **K2cloud SaaS**, K2view manages the container registry and image distribution.

With **K2cloud Self-Hosted**, the required Fabric and Fabric Studio images must also be made available in the customer container registry at the locations configured for the Site before they can be deployed.

The detailed image preparation procedure is covered in the installation and upgrade documentation.

## Upgrades and Rollbacks

K2cloud coordinates the lifecycle operation when a Space is upgraded.

For multi-replica Fabric deployments, upgrades typically use the deployment strategy associated with the Space Profile to replace pods progressively, minimizing operational impact.

Space Details can be used to monitor pod transitions, runtime status, Kubernetes events, and other operational information during the upgrade.

Where a previous Fabric Image remains available, K2cloud also supports rolling the Space back to that version.


## Performing an Upgrade

The complete procedures for selecting versions, preparing images for customer-managed environments, upgrading Spaces, and performing rollbacks are maintained in the Fabric installation and upgrade documentation.

See:

[Upgrading Fabric and Studio with K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/README.md)