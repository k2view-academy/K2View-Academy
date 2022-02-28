# TDM - Deleting Entities

Every LU must include the ability to Delete Entities from a target environment to support the following load tasks:

- Delete task, the task operation mode is [Delete and Load Entity](/articles/TDM/tdm_gui/19_load_task_request_parameters_regular_mode.md#delete-and-load-entity) or [Delete Entity without Load](/articles/TDM/tdm_gui/19_load_task_request_parameters_regular_mode.md#delete-entity-without-load). 
- [Data Flux](/articles/TDM/tdm_gui/18_load_task_data_versioning_mode.md) task, the entities are deleted and reloaded to the target environment.

Note that if there is no need to support a delete of entities or Data Flux load tasks, the target tables do not need to be added to the LUs.

## LU Structure - Target Tables

Each LU has two main branches that are linked to the **FABRIC_TDM_ROOT** root table:

- **Source branch**, LU tables that extract an entity's source data. Source LU tables are populated except for [delete only tasks](/articles/TDM/tdm_gui/19_load_task_request_parameters_regular_mode.md#delete-entity-without-load) where no data is extracted from the data sources.
- **Target branch**, LU tables that extract the target keys of an entity. The keys are extracted from the **target environment** when the task needs to delete the entities from the target environment.

The target table contains the list of target IDs (keys) required to delete the data of the selected entities from the target environment and populate [TDM_LU_TYPE_REL_TAR_EID](06_tdm_implementation_support_hierarchy.md#tdm_lu_type_rel_tar_eid) with the target children IDs. It is recommended to add the **TAR_** prefix to each target table. 

Note that a given target LU table can be used to delete multiple target tables.

**Example:**

The Customer_ID key can be used to delete Customer, Contract, and Address target tables. Define the TAR_CUSTOMER table to hold the target Customer_ID and use the TAR_CUSTOMER to delete the target tables.

### Adding a Decision Function to the Target LU Tables

The target LU tables must be populated when running a TDM task that deletes the entities from the target environment. The target keys must be extracted from the target environment to enable deleting entities and their related data.

If the task does not delete the entities from the target environment, the target keys do not need to be extracted from the target environment.

The Broadway [InitiateTDMLoad](10_tdm_generic_broadway_flows.md#initialization) flow sets the **TDM_DELETE_BEFORE_LOAD** Global to **true** if the task must **delete the entities** from the target. Otherwise, this Global is set to **false**.   

Add the **fnDecisionDeleteFromTarget** [Decision function](/articles/14_sync_LU_instance/05_sync_decision_functions.md) imported to the Shared Objects from the [TDM Library](04_fabric_tdm_library.md) to **all target tables**. 

### Populating Target Tables with the Target Environment

The LUI sync can populate the branches, source and target when running a load task that requires both delete and load entity operations. 

The **source LU tables** must extract the data from the **source environment**, where the **target LU tables** must extract the data from the **target environment**.

The Broadway [InitiateTDMLoad](10_tdm_generic_broadway_flows.md#initialization) flow sets the active environment to the source environment before synching the LUI into Fabric.

Do the following to populate the target LU tables based on the target environment:

1.  Update the execution order of the target LU tables' populations to run **after the source LU tables**. For example:

   ![lu example](images/lu_tar_tables_example.png)

2. Create the population object of the **main target LU table** based on the **Broadway flow**. This population must set the **target environment** to be the active environment before selecting the data from the data source: 

   - Edit the population of the Broadway flow and set the active environment based on the **TDM_TARGET_ENV_NAME** key which is set by the **InitiateTDMLoad** flow based on the task's target environment. Add the following actor before the Query actor: **setTargetEnv_Actor**. 

     For example:

      ![Broadway population](images/broadway_tar_table_population_example.png)

      Click for more information about [Broadway as a population and Fabric command actors](/articles/19_Broadway/09_broadway_integration_with_Fabric.md).

4. Note that the populations of the target tables are based on on **the same interfaces as the source LU tables**, but the connection details change to the target environment, since the population of the main tarhet LU table sets the target environment to be the acvtive environment.

## Adding Broadway Flows to Delete the Entities

Add Delete Broadway flows to delete the entities from the target environment based on the target LU tables.

Click for more information about how to add [Broadway Load and Delete flows to each LU](11_tdm_implementation_using_generic_flows.md).



[![Previous](/articles/images/Previous.png)](07_tdm_implementation_parameters_handling.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_tdm_reference_implementation.md)
