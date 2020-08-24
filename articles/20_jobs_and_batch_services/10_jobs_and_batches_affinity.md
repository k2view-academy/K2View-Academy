# Jobs & Batch Processes Affinity


## Affinity Overview
Nodes Affinity refers to the ability of Fabric initiating node to send and allocate a job or a batch process to a specific node (the handling node) within a Fabric Cluster.
This is particularily handy when specific nodes are reserved for specific tasks or need to be dedicated for time-consuming or heavy processing executions. 
Allocating specific node to handle particular types of jobs could have very big and positive impact on the overall cluster's performance and QoS.
For example - large sync-ing processes would be executed by a specific node while CDC or Cross-Instances searches would be handled by a different one.


## Affinity Properties

### Physical Affinity:
It consists of a node's IP or a DC name
e.g. 10.20.30.40, DC-Europe



### Logical Affinity
It can be seen as a role attached to a specific physical node and to which can be dedicated a number of threads, lower or equal to the number of threads allocated to the physical node.
Such a Logical ID can be giving to node by adding the requested id to the 'node.id' file located in the fabric_home/config directory.
Each node can have multiple logical ids.



## Affinity Use Cases

### Use Case 1

### Use Case 2






[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/09_jobs_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/11_batch_process_overview.md)


