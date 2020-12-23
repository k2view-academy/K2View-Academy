# Recovery Point

### Overview

Broadway flows and jobs can serve complex systems with hundreds of processes for data movement across various systems, such as populating Logical Units with data from external sources, moving data from Fabric to external systems or consuming messages. If a flow crashes before it reaches the end, it should be possible to recover lost data to prevent re-running the flow from the beginning.  

Setting a **Recovery Point** in a Broadway flow allows to mark a Stage at which the flow data should be serialized and saved in the **broadway_recovery_point** Cassandra table under the [k2system keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md). Then if a flow fails to complete (for example, due to outage), the flow can be re-run and it will start from the last saved recovery point rather than from the beginning using the serialized data. Once the flow is completed, the recovery info is deleted from Cassandra.

Recovery point should be used to save the persistent data in the flow. For example, in a complex flow after completing one sub-process and before starting another one. Note that setting a recovery point on a DB result set, on [transactional Stage](23_transactions.md) or inside an [iteration](21_iterations.md) is not supported. 

### How Do I Set a Recovery Point?

Click ![image](images/99_19_dots.PNG)> **Recovery Point** in the [Stage context menu](18_broadway_flow_window.md#stage-context-menu) to display a thick divider line. Repeat the same in additional Stages if more than one Recovery point should be set in the same flow. 

**Example**

The below flow prepares the data, creates a table, selects data from a DB and then inserts the data into the created table.

Set the Recovery Point at **Create Table** Stage. If the flow crashes after this Stage, it can be re-run and then it will start from the **Query** Stage. 

![image](images/99_29_recovery_01.PNG)



### How Do I Run the Flow with Recovery Point?

The flow can be executed in one of the following ways:

* By running the flow using a [BROADWAY command](/articles/02_fabric_architecture/04_fabric_commands.md#fabric-broadway).
  
  * When running a Broadway command, it is mandatory to provide a recovery ID in order to enable the Broadway recovery mechanism. Then if the flow fails, re-run the same flow with the same recovery ID.
* By running a [Broadway job](/articles/20_jobs_and_batch_services/05_create_a_new_broadway_job.md) using the STARTJOB command. 
  
  * When running a Broadway job, there is no need to provide a recovery ID since the recovery mechanism is enabled automatically for the jobs.
* From the Fabric Studio simulating the flow crash by stopping the Fabric. Do the following:
  * Set a breakpoint in the flow at a Stage after the Recovery Point.
  
  * Click **Actions** > **Run with Recovery Point** in the [Main menu](18_broadway_flow_window.md#main-menu) toolbar.
  
  * When the flow reaches the breakpoint, click **Stop Local Fabric** using the [Fabric Studio debug panel](/articles/04_fabric_studio/01_UI_components_and_menus.md#fabric-studio-debug-panel).
  
  * After the Fabric stops, start the Fabric from the debug panel.
  
  * Close and reopen the flow and then run it again clicking **Actions** > **Run with Recovery Point** in the [Main menu](18_broadway_flow_window.md#main-menu) toolbar. 
  
    
  
    Note that you can use the **RecoveryInfo** Actor to receive the recovery information. The Actor should be placed in a flow after a Recovery Point.



[![Previous](/articles/images/Previous.png)](28_actor_editor.md)

