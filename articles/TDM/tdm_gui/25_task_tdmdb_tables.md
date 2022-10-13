# Task - TDM DB Tables

TDM settings are saved in the [TDM PostgreSQL DB](/articles/TDM/tdm_architecture/02_tdm_database.md). 

TDM tasks update the following TDM DB tables:

## Tasks

This table holds all [TDM tasks](14_task_overview.md) defined in  the TDM GUI.

- A new record is created for each task.

- Each task record gets a unique **task_id** sequence which is the table's PK.

  ### Task General Information

  - **task_title**  - the task name. To prevent creating several active tasks with the same name, the **task_title** column has a **unique index** when the status is **Active**.
  - **task_type** - **Extract** , **Load**, or **Reserve**.
  - **be_id** - the task's BE. The be_id can be linked to the **product_logical_units** TDM DB table. 
  - **number_of_entities** - populated by the number of entities in the task.
  - **load_entities** - populated with **true** for Load tasks. Otherwise, populated with **false**.
  - **delete_before_load**- populated with **true** for **Delete** or **Load and Delete** tasks. Otherwise populated with **false**.
  - **task_created_by**, and  **task_last_updated_by** - populated by the name of the user who creates the task.
  - **task_creation_date** and **task_last_updated_date** - populated by the task's creation datetime.

  ### Reservation Information

  - **reserve_ind**  - indicates if the task reserved entities on the environment.
  - **reserve retention period fields** - define the reservation period on the task's entities.

  ### Map the TDM GUI Task Types to Tasks TDM Table

  <table width="900pxl">
  <tbody>
  <tr>
  <td width="400pxl">
  <p><strong>Task Type Combination</strong></p>
  </td>
  <td width="500pxl">
  <p><strong>TDM Tasks Table Population</strong></p>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Extract</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Extract</li>
  <li>load_entity = false</li>
  <li>delete_before_load=false</li>
  <li>reserve_ind =false</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Extract + Load</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = true</li>
  <li>delete_before_load=false</li>
  <li>reserve_ind =false</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Extract + Load + Reserve</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = true</li>
  <li>delete_before_load=false</li>
  <li>reserve_ind =true</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Extract + Load + Delete</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = true</li>
  <li>delete_before_load=true</li>
  <li>reserve_ind =false</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Extract + Load + Delete + Reserve</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = true</li>
  <li>delete_before_load=true</li>
  <li>reserve_ind =true</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Load</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = true</li>
  <li>delete_before_load=false</li>
  <li>reserve_ind =false</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Load + Reserve</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = true</li>
  <li>delete_before_load=false</li>
  <li>reserve_ind =true</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Load + Delete</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = true</li>
  <li>delete_before_load=true</li>
  <li>reserve_ind =false</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Load + Delete + Reserve</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = true</li>
  <li>delete_before_load=true</li>
  <li>reserve_ind =true</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="400pxl">
  <p>Delete</p>
  </td>
  <td width="500pxl">
  <ul>
  <li>task_type = Load</li>
  <li>load_entity = false</li>
  <li>delete_before_load=true</li>
  <li>reserve_ind =false</li>
  </ul>
  </td>
  </tr>
  <tr style="height: 118px;">
  <td style="width: 294.673px; height: 118px;">
  <p>Reserve</p>
  </td>
  <td style="width: 589.929px; height: 118px;">
  <ul>
  <li>task_type = Reserve</li>
  <li>load_entity = false</li>
  <li>delete_before_load=true</li>
  <li>reserve_ind =false</li>
  </ul>
  </td>
  </tr>
  </tbody>
  </table>

  ### Task Status

  - **task_status**: each task is created in **Active** task_status. Deleted tasks have an **Inactive** task_status and are not physically deleted from this table.
  - **task_execution_status**: 
    - **Active** - the task can be executed.
    - **onHold** - [pause the task](/articles/TDM/tdm_gui/26_task_execution.md#holding-task-execution) and set it on-hold.
    - **Inactive** - deleted task.

  ### Requested Entities Columns

  - **selection method**: populated based on the selection method in the [Requested Entities tab](14_task_overview.md#3-requested-entities). This column can be populated by either:
    - **L** - [Entity List](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#entity-list) 
    - **R** - [Random Selection](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#random-selection)
    - **S** - [Entity Clone (Synthetic)](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#entity-clone).
    - **PR** - [Parameters with a random selection checkbox](17_load_task_regular_mode.md#use-parameters-with-random-selection-checkbox).
    - **P** - [Parameters when a random selection checkbox is cleared](17_load_task_regular_mode.md#use-parameters-with-random-selection-checkbox).
    - **ALL** - [Select All entities of the Selected Version](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#select-all-entities-of-the-selected-version) on [load Data Versioning tasks](18_load_task_data_versioning_mode.md), or [Select a Predefined Entity List](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#select-a-predefined-entity-list) on [Extract](16_extract_task.md) tasks.
    - **REF** - create a [Reference Only](24_task_reference_tab.md) task.
    - **C** - [Custom Logic](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#custom-logic).
  - **selection_param_value**: populated when the task selection method is either Entity List, Parameters, Custom Logic or Entity Clone:

  <table width="900pxl">
  <tbody>
  <tr>
  <td width="300pxl">
  <p><strong>Task selection method</strong></p>
  </td>
  <td width="200pxl">
  <p><strong>selection_method</strong></p>
  </td>
  <td width="400pxl">
  <p><strong>Selection_param_value</strong></p>
  </td>
  </tr>
  <tr>
  <td width="300pxl">
  <p>Entity List</p>
  </td>
  <td width="200pxl">
  <p>L</p>
  </td>
  <td width="400pxl">
  <p>Populated by the list of entities separated by a comma.</p>
  <p><strong>Examples</strong>:</p>
  <ul>
  <li>1,2,3,4</li>
  <li>66</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="300pxl">
  <p>Parameters, with or without a random selection</p>
  </td>
  <td width="200pxl">
  <p>P, PR</p>
  </td>
  <td width="400pxl">
  <p>Populated by the SQL where statement, generated by the selected parameters.</p>
  <p><strong>Example</strong>:</p>
  <ul>
  <li>(( 'New York' = ANY("Customer.CITY") ))</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="300pxl">
  <p>Entity Clone (Synthetic)</p>
  </td>
  <td width="200pxl">
  <p>S</p>
  </td>
  <td width="400pxl">
  <p>Populated with the cloned entity ID.</p>
  <p><strong>Example</strong>:</p>
  <ul>
  <li>7889</li>
  </ul>
  </td>
  </tr>
  <tr>
  <td width="300pxl">
      <p>Custom Logic</p>    
  </td>
  <td>C</td>
  <td>Populated with the name of the selected Broadway flow that runs and gets the entity list for the task execution.</td>    
      </tr>    
  </tbody>
  </table>

  This column is used by the TDM task execution process to [create the entities list of the root LUs](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#root-lus) for each task.

   

  ### Data Versioning Parameters

  - **version_ind** - populated by **true** in a [Data Versioning task](15_data_flux_task.md).
  - **selected_version_task_name**, **selected_version_datetime**, and **selected_version_task_exe_id** - the selected entities when creating a load Data Versioning task to reload a selected version of entities into the target environment.
  - **selected_ref_version_task_name, selected_ref_version_datetime**, and **selected_ref_version_task_exe_id** - the selected Reference's version when creating a Data Versioning task to copy [a selected version of Reference tables](24_task_reference_tab.md) into the target environment.

  ### Environments Columns

  #### Source Environment

- source_env_name 

- source_environment_id

  #### Target Environment

- environment_id

### Task Execution Parameters

#### Scheduling Parameters

- **Scheduler** - set based on the task's [Execution Timing](22_task_execution_timing_tab.md):
  - **Execution by Request**, populated by **immediate**.
  - **Scheduled Execution**, populated by a **crontab** based on the selected scheduling parameters of the task.
- **scheduling_end_date** - populated by the **End Date** if set on scheduled tasks.

#### Extract Tasks - [Retention Period](16_extract_task.md#retention-period)

- **retention_period_type** and **retention_period_value**.
- Example:
  - Set the retention_period_type by **Days** and the retention_period_value by **5** to set a retention period of five days.

#### Load Tasks - [Request Parameters](19_load_task_request_parameters_regular_mode.md)


#### Other Parameters

- **replace_sequences**
- **sync_mode** - 
  - If the task does not override the sync mode, this column remains empty.
  - If the task overrides the [sync mode](19_load_task_request_parameters_regular_mode.md#override-sync-mode), populated by **FORCE** or **OFF**. 

 

## Tasks_Logical_Units

This table holds the LUs list of each task. A separate record is created for each LU.

## Tasks_Post_Exe_Process

-------------------------

This table holds a task's [post execution processes](04_tdm_gui_business_entity_window.md#post-execution-processes-tab). A new record is created for each post execution process.

This table holds the following columns:

-  **task_id** - a unique identifier of the task which links to the **Tasks** TDM DB table.
-  **process_id** - a unique identifier of the process which links [tdm_be_post_exe_process](06_be_product_tdmdb_tables.md#tdm_be_post_exe_process) TDM DB table.
-  **execution_order** - the  execution_order of the post execution process as defined in the [tdm_be_post_exe_process](06_be_product_tdmdb_tables.md#tdm_be_post_exe_process) TDM DB table. 

## Task_Globals

This table holds all [variables that are overridden by the task](23_task_globals_tab.md). A separate record is created for each variable.

## Task_Ref_Tables

This table holds a list of the task's [Reference tables](24_task_reference_tab.md). A separate record is created for each Reference table.

  [![Previous](/articles/images/Previous.png)](24_task_reference_tab.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](26_task_execution.md)



