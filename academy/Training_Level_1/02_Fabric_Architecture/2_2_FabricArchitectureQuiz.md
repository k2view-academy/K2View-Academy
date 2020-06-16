# Fabric Architecture Quiz

![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png) 



Excellent! 
You have completed the Fabric Overview and Architecture learning items.

 
Before moving to the next item, let's take the following quiz to see what you have learnt. The Quiz consists of a number of multiple-choice questions, each providing a number of possible answers. Select the answer you think is correct by clicking over it:



#### Question 1: Fabric overview

Working with Fabric will allow you to:


\-  A: Get fast answers to complex questions across data-lakes, such as across-the-board statistics or BI insights.


\-  B: Answer complex questions about a customer or multiple customers in real-time, even when data might be spread across multiple data centers.


\-  C: Create graphical visualizations of data spread across multiple data centers.


(***Solution 1: B; Yes, Fabric is all about specific entities, down to the last bit of information they contain.***)



#### Question 2: Fabric concepts

Logical Units are:


\- A: Hierarchical presentations of data related to a defined digital entity that need to be carefully designed according to a specific set of business requirements.


\-  B: Java functions designed to extract information from a database.  


\-  C: A data mapping tool that extracts information from any type of database.


\-  D: Web-Services enabling external systems to access Fabric data. 


\-  E: All the above.


(***Solution 2: A ; note if you answered E, you probably had Fabric in mind, but this particular question was about Logical Units***)

 

#### Question 3: Fabric usage

You use Fabric when you need to:


\- A: Ask any question about a digital entity (like a customer), even if where their data resides on tens or hundreds of applications.


\- B: Get up-to-date data.


\- C: Get an answer in milliseconds. 


\- D: Use SQL for complex queries on entities usually residing across multiple databases and servers.


\- E: Have available and secured data at all times.


\- G: All the above.


(***Solution 3: G***)



#### Question 4: Fabric product

Fabric is:


\- A: A schema builder that can create co-relational logic between multiple entities originating from multiple tables, databases or data source types.


\- B: A powerful ETL engine that allows you to connect to remote databases and execute complex data queries. 


\- C: A development environment that allows you to write functions and execute them on a schema's tables.


\- D: A resilient, multi-tenant web-service publishing engine. 


\- E: All the above.

(***Solution 4: E***)



#### Question 5: Fabric storage

Fabric enables all data retrieved from external sources to be stored as:


\- A: A single database residing on a dedicated PostgreSQL server, residing on a single node server to increase access speed and reduce network latency.


\- B: As multiple digital entities (or Logical Unit Instances), each stored as an SQLite micro-database that is compressed and stored on Cassandra distributed DB.


\- C: As a single binary file pointing to different databases distributed across external networks.


 (***Solution 5: B - Note that Fabric can be configured to avoid storage of Sqlite file in Cassandra.***)



#### Question 6: Fabric connections

Users can query Fabric from external systems by:


\- A: Using the Fabric JDBC driver.


\- B: Pushing data directly into Fabric through a web-service enabling direct insert, update and delete operations into LUIs.


\- C: Listening to the Fabric CDC (Change Data Capture) module and acting upon changes if needed.


\- D: All the above.


(***Solution 6: D***)



####  Question 7: Fabric and message queues

Fabric publishes data changes to external servers using:


\- A: Kafka and CDC (Change Data Capture) module to notify changes occurring to specific LUIs.


\- B: CDC and iiDFinder modules to notify about changes occurring in the 'commonDB' tables.


\- B: MSMQ.


\- C: Its own proprietary Queue Messaging API.


\- D: All the above.


(***Solution 7: A***)



#### Question 8: Data security

Fabric uses data from multiple external sources. To ensure the integrity of the data you fetch, store and generate, Fabric provides:


\- A: Strong authorization and authentication to protect systems from incoming requests when exposing the data as Web-Services. 


\- B: Data masking to hide real data during development or QA phases.


\- C: Encryption of all (LUI) micro-databases created at any time. A different key (based on the master key) is generated for each LUI, meaning that even if you did manage to access one LUI, you will still not have access to all the other LUIs.


\- D: Encryption of part of an LUI according to specifications provided by the LU designer. 


\- E: All the above.


(***Solution 8: E***)

 

#### Question 9: Fabric data flow

Implementers can use Fabric to define and run:


\- A: Entire data manipulations flows like inserting data directly into targets (LUIs).


\- B: Schedule jobs to be executed according to pre-defined rules or schedules.


\- C: Parse data from any type of data source and store it in Cassandra DB.


\- D: Write java functions to transform data or trigger synchronizations processes with external sources.


\- E: All the above and more. 


(***Solution 9: E***)



#### Question 10: Fabric CommonDB

Fabric stores reference tables (tables that contain elements common to all LUIs):


\- A: As Cassandra's keyspaces kept in a single dedicated Cassandra server than can be queried using CSQL queries.


\- B: As a a set of multiple SQL databases copies, each residing on a Fabric server node and systematically kept in sync with the others using Kafka Queue Management service.

\- C: As a single centralized SQL DB residing on one dedicated common server. 

 (***Solution 10: B)



#### Question 11: Fabric cluster

Fabric can be deployed and used across:


\- A: Multiple clusters spread over different datacenters independently of Cassandra's storage locations.


\- B: Only on the same the datacenters where Cassandra servers have been deployed.


\- C: Only on the servers where external sources reside.


 (***Solution 11: A***)



[![img](https://github.com/k2view-academy/K2View-Academy/raw/master/articles/images/Previous.png)](/academy/Training_Level_1/02_Fabric_Architecture/2_1_FabricArchitectureOverview.md)
