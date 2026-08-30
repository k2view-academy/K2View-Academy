# Identity and Access Troubleshooting

## Overview

Identity and access problems generally occur at one of several layers:

```text
Authentication
      ↓
Identity Federation
      ↓
K2cloud Orchestrator Authorization
      or
Fabric Space Authorization
      ↓
Optional Application Authorization
```

Start by determining **where access fails** before changing roles, groups, or federation configuration.

## User Cannot Authenticate

If the user cannot complete sign-in, investigate authentication and federation before investigating Fabric roles.

For federated users, verify:

- the user can authenticate with the customer Identity Provider (IdP),
- the correct IdP is being used,
- the federation configuration is active,
- the required SAML claims are being returned,
- and the federation metadata and signing certificates remain valid.

Changes to the customer IdP, SAML application, certificates, metadata, or identity claims can affect authentication.

For more information, see [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md).

## User Can Authenticate but Cannot Access K2cloud Orchestrator

Successful authentication does not automatically provide access to K2cloud Orchestrator.

Verify that the user has the appropriate K2cloud authorization.

Project Manager access requires the `cloud_user` role.

Do not assign `cloud_user` simply to solve a Space-access problem. This is a highly privileged role that provides Project and Space lifecycle capabilities.

If the user only needs access to a Studio or Fabric Space, troubleshoot Space authorization instead.

## User Can Authenticate but Cannot Access a Space

If authentication succeeds but the Space cannot be accessed, verify the user's Space authorization.

For federated users, check the authorization chain:

```text
Customer IdP Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
        ↓
Space Access
```

Verify that:

- the user belongs to the expected IdP group,
- the group is included in the authentication assertion,
- the group is mapped through K2cloud Identity Federation,
- the mapped Fabric role exists,
- and the Fabric role provides the required access.



## User Can Access the Space but Cannot Perform an Operation

If the user can enter the Space but cannot perform a specific operation, authentication and basic Space access are already working.

Review the permissions assigned to the user's Fabric role.

For example, determine whether the role provides the permissions required for the affected:

- Fabric operation,
- Web Studio capability,
- runtime operation,
- web service,
- or Logical Unit.



## User Cannot Access TDM Functionality

TDM introduces an additional authorization layer.

The complete mapping can be:

```text
Customer IdP Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
        ↓
TDM Permission Group
```

If the user can access Fabric but cannot perform the expected TDM operation, verify the mapping between the Fabric role and the appropriate TDM permission group.



## Newly Created Space Has No Expected Users

Creating a Space does not automatically grant the Space creator runtime administrative access.

Also, the built-in `space_user` role is not automatically added to a newly created Space.

Verify that:

- an appropriate Space Administrator has been assigned,
- the required Fabric roles exist,
- the required permissions are assigned,
- and the corresponding identity groups are correctly mapped.

## User Can Access a Space but Cannot Commit or Push to Git

Git access is separate from K2cloud and Fabric authorization.

If a Studio user can access the Space but cannot commit or push changes, verify:

- the user's Git credentials or personal access token,
- repository permissions,
- repository branch protections,
- and the Git platform's access policies.

Do not attempt to solve a Git authorization problem by changing K2cloud or Fabric roles.

## Access Stopped Working After an Identity Change

If access previously worked, identify what changed.

Common areas to review include:

- IdP group membership,
- group names or identifiers,
- SAML application configuration,
- federation metadata,
- signing certificates,
- SAML claims,
- federation mappings,
- Fabric roles,
- Fabric permissions,
- and TDM permission mappings.

Federation configuration is operational configuration. Changes to identity trust or group mappings can affect deployed Spaces and should be planned and validated carefully.

## Troubleshooting Sequence

Use the following sequence to isolate the problem:

1. **Can the user authenticate?**
   - If no, investigate the IdP and federation configuration.

2. **Can the user access the requested application?**
   - For K2cloud Orchestrator, verify K2cloud authorization.
   - For a Space, verify Fabric role mapping.

3. **Can the user enter the Space but not perform the required operation?**
   - Verify Fabric role permissions.

4. **Is the problem specific to TDM?**
   - Verify the TDM permission-group mapping.

5. **Is the problem specific to Git?**
   - Verify Git credentials and repository authorization independently.

This sequence helps avoid granting unnecessary privileges while troubleshooting an access problem.

## Related Documentation

- [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md)
- [Authentication Models](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_authentication_models.md)
- [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md)
- [Roles and Permissions](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_roles_and_permissions.md)
- [Space Access](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_space_access.md)