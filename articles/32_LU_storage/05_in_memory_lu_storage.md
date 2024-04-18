# In-memory LU Storage

### Overview

Fabric is a distributed data management system, designed to get, transform, store and expose data for real-time operational needs. An LU Instance is synchronized between data sources and the Fabric database, and can be exposed to external systems. The response time of each LUI retrieval is highly important as Fabric can receive multiple concurrent requests.

One of the optimizations to the Fabric storage mechanism is the ability to save the LUI data in Fabric memory instead of saving it to SQLite files. The advantage of keeping the LU Storage in memory is to prevent the waiting time caused by contention, when two Fabric sessions are trying to access the same LUI simultaneously. The configuration steps are described further in this article. 

The in-memory storage mode is best to be used in a project where an average LUI sync time is considerably low (comparing to the time of ATTACH/DETACH), while the concurrency of the calls to Fabric is extremely high.

The decision whether to move to in-memory storage mode also depends on the operational requirements such as: are there multiple CPU, is there enough memory for LUI storage, etc.

### Configuration

To utilize this feature, the new parameter CACHE_TYPE in the config.ini file should be set to MEMORY_NO_CACHE (the default is set to FILES_CACHE).

