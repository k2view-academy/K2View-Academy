# Catalog Artifacts and Masking

### Overview

The Catalog provides an ability to build the artifacts and save them into the project tree, in order to be used by the Catalog Masking mechanism. The artifact is a file created in CSV format that includes the list of IDs of all catalog fields, their Classification, PII and Auto-Mask properties, for the given catalog version. 

While Classification and PII are the properties added to the Catalog nodes by the Classifier plugins, the Auto-Mask is a property should be added manually when needed.  

### Build Artifacts

Building of the catalog's artifacts is triggered by clicking **Actions > Build Artifacts** in the menu bar.  

The artifact called **catalog_info** is created by the Fabric and saved into the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder of the Project tree.  

Below is an illustration of the catalog_info.csv file:

<img src="images/catalog_info_mtable.png" style="zoom:75%;" />

The last column's name holds the catalog version for which the artifact was created (e.g. V4). This column is always empty. 

Note that the artifact can be created for any version, however it overrides the previous artifact in the Project tree.

### Catalog Masking

The Catalog Masking mechanism uses the catalog_info MTable together with the masking_setup MTable in order to identify at run-time which fields are supposed to be masked and how.

The Catalog Masking algorithm is as follows:

* Go over the entries in the catalog_info MTable (combination of data_platform, schema, dataset, class and field), and for 	each:
* If PII is true and Auto-mask is not false, the field's value should be masked. 
* Using the Classification value, find the masking actor and its parameters via the masking_setup MTable.





[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_catalog_APIs.md) 







