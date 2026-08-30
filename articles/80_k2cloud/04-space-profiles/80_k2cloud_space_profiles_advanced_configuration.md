# Advanced Space Profile Configuration

## Overview

The **Advanced Settings** of a Space Profile provide additional configuration for the runtime deployed from that profile.

Advanced Settings organize Space Profile configuration into areas including:

- General settings,
- Fabric configuration,
- non-Fabric image configuration,
- and additional runtime configuration.

These settings should be changed only when required by the deployment and with an understanding of their effect on Spaces using the profile.

## Open Advanced Settings

To access the Advanced Settings:

1. Open the K2cloud Orchestrator.
2. Open **Projects**.
3. Locate the Project and Space Profile.
4. Open the Space Profile **Advanced Settings**.

The available configuration depends on the Space Profile and K2cloud deployment.

## General Settings

General Settings control characteristics such as the environment designation, Space limits, and ingress behavior.

See [Space Profile General Settings](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_general_settings.md).

## Fabric Configuration

Fabric Configuration settings define Fabric runtime properties that are applied as part of the Space Profile.

See [Fabric Configuration Settings](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_fabric_configuration_settings.md).

## Non-Fabric Images

Non-Fabric Image settings control supporting container images deployed as part of the Space Profile, such as PostgreSQL where applicable.

See [Non-Fabric Image Configuration Settings](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_non_fabric_image_configuration_settings.md).

## Changing Advanced Configuration

Space Profiles provide reusable deployment definitions and may be used by multiple Spaces.

Before changing advanced configuration:

- understand the purpose of the setting,
- consider which Spaces use the profile,
- determine whether the change is appropriate for those Spaces,
- and validate significant changes before applying them to production environments.

Avoid creating unnecessary profile variations when an existing standardized profile meets the deployment requirements.

## Related Documentation

- [Space Profiles Overview](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_overview.md)
- [Space Profile General Settings](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_general_settings.md)
- [Fabric Configuration Settings](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_fabric_configuration_settings.md)
- [Non-Fabric Image Configuration Settings](/articles/80_k2cloud/04-space-profiles/80_k2cloud_space_profiles_non_fabric_image_configuration_settings.md)