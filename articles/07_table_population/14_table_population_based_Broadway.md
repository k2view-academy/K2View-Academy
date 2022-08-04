# Table Populations Based on Broadway Flows

A [Table Population](/articles/07_table_population/01_table_population_overview.md) defines and executes mapping and transformation rules from a data source to a target. A table population can be created based on a source object or based on a Broadway flow. 

A [Broadway flow](/articles/19_Broadway/02a_broadway_flow_overview.md) is a core Broadway object that represents a business process and is built from several [Stages](/articles/19_Broadway/19_broadway_flow_stages.md) where each Stage includes one or more [Actors](/articles/19_Broadway/03_broadway_actor.md).

The advantages of using a Broadway flow for a table population rather than a source object based population are:

* Streamlining logic and all related validations into one business process whereby improving the project's maintainability.
* Populating more than one table in a single population flow.
* Replacing the source DB with another action such as an HTTP call.

[Click for more information about Broadway](/articles/19_Broadway/01_broadway_overview.md).

### Flow Population Template

A Broadway population is created as a template with predefined Stages and designated Actors. The template flow is ready to be used and can be executed as part of the Logical Unit population without any changes. If needed, the flow can be modified by adding or removing Actors and Stages.

The following example displays a Broadway flow template created to populate the CASES table in the Logical Unit. 

![image](images/07_14_01.PNG)



**Predefined Stages and Actors**

* **Input** Stage, defines the population's input arguments using a designated **PopulationArgs** Actor. 

  *  **PopulationArgs** is a mandatory Actor in the Broadway population and is the only Actor that cannot be removed from the template. The purpose of the **PopulationArgs** Actor is to connect the current population with the LU Schema by setting the parent-child relationship via the **iid** and **parent_rows** output arguments.

  *  The input arguments of the **PopulationArgs** are added either automatically, based on the selected table's fields, or should be added manually. 

  *  The **iid** output argument indicates the instance ID of the execution. The **parent_rows** output argument is an array of objects that iterate over parent rows. For example, when the CASES table is related to the ACTIVITY table in the LU Schema, the **iid** is a customer ID and the **parent_rows** includes the list of activity IDs of this customer.

    <img src="images/07_14_03.PNG" alt="image" style="zoom:75%;" />

  * Starting from Fabric V6.5.9, **SyncDeleteMode** Actor is added to the template. The purpose of **SyncDeleteMode** Actor is to set the population's Delete Mode. By default, the Actor sets the Delete Mode to OFF, which means the Delete Mode is taken from the LU table's properties. Additional values are All and NonUpdated.

  *  [Click for more information about LU table's Delete Mode](/articles/06_LU_tables/04_sync_methods.md#delete-mode-and-truncate-before-sync-properties).

* **Source** Stage, defines a query that retrieves source data using the **SourceDbQuery** Actor. The **SourceDbQuery** Actor inherits from the [DbCommand Actor](/articles/19_Broadway/actors/05_db_actors.md) and extends it with additional **parent_rows** and **size** input arguments.

  * The interface for the query's execution is selected from the list of Fabric [DB interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md). 

  * The **sql** input argument holds a query that is populated either automatically or should be added manually. A query can be validated in the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) by clicking **QB** in the **sql** input argument field. 

  * The **size** value is used to group the rows from **parent_rows** where each group is used to generate the WHERE clause for the provided SQL statement. The **size** is important for the Actor's performance as it generates less calls to the source DB.

  * The WHERE clause is generated automatically in the same way as for regular populations and is not visible in the Actor's UI. 

    For example, when the **sql** input argument displays the statement:

    ~~~sql
    SELECT * FROM CASES
    ~~~

    The SQL statement that will actually be executed in the server side is:

    ~~~sql
    SELECT * FROM CASES WHERE ACTIVITY_ID IN (...)
    ~~~

  * The **size** value determines the number of ACTIVITY_ID values included in the SQL.

  * Additional parameters can be added to the WHERE clause if needed. For example, to filter cases by their status.

  * The **SourceDbQuery** Actor supports non-prepared statement parameters. For example, to dynamically transfer a table or a column name to a query.

  * [Click here for more information about parameters support and non-prepared statement parameters](/articles/19_Broadway/actors/05_db_actors.md).

* **Stage 1**, a placeholder in the template that enables adding additional activities that can be performed on the data prior to loading it to the target DB. This feature is similar to using a [Root function](/articles/07_table_population/02_source_object_types.md) that is added in a regular population object.  

* **LU Table** Stage, defines the target LU table using the **DbLoad** Actor. 

  * The target **interface**, **schema**, **table** and INSERT, UPDATE or UPSERT **commands** are set using the Actor's input arguments. 
  * The [link type](/articles/19_Broadway/07_broadway_flow_linking_actors.md#link-object-properties) from the Query to the load is set as **Iterate** in order to enable looping over the query results.
  * Note that by default, **schema** and **table** input arguments are defined as an [External population type](/articles/19_Broadway/03_broadway_actor_window.md#actors-inputs-and-outputs) in order to enable populating these parameters dynamically. When required, a **Const** or **Link** population type can be defined. 

* **Post Load** Stage, a placeholder added to the template to indicate that additional activities can be performed after the data has been loaded to the target DB. This feature is  similar to using an [Enrichment function](/articles/10_enrichment_function/01_enrichment_function_overview.md). If it is not needed, this Stage can be deleted or left empty.

### How Do I Create a Population Based on a Broadway Flow?

The starting points for creating a population based on a Broadway flow are:

* [Auto Discovery Wizard](/articles/03_logical_units/06_auto_discovery_wizard.md), check the **Table population based Broadway flow** checkbox in step 2 of the Wizard.
* [LU Schema window](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-schema), either:
  * Right-click and select **New table from SQL based Broadway flow**.
  * Drag a DB table and select **Create table based Broadway flow**.
* Project Tree, right-click a table object and select **Create Table Population based Broadway Flow**.
* Reference, right-click and select **Create References from DB tables**.

The population is created as a template with predefined Stages and designated Actors. When creating a flow from the Auto Discovery Wizard or from the LU Schema, the input fields, interface, and SQL statement are added automatically based on the selected table's fields. Complete the missing information and if needed, update the flow and then connect the table population to the LU hierarchy via the LU Schema window.

Note that for the population to be effective on the server side, LU deployment is required. When running in debug mode, the deployment to debug is performed automatically.

[Click here for more information about deployment from the Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md).

### Support Instance ID Different From Source DB Column

Starting from Fabric V6.5.9, when populating the LU Root table, Broadway population supports having an LU Instance ID column name, which is different from the Source DB column name. To utilize this feature, take the following steps:

1. Change the Instance ID name in the LU Root table from the Source DB column name to another name. For example, from CUSTOMER_ID to ID. Note that the population’s input remains the same as the column name in the Source DB - CUSTOMER_ID in this example.

   ![](images/07_14_InstanceIdLU.png)

2. Update the CUSTOMER_ID column of the **DbLoad** Actor to ID, to correspond to the LU table's column name. Then, map the output of **SourceDbQuery** Actor to the new input of **DbLoad** Actor.

   ![](images/07_14_InstanceIdPop.png)



### Example of Creating a Population Based Broadway Flow

1. In the **DB Objects tab** of the **LU Schema**, drag the required table into the main area and click **Create Table based Broadway Flow**. The flow's template is created and includes the basic steps for retrieving  source data and loading it into the target. 

   ![image](images/07_14_01.PNG)



2. Connect the required input arguments of the **PopulationArgs** Actor to the relevant port of the parent table in the LU Schema. 


3. (Optional) Add the WHERE clause to the **sql** input argument of the **Query** Actor.

[Click here to display the examples of parameters support and non-prepared statement parameters](/articles/19_Broadway/actors/05_db_actors.md#examples).

[Click here to display an example of a population flow in the Demo project.](/articles/demo_project/README.md)

[![Previous](/articles/images/Previous.png)](13_LU_table_population_execution_order.md)
