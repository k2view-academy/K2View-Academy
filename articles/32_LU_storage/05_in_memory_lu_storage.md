# In-memory Working Cache

### Overview

Fabric is a distributed data management system, designed to get, transform, store and expose data for real-time operational needs. An LU Instance is synchronized between data sources and the Fabric database, and can be exposed to external systems. The response time of each MicroDB retrieval is highly important as Fabric can receive multiple concurrent requests.

One of the optimizations to the Fabric cache mechanism is the ability to save the MicroDB in Fabric memory instead of saving it to SQLite files. The advantage of keeping the LU Storage in memory is the consequent prevention of waiting time caused by contention, where two Fabric sessions are simultaneously trying to access the same MicroDB. The configuration steps are described further in this article. 

The in-memory cache mode is best to be used in a project where an average MicroDB sync time is considerably low (comparing to the time of ATTACH/DETACH), while the concurrency level of the calls to Fabric is extremely high.

The decision whether to move to an in-memory cache mode depends also on the operational requirements, such as number of CPUs, memory capacity enough for MicroDB storage, etc.

Fabric allows you to configure how the MicroDB cache is stored using the CACHE_TYPE parameter in config.ini. The supported values are:

FILES_CACHE (default) – stores the MicroDB cache on disk as SQLite files.

MEMORY_NO_CACHE – stores the MicroDB cache in memory.

Starting with Fabric 8.5, a new CACHE_TYPE value, HYBRID_NO_CACHE, is available. This option is designed for data products that contain a mix of small and large MicroDBs, optimizing both performance and memory utilization.

When CACHE_TYPE is set to HYBRID_NO_CACHE, Fabric determines where to store each MicroDB cache based on its size. The size threshold is configured using the HYBRID_MEMORY_THRESHOLD parameter in the [fabricdb] section of config.ini.

For new MicroDBs, Fabric initially caches the MicroDB in memory. As the MicroDB grows, Fabric continuously monitors its size. If the MicroDB reaches or exceeds the configured threshold, the cache is automatically migrated from memory to disk and continues to be maintained as an SQLite file.

For existing MicroDBs, Fabric evaluates the current MicroDB size when it is loaded. If the MicroDB is already greater than or equal to the configured threshold, its cache is created directly on disk as an SQLite file.

If the MicroDB size is less than the configured threshold, its cache is stored in memory. If the MicroDB size is greater than or equal to the configured threshold, its cache is stored on disk as an SQLite file.

The default value of HYBRID_MEMORY_THRESHOLD is 1073741824 (1 GB, in bytes).
