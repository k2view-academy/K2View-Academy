# **Secret Managers Integration** 

Fabric supports integration with Secret Manager services, so that secrets - like passwords, used in [interfaces]("/articles/05_DB_interfaces/01_interfaces_overview.md") that enable communication to external systems - will not be stored in Fabric itself. (For information of how can secrets be securely stored on Fabric - read [here](/articles/26_fabric_security/04_fabric_interfaces_security.md))

Secret Manager services are tools aimed for securely storing, managing, accessing and auditing sensitive information such as passwords, API keys, and other credentials, across the organization. Features of Secret Manager services include encryption, access controls, auditing and automatic rotation of secrets.

Such a service has several advantages: 

- Reducing the risk of secrets leaks when providing the secret for each client application.
- Having a single source of truth, which can be better controlled and changed or rotated, manually or automatically.
- Managing access to secrets with fine-grained policies.
- Detecting security breaches and attempted access to systems, via audit logs and alerts, providing detailed history of client interactions, which can be also used for guiding policy enforcement.

Fabric supports integration with various external secret manager service providers, whereby in this case Fabric doesn't stores secrets but instead it stores only the secrets reference IDs. 

Supported secret manager providers are: 

- [AWS Secret Manager](https://aws.amazon.com/secrets-manager/)
- [HashiCorp Vault](https://www.hashicorp.com/products/vault/secrets-management)
- [Azure Key Vault](https://azure.microsoft.com/en-us/products/key-vault/)



## How does it work

1. customer's security team administrator creates a set of credentials on the database or similar secured resource server, and then provisions them as secrets in Secrets Manager. The Secret Manager encrypts and stores the credentials within the secrets.
2. The administrator configures the access permissions of those secrets required for Fabric, as the client application, to retrieve them.
3. When Fabric opens a connection in order to access a resource server like database, via an interface, it examines if its credentials defined as reference IDs into external secret manager. If they defined like that, Fabric queries the secrets manager for the relevant secrets. 
4. Secrets Manager retrieves the secrets, decrypts and returns them to Fabric over a secured (HTTPS with TLS) channel.
5. Fabric uses the secrets as the resource server credentials, as defined in the interface.
6. Fabric caches the credentials in memory. Once a connection to a resource server failed due to credentials, Fabric assumes that credentials were changed and accesses again to the secret manager to get them.



## Using Secret Manager Service

To enable a secret manager's usage the following  shall be done:

1. Set the configuration at config.ini file with the selected secret manager access and permissions details
2. Provision and sign the required interface connection details as those that shall be taken from the secret manager, as part of the project's implementation settings.

### Config.ini file

Each of the supported secret managers has its own dedicated section at config.ini file, with all required access and permissions details.

In addition of populating these details you shall turn it on by set `ENABLED` property to be `true`.

##### AWS Secret Manager

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

Fabric supports several authentication methods for Azure Key Vault:

 1. CLIENT_ID + CLIENT_SECRET + TENANT
 2. USE_MANAGED_IDENTITY_AUTH + CLIENT_ID + RESOURCE_ID (Can be used when app is hosted on Azure server)
 3. CLIENT_ID + USER_NAME + PASSWORD + TENANT_ID
 4. when app is hosted on Azure server (optional: CLIENT_ID, TENANT_ID, RESOURCE_ID)

For any of the options the Azure key vault name - `KEY_VAULT_NAME` is required. All other properties are required, according to the above options.

section name: [encryption_azure_sm]

- KEY_VAULT_NAME
- CLIENT_ID
- CLIENT_SECRET
- TENANT_ID
- USER_NAME
- PASSWORD
- RESOURCE_ID
- USE_MANAGED_IDENTITY_AUTH
- ENDPOINT_TEMPLATE - by default it is https://{key_vault_name}.vault.azure.net, so otherwise it is different, this property is optional and Fabric uses this default, according to thr key vault name.



### Interface Connection Details Settings

 To sign an interface connection details property to be taken from the secret manager, you shall use this pattern in its value:

${secretmanager:\<id-at-seceret-manager\>}
For example: ${secretmanager:mysql.password}

> Notes: 
>
> * Each secret manager service has its own pattern, usually by hierarchy, so follow those patterns. 
> * Using secret manager is applied also for interface connection details as appear at Environments. Each environment and each interface is independent so that in one of the environments, like local testing, a connection details property will not use secret manager service, while for other environments it will. 
> * You can use the "Test connection" option to verify the settings are OK, also  when using a secret manager service.
> * These are the properties that can be addressed to the secret manager for the DB Interfaces types: Host, user, password. For all other interfaces, all connection details properties can be set to use secret manager.  





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04_fabric_interfaces_security.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
