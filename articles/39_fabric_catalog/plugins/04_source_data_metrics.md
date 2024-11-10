# Source Data Metrics Plugins

### Overview

The following article describes the source data metrics plugins in the Catalog solution:

* [NULL Percentage](04_source_data_metrics.md#null-percentage) - to calculate the percentage of NULL values per column, based on the data snapshot.
* [Data Quality Metrics](04_source_data_metrics.md#data-quality-metrics) - to calculate various data quality metrics. 

### NULL Percentage

The purpose of this plugin is to calculate the percentage of NULL values per column, based on the data snapshot. This percentage is calculated on each column of non-empty tables. The default size of the data snapshot is configured in the plugins.discovery file as explained earlier in this article.

As a result, the **Null Percentage** property is added to the field's properties when the calculated value is above the threshold. 

For example, when 30% of the values in a certain field are null, the Null Percentage property will be added to this field with the value = 0.3. However, if 20% or less of the values in this field are null, then this property would not be added.

### Data Quality Metrics

The plugin scans the data of the data sample in order to calculate the following 3 parameters:

* **Dataset Size** - actual number of values in a column in a data sample.
* **Distinct Values** - number of distinct values per column in a data sample.
* **Range Of Values** - the min and max values in a numeric column, in a data sample (e.g. 1 - 100).



