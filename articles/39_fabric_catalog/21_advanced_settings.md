# Advanced Configuration

<web>

### Web Studio

The ```Implementation/SharedObjects/Interfaces/Discovery/``` folder in the Project tree is a folder that holds all Catalog and Discovery process-related configuration files: 

<img src="images/discovery_folder.png"  />

* The **MTable** subfolder holds the MTables used by the Catalog's various processes. Additionally, when [Build Artifacts](09_build_artifacts.md) is performed, the Catalog artifact - **catalog_field_info.csv** - is created in this folder.
  * Starting from V8.1, the Catalog artifact can be split. As a result, multiple files will be created instead of a single one. Click [here](09_build_artifacts.md#splitting-and-combining-artifacts) for more information about it.
* The **pluginsOverride.discovery** is a configuration file that defines the prospective overrides in the Crawler and the plugins configuration (starting from V8.2). Click [here](13_discovery_pipeline_settings.md#overview) for more information about it.
  * Note that prior to V8.2, the project-level overrides file was called **plugins.discovery**. Refer to the [Fabric Upgrade Procedure to V8.2](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/V8.2/Fabric_Upgrade_Procedure_To_V8.2.pdf.html) for more details.

*Show Catalog Commands* is a Web Studio setting that either shows or hides the Catalog's related commands **Run Discovery Job** and **Open in Catalog** in the Web Studio. 

* By default, *Show Catalog Commands* is enabled. 
* This setting can be updated using the [Web Studio's user preferences](/articles/04_fabric_studio/04_user_preferences.md). 

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

The ```NEO4J_SERVER_MEMORY_HEAP_MAX_SIZE_MB``` parameter in the ```[data_discovery]``` section of the config.ini file specifies the maximum heap size for the Neo4j server. By default, it is set to 2048 Mb. 

* The Neo4j heap size is set when starting Neo4j in a space. 
* This value can be adjusted, based on the data platform size and the number of schemas. For example, when an expected data platform size is large, it is recommended to increase this setting. 
* To update the heap size in an existing space, stop Neo4j and the DATA_DISCOVERY_JOB, update this setting in config.ini and run the Discovery Job.

```DATA_SNAP_WRITE_MEMORY_CAP_MB``` parameter in the ```[data_discovery]``` section of config.ini specifies the maximum amount of Fabric memory allocated for the Data Snapshot process. It helps to balance the Fabric memory when running the Discovery on a data platform with multiple schemas or when multiple Discovery jobs are running in parallel on the same Neo4j.

* When the in-memory data reaches this predefined limit, the Data Snapshot's data is committed to the SQLite file. 


* By default, the parameter is set to 4096 Mb. When working with very large data sources, it is recommended to increase this setting – assuming the system has sufficient resources for such increase.

```ENABLE_DATA_DISCOVERY``` is a hidden configuration parameter that defines whether the Discovery should be enabled in the system (if Neo4j is part of the Fabric space). By default, it is set to true. If the Fabric space does not include Neo4j, ```ENABLE_DATA_DISCOVERY``` should be added to this section and set to false.



[![Previous](/articles/images/Previous.png)](20_catalog_APIs.md)

