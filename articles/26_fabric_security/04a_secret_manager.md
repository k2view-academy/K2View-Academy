# **Secret Management Integration** 

Fabric supports integration with Secret Manager services, so that secrets - like passwords, used in [interfaces]("/articles/05_DB_interfaces/01_interfaces_overview.md") that enable communication to external systems - will not be stored in Fabric itself. (For information of how can secrets be securely stored on Fabric - read [here](/articles/26_fabric_security/04_fabric_interfaces_security.md))

Secret Manager services are tools aimed for securely storing, managing, accessing and auditing sensitive information such as passwords, API keys, and other credentials, across the organization. Features of Secret Manager services include encryption, access controls, auditing and automatic rotation of secrets.

Such a service has several advantages: 

- Reducing the risk of secret leaks when providing the secret for each client application.
- Having a single source of truth, which can be better controlled, changed or rotated, manually or automatically.
- Managing access to secrets with fine-grained authorization policies.
- Detecting security breaches and attempted access to systems, done by analyzing audit logs and alerts that provide detailed history of client interactions, which can also be used for guiding security policy enforcement.

Fabric supports integration with various external Secret Management providers, in which case Fabric doesn't store the secrets but rather their reference IDs. 

Supported Secret Management providers are: 

- [AWS Secret Manager](https://aws.amazon.com/secrets-manager/)
- [HashiCorp Vault](https://www.hashicorp.com/products/vault/secrets-management)
- [Azure Key Vault](https://azure.microsoft.com/en-us/products/key-vault/)



## How does it work

1. The customer's security team administrator creates a set of credentials on either the database or a similar secured resource server, and then provisions them as secrets in the Secrets Manager. The Secret Manager encrypts and stores the credentials within the secrets.
2. The administrator configures these secrets' access permissions required for Fabric, as the client application.
3. When Fabric opens a connection in order to access a resource server like database, via an interface, it examines whether its credentials are defined as reference IDs in the external Secret Manager. If they are defined like that, Fabric queries the Secrets Manager for the relevant secrets. 
4. The Secrets Manager retrieves the secrets, decrypts and returns them to Fabric over a secured (HTTPS with TLS) channel.
5. Fabric uses the secrets as the resource server credentials, as defined in the interface.
6. Fabric caches the credentials in memory. Once a connection to a resource server failed due to credentials, Fabric assumes that credentials were changed and accesses again to the secret manager to get them.



## Using a Secret Manager Service

In order to use a Secrets Manager:

1. Set the configuration at config.ini file with the selected Secrets Manager access and permissions details.
2. Provision and mark the required interface connection details as those that shall be taken from the Secrets Manager, as part of the project's implementation settings.

### Config.ini file

Each of the supported Secrets Managers has its own dedicated section at config.ini file, with all required access and permissions details.

In addition of populating these details, you shall turn it on by setting the 'ENABLED' property to be 'true'.

##### AWS Secrets Manager

section name: [encryption_aws_sm]
properties:

* ACCESS_KEY_ID
* SECRET_ACCESS_KEY
* REGION

##### HashiCorp Vault

section name: [encryption_hashicorp_sm]

* AUTH_TOKEN
* URL

##### Azure Key Vault

Fabric supports one of the following authentication methods for Azure Key Vault:

 1. CLIENT_ID + CLIENT_SECRET + TENANT
 2. USE_MANAGED_IDENTITY_AUTH + CLIENT_ID + RESOURCE_ID 
 3. CLIENT_ID + USER_NAME + PASSWORD + TENANT_ID
 4. When Fabric is hosted on an Azure server or when Azure [CLI](https://learn.microsoft.com/en-us/cli/azure/) agent is installed and activated, Fabric is already considered as authenticated, without providing further authentication credentials.

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

To mark an interface connection details property to be taken from the Secrets Manager, you shall use this pattern in its value:

${secretmanager:\<id-at-seceret-manager\>}
For example: ${secretmanager:mysql.password}

> Notes: 
>
> * Each Secret Manager service has its own pattern, usually by hierarchy; you should follow these patterns. 
> * The Secret Manager service can be used also for interface connection details inside Environments. Each one of the environments and the interfaces is independent, in a way that some environments may use the Secret Manager service, while others, like local testing, may not. 
> * You can use the "Test connection" option to verify that the connection settings are OK, also when the Secret Manager service is activated.
> * The following properties can be addressed to the Secrets Manager for the DB Interfaces types: host, user, password. For all other interfaces, all connection details properties can be set to use the Secrets Manager.  





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04_fabric_interfaces_security.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
