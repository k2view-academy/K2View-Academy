# TDM Reference Processes

The list of Reference tables available for TDM tasks is populated in the [trnRefList](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnreflist) translation object.  TDM Extract tasks store the selected Reference data in the Cassandra DB and the TDM Load tasks select the Reference tables from Cassandra and load them into the target. 

The Cassandra table of each Reference table must be created before running TDM Extract tasks to store data in Cassandra.

### TDM LU - fnValidateAndRebuildRefTables Job 

This job creates the **schema** of each Reference table in the **Cassandra DB** under the **k2view_tdm keyspace**. 

The job scans the trnRefList and checks whether a table exists in Cassandra: 

1.  If the table does not exist in Cassandra, it gets the table structure from the source DB based on the interface name, schema name and table name in  the trnRefList, and creates the table in the Cassandra DB.
2. If the table exists in the Cassandra DB, it compares the structure of the Cassandra table to the structure of the source table. If the structure of the tables does not match, it terminates the related TDM versions for this table by populating the version_expiration_date of task_execution_list TDM DB table with the current date and re-creates this table in the Cassandra DB.

### TDM LU - tdmCopyRefTablesForTDM Job

This job is executed on each Reference table by the [main task execution process](03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) of the Extract task and populates the Reference table in the Cassandra DB. It selects the data of the Reference table from the source DB. The source DB Interface Name and Schema Name settings are taken from trnRefList. The selected records are populated into the Reference Cassandra table. 

The job updates the status of the processed Reference table in the [task_ref_exe_stats](02_tdm_database.md#task_ref_exe_stats) TDM DB table: 

- If the copy succeeds, sets the status to **completed**.
- If the copy fails, sets the status to **failed** and populates the error_msg field in the error message. (0)

### Reference Cassandra Table
[TDM Extract tasks](/articles/TDM/tdm_gui/16_extract_task.md) can extract data from different source environments or can create [different versions](/articles/TDM/tdm_gui/15_data_flux_task.md) of a selected Reference table. As a result, each Cassandra table created for a Reference table, must store different versions of the Reference table. Each Cassandra table created for a Reference table contains the following columns to store Reference data for different source environments and different versions:

- SOURCE_ENV_NAME,  populated by the source environment.
- TASK_EXECUTION_ID, by default populated by **ALL**. When running a TDM Extract task in [Data Flux mode](/articles/TDM/tdm_gui/16_extract_task.md#entity-versioning), this column is populated by the task_execution_id of the task execution. 
- REC_ID, an internal sequence set on each record and added to the **PK** of the Cassandra table.

In addition to the columns above, each Cassandra table also contains the list of columns of the Reference table in the source DB.

The **PK** (primary key) of each Cassandra table consists of the following columns:

- SOURCE_ENV_NAME
- TASK_EXECUTION_ID
- REC_ID
- If the source table has a primary key, the columns in a primary key are added to the primary key fields of the Cassandra table. 

**Example:**

- CUSTOMER_TYPE Reference table. This table has three fields: CUSTOMER_TYPE, CUSTOMER_SUB_TYPE and DESCRIPTION.
- CUSTOMER_TYPE is populated as follows in **ENV1**:

| CUSTOMER_TYPE | CUSTOMER_SUB_TYPE | DESCRIPTION       |
| ------------- | ----------------- | ----------------- |
| I             | Private           | Private customer |
| B             | S                 | Small business    |

-  CUSTOMER_TYPE is populated as following in **ENV2**:

| CUSTOMER_TYPE | CUSTOMER_SUB_TYPE | DESCRIPTION         |
| ------------- | ----------------- | ------------------- |
| I             | Private           | Private customer   |
| B             | S                 | Small business      |
| B             | M                 | Medium business     |
| B             | C                 | Corporate customer |

 

- Creating an Extract task with a regular mode (Entity Versioning setting is cleared) for CUSTOMET_TYPE Reference table:

| SOURCE_ENV_NAME | TASK_EXECUTION_ID | REC_ID | CUSTOMER_TYPE | CUSTOMER_SUB_TYPE | DESCRIPTION       |
| --------------- | ----------------- | ------ | ------------- | ----------------- | ----------------- |
| ENV1            | ALL               | 1      | I             | Private           | Private  customer |
| ENV1            | ALL               | 2      | B             | S                 | Small business    |

 

- Creating an Extract Data Flux task for CUSTOMET_TYPE Reference table on ENV1.The records of the created version are added to the Cassandra table:

| SOURCE_ENV_NAME | TASK_EXECUTION_ID | REC_ID | CUSTOMER_TYPE | CUSTOMER_SUB_TYPE | DESCRIPTION       |
| --------------- | ----------------- | ------ | ------------- | ----------------- | ----------------- |
| ENV1            | ALL               | 1      | I             | Private           | Private customer |
| ENV1            | ALL               | 2      | B             | S                 | Small business    |
| ENV1            | 1234              | 1      | I             | Private           | Private customer |
| ENV1            | 1234              | 2      | B             | S                 | Small business    |

 

- Create an Extract Task with a regular mode for CUSTOMET_TYPE on ENV2:

| SOURCE_ENV_NAME | TASK_EXECUTION_ID | REC_ID | CUSTOMER_TYPE | CUSTOMER_SUB_TYPE | DESCRIPTION         |
| --------------- | ----------------- | ------ | ------------- | ----------------- | ------------------- |
| ENV1            | ALL               | 1      | I             | Private           | Private customer   |
| ENV1            | ALL               | 2      | B             | S                 | Small business      |
| ENV1            | 1234              | 1      | I             | Private           | Private customer   |
| ENV1            | 1234              | 2      | B             | S                 | Small business      |
| ENV2            | ALL               | 1      | I             | Private           | Private customer   |
| ENV2            | ALL               | 2      | B             | S                 | Small business      |
| ENV2            | ALL               | 3      | B             | M                 | Medium business     |
| ENV2            | ALL               | 4      | B             | C                 | Corporate customer |

 

- The source data of ENV1 is updated and a new record is added to CUSTOMER_TYPE table for a Government customer type.
- Execute the regular Extract Task for CUSTOMET_TYPE on ENV1 again:

| SOURCE_ENV_NAME | TASK_EXECUTION_ID | REC_ID | CUSTOMER_TYPE | CUSTOMER_SUB_TYPE | DESCRIPTION        |
| --------------- | ----------------- | ------ | ------------- | ----------------- | ------------------ |
| ENV1            | ALL               | 1      | I             | Private           | Private customer  |
| ENV1            | ALL               | 2      | B             | S                 | Small business     |
| ENV1            | ALL               | 3      | B             | G                 | Government         |
| ENV1            | 1234              | 1      | I             | Private           | Private customer  |
| ENV1            | 1234              | 2      | B             | S                 | Small business     |
| ENV2            | ALL               | 1      | I             | Private           | Private customer  |
| ENV2            | ALL               | 2      | B             | S                 | Small business     |
| ENV2            | ALL               | 3      | B             | M                 | Medium business    |
| ENV2            | ALL               | 4      | B             | C                 | Corporate customer |

 

- The table is re-populated by the data of ENV1 and ALL version. The records of the specific version of ENV1 - 1234, remain unchanged. 

  
    


 [![Previous](/articles/images/Previous.png)](04_task_execution_overridden_parameters.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdmdb_cleanup_process.md)

  