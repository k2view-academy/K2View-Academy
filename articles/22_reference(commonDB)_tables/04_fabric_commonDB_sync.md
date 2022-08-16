# CommonDB - Cluster Synchronization Overview


## Overview
Replications of Reference tables are kept within each Fabric Node, therefore Fabric CommonDB architecture has been designed to answer the following requirements:

- Manage the synchronization process with their source, either automatically in the background, on-demand at any given time, or according to a specific schedule.
- Ensure high availability levels at all times with the lowest access time possible.

CommonDB consists of a single SQLite file that holds all the Reference tables populated by external sources or from Fabric System of Records (SOR).

This means that in a distributed Fabric Cluster environment, each Fabric node contains all common tables within this specific SQLite file. 



## Replication Use Case

Let’s consider a Sales Manager who has just closed a case whereby a new case entry has been created in the Billing database. In this update a new email address has been added to the customer’s details. The email provider’s details are saved in a Reference table featuring all email providers from all customers.

All other Fabric nodes must synchronize the reference table so as to provide other sales managers with the most updated list of email providers.  

Moreover, what happens when two sales managers both make changes on the same row and entry in the same Reference Table?

Fabric provides a resilient mechanism to unsure that the most updated data is always available and distributed across all Fabric nodes in the same cluster. 


## Examples

Multiple transactions are made on a common table with multiple nodes executing transactions on copies of the same local CommonDB file. The first node to commit the changes is the first node to update the table.

Let's assume a simple case: 2 nodes are modifying the same Reference Table, each saved locally. 

As will be explained later, Kafka queues are used as the transaction broker common between all nodes. 

In a way each Kafka queue plays the role of a virtual table that publishes all transaction updates, and from which each node reads the transactions of all nodes (including its own) to physically update its own local copy of the CommonDB SQLite file.

- Node 1 and Node 3 wish to modify table T5 of CommonDB with an update on the same row, but with different values:

  - Node 3 wants to update T5 on Row 10 with value = 3
  - Node 1 wants to update T5 on Row 10 with value = 1
  - Assuming that table T5 is in-sync across all nodes, if Node 3 commits its update (on T5, Row 10, Value=3) first then table T5 will be first updated by Node 3 and all other nodes will subsequently update their tables using the message published by Node 3. 
  - Then, all nodes will re-update their tables using the message published by Node 1.

- Node 1 and Node 3 wish to modify table T5 of CommonDB with a different update.
  - Assuming that table T5 is in-sync across all nodes, all nodes will catch up with the update messages published by Node 1 and Node 3, starting with the update that was first committed.


Note:

The Node originating the update will also update its own CommonDB table by reading the very same message it published to Kafka and by using the updated data available on Kafka's message.

  
  


[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/03_fabric_commonDB_runtime.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/05_commonDB_sync_modes_and_flow.md)


