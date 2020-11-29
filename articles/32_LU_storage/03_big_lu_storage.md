# Big Logical Units

There is a 2G limitation in Cassandra for writing compressed SQLite LUI files into a Cassandra **entity** table as blobs. Fabric enables storing big LUIs without limitations on size by splitting data into chunks. The SQLite file's chunks are written into Cassandra **entity_chunks** table in parallel. The data is first written into the **entity_chunks** table and then the **entity** table is populted. 

The **entity** table includes the following data:

* batch_id, unique ID that represents the relationship between an **entity** and **entity_chunks** tables.
* chunks_count, number of chunks.
* For performance optimization during  parallel data inserts, the size is always zero if the  number of chunks > 1, .

The **entity_chunks** table includes the following data:

* id, holds the instance ID.
* sync_version, holds the same version as the version populated in the **entity** table. 
* batch_id, holds the same ID as in the **entity** table.
* chunk_index, holds the chunk number.
* data, holds the split SQLite file after compression for the chunk index.

The chunk size is set using the config.ini file parameters, defined per node:

* INSTANCE_CHUNK_SIZE, impacting the read from Cassandra (pagination) and write into Cassandra chunk size (default 10M – 10485760 bytes).
* MAX_SAVE_MEMORY_USAGE, total memory to be consumed when loading LUIs into memory in parallel (default 200M) – according to default configuration, a maximum of 20 chunks can be loaded into memory per node in parallel, other chunks will wait in the queue. 



[![Previous](/articles/images/Previous.png)](02_storage_management.md)

