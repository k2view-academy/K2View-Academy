# Classifier Configuration

The purpose of the Classifier Configuration screen is to setup the rules used by the [Catalog plugins](04_plugin_framework.md#built-in-plugins) and the [Catalog Masking actors](11_catalog_masking.md). The Catalog includes a set of built-in rules defined on the product level. These rules can be updated to fit the Project's needs. 

This article includes the following sections:

* [Regex Setup Tab](10_classifier_configuration.md#regex-setup-tab)
* [PII & Masking Setup Tab](10_classifier_configuration.md#pii--masking-setup-tab)
* [Configuration Save](10_classifier_configuration.md#configuration-save)

### Regex Setup Tab

The **Regex Setup** tab allows to view and update the Profiling regular expression rules that are used by the Profiling built-in plugins: *Data Regex Classifier* and *Metadata Regex Classifier*. 

<img src="images/classifier.png" style="zoom: 67%;" />

The columns of the tab are:

* **Classification** defines the value of a Classification property added to the Catalog's fields as a result of the Profiling plugins. 

* **Type** can be either **Field Name** or **Field Value**:
  * The entries defined with the **Field Name** type are used by the *Metadata Regex Classifier* plugin.
  * The entries defined with the **Field Value** type are used by the *Data Regex Classifier* plugin.
* **Regular Expression** defines the expression applied on the field, either its name or its value, depending on the **Type**.
* **Score** defines the confidence level that the current rule is true. 

Each **Classification** can have several definitions, with the same or different **Types**.

You can edit the existing definition or add new using this screen. The Classification value can be either new or selected from the list.

Click [here](04_plugin_framework.md#built-in-plugins) to get more details about these plugins. 

### PII & Masking Setup Tab

The **PII & Masking Setup** tab allows to view and update the PII and Catalog based masking settings of each classification. The PII indicator is used by the *Classification PII Marker* built-in plugin. The Masking setup is used by the Catalog Masking actors as described further. 

<img src="images/classifier_pii_masking.png" style="zoom: 67%;" />

The tab includes two definitions per each Classification:

* **PII** - to indicate whether the Classification is considered as PII. 
* **Generator** - to define which masking logic should be applied by the [Catalog masking mechanism](09_build_artifacts.md#catalog-masking).

Each **Classification** can have **only one** definition (row) in this tab.

To edit the Generator and its parameters - click the <img src="images/edit_masking.png" style="zoom: 80%;" /> icon.

* The **Generator** can be either one of the existing built-in actors, a custom actor or a flow that will be used to perform the masking logic for the values of field with a given Classification.

* For example, for masking the fields classified as Social Security Number, you can either use the built-in RandomSSN.actor or create your own actor or a flow and attach it here.

  <img src="images/classifier_pii_masking_edit.png" style="zoom: 67%;" />

Click to get more details about the [Catalog masking mechanism](09_build_artifacts.md#catalog-masking).

### Configuration Save

Once the Save button is clicked, the following MTables are updated in the Fabric's memory and in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable ```folder of the Project tree:

* The Regex setup tab updates the **metadata_profiling** and **data_profiling** MTables.
* The PII & Masking setup tab updates the **pii_profiling** and **catalog_classification_generators** MTables.

Note that Classifier Configuration settings impact the above MTables only. There is no need to re-build the catalog artifact when the configurations are updated.



[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_catalog_masking.md) 

