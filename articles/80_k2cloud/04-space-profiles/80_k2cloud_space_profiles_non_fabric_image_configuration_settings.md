# Non-Fabric Image Configuration Settings

## Overview

The **Non-Fabric Images** settings of a Space Profile control supporting runtime images that are deployed as part of the Space but are separate from the Fabric or Studio image.

The most common example is **PostgreSQL**, which is included with Studio-oriented profiles that provide a PostgreSQL database as part of the Space.

## Open the Non-Fabric Images Settings

To access the settings:

1. Open the K2cloud Orchestrator.
2. Open **Projects**.
3. Locate the Project and Space Profile.
4. Open the Space Profile **Advanced Settings**.
5. Select the **Non-Fabric Images** tab.

## Supporting Runtime Images

Non-Fabric images provide services required by the deployment but are not part of the Fabric runtime image itself.

The images available depend on the Space Profile and its deployment topology.

For example, a Studio profile can include PostgreSQL as a supporting service, while a Fabric profile using externally managed persistence does not require PostgreSQL to be deployed as part of the Space.

## PostgreSQL Image

For profiles that include PostgreSQL, the **Non-Fabric Images** settings identify the PostgreSQL image version to deploy with the Space.

The available versions are determined by the images made available through K2cloud.

## Changing a Supporting Image

Changing a supporting image should be treated separately from changing the Fabric Image.

The **Fabric Image** controls the Fabric or Studio software version. The **Non-Fabric Images** settings control supporting components deployed with the profile.

Changes to supporting images should therefore be planned and validated based on the role of the component and its compatibility with the environment.

## Related Documentation

- [Space Profiles Overview](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_overview.md)
- [Fabric Image Versions](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_fabric_image_versions.md)
- [Fabric Configuration Settings](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_fabric_configuration_settings.md)
- [Advanced Space Profile Configuration](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_advanced_configuration.md)