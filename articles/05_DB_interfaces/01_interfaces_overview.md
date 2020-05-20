# Interface Overview - Connectivity to the External World

### What is an Interface?
In Fabric an Interface is a data communication channel to an external system that enables users to define connection parameters for a data source. All data needed by Fabric from the data source is transferred through an Interface.
 
When multiple data sources are needed by the Project implementation, several Interfaces are defined, one for each data source. It is therefore natural for a Project to have multiple Interfaces. 
Fabric Interfaces are defined as [Shared Objects], whereby they can be accessed by any objects in a Project, such as [LUs], [Web Services], [Translations] or [Reference tables].

### DB Interfaces and Non-DB Interfaces

Fabric distinguishes between DB Interfaces and Non-DB Interfaces:\
[**DB Interfaces**]\
DB Interfaces enable Fabric Server connections to databases like the SQL Server, PostgreSQL or Oracle. They are used to access database data and metadata.\
**Click for more information about Creating a New Database Interface.**\
**Non-DB Interfaces**\
Non-DB Interfaces are used to define a connection with data sources that are not databases. For example, search engines, FTP servers or message queues like JMS or Kafka. 

[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/02_interfaces_source_analysis_guidelines.md)
