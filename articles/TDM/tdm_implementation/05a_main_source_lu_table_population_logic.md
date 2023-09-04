# Populating the Main Source LU Table - Logic

The Broadway flow of the main source LU table is generated based on the **populationRootTable.pop.flow** template. It deletes and re-populates the main source LU table under the following conditions:

- Running an [Extract task](/articles/TDM/tdm_gui/16_extract_task.md) ,  [regular Load task](/articles/TDM/tdm_gui/17_load_task_regular_mode.md) (the Data Versioning checkbox is cleared), or [Generate task].

- The **Set Sync Policy** task's setting is not set to **Do not Sync From Source Data** in order to prevent synchronizing the entities from the source system. 

  Click to view the [Override Sync Mode Summary Table](/articles/TDM/tdm_architecture/04_task_execution_overridden_parameters.md#overriding-the-sync-mode-on-the-task-execution).

The source LU tables are not populated by the LUI sync in the following cases:

- The Sync Policy is set to **Do Not Sync From Source Data** by the user.
- A [delete only task](/articles/TDM/tdm_gui/19_delete_only_task.md).
- A  [reserve only task](/articles/TDM/tdm_gui/20_reserve_only_task.md).
- A [Data Versioning load task](/articles/TDM/tdm_gui/15_data_flux_task.md): the selected data version is copied from Fabric. 
- Load synthetic entities tasks (the source environment is Synthetic). 

The Broadway flow also validates whether the entity exists in the source table. If the entity is not found in the main source tables, an Exception is thrown and the entity is rejected.

The below image is an example of a Broadway flow that populates a main source LU table:



![root example](images/pop_root_lu_table_flow_example.png)

