# Bulk Edit

### Overview

Manually updating multiple Catalog entities can be time-consuming and error-prone, especially when you need to apply the same changes - such as adding a new property - to many entities. To improve efficiency and usability, in V8.3 the Catalog provides a **Bulk Creation and Edit** capability. 

#### When to Use Bulk Creation and Editing

- For adding a new property to several entities at once.
- For updating or deleting an existing property for multiple entities.

This capability streamlines tasks often performed by database administrators or managers that need to make large-scale changes with consistency and ease.

This article explains how to  edit the properties in bulk the bulk. Click [here](14_1_bulk_creation.md) to learn how to create and view bulk.

### How Can I Edit a Bulk?

1. To start the manual overrides, click **Actions > Edit Catalog** in the menu bar.  
2. Then click the <img src="../images/bulk_icon.png" style="zoom:55%;"> icon on the menu bar to view the bulk.
3. To **add** a new property:
   * Click the <img src="../images/add.png" alt="plus" style="zoom:75%;" /> icon and populate the **Name**, **Value** and **Notes** fields in the **Edit property** area. 
   * Then click the <img src="../images/V_bulk.png" alt="plus" style="zoom:75%;" /> icon to add the new property to the **Common properties list**.
4. To **delete** a property, click the <img src="../images/delete.png" alt="plus" style="zoom:75%;" /> icon.
5. To **edit** an existing property:
   * Click the property in the Common properties list and update its **Value** and **Notes** fields. 
   * Then click the <img src="../images/V_bulk.png" alt="plus" style="zoom:75%;" /> icon to add it to the **Common properties list**.
6. Click **Submit** when bulk edit is completed.

Note that all updates are aggregated on the client side only. The **Save** button should be clicked in the menu bar to trigger saving of all the changes together and creation of a new version. The Catalog will then exit the edit mode.