# commonDB Architecture



## Synchronization Flow

2 types of transactions can be differentiated when a commonDB reference table is updated: 
- Short message updates - whereby both update's message and content are both stored on the dedicated Kafka queue.
- Long message updates - whereby the update's message is published on the dedicated Kafka queue while the update's content is stored on Cassandra.

2 different flows occur for each of these cases, depending on whether the update content size exceeds 1000 rows or not. 


### Short Message Case

The case illustrated below shows how a Synchronisation Job (Sync Job 2) publishes an update notification and short message content on the Kafka Queue dedicated to Table 1, subsequently causing all listening nodes, in the cluster, to write the update directly from Kafka to its own SQLite CommonDB copy. 

![image](/articles/22_reference(commonDB)_tables/images/08_commonDB_RefSyncShort.png)



### Long Message Case

The case illustrated below shows how a Synchronisation Job (Sync Job 1) publishes an update message on the Kafka Queue dedicated to Table T, and how it writes the long message content in Cassandra, subsequently causing any listening node, within the cluster, to write the update content directly from Cassandra to its own SQLite CommonDB copy. 

![image](/articles/22_reference(commonDB)_tables/images/09_commonDB_RefSyncLong.png)


### Synchronization Properties

Any transaction involving the common table is done in asynchronous mode, meaning that the updated data cannot be seen until it has been committed, and until Fabric updates the relevant commonDB table.

The transaction message is sent to Kafka while its content is saved into Kafka (within the message payload) or in a Cassandra keyspace, depending on its size.


## Synchronization modes

Regardless of the synchronization type (background or on-demand), Fabric provides 2 different modes to synchronize reference tables data.

### Update Mode: 
This mode is (automatically) selected in cases where row updates to the reference table are needed. 
In this mode, updates are performed in ways of Create/Update/Delete SQL queries directly on the table itself - each node will execute this change locally on his local SQLite commonDB copy) 

- Small updates: - In cases where less than 1000 updates needs to be performed
- Big updates: - In cases exceeding 1000 rows, in which case bulks of 1000 rows are created in Cassandra:

e.g. Supposing the update consists of  running 2500 insert commands, each bulk of 1000 commands is written to Cassandra (the 2500 inserts are divided into 3 bulks of 1000, 1000 and 500 each) while Kafka gets the transaction message (one message is sent to Kafka per table and per transaction). 

Note:
The [bulk size](/articles/22_reference(commonDB)_tables/07_fabric_commonDB_configuration.md#bulk-size) can be configured, but due to Kafka message size limitations, it is recommended not to exceed 1000 rows per bulk. 

### Snapshot Mode:

#### Snapshot Content 
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
- When a delete request is sent to a Reference Table without a ```where``` statement


#### Snapshots Synchronization Mechanism

Each node will perform the following snapshot synchronization as follow: 

##### Option 1 - Truncate option is set

- The snapshot is started
- All rows are added to the snapshot
- If any error or exception occurs the rollback process is engaged, otherwise the transaction is committed in a single transaction:
  - Indices are created on the temporary table.
  - The old reference table is dropped.
  - The temporary table is renamed to the Reference Table name.

##### Option 2 - Truncate option is not set

- All updates for the Reference Table are executed 
- Once the batch of transactions is available, all updates are executed in one commit, unless there are failures in which case each update is executed one by one.


[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/04_fabric_commonDB_sync.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/06_fabric_commonDB_misc.md)


