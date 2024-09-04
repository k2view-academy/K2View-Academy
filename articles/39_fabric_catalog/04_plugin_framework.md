# Plugin Framework

### Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Catalog schema. 

The Plugin Framework is executed by the Discovery job after completion of the Crawler. It runs over the Catalog schema and executes the plugins. If the plugin’s rule returns 'true', it can result with a change to the Catalog schema, such as creation or removal of Catalog elements. Each plugin calculates a score - a confidence level of a plugin result's accuracy. The score is calculated per each Catalog element.

The Data Discovery solution includes a constantly growing list of built-in plugins. 

This article describes the main configuration parameters of the **plugins.discovery** file. The list of active plugins, their execution order and configuration parameters are described in the [next article](04a_builtin_plugins.md).

### Plugins Pipeline

The plugins.discovery is the configuration file of the Plugins Pipeline process. Starting from V8.0, this file is part of the product's resource and it is located in the ```/fabric/resources/discovery``` folder. 

The plugins.discovery configuration file includes settings of the Discovery job, such as:

* Data sample settings
* Data platform's inclusion / exclusion lists
* List of active plugins, their threshold and execution order

When a project-level override is needed (such as setting an exclude list or disabling a plugin), the file should be copied to the Web Studio under the ```Implementation/SharedObjects/Interfaces/Discovery/``` folder.

Every time the plugins.discovery file is updated, the Discovery job should be rerun, applying the changes on the Catalog.

### Data Sample Size

The data sample is retrieved from the data source during the Discovery job run. The data is encrypted and is being used by various plugins during the job run. Once the plugins' execution has been completed, the data sample is deleted.

The sample size is configured in the **sample_size** section of the plugins.discovery file as follows:

* Percentage defines the % of the dataset rows to be retrieved as a sample.
* The min_size and max_size definitions are set in order to accommodate for very small and very large datasets. This means that the sample size can’t be lower than minimum or higher than maximum per each dataset.

### Global Shema Exclusion

Starting from V8.0 HF1, a **global_schema_exclude** section has been added to the plugins.discovery file and it allows setting up a list of schemas to be excluded from any data platform when running the Discovery job. This section should be used for listing various system schemas. Its syntax supports regular expressions. For example, "SYS.*" mean to exclude all schemas with name that starts with 'SYS'. 

### Crawler Inclusion / Exclusion List

The **data_platforms** section of the plugins.discovery file enables setting:

* The list of schema(s) and dataset(s) to be **excluded** from the Discovery job run.
* The list of schema(s) and dataset(s) to be **included** in the Discovery job run.

The syntax should provide either the schema name - ```<schema>``` - to be fully included (or excluded), or the comma-separated list of tables - ```<schema>.<table>``` or ```*.<table>```. 

**Example:**

~~~json
"data_platforms":{
    "AdventureWorks": {
       "include_list": ["Production"],
       "exclude_list": ["Production.Samples"]
    },
    "SF_DB": {
       "exclude_list": 	["SFORCE.APEXCLASS","SFORCE.APEXLOG","SFORCE.ASSETHISTORY"]
    }
}
~~~

The above configuration defines the following rules:

* The only schema to be included in the Discovery job on the AdventureWorks data platform is Production. However, the Samples table of this schema should be excluded.
* The tables APEXCLASS, APEXLOG and ASSETHISTORY should be excluded from the Discovery job on the SF_DB data platform. 

If the interface's driver supports wildcards (used in conjunction with the LIKE operator), they can be included in the ```<table>``` definition of the exclusion or inclusion lists. For example, the % symbol usually represents one or more characters in JDBC driver. Thus, writing ```<schema>.<ABC%>``` will define the datasets with name starting with 'ABC'.

### Plugin Threshold

Each plugin's definition in the plugins.discovery includes a **threshold**, which is the score above which the plugin result impacts the Catalog. When the threshold is **set to 0.4** and the rule receives a calculated score of 0.4 or below, this rule has no impact on the Catalog.

To enable the Catalog to show more results - update the threshold to a number lower than 0.4, and to show less results - update the threshold to a number higher than 0.4.

### Custom Plugins

The Plugin Framework supports the execution of custom plugins. In order to incorporate a custom plugin into the job, it needs to be added to the Plugins Pipeline's configuration file.

[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04a_builtin_plugins.md) 

