# TDM - Reference Implementation

<a href="https://www.k2view.com/products/test-data-management/" target="_blank">TDM</a> enables users to extract reference tables from several source environments. TDM 7.5.3 stores the Reference tables in a dedicated LU: TDM_Reference. Each Reference table is stored as a separate LUI.  The LUI contains the following:

[LU name]|[source environment name]|[version id]|[table name]

For example:  Customer|SRCLocalDebug|ALL|DEVICESTABLE2017

This enables saving different versions of a Reference table and source environment into Fabric. Lastly, TDM enables the creation and execution of TDM load tasks to get the reference data from Fabric and load it to the the target environment. 

Notes: 

- The TDM Reference solution is not based on [Fabric Reference tables](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md).
- Previous TDM versions saved the reference tables in Cassandra. 

A TDM implementation has the following steps:

### Step 1 - Deploy the TDM_Reference LU

Import the TDM_Reference LU and load it to Fabric.

Note **that the [Sync method](/articles/14_sync_LU_instance/04_sync_methods.md) LU property is set by default to None**, i.e. each LUI (reference table) is synced only once. You need to edit this property to enable a recurring sync of the reference table from the source environment. 

### Step 2 - Populate trnRefList Translation

The list of Reference tables available for TDM tasks is populated in the [trnRefList](04_fabric_tdm_library.md#trnreflist) translation object. Populate trnRefList with the list of available Reference tables for each LU. The following settings should be populated for each record:

- **lu_name** - populated by the LU name.

- **ID** - populated by a sequence.

- **reference_table_name** - populated by the Reference table in the source env.

- **schema_name** - populated by the source DB schema name that stores the Reference table.

- **interface_name** - the Reference table's source interface.

- **target_ref_table_name** - this is an optional parameter. It can be populated when the Reference table names are different in the source and target. If empty, the target table name will be taken from the **reference_table_name** field.

- **target_schema_name** - populated by the target DB Schema's name that stores the Reference table.

- **target_interface_name** - name of the Reference table's target interface. 

- **table_pk_list** - an optional setting. Populated by the list of target's PK fields in trnRefList. These fields can be later used to customized the load flow to run an Upsert on the target reference table.

- **truncate** - by default the TDM runs a delete on the reference table in the target environment before loading it. If you have permissions to run a truncate on the target reference table and you need to use the target instead of the delete (for example, the target DB is Cassandra), set this indicator to true.

- **count_indicator** - sets to true by default to count the number of records in the source or target to monitor the task execution. Set the indicator to false to avoid counting the records in the target if needed.

- **count_bf** - an optional setting. Populate this setting to run a project Broadway flow to get the count (number of records) in the source or target. 

  

### Step 2 - Create and Execute TDM Extract Tasks

TDM extract tasks store the selected Reference data in the related Cassandra DB table.

Click for more information on how the [TDM stores Reference tables in the Cassandra DB](/articles/TDM/tdm_architecture/05_tdm_reference_processes.md#reference-cassandra-table).

### Step 3 - Create and Execute TDM Tasks

TDM 7.5.3 enables running extract and load tasks on reference tables, i.e. the task extracts the reference table into Fabric and loads it to the target environment. 

Note that on previous TDM versions the extract from the source env was not executed by the load task. The load task copied the table from Cassandra. Therefore, it was needed to run a extract task on a given table before running the load task to load the table to the target environment.

[![Previous](/articles/images/Previous.png)](08_tdm_implement_delete_of_entities.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_tdm_generic_broadway_flows.md)





