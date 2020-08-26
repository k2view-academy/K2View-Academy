# Nodes & Clusters Configuration

Job execution environments can be configured at both node and cluster levels.


## Nodes Configuration
Priority for a given job on any given logical or physical node can be defined in the node's configuration file.
For example: to define that node X handles a maximum of 10 threads in parallel by setting the #K2JOBS_POOL_SIZE variable to 10. 

### **Config.ini**

Job-related configuration variables can be set in this file that is saved in the in the **k2view/config/config.ini** file under the **JOBS** section.

- **K2JOBS_POOL_SIZE=10**, defines the size of the thread pool for processing Fabric Jobs. 

- **K2JOB_ARCHIVING_TIME_HOUR=720**, defines the time when to delete the Job row in the **k2_jobs table**. Default is 720 hours (30 days).

- **OPTIMISTIC_LOCKING**:
      
      If OPTIMISTIC_LOCKING is set to ‘NONE’ then transaction 2 (the latest transaction) overrides transaction 1.
      If OPTIMISTIC_LOCKING is set to ‘QUORUM’ then transaction 1 locks the instance till the transaction is committed and updates at least 2 nodes of each DC.
      If OPTIMISTIC_LOCKING is set to ‘LOCAL QUORUM’ => transaction 1 locks the instance till the transaction is committed and updates at least 2 nodes of DC1.modes.
   

### **Node.ini** 

Node and cluster-related configuration variables can be set in this file which is saved in the **k2view/config/node.ini** file.

A set number of logical names or node identifiers for the node can be defined in the **node.id** file in the **k2view/config**. Node identifiers can be used in the Job Affinity mechanism. 

Note that there can be more than one logical name since a node can have more than one logical role.
The node UUID is unique and if left undefined, Fabric generates a random node when starting up the first time.

Example
 ```uuid:7da16985-a8ac-4ea1-8e93-3118a225edd7```

The logical_id name helps define the affinity between a node and candidate Jobs. Therefore, to limit the number of Fabric Jobs running on a node (i.e. with the same affinity), each logical_id can be associated with the maximum number of Jobs.

Example

Three logical names have been given for NODE 1 that share the 10 threads allocated to Job processing on Node 1. 
- node_b:1
- node_c:3
- node_d:6


Define three jobs with the following affinities:
- Job 1 with AFFINITY=Node1_LogicalId1
- Job 2 with AFFINITY=Node1_LogicalId2
- Job 3 with AFFINITY=Node1_LogicalId3

Note that if no empty slot is left in the pool and a new Job has been allocated to it, the Job remains in WAITING status until a processing slot is available.


## Cluster Configuration

### Cluster Identifier

A Cluster Identifier must contain only letters and numbers and is defined in the **node.id** file:

cluster_id: FabCluster1 

#### **Heartbeat**

A heartbeat value can be defined to set the delay of the Fabric node's heartbeat frequency. Default is 10 seconds.

```FABRIC_HEARTBEAT_INTERVAL_MS=5000``` - the hearbit has been set to 5 seconds.


#### **KeepAlive**

The number of heartbeats that a Fabric node can miss before it is considered as unavailable can be defined so that all Jobs without a specific affinity to this node are allocated to another node. It is important to note that any Job whose affinity has been set to this node will not run, and must be restarted manually.

```FABRIC_HEARTBEAT_MISS=12```

- If the node has been down for 60 seconds (12 missed heartbeats of 5 seconds each), it is considered as unavailable and is not part of the pool during next Job allocation.

The configuration of these parameters can be found in the **k2view/config/config.ini** file under the **Fabric Cluster** section. 
The values of the nodes' heartbeats are systematically stored in Cassandra in the k2system.nodes table and can be queried from the cqlsh shell: 

```select * from k2system.nodes ```

Result 
<img src="/articles/20_jobs_and_batch_services/images/12_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/08_jobs_table_fields.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/10_jobs_and_batches_affinity.md)
