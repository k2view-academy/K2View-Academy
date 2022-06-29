# TDM Reference Processes

The list of Reference tables available for TDM tasks is populated in the [trnRefList](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnreflist) translation object.  TDM Extract tasks store the selected Reference data in the Cassandra DB and the TDM Load tasks select the Reference tables from Cassandra and load them into the target. 

### TDM LU - tdmCopyRefTablesForTDM Job

This job is executed on each Reference table by the [main task execution process](03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) of the Extract task and populates the Reference table in the Cassandra DB. It selects the data of the Reference table from the source DB. The source DB Interface Name and Schema Name settings are taken from trnRefList. If/when required, the job creates the Reference Cassandra table, selects the data from the source table, and populates into the Cassandra table. 

The job updates the status of the processed Reference table in the [task_ref_exe_stats](02_tdm_database.md#task_ref_exe_stats) TDM DB table: 

- If the copy succeeds, sets the status to **completed**.
- If the copy fails, sets the status to **failed** and populates the error_msg field in the error message.

### Reference Cassandra Table
[TDM Extract tasks](/articles/TDM/tdm_gui/16_extract_task.md) can either extract data from different source environments or create [different versions](/articles/TDM/tdm_gui/15_data_flux_task.md) of a selected Reference table. As a result, each Cassandra table, which is created for a Reference table, must store different versions of the Reference table. Each such Cassandra table contains the following columns with the purpose of storing Reference data for different source environments and different versions:

- SOURCE_ENV_NAME - populated by the source environment.
- TASK_EXECUTION_ID - populated - by default - by **ALL**. When running a TDM Extract task in [Data Versioning mode](/articles/TDM/tdm_gui/16_extract_task.md#entity-versioning), this column is populated by the task_execution_id of the task execution. 
- TDM_REC_ID - an internal sequence set on each record and added to the **PK** of the Cassandra table.
- RED_DATA - a text field, which contains a JSON with the selected source record.

The **PK** (primary key) of each Cassandra table consists of the following columns:

- SOURCE_ENV_NAME
- TASK_EXECUTION_ID
- TDM_REC_ID

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

 

- Creating and executing an Extract task with a regular mode (Data Versioning setting is cleared) to extract CUSTOMET_TYPE Reference table from ENV1. The Cassandra table is populated as follows:

  
  
  <table width="900pxl">
  <tbody>
  <tr class="md-end-block md-focus-container">
  <th style="width: 161.432px;"><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th style="width: 172.591px;"><span class="td-span"><span class="md-plain">TASK_EXECUTION_ID</span></span></th>
  <th style="width: 104.25px;"><span class="td-span"><span class="md-plain">TDM_REC_ID</span></span></th>
  <th style="width: 83.5114px;"><span class="td-span"><span class="md-plain">REC_DATA</span></span></th>
  </tr>
  </tbody>
  <tbody>
  <tr class="md-end-block">
  <td style="width: 161.432px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104.25px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 83.5114px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}<br /></span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container">
  <td style="width: 161.432px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104.25px;"><span class="td-span"><span class="md-plain">2</span></span></td>
  <td style="width: 83.5114px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  </tbody>
  </table>

 

- Creating an Extract Data Versioning task for CUSTOMET_TYPE Reference table on ENV1. The records of the created version are added to the Cassandra table:

  
  
  <table style="height: 90px; width: 929px;" width="900pxl">
  <tbody>
  <tr class="md-end-block md-focus-container" style="height: 18px;">
  <th style="width: 161px; height: 18px;"><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th style="width: 172px; height: 18px;"><span class="td-span"><span class="md-plain">TASK_EXECUTION_ID</span></span></th>
  <th style="width: 104px; height: 18px;"><span class="td-span"><span class="md-plain">TDM_REC_ID</span></span></th>
  <th style="width: 492px; height: 18px;"><span class="td-span"><span class="md-plain">REC_DATA</span></span></th>
  </tr>
  <tr class="md-end-block" style="height: 36px;">
  <td style="width: 161px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172px; height: 36px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104px; height: 36px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}<br /></span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container" style="height: 36px;">
  <td style="width: 161px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172px; height: 36px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104px; height: 36px;"><span class="td-span"><span class="md-plain">2</span></span></td>
  <td style="width: 492px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  <tr class="md-end-block" style="height: 36px;">
  <td style="width: 161px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172px;"><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td style="width: 104px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container" style="height: 36px;">
  <td style="width: 161px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172px;"><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td style="width: 104px;"><span class="td-span"><span class="md-plain">2</span></span></td>
  <td style="width: 492px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  </tbody>
  </table>
  
  

- Create an Extract Task with a regular mode for CUSTOMET_TYPE on ENV2:

  <table style="height: 234px; width: 929px;" width="900pxl">
  <tbody>
  <tr class="md-end-block md-focus-container" style="height: 18px;">
  <th style="width: 161.432px; height: 18px;"><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain">TASK_EXECUTION_ID</span></span></th>
  <th style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">TDM_REC_ID</span></span></th>
  <th style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">REC_DATA</span></span></th>
  </tr>
  <tr class="md-end-block" style="height: 36px;">
  <td style="width: 161.432px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px; height: 36px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104.25px; height: 36px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492.557px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}<br /></span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container" style="height: 36px;">
  <td style="width: 161.432px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px; height: 36px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104.25px; height: 36px;"><span class="td-span"><span class="md-plain">2</span></span></td>
  <td style="width: 492.557px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  <tr class="md-end-block" style="height: 36px;">
  <td style="width: 161.432px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px; height: 36px;"><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td style="width: 104.25px; height: 36px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492.557px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container" style="height: 36px;">
  <td style="width: 161.432px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px; height: 36px;"><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td style="width: 104.25px; height: 36px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492.557px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}<br /></span></span></td>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain"> ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">2&nbsp;</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain"> ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">3</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"M","description":"Medium business"}<br /></span></span></td>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain"> ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">4&nbsp;</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"C","description":"Corporate customer"}</span></span></td>
  </tr>
  </tbody>
  </table>
  
  

- The source data of ENV1 is updated and a new record is added to CUSTOMER_TYPE table for a Government customer type.

- Execute again the regular Extract Task for CUSTOMET_TYPE on ENV1 again:

  
  
  <table style="height: 234px; width: 929px;" width="900pxl">
  <tbody>
  <tr class="md-end-block md-focus-container" style="height: 18px;">
  <th style="width: 161.432px; height: 18px;"><span class="td-span md-focus"><span class="md-plain md-expand">SOURCE_ENV_NAME</span></span></th>
  <th style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain">TASK_EXECUTION_ID</span></span></th>
  <th style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">TDM_REC_ID</span></span></th>
  <th style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">REC_DATA</span></span></th>
  </tr>
  <tr class="md-end-block" style="height: 36px;">
  <td style="width: 161.432px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px; height: 36px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104.25px; height: 36px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492.557px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}<br /></span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container" style="height: 36px;">
  <td style="width: 161.432px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px; height: 36px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104.25px; height: 36px;"><span class="td-span"><span class="md-plain">2</span></span></td>
  <td style="width: 492.557px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  <tr>
  <td style="width: 161.432px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px;"><span class="td-span"><span class="md-plain">ALL</span></span></td>
  <td style="width: 104.25px;"><span class="td-span"><span class="md-plain">3</span></span></td>
  <td style="width: 492.557px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"G","description":"Government"}</span></span></td>
  </tr>
  <tr class="md-end-block" style="height: 36px;">
  <td style="width: 161.432px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px; height: 36px;"><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td style="width: 104.25px; height: 36px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492.557px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}</span></span></td>
  </tr>
  <tr class="md-end-block md-focus-container" style="height: 36px;">
  <td style="width: 161.432px; height: 36px;"><span class="td-span"><span class="md-plain">ENV1</span></span></td>
  <td style="width: 172.591px; height: 36px;"><span class="td-span"><span class="md-plain">1234</span></span></td>
  <td style="width: 104.25px; height: 36px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492.557px; height: 36px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain">ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">1</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">{"customer_type":"I","customer_sub_type":"P","description":"Private customer"}<br /></span></span></td>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain"> ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">2&nbsp;</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"S","description":"Small business"}</span></span></td>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain"> ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">3</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"M","description":"Medium business"}<br /></span></span></td>
  </tr>
  <tr style="height: 18px;">
  <td style="width: 161.432px; height: 18px;"><span class="td-span"><span class="md-plain"> ENV2</span></span></td>
  <td style="width: 172.591px; height: 18px;"><span class="td-span"><span class="md-plain"> ALL</span></span></td>
  <td style="width: 104.25px; height: 18px;"><span class="td-span"><span class="md-plain">4&nbsp;</span></span></td>
  <td style="width: 492.557px; height: 18px;"><span class="td-span"><span class="md-plain">{"customer_type":"B","customer_sub_type":"C","description":"Corporate customer"}</span></span></td>
  </tr>
  </tbody>
  </table>
  
   

- The table is re-populated by the data of ENV1 and ALL version. The records of the specific version of ENV1 - 1234, remain unchanged. 

  
  


 [![Previous](/articles/images/Previous.png)](04_task_execution_overridden_parameters.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdmdb_cleanup_process.md)

  
