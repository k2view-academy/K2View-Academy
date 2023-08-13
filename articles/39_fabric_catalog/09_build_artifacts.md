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

The purpose of the Catalog Masking mechanism is to perform the masking based on the Catalog's artifacts rather than based on the logic defined in each LU population. Three new actors have been introduced for that: **CatalogMaskingMapper, CatalogMaskingRecord** and **CatalogMaskingField**.

* The **CatalogMaskingMapper** Actor receives a data set, which maps the data on the fly, and does not load the entire data set to memory. The actor internally iterates on each entry and invokes the **CatalogMaskingRecord** Actor. The actor returns a data set with the same structure as it received it.

* The **CatalogMaskingRecord** Actor receives a record, internally splits it into the pairs of key and value and invokes the **CatalogMaskingField** Actor for each pair. The actor returns an object with the same structure as it received it. 

* The **CatalogMaskingField** Actor checks if the field should be masked – based on classification set by the Fabric Catalog. If yes, it masks the input value using the Masking Actor and the generator (based on prior configuration).

The algorithm applied by the **CatalogMaskingField** is described below:

* Search an input field in the **catalog_info** MTable created by the Build Artifacts action. If a field name is found, do the following:
  * Check if PII is true and Auto-Mask is true or empty. If so, the field's value should be masked. 
  * Retrieve the field's Classification to proceed.
* Search the field's Classification in the **masking_setup** MTable to find out which generator should be used for masking.
* Once the generator is found, invoke the **Masking** Actor with this generator and its parameters defined in the **masking_setup** MTable.

Click for more details about setting up the masking_setup MTable using the [Classifier Configuration screen](05_catalog_app.md#classifier-configuration).

To apply the Catalog Masking mechanism, start from running the Discovery job, validating the results and building the Catalog's artifacts. Then, add the **CatalogMaskingMapper** Actor to LU populations. Alternatively, you can add either the **CatalogMaskingRecord** or **CatalogMaskingField** Actors, to apply the masking on a single record or a single field. 

Using the **CatalogMaskingMapper** in the LU population makes the masking process generic since the implementor doesn't need to know in advanced which of the population fields should be masked. 

### Auto-Mask Property

While Classification and PII properties are added to the Catalog nodes by the Classifier plugins, the Auto-Mask property should be added manually, when needed. 

The purpose of the Auto-Mask property is to mark which Catalog fields - identified as PII - should not be masked using the Masking Actor defined for their Classification. 

For such fields, the user should manually add Auto-Mask = false property in the Catalog application and then attach the relevant masking logic to the population flow.





[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_catalog_APIs.md) 

</web>





