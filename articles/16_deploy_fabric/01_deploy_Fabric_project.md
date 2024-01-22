# Deploying a Fabric Project

### What is Fabric Deployment?
The implementation of a Fabric project can be divided into 2 on-going stages:
- **Stage 1 - design, configuration and coding** - which includes the creation of a Fabric project and the definition of the relevant objects in the Fabric Studio, in order to implement the business requirements.
- **Stage 2 - deployment** of the project into the Fabric server, where all recent changes, done after its last deployment, are applied and are functioning on the server side. 



A deployment includes the newly created or modified Fabric objects such as [LU Schema](/articles/03_logical_units/03_LU_schema_window.md), [LU Tables](/articles/06_LU_tables/01_LU_tables_overview.md), [Interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md), [Globals](/articles/08_globals/01_globals_overview.md), [Project functions](/articles/07_table_population/08_project_functions.md), [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md), [Broadway flows](/articles/19_Broadway/02a_broadway_flow_overview.md)  and [Graphit](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md).


The deployment of a Fabric project is performed on the following levels:

- **Deployment of Logical Units** - each LU is deployed separately. 
  
  - When required, a deployment can be performed on a specific LU, only if the LU has been modified.
  
- **Deployment of Broadway flows** as part of the LU to which they belong.

- **Deployment of Reference objects**, when they exist in a project. All Reference (common) tables are deployed together.

- **Deployment of Web Services**, when they exist in the project. <studio> The following deployment options are available: 
  
  - Deployment of all Web Services. 
  
  - Deployment of selected Web Services within a category.  
  - Deployment of selected Web Services.
  
  </studio>
  
- **Deployment of Graphit**, when it exists in the project. <studio> The following deployment options are available: 
  
  - All Graphit objects can be deployed as part of the Web Services. 
  - Each Graphit object can be deployed separately. </studio>
  
- **Deployment of Environments** 



Deployment can be performed using several methods:	

- In the [Fabric Studio](02_deploy_from_Fabric_Studio.md#deploy-from-fabric-studio). 
- <studio>In the Fabric server itself, using Build and Deploy scripts.</studio><web>In k2cloud UI.</web>
- Remotely, using API calls. This is useful for CI/CD pipelines, where once the project package is verified, it can be sent into the target server.

The latter 2 options are also called [Offline Deploy](03_offline_deploy.md).



In the Fabric Project, deployment involves generating 2 files (a .jar and a .zip) for each Fabric Object. These files store all the components under the Logical Unit (LU) in the project's directory structure. Each Fabric Object has its own subdirectory under **/storage/lu** for storing its deployment artifacts, and this directory is created during the first deployment within the project. Later Fabric Object deployments add their respective directories (in an epoch format) under **/storage/lu**.



Note that Shared Objects are not independent objects in a project and therefore cannot be deployed as stand-alone items. You must re-deploy the LUs that use these objects in order to bring the changes into effect. For example, when an interface is updated, all LUs that use this interface should be re-deployed.

### Soft Deploy

**Soft Deploy** is a Fabric's capability to exclude automatic processes from the LU deployment. Accordingly, when the Soft Deploy is being used, the [deploy.flow](/articles/19_Broadway/09a_automatic_flows_execution_upon_deploy.md) is not running. 

<studio>

Moreover, when using the Soft Deploy option, these processes are not triggered:

* User jobs
* Parsers
* Interface listener

To activate the Soft Deploy when deploying from the Fabric Studio, turn on the Soft Deploy option in the [User Preferences > Server Configuration](/articles/04_fabric_studio/04_user_preferences.md#what-is-the-purpose-of-the-server-configuration-tab) window.

</studio>

<web>

To activate the Soft Deploy, right-click on a Logical Unit name, and select Soft Deploy. 

</web>

To activate the Soft Deploy during the [Offline Deploy](/03_offline_deploy.md), set the **SOFT_DEPLOY** optional parameter to TRUE.



### How Do I Check Which Project is Deployed to Fabric? 

Only **one project** can be deployed to [each Fabric cluster](/articles/02_fabric_architecture/01_fabric_architecture_overview.md#fabric-cluster). If a project has already been deployed and an attempt to deploy another project to the same cluster is being made, an error message displays.

<studio>
	
To perform a project’s deployment check in the Fabric server, use the **SET** command from the Fabric console.

</studio>

<web>
	
To perform a project’s deployment check in the Fabric server, use the **SET** command from the Fabric terminal
(choose **Fabric -> Open Fabric Terminal**).

</web>


###### Syntax:

~~~
set;
~~~


This command displays the currently deployed project's name. 

[Click for more information about Fabric Basic Commands](/articles/02_fabric_architecture/04_fabric_commands.md#fabric-commands). 

### How Are Deployed Objects Reflected in the Fabric Server?
You can check which objects are deployed in the Fabric server by using the Fabric **LIST** command.
- **list LU_TYPES/LUT** - provides a list of LU Types deployed to the Fabric server and can display an LU's [storage type](/articles/32_LU_storage/01_LU_storage_overview.md). This command can be invoked for either all deployed LU Types or a specific one.
- **list WS** - provides a list of all Web Service methods deployed to the Fabric server. 
- **list ENVIRONMENTS/ENVS** - provides a list of environments deployed to the Fabric server.
- **list BF/BROADWAY_FLOWS** - lists all Broadway flows of all LU Types with their inputs and outputs deployed to the server:
  - If LU_NAME is provided, the command lists only Broadway flows that belong to the LU.
  - If FLOW is specified, the result shows a row for each input and output arguments of the flow, along with their type and schema. If more than one flow are found with the same name, the result will be a list of LUs. Specify the LU_NAME to ensure that a single flow is found.

- **list INSTANCE_GROUPS/IGS** - lists all instance groups of all LU Types deployed to the server.
- **list DB_SOURCES** - lists all the DB interfaces.
- **list INTERFACES** - lists all the interfaces. 

###### Syntax:
~~~
    list lut; 
    list lu_types;
    list lut storage=y;
    list ws;
    list ENVIRONMENTS; 
    list ENVS;
    list BF;
    list BROADWAY_FLOWS;
    list IGS;
    list INSTANCE_GROUPS;
    list DB_SOURCES;
    list INTERFACES;
~~~

**Examples:**

~~~
fabric>list lut storage=y;
|LU_NAME        |STORAGE|
+---------------+-------+
|SummaryExercise|Default|
|Customer       |Default|
|ORDERS         |Default|
|CRM            |Default|
~~~

### How Are Deployed Objects Reflected in Cassandra?

A project deployment is reflected in [**Cassandra**](/articles/02_fabric_architecture/01_fabric_architecture_overview.md#cassandra-) as follows:
- The deployment of a [Logical Unit](/articles/03_logical_units/01_LU_overview.md), [References](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md) and [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) creates new records in the [**k2_lut_info** table](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) under the **k2system** keyspace. A separate record is created for each LU and Reference table and for each deployed Web Service.
- Each deployed LU creates a new Cassandra **keyspace** named **k2view_[LU Name]_[cluster id if exists]**.
- The first deployed WS creates a new Cassandra **keyspace** named **k2view_k2_ws**.


## Project Versioning

As the project implementation is an on-going process, where many changes are done over time, there is a need to clarify which code or configuration has been deployed to the server.

Fabric offers project version tagging that shows the deployed version tag on the deployed server.

The version tag creation is done along with a GIT tagging capability, allowing for a project version to be preserved both as a property in the project definitions file (k2proj.xml) and as a GIT annotated tag.

While Fabric provides the mechanism and technology for project versioning, the project owner and developers shall follow the related project lifecycle procedures, when tagging a version and while deploying the project.  

For more information about Project Versioning, read [here.](/articles/16_deploy_fabric/04_project_versioning.md)

### Downloading the Deployed Code

In addition to project versioning, which allows you to compare between the deployed version and the matched tag in GIT, Fabric supports the creation of a zip file for any selected LU name, enabling the implementor to download the code that is deployed in the environment and to check it.


###### Syntax:

```
http://[host]:3213/lut?lutName=[luname]&token=[token]
```

For example:

```
http://[host]:3213/lut?lutName=k2_ws&token=[token]
```



The outcome of this command is that **ludbXMLs.zip** is downloaded to your local machine and can be opened in the Studio.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md)

