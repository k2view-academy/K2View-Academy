# TDM - Deleting Entities

Every LU must include the ability to Delete Entities from a target environment in order to support the following tasks:

- [Load and Delete task](/articles/TDM/tdm_gui/14_task_overview.md#task-types) - the entities are deleted and reloaded to the target environment.
- [Delete task](/articles/TDM/tdm_gui/19_delete_only_task.md) - the entities are deleted (cleaned) from the task's environment.
- [Data Versioning](/articles/TDM/tdm_gui/15_data_flux_task.md/articles/TDM/tdm_gui/18_load_task_data_versioning_mode.md) load task - the entities are deleted and reloaded to the target environment.

Note that if there is no need to support a deletion of entities or Data Versioning load tasks, the target tables do not need to be added to the LUs.

## LU Structure - Target Tables

Each LU has 2 main branches that are linked to the **FABRIC_TDM_ROOT** root table:

- **Source branch** - LU tables that extract an entity's source data. Source LU tables are populated except for [delete only tasks](/articles/TDM/tdm_gui/19_delete_only_task.md), where no data is extracted from the data sources.
- **Target branch** - LU tables that extract the target keys of an entity. The keys are extracted from the **target environment** when the task needs to delete the entities from the target environment.

The target table contains the list of target IDs (keys) required to delete the data of the selected entities from the target environment and populate [TDM_LU_TYPE_REL_TAR_EID](06_tdm_implementation_support_hierarchy.md#tdm_lu_type_rel_tar_eid) with the target children IDs. It is recommended to add the **TAR_** prefix to each target table. 

TDM 8.1 added an automatic generation of the target tables and their population flow:

### Target Tables Generation

1. Open the Shared Globals and verify that **TDM_DELETE_TABLES_PREFIX** Global is defined (the default is  TAR_). This is the prefix of the target LU tables.

2. Verify that the **ROOT_TABLE_NAME** LU's Global is set. 

3. Complete the adding of all source LU tables to the LU schema.

4. Deploy the LU to the debug server.

5. In order to create the target tables and add them to the LU, run either:

   1. **createDeleteTablesAndPopulations** flow (imported from the TDM library). Set the input parameters:

      - **LU_NAME**

      - **OVERRIDE_EXISTING_FLOWS** - valid values: true/false. When set to **true**, the flow deletes and recreates existing population flows for the target tables. When set to **false**, the flow skips existing flows and creates new flows only, if needed. The **default** value is **false**.

      - **TARGET_SCHEMA**

      - **TARGET_INTERFACE**

        This flow creates the target tables with their population flows, and adds them to the LU schema.

        

   2. [TDMInitFlow](05_tdm_lu_implementation_general.md#ii-run-the-tdmluinit-flow) (imported from the TDM library). Set the  **CREATE_DELETE_TABLES** input parameter to **true**. Note that this flow is designed to run one time, when creating an LU, and it also adds the TDM tables to the LU. If the LU already contains the TDM tables, it is recommended to run the **createAllFromTemplates** flow (see the below line) to add the target tables to the LU.

   3. [createAllFromTemplates flow](11_tdm_implementation_using_generic_flows.md#step-3---create-load-and-delete-flows) (imported from the TDM library). This flow creates the target LU tables to support the entity deletion and adds them to the LU. In addition, it creates the delete and load flows based on the updated LU.
      
      The flow gets the following input parameters:

      - **CREATE_DELETE_TABLES** - set this parameter to **true** to create and add the LU the target tables to support the delete entity. 

      - **LU_NAME**

      - **TARGET_SCHEMA**

      - **TARGET_INTERFACE**

      - **OVERRIDE_EXISTING_FLOWS** - valid values: true/false. When set to **true**, the flow deletes and recreates existing objects. When set to **false**, the flow skips existing objects and creates new ones, if needed. This indicator is checked by all 3 parts of the flow: (adding target LU tables, adding data generation flows, and adding the delete and load flows). The **default** value is **false**.

      - **TARGET_ENVIRONMENT**

        

6. Open the LUs schema, right-click > Automatic Layout to view the added target tables.

   

#### Adding a Decision Function to the Target LU Tables

The target LU tables must be populated when running a TDM task that deletes the entities from the target environment. The target keys must be extracted from the target environment in order to enable deleting entities and their related data.

If the task does not delete the entities from the target environment, the target keys do not need to be extracted from the target environment.

The Broadway [InitiateTDMLoad](10_tdm_generic_broadway_flows.md#initialization) flow sets the **TDM_DELETE_BEFORE_LOAD** Global to **true** if the task must **delete the entities** from the target. Otherwise, this Global is set to **false**.   

The **fnDecisionDeleteFromTarget** [Decision function](/articles/14_sync_LU_instance/05_sync_decision_functions.md) (imported from the [TDM Library](04_fabric_tdm_library.md)) is **automatically added to all target tables** by the target tables generation flow. 

#### Populating Target Tables with the Target Environment

The LUI sync can populate the branches, source environment and target environment when running a load task that requires both delete and load entity operations. 

The **source LU tables** must extract the data from the **source environment**, where the **target LU tables** must extract the data from the **target environment**.

The Broadway [InitiateTDMLoad](10_tdm_generic_broadway_flows.md#initialization) flow sets the active environment to the source environment before syncing the LUI into Fabric.

The **setTargetEnv_Actor** is **automatically added to the generated population flow of the main target LU table**. This Actor sets the active environment based on the **TDM_TARGET_ENV_NAME** key (set by the **InitiateTDMLoad** flow with the task's target environment).

For example:

 ![Broadway population](images/broadway_tar_table_population_example.png)

 Click for more information about [Broadway as a population and Fabric command Actors](/articles/19_Broadway/09_broadway_integration_with_Fabric.md).

Note that the population of the target tables can be based on **the same interfaces as those of the source LU tables**. It is not necessary to define separate target interfaces for the reason that the population of the main target LU table sets the target environment to be the active environment. As a result, the population of the target tables use the target connection details. 

## Adding Broadway Flows to Delete the Entities

Add Delete Broadway flows to delete the entities from the target environment based on the target LU tables.

Click for more information about how to add [Broadway Load and Delete flows to each LU](11_tdm_implementation_using_generic_flows.md).



[![Previous](/articles/images/Previous.png)](07_tdm_implementation_parameters_handling.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_tdm_reference_implementation.md)
