# TDM Delete Task

A Delete task contains only the **Delete** task type and deletes (cleans) the selected entities from the target environment. The delete task also releases the cleaned entities if the entity is reserved.

Note that the deleted flows must be implemented in the Fabric implementation. 

Click for more information about the [delete implementation](/articles/TDM/tdm_implementation/08_tdm_implement_delete_of_entities.md).

A Delete task contains the following tabs:

- [General](14a_task_general_tab.md)
- [Additional Execution Parameters](#additional-execution-parameters-tab)
- [Requested Entities](#requested-entities-tab)
- [Task Scheduling](22_task_execution_timing_tab.md)

When checking the **Set Task Variables** setting, a new [Task Variables](23_task_globals_tab.md) tab opens.

## Additional Execution Parameters Tab

The following execution parameters are set on **Delete tasks**:

![additional exe params](images/delete_task_additional_exe_params.png)



### Data Type

Check the **Entities** to delete the requested entities from the target environment.

### Additional Execution Parameters

#### Set Task Variables 

Check to open the Task Variables tab and [set the variable value on a task level](23_task_globals_tab.md).

### Post Execution Processes

Select all, partial, or one [post execution process](04_tdm_gui_business_entity_window.md#post-execution-processes-tab) of the selected BE.



## Requested Entities Tab

This tab defines the subset of entities for the task:

![requested entities](images/delete_task_requested_entities.png)

The following selection methods are available on load tasks: 

### Entity list 

This is the **default option**. Populate the list of entities for the task, separating them with a comma.  Note that a warning is given if the entity list has entities that are reserved for another user.

### Custom Logic

Select a Broadway flow to get the entity list for the task and set the maximum number of entities for the task.

TDM supports the creation of external input parameters on a Custom Logic Flow. In this case, the TDM Portal displays the input parameters in the task window, enabling the user to send values for these parameters. 

The **Filter out Reserved Entities** checkbox has been added in TDM 7.6 and it indicates if entities that are reserved for other users must be filtered out from the task's entity list. If checked, these entities are filtered out from the task's entity list.

See example:

![custom logic](images/delete_task_requested_entities_custom_logic_2.png)



Note:

- It is possible to set an array value in a Custom Logic's parameter. The values are populated as a String with the delimiter, which is set in the Custom Logic Broadway flow. For example: 1,2,3 or NY,LA. 

 [![Previous](/articles/images/Previous.png)](18_load_task_data_versioning_mode.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](20_reserve_only_task.md)
