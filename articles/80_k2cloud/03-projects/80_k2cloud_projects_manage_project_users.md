# Manage Space Access and Roles

## Overview

K2cloud does not manage development users at the Project level.

Project source-code access is managed through the Git platform associated with the Project repository. Access to deployed K2view environments is managed separately through Space and Fabric authorization.

This creates an important separation:

- **Git access** controls who can contribute to the project source.
- **K2cloud Orchestrator access** controls who can manage Projects and the lifecycle of Spaces.
- **Space access** controls who can access and operate deployed K2view environments.

Developers therefore do not need access to the K2cloud Orchestrator simply to work within a development Space.

## Orchestrator Access

Access to the K2cloud Orchestrator requires the `cloud_user` role.

The `cloud_user` role is highly privileged and is intended for Project Managers and other trusted users responsible for operations such as:

- creating and managing Projects,
- creating and managing Spaces,
- and performing Space lifecycle operations.

Developers and testers should not normally receive `cloud_user` simply because they require access to a Space.

Instead, a Project Manager can create the Space and provide its URL to the users who require access.

A user without `cloud_user` who attempts to access:

```text
https://cloud.k2view.com
```

is denied access to the K2cloud Orchestrator but can still access an authorized Space directly using its Space URL.

## Space Access

Space access is separate from K2cloud Orchestrator access.

K2cloud provides built-in Space roles including:

- `space_admin`
- `space_user`

Organizations can also define custom Fabric roles to provide more granular authorization.

The appropriate model depends on the responsibilities of the users accessing the Space.

### `space_admin`

Every Studio/Fabric Space includes the built-in `space_admin` role.

This is a broad administrative role used to bootstrap and administer authorization within the Space. It should be tightly controlled and should not normally be used for routine developer access.

### `space_user`

The built-in `space_user` role provides a standard mechanism for non-administrative Space access.

The permissions associated with Space users are controlled through the Fabric authorization model.

The `space_user` role is not automatically bootstrapped into a newly created Space. Initial authorization configuration must be performed by an appropriately authorized administrator.

### Custom Fabric Roles

Custom Fabric roles allow organizations to tailor Space permissions to actual responsibilities.

For example, organizations may define roles for:

- developers,
- testers,
- deployment operators,
- production support,
- or other operational responsibilities.

Custom roles should be designed around **responsibilities**, not individual users.

Avoid creating a separate Fabric role for every developer. Instead, define reusable roles representing the access required by a group of users performing the same function.

## Federated Identity and Group Mapping

For customers using SAML federation, enterprise Identity Provider groups can be mapped to Fabric roles.

The general model is:

```text
Identity Provider Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
```

This allows membership to remain governed through the customer's Identity Provider while Fabric determines what members of that group are authorized to do within the Space.

For example, an organization might define:

```text
Development Team Group
        ↓
Developer Fabric Role
```

or:

```text
Production Support Group
        ↓
Production Support Fabric Role
```

Adding or removing users from the corresponding Identity Provider group then controls which users receive that authorization.

This is preferable to creating roles or authorization mappings for individual developers.

For detailed identity and authorization design, see [Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md).

## Git Access Is Separate

Space authorization does not determine who can commit or push project source.

The Project Git token is used by K2cloud for read access to the repository when required by K2cloud operations.

Developers working in Fabric Studio use their own Git credentials, such as a personal access token (PAT), for Git operations including commits and pushes.

Repository users, groups, and permissions remain managed by the Git platform.

For more information, see [Configure Git](/articles/80_k2cloud/03-projects/80_k2cloud_projects_configure_git.md).

## TDM Authorization

For TDM-enabled environments, authorization includes an additional application-level permission model.

Conceptually:

```text
Identity Provider Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
        ↓
TDM Permission Group
```

Fabric determines platform-level authorization, while TDM permission groups determine authorization within TDM.

This allows TDM permissions to be managed independently while remaining connected to the enterprise identity and Fabric authorization model.

Detailed TDM authorization configuration is covered in the Identity and Access documentation.

## Recommended Access Model

For most organizations:

1. Limit `cloud_user` to Project Managers and other trusted operational users who require K2cloud Orchestrator access.
2. Limit `space_admin` to users responsible for bootstrapping and administering Space authorization.
3. Provide developers, testers, and operators with Space-level access appropriate to their responsibilities.
4. For federated customers, manage user membership through Identity Provider groups.
5. Map those groups to reusable Fabric roles designed around organizational responsibilities.
6. Avoid creating roles for individual users.

This separates environment management from application development while supporting least-privilege access.

## Related Documentation

- [Configure Git](/articles/80_k2cloud/03-projects/80_k2cloud_projects_configure_git.md)
- [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md)
- [Identity and Access](/articles/80_k2cloud/08-identity-and-access/README.md)