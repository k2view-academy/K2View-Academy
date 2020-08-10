# **Fabric Batch Overview** 
The batch command was designed to enable Fabric users to encapsulate multiple instances of the same operation to any given population, regardless of its size or type.
As such, the batch process can be used in any of the following applications:
- migrate - multiple sync applied to a set of LUIs or groups of LUIs
- broadway flows - to run multiple flows
- Message queues - to republish data, using the CDC notification mechanism



Using the Batch process will provide the following assets:
- Efficient distribution of jobs between nodes
- Ability to dynamically change the configuration of work balance between nodes during the execution phases.
- Monitoring of migrate progress (batch sync) at the following levels: Cluster, DC, or nodes.
- Recovery process in case of failure during the process (Nodes are getting down, etc..)
- Ability to stop and resume the migration process (LUI synchronization)
- Process tracking at entity level (timing, duration, handling node, failure management)


 # **What is a Fabric Batch Process ?** 
Migrate:

BATCH OracleLU.('1','2','3','4') FABRIC_COMMAND="sync_instance OracleLU.?" with ASYNC='true';


Broadway:

BATCH OracleLU fabric_command="broadway OracleLU.SampleFlow SampleIID=?" with async=true;


Cdc republish:

BATCH OracleLU from fabric fabric_command="cdc_republish_instance OracleLU.?" with async=true;
