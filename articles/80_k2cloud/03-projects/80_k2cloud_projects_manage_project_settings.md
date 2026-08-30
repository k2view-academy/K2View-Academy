# Manage Project Settings

## Overview

Project settings allow a Project Manager to review and modify the configuration associated with an existing Project.

Project configuration includes:

- Project information,
- Git repository configuration,
- Space Profiles,
- and the Fabric Image selected for each Space Profile.

Changes to Project settings can affect future Space creation and, in some cases, existing Spaces. Review changes carefully before saving them.

## Open Project Settings

To manage an existing Project:

1. Sign in to the K2cloud Orchestrator at:

   ```text
   https://cloud.k2view.com
   ```

2. Open **Projects**.
3. Locate the Project.
4. Open the Project configuration.

## Project Information

Review the Project information and update it when necessary.

Use Project names and descriptions that clearly identify the implementation and remain meaningful across its development, QA, staging, and production Spaces.

A Project normally represents the implementation rather than a particular runtime environment.

## Git Configuration

The Project's Git configuration associates it with the repository containing the K2view project source.

If the repository or Git token changes, update the Project configuration accordingly.

Before saving a Git configuration change, verify that:

- the repository URL is correct,
- the Git token is valid,
- and the token provides the required repository read access.

For detailed information, see [Configure Git](/articles/80_k2cloud/03-projects/80_k2cloud_projects_configure_git.md).

## Space Profiles and Fabric Images

A Project can have one or more Space Profiles available for creating Spaces.

For each Space Profile, review the associated Fabric Image and other available profile configuration.

The Space Profile defines the runtime topology and resources, while the Fabric Image determines the Fabric version.

Changing the Fabric Image associated with a Space Profile can make an upgrade available to existing eligible Spaces using that profile. It does not immediately upgrade those Spaces.

For information about Fabric versions and upgrades, see [Fabric Versions and Upgrades](/articles/80_k2cloud/02-getting-started/80_k2cloud_getting_started_upgrading_and_requesting_a_fabric_image_version.md).

## Save Changes

After modifying the Project:

1. Review the changed settings.
2. Verify Git configuration if it was changed.
3. Verify the Space Profile and Fabric Image selections.
4. Save the Project.

Changes become part of the Project configuration used by subsequent K2cloud operations.

## Site Selection

A Site is **not selected as a Project setting**.

The Site is selected when a Space is created and determines where that particular Space is deployed.

This allows Spaces belonging to the same Project to be deployed to different Sites when required.

For more information, see [Sites](/articles/80_k2cloud/05-sites/README.md).

## Related Documentation

- [Project Overview](/articles/80_k2cloud/03-projects/80_k2cloud_projects_project_overview.md)
- [Configure Git](/articles/80_k2cloud/03-projects/80_k2cloud_projects_configure_git.md)
- [Space Profiles](/articles/80_k2cloud/04-space-profiles/README.md)
- [Sites](/articles/80_k2cloud/05-sites/README.md)