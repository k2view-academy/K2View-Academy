# How Fabric works with  SAML 

In this article we describe how Fabric work with SSO when using **SAML** protocol. 

The SAML can be used for the Fabric's [Web Framework](/articles/30_web_framework/01_web_framework_overview.md) and its apps like [Admin](/articles/30_web_framework/03_web_admin_application.md), [TDM]() (7.1 and later) and [DPM](), where Fabric follows SAML standard flows.

#### Log in

Consider the following scenarios: 

- A CRM user is logged into a system that acts as an identity provider, for example when login to his PC. The user wants to log in to Fabric by using a link from the organization CRM. 
- A support user is not logged into via the IDP, e.g. he gets at ticket link via SMS, leading to a Fabric app.

In both case the following happens:

1. The user clicks on a link that lead him to a Fabric app.
2. In case user already has an existing Fabric session he is enabled to access and make actions according to the permissions granted to him by Fabric and its apps. 
3. In case user does not have an active session, Fabric redirects the user to the identity provider, asking for authentication. This is the authentication request.
4. The user either has an existing active browser session with the identity provider or establishes one by logging into the identity provider, via IDP login page.
5. The identity provider builds the authentication response in the form of an XML-document containing the user’s identifier, signs it using an X.509 certificate, and posts this information to Fabric, along with some other assertions and attributes.
6. Fabric verifies first that the response sent from the identity provider; verifies and open the response using the certification and encryption keys and algorithms; and finally process the and extract the content, including the groups/roles that the user is associated to.
7. Fabric establishes a Fabric session for the user enabling him access according to the permissions granted to him by Fabric and its apps. Fabric session provides via the *UsercCode* the information about the user and the roles which are associated to.



Following is the logical flow that illustrate these steps: 

<img src="/articles/26_fabric_security/images/11_Fabric_SAML_login.jpg">

See [here](/articles/26_fabric_security/12_web_ login.md) for more information about user experience login process.



#### Log out and session expiration

In case user click to logout his Fabric session is invalidated. similarly after some time that user was idle his Fabric session is expired. 

In both cases Fabric start the authentication process with the IDP, as explained at login Flow in step #3.





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/09_user_IAM_SAML_ fundamentals_and_terms.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/11_user_IAM_LDAP.md)

