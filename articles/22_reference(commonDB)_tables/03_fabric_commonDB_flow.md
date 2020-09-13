# CommmonDB - Architecture and Flow


## Overview
Fabric CommonDB architecture has been designed to answer the following requirements:

- Keep reference tables in each node
- Manage their synchronization with the source, either automatically in the background or on-demand according to a specific schedule.
- Ensure high availability levels at all times with the lowest access time possible.

CommonDB consists of a single SQLite file that contains all the common reference tables that have been designed during their creation process.

This means that in a distributed environment (Fabric Cluster) each Fabric node contains all common tables within this single file. In case of parallel transactions on common tables, the first node to commit the change updates the table.

Examples:

- Node 1 and Node 3 wish to modify table T5 of commonDB with an update on same row but with different value:

  - Node 3 wants to update T5 on Row 10 with value = 3
  - Node 1 wants to update T5 on Row 10 with value = 1
  - Assuming that table T5 is in-sync across all nodes, if Node 3 commits its update (on T5, Row 10, Value=3) first then table T5 will be first updated by Node 3 and all other nodes will subsequently update their tables using the message published by Node 3. 
  - Then, all nodes will re-update their tables using the message published by Node 1.

- Node 1 and Node 3 wish to modify table T5 of commonDB with a different update.
  - Assuming that table T5 is in-sync across all nodes, all nodes will catch up with the update messages published by Node 1 and Node 3, starting with the update that was first committed.

- Note: The publishing Node also updates its own commonDB table after reading the very message it published on Kafka, and using the update data available on Kafka's update.


## Synchronization modes

Fabric provides 2 different modes to synchronize reference tables data.

### Update Mode: 
This mode is (automatically) selected in cases where an update from a reference table is needed. 
In this mode, update are performed in ways of Create/Update/Delete SQL queries directly on the table

- Small updates: - In cases where less than 1000 updates needs to be performed
- Big updates: - In cases exceeding 100 rows, in which case bulks of 1000 rows are created in Cassandrsa


### Snapshot Mode:

This mode is (automatically) selected in cases where synchronizing the entire table is needed. 
A full snapshot of the table is then created by an available Fabric node, and is published both to the corresponding Kafka topic dedicated to the table being updated (header only) and to Cassandra (data).

- The header published on the Kafka update topic contains the UUID of the snapshot

- The Cassandra table row contains the following:
  - Uuid – unique per snapshot.
  - Table name
  - message id (from 0 upwards)
  - Data:
  ```
  *list of updates* (as for regular update message), 
  *size of list* (default set using the configuration parameter and which user can changed on a per-snapshot basis). 
  ```

A snapshot will only be published once one of the following actions will be triggered: 

-	The full table synchronization is initialized by a job.
-	Scheduled Sync time has come.
-	Manually, when requested by the user.

Note that in addition, if a delete request is sent to a Reference Table without a ```where``` statement, it is automatically treated as a snapshot update. 

## Synchronization Properties

Any transaction involving the common table is done in asynchronous mode, i.e. the updated data cannot be seen until it has been committed, and Fabric updates the relevant commonDB table.

The transaction message is sent to Kafka and its content is saved into Kafka or Cassandra, depending on its size.



## Synchronization Flow

2 types of transactions can be differentiated: 
- Short message updates - update's content on Kafka queue.
- Long message updates - update's content stored on Cassandra

2 different flows occur for each of these cases, depending on whether the update content size exceeds 1000 rows or not. 


### Short Message

The case illustrated below shows how a Synchronisation Job (Sync Job 2) publishes an update notification and short message content on the Kafka Queue dedicated to Table 1, subsequently causing all listening nodes in the cluster to write the update directly from Kafka to its SQLite CommonDB copy. 

![image](/articles/22_reference(commonDB)_tables/images/08_commonDB_RefSyncShort.png)



### Long Message

The case illustrated below shows how a Synchronisation Job (Sync Job 1) publishes an update message on the Kafka Queue dedicated to Table T, and how it writes the long message content in Cassandra, subsequently causing any listening node in the cluster to write the update content directly from Cassandra to its SQLite CommonDB copy. 

![image](/articles/22_reference(commonDB)_tables/images/09_commonDB_RefSyncLong.png)



## Snapshots Synchronization Mechanism

### Option 1 - Truncate option is set

- The snapshot is started
- All rows are added to the snapshot
- If any error or exception occurs the rollback process is engaged, otherwise the transaction is committed in a single transaction:
  - Indices are created on the temporary table.
  - The old reference table is dropped.
  - The temporary table is renamed to the Reference Table name.

### Option 2 - Truncate option is not set

- All updates for the Reference Table are executed 
- Once the batch of transactions is available, all the updates are executed in one commit, unless there are failures in which case each update is executed one by one.



## Common Area table initialization
When a new node is  coming up online, and rejoins the current Fabric cluster, all the common tables need to be brought to this node. Two options are available to perform this enrollment:

- Option 1: *Directly from kafka*

The new node connects directly to each kafka topics (one per reference table) to look whether a snapshot is available:

  -	the table is regularly sync-ed as per defined in its sync schedule and therefore a snapshot is available
  -	a snapshot has already been created due to a similar request generated by another node. Note that depending on the size of the table the table will be synced either from the kafka topic or from Cassandra


- Option 2: *From another node*
  
  -	no snapshot is available on the corresponding Kafka topic, then the new node asks for a snapshot to be created by another node and waits by listening Kafka
  - another node prepared a snapshot and puts it either in kafka (short) or cassandra (long)



## Use Cases

### Deployment Process

Deploying a new reference table will have the following consequences:
1. All running reference table synchronization jobs will stop.
2  A new table/index will be created on CommonDB 
3. All Kafka Consumer/Topic will be cleared if the table was removed.
4. A new Kafka topic will be created if a new table was added.
5. A new Kafka consumer will be created for each node.
6. New sync jobs will be started (even if deploy failed so not to prevent existing sync-ing although the ref table deploy failed)
7. Existing configuration parameters are applied (such as sync job retry interval) on coordinator node


### Table Removal
This happens as a result of deployment process:

- Table is dropped from CommonDB
-	All local topic consumers and producers are dropped.

### commonDB drop
This happens when running the following command ```drop lutype k2_ref;``` from a Fabric Node:

- All existing tables in common.db are dropped (including the internal and temporary tables)
- All existing sync jobs are stopped.
- All existing consumer / producers are terminated.





## Miscellaneous

- Kafka consumer and producer must be setup along with 2 SSL parameters, one for the consumer topic and one for the producer topic. 

- Operation mode can be with Kafka or in Memory depending on project scope
  - Mode for PoCs w/o kafka dependency
  - no data persistency


[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/02_add_a_reference_table.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/04_fabric_commonDB_configuration.md)


