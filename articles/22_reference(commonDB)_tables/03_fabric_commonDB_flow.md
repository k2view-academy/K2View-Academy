# CommmonDB - Architecture and Flow


## Overview
Fabric CommonDB architecture has been designed to answer the following requirements:

- Keep reference tables in each node
- Manage their synchronization with the source, either automatically in the background or on-demand according to a specific schedule.
- Ensure high availability levels at all times with the lowest access time possible.

## Properties

CommonDB consists of a single SQLite file that contains all the common reference tables that have been designed during their creation process. 
This means that in a distributed environment (Fabric Cluster) each Fabric node contains all common tables within this single file. 
As a rule of thumb, reference tables updates are managed in Kafka, while snapshot updates will be managed via Cassandra.

## Synchronization modes

Fabric provides 2 different modes to synchronize reference tables data.

### Update Mode: 
For cases where an update from a reference table is needed. @ types of transactions can be differentiated 
Short or long message (kafka vs Cassandra)
In this mode, update are performed in ways of Create/Update/Delete SQL queries directly on the table


### Snapshot Mode
For cases where synchronizing the entire table is needed


The Snapshot will only be published once one of the 

o	the full table is initialized by job
o	Sync time arrived
o	Manually : begin -> delete from table w/o where statements -> means send update as snapshot 

•	Snapshot is published both to update topic – header only and to Cassandra table – data.
•	Header (published to the update topic) contains:
o	UUID
o	Is required (or optional)
•	Cassandra table row contain
o	Uuid – unique per snapshot.
o	Table name
o	message id (from 0 upwards)
o	Data
	List of updates (as for regular update message).
	The size of list set by default using configuration parameter, but user can change it for any specific snapshot. 


## Common Area table initialization
When a new node is online, and joins the current cluster, we need to bring the common tables to this node.  
Option 1: from kafka
Goes to kafka to look for a snapshot for 2 possible reasons:
-	table that is sync-ed every x time & snapshot is available
-	snapshot already created for another node
Option 2: from another node
-	no snapshot is available, then New node asks for a snapshot and waits by listening Kafka
-	another node prepareds snapshot and puts it either in kafka(small) or cassandra(big)
o	manual command ? maybe new feature
o	startup/recovery process 

## Use Cases
Studio
Existing Reference list used by LU has only meaning on first / force sync (see Advanced features).
-	First time ever Reference Table for node, get of LUI will wait first for Ref Table to re-sync -> if reference needed
-	Sync from reference and then GET – ref_sync and then GET in same session for cases where I need most updates ref table

## Deploy process
1.	Stop all running table sync jobs.
2.	Create table / index on 'Common' 
3.	Clear Kafka Consumer / Topic if table was removed.
4.	Create a Kafka topic (if configured Kafka mode)
5.	Create a Kafka consumer for each node.
Start sync jobs (even if deploy failed) so not to prevent existing sync-ing although the ref table deploy failed
Apply existing config parameters (Sync job retry interval; ) on coordinator server (one addressed by deploy) on sync jobs

## Sync
If truncate applied 
•	Start snap
•	Add all rows to the snap
•	If any error or exception rollback 
•	Else commit – in a single transaction:
o	Create indices on the temporary table.
o	Drop an old reference table.
o	Rename the temporary table.
If no truncate defined 
•	Run updates for table (note, no transactions are available at this stage)
•	batch of transactions is available -> everything in one commit, unless failures (1 by 1)

## Configuration

Config.ini
•	Uses Cassandra for snapshot storing; same Cassandra definition; separate keyspace for long messages
•	Kafka consumer and producer setting - two sections. + 2 SSL parameters sections for consumer and producer 
•	Reference commonDB sqlite files location 
•	Operation mode one of (Kafka , Memory )
•	Mode for PoCs w/o kafka dependency (no data persistency) (achi)

