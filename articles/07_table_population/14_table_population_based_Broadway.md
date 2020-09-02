# Table Population Based on a Broadway Flow

[Broadway](01_broadway_overview.md) is a Fabric module that is used to design data movement, its transformation and the orchestration of business flows. A [Broadway flow](/articles/19_Broadway/02a_broadway_flow_overview.md.md) is a core Broadway object that represents a business process and is built from several [Stages](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP2_99_BROADWAY/articles/19_Broadway/19_broadway_flow_stages.md) where each Stage includes one or more [Actor](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP2_99_BROADWAY/articles/19_Broadway/03_broadway_actor.md).

A table population can be created based on a Broadway flow. The advantage of using a Broadway flow for table population rather than a source object based population, is to streamline the logic and all related validations into one business process to improve the project's maintainability.

<!--Tali- please add a cross reference from the population overview item to this item-->

### How Do I Create a Population Based on a Broadway Flow?

The triggers for creating a population based on a Broadway flow are the same as for [creating any new table population](03_creating_a_new_table_population.md) but using **Create Table Population based Broadway Flow** option.

For example:

1. Right click the table name under the **Project Tree** and then click **New Table Population based Broadway Flow** to open the Population Name popup.

2. Enter the population name and click **OK** to open a Broadway flow window. The flow template is created and includes the basic steps for retrieving  source data and loading it into the target. The template can be modified according to the project's requirements.

   ![image](images/07_14_01.PNG)


<!--Tali- you need to mention that you've added the input fields to the input actor. In addition- how can I mark input fields as input arguments that can be linked to a parent LU table? -->

### How Do I Use a Flow Population Template?

A Broadway population flow template includes predefined Stages and designated Actors and can be modified by adding more Actors when needed. 

A population flow template has the following Stages:

* **Input** Stage, defines the population's input arguments using a designated **PopulationArgs** Actor.
<!-- Tali- please add an explanation about the inout fields, and the output parent rows -->
* **Source** Stage, defines a query that retrieves source data using the **SourceDbQuery** Actor. The interface for the query's execution is selected from the list of Fabric [DB interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md). A query can be validated in the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) by clicking **QB** in the **sql** input argument field of the Actor. The **SourceDbQuery** Actor inherits from the [**DbCommand** Actor](05_db_actors.md) and extends it with additional **parent_rows** and **size** input arguments whereby improving the Actor's performance and less calls to the source.
<!-- Tali -please add a more detailed explanation about the size. In addition- does it add a where statement automatically to the select like Fabric does for a DbQuery?. Also- how can we use parameters in the query? Please add a screenshot with an exapmple for a source query that has a where statement with parameters.  -->

* **Stage 1**, an empty Stage added to the template to indicate that additional activities can be performed on the data prior to loading it to the target DB. 
* **LU Table** Stage, defines the target LU table using the **DbLoad** Actor. The target interface, table and INSERT, UPDATE or UPSERT commands are set using the Actor's input arguments. The [link type](/articles/19_Broadway/07_broadway_flow_linking_actors.md#link-object-properties) from the query to the load is set as **Iterate** to enable looping over the query results.
* **Post Load** Stage, an empty Stage added to the template to indicate that additional activities can be performed after the data has been loaded to the target DB. This is similar to the functionality of [Enrichment functions](/articles/10_enrichment_function/01_enrichment_function_overview.md) that are executed on an [LU table's](/articles/06_LU_tables/01_LU_tables_overview.md) data after it has already been populated from a source object.
<!-- Tali- does the post load run after the LU table is loaded or after ALL LU tables are populated like an enrichment function?? -->

[Click for more information about Broadway and its building blocks](/articles/19_Broadway/README.md).

[![Previous](/articles/images/Previous.png)](13_LU_table_population_execution_order.md)
