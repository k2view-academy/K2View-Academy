# Revert Catalog Version

### Overview

K2view's Catalog supports **versioning**, which is the ability to create a new Catalog version in the Neo4j Graph DB whenever the Discovery process runs and detects differences compared to the previous version. [Click here for more information about the Catalog versioning](06_catalog_versioning.md).

Once the new version is created, the user might realize that the changes are irrelevant or incorrect (due to various reasons). In this case, the user might want to go back to the previous Catalog version.

The Catalog provides an ability to revert from the latest to one of the earlier versions. It can be done for a selected data platform or for the whole catalog. The revert action creates a new Catalog version. 

### Revert Initiation

To initiate the revert, start from switching to the Catalog's comparison mode by clicking the comparison <img src="../images/compare.png" style="zoom:75%;" /> icon in the version's drop-down list.