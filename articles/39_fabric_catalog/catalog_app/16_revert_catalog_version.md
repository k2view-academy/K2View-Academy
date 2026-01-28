# Revert Catalog Version

### Overview

K2view's Catalog supports [versioning](06_catalog_versioning.md) by creating a new Catalog version in the Neo4j Graph DB whenever the Discovery process detects differences from the previous version, either due to source changes or configuration updates.

If, after user verification, these changes are deemed irrelevant or incorrect, users may choose to revert to a previous Catalog version.

The Catalog allows reverting from the latest version to any earlier version. The revert can be applied to a selected data platform or the entire catalog, generating a new Catalog version. 

The revert is available starting Fabric V8.4.

### How Can I Initiate Revert?

To initiate a revert, switch to the Catalog's comparison mode by clicking the comparison <img src="../images/compare.png" style="zoom:75%;" /> icon in the version drop-down list. Then click **Actions > Revert** in the menu bar. You can choose to revert either the selected (or expanded) data platform or the entire Catalog.

The revert process begins once the user confirms the action.

![revert](../images/revert_init.png)



Upon successful completion of the revert, a new Catalog version is created. 

When there are no differences between the selected versions, the user is notified, and a new version will not be created:

![no-revert](../images/revert_no_changes.png)
