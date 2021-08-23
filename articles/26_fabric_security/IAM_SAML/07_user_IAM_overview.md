# User Identification and Access Management

## Overview and Fundamentals 

Modern authentication and authorization platforms are responsible for user **identification** via an **authentication** process, and for **access management** via **authorization** mechanisms. Together they are also called Identification and Access Management (IAM).

Such platforms and related protocols usually define several actors that are present in every use case: *principal, Identity Provider (IDP)*, and *Relying Party (RP)* also called *Service Provider (SP)*:

- The *principal* (typically a user, though maybe a system actor) is an entity that can be authenticated.
- The *IDP* authenticates principals and issues tokens (or assertions). 
- The *SP/RP* is an application or resource that is protected and requires authentication and authorization of any principal wishing to access it. 

It is important to keep the definitions of these terms in mind: 

- *Authentication* is the process of the principal proving its identity to the system.  This process is done by the IDP. The SP is not responsible or even aware of the user credentials or the actual identity of the user. 

- *Authorization* is the process by which a protected system (the SP, in this case) makes a decision of whether an authenticated principal is allowed to access the system and which permissions does he have on a resource.



Fabric provides user identification and access management (IAM) for web, console, and web-services access, either using Fabric's local repository or by using the organization’s identify provider (IDP) which Fabric integrated with as Service Provider (SP). 

Using the organization’s IDP gives the organization full and dynamic control of access permissions to Fabric. It lets the organization add or remove users and grant or revoke their permissions, using its own admin centralized tools. In addition, the organization has a centralized auditing log which covers their users’ access to all its services and resources. 

The authorization process is carried out by Fabric, using its roles and permissions mechanism.

## Identity Providers & Authenticators

Fabric works with several authentication providers. Each authenticator is responsible for user authentication, and provides user-ID and his roles.

Following are the supported authentication providers:

- **Fabric**, for console, WS and web access, using its repository. This is Fabric's default authentication method. For more information about managing credentials and permissions at Fabric see [here](/articles/17_fabric_credentials/01_fabric_credentials_overview.md).

- **LDAP** server, for console, WS and web access. Done via LDAP integration. For more information see [here](/articles/26_fabric_security/11_user_IAM_LDAP.md).

- **ADLDAP** (Active Directory) server, for console, WS and web access. Done via LDAP integration. For more information see [here](/articles/26_fabric_security/11_user_IAM_LDAP.md).

- **SAML** server, for web and WS access. Done via SAML IDP integration. For more information see [here](/articles/26_fabric_security/09_user_IAM_SAML_fundamentals_and_terms.md).

  Using SAML is actually adopting the SSO methodology. This brings additional benefits, in addition to admin operation perspectives: first, it improves the user experience. Users do not need to type their credentials repeatedly on different services among the organization. Second, in terms of security, the authentication credentials are not handled or even known by Fabric. See [here](/articles/26_fabric_security/08_user_IAM_SSO_overview.md) more about SSO.

  Fabric is ready to work with commonly used and major IDPs such as Azure AD and Okta. 

Fabric also provides the option to **block** the access either for console or web access. This capability empowers the security access control, for example when an organization wants users access to be limited to only specific node/s in cluster.

See [here](/articles/26_fabric_security/13_user_IAM_configiration.md) how to define and configure Fabric's authenticator. 

### Proprietary Custom Authenticator

Fabric can work with a proprietary custom authenticator, when required by the organization.

To use such custom authenticator, implement the interface `com.k2view.fabric.authentication.providers.Authenticator` and set the Fabric configuration accordingly. For more information see [here](/articles/26_fabric_security/17_user_IAM_custom_authenticator.md)

### Sequence Authenticators

Fabric provides a flexible mechanism, in which the customer can define a sequence of authenticators. Each authenticator is used as a fallback to its predecessor.

For example, the console access can be set to be using an LDAP server, and if it fails to try using Fabric credentials. This fallback mechanism must be used carefully, and only in very specific cases. For more information see [here](/articles/26_fabric_security/13_user_IAM_configiration.md#sequence-authenticators).

Note that web access can be set to use either a SAML server, or other authenticators, but it cannot be combined with others. In other words, in case of using SAML server there can be no fallback.  

### Authentication method by Channel

Fabric provides the flexibility to separate between the authenticators for the various access methods - web, console and WS. 

For example, in case IAM is not handled by Fabric, apps might use web access via SAML and console access via LDAP.  

Note that for web service (WS) to be managed by SAML, a JWT token must be used, as explained [here](/articles/26_fabric_security/05_fabric_webservices_security.md).

### User Information at UserCode 

*UserCode*, which can be used in the project implementation code, has a method called `sessionUser()` . This function provides a *SessionUser* object that contains the user-name and roles, relevant to the current user session and that are populated by the relevant active authentication provider. This can be used, for example, to apply  permissions by roles in the code. More information can be found at the Fabric online Javadoc.



## Data Protection in Transit and at Rest

When Fabric is integrated with an external IDP or authenticator,  it applies security methods in both transit and at rest, as follows:

- All supported transit methods provide secured SSL/HTTPS access.

- Fabric & IDP interactions are done using certification and encryption methods. For more information see [here](/articles/26_fabric_security/09_user_IAM_SAML_fundamentals_and_terms.md)

- User, their credentials, and their association to roles are not stored at Fabric and are only managed by the IDP. In that way the organization is responsible for access management while Fabric only implements the roles policies,  based on the IDP group / role authorization definition, and use them to define their associated permissions in Fabric.

- The credentials that Fabric uses for querying users' information from LDAP, are encrypted. 



## Auditing

Users’ login access to Fabric is recorded into the Fabric auditing mechanism with the information about the channel and which authenticator which has been used. For more information see [here](/articles/26_fabric_security/16_user_IAM_auditing.md).



For more information about Fabric Security refer to these articles:

- [Fabric Security Overview](/articles/26_fabric_security/01_fabric_security_overview.md)
- [Fabric Credentials Overview](/articles/17_fabric_credentials/01_fabric_credentials_overview.md)
- [Fabric Devops Security](/articles/99_fabric_infras/devops/01_fabric_security_overview.md)



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/IAM_SAML/08_user_IAM_SSO_overview.md)