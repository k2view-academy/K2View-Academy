# User Identification and Access Management

## Table of Contents

1. [Overview and Fundamentals](#overview-and-fundamentals)  
2. [Identity Providers & Authenticators](#identity-providers--authenticators)  
   2.1 [Proprietary Custom Authenticator](#proprietary-custom-authenticator)  
   2.2 [Sequence Authenticators](#sequence-authenticators)  
   2.3 [Authentication method by Channel](#authentication-method-by-channel)  
   2.4 [User Information at UserCode](#user-information-at-usercode)  
3. [Data Protection in Transit and at Rest](#data-protection-in-transit-and-at-rest)  
4. [Auditing](#auditing)  


## Overview and Fundamentals 

Modern authentication and authorization platforms are responsible for user **identification** via an **authentication** process, and for **access management** via **authorization** mechanisms. Together, they are also referred to as Identity and Access Management (IAM).

Such platforms and related protocols usually define several actors that are present in every use case: *principal, Identity Provider (IDP)*, and *Relying Party (RP)*, also called *Service Provider (SP)*:

- The *principal* (typically a user, although it may be a system actor) is an entity that can be authenticated.
- The *IDP* authenticates principals and issues tokens (or assertions). 
- The *SP/RP* is an application or resource that is protected and requires authentication and authorization of any principal wishing to access it. 

It is essential to keep the definitions of these terms in mind: 

- *Authentication* is the process by which the principal proves its identity to the system.  The IDP carries out this process. The SP is neither responsible nor aware of the user's credentials or actual identity. 

- *Authorization* is the process by which a protected system (the SP, in this case) determines whether an authenticated principal is allowed to access the system and what permissions they have on a resource.


Fabric provides user identification and access management (IAM) for web, console, and web-services access, either using Fabric's local repository or by using the organization’s identity provider (IDP), which Fabric integrates with as a Service Provider (SP). 

Using the organization’s IDP provides the organization with complete and dynamic control over access permissions to Fabric. It enables the organization to add or remove users and grant or revoke their permissions using its centralized admin tools. Additionally, the organization maintains a centralized auditing log that tracks its users’ access to all services and resources. 

The authorization process is carried out by Fabric, using its roles and permissions mechanism.

## Identity Providers & Authenticators

Fabric works with several authentication providers. Each authenticator is responsible for user authentication and provides the user ID and their roles.

The following are the supported authentication providers:

- **Cassandra**, for console, WS, and web access. This Fabric  authentication method uses Cassandra as a System DB. When another System DB is being used, then it shall be changed to "Fabric".

- **Fabric**, for console, WS, and web access. When used, Fabric stores users along with their credentials in a System DB table. Passwords are stored securely in this table, using a salted password hashing technique. By default, Fabric is configured to use 32 32-byte salt length.

- **LDAP** server, for console, WS, and web access. Done via LDAP integration. For more information, see [here](/articles/26_fabric_security_iam/11_user_IAM_LDAP.md).

- **ADLDAP** (Active Directory) server, for console, WS, and web access. Done via LDAP integration. For more information, see [here](/articles/26_fabric_security_iam/11_user_IAM_LDAP.md).

- **SAML** server, for web access, done via SAML IDP integration. For more information, see [here](/articles/26_fabric_security_iam/09_user_IAM_SAML_fundamentals_and_terms.md).

**Using SAML as the SSO methodology**. This brings additional benefits, in addition to admin operation perspectives: first, it improves the user experience. Users do not need to type their credentials repeatedly on different services within the organization. Second, in terms of security, the authentication credentials are neither handled nor known by Fabric. See [here](/articles/26_fabric_security_iam/08_user_IAM_SSO_overview.md) more about SSO.

  Fabric is ready to work with commonly used and major IDPs such as Microsoft Entra ID and Okta. 

Fabric also provides the option to **block** access either for console or web access. This capability empowers the security access control, for example, when an organization wants users' access to be limited to only specific node/s in a cluster.

Read [here](/articles/26_fabric_security_iam/13_user_IAM_configuration.md) how to define and configure Fabric's authenticator. 

### Proprietary Custom Authenticator

Fabric can work with a proprietary custom authenticator when required by the organization.

To use such a custom authenticator, implement the interface `com.k2view.fabric.authentication.providers.Authenticator` and set the Fabric configuration accordingly. For more information see [here](/articles/26_fabric_security_iam/17_user_IAM_custom_authenticator.md)

### Sequence Authenticators

Fabric provides a flexible mechanism that allows customers to define a sequence of authenticators. Each authenticator is used as a fallback to its predecessor.

For example, the console access can be set to use an LDAP server, and if it fails, try using Fabric credentials. This fallback mechanism must be used carefully, and only in very specific cases. For more information see [here](/articles/26_fabric_security_iam/13_user_IAM_configuration.md#sequence-authenticators).

Note that web access can be configured to use either a SAML server or other authenticators, but it cannot be combined with other authentication methods. In other words, when using a SAML server, there is no fallback option.  

### Authentication method by Channel

Fabric provides the flexibility to separate authenticators for various access methods, including web, console, and WS. 

For example, in case Fabric does not handle IAM, apps might use web access via SAML and console access via LDAP.  

Note that for web service (WS) to be managed by SAML, a JWT token must be used, as explained [here](/articles/26_fabric_security/05_fabric_webservices_security.md).

### User Information at UserCode 

*UserCode*, which can be used in the project implementation code, has a method called `sessionUser()`. This function provides a *SessionUser* object that contains the username and roles relevant to the current user session, populated by the active authentication provider. This can be used, for example, to apply  permissions by roles in the code. More information can be found at the Fabric online Javadoc.



## Data Protection in Transit and at Rest

When Fabric is integrated with an external IDP or authenticator,  it applies security methods in both transit and at rest, as follows:

- All supported transit methods provide secure SSL/HTTPS access.

- Fabric and IDP interactions are performed using certificates and encryption methods. For more information see [here](/articles/26_fabric_security_iam/09_user_IAM_SAML_fundamentals_and_terms.md)

- User credentials and their association with roles are not stored at Fabric and are managed solely by the IDP. In this way, the organization is responsible for access management. At the same time, Fabric only implements the role policies based on the IDP group/role authorization definition and uses them to define the associated permissions in Fabric.

- The credentials that Fabric uses for querying users' information from LDAP are encrypted. 



## Auditing

Users’ login access to Fabric is recorded in the Fabric auditing mechanism, including information about the channel and the authenticator used. For more information, see [here](/articles/26_fabric_security_iam/16_user_IAM_auditing.md).



For more information about Fabric Security, refer to these articles:

- [Fabric Security Overview](/articles/26_fabric_security/01_fabric_security_overview.md)
- [Fabric DevOps Security](/articles/99_fabric_infras/01_fabric_security_overview.md)



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security_iam/08_user_IAM_SSO_overview.md)
