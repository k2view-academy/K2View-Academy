# Logical Unit Storage Overview

A [Logical Unit (LU)](/articles/03_logical_units/01_LU_overview.md) is a blueprint holding a set of definitions / instructions used to create and maintain the data of a business entity (like a customer).

Fabric uses Cassandra DB as a Logical Unit's [storage layer](/articles/02_fabric_architecture/01_fabric_architecture_overview.md#21-fabric-storage) where each business entity's instance is saved as a [MicroDB](/articles/01_fabric_overview/02_fabric_glossary.md#mdb--microdb) in an **entity** table (and in **entity_chunks** table for [Big LU](03_big_lu_storage.md)) under the Cassandra **k2view_[LU_name]** keyspace.  

[Click for more information about Cassandra keyspaces](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md#cassandra-keyspaces-for-fabric).

### Storage Types

The Logical Unit permanent storage location depends on the setting of the LU Schema's **Storage** property. There are three Storage types:

* **Default**, inherit the storage settings in **[fabricdb]** section of the **config.ini** file.
* **None**, do not store the instance in the Cassandra after the GET  retrieves the instance data from the source DB. 
* **Cassandra**, store the instances in the Cassandra DB after the GET command execution.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_storage_management.md)













