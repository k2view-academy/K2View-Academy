# Deploy from Fabric Studio

New or already existing Fabric project implementations must be deployed to the server side so that all recent code changes are compiled and are ready to be used.
A deployment can be performed either from the Fabric Studio or from the Fabric Server (also known as an [Offline Deploy](/articles/16_deploy_fabric/03_offline_deploy.md)).

Before starting the Deployment process on a project, check that the target Fabric Server is configured. To do so, go to the [User Preferences > Server Configuration](/articles/04_fabric_studio/04_user_preferences.md#what-is-the-purpose-of-the-server-configuration-tab) window and configure the required Fabric Server, or use the  **DEBUG SERVER** option that is already configured by default to localhost.

Note that to **force the sync** of the LU Instances after deployment, check [Force Upgrade Post Deploy]((/articles/14_sync_LU_instance/02_sync_modes.md#fabric-studio-server-configuration---force-upgrade-post-deploy-checkbox) ) in the **Server Configuration** window.

### How Do I Deploy a Fabric Object from the Fabric Studio?  

The following steps should be performed in the Fabric Studio for each deployed object, Logical Unit, Web Services, Broadway flow, Graphit <!--future link to Graphit DROP 2 -->and Reference tables.

1. To start the deployment of a Fabric object, right click the **object** to be deployed.
2. Select **Deploy to Server** to display the list of servers defined in the **User Preferences** window.
3. Do either:

- Click [**Server Name**].
- Click **Add/Edit** to open the **User Preferences** window if the required server configuration is missing. 



[![Previous](/articles/images/Previous.png)](/articles/16_deploy_fabric/01_deploy_Fabric_project.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/16_deploy_fabric/03_offline_deploy.md)
