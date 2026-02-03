# Revert Catalog Version

### Overview

K2view's Catalog supports [versioning](06_catalog_versioning.md) by creating a new Catalog version in the Neo4j Graph DB whenever the Discovery process detects differences from the previous version, either due to source changes or configuration updates.

If, after user verification, these changes are deemed irrelevant or incorrect, users may choose to revert to a previous Catalog version.

The Catalog allows reverting from the latest version to any earlier version. The revert can be applied to a selected data platform or the entire Catalog, generating a new version. 

Reverting is available starting in Fabric V8.4.

### What is Revert?

Reverting a Catalog version involves creating a new version using a reverted list of changes. For example:

* If a node (e.g. table, field, property) was deleted, this node is added back.
* If a property value was updated, the property is assigned the previous (old) value.
* If the change was due to a manual override, this manual override becomes obsolete (expired). 

Prior to performing the revert, the process gathers all changes between the latest and the selected versions.  When there are no changes to revert between these versions, the revert is not performed and a new version is not created. 

The revert can remove an entire data platform. For example, if CRM_DB was created in the Catalog V5 and the user chooses to revert the entire Catalog to V4, the CRM_DB will be removed from the Catalog tree. 

Reverting a revert version cancels the last changes. For example, when running a revert from V4 to V3, a Revert version V5 is created. Now, running revert from V5 to V4 will created a Revert version V6. After the revert, V6 is identical to V4.

### How Can I Initiate Revert?

To initiate a revert, switch to the Catalog's comparison mode by clicking the comparison <img src="../images/compare.png" style="zoom:75%;" /> icon in the version drop-down list. Then click **Actions > Revert** in the menu bar. You can choose to revert either the selected (or expanded) data platform or the entire Catalog.

The revert process begins once the user confirms the action.

![revert](../images/revert_init.png)



Upon successful completion of the revert, a new Catalog version is created. 

When there are no differences between the selected versions, the user is notified, and a new version will not be created:

![no-revert](../images/revert_no_changes.png)
