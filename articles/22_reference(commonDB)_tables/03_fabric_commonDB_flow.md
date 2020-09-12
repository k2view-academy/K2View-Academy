# CommmonDB - Architecture and Flow


## Overview
Fabric CommonDB architecture has been designed to answer the following requirements:

- Keep reference tables in each node
- Manage their synchronization with the source, either automatically in the background or on-demand according to a specific schedule.
- Ensure high availability levels at all times with the lowest access time possible.

CommonDB consists of a single SQLite file that contains all the common reference tables that have been designed during their creation process.

This means that in a distributed environment (Fabric Cluster) each Fabric node contains all common tables within this single file. 



 Parallel transactions on common tables:
 The first commit updates the table. The commit is initiated:
• Short transaction- the user runs the commit command.
• Large transaction- the commit is initiated internally for each bulk size, populated in the Cassandra.
 Common Snapshot:
 When you run delete and then insert on a common table in the same trx, or you run delete command in the trx, the fabric creates a snapshot on the common table. Note: You can still create a snapshot using population, and ref_sync when the population includes a truncate of the table. The sync is a separate trx.



## Synchronization modes

Fabric provides 2 different modes to synchronize reference tables data.

### Update Mode: 
This mode is (automatically) selected in cases where an update from a reference table is needed. 
In this mode, update are performed in ways of Create/Update/Delete SQL queries directly on the table

- Small updates: - In cases where less than 1000 updates needs to be performed
- Big updates: - In cases exceeding 100 rows, in which case bulks of 1000 rows are created in Cassandrsa


### Snapshot Mode:
This mode is (automatically) selected in cases where synchronizing the entire table is needed. A full snapshot of the table is then created by an available Fabric node, and is published both to the corresponding Kafka topic dedicated to the table being updated (header only) and to Cassandra (data).

- The header published on the Kafka update topic contains the UUID of the snapshot

- The Cassandra table row contains the following:
  - Uuid – unique per snapshot.
  - Table name
  - message id (from 0 upwards)
  - Data
        List of updates (as for regular update message).
         The size of list (default set using the configuration parameter and which user can changed on a per-snapshot basis). 


A snapshot will only be published once one of the following actions will be triggered: 

o	The full table synchronization is initialized by a job.
o	Scheduled Sync time has come.
o	Manually, when requested by the user.

Note that in addition, if a delete request is sent to a Reference Table without a ```where``` statement, it is automatically treated as a snapshot update. 

## Synchronization Properties
The transaction of the common table is done in asynchronous mode, i.e. you cannot view the updated data until you run the commit, and the fabric updates the common table.
 The transaction is sent to Kafka and is saved into the Kafka or the Cassandra, depending on its size:
 A new configurable parameter in config.ini - TRANSACTION_BULK_SIZE- defines the maximum number of commands, sent by each bulk.
 For example:
 



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


## Common Area table initialization
When a new node is  coming up online, and rejoins the current Fabric cluster, all the common tables need to be brought to this node. Two options are available to perform this enrollment:

- Option 1: Directly from kafka
The new node connects directly to each kafka topics (one per reference table) to look whether a snapshot is available:
*-	the table is regularly sync-ed as per defined in its sync schedule and therefore a snapshot is available
*-	a snapshot has already been created due to a similar request generated by another node. Note that depending on the size of the table the table will be synced either from the kafka topic or from Cassandra

- Option 2: From another node
*-	no snapshot is available on the corresponding Kafka topic, then the new node asks for a snapshot to be created by another node and waits by listening Kafka
*-	another node prepared a snapshot and puts it either in kafka (short) or cassandra (long)
o	manual command ? maybe new feature
o	startup/recovery process 

## Use Cases
Studio
Existing Reference list used by LU has only meaning on first / force sync (see Advanced features).
-	First time ever Reference Table for node, get of LUI will wait first for Ref Table to re-sync -> if reference needed
-	Sync from reference and then GET – ref_sync and then GET in same session for cases where I need most updates ref table

## Deploy Process

Deploying a new reference table will have the following consequences:
1. All running reference table synchronization jobs will stop.
2  A new table/index will be created on CommonDB 
3. All Kafka Consumer/Topic will be cleared if the table was removed.
4. A new Kafka topic will be created if a new table was added.
5. A new Kafka consumer will be created for each node.
6. New sync jobs will be started (even if deploy failed so not to prevent existing sync-ing although the ref table deploy failed)
7. Existing configuration parameters are applied (such as sync job retry interval) on coordinator node


## Sync Process
If truncate applied 
•	Start snap
•	Add all rows to the snap
•	If any error or exception rollback 
•	Else commit – in a single transaction:
o	Create indices on the temporary table.
o	Drop an old reference table.
o	Rename the temporary table.
If no truncate defined 
•	Run updates for table (note, no transactions are available at this stage)
•	batch of transactions is available -> everything in one commit, unless failures (1 by 1)

## Configuration Settings

### CommonDB General Settings

These are located in the *common_area_config* section of the Config.ini file located in the home directory of each Fabric Node.


#### Messages distribution mode - MEMORY / KAFKA
```MESSAGES_BROKER_TYPE=MEMORY```

#### Max retry for a poisoned message
```PROCESS_UPDATE_MESSAGE_RETRIES_COUNT=3```

#### Max idle time when consuming snapshot messages, if processing hangs longer the consumer will return IDLE_TIMOUT
```CONSUMER_IDLE_TIME=60000```

#### Max retry for common area operation
```OPERATION_RETRIES_COUNT=3```

#### Affinity to common sync job (comma separated), default: no affinity
```SYNC_JOBS_AFFINITY=10.23.10.11```

#### If true, remove all topics that not in use on drop
```DELETE_TOPICS_ON_DROP=true```

##### Size of transaction bulk size
```TRANSACTION_BULK_SIZE= 1000```
If 2500 insert commands are required, each bulk of 1000 commands is sent to Kafka, while the update content is kept in Cassandra. These 2500 inserts are divided into 3 transactions, the first 2 containing 1000 rows to insert, and the third one 500.

#### TIMEOUT
Maximum idle time when consuming snapshot messages
```
CASSANDRA_WAIT_MESSAGE_TIMEOUT=60000
```

#### MAXIMUM TRANSACTIONS in BULK
```
MAX_TRANSACTIONS_COMMIT=100
```





#### SNAPSHOTS
```
COMMONS_SNAP_TABLE=snapshots
```

#### Snapshot table TTL in seconds - Default is one week

```
# set to one day
COMMONS_TABLE_TTL=86400 
```

### Cassandra Snapshots Settings

One separate keyspace is dedicated to long messages, each one stored in a table.


•	Kafka consumer and producer setting - two sections. + 2 SSL parameters sections for consumer and producer 
•	Reference commonDB sqlite files location 
•	Operation mode one of (Kafka , Memory )
•	Mode for PoCs w/o kafka dependency (no data persistency) (achi)








[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/02_add_a_reference_table.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/04_fabric_commonDB_CLI.md)


