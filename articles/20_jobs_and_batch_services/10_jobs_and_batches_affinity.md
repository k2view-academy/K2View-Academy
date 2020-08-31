# Jobs & Batch Processes Affinity


## Affinity Overview
The term Nodes Affinity refers to Fabric's ability to initiate a node that sends and allocates a Job or a batch process to a specific handling node in a Fabric cluster. This is particularly useful when specific nodes are reserved for specific tasks or are dedicated for time-consuming and complex processes.
The ability to allocate specific nodes to handle specific types of jobs can have a positive impact on a cluster's overall performance and Quality of Service.
For example, large sync processes can be executed by a specific node while CDC or cross-instance searches are handled by another process.


## Affinity Properties

<table style="width: 619px;">
<tbody>
<tr>
<td style="width: 103px;">Physical Affinity</td>
<td style="width: 503px;">
<p>The node's IP or DC name. For example:</p>
<ul>
<li>10.20.30.40</li>
<li>DC-Europe</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 103px;">Logical Affinity</td>
<td style="width: 503px;">
<p>A role attached to a specific physical node which can have a dedicated number of threads, lower or equal to the number of threads allocated to the node.</p>
<p>A Logical ID can be allocated to the node by adding the requested ID to the <strong>node.id</strong> file in the <strong>fabric_home/config</strong> directory. Each node can have multiple logical names and the same logical node can be shared by multiple physical nodes.</p>
</td>
</tr>
</tbody>
</table>

## Affinity Use Cases

Let's assume the following configuration featuring a Fabric cluster comprised of three different physical nodes with the following IPs:

```Node 1: 10.0.0.10```

```Node 2: 10.0.0.20```

```Node 3: 10.0.0.30```


The project consists of running a Fabric Job to monitor a Kafka messaging queue where network QoS alarms are continuously published and streamed by a source external to the Fabric cluster.

Each message, which can consist of hundreds of entries, is handled by the Fabric cluster as follows:

- Update the corresponding CommonDB table located on each Fabric node.
- Save the data in Cassandra for backup purposes.

Assuming an incoming message rate of three messages per second and an average message size of 1MB, a specific node (let's say Node 1) must be allocated to handle this Job since ongoing strong i/o capabilities for read/write operations in commonDB tables are essential.

Using the Fabric Node synchronization capability, all three nodes are kept in-sync using the mechanism described in the [Fabric CommonDB](/articles/22_commonDB/01_fabric_commonDB_overview.md) article.




[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/09_jobs_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/11_batch_process_overview.md)


