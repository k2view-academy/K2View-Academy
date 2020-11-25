# Big LU Storage

There is a 2G limitation on writing the compressed SQLite LUI files into the Cassandra **entity** table as blob. Fabric allows storing big LUIs with no limitation on sizing by splitting the data into chunks. The SQLite file's chunks are written into Cassandra **entity_chunks** table in parallel. First, the data is written into the **entity_chunks** table. After it's completed, the **entity** table is populated.

The **entity** table includes the following data:

* batch_id, unique ID that represents the relation between **entity** and **entity_chunks** tables.
* chunks_count, number of chunks.
* data, always size zero if number of chunks > 1, for performance optimization during the parallel inserts.

The **entity_chunks** table includes the following data:

* id, holds the instance ID.
* sync_version, holds the same version as version populated in **entity** table. 
* batch_id, holds the same ID as in **entity** table.
* chunk_index, holds the chunk number.
* data, holds the split SQLite file after compression for chunk index.

The chunk size is set using the config.ini file parameters, defined per node:

* INSTANCE_CHUNK_SIZE – impacting the read from Cassandra (pagination) and write into Cassandra chunk size (default 10M – 10485760 bytes).
* MAX_SAVE_MEMORY_USAGE - total memory to be consumed when loading LUIs into memory in parallel (default 200M) – according to default configuration, maximum 20 chunks can be loaded into memory per node in parallel, other chunks will wait in the queue.



[![Previous](/articles/images/Previous.png)](02_storage_management.md)

