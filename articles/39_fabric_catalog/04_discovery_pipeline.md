# Discovery Pipeline

### Overview

The Discovery job is a pipeline that connects a series of steps where some are executed sequentially and some - in parallel. It has **2** main parts: **Crawler** and **Plugin Framework**.

The Crawler scans the data source while identifying the existing entities and the relationships between them. The Crawler's output is the Catalog schema.

The Plugin Framework is an internal platform for running the plugins. It is a pipeline of plugins that are executed by the Discovery job after the Crawler task completion. 

The pipeline is executed based on a combination of the product configuration and the project rules:

* The **product** configuration of the Discovery Pipeline is a baseline, which includes the list of product built-in plugins, their execution order and input parameters, data snapshot sample size and more. 
* The **project** configuration is a list of user-created rules that can be defined for any of the project's interfaces using a [Discovery Pipeline screen](/articles/39_fabric_catalog/catalog_app/13_discovery_pipeline_settings.md) in the Catalog Settings. The rules can be created on either a schema level or a dataset level, and their purpose is to override the baseline settings. 

### Plugins Pipeline

Each plugin is a piece of business logic that is executed in order to complement the Catalog schema. The plugin’s execution can result in a change to the Catalog schema, such as creation or removal of Catalog elements. Some plugins calculate a score - a confidence level of a plugin result's accuracy.  

The plugin input parameters are:

* ```name``` - plugin's unique name
* ```class``` - plugin's Java class 
* ```active``` - whether the plugin is included in the execution ('true') or not ('false')
* ```threshold``` - the score above which the plugin result impacts the Catalog
  * For example, the threshold is 0.4 and the plugin's rule receives a calculated score of 0.4 or below. In such case, this rule has no impact on the Catalog.
  * To enable the Catalog to show more results, update the threshold to a lower number. To show less results, update the threshold to a higher number.
  * Some plugins don't have a threshold.
* ```monitorDesc``` - the description displayed per each plugin in the Execution Progress area of the Catalog Monitor, under the number.
  * For example, "Classification PII Marker" shows the number of found PII Fields, thus this plugin's monitor description is "PII Fields".
* ```inputParameters``` - is a key/value map of additional input parameters that are different per each plugin.

The K2view Discovery solution includes a constantly growing list of built-in plugins.

[Click here for more details about the built-in plugins](/articles/39_fabric_catalog/plugins/README.md).

### Data Sample Size

The data sample is retrieved from the data source during the Discovery job run. The data is encrypted and is being used by various plugins during the job run. Once the plugins' execution has been completed, the data sample is deleted.

The sample size is defined by:

- Percentage, which defines the % of dataset rows to be retrieved as a sample.
- Min and max size definitions that are set in order to accommodate for very small and very large datasets.

For example, the percentage is 10%, min is 100 and max is 500. Hence, if a table includes 200 rows, the sample size wwould be 100. If a table includes 2,000 rows, the sample size would be 200. If a table includes 100,000 rows, the sample size would be 500.

Starting from V8.3, Row Count check box is defined as part of the sample size. When Row Count is set to false, the Data Snapshot step does not perform ```count(*)```. Instead, the sample size is equal to Max. This is recommended for the data sources that have difficulty performing ```count(*)```, such as Cassandra.

### Global Shema Exclusion

By default, all the data platform's entities are scanned except for those that are in the global schema exclude list. 

The global schema exclude list defines the schemas that should be excluded from a Discovery on any data platform. These excluded schemas are system schemas that are not relevant for the Discovery. The syntax supports regular expressions. For example, "SYS.*" refers to all schema names that start with 'SYS'.

### Baseline and Override Rules

From Fabric V8.2 onwards, the Discovery execution is based on rules that accommodate for different variations of a Discovery pipeline process. It is now possible to create rules per data platform to override the baseline configuration on a schema or even on a dataset level. The job executes a combination of the baseline and user-defined rules.

For example, you can define ```Plugin X``` to be executed on ```Schema 1``` while it is not executed on all other schemas of the same data platform. You can also define a larger data sample on ```Schema 2``` while the rest of the schemas will use a default sample size.

The product initial setup includes a **Baseline** rule that represents a baseline configuration, such as a sample size, list of all product plugins and their default settings.

One can override a Baseline rule, for example, deactivate an active plugin in the product settings. A crawler filter cannot be set on a Baseline rule since it is applied for all data platforms. 

The user can create multiple rules per data platform. Each rule can define:

* Crawler filter - schemas and/or datasets to be included or excluded from the Discovery job.
* Override rule - plugins and other general overrides to the default product settings.
* A combination of a filter and override rules.

The rules are executed based on the following hierarchy: When multiple rules apply to the same process element, the most specific rule takes precedence. The following is an illustration of the rules hierarchy:

* A **Baseline** rule defines ```Plugin X``` as inactive. 
* A **Rule 1** is applied on CRM_DB and it sets ```Plugin X``` to active. 
* A **Rule 2** is applied on CRM_DB and public2 schema, and it sets ```Plugin X``` to active and a threshold = 0.8.

Depending which interface and/or schema the Discovery is executed on, the ```Plugin X``` settings are taken from the most specific rule.

All the overrides are saved in the ```Implementation/SharedObjects/Interfaces/Discovery/``` folder, in the **pluginsOverride.discovery** file. This file is created when the overrides are performed using the [Discovery Pipeline screen](/articles/39_fabric_catalog/catalog_app/13_discovery_pipeline_settings.md) in the Catalog Settings tab.

Click [here](/articles/39_fabric_catalog/catalog_app/13_discovery_pipeline_settings.md) to learn about baseline configuration as well as override rules that can be viewed and updated via the Discovery Pipeline screen in the Catalog Settings tab. 




[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04a_catalog_integration_with_fabric.md) 



