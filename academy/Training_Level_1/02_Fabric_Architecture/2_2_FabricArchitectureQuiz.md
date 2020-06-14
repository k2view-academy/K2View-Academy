# Fabric Architecture Quiz

![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png) 



Excellent! you have completed the Fabric Overview and Architecture learning items.

 


Before moving to the next item, you can test what you've learned by taking the following quiz. It consists of a number of multiple-choice questions, each providing a number of possible answers. Select the answer you think is correct by clicking over it:




#### Question 1: Fabric overview

Working with Fabric will allow you to:


\-  A: Get fast answers to complex questions across data-lakes, such as across-the-board statistics or BI insights.


\-  B: Answer to a complex question about one or multiple customers in real-time, although data might be spread across multiple data centres.


\-  C: Create graphical visualizations of data spread across multiple data centres.

 ***Solution 1: B; Indeed Fabric is about specific entities down to the last bit of information they contain.***



#### Question 2: Fabric concepts

Logical Units are:


\- A: Hierarchical presentations of data related to a defined business entity, that need to be carefully designed according to a specific set of business requirements.


\-  B: Java functions designed to extract information from a Database.  


\-  C: A data mapping tool that extracts information from any type of databases.


\-  D: Web-services enabling external systems to access Fabric data . 


\-  E: All the above.

***Solution 2: A ; note if you answered E, you probably had Fabric in mind, but this particular question was about Logical Units***

 



#### Question 3: Fabric usage

You will use Fabric when you need to:


\- A: Be able to ask any question on a business entity (e.g. a customer), even if its data resides on tens or hundreds of applications.


\- B: Get up-to-date data.


\- C: Get an answer is milliseconds. 


\- D: Use SQL for complex queries on entities usually residing across multiple DBs and servers.


\- E: Have data available and secured at all times.


\- G: All the above.


 ***Solution 3: G***





#### Question 4: Fabric product

Fabric is:


\- A: A schema builder that can create co-relational logic between multiple entities originating from multiple tables, databases or data source types.


\- B: A powerful ETL engine that allows you to connect to remote DBs and execute complex data queries. 


\- C: A development environment that allows you to write functions and execute them on your schema's tables.

\- D: A resilient, multi-tenant web-service publishing engine. 
\- E: All the above.

***Solution 4: E***






#### Question 5: Fabric storage

Fabric enables all data retrieved from external sources to be stored as:


\- A: A single database residing on a dedicated PostgreSQL server, residing on a single node server to increase access speed and reduce network latency.


\- B: As multiple digital entities (or Logical Units Instances), each one stored as an SQLite micro-database on one of multiple Cassandra distributed nodes.


\- C: As a single binary file pointing to different DBs distributed across external networks.


 ***Solution 5: B***





#### Question 6: Fabric Storage

To store its data, Fabric uses the following storage mechanism:


\-  A: Storage on files using RAID.


\-  B: Oracle database storage.


\-  C: Cassandra distributed storage as a key value-store.


\-  D: No physical storage. Everything resides in memory.

***Solution 6: C - Note that Fabric can also store and manipulate data in RAM and refrain from keeping data in storage.***






#### Question 7: Fabric Connections

Users can query Fabric from external systems by:

\- A: Using Fabric JDBC driver.

\- B: Pushing data directly into Fabric through a web-service enabling direct CRUD operations into LUIs

\- C: Listening to Fabric CDC (Change Data Capture) module and act upon changes if needed

\- D: All the above

***Solution 7: D***





####  Question 8: Fabric & Message queues

Fabric publishes data changes to external servers by using:

\- A: Kafka & CDC to notify changes occurring to specific LUIs

\- B: CDC (Change Data Capture) & iiDFinder modules to notify changes occurring to common DB tables

\- B: MSMQ 

\- C: Its own proprietary Queue Messaging API

\- D: All the above

***Solution 8: A***





#### Question 9: Data security

Fabric uses data from multiple external sources. To ensure the integrity of the data you fetch, store and generate, Fabric provides:


\- A: Strong authorization and authentication to protect systems from incoming requests when exposing the data as web-services. 


\- B: Data masking to hide real data during development or QA phases.

\- C: Encryption of all micro-databases (LUI) created at any time.


\- D: Encryption of part of an LUI according to specifications provided by the LU designer. 


\- E: All the above.

***Solution 9: E***

 


#### Question 10: Fabric Data Flow

Implementers can use Fabric to define and run:


\- A: Complete flows of data manipulations such as data inserts directly into targets (LUIs).


\- B: Schedule jobs that will be executed according to pre-defined rules or schedules.


\- C: Parse data from any type of data source and store it in CommonDB as SQL tables.


\- D: Write java functions to transform data  or trigger synchronizations with external sources.


\- E: All the above and more. 

***Solution 10: E***






#### Question 11: Fabric Cluster

Fabric can be deployed and used across:

\- A: Multiple clusters spread across different data centres independently of Cassandra's storage

\- B: Only on the same the data centres where Cassandra servers have been deployed

\- C: Only on the servers where External sources reside

 ***Solution 11: A***





[![img](https://github.com/k2view-academy/K2View-Academy/raw/master/articles/images/Previous.png)](/academy/Training_Level_1/02_Fabric_Architecture/2_1_FabricArchitectureOverview.md)