# LLM-Based Data Profiling

### Overview

Starting from V8.2, the Catalog includes a Data profiling plugin powered by LLM. The plugin invokes an LLM model via an LLM AI interface defined in the project and performs profiling of each column's metadata and data. The LLM plugin's response depends on the system prompt in the plugins.discovery file. The pre-configured system prompt can be modified per the project's needs, based on the rules explained further in this article. 

The LLM plugin is added to the product plugins.discovery file twice, to accommodate for the following use cases:

- Use Case 1: **LLM Data Profiling** - classification of columns with sensitive / PII data. 
- Use Case 2: **LLM Description** - creation of each column's short description.

Note that both settings are disabled by default and should be enabled in the project-level plugin.discovery file, when needed. 

The pre-requisite of working with LLM plugin is a definition of a project LLM AI interface. If it is required that the Discovery will use a specific LLM AI interface, tag this interface as 'discovery'. Otherwise, you can keep the tag as 'default'.

The LLM-based plugins' definitions as well as the detail explanations of each use case and its relevant settings can be found further in this article. 

In addition to the above use cases, you can use the same plugin to achieve your own use cases via updating the system prompt and other plugin's input parameters. For example, a new business dimension (new property) can be created via an advanced plugin setup. An explanation and an example are also provided further in this article. 

### LLM Plugin Definition

The input parameters are:

- ```"threshold"``` is the score above which the plugin should not be executed. The threshold is set in order to minimize the number of calls to the LLM. It applies to the cases when the column already has **the same property** created by another plugin during the same Discovery Job execution. 
  - By default, ```"threshold":0.7```. It can be updated, based on the project's requirements.
  - For example, if the Metadata Regex Classifier plugin created a classification property with score = 0.8 (above the threshold), the LLM plugin will not run on this column.
- ```"propertyName"``` is a column property that the plugin should create. 
  - By default,  ```"propertyName": "classification"```.
- ```"system_prompt"``` is a prompt definition. It is a dynamic string, comprised of several parts that are combined at the run time. Some of them are taken from the framework and some are taken from the plugin definition, as follows:
  - ```${tableName} ```, ```${columns}``` and ```${columnName}``` respectively are a table and a column being profiled, as well as the names of all other columns in this table. These are passed to the plugin by the framework.
  - ```${possibleValues}``` defines a list of valid values that can be assigned as a property's value. They need to be defined when it is required that the LLM will select a value from a pre-defined list. The values are taken from the ```"possibleValues"``` input parameter.
  - ```${samplePrompt}``` is a system prompt part related to the data sample. It is taken from the ```"samplePrompt"``` input parameter.
- ```"possibleValues"``` is a list of possible property's values. The values should be defined as an array of strings. 
  - For example, ```"possibleVlues":["FIRST_NAME","LAST_NAME","ADDRESS"]```.
  - Alternatively, the values can be retrieved from a project MTable. In this case, ```"possibleValues"``` definition should be: ```"possibleVlues":"<MTable name>.<Column name>"```. 
  - It is recommended to define a relatively short the list of possible valid values. 
  - When you don't want or need to provide a list of possible values to LLM, it is recommended to edit the ```"systemPrompt"``` by removing the text which refers to the possible values. 
- ```"sampleSize"``` defines a sample size to be used by LLM. By default, ```"sampleSize": 10```.  If you don't want to send any sample data to the LLM, set the sample size to 0. 
- ```"samplePrompt"``` defines a part of the system prompt related to the sample data. It is included in the system prompt when the ```"sampleSize"``` > 0 and if the column is not empty in the data snapshot. 
  - The ```${sampleData}``` is the source data retrieved at the Snapshot step and added to the prompt. 
- ```incrementalMode``` defines whether the plugin should be executed for the fields that already have the same property created by the same LLM plugin in a previous Discovery Job execution. This parameter is set in order to minimize the number of calls to the LLM. It has the following modes:
  - KEEP_ALL (default) - if an LLM plugin has already been executed for this field in a previous Catalog version, don’t invoke it again (even if the field has no LLM-created property). Run the plugin for new fields only.
  - KEEP_EXISTING - if an LLM plugin has already been executed for this field in a previous Catalog version and created a property with a value, don’t invoke it again. Run the plugin for new fields and the fields without this property (e.g. "classification").
  - EVALUATE_ALL - invoke the LLM plugin regardless of whether it has an LLM-created property or not.
- ```"llmInterface"``` is an optional parameter. It allows to override the default project's LLM AI interface, to be used by the LLM plugin. This parameter should include the interface's name.
  - When ```"llmInterface"``` parameter is not set in the plugin definition, the plugin will search for a LLM AI interface with Tag = discovery. 
  - If such does not exist either, the default project's LLM AI interface will be used.

### Use Case 1: LLM Data Profiling

The Catalog includes 2 built-in plugins which classify the columns using regular expressions: [Data Regex Classifier](/articles/39_fabric_catalog/04a_builtin_plugins.md#data-regex-classifier) and [Metadata Regex Classifier](/articles/39_fabric_catalog/04a_builtin_plugins.md#metadata-regex-classifier). 

However, these plugins might miss columns with sensitive data. For example, when a column doesn't have a meaningful name and the regular expression cannot be applied on the column's values (e.g. names of people or geographic locations), the regex-driven plugins will not classify such columns and will not mark them as sensitive. 

LLM-based plugins helps to improve the classification task by analyzing the data, in a context of a table and column names. 

This is an example definition of the LLM plugin for the current use case:

```json
{
	"name": "LLM Data Profiling",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.7,
	"monitorDesc": "Classifications",
	"inputParameters": {
		"propertyName": "classification",
		"systemPrompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease classify the column ${columnName} based on its name, choosing one of the following possible values: ${possibleValues}.\n${samplePrompt}\n If none of the possible values match, return $NONE$.\n Format your response using the following format: <the-selected-value>.\n Your response should only include the selected value with no other text. For example:<ADDRESS>",
		"possibleValues": [
					"FIRST_NAME",
					"LAST_NAME",
					"ADDRESS",
					"CITY",
					"COUNTRY"
				],
		"sampleSize": 10,
		"samplePrompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}.",
      	"incrementalMode":"KEEP_ALL"
	}
}
```

### Use Case 2: LLM Description

The LLM plugin can be used for various tasks. One of them is to generate a free-text description of the Catalog fields. To achieve that, the system prompt and other input parameters should be updated to include the required task. 

Using the below example definition, the LLM plugin will generate a short description of each data source's field in the Catalog:

~~~json
{
	"name": "LLM Description",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.8,
	"monitorDesc": "Descriptions",
	"inputParameters": {
		"propertyName": "description",
		"systemPrompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease write a one line description of the ${columnName} in order to use it in the technical documentation.\n${sample_prompt}\n Do not include table and coulmns names in your response.",
		"sampleSize": 10,
		"samplePrompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}.",
      	"incrementalMode":"KEEP_ALL"
	}
}
~~~

### Custom Use Case Definition

The LLM plugin allows us the flexibility to define our own use cases, such as discovering new business parameters in the data source by setting up the relevant system prompt. 

For example, it might be required to identify all of the data source's columns that include any medical information, such as a medical condition, a medical treatment or a drug. These requirements can be achieved by setting up the relevant system prompt and creating a new property: ```medicalInfo = true``` . 

This is an example definition of the LLM plugin for creating such property:

~~~json
{
	"name": "LLM Medical",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.8,
	"monitorDesc": "Medical Info",
	"inputParameters": {
		"propertyName": "medicalInfo",
		"systemPrompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease classify the column ${columnName} based on its name. If it indicates a medical condition or relates to a specific medical treatment or drug choosing one of the following possible values: ${possibleValues}.\n${samplePrompt}\n If none of the possible values match, return $NONE$.\n Format your response using the following format: <the-selected-value>.\n Your response should only include the selected value with no other text. For example:<true>",
		"possibleValues": ["true"],
		"sampleSize": 10,
		"samplePrompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}.",
      	"incrementalMode":"KEEP_ALL"
	}
}
~~~

