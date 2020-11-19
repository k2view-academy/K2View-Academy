# Web Admin Application

Admin is a web application that enables a user to conduct various Fabric Server and Cassandra activities, such as monitoring batch processes, managing user credentials or executing other Fabric commands. It is a graphical web-based tool that wraps up the Fabric commands and enables running and monitoring them in a more user-friendly manner. 

Admin includes 4 categories available via the framework whereby each category has additional sub-categories. The navigation through them is done using the framework menu where the **breadcrumbs** display the user's location in the Admin application. 

For example, the below image shows that the user navigated to **Admin > Processes > Cluster**.

![image](images/30_03_1.PNG)

### Admin Categories

* **Processes**, to initiate and monitor the Fabric [batch processes](/articles/20_jobs_and_batch_services/11_batch_process_overview.md) and [jobs](/articles/20_jobs_and_batch_services/01_fabric_jobs_overview.md) per the selected time period (for example, today, 3 days ago, etc):

  * Cluster, displaying the cluster and the nodes information such as node ID and status, IP, etc.
  * Tasks, Job and Batches, displaying the currently running processes. You can choose the time period to show the list of processes (active, today, 3 days ago, etc). You can also start a job or a batch by selecting the LU name and setting all relevant parameters.

* **Objects**, to display various Project level objects such as:
  
  * Interfaces, displayed per each deployed [environment](/articles/25_environments/01_environments_overview.md).
  * List of [CommonDB reference tables](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md). You can initiate the sync of the reference tables, selecting either the LU or a specific table.
  * List and details of various implementation objects: Project's LUs, Web Services, Broadway flows, etc.
  
* **Security**, to manage the user access control and restrictions via the [Fabric credentials mechanism](/articles/17_fabric_credentials/01_fabric_credentials_overview.md):
  * Roles, to view the existing and create new roles.
  * Permissions, to grant access to the role on resource or to revoke it.
  * Users, to view the existing, delete and create new users, to assign roles to user or to revoke it.
  * API Keys, to view the existing security tokens and to generate a new one, to assign roles to API keys, to remove it or delete it.
  
* **Commands**, to select and execute any existing Fabric command (one or more). 

  * Click on the command to get full command description and the usage.

    <img src="images/30_03_2.PNG" alt="image" style="zoom:67%;" />

  * Click ![image](images/30_03_icon.PNG) to get additional line when you need to run several commands.

    <img src="images/30_03_3.PNG" alt="image"  />

### Admin History

Admin displays the history of the Fabric commands executed on the server. It is displayed in a small popup screen in the right lower corner and can be expanded to show several rows.

For example, when navigating to Admin > Processes > Job and applying the filter to display today's job, the History popup will display the command: 

~~~
jobstatus 1 days ago
~~~

![image](images/30_03_history.PNG)

[![Previous](/articles/images/Previous.png)](/articles/30_web_framework/02_preintegrated_apps_overview.md)
