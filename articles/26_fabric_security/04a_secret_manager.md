# **Secrets Management Integration** 

Fabric supports integration with Secrets Management services, with the intention of not storing secrets in Fabric itself. An example of a secret is a password that is used in [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md), [Environments](/articles/25_environments/01_environments_overview.md) and [Fabric System Database](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) as a way to enable communication with external systems. Click [here](/articles/26_fabric_security/04_fabric_interfaces_security.md) for further information about secured storage of secrets in Fabric.

Secrets Management services are tools that aim to securely store, manage, access, and audit sensitive information, such as passwords, API keys, and other credentials, across the organization. The features included in Secrets Management services are encryption, access controls, auditing, and automatic rotation of secrets.

The advantages of Secrets Management are: 

- Reducing the risk of secret leaks when providing a secret for each client application.
- Having a single source of truth that can be better controlled, changed, or rotated, manually or automatically.
- Manage access to secrets with fine-grained authorization policies.
- Detecting security breaches and attempted accesses to systems, done by analyzing audit logs and alerts that provide a detailed history of client interactions, which can also be used for guiding security policy enforcement.

Fabric supports integration with various external Secrets Management providers, in which case Fabric does not store the secrets but rather their reference IDs. 

These are the currently supported Secrets Management providers, along with their official webpages: 

-	AWS Secret Manager - [AWS Secret Manager](https://aws.amazon.com/secrets-manager/)

- HashiCorp Vault - [HashiCorp Vault](https://www.hashicorp.com/products/vault/secrets-management)

- Azure Key Vault - [Azure Key Vault](https://azure.microsoft.com/en-us/products/key-vault/)

- CyberArk CCP - [CyberArk CCP](https://docs.cyberark.com/credential-providers/Latest/en/Content/CCP/The-Central%20-Credential-Provider.htm)

- Google Cloud Secret Manager - [Google Cloud Secret Manager](https://cloud.google.com/security/products/secret-manager)

- OneIdentity Safeguard - [OneIdentity Safeguard](https://www.oneidentity.com/products/one-identity-safeguard-for-privileged-passwords/)




## How does it Work

1. The customer's security team administrator creates a set of credentials on either a database or a similarly secured resource server and then provisions them as secrets in the Secrets Management provider. The latter encrypts and stores the credentials within the secrets.
2. The administrator has to grant Fabric (client application) permission to approach these secrets.
3. When Fabric opens a connection to access the database/resource server via an interface, it examines whether its credentials are defined as reference IDs in the external Secrets Management provider. Fabric queries the Secrets Management provider for the relevant secrets if they are defined as such. 
4. The Secrets Management provider retrieves, decrypts, and returns the secrets to Fabric over a secured (HTTPS with TLS) channel.
5. Fabric uses the secrets as the resource server credentials, as defined in the interface.
6. Fabric caches the credentials in memory. If the connection to a resource server fails due to credentials, Fabric assumes that the credentials were changed, and it accesses the Secrets Management provider again to get them.



## Using Secrets Management Services

To use a Secrets Management provider, you should:

1. Set the configuration in the config.ini file with the selected Secrets Management provider's access and permission details.
2. Provision and mark the required interface connection details as those that should be taken from the Secrets Management provider as part of the project's implementation settings.

### Config.ini file

Each supported Secrets Management provider has a dedicated section in the config.ini file with all the required access and permission details.

In addition to populating these details, you **must** turn it on by setting the 'ENABLED' property to 'true' in the chosen Secrets Management provider section. 

The following are the required config.ini file attributes for each Secrets Management provider:

#### <ins>*AWS Secrets Manager*</ins>

Section name: [encryption_aws_sm]
properties:

* ACCESS_KEY_ID
* SECRET_ACCESS_KEY
* REGION

Authentication and authorization processes can be done by the service account associated with the server. This is an alternative to using an Access ID and an Access Key.



#### <ins>*HashiCorp Vault*</ins>

Section name: [encryption_hashicorp_sm]

The authentication within HashiCorp Vault is done by either tokens that can be used directly or using one of HashiCorp's other [auth methods](https://developer.hashicorp.com/vault/docs/concepts/auth), in which case the token is dynamically generated.

Fabric supports two authentication methods:

* Directly - where AUTH_TOKEN should be set.

  When using this method, Fabric accesses the Vault URL with the token as the auth credentials to get the secret.

* [AppRole](https://developer.hashicorp.com/vault/docs/auth/approle) - which is based on the role that Fabric is associated to in the Vault.

  When using the AppRole method, Fabric first accesses the *approle* URL to dynamically get a token, then uses the token as the auth credentials to get the secret. For this method, you should specify the following attributes:

  * ROLE_ID - the role that Fabric is associated with in the Vault.
  * SECRET_ID - the secret that is used to get the token.
  * APPROLE_URL (optional) - in cases where the AppRole endpoint is not the default setting (default setting ends with "/approle").

  

Additionally, this parameter should be set:

* URL - the Vault API endpoint for getting the secrets.

Optional properties:

* NAMESPACE - a secure multi-tenancy capability within Vault to provide isolation among teams in the organization. Read [here](https://developer.hashicorp.com/vault/tutorials/enterprise/namespaces) for more information about namespaces.



#### <ins>*Azure Key Vault*</ins>

Fabric supports one of the following authentication methods for Azure Key Vault:

 1. CLIENT_ID + CLIENT_SECRET + TENANT
 2. USE_MANAGED_IDENTITY_AUTH + CLIENT_ID + RESOURCE_ID 
 3. CLIENT_ID + USER_NAME + PASSWORD + TENANT_ID
 4. When Fabric is hosted on an Azure server, or when the Azure [CLI](https://learn.microsoft.com/en-us/cli/azure/) agent is installed and activated, Fabric is considered to be authenticated without providing further authentication credentials.

Section name: [encryption_azure_sm]

- KEY_VAULT_NAME - this property is required for any of the above 4 authentication methods.
- CLIENT_ID
- CLIENT_SECRET
- TENANT_ID
- USER_NAME
- PASSWORD
- RESOURCE_ID
- USE_MANAGED_IDENTITY_AUTH
- ENDPOINT_TEMPLATE (optional) - its default value is https://{key_vault_name}.vault.azure.net, where Fabric uses it according to the key vault name.



#### <ins>*CyberArk CCP*</ins>

Section name: [encryption_cyberark_sm]

The authentication is done by using either an API key or user and password and accordingly, the following parameters have to be set:

* AUTH_TOKEN
* AUTH_PASSWORD
* AUTH_USER

Other parameters:

* APP_ID (optional) - can be set in the config.ini file and the interface for more granularity.
* FOLDER (optional) - default is Root; this parameter can be specified or overridden per each secret.
* SAFE_NAME (optional) - this parameter can be specified or overridden per each secret.
* SERVER_IP - to be used in the URL parameter.
* TIMEOUT - default is 5000 ms.
* URL - expected format is https://{SERVER_IP}/AIMWebService/api/Accounts.



#### <ins>*Google Cloud Secret Manager*</ins>

Section name: [encryption_gcp_sm]

The authentication is done by a credentials file:

1. In the Google Cloud console
   * Select **IAM & admin** > **Service account**.
   * Find the service account you want to use.
   * Open your service account's Actions ⋮ menu, then select **Create key**.
   * In the resulting **Create private key** dialog, select the **JSON** option, create the key and download it.
2. Locate the file in the Fabric server.
3. Set the CREDENTIAL_FILE parameter, providing the path to the file.  

Other parameters:

* PROJECT_ID

* LOCATION_ID (optional) - in case you use a regional secret manager.

  

#### <ins>*OneIdentity Safeguard*</ins>

Section name: [encryption_safeguard_sm]

The authentication is done by certifications and keys that should be applied.

* HOST - this is the Safeguard URL used for all API calls. 
* TIMEOUT (optional) - default is 10000 ms.



## Interface Connection Details Settings

For Studio to mark an interface's property needing to be taken from the Secrets Management provider, you should use the following pattern for its value: 
> ${secretmanager:\<secret-manager-id\>}.

For example: ${secretmanager:mysql-password}.

* Each Secret Manager service follows a specific pattern, usually a hierarchy (e.g., with a dot sign inside the key name); you should follow this pattern.
* The Secrets Management service can also manage interface connection details in environments. Each environment and its interfaces are independent, so some environments, such as local testing, may use Secrets Management services, while others, such as local testing, might not. 
* When the Secrets Management service is activated, you can also use the Test connection option to validate the connection settings.
* The Secrets Management provider can handle the following properties: host, user, and password for the DB interface types. For all other interfaces, all connection details properties can be set to use the Secrets Management provider.  
* Additional notes and considerations regarding specific Secrets Management providers:
  
  * For CyberArk CCP, you can specify the *folder* and/or the *safe-name* parameters by using the '&' concatenating pattern, e.g., "${secretmanager:Safe=my-safe&Folder=my-folder&Object=mysql-password&AppID=}"

     >  The AppID parameter is optional and can be added for more granularity rather than a general AppID that can be set in the config.ini file.


  * For Safeguard, you should specify both the *asset name* and the *account name* parameters by using the '&' concatenating pattern, e.g., "${secretmanager:asset_name=OracleDB&account_name=PreProd}"

## Using a Secret Manager in Conjunction with the System DB

The credentials of the Fabric System Database are configured in the config.ini and are encrypted by Fabric. You can also use one of the integrated Secret Managers to store these. The following parameters of the config.ini are supported for the System Database:

 * SYSTEM_DB_HOST
 * SYSTEM_DB_PASSWORD
 * SYSTEM_DB_USER

For example, a key-value convention of “system_db.[value]” would be configured as follows when configured in the Secret Manager and used in conjunction with the System Database. In this example, the SYSTEM_DB_HOST key does not use a Secret Manager to host its value.

An excerpt from the config.ini file is shown here.

> \#\# System DB to use for Fabric internal storage (SQLITE,POSTGRESQL,CASSANDRA). Default: CASSANDRA
> SYSTEM_DB_TYPE=POSTGRESQL
> SYSTEM_DB_HOST=10.21.2.94
> SYSTEM_DB_PASSWORD=${secretmanager:system_db.SYSTEM_DB_PASSWORD}
> SYSTEM_DB_USER=${secretmanager:system_db.SYSTEM_DB_USER}
> SYSTEM_DB_PORT=5432
> SYSTEM_DB_DATABASE=test
  




[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04_fabric_interfaces_security.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
