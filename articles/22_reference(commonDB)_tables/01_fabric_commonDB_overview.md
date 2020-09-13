# **Fabric CommonDB Overview** 

A CommonDB is an additional SQLite database used for storing reference tables common to all MicroDB. 
For example, a table storing a list of objects to which all MicroDB Schemas point to, detailed information about a specific set of services to which all LUIs subscribe, or a geo-code table that identifies customer zip addresses. 

In a distributed system, a copy of the CommonDB is stored on each Fabric node that is involved. Fabric handles their cross-synchronization and ensures the local CommonDB SQLite file is always available for queries within each Fabric session. 
This enables writing JOIN clauses between common tables and any MicroDB using only one SQL query from the same node, thus providing instantenous data access and computing resources to these queries. 


 # **What is a Reference Table ?** 

A Reference table is an SQLite table containing metadata that is referenced by different LU instances of a specific LU, by instances from a different LU or by Web Service.

In Fabric Studio, the Reference tab displays a list of the common Reference tables defined in a project.
To ensure that a Reference table in an LU Schema is always synched, check that the Reference table is checked in the References section of the LU Schema tab in the right pane.

When Fabric synchronizes any LU instance, it first searches for the checked Reference tables, checks if they need to be synchronized and then synchronizes them. 

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/02_add_a_reference_table.md) 

