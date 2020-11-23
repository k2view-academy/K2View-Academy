# Logical Unit Storage Overview

A [Logical Unit (LU)](/articles/03_logical_units/01_LU_overview.md) is a blueprint holding a set of definitions / instructions used to create and maintain the data of a business entity (like a customer).

Fabric uses Cassandra DB as a Logical Unit's [storage layer](/articles/02_fabric_architecture/01_fabric_architecture_overview.md#21-fabric-storage) where each business entity's instance is saved as a [MicroDB](/articles/01_fabric_overview/02_fabric_glossary.md#mdb--microdb) in an **entity** or **entity_chunks** table under Cassandra **k2view_[LU_name]** keyspace.  

[Click for more information about Cassandra keyspaces](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md#cassandra-keyspaces-for-fabric).

### Storage Types

The storage mechanism of the Logical Unit depends on the setting of the LU Schema's **Storage** property. There are three Storage types:

* **Default**, inherit the storage type definition of **[fabricdb]** section in the config.ini file.
* **None**, do not store the instance in the Cassandra after the GET command retrieves the instance data from the source DB. 
* **Cassandra**, store the instances in the Cassandra DB.

### Storage Process

The [GET](/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands) command is used to synchronize a specific instance ID (one or several) from the source systems to Fabric and to load it into the Fabric's memory. 

During GET, Fabric either retrieves the data from the source if Sync is needed, or brings the latest version of the LUI from Cassandra DB. The LUI version is kept in **k2view_[LU_name].entity** table together with the corresponding MicroDB.



![image](images/32_01_diagram.PNG)





