# **Secrets Management Integration** 

Fabric supports integration with Secrets Management services, with the intent that secrets - like passwords, used in [interfaces]("/articles/05_DB_interfaces/01_interfaces_overview.md") that enable communication to external systems - will not be stored in Fabric itself. (For information on how can secrets be securely stored in Fabric - read [here](/articles/26_fabric_security/04_fabric_interfaces_security.md)).

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



## How does it work

1. The customer's security team administrator creates a set of credentials on either the database or a similarly secured resource server, and then provisions them as secrets in the Secrets Management provider. The latter encrypts and stores the credentials within the secrets.
2. The administrator configures these secrets' access permissions required for Fabric, as the client application.
3. When Fabric opens a connection in order to access a resource server such as a database, via an interface, it examines whether its credentials are defined as reference IDs in the external Secrets Management provider. If they are defined this way, Fabric queries the Secrets Management provider for the relevant secrets. 
4. The Secrets Management provider retrieves the secrets, decrypts and returns them to Fabric over a secured (HTTPS with TLS) channel.
5. Fabric uses the secrets as the resource server credentials, as defined in the interface.
6. Fabric caches the credentials in memory. Once a connection to a resource server failed due to credentials, Fabric assumes that credentials were changed and accesses again to the secret manager to get them.



## Using Secrets Management Services

In order to use a Secrets Management provider:

1. Set the configuration in the config.ini file with the selected Secrets Management provider's access and permissions details.
2. Provision and mark the required interface connection details as those that shall be taken from the Secrets Management provider, as part of the project's implementation settings.

### Config.ini file

Each of the supported Secrets Management providers has its own dedicated section in the config.ini file, with all required access and permissions details.

In addition to populating these details, you shall turn it on by setting the 'ENABLED' property to 'true', in the section of the chosen Secrets Management provider.

Following are the config attributes required for each Secrets Management provider:

#### AWS Secrets Manager

section name: [encryption_aws_sm]
properties:

* ACCESS_KEY_ID
* SECRET_ACCESS_KEY
* REGION



#### HashiCorp Vault

section name: [encryption_hashicorp_sm]

The authentication within HashiCorp Vault is done by tokens that can be used directly or by using one of their other [auth methods](https://developer.hashicorp.com/vault/docs/concepts/auth), in which case the token is dynamically generated.

Fabric supports 2 authentication methods:

* Directly, where AUTH_TOKEN shall be set.

  When using this method, Fabric accesses the Vault URL with the token as the auth credentials, in order to get the secret.

* [AppRole](https://developer.hashicorp.com/vault/docs/auth/approle), which is based on the role that Fabric is associated to at the Vault.

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

section name: [encryption_azure_sm]

- KEY_VAULT_NAME - this property is required for any of the above 4 authentication method options
- CLIENT_ID
- CLIENT_SECRET
- TENANT_ID
- USER_NAME
- PASSWORD
- RESOURCE_ID
- USE_MANAGED_IDENTITY_AUTH
- ENDPOINT_TEMPLATE - by default it is https://{key_vault_name}.vault.azure.net, so otherwise it is different, this property is optional and Fabric uses this default, according to the key vault name.



### Interface Connection Details Settings

Marking an interface connection details property, to be taken from the Secrets Management provider, you shall use this pattern in its value:

${secretmanager:\<id-at-seceret-manager\>}
For example: ${secretmanager:mysql.password}

> Notes: 
>
> * Each Secrets Management provider has its own pattern, usually by hierarchy; you should follow these patterns. 
> * The Secrets Management service can be used also for interface connection details inside Environments. Each one of the environments and the interfaces is independent, in a way that some environments may use  Secrets Management services, while others, like local testing, may not. 
> * You can use the *Test connection* option to verify that the connection settings are OK, also when the Secrets Management service is activated.
> * The following properties can be addressed to the Secrets Management provider for the DB Interfaces types: host, user, password. For all other interfaces, all connection details properties can be set to use the Secrets Management provider.  





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04_fabric_interfaces_security.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
