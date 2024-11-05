# Data Quality Metrics Plugins

### Overview

The following article describes the data quality metrics plugins in the Catalog solution:

* [NULL Percentage](04_data_quality_metrics.md#null-percentage) - to calculate the percentage of NULL values per column, based on the data snapshot.

### NULL Percentage

The purpose of this plugin is to calculate the percentage of NULL values per column, based on the data snapshot. This percentage is calculated on each column of non-empty tables. The default size of the data snapshot is configured in the plugins.discovery file as explained earlier in this article.

As a result, the **Null Percentage** property is added to the field's properties when the calculated value is above the threshold. 

For example, when 30% of the values in a certain field are null, the Null Percentage property will be added to this field with the value = 0.3. However, if 20% or less of the values in this field are null, then this property would not be added.



