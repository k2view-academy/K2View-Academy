# LLM-Based Data Profiling

### Overview

Starting from V8.2, the Catalog includes a data profiling plugin powered by LLM. The plugin invokes an LLM model via an OpenAI interface defined in a project and performs profiling of each column's metadata and data. The LLM plugin's response depends on the system prompt definition. The included system prompt can be modified per project's needs, based on the rules explained further in this article. 

The product includes settings for 3 pre-defined use cases:

1. Improved classification of columns which include sensitive / PII info. 
2. Creation of a new business dimension (new property).
3. Creation of a column's short description.

See below the explanation of each use case. By understanding how each use case is achieved via updating the system prompt and other plugin's input parameters, you can use the same plugin to achieve your own use cases.

### LLM Plugin Definition

The LLM plugin's input parameters definition:

- ```"property_name``` is a field-level property that the plugin should create if needed (based on the LLM response and the threshold).
  - ```"threshold": 0.8``` defines whether the plugin should be executed. The plugin skips the columns which already have the same property created by another plugin, with a score above the threshold. 
  - For example, if the Metadata Regex Classifier plugin created a classification with score = 0.9, the LLM plugin will not be executed on this field.
- ```"system_prompt"``` is a prompt definition. It is a dynamic string, comprised of several parts that are combined at the run time. Some of them are taken from the framework and some are taken from the plugin definition:
  - ```${tableName} ```, ```${columns}``` and ```${columnName}``` are respectively the names of a table, the column being profiled and the names of all other columns in this table. They are passed to the plugin by the framework.
  - ```${possible_values}``` is a list of valid values that can be assigned as a property's value. The values are taken from the ```"possible_values"``` input further in the plugin definition.
  - ```${sample_prompt}``` is a system prompt part related to the data sample. It is taken from the ```"sample_prompt"``` input further in the plugin definition.
- ```"possible_values"``` is a list of possible property valid values. They can be defined as an array of strings in the plugin definition (as shown above). Alternatively, the values can be retrieved from any project MTable. To define that the values should be taken from an MTable, write ```<MTable Name>.<Column Name>```. 
  - For example, ```"possible_values":"pii_profiling.name"```.
- ```"sample_size"``` defines a sample size to be used by LLM. If you don't want to send any sample data to the LLM, set the sample size to 0. 
- ```"sample_prompt"``` defines a part of the system prompt related to the sample data. It is added to the system prompt when the ```"sample_size"``` > 0 and if the column is not empty. 
  - The ```${sampleData}``` is the source data retrieved at the Snapshot step and added to the prompt. 
- ```"llm_interface"``` is an optional parameter. It can be set to a name of a project's OpenAI interface that should be used by the LLM plugin. 
  - When ```"llm_interface"``` parameter is not defined in the plugin definition, the plugin will search for a OpenAI interface with Tag = discovery. 
  - If such does not exist either, the default project's OpenAI interface will be used.

### Use Case 1 - LLM Based Classification

The Catalog includes 2 plugins which classify the columns based on their data and metadata. Both are using regular expressions to perform the profiling task. But these plugins might miss sensitive data in certain cases. For example, when a column name is not meaningful and the regular expression cannot be applied on column values (e.g. names), the regex-driven plugins might miss columns with sensitive data. 

LLM can help to improve the classification task by analyzing the data and also by using the full context of a table and column names. 

This is an example definition of the plugin for Use Case 1:

```json
{
	"name": "LLM Data Profiling Plugin",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.8,
	"monitor_desc": "Classifications",
	"input_parameters": {
		"property_name": "classification",
		"system_prompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease classify the column ${columnName} based on its name, choosing one of the following possible values: ${possible_values}.\n${sample_prompt}\n If none of the possible values match, return $NONE$.\n Format your response using the following format: <the-selected-value>.\n Your response should only include the selected value with no other text. For example:<ADDRESS>",
		"possible_values": [
					"SOCIAL_SECURITY_NUMBER",
					"FIRST_NAME",
					"LAST_NAME",
					"ADDRESS",
					"CITY",
					"POSTAL_CODE",
					"STATE",
					"COUNTRY"
				],
		"sample_size": 10,
		"sample_prompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}.",
		"llm_interface": "myGpt4o"
	}
}
```

### Use Case 2 - New Dimension Creation 

xxxxxx

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

### Use Case 3 - Column Description by LLM

xxx

~~~json
{
	"name": "LLM description",
	"class": "com.k2view.discovery.plugins.llm.LLMDataProfilingPlugin",
	"active": true,
	"threshold": 0.8,
	"monitor_desc": "descriptions",
	"input_parameters": {
		"property_name": "description",
		"system_prompt": "Given the following table ${tableName} which includes the following columns ${columns}.\nPlease write a one line description of the ${columnName} in order to use it in the technical documentation.\n${sample_prompt}\n Do not include table and coulmns names in your response.",
		"sample_size": 10,
		"sample_prompt": "Here is a data sample from the column ${columnName} to help you classify the column: ${sampleData}."
	}
}
~~~

