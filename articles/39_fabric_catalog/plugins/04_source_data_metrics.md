# Source Data Analysis

This article describes plugins that analyze source systems and calculate various metrics. The analysis is done based on data snapshots.

The plugins are:

* [Empty Datasets Discard](04_source_data_metrics.md#empty-datasets-discard) — excludes empty tables based on the data snapshot results. This plugin is available starting from Fabric V8.3.1.
* [Data Quality Metrics](04_source_data_metrics.md#data-quality-metrics) — calculates various data quality metrics as described below. These metrics can then be applied for data masking and synthetic data generation.
* [Option Set Analyzer](04_source_data_metrics.md#option-set-analyzer) — identifies fields that contain a limited number of distinct values (within a data sample) and saves them in an MTable. These metrics can then be applied for data masking and synthetic data generation. This plugin is available starting from Fabric V8.3.

All of the above plugins are inactive by default and must be activated through the Discovery Pipeline if needed. 

## Empty Datasets Discard

Some data sources may contain a large number of empty tables that are irrelevant for the Catalog and for further LU creation. 

Accordingly, the purpose of this plugin is to improve the Catalog usability as well as the LU development process. When activated, the plugin automatically **discards** all empty tables during the Discovery job, writing a message in the Fabric log (one message per schema):

~~~
"<num> empty datasets were removed from schema <schema name>"
~~~

The Catalog schema is then created without the discarded tables.

## Data Quality Metrics

This plugin scans the data of the data sample in order to calculate various data quality metrics. These metrics can then be used for masking and synthetic data generation.

* **Data Sample Size** — the actual number of values per column in the data sample.
  * The data sample is retrieved per the Catalog settings. For example, the default sample is 10% of the table size, with a minimum of 100 and a maximum of 500. However, the actual size of the data sample can vary depending on the table size.
* **Distinct Values** — the count of distinct values per column in the data sample. 
  * This parameter helps to assess the variety or uniqueness of data within a column.  
  * In addition, it can help to validate whether the data values are within an acceptable or predefined range. For example, if a column is expected to store binary values (Yes/No or true/false), the presence of additional distinct values may indicate data quality issues. 
  * A high number of distinct values in a column, where fewer unique entries are expected, may indicate potential data anomalies, typographical errors, or other issues. 
  * This calculation is performed for alphanumeric and numeric fields (strings, integers and real numbers).
* **Minimum Value**, **Maximum Value**, **Average**, and **Standard Deviation** are basic statistical calculations performed on numeric or date columns in the data sample.
  * Establishing the existing range of values in the data can assist in verifying whether these values fall within expected or acceptable limits. This can help reveal potential errors, such as outliers or incorrect data entries (e.g., a negative age value).
  * Understanding the range of values helps ensure consistency across similar datasets. It can also support business decisions-making by providing insights into variability and distribution. 
  * As part of basic descriptive statistics, these metrics offer an initial insight into data distribution and can serve as a precursor to more advanced statistical analyses.
* **Null Percentage** — the percentage of null values per column. 
  * This percentage is calculated for each column in non-empty tables. The **Null Percentage** property is added to the field's properties when the calculated value exceeds the plugin's threshold. 
  * For example, when 30% of the values in a given field are null, the Null Percentage property will be added to the field with the value = 0.3. However, if 20% or fewer of the values in this field are null, then this property will not be added.


## Option Set Analyzer

The purpose of this plugin is to identify fields with a limited number of distinct values **in the data sample** and save these values in a dedicated MTable, enabling their use in masking and synthetic data generation.

In addition, when running discovery on JSON Schema files (using the [File Cataloging solution](/articles/39_fabric_catalog/05_cataloging_of_files.md)), the plugin can identify fields with `enum` property and save the values of `enum` in a dedicated MTable, enabling their use in masking and synthetic data generation. Note that in this case, the distinct values are not related to the data sample but simply taken from the 'enum' property values. This addition is available starting from Fabric V8.4.

Once a field is identified as an Option Set, the property ```optionSet = true``` is created for it. A separate MTable is generated for each data platform and schema to store the distinct values (and their distribution) identified by the plugin in a field. The MTable has the following format: 

```catalog_field_option_set___<dataPlatform>_<schema>.csv```, (containing three underscores before the data platform name).

The below image is an example of such MTable:

<img src="../images/option_set_mtable_ex.png" />

Starting from Fabric V8.3.1, the **OPTION_SET** classification is assigned to this field, unless it has already been classified. In the Catalog Settings, the OPTION_SET classification is mapped to the **RandomOptionSet** Actor for masking and synthetic data generation. The actor randomly selects a value from the catalog_field_option_set MTable, based on the input data platform, schema, dataset, class and field.

To identify fields with limited distinct values, consider these guidelines:

1. Ensure the field is non-PII to comply with privacy regulations and avoid the exposure of sensitive data. 

2. The number of distinct values should be below either a plugin’s threshold (e.g., 0.05) or the Absolute Threshold input parameter, which defaults to 15.


Additional rules apply based on the **plugin input parameters**, as explained below.

#### Property Name

The property that will be created on a field if the plugin returns true. By default, the property is named `optionSet`.

#### Absolute Threshold

This parameter defines the absolute threshold number of distinct values. If the relative number of distinct values per field, found in a data sample, exceeds the plugin’s threshold (0.05), it is then validated against the absolute threshold (15). For example:

* The sample size is 100 and a field includes 10 distinct values, thus the proportion of distinct values equals to 0.1. This figure exceeds the plugin's threshold (0.05).
* In this case, the result is validated against the absolute threshold to verify whether it qualifies as an **Option Set**. 
* Since 10 distinct values are below the absolute threshold (15), the field qualifies as an **Option Set**.

#### Field Type Include List

This parameter controls which field data types are considered when checking for distinct values. 

By default, ```fieldTypeIncludeList``` is set to STRING and INTEGER. The valid values are STRING, INTEGER, REAL, DATETIME, DATE and BOOLEAN.

#### Field Name Include List

This parameter allows to set up an override list of field names. These fields will be included in the plugin's validation algorithm, even if they are identified as PII or belong to a small table (see the ```minSampleSize``` property).

#### Field Name Exclude List

This parameter allows to set up an override list of field names. These fields will be excluded from the plugin's validation algorithm.

#### Incremental Mode

This parameter is introduced in Fabric V8.3.1. It defines whether the Option Set Analyzer plugin should be executed for the fields that already have the same property created by this plugin in a previous Discovery Job execution. It has the following modes:

- ```"Keep All"``` (default) — if the plugin has already been executed for this field in a previous Discovery Job execution, do not invoke the plugin again (even if the field does not have the 'Option Set' property). The plugin will only be invoked for new fields.
- ```"Keep Existing"``` — if the plugin has already been executed for this field in a previous Discovery Job execution and created a property, do not invoke it again. The plugin will only be invoked for new fields and for the fields without this property.
- ```"Evaluate All"``` — the plugin will be invoked for all fields.

#### Max String Length

This parameter sets a limit on STRING size to prevent handling text files or complex structures within a field. The default value is 512 bytes.

#### Min Sample Size

This parameter allows to skip small tables by defining the minimum sample size required to determine whether a field qualifies as an **Option Set**. The default value is 100.

## 
