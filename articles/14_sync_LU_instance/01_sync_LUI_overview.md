# Sync of a Logical Unit Instance - Overview

The Sync mechanism synchronizes data between data sources and the Fabric database. During a Sync, extraction and transformation processes are executed on an [LU Instance (LUI)](https://github.com/k2view-academy/K2View-Academy/wiki/Fabric-Glossary#lui) whereby data is retrieved from the source systems and then loaded into the Fabric database. 
 
**A sync on an LUI can be initiated in two modes**:
* Proactive Sync , synchronizing LUI proactively by interacting with a CDC tool that feeds Fabric with data changes from source systems. This solution is based on an initial load process that sets a baseline for the data in Fabric before the feeds are received from the CDC tool. 

  A Proactive approach is required in huge systems that have millions of database changes per second with many CSRs simultaneously querying Fabric and requesting fresh data. The CSR must receive a quick response and cannot wait for the data to be refreshed online from source systems. Synchronizing database changes proactively into Fabric ensures a much faster response time to CSR requests for data. 
* Lazy (on-demand) Sync, synchronizing an LUI on demand  . For example, a data sync is initiated for Customer 123 when a CSR in a Call Center triggers a Fabric WS call requesting fresh data for Customer 123. Fabric only updates data in Customer 123 upon request when the data is needed by the user. A Lazy Sync is initiated based on a predefined [Sync method](https://github.com/k2view-academy/K2View-Academy/wiki/Sync-Methods) on the LU and the [Sync mode](https://github.com/k2view-academy/K2View-Academy/wiki/Sync-Modes).

  A Sync on an LUI (like Customer) executes extraction and transformation logic on the LU Tables and then populates them in the LUI. Both LU population  objects and enrichment functions  are executed in a Sync process. 




[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/02_sync_modes.md)
