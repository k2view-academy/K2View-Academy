# **Fabric Jobs Flow** 

Similar to most Fabric entities, a Job's flow has Define, Configure and Deploy stages:

<figure><table>
<thead>
<tr><th>Stage</th><th>Description</th></tr></thead>
<tbody><tr><td>Define</td><td>A function, flow or process defined as the method to be invoked by the Job when scheduled. For example: <br>User Job: a Java function defined and attached to a specific LU type under the LU Utilities folder. <br>Broadway Job: a Broadway flow as defined in the Broadway GUI.<br>Process Job: a script stored on the Fabric server.</td></tr><tr><td>Configure</td><td>A new job entry must be added to the Jobs table under the LU Type tree and have the following parameters:<br>- Schedule Job type<br>- MethodAffinity flag that specifies the Fabric node to be allocated to this Job (if any).</td></tr><tr><td>Deploy</td><td>The LU, its associated Job functions and Jobs table are processed by the the Fabric Node onto which the deployment was performed. <br>The Job is triggered either automatically or manually depending on the parameter specified during the configuration phase.</td></tr></tbody>
</table></figure>




# **Fabric Jobs Status** 

All Fabric jobs will go through different stages, each of which indicates a specific step in the job's handling process:

**Scheduled** - This stage is used for repetitive jobs with the following types of schedules:

    ****Time interval**** – when a recurring job is scheduled to run at every given time. If the job is scheduled to run every 60 minutes, it will do so but coubnt 60 minutes from the time the previous occurrence of the job finished.

    ****Timestamp**** – when a specific job is scheduled to run only once at a given date and time

    ****Cron**** – when a series of the same job needs to be run according to the crontab scheduling format. Note that the actual execution of the job will be handled by Fabric's own scheduler.

**Waiting** - When a job is in this particular stage, it is waiting to be executed by the appropriate fabric node to which it will be allocated. 


For the previous stages, it is important to note that if a (same) job is redeployed, and already registered by Fabric, i.e. the row already exists in the Jobs table, then it will not be overridden.
This means that if a recurring job is redeployed after it has been run one or more times, the schedule for the next run will not be affected or reset.

**In Process** - This is the actual stage when the execution happens.

**Processed** - Indicates that a job has been executed successfully.

**Stopping** - Indicates that either a user or a system action has triggered a stopjob command and that the job is being terminated.

**Terminated** - Describes the end of the stopping process. The job in process is no longer being executed or requested to stop.

**Failed** - Describes that the job failed to run and that the process did not run.

**Restart** - Indicates that a job has been actively restarted.


The image below illustrates the different stages of a job's lifecycle and the different types of actions that can get a specific job to transit from one particular state to another:
The blue arrows show the natural path of a job during it's life cycle in Automatic Execution mode.
The other dotted or plain arrows show the transition between stages in manual execution mode, when applying one of the following commands:
***startjob***
***stopjob***
***restartjob***
***resumejob***


<img src="/articles/20_jobs_and_batch_services/images/01_jobs_and_batch_services_status_flow.PNG">



# **Fabric Nodes and Jobs Processes** 

**Node Affinity**
A particular job can be assigned to a specific Fabric node. This is done by specifying the node's parameters in the jobs definition table in Fabric studio or from the startjob command in Fabric Runtime environment. Once deployed, the job will be only allocated to the specified node.

**Node Competition**

When running multiple Fabric Nodes, jobs can be allocated to different nodes. 
Once a new job is deployed, each node will compete to execute the new job while Cassandra's optimistic locking process will ensure a consensus is reached between all the nodes and that each job gets to be executed by the best candidate node at any given time, and that it will be executed once only.
Within each Fabric node, a running thread checks whether a new job has been deployed and in case the Cassandra's LiteWeight Transactions process will allocate the job to this specific node, the thread will handle the processing and lifecycle of the job.

In the illustation below, we show 2 different examples whereby JOB1 is allocated to Node 2, as it has been configured 

<img src="/articles/20_jobs_and_batch_services/images/02_jobs_and_batch_services_Nodes_Allocation.PNG">


**Job Process**

Fabric runtime server comes with a few java classes dedicated to handle the jobs' lifecycle. Among these classes the 3 following services are particularily important:
- JobsExecutor to manage the job's execution along the different phases of its lifecycle, manage retrials when necessary and update job status 
- JobsScheduler to manage the ownership of a specific job sitting on the queue
- JobsReconcile to handle the re-allocation of jobs to the propper Fabric node if a node went offline.


**Job Execution Resiliency**

Fabric ensures that jobs executions gets multiple recovery opportunities in case the node responsible for its execution falls through. 
A heartbit variable can be configured for each node, allowing for each fabric node status to be monitored and for jobs to be reallocated to a different node. 
Yet if the node restarts, and if there is no sufficient time until the scheduled job's execution, the rebooting node will be given precedence over all other nodes for the execution of the job's instance.


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/01_fabric%20jobs_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/03_create_a_job.md)
