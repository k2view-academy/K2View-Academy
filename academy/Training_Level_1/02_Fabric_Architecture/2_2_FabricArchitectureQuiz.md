# Fabric Architecture Quiz

![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png) 



Excellent! 
You have completed the Fabric Overview and Architecture learning items.


Before moving to the next item, let's take the following quiz to see what you have learnt. The Quiz consists of a number of multiple-choice questions, each providing a number of possible answers. Click the answer you think is correct. 



#### Question 1: Fabric Overview

Working with Fabric will allow you to:


\- A:  Get fast answers to complex questions across data-lakes, such as across-the-board statistics or BI insight.


\- B:  Answer complex questions about a customer or multiple customers in real-time, even when data might be spread across multiple datacenters.


\- C:  Create graphical visualizations of data spread across multiple datacenters.


(***Solution 1: B; Yes, Fabric is about specific entities and every particle of information they hold***).



#### Question 2: Fabric Concepts

Logical Units are:


\- A:  Hierarchical presentations of data related to a defined Digital Entity that need to be carefully designed according to a specific set of business requirements.


\- B:  Java functions designed to extract information from a database.  


\- C:  A data mapping tool that extracts information from any type of database.


\- D:  Web Services that enable external systems to access Fabric data. 


\- E: All the above.


(***Solution 2: A; Note that if you answered E, you probably had Fabric in mind, but this particular question is about Logical Units***).

 

#### Question 3: Fabric Usage

You use Fabric when you need to:


\- A: Ask questions about a Digital Entity (like a customer), even when their data resides on tens or hundreds of applications.


\- B: Get up-to-date data.


\- C: Get an answer in milliseconds. 


\- D: Use SQL for complex queries on entities usually residing across multiple databases and servers.


\- E: Have available and secured data at all times.


\- F: All the above.


(***Solution 3: F***)



#### Question 4: Fabric Product

Fabric is:


\- A: A schema builder that can create co-relational logic between multiple entities originating from multiple tables, databases or data source types.


\- B: A powerful ETL engine that allows you to connect to remote databases and execute complex data queries. 


\- C: A development environment that allows you to write functions and execute them on a Schema's tables.


\- D: A resilient, multi-tenant Web Service publishing engine. 


\- E: All the above.

(***Solution 4: E***)



#### Question 5: Fabric Storage

Fabric enables all data retrieved from external sources to be stored as:


\- A: A single database residing on a dedicated PostgreSQL server, residing on a single node server to increase access speed and reduce network latency.


\- B: Multiple Digital Entities (or LUI), each stored as an SQLite MicroDB that is compressed and stored on a Cassandra distributed database.


\- C: A single binary file pointing to different databases distributed across external networks.


 (***Solution 5: B - Note that Fabric can be configured to avoid storing Sqlite files in Cassandra***).



#### Question 6: Fabric Connections

Users can query Fabric from external systems by:


\- A: Using the Fabric JDBC driver.


\- B: Pushing data directly into Fabric through a Web Service enabling direct insert, update and delete operations into LUIs.


\- C: Listening to the Fabric CDC (Change Data Capture) module and acting upon changes if needed.


\- D: All the above.


(***Solution 6: D***)



####  Question 7: Fabric and Message Queues

Fabric publishes data changes to external servers using:


\- A: Kafka and CDC (Change Data Capture) module to notify about changes occurring in specific LUIs.


\- B: CDC and iiDFinder modules that notify about changes occurring in commonDB tables.


\- B: MSMQ.


\- C: Its own proprietary Queue Messaging API.


\- D: All the above.


(***Solution 7: A***)



#### Question 8: Data Security

Fabric uses data from multiple external sources. To ensure the integrity of the data you fetch, store and generate, Fabric provides:


\- A: Strong authorization and authentication to protect systems from incoming requests when exposing the data as Web Services. 


\- B: Data masking to hide real data during development or QA phases.


\- C: Encryption of all (LUI) MicroDB created at any time. A different key (based on the master key) is generated for each LUI, meaning that even when you access an LUI, you will not have access to the other LUIs.


\- D: Encryption of part of an LUI according to specifications provided by the LU designer. 


\- E: All the above.


(***Solution 8: E***)

 

#### Question 9: Fabric Data Flow

Implementers can use Fabric to:


\- A: Define entire data manipulation flows like inserting data directly into targets (LUIs).


\- B: Schedule jobs to be executed according to predefined rules or schedules.


\- C: Parse data from any type of data source and store it in the Cassandra database.


\- D: Run Java functions to transform data or trigger synchronization processes with external sources.


\- E: All the above and more. 


(***Solution 9: E***)



#### Question 10: Fabric CommonDB

Fabric stores reference tables that contain elements common to all LUIs:


\- A: As Cassandra's keyspaces kept in a single dedicated Cassandra server that can be queried using CSQL queries.


\- B: As a a set of multiple SQL databases copies, each residing on a Fabric server node and systematically kept in sync with the others using Kafka Queue Management service.

\- C: As a single centralized SQL DB residing on one dedicated common server. 

 (***Solution 10: B)



#### Question 11: Fabric Cluster

Fabric can be deployed and used across:


\- A: Multiple clusters spread over different datacenters, decoupled from Cassandra's storage locations.


\- B: Only on the same the datacenters where Cassandra servers have been deployed.


\- C: Only on the servers where external sources reside.


 (***Solution 11: A***)



[![img](https://github.com/k2view-academy/K2View-Academy/raw/master/articles/images/Previous.png)](/academy/Training_Level_1/02_Fabric_Architecture/2_1_FabricArchitectureOverview.md)
