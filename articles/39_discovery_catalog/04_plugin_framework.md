<web>

# Plugin Framework

### Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Discovery Schema. 

The Plugin Framework runs over the Discovery Schema and executes the plugins based on the execution order defined in the Plugins Pipeline configuration file. A result of the plugin execution can be the creation of additional schema entities or properties. A score - a probability that the outcome is correct - is calculated per each new property and augmented to the Discovery Schema as well.

After the rules execution is completed, a Catalog version is created in the neo4j Graph DB. 

### Built-In Plugins Description

The solution has built-in plugins and also supports execution of custom plugins. 

The built-in plugins are:

**Data Profiling**

* The purpose of a data profiling plugin is to analyze and classify the source fields based on their data. The profiling helps identifying which Catalog entities store sensitive information. 
* This plugin runs on a data snapshot, extracted from the source by the Crawler. The profiling rules (regex) are defined in a built-in MTable **data_profiling**. 
* If a match is found between the field's data and the rule regex, a classification property (such as email, gender, or credit card) is added to the node. If a match is found for more than one regex, only one property will be created, with the maximum score.
* If this classification type is also defined as PII, PII = true is set in the same property 
* 

**Matching by Field Name**

* Identify links between the Discovery Schema nodes creating the missing *refers to* relations. This can be useful when, for example, a data source has no PK-FK relations or when cross-schema relations are required. 



[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md) 

</web>