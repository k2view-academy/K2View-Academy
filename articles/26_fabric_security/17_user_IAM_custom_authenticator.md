# User IAM Custom Authenticator

Fabric enables you to use a custom authenticator for the users' IAM.

Authenticator is responsible for the following: 

- Verifying user's credentials, as populated in the login page.
- Supplying the user-name and his associated roles. 

In addition, when relevant and available, it provides a way to acquire and display user list information. 


### How to create a custom authenticator

A custom authenticator implements the  `com.k2view.fabric.authentication.providers.Authenticator` interface.

It implements 3 methods:

- `authenticate()` which is responsible for authenticating the input credentials' parameters. It returns an *AuthnResponse* object.
- `listUsers()` which whose returen is a list (iterator) of users (*UserItem* object), according to the input parameters' filters.
- `type()` where the authenticator declares a type of format "AuthenticationType.CUSTOM". This is used for both operational and auditorial purposes.  


### How to pack and deploy custom authenticator

Pack the authenticator into a JAR file and locate it under the $K2_HOME/ExternalJars directory.

The JAR should be copied to each of the cluster nodes.

For more information about working with external JAR files, see [here](/articles/31_external_resources/01_external_jars.md).

### How to activate custom authenticator

To activate the authenticator, configure it in the **config.ini** file. for more information see [here]().

After the authenticator has been configured properly, restart Fabric.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/16_user_IAM_auditing.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/18_FIPS_implementation.md)

