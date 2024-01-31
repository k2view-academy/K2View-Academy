<web>

# Catalog Settings

The purpose of the Settings screen in the Catalog application is to view and edit the rules used by the Catalog. The Catalog includes the built-in rules for profiling and masking. These rules can be updated to fit the Project's needs. 

This article includes the following sections:

* [Classifier Regex Setup](10_catalog_settings.md#classifier-regex-setup)
* [Classifier PII & Masking Setup](10_catalog_settings.md#classifier-pii--masking-setup)

### Classifier Regex Setup

The **Regex Setup** tab allows to view and update the Profiling regular expression rules that are used by the Profiling built-in plugins: *Data Regex Classifier* and *Metadata Regex Classifier*. 

<img src="images/settings_regex.png" style="zoom:80%;" />

The columns of this tab are:

* **Classification** defines the value of a Classification property added to the Catalog's fields as a result of the Profiling plugins. 

* **Type** can be either **Field Name** or **Field Value**:
  * The entries defined with the **Field Name** type are used by the *Metadata Regex Classifier* plugin.
  * The entries defined with the **Field Value** type are used by the *Data Regex Classifier* plugin.
* **Regular Expression** defines the expression applied on the field, either its name or its value, depending on the **Type**.
* **Score** defines the confidence level that the current rule is true. 

Each **Classification** can have several definitions, with the same or different **Types**.

You can edit the existing definition or add new using this screen. The Classification value can be either new or selected from the list.

Once the Save button is clicked, the **metadata_profiling** and **data_profiling** MTables are updated in the Fabric's memory and in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable ```folder of the Project Tree.

Click [here](04_plugin_framework.md#built-in-plugins) for more details about these plugins.

### Classifier PII & Masking Setup

The **PII & Masking Setup** tab allows to view and update the PII and Catalog based masking settings of each classification. The PII indicator is used by the *Classification PII Marker* built-in plugin. The Masking setup is used by the Catalog Masking actors as described further. 

<img src="images/settings_pii_mask.png" style="zoom:80%;" />

The tab includes two definitions per each Classification:

* **PII** - indicates whether the Classification is considered as PII. 
* **Generator** - shows which actor or flow is applied by the [Catalog masking mechanism](11_catalog_masking.md).

Each **Classification** can have **only one** definition (row) in this tab.

#### Generator Setup

To setup the Generator and its parameters - click the <img src="images/edit_masking.png" style="zoom: 80%;" /> icon to expand the Classification area. 

<img src="images/settings_masking_edit.png" style="zoom: 80%;" />

Here you could select an actor or a flow for generating a random value. The Generator can be either one of the existing built-in actors, a custom actor or a flow. 

The generated value is populated to a field with a given Classification, upon the invocation of a Catalog masking actor - e.g. during the table population. For example, for masking the fields classified as Social Security Number, you can either use the built-in RandomSSN.actor or create your own actor or a flow and attach it here.

Upon selecting an actor or a flow, its respective input parameters are automatically added to the setup screen. Note that the first input is considered to include the value to be masked and is not one of the configuration parameters. Thus, it is hidden by this configuration screen. So, when creating a custom actor or flow to generate a value, the actor/flow must have an input called 'value', even if this flow doesn't need to receive any input. Otherwise, the first input (defined as Link or External) will be hidden.

Once the Save button is clicked on the **PII & Masking Setup** tab, the **pii_profiling** and **catalog_classification_generators** MTables are updated in the Fabric's memory and in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable ```folder of the Project tree.

Click to get more details about the [Catalog masking mechanism](11_catalog_masking.md).



[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_catalog_masking.md) 

</web>
