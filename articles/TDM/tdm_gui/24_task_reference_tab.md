# Task Window - Reference Tab

The Reference tab is opened when checking the **Reference** Data Type setting in the **Additional Execution Parameters** tab.

Extract tasks extract **Reference tables** from a source environment and store them in the **Cassandra DB**.  Load tasks select data in Reference tables from the Cassandra DB and load them to the target environment.

Unlike LUIs, a Load task does not activate an extraction from the source environment. Therefore, Reference tables must be loaded into Cassandra by the Extract task before loading them to the target.

Click for more information about [Reference tables implementation](/articles/TDM/tdm_implementation/09_tdm_reference_implementation.md).

## Reference Tab - Extract Task

Display a list of all Reference tables defined in [trnRefList](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnreflist) Fabric translation object for the task's LUs. The Reference tab displays its **Source Interface Name** on each table and **LU Name**. You can **Select All**, **Unselect All**, or check a selected list of Reference tables.

The Extract task extracts the selected Reference tables and [saves them into the Cassandra DB](/articles/TDM/tdm_architecture/05_tdm_reference_processes.md#tdm-lu---tdmcopyreftablesfortdm-job). 



## Reference Tab - Load Task

### Regular Mode

Display a list of all Reference tables [extracted into Cassandra for the task's LU and source environment](/articles/TDM/tdm_architecture/05_tdm_reference_processes.md#tdm-lu---tdmcopyreftablesfortdm-job). Similar to the Extract Task window, a Reference tab displays the **Source Interface Name**, **Schema Name**, and the **LU Name** on each table. You can **Select All**, **Unselect All**, or check a selected list of Reference tables.

### Data Versioning Mode

Displays the list of available versions created on Reference tables and a source environment, and a load task's LU. The versions created during the last month are displayed. To select another period, edit the **From Date** and **To Date** settings.




 [![Previous](/articles/images/Previous.png)](23_task_globals_tab.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](25_task_tdmdb_tables.md)

