# commonDB Architecture



## Synchronization Flow

Two types of transactions can be differentiated when updating a commonDB reference table: 
- Short message updates - whereby both update's message and content are stored on the dedicated Kafka queue.
- Long message updates - whereby the update's message is published on the dedicated Kafka queue while the update's content is stored in Cassandra.

Two different flows occur for each transaction, depending on whether the update content size exceeds 1000 rows or not. 


### Short Message Case

The following illustration shows how a Synchronisation Job (Sync Job 2) publishes an update notification and a short message content on the Kafka Queue dedicated to Table 1, subsequently causing all listening nodes in the cluster to write the update directly from Kafka to their own SQLite CommonDB copy. 

![image](/articles/22_reference(commonDB)_tables/images/08_commonDB_RefSyncShort.png)



### Long Message Case

The following illustration shows how a Synchronisation Job (Sync Job 1) publishes an update message in the Kafka Queue dedicated to Table T, and how it writes the long message content in Cassandra. This, subsequently, causes any listening node within the cluster to write the update's content directly from Cassandra into its own SQLite CommonDB copy. 

![image](/articles/22_reference(commonDB)_tables/images/09_commonDB_RefSyncLong.png)


### Synchronization Properties

Any transaction involving the common table is done in asynchronous mode, meaning that the updated data cannot be seen until it has been committed, and until Fabric updates the relevant commonDB table; more over, each node will perform the update in its own time.

The transaction message is sent to Kafka while its content is saved into Kafka (within the message payload) or in a Cassandra keyspace, depending on its size.


## Synchronization Modes

Regardless of the synchronization type (background or on-demand), Fabric provides two different modes for synchronizing Reference tables data.

### Update Mode
This mode is (automatically) selected when any row update to the reference table is needed. 
In this mode, updates are performed as Create/Update/Delete SQL queries directly on the table itself. Each node executes this change locally on its local SQLite commonDB copy.

- Small updates: applicable for less than 1000 updates.
- Big updates: applicable for transactions exceeding 1000 rows, in which case bulks of 1000 rows are created in Cassandra:

For example, an update consists of running 2500 insert commands. Each 1000 command bulk is written to Cassandra. The 2500 inserts are divided into 3 bulks of 1000, 1000 and 500 each. Kafka gets the transaction message. One message is sent to Kafka per table and per transaction. 


### Snapshot Mode

#### Snapshot Content 
For example, an update consists of running 2500 insert commands. Each 1000 command bulk is written to Cassandra. The 2500 inserts are divided into 3 bulks of 1000, 1000 and 500 each. Kafka gets the transaction message. One message is sent to Kafka per table and per transaction.

- The header published in the Kafka update topic contains the UUID of the snapshot

- The Cassandra table row contains the following:
  - Uuid – unique per snapshot.
  - Table name
  - message id (from 0 upwards)
  - Data:
  ```
  *list of updates* (as for regular update message), 
  *size of list* (default set using the configuration parameter and which the user can change on a per-snapshot basis). 
  ```

A snapshot will only be published once one of the following actions is triggered: 

-	The full table synchronization is initialized by a job.
-	A scheduled Sync time has arrived.
-	Manually, when requested by the user.
- When a delete request is sent to a Reference Table without a ```where``` statement


#### Snapshots Synchronization Mechanism

Each node performs the following snapshot synchronization: 

##### Option 1 - Truncate option is set in the [Truncate Before Sync]() property. 

- The snapshot is started
- All rows are added to the snapshot
- If an error or exception occurs, the rollback process begins, otherwise the transaction is committed in one transaction:
  - Indices are created in the temporary table.
  - The old reference table is dropped.
  - The temporary table is renamed to the Reference table's name.

##### Option 2 - Truncate option is not set

- All updates for the Reference table are executed 
- Once the batch of transactions is available, all updates are executed in one commit, unless there are failures in which case each update is executed.


[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/04_fabric_commonDB_sync.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/06_fabric_commonDB_misc.md)


