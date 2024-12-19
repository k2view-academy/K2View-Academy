# Catalog Settings

The purpose of the Settings tab in the Catalog application is to enable viewing and editing various Catalog configurations. The Catalog includes product pre-defined settings that can be updated to accommodate the Project's needs. The updates are saved in the project. 

The Settings tab includes the following sections, described in this and other articles:

* [Classifier Regex](10_catalog_settings.md#classifier-regex-tab)
* [PII & Masking](10_catalog_settings.md#pii--masking-tab)
* [Sequences](10_catalog_settings.md#sequences-tab), available from V8.1
* [Discovery Pipeline](13_discovery_pipeline_settings.md), available from V8.2

## Classifier Regex Tab

The **Classifier Regex** tab allows to view and update the Profiling regular expression rules that are used by the Profiling built-in plugins, *Data Regex Classifier* and *Metadata Regex Classifier*.

![](images/settings_regex.png)

The columns of this tab are:

* **Classification**, which defines the value of a Classification property added to the Catalog's fields as a result of the Profiling plugins. 

* **Type**, which can be either **Field Name** or **Field Value**:
  * Entries defined as the **Field Name** type are used by the *Metadata Regex Classifier* plugin.
  * Entries defined as the **Field Value** type are used by the *Data Regex Classifier* plugin.
* **Regular Expression**, which defines the expression applied on the field, either its name or its value, depending on the **Type**.
* **Score**, which defines the confidence level that the current rule is true. 

Each **Classification** can have several definitions, with either the same or different **Types**.

Using this tab, you can either edit existing definitions or add new ones. The Classification value can be either new or selected from the list.

Once the Save button is clicked, the **metadata_profiling** and **data_profiling** MTables are updated in Fabric's memory and in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable ```folder of the Project tree.

Click [here](04_plugin_framework.md#built-in-plugins) for more details about these Profiling plugins.

## PII & Masking Tab

The **PII & Masking** tab allows to view and update the PII and Catalog-based masking settings of each classification. The PII indicator is used by the *Classification PII Marker* built-in plugin. The Masking setup is used by the Catalog Masking actors as described later in this article.

<img src="images/settings_pii_mask.png" />

Each **Classification** in this tab is unique, and it includes the following attributes:

* **PII** indicates whether the Classification is considered Personally Identifiable Information. 
* **Generator** shows which actor or flow is applied by the [Catalog masking mechanism](11_catalog_masking.md) for generating masking values. The generator runs in the case of either:
  - Data masking
  - [Rule-based](/articles/TDM/tdm_implementation/16_tdm_data_generation_implementation.md) synthetic data generation
* **Consistency Mode** sets the definition of consistency, uniqueness and see for a value that will be generator by the selected Generator. The values of the Consistency Mode are:
  * **Random** (not consistent and not unique)
  * **Consistent using table** (consistent and not unique)
  * **Consistent using seed** (consistent with seed and not unique)
  * **Consistent and unique**

Note that **Consistent using seed** value is only available when the selected Generator supports seed.

Click [here](/articles/26_fabric_security/06_data_masking.md) for more information about data consistency.

In this tab, each classification can have only one definition (row). Note that you cannot create a sequence (via the Sequence Setup tab) with the same name as a classification that appears in this tab since both classifications & sequences are saved in the same MTable.

#### Masking Setup Guidelines

Click the <img src="images/edit_masking.png" style="zoom: 80%;" /> icon to expand the Generator and its parameters setup area (PII, Consistency Mode and other [Advanced](10_catalog_settings.md#advanced-masking-settings) parameters), that will be used for generating a value. The Generator can be any existing built-in actor, a custom actor or a flow, which should be created under the **Shared Objects** in the Fabric Studio.

Upon invocation of a Catalog Masking actor - e.g., during a table population - the generated value is populated in a field with a given Classification. For instance, when a field is classified as a Social Security Number, you should set up the Generator for masking it. The Generator can be either the built-in RandomSSN.actor, or a custom actor or flow.

<img src="images/settings_masking_edit.png"  />

When selecting an actor or a flow, its respective input parameters are dynamically added below it. 

**Guidelines for Creating a Masking Flow**

The first input parameter of a masking flow (or a custom actor) - selected as a Generator - is considered as the value that should be masked, and not as a masking configuration parameter. Hence, it is hidden (and not dynamically added) when a masking flow is selected in the Masking setup screen above. This is applicable only for an input parameter of Link or External type. 

Therefore, when creating such a flow, its first input should be named 'value', even if this flow doesn't need to receive any input. This prevents the hiding of the first input from the Masking setup screen as explained above. 

Below is a sample of such flow:

<img src="images/settings_masking_flow.png" style="zoom: 80%;" />



Once the Save button is clicked in the **PII & Masking** tab, the **pii_profiling** and **catalog_classification_generators** MTables are updated in Fabric's memory and in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable ```folder of the Project tree.

Click for more details about the [Catalog masking mechanism](11_catalog_masking.md).

#### Advanced Masking Settings

The purpose of the Advanced Masking Settings pop-up window is to allow setting up of additional masking parameters. This window includes the following:

* **Masking indicators** determine the masking behavior during a flow run. They can be set either per population via the Catalog Masking Actor's inputs or per Classification via the Advanced Masking Settings screen. The Catalog definition of masking indicators overrides the setting of these indicators on the Catalog Masking Actor - for all the fields with the same Classification.
* **Formatter Name and Parameters** are set in order to enable the [format-preserving masking](/articles/26_fabric_security/06_data_masking.md#format-preserving-masking).
* **Pre-Execution Logic** is an actor or a flow to be executed by the Catalog Masking Actor. 

<img src="images/settings_masking_advanced.png" />

The Advanced Masking Settings are defined per each Classification by using the above pop-up window.

The **Submit** button in this window aggregates the data in the application’s client side until saving is done using the **Save** button in the **PII & Masking** tab. 

Upon clicking the **Save** button in the **PII & Masking** tab, the **pii_profiling** and  **catalog_classification_generators** MTables are updated in Fabric's memory and in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable ```folder of the Project tree.

## Sequences Tab

The **Sequences** tab allows to set up the sequences that can be generated in a project as part of a population or any other flow. This tab doesn't have a product built-in list of sequences since the sequence names and their definitions are usually project specific. 

To create a sequence: 

* Click the **Add Record +** button and populate a **Sequence Name**, **Generator** and its parameters (PII, Consistency Mode and the [Advanced](10_catalog_settings.md#advanced-sequence-settings) parameters, if needed), that will be used for generating a sequence value. 
* Note that the **Generator** is pre-populated with the **Sequence.actor** though it can be updated to any existing built-in actor, a custom actor or a flow (the flow should be created under the project's **Shared Objects**).
* The **sequenceId** parameter of the **Sequence.actor** is populated with the same value as the **Sequence Name**, when it is typed for the first time. Later, each one can be changed to a different value, if needed.

<img src="images/settings_seq.png" />

Each sequence can have only one definition (row). Note that you cannot create a classification (via the PII & Masking tab) with the same name as a sequence in this tab.

Currently the Catalog doesn’t identify the sequence fields automatically. Thus, after a list of sequences is set in the **Sequences** tab, the relevant Catalog fields should be marked as sequences manually, as follows:

* Click **Actions > Edit Catalog**.
* Navigate to the required field and click the <img src="images/add.png" alt="plus" style="zoom:75%;" /> icon to add a new property. 
  * Select or type sequenceName as the property name.
  * In the property value, select the name of the sequence that was setup via the Sequences tab. 

<img src="images/add_sequence_prop.png" />

#### Advanced Sequence Settings

The purpose of the Advanced Sequence Settings pop-up window is to setup additional sequence parameters; it is very similar to the Advanced Masking Settings pop-up window. 

<img src="images/settings_seq_advanced.png" />

Upon clicking the **Save** button in the **Classifier Sequence Setup** tab, the **pii_profiling** and **catalog_classification_generators** MTables are updated in Fabric's memory and in the ```Implementation/SharedObjects/Interfaces/Discovery/MTable ```folder of the Project tree.

The sequences are saved in the same MTable as the masking classifications - **catalog_classification_generators**, with the following difference:

* The category of masking classifications is **enable_masking**.
* The category of non-PII sequences is **enable_sequence**.
* The category of PII-sequences is **enable_masking_uniqueness**.



[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_catalog_masking.md) 

