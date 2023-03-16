<web>

# Plugin Framework

### Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Discovery Schema. 

The Plugin Framework runs over the Discovery Schema, created by the Crawler, and executes the plugins. A result of the plugin execution can be the creation (or removal) of various Catalog elements or properties. Each plugin calculates a score - a probability that the outcome is correct. The score is calculated per each new element or property and is augmented to the Discovery Schema.

The Data Discovery solution includes a constantly growing list of built-in plugins. The list of plugins and their execution order is defined by the Plugins Pipeline configuration file, which also determines a threshold per each plugin. The threshold is the minimum score required for adding the plugin results to the Catalog. For example, if the plugin found a match with a score of 0.3 while the plugin's threshold is set to 0.7, the plugin results will be dropped and will not be added to the Catalog. 

The Plugin Framework supports execution of custom plugins. In order to incorporate them into the process, these custom plugins need to be added to the Plugins Pipeline configuration file.

### Built-In Plugins

**Data Profiling**

* The purpose of the Data Profiling is to classify the source fields based on their data. Among other goals, the profiling helps to identify which Catalog entities store sensitive information and should therefore be masked. 
* The plugin runs on a data snapshot, extracted by the Crawler from the data source, and executes the profiling rules. The rules are defined in a built-in **data_profiling** MTable. 
* If the field's data match a rule, a **Classification** property is added to the field's properties with a value such as **email**, **gender**, or **credit card**. If a match is found for more than one rule, only one property is created (the one with the higher score).
* If this classification type is defined as PII in the data_profiling MTable, PII properties is set to true in the field's properties. 

**Matching by Field Name**

* The purpose of this plugin is to identify possible links between the Discovery Schema nodes in order to create additional ***refers to*** relations. This plugin is especially useful when, for example, a data source has no PK-FK relations or when cross-schema relations need to be established. 
* The matching algorithm works, each time, on comparing 2 field names of 2 different datasets. Prior to matching, formatting rules are applied in order to "normalize" the field names (e.g. remove underscore ‘_’, convert to lower-case). 
* If a match is found, the plugin estimates the link direction: which field is a PK and which one is an FK.
* Eventually, the relation is created with the score - the probability that the match is correct. 

**Nullability Check By Field Data**

* The purpose of this plugin is to check the % of null values per column, in the selected data snapshot.
* As a result, the **Nullability Percentage** property is added to the field's properties. Its score indicates the % of null values in the column.



[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_catalog_app.md) 

</web>
