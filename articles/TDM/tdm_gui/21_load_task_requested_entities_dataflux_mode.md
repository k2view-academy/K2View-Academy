# TDM Load Task - Data Flux Mode - Requested Entities Tab

This tab displays a list of available versions that can be selected and reloaded to the target environment. By default, the TDM GUI displays a list of the versions created during the last month. To set a different period, edit the **From Date** and **To Date** settings.

The settings in the Requested Entities tab depend on the [Select All Entities](20_load_task_dataflux_mode.md#select-all-entities) settings in the General tab:

### Select All Entities is Checked

Displays all available versions created in the source environment for the task's LUs. 

Select a version and click **Next**.

### Select All Entities is Unchecked

Populates the list of entities separated by a comma in the **Entities List** setting.

The TDM displays all available versions created in the source environment for the task's LUs and the selected entities.

The available versions are selected based on the following criteria:

- All versions, created for all entities.
- All versions, created for the entities list, where the list of the version contains the selected entities of the load task.

Each update on the entities list may change the list of available versions for the task. 

Select a version and click **Next**. TDM revalidates the entities list and checks whether each entity has been successfully synchronized into Fabric by the extract task that created the selected version. 

#### Notes:

- Only one version can be selected for a load task.
- The **Version Name** is the **Task Title** of the extract task that created the version.
- The **Date Time** is the execution date of the task. An extract task can be executed multiple times. Each execution creates a separate version and has its own date and time.
- The  **Version Type** setting indicates whether the extract task has been created for all entities, or for a selected list of entities.
- The table holding the available versions can be sorted or filtered.
- To update the list of available versions, click **Refresh**.





 [![Previous](/articles/images/Previous.png)](20_load_task_dataflux_mode.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](22_task_execution_timing_tab.md)

