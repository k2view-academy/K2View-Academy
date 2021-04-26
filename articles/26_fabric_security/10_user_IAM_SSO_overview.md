# SSO Overview

Single sign-on (SSO) is a user authentication service that permits the organization's employees to use one set of login credentials to access multiple applications and services. This service, also called IDP (Identification Provider), authenticates the user for all the applications the user has rights to, and eliminates further prompts when the user switches applications during the same session. On the backend, SSO is helpful for logging user activities as well as monitoring user accounts.

The benefits of using single sign-on include: 

- Mitigate risk for access to 3rd-party sites (user passwords not stored or managed externally).
- Improve the user experience by reducing the time spent re-entering passwords or to look over and over again for right expected passwords patterns. 
- Reduce IT costs due to lower number of IT help desk calls about passwords. 
- A centralized authentication service that all applications and systems use, improving IT security control. 

If your organization is using an SSO service, you can connect Fabric o it very easily.

Fabric supports the SAML - commonly used SSO protocol - toward variety IDPs such as Azure ADFS and Okta.



### SAML

Security Assertion Markup Language (SAML) is one of the standards protocol used for SSO. It provides a mechanism for exchanging authentication and authorization data between an identity provider (IDP) and a service provider (SP). 

The SP requests and obtains an authentication assertion from the IDP. On the basis of this assertion, the SP can make an access control decision, that is, it can decide whether to give access to the SP resources and to which of them. The interactions are XML based and use certification, encryption and other methods to secure these interactions and avoid frauds.



To learn more about SSO and SAML:

- 

To lean more about Fabric SSO configuration, refer to these guides:

- 



## Architecture and System Flow

#### Background

Modern authentication&authorization platforms and services are responsible for user **identification**, via **authentication** process and for **access management** via **authorization** mechanisms. Together they are also called **IAM** (Identification + Access Management).

There are 2 main commonly used modern methods for IAM:

1. **SAML** (Security Assertion Markup Language), responsible for both tasks and is geared towards enterprise security.
2. **OAuth + OIDC**, where OAuth is responsible for authorization and OIDC – OpenID Connect, is responsible for authentication. These methods are commonly by mobile apps, modern web apps, game consoles, and IoT devices.

Both methods define several actors that participate in every use case: Identity Provider (IdP), Relying Party (RP) or Service Provider (SP), and principal. 

- The **IdP** owns the users and their permissions. It authenticates principals and issues tokens/assertions.
- The **SP** is an application or resource that is protected and requires authentication and authorization of any principal wishing to access it. 
- The **principal** (human user or application/system) is an entity that can be authenticated.

SP is no longer responsible or aware to the user credentials and interacts with the IdP using tokens to verify user authentication and to get authorization info.

SAML

SAML Security

#### How Fabric works with SSO / SAML 







[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)