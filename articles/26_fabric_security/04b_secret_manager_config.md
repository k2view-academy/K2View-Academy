# Integrating Secrets Management Services - Configuration

Fabric supports integration with Secrets Management services as they provide several benefits while secrets are not stored in Fabric itself. 

In order to integrate any one of Fabric’s currently supported Secrets Management services, you should configure the config.ini file with the properties of the selected Secrets Management service, along with the access and permission details.

Ready to be selected, each supported Secrets Management service has its own dedicated section in the config.ini file, containing all required access and permission details.

In addition to populating these details, you **must** also activate that selected Secrets Management service by setting the 'ENABLED' property to 'true' in the relevant service section in the config.ini file. 



## Configuration Settings

The following are the required config.ini file properties for each Secrets Management service provider:

### AWS Secrets Manager

**Section name**: [encryption_aws_sm]

**Properties**:

* ENABLED=true
* REGION

**Authentication** process can be done by setting these properties:

* ACCESS_KEY_ID
* SECRET_ACCESS_KEY

 The authentication can also be done by the service account, which the server is associated with. This is an alternative to using an Access ID and an Access Key.



### HashiCorp Vault

**Section name**: [encryption_hashicorp_sm]

**Properties**:

* ENABLED=true
* URL - the Vault API endpoint for getting the secrets.

**Optional Properties**:

* NAMESPACE - a secure multi-tenancy capability within Vault as a means to provide isolation among teams in the organization. Read [here](https://developer.hashicorp.com/vault/tutorials/enterprise/namespaces) for more information about namespaces.

**Authentication** is done by either tokens that can be used directly or using one of HashiCorp's other [auth methods](https://developer.hashicorp.com/vault/docs/concepts/auth), in which case the token is dynamically generated.

Fabric supports 2 authentication methods:

* Directly - where AUTH_TOKEN property should be set.

  When using this method, Fabric accesses the Vault URL with the token as auth credentials to get the secret.

* [AppRole](https://developer.hashicorp.com/vault/docs/auth/approle) - which is based on the role that Fabric is associated to in the Vault.

  When using the AppRole method, Fabric first accesses the Approle URL to dynamically get a token, and then uses that token as auth credentials for the purpose of getting the secret. For this method, you should specify the following properties:

  * ROLE_ID - the role that Fabric is associated to in the Vault.
  * SECRET_ID - the secret that is used for getting the token.
  * APPROLE_URL

  

### Azure Key Vault

**Section name**: [encryption_azure_sm]

**Properties**:

- ENABLED=true
- KEY_VAULT_NAME

**Optional Properties**:

* ENDPOINT_TEMPLATE - its default value is https://{key_vault_name}.vault.azure.net, where Fabric uses it according to the key vault name.

**Authentication**, Fabric supports one of the following authentication methods for Azure Key Vault and accordingly you shall set their properties:

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

* APP_ID - can be set in the config.ini file as well as in the interface, for more granularity, when needed.
* FOLDER - default is Root; this parameter can be specified or overridden per each secret.
* SAFE_NAME - this parameter can be specified or overridden per each secret.
* TIMEOUT - default is 5000 ms.

**Authentication** is done by using either an API key or user and password, and accordingly the following parameters have to be set:

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

**Authentication** is done by a credentials file:

1. In the Google Cloud console
   * Select **IAM & admin** > **Service account**.
   * Find the service account you want to use.
   * Open your service account's Actions ⋮ menu, then select **Create key**.
   * In the resulting **Create private key** dialog, select the **JSON** option, create the key and download it.
2. Locate the file in the Fabric server.
3. Set the CREDENTIAL_FILE property, providing the path to the file.  



### OneIdentity Safeguard

**Section name**: [encryption_safeguard_sm]

**Properties**:

* ENABLED=true
* HOST - this is the Safeguard host, used for all API calls. 

**Optional Properties**:

TIMEOUT - default is 10000 ms.

**Authentication** is done by certifications and keys that should be applied.



## Multi Secrets Management Services and Instances Support

You can use several Secrets Management services on the same Fabric by setting and activating them in the config.ini file.

### Multi Secrets Management Service Systems

There may be various systems that provide Secrets Management services for your organization, where data resource credentials are set across different providers. In such cases, Fabric is required to access each one of them to obtain the secrets.  

To use it: 

1. Set the properties of the required Secrets Management services in their relevant sections in the config.in file.
2. Set the 'ENABLED' property to 'true' to activate the Secrets Management service.

Note that in the Interface Editor you can specify, per secret, which secret manager provider to use. If you do not specify, then Fabric will try find the secrets in each of the activated providers (according to their appearance in the config.ini file). 

### Multi Secrets Management Instances

Different secrets management service instances might be used in your organization. For example, a TDM production DB resource secrets are managed at the production's secrets manager service, while the DB target resource secrets are managed by another secrets manager service instance, even though they are on same provider.

To use it:

1. Name the secret manager section you want to use, following this pattern: `[encryption_{my_name}_sm]`. For example,  name the section for production secret manager instance as`[encryption_prod_sm]` and  `[encryption_qa_sm]` for the QA secret manager instance.
2. Add `TYPE`property to that section with the name of the service provider. You can find the type by looking for the default section name, as list above. For example, the section name for AWS Secret Manager is `[encryption_aws_sm]` and accordingly its type is `aws`. (Note: for the default sections it is not required, that is - no need to specify its type).



You can add as many sections as needed, also several instances among several providers. Later on, in the Interface Editor you shall refer and specify, per secret, which secret manager provider's  instance to use.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04a_secret_manager.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/04c_secret_manager_interface.md)
