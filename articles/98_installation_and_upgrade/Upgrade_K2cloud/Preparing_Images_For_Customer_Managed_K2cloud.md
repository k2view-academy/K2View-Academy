# Preparing Images for Customer-Managed K2cloud

## Table of Contents

- [Overview](#overview)
- [Image Distribution](#image-distribution)
- [Verify the Destination URL](#verify-the-destination-url)
- [Making a Fabric Release Available in the Customer Registry](#making-a-fabric-release-available-in-the-customer-registry)
- [Next Step](#next-step)

## Overview

This procedure applies only to **customer-managed K2cloud environments**.

For customer-managed environments, customers do not pull Fabric images directly from the K2view container registry. The required Fabric and Fabric-Studio images must be available in the customer's container registry at the destination locations configured for the Site.

When a new Fabric release is published in K2cloud, the release can be selected without asking K2view to add or annotate that version for the customer.

The customer must make the corresponding Fabric and Fabric-Studio images available in the customer container registry at the **Destination URLs** configured for the Site.

> **K2cloud SaaS:** K2view manages the container registry and image distribution. SaaS customers do not perform the image preparation described in this article.

## Image Distribution

Fabric runtime versions are delivered as Fabric Images for Fabric - the runtime image - and Studio - the development image.

For each available release, K2cloud provides release information including:

- Version and lifecycle status
- Release date and description
- A link to the corresponding release notes
- The K2view source image location
- The destination image location configured for the Site

Before using a release, verify that the required Fabric and Fabric-Studio images are available at the locations configured for the Site.

> **IMPORTANT:** If you have not populated your registry at these specified locations for both Fabric and Fabric-Studio images, your installation or upgrades will fail to complete.

## Verify the Destination URL

To determine the destination location:

1. Open the **Fabric Image** dropdown for the Space Profile.
2. Locate the required Fabric release.
3. Click the information icon (ⓘ) to view the release details.
4. Review the **Destination URL** for the Site.

You can copy the Destination URL from the release details. This is the location from which the K2cloud Agent will initiate the Space upgrade to the selected version.

Before proceeding, verify that the Destination URL shown for the Site matches the location in the customer's container registry.

If the Destination URL does not match the customer's registry configuration, contact K2view before proceeding so that the Site configuration can be corrected.

Open a support ticket and include the expected customer registry location.

## Making a Fabric Release Available in the Customer Registry

The workflow for customer-managed environments is:

1. Open the **Fabric Image** dropdown for the Space Profile.
2. Locate the required Fabric release.
3. Click the information icon (ⓘ) to view the release details.
4. Verify the **Destination URL** for the Site.
5. Copy the required Fabric and Fabric-Studio images into the customer registry at the configured destinations.
6. Select the Fabric version for the Space Profile.
7. Save the Project configuration.
8. Upgrade the appropriate Spaces.

## Next Step

After the Fabric and Fabric-Studio images are available in the customer registry at the configured locations, proceed to [Upgrading and Rolling Back a K2cloud Space](/articles/98_installation_and_upgrade/Upgrade_K2cloud/Upgrading_Rolling_Back_K2cloud_Space.md).