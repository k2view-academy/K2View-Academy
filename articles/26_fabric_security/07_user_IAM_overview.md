# User Identification and Access Management

## Overview and Fundamentals 

Modern auth&auth platforms are responsible for user **identification**, via **authentication** process and for **access management** via **authorization** mechanisms. Together they are also called Identification and Access (IAM).

Such platforms and related protocols usually define several actors that are present in every use case: *Identity Provider (IDP), Relying Party (RP) or Service Provider (SP), and principal*:

- The *IDP* authenticates principals and issues tokens (or assertions). 
- The *SP* is an application or resource that is protected and requires authentication and authorization of any principal wishing to access it. 
- The *principal* (typically a user, though maybe a system actor) is an entity that can be authenticated.

In processes perspective: 

- *Authentication* is the process of the principal proving its identity to the system.  This process is done by the IDP. SP is not responsible or aware to the user credentials or actual identity.

- *Authorization* is the process by which a protected system (the SP, in this case) makes a decision of whether an authenticated principal is allowed to access the system.



Fabric provides user identification and access management (IAM) for web, console and web-services access, either using Fabric local repository or by using the organization’s identify provider (IDP) which Fabric integrated with as Service Provider (SP). 

Using the organization’s IDP gives the organization a full and dynamic control on access permissions to Fabric. It lets the organization to add or remove users and to grant or revoke their permissions, using its own admin centralized tools. In addition, the organization has a centralized auditing log, covers their users’ access to all its services and resources. 

As explained above, the authorization part is done by Fabric, using its roles and permissions mechanism.

## Identity Providers & Authenticators

Fabric works with several authentication providers. Each authenticator is responsible to the user authentication, to provide user-name and his roles.

Following are the supported authentication providers:

- **Fabric**, for console, WS and web access, using its repository. This is Fabric default authentication.

- **LDAP**, for console, WS and web access. Done via LDAP integration. For more information see [here](/articles/26_fabric_security/11_user_IAM_LDAP.md).

- **ADLDAP**, for console, WS and web access. Done via LDAP integration. For more information see [here](/articles/26_fabric_security/11_user_IAM_LDAP.md).

- **SAML**, for web and WS access. Done via SAML IDP integration. For more information see [here](/articles/26_fabric_security/09_user_IAM_SAML_fundamentals_and_terms.md).

  Using SAML is actually adopting SSO methodology which brings additional benefits, other than admin operation perspectives: it improves the users experience who do not need to type their credentials repeatedly on different services among the organization. In terms of security, the authentication credentials are not handled or even known by Fabric. See [here](/articles/26_fabric_security/10_user_IAM_SSO_overview.md) more about SSO.

  Fabric is ready to work with commonly used and major IDPs such as Azure AD and Okta. 

Fabric also provides the option to **block** the access either for console or web access. This empowering the security access control, for example when organization wishes that users’ access shall be limited only to specific node/s in cluster.

See [here](/articles/26_fabric_security/13_user_IAM_configiration.md) how to define and configure the authenticator. 

#### Proprietary Custom Authenticator

Fabric enables to use a proprietary custom authenticator, when required by the organization.

In order to use such custom authenticator, implement the interface `com.k2view.fabric.authentication.providers.Authenticator` and set the Fabric configuration accordingly. For more information see [here](/articles/26_fabric_security/17_user_IAM_custom_authenticator.md)

#### Sequence Authenticators

Fabric provides a flexible mechanism, enabling to define a sequence of authenticators, each is used as fallback to its predecessor.

For example, the console access can be set to be using LDAP and if it failed to try using Fabric credentials. This fallback mechanism shall be used carefully and on very specific cases. For more information see [here](/articles/26_fabric_security/13_user_IAM_configiration.md#sequence-authenticators).

Note that web access can be set to be either SAML or other authenticators and cannot be combined. 

#### Authentication method by Channel

Fabric provides the flexibility to separate between the authenticators for the various access methods - web, console and WS. Note that in order WS will work with the authenticators

For example, in case IAM is not handled by Fabric, apps might use web access via SAML and console access via LDAP.  

Note that in order web service (WS) will be managed by the above authenticators when using JWT token as explained [here](/articles/26_fabric_security/05_fabric_webservices_security.md).

#### User Information at UserCode 

*UserCode*, which can be used at project implementation code, has a method called `sessionUser()` that provides an *SessionUser* object that contain the user-name and roles, relevant to the current user session and that populated by the relevant active authentication provider. This is useful to apply  permissions by roles at code. More information can be found at the Fabric online Javadoc.



## Data Protection in Transit and at Rest

When Fabric is integrated with external IDP or authenticator it applies security methods in both transit and at rest, as following:

- All supported transit methods provide secured SSL/HTTPS access.

- SAML Fabric & IDP intersections are done using certification and encryption methods.

- User, their credentials, and their association to roles are not stored at Fabric and by that empowering the organization access control capabilities. Note that roles themselves - their definitions and associated permissions are managed by Fabric.

- LDAP admin user credentials are encrypted at the Fabric configuration. 



## Auditing

Users’ login access to Fabric is recorded into Fabric Auditing mechanism with the information about the channel and the authenticator which has been used. For more information see [here](/articles/26_fabric_security/16_user_IAM_auditing.md).



For more information about Fabric Security refer to these articles:

- [Fabric Security Overview](/articles/26_fabric_security/01_fabric_security_overview.md)
- [Fabric Credentials Overview](/articles/17_fabric_credentials/01_fabric_credentials_overview.md)
- [Fabric Devops Security](/articles/99_fabric_infras/devops/01_fabric_security_overview.md)



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/08_user_IAM_SSO_overview.md)