# Selecting a Fabric or Studio Version with K2cloud

## Table of Contents

- [Understanding Fabric Images](#understanding-fabric-images)
- [Viewing Available Fabric Releases](#viewing-available-fabric-releases)
- [Project-Level Version Selection](#project-level-version-selection)
- [Versionless Space Profiles](#versionless-space-profiles)
- [Requesting a Fabric Image Version](#requesting-a-fabric-image-version)
- [Next Step](#next-step)

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

For customer-managed environments, the required images must be available in the customer's container registry at the destination locations configured for the Site. For instructions, see [Preparing Images for Customer-Managed K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/Preparing_Images_For_Customer_Managed_K2cloud.md).

For K2cloud SaaS environments, K2view manages the container registry and image distribution.

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

For customer-managed K2cloud, the required Fabric and Fabric-Studio images must be available in the customer container registry at the configured destinations before the release is used. See [Preparing Images for Customer-Managed K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/Preparing_Images_For_Customer_Managed_K2cloud.md).

For K2cloud SaaS, K2view manages the registry configuration and image distribution.

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

For a list of available [Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/profiles.md).

## Requesting a Fabric Image Version

With the August 2026 release, customers no longer need to open a support request simply to have an available K2view Fabric release configured in K2cloud.

Newly published Fabric releases automatically appear in the **Fabric Image** dropdown.

If the required Fabric release does not appear, open a support ticket and provide:

- The Site
- The required Fabric version

For customer-managed K2cloud, also open a support ticket if the **Destination URL** shown for the Site does not match the customer's registry configuration. Include the expected customer registry location.

For K2cloud SaaS, registry configuration and image distribution are managed by K2view. SaaS customers do not need to request registry-location changes or notify K2view after an image becomes available.

## Next Step

After selecting the required version:

- For **customer-managed K2cloud**, see [Preparing Images for Customer-Managed K2cloud](/articles/98_installation_and_upgrade/Upgrade_K2cloud/Preparing_Images_For_Customer_Managed_K2cloud.md).
- For **K2cloud SaaS**, proceed to [Upgrading and Rolling Back a K2cloud Space](/articles/98_installation_and_upgrade/Upgrade_K2cloud/Upgrading_Rolling_Back_K2cloud_Space.md).