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

   * **KV (key-value) secrets engine** in HashiCorp Vault is designed as a hierarchical key-value store.
   
      - Each **path** is like a folder (for example:`k2view/mysql`).
   
      - Inside each path, you can store multiple key-value pairs (e.g., `user`, `password`, `host`, `port`. In the below illustrated example we show `password` and `user`).
   
        ![](images/04c_hashicorp_example.png)
   
   * When retrieving secrets via the API, Vault returns **all** keys under that path. However, Fabric allows you to specify which key you wish to use.
   
      The pattern is `key-path.key`. For example: <studio>${secretmanager:k2view/mysql.user} and ${secretmanager:k2view/mysql.password}</studio><web>k2view/mysql.user and k2view/mysql.password</web>
   
   * HashiCorp has two versions, and in each version, the key paths are different. However, this difference in paths does not affect how the keys work or how you configure them in the interface properties. For more information about the versions, read [here](https://developer.hashicorp.com/vault/docs/secrets/kv).
   
   
   
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

For example, suppose you provision both AWS and HashiCorp Secrets Management service provider sections in the config.ini file, and you have a secret property whose key is 'oracle-password'. In such scenario, Fabric will first call AWS to look for this key. If found, Fabric would use it and if the key is not found, Fabric will call the next provider in our example - HashiCorp. Calls are made in the order in which the providers' sections appear in the config.ini file.

When using several Secrets Management service providers, it is recommended to explicitly specify the provider's name for each interface property. This helps to prevent mistakes and to avoid unnecessary calls to irrelevant providers.

To do this, you should insert the provider's name, followed by a colon and then the name of the key. <studio>The pattern is: ${secretmanager:secretmanager_provider-name:my_secret} </studio><web>secretmanager_provider-name:my_secret</web>

For example, if you use HashiCorp Vault for storing SQL Server DB connection secrets, while Google Cloud Secret Manager is being used for storing BigQuery connection secrets, then the interface property values representing the keys may looks like the following:

<web>

SQL Server DB connection secrets: "hashicorp:AdventureWorks-User" and "hashicorp:AdventureWorks-Password", assuming that in HashiCorp there are corresponding "AdventureWorks-User" and "AdventureWorks-Password" keys.



![](images/web-specific-secret.png)


</web>  

<studio>

"${secretmanager:hashicorp:AdventureWorks-User}" and "${secretmanager:hashicorp:AdventureWorks-Password}", assuming that in HashiCorp there are corresponding "AdventureWorks-User" and "AdventureWorks-Password" keys. 

</studio>

### Multi Secrets Management Service Instances

When different Secrets Management service **instances** are provisioned in the configuration, you should explicitly specify which one of them is ought to be used for each interface secret property.

To do this, you should insert the instance's name, followed by a colon and then the name of the key. The pattern is: <Stduio>${secretmanager:my_new_secretmanager:my_secret} </studio><web>my_new_secretmanager:my_secret</web>

Given example: 

1. Two sections are provisioned in the config.ini file - `[encryption_prod_sm]` and  `[encryption_qa_sm]` - both with AWS as the Secrets Management service provider. 
2. As per this example, when different Secrets Management service providers are being used, the "prod" instance is aimed to be used for Production source system secrets, while the "qa" instance is aimed to be used for secrets related to QA target system. 
3. There are two environments, one for Production interfaces and one for QA interfaces.
4. One of the DBs is Oracle, both at source and target systems.

Then, in the Production environment, the **value** of the **password property** may look like "prod:oracle1-pswd", whereas in the QA environment, the corresponding property would be "qa:oracle1-pswd" (naturally, if the key in the QA Secrets Management service is different, then the same must also be reflected in the interface property).

At runtime, Fabric will act according to these definitions, in a way that for those properties with "prod" prefix, it will connect to the Secrets Management service that is defined in the `encryption_prod_sm` section of the config.ini file.





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04b_secret_manager_config.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
