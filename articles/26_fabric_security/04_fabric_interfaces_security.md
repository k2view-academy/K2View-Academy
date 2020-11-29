# **Fabric Interfaces Security** 




## Environments

Fabric enables you to define a number of source [environments]() and to switch between these environments within the same Fabric session. This way, you can change your source connections without re-deploying your LU.

For each environment, set the connection details of your interfaces, and then deploy the environments to your Fabric cluster. The deployment of the environments is separate from the deployment of your LUs.
The passwords of the interfaces are encrypted by the fabric master key.

Fabric encrypts the interfaces’ details for each environment using the same master key generated on Fabric start (or regenerated) and that is also used to encrypt LU instances. 

In order to re-key all interfaces that belong to a given environment, open the Environments window of the Fabric Studio, set a Fabric node in the Fabric URL for each environment, 
and click on the ‘Re-Key’ option.
In addition, if you change the connection details of the interfaces for the environment and save the changes, the updated connection details will be re-encrypted.
As it is the case for entities, Fabric keeps the key description of the master key used for the encryption, for each environment.

<img src="/articles/26_fabric_security/images/06_fabric_envEncryption.PNG">



## Environments - 3 Levels of Security 

Fabric enable users to define and use three different levels of environment interfaces encryption (from strong to strongest levels).


### Level 1 - Unassigned environment 
In this case Fabric cannot be reached via URL and the following configuration parameters apply:

- Master key is hardcoded in Fabric code
- The Encryption process is similar on all environments, for all projects and accounts deployed and setup.

This means that in order to get full access to all interfaces on any given project, the environment xml file is required and must be added to the **Projects\PROJECT_NAME\Implementation\SharedObjects\Environments** folder.


### Level 2 - Assigned environment without keystore

At this level the following mechanism is at play:

- The master key is generated and the encrypted master key is saved in Cassandra. 
- Fabric uses AES256 algorithm with GCM (Galois/Counter Mode) to encrypt the master key
- The interface password encryption is valid *only* on the defined Fabric cluster
- The interface password can be re-encrypted by using re-key button or by saving password changes


### Level 3 - Assigned environment with keystore (protection key)

In that scenario, the protection key is used to encrypt the master key (by using java keystore). In order to decrypt password to environment using this option, the following accesses are needed:
- Access to environments xml (like on previous solution)
- Access to master key stored in Cassandra (environment dependent) – need access to all Cassandra cluster
- Access to keystore
- Access to keystore password (config.ini) to get the protection key storing the encrypted master key.


- When changing and saving the environment interfaces passwords, Fabric will encrypt the modified passwords using the server logic to re-key automatically the master key.
- A new Fabric URL field is added in order to define the Fabric node to be used in order to encrypt the passwords and run the test connection function. Note that if left un-checked, the local test connection and previous encryption algorithm will be used.
- The *Re-Key* column was added in order to use the mostly updated encryption mechanism and the latest master key to encrypt all the interfaces passwords


## Interfaces 

When defining a new interface, the password for this interface is hashed and saved as a hashed field in the [XML file](/articles/25_environments/04_offline_deployment.md#xml-file-example)  

``` 
<dbScheme>postgres</dbScheme>
<dbUser>postgres</dbUser>
<dbPasswordEncrypted>dqmdIUWuyC+4KaNDEKDlBimtd2utoESMq2Oj4NhUzCY=:X8P+ihKPTG2WuwfX0xztOPSS3lDLrr7Y+UrkzjkHf/c=</dbPasswordEncrypted>
```

<img src="/articles/26_fabric_security/images/05_fabric_Interfacesencryption.png">
          


## File Systems

Fabric enables connection to SFTP servers hosting files.
When an SFTP connection is needed to pull or push files, SSH keys exchange based authentication can be used, in which case the password in the SFTP interface must be left blank.  
 

[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/03_fabric_LUI_encryption.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
