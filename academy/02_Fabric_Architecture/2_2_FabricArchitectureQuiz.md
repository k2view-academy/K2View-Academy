# Fabric Architecture Quiz

Logical Units are:

\-     A: Hierarchical presentations of data related to a defined business entity, that you will need to carefully design according to a specific set of business requirements.

\-     B: Blocks of code queries designed to extract information from DB  

\-     C: A data mapping tool to extract information from data warehouses

\-     D: A data publishing service for external 

\-     E: All the above

 

Working with fabric will allow you to:

\-     A: Get fast answers to complex questions across data-lakes such as across-the-board statistics or BI insights

\-     B: Answer to a complex question about one or multiple customers in real-time, although data might be spread across multiple data centers

\-     C: Securely store data

 

You will use Fabric for cases when you need to:

\-     A: Be able to ask any question on a business entity (e.g. a customer), even if its data resides on tens or hundreds of applications.

\-     B: Get up-to-date data

\-     C: Get the answer is milliseconds 

\-     D: Use SQL for complex queries on entities usually residing across DBs or and servers

\-     E: Have data available & secured at all times

\-     G: All the above

 

Fabric is:

\-     A: A schema builder that can create co-relational logic between multiple entities originating from multiple tables, databases or data source types.

\-     B: A powerful ETL engine that allows you to connect to remote DBs and execute complex data queries 

\-     C: A development engine that allows you to write enrichment functions and execute them on your schema

\-     D: A resilient, multi-tenant web-service publishing engine 

\-     E: All the above

 

Fabric enables all data retrieved from external sources to be stored as:

\-     A: a single Logical Entity on one dedicated PostGreSQL database, residing on a single node server to increase access speeds and reduce network latency

\-     B: as multiple digital entities, each one stored as an SQLite micro-database on Cassandra distributed nodes.

\-     C: as a digital unit stored as a binary file pointing to different DBs distributed across external networks

 

In order to store its data, Fabric uses the following storage mechanism:

\-     Storage on file using RAID

\-     Oracle database storage

\-     Distributed storage as a key value-store

\-     No physical storage. Everything resides in memory.

 

 

Data security

Fabric uses data from multiple external sources. In order to ensure the integrity of your data you store and generate, Fabric provides:

\-     A: Strong authorization and authentication to protect the systems from incoming requests when exposing the data as web-services 

\-     B: Data masking to hide real data during development or QA phases

\-     C: Encryption of all micro-databases (LU instances) created at any time

\-     D: Encryption of part of the LUIs according to specifications provided by the LU designer 

\-     E: All the above

 

Fabric as a Data Flow Management System

Implementors can use Fabric to define and run:

\-     A: Complete flows of data manipulations such as data inserts directly into targets (LUIs)

\-     B: Schedule jobs that will be executed according to pre-defined rules or schedules.

\-     C: Parse data from any kind of data sources and store it in SQL tables

\-     D: Write java functions to operate data transformations or trigger synchronizations

\-     E: All the above and more 

 

In Fabric you can create functions that can be used as: 

\-     Root of population maps

\-     New SQL query functions

\-     Web-Services

\-     All of the above