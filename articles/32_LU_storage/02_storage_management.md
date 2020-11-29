# LU Storage and Cache Management

The Sync mechanism synchronizes data between data sources and the Fabric database. During a synchronization, extraction and transformation processes are executed on an [LU Instance (LUI)](/articles/01_fabric_overview/02_fabric_glossary.md#lui) whereby data is retrieved from source systems using the GET command and then loaded into the Fabric database.

Since Fabric recieves multiple requests concurrently, the GET command's response time is highly important. To optimize the LUI retrieval process, Fabric uses a cache mechanism that holds a predefined number of instances which enable loading an instance into the memory faster.

The size and location of the cache on the Fabric server are defined in the **[fabricdb]** section of the config.ini file.

The size of the cache is limited and can be updated if needed. Note that more disk space is required when the size of the chache is increased.


### Defining the Storage and Location of the Cache in the config.ini

The following variables in the **[fabricdb]** section of the **config.ini** are used to define the permanent storage and location of the cache in the Fabric server:

- MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE, holds the storage type:
  
  - The default and most commonly used value is **Cassandra**. 
  
    ~~~
    #MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=com.k2view.cdbms.dao.CassandraEntityStorage
    ~~~
  
  - When the storage type in the config.ini is set to **NONE**, the instance is not saved to Fabric storage.
  
- MDB_DEFAULT_CACHE_PATH, holds the location of the Fabric MDB cache files on the server.

  - The default path is set in the config.ini and can be changed if required.

    ~~~
    #MDB_DEFAULT_CACHE_PATH=/dev/shm/fdb_cache
    ~~~

- MDB_DEFAULT_SCHEMA_CACHE_SIZE, defines the cache's size limit in bytes per schema for MicroDB instances. 

### Changing the Location of the Cache 
Fabric enables users to modify the location of the cache per Logical Unit from the default product setting using the MDB_DEFAULT_CACHE_PATH variable in the **[fabricdb]** section of the **config.ini** and the LU Schema's **Cache Location** property as follows:

* When the Cache Location is set to **Default**, store the cache in **/dev/shm/fdb_cache** if it exists, otherwise store it in **${FABRIC_HOME}/storage/fdb_cache**. 

* When the Cache Location is set to **Storage**, store the cache under the storage folder in Fabric **${FABRIC_STORAGE}/storage/fdb_cache** if it exists, otherwise store it in **${FABRIC_HOME}/storage/fdb_cache**.
  * The definition of the **${FABRIC_STORAGE}** path can be updated via the **fabric-server-start.sh** script using the **-DFABRIC_STORAGE** variable.
* If the default path in the config.ini is updated to another path, the cache will always be located according to the config.ini regardless of the LU Schema property setting in the Fabric Studio.

Note that these changes become effective in the LU Schema only after Fabric is restarted.

### LIST Command

The **LIST LU_TYPES / LIST LUT** command is used to list the Logical Unit types and can display an LU's storage type. This command can be invoked for all deployed Logical Unit types or for a specific one.

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



