# Source Data Metrics Plugins

### Overview

This article describes the source data metrics plugins in the Catalog solution, calculated based on the data snapshot:

* [NULL Percentage](04_source_data_metrics.md#null-percentage) - to calculate the percentage of NULL values per column. From Fabric V8.2 onwards, this plugin has been combined with the Data Quality Metrics plugin.
* [Data Quality Metrics](04_source_data_metrics.md#data-quality-metrics) - to calculate various data quality metrics. 

### NULL Percentage

The purpose of this plugin is to calculate the percentage of NULL values per column, based on the data snapshot. This percentage is calculated on each column of non-empty tables. The default size of the data snapshot is configured in the plugins.discovery file as explained [here](/articles/39_fabric_catalog/04_discovery_pipeline.md#data-sample-size).

As a result, the **Null Percentage** property is added to the field's properties when the calculated value is above the threshold. 

For example, when 30% of the values in a certain field are null, the Null Percentage property will be added to this field with the value = 0.3. However, if 20% or less of the values in this field are null, then this property would not be added.

This plugin exists until the Fabric V8.1. In V8.2 it has been combined with the Data Quality Metrics.

### Data Quality Metrics

This plugin scans the data of the data sample in order to calculate the following 3 parameters:

* **Dataset Size** - the actual number of values in a column in the data sample.
  * The data sample is retrieved per the Catalog settings. For example, the default is 10% of the table size with minimum 100 and maximum 500. However, the actual data sample can vary, based on the table size.
* **Distinct Values** - the number of distinct values per column in the data sample. 
  * This metric helps to assess the variety or uniqueness of data within a column. It is beneficial for data categorization, since it helps to analyze whether the data includes a specific set of values or labels (such as status fields or categorical variables). 
  * In addition,  it can help validating whether the data values are within an acceptable or predefined range. For example, in a column is expected to store binary values (Yes/No or true/false), the presence of more distinct values might indicate data quality issues. 
  * A high number of distinct values in a column where you expect fewer unique entries might suggest potential data anomalies, typos, or other errors. 
* **Range Of Values** - the minimum and maximum in a numeric column, in the data sample (e.g., 1 - 100).
  * Establishing the range of values present in the data can help to verify whether these values fall within expected or acceptable limits. This helps to identify potential errors, such as outliers or incorrect data entry (e.g., a negative age value).
  * Understanding the range helps ensure consistency across similar datasets. The range can inform business decisions by providing insights into variability and distribution. 
  * As part of basic descriptive statistics, the range provides a first glimpse into data distribution and can be a precursor to more advanced statistical analyses.
* **Null Percentage** - the percentage of null values per column. 
  * This percentage is calculated on each column of non-empty tables. The **Null Percentage** property is added to the field's properties when the calculated value is above the plugin's threshold. 
  * For example, when 30% of the values in a certain field are null, the Null Percentage property will be added to this field with the value = 0.3. However, if 20% or less of the values in this field are null, then this property would not be added.





