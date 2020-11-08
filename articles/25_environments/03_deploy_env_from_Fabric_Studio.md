# Deploy Environment from Fabric Studio

To deploy an environment to server from the Fabric Studio, do the following:

1. Go to **Project Tree** > **Shared Objects** and double click on **Environments** to open the Environments window.

2. Click on the environment in the **Environments List** tab and then click **Deploy** to open the popup screen with two drop-down lists.

   ![image](images/25_03_1.png)

3. Click **Deploy environments to server** drop-down list to select the server.

4. After the deployment server is set, click to set an active environment from the second drop-down list or keep the value *Don't change active environment*.

   ![image](images/25_03_2.png)

5. Once the values are selected, click **Deploy**.

6. In case of successful deployment, the dialog box opens confirming the activity completion.

   ![image](images/25_03_3.PNG)

Notes:

* All the environments (not only the active one) are deployed to the server. You can see all the deployed environments using the LIST command.
* Setting an active environment during the deployment is optional. If you don't change the active environment at deployment, **_dev** remains an active one. The active environment can be checked, set and changed later using one of SET commands.
* [Click for more information about SET and LIST commands for environments](/articles/25_environments/05_set_and_list_commands.md).

[![Previous](/articles/images/Previous.png)](02_create_new_environment.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_manual_deployment_from_XML_file.md)