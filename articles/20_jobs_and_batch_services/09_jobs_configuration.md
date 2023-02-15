# Nodes Configuration in Clusters

Job execution environments can be configured at both node and cluster levels.


## Nodes Configuration
Priority for a given job on any given logical or physical node can be defined in the node's configuration file.
For example: to define that node X handles a maximum of 10 threads in parallel by setting the #K2JOBS_POOL_SIZE variable to 10. 

### **Config.ini**

Job-related configuration variables can be set in this file that is saved in the **k2view/config/config.ini** file under the **JOBS** section.

**K2JOBS_POOL_SIZE=25**, defines the size of the thread pool for processing Fabric Jobs. 

**K2JOB_ARCHIVING_TIME_HOUR=720**, defines the time when to delete the Job row in the **k2_jobs table**. Default is 720 hours (30 days).

**OPTIMISTIC_LOCKING**:

If set to ‘NONE’ then transaction 2 (the latest transaction) overrides transaction 1

If set to ‘QUORUM’ then transaction 1 locks the instance till the transaction is committed and updates at least 2 nodes of each DC.

If set to ‘LOCAL QUORUM’ => transaction 1 locks the instance till the transaction is committed and updates at least 2 nodes of DC1.modes.
   

### **Node.id** 

Node and cluster-related configuration variables can be set in this file which is saved in the **k2view/config/node.id** file.

A set number of logical names or node identifiers for the node can be defined in the **node.id** file in the **k2view/config** repository. Node identifiers can be used in the Job Affinity mechanism. 

Note that there can be more than one logical name since a node can have more than one logical role.
The node UUID is unique and if left undefined, Fabric generates a random node when starting up the first time.

Example:
 ```uuid:7da16985-a8ac-4ea1-8e93-3118a225edd7```
 
#### Affinity Allocation

The logical_id name helps define the affinity between a node and candidate Jobs. Therefore, to limit the number of Fabric Jobs running on a node (i.e. with the same affinity), each logical name can be associated with the maximum number of threads.

Example:

Three logical names have been given for NODE 1 that share the 10 threads allocated to Job processing on Node 1.

```
- Node_b:1
- Node_c:3
- Node_d:6
```

Defining three jobs with the following affinities:

```
- Job 1 with AFFINITY=Node_b
- Job 2 with AFFINITY=Node_c
- Job 3 with AFFINITY=Node_d
```

In this case, Job 3 (the job with affinity set to Node_d) will get up to 6 out of the 10 threads reserved on that node.


Note:
- if no empty slot is left in the pool and a new Job has been allocated to it, the Job remains in WAITING status until a processing slot is available.
- Logical nodes and physical nodes cannot be specified as shared affinity in the same Job. 
- Several nodes can share the same logical name.

#### *ANY* flag
The *ANY* option is - by default - attributed to all nodes and only applies to jobs that were defined without affinity. This means that any node can contribute and compete for the execution of the jobs that were defined without a specific affinity.

1. It is possible to exclude a node for by setting the *ANY* flag to 0 in the node.id file - in which case, the node will not execute jobs defined without affinity. 
2. If set to a value greater than zero, the chosen value will reflect the maximum number of threads that can be allocated to a job when executed by this node.
3. If the *ANY* parameter is not added to the node.id file, by default, the node will be contributing to all jobs that have been defined without affinity.

#### DC Identifier

```dc_name: DC1```

Dc Name - By default, Fabric uses the same DC name that Cassandra is using. Fabric reads the DC from Cassandra and set it up automatically. 
You should only override it in case you need to use your own specific DC name.

## Cluster Configuration

### Cluster Identifier

A Cluster Identifier must contain only letters and numbers and is defined in the **node.id** file:

```cluster_id: FabCluster1```

Cluster are used in the following cases:
- Run multiple Fabric nodes on the same Cassandra server 
- In Fabric Studio, run multiple projects (each one as a single node) on the same Cassandra server

### **Heartbeat**

A [heartbeat](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md#job-execution-resiliency) value can be defined to set the delay of the Fabric node's heartbeat frequency. Default is set to 10 seconds.

```FABRIC_HEARTBEAT_INTERVAL_MS=5000``` - in this case, the heartbit has been set to 5 seconds.


### **KeepAlive**

The number of heartbeats that a Fabric node can miss before it is considered as unavailable can be defined so that all Jobs without a specific affinity to this node are allocated to another node. It is important to note that any Job whose affinity has been set to this node will not run, and must be restarted manually.

```FABRIC_HEARTBEAT_MISS=12```

- If the node has been down for 60 seconds (12 missed heartbeats of 5 seconds each), it is considered as unavailable and is not part of the pool during next Job allocation.

The configuration of these parameters can be found in the **k2view/config/config.ini** file under the **Fabric Cluster** section.

The values of the nodes' heartbeats are systematically stored in Cassandra in the k2system.nodes table and can be queried from the cqlsh shell: 

Example:

```select * from k2system.nodes ```

Result:

```
 guid                                 | dc  | effective_ip | ips                          | local_quorum_heartbeat          | logical_ids | quorum_heartbeat
--------------------------------------+-----+--------------+------------------------------+---------------------------------+-------------+---------------------------------
                           10.21.2.99 | DC1 |   10.21.2.99 |  ['127.0.0.1', '10.21.2.99'] | 2020-08-26 13:24:45.316000+0000 |        null | 2020-08-26 13:24:45.328000+0000
                          10.21.2.101 | DC2 |  10.21.2.101 | ['10.21.2.101', '127.0.0.1'] | 2020-08-26 13:24:48.363000+0000 |        null | 2020-08-26 13:24:48.368000+0000
                          10.21.2.103 | DC2 |  10.21.2.103 | ['127.0.0.1', '10.21.2.103'] | 2020-08-26 13:24:43.878000+0000 |        null | 2020-08-26 13:24:43.882000+0000
 f3e9dc7f-7b98-4dad-96a3-9689851873d1 | DC2 |  10.21.2.102 | ['127.0.0.1', '10.21.2.102'] | 2020-08-26 13:24:45.500000+0000 |        null | 2020-08-26 13:24:45.501000+0000
                          10.21.2.100 | DC1 |  10.21.2.100 | ['10.21.2.100', '127.0.0.1'] | 2020-08-26 13:24:50.429000+0000 |        null | 2020-08-26 13:24:50.435000+0000

(5 rows)

```





[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/08_jobs_table_fields.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/10_jobs_and_batches_affinity.md)
