# TDM - Tables Implementation

TDM enables the user to provision tables in a TDM task. The user can select either of the following options:

1. Business entities and referential data. The included tables are related to the task's business entities and are needed to complete the data in the tested environment.
2. Tables - TDM 9.0 enables the option to select a list of tables from multiple DBs related to the source environment without any relation to a business entity.

The user can either store the tables in Fabric for a later use or set the task's retention period to *Do not retain* to load the tables directly to the target environment without saving them to Fabric.

Each table is stored in Fabric as a separate LUI **TDM_TableLevel** LU and each execution is stored as a separate LUI (separate data snapshot). For example: running 2 executions of a task that extract Product_Mapping table. Each execution creates a separate LUI (snapshot).

 The LUI format is as follows:

[source environment name]|[DB interface name]|[DB schema name]|[table name]|[task execution id]

Examples:  

- SRC|CRM_DB|public|case_note|102822

- SRC|CRM_DB|public|case_note|102826

- SRC|BILLING_DB|public|contract_offer_mapping|102826

Each LUI contains the following tables:

- TDM_REF_ROOT

- A dynamic SQLite table created with the structure of the source table and contains the extracted table records. The dynamic SQLite table naming convention is: 

  ```
  __t_<source table name>
  ```

  

- Example of an insert of case_note table record into the SQLite dynamic table:

  ```sqlite
  /*sqlite*/ insert into TDM_TableLevel.__t_case_note (case_id,note_id,note_date,note_text) values(?,?,?,?);
  ```

  

Notes: 

- Previous TDM versions saved the tables into the TDM_Reference LU. This table is no longer in use from the TDM 9.0 onwards and the tables must be re-extracted into the new tables LU - TDM_TableLevel.  

A TDM implementation has the following steps:

## Step 1 - Deploy the TDM_TableLevel LU

Import and deploy the TDM_TableLevel LU. 

## Additional Optional Steps 

### Step 2 - Relate Tables to a Business Entity

The list of referential tables available for a TDM task for Business entity & referential data  is populated in the [RefList](04_fabric_tdm_library.md#reflist) MTable object. Populate the **RefList** with the list of available related tables for each LU. The following settings should be populated for each record:

- **lu_name** - populated by the LU name to enable a selection of the related table in a TDM task based on the task's LUs.

- **id** - populated by an increment number.

- **reference_table_name** - populated by the table name in the source environment.

- **schema_name** - populated by the source DB schema's name that stores the table.

- **interface_name** - the table's source interface.

- **target_ref_table_name** - This is an optional parameter. It can be populated when the table names are different in the source and target. If empty, the target table name will be taken from the **reference_table_name** field.

- **target_schema_name** - populated by the target DB schema's name that stores the table.

- **target_interface_name** - the name of the table's target interface. 

- **table_pk_list** - an optional setting. Populated by the list of the target's PK fields in the RefList object. These fields can be later used to customize the load flow to run an Upsert on the target table.

- **truncate_indicator** - by default, the TDM runs a delete on the table in the target environment before loading it. If you have permission to run a truncate on the target table and you need to use the truncate instead of the delete (e.g., the target DB is Cassandra), set this indicator to true.

- **count_indicator** - is set to true, by default, for counting the number of records in the source or target, in order to monitor the task execution. Set the indicator to false, if required, in order to avoid counting the records in the target.

- **count_flow** - an optional setting. Populate this setting to run a project Broadway flow to get the count (number of records) in the source or target.

  


 Click [here](/articles/09_translations/06_mtables_overview.md) for more information about MTable objects. 

### Step 3 - Catalog

#### Edit the PII settings

The TDM table flow uses [Fabric catalog masking](/articles/39_fabric_catalog/11_catalog_masking.md).  You can [edit the PII settings](/articles/39_fabric_catalog/10_catalog_settings.md#classifier-pii--masking-setup) in the catalog if needed.

#### Run the Catalog to Identify Tables Relation

Running the discovery for the DB interfaces. Note that once job is completed, the interface metadata will be retrieved from the catalog.

### Step 4 - Special Handling

The **TableLevelInterfaces** MTable enables to disable a tables' selection from a given DB, or set special handling for the tables belong to a given DB.

By default, the MTable is populated with the TDM DBs to disable a selection of TDM tables by a TDM task. It is possible to add DB interfaces to this MTable to exclude them from the table selection in the TDM task or in order to set a special handling for their tables. A separate records needs to be set for each DB interface.  The following settings should be populated for each record:

- **interface_name** - the DB interface name as defined in the TDM product implementation. 
- **suppress_indicator** - if **true**, the DB tables are excluded from the tables' selection in the TDM task. If this field is **false** , the interface's tables can be selected in a TDM task.
- **truncate_indicator** - by default, the TDM runs a delete on the table in the target environment before loading it. If you have permission to run a truncate on the target table and you need to use the truncate instead of the delete (e.g., the target DB is Cassandra), set this indicator to true.
- **count_indicator** - is set to true, by default, for counting the number of records in the source or target, in order to monitor the task execution. Set the indicator to false, if required, in order to avoid counting the records in the target.
- **count_flow** - an optional setting. Populate this setting to run a project Broadway flow to get the count (number of records) in the source or target. table's source interface.





[![Previous](/articles/images/Previous.png)](08_tdm_implement_delete_of_entities.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_tdm_generic_broadway_flows.md)





