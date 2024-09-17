# TDM - Tables Implementation

TDM enables the user to provision tables in a TDM task. The user can select 1 of the following 2 options:

1. Business entities and referential data. The included tables are related to the task's business entities and are needed in the testing environment.
2. Tables - TDM 9.x enables the option to select a list of tables from multiple DBs related to the source environment without any relation to a business entity.

The user can store the tables in Fabric for a later use or set the task's retention period to *Do not retain* in order to load the tables directly to the target environment without saving them to Fabric.

Each table is stored in Fabric as a separate LUI in the **TDM_TableLevel** LU, and each execution is stored as a separate LUI (a separate data snapshot). For example: Running 2 executions of a task that extract the Product_Mapping table. Each execution creates a separate LUI (snapshot).

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

- Previous TDM versions saved the tables into the TDM_Reference LU. This LU is no longer in use from TDM 9.0 onwards, and the tables must be re-extracted into the new LU - TDM_TableLevel. 

A TDM table-level implementation has the following steps:

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

- **table_pk_list** - an optional setting. Populated by the list of the target's PK fields in the RefList object. These fields can be later used to customize the load flow to run an Upsert on the target table.

- **truncate_indicator** - by default, the TDM runs a delete on the table in the target environment before loading it. If you have a permission to run a truncate on the target table and you need to use the truncate instead of the delete (e.g., the target DB is Cassandra), set this indicator to true.

- **count_indicator** - is set to true, by default, for counting the number of records in the source or target, in order to monitor the task execution. Set the indicator to false, if required, in order to avoid counting the records in the target.


 Click [here](/articles/09_translations/06_mtables_overview.md) for more information about MTable objects. 

## Step 3 - Optional - Set Different Source and Target Settings for Table Level Tasks

TDM 9.1 enables adding tables to the **RefList** MTable in order to support the setting of different interface, schema name, or table name in the source and target environments for [Table level tasks](/articles/TDM/tdm_gui/14c_task_source_component_tables.md). Set the **lu_name** to **TDM_TableLevel** in order to define different settings on the source and target environments for Table level tasks. 

## Step 4 - Catalog

### Edit the PII settings

The TDM table flow uses [Fabric Catalog masking](/articles/39_fabric_catalog/11_catalog_masking.md). You can [edit the PII settings](/articles/39_fabric_catalog/10_catalog_settings.md#classifier-pii--masking-setup) in the Catalog if needed.

### Run the Catalog to Identify Tables Relation and Order

Run the discovery job on the table's interfaces.  Once the job is completed, the interface metadata will be retrieved from the Catalog.

Note that if you define a different interface in the target environment, you need to run the discovery process on the **target interface** in order to get the table's list, order, and fields from Catalog.

## Step 5 - Special Handling and Disabling Tables' Selection 

###  TableLevelInterfaces MTable

The **TableLevelInterfaces** MTable enables either disabling a table's selection from a given DB or setting a special handling for the tables that belong to a given DB.

By default, the MTable is populated with the TDM DBs to disable a selection of TDM tables by a TDM task. It is possible to populate additional DB interfaces in order to exclude them from the table selection in the TDM task or to set special handling for their tables. A separate record needs to be set for each DB interface. The following settings should be populated for each record:

- **interface_name** - the DB interface name defined in the TDM project implementation. 

- **suppress_indicator** - if **true**, the DB tables are excluded from the tables' selection in the TDM task. If this field is **false**, the interface's tables can be selected in a TDM task.

- **truncate_indicator** - by default, the TDM runs a delete on the table in the target environment before loading it. If you have permission to run a truncate on the target table and you need to use the truncate instead of the delete (e.g., the target DB is Cassandra), set this indicator to true.

- **count_indicator** - is set to true, by default, for counting the number of records in the source or target, in order to monitor the task execution. Set the indicator to false, if required, in order to avoid counting the records in the target.

- **order_flow** - an optional setting. Populate this setting to run a project's Broadway flow to define customized logic to get the table's execution order.  The order flow must have an external output **Map** named **result** with the list of the tables and their order. For example:

  ```json
  {
    "customer": 0,
    "address": 1
  }
  ```

  

- **no_schema** - this indicator is used for interfaces that do not have a DB schema, but the JDBC connector adds a schema for them. For example: CSV files. The **CSV JDBC Connector** extension concatenates the 'main' schema name to the file list. Set this field to **true** in order to ignore the concatenated schema when accessing the files. 

### TableLevelDefinitions MTable - Customized Logic for Tables 

TDM 9.1 has added the **TableLevelDefinitions** MTable to enable setting a customized logic for selected tables.

A customized flow can be added to table's extract, load or delete.  The implementor can set a customized flow for all activities - extract, delete, and load - or for some of the activities. This feature opens a variety of capabilities such as:

- Custom masking of selective fields (not catalog-based).

- Extract or Load massive data that requires using 3rd party tools, such as, DB2move.

- Impact the order of the table's execution.

The following settings should be populated for each record:

- **interface_name** - the interface name defined in the TDM project implementation. 
- **schema_name** - the DB schema.
- **table_name** - populated with the table name. If the table_name is empty, the customized flows will run on all the tables in the interface and schema.
- **extract_flow** - populated with the customized extract flow.
- **table_order** - populated with a number. The table order in the TableLevelDefinitions has the highest priority and can override the order defined in the TableLevelInterfaces MTable.
- **delete_flow** - populated with the customized delete flow. 
- **load_flow** - populated with the load flow.

### Customized Table's Flows - Implementation Guidelines

The customized table's flows are Broadway flows. These flows must be added under the Shared Objects or the TDM_TableLevel LU in the project tree.

#### Extract Flow

- The extract flow gets a list of input parameters from the TDM execution processes and returns the number of records in the table and an object's array for the result. Duplicate the **GetSourceDataByQuery**  flow (located in the TDM_TableLevel LU) to get the extract flow template and customize the extract logic. 

##### Customized Masking Logic

The catalog masking actor is invoked **after** the extract flow execution. Do the following in order to set a customized masking logic on the table:

- If you need to set a customized logic on specific fields, edit the catalog and remove the PII property from these fields in the catalog in order to prevent double masking of these fields.
- Sometimes, the customized masking logic is based on the catalog masking. For example, build the masked email based on the masked first and last names. If you need to call the catalog masking in the extract flow, do the following: override the masking of all the table's PII fields, do the following:
  -  Add the **CatalogMaskingMapper** actor to the extract flow. 
  - Add the customized masking actors to the extract flow.
  - Set the **enable_masking** to **false** in the end of the extract flow in order to prevent a double masking of the table's record by the TDM execution processes. 

#### Load Flow

- The load flow gets a list of input parameters from the TDM execution processes and returns the number of loaded records. Duplicate the **LoadTableByQuery**  flow (located in the TDM_TableLevel LU) to get the load flow template and customize the load logic.

  

#### Delete Flow

- The delete flow gets a list of input parameters from the TDM execution processes and deletes the table before the load. Duplicate the **DeleteTableByDBCommand**   flow (located in the TDM_TableLevel LU) to get the delete flow template and customize the delete logic.



[![Previous](/articles/images/Previous.png)](08_tdm_implement_delete_of_entities.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_tdm_generic_broadway_flows.md)





