<web>

# Catalog Masking Mechsnism

The purpose of the Catalog Masking mechanism is to perform masking based on the Catalog artifact rather than based on the logic defined in each LU population. 

3 new actors have been introduced for this purpose: CatalogMaskingMapper, CatalogMaskingRecord and CatalogMaskingField.

* The **CatalogMaskingMapper** Actor receives a Dataset, which maps the data on the fly, and does not load the entire Dataset to memory. The actor iterates internally on each entry and invokes the CatalogMaskingRecord Actor which returns a Dataset with the same structure it was received.
* The **CatalogMaskingRecord** Actor receives a record, splits it internally into key-value pairs and invokes the CatalogMaskingField Actor for each pair. The actor returns an object with the same structure it was received.
* The **CatalogMaskingField** Actor checks whether the field should be masked:
  * This check is performed based on its PII and Masking columns set in the catalog_field_info MTable after building the Catalog artifact. [Click here for more information about this MTable and how it is created](09_build_artifacts.md).
  * If both PII is true and the Masking property is not OFF (see below more details about the Masking property), the field's value should be masked. 
  * Then, get the field's Classification from the **catalog_field_info** MTable and search for the Masking Generator (a flow and or an actor) in the **catalog_classification_generators** MTable
  * [Click for more information about setting up the catalog_classification_generators MTable using the Classifier Configuration screen](10_classifier_configuration.md).


To apply the Catalog Masking mechanism, start with running the Discovery job, validating the results and building the Catalog artifacts. Then, add the **CatalogMaskingMapper** or **CatalogMaskingRecord** Actor to LU populations. Alternatively, for applying the masking on a single field, you can just add the **CatalogMaskingField** Actors . 

### The Masking Property

While the Classification and PII properties are added to the Catalog nodes by the Classifier plugins, the **Masking** property can be added manually when it is required to control the masking mechanism of specific fields. 

<img src="images/masking_prop.png" style="zoom:80%;" />

The purpose of adding the Masking property is to mark the fields identified as PII, that require special handling by the Catalog Masking mechanism. The Masking property can have one of the following valid values:

* **Consistent** - the Catalog Masking Actors should produce a consistent value across the Catalog (meaning the same input will always return the same masked value).
* **Unique** - the Catalog Masking Actors should produce a unique value across the Catalog (meaning the masking value will be unique).
* **Consistent & Unique** - the Catalog Masking Actors should produce a consistent yet unique value across the Catalog.
* **Generate value** - the Catalog Masking Actors should produce any random value, not consistent and not unique. 
* **OFF** - the Catalog Masking mechanism should not mask the field. This valid value is useful when a custom masking logic is required. In this case, it is the implementor's responsibility to add the custom masking logic to the relevant LU population.

Note that when the Masking property is set to either Consistent, Unique or Consistent & Unique value, it overrides the same definition on the Classification level (performed via the [Classifier Configuration screen](10_classifier_configuration.md).



[![Previous](/articles/images/Previous.png)](10_classifier_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](20_catalog_APIs.md) 

</web>





