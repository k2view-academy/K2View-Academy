# LU Storage and Cache Management

The Sync mechanism synchronizes data between data sources and the Fabric database. During a synchronization, extraction and transformation processes are executed on an [LU Instance (LUI)](/articles/01_fabric_overview/02_fabric_glossary.md#lui) whereby data is retrieved from source systems using the GET command and then loaded into the Fabric database.

Since Fabric receives multiple requests concurrently, the GET command's response time is highly important. To optimize the LUI retrieval process, Fabric uses a cache mechanism that has a predefined size limit which enables loading an instance into the memory faster.

The size and location of the cache on the Fabric server are defined in the **[fabricdb]** section of the config.ini file.

The size of the cache is limited and can be updated if needed. Note that more disk space is required when the cache size is increased.


### Defining the Storage and Location of the Cache in the config.ini

The following variables in the **[fabricdb]** section of the **config.ini** are used to define the permanent storage and location of the cache in the Fabric server:

- MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE, holds the storage type:
  
  - The default and most commonly used value is **Cassandra**. 
  
    ~~~
    #MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=CASSANDRA
    ~~~
  
  - When the storage type in the config.ini is set to **NONE**, the instance is not saved to Fabric storage.
  
  - As experimental feature on Fabric 6.5.2, it is also possible to define S3 or AZURE_BLOB_STORE as the storage type.
  
- MDB_DEFAULT_CACHE_PATH, holds the location of the Fabric MDB cache files on the server.

  - The default path is set in the config.ini and can be changed if required.

    ~~~
    #MDB_DEFAULT_CACHE_PATH=/dev/shm/fdb_cache
    ~~~

- MDB_DEFAULT_SCHEMA_CACHE_SIZE, defines the size limit of the cache in bytes for MicroDB instances. 

### Changing the Location of the Cache 
Fabric enables you to modify the default location of the cache per Logical Unit using the MDB_DEFAULT_CACHE_PATH variable in the **[fabricdb]** section of the **config.ini** and the [LU Schema's **Cache Location** property](/articles/03_logical_units/04_LU_properties.md) as follows:

* When the Cache Location is set to **Default**, store the cache per the definition of MDB_DEFAULT_CACHE_PATH in config.ini. The default value in config.ini is **/dev/shm/fdb_cache/<LU_name>** but it can be changed to another location. 
* When the Cache Location is set to **Storage**, store the cache under the storage folder in Fabric **${FABRIC_STORAGE}/storage/fdb_cache/<LU_name>** if this location exists. If this location does not exist, store the cache in **${FABRIC_HOME}/storage/fdb_cache/<LU_name>**.
  * The definition of the **${FABRIC_STORAGE}** path can be updated via the **fabric-server-start.sh** script using the **-DFABRIC_STORAGE** variable.
* If the default path in the config.ini is updated to another path, the cache is always located according to the config.ini regardless of the LU Schema property setting in the Fabric Studio.

Note that both the config.ini and the Fabric Studio configuration changes become effective only after Fabric is restarted. On the first LU deployment the restart is not required.

 

[![Previous](/articles/images/Previous.png)](01_LU_storage_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_big_lu_storage.md) 



