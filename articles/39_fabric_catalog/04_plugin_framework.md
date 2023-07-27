<web>

# Plugin Framework

### Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Discovery schema. 

The Plugin Framework is executed by the the Discovery job after the Crawler completion. It runs over the Discovery Schema and executes the plugins. A result of the plugin execution is the creation or removal of the Catalog elements. Each plugin calculates a score - a confidence level that the outcome is correct. The score is calculated per each Catalog element.

The Data Discovery solution includes a constantly growing list of built-in plugins. The list of active plugins and their execution order is set using the **plugins.discovery**, as described further in this article.

### Plugins Pipeline

**Configuration**

The plugin's execution order is defined by the Plugins Pipeline configuration file called **plugins.discovery**. This file is located in the Web Studio under the ```Implementation/SharedObjects/Interfaces/Discovery/``` folder and can be updated per your project's needs: a plugin can be set to inactive, the plugin's threshold can be updated or a custom plugin can be added to the **plugins.discovery**. Once any change is performed to the file, the Discovery job should be rerun in order to apply the changes on the Catalog.

**Plugin's Threshold**

Each plugin's definition in the plugins.discovery includes a *threshold* - the score above which the plugin result impacts the Catalog. For example, when the plugin's threshold is set to 0.4, the plugin calculated results of 0.4 or below are dropped and will not be added to the Catalog. The threshold can be updated if needed. 

**Custom Plugins**

The Plugin Framework supports execution of custom plugins. In order to incorporate them into the process, these custom plugins need to be added to the Plugins Pipeline configuration file.

### Built-In Plugins

**Metadata Logical Reference**

The purpose of the *Metadata Logical Reference* plugin is to identify possible foreign key references between datasets and to create *refers to* relations. It is useful when for example a source doesn't have predefined foreign key relations. 

The matching algorithm works, each time, on comparing 2 field names of 2 different datasets. Prior to matching, the field names are "normalized" using the following formatting rules: remove the underscore ‘_’, convert to lower-case and add the table name if the field name is ID. 

For example, the following field names can be matched by the plugin:

* CUSTOMER_ID and CustomerID
* CUSTOMER.ID and CustomerID

The plugin includes a blacklist of field names (e.g. 'username' or 'age') and a blacklist of field types (e.g. date, time, blob) to be excluded from the matching algorithm. These blacklists are defined in the plugins.discovery file as plugin input parameters and can be updated on project level.

If a match is found, the plugin estimates the relation direction and the foreign key fields using the matching rule. The *refers to* relation direction is Many-to-One. The relation is created with a score - a probability that the match is correct. Some examples of the matching rules are:

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 125px;" colspan="2"><strong>Input: a pair of datasets</strong></td>
<td style="width: 650px;" colspan="2">
<p><strong>Output: relation created by plugin</strong></p>
</td>
</tr>
<tr>
<td style="width: 125px;">
<p><strong>DS1</strong></p>
</td>
<td style="width: 125px;">
<p><strong>DS2</strong></p>
</td>
<td style="width: 600px;">
<p><strong>Relation Direction and FK</strong></p>
</td>
<td style="width: 50px;">
<p><strong>Score</strong></p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>field_1&nbsp; PK</p>
</td>
<td style="width: 141.016px;">
<p>field_0&nbsp; PK</p>
<p>field_1 (not PK)</p>
</td>
<td style="width: 190.531px;">
<p><em>DS2 refers to DS1</em></p>
<p>Foreign Key: field_1</p>
</td>
<td style="width: 49.4375px;">
<p>High</p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>field_1 PK</p>
</td>
<td style="width: 141.016px;">
<p>field_1 PK</p>
<p>field_2&nbsp; PK</p>
</td>
<td style="width: 190.531px;">
<p><em>DS2 refers to DS1</em></p>
<p>Foreign Key: field_1</p>
</td>
<td style="width: 49.4375px;">
<p>High</p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>field_1 PK</p>
<p>field_2&nbsp; (not PK)</p>
</td>
<td style="width: 141.016px;">
<p>field_1 PK</p>
<p>field_2&nbsp; PK</p>
</td>
<td style="width: 190.531px;">
<p><em>DS2 refers to DS1</em></p>
<p>Foreign Key: field_1, field_2</p>
</td>
<td style="width: 49.4375px;">
<p>High</p>
</td>
</tr>
<tr>
<td style="width: 141.016px;">
<p>field_1 is a single PK</p>
</td>
<td style="width: 141.016px;">
<p>field_1 is a single PK</p>
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
<p>field_1 is not a PK</p>
</td>
<td style="width: 141.016px;">
<p>field_1 is not a PK</p>
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



**Data Regex Classifier**

The purpose of *Data Regex Classifier* is to classify the source fields based on their **data**. This classification helps to identify which Catalog entities store sensitive information and should therefore be masked. 

The plugin runs on a data snapshot, extracted from the source, and executes the regular expressions defined in a built-in **data_profiling** MTable. 

If the regex matches the field's data, a **Classification** property is added to the field's properties with a value corresponding the matched regex (e.g. **EMAIL**). If a match is found for more than one regex, only one property is created  - the one with higher score.

To update the data profiling rules, go to Actions > Classifier Configuration in the Catalog application. 

[Click for more details about the Classifier Configuration window](05_catalog_app.md#classifier-configuration-window).

**Metadata Regex Classifier**

The purpose of *Metadata Regex Classifier* is to classify the source fields based on their **metadata**. 

The matching rules are defined using regular expressions in a built-in **metadata_profiling** MTable. 

If the regex matches the field's data, a **Classification** property is added to the field's properties with a value corresponding the matched regex (e.g. **NAME**). If a match is found for more than one regex, only one property is created  - the one with higher score.

To update the metadata profiling rules, go to Actions > Classifier Configuration in the Catalog application. 

[Click for more details about the Classifier Configuration window](05_catalog_app.md#classifier-configuration-window).

**Classification PII Marker**

The purpose of *Classification PII Marker* is to go over all fields which have got the **Classification** property (by either one of the above plugins)  and to add the **PII** property. 

The rules whether the classification type is considered a PII are defined in a built-in **pii_profiling** MTable. 

To update the PII indicator of the profiling rules, go to Actions > Classifier Configuration in the Catalog application. 

[Click for more details about the Classifier Configuration window](05_catalog_app.md#classifier-configuration-window).

**NULL Percentage**

The purpose of this plugin is to check the % of null values per column, using the data snapshot. The nullability percentage is calculated on each column of non-empty tables. 

As a result, the **Nullability Percentage** property is added to the field's properties when the its calculated value is above the threshold. 

For example, when 30% of the values in a certain field are null, the Nullability Percentage property will be added to this field with the value = 0.3. But when it's 20% or less, this property will not be added.



[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04a_catalog_integration_with_fabric.md) 

</web>
