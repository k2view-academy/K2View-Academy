# SSO Overview

Single sign-on (SSO) is a user authentication service that enables an organization's members (users) to use a single set of login credentials to access multiple applications and services. This service, also known as an Identity Provider (IDP), authenticates the user for all applications to which the user has access, and eliminates further prompts when the user switches between applications during the same session. On the backend, SSO helps log user activities and monitor user accounts.

The benefits of using single sign-on include: 

- Reducing the security risk for accessing 3rd-party applications (user passwords are stored and managed externally).
- Improving the user experience by reducing the time spent re-entering passwords or having to look up passwords again and again.  
- Lower IT efforts, resulting from a decrease in IT help desk calls regarding password issues.  
- Improved IT security control, due to a centralized authentication service that all applications and systems use. 

If your organization is using an SSO service, you can connect Fabric to it. Fabric supports SAML for SSO.

## SAML

Security Assertion Markup Language (SAML) is a standard protocol used for SSO. It provides a mechanism for exchanging authentication and authorization data between an identity provider (IDP) and a service provider (SP). 

The SP requests and obtains an authentication assertion from the IDP. Based on this assertion, the SP can make an access control decision. That is, it can decide whether to give access to some or all of the SP resources. The interactions are XML-based and utilize certification, encryption, and other security measures to safeguard these interactions and prevent fraud.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security_iam/07_user_IAM_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security_iam/09_user_IAM_SAML_fundamentals_and_terms.md)

