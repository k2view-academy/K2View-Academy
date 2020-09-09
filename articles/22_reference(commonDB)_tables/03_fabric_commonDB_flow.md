# CommmonDB


## Overview
Fabric CommonDB architecture has been designed to answer the following requirements:

- Keep reference tables in each node
- Manage their synchronization with the source, either automatically in the background or on-demand according to a specific schedule.
- Ensure high availability levels at all times with the lowest access time possible.

## Properties

CommonDB consists of a single SQLite file that contains all the common reference tables that have been designed during their creation process. 
This means that in a distributed environment (Fabric Cluster) each Fabric node contains all common tables within this single file. 
As a rule of thumb, reference tables updates are managed in Kafka, while snapshot updates will be managed via Cassandra.
