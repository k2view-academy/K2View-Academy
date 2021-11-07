# Report Execution Guidelines

BI reports are created and executed using the **Designer** module. Generation of a report without opening the BI application can be done by the  **GetExecute** REST API.

The [Fabric Jobs mechanism](/articles/20_jobs_and_batch_services/01_fabric_jobs_overview.md) enables generation of BI reports via Fabric user code so they can be scheduled and can benefit from Jobs execution parameters. 

The sections below explain both options.

### Reports Generation Using the Designer Module

Generate the report in the **Designer** module using the ![run](images/run_icon.PNG) icon in the Report Tree or the Run ![run](images/run_button.PNG) button in the report editor. 

You can setup various report generation options using the Report Options (applicable for Advance Reports). 

### Reports Generation Using the Fabric Jobs Mechanism

Generate the report using the [STARTJOB command](/articles/20_jobs_and_batch_services/07_jobs_commands.md). Set the Job parameters as follows:

* Use the **GENERATE_BI** as the job type.

* Set the **NAME** to be the full path from the root folder to the report. In the below example, the NAME is 'FABRIC_QA/TDM Load/Reports/Load_test_1'.

  ![img](images/report_exe_1.PNG)

* The following **ARGS** should be passed:
  * **OUTPUT_NAME** - extract file name. For example, you can concatenate parameters such as IID or datetime to the original report name or even provide a completely different name for the extract file.
  * **TYPE** - export file format can be one of the following valid formats: html, csv, pdf, rtf, excel, json.
  * **DESTINATION** - the name of the Fabric interface where the export file should be placed. It can be either a Local File System or an SFTP interface type.
  * **SESSION_PARAMS** - array of the session parameters used by the report. Only the **Id** (name) and the **Value** attributes of the parameter are mandatory. The other attributes (such as Type) are optional. 
  * **DATA_SOURCES_LIST** (optional) - array of the Fabric interface names that should correspond with the report's data sources defined for this report. The **DATA_SOURCES_LIST** should only be sent when you need the report to use the connection details different from the connection details defined in the BI Admin.

* The following are optional **ARGS**:

  * **FILTER** (optional) - parameters to filter the report's results. Note that this is a run-time filter and it is <u>not</u> related to the built-in filter which is added as part of report creation. Only the **FilterText** (the value of the filter) is required, the other attributes are optional. The **FilterText** should include a data object and a column to filter on.
  * **SORT** (optional) - parameters to sort the report's results. Note that this is a run-time sort and it is <u>not</u> related to the built-in sort which is added as part of report creation.  The fields **EntityName** and **ColumnName** (data object and column to sort on) are required.


Note that **SESSION_PARAMS** is an optional parameter for REST API invocation. However all reports based on Fabric LU data require at least one session parameter definition - to pass the IID for the GET INSTANCE command.



**Example of GENERATE_BI Job with Session Parameter**

~~~bash
startjob GENERATE_BI NAME='FABRIC_QA/TDM Load/Reports/Load_test_1' ARGS='{"OUTPUT_NAME":" Load_test_TaskID_12345_ExecutionDate_20212309", "DATA_SOURCES_LIST":"[{\"Name\": \"Fabric-PROD-V1\"}]", "TYPE":"csv", "DESTINATION":"MyLocalFS", , "SESSION_PARAMS": "[{\"Id\":\"TASK_EXECUTION_ID\", \"Value\":\"600\" }]"}';
~~~

**Example of GENERATE_BI Job with Filter** 

~~~bash
startjob GENERATE_BI name='Public/task_exe_test1' ARGS='{"OUTPUT_NAME":"task_exe_7_NOV_TEST", "TYPE":"csv", "DESTINATION":"LocalListener", "FILTER":"{\"FilterText\":\"TASK_EXECUTION_ENTITIES_9.LU_NAME\", \"Values\":[\"Billing\"] }" }';
~~~

**Example of GENERATE_BI Job with Sort** 

~~~bash
startjob generate_bi name='test_designer/mig_rep' ARGS='{"OUTPUT_NAME":"mig_rep_sorted","DESTINATION":"LocalListener","TYPE":"csv","SORT":"{\"EntityName\":\"mig_summary_recon_8\",\"ColumnName\":\"bo_name\"}"}';
~~~



[Click to get more information about the Fabric BI (ExagoBI) GetExecute REST API](https://exagobi.com/support/administrators/rest-web-service-api/getexecute/).

### Reports Generation Using "Deep Link"

Generate the report using a direct link to it in a separate browser, as follows:

~~~
<host>:<port>/app/BI/<report path in BI>
~~~

Where <report path in BI> is the full name of the report from the Root folder till the report name.

For example, the "deep link" to the report **mig_rep** under the folder **test_designer** running on a localhost is: 

http://localhost:3213/app/BI/test_designer/mig_rep



[![Previous](/articles/images/Previous.png)](05_report_creation_guidelines.md)

