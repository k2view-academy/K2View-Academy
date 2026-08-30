# Space Access

## Overview

Space access controls who can enter and use a deployed Studio or Fabric Space.

Space access is separate from K2cloud Orchestrator access:

- **K2cloud Orchestrator access** allows authorized users to manage Projects and Space lifecycle operations.
- **Space access** allows authorized users to access the applications and capabilities available within a deployed Space.

A user who only needs access to a Space does not need access to K2cloud Orchestrator.

## Accessing a Space

Users access an authorized Space directly through its Space URL.

When a user opens the Space URL, the user is redirected through the configured K2cloud identity flow. After authentication, the user's authorization for the Space is evaluated.

Depending on the Space and the user's permissions, access can include applications and capabilities such as:

- Fabric Web Studio,
- Fabric Admin,
- TDM,
- APIs,
- Reports,
- Catalog,
- Trace,
- Statistics,
- and Data Explorer.

Successful authentication alone does not grant access. The authenticated user must also have the appropriate authorization for the Space.

## Space Roles

Space access is controlled through Fabric roles.

Common runtime roles include:

- `space_admin`,
- `space_user`,
- customer-defined Fabric roles,
- and application-specific permission groups.

The roles assigned to a user determine the applications and capabilities available within the Space.

For detailed authorization-design guidance, see [Roles and Permissions](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_roles_and_permissions.md).

## Creating a Space Does Not Grant Space Administration

Creating a Space through K2cloud Orchestrator does **not** automatically make the person who created it a Space Administrator.

K2cloud Orchestrator lifecycle privileges and Fabric runtime authorization are separate.

For example, a Project Manager can:

1. Create a Studio Space.
2. Provide developers with the Space URL.
3. Have the developers authorized through the appropriate Fabric roles.

The developers can then access the Studio Space directly without requiring the `cloud_user` role or access to K2cloud Orchestrator.

Initial Space authorization must be established by an appropriately authorized administrator.

## Managing Runtime Authorization

Space roles and permissions are managed through the Space's **Web Admin** interface.

Web Admin is used to administer Fabric runtime authorization, including the roles and permissions applicable within the Space.

K2cloud Orchestrator does not replace the Fabric runtime permission model.

The operational boundary is:

```text
K2cloud Orchestrator
    ↓
Space lifecycle management

Fabric Web Admin
    ↓
Space runtime authorization
```

This separation allows responsibility for operating the K2cloud environment to remain independent from responsibility for administering or using applications within a Space.

## Federated Space Access

In federated environments, customer IdP groups are mapped through K2cloud identity federation to Fabric roles.

The typical authorization chain is:

```text
Customer IdP Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
        ↓
Space Runtime Authorization
```

The customer manages user membership in its enterprise IdP groups. The federation mapping associates those groups with the appropriate Fabric roles.

If the mapping is incomplete or incorrect, a user may successfully authenticate but still be unable to access the Space or the expected functionality.

For more information, see [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md).

## TDM Access

TDM-enabled Spaces can require additional TDM authorization.

The authorization chain can include:

```text
Customer IdP Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
        ↓
TDM Permission Group
```

A user can therefore be authorized to access the Fabric Space but still lack the permissions required to use specific TDM capabilities.

Fabric roles and TDM permission groups should be assigned according to the user's responsibilities.

## Git Access Is Separate

Space access does not grant source-code contribution rights.

Git repository access is managed independently through the Git platform.

For example:

- developers working in Studio use their own Git credentials or personal access tokens for commits and pushes,
- repository permissions are managed by the Git platform,
- and the Project Git token configured for K2cloud is an operational, read-only credential used to retrieve Project content for deployment.

K2cloud does not manage Git users or repository permissions.

## Assign Access According to Responsibility

Space access should reflect what the user needs to do.

<table>
<thead>
<tr>
<th>Responsibility</th>
<th>Typical Access</th>
</tr>
</thead>
<tbody>
<tr>
<td>Developer</td>
<td>Studio Space access</td>
</tr>
<tr>
<td>Tester</td>
<td>TDM or runtime Space access as required</td>
</tr>
<tr>
<td>Runtime operator</td>
<td>Fabric runtime administration as required</td>
</tr>
<tr>
<td>Project Manager</td>
<td>K2cloud Orchestrator and Space access where required</td>
</tr>
<tr>
<td>Business or application user</td>
<td>Application-specific runtime access</td>
</tr>
</tbody>
</table>

Do not grant K2cloud Orchestrator access simply because a user requires access to a Space.

## Related Documentation

- [Access Fabric Web Studio and Fabric Spaces](/articles/80_k2cloud/06-spaces/80_k2cloud_spaces_access_fabric_web_studio_and_fabric_spaces.md)
- [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md)
- [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md)
- [Roles and Permissions](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_roles_and_permissions.md)
- [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md)