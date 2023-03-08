# Sync LUI - Overview

The Sync mechanism synchronizes data between data sources and the Fabric database. During the sync, extraction and transformation processes are executed on an [LU Instance (LUI)](/articles/01_fabric_overview/02_fabric_glossary.md#lui) whereby data is retrieved from the source systems and then loaded into the Fabric database. 

**A sync on an LUI can be initiated in two modes**:

* Proactive sync, synchronizing LUI proactively by interacting with a CDC tool that feeds Fabric with data changes from source systems. This solution is based on an initial load process that sets a baseline for the data in Fabric before the feeds are received from the CDC tool. 

  A Proactive approach is required in huge systems that have millions of database changes per second with many CSRs simultaneously querying Fabric and requesting fresh data. The CSR must receive a quick response and cannot wait for the data to be refreshed online from source systems. Synchronizing database changes proactively into Fabric ensures a much faster response time to CSR requests for data. 

* Lazy sync, synchronizing an LUI by request. For example, a data sync is initiated for Customer 123 when a CSR in a Call Center triggers a Fabric WS call requesting fresh data for Customer 123. Fabric only updates data in Customer 123 upon request when the data is needed by the user. A Lazy sync is initiated based on a predefined [sync method](/articles/14_sync_LU_instance/04_sync_methods.md) on the LU and the [sync mode](/articles/14_sync_LU_instance/02_sync_modes.md).

  A sync on an LUI (like Customer) executes extraction and transformation logic on the [LU tables](/articles/06_LU_tables/01_LU_tables_overview.md) and then populates them in the LUI. Both LU [population objects](/articles/07_table_population/01_table_population_overview.md) and enrichment functions are executed in a Sync process. 

  Lazy sync can be initiated either by the explicit [GET command](/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands) or by querying LUI without GET when [AUTO_MDB_SCOPE is set to True](articles/02_fabric_architecture/04_fabric_commands.md) on a session level.




[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/14_sync_LU_instance/02_sync_modes.md)
