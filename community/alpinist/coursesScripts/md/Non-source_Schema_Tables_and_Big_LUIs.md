# Non-source_Schema_Tables_and_Big_LUIs

# LU Tables

## Business Tables

Business tables are stand-alone LU tables that have no connection (direct or via other LU tables) to the LU Root table. Business tables allow the user to compute, transform and store new data inside the LU.

Use cases:

- Prepare and store an API response once data is changed in the LUI, to be ready in-advanced.

- Run complex/long process to be ready immediately when needed.

Best practice:

Business table’s population should run only when the source tables are updated. Use the decision function to determine whether the population should be executed or not.

Note: When creating a population for a business table without a parent, the SourceDBQuery Actor should be replaced with the DbCommand Actor, and it should not be linked to the PopulationArgs Actor.

## LU Product tables

- _k2_main_info: stores the last deployed LU version and the last sync time.

- This table useful for investigation where it can help the investigator to gather statistics about specific LU/LUI such as last LUTYPE deploy, LUI last sync time.

| Field Name | Description |
| --- | --- |
| lu_name | Logical Unit name |
| version | Version of the last deployed LU (assuming there was a GET in sync force/on) |
| instance_id | Instance Id |
|  |  |
| version_timestamp | Timestamp of the LU Schema version last deployed. This field exists due to backward compatibility and not in use anymore |

_k2_objects_info:

-This table stores information about statistics for each table population and elements used during the population.

- The table is updated after each component is executed (population or enrichment function) during the syncing of the LUI.

- It is used in troubleshooting to investigate issues such as slow migrations/GETs.

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
| next_time_to_populate_object | Next time the object should be synced. Calculated based on the sync policy, starting from the beginning of the current object sync. |
| version | Version of the object's last deployed schema |
| sync_error | The sync_error is populated in case of a sync raised an error that didn't cause a rollback. For example, if you start the transaction and then perform a GET command which fails - the sync_error will be populated until the transaction is closed. |

Note: Populations that do not use sourceDbQuery, such as business tables, will not update the number_of_records field. To achieve this functionality, use the PopulationCount actor.

k2_transactions_info:

- Doesn’t exist in Fabric 7.2 and up.

- Used to hold the fabric transaction id for the CDC.

- There is no cleaning up mechanism in this table, so it is important to check it in projects that use CDC and implement logic to delete from this table.

| Field Name | Description |
| --- | --- |
| Id | Transaction ID |
| Ts | Timestamp |

- _k2_read_pos - not in use and will be removed in Fabric 8.1

- _k2_transaction_info

- - Holds information on errors, including when each error occurred.

- - Used by iidfinder partitioned delta mode.

| Field Name | Description |
| --- | --- |
| Offset | The offset of the IID |
| Update_time | The time when a Sync is being executed |
| Content | The error content |
|  |  |

- When selecting from the product tables, use the LUT name

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

The References tab displays a list of Common Reference tables defined in the project. A Reference table should be selected only if:

- You want to use the Reference table as a lookup function in one of the populations.

- You need to ensure that GET operations do not run before the Common table completes its first sync.

Note:

- A Reference table can still be accessed through code (e.g., in functions) even if it is not selected in the References tab.

- The sync status of a Common table is verified against the k2_objects_info table within the Common table's schema. If a record exists, the GET operation can proceed.

- It is recommended to limit the number of selected Reference tables to prevent unnecessary delays, as each GET operation will validate the sync status of all selected Common tables.

- This configuration also affects GET operations within sessions that manually invoke the REF_SYNC command. In such cases, the GET will wait for all selected Reference tables to complete their sync before proceeding, even if the Common tables were already synced earlier.

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

The SQLite file's chunks are written into the Cassandra entity_chunks table in parallel, using the Cassandra Loader. The Loader configuration for the parallel save can be done using config.ini, by adding a section named [LU type]_cassandra_entity_storage per each LU and increasing the NUMBER_OF_THREADS parameter.

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

- Mutations are changes made to the database, such as inserting new data or updating existing data

- Max mutation size is also configurable via max_mutation_size_in_kb setting in cassandra. yaml . The default is half the size commitlog_segment_size_in_mb * 1024 .

- When a write operation occurs (such as an insert, update, or delete), the data is first written to the commitlog before being written to the memtable (an in-memory data structure)

- The commitlog ensures that all write operations are durable, meaning they are stored on disk and can be recovered if the node crashes before the data is flushed from the memtable to the SSTables (the on-disk data files).

- Commitlog Segments: The commitlog is divided into segments, which are individual files that store the write-ahead logs. The commitlog_segment_size_in_mb parameter defines the maximum size of each segment file. When a segment reaches this size, a new segment file is created.

- The Cassandra Loader can be defined in either one of the following sections, by priority:
default_loader )for all Cassandra operations)< Cassandra_entity_storage_loader (for all LUTs) < [lu_type]_cassandra_entity_storage (for a scpecific LUT).
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

- Config.ini.[system_db_entity_storage].ASYNC_LOAD_MAX_MEMORY_IN_MB, maximum memory to be allocated for the parallel load process. Default is set to 2000 MB.

Note:

- chunk_index column is added to the partition key. Therefore, there is no upgrade path for existing projects. You must clean all data in Fabric and bring it back. 
Entity table structure:
First - ENABLE_PARTITIONED_MDB=true
Second - ENABLE_PARTITIONED_MDB=false

- It is recommended to turn on this feature only when dealing with very big LUIs that are split into multiple chunks.

## Big LUI Considerations

There are a few considerations that need to be done when working with big LUIs:

- Design Considerations:

- LU Schema:

- When possible, divide the LUT into multiple smaller LUTs. 
This allows other processes, like APIs, to skip fetching of unnecessary LUTs, optimizing performance.

- Minimize storage:

- Store only relevant data in the LUI for minimizing storage usage.

- Apply data purging where possible.

- In case history data is required, consider storing it in a different location (Cassadra/history LUI)

- Store only relevant columns.

- Sync Time: 
Syncing large instances from the source may require additional time due to the retrieval of numerous records. 
As it is not advisable to set the sync timeout property based on the largest LUI, timeout may occur when syncing the LUI. 
Consider implementing a separate process to handle big LUIs and adjust the session sync timeout using the ‘set sync timeout’ command to accommodate longer synchronization duration.

- Tunning:

- If the LUI is going to source many times, consider increasing the size in the SourceDbQuery actor.

- If the network is slow, increase the FETCH_SIZE to have more data sent over the network per each roundtrip. 
Fetch size settings determine the number of rows that are retrieved in any subsequent trips to the database for a result set.

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

- If using Cassandra as the LUIs storage:

- Configure Fabric to load the LUI in parallel.

- For very big LUIs, configure Fabric to divide the entity chunks between different Cassandra nodes.

- Cache Storage:
For very big LUIs, consider moving the cache directory to a different storage to not exhaust the memory.

Note: When using cloud storage for the LUIs, the fetch time and cost are not impacted by the LUI size.

# Useful built-in Fabric functions

| Function | Description |
| --- | --- |
| getInstanceID() | Get the instance id of the LU currently attached. |
| getLastSyncTime() | Get the last sync time of the LU currently attached This works in the context of sync |
| getLuType() | Get the LU Type associated with the current context This method gives access to an internal class and is subject to change. |
| getLuKeyspaceName() | Get the Cassandra Keyspace associated with the LU of the active context. |
| getPopulationName() | Get the population name of the current map execution. |
| getSyncMode() |  |
| getTableName() | Get the active table name of the current map execution. |
| getThreadGlobals(String key) | Access to a simple ThreadLocal implementation. Use the clearThreadGlobals function to make sure thread globals are cleared at the end of the get process. |
| inDebugMode() | Use this method to find out if the current contest is running in the Studio debug interface |
| isFirstSync() | In the context of sync, indicate if this is the first sync for this instance |
| isStructureChanged() |  |
| openFabricSession(String interfaceName) | Open a new fabric session based on the "fabric" interface credentials. |
| rejectInstance(String message) | Reject the current instance in the context of a sync |
| rejectRecord(String message) | Reject the current record in the context of a sync population |
| serializeLU(String startingLudbObjectName) | Serialize the current LU instance into JSON |
| sessionUser() |  |
| setInstanceFound(boolean isFound) | During sync, use this method to explicitly affect the if the sync should return instance not found |
| skipSync() | skip current sync, rollback changes done and mark sync as SKIP |

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

- Populations: under transaction >> populationSync:
For each population – count and duration:

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

- Use the clearThreadGlobals function to make sure thread globals are cleared at the end of the GET.

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
