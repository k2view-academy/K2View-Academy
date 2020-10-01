# CommonDB - Synchronization Overview


## Overview
Copies of Reference tables are kept within each Fabric Node, therefore Fabric CommonDB architecture has been designed to answer the following requirements:

- Manage the synchronization process with their source, either automatically in the background, on-demand at any given time, or according to a specific schedule.
- Ensure high availability levels at all times with the lowest access time possible.

CommonDB consists of a single SQLite file that contains all the Reference Tables that have been populated from External Sources or from Fabric System of Records (SOR).

This means that in a distributed environment (Fabric Cluster) each Fabric node contains all common tables within this single SQLite file. 



## Synchronization Use Cases

*Case 1 - Scheduled Sync*

A system might need to operate a synchronization process every day at 2 AM as a result of a maintenance task - i.e. all new customers, or new transactions created over the last 24 hours. In this case, a recurring background sync will be scheduled for this specific time.

*Case 2 - Pro-Active Sync*

A customer service operative needs get the most updated list of new services subscribed by a customer in real-time. In this case the web-service or job request will trigger the  ref_sync and ref_sync_wait commands described [here](/articles/22_reference(commonDB)_tables/03_fabric_commonDB_runtime.md#ref_sync-lu_namelu-name-tablesall-or-table-1table-2etc-forcetruefalse).

*Case 3: - Ongoing Background Sync* 

A sales manager is closing the case he just treated - resulting in creating a new case entry in the Billing Database. 
Assuming the customer provided a new email address whereby the email provider details are kept on a Reference Table (featuring all email providers from all customers), all nodes will need to synchronize the reference table so to provide the most updated list of providers to other sales managers.  


## Examples

In cases of multiple transactions on a given common table (multiple nodes execute transactions with their local commonDB file copy) , the first node to commit the change will also updates the table.

Let's assume a simple case: 2 nodes are modifying the same Reference Table, each one kept locally. 

As will be explained later, Kafka queues are used as the transaction broker common between all nodes. 

In a way each Kafka queue plays the role of a virtual table on which all transactions updates are published, and from which each node reads the transactions of all the nodes (including its own) to physically update its own local copy of commonDB SQLite file.  


- Node 1 and Node 3 wish to modify table T5 of commonDB with an update on same row, but with different value:

  - Node 3 wants to update T5 on Row 10 with value = 3
  - Node 1 wants to update T5 on Row 10 with value = 1
  - Assuming that table T5 is in-sync across all nodes, if Node 3 commits its update (on T5, Row 10, Value=3) first then table T5 will be first updated by Node 3 and all other nodes will subsequently update their tables using the message published by Node 3. 
  - Then, all nodes will re-update their tables using the message published by Node 1.

- Node 1 and Node 3 wish to modify table T5 of commonDB with a different update.
  - Assuming that table T5 is in-sync across all nodes, all nodes will catch up with the update messages published by Node 1 and Node 3, starting with the update that was first committed.


Note:

The Node originating the update will also update its own commonDB table by reading the very same message it published on Kafka and by using the update data available on Kafka's message.

  
  


[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/03_fabric_commonDB_runtime.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/05_commonDB_sync_modes_and_flow.md)


