# TDM Task Components

## Task Actions

The following task actions are supported by TDM:

- **Extract** - extracts the selected entities and/or tables from the selected source environment. The data can be saved in the Test Data Store (Fabric) for a later use.
- **Generate** - generates synthetic entities. Either one of the entities' generation methods can be applied: Rule-based data generation or AI-based data generation.
- **Load** - provisions the selected entities and/or tables to the selected target environment. The target environment can be **AI training** to run AI-based training on a subset of entities.
- **Delete** - deletes the selected entities from the target environment.
- **Reserve** - reserves the selected entities in the target environment.

## Task Architectural Widget

TDM redesigns the task creation flow in order to simplify the task creation or edit and make it more intuitive. A **graphic architectural widget** guides the user on the task's related components:

- [Source](14a_task_source_component.md) – defines the data source of task's entities and/or tables. The data source can be either a source environment or a synthetic data generation.

- [Subset](15_task_subset_component.md) – defines the entities' subset or the tables' filter. For example, select 50 customers that live in NY and have a Gold status.

- [Test Data Store](16_task_test_data_store_component.md) – this is Fabric that can be used as a staging DB to save the task's entities and/or tables.

- [Target](17_task_target_component.md) – defines the target environment for the task. It can be either a testing environment or AI training to create a training model for generating AI-based synthetic entities.

**The task actions are set by the components that are selected and set by the user**. The **Test Data Store must be set for all tasks**.

Examples:

- The user wishes to extract entities from Production and save them in the Test Data Store (Fabric) for a later use. The user needs to select the Source component:

  ![extract task widget](images/task_widget_extract_only.png)

- The user wishes to extract entities from Production and load them into the UAT environment. The user needs to select both the Source and the Target components:

  ![load task widget](images/task_widget_load.png)

The user can click on each one of the components to open its form and update its settings. The task also has **Save task**, **Save & execute** and **Advanced settings** icons.

## Task Components

### Task Name

When creating a new task or opening a task, the **Task name** form opens. Populating the Task name and Task description fields is optional. If no Task name is set, a default Task name is generated with some basic information about the task.

You can exit each form, including the Task name form, by clicking any task component. You can re-open the Task name form by clicking the Task name in the upper-left corner of the window.

![task name](images/task_name_form.png)

### Task Architectural Widget

Each task must include the [Test Data Store](16_task_test_data_store_component.md) (Fabric) and at least one environment: [Source](14a_task_source_component.md) or [Target](17_task_target_component.md). A task can include Source only, Target only, or both environments.

Click on each one of the components to open and update its form.

### Advanced Settings

The Advanced settings include **optional** task settings:

- [Pre and post execution processes](21_task_pre_and_post_execution_processes.md)
- [Task variables](23_task_globals_tab.md)
- [Scheduler](22_task_execution_timing_tab.md)

### Save or Save & Execute

The **Save task** icon saves the task in the TDM DB.

The **Save & execute** icon saves the task in the TDM DB and executes the task.



 [![Previous](/articles/images/Previous.png)](14_task_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](14a_task_source_component.md)
