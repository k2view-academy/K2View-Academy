# Jobs Configuration

Jobs Execution Environments can be configured at both node and cluster levels.


## Nodes Configuration
Priority for a given job on any given logical and physical node can be defined within the node configuration file.
For example: it is possible to define that node X will handle a maximum of 10 threads in parallel by setting the #K2JOBS_POOL_SIZE variable to 10 in the 

***Config.ini***
In this file can be set jobs-related configuration variable. It is located in the in the k2view/config/config.ini file under the section *JOBS*.
K2JOBS_POOL_SIZE=10: This value will define the size of the thread pool for fabric jobs processing. 
K2JOB_ARCHIVING_TIME_HOUR=720: This value will define the time to delete the job row from k2_jobs table. Default is 720 hours(30 days).


***Node.ini***
In this file can be set node and cluster-related configuration variables. It is located in the in the k2view/config/node.ini file.
In node.id file in k2view/config it is possible to define a set a number of logical names or Node Identifiers for the node in question; such node identifiers can be used for the job affinity mechanism. Note that there can be more than one logical names since a node can have more than one logical role.

The node UUID is unique and if left undefined, fabric will generate a random one while starting, the first time.

Example: ```uuid:7da16985-a8ac-4ea1-8e93-3118a225edd7```

The logical_id name helps define affinity between a node and candidate jobs. Therefore, in order to limit the number of fabric jobs running on a node (i.e. with the same affinity), each logical_id can be associated with the maximum number of jobs.

Example:
3 logical names were given for NODE 1, sharing the 10 threads allocated to Job processing on Node 1. 
node_b:1
node_c:3
node_d:6
You can then define 3 jobs with the following affinities:
- Job 1 with AFFINITY=Node1_LogicalId1
- Job 2 with AFFINITY=Node1_LogicalId2
- Job 3 with AFFINITY=Node1_LogicalId3

Note: If no empty slot is left on the pool, and a new job has been allocated to this pool, the job will remain in WAITING status. The user will be able to see this note only in trace but not in the log.


## Cluster Configuration

#### Cluster Identifier

It should contain only letters and numbers and is defined in the node.id file:
cluster_id: FabCluster1 

***Heartbeat***

A heartbeat value can be defined to setup the delay of fabric node heartbeat frequency. Default is set to 10 seconds.
```FABRIC_HEARTBEAT_INTERVAL_MS=5000``` - the hearbit has been set to 5 seconds.


***KeepAlive***

You can define the number of heartbeats that a fabric node can miss before it will be considered as unavailable, in which case all jobs that have been defined without a specific affinity to this node will be reallocated to another node. It is important to note, that any job whose affinity was set to this node will not run, and will have to be restarted manually.
```FABRIC_HEARTBEAT_MISS=12``` - if the node has been down for 60 seconds (12 missed heartbits, each of 5 seconds), it will be considered as unavailable and will not be considered as part of the pool during next job allocation.

The configuration of these parameters can be found in the k2view/config/config.ini file under the *Fabric Cluster* section
The values of the nodes' heartbeats are systematically stored in Cassandra, in the k2system.nodes table and can be queried from the cqlsh shell: 

```select * from k2system.nodes ```

Result:
<img src="/articles/20_jobs_and_batch_services/images/12_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/05_jobs_table_fields.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/07_jobs_examples.md)
