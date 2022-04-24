# **Fabric Jobs Flow** 

Similar to most Fabric entities, a Job's flow undergoes the following stages:

<figure><table>
<thead>

<tbody><tr><td>Define</td><td>A function, flow or process defined as the method to be invoked by the Job when scheduled.  
For example: 
<Li>User Job, a Java function defined and attached to a specific LU type under the LU Utilities folder.</Li> 
<Li>Broadway Job, a Broadway flow defined in the Broadway GUI.</Li>
<Li>Process Job, a script stored on the Fabric server.</Li>
</td></tr><tr><td>Configure</td>
<td>A new job entry must be added to the Jobs table under the LU Type tree and must have the following parameters:<br><Li>Schedule Job type.</Li>
<Li>A method field that specifies the function to be allocated to this Job.</Li>
<Li>An affinity field that specifies the Fabric node to be allocated to this Job</Li> 
</td></tr><tr><td>Deploy</td><td>The LU, its associated Job functions and Jobs table are processed by the deployed Fabric node. The Job is triggered either automatically or manually depending on the parameters specified during configuration.</td></tr></tbody>
</table></figure>
 

# **Fabric Jobs Status** 

All Fabric Jobs undergo different stages, where each stage indicates a specific step in the Job's handling process:

## **Statuses**

<table>
<tbody>
<tr>
<td>
<p><strong>Stage</strong></p>
</td>
<td>
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td>
<p><strong>Scheduled</strong></p>
</td>
<td>
<p>This stage is for repetitive Jobs and has the following schedules:</p>
<ul>
<li>Time interval, the recurring Job is scheduled to run at every given time. If the Job is scheduled to run every 60 minutes, it counts 60 minutes from the time when the previous occurrence of this Job ended.</li>
<li>Timestamp, the date and time a specific Job is scheduled to run.</li>
<li>Cron, a series of the same Job must be run according to the&nbsp;<strong>crontab</strong>&nbsp;scheduling format. Note that the actual execution of the Job is handled by Fabric Scheduler.</li>
</ul>
</td>
</tr>
<tr>
<td>
<p><strong>Waiting</strong></p>
</td>
<td>
<p>A Job is waiting to be executed by the Fabric node to which it is allocated.</p>
<p>Note that in previous stages, a Job is overridden if it has been redeployed and registered by Fabric and its row already exists in the Jobs table. That is, if a recurring Job is redeployed after it has been run one or more times, the schedule for the next run will not be affected or reset.</p>
</td>
</tr>
<tr>
<td>
<p><strong>In Process</strong></p>
</td>
<td>
<p>The actual stage when the execution happens.</p>
</td>
</tr>
<tr>
<td>
<p><strong>Processed</strong></p>
</td>
<td>
<p>A Job has been executed successfully.</p>
</td>
</tr>
<tr>
<td>
<p><strong>Stopping</strong></p>
</td>
<td>
<p>Either a user or a system action has triggered a&nbsp;<strong>stopjob</strong>&nbsp;command and the Job is being terminated.</p>
</td>
</tr>
<tr>
<td>
<p><strong>Terminated</strong></p>
</td>
<td>
<p>The end of the&nbsp;<strong>Stopping</strong>&nbsp;process. The Job in process is no longer being executed or has been requested to stop.</p>
</td>
</tr>
<tr>
<td>
<p><strong>Failed</strong></p>
</td>
<td>
<p>The Job failed to run and the process did not run.</p>
</td>
</tr>
<tr>
<td>
<p><strong>Restart</strong></p>
</td>
<td>
<p>A Job has been actively restarted.</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>



## **Job's Lifecycle**
The  following image illustrates the different stages of a Job's lifecycle and the different types of actions that transit a specific Job from one specific state to another.

-  The blue arrows show the natural path of a Job during its lifecycle in Automatic Execution mode. 
-  The dotted or plain arrows show the transition between stages in Manual Execution mode when manually applying one of the following commands:
   -  **startjob** - plain line 
   -  **stopjob** - plain line
   -  **restartjob** - dotted line
   -  **resumejob** - dotted line

<img src="/articles/20_jobs_and_batch_services/images/01_jobs_and_batch_services_status_flow.PNG">


# **Fabric Nodes and Jobs Processes** 

## **Nodes Affinity**

A specific Job can be assigned to a specific Fabric node by specifying the node's parameters in the Jobs [affinity](/articles/20_jobs_and_batch_services/10_jobs_and_batches_affinity.md) definition table in the Fabric Studio or from the **startjob** command in the Fabric Runtime environment. Once deployed, the Job is only allocated to the nodes specified in the affinity flag.

## **Nodes Competition**

### **Node Allocation to a Job**

When running multiple Fabric nodes, Jobs can be allocated to different nodes. Once a new Job is automatically or manually started, each node within the Fabric Cluster will compete to execute it. The Cassandra Optimistic Locking process ensures an agreement is reached between all nodes and that each Job is executed only once by the best candidate node at any given time. Each Fabric node checks in the k2_jobs table whether a new Job has been deployed and whether it has already been allocated to a node by the Cassandra LiteWeight Transactions process or assigned to a specific node by way of [affinity](/articles/20_jobs_and_batch_services/10_jobs_and_batches_affinity.md). 


The following image illustrates two different examples:

- JOB 1 is allocated to Node 1 because the job's affinity was specifically set to Node 1 in the Fabric Studio from the [Job's Parameters Configuration table](/articles/20_jobs_and_batch_services/03_create_a_new_user_job.md#step-6), or from the Command Line using one of the following commands: [startjob](/articles/20_jobs_and_batch_services/07_jobs_commands.md#startjob-jobtype-namename-uiduid-affinityaffinity-argsargs-exec_intervalexecinterval) and [updatejob](/articles/20_jobs_and_batch_services/07_jobs_commands.md#updatejob-jobtype-namename-uiduid-affinityaffinity-argsargs-exec_intervalexecinterval-reset_end_timetruefalse).

- JOB 2 (to which no affinity is set) is allocated to Node 2, as the ANY option has been added to all nodes *node.id* files from Node 2 to Node (N-1) included.
When ANY is set to 0 on the node.id, the node is not part of any job execution.
When ANY is set to a value greater than zero, the number shows the maximum number of threads to be allocated to this job.



<img src="/articles/20_jobs_and_batch_services/images/02_jobs_and_batch_services_Nodes_Allocation2.png">

### **Jobs Optimistic Locking Configuration**
A configurable parameter **OPTIMISTIC_LOCKING** in the node's config.ini file can be set to support lightweight transactions between nodes to decide on a Job's allocation.

The supported values are as follow:
- NONE - this is the default value. The latest transaction overrides the instance ID.
- QUORUM - the first transaction locks the instance ID. Latest transaction will fail until the transaction is committed (the commit requires a quorum).
- LOCAL QUORUM - the first transaction locks the instance ID. Latest transaction will fail until the transaction is committed (the commit requires a local quorum on the DC).


## **Job's Logic**

Each instance of Fabric runtime server (Fabric node) comprises the dedicated Java logic and classes responsible to handle any Job's lifecycle. Among these classes, the following are particularily significant in the process:
- JobsExecutor - managing the Job's execution and transitions between the different stages, including a multiple retry mechanism when necessary. 
- JobsScheduler - managing the node ownership of a specific Job waiting in the queue.
- JobsReconcile - handling the re-allocation of Jobs to a new Fabric node, if the one dedicated through affinity or allocated by Cassandra, is not reachable.


## **Job Execution Resiliency**

Fabric ensures that Job executions have multiple recovery opportunities if the node responsible for its execution fails. 
A [heartbeat](/articles/20_jobs_and_batch_services/09_jobs_configuration.md#heartbeat) variable can be configured for each node so that the status of each Fabric node can be monitored and their dedicated Jobs reallocated to different nodes if necessary. 
If a node restarts, and insufficient time is left before a scheduled Job's execution, the rebooting node has precedence over all other nodes for the execution of this particular Job.


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/01_fabric_jobs_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/03_create_a_new_user_job.md)
