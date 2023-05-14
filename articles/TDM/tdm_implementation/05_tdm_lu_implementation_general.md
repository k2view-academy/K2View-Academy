# TDM LU Implementation - General Guidelines

A TDM task copies a [Business Entity](/articles/TDM/tdm_overview/03_business_entity_overview.md) (BE) from a source environment to a target environment. A BE can have multiple [LUs](/articles/03_logical_units/01_LU_overview.md) with either a flat or a hierarchical structure. For instance, a Customer BE consists of Customer Care, Billing, Ordering and Usage LUs. The ability to break up a BE into several LUs enables maximum flexibility and avoids duplication of development. Additionally, defining a hierarchical structure of parent-child LUs enables creating LUs, based on the natural root entity of the related data sources, instead of forcefully setting unified root entities on all LUs related to a given BE.

Each LU in a TDM project should have additional components in order to support TDM functionalities, as described below:   

## Basic LU Structure

Each LU in a TDM project has the following structure:

- Dummy root table, FABRIC_TDM_ROOT. 

- Generic TDM LU tables that are linked to the root table. 

- 2 main branches that are linked to the root table:

  - **Source branch** - LU tables that extract an entity's source data. Source LU tables are populated when a TDM task needs to load (insert) entities into a target environment and therefore must extract the source data of these entities.

  - **Target branch** - LU tables that extract the target keys of an entity. The keys are extracted from the target environment in order to enable deleting an entity from a target environment if required by the TDM task.

    Click for more information about [Fabric implementation and deleting entities from the target environment](08_tdm_implement_delete_of_entities.md).

### Step 1 - Duplicate the TDM_LIBRARY LU into the New LU

Import the [TDM_LIBRARY LU](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#tdm_library-lu) from the **TDM Library** to your project and duplicate it to the newly created LU (right click -> Duplicate Logical Unit). Now you have a template of the new LU with the TDM tables, including FABRIC_TDM_ROOT table as a root LU table.

Note that **the LU_PARAMS table must be added to the LU schema although it is not required for defining LU parameters**. In this case, the LU_PARAM table holds only the ENTITY_ID and SOURCE_ENVIRONMENT fields.


### Step 2 - Add the Source LU Tables to the LU Schema


1. Link the main source LU tables to the FABRIC_TDM_ROOT table. The main source tables represent the main (root) tables in the data source. For example, the Customer table is the main source LU table of the Customer LU.

2. Verify that the main source LU tables are also populated in [ROOT_TABLE_NAME and ROOT_COLUMN_NAME Globals](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#globals).

3. Create the population of the main source LU tables:

   Generate a Broadway flow for the LU table population based on [populationRootTable.pop.flow](#populating-the-main-source-lu-table---logic) template (imported from the TDM Library): 

   - Right-click the table name > **New Table Population Flow From Template > populationRootTable.pop.flow**. A popup window opens.

   - Populate the popup window's settings as follows:

     - **File Name** - populate the file name by [LU Table Name].[flow name]
     - **Parameters** -
       - **TABLE_NAME** - populate it by the LU table name. Note that the LU table name should be identical to the data source table name.
       - **KEY** - populate the key to delete the LU table before populating it.
       - **SOURCE_INTERFACE** - the interface name for the source DB query.

   - Example:

     ![template](images/create_main_source_lu_flow_by_template.png)

   

4. Set the [Truncate Before Sync](/articles/14_sync_LU_instance/04_sync_methods.md#truncate-before-sync) property of the main source LU table to False, as the Broadway flow deletes the LU table before populating it.

5. Link the remaining source LU tables to the main LU tables, in a way that if the main source LU table is not populated, the remaining source LU tables remain empty as well.

6. Mask sensitive data in the LU tables by creating a Broadway flow with [Masking Actors](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) to populate the LU tables with the sensitive data. 

   Click for more information about [TDM Masking](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#step-5---mask-the-sensitive-data).
   
7. Edit **trnLuParams** and **LU_PARAMS** in order to enable a subset of entities from selected parameters for this LU. 

   Click for more information about [Handling TDM Parameters](07_tdm_implementation_parameters_handling.md).

### Step 3 - Add the Target LU Tables to the LU Schema

1. Define the LU tables in order to extract the target keys from the target environment. These keys are used by the [delete flows](11_tdm_implementation_using_generic_flows.md#step-3---create-load-and-delete-flows) for deleting an entity from the target.

2. Link the main target LU table to the FABRIC_TDM_ROOT table.

3. Add the **fnDecisionDeleteFromTarget** Decision function to all target LU tables. Note that this Decision function is under Shared Objects and is imported from the [TDM Library](04_fabric_tdm_library.md).

4. Create the population of the main target LU table based on a Broadway flow. The Broadway flow should set the task's target environment to be the **active environment**, enabling the selection of target IDs from the target environment. 

5. Link the remaining target LU tables to the main target LU table.

Click for more information about the [deleting entities implementation](/articles/TDM/tdm_implementation/08_tdm_implement_delete_of_entities.md).

Click for more information about [deleting entities](/articles/TDM/tdm_gui/14_task_overview.md#task-types) from a target environment using a TDM task.

### LU Debug

The LUI should include the source environment, which should be set as the [active environment](/articles/25_environments/01_environments_overview.md) in Fabric. When running a [Data Viewer](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md) on the LU to debug its implementation, do either:

- Populate the source environment of the LUI using `_dev_`.  For example, **_dev_1**.
- Create and deploy a source and target environments to the Fabric Debug server, set the source environment as an active environment in the Fabric Debug server and populate the deployed source environment name in the LUI, e.g., **UAT_1**. Note that the main target LU table sets the **target environment** to be the **active environment**.

### Populating the Main Source LU Table - Logic

The Broadway flow of the main source LU table is generated based on the **populationRootTable.pop.flow** template. It deletes and re-populates the main source LU table under the following conditions:

- Running an [Extract task](/articles/TDM/tdm_gui/16_extract_task.md) ,  [regular Load task](/articles/TDM/tdm_gui/17_load_task_regular_mode.md) (the Data Versioning checkbox is cleared), or [Generate task].

- The **Set Sync Policy** task's setting is not set to **Do not Sync From Source Data** in order to prevent synchronizing the entities from the source system. 

  Click to view the [Override Sync Mode Summary Table](/articles/TDM/tdm_architecture/04_task_execution_overridden_parameters.md#overriding-the-sync-mode-on-the-task-execution).

The source LU tables are not populated by the LUI sync in the following cases:

- The Sync Policy is set to **Do Not Sync From Source Data** by the user.
- A [delete only task](/articles/TDM/tdm_gui/19_delete_only_task.md).
- A  [reserve only task](/articles/TDM/tdm_gui/20_reserve_only_task.md).
- A [Data Versioning load task](/articles/TDM/tdm_gui/15_data_flux_task.md): the selected data version is copied from Fabric. 

The Broadway flow also validates whether the entity exists in the source table. If the entity is not found in the main source tables, an Exception is thrown and the entity is rejected.

See example of a Broadway flow that populates a main source LU table:



![root example](images/pop_root_lu_table_flow_example.png)





[![Previous](/articles/images/Previous.png)](04_fabric_tdm_library.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdm_implementation_support_hierarchy.md)
