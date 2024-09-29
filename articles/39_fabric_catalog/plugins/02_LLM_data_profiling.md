# LLM-Based Data Profiling

### Overview

Starting from V8.2, the Catalog includes a data profiling plugin powered by LLM. The plugin invokes an LLM model via an LLM AI interface defined in the project and performs profiling of each column's metadata and data. The LLM plugin's response depends on the system prompt in the plugin's settings file. The pre-configured system prompt can be modified per project's needs, based on the rules explained further in this article. 

The LLM plugin is added to the product plugins.discovery file twice, to accommodate for the following use cases:

- Use Case 1: **LLM Data Profiling** - classification of columns with sensitive / PII data. 
- Use Case 2: **LLM Description** - creation of each column's short description.

Note that both settings are disabled by default and should be enabled in the project-level plugin.discovery file, if needed. The pre-requisite of working with LLM plugins is a definition of the LLM AI interface. 

See below the explanation of each use case and its relevant settings. 

In addition to the above use cases, you can use the same plugin to achieve your own use cases via updating the system prompt and other plugin's input parameters. For example, a new business dimension (new property) can be created via an advanced plugin setup. An explanation and an example are provided further in this article. 

### LLM Plugin Definition

The LLM plugin's input parameters are:

- ```"threshold"``` defines the score above which the plugin should not be executed. The threshold is set in order to minimize the number of calls to the LLM. It applies to the cases when the column already has **the same property** created by another plugin during the same Discovery Job execution. 
  - By default, ```"threshold":0.7```. It can be updated, based on the project's requirements.
  - For example, if the Metadata Regex Classifier plugin created a classification property with score = 0.8 (above the threshold), the LLM plugin should not be executed on this column.
- ```"property_name"``` is a column property that the plugin should create. The property's value will be the LLM response value.
- ```"system_prompt"``` is a prompt definition. It is a dynamic string, comprised of several parts that are combined at the run time. Some of them are taken from the framework and some are taken from the plugin definition, as follows:
  - ```${tableName} ```, ```${columns}``` and ```${columnName}``` respectively are a table and a column being profiled, as well as the names of all other columns in this table. These are passed to the plugin by the framework.
  - ```${possible_values}``` defines a list of valid values that can be assigned as a property's value. They need to be defined when it is required that the LLM will select a value from a pre-defined list. The values are taken from the ```"possible_values"``` input further in the plugin definition.
  - ```${sample_prompt}``` is a system prompt part related to the data sample. It is taken from the ```"sample_prompt"``` input further in the plugin definition.
- ```"possible_values"``` is a list of possible property's valid values. They should be defined as an array of strings in the plugin definition (as shown above). Alternatively, the values can be retrieved from any project MTable. Note that it is recommended to define a relatively short the list of possible valid values. 
  - The format of ```"possible_values"``` definition from an MTable is: ```<MTable Name>.<Column Name>```. For example, ```"possible_values":"llm_profiling.name"```.
- ```"sample_size"``` defines a sample size to be used by LLM. By default, ```"sample_size": 10```.  If you don't want to send any sample data to the LLM, set the sample size to 0. 
- ```"sample_prompt"``` defines a part of the system prompt related to the sample data. It is added to the system prompt when the ```"sample_size"``` > 0 and if the column is not empty in the data snapshot. 
  - The ```${sampleData}``` is the source data retrieved at the Snapshot step and added to the prompt. 
- ```"llm_interface"``` is an optional parameter. It can be set to a name of a project's  LLM AI interface that should be used by the LLM plugin. 
  - When ```"llm_interface"``` parameter is not set in the plugin definition, the plugin will search for a LLM AI interface with Tag = discovery. 
  - If such does not exist either, the default project's LLM AI interface will be used.

### Use Case 1: LLM Data Profiling

The Catalog includes 2 built-in plugins which classify the columns based on their data and metadata: [Data Regex Classifier](/articles/39_fabric_catalog/04a_builtin_plugins.md#data-regex-classifier) and [Metadata Regex Classifier](/articles/39_fabric_catalog/04a_builtin_plugins.md#metadata-regex-classifier). Both are using regular expressions to perform the profiling task. 

However, these plugins might miss sensitive data in certain cases. For example, when a column name cannot be profiled using a regular expression and also the regular expression cannot be applied on column values (e.g. names of people or geographic locations), the regex-driven plugins might miss columns with sensitive data. 

LLM can help to improve the classification task by analyzing the data and also by using the full context of a table and column names. 

This is an example definition of the LLM plugin for the current use case:

```json
{
	"name": "LLM Data Profiling",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.7,
	"monitor_desc": "Classifications",
	"input_parameters": {
		"property_name": "classification",
		"system_prompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease classify the column ${columnName} based on its name, choosing one of the following possible values: ${possible_values}.\n${sample_prompt}\n If none of the possible values match, return $NONE$.\n Format your response using the following format: <the-selected-value>.\n Your response should only include the selected value with no other text. For example:<ADDRESS>",
		"possible_values": [
					"FIRST_NAME",
					"LAST_NAME",
					"ADDRESS",
					"CITY",
					"POSTAL_CODE",
					"COUNTRY"
				],
		"sample_size": 10,
		"sample_prompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}."
	}
}
```

### Use Case 2: LLM Description

The LLM plugin might help writing a free-text description of the Catalog fields. 

Using the below example definition, a short description of each data source's field in the Catalog will be created by the LLM plugin:

~~~json
{
	"name": "LLM Description",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.8,
	"monitor_desc": "Descriptions",
	"input_parameters": {
		"property_name": "description",
		"system_prompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease write a one line description of the ${columnName} in order to use it in the technical documentation.\n${sample_prompt}\n Do not include table and coulmns names in your response.",
		"sample_size": 10,
		"sample_prompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}."
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
	"monitor_desc": "Medical Info",
	"input_parameters": {
		"property_name": "medicalInfo",
		"system_prompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease classify the column ${columnName} based on its name. If it indicates a medical condition or relates to a specific medical treatment or drug choosing one of the following possible values: ${possible_values}.\n${sample_prompt}\n If none of the possible values match, return $NONE$.\n Format your response using the following format: <the-selected-value>.\n Your response should only include the selected value with no other text. For example:<true>",
		"possible_values": ["true"],
		"sample_size": 10,
		"sample_prompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}."
	}
}
~~~

