<web>

# Catalog Artifacts and Masking

### Overview

The Catalog provides an ability to build artifacts and save them into the Project tree. An artifact includes details of all the Catalog fields with their properties, such as Classification and PII, for a currently displayed Catalog version. 

### Build Artifacts

Building a Catalog artifact is triggered by clicking **Actions > Build Artifacts** in the Catalog application's [Menu bar](05_catalog_app.md#menu-bar). A Catalog artifact is a file called **catalog_info.csv**. It is created in a CSV format, saved into the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder in the Project tree and uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md).

The below image is an example of a Catalog artifact:

<img src="images/catalog_info_mtable.png" style="zoom:75%;" />

The artifact is created for the Catalog version that is displayed in the application. The last column's heading holds the version number, such as *V4* in the case of the above image. This column remains always empty.

Note that a Catalog artifact can be created for any Catalog version. Each new artifact overrides the previous one in the Project tree.

### Catalog Masking

The purpose of the Catalog Masking mechanism is to perform masking based on the Catalog artifact rather than based on the logic defined in each LU population. 3 new actors have been introduced for this purpose: **CatalogMaskingMapper, CatalogMaskingRecord** and **CatalogMaskingField**.

* The **CatalogMaskingMapper** Actor receives a Dataset, which maps the data on the fly, and does not load the entire Dataset to memory. The Actor iterates internally on each entry and invokes the **CatalogMaskingRecord** Actor. The Actor returns a Dataset with the same structure it was received.

* The **CatalogMaskingRecord** Actor receives a record, splits it internally into key-value pairs and invokes the **CatalogMaskingField** Actor for each pair. The Actor returns an object with the same structure it was received.

* The **CatalogMaskingField** Actor checks whether the field should be masked – based on its classification and PII indication set by the Fabric Catalog. If masking should be applied, this Actor masks the input value using the generator which is configured via the [Classifier Configuration screen](05_catalog_app.md#classifier-configuration) per each classification.

The algorithm applied by the **CatalogMaskingField** is described below:

* Search an input field in the **catalog_info** MTable created by the Build Artifacts act. If a field name is found, perform the following:
  * Check if both PII is true and the Masking property is not OFF (see below more details about the Masking property). If they are, the field's value should be masked. 
  * Retrieve the field's Classification in order to proceed.
* Search the field's Classification in the **catalog_classification_generators** MTable to find out which Generator should be used for masking.
* Once a Generator is found, invoke the **Masking** Actor with this generator and its parameters that are defined in the **catalog_classification_generators** MTable.

Click for more details about setting up the catalog_classification_generators MTable using the [Classifier Configuration screen](05_catalog_app.md#classifier-configuration).

To apply the Catalog Masking mechanism, start with running the Discovery job, validating the results and building the Catalog artifacts. Then, add the **CatalogMaskingMapper** Actor to LU populations. Alternatively, for applying the masking on a record or a single field, you can add the **CatalogMaskingRecord** or the **CatalogMaskingField** Actors respectively. 

Using the **CatalogMaskingMapper** in the LU population makes the masking process generic since the implementor doesn't need to know in advance which of the population fields should be masked. 

### The Masking Property

While the Classification and PII properties are added to the Catalog nodes by the Classifier plugins, the Masking property can be added manually when it is required to control the masking mechanism of specific fields. 

<img src="images/masking_prop.png" style="zoom:80%;" />

The purpose of adding the Masking property is to mark the fields identified as PII, that require special handling by the Catalog Masking mechanism. The Masking property can have one of the following valid values:

* **Consistent** - the Catalog Masking Actors should produce a consistent value across the Catalog (meaning the same input will always return the same masked value).
* **Unique** - the Catalog Masking Actors should produce a unique value across the Catalog (meaning the masking value will be unique).
* **Consistent & Unique** - the Catalog Masking Actors should produce a consistent yet unique value across the Catalog.
* **Generate value** - the Catalog Masking Actors should produce any random value, not consistent and not unique. 
* **OFF** - the Catalog Masking mechanism should not mask the field. This valid value is useful when a custom masking logic is required. In this case, it is the implementor's responsibility to add the custom masking logic to the relevant LU population.

Note that when the Masking property is set to either Consistent, Unique or Consistent & Unique value, it overrides the same definition on the Classification level (performed via the [Classifier Configuration screen](05_catalog_app.md#classifier-configuration)).



[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_catalog_APIs.md) 

</web>





