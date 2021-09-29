# Jobs & Batch Processes Affinity


## Affinity Overview
Nodes Affinity refers to the ability of Fabric initiating node to send and allocate a job or a batch process to a specific node (the handling node) within a Fabric Cluster.
This is particularily handy when specific nodes are reserved for specific tasks or need to be dedicated for time-consuming or heavy processing executions.

Allocating specific node to handle particular types of jobs could have very big and positive impact on the overall cluster's performance and Quality of Service.
For example - large sync-ing processes would be executed by a specific node while CDC or Cross-Instances searches would be handled by a different one.


## Affinity Properties

### Physical Affinity:
It consists of a node's IP or a DC name

e.g.

```10.20.30.40```

```DC-Europe```



### Logical Affinity:
It can be seen as a role attached to a specific physical node and to which can be dedicated a number of threads, lower or equal to the number of threads allocated to the nodes.
Such a Logical ID can be giving to node by adding the requested id to the 'node.id' file located in the fabric_home/config directory.
Each node can have multiple logical names, and a logical node can be shared by multiple physical nodes.

#### Affinity Pool Size (<u>from 6.4.2 onwards</u>)

A *Recommended Pool Size* capability has been added to the affinity function to rebalance jobs and get the ability to dynamically split (in runtime) jobs executions between nodes. This flag must be defined in the node.id file:
2 new parameters can be defined:
- recommended number of jobs
- maximum number of jobs that can run concurrently on the same node.

e.g.

```logical_id:2 4```
```logical_id:2-4```

whereby:
- 2 is the recommended number of jobs that can run concurrently.
- 4 is the maximum number of jobs that can run concurrently.

The dedicated node will avoid taking jobs above the recommended number immediately in order to give the opportunity to other node with empty slots to claim that specific job. 

In such a case, if a node has already reached its recommended size, and if it is trying to claim a new job allocated to it, the node will need to wait for a number of seconds - specified by the *CLAIM_EXCEPTIONAL_INTERVAL_SEC* parameter - **before** it can claim **that specific** job.
 The parameter is configured in the **config.ini** file. Its default value is set to 60 seconds.
```CLAIM_EXCEPTIONAL_INTERVAL_SEC=60```

This mechanism gives the opportunity to different nodes with empty slots to claim this job.
In cases where a node claimed a job above its recomended pool size, the server will stop and release all the extra jobs that are running above the recommended pool size. Then, other servers will be allowed to take the jobs that have been stopped. 

For this purpose, a random number is generated to decide when it will be set to *restart* status and therefore give the opportunity to other nodes (with empty slots) to execute it. 

This random number will fluctuate between the 2 following paramaters: *MIN_GIVE_UP_EXCEPTIONAL_MINUTES* and *MAX_GIVE_UP_EXCEPTIONAL_MINUTES* - that can be changed in [config.ini file](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) under the *add param names* section.

        - *MIN_GIVE_UP_EXCEPTIONAL_MINUTES* value defines the minimum time for restarting jobs that taken after the recommended time reached, min value = 1
        MIN_GIVE_UP_EXCEPTIONAL_MINUTES = 1

        - *MAX_GIVE_UP_EXCEPTIONAL_MINUTES* value defines the maximum time for restarting jobs that taken after the recommended time reached, min value = 2, max = 14400
        MAX_GIVE_UP_EXCEPTIONAL_MINUTES = 1440
 


## Affinity Use Cases

Let's assume the following configuration, featuring a Fabric cluster comprised of 3 different physical nodes with the following IPs:

```Node 1: 10.0.0.10```

```Node 2: 10.0.0.20```

```Node 3: 10.0.0.30```


The project consists in running a Fabric Job to monitor a Kafka Messaging Queue, on which network QoS alarms are continuously published and streamed by a source external to the Fabric Cluster.

In turn, each message, which can consists of hundreds of entries, will be handled by the Fabric Cluster as follows:

- Update the corresponding CommonDB table located on each Fabric node,
- Save the data in Cassandra for backup purposes.

Assuming an incoming message rate of 3 messages per second and an average message size of 1MB, it becomes clear that a specific node (let's say Node 1) handling this job needs to be allocated, since on-going strong i/o capabilities for read/write operations in commonDB tables are essential to meet higher performance requirements.

Using Fabric Node synchronization capability, all 3 nodes will then be kept in-sync using the mechanism described in the [Fabric CommonDB](/articles/22_reference(commonDB)_tables/04_fabric_commonDB_sync.md) article.




[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/09_jobs_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/11_batch_process_overview.md)


