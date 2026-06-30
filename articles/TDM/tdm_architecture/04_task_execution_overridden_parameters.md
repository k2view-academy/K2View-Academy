# Task Execution - Overriding Parameters

A task execution can override execution parameters by:

- setting the active environment based on the task's environments.
- setting key-value parameters on a session level.
- overriding [Globals'](/articles/08_globals/01_globals_overview.md) values on a session level.
- overriding the [Sync Mode](#overriding-the-sync-mode-on-the-task-execution) of the task execution.
- overriding additional execution parameters without changing the task itself.

### Setting Active Environments

#### Extract Tasks

The [task execution process](03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) sets the task's environment as the [active environment](/articles/25_environments/05_set_and_list_commands.md) on the executed task.

#### Load Tasks

The [task execution process](03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job) sets the [active environment](/articles/25_environments/05_set_and_list_commands.md) as follows:

1. It first sets the task's source environment as the active environment and gets the LUI from Fabric.
2. After the LUI sync, it sets the task's target environment as the active environment and runs the delete and/or load flows on the target environment.



### Overriding Globals' Values 

A project's Global can be overridden on either a [TDM environment](/articles/TDM/tdm_gui/12_environment_globals_tab.md) or a [TDM task](/articles/TDM/tdm_gui/23_task_globals_tab.md) level.

The task execution process sets the values on the Globals on a [session level](/articles/08_globals/03_set_globals.md#how-do-i-use-the-set-command).

Note: Task-level variables have a higher priority than TDM environment-level variables. That is, if a variable (Global) is set on both - the task and the related environment levels - the task's Global value gets the higher priority.

### Overriding the Sync Mode on the Task Execution 

When executing a TDM task, set the Sync mode according to the following table:

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="150pxl">
<p><strong>Source env -&nbsp; Override Sync Mode</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Task - Source env - policy for fetching data</strong></p>
</td>
<td valign="top" width="150pxl">
<p><strong>Execution Sync Mode</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Results</strong></p>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 120.094px;" valign="top">
<p>Empty</p>
</td>
<td valign="top" width="300pxl">
<ul>
<li>Available data from the Test data store, new data from &lt;source env&gt;&nbsp;</li>
</ul>
</td>
<td style="height: 64px; width: 121.688px;" valign="top">
<p>On</p>
</td>
<td valign="top" width="300pxl">
<p>LUIs are synced according to their sync method. See the <a href="/articles/14_sync_LU_instance/10_sync_behavior_summary.md">Sync Behavior Summary table</a>.</p>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 120.094px;" valign="top">
<p>Empty</p>
</td>
<td valign="top" width="300pxl">
<ul>
<li>All data from &lt;source env&gt;</li>
</ul>
</td>
<td style="height: 64px; width: 121.688px;" valign="top">
<p>Force</p>
</td>
<td valign="top" width="300pxl">
<p>LUIs are synced from the source.</p>
</td>
</tr>
<tr>
<td style="width: 120.094px;">
<p>Empty</p>
</td>
<td style="width: 120.797px;">
<ul>
<li>Available &lt;source env&gt; data in the Test data store&nbsp;</li>
<li>Selected snapshot (version)&nbsp;</li>
</ul>
</td>
<td style="width: 121.688px;">
<p>If the task includes a delete =&gt; On</p>
<p>Else =&gt; Off</p>
</td>
<td style="width: 384.422px;">
<ul>
<li>If this is the first sync, return an error.</li>
<li>If the LUIs exist in Fabric:
<ul>
<li>Source LU tables:</li>
Get the data from Fabric.
<li>Target LU tables (populated for a delete activity):</li>
Sync the data from the target environment.</ul>
</li>
</ul>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 120.094px;" valign="top">
<p>Always sync</p>
</td>
<td valign="top" width="300pxl">
<ul>
<li>Available data from the Test data store, new data from &lt;source env&gt;&nbsp;</li>
</ul>
</td>
<td style="height: 64px; width: 121.688px;" valign="top">
<p>Force</p>
</td>
<td valign="top" width="300pxl">
<p>LUIs are synced from the source.</p>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 120.094px;" valign="top">
<p>Always sync</p>
</td>
<td valign="top" width="300pxl">
<ul>
<li>All data from &lt;source env&gt;</li>
</ul>
</td>
<td style="height: 64px; width: 121.688px;" valign="top">
<p>Force</p>
</td>
<td valign="top" width="300pxl">
<p>LUIs are synced from the source.</p>
</td>
</tr>
<tr style="height: 155px;">
<td style="height: 155px; width: 120.094px;">
<p>Do Not Sync</p>
</td>
<td style="height: 155px; width: 120.797px;">
<ul>
<li>Only the following options are available in the task:
<ul>
<li>Available &lt;source env&gt; data in the Test data store&nbsp;</li>
<li>Selected snapshot (version)&nbsp;</li>
</ul>
</li>
</ul>
</td>
<td style="height: 155px; width: 121.688px;">
<p>If the task includes a delete =&gt; On</p>
<p>Else =&gt; Off</p>
</td>
<td style="height: 155px; width: 384.422px;">
<ul>
<li>If this is the first sync, return an error.</li>
<li>If the LUIs exist in Fabric:
<ul>
<li>Source LU tables:</li>
Get the data from Fabric.
<li>Target LU tables:</li>
Sync the data from the target environment.</ul>
</li>
</ul>
</td>
</tr>
</tbody>
</table>





#### Overriding Additional Task Execution Parameters

The TDM API that [starts a task execution](/articles/TDM/tdm_gui/TDM_Task_Execution_Flows_APIs/04_execute_task_API.md) can get a list of parameter-value pairs to **override the original values** of these parameters on the task execution **without changing the task data**. 

This way, various users can **use a task as a template** and change (override) the execution parameters without changing the task itself: Each user can run the task on their environment and update the execution parameter according to their needs.

Click [here](/articles/TDM/tdm_gui/14_task_overview.md#attributes-available-for-runtime-override) for the list of **task attributes that can be overridden at runtime**. 

### Task Execution Process

Overriding task execution parameters does not update the task itself, but rather impacts the given task execution:

The [task execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md) gets the overridden parameters from [task_execution_override_attrs](/articles/TDM/tdm_architecture/02_tdm_database.md#task_execution_override_attrs) TDM DB table and executes the task based on the overridden parameters.



[![Previous](/articles/images/Previous.png)](03b_task_execution_affinity_and_workers_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdmdb_cleanup_process.md)

