### Soft Deploy

![](images/soft_deploy_1.PNG)

Soft Deploy is Fabric's ability to exclude automatic processes from the LU deployment. 

Soft Deploy is mostly useful for implementers working in a development environment and frequently changing their code, such as Broadway flows or Java functions. They can use the Soft Deploy option for  deploying their changes without triggering automatic processes such as:

- User jobs
- Parsers
- Interface listener

In order to activate Soft Deploy when executing a deployment from the Fabric Studio, mark the Soft Deploy checkbox in the [User Preferences > Server Configuration](https://support.k2view.com/Academy_6.5/articles/04_fabric_studio/04_user_preferences.html#what-is-the-purpose-of-the-server-configuration-tab) window.

In order to activate the Soft Deploy during an [Offline Deploy](https://support.k2view.com/Academy_6.5/articles/16_deploy_fabric/03_offline_deploy.html), set the **SOFT_DEPLOY** optional parameter to TRUE.
