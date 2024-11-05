# Discovery Pipeline

### Overview

The Discovery job is a pipeline which connects a series of steps where some are executed sequentially and some - in parallel. It has two main parts: Crawler and Plugin Framework.

* The Crawler scans the data source while identifying the existing entities and the relationships between them. The Crawler's output is the Catalog schema.
* The Plugin Framework is an internal platform for running the plugins. It is a pipeline of plugins which are executed by the Discovery job after the Crawler completion. 

 The pipeline execution is performed based on the product configuration **plugins.discovery** file which defines the crawler filter (if needed) and the list of plugins, their execution order and input parameters.

Starting V8.2, the pipeline configuration is performed via a new [Discovery Pipeline screen] rather than by editing the plugins.discovery file manually. 

### Plugins Pipeline

Each plugin is a piece of business logic that executes predefined rules in order to complement the Catalog schema. The plugin’s execution can result a change to the Catalog schema, such as creation or removal of Catalog elements. Some plugins calculate a score - a confidence level of a plugin result's accuracy.  

The plugin input parameters are:

* ```name``` - plugin's unique name
* ```class``` - plugin's Java class 
* ```active``` - whether the plugin is included in the execution ('true') or not ('false')
* ```threshold``` - the score above which the plugin result impacts the Catalog
  * For example: the threshold is **0.4** and the plugin's rule receives a calculated score of 0.4 or below. This rule has no impact on the Catalog.
  * To enable the Catalog to show more results, update the threshold to a lower number lower. To show less results, update the threshold to a higher number.
* ```monitorDesc``` - the description displayed per each plugin in the Execution Progress area of the Catalog Monitor, under the number.
  * For example, "Classification PII Marker" shows the number of found PII Fields, thus this plugin's monitor description is "PII Fields".
* ```inputParameters``` - is a key/value map of additional input parameters, which are different per each plugin.

The K2view Discovery solution includes a constantly growing list of built-in plugins.

[Click here for more details about the built-in plugins](/articles/39_fabric_catalog/plugins/README.md).

### Data Sample Size

The data sample is retrieved from the data source during the Discovery job run. The data is encrypted and is being used by various plugins during the job run. Once the plugins' execution has been completed, the data sample is deleted.

The sample size is defined as follows:

- Percentage defines the % of the dataset rows to be retrieved as a sample.
- The min and max size definitions are set in order to accommodate for very small and very large datasets. This means that the sample size can’t be lower than minimum or higher than maximum per each dataset.

### Global Shema Exclusion

By default, all the data platform's entities are scanned except for those in the global schema exclude list. 

The global schema exclude list defines the schemas that should be excluded from a discovery on any data platform. Those are system schemas that are not relevant for the discovery. The syntax supports regular expressions. For example, "SYS.*" means all schemas with a name that starts with 'SYS'.

### Baseline and Override Rules

Starting V8.2, the Discovery execution is based on the rules. Creation of various rules enables creating different variations of a discovery pipeline process per data platform and schema.

For example, you can define that a ```Plugin X``` is executed on ```Schema 1``` while it is not executed on all other schemas of the same data platform. You can also define a bigger data sample on ```Schema 2``` while all the rest will have a default sample size. 

The product initial setup includes a **Baseline** rule which represents a baseline configuration, such as a sample size, list of all product plugins and their default settings.

One can override a Baseline rule, e.g. make some plugin to be active, if its product setting is inactive. A crawler filter cannot be set on a Baseline rule since it is applied for all data platforms. 

The user can create one or more rules, per each relevant data platform. Each rule can define:

* Crawler filter - schemas and/or datasets to be included or excluded from the Discovery job.
* Override rule - plugins and other general overrides to the default product settings.
* A combination of a filter and override rules.

When there are multiple rules for the same process element, the most specific rule "wins". For example:

* A **Baseline** rule defines that a ```Plugin X``` is inactive. 
* A **Rule 1** is created for CRM_DB and it sets ```Plugin X``` to active. 
* A **Rule 2** is created for CRM_DB and public2 schema, and it  sets ```Plugin X``` to active and a threshold = 0.8.

So, depending of which interface and/or schema the Discovery is executed, the ```Plugin X``` settings are taken from the most specific rule.

All the above configuration changes are saved on the project level under the ```Implementation/SharedObjects/Interfaces/Discovery/``` folder. 

The baseline configuration as well as the override rule can be viewed and updated via the Discovery Pipeline screen in the Catalog Settings. 

[Click here for a detailed screen description, the guidelines for rules creation and examples.]






[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04a_catalog_integration_with_fabric.md) 



