# Advanced Configuration

<web>

### Web Studio

* The ```Implementation/SharedObjects/Interfaces/Discovery/``` folder in the Project tree is a folder that holds all Catalog and Discovery process-related configuration files: 

  <img src="images/discovery_folder.png"  />

  * The **MTable** subfolder holds the MTables used by the Catalog's various processes. In addition, when [Build Artifacts](09_build_artifacts.md) is performed, the Catalog artifact - **catalog_field_info.csv** - is created in this folder.
  * The **pluginsOverride.discovery** is a configuration file that defines the overrides in the crawler or plugins configuration (starting V8.2). Before V8.2 the project-level overrides file was called **plugins.discovery**. 

* *Show Catalog Commands* is a Web Studio setting that either shows or hides the Catalog's related commands **Run Discovery Job** and **Open in Catalog** in the Web Studio. 

  * By default, *Show Catalog Commands* is enabled. 
  * This setting can be updated using the [Web Studio's user preferences](/articles/04_fabric_studio/04_user_preferences.md). 

</web>

### Catalog Application Configuration

The **properties-info.json** is a configuration file used by the Catalog Application to determine the view and behavior of various Catalog UI elements. The following settings can be performed:
* ```"editable": true``` - the property is editable via the [Edit Catalog capabililty](07_manual_overrides.md).
* ```"deletable": true``` - the property can be deleted via the [Edit Catalog capabililty.](07_manual_overrides.md)
* ```"searchable": true``` - the property is searchable using the [Advanced Search](08_search_catalog.md#advanced-search) screen.
* ```"filterable": true``` - the property is filterable using the [Catalog Filter](08a_filter_catalog.md) screen.
* ```"values":[]``` defines a list of property's valid values. For some properties, this list is combined with programmatically retrieved values (e.g. classification).
* ```"allow_custom_values": true``` defines the ability to populate a custom value for a property that has a drop-down list. 
* ```"hidden": true``` - the property is hidden from the Catalog's Properties tab.

The properties-info.json file is located in ```fabric/staticWeb/catalog``` folder.

In order to perform the project-level overrides in the **properties-info.json** file:

* Create a **catalog** folder under the **Web** folder of the **Web Services** LU and copy the file into it. 

  <web>

![](images/web_catalog.png)

</web>

* After updating the file, save it and deploy the LUs.



### General

NEO4J_SERVER_MEMORY_HEAP_MAX_SIZE_MB parameter in the [data_discovery] section of config.ini specifies the maximum heap size for the Neo4j server. By default, it is set to 2048 Mb. 

* The Neo4j heap size is set when starting Neo4j in a space. 
* This value can be adjusted, based on the data platform size and the number of schemas. For example, when an expected data platform size is large, it is recommended to increase this setting. 
* To update the heap size in an existing space, stop Neo4j and the DATA_DISCOVERY_JOB, update this setting in config.ini and run the Discovery Job.

ENABLE_DATA_DISCOVERY is a hidden configuration parameter that defines whether the Discovery should be enabled in the system (if neo4j is part of the Fabric space). By default it is set to true. If the Fabric space doesn’t include *neo4j*, ENABLE_DATA_DISCOVERY should be added to this section and set to false.



[![Previous](/articles/images/Previous.png)](20_catalog_APIs.md)

