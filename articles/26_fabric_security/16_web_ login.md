# Web Login

The access to the Fabric web framework and its apps is done upon user credentials and permissions. 



## Login

In case there is no active Fabric session, user shall provides his credentials which are verified by the relevant IAM authenticator.

#### Fabric and LDAP Authenticators



#### SAML Authenticator

User is redirected to the configured SAML IDP and see its login page, where he shall type his credentials. For example at Okta IDP:

<img src="/articles/26_fabric_security/images/16_okta_sso_login.jpg">

## Logout

#### Fabric and LDAP Authenticators



#### SAML Authenticator

On logout the Fabric session is invalidated and the user is directed to a "logout" page, where he can decide to reconnect.

<img src="/articles/26_fabric_security/images/16_sso_reconnect.jpg">

Clicking in the Reconnect button will activate SAML authentication process, where in case the IDP verify that its user's is still valid the user is getting back into the Fabric web framework. If IDP session is not valid then user will be redirected to the IDP login page.

## Session timeout

#### Fabric and LDAP Authenticators



#### SAML Authenticator





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)