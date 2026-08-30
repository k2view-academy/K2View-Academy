# Navigate the K2cloud Orchestrator

## Overview

The K2cloud Orchestrator provides the management interface for working with K2cloud Projects and Spaces.

Project Managers with the `cloud_user` role access the K2cloud Orchestrator at:

`https://cloud.k2view.com`

From the K2cloud Orchestrator console, Project Managers can create and manage Projects, create and operate Spaces, manage deployments, select Fabric versions, and perform supported lifecycle operations.

This article provides a basic orientation to the K2cloud Orchestrator console.

## Sign In

Open the K2cloud Orchestrator:

`https://cloud.k2view.com`

Sign in using the identity configured for your organization.

Depending on your organization's identity configuration, authentication may use a K2view-managed identity or be federated to your enterprise Identity Provider.

Access to the K2cloud Orchestrator requires the `cloud_user` role. This is a highly privileged role intended for Project Managers and other users responsible for managing Projects and the lifecycle of Spaces. It should not normally be granted to developers simply to provide access to a development Space.

A common model is for the Project Manager to create the development Space and provide the developer with the Space URL. The developer accesses the Space directly rather than through the K2cloud Orchestrator.

A user without the `cloud_user` role who attempts to access `https://cloud.k2view.com` is denied access to the K2cloud Orchestrator. This does not prevent the user from accessing a Space directly when the appropriate Space-level authorization has been granted.

Space access is governed separately using `space_admin`, `space_user`, and customer-defined Fabric roles and permissions.

For customers using SAML federation, Identity Provider groups can be mapped to customer-defined Fabric roles, allowing Space permissions to be tailored to organizational responsibilities. Roles should be designed around responsibilities rather than individual users; avoid creating a separate role for each developer.

For more information, see [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md).

## Projects

The **Projects** page is used to create and manage K2cloud Projects.

A Project provides the context for the K2view implementation and its Spaces. From a Project, a Project Manager can manage configuration including:

- the Git repository associated with the Project,
- Space access,
- Space Profiles,
- and the Fabric Images available to those profiles.

A Project can have multiple Spaces representing different development and runtime environments.

For more information, see [Projects](/articles/80_k2cloud/03-projects/README.md).

![K2cloud Orchestrator Projects page](/articles/80_k2cloud/images/02_navigate_orchestrator_projects.png)


## Spaces

The **Spaces** page provides the operational view of the Spaces available to the Project Manager.

Each Space tile identifies the deployed environment and provides information such as:

- Project,
- Space Profile,
- Site,
- creation information,
- current status,
- and the most recent status change.

The actions available from a Space depend on the type and state of the Space.

From the Spaces page, authorized users can perform supported operations such as:

- opening a Space,
- refreshing its status,
- viewing Space details,
- deploying environments and projects,
- upgrading eligible Spaces,
- and performing other lifecycle operations available for that Space.

For more information, see [Spaces](/articles/80_k2cloud/06-spaces/README.md).

![K2cloud Orchestrator Spaces page](/articles/80_k2cloud/images/02_navigate_orchestrator_spaces.png)


## Opening a Space

A Space can be opened from its Space tile.

Depending on the Space type, this provides access to the applications available within that environment.

A Studio development Space provides access to Fabric Studio and Fabric administration capabilities.

A Fabric runtime Space provides access to the deployed Fabric environment and its runtime administration capabilities.

Access to the Space is governed independently from access to the K2cloud Orchestrator. A user must have the appropriate Space-level authorization to access the environment.

## Space Details

The **Space Details** page provides operational information about a deployed Space.

Depending on the Space and its current state, this includes information about:

- the Project, Space Profile, and Site,
- Kubernetes pods,
- runtime status,
- Kubernetes events,
- resource utilization,
- restart information,
- and pod logs.

Space Details is particularly useful when monitoring lifecycle operations or investigating the state of a Space.


## Where to Go Next

After becoming familiar with the K2cloud Orchestrator, the typical workflow is to understand how Projects, Space Profiles, Sites, and Spaces work together.

Continue with:

- [Projects](/articles/80_k2cloud/03-projects/README.md)
- [Space Profiles](/articles/80_k2cloud/04-space-profiles/README.md)
- [Sites](/articles/80_k2cloud/05-sites/README.md)
- [Spaces](/articles/80_k2cloud/06-spaces/README.md)