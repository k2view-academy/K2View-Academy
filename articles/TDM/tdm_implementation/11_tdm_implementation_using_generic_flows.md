# Broadway Flows Implementation

The TDM library contains sets of generic flows that allow you to create a standard TDM implementation in just a few minutes. Once such implementation has been created, its flows can be edited and tailored to your project's needs.

## How Do I Create the TDM Broadway Flows?

.

## Step 2 - Create Sequences

[Add an explanation about the new inner flow in the load. Rerun of the TDMLuInitBasedOnFabric requires to override exsisting flows in order to generate the missing sequence flows]

It may be required to replace the loaded IDs (sequences) when populating a target database as a way to avoid collision with existing IDs. Setting and initiating sequences is mandatory in order to enable the [IDs' replacement](/articles/TDM/tdm_gui/17a_task_target_component_entities.md#replace-ids-for-the-copied-entities) in TDM tasks.

Fabric V8.2 has added the [**Catalog**'s sequence settings](/articles/39_fabric_catalog/10_catalog_settings.md) feature. TDM 9.3 and onwards supports the following two sequence methods: 

I. [Sequence handling based on Catalog](11a_tdm_sequence_implementation_based_on_catalog.md).

II. [Sequence handling without Catalog](11b_tdm_sequence_implementation_without_catalog.md). 

A new shared Global has been introduced in TDM 9.3, named **TDM_USING_CATALOG_SEQUENCES**. Its purpose is to set the default **sequence handling behavior** of TDM to either Catalog-based sequence or sequence handling without Catalog. This Global (which uses true/false setting) can be **added to an LU** for establishing the **LU's behavior**.

Example:
- The TDM project has the CRM, Billing, and Ordering LUs.
- By default, the sequences are handled without the Catalog, except the Billing LU for which sequences are Catalog-based.
- The TDM_USING_CATALOG_SEQUENCES Global must be set as follows:
  - Shared Global – set to false.
  -	Billing LU – set to true.

Notes: 
- A sequence can be shared between multiple LUs. For example, the subscriber_id is shared between the CRM and Billing LUs. In order to ensure referential integrity, all shared LUs must have the same sequence name regardless of their sequence handling method.

- Both of the above-mentioned sequence methods require the creation of the **k2masking** schema. The k2masking schema is created by the TDM deploy.flow. Alternatively, creating the k2masking schema can be done by running the **masking-create-cache-table.flow** from the Broadway Examples (found in the Broadway Flow window, Main Menu > Actions > Examples and select this flow). 

- Starting from Fabric V7.2, SQLite and PostgreSQL are also supported as System DBs. Setting them is done via the [internal_db section](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md#how-to-switch-to-sqlite-or-postgresql) of the Fabric config.ini file. Before deploying the TDM LU, verify that the **SEQ_CACHE_INTREFACE** Shared Global is set with the proper interface. By default, it is populated with **DB_CASSANDRA**. If you wish to create the k2masking schema on a PostgreSQL DB, set a PG DB interface name in the SEQ_CACHE_INTREFACE Global.

### Set the Sequence Report Global

A new Global - **TDM_SEQ_REPORT** - has been added to the Shared Globals in TDM 8.1. When set to **true** (the default value), the task execution populates the **TDM_SEQ_MAPPING** table and adds the **Replace Sequence Summary Report** to the [task execution report](/articles/TDM/tdm_gui/27_task_execution_history.md#generating-a-task-execution-summary-report).

For better performance, set the **TDM_SEQ_REPORT** Global to **false** to prevent populating the TDM_SEQ_MAPPING table and generating the **Replace Sequence Summary Report**. Note that the Replace Sequence Summary Report would not be available for a task that is executed with the TDM_SEQ_REPORT Global set as **false**.

## Step 3 - Create, Load and Delete Flows

In order to create the load and delete flows, run either:

I. [TDMLUInit](05_tdm_lu_implementation_general.md#ii-run-the-tdmluinit-flow) flow. Note that this flow is designed to run one time when creating an LU, and it also adds the TDM tables to the LU. If the LU already contains the TDM tables, it is recommended to run the **createAllFromTemplates** flow (see the below line) to add the target tables to the LU.

II. **createAllFromTemplates** flow. This flow:

- 1. Creates and adds the [delete entity's target LU tables](08_tdm_implement_delete_of_entities.md#lu-structure---target-tables) to the LU, if needed.

  2. Creates and adds the [data generation flows](16_tdm_data_generation_implementation.md) to the LU tables, if needed.

  3. Creates the delete and load flows based on the updated LU.  

- The flow gets the following input parameters:

  - **CREATE_DELETE_TABLES** - valid values: true/false. When true, the flow creates and adds the target tables to the LU in order to support the delete entity. The default value is false.

  - **LU_NAME** - the input LU must be deployed to Fabric debug server before running the createAllFromTemplates flow.

  - **TARGET_SCHEMA**

  - **TARGET_INTERFACE**

  - **OVERRIDE_EXISTING_FLOWS** - valid values: true/false. When set to **true**, the flow deletes and recreates existing objects. When set to **false**, the flow skips existing objects and creates new ones, if needed. This indicator is checked by all 3 parts of the flow (adding target LU tables, adding data generation flows, and adding the delete and load flows). The **default** value is **false**.

  - **TARGET_ENVIRONMENT**

  - **CREATE_GENERATE_FLOWS** - valid values: true/false. When true, the flow creates and adds data generation flows to the LU. The default value is false.

    

​	Notes: 


- If the target table name is not identical to the related LU table name, you must populate the mapping of the LU table name with the target table name in **TDMTargetTablesNames** Actor (imported from the TDM library) and redeploy the LU to the Fabric debug server before running the **createAllFromTemplates** flow.

- From Fabric 7.1 onwards, physical folders are supported in the Studio. Therefore, the flows are created under dedicated directories. If your LUs already have load and delete flows, you need to move your existing flows and locate them in the following folders, in order to enable overriding them:

  - Load flows: LoadFlows
  - Delete flows: DeleteFlows

- From TDM 8.1 onwards, the generated delete flows do not require a manual updating of the Get Table Data SQL query and the Delete key.

###  Load and Delete Flows

The createAllFromTemplates creates a separate flow per table on each type - load and delete. Moreover, it creates a *load all* flow to run all table-level load flows in the right order, and a *delete all* flow to run all table-level delete flows in the right order.

The sequence Actors are added automatically to the load flows based on the **TDMSeqSrc2TrgMapping** table.

From TDM 9.3.1 onwards, the **CatalogMaskingMapper** Actor is added to the load flows as a way to enable [Catalog-based sequence](11a_tdm_sequence_implementation_based_on_catalog.md) handling.

Additionally, the **createAllFromTemplates** flow adds the **setTargetEntityId_Actor** to the load flow of the **main target table** in order to populate the **TARGET_ENTITY_ID** key with the target entity ID. 

### Debug the Load and Delete Flows

You can run each one of the load flows in debug mode. Normally, when running a task, the **InitiateTDMLoad_Actor** gets the task's attributes and sets the execution parameters accordingly. When running a load flow in a debug mode without executing a TDM task, the **InitiateTDMLoad_Actor** sets the execution's parameters based on the TDM Globals. 

## Step 4 - TDM Orchestration Flows

### TDMOrchestrator Flow

The **TDMOrchestrator** flow orchestrates all the processes related to the **executed entity** in **one single flow** and in **one transaction**. A separate TDMOrchestrator flow runs on each task's LU. This flow includes the invocation of all steps such as:

- Initiates the TDM task execution keys.
- Syncs the entity into the Fabric, if needed.
- Deletes the entity from the target environment, if the task requires it.
- Loads the entity to the target, if the task requires it.
- Performs [error handling and statistics gathering](12_tdm_error_handling_and_statistics.md). 

Note that from TDM 7.5.1 onwards, the TDM excludes Fabric from the transaction using the new Fabric 6.5.8 Broadway Actor: NoTx. This fix is needed for the entity clone as all replicas work on **one** single LUI. Fabric cannot open parallel transactions on the same LUI and therefore needs to be excluded from the delete and load Broadway transaction in order to have better parallelism when processing the entity’s replicas.

TDM 8.1 added the **TDMOrchestrator.flow** to the Shared Objects, thus avoiding the need to generate this flow on each LU separately. 

### TDMReserveOrchestrator Flow

The **TDMReserveOrchestrator** runs the [reserve only tasks](https://github.com/k2view-academy/K2View-Academy/blob/Academy_8.1/articles/TDM/tdm_gui/17a_task_target_component_entities.md#reserve). Import the flow from the TDM library into the Shared Objects and redeploy the TDM LU. 



## Step 6 - Optional - Get the Entity List for a 'Select a predefined entity list' Task’s Selection Method

The entity list of the full entity subset can be generated by either using an SQL query on the source DB or running a Broadway flow. A Broadway flow is needed when running an extract task on a [non-JDBC data source](14_tdm_implementation_supporting_non_jdbc_data_source.md).  

### Implementation Guidelines

Create a Broadway flow under the related root LU or the Shared Objects. It is recommended to locate the Broadway flow under the Shared Objects to enable running the flow on several root LUs of a given Business Entity. The Broadway flow must include the following stages: 

- Stage 1: Get the list of entities.
- Stage 2: Call the **insertToLuExternalEntityList** Actor (imported from the TDM library) in a loop (iteration) to insert all entities into the entity table created in the TDM DB:
   - Set the input LU_NAME to be external and get its value from the task execution process.  
   - Set a [Transaction](/articles/19_Broadway/23_transactions.md#transaction-in-iterations) in the loop to have one commit on all iterations.  


Populate the Broadway flow in the [MigrateList MTable](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#migratelist).

Redeploy the related LUs and the TDM LU.



### How does the Broadway Flow Generate an Entity List for the Task Execution? 

The TDM library provides a list of Broadway Actors and flows to support generating an entity list by a project's Broadway flow. The project's Broadway flow gets the entity list and calls the TDM library Actors to insert them into a dedicated table in the TDM DB. A separate entity table is created upon a task execution and has the following naming convention: `entity_list_<task exe_id>`. 

The [TDM task execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md) runs the [batch process](/articles/20_jobs_and_batch_services/11_batch_process_overview.md) on entities in the entity table that are a part of the current task execution, having the current task execution id. The table is dropped at the end of the task execution.

Note that previous TDM versions populated the entities into a dedicated Cassandra table in **k2view_tdm** keyspace. From TDM 8.1 onwards, the entity table is created in the TDM DB.

### Debugging the Broadway Flow

1. Run the **createLuExternalEntityListTable** TDM flow (imported from the TDM library) and populate the input **taskExecutionId** parameter to create the entity table in the TDM DB.
2. Populate the input parameters and run the customized Broadway flow. 



##  Step 7 - Optional - Build Broadway Flows for the [Custom Logic ](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#custom-logic) Selection Method

You can build one or multiple Broadway flows to get a list of entities for a task execution. These Broadway flows are executed by the TDM task execution process, building the entity list for the task. 

### Custom Logic - TDM 8.1 Improvements

TDM 8.1 enables **2 execution modes** for the Custom Logic flows:

1. **Direct Call** - a newly added mode, where the batch process calls the Custom Logic flow directly, **getting the entity list without pre-populating the entities in a dedicated table**. This approach is **available only when the flow is based on one single DbCommand**, i.e., runs one Select query to get the required entities, and the **Business entity has only one root LU**.

   The Direct Call mode performs rather better: It does not need to complete the population of all entities in a predefined table before starting the task execution. The task execution consumes the output cursor of the Select statement and executes the task on any chunk of consumed entities. Due to this behavior, **the Direct Call mode does not fit a Business Entity with multiple root LUs that must run on the same entity list**.

2. **Indirect call** - the indirect call **creates and populates a dedicated table in the TDM DB**. The table is created per execution with the following naming convention: `entity_list_<task exe_id>`. The task execution's batch process runs a Select query from the newly created table to get the task's entities. The table is dropped from the DB when the task execution is completed.  

   Note that previous TDM versions populated the entities into a dedicated Cassandra table in **k2view_tdm** keyspace. From TDM 8.1 onwards, the entity table is created in the TDM DB.

#### CustomLogicSql Flow

A new generic Custom Logic flow has been added to the TDM library in TDM 8.1 - **CustomLogicSql**. This flow gets an SQL query to run on a given DB interface. 
Edit the flow in order to use it in the TDM tasks:
 - Populate the **interface** input parameter in the **Run Input SQL** Actor (currently it is defined as an empty linked field).
 - It is recommended to update the external name of the **sql** input parameter in the **Run Input SQL** Actor to a meaningful name (currently it is populated with SQL). For example, SQL_query_on_CRM. 
 - Add the CustomLogicSql flow to the **CustomLogicFlows** Actor. Populate the new record as follows:
   -  LU_NAME: optional. Can be left empty.
   -  FLOW_NAME: CustomLogicSql
   -  DESCRIPTION: populated with a free text.
   -  DIRECT_FLOW: true
  - Redeploy the Web Services to Fabric.
  - If the LU_NAME field is populated with an LU name, redeploy the LU name to Fabric. Else (if the LU_NAME field is empty), redeploy the TDM LU to Fabric.
    

The following parameters can be set by the user that creates the task:

- **sql** - mandatory parameter defining the Select query to run on the TDM DB and to get the task's entity list.
- **sqlParams** - optional parameter to set parameters for the Select query. You can set multiple parameters separated by a comma.

The customLogicSql flow runs in a **direct call** mode. 

##### Examples of an input SELECT query:

1. Populating both parameters - the **sql** and the **sqlParams**: 

   - **sql**: 

     select distinct cust.customer_id from customer cust, activity act, cases cs  where cust.customer_id = act.customer_id and act.activity_id = cs.activity_id and cs.status = ?  and cs.case_type = ? 

   - **SqlParams:**

     Open,Billing Issue

2. Populate only the **sql** parameter: 

   - **sql:**

     Select Distinct act.customer_id From activity act,   cases ca Where act.activity_id = ca.activity_id And ca.status <> 'Closed' And ca.case_type  in  ('Device Issue', 'Billing Issue');



### Step 7.1 - Create a new Custom Logic Flow

The Custom Logic Broadway flow can be created in either the **Shared Objects** or **a given LU**.

The Custom Logic Broadway flow always has **2 external input parameters** and it gets their values from the task execution process:

- LU_NAME
- NUM_OF_ENTITIES - the maximum number of entities to be processed by the task execution. The number is set in either the task or the task's [overridden parameters](/articles/TDM/tdm_architecture/04_task_execution_overridden_parameters.md#overriding-additional-task-execution-parameters).

TDM supports the creation of **additional external parameters** in the flow, enabling the user to send the values of these parameters in the TDM task; e.g., you can add an external parameter name - customer_status - to the flow. The flow selects the customers for the task based on the customer_status input parameter. This way you can filter the selected customers by their status and still use the same flow to select them.

**Notes:** 

- The input parameter name must **not contain spaces or double quotes**.

- TDM 8.0 added an integration of **Broadway editors** into the TDM portal when populating either the data generation parameters or the Custom logic parameters in the task’s tabs. This integration enables the user to select a valid value from a list, to set dates, and to set distributed parameters. 

  Click [here](15_tdm_integrating_the_tdm_portal_with_broadway_editors.md) for more information about the TDM integration with the Broadway editors and related implementation instructions.

- Sending multiple values in one single parameter - you can define a String input parameter in order to get a list of values into the parameter and split it into an array in the flow, e.g., "CA,NY". The Broadway flow can split this String by the delimiter. The values must be delimited by the delimiter, which is set in the split Actor in Broadway flow.

- You can get an input Select statement with binding parameters. The parameters' values can be either sent into a separate input parameter or added to the Select statement. See the [CustomLogicSql flow's examples](#examples-of-an-input-select-query) above.

  

### Custom Logic High-Level Structure

#### Direct Call Flow

The [direct call](#custom-logic---tdm-81-improvements) Custom Logic flow must have the following structure:

![direct call structure](images/direct_call_custom_logic_structure.png)

1. Init - calls the **TDMSetSessionGlobals** Actor to run the initial setting for the custom logic flow execution. The SESSION_GLOBALS input parameter must be defined as an external parameter. The external parameter name must be SESSION_GLOBALS.

2. **DbCommand** - defines the Select statement to select the task's entities. The Select statement must return only the entity IDs. 

3. **customLogicDirectFlowUtil** - filters out the reserved entities if needed,  and formats the entity IDs for the task execution:
   - Set the **NUMBER_OF_ENTITIES** input parameter to be external.
   - Link the DBCommand result to the **input values** parameter.
   - Set the **innerFlowClose** input parameter to **false** in order to support the streaming of the resultSet by the inner flow and avoid the Broadway limitation of the maximum number of records (set to 100K by default).
   - The **output values** parameter must be external.


#### Indirect Call Flow

- **Stage 1**: 

  - Add a logic, requiring the entities - for example, a DbCommand Actor that runs a Select statement on the CRM DB. The Actor needs to return the list of the selected entity IDs. 
  - Initialize the entities' number counter for execution - add the **InitRecordCount** TDM Actor (imported from the TDM library).
  - Notes: 
      - If the flow needs to get an array of parameters, it is recommended to define the external input parameter as a String and add a **Split** Actor to the flow in order to split the values by the delimiter and populate them into a String's array.
      - It is recommended to add a limit to the SQL query if you do not need to filter out reserved entities when running this flow. This way the query returns a limited size of records.

- **Stages 2-4**: **Loop on the selected entities** - set a [Transaction](/articles/19_Broadway/23_transactions.md#transaction-in-iterations) in the loop in order to have one commit for all iterations: 

  1. Stage 2: Set the selected entity ID - returned by the Actor of Stage 1 - to a String using the **ToString** Actor.

  2. Stage 3: Call **CheckReserveAndLoadToEntityList** TDM Broadway flow (imported from the TDM library):

     - **Input** - **LU_NAME** parameter. This is an **external parameter** and it gets its value by the task execution process.
     - **Output** - **recordLoaded**. This is the entity number counter, loaded into the entity table.
     - This flow executes the following activities on each selected entity ID:
   - Checking whether the entity is reserved for another user in the task's target environment when running a load task without a sequence replacement, a delete task, or a reserve task. If the entity is reserved for another user, it skips it, as it is unavailable.
   - Loading the available entities into the entity table in the TDM DB and updating the entity number counter.

  3. Stage 4: Calls **CheckAndStopLoop** TDM Actor (imported from the TDM library). Set the **NUM_OF_ENTITIES** to be an **external input parameter** to get its value from the task execution process. It checks the number of entities inserted to the entity table, and stops the loop if the custom flow reaches the task's number of entities. 

     **Example**:

     The task needs to get 5 entities. The Select statement gets 20 entities. The first 2 selected entities are reserved for another user. The 3rd, 4th, 5th, 6th, and 7th entities are available and are populated in the entity table; the entity loop then stops.


Below are examples of a Custom Logic flow:

**Example 1 - get the Contract Status as an input parameter and build the Select statement accordingly:** 

![custom logic](images/custom_logic_example.png)



**Example 2 - get an input String of States, separated by a comma. Split the input String into an array and send it to the SQL query**:

An example of US states input: 

- NY,CA

![custom logic](images/custom_logic_example_2.png)



**Example 3 - get an input Select statement with parameters for the Select statement:**

![custom logic](images/custom_logic_example_3.png)

Note: When exposing the SQL statement as an external parameter for the user, verify that it runs on a read-only DB connection; this would prevent a DB update.

### Debugging the Customized Flow

1. Run the **createLuExternalEntityListTable** TDM flow (imported from the TDM library) and populate the input **taskExecutionId** parameter to create the entity table in the TDM DB.
2. Populate the input parameters and run the customized flow. 

### Step 7.2 - Populate the Custom Logic Flow in the Custom Logic Table

Add the LU name and Custom Logic flow name to the **CustomLogicFlows** constTable TDM Actor (imported from the TDM library).

View the below example:

![custom logic](images/custom_logic_table_example.png)



Check the **DIRECT_FLOW** checkbox to enable a [direct call](#custom-logic---tdm-81-improvements) of the Custom Logic flow.

Redeploy the Web Services.

[![Previous](/articles/images/Previous.png)](10_tdm_generic_broadway_flows.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](12_tdm_error_handling_and_statistics.md)



