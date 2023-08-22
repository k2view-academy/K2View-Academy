# Task Window - Reference Tab

The Reference tab opens when checking the **Reference** Data Type setting in the **Additional Execution Parameters** tab.

Extract tasks extract **Reference tables** from a source environment and store them in Fabric. Load tasks sync the Reference tables into Fabric if needed, and load them to the target environment.

Click for more information about [Reference tables implementation](/articles/TDM/tdm_implementation/09_tdm_reference_implementation.md).

## Reference Tab - Extract Task

Display a list of all Reference tables defined in [RefList](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#reflist) object for the task's LUs. The Reference tab displays its **Source Interface Name** on each table and **LU Name**. You can either **Select All**, **Unselect All** or check a selected list of Reference tables.

The Extract task extracts the selected Reference tables and [saves them into Fabric](/articles/TDM/tdm_architecture/05_tdm_reference_processes.md). 



## Reference Tab - Load Task

### Regular Mode

Similar to the Extract Task window, a Reference tab displays the **Source Interface Name**, **Schema Name**, and the **LU Name** on each table. You can **Select All**, **Unselect All**, or check a selected list of Reference tables.

### Data Versioning Mode

Data versioning mode displays the following:
- a list of available versions created on Reference tables
- a source environment
- a load task's LU
  
The display is of versions created during the last month. To select another period, edit the **From Date** and **To Date** settings.





 [![Previous](/articles/images/Previous.png)](23_task_globals_tab.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](25_task_tdmdb_tables.md)

