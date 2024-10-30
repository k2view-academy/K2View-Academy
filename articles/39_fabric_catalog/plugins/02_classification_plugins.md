# Basic Classification Plugins

### Overview

The following article describes the basic classification plugins in the Catalog solution:

* [Data Regex Classifier](00_classification_plugins.md#data-regex-classifier) - to classify the source fields based on their data - field value. 
* [Metadata Regex Classifier](00_classification_plugins.md#metadata-regex-classifier) - to classify the source fields based on their metadata - field name.
* [Classification PII Marker](00_classification_plugins.md#classification-pii-marker) - to set the fields as based on their classification.
* [NULL Percentage](00_classification_plugins.md#null-percentage) - to calculate the percentage of NULL values per column, based on the data snapshot.

### Data Regex Classifier

The purpose of **Data Regex Classifier** plugin is to classify the source fields based on their data - field value. This classification helps to identify which Catalog entities store sensitive information and should therefore be masked. 

This plugin runs on a data snapshot that is extracted from the source, and it executes the regular expressions defined in a built-in **data_profiling** MTable.

If a regular expression (known as regex) matches the field's data, a Classification property is added to the field with a value corresponding to the matching regex (e.g., EMAIL). If a match is found for more than one expression, the property is created with the Classification that got a higher calculated score. 

To update the data profiling rules, go to the [Catalog Settings > Classifier Regex Setup tab](../10_catalog_settings.md#classifier-regex-setup).

**Example:**

The below regular expression ```\b(?:\d[ -]*?){13,16}\b``` is executed on the field's values:

![](../images/regex_example.png)

When the expression matches a field's value, the probability that this field holds a credit card number is 0.8. Thus, in case of a match, the score is 0.8 and when there is no match, the score is 0. The expression is executed on all values on the given column in the data sample and the average score is calculated. Then, the calculated average score is compared with the plugin's threshold as explained earlier in this article. If the calculated average score is above the threshold, the Classification = CREDIT_CARD property is added to the field.

### Metadata Regex Classifier

The purpose of **Metadata Regex Classifier** plugin is to classify the source fields based on their metadata - field name. 

The matching rules are defined using regular expressions in a built-in **metadata_profiling** MTable. 

If a regular expression (known as regex) matches the field's name, a Classification property is added to the field with a value corresponding to the matching regex (e.g., SOCIAL_SECURITY_NUMBER). If a match is found for more than one expression, the property is created with the Classification that has the highest score.

<img src="../images/field_classification.png" style="zoom: 67%;" />

To update the metadata profiling rules, go to the [Catalog Settings > Classifier Regex Setup tab](../10_catalog_settings.md#classifier-regex-setup).

#### Field Exclusion List

Fields can be excluded from the **Metadata Regex Classifier** plugin's logic by either their name or type. This can be useful when, for example, you need to exclude all fields with a certain name or a name pattern from the classification process. 

The exclusion list can be defined using the **field_name_exclude_list** and **field_type_exclude_list** arrays in the plugin's input parameters definition of the plugins.discovery configuration file. The **field_name_exclude_list** definition can be either the exact field name or a regular expression.

**Example:**

~~~json
"input_parameters": {
	"field_name_exclude_list": [
					"(?i).*NAME.*",
        			"STREET_ADDRESS_2"
	],
	"field_type_exclude_list": [
					"boolean"
	]
}
~~~



### Classification PII Marker

The purpose of **Classification PII Marker** plugin is to go over all the fields that have got the **Classification** property (by either one of the above plugins) and to add the **PII** property. 

The rules as to whether the classification type is considered a PII are defined in a built-in **pii_profiling** MTable. 

To update the Classification's PII indicator, go to the [Catalog Settings > Classifier PII & Masking Setup](../10_catalog_settings.md#classifier-pii--masking-setup). 

#### Field Exclusion List

Fields can be excluded from the **Classification PII Marker** plugin's logic by either their name or type. This can be useful when, for example, you need to exclude all fields with a certain name or a name pattern from the PII marking process. 

The exclusion list can be defined using the **field_name_exclude_list** and **field_type_exclude_list** arrays in the plugin's input parameters definition of the plugins.discovery configuration file. The **field_name_exclude_list** definition can be either the exact field name or a regular expression.

**Example:**

~~~
"input_parameters": {
	"field_name_exclude_list": [
					"^(?i)[a-z]+_?ID$"
	],
	"field_type_exclude_list": [
					"DATETIME"
	]
}
~~~



### NULL Percentage

The purpose of this plugin is to calculate the percentage of NULL values per column, based on the data snapshot. This percentage is calculated on each column of non-empty tables. The default size of the data snapshot is configured in the plugins.discovery file as explained earlier in this article.

As a result, the **Null Percentage** property is added to the field's properties when the calculated value is above the threshold. 

For example, when 30% of the values in a certain field are null, the Null Percentage property will be added to this field with the value = 0.3. However, if 20% or less of the values in this field are null, then this property would not be added.



