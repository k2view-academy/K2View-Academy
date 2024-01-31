<web>

# Plugin Framework

### Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Catalog schema. 

The Plugin Framework is executed by the Discovery job after completion of the Crawler. It runs over the Catalog schema and executes the plugins. If the plugin’s rule returns 'true', it can result with a change to the Catalog schema, such as creation or removal of Catalog elements. Each plugin calculates a score - a confidence level of a plugin result's accuracy. The score is calculated per each Catalog element.

The Data Discovery solution includes a constantly growing list of built-in plugins. 

This article describes the main configuration parameters of the **plugins.discovery** file. The list of active plugins, their execution order and configuration parameters are described in the [next article](04a_builtin_plugins.md).

### Plugins Pipeline

The plugins.discovery is the configuration file of the Plugins Pipeline process. This file is located in the Web Studio under the ```Implementation/SharedObjects/Interfaces/Discovery/``` folder.

The plugins.discovery configuration file includes the settings of the Discovery job such as:

* Data sample settings
* Data platform's include / exclude lists
* List of active plugins, their threshold and the execution order

This file can be updated per your project's requirements. Once the plugins.discovery configuration file is updated, the Discovery job should be rerun, applying the changes on the Catalog.

### Data Sample Settings

The data sample is retrieved from the data source during the Discovery job run. The data is encrypted and is being used by the various plugins during the job run. Once the plugins' execution has been completed, the data sample is deleted.

The sample size is configured in the [sample_size] section of the plugins.discovery file as follows:

* The default sample size is 10% of the dataset rows.
* Min=100 and max=10000 definitions are set in order to accommodate for very small and very large datasets. This means that the sample size can’t be lower than MIN (100 rows) or higher than MAX (10000 rows) per each dataset.

### Crawler Inclusion / Exclusion List

The [data_platforms] section of the plugins.discovery file enables setting:

* The list of schema(s) and dataset(s) to be **excluded** from the Discovery job run.
* The list of schema(s) and dataset(s) to be **included** in the Discovery job run.

The syntax should provide either the schema name - ```<schema>``` - to be fully included (or excluded), or the comma-separated list of tables - ```<schema>.<table>``` or ```*.<table>```. 

If the interface's driver supports wildcards (used in conjunction with the LIKE operator), they can be included in the ```<table>``` definition of the exclude or include lists. For example, the % symbol usually represents one or more characters in JDBC driver. Thus, writing ```<schema>.<ABC%>``` will define the datasets with name starting with 'ABC'.

**Example:**

~~~json
"data_platforms":{
    "AdventureWorks": {
       "include_list": ["Production.Product%"]
    },
    "SF_DB": {
       "exclude_list": ["SFORCE.APEXCLASS","SFORCE.APEXLOG","SFORCE.ASSETHISTORY"]
    }
}
~~~



The above configuration defines the following rules:

* The only schema to be included in the Discovery Job on the AdventureWorks data platform is Production. And it should only include the datasets that start with 'Product'.
* The tables APEXCLASS, APEXLOG and ASSETHISTORY should be excluded from the Discovery Job on the SF_DB data platform. 

### Plugin Threshold

Each plugin's definition in the plugins.discovery includes a **threshold** - the score above which the plugin result impacts the Catalog. When the threshold is set to 0.4 and the rule receives a calculated score of 0.4 or below, this rule has no impact on the Catalog.

To enable the Catalog to show more results - update the threshold to a number lower than 0.4, and to show less results - update the threshold to a higher number.

### Custom Plugins

The Plugin Framework supports execution of custom plugins. In order to incorporate a custom plugin into the process, it needs to be added to the Plugins Pipeline configuration file.

[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04a_builtin_plugins.md) 

</web>
