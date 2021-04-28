# Web Login

The access to the Fabric web framework and its apps is done upon user credentials and permissions. 



## Login

In case there is no active Fabric session, user shall provides his credentials which are verified by the relevant IAM authenticator.

#### Fabric and LDAP Authenticators

<img src="/articles/26_fabric_security/images/16_login_fabric.png">

#### SAML Authenticator

User is redirected to the configured SAML IDP and see its login page, where he shall type his credentials. For example at Okta IDP:

<img src="/articles/26_fabric_security/images/16_okta_sso_login.jpg">

While preparing the authentication request and initiate the interaction with the IDP, a loading spinner might appear, indicating the user that process is proceeding.

## Logout

#### Fabric and LDAP Authenticators

On logout the Fabric session is invalidated and the user is directed back to the login page where he can type his credentials and login back to the web framework.

#### SAML Authenticator

On logout the Fabric session is invalidated and the user is directed to a "logout" page, where he can decide to reconnect.

<img src="/articles/26_fabric_security/images/16_sso_reconnect.jpg">

Clicking in the Reconnect button will activate SAML authentication process, where in case the IDP verify that its user's is still valid the user is getting back into the Fabric web framework. If IDP session is not valid then user will be redirected to the IDP login page.

## Session timeout

After some predefined idle time, with any user actions at the Fabric web framework, his session is expired and shall be renewed when he wish to continue to use it. When trying to make any action after session expiration, the user is notified about with a popup alert.

When either Fabric or LDAP authenticators are active, user is informed that he will redirected to the login page, to type his credentials again, where in case SAML authenticator is active he just notified that his session need to be renewed. In such case, while Fabric session was expired the IDP session is still valid and user can enjoy from the SSO benefits where he does not need  to go thru login process over again.

<img src="/articles/26_fabric_security/images/16_timeout_saml.png">

 

## User Details Panel

User Details panel located at the top right area of the web framework along with avatar icon.

Once clicked user can see the connected user name and the logout action.

In case of Fabric authenticator the "change passwords" action appears too, otherwise it is hidden and disabled. Below is the opened panel when using an external authenticator:

<img src="/articles/26_fabric_security/images/16_details_panel_ext.png">





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)