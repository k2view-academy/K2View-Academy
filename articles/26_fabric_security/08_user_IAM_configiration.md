## User Identification & Access Management (IAM) Configuration

Fabric lets configuring the web access and the console access according to required authentication methods.

* The **web access** is based on the `WEB_AUTHENTICATION_PROTOCOL` property which gets one of 2 values:
  * "SAML", indicate to Fabric to use SAML as IDP. In this case the `saml` section properties shall be set properly. see more information [here]().
  * "server_authenticator", indicate to Fabric to use the authenticators which are defined at `server_authenticator` section.

- The **console access** is based on `server_authenticator` section definitions.



## SAML

Following are the configuration that shall be applied when using SAML for the web access. 





Some of the actual values are taken from the IDP. For more information per IDP see [Azure ADFS SSO SAML Setup]() and [Okta SSO SAML Setup]().



## server_authenticator

`server_authenticator`  property requires to define which authenticator to be used and when needed an additional accompany section.

There are four authenticators which come as part of the Fabric platform: "fabric", "block_all", "ldap", "asldap", as following:

- **fabric**, when using Fabirc local Cassandra. For this no no further settings is required.
- **block_all** meaning that access is blocked. For this no no further settings is required. 
- **ldap**, connect to LDAP server. For this option the LDAP server connection details are required and shall be defined at section name: `ldap_auth`. 

- **adldap**. connect to AD/LDAP server. For this option the AD/LDAP server connection details are required and shall be defined at section name: `adldap_auth`.

The default authenticator is "fabric" when `server_authenticator` is not set.



The `adldap_auth` or `ldap_auth` sections shall define the following:

- url - LDAP URL endpoint.
- security_level, default is "simple".
- admin_dn, the admin user at LDAP that has permissions to search and look other users.
- admin_password, the admin user password.
- users_base_dn, the root base "dn" of the users.

The values shall be provided by the organization LDAP group.

### Sequence Authenticators

A sequence of authenticators can be specified them using comma separator:  `server_authenticator=<auth_1>[,<auth_2>, <auth_3>...]`.  For example: `server_authenticator=ldap,fabric`. The specified authenticators are evaluated by their order, where each is used as fallback to its predecessor.

Note that appropriate accompany section shall be added per authenticator. For example, if `server_authenticator=ldap,fabric` is configured then one additional section - "ldap_auth" - shall be added.

### Proprietary Custom Authenticator

In order to use a custom authenticator it shall be added to the `server_authenticator` authenticator list and an accompany section shall be added, following the naming convention: `<authenticator_name>_auth`.





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)