# Create a Space

## Overview

A Space is created within a Project by selecting the deployment configuration and Site where the Space will run.

Before creating a Space, the Project must already have the appropriate:

- Git configuration,
- Space Profile,
- Fabric Image,
- and available Site.

The **Project Manager** performs the Space creation operation through the K2cloud Orchestrator.

## Create the Space

1. Sign in to the K2cloud Orchestrator.
2. Open the **Spaces** page.
3. Select the option to create a Space.
4. Enter a name for the Space.
5. Select the **Project**.
6. Select the **Space Profile**.
7. Select the **Site**.
8. Review the configuration and create the Space.

The Fabric Image associated with the selected Space Profile determines the Fabric or Studio version initially deployed to the Space.

## Space Name

Choose a Space name that clearly identifies the purpose of the environment.

For example:

```text
development
qa
staging
production
```

The naming convention should be consistent with the organization's environment and operational conventions.

## Select the Project

The Project identifies the K2view implementation deployed to the Space and provides the associated Git repository and Space Profiles.

A Space belongs to one Project.

## Select the Space Profile

The Space Profile defines the topology and runtime characteristics of the Space.

For example, the selected profile can determine:

- CPU and memory resources,
- Fabric replica count,
- supporting services,
- persistence configuration,
- and other runtime settings.

Use a profile appropriate for the purpose and expected workload of the Space.

For profile definitions and selection guidance, see [K2cloud Versionless Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/README.md).

## Select the Site

The Site determines the runtime infrastructure where the Space is deployed.

Only select a Site appropriate for the intended environment and its connectivity requirements.

For more information, see [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md).

## Create and Monitor the Space

After the Space is created, K2cloud begins provisioning the required runtime resources.

The Space status changes as provisioning progresses.

Wait until the Space reaches its operational state before attempting to use or configure the environment.

If additional deployment information is required, open the Space details to review the Kubernetes pods and events associated with the deployment.

## After the Space Is Created

After provisioning completes, the next steps depend on the Space type.

For a **Studio Space**, developers can access the Space directly and begin working with Fabric Studio once the appropriate runtime authorization has been configured.

For a **Fabric Space**, the environment and Project content can be deployed and activated as required for that runtime environment.

Creating the Space does **not** automatically make the person who created it a Space Administrator. Runtime access and authorization are managed separately from the Space creation operation.

For more information, see [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md).

## Related Documentation

- [Spaces Overview](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_space_overview.md)
- [K2cloud Versionless Space Profiles](/articles/98_installation_and_upgrade/K2cloud_Space_Profiles/README.md)
- [Sites Overview](/articles/80_k2cloud/05-sites/80_k2cloud_sites_overview.md)
- [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md)
- [View Space Details](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_view_space_details.md)