# Identity and Access Overview

## Table of Contents

- [Overview](#overview)
- [Two Access Planes](#two-access-planes)
  - [K2cloud Orchestrator Access](#k2cloud-orchestrator-access)
  - [Space Runtime Access](#space-runtime-access)
- [Authentication Models](#authentication-models)
- [Identity Federation](#identity-federation)
- [Roles and Permissions](#roles-and-permissions)
- [Where Access Is Controlled](#where-access-is-controlled)
- [Space Access](#space-access)
- [Related Documentation](#related-documentation)

## Overview

K2cloud separates **authentication** from **authorization**:

- **Authentication** determines who the user is.
- **Authorization** determines what the user can access and what they can do.

This distinction is important because K2cloud Orchestrator and deployed Spaces are separate access planes with different roles and permissions.

A user who can access a Space does not necessarily have access to K2cloud Orchestrator. Similarly, successful authentication does not by itself grant access to a Space or its applications.

## Two Access Planes

K2cloud access is best understood as two access planes.

### K2cloud Orchestrator Access

K2cloud Orchestrator is the SaaS control plane used to manage Projects and the lifecycle of Spaces.

Users access the K2cloud Orchestrator console at:

```text
https://cloud.k2view.com
```

Orchestrator access is typically reserved for Project Managers, platform administrators, K2view operators, and other trusted customer operators.

The built-in `cloud_user` role provides this type of access and is highly privileged. It should not normally be granted to developers or other users simply because they need access to a development Space.

### Space Runtime Access

Users access authorized Studio and Fabric Spaces directly through the Space URL.

Depending on the Space and the user's permissions, this can provide access to applications and capabilities such as:

- Fabric Web Studio,
- Fabric Admin,
- TDM,
- APIs,
- and other Fabric runtime applications.

Space access is controlled through Fabric roles and, where applicable, TDM permission groups.

Common roles include:

- `space_admin`
- `space_user`
- customer-defined Fabric roles

A user can therefore be authorized to use a Space without being authorized to use K2cloud Orchestrator.

The distinction is fundamental:

**K2cloud Orchestrator manages the lifecycle of Spaces. Fabric and its applications enforce access within the Space.**

## Authentication Models

K2cloud supports two primary authentication models:

1. **K2directory-hosted users**
2. **Customer-federated users**

With K2directory, users authenticate using identities hosted through K2view's Identity service.

With customer federation, users authenticate through the customer's identity provider, such as Microsoft Entra ID, Okta, PingFederate, or another supported SAML identity provider.

For more information, see [Authentication Models](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_authentication_models.md).

## Identity Federation

K2cloud uses its identity service as the federation layer between customer identity providers and K2view services.

For federated customers, the customer's identity provider remains authoritative for users and group membership. Identity federation maps those groups to the roles used by K2cloud and Fabric.

A typical mapping is:

```text
Customer IdP Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
        ↓
Optional TDM Permission Group
```

This allows customers to manage users through their existing identity-management processes while K2cloud and Fabric enforce the resulting authorization.

For more information, see [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md).

## Roles and Permissions

Authorization should be designed around **responsibilities**, not individual users.

For example, an organization may define responsibilities for:

- K2cloud Project Managers,
- Space administrators,
- developers,
- production users,
- TDM administrators,
- TDM testers,
- and other application users.

Users are assigned to the appropriate identity-provider groups, and those groups are mapped to the corresponding K2cloud, Fabric, or TDM roles.

This provides a more maintainable model than creating roles for individual users.

For more information, see [Roles and Permissions](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_roles_and_permissions.md).

## Where Access Is Controlled

Access is enforced at several layers.

<table>
<thead>
<tr>
<th>Layer</th>
<th>What It Controls</th>
</tr>
</thead>
<tbody>
<tr>
<td>Customer IdP</td>
<td>User authentication and group membership for federated users</td>
</tr>
<tr>
<td>Identity federation</td>
<td>Mapping customer identity groups to K2view authorization</td>
</tr>
<tr>
<td>K2cloud Orchestrator</td>
<td>Access to Project and Space lifecycle operations</td>
</tr>
<tr>
<td>Fabric</td>
<td>Space access and Fabric roles and permissions</td>
</tr>
<tr>
<td>TDM</td>
<td>TDM-specific permission groups and application permissions</td>
</tr>
<tr>
<td>Git</td>
<td>Source repository access, including commit and push permissions</td>
</tr>
</tbody>
</table>

Git authorization is separate from K2cloud authorization. K2cloud uses Git-backed Project content during deployment but does not manage repository users or permissions.

## Space Access

Creating a Space does **not** automatically make the person who created it a Space Administrator.

Space authorization must be established through the appropriate Fabric roles and identity mappings.

This allows responsibility for operating K2cloud to remain separate from responsibility for developing, administering, or using applications within a Space.

For example, a Project Manager can create a development Space and provide its Space URL to developers. Those developers can access the Space directly when appropriately authorized without requiring the `cloud_user` role or access to K2cloud Orchestrator.

For more information, see [Space Access](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_space_access.md).

## Related Documentation

- [Authentication Models](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_authentication_models.md)
- [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md)
- [Roles and Permissions](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_roles_and_permissions.md)
- [Space Access](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_space_access.md)
- [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md)
