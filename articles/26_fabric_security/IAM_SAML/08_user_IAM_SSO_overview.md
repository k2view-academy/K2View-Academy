# SSO Overview

Single sign-on (SSO) is a user authentication service that permits an organization's members (users) to use one set of login credentials to access multiple applications and services. This service, also called IDP (Identification Provider), authenticates the user for all the applications the user has rights to, and eliminates further prompts when the user switches applications during the same session. On the backend, SSO is helpful for logging user activities as well as monitoring user accounts.

The benefits of using single sign-on include: 

- Reducing the security risk for accessing 3rd-party applications (user passwords are stored and managed externally).
- Improving the user experience by reducing the time spent re-entering passwords or having to look up passwords again and again.  
- Lower IT efforts, due to a lower number of IT help desk calls about passwords.  
- Improved IT security control, due to a centralized authentication service that all applications and systems use. 

If your organization is using an SSO service, you can connect Fabric to it. Fabric supports SAML for SSO.

## SAML

Security Assertion Markup Language (SAML) is a standard protocol used for SSO. It provides a mechanism for exchanging authentication and authorization data between an identity provider (IDP) and a service provider (SP). 

The SP requests and obtains an authentication assertion from the IDP. On the basis of this assertion, the SP can make an access control decision. That is, it can decide whether to give access to some or all of the the SP resources. The interactions are XML based and use certification, encryption and other methods to secure these interactions and avoid frauds.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/IAM_SAML/07_user_IAM_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/IAM_SAML/09_user_IAM_SAML_fundamentals_and_terms.md)

