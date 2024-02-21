# Stream Sync Overview

## What is Stream Sync?

**Stream Sync** is a Fabric module which enables keeping Fabric synchronized with source systems by processing only the changes in the source system, without the need to re-synchronize the entire instance on every change in the source.

The Stream Sync job runs on a Fabric server and receives the Insert, Update and Delete transactions from the source system via a pre-defined PubSub interface. The job identifies which Instance ID is impacted by the change and updates it in the relevant tables of the Fabric DB. 

For example, the Oracle’s Golden Gate system publishes messages with the data updates which occurred in the source Oracle DB table to Kafka. The Stream Sync listens to Kafka and saves the changes to internal tables, in order to process them and update the instances in Fabric.

The Stream Sync has a sophisticated algorithm that can identify whether the received data updates are incomplete or missing logical relationships due to the out-of-order arrival. If the received data is invalid or incomplete, it is handled differently from the case when the received data is valid and complete.

## iidFinder vs Stream Sync

Starting from V8.0, Fabric provides two solutions for the Fabric DB synchronization with the source system changes:

*  **iidFinder** is a legacy solution relevant for Fabric over SQLite operational DB. Its configuration is based on iifConfig.ini and it runs as a separate process.
* **Stream Sync** is a new solution introduced in V8.0. It is relevant for FabricDbOne over PostgreSQL only. It is configured via the config.ini file and it runs as a Fabric job.

Both solutions use the same resources, such as:

* IID Finder folder under the Logical Unit folder in the Studio
* IID Finder tab in the LU Schema properties
* Several specific input parameters in the **SourceDbQuery** Actor (used in the population flows)

The articles of this section describe the **Stream Sync** solution.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_stream_sync_architecture.md) 