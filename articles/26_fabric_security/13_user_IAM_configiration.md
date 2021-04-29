# User Identification & Access Management (IAM) Configuration

Fabric lets configuring the web access and the console access according to required authentication methods.

* The **web access** is based on the `WEB_AUTHENTICATION_PROTOCOL` property which gets one of 2 values:
  * "SAML", indicate to Fabric to use SAML as IDP. In this case the `saml` section properties shall be set properly. see more information [here]().
  * SERVER_AUTHENTICATOR", indicate to Fabric to use the authenticators which are defined at `server_authenticator` section.

- The **console access** is based on `server_authenticator` section definitions.



## SAML Configuration

Following are the configuration that shall be applied when using SAML for the web access. 



- **SP_ENTITYID**, the identity of the Fabric, in URI format, which shall be populated at the IDP 

- **SP_ASSERTION_CONSUMER_SERVICE_URL**, the endpoint at Fabric where the identity provider will redirect to with its authentication response. Format: `https://<host-name>:3213/api/authenticate`. The host name shall be the Fabric load-balancer hostname. This property is also populated at the IDP side.
- **IDP_ENTITYID**, the entity ID, in URI format, as supplied by the IDP.

- **IDP_SINGLE_SIGN_ON_SERVICE_URL**, the IDP endpoint for SAML request. 

- **SECURE**, flag indicating if certification and encryption is applied. Default is "true".

- **SP_CERT_ALIAS**, alias to the certification that is uploaded to the IDP,which is the public key to sign and encrypt the SAML response 

- **IDP_CERT_ALIAS**, alias to the certification that supplied by the IDP, which is the public key to to sign and encrypt the SAML request.



Note that some of the values are taken from the IDP and some are supplied to by Fabric during the setup preprations. For more information and guides per IDP see [Azure ADFS SAML Setup Guide](/articles/26_fabric_security/14_user_IAM_SAML_Azure_ADFS_setup.md) and [Okta SAML Setup Guide](/articles/26_fabric_security/15_user_IAM_SAML_Okta_setup.md).



## server_authenticator Configuration

`server_authenticator`  property requires to define which authenticator to be used and when needed an additional accompany section.

There are four authenticators which come as part of the Fabric platform: "fabric", "block_all", "ldap", "asldap", as following:

- **fabric**, when using Fabirc local Cassandra. For this no no further settings is required.
- **block_all** meaning that access is blocked. For this no no further settings is required. 
- **ldap**, connect to LDAP server. For this option the LDAP server connection details are required and shall be defined at section name: `ldap_auth`. 

- **adldap**. connect to AD/LDAP server. For this option the AD/LDAP server connection details are required and shall be defined at section name: `adldap_auth`.

The default authenticator is "fabric" when `server_authenticator` is not set.



The `adldap_auth` or `ldap_auth` sections shall define the following:

- **url** - LDAP URL endpoint.
- **security_level**, default is "simple".
- **admin_dn**, the admin user at LDAP that has permissions to search and look other users.
- **admin_password**, the admin user password.
- **users_base_dn**, the root base "dn" of the users.

The values shall be provided by the organization LDAP group.

### Sequence Authenticators

A sequence of authenticators can be specified them using comma separator:  `server_authenticator=<auth_1>[,<auth_2>, <auth_3>...]`.  For example: `server_authenticator=ldap,fabric`. The specified authenticators are evaluated by their order, where each is used as fallback to its predecessor.

Note that appropriate accompany section shall be added per authenticator. For example, if `server_authenticator=ldap,fabric` is configured then one additional section - "ldap_auth" - shall be added.

### Proprietary Custom Authenticator

In order to use a custom authenticator do the following at the config.ini:

1. add it to the `server_authenticator` authenticator list
2. add an new accompany section, following this naming convention: `<authenticator_name>_auth`. 
3. under this section add parameter named "class_name" where its value is the full class name of the implemented authenticator. Other parameters can be added too and will be passed to the authenticator when activated.



For more information about customer authenticator implementation see [here]().





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/12_web_ login.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/14_user_IAM_SAML_Azure_ADFS_setup.md)