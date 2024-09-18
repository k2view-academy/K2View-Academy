# LLM-Based Data Profiling

### Overview

Starting from V8.2, the Catalog includes a data profiling plugin powered by LLM. The plugin invokes an LLM model via an OpenAI interface defined in a project. The respond of the LLM plugin depends on the system prompt definition. The system prompt can be modified per project's needs, based on the rules explained further in this article. 

The product includes settings for 3 pre-defined use cases:

1. Column classification based on metadata and data profiling.
2. Creation of a new business dimension (new property).
3. Creation of a column's short description.

The article below explains each use case in more details. By understanding how each use case is achieved via updating the plugin's system prompt and other input parameters, you can use the same plugin to achieve your own use cases.

### LLM Plugin Definition

Below is the description of LLM plugin's input parameters definition:

- ```"property_name``` is a field-level property that the plugin should create (if needed, based on the LLM response and the threshold).
  - ```"threshold": 0.8``` defines whether the plugin should be executed. The rule is that the plugin skips the columns which already have the same property with a score above the threshold. 
- ```"system_prompt"``` is a prompt definition and it is comprised of several parts. Some of them are taken from the framework and some are taken from the plugin definition:
  - ```${tableName} ```, ```${columns}``` and ```${columnName}``` are respectively the names of a table, the column being profiled and all other columns. They are passed to the plugin by the framework.
  - ```${possible_values}``` is a list of valid values that can be assigned as a property value. The values are taken from the ```"possible_values"``` input further in the plugin definition.
  - ```${sample_prompt}``` is a system prompt part related to the data sample. It is taken from the ```"sample_prompt"``` input further in the plugin definition.
- ```"possible_values"``` is a list of possible property valid values. They can be defined as an array of strings in the plugin definition (as shown above). Alternatively, the values can be retrieved from any project MTable. To define that the values should be taken from an MTable, write ```<MTable Name>.<Column Name>```. For example, ```"possible_values":"pii_profiling.name"```.
- ```"sample_size"``` defines a sample size to be used by LLM. If you don't want to send any sample data to the LLM, set it to 0. 
- ```"sample_prompt"``` defines a part of the system prompt related to the sample data. It is concatenated with the system prompt when the ```"sample_size"``` is higher than 0 and there is data in the column. 
  - The ```${sampleData}``` is the actual data retrieved by the framework and concatenated to the prompt. 
- ```"llm_interface"``` is an optional parameter. It can be set to a name of a project's **OpenAI interface** that should be used by the plugin. 
  - When ```"llm_interface"``` parameter is not defined in the plugin definition, the plugin will search for a OpenAI interface with Tag = discovery. 
  - If such does not exist either, the default project's OpenAI interface will be used.

### Use Case 1 - LLM Based Classification

The Catalog includes 2 plugins which classify the columns based on their data and metadata. Both are using regular expressions to perform the profiling task. 

LLM can help to improve the classification task. For example, when a column name is not meaningful and the regular expression cannot be applied on column values (e.g. names), the regex-driven plugins might miss columns with sensitive data. However, LLM can catch it correctly. 

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

