# **Secrets Management Integration** 

Fabric supports integration with Secrets Management services, with the intention of not storing secrets in Fabric itself. An example for secrets is passwords that are used in [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md), [Environments](/articles/25_environments/01_environments_overview.md) and [Fabric System Database](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) as a way to enable communication with external systems. Click [here](/articles/26_fabric_security/04_fabric_interfaces_security.md) for further information about secured storage of secrets in Fabric.

**Secrets Management services** are tools that aim to securely store, manage, access and audit sensitive information such as passwords, API keys and other credentials, across the organization. The features included in Secrets Management services are encryption, access controls, auditing and automatic rotation of secrets.

The advantages of Secrets Management are: 

- Reducing the risk of secret leaks when providing a secret for each client application.
- Having a single source of truth, which can be better controlled, changed or rotated, manually or automatically.
- Managing access to secrets with fine-grained authorization policies.
- Detecting security breaches and attempted system access, done by analyzing audit logs and alerts that provide detailed history of client interactions, which can also be used for guiding security policy enforcement.

Fabric supports integration with various external Secrets Management providers, in which case Fabric does not store the secrets but rather their reference IDs. 

These are the currently supported Secrets Management providers, along with their official webpages: 

- AWS Secret Manager - [AWS Secret Manager](https://aws.amazon.com/secrets-manager/) [![link out](images/link-out-blue.png)](https://aws.amazon.com/secrets-manager/)
- HashiCorp Vault - [HashiCorp Vault](https://www.hashicorp.com/products/vault/secrets-management) [![link out](images/link-out-blue.png)](https://www.hashicorp.com/products/vault/secrets-management)
- Azure Key Vault - [Azure Key Vault](https://azure.microsoft.com/en-us/products/key-vault/) [![link out](images/link-out-blue.png)](https://azure.microsoft.com/en-us/products/key-vault/)
- CyberArk CCP - [CyberArk CCP](https://docs.cyberark.com/credential-providers/Latest/en/Content/CCP/The-Central%20-Credential-Provider.htm)  [![link out](images/link-out-blue.png)](https://docs.cyberark.com/credential-providers/Latest/en/Content/CCP/The-Central%20-Credential-Provider.htm)
- Google Cloud Secret Manager - [Google Cloud Secret Manager](https://cloud.google.com/security/products/secret-manager) [![link out](images/link-out-blue.png)](https://cloud.google.com/security/products/secret-manager)
- OneIdentity Safeguard - [OneIdentity Safeguard](https://www.oneidentity.com/products/one-identity-safeguard-for-privileged-passwords/) [![link out](images/link-out-blue.png)](https://www.oneidentity.com/products/one-identity-safeguard-for-privileged-passwords/)



## How does it Work

1. The customer's security team administrator creates a set of credentials on either a database or a similarly secured resource server, and then provisions them as secrets in the Secrets Management provider. The latter encrypts and stores the credentials within the secrets.
2. The administrator has to grant Fabric (client application) with permissions to approach these secrets.
3. When Fabric opens a connection in order to access the database/resource server via an interface, it examines whether its credentials are defined as reference IDs in the external Secrets Management provider. If they are defined as such, Fabric queries the Secrets Management provider for the relevant secrets. 
4. The Secrets Management provider decrypts and returns the secrets to Fabric over a secured channel.
5. Fabric uses the secrets as the resource server credentials, as defined in the interface.
6. Fabric caches the credentials in memory. If the connection to a resource server fails due to credentials, Fabric assumes that the credentials were changed, and it accesses the Secrets Management provider again for getting them.



## Using Secrets Management Services

In order to use a Secrets Management provider, you should:

1. **config.ini**, Set the configuration in the config.ini file with the selected Secrets Management provider, along with access and permission details. Read [here](/articles/26_fabric_security/04b_secret_manager_config.md) for more details.
2. **Interface Editor**, Provision and mark the required interface connection details as those that should be taken from the Secrets Management provider, as part of the project's implementation settings. Read [here](/articles/26_fabric_security/04c_secret_manager_interface.md) for more details.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04_fabric_interfaces_security.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/04b_secret_manager_config.md)
