# TDM - Tables Implementation

TDM enables the user to provision tables in a TDM task. To do that, the user can select 1 of the following 2 options:

1. Business entities and referential data. The included tables are related to the task's business entities and are needed in the testing environment.
2. Tables - TDM 9.x enables the option to select a list of tables from multiple DBs related to the source environment without any relation to a business entity.

The user can either store the tables in Fabric for a later use or set the task's retention period to *Do not retain* in order to load the tables directly to the target environment without saving them to Fabric.

Each table is stored in Fabric, whithin the **TDM_TableLevel** LU, as a separate LUI. Each execution is stored as a separate LUI (a separate data snapshot), as well as creates a separate LUI (snapshot). For example: Running 2 executions of a task that extract the Product_Mapping table would create 2 LUIs in the **TDM_TableLevel** LU in Fabric. 

 The LUI format is as follows:

[source environment name]|[DB interface name]|[DB schema name]|[table name]|[task execution id]

Examples:  

- SRC|CRM_DB|public|case_note|102822

- SRC|CRM_DB|public|case_note|102826

- SRC|BILLING_DB|public|contract_offer_mapping|102826

Each LUI contains the following tables:

- TDM_REF_ROOT

- A dynamic SQLite table created with the structure of the source table and that contains the extracted table records. The dynamic SQLite table naming convention is: 

  ```
  __t_<source table name>
  ```

  

- Example of an insert of case_note table record into the SQLite dynamic table:

  ```sqlite
  /*sqlite*/ insert into TDM_TableLevel.__t_case_note (case_id,note_id,note_date,note_text) values(?,?,?,?);
  ```

  

Notes: 

- Previous TDM versions saved the tables into the TDM_Reference LU. However, as this LU is no longer in use (since TDM 9.0), the tables must be re-extracted into the new LU, namely TDM_TableLevel. 

A TDM table-level implementation contains the following steps:

## Step 1 - Deploy the TDM_TableLevel LU

Import and deploy the TDM_TableLevel LU. 

## Step 2 - Relate Tables to a Business Entity

**This step is required for [Entities & referential data](/articles/TDM/tdm_gui/14b_task_source_component_entities.md) tasks**. The list of available referential tables for a TDM task that contains a Business entity and referential data, is populated in the [RefList](04_fabric_tdm_library.md#reflist) MTable object. Populate the **RefList** with the list of available related tables for each LU. The following settings should be populated for each record:

- **lu_name** - populated by the LU name to enable a selection of the related table in a TDM task based on the task's LUs.

- **id** - populated by an incrementing number.

- **reference_table_name** - populated by the table name in the source environment.

- **schema_name** - populated by the source DB schema's name that stores the table.

- **interface_name** - the table's source interface.

- **target_ref_table_name** - this is an optional parameter. It can be populated when the table names are different in the source and target. If empty, the target table name will be taken from the **reference_table_name** field.

- **target_schema_name** - populated by the target DB schema's name that stores the table.

- **target_interface_name** - the name of the table's target interface. 

- **table_pk_list** - an optional setting. Populated by the list of the target's PK fields in the RefList object. These fields can be used later for customizing the load flow to run an Upsert on the target table.

- **truncate_indicator** - by default, the TDM runs a delete on the table in the target environment before loading it. If you have a permission to run a truncate on the target table and you need to use the truncate instead of the delete (e.g., the target DB is Cassandra), set this indicator to **true**.

- **count_indicator** - this setting is set to **true**, by default, for counting the number of records in the source or target, in order to monitor the task execution. Set the indicator to **false**, if required, in order to avoid counting the records in the target.

Note that from TDM 9.3.1 onwards, the schema_name and target_schema_name fields can be populated with either:

- Schema name
- Global name. Add a `@` sign before and after the Global name in order to indicate that the schema name needs to be taken from the Global's value. For example: `@CUSTOMER_SCHEMA_NAME@`. Populating the schema with a Global is useful when different environments have different schema names. 


 Click [here](/articles/09_translations/06_mtables_overview.md) for more information about MTable objects. 

## Step 3 - Optional - Set Different Source and Target Settings for Table-Level Tasks

TDM 9.1 enables adding tables to the **RefList** MTable for the purpose of supporting the setting of different interface, schema name, or table name in the source and target environments for [table-level tasks](/articles/TDM/tdm_gui/14c_task_source_component_tables.md). Set the **lu_name** to **TDM_TableLevel** as a way to define different settings on the source and target environments for table-level tasks. 

## Step 4 - Catalog

### Edit the PII settings

The TDM table flow uses [Fabric Catalog masking](/articles/39_fabric_catalog/11_catalog_masking.md). You can [edit the PII settings](/articles/39_fabric_catalog/10_catalog_settings.md#classifier-pii--masking-setup) in the Catalog, if needed.

### Run the Catalog to Identify a Table's Relation and Order

Run the Discovery job on the table's interfaces. Once the job has been completed, the interface metadata would be retrieved from the Catalog.

Note that if you define a different interface in the target environment, you need to run the discovery process on the **target interface** in order to get the table's list, order, and fields from the Catalog.

## Step 5 - Special Handling and Disabling Tables' Selection 

###  TableLevelInterfaces MTable

The **TableLevelInterfaces** MTable enables either disabling a table's selection from a given DB or setting special handling for the tables that belong to a given DB.

By default, the MTable is populated with the TDM DBs to disable a selection of TDM tables by a TDM task. It is possible to populate additional DB interfaces in order to exclude them from the table selection in the TDM task or to set special handling for their tables. A separate record needs to be set for each DB interface. The following settings should be populated for each record:

- **interface_name** - the DB interface name defined in the TDM project implementation. 

- **suppress_indicator** - if **true**, the DB tables are excluded from the tables' selection in the TDM task. If this field is **false**, the interface's tables can be selected in a TDM task.

- **truncate_indicator** - by default, the TDM runs a delete on the table in the target environment before loading it. If you have permission to run a truncate on the target table and you need to use the truncate instead of the delete (e.g., the target DB is Cassandra), set this indicator to **true**.

- **count_indicator** - this setting is set to **true**, by default, for counting the number of records in the source or target, in order to monitor the task execution. Set the indicator to **false**, if required, in order to avoid counting the records in the target.

- **order_flow** - an optional setting. Populate this setting to run a project's Broadway flow to define customized logic for getting the table's execution order. The order flow must have an external output **Map** named **result** with the list of the tables and their order. For example:

  ```json
  {
    "customer": 0,
    "address": 1
  }
  ```

  

- **no_schema** - this indicator is used for interfaces that do not have a DB schema, but the JDBC connector adds a schema for them. For example: CSV files. The **CSV JDBC Connector** extension concatenates the 'main' schema name to the file list. Set this field to **true** in order to ignore the concatenated schema when accessing the files. 

### TableLevelDefinitions MTable - Customized Logic for Tables 

TDM 9.1 has added the **TableLevelDefinitions** MTable to enable setting a customized logic for selected tables.

A customized flow can be added to a table's extract, load or delete processes. The implementor can set a customized flow for all activities - extract, delete, and load - or only for apecific activities. This feature opens a variety of capabilities such as:

- Custom masking of selective fields (not Catalog-based).

- Extract or Load massive data that requires using 3rd party tools, such as, DB2move.

- Impact the order of the table's execution.

The following settings should be populated for each record:

- **interface_name** - the interface name defined in the TDM project implementation. 
- **schema_name** - the DB schema. Can be populated either with:
  - Schema name
  - From TDM 9.3.1 onwards, the schema name can also be populated with the Global name. Add a `@` sign before and after the Global name in order to indicate that the schema name needs to be taken from the Global's value. For example: `@CUSTOMER_SCHEMA_NAME@`. Populating the schema with a Global is useful when different environments have different schema names. 

- **table_name** - populated with the table name. If the table_name is empty, the customized flows will run on all the tables in the interface and schema.
- **extract_flow** - populated with the customized extract flow.
- **table_order** - populated with a number. The table order in the TableLevelDefinitions MTable has the highest priority, and it can override the order defined in the TableLevelInterfaces MTable.
- **delete_flow** - populated with the customized delete flow. 
- **load_flow** - populated with the load flow.



### Supporting Table-Level Tasks Using Connectors - Update TableLevelDefinitions MTable 

- Add a new record to the TableLevelDefinitions MTable after installing a connector extension, e.g. **BigQuery**, in order to support table-level tasks based on the connector:
  - **interface_name** - populate this field with the connector's interface name.
  - **extract_flow** - populate this field with the connector's extract flow. 
  - **delete_flow** - populate  this field with the connector's delete flow.
  - **load_flow** - populate this field with the connector's load flow.

For more information about each connector, read the connector's Readme file. 

### Customized Table Flows - Implementation Guidelines

The customized table flows are Broadway flows. These flows must be added under the Shared Objects in the Project tree.

#### Extract Flow

- The extract flow gets a list of input parameters from the TDM execution processes and returns the number of records in the table and an object's array for the result. Duplicate the **GetSourceDataByQuery**  flow (located in the TDM_TableLevel LU) to get the extract flow template and customize the extract logic. 

##### Customized Masking Logic

The Catalog masking actor is invoked **after** the extract flow execution. 

Setting customized masking logic on tables:
- If you need to set customized logic on specific fields, edit the Catalog and remove the PII property from these fields in the Catalog in order to prevent double masking them.
- Sometimes, the customized masking logic is based on the Catalog masking output, e.g., building the masked email address based on the masked first and last names. If you need to call the Catalog masking actor in the extract flow, proceed as follows: 
  - Add the **CatalogMaskingMapper** actor to the extract flow. 
  - Add the customized masking actors to the extract flow to be envoked after the CatalogMaskingMapper actor.
  - Set the **enable_masking** parameter to **false** at the end of the extract flow as a way to prevent double masking of the table's record by the TDM execution processes.

##### Customized Extract Flow - Example

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
- Note that if you use **Fabric 8.1.6 and above**, you must manually add the **__active_environment** input parameter to the DbCommand/DbLoad actors. Set this parameter as *Const* and populate it with any value, e.g., target. See an example in the **LoadTableByQuery**  flow. This parameter is added as a way to support a direct table's load from environment A to environment B without storing the table in Fabric. The **__active_environment** parameter is needed in order to refresh the environment, update it to the target environment in the load flow, and run the load on the target environment.

  

#### Delete Flow

- The delete flow gets a list of input parameters from the TDM execution processes and deletes the table before the load. Duplicate the **DeleteTableByDBCommand**  flow (located in the TDM_TableLevel LU) to get the delete flow template and to customize the delete logic.



[![Previous](/articles/images/Previous.png)](08_tdm_implement_delete_of_entities.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_tdm_generic_broadway_flows.md)





