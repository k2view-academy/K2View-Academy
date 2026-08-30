# Authentication Models

## Overview

K2cloud supports two primary authentication models:

- **K2directory / CyberArk-hosted users**
- **Customer-federated users**

Both models use CyberArk Identity as part of the K2cloud identity service, but they differ in where the user's identity is managed and where authentication occurs.

In either model, authentication establishes the user's identity. Authorization is then independently enforced by K2cloud Orchestrator, Fabric, and, where applicable, TDM.

## K2directory / CyberArk-Hosted Users

In the K2directory model, user accounts are hosted in K2view's CyberArk Identity service.

Users authenticate directly through the K2cloud identity service.

This model can be used for:

- K2view users,
- customer users where federation is not configured,
- support access,
- training,
- and other onboarding or operational requirements.

After authentication, the user's group membership is used to determine the appropriate K2cloud and Fabric authorization.

A typical authorization path is:

```text
CyberArk User
    ↓
CyberArk Group
    ↓
Fabric Role
    ↓
Optional TDM Permission Group
```

## Customer-Federated Users

For customer-federated users, the customer's enterprise Identity Provider (IdP) is authoritative for the user identity.

Supported enterprise identity providers can include:

- Microsoft Entra ID,
- Okta,
- PingFederate,
- and other supported SAML identity providers.

The customer manages its users through the enterprise identity platform, including:

- user accounts,
- authentication policies,
- MFA,
- group membership,
- conditional access,
- and identity governance.

K2cloud delegates user authentication to the customer's IdP and uses the resulting identity and group information as part of the authorization process.

A typical authorization path is:

```text
Customer IdP Group
    ↓
K2cloud Identity Federation
    ↓
Fabric Role
    ↓
Optional TDM Permission Group
```

For more information about this mapping, see [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md).

## Authentication and Authorization

Authentication and authorization are separate.

**Authentication** determines:

```text
Who is this user?
```

**Authorization** determines:

```text
What is this user allowed to access and do?
```

Successful authentication therefore does not automatically provide access to K2cloud Orchestrator or to a Space.

For example, a federated user may successfully authenticate through Microsoft Entra ID but still be denied access if the user's IdP group has not been mapped to the required K2cloud or Fabric role. This separation is consistent with the K2cloud authorization architecture. 

## Orchestrator Access and Space Access

The authentication model does not change the distinction between K2cloud Orchestrator access and Space access.

A user may be authorized to access:

- K2cloud Orchestrator,
- one or more Studio or Fabric Spaces,
- TDM,
- or a combination of these capabilities.

Access depends on the roles associated with the authenticated identity.

For example, developers who only require access to a Studio Space do not need the `cloud_user` role simply to authenticate or access that Space. The authorization guidance similarly recommends avoiding the Cloud User role unless the user actually requires Project Manager responsibilities. 

## Choosing an Authentication Model

Customer federation is appropriate when the customer wants its enterprise identity platform to remain authoritative for users and group membership.

This allows the customer to manage identity lifecycle and authentication controls through its existing enterprise processes while K2cloud, Fabric, and TDM independently enforce their respective authorization models.

K2directory-hosted identities remain available where customer federation is not used or where separately managed identities are required.

## Related Documentation

- [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md)
- [Identity Federation](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_identity_federation.md)
- [Roles and Permissions](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_roles_and_permissions.md)
- [Space Access](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_space_access.md)
- [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md)