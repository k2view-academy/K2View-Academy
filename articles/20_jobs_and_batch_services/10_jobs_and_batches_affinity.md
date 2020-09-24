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

Using Fabric Node synchronization capability, all 3 nodes will then be kept in-sync using the mechanism described in the [Fabric CommonDB]
() article.




[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/09_jobs_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/11_batch_process_overview.md)


