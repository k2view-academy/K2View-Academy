# **Fabric Interfaces Security** 


## Environments

Fabric enables you to define a number of source [environments]() and to switch between them during the same Fabric session. This way, you can change source connections without redeploying an LU.

For each environment, set the connection details of the interfaces and then deploy the environments to the Fabric cluster. Deployment of the environments is separate from the deployment of the LUs. The passwords of the interfaces are encrypted by the Fabric master key.

Fabric encrypts the interface details of each environment using the same master key generated when Fabric starts (or regenerates it). This key is also used to encrypt LU instances. 

To rekey the interfaces belonging to a given environment, do the following:
1. Open the Environments window of the Fabric Studio.
2. Set a Fabric node in the Fabric URL of each environment.
3. Click Re-Key.

When the connection details of the interfaces of the environment are updated and saved, the updated connection details are re-encrypted.
Similar to entities, Fabric keeps the key description of the master key used for the encryption of each environment.

Notes:

- When changing and saving the passwords of an interface in an environment, Fabric encrypts the modified passwords using server logic to automatically rekey the master key.
- A new Fabric URL field is added to define the Fabric node to be used to encrypt the passwords and run the test connection function. Note that when unchecked, the local test connection and previous encryption algorithm is used.
- The **Re-Key** column has been added in order to use the most updated encryption mechanism and the latest master key to encrypt the passwords of all interfaces.



<img src="/articles/26_fabric_security/images/06_fabric_envEncryption.PNG">




## Environments - Three Levels of Security 

Fabric enables users to define and apply strong, stronger and strongest levels of encryption on environment interfaces.


### Level 1 - Unassigned environment 
Fabric cannot be reached via the URL and the following configuration parameters apply:

- Master key is hardcoded in Fabric code
- The Encryption process is similar on all environments, for all projects and accounts deployed and setup.

This means that to get full access to all interfaces in any given project, the environment XML file is required and must be added to the **Projects\PROJECT_NAME\Implementation\SharedObjects\Environments** folder.


### Level 2 - Assigned environment without keystore

The following mechanism is used:

- The master key is generated and the encrypted master key is saved in Cassandra. 
- Fabric uses the AES256 algorithm with GCM (Galois/Counter Mode) to encrypt the master key.
- The interface password encryption is valid *only* on the defined Fabric cluster.
- The interface password can be re-encrypted using the Re-Key button or by saving the password changes.


### Level 3 - Assigned environment with keystore (protection key)
The protection key is used to encrypt the master key using a Java keystore. To decrypt the password to the environment using this option, the following access is needed:
- Environment's XML (like for the previous solution).
- Master key stored in Cassandra (environment dependent), access to the entire Cassandra cluster is required.
- Keystore.
- Keystore password (config.ini) to get the protection key storing the encrypted master key.


## Interfaces 

When defining a new interface, the password for this interface is hashed and saved as a hashed field in the [XML file](/articles/25_environments/04_offline_deployment.md#xml-file-example). 

``` 
<dbScheme>postgres</dbScheme>
<dbUser>postgres</dbUser>
<dbPasswordEncrypted>dqmdIUWuyC+4KaNDEKDlBimtd2utoESMq2Oj4NhUzCY=:X8P+ihKPTG2WuwfX0xztOPSS3lDLrr7Y+UrkzjkHf/c=</dbPasswordEncrypted>
```

<img src="/articles/26_fabric_security/images/05_fabric_Interfacesencryption.png">
          
## File Systems

Fabric enables connection to SFTP servers hosting files.
When an SFTP connection is needed to pull or push files, SSH keys exchange-based authentication can be used, in which case the password in the SFTP interface must be left blank.  
 

[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/03_fabric_LUI_encryption.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
