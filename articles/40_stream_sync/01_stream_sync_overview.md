# Stream Sync 

## Overview

Fabric Sync is a mechanism that synchronizes data between the data sources and Fabric by extraction and transformation processes that are executed on an [LU Instance (LUI)](/articles/01_fabric_overview/02_fabric_glossary.md#lui). Fabric Sync can be performed in 2 modes:

* Synchronization by request, which is based on predefined sync method and sync mode. [Click here for more information about Sync LUI by request, and various sync methods and sync modes](/articles/14_sync_LU_instance/01_sync_LUI_overview.md).
* Proactive sync. A Proactive approach is required in very large systems where millions of database changes occur per second, and numerous CSRs simultaneously query Fabric and request fresh data. A CSR must receive quick responses and should not be delayed due to an online data refreshing from source systems. The proactive sync mode ensures a much faster response time to external data requests, as it does not rely on an online refresh, since the data is always synchronized, as explained below.

## What is Stream Sync?

**Stream Sync** is a Fabric module, which enables a proactive synchronization of Fabric with source systems by processing only the changes in the source system, without the need to re-synchronize the entire instance on every change in the source.

The Stream Sync job runs on a Fabric server and receives the Insert, Update and Delete transactions from the source system via a pre-defined PubSub interface. The job identifies which Instance ID is impacted by the change and updates it in the relevant tables of the Fabric DB. 

For example, the Oracle GoldenGate system publishes messages with the data updates that occur in the source Oracle DB table to Kafka. Stream Sync then listens to Kafka and saves the changes in internal tables in order to process them and to update the instances in Fabric.

The Stream Sync has a rather sophisticated algorithm, capable of identifying whether the received data updates are incomplete or missing logical relationships due to an out-of-order arrival. Invalid or incomplete received data is handled differently from valid and complete received data.

## IID Finder vs. Stream Sync

Starting from V8.2, two solutions are provided for the Fabric DB synchronization with the source system changes:

*  **IID Finder**, which is a legacy solution, relevant for Fabric over SQLite operational DB. Its configuration is based on iifConfig.ini and it runs as a separate process.
*  **Stream Sync**, which is a new solution introduced in V8.2. It is relevant for [Business Entity over PostgreSQL only](/articles/32_LU_storage/04_business_entity_on_pg.md). It is configured via the config.ini file and it runs as a Fabric job.

Both solutions use the same resources, such as:

* IID Finder folder under the Logical Unit folder in the Studio.
* IID Finder tab in the LU Schema properties.
* Several specific input parameters in the **SourceDbQuery** Actor (used in population flows).

The articles of this section describe the **Stream Sync** solution.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_stream_sync_init.md) 
