# **Fabric CommonDB and Reference Table Overview** 

A Reference table is an SQLite table containing data that is used by different instances of a specific LU or of multiple LUs, Web Services and other Fabric objects like Jobs or Broadway flows.
For example, a Reference Table can be:

- A table storing a list of objects to which all MicroDB Schemas point to, 
- Detailed information about a specific set of services to which all LUIs subscribe, 
- A geo-code table that identifies customer zip addresses, 
- A report of number of customers per customer type. 

In Fabric Studio, the Reference tab displays a list of the common Reference tables defined in a project. It is strongly recommended to ensure that the Reference table is attached to an LU Schema by ticking on the appropriate Reference table in the References section of the LU Schema tab in the right panel. 
By attaching the reference table to the LU, we ensure that when fetching any instance for the first time, the process will always wait for the reference table to be populated.

### **Reference Tables Types**
#### Type 1:
Reference Table that can be synchronized from External Sources by defining a population in Fabric Studio. 

#### Type 2:
Reference Table that can be populating by using Fabric transactions. In this specific case, no population appears in Fabric Studio. 


# **Where are Reference Tables Stored?**

CommonDB is an SQLite database used for storing all Reference Tables.

In a distributed system, a copy of the CommonDB is stored on each Fabric Node.
Fabric handles their cross-synchronization and ensures that the local CommonDB SQLite file is always available for queries from within each Fabric session. 

This enables writing JOIN clauses, locally, between any common table and any LUI using regular SQL queries, thus providing instantenous data access and local computing resources to these queries. 


 
[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/02_reference_table_fabric_studio.md) 

