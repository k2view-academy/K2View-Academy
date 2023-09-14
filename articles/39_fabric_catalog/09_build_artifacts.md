<web>

# Catalog Artifacts and Masking

### Overview

The Catalog provides an ability to build artifacts and save them into the Project tree. An artifact includes the details of all Catalog fields with their properties, such as Classification and PII, for a currently displayed Catalog version. 

### Build Artifacts

Building the Catalog's artifact is triggered by clicking **Actions > Build Artifacts** in the Catalog application's [Menu bar](05_catalog_app.md#menu-bar). The artifact is created in a CSV format, saved into the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder of the Project tree and is uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md).

The below is an illustration of the catalog_info.csv file:

<img src="images/catalog_info_mtable.png" style="zoom:75%;" />

The artifact is created for the Catalog version that is displayed in the application. The last column's name holds the version number, such as *V4* in the case of the above image. This column remains always empty.

Note that an artifact can be created for any Catalog version. Each new artifact overrides the previous one in the Project tree.

### Catalog Masking

The purpose of the Catalog Masking mechanism is to perform masking based on the Catalog's artifacts rather than based on the logic defined in each LU population. 3 new Actors have been introduced for this purpose: **CatalogMaskingMapper, CatalogMaskingRecord** and **CatalogMaskingField**.

* The **CatalogMaskingMapper** Actor receives a dataset, which maps the data on the fly, and does not load the entire dataset to memory. The Actor iterates internally on each entry and invokes the **CatalogMaskingRecord** Actor. The Actor returns a dataset with the same structure it was received.

* The **CatalogMaskingRecord** Actor receives a record, splits it internally into key-value pairs and invokes the **CatalogMaskingField** Actor for each pair. The Actor returns an object with the same structure it was received.

* The **CatalogMaskingField** Actor checks if the field should be masked – based on classification and the PII indication set by the Fabric Catalog. If yes, it masks the input value using the Masking Actor and the generator which is configured via the [Classifier Configuration screen](05_catalog_app.md#classifier-configuration). 

The algorithm applied by the **CatalogMaskingField** is described below:

* Search an input field in the **catalog_info** MTable created by the Build Artifacts action. If a field name is found, do the following:
  * Check if PII is true and the Masking property is not OFF (see below more details about the Masking property). If so, the field's value should be masked. 
  * Retrieve the field's Classification to proceed.
* Search the field's Classification in the **catalog_classification_generators** MTable to find out which generator should be used for masking.
* Once the generator is found, invoke the **Masking** Actor with this generator and its parameters defined in the **catalog_classification_generators** MTable.

Click for more details about setting up the catalog_classification_generators MTable using the [Classifier Configuration screen](05_catalog_app.md#classifier-configuration).

To apply the Catalog Masking mechanism, start from running the Discovery job, validating the results and building the Catalog's artifacts. Then, add the **CatalogMaskingMapper** Actor to LU populations. Alternatively, you can add either the **CatalogMaskingRecord** or **CatalogMaskingField** Actors, to apply the masking on a single record or a single field. 

Using the **CatalogMaskingMapper** in the LU population makes the masking process generic since the implementor doesn't need to know in advance which of the population fields should be masked. 

### The Masking Property

While the Classification and PII properties are added to the Catalog nodes by the Classifier plugins, the Masking property can be added manually when it is required to control the masking mechanism of specific fields. 

<img src="images/masking_prop.png" style="zoom:80%;" />

The purpose of adding the Masking property is to mark the fields, which have been identified as PII, that require special handling by the Catalog Masking mechanism. The Masking property can have one of the following valid values, as described below: 

* **Consistent** - the Catalog Masking Actors should produce a consistent value across the Catalog (meaning the same input will always return the same masked value).
* **Unique** - the Catalog Masking Actors should produce a unique value across the Catalog (meaning the masking value will be unique).
* **Consistent & Unique** - the Catalog Masking Actors should produce a consistent and a unique value across the Catalog.
* **Generate value** - the Catalog Masking Actors should produce any random value, not consistent and not unique. 
* **OFF** - the Catalog Masking mechanism should not mask the field. This valid value is useful when a custom masking logic is required. In this case, it is the implementor responsibility to add the custom masking logic to the relevant LU population.

Note that when the Masking property is set to either Consistent, Unique or Consistent & Unique values, it overrides the same definition on the Classification level (performed via the [Classifier Configuration screen](05_catalog_app.md#classifier-configuration)).



[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_catalog_APIs.md) 

</web>





