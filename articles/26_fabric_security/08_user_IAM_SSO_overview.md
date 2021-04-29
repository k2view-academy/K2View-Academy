# SSO Overview

Single sign-on (SSO) is a user authentication service that permits the organization's employees to use one set of login credentials to access multiple applications and services. This service, also called IDP (Identification Provider), authenticates the user for all the applications the user has rights to, and eliminates further prompts when the user switches applications during the same session. On the backend, SSO is helpful for logging user activities as well as monitoring user accounts.

The benefits of using single sign-on include: 

- Mitigate risk for access to 3rd-party sites (user passwords not stored or managed externally).
- Improve the user experience by reducing the time spent re-entering passwords or to look over and over again for right expected passwords patterns. 
- Reduce IT costs due to lower number of IT help desk calls about passwords. 
- A centralized authentication service that all applications and systems use, improving IT security control. 

If your organization is using an SSO service, you can connect Fabric o it very easily.



There are 2 main commonly used modern methods for SSO:

1. **SAML** (Security Assertion Markup Language), responsible for both tasks and is geared towards enterprise security.
2. **OAuth + OIDC**, where OAuth is responsible for authorization and OIDC – OpenID Connect, is responsible for authentication. These methods are commonly by mobile apps, modern web apps, game consoles, and IoT devices.



## SAML

Security Assertion Markup Language (SAML) is one of the standards protocol used for SSO. It provides a mechanism for exchanging authentication and authorization data between an identity provider (IDP) and a service provider (SP). 

The SP requests and obtains an authentication assertion from the IDP. On the basis of this assertion, the SP can make an access control decision, that is, it can decide whether to give access to the SP resources and to which of them. The interactions are XML based and use certification, encryption and other methods to secure these interactions and avoid frauds.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/07_user_IAM_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/09_user_IAM_SAML_ fundamentals_and_terms.md)

