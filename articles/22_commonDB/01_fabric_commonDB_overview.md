# **Fabric CommonDB Overview** 

CommonDB is an additional SQLite database used for storing reference tables common to all MicroDB. 
For example, it could be a table storing a list of objects to which all MicroDB schemas point to, such as detailed information about a specific set of services to which all LUIs subscribe or a geo-code table that identifies customers' zip addresses. 

In a distributed system, one copy of commonDB is stored on each one of the Fabric nodes involved. Fabric handles their cross-synchronization, and ensures the local CommonDB SQLite file is always available for queries within each Fabric session. 
This enables writing JOIN clauses between Common tables and any MicroDB using one SQL query only from the same node, thus providing instantenous data access and computing resources to such queries.  

 # **What is a Reference Table ?** 
A Reference table is an SQLite table containing metadata that is referenced by different LU instances of a specific LU, by instances from a different LU or by Web Services.
In Fabric Studio, the Reference tab displays a list of the common Reference tables defined in a project.
To ensure that a Reference table in an LU schema is always synched, check that the Reference table is checked-in in the References section of the LU schema tab on the right-hand pane. 
When Fabric synchronizes any LU instance, it first searches for the checked Reference tables, checks if they need to be synchronized and then synchronizes them. 


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md) 
