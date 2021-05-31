# Task Window - Globals Tab

The TDM enables overriding [Globals](/articles/08_globals/01_globals_overview.md) on both environment and task levels:

-  Setting Globals on a task level only impacts the execution of a task. 
-  Setting Globals on an environment impacts the execution of all tasks created on the environment.

[Click to read more about overriding Fabric settings by a task's execution](/articles/TDM/tdm_architecture/04_task_execution_overridden_parameters.md).

## How do I Override Globals on a Task?

Check **Set Global Variables** to open the **Task Globals** tab.

### Task Globals Tab

This tab displays the list of Globals set on a task level. 

![task globals](images/task_globals_tab.png)



Click **Add Global** to add a Global to a task. A popup window opens:

![add global](images/task_add_global.png)

- Populate **Global Name**, select a value from the dropdown list of Global variables defined in the Fabric implementation.
- Populate **Global Value** with the value of the Global variable. This value is set on the Global name for each task execution.



 [![Previous](/articles/images/Previous.png)](22_task_execution_timing_tab)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](24_task_reference_tab.md)

