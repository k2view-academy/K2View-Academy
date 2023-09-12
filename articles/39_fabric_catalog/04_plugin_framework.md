<web>

# Plugin Framework

### Overview

The Plugin Framework is an internal platform for running the plugins. Each plugin is a piece of business logic that executes predefined rules in order to complement the Discovery schema. 

The Plugin Framework is executed by the Discovery job after completion of the Crawler. It runs over the Discovery schema and executes the plugins. The result of the plugin execution is Catalog elements creation or removal. Each plugin calculates a score - a confidence level that the plugin result is correct. The score is calculated per each Catalog element.

The Data Discovery solution includes a constantly growing list of built-in plugins. The list of active plugins and their execution order is configured using the **plugins.discovery**, as described further in this article.

### Plugins Pipeline

**Configuration**

The **plugins.discovery** is the configuration file of the Plugins Pipeline process. This file is located in the Web Studio under the ```Implementation/SharedObjects/Interfaces/Discovery/``` folder.

The plugins.discovery configuration file defines a list of active plugins and their execution order. It also defines the data sample size. This file can be updated per your project's requirement. 

Once the plugins.discovery configuration file is updated, the Discovery job should be rerun, applying the changes on the Catalog.

**Data Sample Settings**

The data sample is retrieved from the data source during the Discovery job run. The data is encrypted and is used by the various plugins during the job run. Once the plugins execution has been completed, the data sample is deleted.

The sample size is configured in the plugins.discovery file as follows:

* The default sample size is 10% of the dataset rows.
* Min=100 and max=10000 definitions are set in order to accommodate for very small and very large datasets. This means that the sample size can’t be lower than MIN (100 rows) or higher than MAX (10000 rows) per each dataset.

**Plugin's Threshold**

Each plugin's definition in the plugins.discovery includes a *threshold* - the score above which the plugin result impacts the Catalog and its display. When the plugin's threshold is set to 0.4, the plugin-calculated results of 0.4 or below are dropped and are not added to the Catalog. To enable the Catalog to show more results, update the threshold to a number lower than 0.4, and vice versa, to show less results, update the threshold to a higher number.

**Custom Plugins**

The Plugin Framework supports execution of custom plugins. In order to incorporate a custom plugin into the process, it needs to be added to the Plugins Pipeline configuration file.

### Built-In Plugins

**Metadata Logical Reference**

The purpose of a *Metadata Logical Reference* plugin is to identify possible foreign key references between datasets and to create *refers to* relations. This plugin is useful in a case where a source doesn't have predefined foreign key constraints.

The matching algorithm works by comparing 2 field names of 2 different datasets at a time. Prior to the matching, the field names are normalized using the following formatting rules: underscore ‘_’ removal, conversion to lowercase letters and addition of a table name if the field name is ID.

For example, the following field names will be matched: customer.ID, CUSTOMER_ID and CustomerID.

This plugin includes a blacklist of field names (e.g., 'username' or 'age') and a blacklist of field types (e.g., date, time, blob) to be excluded from the matching algorithm. These blacklists are defined in the plugins.discovery file as plugin input parameters, and they can be updated on a project level.

If a match is found, the plugin evaluates both the relation direction and the foreign key fields using the matching rule. The *refers to* relation direction is Many-to-One. The relation is created with a score - a probability that the match is correct. The following matching rules are defined in the plugins.discovery file and are applied by the plugin:

* **field_name_is_id_and_pk** - Dataset1 has a PK field **id** and dataset2 has a field **dataset1id** (normalized).
  * The relation *dataset2 refers to dataset1* is created and its score is 0.8.
  * Example: *customer.ID (PK) and* *activity.customer_id*
* **field_name_is_id_and_not_pk** - Dataset1 has a non-PK field **id** and dataset2 has a field **dataset1id** (normalized).
  * The relation *dataset2 refers to dataset1* is created and its score is 0.6.
  * Example: *customer.ID (non-PK) and* *activity.customer_id*
* **single_field_pk_and_not_pk** - Dataset1 has a PK field **id** and dataset2 has a field with the same name (normalized), non-PK.
  * The relation *dataset2 refers to dataset1* is created and its score is 0.8.
  * Example: *customer.customer_id (PK) and* *activity.customer_id* 
* **common_fields_in_both_pk** - Common fields which a part of PK in both datasets, but dataset1 has less PKs than dataset2.
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

* **same_field_names_pk** - Common fields, a part of PK in both datasets, and both datasets have identical number of PKs.
  * The relation is created and its direction is random. The score is 0.4.
* **same_field_names_not_pk** - Both datasets have fields with the same names (normalized, not in *field_name_blk*), both are non-PK.
  * The relation is created and its direction is random. The score is 0.2.

**Data Regex Classifier**

The purpose of *Data Regex Classifier* plugin is to classify the source fields based on their **data**. This classification helps to identify which Catalog entities store sensitive information and should therefore be masked. 

This plugin runs on a data snapshot that is extracted from the source, and it executes the regular expressions defined in a built-in **data_profiling** MTable.

If a regular expression (aka regex) matches the field's data, a Classification property is added to the field with a value corresponding to the matching regex (e.g., EMAIL). If a match is found for more than one expression, the property is created with the Classification that has a higher score. 

To update the data profiling rules, go to Actions > [Classifier Configuration](05_catalog_app.md#classifier-configuration-window) in the Catalog application. 

**Metadata Regex Classifier**

The purpose of *Metadata Regex Classifier* plugin is to classify the source fields based on their **metadata**. 

The matching rules are defined using regular expressions in a built-in **metadata_profiling** MTable. 

If a regular expression (aka regex) matches the field's data, a Classification property is added to the field with a value corresponding to the matching regex (e.g., NAME). If a match is found for more than one expression, the property is created with the Classification that has a higher score.

To update the metadata profiling rules, go to Actions > [Classifier Configuration](05_catalog_app.md#classifier-configuration-window) in the Catalog application. 

**Classification PII Marker**

The purpose of *Classification PII Marker* plugin is to go over all the fields that have got the **Classification** property (by either one of the above plugins) and to add the **PII** property. 

The rules as to whether the classification type is considered a PII are defined in a built-in **pii_profiling** MTable. 

To update the PII indicator of the profiling rules, go to Actions > [Classifier Configuration](05_catalog_app.md#classifier-configuration-window) in the Catalog application. 

**NULL Percentage**

The purpose of this plugin is to check the % of null values per column, using the data snapshot. The nullability percentage is calculated on each column of non-empty tables. 

As a result, the **Null Percentage** property is added to the field's properties when the calculated value is above the threshold. 

For example, when 30% of the values in a certain field are null, the Null Percentage property will be added to this field with the value = 0.3. However, if 20% or less of the values in this field are null, then this property would not be added.



[![Previous](/articles/images/Previous.png)](03_discovery_process.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04a_catalog_integration_with_fabric.md) 

</web>
