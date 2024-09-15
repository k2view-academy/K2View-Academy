# **Secrets Management Integration** 

Fabric supports integration with Secrets Management services, with the intent that secrets - like passwords, used in [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md), [Environments](/articles/25_environments/01_environments_overview.md) and [Fabric System Database](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) that enable communication to external systems - will not be stored in Fabric itself. (For information on how can secrets be securely stored in Fabric - read [here](/articles/26_fabric_security/04_fabric_interfaces_security.md)).

Secrets Management services are tools aimed for securely storing, managing, accessing and auditing sensitive information such as passwords, API keys, and other credentials, across the organization. Features of Secrets Management services include encryption, access controls, auditing and automatic rotation of secrets.

Secrets Management has several advantages: 

- Reducing the risk of secret leaks when providing the secret for each client application.
- Having a single source of truth, which can be better controlled, changed or rotated, manually or automatically.
- Managing access to secrets with fine-grained authorization policies.
- Detecting security breaches and attempted access to systems, done by analyzing audit logs and alerts that provide detailed history of client interactions, which can also be used for guiding security policy enforcement.

Fabric supports integration with various external Secrets Management providers, in which case Fabric doesn't store the secrets but rather their reference IDs. 

Supported Secrets Management providers are: 

- [AWS Secret Manager](https://aws.amazon.com/secrets-manager/)
- [HashiCorp Vault](https://www.hashicorp.com/products/vault/secrets-management)
- [Azure Key Vault](https://azure.microsoft.com/en-us/products/key-vault/)
- [CyberArk CCP](https://docs.cyberark.com/credential-providers/Latest/en/Content/CCP/The-Central%20-Credential-Provider.htm)
- [Google Cloud Secret Manager](https://cloud.google.com/security/products/secret-manager)



## How does it Work

1. The customer's security team administrator creates a set of credentials on either the database or a similarly secured resource server, and then provisions them as secrets in the Secrets Management provider. The latter encrypts and stores the credentials within the secrets.
2. The administrator has to grant Fabric (client application) with permissions to approach these secrets.
3. When Fabric opens a connection in order to access a resource server such as a database, via an interface, it examines whether its credentials are defined as reference IDs in the external Secrets Management provider. If they are defined this way, Fabric queries the Secrets Management provider for the relevant secrets. 
4. The Secrets Management provider retrieves the secrets, decrypts and returns them to Fabric over a secured (HTTPS with TLS) channel.
5. Fabric uses the secrets as the resource server credentials, as defined in the interface.
6. Fabric caches the credentials in memory. If a connection to a resource server fails due to credentials, Fabric assumes that the credentials were changed, and accesses the Secrets Management provider again to get them.



## Using Secrets Management Services

In order to use a Secrets Management provider:

1. Set the configuration in the config.ini file with the selected Secrets Management provider's access and permissions details.
2. Provision and mark the required interface connection details as those that shall be taken from the Secrets Management provider, as part of the project's implementation settings.

### Config.ini file

Each of the supported Secrets Management providers has its own dedicated section in the config.ini file, with all required access and permissions details.

In addition to populating these details, you shall turn it on by setting the 'ENABLED' property to 'true', in the section of the chosen Secrets Management provider.

Following are the config.ini attributes required for each Secrets Management provider:

#### AWS Secrets Manager

Section name: [encryption_aws_sm]
properties:

* ACCESS_KEY_ID
* SECRET_ACCESS_KEY
* REGION

Authentication and authorization can be done by the service account that the server is associated with. This is an alternative to usage of Access ID and Access Key.



#### HashiCorp Vault

Section name: [encryption_hashicorp_sm]

The authentication within HashiCorp Vault is done either by tokens that can be used directly or by using one of their other [auth methods](https://developer.hashicorp.com/vault/docs/concepts/auth), in which case the token is dynamically generated.

Fabric supports 2 authentication methods:

* Directly, where AUTH_TOKEN shall be set.

  When using this method, Fabric accesses the Vault URL with the token as the auth credentials, in order to get the secret.

* [AppRole](https://developer.hashicorp.com/vault/docs/auth/approle), which is based on the role that Fabric is associated to in the Vault.

  When using this method, Fabric first accesses the *approle* URL to dynamically get a token, and then uses this token as the auth credentials, in order to get the secret. For this method you shall specify the following attributes:

  * ROLE_ID, the role that Fabric is associated to in Vault.
  * SECRET_ID, the secret that is used to get the token.
  * APPROLE_URL, optional, when AppRole endpoint is not the default setting (default setting ends with "/approle").

  

Additionally, this shall be set:

* URL - the Vault API endpoint to get the secrets.



#### Azure Key Vault

Fabric supports one of the following authentication methods for Azure Key Vault:

 1. CLIENT_ID + CLIENT_SECRET + TENANT
 2. USE_MANAGED_IDENTITY_AUTH + CLIENT_ID + RESOURCE_ID 
 3. CLIENT_ID + USER_NAME + PASSWORD + TENANT_ID
 4. When Fabric is hosted on an Azure server or when Azure [CLI](https://learn.microsoft.com/en-us/cli/azure/) agent is installed and activated, Fabric is already considered as being authenticated, without providing further authentication credentials.

Section name: [encryption_azure_sm]

- KEY_VAULT_NAME - this property is required for any of the above 4 authentication method options
- CLIENT_ID
- CLIENT_SECRET
- TENANT_ID
- USER_NAME
- PASSWORD
- RESOURCE_ID
- USE_MANAGED_IDENTITY_AUTH
- ENDPOINT_TEMPLATE - this property is optional. The default value is https://{key_vault_name}.vault.azure.net, where Fabric uses it according to the key vault name.



#### CyberArk CCP

Section name: [encryption_cyberark_sm]

Authentication is done by sending an `Authorization: Bearer` header, either API key or user:passowrd, using HTTP basic authentication method:

* AUTH_TOKEN
* AUTH_PASSWORD
* AUTH_USER

Other parameters:

* APP_ID (can be set in the config.ini file as well as in the interface, for more granularity, when needed)
* FOLDER, optional (default is Root. Can be specified or overriden per secret)
* SAFE_NAME, optional (can be specified or overriden per secret)
* SERVER_IP, will be used in the URL parameter
* TIMEOUT (default is 5000 ms)
* URL, expected format: https://{SERVER_IP}/AIMWebService/api/Accounts



#### Google Cloud Secret Manager

Section name: [encryption_gcp_sm]

Authentication is done by a credentials file:

1. In the Google Cloud console
   * Select **IAM & admin** > **Service account**.
   * Find the service account you want to use.
   * Open your service account's Actions ⋮ menu, then select **Create key**.
   * In the resulting **Create private key** dialog, select the **JSON** option, create the key, and download it.
2. Locate the file in the Fabric server.
3. Set the CREDENTIAL_FILE parameter, providing the path into the file.  

Other parameters:

* PROJECT_ID

* LOCATION_ID, Optional - in case you use a regional secret manager.

  

### Interface Connection Details Settings


Marking an interface connection details property, to be taken from the Secrets Management provider, you shall use this pattern in its value:

${secretmanager:\<id-at-seceret-manager\>}
For example: ${secretmanager:mysql-password}



* Each Secret Manager service has its own pattern, usually by hierarchy (for example, with a dot sign inside the key name); you should follow these patterns. 
* For CyberArk CCP, you can specify the *folder* and/or *safe-name* parameters, by using the '&' concatenating pattern. For example: "${secretmanager:Safe=my-safe&Folder=my-folder&Object=mysql-password&&AppID=}"

  >  Mentioning that AppID can be added for more granularity, rather than general one, which can be set in the config.ini file.



> Notes: 
>
> * Each Secrets Management provider has its own pattern, usually by hierarchy; you should follow these patterns. 
> * The Secrets Management service can be used also for interface connection details inside Environments. Each one of the environments and the interfaces is independent, in a way that some environments may use Secrets Management services, while others, like local testing, may not. 
> * You can use the *Test connection* option to verify that the connection settings are OK, also when the Secrets Management service is activated.
> * The following properties can be addressed to the Secrets Management provider for the DB Interfaces types: host, user, password. For all other interfaces, all connection details properties can be set to use the Secrets Management provider.  




[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04_fabric_interfaces_security.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
