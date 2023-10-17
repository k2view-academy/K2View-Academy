# TDM LU Implementation - General Guidelines

A TDM task copies a [Business Entity](/articles/TDM/tdm_overview/03_business_entity_overview.md) (BE) from a source environment to a target environment. A BE can have multiple [LUs](/articles/03_logical_units/01_LU_overview.md) with either a flat or a hierarchical structure. For instance, a Customer BE consists of Customer Care, Billing, Ordering and Usage LUs. The ability to break up a BE into several LUs enables maximum flexibility and prevents duplication of development. Additionally, defining a hierarchical structure of parent-child LUs enables creating LUs, based on the natural root entity of the related data sources, instead of forcefully setting unified root entities on all LUs related to a given BE.

Each LU in a TDM project should have additional components in order to support TDM functionalities, as described below:   

## Basic LU Structure

Each LU in a TDM project has the following structure:

- Dummy root table - FABRIC_TDM_ROOT. 

- Generic TDM LU tables that are linked to the root table. 

- 2 main branches that are linked to the root table:

  - **Source branch** - LU tables that extract an entity's source data. Source LU tables are populated when a TDM task needs to load (insert) entities into a target environment and they therefore must extract the source data of these entities.

  - **Target branch** - LU tables that extract the target keys of an entity. The keys are extracted from the target environment in order to enable deleting an entity from the target environment if required by the TDM task.

    Click for more information about [Fabric implementation and deleting entities from the target environment](08_tdm_implement_delete_of_entities.md).

## Add the TDM LU Tables to the New LU

Use either one of the following methods to add the TDM LU tables to the new LU:

### I. Duplicate the TDM_LIBRARY LU into the New LU

This method adds the TDM tables to the LU schema **in advance, before adding the source LU tables** to the LU.

Import the [TDM_LIBRARY LU](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#tdm_library-lu) from the **TDM Library** to your project and duplicate it to the newly created LU (right-click -> Duplicate Logical Unit). Now you have **a template of the new LU** with the TDM tables, including FABRIC_TDM_ROOT table as a root LU table.

Note that **the LU_PARAMS table must be added to the LU schema although it is not required for defining LU parameters**.

### II. Run the TDMLUInit Flow

After building the LU schema with the source LU tables, the **TDMLUInit** flow runs on the LU **one time** in order to add the TDM tables to this LU schema as well. The flow edits the LU as follows:

- It adds the TDM tables to the LU schema.

- It sets the FABRIC_TDM_ROOT LU table to be the root LU table and links it to the main source LU table.

- It sets the ROOT_TABLE_NAME and ROOT_COLUMN_NAME Globals on the LU. 

- It recreates the LU population of the main source LU table.

  Click [here](05a_main_source_lu_table_population_logic.md) for more information about the main source LU table's population's logic.

- It creates the [sequence Actors](11_tdm_implementation_using_generic_flows.md#step-2---create-sequences) for the load and data generation flows.
- It creates the delete and load flows.
- **Optional updates**:
  - Creating and adding the target tables to the LU to support the [delete entities implementation](08_tdm_implement_delete_of_entities.md).
  - Creating the [data generation flows](16_tdm_data_generation_implementation.md) to support [Generate tasks](/articles/TDM/tdm_gui/16a_generate_task.md).  

#### Flow Execution:

1. Verify that the LU schema does not have [grouped tables](/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md), and deploy the LU to Fabric debug server before running the flow. 

2. Set the flow's input parameters before executing it:

   - **LU_NAME**

   - **ROOT_TABLE_NAME**  - populated with the current root LU table (the main source LU table). For example: customer.

   - **ROOT_COLUMN_NAME** - populated with the current instance ID field. For example: customer_id.

   - **SOURCE_INTERFACE** -  populated with the source DB interface name.

   - **SOURCE_SCHEMA** -  populated with the source DB schema name.
     
   - **TARGET_SCHEMA** - populated with the target DB schema name.
     
   - **TARGET_INTERFACE** - populated with the target DB interface name.
     
   - **TARGET_ENVIRONMENT** - populated with the target environment name. 


3. Additional **flow input parameters**:

   - Set the  **CREATE_DELETE_TABLES** input parameter to **true** in order to create and add the target tables to the LU.

   - Set the **CREATE_GENERATE_FLOWS** input parameter to **true** in order to create data generation flows for the LU.

   Below is an example of the TDMLUInit flow's input parameters:

  ![TDMLUInit](images/TDMLUInit_input_parameters.png)

4. Run the flow in Fabric Studio.

## Add the Source LU Tables to the LU Schema


1. Link the main source LU tables to the FABRIC_TDM_ROOT table if you duplicate the TDM_LIBRARY to a new LU. The main source tables represent the main (root) tables in the data source. For example, the Customer table is the main source LU table of the Customer LU.
2. Verify that the main source LU tables are also populated in [ROOT_TABLE_NAME and ROOT_COLUMN_NAME Globals](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#globals).

### Create the Source LU Tables' Populations

The population of the LU table needs to be based on a Broadway flow.

<web>

#### Catalog Masking Integration

- Fabric 7.2 introduces [Fabric Discovery and Catalog solution]((/articles/39_fabric_catalog/01_catalog_overview.md)), which provides an insight into the Fabric interfaces, starting with the RDBMS interface types in the MVP version. Fabric catalog supports a [catalog-based masking](/articles/39_fabric_catalog/09_build_artifacts.md) of PII fields.
- TDM 8.1 adds new templates to integrate the TDM with the catalog masking. These templates add the **CatalogMaskingMapper** Actor to the LU population flows in order to run the catalog-based masking on the identified PII fields before loading them into the LU table. Note that it is not mandatory to implement the Fabric catalog: if the catalog is not implemented, the CatalogMaskingMapper Actor returns an empty output.

- Optional: Edit the population flows to override the catalog masking for some of the PII fields: add [Masking Actors](articles/19_Broadway/actors/07_masking_and_sequence_actors.md) after the **CatalogMaskingMapper** Actor and link them to the relevant fields in the **DbLoad** Actor.

</web>

#### Create the population of the main source LU tables

The main source LU tables have their own logic and are generated by a dedicated TDM template: **populationRootTable.pop.flow**.

 Use either one of the following methods to generate the LU population for the main source LU tables:

1. Implement all source LU tables with the default population template. Then run the **TDMLUInit flow** to add the TDM tables to the LU schema and to regenerate the LU population for the main source LU table.
2. Generate the population flow based on **populationRootTable.pop.flow**:

    <studio>
   
      - Right-click the table name > **New Table Population Flow From Template > populationRootTable.pop.flow**. A pop-up window opens.
  
     - Populate the pop-up window's settings as follows:
  
       - **File Name** - populate the file name by [LU Table Name].[flow name]
       - **Parameters** -
         - **TABLE_NAME** - populate it by the LU table name. Note that the LU table name should be identical to the data source table name.
         - **KEY** - populate the key to delete the LU table before populating it.
         - **SOURCE_INTERFACE** - the interface name for the source DB query.
  
     - Example:
  
       ![template](images/create_main_source_lu_flow_by_template.png)
  
    </studio>
  
    <web>
  
     - Right-click the table name > **New Population**. A pop-up window opens. Set the new population name and click Enter. Then, select the **populationRootTable.pop.flow** template and populate its parameters:
        - **TABLE_NAME** - populate it by the LU table name. Note that the LU table name should be identical to the data source table name.
        - **KEY** - populate the key to delete the LU table before populating it.
        - **SOURCE_INTERFACE** - the interface name for the source DB query.
  
    </web>  
  
  <studio>

Click [here](05a_main_source_lu_table_population_logic.md) for more information about the main source LU table's population's logic.

#### Source LU Tables Populations - Mask Sensitive Data 

Mask sensitive data in the LU tables by adding  [Masking Actors](/articles/19_Broadway/actors/07_masking_and_sequence_actors.md) to the population flows. 

Click for more information about [TDM Masking](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#step-5---mask-the-sensitive-data).

</studio>

### Source LU Tables - Additional Steps

- Link the remaining source LU tables to the main LU tables, in a way that if the main source LU table is not populated, the remaining source LU tables remain empty as well.

- Edit **LuParams** MTable in order to enable a subset of entities from selected parameters for this LU. 

   Click for more information about [Handling TDM Parameters](07_tdm_implementation_parameters_handling.md).

## Add the Target LU Tables to the LU Schema

 Use either one of the following methods to create and add the target tables to the LU:

1.  Run the [TDMLUInit](#ii-run-the-tdmluinit-flow) flow.
2. Run the **createAllFromTemplates** flow to add the TAR_  tables, data generation flows, and the load and delete flows to the LU. 
3. Run the **createDeleteTablesAndPopulations** flow to add the TAR_  tables to the LU. 

Click for more information about the [deleting entities implementation](/articles/TDM/tdm_implementation/08_tdm_implement_delete_of_entities.md).

Click for more information about [deleting entities](/articles/TDM/tdm_gui/14_task_overview.md#task-types) from a target environment, using a TDM task.

## LU Debug

The LUI should include the source environment, which should be set as the [active environment](/articles/25_environments/01_environments_overview.md) in Fabric. When running a [Data Viewer](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md) on the LU to debug its implementation, do either:

- Populate the source environment of the LUI using `_dev_`.  For example, **_dev_1**.
- Create and deploy the Environments to the Fabric Debug server. Set the source environment as the active environment in the Fabric Debug server and populate the deployed source environment name in the LUI, e.g., **UAT_1**. However, note that the main target LU table sets the **target environment** to be the **active environment**.





[![Previous](/articles/images/Previous.png)](04_fabric_tdm_library.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdm_implementation_support_hierarchy.md)
