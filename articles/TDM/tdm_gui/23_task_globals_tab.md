# Task Variables Tab

K2view's TDM enables overriding [Global variables](/articles/08_globals/01_globals_overview.md) on both environment and task levels:

-  Setting variables on a task level only impacts the execution of a task. 
-  Setting variables on an environment impacts the execution of all tasks created on the [TDM environment](12_environment_globals_tab.md).

[Click to read more about overriding Fabric settings by a task's execution](/articles/TDM/tdm_architecture/04_task_execution_overridden_parameters.md).



The **Task variables** tab in the task's **Advanced settings** displays the list of variables set on a task level and enables setting variables on a task. 

Click **Set Task Variable** to set a variable value on the task. The following pop-up window opens:

![add global](images/task_add_global.png)

- **Variable Name** - select a value from the drop-down list of Global variables defined in the Fabric implementation.
- **Logical Unit** - can be populated with either 'ALL' to impact all the task's LUs or a specific LU name.
- **Variable Value** - the default value is displayed. You can set a different value on the variable.



