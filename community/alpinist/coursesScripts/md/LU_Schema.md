# LU_Schema

LU Schema course

# Introduction

What is an LUI?

An LUI - Logical Unit Instance - represents a standalone SQLite database housing the data for a single instance. Within this database, tables that contain data are structured according to the LUI Schema. The populations associated with each table are tasked with fetching data into their respective tables.

Moreover, an LUI schema has the capability to define Java functions that can be applied to the data, including triggers, enrichment functions, events, and more (these will be discussed in course #2).

Each LUI database is stored as an individual SQLite file within the system_db (Cassandra, S3, or Postgres), as a Blob.

### LUT – Logical Unit Type

The LUT represents a "Business Entity" within a system, which can vary depending on the core business. Common examples of business entities include customer, product, supplier, loan, order, etc.

As part of the LUT definition, the LUT Schema is created, which acts as a “template” for an SQLite database that will store the data for each instance of the business entity (e.g., a Customer). This database template includes tables, columns, foreign keys, primary keys, indexes, and other database structures.

In addition to the database structure, the LU Schema also defines the functionality required to populate and manage the data within the database.

### LUI – Logical Unit Instance

An LUI is a specific instance of a business entity (e.g., a customer with ID 1234). It consists of a micro SQLite database file that is built from the LUT Schema template and contains data for that individual instance.

An LUI is created for each instance (e.g., each customer) when the GET process is run for the first time. During this process:

- The SQLite database is generated using the metadata and structure defined in the LUT Schema.

- Data is fetched from source systems and inserted into the corresponding tables, following the rules defined in the LUT Schema.

- The LUI database is stored as an individual SQLite file in the System Database (e.g., Cassandra, S3, or Postgres) as a blob.

Example

Assume Company ABC has 35 million customers:

- LUT: Customer (defines the structure for storing customer data)

- LUI: One instance of the Customer LUT—an individual SQLite database containing data for a specific customer.

Once GET process is executed for every customer ID in the system, Fabric will store 35 million Customer LUIs (SQLite files), each saved as a blob in the System Database.

### Subsequent GET Process:

After the LUI is created, any future GET execution retrieves the LUI from the System Database, converts it from a blob back into an SQLite database, and makes it available for reading or updating with new data.

### Detailed Functionality Defined in the LU Schema:

- Data Fetch Logic:

- Table Population: Specifies what data to fetch from the source and how it should be inserted into each table.

- Sync Method: Determines when the data fetching process should be triggered.

- Delete Mode: Defines how existing data is handled when new data is fetched from the source.

- Additional Logic:

- Trigger Functions: Executed on every insert, update, or delete operation performed on a table.

- Enrichment Functions: Run after all data populations are completed.

- Event Functions: Executed based on the success or failure of the GET process.

- Additional Properties:

- Sync Timeout: Specifies the maximum allowed time for the GET process before triggering a timeout exception.

- Storage: Defines which database will store the SQLite file.

- Other Properties as needed.

Depending on the Sync Mode (Sync ON, Sync FORCE, or Sync OFF), this functionality may or may not re-execute during each GET process for the instance.

## LUI Life-cycle

#### LUI Creation:

- An LUI is created upon the first GET request. During this process, populations are triggered in the source system to retrieve the instance data, which is then stored in the SQLite database. The resulting SQLite file is saved in both the system_db and the cache.

- If no data is found in the source system, the LUI creation will fail, resulting in an error.

#### LUI Update:

During each GET request in a Sync ON operation, the data in the SQLite database can be updated according to the specified logic. This may involve re-fetching data from the source system or executing Data Manipulation Language (DML) commands directly on the SQLite database.

After the GET operation is completed, any changes are committed to the SQLite file, which is then re-saved in the system_db.

#### LUI Deletion:

When an LUI is deleted, the associated SQLite file is removed from both the system_db and the cache.

An LUI can be deleted under the following conditions: when a Time-To-Live (TTL) parameter is set and expires, when the LUI is empty and flagged for automatic deletion, or when a user explicitly issues a DELETE command.

# How does it work?

## What happens when we execute a GET command?

Get Customer.123:

- Validate user permissions for the LUI.

- Check for any attached MDBs in the session that cannot be released due to an ongoing transaction (within the same LU type). If found, raise an error: "Attached LU cannot be detached while in transaction."

- Extract the SQLite from storage. If not found - start from empty db.

- Decompress the file.

- Decrypt the file if encryption is applied.

- Compare the schema to the LU type definition for any necessary upgrades and implement the schema changes accordingly. If the LUI is new - create the schema.

- Attach the SQLite file and lock it for write.

- If another GET operation is underway for the same instance, on the same node, the current thread will wait MDB_ATTACH_TIMEOUT.

- If timeout exceeds - throw an error.

- Begin syncing the LUI by executing table populations.

- Execute all populations and enrichment functions. Each population establishes connections to its source DB as required (utilizing the connection pool).

- Update k2_objects_info after each population.

- If LUT sync timeout exceed – throw exception

- Commit changes to the SQLite.

- Encrypt if needed.

- Apply compression.

- Save the SQLite back into the storage.

- Perform a resource cleanup.

- Note: Detach occurs only upon

- Execution of a Release command

- When a new instance ID (from the same LU type) needs to be attached.

- When the Fabric session ends

### LUI Storage

The LUI storage defines the location where the SQLite file is stored. It can be configured by using the ‘Storage’ property of the LU Schema.

#### Storage Property

Available options:

- Default - as defined in Config.ini.[fabricdb],MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE.
Options:

- SYSTEM_DB - as defined in config.ini, [system_db] section.
Options: SQLITE, POSTGRESQL, CASSANDRA.

- V8.2 - SYSTEM_DB_DEFAULT_SQLITE_PATH was added for SQLITE.
Default: ${FABRIC_HOME}/storage/systemdb

- S3 - stores LU instances in the AWS S3 Storage, as defined in config.ini, [s3_storage] section.

- AZURE_BLOB_STORE - stores LU instances in the Azure Blob Store Storage, as defined in config.ini, [azure_blob_storage] section.

- GCS - stores in the Google Cloud Storage, as defined in config.ini, [gcs_storage].

- NFS - shared storage. 
Add config.ini.[fabricdb]. MDB_DEFAULT_STORAGE_PATH entry to set the directory
The default location, if entry the is not provided is: ${FABRIC_HOME}/storage

- NONE - LUI is not stored (working only in memory, no save).

- Cassandra - config.ini, [default_session].

- S3 - stores LU instances in the AWS S3 Storage, as defined in config.ini, [s3_storage] section.

- AZURE_BLOB_STORE - stores LU instances in the Azure Blob Store Storage, as defined in config.ini, [azure_blob_storage] section.

- GCS - stores in the Google Cloud Storage, as defined in config.ini, [gcs_storage].

- NONE - LUI is not stored (working only in memory, no save).

Note:
Using cloud storage for LUIs is preferable over Cassandra due to several reasons:

- Frequent upserts of the LUI in Cassandra can lead to a high load due to the frequent creation of SSTables, which in turn requires constant compaction processes to run.

- Big LUIs are stored in chunks due to a Cassandra limitation, resulting in slower saving and fetching processes, compared to cloud storage, which remains unaffected by blob size.

However, it is important to note that cloud storage incurs higher costs compared to Cassandra.

SYSTEM_DB
Fabric System Database is used by Fabric’s internal processes to monitor, secure, control, configure, audit and operate the application.

Fabric supports several types of databases as the System Database storage, as described below:

- NoSQL distributed database, such as Cassandra DB (others can be supported)

- Pros:

- Scalable

- Distributed

- Built-in TTL mechanism on row level.

- If Cassandra is used as a MicroDB storage, there is no need to introduce additional DBs.

- Managed services (such as AWS Keyspaces or Astra) are supported.

- Supported by the iidFinder solution.

- Built-in mechanism for managing parallel threads during a bulk instance loading.

- Cons:

- Consistency

- Not easy to operate and maintain.

- Relational database, such as PostgreSQL (easier than NoSql to add)

- Pros:

- Consistency

- Compliance with services such as Cloud Spanner, AlloyDB.

- Easy to maintain.

- Cons:

- Single point of failure

- Not supported by the iidFinder solution.

- SQLite

- Pros:

- Development and single-node environments.

Transfer LUIs to a New Storage:

To move LUIs between storages, use the following config.ini settings:
Set config.ini.[fabricdb]:

- MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=TRANSITION

- STORAGE_TRANSITION_FROM=CASSANDRA

- STORAGE_fTRANSITION_TO=S3

LUIs transfer process:

- On GET, when trying to bring data from an LUI, Fabric first checks (i.e., reads) STORAGE_TRANSITION_TO for data. If Fabric cannot find data there, it then reads STORAGE_TRANSITION_FROM for data.

- On save, Fabric saves data in STORAGE_TRANSITION_TO. If the LUI was found in STORAGE_TRANSITION_FROM during the read process, then after saving the data in STORAGE_TRANSITION_TO, Fabric deletes the data that was found in STORAGE_TRANSITION_FROM.

- If STORAGE_TRANSITION_FROM is set to blank, the system will stop reading STORAGE_TRANSITION_FROM (no need for a restart).

#### Code

Get each LU storage property:

fabric>list lut storage='Y';

|LU_NAME |STORAGE|Project Version|

+--------+-------+---------------+

|Customer|Default|               |

#### Entity Table

When the LU storage is set to Cassandra, the LUIs are stored, as a BLOB (Binary Large Object), in a table named “Entity” within the k2view_[LUT_name]_[cluster id if exists] keyspace.

Entity table:

- Id = iid

- Batch_id - see Big LUIs section

- Chunks_count - see Big LUIs section

- Data – blob of the LUI SQLite file

- Key_desc_id - see Big LUIs section

- Schema_hash - Schema metadata hash. Used as indication to whether a schema upgrade is needed.

- Sync_version - the version of the LUI

Entity table best practice:

- To count the number of instances, use Cassandra COPY command (using consistency ALL)

- In case resync is needed for the entire population, do not use ‘select id from entity’ for the batch command. That will bring the IIDs (instance IDs) by partitions and therefore the load on the cluster will not be distributed.
Instead, use the ‘reverse migration’ logic: assign UUID for each IID (instance IDs), create a new Cassandra table with the UUID as the partition key, and select all the IIDs from this table.

- When running the command: 
‘batch LU from fabric fabric_command="sync_instance LU.?" with ASYNC=true’
And the LUIs storage is set to Cassandra, Fabric will use ‘select id from entity’ in order to fetch the list of IIDs. If your entity table is big, consider using reverse migration apprach instead.

#### LUI Compression

Before storing the LUI in the storage, Fabric compresses the SQLite file, to use a smaller space and for a faster extract.
The compression can be changed in config.ini.[fabricdb]. MDB_DEFAULT_STORAGE_COMPRESSION
Default is LZ4. Other options: GZIP/NONE (no compression).

GZIP - result is 25% of the original file. 
LZ4 has better compression but is a bit slower.

#### LUI Vacuum

As an SQLite database, the below scenarios may occur:

- When content is deleted, it is not usually erased but rather the space used to hold the content is marked as being available for a reuse.
When a large amount of data is deleted from the database file, it leaves behind an empty space, or ‘free’ database pages. This means the database file might be larger than strictly necessary.

- Frequent inserts, updates, and deletes can cause the database file to become fragmented.

Running VACUUM operation to reclaims the ‘free’ space, fixes the fragmentations and reduces the size of the database file.

Fabric is running vacuum before saving the LUI back to the storage if it’s exceeding MDB_VACUUM_THRESHOLD_KB bytes. This parameter is set to –1 by default which means that the LUIs are not undergoing vacuum. In case of an issue, and after consulting with R&D, you can force reclaim free space before storing the mdb using config.ini.[fabricdb].MDB_VACUUM_THRESHOLD_KB: -1 is off, 0 is always.

### LUI Version

Each time the LUI is saved back to the storage, it is assigned with a new version number. The version number is constructed from the timestamp when the file is saved back.

When the storage is Cassandra, the version is kept in the Entity table.
When it is stored in cloud, it is kept as a property of the file (tag).

The version is used by Fabric in the below cases:

- Validate LUI override
When the LUI is retrieved from the storage, Fabric retains the current version. Prior to saving the updated LUI back into storage, it verifies that the version in storage matches the initially retrieved version. If not, indicating that it has been updated by another process, Fabric may throw an exception to prevent overwriting changes made by the other process (depending on OPTIMISTIC_LOCKING configuration – see ?? section).

- Validate cache
Before retrieving LUI from cache, Fabric validates that the LUI is up-to-date using the cache version comparing to the version in the storage (see LUI Cache section).

Code:

fabric>get Customer.100;
fabric>select version(“Customer”);

### LUI Cache

To optimize the LUI retrieval process, Fabric uses a cache mechanism, which enables a faster loading of an instance into the memory.
The cache location is defined in the Cache Location property of the LUI Schema.

#### Cache Location Property

Available options:

- Default

- As configured in config.ini.[fabricdb]. MDB_DEFAULT_CACHE_PATH

- Default is /dev/shm/fdb_cache. If this path does not exist, ${FABRIC_HOME}/storage/fdb_cache/ will be used.

- Under the default path, a folder is created per each LUT (LU Type).

- The /dev/shm directory is a special directory on Linux systems that is used for storing temporary files. The files in /dev/shm are stored in memory, rather than on disk, which makes them much faster to access (disk is mounted directly to the Memory)

- Storage:
MDB files will be stored in ${FABRIC_STORAGE}/storage/fdb_cache/. If this location does not exist, store the cache in ${FABRIC_HOME}/storage/fdb_cache/.

Note: Changing the default path in config.ini will results in ignoring this setting for all the LUTs.

#### Cache Storage Size

The cache storage size is restricted and is set per LUT in config.ini [fabricdb] as MDB_DEFAULT_SCHEMA_CACHE_SIZE.

Once the cache storage reaches the specified size:

- Inactive files are removed from the cache by LRU (Least Recently Used) order (first the new file is added and then the LRU file will is removed).
The LRU file is identified using in-memory data structure.

- If the size of the LUI file exceeds the remaining space within our specified limitations, it will be retained in the cache, potentially exceeding the maximum size defined, but it will not be stored in the directory once the session ends.

- If the file size exceeds the total memory, Fabric will crash.

LUI cache best practice:

- Always prioritize using the default path (/dev/shm) to optimize performance.

- For very large LUIs that exceed the cache size, consider configuring the cache to utilize another disk space.

Note:

- The cache is stateless - cached files are deleted upon a Fabric restart.

- The cache is utilized best when the same LUI is accessed multiple times within a short period. Otherwise, it is possible for the file to be removed from the cache due to other files utilizing it.

#### Fabric Usage of the LUI Cache

- Fabric is keeping a map in the memory to hold the list of instances IIDs that their MDB files are currently cached. The map also contains the LUI version and the Schema Hash.

- When a new GET is invoked, Fabric checks in the map if the IID is in the cached files.

- Prior to utilizing the cached file, Fabric validates whether it is the most recent version by querying the storage for MDB with the same IID but with version that is greater than cached one. If MDB is found – the cached file will be replaced with the newer version.

Version 8.0.0:

CACHE_TYPE Configuration Parameter

Controls optimizations to Fabric’s caching mechanism. Instead of writing LUI data into local SQLite files, Fabric can store the LU Storage directly in memory.

Cache Types

FILES_CACHE – Uses the Storage property (default behavior).

FILES_NO_CACHE – Each Fabric session uses its own SQLite cache file.
Pros: Prevents lock contention when multiple sessions request the same LUI.
Cons: Each session loads its own SQLite file, which may increase storage and I/O costs (for example, in S3).

MEMORY_NO_CACHE – Ignores the Storage property and keeps all cache data in memory (no files created).
Pros: Removes the need for ATTACH/DETACH operations. Since the attach operation is limited to one execution at a time per node, this mode eliminates that bottleneck—especially when the LUI sync time is very short and many LUIs are fetched on the same node concurrently.
Cons: Requires sufficient CPU and memory resources, as LUI storage is held in memory and may limit resources for other processes. Also note that the in-memory database size is limited to 2 GB.

### LUI Transaction

The Sync process is managed as a single transaction that starts at the beginning of the Sync process and finishes at its end. If the Sync is completed successfully, the data is committed to the Fabric database. However, if an error occurs at any stage during the Sync process, the transaction is rolled back.

The sync transaction can be managed from outside the Sync process, when opening it by the calling session:

Db ci = db("fabric");
ci.beginTransaction();
ci.execute("get Customer.'" + IID + "'");
String SQL = "INSERT into CONTRACT_	COPY (CUSTOMER_ID,CONTRACT_ID,CONTRACT_REF_ID) values (?, ?, ?)";
Object[] params = new Object[]{IID, contrID, contrRefID};
ci.execute(SQL, params);
ci.execute("commit");

Note:

- In case of using iidFinder, delta tansactions will be deleted even if rollback was executed.

- There is no difference between db("fabric") and fabric().
From R&D’s code:
public Db fabric() {
	return db(FABRIC_INTERFACE);
 } 

Meaning, the code below is the same as the example above, and Db ci = db("fabric");
is NOT opening a new connection:

fabric().beginTransaction();
 fabric().execute("get Customer.'" + IID + "'");
 String SQL = "INSERT into CONTRACT_COPY (CUSTOMER_ID,CONTRACT_ID,CONTRACT_REF_ID) values (?, ?, ?)";
 Object[] params = new Object[]{IID, contrID, contrRefID};
 fabric().execute(SQL, params);
 fabric().execute("commit");

#### Ignore source exception

In case the source system cannot be accessed, you may prefer to roll back the Sync without getting an error (exception). 
In that case, use the ignore_source_exception command set to ‘true’ on the session level.
Note: If the instance is not yet in Fabric, the GET command will throw an exception.

### LUI Sync Timeout

By default, LUI sync time is not time-limited. Nevertheless, it is recommended to limit the sync time to avoid bottlenecks and stuck instances.

If a timeout is set and the sync exceeds the predefined timeout, Fabric rollbacks the changes and throws the following exception: Timeout occurred.

A sync timeout can be defined either per session or at an LUT schema level:

- LU Schema level - set the timeout for all the instances of the LUT.

- Session level - override the LUT setting on a session level, using the ‘set sync_timeout’ command.

Design best practice:

For instances that exceed the timeout, it is recommended to have a retry process (log the failure and create a retry process). The retry process can dynamically adjust the sync timeout at the session level using the set sync_timeout command.

Note: When creating a different process, consider the limitations described in the Parallel GETs section.

### Schema Changes

Upon modifying the LU Schema metadata, every existing LUI in Fabric must undergo a schema upgrade process.

With each GET operation, Fabric verifies whether the LUI requires a schema upgrade by comparing the schema_hash property of the LUI with the latest deployed schema hash. If an upgrade is required, Fabric compares the LUI schema with the latest schema and proceeds with the upgrade process.

During the Schema upgrade process, populations will run automatically on sync ON/Force (depending on the Sync policy), for:

- A new added table.

- A table with a newly added column.

- A table with a new link connecting to a parent table.

- A table whose link to a parent table was removed

- A table with newly added/removed index

SQLite doesn’t support altering a column type. Therefore, when a column type was changed, Fabric performs the below steps:

- Creates a temporary table within the LUI.

- Copies the data from the original table to the temporary table.

- Deletes the original table.

- Renames the temporary table to match the original table's name.

Note:

- Changing population logic alone does not automatically activate it. To ensure the population runs after a schema upgrade deployment, you can:

- Develop custom logic to activate the population, using the isStructureChanged() built-in function to identify the scenario.

- Deploy the change using Force Upgrade Post Deploy, after which the Sync mode is automatically set to FORCE during the first sync of each LUI.
Note: even after unchecking this option, LUIs will keep syncing in FORCE on their first sync.

- A schema change also impacts GET in Sync OFF. The schema change logic runs, but since the LUI is not saved to Cassandra, the changes do not take effect until the first Sync ON.

- Populations are automatically activated for tables whose structure has changed, following the Sync policy. However, it's important to note that populations of child tables are not triggered automatically.
NOTE: You can identify schema change of a parent table using the built-in isStructureChanged() function. This function returns true/false per table. Therefore, when using this function, isStructureChanged() will return true on the parent table, not on the child. You can raise a session global on the parent population and use it in the child decision function.

### LUI Encryption

Fabric offers a built-in functionality for encrypting either the entire LUI or specific data within the LUI (like PII data).

Best practice:

- Do not encrypt the LUI if not needed. Encrypt and decrypt add time to the Sync duration. Instead, if needed, you can encrypt only the required data inside the LUI.

- If the LUI encryption is done before the LUI is compressed, the LUI size increases.

More details are provided in the Security course.

### Pools & Cache

- Fabric pool
Connections to Fabric are managed using a pool.

- Pool size: config.ini. [fabricdb].MDB_CONTEXT_POOL_SIZE=200 (size is fixed, no minimum or maximum).
The pool’s connections are established once Fabric is starting.

- Timeout to wait on connection: config.ini. [fabricdb]. MDB_CONTEXT_POOL_GET_TIMEOUT_MILLIS after which exception will be thrown.

- Note: When setting MDB_CONTEXT_POOL_SIZE =0:

- There will be no limitation to the number of connections to Fabric.

- Connection to Fabric will be lazy - will be opened on request and SQLite context will be discarded after each use.

- There might be a performance impact. Tune carefully and test before changing the parameter.

Use case: Specific scenarios where numerous connections need to be temporarily open, without increasing the pool size in order to avoid excessive resource and memory usage.

- Broadway pool
Fabric keeps a pool of compiled Broadway flows per lu/flow.
The size of the pool is set in config.ini. [fabricdb]. BROADWAY_LU_POOL_SIZE. Default is 200 (per node). If more flows are used in parallel, they will be parsed and disposed of after use.

- Tune this parameter in case you are execute a Broadway flow more than 200 times in parallel and observe performance issues.

- Prepare statements cache
A Fabric session keeps a cache for the prepared statement for LUIs (MDB context).
The cache is kept per Fabric session (each connection in MDB_CONTEXT_POOL_SIZE)
The max size of the cache is set in config.ini. [fabricdb]. MDB_PREPARED_STATEMENT_CACHE_LIMIT. Default is 200

If a cache reaches the limit, a warning message will appear in logs:
FabricDB Prepared Statement Cache limit reached 200
As a workaround, and until issue is found, you can set the parameter to 0 to avoid caching.

- Coding best practice:

- Use Non-Bind variables in an SQL statement only when the values are constant. In any other case, use a Prepare statement and send the values as parameters to the SQL statement.

- NOTE: In Fabric 6.2 and above, binding is supported for all Fabric commands.

## Parallel Sync of the Same Instance

### Parallel GETs of the Same Instance on the Same Fabric Node

Running GET with Sync ON fetches the MDB file to the cache storage and performs a write lock of the SQLite database on attach (open a transaction).

Sequential GET on the same node for the same instance are trying to use the same MDB file. Therefore:

- If Running in Sync ON - will not be able to open a transaction as the file is already in an exclusive lock. Therefore, it will wait until the locking is released (even if no changes are required).

- If Running in Sync OFF - will wait on read if LUI is inside a transaction.

To improve the response time of multiple GET LUI requests on the same LUI and Fabric node, Fabric supports a time window on which the sequential GETs in Sync ON will behave like Sync OFF (populations will not run) and the cache validation, that the MDB is up-to-date will will not be performed.

To activate the time window, set the SYNC_PROTECTION in config.ini as follows:

- The default value is zero. When Sync is set to ON, Fabric implements the Sync only on the first request. The following GETs will be treated as Sync OFF until the first Sync has been completed.

- If this parameter is set to -1, Sync ON Protection is disabled and Fabric implements the Sync on each request. All requests have Sync set to ON in this case.

- This parameter can be set in milliseconds. In such case, each SYNC activated from the time in which the first Sync ON has started, and until the defined milliseconds setting has passed, will be treated as Sync OFF.

SYNC_PROTECTION can be disabled on the session level using the SET SYNC_PROTECTION=off command.

Time to wait for the MDB to be released can be defined in config.ini MDB_ATTACH_TIMEOUT parameter. The default is 10000.

Fabric 8 will introduce a new mechanism utilizing a write-ahead log (WAL) file on the database file to manage transactions. This will allow read operations to be performed while the file is in transaction.

### Parallel GETs of the Same Instance on different Fabric Nodes

In case multiple GETs are running concurrently on the same LUI on 2 different nodes, the locking mechanism is not enforced because each node operates on its own cached file.
As a result, it is possible for both nodes to attempt to update the same instance at the same time. Therefore, when storing the MDB file back to the storage, the changes made by the latest node may override those made by the first node.

To avoid overriding data, use the optimistic locking mechanism configured in config.ini.[system_db_entity_storage].OPTIMISTIC_LOCKING as follows:

- NONE (default). The latest transaction overrides the LUI (Instance ID).

- QUORUM. The latest transaction fails (the commit of the first sync requires a quorum).

- LOCAL QUORUM. The latest transaction fails (the commit of the first sync requires local quorum on the DC (Data Center)).

When employing optimistic locking, an error will be raised during the process of saving the LUI back to the storage if the LUI version has changed since it was first fetched.

Note:

- When using gcp or azure as the entity storage, configure VALIDATE_REMOTE_VERSION=true in [gcs_storage] or in [azure_blob_storage] instead of the [system_db_entity_storage].OPTIMISTIC_LOCKIN parameter.

- V8.2 - OPTIMISTIC_LOCKING functionality is supported for S3.

### Parallel GETs Design Considerations

- Processes that sync instances should ensure that the same instance is getting synced on the same node, to avoid syncing the same LUI on 2 different nodes at the same time.
Other processes such as APIs, jobs, or flows should use the LUI in Sync OFF When possible.

- To avoid data override, set OPTIMISTIC_LOCKING with either 'QUORUM' or 'LOCAL_QUORUM'. This approach is recommended when dealing with SOR data or when running simultaneous syncing processes of the same LUI on different nodes (for example, Sync FORCE on first node and Sync ON on the second).

# Populations

## What is a population?

Data is retrieved from the source and inserted into each LUI table using populations. Each population specifies the source table from which to extract data and, if necessary, can incorporate data transformation logic.

Populations properties:

- Sync Methos – define when the population should run

- Population Mode – define the logic to apply the data (records) in the LU table

- Delete Mode – define the logic to delete the records from the LU table

Note: On cloud version, the above 2 properties moved to the table level, and applied for all the populations defined for this table.

## Sync Methods

Set WHEN a population should run:

- None: The population never runs unless it's the first sync or Sync FORCE is applied.

- Time Interval: The population runs only if the time elapsed since the last run exceeds the duration defined as “Perform every.”

- Decision Function: The population executes if the decision function returns true.

- Inherited: The population follows the Sync Methods defined in the table. If the table's Sync Method is also inherited, the Schema Sync Method will apply.

Note:

- The sync methods listed above are relevant only when Sync is ON.

- If Sync is OFF, populations will never run, regardless of the Sync Method set.

- If Sync FORCE is applied, populations will always run regardless of the Sync Method set, except for the decision function, which will still execute and determine whether to run or not.

- The first sync behaves like Sync FORCE.

- Populations will run according to their sync methods, even if the parent table hasn’t run.

## Table Population Mode

Table population mode controls the operation that will be executed on the LU table for each row fetched from source.

| Population Mode | Description |
| --- | --- |
| INSERT | Each record extracted from the source is inserted into the LU table using the INSERT operation. |
| UPSERT | Executing INSERT ON CONFLICT(PK) UPDATE operation: if a record extracted from the source does not exist in the LU table (based on primary key values), insert is performed. Otherwise, the record is updated. Older versions used INSERT OR REPLACE which invoked delete & insert, and therefore sent 2 CDC messages |
| UPDATE | Update records that are fetched from source. Used to update specific columns (for example – calculated column). The population is then connected only to the relevant columns. |
| DELETE | All records will be deleted from the LU table. For example – delete records from Target DB in TDM system |

Note:

- It is recommended to set up ‘Insert’ mode on the 1st population.

- Table population ‘Update/Delete’ mode - when using ‘Update’ mode, make sure the tables’ key fields are being marked. Otherwise, all records will be updated on each iteration. As a result, all records will be updated with the last iteration.

## Truncate before sync/Delete Mode

The truncate before sync property of a population defines the policy to delete existing LU table’s records prior to executing the population. Two available options:

- True – delete all the table’s records.

- False – do not delete any record.

Starting Fabric 6.5.9 this property was replaces with the Delete Mode property, and a new option was introduced:

- ALL (=true) – entire LU table is truncated before any population is executed.

- OFF (=false) – data in the LU table remains untouched.

- NonUpdated – only records that no longer exist in the source are deleted.
This mode should be used when CDC is defined on the table, to prevent Fabric from deleting records that exist in source, and then add them back again – what will cause Fabric to send delete and insert transactions (instead of only update).
To support that mode, Fabric is creating a temporary table containing the primary key values for each record extracted from the source to the LU table. Once the data extraction process is finished, the system removes records from the LU table if their primary keys are not present in the temporary table. Subsequently, the temporary table is discarded.

Note: to use this mode:

- PK fields must be defined on the table

- The population mode must be either Upsert or Update

The Delete Mode property exists in both population and table level.

- When set on the table level, the table will be truncated prior to executing any of the table’s populations.

- When set on the population level (to a value other than OFF), the delete functionality will be triggered only if and when the population is executed, and it is operating on the entire table’s records.
Set up ‘Truncate’ in case the population extracts all data for a given instance. 
NOTE: The ‘Truncate mode’ setting on the population level truncates the entire table (for example, even if it was set on the 3rd population). 
Set up ‘No Truncate’ in case the population extracts a delta from the source.

In general, the Delete Mode is set on the LU table. However, the Delete Mode can be overridden by the LU table population if the LU table's Delete Mode is not set to All.

The relationship between LU table level and population level operates on a logical OR basis, with "ALL" taking precedence over all other modes, and "NonUpdated" taking precedence over "OFF".

Note:

- The table will be truncated only if at least one population is executed,

- When using Broadway flow as the table population the Delete Mode is set on the SyncDeleteMode actor.

## Population types

### Differences between DB query, Root query and Broadway population

Population may be defined using either of the 3 options: DB query, Root function or Broadway flow.

| Category | DB Query | Root function | Broadway flow |
| --- | --- | --- | --- |
| Content | SQL statement | Java function |  |
| Data filter | WHERE statement is added automatically | No automatic filtering | WHERE statement is added automatically |
| Population Execution times | One time | Execute for every distinct parent link value | One time |
| Times fetching data from source | Population is getting distinct values. Each SQL query statement is using MAX_SOURCE_QUERIES_GROUPING number of distinct values (Fabric keeps prepared statement), until all distinct values are used. The remaining distinct values will be used in one query (also as prepared statement) | Custom implementation | Group values to a single query according to the sourceDbQuery.size parameter (same concept as in DB query). |

### Root function best practice

- Root function population is executed for each distinct value of the parent table linking values.

- If the root function is not using the INPUT fields (the distinct parent values) as part of its logic and therefore should not be executed more than one time, consider the below:

- Connect the table to the LU root table to have it running one time only.

- If connecting the population to the root table cannot be done due to other logical constraints (for example a delete orphans functionality), a thread global should be used to make sure this population is executed only once.

## Populations – behind the scenes

### How is the SELECT Statement Constructed on the Source?

Every population utilizes SourceDbQuery actor, which constructs the SELECT statement to run on the source tables and pull the data. To ensure that only records that belong to this instance are retrieved, the SourceDbQuery receives the input parameter ‘parent_rows’, which supplies distinct values of the linking fields from the parent table's records. Subsequently, the actor incorporates a WHERE statement into the SELECT query that is executed on the source database.

For example:

The population of CASES table defines the following SELECT statement:
SELECT * FROM CASES
On runtime, the SourceDbQuery gets the distinct activity_id values from ACTIVITY table, and adds the below WHERE statement to the query:

- Up to version 6.5:
WHERE ACTIVITY_ID IN (?,?,?...).

- After version 6.5:
WHERE ACTIVITY_ID = ? Or ACTIVITY_ID = ? Or...

The number of values included in the IN/OR statement depends on the Size parameter of the SourceDbQuery actor. By default, the Size is set to 100. 

If a child table is linked to its parent table using more than one field, the WHERE statement will be constructed as follows:
Where (linkField1=? And linkField2=?) or (linkField1=? And linkField2=?) or (linkField1=? And linkField2=?)…

### Opening Connection to Source

#### DB Interface Pool

Every population establishes a connection to the source system via the DB Interface. To optimize performance, each node maintains a pool of open connections for each DB interface.

The default pool size is defined in config.ini.[fabric].MAX_CONNECTIONS_TO_SOURCE. The definition is per node.

You can override this value using the DB Interface properties in the studio:

- Min Connection Pool: number of connections kept open, even if not in use.
Note: the minimum connections are opened only upon first request, and not automatically by Fabric when it starts.

- Max Connection Pool - max number of connections allowed to source system.

#### Population Use of the DB Interface Pool

When the GET command is executed, populations within the LUIs are executed sequentially according to a predefined order. Each population is associated with a particular DB Interface. The population utilizing a specific DB interface requests a connection from the connection pool:

- If an available connection exists, it will be utilized.

- If there are no available connections but the pool isn't at capacity, a new connection is opened.

- If the pool is at capacity, the process waits until a connection becomes available. 
Timeout to wait on connection is hard coded 30 seconds.

Idle connection will be removed from the pool after 60 seconds. The time can be configured though the driver parameters.

Every connection is returned to the pool after each use (either db(...) or any BW actor). If two populations are using the same DB interface, each one will request its own connection from the pool and return it once done (most likely the second population will get the same connection instance as the first one).

This can be controlled by a parameter in the config 'ENABLE_SELF_CLOSE_CONNECTION'.

JMX Stats:

- jdbcActiveSessions

- dbcIdleSessions - idle connections in the pool

- JdbcMaxSessions – max connections as configured for the pool

No available way to check waiting on the pool

## Parallel Populations

By default, the populations with the same execution order are running sequentially.

To improve the LUI sync time, Fabric supports parallel syncs of multiple populations within the same execution order.

Use config.ini. [fabric] .MAX_PARALLEL_SYNC_SAME_ORDER (default value = 1) to set the desired number of parallel populations execution.

Note:

- Although the populations are fetching the data in parallel, the data write to the LUI remains sequential.

- When two populations are running in parallel, they will use the connection in turns, giving each other time to read on write.

- The capability to share the same connection relies on the JDBC driver's support for multithreading & multiplex.

Best practice:
Allowing more than a single population to run in parallel means consuming more resources by a single LUI sync, which may therefore block other LUIs that are syncing in parallel.

## Sync on demand

The purpose of Sync On Demand mode is to reduce Fabric's LUI sync time by only synchronizing relevant data.

Sync On Demand logic:

- Executing the GET command does not trigger instance synchronization (like SYNC OFF mode).

- If the instance doesn't exist in Fabric, a full sync is performed.

- When executing SELECT statements on LU tables, an evaluation determines if a sync is needed.

- This evaluation applies only to the LU tables in the SELECT statement and their parent tables up to the Root table.

- Synchronization follows the standard Sync mechanism rules based on LU's predefined sync method and mode.

Sync On Demand can run in two modes:

- True: Each table can be synchronized only once per GET, even if multiple SELECT statements are executed and the source table changes.

- Always: Each table can be synchronized on each SELECT, assuming the sync conditions are met.

To set Sync On Demand mode:

- Set the SYNC_ON_DEMAND parameter in the config.ini file to True (default is False).

- Run the SET SYNC_ON_DEMAND = [TRUE/FALSE/ALWAYS] command to set it at the session level.

NOTE:
The implementor should manage transactions efficiently. When a Web Service or GraphIt invokes multiple SELECT statements on the same LU, it is the implementor's responsibility to minimize writes to the MDB Storage.

For example:
Several SELECTs in a Web Service or GraphIt:

- Set Sync On Demand to either true or always, whatever is required.

- Perform GET LUI.

- Begin the transaction.

- Perform all the required SELECT statements:

- On each SELECT, Fabric checks whether sync should be performed (based on sync mode and sync method). If it should, the relevant tables are synchronized - as per the above logic.

- Commit the transaction.

AUTO_MDB_SCOPE??

## Data Pulling Best Practice

### LU Schema Structure

- Always consider the order in which the table is populated.

- Mark the Key fields of each table and set them as a unique index.

- Include only the necessary columns in the LU table. 
The higher the number of columns:

- The more data is fetched and transmitted over the network, resulting in slower fetching.

- The LUI size increases, which subsequently results in slower save and fetch from the system_db.

- Connect parent-child tables using the minimum required fields to have a better performant query statement.
For example:
Activity table contains customer_id, activity_id.
Activity_history table contains customer_id, activity_id, history_id.
We can link these tables using both customer_id and activity_id. 
However, if we require all customer records in the activity_history table, it is sufficient to connect the 2 tables using only the customer_id field.

- A table should always be connected to the parent that will minimize the number of times that the population will go to source.
For example – connect a table to Account (using account_id) instead on connecting it to Subscriber table (using subscriber_id), as anyway all the subscribers belong to the same account.

- Avoid creating indexes on tables before conducting performance testing. Indexes are typically necessary for very large datasets and can potentially slow down Data Manipulation Language (DML) commands.

### Populations

- Ensure that there is an index on the source side that corresponds to the WHERE statement executed by the population.

- Ensure that linking fields are of the same type in order to prevent the source from converting them during fetch operations. For instance, if a parent field of type String is linked to a child table field of type int, certain database types may result in failure, while others may convert the String to Int, potentially slowing down the fetch performance.

- Fetch from source only the records that are needed.
For example, if accounts that are closed are not required, do not fetch ‘closed’ accounts.

- If a population is accessing the source system many times (i.e., the distinct values of the parent tables exceed the Size parameter), consider changing the Size parameter to reduce the number or roundtrips. However, increasing it excessively may exhaust the network bandwidth and potentially cause blockages for other processes requiring it.
Size parameter is overriding the config.ini MAX_SOURCE_QUERIES_GROUPING parameter.

### Source Interface

- Make sure the DB Interface connection pool is sized as required, in order not to wait for connection to source when running multiple sync processes at the same time.

### Query Optimization

- Create the relevant indexes based on your statement.
Note: Creating the correct indexes improves the performance of SELECT,
but will slow down the performance of INSERT/UPDATE/DELETE statements.
Therefore, it is recommended to execute an explain query plan to validate that the correct indexes are being utilized.

- If you apply additional manipulation/transformation in the query on the index fields, it will not be used.
For example: Concatenation of 2 fields on the 'where' statement. In such a case, even if there is an index on those fields, it will not be used.

- If needed, enforce the index utilization in the query using INDEXED BY. If it is failing on parsing, use /* sqlite */ or /* k2_no_parse */ before the select  statement(depending on the Fabric version).

- Simplify queries used to achieve better performance and readability.

- Avoid using the JOIN operator too many times in the same query. Consider splitting the query to several simple queries.

- Avoid using the UNION operator too many times. Additionally, use UNION ALL in case that data, being retrieved by the sub-queries, is unique. This will improve the performance, as UNION adds another step of ‘distinct’ between the queries’ results.

- To validate that a record exists, select the first row with the required ‘where’ condition (using limit 1 or rownum < 2 – depending on the DB). Do not use count(*) in the query as this is time consuming.

# LU Tables

## Business Tables

Business tables are stand-alone LU tables that have no connection (direct or via other LU tables) to the LU Root table. Business tables allow the user to compute, transform and store new data inside the LU.

Use cases:

- Prepare and store an API response once data is changed in the LUI, to be ready in-advanced.

- Run complex/long process to be ready immediately when needed.

Best practice:

Business table’s population should run only when the source tables are updated. Use the decision function to determine whether the population should be executed or not.

## LU Product tables

- _k2_main_info:

- Stores the last deployed LU version and time when the LU schema changed.

| Field Name | Description |
| --- | --- |
| lu_name | Logical Unit name |
| version | Version of the last deploy which impacted the LU Schema |
| instance_id | Instance Id |
|  |  |
| version_timestamp | The timestamp of the last LU schema deployment. This field is retained for backward compatibility but is no longer in use |

_k2_objects_info:

- -This table stores information about statistics for each table population and elements used during the population.

- - The table is updated after executing table populations during the syncing of the LUI.

- - It is used in troubleshooting to investigate issues such as slow migrations/GETs.

| Field Name | Description |
| --- | --- |
| table_name | LU table name |
| object_name | Object name - population name or enrichment function name |
| type | Object type: 6 - population, 10 - enrichment function |
|  |  |
| verified_time | Last time of verification as to whether the object should be synced or not (according to the sync policy). Will not be updated if the population was not executed |
| start_sync_time | Object's last sync start time |
| end_sync_time | Object's last sync end time |
| start_write_time | Start time of the last write LU instance into the SQLite file |
| last_write_time | End time of the last write LU instance into the SQLite file |
| number_of_records | Number of processed records |
| time_to_populate_in_sec | Total time in seconds to run the object |
| next_time_to_populate_object | Next time the object should be synced (Rami) |
| version | The Version Id of the last object's sync |
| sync_error | Object's sync error message in case of a failure |

K2_delta_error:

- - Used by iidfinder partitioned delta mode.

- - Holds information on errors, including when each error occurred.

| Field Name | Description |
| --- | --- |
| Offset | The offset of the IID |
| Update_time | The time When the sync executed |
| Content | The error content |

k2_transactions_info:

- Doesn’t exist in Fabric 7.2 and up.

- Used to hold the fabric transaction id for the CDC.

- There is no cleaning up mechanism in this table, so it is important to check it in projects that use CDC and implement logic to delete from this table.

| Field Name | Description |
| --- | --- |
| Id | Transaction ID |
| Ts | Timestamp |

|  |  |
| --- | --- |

- When selecting from the product tables, use the LUT name

- K2_read_pos:
Not in use – will be removed in Fabtic 8.1

## Additional Table Properties

Columns Collation:
The COLLATE operator in SQLite determines how string values are compared when using a WHERE statement in queries. 
SQLite offers various collation functions to customize string comparisons:

- BINARY - used the most. Case-sensitive comparison based on ASCII values.

- NOCASE enables case-insensitive comparison.
For example:

- Select TYPE from tblExample where NAME = ‘value’ returns records when the NAME field is set to either ‘VALUE’ or ‘value or ‘Value’.

- RTRIM removes trailing spaces before comparison.
For example:

- Select TYPE from tblExample where the NAME = ‘value’ returns records that match both ‘value’ and ‘value ‘.

By default, Fabric is using BINARY COLLATION for every text type column.

When changing this property, if LUIs already exist in Fabric, it is necessary to delete them first, deploy the changes, and resync. Alternatively, if you are working in a development environment, you can drop the LUT before the deploy.

LU Reference Tables List:
The References tab displays a list of common Reference tables defined in the project.

A Reference table should be checked only if:

- We want to use the Reference table as a lookup function in one of the populations.

- We want to activate the Reference table sync (if needed, according to the sync policy) once the LUI is synced.

Note:

- A Reference table can be accessed from code (e.g., function) also when the table is not checked in the References tab.

- It is recommended to limit the number of checked Reference tables in the Reference tab to avoid a massive sync of the Reference tables when synchronizing an LU instance for the first time.

Coding best practice:

To select from Reference use ludb.fetch or fabric.fetch, instead of FabricDB.fetch (fabric local interface defined in the implementation) as this drastically impacts the performance (fabriclocal/fabricremote is over TCP).

Full-Text Search (FTS)

Fabric is using the SQLite FTS5 (extension module) to provide full table search capabilities for an LU table.

The most common use case for the FTS tables is a wild card/prefix search, where queries using the LIKE operator are not sufficient (fuzzy search).

When the full-text search property is set to TRUE, the virtual table will be created, alongside 5 additional tables and it will hold all necessary data/tokens/indexes to perform a full-text search.

When deciding to use the FTS feature, the following aspects should be taken into consideration:

- Full-text search is most beneficial when you have large volumes of text data that need to be frequently searched.

- For each INSERT/UPDATE/DELETE operation, corresponding tables will be updated, which may affect performance on the large tables. 
Make sure inserts are not intensive and within the SLA.

- Full-text search can be resource-intensive, so it is important to consider the performance impact on your database. Indexing large amounts of text data can increase the database size. Limit the number of columns to the minimum required.

More about the FTS MATCH command:

# Big LUIs

## Storing Big LUIs

When using Cassandra as the LUI storage, due to Cassandra’s limitation of 2G per column, big LUI’s blob is kept in chunks. 
Chunk size is configured in config.ini. [system_db_entity_storage].INSTANCE_CHUNK_SIZE. Each instance that exceeds this size (after compression), would be stored in chunks.

The SQLite file's chunks are written into the Cassandra entity_chunks table in parallel, using the Cassandra Loader. The Loader configuration for the parallel save can be done using config.ini, by adding a section named [LU type]_cassandra_entity_storage per each LU, which would increase the NUMBER_OF_THREADS parameter.

The LUI data is first written into the entity_chunks table and only then the entity table is populated.

The entity table includes the following data:

- batch_id - unique ID to connect between entity and entity_chunks tables.

- chunks_count - number of chunks.

- Data - holds the LUI SQLite file following compression.

The entity_chunks table includes the following data:

- Id - holds the instance ID.

- sync_version - holds the same version as the version populated in the entity table.

- batch_id - holds the same ID as in the entity table.

- chunk_index - holds the chunk number.

- Data - holds the split SQLite file after compression for the chunk index.

Note:

- Changing the chunks’ size in INSTANCE_CHUNK_SIZE may require additional tunning of Cassandra read/write chunk size (like mutation size, commitlog_segment_size_in_mb, etc.). Additionally, you may need to change the settings in the config.ini Cassandra Loader (like MODE=SINGLE). It is important to consult with the Product Solution team before changing this parameter.

- The Cassandra Loader can be defined in either one of the following sections, by priority:
default_loader < Cassandra_entity_storage_loader < [lu_type]_cassandra_entity_storage.
Entity storage can use a different method than other Fabric operations, and then some LU_types can as well use a setting different than the default.

Parallel Loading of Big LUI from Cassandra

When loading the chunks of a big LUI from Cassandra to Fabric as part of a GET command, the chunks are combined and decompressed. There is a trade-off between the performance of the load and the memory allocated to this process. 
To improve the performance of such loading, you can define the number of threads that will be executed in parallel. When setting the number of threads, you must also define the maximum memory allowed to be used for the parallel load:

- Config.ini.[system_db_entity_storage].ASYNC_LOAD_MAX_THREADS, the maximum number of threads (over all Fabric sessions on the same node) to be allocated. 
Default is set to 0, which means that by default the parallel load is disabled.

- Config.ini.[system_db_entity_storage].ASYNC_LOAD_MAX_MEMORY_IN_MB, maximum memory to be allocated for the parallel load process. Default is set to 2000 MB.

Big LUI Partitioning

Store the entity chunks using a new partition key to distribute the load across multiple nodes.
Use the below parameters to use this feature:

- Config.ini.[system_db_entity_storage].ENABLE_PARTITIONED_MDB=true

- Config.ini.[system_db_entity_storage].ASYNC_LOAD_MAX_THREADS bigger than zero

Note:

- chunk_index column is added to the partition key. Therefore, there is no upgrade path for existing projects. You must clean all data in Fabric and bring it back.

- It is recommended to turn on this feature only when dealing with LUIs that are split into multiple chunks.

## Big LUI Considerations

There are a few considerations that need to be done when working with big LUIs:

- Design Considerations:

- When possible, divide the LUT into multiple smaller LUTs. 
This allows other processes, like APIs, to skip fetching of unnecessary LUTs, optimizing performance.

- Store only relevant data in the LUI for minimizing storage usage.

- Store only relevant columns.

- Apply data purging where possible.

- In case history data is required, consider storing it in a different location (Cassadra/history LUI)

- Sync Time: 
Syncing large instances from the source may require additional time due to the retrieval of numerous records. 
As it is not advisable to set the sync timeout property based on the largest LUI, timeout may occur when syncing the LUI. 
Consider implementing a separate process to handle big LUIs and adjust the session sync timeout using the ‘set sync timeout’ command to accommodate longer synchronization duration.

- Tunning:

- Increase fetching Size: If the LUI is going to source many times, consider increasing the size in the SourceDbQuery actor.

- Optimize Network Fetching: If the network is slow for a specific source, increase the fetch size to transfer more data per roundtrip. 
Fetch size settings determine the number of rows that are retrieved in any subsequent trips to the database for a result set

- Tune FETCH_SIZE in config.ini (will affect all DB Interfaces)

- Using 'Post connection commands' property of the interface

- Use the below code: 
 
Connection con = getConnection("EDF_UAT"); 
try(Statement stm = con.createStatement()) { 
    stm.setFetchSize(10000); 
    try (ResultSet rs = stm.executeQuery("select ID FROM GDB.ORGANIZATION")) { 
        while (rs.next()) { 
            yield(new Object[]{rs.getString(1)}); 
        } 
    } 
}

- Cassandra Storage Optimization:

- Enable parallel saving to the storage

- Enable parallel loading from the storage.

- For very large LUIs, configure Fabric to distribute entity chunks across different Cassandra nodes.

- Cache Storage:
For very big LUIs, consider moving the cache directory to a different storage to not exhaust the memory.

Note: When using cloud storage for the LUIs, the fetch time and cost are not impacted by the LUI size.

# Useful built-in Fabric functions

|  |  |  |
| --- | --- | --- |
| getInstanceID() | Get the instance id of the LU currently attached. |  |
| getLastSyncTime() | Get the last sync time of the LU currently attached This works in the context of sync |  |
| getLuType() | Get the LU Type associated with the current context This method gives access to an internal class and is subject to change. |  |
| getLuKeyspaceName() | Get the Cassandra Keyspace associated with the LU of the active context. |  |
| getPopulationName() | Get the population name of the current map execution. |  |
| getSyncMode() |  |  |
| getTableName() | Get the active table name of the current map execution. |  |
| isFirstSync() | In the context of sync, indicate if this is the first sync for this instance |  |
| isStructureChanged() |  |  |
| openFabricSession(String interfaceName) | Open a new fabric session based on the "fabric" interface credentials. |  |
| rejectInstance(String message) | Reject the current instance in the context of a sync (delete the instance if already exitsts) |  |
| serializeLU(String startingLudbObjectName) | Serialize the current LU instance into JSON |  |
| setInstanceFound(boolean isFound) | During sync, use this method to explicitly affect the if the sync should return instance not found |  |
| skipSync() | skip current sync, rollback changes done and mark sync as SKIP |  |

SET DB_PROXY

# Useful Fabric commands

## LUI Commands

### GET Instance

#### Parallel GETs for Different LUTs

#### Use the following command to concurrently retrieve multiple instances of different LUTs, for a better performance:
get Customer.1, Crm.1 WITH parallel=true [STOP_ON_ERROR=true/false];
If one of the instances encounters an error, setting stop_on_error=true will halt the fetching process for the remaining instances.

The config.ini.[fabric].PARALLEL_GET_POOL_SIZE limits the number of parallel GETs when using this command, per node. The default is set to 200.

Use sync WITH PARALLEL when possible, to reduce the time that the API is waiting for the LUI fetch.

#### Parallel GETs for the Same LUT

Only a single instance per LUT can be fetched at a time into a Fabric session. To work with 2 LUIs of the same LUT, use the openFabricSession command as follows:

#### fabric().execute("get Customer.?", cust1);
openFabricSession("fabric2");
db("fabric2").execute("get Customer.?",cust2);

#### getf

GET an instance using a function that returns the IID to fetch.

For example: 
getf Customer.fnCreateInstId(235);
fnCreateInstId function adds 1000 to the input value and returns the value 1235. Fabric gets Customer # 1235.

GET command best practice:

- Avoid executing set sync on before a GET command as this is already the default (can be configured in config.ini file)

- Avoid executing set sync off after a GET command and before executing the queries, as such queries will not trigger a sync (applicable for Fabric Version 5.x and above).
This is the default system behavior, but it can be modified via a change in the config.ini file.
On versions prior to 5.x, set sync off should be used.

- Use binding in GET commands: fabric().execute("get Customer.?", cust1);

### Release Instance

Use the following command to detach the LUI from the session for a list of LUs or for all LUs use: release [<LU_NAME>,<LU_NAME]]

Example:

- Release;

- Release Customer,Collection – release only the 2 LUTs. The rest remains attached.

Note: The release command will fail if there is an open result set or transaction.

### Delete Instance

Options for deleting an instance:

- LU Schema property: DELETE INSTANCE IF NOT EXIST

- When set to True, Fabric deletes the LUI if it is not found in the source system (root table does not have data in source). An error is thrown if this is the first GET.

- When set to False (default), the instance is retained in Fabric even if it is empty.

- Use the command: delete instances if not exist <LUT_Name>; to delete all LUIs that do not exist in the source system.  
To use this command, enable it by setting  config.ini.[fabricdb].DELETE_INSTANCES_IF_NOT_EXIST_COMMAND_ENABLED parameter to True.

- Use the command delete instance <LUT_Name>.'<instance_id>' [mdbFinder=<false/true>];

- You can delete multiple instances by adding additional <LUT_Name>.'<instance_id>' to the command.

- mdbFinder = true will also delete from iidf_info table instead of marking it as ‘deleted’.
When an instance is marked as deleted, iidfinder will discard all its incoming transactions.

- Use the command SET INSTANCE_TTL to set the Time-To-Live (TTL) in seconds for each LUI running in the session; 
The LUI is deleted automatically from Fabric after the TTL ends.

Note: supported only in case the storage supports TTL functionality.

Note: delete instance do not delete data from IIDFinder cache tables.

## MDB File Info

- Get MDB file size

- MDB_SIZE command: return the actual size (in bytes) of an instance/s blob in storage:

- MDB_SIZE <LUT_NAME>.'<INSTANCE_ID>'

- MDB_SIZE <LUT_NAME>.(<instance 1,instance 2,etc...>)

- mdbFileSize function: returns the length, in bytes, of the file in cache, or 0L if the file does not exist. 

fabric>get Customer.1;
fabric>select mdbFileSize(‘Customer’);

- Get MDB file path

- fabric>get Customer.1;
fabric>select mdbFilePath(‘Customer’);

## MDB Import / Export

Export/Import the MicroDB data from SQLite to another DB type (tested on PostgreSQL only).
This solution provides the capability to back up Fabric data, share it with others or import data from external data sources into Fabric.

- MDB_EXPORT <LU>[.<IID>] WITH INTERFACE_NAME=<name> [EXCLUDED_TABLES=<TBL1>,<TBL2>] [FK=<false>] [REMOTE_IID=<OTHER-IID>]

- MDB_IMPORT with IID

- MDB_EXPORT with or without IID 
Without IID - create the LUI schema in the target DB, including PK, FK drop and recreate).
With IID - export the LUI data (schema should already exist on the target DB).
Both import and export commands have an optional parameter, EXCLUDED_TABLES, to specify a list of tables to be excluded from the import/export process.

## SET OUTPUT FILE

Direct SELECT statements’ outputs to a file.

SET OUTPUT FILE=<file_name> with arg1=val1 and arg2=val2                                             |

where args could be:

- DELIMITER

- LINE_TERMINATOR

- HEADER

- QUOTE

- FORCE_QUOTE

- ENCODING

- APPEND

The directory used for the file is set in config.ini.[fabric].EXPORT_DIR

To cancel file redirection and output to the standard output (stdout), use the command: 
SET OUTPUT=stdout.

Coding best practice:

When possible, use the Fabric set output file command followed by a query execution instead of writing code. This will generate a CSV file that contains Fabric data.

## SET DB_PROXY

SET DB_PROXY [= <interface name>]

Activates an operations' scope toward the specified DB interface, so that until it is turned off, all operations are done against this interface.

To turn it off use: set db_proxy=off.

To enable this command, set config.ini.[fabric].ENABLE_DB_INTERFACE_PROXY to TRUE.

## List

- List LU_TYPES/LUT [COUNT=<'Y'/'N'> [LU_NAME=<'NAME'>]] [STORAGE=<'Y'/'N'> [LU_NAME=<'NAME'>]]

- COUNT - counts the number of instances
If LU_NAME is added, count only for this LUT.

- Storage - storage property value

- List INSTANCES LU_NAME={NAME} - list of Instances per LU name

Best practice for counting number of instances:

- If the LUI storage is Cassandra - use the Cassandra COPY command.

- Else, use the LIST command.

Best practice for getting a full list of IIDs:

- Use the LIST command.

- If the list of instances is required to run a batch command on the entire LUI population, and Cassandra is used as the LUI storage, do not use ‘select id from entity’ table, as the result will be returned by partition and therefore the batch load will not be always distributed between the nodes. Instead, use the 'reverse migration’ approach.

Other List options:

- DB_SOURCES - for a list of DB interfaces

- INTERFACES - for a list of all interfaces

- ENVIRONMENTS, ENVS

- INSTANCE_GROUPS, IGS,

- BROADWAY_FLOWS, BF, WS

- SECURITY_PROFILES

- REF_BACKUPS

- MTABLE

- CONFIG [section_filter=filter] [key_filter=filter]

- CONFIG_OVERRIDES - list the custom user overrides

- CONFIG_OVERRIDES_HISTORY - history of overrides, including the version, date of change, user, node ID and the new value.

- ROLES, USERS, TOKENS, ROLE_PERMISSIONS, METHODS

## Describe

The describe command provides LU schema metadata information:

- list of tables per LUT schema

- list of columns per table

- list of indexes per table

# MDB JMX Stats

- MDB Save

- mdbSaveErrors - duration and count of mdb saved to storage, resulting in an exception, per schema.

- mdbSaveDuration - duration and count of mdb saved to storage, per schema.

- mdbSaveBytes - Bytes (uncompressed) and count of mdb saved to storage, per schema.

- GET

- getDuration – total duration (and count) of GET performed

- MDB Fetch

- MdbFetchDuration - Duration and count of mdb read from storage, per schema.

- mdbFetchNolnstance Duration and count of mdb fetch from storage, where no instance was available, per schema.

- mdbFetchErrors Duration and count of mdb fetch from storage, resulting in exception, per schema.

- mdbFetchBytes Bytes (uncompressed) and count of mdb read from storage, per schema.

- MDB Attach

- mdbAttachError Duration and count of mdb attach that resulted in an error, per schema.

- mdbAttachDuration Duration and count of successful mdb attach, per schema.

- MDB Cache

- mdbCacheCount Count of LUI micro databases cached (not in use), per schema.

- mdbFetchNoNewVersion Duration and count of mdb fetch from storage, where the cache was up to date, per schema (fetched from cache).

- mdbCacheBytes Bytes of LUI micro databases cached (not in use), per schema.

- Statements

- mdbActivePreparedStatements The number of active prepared statements, including the ones in the prepared statement cache. (MDB_PREPARED_STATEMENT_CACHE_LIMIT pool).

- mdbActiveStatements The number of active statements (non-prepared).

- mdbActiveResultSets The number of active result sets.

- Fabric Pool:

- mdbSessionFromPoolDuration Time spent successfully waiting for a session from the mdb session pool (MDB_CONTEXT_POOL_SIZE pool).

- Lock

- mdbWriteLockDuration Time spent in write locking the mdb.
Time spent on waiting to lock mdb file for write (until previous lock is released)

- Vacuum

- mdbVacuumReclaim Number of bytes reclaimed by the vacuum operations.

- mdbVacuumDuration Duration of vacuum done on mdb above thresholds.

- mdbVacuumErrors Number of errors in mdb Vacuum.

- Resources

- threadPoolWaiting ThreadPool thread wait duration (what pool is it? Where do we define size?)

- threadPoolExecution ThreadPool execution duration (what does ‘execution’ means)?

- DB Interface

- jdbcidleSessions Number of idle JDBC sessions.

- jdbcActiveSessions Number of active JDBC sessions.

# Design Considerations

- LU Schema:

- How to choose the right IID (lookup if needed)

- Movement of data between LUIs handling.

- Multi parents handling - data that belongs to cross LUIs.

- Table Population:

- What is the source of the table (DB, File, Kafka)?

- How will it be synced for the initial load?

- How will it be synced as part of BAU? Which interval is needed?

- Are we getting ongoing updates or a full refresh from source?

- If on-going updates - what is the delay from source?

- What is the expected volume of GG transactions per each table? Are there any peak times?

- What types of DML? Only insert / insert+delete / insert+update+delete.

- Are there any batch processes running on the source side?

- Is there a retention of the data? Is purging needed?

- Is any transformation logic required?

- Is this table a real table or a joint of a few tables?

- How many records are there on the source system? Partitions?

- Does a PK exist? Indexes? FK?

- Any special column types (BLOB/CLOB)?

- Are there PII fields?

- Which columns are needed?

- Is it a reference table or an application table?

- Is it a SOR table?

- How is this table being used?

- LU Table vs Common Table Decision:

- Is the table data associated with many instances, and can it be considered reference data?

- How many records does the table contain?

- How often is this table expected to be changed?

- Full refresh from source or ongoing transaction?

- NRT needed?

- Source APIs:

- How to call the API? Authentication?

- What is the API output format?

- Does it include cross instances data?

- How many parallel calls can be executed?

- What is the trigger to call the API?

- Is there a dependency between API calls or any sequence of the API calls?

- Error handling? Failover?

- Expected response time? After how long should we raise timeout?

- Expected response size?

- Files as Source:

- How many files per day? Peak time?

- Pull or push?

- What is the scheduling of the files?

- What is the size of the files?

- What is the type of files/file format?

- Must the files be processed in the order in which they were received?

- Must the records in the files be processed by order?

- File structure, Header/footer, encryption?

- What are the required File/data validations?

- Is the File full dump or delta?

- Transformation logic?

- Files purging logic?

- Is the file data related to multiple instances?

- Error handling?

- How can we identify when the file is ready to commence processing, rather than still undergoing writing operations?

- Source DBs

- What is the DB type?

- Can we connect directly using JDBC?

- Can we connect 24/7 or are there any limitations?

- How many parallel connections are allowed?

- Are there any PK or Unique indexes available on the tables?

- Are indexes defined on the tables for the linked fields?

- Is it the source DB or replica? If replica, what is the delay?

- Is it a one DB instance or multiple? If multiple - what is the logic?

- Do we have different schema names in different environments (prod/test)?

- General

- How many instances per LUT do we have?

- LUI purging logic?

- Is there encrypted data (file or field)? Masking?

- How many trail files are expected to be sent per day?

- External Interface Assumptions

- Fabric will have access to the source DB to extract data. The number of connections required to extract data should be defined as soon as possible, as it may impact the design.

- Fabric’s ability to access source DBs for a reading activity will not be limited by time.

- The schema names for source DB won't change based on the environment.

- Source DB will define indexes on the fields used to fetch data into the LU.

- Any other restrictions on external interfaces, such as DB, Kafka queue, APIs, etc., should be documented as soon as possible and considered while building the design. Example:

- Number of allowed parallel APIs call.

- Limited access hours.

# Performance Considerations

Link to LU schema structure

Link to Populations

Link to Source Interface

# Coding Best Practices

- General:

- Avoid executing set sync on before a GET command, as this is already the default.

- Avoid executing set sync off after a GET command and before executing the queries, as such queries will not trigger a sync (applicable for Fabric Version 5.x and above).

- This is the default system behavior, but it can be modified via a change in the config.ini file.

- On versions prior to 5.x, set sync off should be used.

- Always attempt to use in-memory data and avoid accessing external interfaces.

- Avoid accessing the same table multiple times if all queries can be combined into one single query.

- Stop an LUI sync when it takes too long by using the sync timeout property on the LU level.

- When selecting from reference table use ludb.fetch or fabric.fetch, instead of FabricDB.fetch (fabric local interface defined in the implementation), as ludb.fetch or fabric.fetch are more efficient (do not use TCP).

- Use the clearThreadGlobals function to make sure thread globals are cleared at the end of the GET (those set by setThreadGlobal)

- When possible, use the Fabric set output file command followed by a query execution instead of writing code. This will generate a CSV file that contains Fabric data.

- LUDB Functions:

- The purpose of LUDB functions is to expand the SQL library.

- An LUDB function that does not have or does not use input parameters and is used within a query will be called multiple times and will return the exact same result for all rows.

- Graphit:

- Try using Graphit whenever possible and minimal (or no) java code to allow easier maintenance and readability. If you are not sure how to implement a specific functionality in Graphit, please contact the COE.

- A Graphit file can be a standalone web service.

- Use SQL non-prepared as a node type only when it is needed; by default, SQL should be used as it works much faster.

- If you use a GET inside the Graphit file, make sure a redundant GET is not executed in the web service.

- Use the resources try or close to release the entry back into the GraphitPool. Without this step, Fabric will generate/compile a new Graphit file every time, which could negatively affect performance. Code Example (calling graphit from enrichment function): GraphitPool.Entry entry = getLuType().graphitPool().get("Customer360.graphit"); Graphit graphit = entry.get();** Object result = graphit.run();** entry.close();**
