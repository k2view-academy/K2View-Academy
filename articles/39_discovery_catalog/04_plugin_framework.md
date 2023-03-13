<web>

# Plugin Framework

### Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Discovery Schema. 

The Plugin Framework runs over the Discovery Schema, created by the Crawler, and executes the plugins based on the order defined in the Plugins Pipeline configuration file. A result of the plugin execution can be the creation of additional schema entities or properties, while each new property is given a score. A score is a probability that the outcome is correct; it is calculated per each new property and augmented to the Discovery Schema.

After the rules execution is completed, a Catalog version is created in the neo4j Graph DB. 

### Built-In Plugins

The Data Discovery solution includes a constantly growing list of built-in plugins. 

**Data Profiling**

* The purpose of Data Profiling is to classify the source fields based on their data analysis. The profiling helps identifying which Catalog entities store sensitive information and thus should be masked. 
* The plugin runs on a data snapshot, extracted by Crawler from the source and executes the profiling rules which are defined in a built-in **data_profiling** MTable. 
* If the field's data matches a rule, a **Classification** property (such as email, gender, or credit card) is added to the field's properties. If a match is found for more than one rule, only one property with the maximum score is created.
* If this classification type is defined as PII in the data_profiling MTable, PII properties is set to true in the field's properties. 

**Matching by Field Name**

* The purpose of this plugin is to identify possible links between the Discovery Schema nodes in order to create additional ***refers to*** relations. This plugin is especially useful when, for example, a data source has no PK-FK relations or when cross-schema relations need to be established. 
* The matching algorithm works on comparing 2 field names of 2 different datasets each time. Prior to the matching, formatting rules are applied in order to "normalize" the field name (e.g. remove underscore ‘_’, convert to lower-case). 
* If the match is found, the next step is to estimate the link direction: which field is a PK and which one is an FK.
* Eventually, the relation is created with the score - the probability that the match is correct. 

**Nullability Check By Field Data**

* The purpose of this plugin is to check the % of null values per column, in the selected data snapshot.
* As a result, the **Nullability Percentage** property is added to the field's properties. Its score indicates the % of null values in the column.



[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 

</web>