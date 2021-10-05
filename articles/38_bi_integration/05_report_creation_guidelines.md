# Report Creation Guidelines

### Overview

**BI Designer** is the Web Framework application powered by Exago BI which enables creating various types of reports and dashboards. The [Exago Support Center](https://exagobi.com/support/) provides full information how to do it.

This article describes several important guidelines to enable the user creating the reports over Fabric.

### Creating Reports Based on Fabric LU Tables

One of the basic reports is a report based on Fabric LU instance data. Follow these steps to create such report:

1. Open **BI Admin** to:

   * Create the Fabric data source and define its objects and joins, as explained step-by-step in the [Metadata Setup](03_Metadata_Setup.md) article. 
   * Create the parameter(s) to be used by the filter, as explained in the [Parameters](04_parameters.md) article.

2. Open **BI Designer** to click the ![image](images/create_icon.PNG) icon to trigger the new report creation and select the report type, for example **Advanced Report**. 

3. Start by selecting the data objects for the report. 

   * The list of data objects presents a list of all existing data objects under all defined data sources. 
   * Once the first data object is selected, most of the objects are disabled except for those that have a join with the selected object.


   Remember that you must start the selection from the LU Root table and down to the required tables, based on the LU Schema relations. 

4. Once the data objects are selected, click the![image](images/filter_icon.PNG) icon to set the filter by IID as follows:

![image](images/filter_definition.PNG)

### Creating Reports Based on Fabric Custom SQL

Sometimes the report data requires writing a complex SQL query rather then just selecting columns from several joined tables. This is supported by a Customer SQL Object screen.

Creating a report based on a query executed on Fabric LUI is similar to creating it based on Fabric LUI tables.  Click the ![image](images/create_icon.PNG) icon to trigger the new report creation and then instead of selecting the data objects, click the![image](images/add_sql_icon.PNG)icon to write the custom query using the Custom SQL Object screen:

![image](images/custom_sql_obj.PNG)

Remember that since this SQL is executed on LUI, it must also include a filter by LU Instance ID. For example:

~~~sql
SELECT TE.source_id, TE.target_id, tasks.be_name, task_execution.task_execution_id, 'Copied' as status
FROM (select distinct entity_id as source_id, target_entity_id as target_id
		from task_execution_link_entities
		where parent_entity_id = '' and execution_status = 'completed'
		except
	  select entity_id as source_id, target_entity_id as target_id
		from task_execution_link_entities
		where parent_entity_id = '' and execution_status <> 'completed') TE, tasks, task_execution
WHERE task_execution.task_execution_id = '@task_execution_id@'
~~~

The IID value can be provided using a parameter which is created in BI Admin and set either in BI Admin or programmatically via the REST API call. See more at [Reports execution guidelines](06_report_execution_guidelines.md). 

### Creating Reports Based on Fabric Command

When you need to create a report based on the Fabric Command results, it can be done using the Custom SQL Object screen. 

Click the ![image](images/create_icon.PNG) icon to trigger the new report creation and then instead of selecting the data objects, click the![image](images/add_sql_icon.PNG)icon to write the custom query using the Custom SQL Object screen. Use the special syntax as shown below:

~~~sql
select * from k2_fabric_command_sql where command='<command>'
~~~

For example, when the report should include the results of TEST_CONNECTION, write the following custom query:

~~~sql
select * from k2_fabric_command_sql where command='test_connection'
~~~



[![Previous](/articles/images/Previous.png)](04_parameters.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_report_execution_guidelines.md)
