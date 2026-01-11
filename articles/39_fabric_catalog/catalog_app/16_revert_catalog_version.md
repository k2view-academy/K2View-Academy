# Revert Catalog Version

### Overview

K2view's Catalog supports **versioning**, which is the ability to create a new Catalog version in the Neo4j Graph DB whenever the Discovery process runs and detects differences compared to the previous version. [Click here for more information about the Catalog versioning](06_catalog_versioning.md).

Once the new version is created, the user might realize that the changes are incorrect due to various reasons (e.g. incorrect regular expression). In this case, the user might want to go back to one of the previous Catalog versions.

The Catalog provides an ability to revert from the latest to one of the earlier versions. The revert can be applied to a selected data platform or the whole catalog. The revert activity creates a new Catalog version. 

### How Can I Initiate Revert?

To initiate the revert, start from switching to the Catalog's comparison mode by clicking the comparison <img src="../images/compare.png" style="zoom:75%;" /> icon in the version's drop-down list. Then you can revert either the selected (or expanded) data platform or the entire Catalog. 

The revert is initiated after the user confirms his action. 

![revert](../images/revert_init.png)



When the revert is completed successfully, a new Catalog version is created. 

When there are no differences between the selected versions, the user is notified about it and a new version is not created:

![no-revert](../images/revert_no_changes.png)