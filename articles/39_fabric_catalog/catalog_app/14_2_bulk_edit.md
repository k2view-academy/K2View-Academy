# Bulk Edit of Catalog Entities

### Overview

Manual update of multiple Catalog entities is often slow and prone to errors, especially when the same change must be applied to many of them. Starting from Fabric V8.3, the Catalog includes **Bulk Creation and Edit** capabilities for improving efficiency and usability of manual procedures.

**Why Should I Bulk-Edit Entities?**

- For simultaneous editing, adding or deleting of multiple entities.

This capability streamlines tasks often performed by database administrators or managers that need to make large-scale changes with consistency and ease.

This article explains how to edit properties using the Bulk Edit capability. Click [here](14_1_bulk_creation.md) to learn how to create and view a bulk of entities.

### How Can I Edit Entities in a Bulk Group?

The **Common properties list** displays the properties that are shared across all bulk-selected entities. Bulk-editing of properties is performed using this pane.

1. To initiate the bulk-edit activity, it is required to switch the Catalog to an edit mode by clicking **Actions > Edit Catalog** in the menu bar.  
2. Next, click the <img src="../images/bulk_full.png" > icon located in the menu bar to view the **bulk** of entities.
   * An orange icon indicates that the bulk includes one or more entities.
3. To **add** a new property:
   * Click the <img src="../images/add.png" alt="plus" style="zoom:85%;" /> icon and populate the **Name**, **Value** and **Notes** fields in the **Edit property** area. 
   * Then click the <img src="../images/V_bulk.png" alt="plus" style="zoom:95%;" /> icon to add the new property to the **Common properties list**.
4. To **delete** a property, click the <img src="../images/delete.png" alt="plus" style="zoom:95%;" /> icon.
5. To **edit** an existing property:
   * Click the property in the Common properties list and update its **Value** and **Notes** fields. 
   * Then click the <img src="../images/V_bulk.png" alt="plus" style="zoom:95%;" /> icon to add it to the **Common properties list**.
6. Click **Submit** when bulk edit is completed.

Note that clicking **Submit & Clear bulk** submits the bulk changes and clears the bulk in one action. This is useful when an additional bulk of entities needs to be created and bulk-edited. 

All updates are aggregated on the client side only. The **Save** button should be clicked in the menu bar to trigger saving of all the changes together and creation of a new version. The Catalog will then exit the edit mode.

![](../images/editBulk.gif)
