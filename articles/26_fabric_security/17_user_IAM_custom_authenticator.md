# User IAM Custom Authenticator

Fabric enables using custom authenticator to be used for users IAM.

Authenticator is basically responsible to

- Verify user's credentials, as populated at the login page
- Supply the user-name and his associated roles. 

In addition, when relevant and available, it shall provides a way to acquire for users and users list information.



### How to create custom authenticator

Custom authenticator shall implements `com.k2view.fabric.authentication.providers.Authenticator` interface.

it shall implement 3 methods:

- `authenticate()` which shall responsible to the authentication with the input credentials parameters. Its return value as *AuthnResponse* object.
- `listUsers()` which its return value is a list (iterator) of users (*UserItem* object), according to input parameters filters.
- `type()` where it can declarers of its type - shall be "AuthenticationType.CUSTOM". This is used for both operational and auditing purposes.  



### How to pack and deploy custom authenticator



### How to activate custom authenticator

In order the authenticator will be active it shall be configured at the config.ini. for more information see [here]().

After it configured properly Fabric shall be restarted.



[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)