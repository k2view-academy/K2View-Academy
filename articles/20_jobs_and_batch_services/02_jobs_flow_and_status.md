# **Fabric Jobs Flow** 

Similar to most Fabric entities, a Job's flow undegoes the following stages:

<figure><table>
<thead>

<tbody><tr><td>Define</td><td>A function, flow or process defined as the method to be invoked by the Job when scheduled.  
For example: 
<Li>User Job, a Java function defined and attached to a specific LU type under the LU Utilities folder.</Li> 
<Li>Broadway Job, a Broadway flow defined in the Broadway GUI.</Li>
<Li>Process Job, a script stored on the Fabric server.</Li>
</td></tr><tr><td>Configure</td>
<td>A new job entry must be added to the Jobs table under the LU Type tree and must have the following parameters:<br><Li>Schedule Job type.</Li>
<Li>MethodAffinity field that specifies the Fabric node to be allocated to this Job (if any).</Li>
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
<li>Time interval, the recurring Job is scheduled to run at every given time. If the Job is scheduled to run every 60 minutes, it counts 60 minutes from the time the previous occurrence of this Job ended.</li>
<li>Timestamp, the date and time a specific Job is scheduled to run.</li>
<li>Cron, a series of the same Job must be run according to the&nbsp;<strong>crontab</strong>&nbsp;scheduling format. Note that the actual execution of the Job is handled by the Fabric Scheduler.</li>
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
-  The dotted or plain arrows show the transition between stages in Manual Execution mode when applying one of the following commands:
   -  **startjob** 
   -  **stopjob**
   -  **restartjob**
   -  **resumejob**

<img src="/articles/20_jobs_and_batch_services/images/01_jobs_and_batch_services_status_flow.PNG">


# **Fabric Nodes and Jobs Processes** 

**Nodes Affinity**

A specific Job can be assigned to a specific Fabric node by specifying the node's parameters in the Jobs definition table in the Fabric Studio or from the **startjob** command in the Fabric Runtime environment. Once deployed, the Job is only allocated to the specified node.

**Nodes Competition**

When running multiple Fabric nodes, Jobs can be allocated to different nodes. Once a new Job is deployed, each node competes to execute it. The Cassandra Optimistic Locking process ensures an agreement is reached between all nodes and that each Job is executed only once by the best candidate node at any given time. A running thread in each Fabric node checks whether a new Job has been deployed and if the Cassandra LiteWeight Transactions process has allocated the Job to it. The thread handles the processing and lifecycle of the Job.

The following image displays two examples whereby JOB 1 is allocated to Node 2 according to its configurations: 

<img src="/articles/20_jobs_and_batch_services/images/02_jobs_and_batch_services_Nodes_Allocation2.PNG">



**Job Process**

The Fabric runtime server offers dedicated Java classes that handle the Jobs lifecycle. The following classes are particularily important:
- JobsExecutor, manages the Job's execution during the different phases of its lifecycle, retries when necessary and updates Job status. 
- JobsScheduler, manages the ownership of a specific Job waiting in the queue.
- JobsReconcile, handles the reallocation of Jobs to the correct Fabric node if a node is offline.


**Job Execution Resiliency**

Fabric ensures that Job executions have multiple recovery opportunities if the node responsible for its execution fails. 
A heartbeat variable can be configured for each node so that each Fabric node status can be monitored and Jobs can be reallocated to different nodes. 
If a node restarts, and if there is insufficient time before the scheduled Job's execution, the rebooting node has precedence over all other nodes for the execution of the Job's instance.


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/01_fabric%20jobs_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/03_create_a_job.md)
