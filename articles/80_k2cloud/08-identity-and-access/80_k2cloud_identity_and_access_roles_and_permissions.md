# Roles and Permissions

## Table of Contents

- [Overview](#overview)
- [Authorization Principles](#authorization-principles)
- [Authorization Layers](#authorization-layers)
- [K2cloud Orchestrator Access](#k2cloud-orchestrator-access)
- [Space Roles](#space-roles)
  - [space_admin](#space_admin)
  - [space_user](#space_user)
  - [Custom Fabric Roles](#custom-fabric-roles)
- [TDM Permission Groups](#tdm-permission-groups)
- [Responsibility-Based Authorization](#responsibility-based-authorization)
- [Federated Role Mapping](#federated-role-mapping)
- [Git Permissions](#git-permissions)
- [Recommended Practices](#recommended-practices)
- [Related Documentation](#related-documentation)

## Overview

K2cloud authorization determines what an authenticated user can access and what operations the user can perform.

Authorization is enforced independently across:

- K2cloud Orchestrator,
- Fabric Spaces,
- TDM,
- and Git.

These authorization layers should be designed according to the user's responsibilities rather than simply granting broad access across the platform.

## Authorization Principles

A K2cloud authorization design should follow several basic principles:

- separate authentication from authorization,
- define access according to responsibilities,
- apply least privilege,
- use identity-provider groups rather than individual user mappings where possible,
- separate K2cloud Orchestrator privileges from Space access,
- and keep Fabric and TDM permissions independently manageable.

The objective is to define roles that represent meaningful responsibilities and then assign users to those responsibilities through the organization's identity-management processes.

## Authorization Layers

Different components control different types of access.

<table>
<thead>
<tr>
<th>Layer</th>
<th>Authorization Responsibility</th>
</tr>
</thead>
<tbody>
<tr>
<td>K2cloud Orchestrator</td>
<td>Project and Space lifecycle operations</td>
</tr>
<tr>
<td>Fabric</td>
<td>Space access and Fabric capabilities</td>
</tr>
<tr>
<td>TDM</td>
<td>TDM-specific application permissions</td>
</tr>
<tr>
<td>Git</td>
<td>Repository access, commits, pushes, and other source-control permissions</td>
</tr>
</tbody>
</table>

A user can therefore have access at one layer without automatically receiving access at another.

## K2cloud Orchestrator Access

The built-in `cloud_user` role provides Project Manager access to K2cloud Orchestrator.

A Project Manager can perform lifecycle operations such as:

- creating Spaces,
- deleting Spaces,
- pausing and resuming Studio Spaces,
- restarting Fabric Spaces,
- and performing deployment and other operational actions.

`cloud_user` is a highly privileged role.

It should be assigned only to users who require K2cloud Project and Space lifecycle responsibilities.

For example, a developer who only needs to work within a Studio Space does **not** need `cloud_user`. The Project Manager can create the Space and provide the developer with its URL.

## Space Roles

Fabric independently controls access within a Space.

Built-in Space roles include `space_admin` and `space_user`. Customer-defined Fabric roles can provide more granular authorization.

### `space_admin`

`space_admin` provides broad administrative access within a Space.

It is primarily appropriate for:

- initial Space administration,
- bootstrap configuration,
- role and permission configuration,
- and users who genuinely require broad administrative capabilities.

Because of its broad permissions, `space_admin` should be tightly controlled.

Creating a Space does **not** automatically make the creator a Space Administrator.

### `space_user`

`space_user` provides access to a Space based on the permissions configured for that role.

It should not be assumed that every user of a Space requires `space_admin`.

`space_user` is also not automatically bootstrapped into a newly created Space. Initial authorization must be established by an appropriately authorized administrator.

### Custom Fabric Roles

Customer-defined Fabric roles can be used to implement more granular authorization.

For example, an organization might define roles corresponding to responsibilities such as:

```text
Developer
Production Operator
Read-Only User
Application Administrator
```

The exact roles and permissions depend on the customer's implementation and security requirements.

Custom roles allow access to be tailored without granting the broad permissions associated with `space_admin`.

## TDM Permission Groups

TDM has its own permission model.

A Fabric role can be associated with the appropriate TDM permission group so that users receive the TDM capabilities required for their responsibilities.

For example:

```text
Fabric Role
    ↓
TDM Permission Group
```

Possible responsibilities can include:

- TDM Administrator,
- TDM Owner,
- TDM Tester,
- or other customer-defined TDM responsibilities.

Fabric and TDM permissions should remain logically separate so that access to Fabric does not automatically imply unrestricted access to TDM.

## Responsibility-Based Authorization

Authorization should begin with responsibilities rather than individual users.

A useful design process is:

```text
Responsibilities
      ↓
Platform Roles
      ↓
Identity Provider Groups
      ↓
Users
```

For example, instead of creating separate Fabric roles for individual developers:

```text
Developer_A_Role
Developer_B_Role
Developer_C_Role
```

define a common responsibility:

```text
Developer
```

and map the appropriate identity-provider group to the Fabric role that implements that responsibility.

This makes the authorization model easier to understand, maintain, and audit as users join, leave, or change responsibilities.

## Federated Role Mapping

For customer-federated users, authorization is normally based on enterprise IdP groups.

The typical mapping is:

```text
Customer IdP Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
        ↓
Optional TDM Permission Group
```

The customer manages user membership in the IdP group.

The federation configuration maps that group to the appropriate K2view authorization.

This allows user lifecycle management to remain in the customer's enterprise identity platform while Fabric and TDM independently enforce application permissions.

For more information, see [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md).

## Git Permissions

Git authorization is separate from K2cloud, Fabric, and TDM authorization.

K2cloud does not manage Git users or repository permissions.

For example:

- K2cloud uses the Project Git configuration to retrieve versioned content for deployment.
- Developers use their own Git credentials or personal access tokens when committing and pushing source changes.
- Repository permissions are managed in the customer's Git platform.

A user's K2cloud or Fabric role therefore does not determine whether that user can commit or push changes to Git.

## Recommended Practices

When designing roles and permissions:

- grant `cloud_user` only to users who require K2cloud lifecycle responsibilities,
- tightly control `space_admin`,
- use custom Fabric roles where more granular permissions are appropriate,
- define roles around responsibilities rather than individual users,
- use enterprise IdP groups to manage federated user membership,
- keep Fabric and TDM authorization logically separate,
- keep Git authorization separate from K2cloud authorization,
- and periodically review group membership and role mappings.

Avoid using broad administrative roles simply to make initial access easier. Establish the required authorization model as part of provisioning the environment.

## Related Documentation

- [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md)
- [Authentication Models](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_authentication_models.md)
- [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md)
- [Space Access](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_space_access.md)
- [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md)