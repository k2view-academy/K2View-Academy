<web>

# Plugin Framework

## Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Catalog schema. 

The Plugin Framework is executed by the Discovery job after completion of the Crawler. It runs over the Catalog schema and executes the plugins. If the plugin’s rule returns true, it can result with a change to the Catalog schema, such as creation or removal of Catalog elements. Each plugin calculates a score - a confidence level of a plugin result's accuracy. The score is calculated per each Catalog element.

The Data Discovery solution includes a constantly growing list of built-in plugins. The list of active plugins and their execution order is configured using the **plugins.discovery**, as described further in this article.

## Plugins Pipeline

The **plugins.discovery** is the configuration file of the Plugins Pipeline process. This file is located in the Web Studio under the ```Implementation/SharedObjects/Interfaces/Discovery/``` folder.

The plugins.discovery configuration file includes the settings of the Discovery job such as:

* Data sample size
* Data platform's include / exclude lists
* List of active plugins and their execution order

This file can be updated per your project's requirements. Once the plugins.discovery configuration file is updated, the Discovery job should be rerun, applying the changes on the Catalog.

**Data Sample Settings**

The data sample is retrieved from the data source during the Discovery job run. The data is encrypted and is used by the various plugins during the job run. Once the plugins execution has been completed, the data sample is deleted.

The sample size is configured in the plugins.discovery file as follows:

* The default sample size is 10% of the dataset rows.
* Min=100 and max=10000 definitions are set in order to accommodate for very small and very large datasets. This means that the sample size can’t be lower than MIN (100 rows) or higher than MAX (10000 rows) per each dataset.

**Data Platforms Exclusion List**

The data_platforms section of the plugins.discovery configuration file enables setting:

* The **exclude_list** - the list of schema(s) and dataset(s) to be excluded from the Discovery job run.
* The **include_list** - the list of schema(s) and dataset(s) to be included in the Discovery job run.

The syntax is to either provide the schema name - ```<schema>``` - to be fully included (or excluded), or the coma separated list of ```<schema>.<table>``` or ```*.<table>```.

## Plugin Threshold

Each plugin's definition in the plugins.discovery includes a *threshold* - the score above which the plugin result impacts the Catalog. When the threshold is set to 0.4 and the rule receives a calculated score of 0.4 or below, this rule has no impact on the Catalog. See the detailed example further in this article. 

To enable the Catalog to show more results, update the threshold to a number lower than 0.4, and vice versa, to show less results, update the threshold to a higher number.

## Built-In Plugins

### Metadata Logical Reference

The purpose of a *Metadata Logical Reference* plugin is to identify possible foreign key references between datasets and to create *refers to* relations. This plugin is useful in a case where a source doesn't have predefined foreign key constraints.

The matching algorithm works by comparing the field names of 2 different datasets at a time. Prior to the matching, the field names are normalized using the following formatting rules: underscore ‘_’ removal, conversion to lowercase letters and addition of a table name if the field name is ID.

For example, the following field names will be matched: customer.ID, CUSTOMER_ID and CustomerID.

This plugin includes a blacklist of field names (e.g., 'username' or 'age') and a blacklist of field types (e.g., date, time, blob) to be excluded from the matching algorithm. These blacklists are defined in the plugins.discovery file as plugin input parameters, and they can be updated on a project level.

If a match is found, the plugin evaluates both the relation direction and the foreign key fields using the matching rule. The *refers to* relation direction is Many-to-One. The relation is created with a score - a probability of the match's correctness. 

#### Matching Rules

The following matching rules are defined in the plugins.discovery file and are applied by the plugin:

* **field_name_is_id_and_pk** - Dataset1 has a PK field **id** and dataset2 has a field **dataset1id** (normalized).
  
  * The relation *dataset2 refers to dataset1* is created and its score is 0.8.
  * Example: *customer.ID (PK) and* *activity.customer_id*
* **field_name_is_id_and_not_pk** - Dataset1 has a non-PK field **id** and dataset2 has a field **dataset1id** (normalized).
  * The relation *dataset2 refers to dataset1* is created and its score is 0.6.
  * Example: *customer.ID (non-PK) and* *activity.customer_id*
* **single_field_pk_and_not_pk** - Dataset1 has a PK field **id** and dataset2 has a field with the same name (normalized), non-PK.
  * The relation *dataset2 refers to dataset1* is created and its score is 0.8.
  * Example: *customer.customer_id (PK) and* *activity.customer_id* 
* **common_fields_in_both_pk** - Common fields that are part of the PK in both datasets, but where dataset1 has less PKs than dataset2.
  * The relation *dataset2 refers to dataset1* is created and its score is 0.8.
  * Some examples of the matching rules are:

    <table style="width: 900px;">
    <tbody>
    <tr>
    <td style="width: 125px;" colspan="2"><strong>Input: 2 Datasets</strong></td>
    <td style="width: 650px;" colspan="2">
    <p><strong>Output: Relation created by plugin</strong></p>
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
    <p><strong>Relation direction and FK</strong></p>
    </td>
    <td style="width: 50px;">
    <p><strong>Score</strong></p>
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
    <p>FK: DS2 (field_1)</p>
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
    <p>FK: DS2 (field_1, field_2)</p>
    </td>
    <td style="width: 49.4375px;">
    <p>High</p>
    </td>
    </tr>
    </tbody>
    </table>

* **same_field_names_pk** - Common fields that are part of the PK in both datasets, and where both datasets have identical number of PKs.
  * The relation is created and its direction is random. The score is 0.4.
* **same_field_names_not_pk** - Both datasets have fields with the same names (normalized, not in *field_name_blk*), both are non-PK.
  * The relation is created and its direction is random. The score is 0.2.

#### Field Exclusion List

Fields can be excluded from the *Metadata Logical Reference* plugin's matching algorithm by their name or type. The exclusion list can be defined using the **field_name_exclude_list** and **field_type_exclude_list** arrays in the plugin's input parameters definition of the plugins.discovery configuration file. This can be useful when, for example, the same field name exists in many datasets of the same schema and this field should not be part of the *refers to* relation, e.g., lastModifiedDate.

### Data Regex Classifier

The purpose of *Data Regex Classifier* plugin is to classify the source fields based on their data - field value. This classification helps to identify which Catalog entities store sensitive information and should therefore be masked. 

This plugin runs on a data snapshot that is extracted from the source, and it executes the regular expressions defined in a built-in **data_profiling** MTable.

If a regular expression (aka regex) matches the field's data, a Classification property is added to the field with a value corresponding to the matching regex (e.g., EMAIL). If a match is found for more than one expression, the property is created with the Classification that got a higher calculated score. 

To update the data profiling rules, go to the [Catalog Settings > Classifier Regex Setup tab](10_catalog_settings.md#classifier-regex-setup).

**Example:**

The below regular expression ```\b(?:\d[ -]*?){13,16}\b``` is executed on the field's values:

![](images/regex_example.png)

When the expression matches a field's value, the probability that this field holds a credit card number is 0.8. So, in case of a match, the score is 0.8 and when there is no match, the score is 0. The expression is executed on all values on the given column in the data sample and the average score is calculated. Then, the calculated average score is compared with plugin's threshold as explained earlier in this article. If the calculated average score is above the threshold, the Classification = CREDIT_CARD property is added to the field.

### Metadata Regex Classifier

The purpose of *Metadata Regex Classifier* plugin is to classify the source fields based on their metadata - field name. 

The matching rules are defined using regular expressions in a built-in **metadata_profiling** MTable. 

If a regular expression (aka regex) matches the field's name, a Classification property is added to the field with a value corresponding to the matching regex (e.g., SOCIAL_SECURITY_NUMBER). If a match is found for more than one expression, the property is created with the Classification that has the highest score.

<img src="images/field_classification.png" style="zoom: 67%;" />

To update the metadata profiling rules, go to the [Catalog Settings > Classifier Regex Setup tab](10_catalog_settings.md#classifier-regex-setup).

#### Field Exclusion List

Fields can be excluded from the *Metadata Regex Classifier* plugin's logic by their name or type. This can be useful when, for example, you need to exclude all fields with certain name or name pattern from the classification process. 

The exclusion list can be defined using the **field_name_exclude_list** and **field_type_exclude_list** arrays in the plugin's input parameters definition of the plugins.discovery configuration file. The **field_name_exclude_list** definition can be either the exact field name or a regular expression.

**Example:**

~~~json
"input_parameters": {
	"field_name_exclude_list": [
					"^(?i)[a-z]+_?ID$",
        			"name"
	],
	"field_type_exclude_list": [
					"boolean"
	]
}
~~~

### Classification PII Marker

The purpose of *Classification PII Marker* plugin is to go over all the fields that have got the **Classification** property (by either one of the above plugins) and to add the **PII** property. 

The rules as to whether the classification type is considered a PII are defined in a built-in **pii_profiling** MTable. 

To update the Classification's PII indicator, go to the [Catalog Settings > Classifier PII & Masking Setup](10_catalog_settings.md#classifier-pii--masking-setup). 

#### Field Exclusion List

Fields can be excluded from the *Classification PII Marker* plugin's logic by their name or type. This can be useful when, for example, you need to exclude all fields with certain name or name pattern from the PII marking process. 

The exclusion list can be defined using the **field_name_exclude_list** and **field_type_exclude_list** arrays in the plugin's input parameters definition of the plugins.discovery configuration file. The **field_name_exclude_list** definition can be either the exact field name or a regular expression.

**Example:**

~~~json
"input_parameters": {
	"field_name_exclude_list": [
					"^(?i)[a-z]+_?ID$",
        			"name"
	],
	"field_type_exclude_list": [
					"boolean"
	]
}
~~~

### NULL Percentage

The purpose of this plugin is to calculate the percentage of NULL values per column, based on the data snapshot. This percentage is calculated on each column of non-empty tables. The default size of the data snapshot is configured in the plugins.discovery file as explained earlier in this article.

As a result, the **Null Percentage** property is added to the field's properties when the calculated value is above the threshold. 

For example, when 30% of the values in a certain field are null, the Null Percentage property will be added to this field with the value = 0.3. However, if 20% or less of the values in this field are null, then this property would not be added.

### Custom Plugins

The Plugin Framework supports execution of custom plugins. In order to incorporate a custom plugin into the process, it needs to be added to the Plugins Pipeline configuration file.

### 

[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04a_catalog_integration_with_fabric.md) 

</web>
