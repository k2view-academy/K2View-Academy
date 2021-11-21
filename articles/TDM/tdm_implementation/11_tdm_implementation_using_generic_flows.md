# Broadway Flows Implementation

The TDM library has sets of generic flows that allow you to create a standard TDM implementation in just a few minutes. Once a standard implementation has been created, its flows can be edited and tailored to your project's specific requirements.

## How Do I Create TDM Broadway Flows?

### Step 1 - Define Tables to Filter Out

Before beginning to create Broadway flows, define the tables that are filtered out during the DELETE and LOAD flows. The library includes settings for the following filtered auxiliary tables:

![image](images/11_tdm_impl_actor_1.PNG)

This setting is implemented using the **TDMFilterOutTargetTables** Actor. To filter more tables, open the **TDMFilterOutTargetTables** Actor and edit its **table** object. The **lu_name** column should be populated as follows:

* ALL_LUS, when a filtered table is relevant for all TDM LUs.
* [LU name], when a table belongs to a specific LU. In some cases you may need to add tables to the LU schema to get the child IDs and populate the TDM_LU_TYPE_RELATION_EID TDM DB table. For example, add the Orders table to Customer LU to get the list of customer's orders. These tables need to be added to **TDMFilterOutTargetTables** Actor to avoid creating load or delete flows for them since they are loaded or deleted by the child LUs. 

After the Actor's update is completed, refresh the project by clicking the ![image](images/11_tdm_refresh.PNG) button on top of the project tree to apply the changes in the **TDMFilterOutTargetTables** Actor and deploy the LU. In this example, the **TDMFilterOutTargetTables** Actor will look like this: 

![image](images/11_tdm_impl_actor_2.PNG)



### Step 2 - Create Sequences

Since sequences are required when populating a target database, setting and initiating sequences are mandatory when creating a TDM implementation. 

If the **k2masking** keyspace does not exist in the DB interface defined for caching the masked values, create it using the **masking-create-cache-table.flow** from the library of Broadway examples or the **create_masking_cache_table.sql** of the TDM Library.  

Note that the k2masking can also be created by the deploy.flow of the TDM LU.

Do the following to create the sequences for your TDM implementation:

#### Generate the Sequence Actors

A. The TDM library includes a **TDMSeqList** Actor that holds a list of sequences. Populate the Actor's  **table** object with the information relevant for your TDM implementation as follows:
   - **SEQUENCE_NAME** - the sequence name must be identical to the DB's sequence name if the next value is taken from the DB.
   - **CACHE_DB_NAME** - populate this setting using **DB_CASSANDRA** where the Sequence Cache tables are stored.
   - **SEQUENCE_REDIS_OR_DB** - indicates if the next value is taken from Redis or the target DB interface. Populate this setting using the **FabricRedis** interface (imported from the TDM library) or with the **target DB interface name**. Getting the next value from the DB sequence is supported for Oracle, DB2 and PostgreSQL DBs. 
   - **INITIATE_VALUE_OR_FLOW** - set an initial value for the sequence or populate the name of an inner flow to apply logic when getting the initial value. For example, you can set the initial value from the max value of the target table. The initial value is only relevant when getting the next value from **FabricRedis**. Otherwise, the next value is taken from the target system.

   Example of the **TDMSeqList** Actor:

   ![image](images/tdmSeqListExample.png)

   Example of an inner flow to get the initial sequence value:

   ![image](images/CustomerIdInitFlow.png)

   The table's values are used by the **createSeqFlowsOnlyFromTemplates** flow that generates the Sequences Actors. 

   After the Actor's update is completed, refresh the project by clicking the ![image](images/11_tdm_refresh.PNG) button on top of the project tree to apply the changes in the **TDMSeqList** Actor and deploy the **TDM LU**.

B. Run **createSeqFlowsOnlyFromTemplates.flow** from the Shared Objects ScriptsforTemplates folder. The flow has two [Inner Flows](/articles/19_Broadway/22_broadway_flow_inner_flows.md) that first create a Broadway flow for each sequence in the **TDMSeqList** Actor and then create an Actor from each flow. The generated sequence flows invoke the [MaskingSequence Actor](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) to get the new sequence value and populate the source and target IDs in the TDM_SEQ_MAPPING table under the k2masking keyspace.

   Note that this flow should run once per TDM implementation and not per each LU since the sequences are used across several LUs in the TDM project.
   The sequences flows and Actors are created under **Shared Objects** to enable several LUs to use a Sequence Actor.



#### Populate the Sequence Mapping Table to Add the Sequence Actors to the Load Flows

The **TDMSeqSrc2TrgMapping** table has been added in tdm 7.3 to automatically add the sequence actors to the load flows. Populate **TDMSeqSrc2TrgMapping** table to map between the generated sequence actors and the target tables' columns. A sequence actor can be mapped into different table and different LU.

See example:

![seq mapping](images/tdmSeqSrc2TrgMapping_example.png)

#### Customize the Sequence Logic
Fabric 6.5.3 supports sending a [category](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md#how-do-i-set-masking-input-arguments) parameter to the masking actors.
It enables to create your own function or Broadway flow to generate a new ID using the **MaskingLuFunction** or **MaskingInnerFlow** actors in the Sequence actor. Set the category to **enable_sequences** to use the actor for sequence (ID) replacement. The [TDM task execution processes](/articles/TDM/tdm_architecture/03_task_execution_processes.md) set the **enable_masking** and **enable_sequences** session level keys to **true** or **false** based on the TDM task's attributes. The masking actors generate a new ID (sequence) if the task requires a sequence replacement and the TDM process sets the **enable_sequences** session level keys to **true**. If the task does not requir a sequence replacement, the original value is returned by the masking actors.

Click for more information about [customizing the replace sequence logic](/articles/19_Broadway/actors/08_sequence_implementation_guide.md#custom-sequence-mapping).

### Step 3 - Create, Load, and Delete Flows

In this step you will run the generic **createFlowsFromTemplates.flow** from the Shared Objects Broadway folder to create the delete and load flows under the LU. The flow gets the following input parameters:

-  **LU name**
- **Target Interface**
- **Target Schema**
- **Override Existing Flows**: when set to **true**, the flow deletes and recreates existing load and delete flows. When set to **false** the flow skips existing load and delete flows and only create new flows if needed. The **default** value is **false**.

The **createFlowsFromTemplates.flow** executes the inner flows listed below. These inner flows generate the load and delete flows. The LU source table names must be identical to the table names in the target environment to generate the load and delete flows with the correct table names: 

**A. Create a LOAD flow per table**

Performed by the **createLoadTableFlows.flow** that receives the Logical Unit name, target interface and target schema and retrieves the list of tables from the LU Schema. It then creates a Broadway flow to load the data into each table in the target DB. The name of each newly created flow is **load_[Table Name].flow**. For example, load_Customer.flow. The tables defined in Step 1 are filtered out and the flow is not created for them.

#### Update the Load Flows with the Sequence Actors: 
The sequence actors are added automatically to the load flows based on the **TDMSeqSrc2TrgMapping** table.

In addition, the  **createFlowsFromTemplates.flow** adds the **setTargetEntityId_Actor** to the Load flow of the **main target table** to populate the **TARGET_ENTITY_ID** key by the target entity ID. For example, add the  **setTargetEntityId_Actor** to **load_cases** flow and send the target case ID as an input parameter to the actor:  

   ![image](images/loadFlow_seq_example.png)



**B. Create the main LOAD flow**

Performed by the **createLoadAllTablesFlow.flow** that receives the Logical Unit name and creates an envelope **LoadAllTables.flow** Broadway flow. The purpose of this flow is to invoke all LOAD flows based on the LU Schema's execution order.

**C. Create a DELETE flow per table**

Performed by the **createDeleteTableFlows.flow** that receives the Logical Unit name, target interface, and target schema and retrieves the list of tables from the LU Schema. It then creates a Broadway flow to delete the data from this table in the target DB. The name of each newly created flow is **delete_[Table Name].flow**. For example, delete_CUSTOMER.flow. The tables defined in Step 1 are filtered out and the flow is not created for them. 

The following updates must be performed manually:

* Populate the **sql** input argument of the **Get Table Data** Actor with the SELECT query that retrieves the keys of the data to be deleted. For example, in the delete_ACTIVITY.flow, write the following query since the CUSTOMER_ID and ACTIVITY_ID are the keys of the ACTIVITY table.

  ~~~sql
  SELECT CUSTOMER_ID, ACTIVITY_ID FROM TAR_ACTIVITY;
  ~~~

  ![images](images/11_tdm_impl_delete1.PNG)

* Populate the **keys** input argument of the **DbDelete** Actor. These should correlate with the table's keys.

  ![images](images/11_tdm_impl_delete2.PNG)

**D. Create the main DELETE flow**

Performed by the **createDeleteAllTablesFlow.flow** that receives the Logical Unit name and creates an envelope **DeleteAllTables.flow** Broadway flow. The purpose of this flow is to invoke all DELETE flows in the opposite order of the population order, considering the target DB's foreign keys. 


### Step 4 - Create the TDMOrchestrator.flow from the Template

Once all LOAD and DELETE flows are ready, create an orchestrator. The purpose of the **TDMOrchestrator.flow** is to encapsulate all Broadway flows of the TDM task into a single flow. It includes the invocation of all steps such as:

* Initiate the TDM load.
* Delete the target data, if required by the task's [operation mode](/articles/TDM/tdm_gui/19_load_task_request_parameters_regular_mode.md#operation-mode) or the [Data Flux load task](/articles/TDM/tdm_gui/20_load_task_dataflux_mode.md).
* Load the new data into the target, if required by the task's [operation mode](/articles/TDM/tdm_gui/19_load_task_request_parameters_regular_mode.md#operation-mode) or the [Data Flux load task](/articles/TDM/tdm_gui/20_load_task_dataflux_mode.md). 
* Manage the TDM process as one transaction.
* Perform [error handling and gather statistics](12_tdm_error_handling_and_statistics.md). 

The **TDMOrchestrator.flow** should be created from the Logical Unit's Broadway folder and is built for each Logical Unit in the TDM project. [Deploy the Logical Unit](/articles/16_deploy_fabric/01_deploy_Fabric_project.md) to the debug server and then create the Orchestrator flow using a template as follows:

![image](images/11_tdm_impl_02.PNG)

### Step 5 - Mask the Sensitive Data

TDM systems often handle sensitive data. To be compliant with data privacy laws, Fabric enables masking sensitive fields like SSN, credit card numbers and email addresses before they are loaded either to Fabric or into the target database.

* To mask a sensitive field prior to loading it into Fabric, create a Broadway population flow for the table that includes this field and add one or more **Masking** actors. 

  ![image](images/11_tdm_impl_05.PNG)

  If the masked field is used as an [input argument](/articles/03_logical_units/12_LU_hierarchy_and_linking_table_population.md) that is linked to another LU table, add the masking population that masks the fields in all LU tables to the last executed LU table in order to have the original value when populating the LU tables. 
  
* To mask a sensitive field as part of a load to the Target DB, add a masking actor to the relevant **load_[Table Name].flow**. The TDM infrastructure controls enabling or disabling masking based on the settings of the global variables. 

  There are three possible scenarios for handling masking:

  * When the TDM task is for synthetic data creation, masking is always enabled.
  * When The TDM task is for Data Flux, masking is always disabled.
  * In all other scenarios masking behavior depends on the MASK_FLAG settings.
  
* Note that TDM 7.3 creates only **one LUI instance for all clones** when executing a task with a **Synthetic** selection method (entity cloning). Add a masking on both processes (LUI Sync and the Load flows) to get a different data in the masked fields on each clone.

[Click here to learn how to use Masking Actors](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md#).

[Click here to learn how the TDM task execution process builds the entity list](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md).

### Step 6 - Optional - Get the Entity List for an Extract All Task Using a Broadway Flow

The entity list of the full entity subset can be generated using an SQL query on the source DB or running a Broadway flow. A Broadway flow is needed when running an extract on a non JDBC data source.  

Create a Broadway flow under the related root LU or the shared objects. It is recommended to locate the Broadway flow under the shard objects to enable running the flow on several root LUs of given Business Entity. The Broadway flow must include the following stages: 
- Stage 1: Get the list of entities.
- Stage 2: Call the **insertToLuExternalEntityList**  Actor (imported from the TDM library) in a loop (iteration) to insert all entities into an entity list Cassandra table:
   - Set the input LU_NAME to be external and get its value from the task execution process.  
   - Set a [Transaction](/articles/19_Broadway/23_transactions.md#transaction-in-iterations) in the loop to have one commit all iterations.  


Populate the Broadway flow in the [trnMigrateList](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnmigratelist) translation.

Redeploy the related LUs and the TDM LU.

#### How does the Broadway Flow Generate an Entity List for the Task Execution? 

The TDM library provides a list of Broadway actors and flows to support a generation of an entity list based on a project Broadway flow. The project Broadway flow gets the entity list and calls the TDM library actors to insert them into a dedicated Cassandra table in **k2_tdm** keyspace. A separate Cassandra entity table is created on each LU and has the following naming convention: [LU_NAME]_entity_list. 

The [TDM task execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md) runs the [batch process](/articles/20_jobs_and_batch_services/11_batch_process_overview.md) on the entities in the Cassandra table that belong to the current task execution (have the current task execution id).

Click [here](14_tdm_implementation_supporting_non_jdbc_data_source.md) fore more information about TDM implementation on non JDBC Data Source.

 

 

[![Previous](/articles/images/Previous.png)](10_tdm_generic_broadway_flows.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](12_tdm_error_handling_and_statistics.md)



