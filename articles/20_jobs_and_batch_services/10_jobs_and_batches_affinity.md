# Job & Batch Processes Affinity


## Affinity Overview
*Affinity* refers to Fabric assigning a job or a batch process to a specific handling node within a Fabric Cluster.
This is particularly handy when specific nodes are reserved for specific tasks or need to be dedicated to time-consuming or heavy processing executions.

Allocating a specific node to handle particular types of jobs can have a significant and positive impact on the overall cluster's performance and Quality of Service.

For example, in a large Fabric cluster, processes handling a very large amount of data can be executed by a single specific node. In contrast, a different node can handle Capture Data Changes or Cross-Instance searches. This technique removes unnecessary overhead by spreading the load across the cluster.      


## Affinity Properties

### Physical Affinity
Physical Affinity consists of a node's IP or a DC name, e.g.: ```10.20.30.40``` or ```DC-Europe```

### Logical Affinity
Logical Affinity can be viewed as a role assigned to a specific physical node, to which several threads can be dedicated, equal to or lower than the number of threads allocated to that node.
Such a Logical ID can be given to a node by adding the requested ID to the 'node.id' file located in the fabric_home/config directory.
Each node can have multiple logical names, and a logical node can be shared by multiple physical nodes.

#### Affinity Pool Size

A *Recommended Pool Size* capability has been added to the affinity function from V6.4.2 onwards to rebalance jobs and get the ability to dynamically split (at runtime) job executions between nodes. This flag must be defined in the node.id file:
Two new parameters can be defined:
- The recommended number of jobs
- The maximum number of jobs that can run concurrently on the same node.

For example: ```logical_id:2 4``` or ```logical_id:2-4```

Whereby:
- 2 is the recommended number of jobs that can run concurrently.
- 4 is the maximum number of jobs that can run concurrently.

The dedicated node will avoid taking jobs above the recommended number immediately in order to give the opportunity to other node with empty slots to claim that specific job. 

In such a case, if a node has already reached its recommended size, and if it is trying to claim a new job allocated to it, the node will need to wait for a number of seconds specified in the ```CLAIM_EXCEPTIONAL_INTERVAL_SEC``` parameter - *before* it can claim *that specific* job.
The parameter is configured in the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file. Its default value is set to 60 seconds.

This mechanism allows different nodes with empty slots to claim this job.
In cases where a node claimed a job above its recommended pool size, the server will stop and release all the extra jobs that are running above the recommended pool size. Then, other servers will be allowed to take the jobs that have been stopped. 

For this purpose, a random number is generated to determine when it will be set to the *restart* status, thereby giving other nodes (with empty slots) the opportunity to execute it. This random number will fluctuate between the 2 following hidden parameters that can be changed in [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) file under the [jobs] section:

* ```MIN_GIVE_UP_EXCEPTIONAL_MINUTES``` - defines the minimum time for restarting jobs after the recommended time is reached. The default value = 1.
* ```MAX_GIVE_UP_EXCEPTIONAL_MINUTES``` - defines the maximum time for restarting jobs after the recommended time is reached. The default value = 14400.

## Affinity Use Cases

Let's assume the following configuration, featuring a Fabric cluster comprised of 3 different physical nodes with the following IPs:

```Node 1: 10.0.0.10```

```Node 2: 10.0.0.20```

```Node 3: 10.0.0.30```


The project consists of running a Fabric Job to monitor a Kafka Messaging Queue, on which network QoS alarms are continuously published and streamed by a source external to the Fabric Cluster.

In turn, each message, which can consist of hundreds of entries, will be handled by the Fabric Cluster as follows:

- Update the corresponding CommonDB table located on each Fabric node,
- Save the data in Cassandra for backup purposes.

Assuming an incoming message rate of 3 messages per second and an average message size of 1MB, it becomes clear that a specific node (let's say Node 1) handling this job needs to be allocated, since on-going strong i/o capabilities for read/write operations in commonDB tables are essential to meet higher performance requirements.

Using Fabric Node synchronization capability, all 3 nodes will then be kept in sync using the mechanism described in the [Fabric CommonDB Cluster Synchronization](/articles/22_reference(commonDB)_tables/04_fabric_commonDB_sync.md) article.




[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/09_jobs_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/11_batch_process_overview.md)


