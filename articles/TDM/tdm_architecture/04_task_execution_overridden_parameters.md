# Task Execution - Overriding Parameters

A task execution can override execution parameters, as follows:

- Setting the active environment based on the task's environments.
- Setting key-value parameters on a session level.
- Overriding Globals values on a session level.
- Overriding the [Sync Mode](#overriding-the-sync-mode-on-the-task-execution) of the task execution.
- Overriding additional execution parameters without changing the task itself.

### Setting Active Environments

#### Extract Tasks

The [TDM Execution process](03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) sets the task's environment as the [active environment](/articles/25_environments/05_set_and_list_commands.md) on the executed task.

#### Load Tasks

The [TDM Execution process](03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) sets the [active environment](/articles/25_environments/05_set_and_list_commands.md):

1. Sets the task's source environment as the active environment. 
2. Gets the LUI from Fabric.
3. Sets the task's target environment as the active environment. 
4. Runs the delete and/or load flows on the target environment.

### Setting Key-Value Parameters

Set execution parameters like replace sequence, delete before load or insert to target indicators. These parameters are based on the task's settings and are set on a session level.

### Overriding Globals Values

A project's Globals can be overridden on a [TDM environment](/articles/TDM/tdm_gui/12_environment_globals_tab.md) or [TDM task](/articles/TDM/tdm_gui/23_task_globals_tab.md) level.

The TDM execution process sets the values on the Globals on a [session level](/articles/08_globals/03_set_globals.md#how-do-i-use-the-set-command).

Note that task level Globals have a higher priority than TDM environment level Globals, i.e. if a Global variable is set on both - the task and the related environment levels - the task's Global value is set.

### Overriding the Sync Mode on the Task Execution 

When executing a TDM task, set the Sync mode according to the following table:

<table width="700pxl">
<tbody>
<tr>
<td valign="top" width="100pxl">
<p><strong>Override Sync - Source Env Level</strong></p>
</td>
<td valign="top" width="100pxl">
<p><strong>Override Sync - Task Level&nbsp;</strong></p>
</td>
<td valign="top" width="90pxl">
<p><strong>Task Operation Mode</strong></p>
</td>
<td valign="top" width="90pxl">
<p><strong>Task Execution Sync Mode</strong></p>
</td>
<td valign="top" width="200pxl">
<p><strong>Results</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>None</p>
</td>
<td valign="top" width="100pxl">
<p>None</p>
</td>
<td valign="top" width="90pxl">
<p>All</p>
</td>
<td valign="top" width="90pxl">
<p>On</p>
</td>
<td valign="top" width="200pxl">
<p>LUIs are synced according to their sync method. See the <a href="/articles/14_sync_LU_instance/10_sync_behavior_summary.md">Sync Behavior Summary table</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>None</p>
</td>
<td valign="top" width="100pxl">
<p>Request up-to-date entity</p>
</td>
<td valign="top" width="90pxl">
<p>All</p>
</td>
<td valign="top" width="90pxl">
<p>Force</p>
</td>
<td valign="top" width="200pxl">
<p>LUI are synced from the source.</p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Always sync</p>
</td>
<td valign="top" width="100pxl">
<p>None</p>
</td>
<td valign="top" width="100pxl">
<p>All</p>
</td>
<td valign="top" width="100pxl">
<p>Force</p>
</td>
<td valign="top" width="200pxl">
<p>LUI are synced from the source.</p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Always sync</p>
</td>
<td valign="top" width="100pxl">
<p>Request up-to-date entity</p>
</td>
<td valign="top" width="100pxl">
<p>All</p>
</td>
<td valign="top" width="100pxl">
<p>Force</p>
</td>
<td valign="top" width="200pxl">
<p>LUI are synced from the source.</p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Do not sync</p>
</td>
<td valign="top" width="100pxl">
<p>Request up-to-date entity</p>
</td>
<td valign="top" width="100pxl">
<p>All</p>
</td>
<td valign="top" width="100pxl">
<p>Force</p>
</td>
<td valign="top" width="200pxl">
<p>LUI are synced from the source.</p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Do not sync</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>None</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>Insert</p>
</td>
<td valign="top" width="100pxl">
<p>Off</p>
</td>
<td valign="top" width="200pxl">
<ul>
<li>First sync, return an error.</li>
<li>If the LUIs exist in Fabric:</li>
    Get the data from Fabric.
</ul>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Delete and insert&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>On</p>
</td>
    <td valign="top" width="100pxl"></td>
    <td valign="top" width="100pxl"></td>
<td valign="top" width="200pxl">
<ul>
<li>First sync, return an error.</li>
<li>If the LUIs exist in Fabric:
<ul>
<li>Source LU tables:</li>
    Get the data from Fabric.
<li>Target LU tables:</li>
    Sync the data from the target environment.
</ul>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Delete only</p>
</td>
<td valign="top" width="100pxl">
<p>On</p>
</td>
    <td valign="top" width="100pxl"></td>
    <td valign="top" width="100pxl"></td>
<td valign="top" width="200pxl">
    <br>Target LU tables are synced</br>    
    <br>from the target environment.</br>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Do not sync</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>Do not sync source data</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>Insert</p>
</td>
<td valign="top" width="100pxl">
<p>Off</p>
</td>
<td valign="top" width="300pxl">
<ul>
<li>First sync, return an error.</li>
    <li>If the LUIs exist in Fabric:</li>
     Get the data from Fabric.&nbsp;
</ul>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Delete and insert&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>On</p>
</td>
    <td valign="top" width="100pxl"></td>
    <td valign="top" width="100pxl"></td>
<td valign="top" width="200pxl">
<ul>
<li>First sync, return an error.</li>
<li>If the LUIs exist in Fabric:
<ul>
<li>Source LU tables:</li>
    Get the data from Fabric.
<li>Target LU tables:</li>
     Get the data from the target environment.
</ul>
</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>None</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>Do not sync source data</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>Insert</p>
</td>
<td valign="top" width="100pxl">
<p>Off</p>
</td>
<td valign="top" width="200pxl">
<ul>
<li>First sync, return an error.</li>
<li>If the LUIs exist in Fabric:</li>
    Get the data from Fabric.
</ul>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<p>Delete and insert&nbsp;</p>
</td>
<td valign="top" width="100pxl">
<p>On</p>
</td>
     <td valign="top" width="100pxl"></td>
     <td valign="top" width="100pxl"></td>
<td valign="top" width="200pxl">
<ul>
<li>First sync, return an error.</li>
<li>If the LUIs exist in Fabric:
<ul>
<li>Source LU tables:</li>
    Get the data from Fabric.
<li>Target LU tables:</li>
    Get the data from the target environment.
</ul>
</li>
</ul>
</td>
</tr>
</tbody>
</table>



# Overriding Additional Task Execution Parameters

The TDM API that [starts a task execution](/articles/TDM/tdm_gui/TDM_Task_Execution_Flows_APIs/04_execute_task_API.md) can get a list of parameter-value pairs to **override the original values** of these parameters on the task execution **without changing the task data**. 

This way, various users can **use a task as a template** and change (override) the execution parameters without changing the task itself: each user can run the task on their environment and update the execution parameter based on their needs.

TDM supports the override of the following parameters:

- [TDM Environments](/articles/TDM/tdm_gui/07_tdm_gui_environment_overview.md): 
  - Source Environment on a [Extract task](/articles/TDM/tdm_gui/16_extract_task.md).
  - Source and/or Target Environments on a [Load task](/articles/TDM/tdm_gui/17_load_task_regular_mode.md).
- Globals: 
  - Adding Globals.
  - Updating the values of the [task's Globals](/articles/TDM/tdm_gui/23_task_globals_tab.md).

- [Entity List](/articles/TDM/tdm_gui/18_load_task_requested_entities_regular_mode.md#entities-list) : provide a list of entities separated by a comma instead of the task's entity list. Note that the Entity List can only contain one entity ID when executing a task with a Synthetic selection method. 
- [Selection  Method](/articles/TDM/tdm_gui/25_task_tdmdb_tables.md#requested-entities-columns) : set the overridden selection method to 'L' (Entity List), when overriding the task's Entity List, except for a task with a Synthetic selection method. It is possible to provide a single entity in the Entity List to clone the entity by the task with the Synthetic selection method.
- [Number of Entities](/articles/TDM/tdm_gui/17_load_task_regular_mode.md#number-of-entities) : change the number of entities to be processed by the task execution. This parameter is only relevant for Load tasks when the **entitylist** override parameter is not set.
- **dataVersionExecId**: populated with the task execution id of the selected data version. The parameter can be set on Data Versioning load tasks.
- **dataVersionRetentionPeriod**: populated with the the retention period of the extracted data version. This parameter contains the unit (Hours, Days, Weeks..) and the value.
- **reservationInd**: true/false. Set to true if the task execution needs to reserve the entities on the target environment. The parameter is relavant for load or reserve TDM tasks.
- **reservationPeriod**: populated with the the reservation period of the task's entities. This parameter is contains the unit (Hours, Days, Weeks..) and the value.

Notes:
- The TDM supports overriding the task execution parameters only when invoking the start task execution API outside the TDM GUI. **Currently this option is not supported when executing the task using the TDM GUI.**
- The execution parameters override is not supported for Data Flux tasks.

### Validate the Task Execution Parameters

This API validates the overridden parameters with the user's permissions on the task's environments:

- Overrides the source and/or target environments: check the user's permissions on the new environments and verify that the user has permissions to run the task on the environments.
- The task's environments are not overridden: check the user's permissions on the task's environments  and verify that the user has permissions to run the task on the environments.
- Overrides the Entity List or the Number of Entities: verify that the updated number of entities does not exceed the user's permissions on the environments on which the task needs to run. 
- Validate the number of reserved entities: if the task reserves the entities wheather the reservationInd is set to true in the task itself or in the overridden parameters, accumulate the number of entities in the task to the total number of reserved entities for the user on the target environment. If the total number of reserved entities exceeds the user's permissions on the environment, return an error. For example, if the user is allowed to reserved up to 70 entities in ST1 and there are 50 entities that are already reserved for the user in ST1, the user can reserve up to additional 20 entities in ST1.

If at least one of the validation fails, the API does not start the task and returns the validation errors. 

### Task Execution Process

The override of task execution parameters does not update the task itself, but only impacts the given task execution:

The [task execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md) gets the overridden parameters from [task_execution_override_attrs](/articles/TDM/tdm_architecture/02_tdm_database.md#task_execution_override_attrs) TDM DB table and executes the task based on the overridden parameters.




[![Previous](/articles/images/Previous.png)](03_task_execution_processes.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_tdm_reference_processes.md)

