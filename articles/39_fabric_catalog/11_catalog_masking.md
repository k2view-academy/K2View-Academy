<web>

# Catalog Masking Mechanism

The purpose of the Catalog Masking mechanism is to perform masking based on the Catalog profiling. The idea behind this mechanism is to base the masking logic on the discovery results in the Catalog rather than define it in each LU table population. 

To apply the Catalog Masking mechanism, start with running the Discovery job, validating the results and building the [Catalog artifact](09_build_artifacts.md). Then, create an LU and add either the **CatalogMaskingMapper** or **CatalogMaskingRecord** Actor to LU populations. Alternatively, for applying the masking on a single field, you can just add the **CatalogMaskingField** Actors. 

### Catalog Masking Actors

3 new actors have been introduced for this purpose: *CatalogMaskingMapper*, *CatalogMaskingRecord* and *CatalogMaskingField*. The Catalog based masking logic is included in the *CatalogMaskigField* while the *CatalogMaskingMapper* and the *CatalogMaskingRecord* serve as a wrapper - on a dataset level and a record level accordingly. It works as follows:

* The **CatalogMaskingMapper** Actor receives a dataset, which maps the data on the fly, and does not load the entire dataset to memory. The actor iterates internally on each record and invokes the **CatalogMaskingRecord** Actor. The actor returns a dataset with the same structure it was received.
* The **CatalogMaskingRecord** Actor receives a record, splits it internally into key-value pairs and invokes the **CatalogMaskingField** Actor for each pair. The actor returns an object with the same structure it was received.
* The **CatalogMaskingField** Actor’s purpose is to mask a single field’s value based on the Catalog’s classification and the masking rules definition. 
  * The actor starts from checking whether the field should be masked. The check is based on the field's PII and Masking columns set in the catalog_field_info MTable after building the Catalog artifact. [Click for more information about the Catalog artifact](09_build_artifacts.md).
    * If both PII is true and the Masking property is not OFF (see below more details about the Masking property), the field's value should be masked. 
  * Then, the actor retrieves the field's Classification from the **catalog_field_info** MTable and searches for the generator in the **catalog_classification_generators** MTable. The generator can be either one of the existing built-in actors (RandomSSN, RandomZipCode, etc.), a custom actor or a flow.
    * Click for more information about the Catalog's [PII & Masking Setup](10_catalog_settings.md#classifier-pii--masking-setup).
  * Finally, the actor internally invokes the **Masking** actor, sending its parameters as follows:
    * The maskingId is set to the Classification.
    * The flowName is set to the Generator defined in the **catalog_classification_generators** MTable for this Classification.
    * If the given Generator has parameters, they are also taken from the above MTable.

### The Masking Property

While the Classification and PII properties are added to the Catalog nodes by the Classifier plugins, the **Masking** property can be added manually when it is required to control the masking mechanism of specific fields. 

<img src="images/masking_prop.png" style="zoom:80%;" />

The purpose of adding the Masking property is to mark the fields identified as PII, that require special handling by the Catalog Masking mechanism. The Masking property can have one of the following valid values:

* **Consistent** - the Catalog Masking Actors should produce a consistent value across the Catalog (meaning the same input will always return the same masked value).
* **Unique** - the Catalog Masking Actors should produce a unique value across the Catalog (meaning the masking value will be unique).
* **Consistent & Unique** - the Catalog Masking Actors should produce a consistent yet unique value across the Catalog.
* **Generate value** - the Catalog Masking Actors should produce any random value, not consistent and not unique. 
* **OFF** - the Catalog Masking mechanism should not mask the field. This valid value is useful when a custom masking logic is required. In this case, it is the implementor's responsibility to add the custom masking logic to the relevant LU population.

Note that when the Masking property is set to either Consistent, Unique or Consistent & Unique value, it overrides the same definition on the Classification level performed via the [Catalog Settings screen](10_catalog_settings.md).



[![Previous](/articles/images/Previous.png)](10_catalog_settings.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](20_catalog_APIs.md) 

</web>





