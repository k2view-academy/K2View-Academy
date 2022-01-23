# Big Logical Units

There is a 2G limitation in Cassandra for writing compressed SQLite LUI files into a Cassandra **entity** table as blobs. Fabric enables storing big LUIs without limitations on size by splitting data into chunks. The SQLite file's chunks are written into the Cassandra **entity_chunks** table in parallel using the [Cassandra Loader](/articles/28_cassandra_loader/01_cassandra_loader_overview.md). 

The Loader configuration for the parallel save can be done using the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) by adding a section named **[LU type]_ cassandra_entity_storage** per each LU. The parameters under this section are the same as the Cassandra Loader definition parameters (for example, Loader execution mode).

The LUI data is first written into the **entity_chunks** table and then after all chunks were written successfully the **entity** table is populated. 

The **entity** table includes the following data:

* batch_id, unique ID that represents the relationship between an **entity** and **entity_chunks** tables.
* chunks_count, number of chunks.
* data, holds the LUI SQLite file after compression. For performance optimization during  parallel data inserts, the size is zero if the  number of chunks > 1.

The **entity_chunks** table includes the following data:

* id, holds the instance ID.
* sync_version, holds the same version as the version populated in the **entity** table. 
* batch_id, holds the same ID as in the **entity** table.
* chunk_index, holds the chunk number.
* data, holds the split SQLite file after compression for the chunk index.

The chunk size is set using the config.ini file parameters, defined per node:

* INSTANCE_CHUNK_SIZE, impacting the read from Cassandra (pagination) and write into Cassandra chunk size (default 10MB – 10485760 bytes).

### Parallel Load Big LUI from Cassandra - Experimental Feature

When loading the chunks of Big LUI from Cassandra to Fabric as part of the GET command, there is a trade-off between the performance of the load and the memory allocated to this process. To improve the performance of the load, you can define the number of threads that will be executed in parallel. When setting the number of threads, you must also define the maximum memory allowed to be used for the parallel load. 

The config.ini parameters to configure the above are:

* ASYNC_LOAD_MAX_THREADS, the maximum number of threads (over all Fabric nodes) to be allocated. Set to 0 by default, which means that by default the parallel load is disabled.
* ASYNC_LOAD_MAX_MEMORY_IN_MB, maximum memory to be allocated for the parallel load process. Set by default to 2000 MB.

### Improve LUI partitioning - Experimental Feature

Another major performance improvement was introduced on release 6.5.4, when dealing with a lot of entity chunks.

The config.ini parameters to configure the above are:

* ENABLE_PARTITIONED_MDB=true
* ASYNC_LOAD_MAX_THREADS bigger than zero

There is no upgrade path for existing projects, need to clean all data in Fabric and bring it back.
It is recommended to turn this feature on only in case of dealing with really big LUIs that split into many chunks.


[![Previous](/articles/images/Previous.png)](02_storage_management.md)

