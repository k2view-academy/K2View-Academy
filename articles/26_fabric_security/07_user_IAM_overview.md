# User Identification and Access Management

## Overview

Fabric provides user identification and access management (IAM) for web, console and web-services access, either using Fabric local repository or by using the organization’s identify provider (IDP) which Fabric integrated with. 

Using the organization’s IDP gives the organization a full and dynamic control on access permissions to Fabric. It lets the organization to add or remove users and to grant or revoke their permissions, using its own admin centralized tools. In addition, the organization has a centralized auditing log, covers their users’ access to all its services and resources. 



## Supported Identity Providers & Authenticators

Fabric supports several authentication providers:

- **Fabric**, for console, WS and web access, using its repository. This is Fabric default authentication.

- **LDAP**, for console, WS and web access. Done via LDAP integration. For more information see [here]()

- **ALDAP**, for console, WS and web access. Done via LDAP integration. For more information see [here]()

- **SSO vis SAML**, for web and WS access. Done via SAML IDP integration. For more information see [here]()

  Using SSO brings additional benefits, other than admin operation perspectives: it improves the users experience who do not need to type their credentials repeatedly on different services among the organization. In terms of security, the authentication credentials are not handled or even known by Fabric. 

  Fabric is ready to work with commonly used and major IDPs such as [Azure]() and [Okta](). 

Fabric also provides the option to **block** the access either for console or web access. This empowering the security access control, for example when organization wishes that users’ access shall be limited only to specific node/s in cluster.

#### Proprietary Custom Authenticator

Fabric enables to use a proprietary custom authenticator, when required by the organization.

In order to use such custom authenticator, implement the interface `com.k2view.fabric.authentication.providers.Authenticator` and set the Fabric configuration accordingly. For more information see [here]()

#### Sequence Authenticators

Fabric provides a flexible mechanism, enabling to define a sequence of authenticators, each is used as fallback to its predecessor.

For example, the console access can be set to be using LDAP and if it failed to try using Fabric credentials. This fallback mechanism shall be used carefully and on very specific cases.

#### Authentication method by Channel

Fabric provides the flexibility to separate between the authenticators for the various access methods - web, console and WS.

For example, in case IAM is not handled by Fabric, apps might use web access via SAML and console access via LDAP.  



## Data Protection in Transit and at Rest

When Fabric is integrated with external IDP or authenticator it applies security methods in both transit and at rest, as following:

- All supported transit methods provide secured SSL/TTPS access.

- SAML Fabric & IDP intersections are done using certification and encryption methods.

- User, their credentials, and their association to roles are not stored at Fabric and by that empowering the organization access control capabilities.

- LDAP admin user credentials are encrypted at the Fabric configuration. 



## Auditing

Users’ login access to Fabric is recorded into Fabric Auditing mechanism with the information about the channel and the authenticator which has been used. For more information see [here]().



For more information about Fabric Security refer to these articles:

- [Fabric Security Overview](/articles/26_fabric_security/01_fabric_security_overview.md)
- [Fabric Credentials Overview](/articles/17_fabric_credentials/01_fabric_credentials_overview.md)
- [Fabric Devops Security](/articles/99_fabric_infras/devops/01_fabric_security_overview.md)



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)