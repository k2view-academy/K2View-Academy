# Table Population Based on Broadway Flow

[Broadway](01_broadway_overview.md) is the Fabric module that is used to design data movement, its transformation and the orchestration of business flows. A [Broadway flow](/articles/19_Broadway/02a_broadway_flow_overview.md.md) is a core Broadway object that represents a business process and it is built from several [Stages](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP2_99_BROADWAY/articles/19_Broadway/19_broadway_flow_stages.md) where each Stage includes one or more [Actor](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP2_99_BROADWAY/articles/19_Broadway/03_broadway_actor.md).

A table population can be created based on a Broadway flow. The advantage of using a Broadway flow for table population rather than a source object based population is to streamline the logic and all the related validations into one business process and by that to improve the Project maintainability.

### How Do I Create a Population Based on Broadway Flow?

The trigger points for creating a population based on a Broadway flow are the same as for [creating any new table population](03_creating_a_new_table_population.md) but using **Create Table Population based Broadway Flow** option.

For example:

1. Right click the table name under the **Project Tree** and then click **New Table Population based Broadway Flow** to open the Population Name  popup.

2. Enter the population name and click **OK** to open a Broadway flow window. The population flow template is created and it includes the basic steps for retrieving the source data and loading it into the target. The template can be modified per the Project's needs and requirements.

   ![image](images/07_14_01.PNG)



### How Do I Use a Flow Population Template?

Broadway mechanism creates a template of a population flow that includes predefined Stages and uses designated Actors. This template can be modified by adding more Actors if needed. 

The population flow template includes the following Stages:

* **Input** Stage defines the population input arguments using a designated **PopulationArgs** Actor.
* **Source** Stage defines a query to retrieve the source data using **SourceDbQuery** Actor. The interface for query execution is selected from the list of Fabric [DB Interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md). You can validate the query using the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) via **QB** button in the **sql** input argument field of the Actor. The **SourceDbQuery** Actor inherits from [**DbCommand** Actor](05_db_actors.md) and extends it with additional input arguments **parent_rows** and **size** improving the Actor's performance with less calls to the source.
* **Stage 1** is an empty Stage which is added to the template in order to indicate that additional activities can be performed on the data prior to loading it to the target DB. 
* **LU Table** Stage defined the target LU Table using the **DbLoad** Actor. The target interface, table and the command (INSERT, UPDATE or UPSERT) are set using the Actor's input arguments. The [link type](/articles/19_Broadway/07_broadway_flow_linking_actors.md#link-object-properties) from the query to the load is set as **Iterate**, to enable the loop over the query results.
* **Post Load** Stage is an empty Stage which is added to the template in order to indicate that additional activities can be performed after the data has been loaded to the target DB. This is similar to the functionality of [Enrichment functions](/articles/10_enrichment_function/01_enrichment_function_overview.md) which are executed on [LU table's](/articles/06_LU_tables/01_LU_tables_overview.md) data after it has already been populated from a source object.

[Click for more information about Broadway and its building blocks](/articles/19_Broadway/README.md).

[![Previous](/articles/images/Previous.png)](13_LU_table_population_execution_order.md)