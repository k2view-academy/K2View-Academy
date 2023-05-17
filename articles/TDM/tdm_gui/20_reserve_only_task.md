# TDM Reserve Task

A Reserve task contains only the **Reserve** task type and [reserves the selected entities](/articles/TDM/tdm_architecture/08_entity_reservation.md) in the task's environment.

A Reserve task contains the following tabs:

- [General](14a_task_general_tab.md)
- [Additional Execution Parameters](#additional-execution-parameters-tab)
- [Requested Entities](#requested-entities-tab)
- [Task Scheduling](22_task_execution_timing_tab.md)

When checking the **Set Task Variables** setting, a new [Task Variables](23_task_globals_tab.md) tab opens.

## Additional Execution Parameters Tab

The following execution parameters are set on **Reserve tasks**:

### Data Type

Check the **Entities** to reserve the requested entities in the target environment.

### Reservation Period

The reservation period settings are displayed **if the load task also reserves the loaded entities** on the target environment (the Reserve task type is checked together with the Load task type).

Note that when the Reservation Period is set to zero, the entities are reserved for an unlimited period.  

Only Admin and Environment owner users can reserve entities for an unlimited period. Users that are attached to the target environment as testers  must set a reservation period. The maximum number of days of a reservation period is set in the **tdm_general_parameters** TDB DB table in the **MAX_RESERVATION_DAYS_FOR_TESTER** parameter.

The start date of the reservation period is the task's execution time. The **reservation period** can be set in **minutes**, **hours**, **days** or **weeks**.

### Reservation Note

This setting has been added by TDM 7.5.2. The user can populate it with a free text. The reservation note is displayed in the [Reserved Entities window](13_reserved_entities_window.md) (Notes fields) and can be used for filtering the reserved entities display. The reservation note can be edited using the Reserved Entities window.

### Additional Execution Parameters

#### Set Task Variables 

Check to open the Task Variables tab and [set the variable value on a task level](23_task_globals_tab.md).

### Post Execution Processes

Select all, partial, or one [post execution process](04_tdm_gui_business_entity_window.md#post-execution-processes-tab) of the selected BE.



## Requested Entities Tab

This tab defines the subset of entities for the task:

![requested entities](images/reserve_task_requested_entities.png)

The following selection methods are available on load tasks: 

### Entity list 

This is the **default option**. Populate the list of entities for the task, separating them with a comma.  Note that a warning is given if the entity list has entities that are reserved for another user.

### Custom Logic

select a [Broadway flow](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#step-7---optional---build-broadway-flows-for-the-custom-logic--selection-method) in order to both get the entity list for the task and to set the maximum number of entities for the task.

Notes:

-  TDM 8.0 added integration of [Broadway editors](/articles/TDM/tdm_implementation/15_tdm_integrating_the_tdm_portal_with_broadway_editors.md) into the TDM portal when populating the Custom logic parameters in the task’s tabs.
-  The **Filter out Reserved Entities** checkbox indicates if entities that are reserved for other users must be filtered out from the task's entity list. If checked, these entities are filtered out from the task's entity list. 

### Parameters

TDM 8.0 added the [Parameters selection method](17_load_task_regular_mode.md#parameters)  to the Reserve tasks. Note that this feature depends on the [TDM configuration](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md#tdm-portal-general-parameters). By default, the Parameters selection method is hidden for Reserve tasks. 




 [![Previous](/articles/images/Previous.png)](19_delete_only_task.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](25_task_tdmdb_tables.md)
