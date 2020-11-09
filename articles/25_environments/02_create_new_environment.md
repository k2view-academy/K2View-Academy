# Create New Environment

An environment is created in two steps:

* Creating the environment and deploying it in the **Environments List** grid.
* Setting the interfaces relevant for the selected environment in the **Selected Environment** grid.

**Step 1 - Creating the Environment**

1. Go to **Project Tree** > **Shared Objects** and double click **Environments** to open the Environments window.

   ![image](images/25_02_1.PNG)

2. In the **Environments List** grid, do the following:

   -  Enter a meaningful name for the environment in the **Name** field.

    - (Optional) In the **Comments** field add a description of the environment.

    - (Optional) In the **Fabric URL** field, select the server from the predefined list in the **Server Configuration** tab in the [**User Preferences**](/articles/04_fabric_studio/04_user_preferences.md) window. 

       The selected Fabric node is used to encrypt the passwords of all interfaces related to the given environment and to run the test connection. If the node is not selected, Fabric uses the local test connection and previous encryption algorithm - ???? 
 [Click for more information about Fabric security hardening].

   -  Click **Re-Key** to use the most updated encryption mechanism and the latest master key to encrypt the passwords of the interfaces.
 
   -  Click **Deploy** to deploy an environment to the Fabric server. An environment is not deployed together with an LUT, Reference tables and Web Services. Note that environments can be deployed to Fabric either by the Fabric Studio or by using a command line to deploy the environment from an xml file.

Click for more information about [Environment Deployment from the Fabric Studio](03_deploy_env_from_Fabric_Studio.md) and about [Manual Deployment from XML File](04_manual_deployment_from_XML_file.md).

**Step 2 - Defining Interface Settings per Environment**

Once **Name** in the  **Environments List** grid is clicked, all DB and non-DB interfaces in the project are copied to the **Selected Environment** grid and displayed in the tabs. Each interface type is displayed in a dedicated tab. For example, if the project has DB, SMTP, Kafka and HTTP interfaces, each type is displayed in a separate tab.

![image](images/25_02_2.PNG)

By default, all interfaces are enabled whereby the settings of each relevant interface can be disabled if it they not applicable for the environment.

1. Select the interface and enable or disable it to add it to the environment or to remove it from the environment by either:
   * Clicking the **Enabled** column.
   * Right clicking the row and clicking **Set Selected Rows as Enabled** or **Set Selected Rows as Disabled**. Hold the **Ctrl** key to select multiple rows. 
2. Populate the relevant fields of each active interface in the  **Selected Environment** grid: server. user, password, etc. If the project has several interface types, go through the tabs and populate all mandatory fields.
3. Click **Save**.

Note that the environment cannot be saved when ![iamge](images/25_red_sign.PNG) is displayed to indicate that a mandatory field is not populated.



[![Previous](/articles/images/Previous.png)](01_environments_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_deploy_env_from_Fabric_Studio.md)
