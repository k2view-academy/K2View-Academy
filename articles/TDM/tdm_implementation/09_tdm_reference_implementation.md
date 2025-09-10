# TDM — Table Implementation

TDM enables users to provision tables in a TDM task. To do this, users can select one of the following two options:

1. Business Entities and referential data. The included tables are related to the task's Business Entities (BEs) and are required in the testing environment.
2. Tables — TDM V9.X provides the option to select a list of tables from multiple DBs that relate to the source environment, without any relation to a Business Entity.

Users can either store the tables in Fabric for later use or set the task's retention period to *Do not retain* in order to load the tables directly to the target environment without saving them to Fabric.

Each table is stored in Fabric, within the **TDM_TableLevel** LU, as a separate LU Instance (LUI). Each execution is stored as a separate LUI (a separate data snapshot), as well as creates a separate LUI (snapshot). For example, running two executions of a task to extract the Product_Mapping table would create two LUIs in the **TDM_TableLevel** LU in Fabric. 

 The LUI format is as follows:

[source environment name]|[DB interface name]|[DB schema name]|[table name]|[task execution id]

Examples:  

- SRC|CRM_DB|public|case_note|102822

- SRC|CRM_DB|public|case_note|102826

- SRC|BILLING_DB|public|contract_offer_mapping|102826

Each LUI contains the following tables:

- TDM_REF_ROOT

- A dynamic SQLite table, which has the same structure as the source table and contains the extracted table records. The naming convention for this table is: 

  ```
  __t_<source table name>
  ```

  

- The following is an example of how a case_note table record is inserted into the SQLite dynamic table:

  ```sqlite
  /*sqlite*/ insert into TDM_TableLevel.__t_case_note (case_id,note_id,note_date,note_text) values(?,?,?,?);
  ```

  

Notes: 

- In previous TDM versions, tables were saved in the TDM_Reference LU. However, as this LU is no longer used (from TDM V9.0), the tables must be re-extracted into the new **TDM_TableLevel LU**. 

A TDM table-level implementation contains the following steps:

## Step 1: Deploy the TDM_TableLevel LU

Import the TDM_TableLevel LU and deploy it. 

## Step 2: Relate Tables to a Business Entity

**This step is required for [Entities & referential data](/articles/TDM/tdm_gui/14b_task_source_component_entities.md) tasks**. The list of available referential tables for a TDM task that contains a Business Entity and referential data is populated in the [RefList](04_fabric_tdm_library.md#reflist) MTable object. Populate the **RefList** with a list of all available related tables for each LU. The following settings should be populated for each record:

- **lu_name** — populated by the LU name to allow selection of the related table in a TDM task based on the task's LUs.

- **id** — an incrementing number.

- **reference_table_name** — populated with the table name in the source environment.

- **schema_name** — populated with the name of the source DB schema that stores the table.

- **interface_name** — the table's source interface.

- **target_ref_table_name** — an optional parameter. Populate it when the table names in the source and target differ. If not provided, the target table name is taken from the **reference_table_name** field.

- **target_schema_name** — populated with the name of the target DB that stores the table.

- **target_interface_name** — the table's target interface. 

- **table_pk_list** — an optional setting. Populated with the list of the target table's PK fields in the RefList object. These fields can be used later for customizing the load flow to run an Upsert on the target table.

- **truncate_indicator** — by default, TDM runs a delete on the table in the target environment before loading it. If you have permission to run a truncate on the target table and need to use the truncate instead of delete (e.g., the target DB is Cassandra), set this indicator to **true**.

- **count_indicator** — this setting is set to **true** by default, enabling counting the number of records in the source or target, as a way to monitor task execution. Set this indicator to **false**, if required, to disable counting records in the target.

Note that from TDM V9.3.1 onwards, the schema_name and target_schema_name fields can be populated with either of the following options:

- Schema name
- Global name. Add a `@` sign before and after the Global name to indicate that the schema name should be taken from the Global's value. For example: `@CUSTOMER_SCHEMA_NAME@`. Using a Global to populate the schema is useful when different environments have different schema names. 


 Click [here](/articles/09_translations/06_mtables_overview.md) for more information about MTable objects. 

## Step 3: Optional — Configure Different Source and Target Settings for Table-Level Tasks

TDM 9.1 enables adding tables to the **RefList** MTable for the purpose of supporting the setting of different interface, schema name, or table name in the source and target environments for [table-level tasks](/articles/TDM/tdm_gui/14c_task_source_component_tables.md). To configure different settings in the source and target environments for table-level tasks, set the **lu_name** to **TDM_TableLevel**. 

## Step 4: Catalog

### Edit the PII Settings

The TDM table flow uses [Fabric Catalog masking](/articles/39_fabric_catalog/catalog_app/11_catalog_masking.md). You can [edit the PII settings](/articles/39_fabric_catalog/catalog_app/10_catalog_settings.md#classifier-pii--masking-setup) in the Catalog when required.

### Run the Catalog to Identify a Table's Relation and Order

Run the Discovery job on the table's interfaces. Following the job completion, the interface metadata is retrieved from the Catalog.

## Step 5: Special Handling and Disabling Tables' Selection 

###  TableLevelInterfaces MTable

The **TableLevelInterfaces** MTable enables implementors to control which tables can be selected in a task and allows to define special handling rules for a given DB.

By default, this MTable is populated with the 'TDM' and 'POSTGRESQL_ADMIN' interfaces in order to prevent the TDM tasks from selecting the TDM tables. It is possible to populate additional DB interfaces in order to exclude them from the table selection in the TDM task or to set special handling for their tables. A separate record needs to be set for each DB interface. The following settings should be populated for each record:

- **interface_name** — the DB interface name defined in the TDM project implementation. 

- **suppress_indicator** — if **true**, the DB tables are excluded from the tables' selection in the TDM task. If this field is **false**, the interface's tables can be selected in a TDM task.

- **truncate_indicator** — by default, the TDM runs a delete on the table in the target environment before loading it. If you have permission to run a truncate on the target table and you need to use the truncate instead of the delete (e.g., the target DB is Cassandra), set this indicator to **true**.

- **count_indicator** — this setting is set to **true**, by default, for counting the number of records in the source or target, in order to monitor the task execution. Set the indicator to **false**, if required, in order to avoid counting the records in the target.

- **order_flow** — an optional setting. Populate this setting to run a project's Broadway flow to define customized logic for getting the table's execution order. The order flow must have an external output **Map** named **result** with the list of the tables and their order. For example:

  ```json
  {
    "customer": 0,
    "address": 1
  }
  ```

  

- **no_schema** — this indicator is used for interfaces that do not have a DB schema, but the JDBC connector adds a schema for them. For example: CSV files. The **CSV JDBC Connector** extension concatenates the 'main' schema name to the file list. Set this field to **true** in order to ignore the concatenated schema when accessing the files. 

### TableLevelDefinitions MTable - Customized Logic for Tables 

TDM V9.1 has added the **TableLevelDefinitions** MTable to enable setting a customized logic for selected tables.

A customized flow can be added to a table's extract, load or delete processes. The implementor can set a customized flow for all activities - extract, delete, and load - or only for apecific activities. This feature opens a variety of capabilities such as:

- Custom masking of selective fields (not Catalog-based).

- Extract or Load massive data that requires using 3rd party tools, such as, DB2move.

- Impact the order of the table's execution.

The following settings should be populated for each record:

- **interface_name** — the interface name defined in the TDM project implementation. 
- **schema_name** — the DB schema. Can be populated either with:
  - Schema name
  - From TDM 9.3.1 onwards, the schema name can also be populated with the Global name. Add a `@` sign before and after the Global name in order to indicate that the schema name needs to be taken from the Global's value. For example: `@CUSTOMER_SCHEMA_NAME@`. Populating the schema with a Global is useful when different environments have different schema names. 

- **table_name** — populated with the table name. If the table_name is empty, the customized flows will run on all the tables in the interface and schema.
- **extract_flow** — populated with the customized extract flow.
- **table_order** — populated with a number. The table order in the TableLevelDefinitions MTable has the highest priority, and it can override the order defined in the TableLevelInterfaces MTable.
- **delete_flow** — populated with the customized delete flow. 
- **load_flow** — populated with the load flow.



### Supporting Table-Level Tasks Using Connectors - Update TableLevelDefinitions MTable 

The installment of a K2exchange connector adds a dedicated TableLevelDefinitions file for the connector. 

**Example -  TableLevelDefinitions___mongodb**:

![mongo example](images/mongo_tableleveldefinitions.png) 



Note that you must set the task's retention period to *Do not retain* in order to load the tables directly to the target environment without saving them to Fabric when the data source is based on a connector. 

### Customized Table Flows — Implementation Guidelines

The customized table flows are Broadway flows. These flows must be added under the Shared Objects in the Project tree.

#### Extract Flow

- The extract flow gets a list of input parameters from the TDM execution processes and returns the number of records in the table and an object's array for the result. Duplicate the **GetSourceDataByQuery**  flow (located in the TDM_TableLevel LU) to get the extract flow template and customize the extract logic. 

##### Customized Masking Logic

The Catalog masking actor is invoked **after** the extract flow execution. 

Setting customized masking logic on tables:
- If you need to set customized logic on specific fields, edit the Catalog and remove the PII property from these fields in the Catalog in order to prevent double masking them.
- Sometimes, the customized masking logic is based on the Catalog masking output, e.g., building the masked email address based on the masked first and last names. If you need to call the Catalog masking actor in the extract flow, proceed as follows: 
  - Add the **CatalogMaskingMapper** actor to the extract flow. 
  - Add the customized masking actors to the extract flow to be invoked after the CatalogMaskingMapper actor.
  - Set the **enable_masking** parameter to **false** at the end of the extract flow as a way to prevent double masking of the table's record by the TDM execution processes.

##### Customized Extract Flow — Example

The below image depicts an example, which executes the following:

- Selecting records from the address table.
- Opening a loop on the extracted records.
- On each record - 
  - Masking the street, city, and zip code fields.
  - Merging the masked fields into the address record. 
  - Accumulating the merged record with the masked fields into an array. The accumulated array is the external **result** field of the flow.


![table extract](images/table_leve_custom_ext_flow.png)

See the loop on the selected address records:

![table extract](images/table_leve_custom_ext_flow2.png)

#### Load Flow

- The load flow gets a list of input parameters from the TDM execution processes and returns the number of loaded records. Duplicate the **LoadTableByQuery** flow (located in the TDM_TableLevel LU) to get the load flow template and customize the load logic.
- Note that if you use **Fabric 8.1.6 and above**, you must manually add the **__active_environment** input parameter to the DbCommand/DbLoad actors. Set this parameter as *Const* and populate it with any value, e.g., target. See an example in the **LoadTableByQuery** flow. This parameter is added as a way to support a direct table's load from environment A to environment B without storing the table in Fabric. The **__active_environment** parameter is needed in order to refresh the environment, update it to the target environment in the load flow, and run the load on the target environment.

  

#### Delete Flow

- The delete flow gets a list of input parameters from the TDM execution processes and deletes the table before the load. Duplicate the **DeleteTableByDBCommand**  flow (located in the TDM_TableLevel LU) to get the delete flow template and to customize the delete logic.



[![Previous](/articles/images/Previous.png)](11e_pre_and_post_execution_processes.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](13_tdm_implementation_supporting_different_product_versions.md)





