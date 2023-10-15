# Classifier Configuration

The purpose of the Classifier Configuration screen is to setup the rules used by the [Catalog plugins](04_plugin_framework.md#built-in-plugins) and the [Catalog Masking actors](11_catalog_masking.md). The Catalog includes a set of built-in rules defined on the product level. These rules can be updated to fit the project's needs. 

It includes the following tabs:

### Regex Setup

The **Regex Setup** tab allows to view and update the profiling regular expression rules that are used by the Profiling built-in plugins: *Data Regex Classifier* and *Metadata Regex Classifier*. 

<img src="images/classifier.png" style="zoom: 67%;" />

The columns of the tab are:

* **Classification** defines the value of a Classification property added to the Catalog's fields as a result of the Profiling plugins. 

* **Type** can be either **Field Name** or **Field Value**:
  * The entries defined with the **Field Name** type are used by the *Metadata Regex Classifier* plugin.
  * The entries defined with the **Field Value** type are used by the *Data Regex Classifier* plugin.
* **Regular Expression** defines the expression applied on the field, either its name or its value, depending on the **Type**.
* **Score** defines the confidence level that the current rule is true. 

Each **Classification** can have several definitions, with the same or different types.

You can edit the existing records or add the new records using this screen. The Classification value can be either new or selected from the list.

Once the Save button is clicked, the **metadata_profiling**  and **data_profiling** MTables are updated in the Fabric's memory and also in the Project tree.  

Click [here](04_plugin_framework.md#built-in-plugins) to get more details about these plugins. 

### PII & Masking Setup

The **PII & Masking Setup** tab allows to view and update the PII and Catalog based masking settings of each classification. Each classification can have **only one** definition in this tab.

<img src="images/classifier_pii_masking.png" style="zoom: 67%;" />

The tab includes two definitions:

* **PII** - to indicate whether the field with this Classification should be marked as PII.
* **Generator** - to display which masking logic should be applied by the [Catalog masking mechanism](09_build_artifacts.md#catalog-masking).

To edit the Generator and its parameters - click the <img src="images/edit_masking.png" style="zoom: 80%;" /> icon.

* The Generator can be either an actor or a flow that will be used to perform the masking logic for the values of field with a given Classification.

* For example, for masking the fields classified as Social Security Number, you can either use the built-in RandomSSN.actor or create your own flow and attach it here.

  <img src="images/classifier_pii_masking_edit.png" style="zoom: 67%;" />

The **Save** button commits all the changes performed in both tabs into the Fabric memory. The profiling rules are also saved in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable ```folder in the Project tree, thus the updated configuration MTables can be committed to your Project.



[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_catalog_masking.md) 

