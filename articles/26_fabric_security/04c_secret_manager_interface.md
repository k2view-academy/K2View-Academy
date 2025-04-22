# Integrating Secrets Management Services - Interface Editor 

Fabric supports integration with Secrets Management services as they provide several benefits while secrets are not stored in Fabric itself, only their reference IDs are. 

In order to use a Secrets Management service, in the Interface Editor (including Environments Editor) you should mark the required interface connection details as those that should be taken from the Secrets Management service.



## Interface Connection Settings

* Each Secrets Management service has its own key pattern, usually by hierarchy (e.g., with a dot sign inside the key name); you should follow that pattern.

* The Secrets Management service can also be used for interface connection details inside environments. 

  > Each one of the environments and the interfaces is independent, in a way that some environments may use Secrets Management services, whereas others such as local testing, might not. 

* You can use the *Test connection* option to validate the connection settings also when the Secrets Management service is activated.

* The following properties - host, port, database, username and password - can be set to the Secrets Management service for the DB type Interfaces. For all other interfaces, all connection detail properties can be set in order to use the Secrets Management service.  



**Setting and marking an interface property to use a Secrets Management service:**

<studio>

Use the following pattern - ${secretmanager:<id-at-secret-manager>} - in the interface property value. For example: `${secretmanager:mysql-password}`, where mysql-password is the key that exists in the Secrets Management service.

</studio>

<web>

1. Turn on the key switch, located beside each relevant property (![off](images/secret-key-off.png) &rarr; ![off](images/secret-key-on.png)).
1. Type in the key as it exists in the Secrets Management service.



> Notes: 
>
> * When turned on, a default value appears, as a proposed **key name** that is composed of the Interface name and the property name. For example, the proposed key name for *Host* property in the ASSETS_DB interface would be `ASSET_DB.Host`. This is only a suggested name, and you should strictly align with the same name that exists in the Secrets Management service.
> * When turned on, a property that is considered a password will not be masked, namely, it will remain visible in its original clear form.

</web>



### Provider-Specific Considerations and Usage Patterns

Following are additional notes and considerations regarding **specific** Secrets Management service providers:

* **HashiCorp Vault**: 

   * **KV (key value) secrets engine** in HashiCorp Vault is designed as a hierarchical key-value store.
   
      - Each **path** is like a folder (for example:`k2view/mysql`).
   
      - Inside each path, you can store multiple key-value pairs (e.g., `user`, `password`, `host`, `port`. In the below illustrated example we show `password` and `user`).
   
        ![](images/04c_hashicorp_example.png)
   
   * When retrieving secrets via the API, Vault returns **all** keys under that path. However, Fabric allows you to specify which key you wish to use.
   
      The pattern is `key-path.key`. For example: <studio>${secretmanager:k2view/mysql.user} and ${secretmanager:k2view/mysql.password}</studio><web>k2view/mysql.user and k2view/mysql.password</web>
   
   * HashiCorp has 2 versions, where their key-path are different but this does not affect the key and their path, as you set for the interface properties. To read more about versions see [here](https://developer.hashicorp.com/vault/docs/secrets/kv).
   
   
   
* **CyberArk CCP**:

   * You should specify the *folder* and/or the *safe-name* parameters by using the '&' concatenating pattern, e.g., `Safe=my-safe&Folder=my-folder&Object=mysql-password&AppID=`
>  


   > The AppID parameter is optional and can be added for more granularity, rather than a general AppID that can be set in the config.ini file.




* **One Identity Safeguard**:

   * You should specify both the *asset name* and the *account name* parameters by using the '&' concatenating pattern, e.g., `asset_name=OracleDB&account_name=PreProd`

  

## Multi Secrets Management Services and Instances Support

You can use several Secrets Management services on the same Fabric, per your needs, as demonstrated [here](/articles/26_fabric_security/04b_secret_manager_config.md#multi-secrets-management-providers-and-instances-support).

### Multi Secrets Management Services

In case you provision several Secrets Management service providers in the config.ini file (and they are set as 'ENABLED'), then Fabric will try to access each one of them to obtain the secrets, until succeeding. 

For example, suppose you provision both AWS and HashiCorp Secrets Management service provider sections in the config.ini, and you have a secret property whose key is 'oracle-password'. In such scenario, Fabric will first call AWS to look for this key. If found, Fabric would use it and if the key is not found, Fabric will call the next provider in our example - HashiCorp. Calls are made in the order in which the providers' sections appear in the config.ini file.

In such cases of using several secret management providers, it is recommended to specify the secret manager provider per interface property, to avoid mistakes and avoid redundant calls to irrelevant enabled providers.

For this you shall give its name following a semicolon and then the name of the key. <studio>The pattern is: ${secretmanager:secretmanager_provider-name:my_secret} </studio><web>secretmanager_provider-name:my_secret</web>

For example, if you use HashiCorp Vault for storing SQL Server DB connections secrets, while GCP Secret Manager for storing BigQuery connection secrets, then the Interface property values representing the keys might looks like the following:

<web>

SQL Server DB connections secrets: "hashicorp:AdventureWorks-User" and "hashicorp:AdventureWorks-Password", assuming that at HashiCorp there are corresponding "AdventureWorks-Password" and "AdventureWorks-User" keys.



![](images/web-specific-secret.png)

The REST API 

</web>  

<studio>

"${secretmanager:AdventureWorks-User}" and "${secretmanager:AdventureWorks-Password}", assuming that at HashiCorp there are corresponding "AdventureWorks-Password" and "AdventureWorks-User" keys. 

</studio>

### Multi Secrets Management Instances

When different secrets management service provider instances were provisioned at the configuration, you shall specify which one of them to use for  each Interface secret property.

For this you shall give its name following a semicolon and then the name of the key. The pattern is: <Stduio>${secretmanager:my_new_secretmanager:my_secret} </studio><web>my_new_secretmanager:my_secret</web>

For example: 

1. 2 sections are provisioned at the config.ini file: `[encryption_prod_sm]` and  `[encryption_qa_sm]` both with "aws" as secret manager provider type. 
2. The "prod" instance is aimed to be used for production source system secrets while "qa" is aimed for secrets related to QA target system. 
3. There are 2 environments one for Production Interfaces and one for QA interfaces.
4. One of the DBs is Oracle, both at source and target systems.

Then, at the Production environment, the value of the password property might looks like "prod:oracle1-pswd", while at the QA Environment the corresponding property will be "qa:oracle1-pswd" (of course if at the QA secret manager the key is different then accordingly it shall be at the interface key).

On runtime, Fabric will act according to theses definitions, so that for those properties with "prod" prefix, it will connect to the secrrt manager which defined at the `encryption_prod_sm` section in the config.ini file.





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04b_secret_manager_config.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
