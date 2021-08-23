# How Fabric Works With SAML 

SAML can be used for the Fabric's [Web Framework](/articles/30_web_framework/01_web_framework_overview.md) and its apps like [Admin](/articles/30_web_framework/03_web_admin_application.md), [TDM](/articles/TDM/tdm_overview/README.md) (7.1 and later) and [DPM](/articles/DPM/01_DPM_Overview/02_DPM_Overview.md), where Fabric follows SAML standard flows.

#### Log in

Consider the following scenarios: 

- A CRM user is logged into a system that acts as an identity provider, for example when the user logs in to his PC. The user wants to log in to Fabric by using a link from the organization's CRM. 
- A support user is not logged into via the IDP (identity provider), e.g. he gets at ticket link via SMS, leading to a Fabric app.

In both cases,  the SP (service provider) initiates an access flow: 

The user clicks on a link that leads him to a Fabric app (1 in the diagram below). One of two access methods follow: 

   a) If the user already has an existing Fabric session (2):

​     He is allowed access and can carry out actions according to the permissions granted to him by Fabric and its apps.  

​     OR 

   b) If the user does not have an active session (3):

- Fabric redirects him to the IDP, asking for authentication. This is the authentication request. 

- The user then either has an existing active browser session with the identity provider or establishes one by logging into the identity provider, via the IDP login page (4).
- The identity provider builds the authentication response in the form of an XML-document containing the user’s identifier, signs it using an X.509 certificate, and posts this information to Fabric, along with various other assertions and attributes (5).
-  Fabric verifies and opens the response using the certification and encryption keys and algorithms, then processes and extracts the content, including the groups/roles with which the user is associated (6).
-  Fabric establishes a Fabric session for the user enabling him access according to the permissions granted to him by Fabric and its apps. The Fabric session provides (via the *UsercCode*) the information about the user and the roles with which he is associated (7).



Following is the logical flow that illustrate these steps: 

<img src="/articles/26_fabric_security/images/11_Fabric_SAML_login.jpg">

See [here](/articles/26_fabric_security/12_web_login.md) for more information about the user log-in process.



#### Log out and session expiration

If the user logs out, his Fabric session is invalidated. Similarly after a period of time during which the user is idle,  his Fabric session is terminated. 

In both cases,  Fabric starts the authentication process with the IDP, as explained in the Log In above (Step 3 in the diagram).




[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/IAM_SAML/09_user_IAM_SAML_fundamentals_and_terms.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/IAM_SAML/11_user_IAM_LDAP.md)

