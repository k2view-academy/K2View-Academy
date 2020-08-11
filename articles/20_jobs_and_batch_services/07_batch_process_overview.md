# **Fabric Batch Overview** 
The batch process is a Fabric utility that provides a mechanism enabling users to run multiple commands on Populations' instances, regardless of its size or type.
As such, the batch process is used to trigger the following applications:
- sync instances - multiple sync applied to a set of LUIs or groups of LUIs
- run broadway flows - to run multiple flows
- publish changes - to republish data, using the CDC notification mechanism


# **Fabric Batch Features**
Using the Batch process will provide the following assets:
- Efficient distribution of jobs between nodes
- Ability to dynamically change the configuration of work balance between nodes during the execution phases.
- Monitoring of the instances synchronization progress (batch sync) at the following levels: Cluster, DC, or nodes.
- Recovery process in case of failure during the process (Nodes are getting down, etc..)
- Ability to stop and resume the migration process (LUI synchronization)
- Process tracking at entity level (timing, duration, handling node, failure management)




[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/06_jobs_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/08_batch_commands.md)
