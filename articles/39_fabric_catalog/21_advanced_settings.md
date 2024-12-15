# Advanced Configuration

<web>

### Web Studio

* The ```Implementation/SharedObjects/Interfaces/Discovery/``` folder in the Project tree is a folder that holds all Catalog and Discovery process-related configuration files: 

  <img src="images/discovery_folder.png"  />

  * The **MTable** subfolder holds the MTables used by the Catalog's various processes. Additionally, when [Build Artifacts](09_build_artifacts.md) is performed, the Catalog artifact - **catalog_field_info.csv** - is created in this folder.
  * The **pluginsOverride.discovery** is a configuration file that defines the overrides in the crawler or plugins configuration (starting from V8.2). Before V8.2, the project-level overrides file was called **plugins.discovery**. 

* *Show Catalog Commands* is a Web Studio setting that either shows or hides the Catalog's related commands **Run Discovery Job** and **Open in Catalog** in the Web Studio. 

  * By default, *Show Catalog Commands* is enabled. 
  * This setting can be updated using the [Web Studio's user preferences](/articles/04_fabric_studio/04_user_preferences.md). 

### Setup of Centralized Catalog for Multiple Users

The following steps should be performed in order to configure a centralized Catalog for multiple users:

* Step 1: creation of a centralized Catalog's GraphDB's space. It should be done prior to creating a 'child' space.

  * Create the Central Neo4j space using a dedicated space profile.

* Step 2: creation of the 'child' spaces using a regular space profile.  

  * Prior to creating the 'child' spaces, update the Advanced Settings of your profile, by setting the following in the config.ini section:

  ~~~
  [data_discovery]
  GRAPH_DB_URL=neo4j://neo4j-service.<space_name-tenant_name>.svc.cluster.local:7687
  ~~~

  * The ```<space_name-tenant_name>``` should be replaced by the Central Neo4j space name.
  * Create the 'child' spaces. They will now point to the Central Neo4j rather than to their local ones.

* Step 3 (optional): if a read-only mode is required for some of the 'child' spaces, the following should be performed. It should be done prior to creating a 'child' space for a read-only mode.

  * Create a read-only neo4j user  - TBD.
  * Add the user's credentials via the Advanced Settings:

  ~~~
  [data_discovery]
  GRAPH_DB_URL=neo4j://neo4j-service.<space_name-tenant_name>.svc.cluster.local:7687

  GRAPH_DB_USER=<read-only user name>
  GRAPH_DB_PASSWORD=<read-only user password>
  ~~~

  * Create the 'child' spaces. They will connect to the Central Neo4j using a read-only user.

</web>

### Catalog Application Configuration

The **properties-info.json** is a configuration file used by the Catalog Application to determine the view and the behavior of various Catalog UI elements. The following settings can be performed:
* ```"editable": true``` - the property is editable via the [Edit Catalog capabililty](07_manual_overrides.md).
* ```"deletable": true``` - the property can be deleted via the [Edit Catalog capabililty.](07_manual_overrides.md)
* ```"searchable": true``` - the property is searchable using the [Advanced Search](08_search_catalog.md#advanced-search) screen.
* ```"filterable": true``` - the property is filterable using the [Catalog Filter](08a_filter_catalog.md) screen.
* ```"values":[]``` defines a list of a property's valid values. For some properties, this list is combined with programmatically retrieved values (e.g., classification).
* ```"allow_custom_values": true``` defines the ability to populate a custom value for a property that has a drop-down list. 
* ```"hidden": true``` - the property is hidden from the Catalog's Properties tab.

The properties-info.json file is located in ```fabric/staticWeb/catalog``` folder.

In order to perform the project-level overrides in the **properties-info.json** file:

* Create a **catalog** folder under the **Web** folder of the **Web Services** LU, and copy the file into it. 

  <web>

![](images/web_catalog.png)

</web>

* After updating the file, save it and deploy the LUs.



### General

NEO4J_SERVER_MEMORY_HEAP_MAX_SIZE_MB parameter in the [data_discovery] section of config.ini specifies the maximum heap size for the Neo4j server. By default, it is set to 2048 Mb. 

* The Neo4j heap size is set when starting Neo4j in a space. 
* This value can be adjusted, based on the data platform size and the number of schemas. For example, when an expected data platform size is large, it is recommended to increase this setting. 
* To update the heap size in an existing space, stop Neo4j and the DATA_DISCOVERY_JOB, update this setting in config.ini and run the Discovery Job.

ENABLE_DATA_DISCOVERY is a hidden configuration parameter that defines whether the Discovery should be enabled in the system (if neo4j is part of the Fabric space). By default, it is set to true. If the Fabric space does not include *neo4j*, ENABLE_DATA_DISCOVERY should be added to this section and set to false.



[![Previous](/articles/images/Previous.png)](20_catalog_APIs.md)

