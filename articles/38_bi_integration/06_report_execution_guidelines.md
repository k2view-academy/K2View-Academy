# Report Execution Guidelines

BI Reports are created using the **BI Designer** module and they can be executed within the same module. Execution of a report without opening the BI application can be done by calling the Exago REST API.

Fabric provides a dedicated **JOBTYPE** = **GENERATE_BI** that enables reports generation from the Fabric user code.

The job starts from creating a session and then invokes the Exago REST API with all the required input parameters, as follows:

~~~
startjob GENERATE_BI NAME='TDM Load Reports/Load-Summary-Report' ARGS='{"REPORT_PATH":"[\"TDM Load Reports/List_of_Copied_Root_Entities\"]", "REPORT_NAME":"Book3", "TYPE":"csv", "DESTINATION":"local"}';
~~~



TBD - to describe all input & output parameters



[![Previous](/articles/images/Previous.png)](05_report_creation_guidelines.md)

