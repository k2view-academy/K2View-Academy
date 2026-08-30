# Identity Federation

## Table of Contents

- [Overview](#overview)
- [Federation Model](#federation-model)
- [Group-to-Role Mapping](#group-to-role-mapping)
- [Federation Configuration](#federation-configuration)
  - [Customer Information](#customer-information)
  - [K2view Information](#k2view-information)
- [Metadata Exchange](#metadata-exchange)
- [Claims and Group Membership](#claims-and-group-membership)
- [Sign-In Flow](#sign-in-flow)
- [Space Access and Federation](#space-access-and-federation)
- [Changes to Federation Configuration](#changes-to-federation-configuration)
- [Related Documentation](#related-documentation)

## Overview

Identity federation allows customer users to authenticate with their enterprise Identity Provider (IdP) when accessing K2cloud Orchestrator and deployed Spaces.

K2cloud supports federation with SAML-compliant identity providers, including:

- Microsoft Entra ID,
- Okta,
- PingFederate,
- and other SAML-compliant IdPs.

The customer's IdP remains authoritative for user identity and group membership.

K2cloud uses CyberArk Identity as the federation layer between the customer IdP and K2view services.

## Federation Model

The federation model is:

```text
Customer IdP
    ↓
K2cloud Identity Federation
    ↓
K2cloud Orchestrator / Fabric Space / TDM
```

Responsibilities are separated:

- The **customer IdP** authenticates the user and provides identity and group information.
- **K2cloud Identity Federation** establishes the federation trust and maps customer identity groups to K2view authorization.
- **K2cloud Orchestrator, Fabric, and TDM** enforce the resulting roles and permissions.

Authentication through the customer IdP does not by itself grant access to K2cloud or a Space.

## Group-to-Role Mapping

For federated customers, authorization should be based on IdP groups rather than individual users.

The core mapping pattern is:

```text
Customer IdP Group
        ↓
K2cloud Identity Federation
        ↓
Fabric Role
        ↓
Optional TDM Permission Group
```

For example, an organization might establish separate IdP groups for:

- K2cloud Project Managers,
- Space administrators,
- developers,
- runtime users,
- TDM administrators,
- and TDM testers.

The customer manages membership in these groups through its enterprise identity-management processes.

K2view configures the corresponding federation mappings so that the appropriate K2cloud, Fabric, and TDM authorization can be applied.

For authorization design guidance, see [Roles and Permissions](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_roles_and_permissions.md).

## Federation Configuration

Federation requires configuration by both the customer and K2view.

### Customer Information

To configure federation, the customer typically provides information such as:

- IdP metadata XML or metadata URL,
- email domain,
- IdP group names,
- group identifiers where required,
- intended role mappings,
- and required claims.

The email domain is used as part of routing users to the appropriate customer IdP.

Depending on the identity provider, both a group name and its unique identifier may be required.

### K2view Information

K2view provides the information required to configure the customer IdP for K2cloud federation.

This can include:

- K2cloud federation metadata,
- Service Provider (SP) details,
- callback or reply URLs,
- and other required SAML configuration.

Use the values supplied by K2view for the customer federation configuration rather than assuming values from another K2cloud implementation.

## Metadata Exchange

Federation requires trust between the customer IdP and the K2cloud federation service.

This trust is established through SAML metadata exchange.

Metadata can include:

- Entity IDs,
- SSO endpoints,
- SLO endpoints,
- Assertion Consumer Service URLs,
- signing certificates,
- and related SAML configuration.

K2view uses the customer IdP metadata to configure the federation trust.

The customer uses K2view-provided metadata to configure the corresponding SAML integration in the enterprise IdP.

## Claims and Group Membership

The SAML assertion provides identity information required by the federation configuration.

Depending on the integration, claims can include:

- User Principal Name,
- email address,
- display name,
- login name,
- group membership,
- and organization.

**Group membership is particularly important because authorization mappings depend on receiving the expected groups for the authenticated user.**

A user can therefore authenticate successfully but still be denied access when:

- the expected group claim is not present,
- the required group is not included in the assertion,
- the group does not match the configured federation mapping,
- or the mapped role does not provide access to the requested resource.

This distinction is important when troubleshooting federation: successful SAML authentication does not necessarily mean authorization has been configured correctly.

## Sign-In Flow

A typical Service Provider-initiated sign-in begins when the user accesses either:

```text
https://cloud.k2view.com
```

or an authorized Space URL.

The general flow is:

```text
User requests K2cloud Orchestrator or a Space
        ↓
K2cloud Identity Federation
        ↓
Customer IdP
        ↓
User authenticates
        ↓
SAML assertion returned
        ↓
Identity and group mappings evaluated
        ↓
Requested application evaluates authorization
```

After successful authentication and authorization, the user is redirected to the requested application.

## Space Access and Federation

The same federation architecture supports access to deployed Spaces.

A user does not need access to K2cloud Orchestrator simply to access an authorized Space.

For example, a developer can open the URL of a Studio Space directly. The user is authenticated through the configured identity federation and then authorized according to the Fabric roles mapped from the user's IdP groups.

This keeps K2cloud Orchestrator privileges separate from runtime Space access.

For more information, see [Space Access](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_space_access.md).

## Changes to Federation Configuration

Federation configuration should be treated as operational configuration rather than as a transient login setting.

Identity trust configuration can affect deployed Spaces and can include values such as:

- IdP Entity ID,
- SSO and SLO URLs,
- signing certificates,
- Service Provider configuration,
- Assertion Consumer Service configuration,
- and group mappings.

Changes to the customer IdP, federation metadata, certificates, or mappings should therefore be planned and validated carefully.

This is particularly important during changes such as:

- migration from one IdP to another,
- certificate replacement,
- SAML application replacement,
- group restructuring,
- or changes to identity claims.

Where possible, federation changes should be prepared and validated before the existing configuration is retired.

## Related Documentation

- [Identity and Access Overview](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_overview.md)
- [Authentication Models](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_authentication_models.md)
- [Roles and Permissions](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_roles_and_permissions.md)
- [Space Access](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_space_access.md)
- [Identity and Access Troubleshooting](/articles/80_k2cloud/08-identity-and-access/80_k2cloud_identity_and_access_troubleshooting.md)