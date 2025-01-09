# LLM-Based Profiling

### Overview

Starting from V8.2, the Catalog includes a Data profiling plugin powered by LLM. The plugin invokes an LLM model via an LLM AI interface defined in the project. Note that prior to an AI interface creation in a project, it is required to install the relevant extension (e.g. OpenAI Connector). 

The LLM plugin performs profiling of each column's metadata and data. The LLM plugin's response depends on the user prompt, which is defined in the plugin's configuration. The pre-defined user prompt can be modified per the project's needs; this user prompt should correspond to use cases as explained further in this article. 

The product includes 2 LLM-based plugins that accommodate for the following use cases:

- Use Case 1: **LLM Data Profiling** - profiling and classification of columns with sensitive / PII data. 
- Use Case 2: **LLM Description** - a creation of each column's short description.

By default, both of these plugins are disabled and should be enabled in the project-level settings, when needed. 

The prerequisite of working with an LLM plugin is a creation of an LLM AI interface in the project. The Discovery can use a designated LLM AI interface (tagged as 'discovery'). If non of the LLM AI interfaces are tagged as 'discovery', an interface with a 'default' tag will be used.

In addition to the above use cases, you can use the same plugin to achieve your own use cases via updating the user prompt and other plugin's input parameters. For example, a new business dimension (a new property) can be created via an advanced plugin setup. An explanation and an example are provided at the end of this article. 

### LLM Plugin Definition

The plugin's input parameters are:

- ```"threshold"``` is the score above which the plugin should not be executed. The threshold is set in order to minimize the number of calls to the LLM. It applies to cases where the column already has **the same property** created by another plugin during the same Discovery Job execution. 
  - By default, ```"threshold":0.7```. 
  - For example, if the Metadata Regex Classifier plugin created a classification property with score = 0.8 (above the threshold), the LLM plugin will not run on this column.
- ```"propertyName"``` is a column's property that should be created by the plugin. 
  - By default,  ```"propertyName": "classification"```, which aims to accommodate the LLM Data Profiling use case.
- ```"userPrompt"``` is an LLM prompt definition. It is a dynamic string, comprised of several parts that are combined at run time. Some of these parts are taken from the framework and some are taken from the plugin's definition, as follows:
  - ```${tableName} ```, ```${columns}``` and ```${columnName}``` are, respectively, a table and a column being profiled, as well as the names of all other columns in this table. These 3 parameters are passed to the plugin by the framework.
  - The ```"userPrompt"``` should be updated to fit the required use case and project's needs. 
- ```"possibleValues"``` is a list of possible property values. 
  - For example, ```"possibleValues":["FIRST_NAME","LAST_NAME","ADDRESS"]```.
  - When you don't intend or need to provide a list of possible values to the LLM, it is recommended to edit the ```"userPrompt"``` by removing the text that refers to the possible values. 
- ```"possibleMTableValues"``` is an alternative way to provide the possible property values. It allows the values to be retrieved from a project's MTable. The ```"possibleMTableValues"``` should be populated using the following format:  ```"<MTable name>.<Column name>"```. 
  - For example, ```"possibleMTableValues" : "pii_profiling.name"```
  - It is recommended for a relatively short list of possible valid values.
  - Either ```"possibleValues"``` or ```"possibleMTableValues"``` should be populated in the plugin's definition, but not both. The ```"userPropmt"``` should be updated accordingly. 
- ```"sampleSize"``` defines a sample size to be used by the LLM. By default, ```"sampleSize": 10```.  If you don't intend to send any sample data to the LLM, set the sample size to 0. 
- ```"samplePrompt"``` defines a part of the user prompt related to the sample data. It is included in the user prompt when the ```"sampleSize"``` > 0 and if the column is not empty in the data snapshot. 
  - The ```${sampleData}``` is the source data retrieved in the Snapshot step and added to the prompt. 
- ```incrementalMode``` defines whether the plugin should be executed for the fields that already have the same property created by the same LLM plugin in a previous Discovery Job execution. This parameter is set in order to minimize the number of calls to the LLM. It has the following modes:
  - ```"KEEP_ALL"``` (default) - if an LLM plugin has already been executed for this field in a previous Discovery Job execution, don’t invoke the plugin again (even if the field has no LLM-created property). The plugin will only be invoked for the new fields.
  - ```"KEEP_EXISTING"``` - if an LLM plugin has already been executed for this field in a previous Discovery Job execution and created a property, don’t invoke it again. The plugin will only be invoked for the new fields and for the fields without this property (e.g., "classification").
  - ```"EVALUATE_ALL"``` - the LLM plugin will be invoked for all fields.
- ```"llmInterface"``` is an optional parameter. It allows overriding the default project's LLM AI interface, to be used by the LLM plugin. This parameter should include the interface's name.
  - When the ```"llmInterface"``` parameter is not set in the plugin definition, the plugin will search for an LLM AI interface tagged as 'discovery'. If non of the LLM AI interfaces are tagged as 'discovery', an interface with a 'default' tag will be used.

### Use Case 1: LLM Data Profiling

The Catalog includes 2 built-in plugins that perform profiling and classification of the columns using the regular expressions [Data Regex Classifier and Metadata Regex Classifier](02_classification_plugins.md). 

However, these plugins might miss some columns with sensitive data, for various reasons. For example, when a column doesn't have a meaningful name and the regular expression cannot be applied on the column's values (e.g., names of people or geographic locations), the regex-driven plugins will not classify such columns. 

LLM-based plugins help to improve the classification task by analyzing the column's data, in a context of table and column names. 

This is a product default definition of the LLM Data Profiling:

```json
{
	"name": "LLM Data Profiling",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.7,
	"monitorDesc": "Classifications",
	"inputParameters": {
		"propertyName": "classification",
		"userPrompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease classify the column ${columnName} based on its name, choosing one of the following possible values: ${possibleValues}.\n${samplePrompt}\n If none of the possible values match, return $NONE$.",
		"possibleValues": [
					"FIRST_NAME",
					"LAST_NAME",
					"ADDRESS",
					"CITY",
					"COUNTRY"
				],
		"possibleMTableValues":"",
      	 "sampleSize": 10,
		"samplePrompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}.",
      	"incrementalMode":"KEEP_ALL"
	}
}
```

### Use Case 2: LLM Description

The LLM plugin can be used for various tasks. One of them is to generate a free-text description of the Catalog fields. To achieve that, the user prompt and other input parameters should be updated to include the required task. 

This is a product default definition of the LLM Description plugin that will generate a short description of each data source's field in the Catalog:

~~~json
{
	"name": "LLM Description",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.8,
	"monitorDesc": "Descriptions",
	"inputParameters": {
		"propertyName": "description",
		"userPrompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease provide a one-line description of ${columnName} with a minimum of 5 words to be used in technical documentation.\n${samplePrompt}\nDo not include table or column names in your response.",
		"sampleSize": 10,
		"samplePrompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}.",
      	"incrementalMode":"KEEP_ALL"
	}
}
~~~

### Use Case 3: LLM Profiling by Property (experimental)

Running the LLM Data Profiling plugin can be effective when either the columns have meaningful names or the column values provide some insight or the combination of both. However, this is not always the case. Sometimes the table and column names are not meaningful and there is no data in them. On the other hand, some field properties can shed more light on how to profile a column. The LLM plugin can use a field property's values to perform the profiling. 

For example, when the table and column names are not meaningful, the descriptions (or remarks) might have been included in the data source for each table and/or column explaining what is stored in them.

To include the value of the ```description``` property in the LLM analysis, use the following syntax in the ```userPrompt```: ```${property.description}```.

Below is an LLM plugin configuration to support this use case:

~~~json
{
	"name": "LLM Property Profiling",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.7,
	"monitorDesc": "Classifications",
	"inputParameters": {
		"propertyName": "classification",
		"userPrompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease classify the column ${columnName} based on its name and additional information included here: ${property.description}. Choosing one of the following possible values: ${possibleValues}.\n${samplePrompt}\n If none of the possible values match, return $NONE$.",
		"possibleValues": [
					"FIRST_NAME",
					"LAST_NAME",
					"ADDRESS",
					"CITY",
					"COUNTRY"
				],
		"possibleMTableValues":"",
      	 "sampleSize": 10,
		"samplePrompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}.",
      	"incrementalMode":"KEEP_ALL"
	}
}
~~~

### Custom Use Case Definition

The LLM plugin facilitates flexibility to define our own use cases, such as discovering new business parameters in a data source by setting up the relevant user prompt and other input parameters. 

For example, it may be required to identify all the data source's columns that include medical information, such as a medical condition, a medical treatment or a drug, and to create a new property ```medicalInfo = true``` . 

This requirement can be achieved by setting up the relevant user prompt and updating the input parameters as follows:

~~~json
{
	"name": "LLM Medical",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.8,
	"monitorDesc": "Medical Info",
	"inputParameters": {
		"propertyName": "medicalInfo",
		"userPrompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease verify if the column ${columnName}, based on its name, indicates a medical condition or relates to a specific medical treatment or drug.\n${samplePrompt}\nIf yes - return <true>. Otherwise, return $NONE$.",
		"sampleSize": 10,
		"samplePrompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}.",
      	"incrementalMode":"KEEP_ALL"
	}
}
~~~

Note that when using the ```com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin``` class for a custom plugin, the following parameters are mandatory:

* ```threshold``` - a number between 0 and 1


* Input parameters:
  * ```propertyName```
  * ```userPrompt```
  * ```sampleSize``` - this can be set to 0, if not applicable.
  * ```samplePrompt``` - it should be referenced in the ```userPrompt```. However, it can be empty if ```sampleSize``` is 0. 
  * ```incrementalMode```

The following parameters are optional:

* ```possibleValues``` - this should be defined as an array and referenced in the ```userPrompt```.
* ```llmInterface```

