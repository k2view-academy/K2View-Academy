# Deploy from Fabric Studio

New or already existing Fabric project implementations must be deployed to the server side so that all recent code changes are compiled and are ready to be used.

<studio>

A deployment can be performed either from the Fabric Studio or from the Fabric Server (also known as an [Offline Deploy](/articles/16_deploy_fabric/03_offline_deploy.md)).

Before starting the Deployment process on a project, check that the target Fabric Server is configured. To do so, go to the [User Preferences > Server Configuration](/articles/04_fabric_studio/04_user_preferences.md#what-is-the-purpose-of-the-server-configuration-tab) window and configure the required Fabric Server, or use the  **"debug"** server entry, that is already configured to localhost, i.e. to the Fabric server that the Studio works with by default.

Note that to **force the sync** of the LU Instances after deployment, check [Force Upgrade Post Deploy](/articles/14_sync_LU_instance/02_sync_modes.md#fabric-studio-server-configuration---force-upgrade-post-deploy-checkbox) in the **Server Configuration** window.

</studio>

## Auto Deploy

The Fabric Studio **Auto Deploy** capability performs an automatic deployment of the code changes, to the local Fabric server, while debugging various components, such as the Data Viewer, LU Schema, Broadway, Broadway  Population, Parser and GraphIt. 

This reduces frictions while debugging and frees the user from doing the manual deployment upon each code change.

Fabric performs local deployment according to the following logic: 

- If the Schema was changed, full deploy is performed.
- If Java was changed, only Java resources and Broadway are deployed.
- If Broadway was changed, only Broadway is deployed.

<studio>

## How Do I Deploy a Fabric Object from the Fabric Studio?  

The following steps must be performed in the Fabric Studio for each deployed object, [Logical Unit](/articles/03_logical_units/01_LU_overview.md), [Web Service](/articles/15_web_services_and_graphit/01_web_services_overview.md), [Broadway flow](/articles/19_Broadway/01_broadway_overview.md), [Graphit](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md) or Reference table.

1. Right-click the **object** to be deployed and then select **Deploy to Server** to display the list of servers defined in the **User Preferences** window. Note that the **Force Upgrade Post Deploy** notification is displayed next to the server if the server is checked as Force Upgrade Post Deploy in the [Server Configuration](/articles/04_fabric_studio/04_user_preferences.md#what-is-the-purpose-of-the-server-configuration-tab) tab. 
2. Do either:

  - Click [**Server Name**].
  - Click **Add/Edit** to open the **User Preferences** window if the required server configuration is missing. 

## Deploying Fabric Objects to the Local Fabric Debug Server

To debug an LU it must first be deployed to the local Fabric debug server. A deployed LU has a green circle next to its name.

To deploy the Fabric implementation to the Fabric debug server, do either:

- Right-click the selected Fabric object > **Deploy To debug**.
- Click **Deploy** in the upper [Fabric Debug panel](/articles/04_fabric_studio/01_UI_components_and_menus.md#fabric-studio-debug-panel).

</studio>

<web>

## Deploying Fabric project  

You can activate a deployment and see the Deployment Status in several places within the Fabric Studio.

### Deployment Status

A deployed LU has a green circle next to its name in the project tree. Otherwise, for example while making changes at its content, the circle turns gray.

In addition, you can look at the "Deployment Status" in the bottom status bar to see whether all LUs are deployed. When hovering over it with the mouse, a tooltip appears, hinting you which LUs are not deployed.

In the illustration below you can see the project tree, where except "Web Services" all other LUs appear with a green circle, meaning that they are deployed. Similar indication exists in the bottom status bar. 

![](images/16_2_web_deploy_status_bar.png)

### Activate Deploy

Deployment can be done either for all updated LUs, those which are not already deployed, or for a specific LU.

To deploy all updated LUs, make either of the following:

* At top menu bar, click Fabric and then "Deploy All Updated Logical Units".
* At the bottom status bar, hover with the mouse over "Deployment Status", to see a tooltip implying which LUs are not deployed. Clicking on "Deployment Status" will trigger the deployments on those LUs.
* Open the command pallet (you can use the CTRL+SHIST+P shortcut), look for "Deploy All Updated Logical Units" and click on it.

> Note: The "Deploy All" action activates a Soft Deploy, unless this is the first time an LU is being deployed. This definition can be changed at the Preferences page, which is accessible via the bottom-left gear icon.



To deploy a specific LU, right-click on its name in the project tree. At the context menu you can choose either Soft Deploy or Deploy. For more information about deploy options read [here](/articles/16_deploy_fabric/01_deploy_Fabric_project.md).

</web>

[![Previous](/articles/images/Previous.png)](/articles/16_deploy_fabric/01_deploy_Fabric_project.md)<studio>[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/16_deploy_fabric/03_offline_deploy.md)</studio>



