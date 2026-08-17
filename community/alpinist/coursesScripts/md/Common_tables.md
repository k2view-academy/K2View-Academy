# Common_tables

Common DB

# What is a Common Table?

The Common Area (or CommonDB) is a component of Fabric that stores tables and data outside of Logical Unit Instances (LUIs).

Typical Uses of Common Tables:

- Reference Tables

- Lookup Data

- Cross-LUI/LUT Data

- Reports

- Data for downstream applications

Common tables are stored in dedicated SQLite files within the "Common Area", a physical location on the server defined in the config.ini file using the [fabricdb].MDB_COMMONS_PATH parameter.

Default path: ${FABRIC_HOME}/storage/common

.

Each node in the Fabric Cluster holds a local copy of the Common tables, enabling SQL JOIN operations between Common tables and LUIs using standard SQL queries. Fabric ensures these local copies remain synchronized across nodes, keeping all SQLite files up-to-date and available for queries within any Fabric session.

The Common Area may contain one or more SQLite files, with each file representing a different schema. The schema for a Common table is determined by the table's "Schema" property:

If the schema does not exist: A new SQLite file will be created to store the table.

If the schema already exists: The table will be added to the corresponding SQLite file.

Default when left empty – “common” schema

# Common Table Types

There are two types of Common tables:

Population-Based Common Tables
These tables rely on a population process to fetch and insert data.

If the Sync Method is set to Time Interval or Decision Function, a COMMONAREA_TABLE_SYNC job is created when the table is deployed.

The job wakes up at intervals defined by the BG_REF_SYNC_INTERVAL_SEC (config.ini) parameter (default: 5 minutes) and triggers the population process according to the table's Sync Method.

Note:

If the Sync Method is set to None, the job will be created but will not run. The REF_SYNC command can manually trigger the job (explained later in this document).

SOR (System of Records) Common Tables
These tables do not define populations. Instead, they are populated through Fabric’s internal logic using Fabric transactions.
Example:

Db ci = db("fabric");

ci.beginTransaction();

String SQL = "INSERT INTO common_table (field) VALUES (?)";

Object[] params = new Object[]{value};

ci.execute(SQL, params);

ci.execute("commit");

- Note:

- You can use Fabric DB Actors to perform DML operations on Common tables. The Actors must operate within a transaction, either by using transaction stages or by manually managing the transaction with begin, commit, and rollback commands.

- When a Common table is updated within the GET functionality, there is no need to explicitly manage a transaction, as the entire GET operation runs within a transaction. The commit or rollback will be handled automatically based on the success or failure of the GET process.

# Common Table Properties

- Schema 
The name of the SQLite DB in which this table will be stored. If left empty, the table will be added to the generic “ (common.db file). See below for more information.

- Columns Collation
See Course 1 for information.

- Full-Text Search (FTS)

- See course 1 – Full-Text-Search property.

- Sync Wait Timeout
See course 1- LUI Sync Timeout. If the timeout exceeded, a timeout error is thrown.

- Sync Method
See course 1- Sync Methods

- Required Reference Tables
Define dependencies between Common Tables. See below for more information.

- Index Post Sync
This setting determines whether an index should be created on a Reference table only after the data is synced. It is particularly useful for Common tables with population, where the Truncate Before Sync property is set to true and the table contains a large volume of data (over 200 million records).

In such cases, during the sync process:

- A temporary table is created in the Common DB (including the PK only)

- The newly fetched data is loaded into the temporary table.

- Indexes are created on the temporary table.

- The main table is deleted, and the temporary table is renamed to replace it.

- Note: Insert without index showed a huge performance improvement comparing to insert with the index

## Common DB Schema

Since Common tables are stored in SQLite, any open transaction blocks other users from writing to the SQLite file (reading is possible as Common tables are using WAL file). Therefore, it is advisable to create separate schemas for tables that:

- Receive many transactions (not to block other tables)

- Need to be updated at the same time as other tables. Only sequential update can be done in the same schema

- In case of corrupted DB – minimize the impact on other tables

## Required Reference Tables property

When there are dependencies between Common tables (e.g., table t2 depends on t1), the Required Reference Tables property ensures proper synchronization.

On the first sync of t2, it will not proceed unless t1 has already completed its initial sync.

Additionally, if a ref_sync is triggered for both tables, they will run sequentially rather than simultaneously, ensuring that dependent tables sync in the correct order.

For example: balance requires payment:

# Attach Common tables to LUT

LU Reference Tables List: 
The References tab displays a list of Common Reference tables defined in the project. A Reference table should be selected only if:

You want to use the Reference table as a lookup function in one of the populations.

You need to ensure that GET operations do not run before the Common table completes its first sync.

Note:

A Reference table can still be accessed through code (e.g., in functions) even if it is not selected in the References tab.

The sync status of a Common table is verified against the k2_objects_info table within the Common table's schema. If a record exists, the GET operation can proceed.

It is recommended to limit the number of selected Reference tables to prevent unnecessary delays, as each GET operation will validate the sync status of all selected Common tables.

This configuration also affects GET operations within sessions that manually invoke the REF_SYNC command. In such cases, the GET will wait for all selected Reference tables to complete their sync before proceeding, even if the Common tables were already synced earlier.

# Common tables behind the scenes

The CommonDB consists of an SQLite file per schema (as defined in the Common table's "Schema" properties) that holds all the reference tables.

Session Initialization:

When a session is opened, Fabric automatically attaches all the SQLite files of the CommonDB to the session, enabling join queries to be executed.

Note: For any reads from the common tables, Fabric uses off-heap memory to store the retrieved data.
SQLite is using C (not java) - Joines between LUI to Common – work on off-heap. In case of selecting large volume of data from common table this will not impact the allocated heap memory

Distributed Environment and Synchronization:

Since Fabric is a distributed environment, each Fabric node contains all the CommonDB files.

Fabric uses an internal Kafka system to synchronize the Common tables across Fabric nodes.

Every transaction applied to a Common table is not directly applied to the local file. Instead, it is sent to a central Kafka broker to be consumed by all Fabric nodes. Each node then applies the transaction to its local Common tables, including the node that initiated it.

High-Level Architecture for Handling Common Table Updates:

a. Common API Handler:

On each Fabric node, a "Common API" handler is created.

b. Command Parsing and Handling:

Any command running on a Fabric session is parsed to check if it's related to one of the Common tables.

If it is, the command is sent to the Common API to be handled.

## Common API logic

The Common API acts as the manager for Common tables, residing on each node. It is responsible for both sending updates to all nodes and consuming updates received from other nodes.

To support this functionality, Fabric creates the following for each Common table once it's deployed:

- Kafka Topic for Transactions

Purpose: To send and receive transactions for the table.

Topic Name: common_area_<SCHEMANAME>.<TABLENAME>_<SPACENAME>_<CLUSTER_ID>

- Staging Area for Long Transactions (explained later)

Database Staging: Keyspace K2_ref/k2_commons with a table named "snapshots".

Kafka Staging: Topic named common_area_internal_<SCHEMANAME>.<TABLENAME>_<SPACENAME>_<CLUSTER_ID>.

- On Each Node:

Table Creation: The Common table is stored in the SQLite file based on its Schema property.

Kafka Producer: Each node has a Kafka producer to send messages for that table from sessions running on that node.

Kafka Consumer: Consumes messages for that table and apply them to the local Common DBs.

- Population-Defined Tables:

If the Sync Method is Time Interval or Decision Function, a COMMONAREA_TABLE_SYNC job is created.

This job is responsible for executing the population every BG_REF_SYNC_INTERVAL_SEC minutes (it will run or not depending on its Sync Method).

## Transaction Flow in Fabric's Common API

Fabric uses a Common API on each node to handle incoming and outgoing transactions for Common tables.

### Outgoing Transactions - Producer Flow

All DML commands executed on Common tables between the “begin” and “commit” or “rollback” statements define a single "transaction" (the transaction scope). Each transaction is handled separately by the Common API.

The Common API analyzes each transaction based on two main parameters, which determine how the transaction will be processed:

Transaction Mode:
If the first command in the transaction deletes all data in the table - either by issuing a TRUNCATE command or a DELETE * without a WHERE clause - the transaction is considered to be in SNAPSHOT mode. If not, the transaction is in TRANSACTION mode.

Transaction Size:
The producer accumulates the commands sent in the transaction. If more than a certain number (X) of commands are executed, it is considered a "Long" transaction; otherwise, it is a "Short" transaction.

Configuration Settings

Staging Area Configuration:

Set in config.ini under [common_area_config].MESSAGES_INTERNAL_BROKER_TYPE:

PUB_SUB: Uses an additional Kafka topic named common_area_internal_<SCHEMANAME>.<TABLENAME>_<SPACENAME>_<CLUSTER_ID>.

SYSTEMDB: Uses a table in the system database named k2ref.snapshots or k2common.snapshots.
Note: the system_db needs to support TTL functionality. Otherwise product enhancement will be required to delete snapshots.

Bulk Size:

The bulk size is determined as the maximum between:

Maximum Bulk Size: TRANSACTION_BULK_SIZE = 1000.

Minimum Bulk Size: LONG_TRANSACTION_MIN (a hidden parameter).

Purpose of Min and Max Parameters:

If the number of commands in a transaction is less than TRANSACTION_BULK_SIZE but more than LONG_TRANSACTION_MIN, it is still considered a "Long" transaction.

This configuration ensures efficient transaction processing by balancing the need for fewer bulks in large tables while still applying the long transaction mechanism for smaller tables.

#### Producer Error Handling

In the event of an error or session timeout (and commit or rollback were not executed):

- Short Transactions: The message will not be sent to Kafka.

- Long Transactions: If bulk messages have already been sent, a rollback message will be issued.

7.2 – add jar in java classpath (or to Kafka folder itself). Add parameter to deserialize the messages.

### Incoming Transactions - Consumer Flow

A Kafka Consumer is automatically created for each Common table on every node.

#### SNAPSHOT mode messages handling:

- Create a temporary table named __t_originalTableName.

- Apply all transactions to this temporary table.

- Execute a commit on the SQLite file after every MAX_TRANSACTIONS_COMMIT commands and after each bulk. (The default value of MAX_TRANSACTIONS_COMMIT is 100.)

- Once all transactions are applied, delete the original table and rename the temporary table to the original table's name.

#### Consumer Error Handling

The consumer reads up to [common_area_pubsub].MAX_POLL_RECORDS messages from Kafka in each poll (default: 500) for performance optimization.

Kafka commits are performed after processing each batch of MAX_POLL_RECORDS messages, as well as before and after each long transaction message.

For any Kafka-related issues (e.g., connection, commit, or read failures), Fabric will retry indefinitely (hard-coded, with no configurable parameters).

If an error occurs within a message, the consumer retries all messages from the last commit. Each message is then committed individually to Kafka.
Note: This process will significantly reduce performance.

Handling Specific Errors:

Logical Errors: If the error is logical (e.g., duplicate primary key), the message is logged and skipped without retries.

Physical Errors:

SQLite Errors (e.g., SQLITE_BUSY, SQLITE_LOCKED):
Retries are performed every OPERATION_RETRIES_WAIT_MS milliseconds (default: 1000 ms) until OPERATION_RETRIES_COUNT is reached (default: -1, meaning unlimited retries).

Kafka Commit Errors: Exit and restart processing from the next 500 messages.

Long Message Errors (Next Bulk Not Available):
The consumer waits for the next bulk, checking every DEFAULT_ACTION_MILLI milliseconds (default: 100 ms).
Max Wait Time:

PUB_SUB: CONSUMER_IDLE_TIME = 60000  (ms)

Cassandra: CASSANDRA_WAIT_MESSAGE_TIMEOUT (ms)

If the Timeout is Reached:

- SNAPSHOT Mode: Delete the __t table.

- TRANSACTION Mode: Roll back the SQLite changes.

Log the error and skip the message.

Proceeds to the next message.

Note: if a command is resulting in 0 records affected (like update when the PK is not there, or delete of records that doesn’t exists) – No logs no stats, (for performance reasons)

## Background Sync Flow

If a Common table has a defined population, a COMMONAREA_TABLE_SYNC job is created to manage ongoing population.

Job UID: COMMONAREA_TABLE_SYNC_<SCHEMANAME>.<TABLENAME>

Job Activation: The job wakes up every BG_REF_SYNC_INTERVAL_SEC seconds (default is 5 minutes) and activates the population process according to its Sync Method.
* The job is created also if Sync Method=None, but will not be activated.

Once the population is activated, the fetched records are sent as one transaction to the Kafka topic, where they are consumed by all nodes. If the Truncate Before Sync property is set to true, the population will operate in SNAPSHOT mode.

Note:

The job wakes up every BG_REF_SYNC_INTERVAL_SEC minutes, so the defined Time Interval may shift by a few minutes.

# Built-in tables in each Common Schema

Each Common schema include the below built-in function:

_k2_delta_errors, _k2_transaction_infi – not in use

## _k2_objects_info

See Course 1 for details.

## _k2_read_pos

Stores the consumer's offset in Kafka and the timestamp of the latest update (updated only after a message has been fully processed).

This information is used for backup functionality, as detailed later in the document.

# common_local_trx

If your implementation requires the transaction to be applied immediately - for instance, if your logic needs to use the transaction data right after applying it within the same session—use the command: set common_local_trx=true;

- When common_local_trx is set to true:

- The session will open a transaction on the Common DB file and apply the DML commands immediately.

- Once commit is executed, the transaction will be rolled back locally and sent via Kafka to be consumed by all Fabric nodes, including the local one.

- When common_local_trx is set to false:
The updated data cannot be viewed until commit is performed, the transaction is sent via Kafka and the local Fabric node consume the commands and updates its local Common DB.

Important node:
When common_local_trx is set to true, the transaction locks the SQLite file until a rollback or commit is executed. During this time, the Kafka consumer for this table cannot proceed and remains on hold until the transaction is completed. Therefore, ensure that the transaction is closed as soon as possible.

# Fabric commands for Common table using population

### REF_SYNC

Start sync job for the specified common table(s).

ref_sync [LU_NAME='lu name'] [TABLES='ALL' or '<table 1,table 2,etc...>'] [FORCE=true/false];

- LU_NAME: Syncs all tables listed in the LUT Reference list property.

- FORCE: Determines whether to force data sync from the source (default: true).

The command sends a request to the COMMONAREA_TABLE_SYNC by updating job parameters in k2_jobs. Once a Fabric node picks up the job, the population process begins.

If ref_sync is executed while another ref_sync is already running, an error message will be displayed.

### REF_CANCEL

Cancels a running ref_sync activity.

ref_cancel TABLE_NAME=table1 ;

- If the job hasn’t started yet:

The ref_sync settings will be rolled back to cancel immediate execution (handled in k2_jobs).

If the job has already started:

A flag is raised to stop the population process. Once stopped, a rollback message is sent.

Any messages already in the internal topic will be consumed until the rollback message is reached.

If population already completed:

Using ref_status - check current offset of each node.

If any of the nodes reached the commit – ref_cancel cannot cancel the ref_sync.

Else – starting from the largest current offset found in the ref_status, find the first and last offsets of LONG transactions, waiting in Kafka.
*If one of the nodes already working on the population message, it’ll stop in the middle, rollback and skip

Send by jdbc to all the nodes, to skip all messages in this range.

Note: When using ref_cancel command, the population will not re-try automatically.

### REF_STATUS

Provides tables sync status for the specified reference tables across all nodes.

In addition, it provides the ‘Current Session Transaction’, i.e. the status of the latest transaction, executed on the table in the current session.

REF_STATUS [LU_NAME='lu name'] [TABLES='ALL' or '<table 1,table 2,etc...>'] [SCOPE='table' or 'population'];

REF_STATUS Results (SCOPE = 'table')

Table Name: The name of the reference table.

Node: The ID of the node

Status:

WAITING_FOR_SYNC:

The table is waiting to be synced for the first time - OR -

A REF_SYNC command was issued, but the job hasn’t started yet.

Looking at k2_objects_info: start_sync_time < request_time.

Note: The table is waiting to be synced for the first time, GET operations using this table will wait for synchronization.

IN_SYNC: Population started 
local k2_objects_info start_time time >end_sync_time
The table is in the process of being synced:

Syncing for the first time.

Sync triggered manually via REF_SYNC in the current session (all other sessions see IN_BACKGROUND_SYNC).

Looking at k2_objects_info:
If (start_sync_time > end_sync_time) and (start_sync_time > request_time).

IN_BACKGROUND_SYNC: 
The sync is running in the background, triggered by:

Another session invoking REF_SYNC.

The COMMONAREA_TABLE_SYNC is running

READY:
The table is fully synchronized.

READY_WITH_BACKLOG:
The table is fully synchronized. There is a Kafka/queue backlog (messages that are not related to the population trx).
Note: If REF_SYNC was triggered in the current session, GET operations can proceed without waiting.

Backlog: Number of pending messages in Kafka (main topic).

Offset: Current Kafka offset (taken directly from Kafka, not from _k2_read_pos).

Offset Duration (min): How long (in minutes) the current offset has been processing. Resets to 0 when the transaction completes.

Num of Messages: For long transactions, the number of commands processed in the current transaction.

Transaction ID: The ID of the currently processed transaction or snapshot.

Transaction Type: LONG_SNAPSHOT, SHORT_SNAPSHOT, SHORT_TRANSACTION, LONG_TRANSACTION, IDLE (no activity since the last restart).

Sub Status: Status of the write operation to the SQLite file.
Options: In process, Index rebuild (for snapshot messages), Done, or Failed.

- Current session transaction – population status (writing to Kafka)

Started: Transaction started but not committed.

Commit in progress: Commit is ongoing.

Completed: Commit successfully completed.

Rollback in progress: Rollback is ongoing.

Sync Error: An error occurred during sync.

Notes: Not in use.

REF_STATUS Results (SCOPE = population)

Shows k2_object_info from all the nodes

table_name: the name of the reference table

population: the name of the population querying the external sources

verified time: timestamp when last sync was verified

start/end sync time: Sync time start and end times

start/last write time: Write time of first and last message

next planned sync: Timestamp for next sync

sync error: error message

node: The ID of the node

notes: details – not in use

### REF_SYNC_WAIT

Used by a session to wait for the completion of a REF_SYNC command or an insert, delete, or update operation on a Common table within the same session.

REF_SYNC_WAIT [LU_NAME='lu name'] [TABLES='ALL' or '<table 1,table 2,etc...>'];

This command pauses the session until the sync process finishes, respecting the Sync Wait Timeout property configured in the Studio for the table. It should be executed after a REF_SYNC command or any insert, delete, or update operation on a Common table.

# CommonDB Backup

When adding a new node to Fabric cluster, or for any issue with Common Area on one of the fabric clusters, a backup file can be used to create the local Common Area DBs.

### REF_BACKUP

Use the following command to back up the Common Area schemas:

REF_BACKUP [SCHEMAS='ALL' or '[schema 1, schema 2, etc...]'];

The backup files are stored in the location specified by the DEFAULT_GLOBAL_STORAGE_TYPE parameter:

## LU Metadata files storage (project artifacts zip jar): SYSTEM_DB/S3/AZURE_BLOB_STORE/GCS/NFS (Default: SYSTEM_DB)

#DEFAULT_GLOBAL_STORAGE_TYPE=SYSTEM_DB

To store backups in a different location, add a new entry in config.ini:
[fabricdb].COMMONS_BACKUP_DEFAULT_STORAGE

Note: The backup files must be stored in a shared location accessible to all nodes.

Backup Process

During the backup, the database file undergoes vacuum and maintenance to optimize storage.

Backup files also retain the last offset consumed by the Kafka consumer. This ensures that any consumer using the backup can resume from the correct offset in Kafka.

### REF_BACKUP_DELETE

Deletes a backup of the specified Common schema(s) from the configured storage.

ref_backup_delete [SCHEMAS='ALL' or '<schema 1,schema 2,etc...>'

### REF_BACKUP_DOWNLOAD

Downloads a snapshot of the specified Common schema(s) from the configured storage to the specified destination path.

ref_backup_download [SCHEMAS='ALL' or '<schema 1,schema 2,etc...>'] DESTINATION='path name';

### Using Backup Files to Recover a Corrupted Common Area or Add a New Node to the Cluster

When a node starts (whether an existing node or a new one added to the cluster), Fabric performs the following steps automatically:

Initialize Consumers and Producers for each table

Check for Local Common Files:

If a local Common file exists:

Begin reading from the offset stored in k2_read_pos for each table.

If no local Common file exists:

Check if a backup is available. If so:

Download it to the local node and begin reading from the last offset in the backup.

If the offset in the backup is no longer available in Kafka, the backup is invalid and cannot be used.

If No Valid Backup Exists:

Request a full snapshot from another node.

A COMMONAREA_TABLE_REPLICATE job is created.

One of the nodes will pick up the job. If this node does not have a 'valid' Common table (i.e., its current offset is not available in Kafka), it will exit the job, allowing another node to take ownership. Otherwise, it will begin sending the snapshot to Kafka.

The snapshot is sent to Kafka with an optional transaction flag, and any node with an invalid table can consume it.

Best Practices and Recommendations

Manage Kafka and Snapshot Retention:
Ensure that the retention period for Kafka topics and Cassandra snapshots is longer than the backup interval; otherwise, the backup may become invalid since not all messages in Kafka, since the time of the backup, will still be there.

Cassandra Configuration:
Set the retention period using: [common_area_system_db_config].COMMONS_TABLE_TTL  
Default: 604800 seconds (7 days)

Kafka Configuration:
Adjust the retention settings in the Kafka server.properties file.

Daily Backups:

Schedule a job to back up all Common tables daily.
Note: when setting up the job interval to a very long period, the restore process can take longer to run (because more messages will need to be consumed from Kafka).

In your implementation, add a BroadwayJob Actor to the deploy.flow Broadway flow in the References LU that will run the REF_BACKUP command for all the relevant schemas. It will start the common tables backup job when deploying the References LU.

Before Adding a New Node:

Create a backup of all Common schemas.

Fixing a Corrupted Common DB on an Existing Node (Fabric 7.1+):

Create a backup from a node with a valid Common DB.

On the corrupted node:

Stop Fabric.

Delete the local Common file.

Restart Fabric.

### Fixing corrupted Common Area in Fabric versions prior to 7.1:

For Fabric versions prior to 7.0:

Sync one fabric node and stop it

Stop relevant nodes

Go to storage/common on the synced node, and make sure the .wal file doesn't exist or empty.
If not – run vacuum manually on sqlite3

Go to the relevant node’s storage\common and delete all files (3 at most)

Copy common.db from the synced node to relevant nodes

On relevant nodes, for Kafka consumer group named as node id - move offset to end.

Restart nodes.

For Fabric version 7.0:

Sync one fabric node and stop it

Stop relevant nodes

Go to storage/common on the synced node, and make sure the .wal file doesn't exist or empty.
If not – run vacuum manually on sqlite3

Go to the relevant node(s) storage\common and delete all files (depending on the number of schemas that you defined in Common DB)

Copy all the schemas of Common DB from the synced node to relevant nodes

Restart nodes.

# IIDFinder

# Vacuum & maintenance

Common tables operate in WAL (Write-Ahead Logging) mode, with Fabric periodically running vacuum and maintenance tasks between transactions.

If Fabric cannot perform maintenance due to high write activity, the WAL file may grow excessively. For a short-term solution, follow the steps below. For a long-term solution, investigate what prevents Fabric from running regular maintenance. Possible causes include:

High volume of updates on the schema – consider splitting the schema into multiple schemas.

Processes (jobs/flows) locking the file for extended periods.

Short-Term Solution for Large WAL Files:

Ensure no Snapshot transaction is running.

Stop Fabric – Fabric will attempt to run maintenance during shutdown.

If the WAL file remains large, manually run vacuum using sqlite3.

Restart Fabric.

# Delete Common Table-

A Common table can be deleted through Fabric Studio by removing it and deploying the changes.

If the DELETE_TOPICS_ON_DROP=true setting is enabled in config.ini, the Kafka topic associated with the dropped table will also be deleted.

When using managed Kafka – we cannot

Note:

Avoid dropping the Kafka topic if another client is using it.

In cloud, when destroying a space, Fabric is not deleting what was not created by it. For example: Topics, Cassandra tables, etc (and not the cloud manager)

# Additional Configurations

A PubSub Configuration interface type defines the Fabric connection to message provider (such as Apache Kafka or JMS) using the PubSub abstraction layer.

All the PubSub connection settings are defined in the [default_pubsub] section of config.ini and not in the interface. The [default_pubsub] section allows to define the connection settings in one location and apply them across various Fabric processes.

The only parameter included in the interface definition is the Config Section. It holds the name of the config.ini section where the connection settings are defined. By default, it is set to default_pubsub.

The [default_pubsub] section is also used by CDC and Common DB processes for the same purpose of connecting to Kafka.
When it is required to have different Kafka settings for Common DB, it can be done using the [common_area_pubsub] section. This section does not have to include all the parameters, but only those which should override the default section's settings.

[default_pubsub]

- TYPE=KAFKA - pubsub type can be one of [MEMORY, KAFKA, NO_OP, ERROR]

- MEMORY (default) - execute the message handling via an internal queue that runs on localhost. This type can only be used for debug purpose.

- NO_OP - do not send or receive the messages. This type can only be used for debug purpose.

- ERROR - simulate throwing an Unsupported Operation exception. This type can only be used for debug purpose.

- POLL_TIMEOUT=-1 - The timeout to wait for a new message. If the timeout elapses the collection will come to an end. If set to -1 the wait will be forever.

- MAX_POLL_RECORDS – Max number of records that the consumer reads from Kafka in each poll (default: 500). For performance optimization.

- TRANSACTION_MODE - Determines how the publisher handles transactions. Options include Async, Broker, and Ignore:

- Async Mode

- Description:

- Messages are aggregated in an internal queue and sent asynchronously to Kafka upon commit (or discarded on rollback).

- Key Points:

- No Guaranteed Delivery: If any message fails, the remaining messages are still sent.

- The commit command raises an exception for failed messages. Check the logs for details.

- Faster than Broker or Ignore modes.

- Recommended for scenarios where transactions are not critical.

- Queue Size:

- Controlled by PUB_QUEUE_LIMIT (a hidden parameter in config.ini).

- Broker Mode

- Description:

- Fully supports transactions, sending messages directly to Kafka without using an internal queue.

- Key Points:

- On commit, the transaction is committed to Kafka.

- On rollback, the transaction is rolled back in Kafka as well.

- Transaction size is limited only by Kafka, with no additional restrictions from Fabric.

- Suitable for scenarios requiring transaction guarantees.

- Ignore Mode

- Description:

- Messages are sent synchronously, one by one, and committed immediately.

- Key Points:

- No need to execute commit in Fabric.

- Transactions are not supported, and each message is handled individually.

- Recommended for scenarios where transaction handling is unnecessary.

- Async

- Aggregates the messages (in internal queue) and send them (in async mode) to Kafka only on commit (or discard on rollback)

- Async mode doesn't guarantee that all messages would be committed. In case any message fails, the rest of the messages will still be sent. The commit command will raise exception. To view the errors, look at the logs.

- Advantage: faster than Broker or Ignore. To be used when transaction is not needed.

- Queue size: PUB_QUEUE_LIMIT (hidden parameter in config.ini)

- Broker

- Support transaction

- Messages are sent directly to Kafka (no internal queue)

- When user is executing "commit" - commit is executed on Kafka. Rollback - will rollback Kafka too

- The transaction can be as big as allowed by Kafka - no limitations from Fabric side.

- Ignore

- Messages are sent synchronously. Commit is done one by one.

- No need to execute commit in Fabric.

- To be used when transaction is not needed.

[common_area_pubsub] - override default_pubsub for Common

[common_area_config]

- Error handling:

- CONSUMER_IDLE_TIME=60000 – Maximum timeout (in milliseconds) to wait for the next snapshot in Kafka. If exceeded, an IDLE_TIMEOUT error will be thrown.

- CASSANDRA_WAIT_MESSAGE_TIMEOUT=60000 - Maximum timeout (in milliseconds) to wait for the next snapshot in Cassandra. If exceeded, an IDLE_TIMEOUT error will be thrown.

- OPERATION_RETRIES_COUNT=-1 - Maximum retries for common SQLite errors (e.g., SQLITE_BUSY, SQLITE_LOCKED). -1 indicates unlimited retries.

- OPERATION_RETRIES_WAIT_MS=1000 – Interval (in milliseconds) between each retry attempt defined by OPERATION_RETRIES_COUNT.

- SYNC_JOBS_AFFINITY - Affinity for common sync jobs (comma-separated). Default: no affinity.

- DELETE_TOPICS_ON_DROP - If set to true, removes all unused topics upon a drop operation.

- TRANSACTION_BULK_SIZE=100 – transaction bulk size

- MAX_TRANSACTIONS_COMMIT=100 – Specifies when to commit to SQLite during snapshot message processing

- MESSAGES_INTERNAL_BROKER_TYPE=PUB_SUB - Defines the storage for long transaction distribution (SYSTEM_DB or PUB_SUB).

- MIN_TRANSACTION_LONG For Cassandra: Ensures efficient transaction handling by creating snapshots in Cassandra for smaller tables instead of sending them over Kafka.

- INTERNAL_QUEUE_SIZE=500 – not in use

- PROCESS_UPDATE_MESSAGE_RETRIES_COUNT=3 – not in use

[common_area_system_db_config]

- COMMONS_SNAP_TABLE=snapshots – Specifies the name of the Cassandra table used for snapshots.

- COMMONS_TABLE_TTL=604800 - Defines the TTL (time-to-live) for the snapshot table in seconds. Default is one week

- COMMONS_KEYSPACE= Specifies the Cassandra keyspace for the snapshots table.

[common_area_memory_queues_config] - not in use

[common_area_kafka_producer] - not in use – replaced by the pub_sub

# JMX Metrics

Under the ‘transactions’ section:

- commonUpdate: Tracks the number of commands (insert, update, delete) executed for each table.

- commonTransactions: Tracks the number of transactions executed for each table.

- commonSnapshot: Tracks the number of snapshots executed for each table.

- commonSnapshotDuration: Measures the duration of full snapshot processing for each table.

- commonMessagesBulkProcessDuration: Measures the processing time for messages that exceed the bulk size.

- commonKafkaRead:  - NOT in used

- commonKafkaCommit: - NOT in used

# Design Considerations

## ES VS REF VS SYSTEM_DB((No)relational DB)

|  | ES | REF | Postgres | Cass |
| --- | --- | --- | --- | --- |
| RT | X (CDC) | X | V | V |
| Massive Deletes (using a single statement) | X | V | V | X |
| High transaction volumes | V | X | V | V |
| FTS with Large Volumes | V | X | X | X |
| FTS with Small to Medium Volumes | X | V | X | X |
| Fuzzy search | V | X | X | X |
|  |  |  |  |  |

*Note : above table does not refer to the complexity of each of the applications such as implementation installation maintenance and Cost.

# Performance Considerations

When creating Common tables, consider the following performance optimizations:

- Adjust bulk settings:

- Adjust TRANSACTION_BULK_SIZE value based on project needs. For large tables with high write activity, increase this parameter.
Note: Kafka has a limitation on the maximum message length.

- Adjust MIN_TRANSACTION_LONG when working with Cassandra. Define this parameter for smaller tables to ensure efficient transaction handling.

- Adjusting MAX_TRANSACTIONS_COMMIT: Specifies when to commit to SQLite during snapshot message processing.
Tradeoff: A higher value improves performance but increases the risk of failures and retries for the entire transaction.

- Use Truncate Before Sync: Enable this property whenever possible for better synchronization efficiency.

- Schema Splitting: Distribute tables across schemas to reduce locking and improve performance, as explained earlier.

- If the GET process updates a Common table, ensure updates occur only when there are actual changes to avoid unnecessary writes.

# Best Practices

- Common Table Population

- Use IIDFinder for Updates instead of resyncing the entire dataset for each update.

- Use Upsert Mode if re-processing transactions may be required.

- For Full Resyncs, set Truncate Before Sync to True, to enable Snapshots management

- Optimize Sync Policy: Avoid short sync intervals to prevent excessive sync cycles. For example: If the source table updates once daily, set the sync interval accordingly.

- Fetching Data from Common Tables

- Use ludb.fetch or fabric.fetch for Queries. Avoid using FabricDB.fetch due to performance overhead from TCP communication.

- Optimize Index Usage: Create indexes only when necessary to avoid adding unnecessary complexity.

- Use FTS for Large Text Queries: For querying large text collections (e.g., articles, reports, chats), consider using SQLite’s built-in Full-Text Search (FTS) feature.
Trade-offs: FTS may reduce insert and update performance and increase storage requirements.
Optimization Tip: split the table into two:

- FTS Table: Contains primary key fields and columns needed for search.

- Secondary Table: A regular table with primary key fields and other columns.

- Transaction Management

- Prefer Kafka for Long Transactions. Kafka is better suited for long transactions than Cassandra.

- Ensure Message Length is Within Kafka Limits. Avoid sending overly long messages that exceed Kafka's size limitations.

- Schedule Long Sync Transactions Strategically. Execute long sync transactions during off-peak hours (low API volumes) to minimize server performance impact.

# Helpful Kafka commands

/opt/apps/kafka/confluent-7.2.1/bin$

./kafka-consumer-groups --bootstrap-server localhost:9093 --all-groups --describe

./kafka-console-consumer --bootstrap-server localhost:9093 --topic <TOPIC_NAME> --from-beginning

/kafka-console-consumer --bootstrap-server localhost:9093 --list
