# TDM Load Task - Data Flux Mode

A [Data Flux Load task](20_load_task_dataflux_mode.md) gets the selected entities or Reference tables from the selected version created on the source environment and loads them to the selected target environment. A load task deletes the selected entities from the target environment and then loads them from the selected version.

To set a load task to a Data Flux task, check the **Entity Versioning** setting.

Click for more information about [Data Flux tasks](15_data_flux_task.md).

A Load Data Flux task contains the following tabs:

- [General](#general)
- [Requested Entities](21_load_task_requested_entities_dataflux_mode.md)
- [Execution Timing](22_task_execution_timing_tab.md): the last tab in the Task window and available for all task types and modes. The following options are available for the task execution:
  - **Execution by Request**: the default option.
  - **Scheduled execution**: set scheduling parameters to automatically execute the task based on the scheduling parameters. Note that a tester can select this option only if their TDM Environment role has a scheduling permission.

## General

The first tab in the TDM task that holds general information about the task. For example:

![general tab](images/load_general_tab_dataflux.png)

### Task Title

A task name. A mandatory setting. Note that only one active task can have a specific Task Title. An error is displayed when an attempt is made to create several tasks with the same task title.

### Task Type

Load or Extract. Set the task type to **Load**.

### Entity Versioning

Check to set the task mode to [Data Flux](15_data_flux_task.md). Testers can check this setting and create a Data Flux load task only if their [TDM Environment role](10_environment_roles_tab.md#role-permissions) has permissions.
  

### Set Global Variables 

Check to [override Globals on a task level](23_task_globals_tab.md).

When checking the **Set Global Variables** setting, a new [Task Globals tab](23_task_globals_tab.md) opens.

### Reference 

[Reference handling](24_task_reference_tab.md). Select a value from the dropdown list:

- **None**: default value. Do not include Reference tables in the task.
- **Reference Only**: create a task to extract Reference tables only into Fabric. Do not include entities in the task.
- **Both - reference and entities**: create a task to extract both entities and Reference tables into Fabric.

When setting the **Reference** setting to **Reference Only** or **Both - reference and entities**, a new [Reference tab](24_task_reference_tab.md) opens.

### Select All Entities

- When checked, all entities of the selected version are reloaded to the target environment.
- Only Admin and Environment owner users can check the **Select All Entities** settings. Other users can only define a list of entities in the [Requested Entities](21_load_task_requested_entities_dataflux_mode.md) tab.
- This setting is disabled for **Reference Only** tasks.

### Environment Names

- Select a **source environment** from the dropdown list of active TDM environments with a **Source** or **Both** [environment type](08_environment_window_general_information.md#environment-type). 
- Select a **target environment** from the dropdown list of active **Source** or **Both** TDM environments with a **Target** or **Both** [environment type](08_environment_window_general_information.md#environment-type). 


Notes:
  - Tester users can only select a source environment they are attached to by a Read [TDM Environment role](10_environment_roles_tab.md) and target environment they are attached to by a Write [TDM Environment role](10_environment_roles_tab.md). 
  - A tester will usually select the testing environment as both - source and target environments - when creating a Data Flux load task to reload the previously created version of the testing data on the environment.

### Business Entity

The [BE](04_tdm_gui_business_entity_window.md) of the task. Select a BE from the dropdown list of the [BEs](05_tdm_gui_product_window.md#be-and-lu-product-relationship) included in the [environment’s products](11_environment_products_tab.md). 

### Logical Units

Select all, partial, or one LU of the selected BE. 

The following validations are set on the selected LUs:

- The selected LUs must include at least one [root LU](/articles/TDM/tdm_overview/03_business_entity_overview.md#root-lu) of the selected BE. 

- You cannot select an LU without its parent LU. 

**Example:**

Customer BE has two levels in its hierarchy: the  root LU is the Customer LU, the Billing LU is a child of the Customer LU, and the Collection LU is the child of the Billing LU. You cannot select a Collection LU without the Billing LU when creating a task on Customer BE.

Click for additional [examples of BE hierarchies](/articles/TDM/tdm_overview/03_business_entity_overview.md).

### Post Execution Processes

Select all, partial, or one [post execution process](04_tdm_gui_business_entity_window.md#post-execution-processes-tab) of the selected BE.



 [![Previous](/articles/images/Previous.png)](19_load_task_request_parameters_regular_mode.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](21_load_task_requested_entities_dataflux_mode.md)

