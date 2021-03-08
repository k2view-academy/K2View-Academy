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

A *Recommended Pool Size* capability has been added to the affinity function to rebalance jobs and get the ability to dynamically split (in runtime) jobs executions between nodes. This flag must be defined in the [node.id file](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#nodeid): 

2 new parameters can be defined:
- recommended number of nodes
- maximum number of nodes

e.g.

```logical_id:2 4``` or ```logical_id:2-4```

- whereby 2 is the recommended number and 4 the maximum number of nodes to be allocated to jobs.

In addition, the ```CLAIM_EXCEPTIONAL_INTERVAL_SEC``` is a new param that needs to be added to the [config.ini configuration file](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) (set by default to 60 sec).
If a node that runs jobs with a limited affinity 'x' and has already reached its recommended size, and is trying to claim a new job with the same affinity, the node will need to wait ```CLAIM_EXCEPTIONAL_INTERVAL_SEC``` seconds before it can claim this job. This is happening in order to give the opportunity for any other node with empty slots to claim this job.

Once ```CLAIM_EXCEPTIONAL_INTERVAL_SEC``` has lapsed and if this particular job has still not been allocated (i.e. no other node has taken the job already), a random number (between 60 and 1440 minutes) is generated to decide when the node will restart the job (put it on RESTART status) and give the opportunity to other nodes (with empty slots) including itself to execute it.


## Affinity Use Cases

Let's assume the following configuration, featuring a Fabric cluster comprised of 3 different physical nodes with the following IPs:

```Node 1: 10.0.0.10```

```Node 2: 10.0.0.20```

```Node 3: 10.0.0.30```


The project consists in running a Fabric Job to monitor a Kafka Messaging Queue, on which network QoS alarms are continuously published and streamed by a source external to the Fabric Cluster.

In turn each message, which can consists of hundreds of entries, will be handled by the Fabric Cluster as follows:

- Update the corresponding CommonDB table located on each Fabric node,
- Save the data in Cassandra for backup purposes.

Assuming an incoming message rate of 3 messages per second and an average message size of 1MB, it becomes clear that a specific node (let's say Node 1) handling this Job needs to be allocated since on-going strong i/o capabilities for read/write operations in commonDB tables are essential.

Using Fabric Node synchronization capability, all 3 nodes will then be kept in-sync using the mechanism described in the [Fabric CommonDB](/articles/22_reference(commonDB)_tables/04_fabric_commonDB_sync.md) article.




[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/09_jobs_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/11_batch_process_overview.md)


