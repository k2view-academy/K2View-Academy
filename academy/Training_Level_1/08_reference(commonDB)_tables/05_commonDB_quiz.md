# Reference Tables & commonDB Quiz

## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png)
Excellent! 
You have completed the commonDB items.


Take the following Quiz to see what you have learnt. 

The Quiz consists of of multiple-choice questions, each providing a number of possible answers. 

Click the answer you think is correct. 


#### Question 1: Reference Tables

Reference Tables are:

A: Tables keeping data within SQLlite in the LUI microDB.

B: Oracle tables used as data model references to create a new LU.

C: Statistics and maintenance tables, belonging to the LU schema .


(**Solution 1: A**).


#### Question 2: Reference Tables Creation

New Reference Tables can be created from:

A: Within the LU schema.

B: Within the Reference folder in Fabric Studio (and only from there).

C: Cassandra CQLSH command line 

D: Fabric runtime console.

E: All of the above

(**Solution 2: B**).


#### Question 3: commonDB Type

commonDB is a Fabric database of the following type:

A: Cassandra.

B: mySQL.

C: SQLite 

D: postGrSQL.

(**Solution 2: C**).


#### Question 4: commonDB Location

How many copies of commonDB are there in a Fabric Cluster?

A: One only (it is common duhhh)

B: One per Fabric node.

C: Multiple - it is configurable depending on the need. 

D: One per LUI schema - a copy of commonDB is kept in each instance

(**Solution 2: B**)


#### Question 5: commonDB Synchronization

commonDB tables are synchronized across the Fabric Cluster (between nodes) ...

A: Each time a change occurs in one of the tables.

B: Once day at a given time.

C: According to a specific schedule.

D: Upon user request only.

E: All of the above

(**Solution 2: A**)


#### Question 6: commonDB Synchronization

commonDB tables can be synchronized from their external sources ...

A: Once a day at a given time.

B: According to a specific schedule (CRON or functions).

C: Upon user request only

D: All of the above

(**Solution 2: D**)


#### Question 7: commonDB Runtime Status Commands

Information on all reference tables synchronization status, can be displayed using the following command:

A: STATUS_REF ALL;

B: REF_STATUS TABLES=’ALL’;

C: REF_STATUS TABLES=’ALL’;

(**Solution 2: B**)


#### Question 8: commonDB Runtime Status Commands

Information on all reference tables synchronization status, can be displayed for the following scopes:

A: Tables;

B: Populations;

C: Both tables and populations;

(**Solution 2: C**)

[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/08_reference(commonDB)_tables/04_commonDB_solutions.md)

------
