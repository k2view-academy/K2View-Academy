# TDM Reference Processes

The list of Reference tables available for TDM tasks is populated in the [trnRefList](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnreflist) translation object.  TDM 7.5.3 stores the Reference tables in a dedicated LU: **TDM_Reference**. Each Reference table is stored as a separate LUI.  

Previous TDM versions stored the extracted reference tables in the Cassandra DB:  the TDM Extract tasks stored the selected reference data in the Cassandra DB and the TDM Load tasks selected the reference tables from Cassandra and loaded them into the target. 



## Task Execution Job

The [main task execution process](03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) runs TDM Broadway flows in order to get the reference table into Fabric (TDM_Reference LU) and load it to the target for a load task. 

The job updates the status of the processed reference table in the [task_ref_exe_stats](02_tdm_database.md#task_ref_exe_stats) TDM DB table: 

- If the process succeeds, it sets the status to **completed**.
- If the process fails, it sets the status to **failed** and populates the error_msg field with the error message.

## TDM_Reference LU

The LUI contains the following:

[LU name]|[source environment name]|[version id]|[table name]

By default, the version id is populated by **ALL**. When running a TDM Extract task in [Data Versioning mode](/articles/TDM/tdm_gui/16_extract_task.md#entity-versioning), this setting is populated by the task_execution_id of the task execution. 

Examples:  

- Customer|SRC|ALL|DEVICESTABLE2017
- Customer|SRC|155|DEVICESTABLE2017



Note **that the [Sync method](/articles/14_sync_LU_instance/04_sync_methods.md) LU property is set by default to None**, i.e. each LUI (reference table) is synced only once. You need to edit this property in order to enable a recurring sync of the reference table from the source environment. 



### TDM_Refrence LU - reference_table LU Table 

The reference_table LU table keeps the reference data. The LU table has the following fields:

- TABLE_NAME - populated with the reference table name, e.g., CUSTOMER_TYPE.
- TDM_TASK_EXECUTION_ID - populated, by default, with **ALL**. When running a TDM Extract task in [Data Versioning mode](/articles/TDM/tdm_gui/16_extract_task.md#entity-versioning), this column is populated by the task_execution_id of the task execution. 
- SOURCE_ENV_NAME - populated by the source environment.
- TABLE_DATA - the table data is extracted into a JSON structure. The JSON is compressed and saved in a BLOB field. 
- RECORD_COUNT - number of records of the reference table.
- TABLE_FIELDS - list of the reference table's fields.



**Example:**

- CUSTOMER_TYPE Reference table. This table has 3 fields: CUSTOMER_TYPE, CUSTOMER_SUB_TYPE and DESCRIPTION.

- CUSTOMER_TYPE is populated in **ENV1** as follows:

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
  <td><span class="td-span"><span class="md-plain">P</span></span></td>
  <td><span class="td-span"><span class="md-plain">Private customer</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td><span class="td-span"><span class="md-plain">B</span></span></td>
  <td><span class="td-span"><span class="md-plain">S</span></span></td>
  <td><span class="td-span md-focus"><span class="md-plain md-expand">Small business</span></span></td>
  </tr>
  </tbody>
  </table>

- CUSTOMER_TYPE is populated in **ENV2** as follows:

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
  <td><span class="td-span"><span class="md-plain">P</span></span></td>
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

 

- Creating and executing an Extract task with a regular mode (Data Versioning setting is cleared) to extract CUSTOMER_TYPE Reference table from ENV1.  The LUI is Customer|ENV1|ALL|CUSTOMER_TYPE.

- The LU table is populated as follows:

  
  
  <table width="900pxl">
  <tbody>
  <tr class="md-end-block md-focus-container">
  <th style="width: 161.432px;"><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th style="width: 172.591px;"><span class="td-span"><span class="md-plain">TDM_TASK_EXECUTION_ID</span></span></th>
  <th style="width: 83.5114px;"><span class="td-span"><span class="md-plain">TABLE_DATA</span></span></th>
  </tr>
  </tbody>
  <tbody>
  <tr class="md-end-block">
  <td style="width: 161.432px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 83.5114px;"><span class="td-span"><span class="md-plain">[{"customer_type":"I","customer_sub_type":"P","description":"Private customer"},
      {"customer_type":"B","customer_sub_type":"S","description":"Small business"}]<br /></span></span></td>
  </tr>
  </tbody>
  </table>

 

- Creating an Extract Data Versioning task for CUSTOMER_TYPE Reference table on ENV1. A new LUI is created: 

  The LUI is Customer|ENV1|1234|CUSTOMER_TYPE.

- The LU table is populated as follows:

  <table style="height: 90px; width: 929px;" width="900pxl">
  <tbody>
  <tr class="md-end-block md-focus-container" style="height: 18px;">
  <th style="width: 161px; height: 18px;"><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th style="width: 172px; height: 18px;"><span class="td-span"><span class="md-plain">TDM_TASK_EXECUTION_ID</span></span></th>
  <th style="width: 492px; height: 18px;"><span class="td-span"><span class="md-plain">TABLE_DATA</span></span></th>
  </tr>
  <tr class="md-end-block" style="height: 36px;">
  <td style="width: 161px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172px;"><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td style="width: 492px;"><span class="td-span"><span class="md-plain">[{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}, {"customer_type":"B","customer_sub_type":"S","description":"Small business"}]</span></span></td>
  </tr>
  </tbody>
  </table>
  
  
- Creating an Extract Task with a regular mode for CUSTOMER_TYPE on ENV2. A new LUI is created: 

  The LUI is Customer|ENV2|ALL|CUSTOMER_TYPE.

- The LU table is populated as follows:

  <table width="900pxl">
  <tbody>
  <tr class="md-end-block md-focus-container" style="height: 18px;">
  <th style="width: 161.432px; height: 18px;"><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain">TDM_TASK_EXECUTION_ID</span></span></th>
  <th style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">TABLE_DATA</span></span></th>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">[{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}, {"customer_type":"B","customer_sub_type":"S","description":"Small business"},
  {"customer_type":"B","customer_sub_type":"M","description":"Medium business"},
  {"customer_type":"B","customer_sub_type":"C","description":"Corporate customer"}]<br /></span></span></td>
  </tr>
  </tbody>
  </table>
  
  
  
  
  
  
  



 [![Previous](/articles/images/Previous.png)](04_task_execution_overridden_parameters.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdmdb_cleanup_process.md)

  
