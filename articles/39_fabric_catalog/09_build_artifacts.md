<web>

# Catalog Artifacts and Masking

### Overview

The Catalog provides an ability to build artifacts and save them into the Project tree. An artifact is a file that includes the details of all Catalog nodes with their properties, such as Classification, PII and Auto-Mask, for a currently displayed Catalog version. 

### Build Artifacts

Building the Catalog's artifact is triggered by clicking **Actions > Build Artifacts** in the the Catalog application's [Menu bar](05_catalog_app.md#menu-bar). The artifact is created in a CSV format, saved into the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder of the Project tree and is uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md).

The below is an illustration of the catalog_info.csv file:

<img src="images/catalog_info_mtable.png" style="zoom:75%;" />

The artifact is created for the Catalog version displayed in the application. The last column's name holds the version number, such as *V4* in this case. This column always remains empty. 

Note that an artifact can be created for any Catalog version. Each new artifact overrides the previous one in the Project tree.

### Catalog Masking

The purpose of the Catalog Masking mechanism is to keep the logic which fields are supposed to be masked and how to mask them in the Catalog rather than in the LU's population. 

The Catalog Masking can be applied to the LU's population by adding either **CatalogMaskingRecord** or **CatalogMaskingField** Actors. The difference between these two actors is that the CatalogMaskingField applies the masking logic on a specific field only while the CatalogMaskingRecord receives the whole record and then applies the masking logic per each field. 

Using the CatalogMaskingRecord in the LU population makes the masking process more generic since the implementor doesn't need to know in advanced which fields should be masked. 

The algorithm of the Catalog Masking is described below:

* Search an input field in the **catalog_info** MTable (using the combination of data_platform, schema, dataset, class and field columns). If a field name is found, do the following:
  * Check if PII is true and Auto-Mask is true or empty. If so, the field's value should be masked. 
  * Retrieve the field's Classification to proceed.
* Search the **masking_setup** MTable by the field's Classification to find which generator should be used for masking.
* Once the generator is found, the **Masking** Actor is invoked with this generator and its parameters defined in the masking setup MTable.

Click for more details about setting up the masking_setup MTable using the  [Classifier Configuration screen](05_catalog_app.md#classifier-configuration).

### Auto-Mask Property

While Classification and PII properties are added to the Catalog nodes by the Classifier plugins, the Auto-Mask property should be added manually, when needed. 

The purpose of the Auto-Mask property is to mark which Catalog fields - identified as PII - should not be masked using the Masking Actor defined for their Classification. 

For such fields, the user should manually add Auto-Mask = false property in the Catalog application and then attach the relevant masking logic to the population flow.





[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_catalog_APIs.md) 

</web>





