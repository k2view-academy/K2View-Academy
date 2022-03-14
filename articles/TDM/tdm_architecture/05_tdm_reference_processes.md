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
- If the copy fails, sets the status to **failed** and populates the error_msg field in the error message.

### Reference Cassandra Table
[TDM Extract tasks](/articles/TDM/tdm_gui/16_extract_task.md) can extract data from different source environments or can create [different versions](/articles/TDM/tdm_gui/15_data_flux_task.md) of a selected Reference table. As a result, each Cassandra table created for a Reference table, must store different versions of the Reference table. Each Cassandra table created for a Reference table contains the following columns to store Reference data for different source environments and different versions:

- SOURCE_ENV_NAME,  populated by the source environment.
- TASK_EXECUTION_ID, by default populated by **ALL**. When running a TDM Extract task in [Data Versioning mode](/articles/TDM/tdm_gui/16_extract_task.md#entity-versioning), this column is populated by the task_execution_id of the task execution. 
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

  <table class="md-table">
  <thead>
  <tr class="md-end-block md-focus-container">
  <th><span class="td-span md-focus"><span class="md-plain md-expand">CUSTOMER_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_SUB_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">DESCRIPTION</span></span></th>
  </tr>
  </thead>
  <tbody>
  <tr class="md-end-block">
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span md-focus"><span class="md-plain md-expand">Small business</span></span></td>
  </tr>
  </tbody>
  </table>

- CUSTOMER_TYPE is populated as following in **ENV2**:

  <table class="md-table">
  <thead>
  <tr class="md-end-block md-focus-container">
  <th><span class="td-span md-focus"><span class="md-plain md-expand">CUSTOMER_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_SUB_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">DESCRIPTION</span></span></th>
  </tr>
  </thead>
  <tbody>
  <tr class="md-end-block">
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span md-focus"><span class="md-plain md-expand">Small business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">M</span></span></td>
  <td><span class="td-span md-focus"><span class="md-plain md-expand">Medium business</span></span></td>
  </tr> 
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">C</span></span></td>
  <td><span class="td-span md-focus"><span class="md-plain md-expand">Corporate customer</span></span></td>
  </tr>
  </tbody>
  </table>

 

- Creating an Extract task with a regular mode (Data Versioning setting is cleared) for CUSTOMET_TYPE Reference table:

  <table class="md-table">
  <thead>
  <tr class="md-end-block md-focus-container">
  <th><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th><span class="td-span"><span class="md-plain">TASK_EXECUTION_ID</span></span></th>
  <th><span class="td-span"><span class="md-plain">REC_ID</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_SUB_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">DESCRIPTION</span></span></th>
  </tr>
  </thead>
  <tbody>
  <tr class="md-end-block">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  </tbody>
  </table>

 

- Creating an Extract Data Versioning task for CUSTOMET_TYPE Reference table on ENV1.The records of the created version are added to the Cassandra table:

  <table class="md-table">
  <thead>
  <tr class="md-end-block md-focus-container">
  <th><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th><span class="td-span"><span class="md-plain">TASK_EXECUTION_ID</span></span></th>
  <th><span class="td-span"><span class="md-plain">REC_ID</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_SUB_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">DESCRIPTION</span></span></th>
  </tr>
  </thead>
  <tbody>
  <tr class="md-end-block">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  </tbody>
  </table>

 

- Create an Extract Task with a regular mode for CUSTOMET_TYPE on ENV2:

  <table class="md-table">
  <thead>
  <tr class="md-end-block md-focus-container">
  <th><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th><span class="td-span"><span class="md-plain">TASK_EXECUTION_ID</span></span></th>
  <th><span class="td-span"><span class="md-plain">REC_ID</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_SUB_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">DESCRIPTION</span></span></th>
  </tr>
  </thead>
  <tbody>
  <tr class="md-end-block">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">3</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">M</span></span></td>
  <td><span class="td-span"><span class="md-plain">Medium business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">4</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">C</span></span></td>
  <td><span class="td-span"><span class="md-plain">Corporate customer</span></span></td>
  </tr>
  </tbody>
  </table>



- The source data of ENV1 is updated and a new record is added to CUSTOMER_TYPE table for a Government customer type.

- Execute the regular Extract Task for CUSTOMET_TYPE on ENV1 again:

  <table class="md-table">
  <thead>
  <tr class="md-end-block md-focus-container">
  <th><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th><span class="td-span"><span class="md-plain">TASK_EXECUTION_ID</span></span></th>
  <th><span class="td-span"><span class="md-plain">REC_ID</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">CUSTOMER_SUB_TYPE</span></span></th>
  <th><span class="td-span"><span class="md-plain">DESCRIPTION</span></span></th>
  </tr>
  </thead>
  <tbody>
  <tr class="md-end-block">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">3</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">G</span></span></td>
  <td><span class="td-span"><span class="md-plain">Government</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">1</span></span></td>
  <td><span class="td-span"><span class="md-plain">I</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">2</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span"><span class="md-plain">Small business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">3</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">M</span></span></td>
  <td><span class="td-span"><span class="md-plain">Medium business</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td><span class="td-span"><span class="md-plain">4</span></span></td>
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">C</span></span></td>
  <td><span class="td-span"><span class="md-plain">Corporate customer</span></span></td>
  </tr>
  </tbody>
  </table>

 

- The table is re-populated by the data of ENV1 and ALL version. The records of the specific version of ENV1 - 1234, remain unchanged. 

  
  


 [![Previous](/articles/images/Previous.png)](04_task_execution_overridden_parameters.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdmdb_cleanup_process.md)

  
