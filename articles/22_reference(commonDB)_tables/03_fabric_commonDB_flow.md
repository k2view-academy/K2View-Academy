# CommmonDB - Synchronization Overview


## Overview
Copies of Reference tables are kept within each Fabric Node, therefore Fabric CommonDB architecture has been designed to answer the following requirements:

- Manage the synchronization process with their source, either automatically in the background, on-demand at any given time, or according to a specific schedule.
- Ensure high availability levels at all times with the lowest access time possible.

CommonDB consists of a single SQLite file that contains all the Reference Tables that have been populated from External Sources or from Fabric System of Records (SOR).

This means that in a distributed environment (Fabric Cluster) each Fabric node contains all common tables within this single SQLite file. 



## Synchronization Use Cases

1. 
A system might need to operate a synchronization process every day at 2 AM as a result of a maintenance task - i.e. all new customers, or new transactions created over the last 24 hours. In this case, a recurring background sync will be scheduled for this specific time.

2.
A customer service operative needs get the most updated list of new services subscribed by a customer in real-time. In this case the web-service or job request will trigger the  refsync and ref_sync_wait commands described [here](/articles/22_reference(commonDB)_tables/03_fabric_commonDB_runtime.md#ref_sync-lu_namelu-name-tablesall-or-table-1table-2etc-forcetruefalse).

3. 
A sales manager is closing the case he just treated - resulting in creating a new case entry in the Billing Database. 
Assuming the customer provided a new email address whereby the email provider details are kept on a Reference Table (featuring all email providers from all customers), all nodes will need to synchronize the reference table so to provide the most updated list of providers to other sales managers.  


## Examples

In cases of multiple transactions on a given common table (multiple nodes execute transactions with their local commonDB file copy) , the first node to commit the change will also updates the table.

Let's assume a simple case: 2 nodes are modifying the same Reference Table, each one kept locally. 

As will be explained later, Kafka queues are used as the transaction broker common between all nodes. 

In a way each Kafka queue plays the role of a virtual table on which all transactions updates are published, and from which each node reads the transactions of all the nodes (including its own) to physically update its own local copy of commonDB SQLite file.  


- Node 1 and Node 3 wish to modify table T5 of commonDB with an update on same row, but with different value:

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



## CommonDB Table Initialization
When a new node is  coming up online, and rejoins the current Fabric cluster, all the common tables need to be brought to this node. Two options are available to perform this enrollment:

- **Option 1: Directly from kafka**

The new node connects directly to each kafka topics (one per reference table) to look whether a snapshot is available:
  -	the table is regularly sync-ed as per defined in its sync schedule and therefore a snapshot is available
  -	a snapshot has already been created due to a similar request generated by another node. Note that depending on the size of the table the table will be synced either from the kafka topic or from Cassandra


- **Option 2: *From another node**
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
  
- Selecting and modifying data contained in Reference Tables can be done by setting the ```common_local_trx``` flag to TRUE, before running a commit.
    ```fabric>set COMMON_LOCAL_TRX=true;```
  
  - Using the [example](/articles/22_reference(commonDB)_tables/02_add_a_reference_table.md#how-do-i-create-a-new-reference-table-in-fabric) defined earlier, and the DEVICESTABLE2017 Reference Table:
  - Enable Reference Table modification: ```fabric>set COMMON_LOCAL_TRX=true;```
  - Use the ```select``` command to view the row that needs modification:
  
    ```
    fabric>select TAC, BRANDMODEL  from DEVICESTABLE2017 where TAC=35156209;
    |TAC     |BRANDMODEL             |
    +--------+-----------------------+
    |35156209|GALAXY J3 2016 SM-J320F|
    ```

  - Start a transaction: ```fabric>begin;```
  - Using the ```update``` command, operate a change in the table: ```fabric>update DEVICESTABLE2017 set BRANDMODEL='GALAXY J3--2016 SM-J320F' where TAC=35156209;```
  - Check that the change was committed:
  
    ```
    fabric>select TAC, BRANDMODEL  from DEVICESTABLE2017 where TAC=35156209;
    |TAC     |BRANDMODEL              |
    +--------+------------------------+
    |35156209|GALAXY J3--2016 SM-J320F|
    ```
  - Close the transaction: ```fabric>end;```
  - Note that if you forget to close the transaction, write sessions to the Reference Table (such as a scheduled Sync) will not work.
  
  


[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/02_reference_table_fabric_studio.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/04_commonDB_synch_modes_and_flow.md)


