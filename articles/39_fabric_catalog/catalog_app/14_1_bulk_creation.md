# Bulk Creation of Catalog Entities 

### Overview

Manual update of multiple Catalog entities can be time-consuming and error-prone, especially when applying the same change — such as adding a new property — to many entities. Starting from Fabric V8.3, the Catalog includes **Bulk Creation and Edit** capabilities for improving efficiency and usability of manual procedures.

**Why Should I Create a Bulk of Entities?**

- For adding a new property to several entities simultaneously.
- For updating or removing an existing property from multiple entities.

This capability streamlines tasks that are often performed by database administrators or managers who need to make large-scale changes easily and consistently.

This article explains how to create and view a bulk of entities. Click [here](14_2_bulk_edit.md) to learn how properties can be bulk-edited.

### How Can I Add Entities to a Bulk Group?

1. Search for the nodes (e.g., Catalog fields) using the [Catalog search](08_search_catalog.md).
2. Select the required nodes and click on the <img src="../images/add_to_bulk_icon.png" style="zoom:100%;"> **Add to bulk** icon. 
3. Once the entity has been added to the bulk, the <img src="../images/bulk_icon.png" > icon appears next to it (in the Name column of the Search results screen), indicating that the entity is now part of the bulk.

![](../images/search_bulk.png)


### How Can I View Entities in a Bulk Group?

To view entities in a bulk group, click the <img src="../images/bulk_icon.png" > icon on the menu bar:

![](../images/mainMenuBulk.png)

* When bulk is empty, the icon is black
* When bulk is not empty, the icon is orange.

![](../images/viewBulk.png)

When the Catalog is not in Edit mode, the Bulk Edit screen only allows viewing the bulk and removing entities from it. 

The **Common properties list** displays a list of properties that are common for all bulk entities. When the property does not have the same value for all entities, only the property name is displayed.

The properties cannot be modified in this mode. Click [here](14_2_bulk_edit.md) to learn how to edit the properties in bulk.
