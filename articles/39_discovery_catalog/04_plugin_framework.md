# Plugin Framework

### Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Discovery Schema. 

The Plugin Framework is executed by the Crawler. It runs over the Discovery Schema and executes the plugins. A result of the plugin execution can be the creation (or removal) of various Catalog elements or properties. Each plugin calculates a score - a confidence level that the outcome is correct. The score is calculated per each data source element.

The Data Discovery solution includes a constantly growing list of built-in plugins. The list of active plugins and their execution order is set using the **plugins.discovery**, as described further in this article.

### Plugins Pipeline

**Configuration**

The plugin's execution order is defined by the Plugins Pipeline configuration file called **plugins.discovery**. This file is located in the Web Studio under the ```Implementation/SharedObjects/Interfaces/Discovery/``` folder and can be updated per your project's needs: a plugin can be set to inactive, the plugin's threshold can be updated or a custom plugin can be added to the **plugins.discovery**. Once any change is performed to the file, you need to rerun the Crawler to see the changes in the Catalog.

**Plugin's Threshold**

Each plugin's definition in the **plugins.discovery** includes a *threshold* - the score above which the plugin result impacts the Catalog. For example, when the plugin's threshold is set to 0.4, the plugin calculated results of 0.4 or below are dropped and will not be added to the Catalog. 

The threshold can be updated to either higher or lower value, depending on what results you expect to see in the Catalog. 

**Custom Plugins**

The Plugin Framework supports execution of custom plugins. In order to incorporate them into the process, these custom plugins need to be added to the Plugins Pipeline configuration file.

### Built-In Plugins

**Metadata Logical Reference**

The purpose of the *Metadata Logical Reference* plugin is to identify possible foreign key references between datasets and to create *refers to* relations. It is useful when for example a source doesn't have predefined foreign key relations. 

The matching algorithm works, each time, on comparing 2 field names of 2 different datasets. Prior to matching, formatting rules are applied in order to "normalize" the field names (remove the underscore ‘_’, convert to lower-case and consider the table name). 

For example, the following field names can be matched:

* CUSTOMER_ID and CustomerID
* CUSTOMER.ID and CustomerID

If a match is found, the plugin estimates the link direction – per the matching rule. The matching rules determine the link direction and the foreign key fields. Some examples of the matching rules are:

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 125px;">
<p><strong>Dataset 1</strong></p>
</td>
<td style="width: 125px;">
<p><strong>Dataset 2</strong></p>
</td>
<td style="width: 600px;">
<p><strong>Relation</strong></p>
</td>
<td style="width: 50px;">
<p><strong>Score</strong></p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>Field1&nbsp; PK</p>
</td>
<td style="width: 141.016px;">
<p>Field0&nbsp; PK</p>
<p>Field1 (not PK)</p>
</td>
<td style="width: 190.531px;">
<p><em>DS2 refers to DS1</em></p>
<p>PK table/columns: DS1 / Field1</p>
<p>FK table/columns: DS2 / Field1</p>
</td>
<td style="width: 49.4375px;">
<p>High</p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>Field1&nbsp; PK</p>
</td>
<td style="width: 141.016px;">
<p>Field1&nbsp; PK</p>
<p>Field2&nbsp; PK</p>
</td>
<td style="width: 190.531px;">
<p><em>DS2 refers to DS1</em></p>
<p>PK table/columns: DS1 / Field1</p>
<p>FK table/columns: DS2 / Field1</p>
</td>
<td style="width: 49.4375px;">
<p>High</p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>Field1&nbsp; PK</p>
<p>Field2&nbsp; (not PK)</p>
</td>
<td style="width: 141.016px;">
<p>Field1&nbsp; PK</p>
<p>Field2&nbsp; PK</p>
</td>
<td style="width: 190.531px;">
<p><em>DS2 refers to DS1</em></p>
<p>PK table/columns: DS1 / Field1, Field2</p>
<p>FK table/columns: DS2 / Field1, Field2</p>
</td>
<td style="width: 49.4375px;">
<p>High</p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>Field1&nbsp; PK</p>
<p>Field2&nbsp; PK</p>
</td>
<td style="width: 141.016px;">
<p>Field1&nbsp; PK</p>
<p>Field2&nbsp; PK</p>
<p>Field3&nbsp; PK</p>
</td>
<td style="width: 190.531px;">
<p><em>DS2 refers to DS1</em></p>
<p>PK table/columns: DS1 / Field1, Field2</p>
<p>FK table/columns: DS2 / Field1, Field2</p>
</td>
<td style="width: 49.4375px;">
<p>High</p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>Field1 is a single PK</p>
</td>
<td style="width: 141.016px;">
<p>Field1 is a single PK</p>
</td>
<td style="width: 190.531px;">
<p>Relation direction is random</p>
</td>
<td style="width: 49.4375px;">
<p>Low</p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>Field1 is not a PK</p>
</td>
<td style="width: 141.016px;">
<p>Field1 is not a PK</p>
</td>
<td style="width: 190.531px;">
<p>Relation direction is random</p>
</td>
<td style="width: 49.4375px;">
<p>Low</p>
</td>
</tr>
</tbody>
</table>


Eventually, the relation is created with a score - a probability that the match is correct. 

**Data Regex Classifier**

The purpose of *Data Regex Classifier* is to classify the source fields based on their **data**. This classification helps to identify which Catalog entities store sensitive information and should therefore be masked. 

The plugin runs on a data snapshot, extracted from the source, and executes the regular expressions defined in a built-in **data_profiling** MTable. 

If the field's data match a regex, a **Classification** property is added to the field's properties with a value such as **EMAIL**. If a match is found for more than one regex, only one property is created  - the one with higher score.

To update the data profiling rules, go to Actions > Classifier Configuration in the Catalog application. [Click for more details about the Classifier Configuration window](05_catalog_app.md#classifier-configuration-window).

**Metadata Regex Classifier**

The purpose of *Data Regex Classifier* is to classify the source fields based on their **metadata**. 

The matching rules are defined using regular expressions in a built-in **metadata_profiling** MTable. 

If the field's data match a regex, a **Classification** property is added to the field's properties with a value such as **NAME**. If a match is found for more than one regex, only one property is created  - the one with higher score.

To update the metadata profiling rules, go to Actions > Classifier Configuration in the Catalog application. [Click for more details about the Classifier Configuration window](05_catalog_app.md#classifier-configuration-window).

**Classification PII Marker**

The purpose of *Classification PII Marker* is to go over all fields which have got the **Classification** property (by either one of the above plugins)  and to add the **PII** property. 

The rules whether the classification type is defined as PII (true) or not (false) are defined in a built-in **pii_profiling** MTable. 

To update the PII profiling rules, go to Actions > Classifier Configuration in the Catalog application. [Click for more details about the Classifier Configuration window](05_catalog_app.md#classifier-configuration-window).

**NULL Percentage**

The purpose of this plugin is to check the % of null values per column, using the data snapshot. The nullability percentage is calculated on each column of non-empty tables. 

As a result, the **NULLABILITY_PERCENTAGE ** property is added to the field's properties list when its value is above the threshold. For example, when 30% of the values in a certain column are null, the NULLABILITY_PERCENTAGE will be equal to 0.3. 



[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04a_catalog_integration_with_fabric.md) 

