# Source Data Analysis

This article describes plugins that analyze source systems and calculate various metrics. The analysis is done based on data snapshots.

The plugins are:

* [Empty Datasets Discard](04_source_data_metrics.md#empty-datasets-discard) — excludes empty tables based on the data snapshot results. This plugin is available starting from Fabric V8.3.1.
* [Data Quality Metrics](04_source_data_metrics.md#data-quality-metrics) — calculates various data quality metrics as described below. These metrics can then be applied for data masking and synthetic data generation. This plugin has been enhanced in Fabric V8.5, as described further in this article. 
* [Option Set Analyzer](04_source_data_metrics.md#option-set-analyzer) — identifies fields that contain a limited number of distinct values (within a data sample) and saves them in an MTable. These metrics can then be applied for data masking and synthetic data generation. This plugin is available starting from Fabric V8.3.

All of the above plugins are inactive by default and must be activated through the Discovery Pipeline if needed. 

## Empty Datasets Discard

Some data sources may contain a large number of empty tables that are irrelevant for the Catalog and for further LU creation. 

This plugin improves both Catalog usability and the LU development process. When activated, the plugin automatically **discards** all empty tables during the Discovery job, writing a message in the Fabric log (one message per schema):

~~~
"<num> empty datasets were removed from schema <schema name>"
~~~

The Catalog schema is then created without the discarded tables.

## Data Quality Metrics

This plugin scans the data sample to calculate various data quality metrics. These metrics can then be used for masking and synthetic data generation.

* **Data Sample Size** — the actual number of values per column in the data sample.
  * The data sample is retrieved per the Catalog settings. For example, the default sample is 10% of the table size, with a minimum of 100 and a maximum of 500. However, the actual size of the data sample can vary depending on the table size.
* **Distinct Values** — the count of distinct values per column in the data sample. 
  * This parameter helps to assess the variety or uniqueness of data within a column.  
  * In addition, it can help to validate whether the data values are within an acceptable or predefined range. For example, if a column is expected to store binary values (Yes/No or true/false), the presence of additional distinct values may indicate data quality issues. 
  * A high number of distinct values in a column, where fewer unique entries are expected, may indicate potential data anomalies, typographical errors, or other issues. 
  * This calculation is performed for strings, integers and real numbers.
* **Minimum Value**, **Maximum Value**, **Average**, and **Standard Deviation** are basic statistical calculations performed on numeric or date columns in the data sample.
  * Establishing the existing range of values in the data can assist in verifying whether these values fall within expected or acceptable limits. This can help reveal potential errors, such as outliers or incorrect data entries (e.g., a negative age value).
  * Understanding the range of values helps ensure consistency across similar datasets. It can also support business decision-making by providing insights into variability and distribution. 
  * As part of basic descriptive statistics, these metrics offer an initial insight into data distribution and can serve as a precursor to more advanced statistical analyses.
* **Null Percentage** — the percentage of null values per column. 
  * This percentage is calculated for each column in non-empty tables. The **Null Percentage** property is added to the field's properties when the calculated value exceeds the plugin's threshold. 
  * For example, when 30% of the values in a given field are null, the Null Percentage property will be added to the field with the value = 0.3. However, if 20% or fewer of the values in this field are null, then this property will not be added.


Starting with Fabric V8.5, the following enhancements have been added to the plugin algorithm:

* **Dataset Row Count** is calculated for each dataset. It shows the number of rows in the source dataset (**not** in the data sample). 
  * This property is created on **the first column of the dataset**, only when the source dataset contains data.

* When a field is marked as PII, the Minimum and Maximum values are not calculated to avoid revealing the field's sensitive data. Average and Standard Deviation continue to be calculated.

* The following parameters have been added to the plugin's input parameters in Fabric V8.5:
  * `fieldNameIncludeList` is an override list of field names to be **included** in the plugin's algorithm, even if they are identified as PII.
  * `fieldNameExcludeList` is an override list of field names to be **excluded** from the plugin's algorithm.

## Option Set Analyzer

The purpose of this plugin is to identify fields with a limited number of distinct values **within a data sample**. It saves these values into a dedicated MTable, enabling their use in data masking and synthetic data generation.

Starting with Fabric V8.4, when running discovery on JSON Schema files (using the [File Cataloging solution](/articles/39_fabric_catalog/05_cataloging_of_files.md)), the plugin identifies fields with an `enum` property and extracts those values directly from the schema rather than a data sample. 

To identify fields with limited distinct values, consider these guidelines:

1. Ensure the field is non-PII to comply with privacy regulations and avoid the exposure of sensitive data. 
2. The number of distinct values should be below either a plugin’s threshold (e.g., 0.05) or the Absolute Threshold input parameter, which defaults to 15.

Additional rules apply based on the **plugin input parameters**, as explained further in this article.

Once a field is identified as an Option Set, the property ```optionSet = true``` is created for it. Starting from Fabric V8.3.1, the ```classification = OPTION_SET``` is assigned to this field, unless it has already been classified. In the Catalog Settings, the OPTION_SET classification is mapped to the **RandomOptionSet** Actor for masking and synthetic data generation. The actor randomly selects a value from the catalog_field_option_set MTable, based on the input data platform, schema, dataset, class and field.

A separate MTable is generated for each data platform and schema to store the distinct values (and their distribution) identified by the plugin in a field. The MTable name format is: 

```catalog_field_option_set___<dataPlatform>_<schema>.csv``` (containing three underscores before the data platform name).

The image below shows an example of such an MTable:

<img src="../images/option_set_mtable_ex.png" />

The plugin's input parameters are described below:

* ```propertyName``` is the field property that the plugin creates when the field qualifies as an Option Set. By default, the property is named `optionSet`.
* ```absoluteThreshold``` defines the maximum number of distinct values allowed. If the proportion of distinct values in a sample exceeds the plugin’s threshold (0.05), the plugin checks the count against this absolute value (15). For example:
  * In a sample of 100 records, a field has 10 distinct values (0.1 proportion). While 0.1 exceeds the 0.05 threshold, the field still qualifies as an **Option Set** because 10 is less than the **Absolute Threshold** (15).
* ```fieldTypeIncludeList``` controls which field data types should be analyzed. 
  * By default, ```fieldTypeIncludeList``` is set to STRING and INTEGER. 
  * Supported values are: STRING, INTEGER, REAL, DATETIME, DATE and BOOLEAN.
* ```fieldNameIncludeList``` is an override list of field names to be **included** in the plugin's validation algorithm, even if they are identified as PII or belong to a small table (see the ```minSampleSize``` property).
* `fieldNameExcludeList` is an override list of field names to be **excluded** from the plugin's validation algorithm.
* ```incrementalMode``` (introduced in Fabric V8.3.1) defines how the plugin handles fields analyzed in previous Discovery Job executions. It has the following modes:

  * ```"Keep All"``` (default) — the plugin will not analyze the fields that have already been analyzed in a previous Discovery Job execution (even if the field does not have the 'Option Set' property). The plugin will only analyze new fields.
  * ```"Keep Existing"``` — the plugin will not analyze the fields that have already been analyzed in a previous Discovery Job execution and an 'Option Set' property was created for it. The plugin will analyze new fields and the existing fields that do not have this property.
  * ```"Evaluate All"``` — the plugin will analyze all fields.
* `maxStringLength` sets a limit on STRING size to prevent handling text files or complex structures within a field. The default value is 512 bytes.
* `minSampleSize` defines the minimum sample size for performing the plugin's algorithm to determine whether a field qualifies as an **Option Set**. Datasets with a smaller sample size are skipped. The default value is 100.

