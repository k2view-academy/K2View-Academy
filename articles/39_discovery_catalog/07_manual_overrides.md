# Manual Overrides

### Overview

The Catalog supports the ability to perform manually overrides. 

To start the manual overrides, a user needs to click **Actions > Edit Catalog** from the menu bar. To end it, the user should either save the changes or discard them. Upon save, a new catalog version is created in *neo4j*. Once the changes are committed to *neo4j*, they are marked as manual overrides that enables keeping them part of the Catalog after running the Crawler again.

The graph's coloring scheme is changed in the Edit mode to the same theme as in the [version comparison](06_catalog_versioning.md) mode. Meaning that all entities and relations first become grey and then every change is indicated by green, red or blue color.

The overrides are only allowed to the latest catalog version.

### Edit Properties

The properties can be edited as follows:  

* To **edit** an existing property, open the More Info popup window of the selected property and update the **Value** and **Notes** fields. The list of editable properties is pre-defined on the Catalog application level. These are mostly the properties that were added by plugins. 

  * On clicking Submit – the change is aggregated in the client side and not sent to the server yet. The change is indicated by the colors.

  * On clicking Cancel – the change in More Info is discarded.

* To **add** a new property, click the <img src="images/add.png" alt="plus" style="zoom:75%;" /> icon and populate Name, Value and Notes via the More Info popup. ID will be generated according to the property's path, Origin will be set to Manual, and the Score to 1. 

* To **delete** a property, click the <img src="images/delete.png" alt="plus" style="zoom:75%;" /> icon. Only editable or manually added properties can be deleted.

### Edit Relations

The relations can be modified as follows:

* To **add** a new link, draw a line from one Dataset1 (the source) to Dataset2 (the target).
  * The name of the new link will be: *Dataset2 refers to Dataset1*. 
  * Dataset1 will be PK Table Name in Properties tab.
  * Dataset2 will be FK Table Name in Properties tab.
  * Populate PK and FK column names and notes and save the link.
* To **delete** a link, click the<img src="images/delete.png" alt="plus" style="zoom:75%;" />icon in the relation's Properties tab.

Only *refers_to* relations can be added and deleted. 

### Saving Manual Overrides

While the Catalog is in the Edit mode, the editing activities described in previous sections are  aggregated in the client side only. Once the user completes to perform the required changes, one of the following should be performed:

* Clicking the **Save** button in the [menu bar](05_catalog_app.md#menu-bar) will save all the changes together as a new version and exit the Edit mode.
* Alternatively, clicking the **Discard** button in the [menu bar](05_catalog_app.md#menu-bar) will exit the Edit mode without saving the pending changes.

Save and Discard buttons are only visible when the Catalog application is in the Edit mode.



[![Previous](/articles/images/Previous.png)](06_catalog_versioning.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_search_catalog.md) 

