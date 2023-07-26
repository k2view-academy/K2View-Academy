<web>

# Manual Overrides

### Overview

The Catalog supports the ability to perform manual overrides. 

To start the manual overrides, click **Actions > Edit Catalog** in the menu bar. To end it, either save the changes or discard them. Upon save, a new catalog version is created in *neo4j*. Once the changes are committed to *neo4j*, they are marked as manual which enables keeping them part of the Catalog after running the Crawler again.

The graph's coloring scheme changes in the Edit mode to the same theme as in the [version comparison](06_catalog_versioning.md) mode. Meaning that all entities and relations become grey and then every change is indicated by green, red or blue color.

Only the latest catalog version can be edited. See below which manual overrides are available in the Catalog.

### Edit Properties

As part of the Discovery process, properties are created for each node. Some properties are created by the Crawler (e.g. PK or source data type) and some are created by the plugins (e.g. PII or Classification). The Catalog allows to update only the properties which are pre-defined as editable in the properties configuration file. This definition can be updated on the project level. The default setting defines the following three editable properties: PII, Classification and Nullability.

The update is performed as follows:  

* To **edit** an existing property, open the More Info popup window of the selected property and update the **Value** and **Notes** fields. 

  * On clicking Submit – the change is aggregated in the client side and not sent to the server yet. The change is indicated by the colors.

  * On clicking Cancel – the change in More Info is discarded.
* To **delete** a property, click the <img src="images/delete.png" alt="plus" style="zoom:75%;" /> icon. Only editable or manually added properties can be deleted.
* To **add** a new property, click the <img src="images/add.png" alt="plus" style="zoom:75%;" /> icon and populate Name, Value and Notes via the More Info popup. ID will be generated according to the property's path, Origin will be set to Manual, and the Score to 1. 
  * When adding a new property, you can either select the property name from the pre-defined list or create a completely new property.


### Edit Relations

The relations can be modified as follows:

* To **add** a new relation, click on the source Dataset and click **Add Relation** in its context menu.  Then draw an arrow from the source (Dataset1) to the target (Dataset2).
  * The name of the new link will be: *Dataset2 refers to Dataset1*. 
  * Dataset1 will be the PK Table Name in Properties tab.
  * Dataset2 will be the FK Table Name in Properties tab.
  * Populate PK and FK column names and notes and save the link.
* To **delete** a link, click the<img src="images/delete.png" alt="plus" style="zoom:75%;" />icon in the relation's Properties tab.

Only *refers_to* relations can be added and deleted. 

### Saving Manual Overrides

While the Catalog is in the Edit mode, all updates are aggregated in the client side only. Once the manual changes are completed, the **Save** button should be clicked in the menu bar. It will trigger saving all of the changes together as a new version and exiting the Edit mode.

Alternatively, it is possible to exit the Edit mode without saving any changes by clicking the **Discard** button.

Save and Discard buttons are only visible when the Catalog application is in the Edit mode.

<img src="images/manual_override.png" style="zoom:75%;" />



[![Previous](/articles/images/Previous.png)](06_catalog_versioning.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_search_catalog.md) 

</web>
