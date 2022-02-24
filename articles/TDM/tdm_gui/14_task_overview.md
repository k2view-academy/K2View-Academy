# TDM Task Overview

Data provisioning is implemented by creating and executing TDM tasks. 

A TDM task is created in the TDM GUI. It holds a list of instructions and settings that define the type and subset of processed entities, the source and target environments and additional information. For example, create a task to copy 5 customers with small and medium business plans from Production into the UAT1 target environment.

The actual data provisioning is performed by the task execution where each task can be executed multiple times.

The following task types are supported by TDM:

- Extract task, extracts the selected entities or Reference tables from the selected source environment and saves this data in Fabric for later use.

- Load task, extracts the selected entities or Reference tables from the selected source environment and loads them to the selected target environment.

A task type can have either of the following task modes:

- [Data Flux](15_data_flux_task.md), whereby users can backup versions of data and reload them to the testing environment. To do so, check **Entity Versioning** to create a Data Flux task. 
- Regular, the default mode.


## Who Can Create a Task?
-  Admin users.
-  Environment owners can create a TDM task for their environment.
-  Testers who can create a TDM task for the environments they are attached to by a [TDM Environment role](10_environment_roles_tab.md):
   - Source environment, testers must be attached to the source environment by a role with [Read](10_environment_roles_tab.md#read-and-write-and-number-of-entities) access.
   - Target environment, testers must be attached to the target environment by a role with [Write](10_environment_roles_tab.md#read-and-write-and-number-of-entities) access.



## TDM Tasks List Window

The TDM Task List displays the following:

- Task Title, task name.
- Task Type, Load / Extract.
- [Entity Versioning](15_data_flux_task.md), true / false.
- BE name.
- Source and Target environments. 
- Selection criteria for entities.
- Number of processed entities.
- General parameters like created by user and update date. 

The following screenshot shows an example of the TDM Task List. 

  ![tasks list](images/tdm_task_list_window.png)

  

1.  Click **Show/Hide Columns** to open a popup window displaying the list of available fields for each task. Fields in green are displayed by default. 
2.  To display additional fields, click the fields.
3.  To remove a field from the display, click the field:

![show hide columms](images/task_list_show_hide_columns.png)

4. To find a field, click **Search** and filter the displayed tasks using the filters.

The TDM GUI displays a list of icons next to each task record:

- ![task icon](images/execute_task_icon.png)[Execute Task](26_task_execution.md). 
- ![task icon](images/hold_task_icon.png) [Hold Task](26_task_execution.md#holding-task-execution), set the task on-hold temporarily.
- ![task icon](images/save_as_icon.png) Save As, copy the task into a new task.
- ![task icon](images/task_execution_history_icon.png)[Task Execution History](27_task_execution_history.md), display the execution history of the selected task.



### How Do I Create or Edit a Task?

1. Click **New Task** in the right corner of the Tasks List window.
2. To open a selected task, click the **Task Title** (task name) of the task.
3. Click the **Back** of **Next** buttons to move between the tabs. 
4. Click **Finish** in the last tab to create the task.
Once the task has been edited a new version with a new task_id is created. The old version is saved in the TDM DB for tracking purposes and its status is set to Inactive.

 [![Previous](/articles/images/Previous.png)](13_reserved_entities_window.mds)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](15_data_flux_task.md)

