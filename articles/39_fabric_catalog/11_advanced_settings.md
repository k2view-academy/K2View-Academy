# Catalog Advanced Settings

### General

* ENABLE_DATA_DISCOVERY is a configuration parameter in config.ini that defines whether the Discovery should be enabled in the system (if neo4j is part of the Fabric space). 
  * By default the parameter is set to true. 
  * Note that if the Fabric space doesn’t include *neo4j*, ENABLE_DATA_DISCOVERY should be set to false (by the system administrator).

### Web Studio

* 'Show Catalog Commands' is a Web Studio setting to either show or hide the Catalog related commands - Run discovery job and Open in catalog - in the Web Studio. It can be updated using the [Web Studio's user preferences](/articles/04_fabric_studio/04_user_preferences.md). 

### Catalog Application Configuration

* The ```properties-info.JSON``` is a configuration file used by the Catalog Application to determine the view and behavior of the node's properties in the Catalog application. 
  * This JSON file defines the order of the properties display on the Properties tab, which properties are editable (for the purpose of [Manual override](07_manual_overrides.md)), which properties are searchable (for the [advanced search](08_search_catalog.md#advanced-search)) and more.
  * The file is located in fabric/staticWeb folder and can be updated on the project level.



