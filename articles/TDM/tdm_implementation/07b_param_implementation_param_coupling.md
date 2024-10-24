# TDM Parameters - Parameters Coupling - Implementation Guidelines

## LU Schema

- Verify that the LU_PARAMS table is attached to the LU schema.
- Verify that the TDM_BE_IIDS table is attached to the LU Schema. This table is mandatory for the parameters coupling mode and is used for joining multiple LU schemas when the task contains parameters of multiple LUs.
- Verify that the **FABRIC_TDM_ROOT** table has a PK on the **iid** field. This PK is needed in order to export the LU schema tables into the TDM DB. The parameters' selection query runs on the exported tables.
- Verify that the **linked fields** in the LU tables have **identical data types**. The linked fields must have identical data types in order to support the [MDB export](/articles/02_fabric_architecture/04_fabric_commands.md#mdb-export--import) of the LU schema into the TDM DB.
- Verify that the linked fields are defined as either PKs or a unique indexes in the parent LU table in order to support the MDB export of these tables. All
 the parent LU table's PK/unique index fields must be linked to the child LU table. This is required for creating the FK relation in the PG DB for the exported LU tables. 

## Optional - Adding Parameters to a Logical Unit

- Add the LU's parameters to the **LuParamsMapping** MTable (located under References in the Project tree).

- The **LuParamsMapping** has the following fields:

  - lu_name

  - param_name

  - lu_table

  - lu_table_field

- Each parameter must be mapped to an LU table's field. The parameter name does not have to be identical to the lu_table_field.

- If you have calculated parameters such as number of open cases or total open debt, add an LU table that contains these parameters. Add the new table with the calculated parameters to [TDMFilterOutTargetTables](11_tdm_implementation_using_generic_flows.md#step-1---define-tables-to-be-filtered-out)  actor in order to exclude these tables from the load, delete, and from the data generation flows creation.

- Verify that all the LU tables in the LuParamsMapping are linked to parent tables. This is required in order to add a FK to tables when they are exported to the TDM DB.
- Note that the MDB export does not support multiple populations with different links to parent tables. The LU tables in LuParamsMapping must have one link to a parent LU table.



## TDM Parameter Tables

### Export the LU Tables into the TDM DB

The TDM extract task execution exports the LU tables to the TDM DB. A dedicated schema is created for each LU. An FK is created for each parent/child link between tables. The following tables are exported into the TDM DB:

- FABRIC_TDM_ROOT - the entire table is exported.
- TDM_BE_IIDS - the entire table is exported.
- The LU tables in LuParamsMapping MTable - only the parameter fields and the linked fields to the parent or child LU tables are exported.
- PK and FK fields are exported.  
  - Example:
    - FABRIC_TDM_ROOT -> Customer -> Address
    - Customer is linked to FABRIC_TDM_ROOT via the customer_id.
    - Address is linked to Customer via the customer_id,
    - Address.state is mapped as a parameter. 
    - All 3 tables are exported. 
    - Customer table  - the customer_id is exported.
    - Address table - the customer_id, address_id, and state fields are exported.

- The exported tables are used for the following:
  - Getting the number of matching entities for the selected parameters of the task.
  - Creating the entity list for the task if the task's selection method is based on parameters.
  - Creating the entity list for the task if a random selection of entities is used.  

### TDM_PARAMS_DISTINCT_VALUES

Moreover, the TDM task execution process populates the [tdm_params_distinct_values](/articles/TDM/tdm_architecture/02_tdm_database.md#tdm_params_distinct_values) TDM DB table with the parameters list and the valid values for [combo parameters](07_tdm_implementation_parameters_handling.md#optional---update-the-maximum-number-of-values-for-combo-parameters).

This table is used for getting the list of available parameters and their valid values in the task.

### AI-based Generation

The AI-based generated entities are not 'synced' from a data source. The AI process generates entities, and TDM imports the generated entities to Fabric. A post TDM process exports the parameter tables of the imported entities, enabling a selection of these entities based on parameters.

Click [here](/articles/TDM/tdm_gui/14e_task_source_ai_based_generation.md) for more information about the AI-based generation.



**Notes:**

- Despite the fact that parameters do not need to be defined for an LU, the LU_PARAMS table must be added to the LU Schema for creating the `<LU Name>_params` table in the TDM DB. The `<LU Name>_params` table is required by both methods of entity selection in a TDM task: [Parameters](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#parameters) and [Random Selection](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#random-selection).
- Do not include spaces or special characters in parameter names.
- The PARAMS_JSON field of the LU_PARAMS table contains the list of LU parameters and their values to enable the debugging of a given entity.
- Click [here](/articles/TDM/tdm_architecture/07_tdm_parameters_handling.md) for more information about handling of parameters.
