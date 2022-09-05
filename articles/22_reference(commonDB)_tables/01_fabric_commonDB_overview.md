# Fabric CommonDB and Reference Table 

## Overview 

A Reference table is an SQLite table containing data that is used by different instances of a specific LU or of multiple LUs, Web Services and other Fabric objects like Jobs or Broadway flows.
For example, a Reference Table can be:

- A table storing a list of objects to which all MicroDB Schemas point to. 
- Detailed information about a specific set of services to which all LUIs subscribe. 
- A geo-code table that identifies customer zip addresses. 
- A report of number of customers per customer type. 

In Fabric Studio, the Reference tab displays a list of the common Reference tables defined in a project. It is strongly recommended to ensure that the Reference table is attached to an LU Schema by ticking on the appropriate Reference table in the References section of the LU Schema tab in the right panel. 
By attaching the reference table to the LU, we ensure that when fetching any instance for the first time, the process will always wait for the reference table to be populated.

## Reference Tables Types
#### Type 1:
Reference Table that can be synchronized from External Sources by defining a population in Fabric Studio. 

#### Type 2:
Reference Table that can be populated by using Fabric transactions. In this specific case, no population appears in Fabric Studio. 


## Where Are Reference Tables Stored?

A CommonDB schema is an SQLite database used for storing Reference Tables. Up to Fabric release 6.5, all reference tables were stored under the same schema - i.e. under the same SQLite file. 

Since Fabric release 6.5.3 it is possible to store reference tables in separate schemas, therefore all CommonDB databases and their associated tables can be dissociated one from another. 

When a reference table is stored, a new SQLite file, bearing the name of the schema specified in the [Reference Table Property](/articles/22_reference(commonDB)_tables/02_reference_table_fabric_studio.md#reference-tables-properties), is created and can also be shared by more reference tables.

Using this reference table segregation schema reduces considerably locking instances of reference tables that usually happens during multiple write processes.

In a distributed system, a copy of all the CommonDB and other *reference tables schemas* are stored on each Fabric Node. Fabric handles their cross-synchronization and ensures that the local CommonDB SQLite files are always available for queries from within each Fabric session. 


This enables writing JOIN clauses, locally, between any common table and any LUI using regular SQL queries, thus providing instantaneous data access and local computing resources to these queries. 



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_reference_table_fabric_studio.md) 

