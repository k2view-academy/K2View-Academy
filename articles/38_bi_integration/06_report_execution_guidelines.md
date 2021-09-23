# Report Execution Guidelines

BI Reports are created and executed using the **BI Designer** module. Generation of a report without opening the BI application can be done by the Exago **GetExecute** REST API.

The Fabric Jobs mechanism enables the BI reports generation from the Fabric user code so they can be scheduled and benefit from Jobs execution parameters. Use the  **GENERATE_BI** job type and set the **NAME** to the full path from the root folder to the report. **ARGS** should be passed as follows:

* **OUTPUT_NAME** - final name of the report. For example, you can concatenate parameters such as IID or datetime to the original report name or even provide a completely different name for the export file.
* **TYPE** - export file format can be of the following valid values:
  * html, csv, pdf, rtf, excel, json.
* **DESTINATION** - the name of the Fabric interface where the export file should be placed, can be either Local File System or SFTP interface type.
* **DATA_SOURCES_LIST** - array of the report's data sources, should be the same as number of data sources defined for this report. [Click to get more details about the data source definition](03_Metadata_Setup.md#data-sources).
* **SESSION_PARAMS** (optional) - array of the session parameters used by the report. Only the **Id** (name) and the **Value** attributes of the parameter are mandatory. The rest attributes (such as Type) are optional. [Click to get more details about the parameters definition](04_parameters.md).
* **FILTER** (optional)
* **SORT** (optional)

~~~
startjob GENERATE_BI NAME='TDM Load Reports/List_of_Copied_Root_Entities' ARGS='{"OUTPUT_NAME":" List_of_Copied_Root_Entities_TaskID_12345_ExecutionDate_20212309", "DATA_SOURCES_LIST":"[{\"Name\": \"Fabric-PROD-V1\"}]", "TYPE":"csv", "DESTINATION":"MyLocalFS", "SESSION_PARAMS": "[{\"Id\": \"task_execution_id\",  \"Value\": \"70\"}, {\"Id\": \"lu_name\", \"DataType\": \"String\", \"Value\": \"CRM_LU\", \"IsHidden\" : false}]"}';
~~~



[Click to get more information about the Exago GetExecute REST API](https://support.exagoinc.com/hc/en-us/articles/115003313988).



[![Previous](/articles/images/Previous.png)](05_report_creation_guidelines.md)

