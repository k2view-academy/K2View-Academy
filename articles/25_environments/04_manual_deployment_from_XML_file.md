# Manual Deployment from XML File

To deploy an environment to server from the XML file, do the following:

1. Connect to a server by using the Fabric Console.

2. Use the command

   ~~~
   Deploy environments from file ‘{filename}’
   ~~~

Fabric encrypts the passwords in the file (if not already encrypted) and saves the XML with the encrypted passwords.

Note that deploying an XML overrides all existing environments except for the **_dev**, which is the default environment. If the environment exists in Fabric, but not in the deployed XML, it is removed from Fabric.

The required permission for this action: DEPLOY_ENVIRONMENTS.







[![Previous](/articles/images/Previous.png)](03_deploy_env_from_Fabric_Studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_set_and_set_global.md)







