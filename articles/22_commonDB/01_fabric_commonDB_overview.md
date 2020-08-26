# **Fabric Common Table Overview** 

CommonDB is an additional SQLite database schema used for storing reference tables common to all MicroDB. 

For example, it could be a table storing a list of objects to which all MicroDB schemas point to. 

In a distributed system, one copy of each reference table is stored on each node. Fabric handles their synchronization across nodes.

CommonDB is always made available for queries within every Fabric session. This enables writing JOIN clauses between Common tables and any MicroDB using one SQL query only.

 # **What is a Fabric Job ?** 
A Fabric Job process can be exposed across Fabric nodes and be run to execute scripts, flows or functions according to a specific schedule or once only.

A Job must be deployed to Fabric so that it can be invoked either by the node onto which it has been deployed or by any other Fabric nodes allocated by Cassandra distribution engine. 

Job functions can be defined in the Fabric Studio, saved to the project file and be deployed to the Fabric server.

For more information, click:
- Different types of jobs, their mechanism and lifecycle.
- Defining Jobs from the Fabric Studio.
- Invoking and managing Jobs from the Fabric Runtime console.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md) 
