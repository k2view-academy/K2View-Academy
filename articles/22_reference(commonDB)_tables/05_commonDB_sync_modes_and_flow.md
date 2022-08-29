# CommonDB Architecture


## Synchronization Modes

Regardless of the synchronization type (background or on-demand), Fabric provides two different options for synchronizing Reference tables data:
- Update synchronization option which is used to modify rows within existing tables.
- Snapshot synchronization option which is used to replace entirely a reference table.

Both Update and Snapshot options can work in either one of the following modes: 

- Short Message mode: applied for transactions that contain less than 1000 rows.
- Long Message mode: applied for transactions exceeding 1000 rows, in which case bulks of 1000 rows are created in Cassandra.

For example, if an update consists of running 2500 insert commands, the 2500 inserts are divided into 3 bulks of 1000, 1000 and 500 each, then each 1000 bulk is written to Cassandra.


### Update Mode
This mode is by default, selected when a row update onto the reference table is required. 
In this mode, updates are performed as Create/Update/Delete SQL queries directly on the table itself. 
Each node executes this change locally on its local SQLite CommonDB copy as a single logical transaction. A message to Kafka is then sent with the content of the update for all other nodes to execute.
Note that if the update is over 1000 rows, Cassandra will also be involved as described later in this article.


### Snapshot Mode

When an update happening in snapshot mode, the node that requested the update will take the data from Kafka and/or Cassandra and not directly from the node that prepared the snapshot.

The snapshot mode will only triggered by one of the following actions: 

-	Manually, when requested by the user sending a delete request to a Reference Table without a ```where``` statement
- When selecting the Truncate option in the [Truncate Before Sync]() property field in Fabric Studio (under the Table Properties panel). 
In most cases the full table synchronization is happening when the truncate mode is set from the studio.


#### Snapshots Synchronization Mechanism

Each node performs the following snapshot synchronization if instructed in the Kafka message. 

- The snapshot is initiated.
- All rows are added to the snapshot.
- The operation is committed in one transaction:
  - The old reference table is dropped.
  - The temporary table is renamed to the Reference table's name.



## Synchronization Flow

Two types of transactions can be differentiated when updating a CommonDB reference table: 
- Short message updates - whereby both update's message and content are stored on the dedicated Kafka queue.
- Long message updates - whereby the update's message is published on the dedicated Kafka queue while the update's content is stored in Cassandra.

Two different flows occur for each transaction, depending on whether the update content size exceeds 1000 rows or not. 


### Short Message Case

The following illustration shows how a Synchronization Job (Sync Job 1) publishes an update notification and a short message content on the Kafka Queue dedicated to Table 1, subsequently causing all listening nodes in the cluster to write the update directly from Kafka to their own SQLite CommonDB copy. 

![image](/articles/22_reference(commonDB)_tables/images/08_commonDB_RefSyncShort.png)



### Long Message Case

The following illustration shows how a Synchronisation Job (Sync Job 2) publishes an update message in the Kafka Queue dedicated to Table T, and how it writes the long message content in Cassandra. This, subsequently, causes any listening node within the cluster to write the update's content directly from Cassandra into its own SQLite CommonDB copy. 

![image](/articles/22_reference(commonDB)_tables/images/09_commonDB_RefSyncLong.png)


### Synchronization Properties

Any transaction involving the common table is done in asynchronous mode, meaning that the updated data cannot be seen until it has been committed, and until Fabric updates the relevant CommonDB table; more over, each node will perform the update in its own time.
The transaction message is sent to Kafka while its content is saved into Kafka (within the message payload) or in a Cassandra keyspace, depending on its size.




[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/04_fabric_commonDB_sync.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/06_fabric_commonDB_misc.md)

