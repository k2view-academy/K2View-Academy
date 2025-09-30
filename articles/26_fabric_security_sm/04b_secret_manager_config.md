# Integrating Secrets Management Services - Configuration

Fabric supports integration with Secrets Management services, as they provide several benefits. While secrets are not stored in Fabric itself, only their reference IDs are. 


## Table of Contents

1. [Configuration Settings](#configuration-settings)  
   1.1 [AWS Secrets Manager](#aws-secrets-manager)  
   1.2 [HashiCorp Vault](#hashicorp-vault)  
   1.3 [Azure Key Vault](#azure-key-vault)  
   1.4 [CyberArk CCP](#cyberark-ccp)  
   1.5 [Google Cloud Secret Manager](#google-cloud-secret-manager)  
   1.6 [One Identity Safeguard](#one-identity-safeguard)  

2. [Multi Secrets Management Services and Instances Support](#multi-secrets-management-services-and-instances-support)  
   2.1 [Multi Secrets Management Service Systems](#multi-secrets-management-service-systems)  
   2.2 [Multi Secrets Management Service Instances](#multi-secrets-management-service-instances)  


## Configuration Settings

In order to integrate any one of the Secrets Management service providers currently supported by Fabric, you should configure the config.ini file with the properties of the selected Secrets Management service, along with the access and permission details.

Ready to be selected, each supported Secrets Management service has its own dedicated section in the config.ini file, containing all required access and permission details.

In addition to populating these details, you **must** also activate that selected Secrets Management service by setting the 'ENABLED' property to 'true' in the relevant service section in the config.ini file. 

The following are the required config.ini file properties for each Secrets Management service provider:

### AWS Secrets Manager

**Section name**: [encryption_aws_sm]

**Properties**:

* ENABLED=true
* REGION

**Authentication** can be done by setting these properties:

* ACCESS_KEY_ID
* SECRET_ACCESS_KEY

Authentication can also be performed by the service account associated with the server. This is an alternative to using an Access ID and an Access Key.



### HashiCorp Vault

**Section name**: [encryption_hashicorp_sm]

**Properties**:

* ENABLED=true
* URL - the Vault API endpoint for getting the secrets.
  * For key vault type 1, format is  `https://<vault_URL>/v1/<engine_name>`, where default engine name is "secret" (accordingly format will looks like ` https://<vault_URL>/v1/secret`
  * For key vault type 2, format is  `https://<vault_URL>/v1/<engine_name>/data` 


**Optional Properties**:

* NAMESPACE - a secure multi-tenancy capability within Vault as a means to provide isolation among teams in the organization. Read [here](https://developer.hashicorp.com/vault/tutorials/enterprise/namespaces) for more information about namespaces.

**Authentication** is done by either tokens that can be used directly or using one of HashiCorp's other [auth methods](https://developer.hashicorp.com/vault/docs/concepts/auth), in which case the token is dynamically generated.

Fabric supports 2 authentication methods:

* Directly - where the AUTH_TOKEN property should be set.

  When using this method, Fabric accesses the Vault URL with the token as auth credentials to get the secret.

* [AppRole](https://developer.hashicorp.com/vault/docs/auth/approle) - which is based on the role that Fabric is associated to in the Vault.

  When using the AppRole method, Fabric first accesses the AppRole URL to obtain a token dynamically and then uses that token as authentication credentials to retrieve the secret. For this method, you should specify the following properties:

  * ROLE_ID - the role that Fabric is associated with in the Vault.
  * SECRET_ID - the secret that is used for getting the token.
  * APPROLE_URL

  

### Azure Key Vault

**Section name**: [encryption_azure_sm]

**Properties**:

- ENABLED=true
- KEY_VAULT_NAME

**Optional Properties**:

* ENDPOINT_TEMPLATE - its default value is https://{key_vault_name}.vault.azure.net, where Fabric uses it according to the key vault name.

**Authentication -** 

Fabric supports one of the following authentication methods for Azure Key Vault, and you should accordingly set their properties:

  1. CLIENT_ID + CLIENT_SECRET + TENANT
  2. USE_MANAGED_IDENTITY_AUTH + CLIENT_ID + RESOURCE_ID 
  3. CLIENT_ID + USER_NAME + PASSWORD + TENANT_ID
  4. When Fabric is hosted on an Azure server or when the Azure [CLI](https://learn.microsoft.com/en-us/cli/azure/) agent is installed and activated, Fabric is considered to be authenticated, without providing further authentication credentials.



### CyberArk CCP

**Section name**: [encryption_cyberark_sm]

**Properties**:

* ENABLED=true
* SERVER_IP - to be used in the URL parameter.
* URL - expected format is https://{SERVER_IP}/AIMWebService/api/Accounts.

**Optional Properties**:

* APP_ID - Can be set in the config.ini file as well as in the interface, for more granularity, when needed.
* FOLDER - Default is Root; this parameter can be specified or overridden per secret.
* SAFE_NAME - This parameter can be specified or overridden for each secret.
* TIMEOUT - The default is 5,000 ms.

**Authentication** is done by using either an API key or a username and password, and accordingly, the following parameters have to be set:

* AUTH_TOKEN
* AUTH_PASSWORD
* AUTH_USER



### Google Cloud Secret Manager

**Section name**: [encryption_gcp_sm]

**Properties**:

* ENABLED=true
* PROJECT_ID

**Optional Properties**:

* LOCATION_ID - in case you use a regional secret manager.

**Authentication** is performed with a credentials file:

1. In the Google Cloud console
   * Select **IAM & admin** > **Service account**.
   * Find the service account you would like to use.
   * Open your service account's Actions ⋮ menu, then select **Create key**.
   * In the resulting **Create private key** dialog, select the **JSON** option, create the key and download it.
2. Locate the file in the Fabric server.
3. Set the CREDENTIAL_FILE property, providing the path to the file.  



### One Identity Safeguard

**Section name**: [encryption_safeguard_sm]

**Properties**:

* ENABLED=true
* HOST - This is the Safeguard host, used for all API calls. 

**Optional Properties**:

TIMEOUT - default is 10000 ms.

**Authentication** is performed through certifications and keys that must be applied.



## Multi Secrets Management Services and Instances Support

You can use several Secrets Management services on the same Fabric by setting and activating them in the config.ini file.

### Multi Secrets Management Service Systems

There may be various systems that provide Secrets Management services for your organization, where data resource credentials are set across different providers. In such a case, Fabric is required to access each one of them to obtain the secrets.  

To use it: 

1. Set the properties of the required Secrets Management services in their relevant sections in the config.in file.
2. Set the 'ENABLED' property to 'true' to activate each Secrets Management service.

Note that in the Interface Editor you can specify, per secret, which Secrets Management service to use. If you do not specify it, Fabric will attempt to find the secrets in each activated service (according to their appearance in the config.ini file). 

### Multi Secrets Management Service Instances

Different Secrets Management service instances may be used in your organization. For example, in TDM production, DB source secrets are managed by a production's Secrets Management service instance, while the DB target secrets are managed by another Secrets Management service instance, although both instances are of the same provider.

To use it:

1. In the config.ini file, name the Secrets Management service section you would like to use, following this pattern: `[encryption_{my_name}_sm]`. For example, name the section for production's Secrets Management service instance as `[encryption_prod_sm]` and the section for the QA's instance as `[encryption_qa_sm]`.

2. Add `TYPE` property to that section, including the name of the service provider. You can identify the type by searching for the default **section name** listed above. For example, the section name for AWS Secrets Manager is `[encryption_aws_sm]`, and accordingly, its type is `aws`.

   > This type-specifying step is not required for sections that preserve their default names stated in the above configuration settings.

3. When importing certificates to Fabric's trust store, the alias must contain the instance server hostname and optionally the port.

   Format: `hostname:port` or `hostname` (port is optional)

   Example: `keytool -import -alias db.example.com:8443_production-db -file server.crt -keystore truststore.jks`



You can add as many sections as needed and also several instances across several providers. Later, in the Interface Editor, refer to and specify each secret, advising which Secrets Management service instance to use.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security_sm/04a_secret_manager.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security_sm/04c_secret_manager_interface.md)

