# LU Storage Management

The Sync mechanism synchronizes data between the data sources and the Fabric database. During the synchronization, extraction and transformation processes are executed on an [LU Instance (LUI)](/articles/01_fabric_overview/02_fabric_glossary.md#lui) whereby data is retrieved from the source systems using the GET command and loaded into the Fabric database.

GET response time is highly important due to multiple requests that Fabric receives concurrently. To optimize the LUI retrieval process, Fabric has a cache mechanism that holds a pre-defined number of instances and enables faster load of the instance into the memory. 

After the data is brought from the source, it is saved to Cassandra as a compresses MicroDB blob with the latest version number and the MDB file is saved to the Fabric cache. Fabric always attempts to bring the MDB file from cache prior to going to Cassandra DB in case no sync is performed during the GET.

The cache is always synchronized with Cassandra DB but since it has limited size, it does not always include the MDB file of the required instance. Then the MDB file is created from Cassandra and saved to the Fabric cache.

The cache size and location on Fabric server are defined in the **[fabricdb]** section of the config.ini file.

Note that only one version LUI can be kept in the memory - per session and LU.




### Storage Definition in [fabricdb] Section of config.ini

- MDB_DEFAULT_CACHE_PATH, holds the location of the Fabric MDB cache file on server.
- MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE, holds the storage type:
  - MDB - ???
  - None
  - Cassandra
- MDB_DEFAULT_SCHEMA_CACHE_SIZE, defines ache size limit in bytes per Schema for MicroDB instances. (not in use???)
- anything else???



### LIST Command

The **LIST LU_TYPES / LIST LUT** command is used to list the Logical Unit types and can display the LU's storage type. This command can be invoked for all deployed Logical Unit types or for a specific one.

* Use the following syntax:

  ~~~
  fabric>list lut storage=y;
  |LU_NAME        |STORAGE|
  +---------------+-------+
  |SummaryExercise|Default|
  |Customer       |Default|
  |ORDERS         |Default|
  |CRM            |Default|
  
  (4 rows)
  fabric>list lut lu_name=CRM storage=y;
  |LU_NAME|STORAGE|
  +-------+-------+
  |CRM    |Default|
  
  (1 rows)
  ~~~



[![Previous](/articles/images/Previous.png)](01_LU_storage_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_big_lu_storage.md) 



