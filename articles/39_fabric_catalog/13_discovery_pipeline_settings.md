# Discovery Pipeline Settings

### Overview

The **Discovery Pipeline** tab in the Catalog Settings provides a full and comprehensive view of the Discovery job configuration. It displays the product's default Baseline configuration rule and the project-level rules. 

The **Baseline** rule includes a list of product built-in plugins with their input parameters, sample size for the data snapshot and more. The product settings are retrieved from the product configuration **plugins.discovery** file.

The Discovery Pipeline setup screen enables performing the following updates:

* Override the Baseline rule's configuration of plugins and other general settings.
* Create various rules which define a crawler filter and/or override the plugins settings.
* Add new plugins to the pipeline.


The overrides are saved into the project **pluginsOverride.discovery** file, created in the Project's ```Implementation/SharedObjects/Interfaces/Discovery/```folder.

This article describes the screen capabilities and explains how they can impact the Discovery job. 

![](images/discovery_pipeline_1.png)

### Baseline Rule

The **Baseline** rule is a product configuration which is applied when running the Discovery job on any data platform (if a more specific rule doesn't exist). It includes the definition of a sample size, global schema exclude and a full list of plugins.

The Baseline rule is always enabled. You can edit the Baseline rule by clicking the **Override** checkbox. The following changes can be applied to the Baseline rule:

* Update the crawler related settings, e.g. a sample size. 
* Update the parameters of the product built-in plugins, e.g. set to inactive or update the threshold. 
* Add a new plugin. 

Note that the Baseline rule overrides are automatically propagated to the project-created rules. For example, when a new plugin is added to the Baseline, it is automatically added to all other rules. 

### Project Rules

The Discovery pipeline enables the creation of multiple rules. Each rule is attached to a data platform, with several optional parameters (schema, dataset, crawler filter and override indicator) that become mandatory based on conditions, as described further in this article. 

The rules are executed based on the following hierarchy: 

* When multiple rules apply on the same process element, the most specific rule takes precedence.
* When there is no specific rule for a process element, the Baseline rule is executed.

There can be one or more rules for the same data platform. 

### How Do I Create a Project Rule?

![](images/discovery_pipeline_2.png)

Click **Add Rule +** to create a new rule. The mandatory rule's parameters are a Rule Name (which must be unique) and a Data Platform. 

* Populating a schema and a dataset is optional. 


* When multiple schemas or datasets are populated, they should be coma separated.

In addition, a rule creation requires either setting a Crawler filter or clicking the Override checkbox or both. 

#### Crawler Filter = Exclude This 

When the filter is set to **Exclude This**:

* The Crawler excludes the specified Schema(s) and Dataset(s), so this rule requires that at least a schema will be populated.
* This rule cannot be combined with Override, because the specified Schema(s) and Dataset(s) are simply excluded.

#### Crawler Filter = Exclude Others

When the filter is set to **Exclude Others**:

* The Crawler excludes everything except for the specified Schema(s) and optionally Dataset(s), so this rule requires that at least a schema will be populated.
* This rule can be combined with Override. It allows to define the Crawler include list and both override the Baseline rules at the same time.


#### Override and No Crawler Filter 

When the filter is empty and the Override checkbox is checked:

* The Crawler is executed on the whole Data Platform.
* The override rules are only applied on the specified Schema(s) and Datasets(s).