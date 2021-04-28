# Big Logical Units

There is a 2G limitation in Cassandra for writing compressed SQLite LUI files into a Cassandra **entity** table as blobs. Fabric enables storing big LUIs without limitations on size by splitting data into chunks. The SQLite file's chunks are written into the Cassandra **entity_chunks** table in parallel using the [Cassandra Loader](/articles/28_cassandra_loader/01_cassandra_loader_overview.md). The Loader configuration for the parallel save can be done using the [config.ini](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) by adding a section named **[LU Name]_ cassandra_entity_storage** per each LU. The parameters under this section are the same as the Cassandra Loader definition parameters (for example, Loader execution mode).

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

* INSTANCE_CHUNK_SIZE, impacting the read from Cassandra (pagination) and write into Cassandra chunk size (default 10M – 10485760 bytes).



[![Previous](/articles/images/Previous.png)](02_storage_management.md)

