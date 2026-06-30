# Task Execution

A task can be executed multiple times. The [Task Execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md) can be initiated by either by the [task execution window](26_task_execution_window.md),  by calling the [start task execution API](/articles/TDM/tdm_gui/TDM_Task_Execution_Flows_APIs/04_execute_task_API.md), or by the TDM scheduling process if the task's [scheduler](22_task_execution_timing_tab.md) is defined.

The TDM Scheduling process checks the **End Date** of the task's scheduling parameters. If the End Date is earlier than the current date, the process cleans the task's **Scheduled Execution** parameters and skips the task execution. 

A task cannot be executed several times in parallel with the same execution attributes. An additional execution can be initiated only if the previous execution has ended.

## Task Execution Order

### Entity-based Task 

A TDM task can include multiple LUs with either a flat or hierarchical structure, and post-execution processes.

The execution of the related task's components runs in the following order:

1. [Pre-execution processes](21_task_pre_and_post_execution_processes.md), if they are added to the task. The pre-execution processes are executed according to their [execution order](04_tdm_gui_business_entity_window.md#pre-and-post-execution-processes-tabs) as defined in the task's BE. 

2. LUs - the execution order depends on the task's execution mode:

   - Horizontal execution - execution of the task LU by LU from parent to child, where all entities are processed in one LU before moving on to the next system in the hierarchy. 
   - Vertical execution - execution of the entire LU hierarchy for each root entity before moving on to the next root entity. Note that this mode is not available for a task that generates [entity clones](17a_task_target_component_entities.md#generate-clones-for-an-entity) and for synthetic entities generation.

   Click for more information about the [execution order of hierarchical LUs](/articles/TDM/tdm_overview/03_business_entity_overview.md#task-execution-of-hierarchical-business-entities).

3. [Post-execution processes](21_task_pre_and_post_execution_processes.md), if they are added to the task. The post-execution processes run after the execution of the LUs ends. The post-execution processes are executed according to their [execution order](04_tdm_gui_business_entity_window.md#pre-and-post-execution-processes-tabs) as defined in the task's BE. 

### Table-based Task

e execution of the related task's components runs in the following order:

1. [Pre-execution processes](21_task_pre_and_post_execution_processes.md), if they are added to the task. The pre-execution processes are executed according to their execution order as defined in the TDM implementation. 

2. [Tables processing](/articles/TDM/tdm_architecture/03_task_execution_processes.md#main-tdm-task-execution-process-tdmexecutetask-job)

3. [Post-execution processes](21_task_pre_and_post_execution_processes.md), if they are added to the task. The post-execution processes run after the execution of the tables ends. The post-execution processes are executed according to their execution order as defined in the TDM implementation. 




## Monitoring Task Execution

The TDM App displays the task execution process.  

Click for more information about the [task execution monitoring](26b_task_execution_monitor.md).



## Disabled Tasks

A Task that contains [disabled systems](11_environment_products_tab.md#disabling-the-environments-systems) on its source and/or target environments, cannot be executed. In such case, these tasks are displayed for view only in the [Task Management](14_task_management_window.md) window, but cannot be  executed. 



  [![Previous](/articles/images/Previous.png)](26_task_execution_window.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](28_task_execution_dashboard.md)

