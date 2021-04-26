# User Identification and Access Management

## Overview

Fabric provides user identification and access management (IAM) for both web and console access, either using Fabric local repository or by using the organization’s identify provider (IDP) which Fabric integrated with. 

Using the organization’s identify provider gives the organization a full and dynamic control on access permissions to Fabric. It lets the organization to add or remove users and to grant or revoke their permissions, using its own admin centralized tools. 



## Supported Identity Providers & Authenticators

Fabric supports several authentication providers:

- **Fabric**, used for both console and web access, using its identity. This is the Fabric default authentication method. for more information see [here]()

- **LDAP**, used for both console and web access. for more information see [here]()

- **ALDAP**, used for both console and web access. for more information see [here]()

- **SSO vis SAML**, used for web access. Using SSO brings additional benefits, other than admin operation perspectives: it improves the users experience who do not need to type their credentials repeatedly on different services among the organization. In terms of security, the authentication credentials are not handled or even known by Fabric when using SSO. for more information see [here]()

Fabric provides also the option to **block** the access either for console or web access. This empowering the security access control, for example when organization wishes that users’ access shall be limited only to specific node/s in cluster.

#### Proprietary Custom Authenticator

Fabric enables to use a proprietary custom authenticator, when required by the organization.

In order to use such custom authenticator, implement the interface `com.k2view.fabric.authentication.providers.Authenticator` and set the Fabric [configuration]() accordingly.

#### Sequence Authenticators

Fabric provides a flexible mechanism, enabling to define a sequence of authenticators, each is used as fallback to its predecessor.

For example, the console access can be set to be using LDAP and if it failed to try using Fabric credentials. This fallback mechanism shall be used carefully and on very specific cases.

#### Web, Console & Web Services Access Authentication

Fabric provides the flexibility to separate between the authenticators for the various access methods - web, console and WS.

For example, in case IAM is not handled by Fabric, apps might use web access via SAML and yet need information about users, which can bee achieved using LDAP. 

Such use case can be found at [TDM](/articles/TDM): An environment owner can see at the TDM web-app all _test_ users which are candidate to use this environment. The TDM web-app access can be _SSO/SAML_. Yet, to show to show the tester list, TDM activates a web-service which call to a Fabric command that get the information using the _LDAP_ authenticator, with the app admin credentials.  



## Data Protection in Transit and at Rest

When Fabric is integrated with external IDP or authenticator it applies security methods in both transit and at rest, as following:

- All supported transit methods provide secured SSL/TTPS access.

- SAML enables turning on and off the signing certification and encryption. The recommended and default settings is like that both are turned on in both SAML request and SAML response where it is strictly enforced by Fabric, i.e. reject unsigned or unencrypted messages.

- User, their credentials, and their association to roles are **not** stored at Fabric and by that empowering the organization access control capabilities.

- LDAP admin user credentials are encrypted at the Fabric configuration.



For more information about Fabric Security refer to these articles:

- [Fabric Security Overview](/articles/26_fabric_security/01_fabric_security_overview.md)
- [Fabric Credentials Overview](/articles/17_fabric_credentials/01_fabric_credentials_overview.md)
- [Fabric Devops Security](/articles/99_fabric_infras/devops/01_fabric_security_overview.md)



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)