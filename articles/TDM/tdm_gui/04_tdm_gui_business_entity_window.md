# Business Entity Windows 

A [Business Entity](/articles/TDM/tdm_overview/03_business_entity_overview.md) (BE) represents the main entity of the data to be provisioned by TDM. A BE can have multiple [LUs](/articles/03_logical_units/01_LU_overview.md) with either a flat or a hierarchical structure. For example, a Customer BE can have Customer Care, Billing, Ordering and Usage LUs. Each LU can be attached to multiple BEs.

## Business Entities List Window  

The **Business Entities** window displays a list of all BEs defined in the TDM.  Only **Admin users** can create, edit or delete a BE. Other users can open BEs for viewing purposes only.

-   To create a new BE, click the **New Business Entity** icon. 
-   To open a selected BE, click the **Name** value of the BE.
-   To delete a BE, click the<img src="images/delete_icon.png" alt="be_Example" style="zoom:80%;" /> icon, located at the upper-right corner of the Business Entity window.

## Business Entity Window    

The Business Entity window displays information about a selected BE. It has 3 main sections:

- General Information, which consists of the BE Name and Description fields.
- Logical Units tab.
- Pre and Post Execution Processes tabs.

The following is an example of a Customer Business Entity window:

![be_Example](images/tdm_gui_customer_be.png)



### General Information Section 

The General Information section consists of the BE **Name** and **Description**. The Name setting is mandatory. Note that each active BE should have a specified Name. An error is displayed when an attempt is made to create several BEs with the same name.

### Logical Units Tab 

To use a BE in a TDM task, it must have one or more LUs assigned to it.

#### How Do I Add an LU to a BE? 

1. Click **Add Logical Unit** to display the **Add Logical Units** dialog box:

![be_Example](images/BE_add_lu_window.png)

2. Do either: 
   * Check **All Logical Units** to attach all LUs that are deployed to Fabric and that are not attached to the BE. The LUs are attached to the BE in a flat structure whereby the Parent LU is empty. When needed, the LUs can be edited to populate the Parent LU and the Data Center settings.
   * Check **Select** and select an LU from the **Logical Unit** drop-down list:

     - Click <img src="images/plus_icon.png" alt="be_plus" style="zoom:80%;" /> or <img src="images/delete_icon.png" alt="be_delete" style="zoom:80%;" /> to add or remove LUs to / from the BE. 
     - Populate the following optional settings for each selected LU:
       - **Logical Unit Description**.
       - **Parent Logical Unit** - set a parent LU to build a [hierarchy in the BE](/articles/TDM/tdm_overview/03_business_entity_overview.md). 
3. Click **Add Logical Units** to add the selected LUs to the BE.

Notes:
 - Both Parent and Child LUs must be attached to the same BE.
 -  An LU can have 0-1 parents.
 -  An LU can have 0-N children.
  - Several levels of parent-child hierarchies can be defined in a BE. Related examples are found in the [Business Entity Overview](/articles/TDM/tdm_overview/03_business_entity_overview.md).

#### Editing LU Settings

Click <img src="images/be_edit_icon.png" alt="be_edit" style="zoom:80%;" /> or <img src="images/be_delete_icon.png" alt="be_delete" style="zoom:80%;" /> to edit or delete the LU from the BE. Note that the LU is deleted from the BE in the TDM DB. 

Click for more information about [TDM DB tables that hold the BE and LU relationship](06_be_product_tdmdb_tables.md).



### Pre and Post Execution Processes Tabs

These tabs enable adding pre and post execution processes. The pre-execution processes run at the beginning of the task's execution , before all the related LUs have been executed. The post-execution processes run at the end of the task's execution, after all the related LUs have been executed.

Examples:

- Running a cleanup flow before executing the task's LUs. 
- Sending an email to the tester to notify that the execution of a task has ended. 

The pre and post execution processes are Broadway flows defined in Fabric by the TDM implementor. The relationship between a pre or post execution process and a BE is many-to-many. That is, a BE can have several pre/post execution processes, and a pre/post execution process can be attached to multiple BEs.  Note that a given flow can be attached as both - pre and post execution process - to a BE.

The [task execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md) executes the [BATCH command](/articles/20_jobs_and_batch_services/15_batch_broadway_commands.md) on each pre and post execution process attached to the task. The execution order is set according to the execution order defined in the BE.

The pre and post execution processes are optional: a BE can be defined without any post-execution processes.

Note that the post-execution processes must be populated in [PostAndPreExecutionProcess](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#postandpreexecutionprocess-) MTable object to be displayed by the BE window.

#### How Do I Add a Pre or Post Execution Process to a BE? 

1. Open the **Pre Execution Processes** or **Post Execution Processes** tabs and click  **Add Pre Execution Processes**  or **Add Post Execution Processes** to open a dialog box.  

2. Click the **Process Name** and select a flow from the drop-down list that holds all pre/post execution processes deployed to Fabric.
3. Populate the **Execution Order** field with a numeric value to set the execution order. Processes with Execution Order 1 run first, followed by processes with Execution Order 2, etc. Note that an execution order can be set for several processes that are executed simultaneously.
4. Populate the **Description** field (optional).
5. Click **ADD PRE/POST EXECUTION PROCESS** to add the process to the BE.
6. Repeat steps 1-5 to add additional pre/post execution processes, if needed.

  

Click [here](06_be_product_tdmdb_tables.md) for more information about the BE TDM DB tables and about the BE and the pre/post-execution processes relationship.


  [![Previous](/articles/images/Previous.png)](03_tdm_gui_data_centers_window.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_tdm_gui_product_window.md)

