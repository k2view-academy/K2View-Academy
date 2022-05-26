<studio>

# Deploy from Fabric Studio

New or already existing Fabric project implementations must be deployed to the server side so that all recent code changes are compiled and are ready to be used.
A deployment can be performed either from the Fabric Studio or from the Fabric Server (also known as an [Offline Deploy](/articles/16_deploy_fabric/03_offline_deploy.md)).

Before starting the Deployment process on a project, check that the target Fabric Server is configured. To do so, go to the [User Preferences > Server Configuration](/articles/04_fabric_studio/04_user_preferences.md#what-is-the-purpose-of-the-server-configuration-tab) window and configure the required Fabric Server, or use the  **DEBUG SERVER** option that is already configured by default to localhost.

Note that to **force the sync** of the LU Instances after deployment, check [Force Upgrade Post Deploy](/articles/14_sync_LU_instance/02_sync_modes.md#fabric-studio-server-configuration---force-upgrade-post-deploy-checkbox) in the **Server Configuration** window.

### Auto Deploy to Debug

The Fabric Studio **Auto Deploy** capability performs the automatic deployment of the code changes to debug while debugging various components, such as the Data Viewer, LU Schema, Broadway, Broadway  Population, Parser and GraphIt. 

This reduces frictions while debugging and releases the user from doing the manual deployment upon each code change.

Fabric performs local deployment according to the following logic: 

- If the Schema was changed, full deploy is performed.
- If Java was changed, only Java resources and Broadway are deployed.
- If Broadway was changed, only Broadway is deployed.

### How Do I Deploy a Fabric Object from the Fabric Studio?  

The following steps must be performed in the Fabric Studio for each deployed object, [Logical Unit](/articles/03_logical_units/01_LU_overview.md), [Web Service](/articles/15_web_services_and_graphit/01_web_services_overview.md), [Broadway flow](/articles/19_Broadway/01_broadway_overview.md), [Graphit](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md) or Reference table.

1. Right click the **object** to be deployed and then select **Deploy to Server** to display the list of servers defined in the **User Preferences** window. Note that the **Force Upgrade Post Deploy** notification is displayed next to the server if the server is checked as Force Upgrade Post Deploy in the [Server Configuration](/articles/04_fabric_studio/04_user_preferences.md#what-is-the-purpose-of-the-server-configuration-tab) tab. 
3. Do either:

  - Click [**Server Name**].
  - Click **Add/Edit** to open the **User Preferences** window if the required server configuration is missing. 

### Deploying Fabric Objects to the Fabric Debug Server

To debug an LU it must first be deployed to the local Fabric debug server. A deployed LU has a green circle next to its name.

To deploy the Fabric implementation to the Fabric debug server, do either:

- Right click the selected Fabric object > **Deploy To debug**.
- Click **Deploy** in the upper [Fabric Debug panel](/articles/04_fabric_studio/01_UI_components_and_menus.md#fabric-studio-debug-panel).

[![Previous](/articles/images/Previous.png)](/articles/16_deploy_fabric/01_deploy_Fabric_project.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/16_deploy_fabric/03_offline_deploy.md)

</studio>
