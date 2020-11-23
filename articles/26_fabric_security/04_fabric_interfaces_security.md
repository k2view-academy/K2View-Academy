# **Fabric Interfaces Security** 

Fabric enables you to define a number of source [environments]() and to switch between these environments within the same Fabric session. This way, you can change your source connections without re-deploying your LU.

For each environment, set the connection details of your interfaces, and then deploy the environments to your Fabric cluster. The deployment of the environments is separate from the deployment of your LUs.
The passwords of the interfaces are encrypted by the fabric master key.


## Interfaces 

When defining a new interface, the password for this interface is hashed and saved as a hashed field in the [XML file](/articles/25_environments/04_offline_deployment.md#xml-file-example)  

``` 
<dbScheme>postgres</dbScheme>
<dbUser>postgres</dbUser>
<dbPasswordEncrypted>dqmdIUWuyC+4KaNDEKDlBimtd2utoESMq2Oj4NhUzCY=:X8P+ihKPTG2WuwfX0xztOPSS3lDLrr7Y+UrkzjkHf/c=</dbPasswordEncrypted>
```

<img src="/articles/26_fabric_security/images/05_fabric_Interfacesencryption.png">
          
  

## Environments


Fabric encrypts the interfaces’ details for each environment using the same master key generated on Fabric start (or regenerated) and that is also used to encrypt LU instances. 

In order to re-key all interfaces that belong to a given environment, open the Environments window of the Fabric Studio, set a Fabric node in the Fabric URL for each environment, 
and click on the ‘Re-Key’ option.
In addition, if you change the connection details of the interfaces for the environment and save the changes, the updated connection details will be re-encrypted.
As it is the case for entities, Fabric keeps the key description of the master key used for the encryption, for each environment.


<img src="/articles/26_fabric_security/images/06_fabric_envEncryption.PNG">


## File Systems

Fabric enables connection to SFTP servers hosting files.
In case there is a need to use the “SFTP” connection to pull/push files, it is highly recommended to use authentication between the servers based on SSH keys exchange. 
In this case, the password in the SFTP interface ust be left blank.  
 

[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/03_fabric_LUI_encryption.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)
