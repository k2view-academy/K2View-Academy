# User IAM Configuration

## Table of Contents

- [Overview](#overview)  
  - [Access Configuration](#access-configuration)  
    - [Web Access](#web-access)  
    - [Console Access](#console-access)  

- [Configuring Authentication](#configuring-authentication)  
  - [Built-in Authenticators](#built-in-authenticators)  
    - [fabric](#fabric)  
    - [cassandra](#cassandra)  
    - [ldap](#ldap)  
    - [adldap](#adldap)  
    - [block_all](#block_all)  
  - [Defaults and Custom Options](#defaults-and-custom-options)  
  - [Sequence Authenticators](#sequence-authenticators)  
    - [Example](#example)  
    - [Using Multiple Instances of the Same Type](#using-multiple-instances-of-the-same-type)  
    - [Worked Example](#worked-example)  

- [SAML Configuration](#saml-configuration)  
  - [Preparations & Prerequisites](#preparations--prerequisites)  
  - [Information Required from the IdP](#information-required-from-the-idp)  
  - [Provide the IDP](#provide-the-idp)  
  - [Generate a Self-signed Certificate](#generate-a-self-signed-certificate)  
  - [Keystore Verification](#keystore-verification)  
  - [Editing the config.ini File](#editing-the-configini-file)  
    - [SAML Attributes as JWT Claims](#saml-attributes-as-jwt-claims)  
    - [Configurations for Windows OS](#configurations-for-windows-os)  

- [LDAP & LDAPS Configuration](#ldap--ldaps-configuration)  
  - [Preparations & Prerequisites](#preparations--prerequisites-1)  
    - [Admin Role Settings](#admin-role-settings)  
    - [LDAPS: Secure LDAP](#ldaps-secure-ldap)  
  - [Editing the config.ini file](#editing-the-configini-file-1)
  - [AD LDAP Login attribute selection](#ad-ldap-login-attribute-selection)

- [Custom Authenticator](#custom-authenticator)  


## Overview

Fabric allows you to configure both **web access** and **console access** according to the authentication methods you choose.

All configuration is managed in Fabric’s main configuration file — **config.ini**.  
For more details about this file, see [Fabric Main Configuration Files](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini).

### Access Configuration

Locate the relevant parameters in the following sections:

- **Web Access**  
  Controlled by the `WEB_AUTHENTICATION_PROTOCOL` property, which can be set to one of two values:  
  1. **`SAML`** — Fabric uses SAML as its identity provider (IDP). When this option is selected, the `[saml]` section properties must be configured as described later in this article.  
  2. **`SERVER_AUTHENTICATOR`** — Fabric uses the authenticators defined in the `[server_authenticator]` section.  
     - This is the default option.  
     - When using this mode, the `WEB_AUTHENTICATION_PROTOCOL` entry may be left commented out.

- **Console Access**  
  Always based on the definitions in the `[server_authenticator]` section.

<br/>

## Configuring Authentication

The `server_authenticator` property defines which authenticator Fabric should use.  
If SAML is not enabled, this property also applies to **web access**.

### Built-in Authenticators

Fabric includes five built-in authenticators, which are considered reserved names:

- **fabric**  
  Uses Fabric itself (system_db) as the authenticator.  
  - No additional configuration is required.

- **cassandra**  
  Uses the Fabric local Cassandra instance as the [Fabric System DB](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md).  
  - In the `cassandra.yaml` configuration file, ensure that both parameters are set:  
    - `authenticator = PasswordAuthenticator`  
    - `authorizer = CassandraAuthorizer`  

  **Important notes:**  
  - The exact values should always be confirmed with the environment owner.  
  - Cassandra can serve as the authenticator even when Fabric uses SQLite as the system_db.  
  - When AWS Keyspaces is used, Fabric cannot create users. In such cases, it is recommended to use **Fabric** as the server authenticator so that Fabric manages the users.

  > **Note:** Fabric supports Cassandra versions **3.11.14**, **4.0.3**, and **4.1.3** as system DBs.  
  > The required `authenticator` and `authorizer` settings apply consistently across all supported versions.

- **ldap**  
  Connects to a standard LDAP server.  
  - Connection details must be provided in the `[ldap_auth]` section.

- **adldap**  
  Connects to an Active Directory/LDAP server.  
  - Connection details must be provided in the `[adldap_auth]` section.

- **block_all**  
  Denies all access.  
  - No configuration is required.  
  - This is the recommended option when SAML is enabled.

### Defaults and Custom Options

- If the `server_authenticator` property is not defined, the default authenticator is **fabric**.  
- Fabric also supports **proprietary/custom authenticators**, which can be configured as explained later in this article.


### Sequence Authenticators

You can configure a sequence of authenticators by listing them as a comma-separated list in the `server_authenticator` property. They are evaluated in order; each serves as a fallback for the one before it.

Example syntax:

  ```server_authenticator=<auth_1>[,<auth_2>,<auth_3>...]```

#### Example

   ```bash
   server_authenticator=ldap,fabric
   ```

In this setup:

  * ldap is attempted first.
  * If it fails, fabric is used as the fallback.

Each authenticator in the sequence must have its corresponding configuration section. For the example above, define the [ldap_auth] section (no extra section is required for fabric).

#### Using Multiple Instances of the Same Type

If you need multiple authenticators of the same type (e.g., two LDAP servers where one is a fallback for the other), follow these steps:

 1. Assign unique names to each instance, e.g., ldap1, ldap2.
 2. Create matching sections with the _auth suffix: [ldap1_auth], [ldap2_auth].
 3. Specify the class name in each section (because ldap1/ldap2 are not reserved names): class_name=com.k2view.fabric.authentication.providers.LdapAuthenticator

#### Worked Example

  ```bash
  server_authenticator=ldap1,ldap2,fabric
  
   [ldap1_auth]
   class_name=com.k2view.fabric.authentication.providers.LdapAuthenticator
   # url=ldaps://ldap1.example.com
   # admin_dn=cn=admin,dc=example,dc=com
   # admin_password=********
   # users_base_dn=ou=users,dc=example,dc=com
  
   [ldap2_auth]
   class_name=com.k2view.fabric.authentication.providers.LdapAuthenticator
   # url=ldaps://ldap2.example.com
   # admin_dn=cn=admin,dc=example,dc=com
   # admin_password=********
   # users_base_dn=ou=users,dc=example,dc=com
  ```

This pattern ensures Fabric correctly recognizes and evaluates each authenticator—even when multiple instances of the same type are used.

<br/>

## SAML Configuration

### Preparations & Prerequisites

Integrating Fabric with a SAML Identity Provider (IdP) requires an exchange of configuration details between the **Identity Provider (IdP)** and the **Service Provider (SP)** (Fabric). Each side must upload and configure the information provided by the other.

---

### Information Required from the IdP

Obtain the following values from your IdP’s administration interface:

- **IdP Entity ID**  
  A unique identifier for the IdP.

- **IdP Service URL**  
  The IdP endpoint to which Fabric redirects users for authentication.

- **Certificate File**  
  The IdP’s public key certificate. Import it into the Fabric truststore with the following command:

  ```bash
  $FABRIC_HOME/fabric/scripts/certificates.sh addtrust <ALIAS-NAME> <full-path-to-downloaded-cert-file>
  ```

The default truststore path is `$FABRIC_HOME/config/.truststore`.
The chosen alias name must also be set in the configuration for the IDP_CERT_ALIAS property.
(You may use any alias name, as long as it matches here and in the configuration.)


### Provide the IDP

   * SP entity ID - The unique identifier for Fabric as a Service Provider.
   * ACS URL - The Fabric endpoint that receives the SAML response.
   * Certificate file - Fabric’s public key certificate, generated from its private key. Create it using:

   ```bash
   keytool -export \
      -alias <ALIAS-NAME> \
      -storepass <password> \
      -file <output-filename>.cer \
      -keystore <full-path-to-keystore-file>
   ```

   The default keystore path is `$FABRIC_HOME/config/.keystore`.

  The specified alias name must be used for the *SP_CERT_ALIAS* property in your configuration. `fabric_cert` is used by default in the config.ini. Ensure that you use the alias of the already stored certificate. Please verify the alias using the keytool list command.

  Use the same alias value in the configuration for the `SP_CERT_ALIAS` property. Confirm the alias exists by running:

    ```bash
    keytool -list -keystore <full-path-to-keystore-file>
    ```

### Generate a Self-signed Certificate

If you need to generate a self-signed certificate, you can follow the steps of the <a href="/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md">Generating a Self-Signed Certificate in Fabric</a> article explains how to create using the `certificates.sh` helper script, and alternatively, how to use the `keytool` utility directly. 


### Keystore Verification

You can verify the certification keys by using this command: `keytool -list -storepass <password> -keystore <path to the JKS repository file>`. JKS (Java KeyStore) is a repository of security certificates – either authorization certificates or public key certificates – along with their corresponding private keys.

While running this command after running the above two keytool's *import* and *export* commands, you will see the corresponding two entries and notice, for each entry, its alias, last modified date, its type, and its fingerprint. Below is an example of the command's output using Okta as IDP:

`okta, Apr 28, 2021, trustedCertEntry, Certificate fingerprint (SHA1): 7F:CD:76:A6:B2:47:53:7E:BD:9E:20:44:B0:25:6B:78:A9:E3:25:40`
`k2view, Apr 18, 2021, PrivateKeyEntry, Certificate fingerprint (SHA1): 2C:9B:F3:8E:60:E6:BC:9F:82:84:A6:55:BE:62:2B:87:7D:42:BB:46`

For more information about keystore handling, please read [here](https://support.k2view.com/Academy/articles/99_fabric_infras/devops/03_fabric_api_and_ui_hardening.html).

<br/>

For more information and guidelines as to where and how this information should be set in IDPs, refer to [Azure AD SAML Setup Guide](/articles/26_fabric_security_iam/14_user_IAM_SAML_Azure_AD_setup.md) and [Okta SAML Setup Guide](/articles/26_fabric_security_iam/15_user_IAM_SAML_Okta_setup.md).



### Editing the config.ini File

The following configuration actions should be applied in the Fabric **config.ini** configuration file, using the information set and acquired during the preparation step.

Edit these properties in the `[saml]` section:

- **SP_ENTITYID** - the identity of the Fabric, in URI format, which should be populated in the IDP. 
- **SP_ASSERTION_CONSUMER_SERVICE_URL** - the endpoint in Fabric to which the identity provider will redirect with its authentication response. Format: `https://<HOSTNAME>:<PORT>/api/authenticate`. The host name should be the Fabric load balancer hostname (a DNS name can also be used). This property is also populated on the IDP side.
- **IDP_ENTITYID** - the entity ID, in URI format, as provided by the IDP.
- **IDP_SINGLE_SIGN_ON_SERVICE_URL** - the IDP endpoint for the SAML request. 
- **SECURE** - a flag indicating whether certification and encryption should apply. The default is *true*.
- **SP_CERT_ALIAS** - alias to the certification that is uploaded to the IDP. This certification is the public key for the SAML response. 
- **IDP_CERT_ALIAS** - alias to the certification supplied by the IDP. This certification is the public key for the SAML request.
- **GROUPS_KEY_MAPPING** (optional) - the name of the groups list, as retrieved from the IDP, as part of the SAML response. Although Fabric expects this value to be "groups" (its default), you can add this parameter and set its value when required.  
- **SP_SECURE** (optional; default is *true*) - indicates whether the expected SAML response is secured (encrypted). Set it to *false* when the client's IDP team does not intend to upload and use the certificate file provided by K2view. Without the public key, encrypting the response can’t take place.



Moreover, the `WEB_AUTHENTICATION_PROTOCOL` property's value shall be set to "SAML".


#### SAML Attributes as JWT Claims

**Requires**: Fabric 8.4.4 or later

Fabric can forward attributes from the SAML response as claims on the JWT it issues, making them available to project implementation code via `sessionUser().claims()`. Disabled by default.

Enable in the `[saml]` section of `config.ini`:

```
ATTRIBUTES_AS_CLAIMS=true
```

For the full description, including reserved attribute names, groups handling, and usage from implementation code, see [Custom Claims from SAML Attributes](/articles/26_fabric_security/06_jwt-custom-claims-and-iid-access-control.md#custom-claims-from-saml-attributes).



#### Configurations for Windows OS

When Fabric is running on a Windows OS, typically during project implementation or testing, when working locally, the following additional steps are required in relation to the certification files.

1. Open the "fabric-server-start.bat" located in the Fabric server scripts directory - "<FABIRC_HOME>\Server\fabric\scripts".
2. Update the following four properties:
   - set javax_net_ssl_keyStore - the full path of the Fabric key store repository (jks file). This repository stores the private key upon which the generated public key is based (this key is sent to the IDP).
   - set javax_net_ssl_keyStorePassword
   - set javax_net_ssl_trustStore - the full path of the trust store, where the public key, which is retrieved from the IDP, is stored (for the local machine, you can use the same JKS file that holds the keystore).
   - set javax_net_ssl_trustStorePassword
3. Restart Fabric.

<br/>

## LDAP & LDAPS Configuration

### Preparations & Prerequisites

##### Admin Role Settings

When LDAP is used as an authenticator, an admin role is automatically created for Fabric Bootstrap. The admin role's name should be configured in the *admin_privileges* configuration file (Use $FABRIC_HOME/config.template/admin_privileges.template file as reference). 

The LDAP owner should provide the name of the role.

##### LDAPS: Secure LDAP

When working with LDAPS (this is a secure LDAP), you must get a certificate file from the LDAP owner and import it into the Fabric truststore as shown:

### Editing the config.ini file

The `adldap_auth` or `ldap_auth` sections must define the following:

- **url** - an LDAP URL endpoint. For an LDAPS (Secure LDAP) connection, it starts with "ldaps://" (note that it is in lowercase letters).
- **security_level** - set to "simple" (the default value, which can also be used for an LDAPS).
- **admin_dn** - the LDAP admin user that has permissions to search and look for other users.
- **admin_password** - the admin user password.
- **users_base_dn** - the root base "dn" of the users.

The LDAP owner should provide the values.

The instructions for using LDAP and LDAPS can be found [here](/articles/26_fabric_security_iam/11_user_IAM_LDAP.md).

<br/>

### AD LDAP Login attribute selection

**Requires**: Fabric 8.3.0 hf3 or later

Fabric’s AD/LDAP authenticator supports using CN and/or sAMAccountName as the username attribute. This lets organizations keep their standard login practice (e.g., LAN ID via sAMAccountName).

**When to use**

* Your AD users typically sign in with LAN ID (sAMAccountName) instead of their Common Name (cn).
* You want to accept either cn or sAMAccountName at the login prompt.

**Configuration**
Add or update the username_attribute setting under the [adldap_auth] section of your config.ini.

 ```bash
SERVER_AUTHENTICATOR=adldap

[adldap_auth]
url=ldaps://<host>:636
security_level=simple
admin_dn=CN=<serviceUser>,OU=...,DC=...,DC=...
admin_password=<secret>
users_base_dn=OU=<...>,DC=<...>,DC=<...>
# Accept one or both attributes (comma-separated list allowed)
username_attribute=cn                    # single attribute
# username_attribute=sAMAccountName      # single attribute
# username_attribute=cn,sAMAccountName   # accept either
# username_attribute=sAMAccountName,cn   # accept either (order preference)
 ```
**Notes**
* Default behavior (when not set) remains cn, preserving backward compatibility.
* You may specify one attribute or a comma-separated list (order does not affect authentication).

**Known Issues**
Fabric's LDAP authenticator does not chase LDAP referrals. This means the user_base_dn may need to be more specific to avoid cross-forest referrals that are not supported. 


## Custom Authenticator

To use a custom authenticator, do the following in the config.ini:

1. Add the `server_authenticator` authenticator list.
2. Add a new accompanying section, following this naming convention: `<authenticator_name>_auth`. 
   - Under this section, add a parameter named "class_name" where its value is the full class name of the implemented authenticator. Additional parameters can also be added and will be passed to the authenticator when activated.

For more information about customer authenticator implementation, read [here](/articles/26_fabric_security_iam/17_user_IAM_custom_authenticator.md).



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security_iam/12_web_login.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security_iam/14_user_IAM_SAML_Azure_AD_setup.md)

