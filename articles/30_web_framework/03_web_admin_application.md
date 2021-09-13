# Web Admin

Admin is a graphical web-based application used to conduct various Fabric Server and Cassandra activities, like monitoring batch processes, managing user credentials or executing other Fabric commands. Admin wraps [Fabric commands](/articles/02_fabric_architecture/04_fabric_commands.md) using REST APIs invoked by the Web Framework UI so that the commands can be run in a more user-friendly manner. 

Admin has four categories where each category has additional sub-categories. Navigation through the application is via the Web Framework's menu whereby breadcrumbs display a user's location. 

For example, the following image shows that the user navigated to **Admin > Security > Permissions**.

![image](images/30_03_1.PNG)

### Admin Categories

* **Processes**, initiate and monitor Fabric [batch processes](/articles/20_jobs_and_batch_services/11_batch_process_overview.md) and [jobs](/articles/20_jobs_and_batch_services/01_fabric_jobs_overview.md) according to a selected time period. This category includes the following sub-categories:

  * Cluster, displays information about the cluster and nodes such as the Node ID, Status, IP, etc.
  * Tasks, Job and Batches, displays running processes. Set the filter to show a list of processes that are currently active, or run today, 3 days ago, etc. To start a job or a batch, select the LU name and set the relevant parameters.

* **Objects**, display various Project level objects such as:
  
  * Interfaces, displayed with their connection status.
  * List of [CommonDB reference tables](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md). To initiate a sync of the reference tables, select either the LU or a specific table.
  * List of the implementation LU objects with the option to download their implementation's zip.
  * List of [Environments](/articles/25_environments/01_environments_overview.md) and their active status, as well as the option to switch to another environment.
  
* **Security**, manage a user's access control and restrictions via the [Fabric credentials mechanism](/articles/17_fabric_credentials/01_fabric_credentials_overview.md):
  * Roles, view existing roles and create new roles.
  * Permissions, grant access to a role on a resource or revoke it.
  * Users, view existing users, delete or create new users, assign roles to users or revoke them.
  * API Keys, view existing security tokens, generate new ones, assign roles to API keys, remove or delete them.
  
* **Commands**, write and execute one or more existing Fabric commands. 

  * Write the command in the command line. Click the command in the list to display its description and usage.

    <img src="images/30_03_2.PNG" alt="image" style="zoom:67%;" />

  * Click ![image](images/30_03_icon.PNG) to add a line to run several commands. 

    <img src="images/30_03_3.PNG" alt="image"  />
    
  * Click Execute to run the commands.

* **Terminal,** bringing fabric console experience to a web application. 

  

### Admin History

Admin displays the history of the Fabric commands executed on the server in a small popup screen in the right lower corner which can be expanded to show several rows.

For example, when navigating to **Admin** > **Processes** > **Job** and applying the filter to display today's job, the History popup displays the command: 

~~~
jobstatus 1 days ago
~~~

![image](images/30_03_history.PNG)

[![Previous](/articles/images/Previous.png)](/articles/30_web_framework/02_preintegrated_apps_overview.md)