# **Fabric Batch Processes Flow** 
The execution of a new batch process automatically triggers a new entry in Cassandra, under k2viewsystem keyspace in the batch table. The batch process will be granted the following status: **WAITING_FOR_JOB**. This is because, in parallel, the execution of a new batch process automatically triggers a new job entry that is recorded in the k2viewsystem.jobs table with the following parameters:
- Name set to the name of the batch process.
- Type set to "BATCH PROCESS".

From there, any available node, or any node whose affinity has been specified in the batch command, will handle the execution of the Job and of the subcommand as specified in the batch command.

Once the corresponding JOB kicks off, and is set to **IN_PROCESS** stage, the batch process itself will move through the following stages:
1. NEW
2. GENERATE_IID
3. IN_PROGRESS
4. FAILED/CANCELLED/DONE
 

<img src="/articles/20_jobs_and_batch_services/images/13_jobs_and_batch_services_batch_process.PNG">



# **Scheduling batch processes**

It is not per-se possible possible to schedule a batch process to be executed at a given time, or recurrently. In order to achieve this, a scheduled process job will have to be created, and called with a script containing the batch command to be repeatidly invoked.
In fact, this consists in creating a Job that calls a Batch process which in turn will create multiple or scheduled one-time jobs with the execution parameters parsed in the batch command.

<img src="/articles/20_jobs_and_batch_services/images/14_jobs_and_batch_services_scheduled_batch_process.PNG">
 

# **Batch process table in Cassandra**
