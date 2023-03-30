# **Fabric Environments & Interfaces Security** 

Fabric enables communication to external systems, such as source and target data, via [interfaces]("/articles/05_DB_interfaces/01_interfaces_overview.md") that define connection definitions.

The secrets, like passwords, that are a part of the interfaces definitions, are stored securely either in Fabric, where secrets are encrypted, or in an external Secret Manager service that Fabric integrates with.

<studio>

## Securing Environments

Fabric enables you to define several source [environments](/articles/25_environments/01_environments_overview.md), where for each environment you can set the connection details of the interfaces.

When using Fabric for storing environment secrets, there are 3 optional encryption levels that can be defined and applied - strong, stronger and strongest:

1. **Assigned Environment with a keystore** (Protection Key) - this is the most recommended and secured method, where:

   * encryption is done using Fabric's powerful [Key Management](/articles//26_fabric_security/02_fabric_entities_design.md#key-management) mechanism.
   * encryption is done using each environment's master key. This means that on runtime, an environment, upon all its interface secrets, can be used only on Fabric that this environment is associated to. For example, Fabric in a UAT environment will not be able to access Production interfaces.
   * the master key itself is protected by a keystore.

   >  To use this option, you need to populate the "Fabric URL" field, as describe [here](/articles/25_environments/02_create_new_environment.md).

2. **Assigned Environment without a keystore** - this option is similar to the previous #1 method, where the master key is set without a keystore protection.

3. **Unassigned Environment** - this option does encrypt the interfaces secrets by environment, and shall be used in case Fabric servers for the environments are not yet running and thus cannot use their master key. 

   The secrets encryption is done by using a code-based mechanism and thus can be used among all Fabric environments. 



> Notes:
>
> * When changing the passwords of an interface in an environment, then on save Fabric re-encrypts the passwords.
> * When master key was rotated at an environment, you can use the "Re-Key" button, appears aside the environment. When activated, the passwords of all interfaces are re-encrypted using the most the latest master key. This is relevant to methods #1 and #2, where environment's Fabric master key is being used for encryption.

   <img src="images/06_fabric_envEncryption.PNG">



## Securing Interfaces Settings

Base interfaces, those that are not a part of the environments, are used either for development environments or by customers that do not have multiple-source environments. The secrets, like passwords, for these base interfaces are encrypted and saved in an [XML file](/articles/25_environments/04_offline_deployment.md#xml-file-example) used to stage the project's deployment onto the server. 

</studio>

<web>

Fabric enables you to define several source [environments](/articles/25_environments/01_environments_overview.md), where for each environment you can set the interfaces' connection details. Securing an environment's interface secrets is done similar to base interfaces.

</web>



## Securing File Systems Settings

Fabric enables connection to SFTP servers hosting files.
When an SFTP connection is needed to pull or push files, SSH key-exchange-based authentication protocol can be used, in which case the password in the SFTP interface should be left blank.  

[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/03_fabric_LUI_encryption.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/04a_secret_manager.md)
