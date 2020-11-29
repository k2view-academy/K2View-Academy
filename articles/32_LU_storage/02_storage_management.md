# LU Storage and Cache Management

The Sync mechanism synchronizes data between the data sources and the Fabric database. During the synchronization, extraction and transformation processes are executed on an [LU Instance (LUI)](/articles/01_fabric_overview/02_fabric_glossary.md#lui) whereby data is retrieved from the source systems using the GET command and loaded into the Fabric database.

The GET command response time is highly important due to multiple requests that Fabric receives concurrently. To optimize the LUI retrieval process, Fabric has a cache mechanism that holds a pre-defined number of instances and enables faster load of the instance into the memory. 

The cache size and location on Fabric server are defined in the **[fabricdb]** section of the config.ini file.

The cache has limited size which can be updated if needed. Note that increasing the cache size requires more disk space.


### Storage and Cache Location Definition in config.ini

The following variables of the **[fabricdb]** section of the **config.ini** are used to define the permanent storage and cache location on the Fabric server:

- MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE, holds the storage type:
  
  - The default and most commonly used value is **Cassandra**. 
  
    ~~~
    #MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=com.k2view.cdbms.dao.CassandraEntityStorage
    ~~~
  
  - When storage type in config.ini is set to **NONE**, the instance is not saved to the Fabric storage.
  
- MDB_DEFAULT_CACHE_PATH, holds the location of the Fabric MDB cache files on server.

  - The default path is set in config.ini and [can be changed](/articles/32_LU_storage/02_storage_management.md#change-cache-location-) if required.

    ~~~
    #MDB_DEFAULT_CACHE_PATH=/dev/shm/fdb_cache
    ~~~

- MDB_DEFAULT_SCHEMA_CACHE_SIZE, defines cache size limit in bytes per Schema for MicroDB instances. 

### Change Cache Location

Fabric enables the implementer to update the cache location per Logical Unit from the default product setting to a different path. 

This is performed by using the MDB_DEFAULT_CACHE_PATH variable of the **[fabricdb]** section of the **config.ini** and the LU Schema's **Cache Location** property as follows:

* When Cache Location property is set to **Default**, storing the cache in **/dev/shm/fdb_cache** if it exists, otherwise store it in **${FABRIC_HOME}/storage/fdb_cache**. 

* When Cache Location property is set to **Storage**, storing it under the storage folder in Fabric **${FABRIC_STORAGE}/storage/fdb_cache** if it exists, otherwise store it in **${FABRIC_HOME}/storage/fdb_cache**.
  * The definition of **${FABRIC_STORAGE}** path can be updated via the **fabric-server-start.sh** script using **-DFABRIC_STORAGE** variable.
* If the default path in config.ini is updated to another path, the cache will always be located according to config.ini regardless of the LU Schema property setting in the Fabric Studio.

Note that the change of the Cache Location property on LU Schema becomes effective after the Fabric restart only.

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



