# FABRIC ARCHITECTURE OVERVIEW


# 1. Fabric Systems Overview

Fabric includes everything that is required to deliver the data you need, where and when you need it.

Fabric can be viewed as a Distributed, Business-Entity-centric, Data management System, designed to get, store, transform and expose data for operational real-time needs.

Fabric takes ownership of the E2E data lifecycle:

- Data discovery, modelling and curation
- Data acquisition and synchronization
- Data validation, aggregation, enrichment, matching, masking, cleansing and transformation
- Data storage, distribution, replication and encryption
- Data exposure

Unlike data virtualization solutions, Fabric stores the data.

By storing the data in Fabric we reduce the load on the systems of record, removing the dependency on legacy performance and availability. Fabric provides the ability to get the data close to its consumers, remove the dependency on legacy applications vendors cost &amp; time-to-implement. The result is a move from vendor data structure to business data structure, freeing the data from the application silos and moving to a data-driven architecture.

Data-warehouses and data-lakes are really good at providing an answer to big questions (e.g. avg invoice amount for all customers in specific state for the last 3 months). They are the right choice for BI, Analytics, ML/AI Training, etc.

Data-warehousing and data-lakes are the wrong choice for answering many complex questions about one customer in real-time and definitely the wrong technology/architecture if you need to do this for many customers at the same time.

The main reasons for that are:

- The amount of data required to be scanned in order to get the data for one customer.
- In most cases, the data for that single customer resides in different servers.

Fabric solves these high-level requirements:

- I want to be able to ask any question on a business entity (e.g. a customer), even if its data resides at tens or hundreds of applications.
- I want to get up-to-date data
- I want to get the answer is milliseconds
- I want to use SQL for my queries
- I want the data to be available at all times
- I want the data to be as close as possible to the data consumers
- I want the data to be secured at all times

In order to achieve that, Fabric is using a patented approach to store, sync and secure data that will be explained in this article. Make sure to read the &quot;[What is Fabric](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/01_what%20is%20fabric.md)&quot; and &quot;[the logical unit overview](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/01_LU_overview.md)&quot; to get the background for the architecture.



# 2. Fabric server main components

In order to deliver the above-mentioned functionality, Fabric relies on a resilient architecture and a strong set of 3rd party technologies widely used across the industry. To ensure scalability, quality of service and resiliency, Fabric has been engineered as a set of layers, each-one designed to address a different part of the overall data flow.

![](fabOverviewPic.gif)



## 2.1 Fabric Storage

In order to deliver the above requirements, Fabric uses 3 types of storage engines:

### ![2.1.1 Micro-databases](microDBPic.gif)

At the core of Fabric storage, Fabric creates (and maintains) a micro-database ([Logical](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/01_LU_overview.md) Unit) for every instance of a business entity.

A micro-database is an SQLite file. It supports everything SQLite provides out of the box.

This method provides several advantages:

- Encapsulation of ALL data of a single business entity (e.g. a customer) in one place so our consumers can ask any question about data usually residing in many different data-sources.

- Micro-databases are very small (hold the data of only one business entity) and therefore can be stored completely in RAM and provide unparalleled query performance.

- Can be encrypted individually at the micro-database or even field levels.

- Using SQLite, provides standard SQL operations.

- Continued availability of data (No business impact) on major structural modification

    
### ![2.1.2 CommonDB](commonDBPic.gif)

This is an additional SQLite Database schema with the purpose of storing the reference tables common to all Micro-databases (e.g. a table storing a list of objects to which all microdatabase schemas will point to). In a distributed system, one copy of each reference table will be stored on each node. Fabric will handle their synchronization across [nodes](#_Fabric_Cluster) c.f. ‎6.1

The common database is always available for query on every Fabric session enabling joining of data between Common tables and micro-database in a single SQL query.


### ![2.1.3 Cassandra](cassPic.gif)

Fabric uses Cassandra for 3 main purposes:

- Storage for all micro-databases (Logical Unit Instances) as compressed blob chunks.

- As Fabric application management database.

- General data-store for any project need

  
## 2.2 Fabric Business Logic

This is where all transformations and data manipulations happen and where Fabric will tackle the business functions you wish to apply on the data that you will import from external sources and store locally c.f. ‎3.3.3



## 2.3 Fabric Studio

Fabric Studio is the development tool for building Fabric Solutions.

The studio is a Windows application designed to enable users to execute the following steps:

- Create interfaces to external sources.
- Design Logical units, schemas and create their associated instances.
- Create resources of all types to be used across a project (such as functions, tables, variables).
- Execute data manipulations using the SQL builder or the integrated Java IntelliJ development environments.
- Create REST APIs to access your data model.
- Design data flows.





# 3. Data Flow

 
## 3.1 Overview

Data flows in and out of Fabric via multiple types of interfaces and data formats.

Fabric users can utilize a large number of possible configurations between their data-supplying and data-subscribing systems. Connection flexibility is essential for Fabric to integrate with data spread across multiple databases and data centers and to generate its uniquely patented Digital Entities.

![](fabDataFlow.png)



## 3.2 Fabric Data Access layer

In this section we will go over all the protocols and standard interfaces through which data can be injected from External Sources into Fabric DB (microDB or commonDB) or through which data can be published to, or accessed by 3rd party systems.

  

### 3.2.1 Standard DML via JDBC or ADO.NET

Fabric provides standard JDBC and ADO.NET drivers to execute queries and data manipulation statements (SQL/DML) from any external JVM or .NET process.

A user/application can connect to Fabric via JDBC or ADO.NET, open a transaction and use standard INSERT, UPDATE and DELETE commands to modify data stored in Fabric. This same interface can be used to run SQL SELECT queries to retrieve data.

  
### 3.2.2 REST API and Web Services

External sources can also read and write data by using standard REST web-services, enabling direct CRUD operations into LUIs and commonDb residing in Fabric Storage.

Conversely, Fabric can expose LUI micro-databases or commonDB objects to external queries using a REST API configured, generated and published by the user. Such API functions can be invoked either by 3rd party systems or directly from any web browser. A web-service is defined as a function that needs to be [deployed](#_Fabric_meta-data_management) to the K2View Fabric Server.



### 3.2.3 CDC (Change Data Capture) via Kafka

Fabric supports real-time, inbound data updates via Kafka interface using the [IiDFinder](#_Data_Processing_modules) module. IidFinder is in charge of identifying incoming events and associating them with the correct Micro database. This interface can be easily integrated with CDC providers such as Oracle Golden Gate.

To publish change events out via this interface, Fabric provides a full CDC solution that notifies external systems about Fabric data changes occurring to LUIs, enabling 3rd party systems to subscribe to a Kafka topic and get a stream of micro database changes.



### 3.2.4 Manual/Scripted input

External data can also be injected to FabricDB via standard commands available from the fabric console application.

 
## 3.3 Fabric ETL Layer

When data needs to be processed before being stored or exposed, it will go through Fabric ETL and business logic engine. Data can be processed and transformed in accordance with the business requirements that users will define by using one of many Fabric flow management systems.

### 3.3.1 Protocols

#### 3.3.1.1 Files

Data can be captured via SFTP or any other transfer protocol and/or streaming service. Standard file types such as Json/XML/CSV can be easily parsed and injected. Users can also easily introduce new formats.

#### 3.3.1.2 Queue messaging services (QMS)

Fabric allows subscription to queue messaging systems to stream-in data using services such as Apache Kafka, JMS and rabbit. Fabric can then step-in and apply transformations and logic to incoming events.

#### 3.3.1.3 APIs

Fabric can consume eternal APIs (such as web services, soap, java libraries etc) through which data can obtained and populated into the fabric storage engine.

APIs can also be invoked as part of flows inside the ETL/Business Logic layer.

#### 3.3.1.4 DB Query

By default, Fabric will support any connection to any Database supporting a JDBC driver. If not supported in Fabric&#39;s standard interface types module, users can define new DB types in accordance with the database and its JDBC driver specifications. Fabric can also be extended programmatically to invoke proprietary drivers or interfaces.



### 3.3.2 Data-processing & Business Logic

In this section we will go through the types of transformations used by the business rules and flows created by users depending upon the project requirements.

#### 3.3.2.1 Data pre-processing

Different projects have different data processing needs, for which Fabric offers a range of built-in functions and libraries, that can be invoked either individually or collectively:

- Data anonymization
  
- Fabric provides a [masking process](#_Masking_algorithm) (c.f. 5.4) that can be used to anonymize data for R&amp;D or QA purposes.
  
- Data cleansing
  
- Fabric can be setup to retrieve (from external sources) only the data necessary to populate the LU instances while disregarding any data that is not relevant to your project implementation.
  
- Data transformation
  - Fabric provides a large set of functions needed to execute data transformations.
  - This set can be extended by combining existing functions or by adding functions using Java or Javascript.
- PII discovery
  
- A built-in set of libraries enabling sensitive data discovery such as Personally Identifiable Information, especially useful to enforce GDPR or CCPA compliancy
  
- Data reconciliation, comparing &amp; matching
  - Fabric will analyze the collected data in order to decide which data to keep or to discard, depending upon whether the data is trustable.
  
- This process can also use Machine Learning algorithms to decide which data set is more trusted when comparing similar entries from multiple tables or DBs
  
      
#### 3.3.2.2 Data Processing modules

Data can be processed from 6 different modules:

- Synchronization process:

As part of data synchronization (on-demand or initial load) using Fabric&#39;s populationobject, the Sync process uses LU schemas defined within Fabric Studio to create (or update) the micro-databases (LUIs). When synchronizing multiple digital entities, Fabric invokes a migration process (distributed parallel sync) for a list of LU instances.

- iiDFinder

Since Fabric creates Digital Entities by extracting data from multiple sources, and then by populating and transforming the data into the LUI tables, any change occurring at the sources level must be reflected all the way down to the fields of the LU instance tables

iiDFinder process manages the deployment of such incremental updates as soon as a change in the data source is detected through usual notification systems (such as Oracle Golden Gate) and/or queue messaging services.

For environments where source data constantly changes, Fabric enables a lazy mode, whereby iiDFinder retrieves the delta updates upon explicit demand from the user.

- Data Enrichment

Users can enrich data by using built-in functions or adding to them using java/javascript code designed to transform data according to the business requirements.

- Jobs

This is where all async recurring or scheduled actions happen, enabling users to run fabric functions according to a pre-defined schedule. Once set-up by the user, Fabric will create asynchronous tasks (running threads) that will execute specific commands, broadway flows or Java code at specific dates and times.

Jobs can be used to collect data from structured DB or any files (HTTP), streams, message queues.

- Broadway

Fabric&#39;s data and business flow management system (Broadway) enables implementors to define, orchestrate and run complete flows of data manipulation and tasks. It provides a work environment that unifies data and execution flows under the same framework.

In a nutshell, Broadway enables you to graphically render your business and data flows and modify them with a set of visual and draggable elements (actors) each one acting as a function, source or target.

- Graphit&#39;s APIs Generator

Graphit is a Fabric utility used to dynamically generate CSV, XML and JSON documents. It is useful for the design and generation of Fabric web-services&#39; customized responses. The response content will be formatted at execution time, according to specific parameters pertaining to the web service calls and the LUI in use.



# 4 Search



## 4.1 Search engine

Since data is spread across multiple micro databases, Fabric provides an indexing process that is ran during data synchronization phases. This enable users to execute queries on indexed fields across any number of LUIs

## 4.2 Elastic search

Via its CDC module, Fabric can use E-S to store its indices and provide a distributed, [multi-tenant](https://en.wikipedia.org/wiki/Multitenancy) capable [full-text search](https://en.wikipedia.org/wiki/Full-text_search) engine for near real-time results across its huge number of LU micro-database instances.



# 5 Security

Since digital entity data encapsulation architecture provides a very strong case for data protection (each LUI lives as a separate entity), Fabric is basically secured by-design. Yet Fabric adds to its arsenal a wide range of security tools, layers and practices to ensure the protection of your data.



## 5.1 Authentication &amp; Authorization engine

This module manages access rights, authentication and authorization for users, web services or any other entities interfacing with fabric by monitoring all in-bound interfaces connections and securing external requests with web tokens issuance and decryption capabilities.

### 5.1.1 Fabric Tokens encryption

Fabric uses tokens to allow the execution of web-services requests. User can also define different roles and assign each role to a specific token. All tokens are encrypted before they are saved into the Cassandra.

### 5.1.2 JWT Tokens

For added security, Fabric can accept JWT (JSON Web Token) signed externally by a shared secret.


## 5.2 MicroDBs (LUI) encryption

Fabric secures the schemas and instances generated whenever a new logical unit is created. The encryption process is relying upon the combination of LU Type, LU Instance and a Master Key generated for each LU.

Fabric generates a master key (AES-256) which is then broken into bytes, each byte being stored in a separate record in a dedicated Cassandra table. Then, thanks to Cassandra distribution data logic, each node stores only part of the Master Key. Furthermore, a master key rotation scheme, allows users to regularly change the master key. New LUIs will be encrypted with the new key, while the previous ones will be re-encrypted with the new key at the next synchronization.

Fabric encrypts each instance (LUI) using the AES-256 in OFB mode encryption algorithm and a combination of the LU name, LUI ID and master key. As a result, Fabric creates a different key for each instance id, because each instance id has a different value.

As a result, in the improbable event that one LUI has been breached, the rest of the entities will remain safe since they each have been encrypted with their own secret key.

In order to reduce load and improve compression, specific fields of an LUI can be encrypted rather than the entire instance.



## 5.3 Environment encryption

Users can define a number of environments for source connectivity according to their specific needs and switch between the environments within the same Fabric session. By default, Fabric encrypts the interfaces&#39; details of each environment using the same master key used to encrypt LU instances. If necessary, users can re-key all interfaces that belong to a given environment. If the connection details of any interface, in a given environment, are changed, the updated connection details will be re-encrypted. Fabric also encrypts the interfaces&#39; details of each environment using the same master key used to encrypt LU instances. Once the data is store in Fabric it is used for connectivity and is not available for querying by external APIs.



## 5.4 Masking algorithm

In addition, in order to protect your company&#39;s data, Fabric masking service creates structurally similar but inauthentic version of your data that can be used for development, integration or testing purposes.





# 6 Resiliency



## 6.1 Fabric Cluster

As we have seen earlier in section 2 &amp; 3, Fabric relies on Cassandra distributed storage to store the massive amount of data pertaining to the LUIs that were generated with data retrieved from external data sources and potentially transformed by Fabric data processing modules.

Fabric also provides strong capabilities to publish data to external third parties.

To this effect, Fabric can be configured as a cluster constituted of multiple nodes spread over multiple data centers.

This will provide boosted levels of accessibility to our systems from any of your 3rd parties issuing inbound data queries.

Such configuration allows for unparalleled flexibility as your DevOps manager in charge of your Fabric configuration, can decide to adapt the number of fabric nodes according to the demand at any given time, but also increase (or reduce) the number of Cassandra nodes depending on the quantity of storage needed for your LUIs.

![](fabCluster.png)