# TDM Extract Task

An Extract task extracts the selected entities or Reference tables from the selected source environment and saves this data in Fabric for later use.

An Extract task contains the following tabs:

- [General](#general)
- [Request Entities](#requested-entities)
- [Request Parameters](#request-parameters)
- [Execution Timing](#execution-timing)

When checking the **Set Global Variables** setting, a new [Task Globals](23_task_globals_tab.md) tab opens.

When setting the **Reference** setting to **Reference Only** or **Both - reference and entities**, a [Reference tab](24_task_reference_tab.md) opens.

## General

This is the first tab in the TDM task and holds general information about the task. For example:

![general tab](images/extract_task_general_tab.png)

### Task Title

A task name. A mandatory setting. Note that only one active task can have a specific Task Title. An error is displayed when an attempt is made to create several tasks with the same task title.

### Task Type

Load or Extract. Set the task type to **Extract**.

### Entity Versioning

- Check to set the task mode to [Data Flux](15_data_flux_task.md). 
- Leave the Entity Versioning unchecked to create a regular mode task.

### Set Global Variables 

Check to [override Globals on a task level](23_task_globals_tab.md).

### Reference 

[Reference handling](24_task_reference_tab.md). Select a value from the dropdown list:

- **None**, default value. Do not include Reference tables in the task.
- **Reference Only**, create a task to extract Reference tables only into Fabric. Do not include entities in the task.
- **Both - reference and entities**, create a task to extract both entities and Reference tables into Fabric.

### Select All Entities 

- When checked, the list of entities is retrieved from the query defined in the [trnMigList](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnmigratelist) shared translation for the root LU of the task's BE, and the [Requested Entities](#requested-entities) tab is removed from the task.
- Only Admin and Environment owner users can check the **Select All Entities** settings. Other users can only define a list of entities in the [Requested Entities](#requested-entities) tab.
- This setting is disabled for **Reference Only** tasks.

### Environment Name

- Select a source environment from the dropdown list of active TDM environments with **Source** or **Both** [environment types](08_environment_window_general_information.md#environment-type). 
- Note that tester users can only select an environment they are attached to by a [TDM Environment role](10_environment_roles_tab.md).

### Retention Period

This is the retention period set on the extracted LUIs. When this period ends, the LUIs are **automatically deleted** from Fabric and are no longer available. 

Note that when the Retention Period is set to zero, no retention period is set on the extracted LUIs.

**A retention period must be set on a Data Flux extract task**, i.e. the retention period must be set to a value greater than zero when Entity Versioning is checked.

**The retention period is optional on a regular extract task.**

The start date of the retention period is the task's execution time. The **retention period** can be set in **minutes**, **hours**, **days**, **weeks**, or **years**.

**Example:** 

The **defaultPeriod** parameter is set to **5 days** and the **maxRetentionPeriod** parameter is set to **90 days**.

The Retention Period window displays the following options:
- When Entity Versioning is checked, the period is set by default to five days.
- When Entity Versioning is cleared, the period is set by default to zero, i.e. no retention period is set for the extracted data. 
- The maximum retention period can be set to 90 days or 12 weeks.  The Years option is not available since the maximum retention period is 90 days.

### Business Entity

The [BE](04_tdm_gui_business_entity_window.md) of the task. Select a BE from the dropdown list of the [BEs](05_tdm_gui_product_window.md#be-and-lu-product-relationship) included in the [environment’s products](11_environment_products_tab.md). 

### Logical Units

Select all, partial, or one LU of the selected BE. 

The following validations are set on the selected LUs:

The selected LUs must include at least one [root LU](/articles/TDM/tdm_overview/03_business_entity_overview.md#root-lu) of the selected BE. 

You cannot select an LU without its parent LU. 

**Example:**

- Customer BE has two level in its hierarchy: the  root LU is the Customer Data, the Billing LU is a child of the Customer Data, and the Collection LU is the child of the Billing LU. You cannot select a Collection LU without the Billing LU when creating a task on Customer BE.

Click for additional [examples of BE's hierarchies](/articles/TDM/tdm_overview/03_business_entity_overview.md).

### Post Execution Processes

Select all, partial, or one [post execution process](04_tdm_gui_business_entity_window.md#post-execution-processes-tab) of the selected BE.

## Requested Entities

This tab is displayed when the **Select All Entities** setting is unchecked. For example:

![requested entities](images/extract_task_requested_entities_tab.png)

Populate the list of entities to process separated by a comma. 

Notes:

- The number of entities populated by the tester user is [limited by their environment's role](10_environment_roles_tab.md#read-and-write-and-number-of-entities). 
- Populate the Entity ID as populated in the source environment. For example, populate the Entities List with 1, 2 to extract Customers 1 and 2. The TDM execution process  [concatenates the required components](/articles/TDM/tdm_implementation/01_tdm_set_instance_per_env_and_version.md) to Each Entity ID when building its LUI.

## Request Parameters

This tab is only displayed for a regular mode task, i.e. the **Entity Versioning** setting is unchecked.  This tab holds the following optional setting: **Request Up to Date Entity**. 

By default, the Requested Up to Date Entity is unchecked. You can check this setting to override the Sync mode on a task level and set the Sync mode of the task execution to [Force](/articles/14_sync_LU_instance/02_sync_modes.md). A tester can select this option only if their **Read** [TDM Environment role](10_environment_roles_tab.md#role-permissions) enables it.

## Execution Timing

This is the last tab in the Task window and is available for all task types and modes.

The following options are available for task execution:

- **Execution by Request**, the default option.

- **Scheduled execution**, set scheduling parameters to automatically execute the task based on the scheduling parameters. Note that a tester can select this option only if their TDM Environment role has a scheduling permission.

Click for more information about [TDM task scheduling](22_task_execution_timing_tab.md).



 [![Previous](/articles/images/Previous.png)](15_data_flux_task.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](17_load_task_regular_mode.md)

