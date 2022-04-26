# Interface Overview

### What Is an Interface?
In Fabric an interface is a data communication channel to an external system enabling users to define connection parameters for a data source. All data needed by Fabric from the data source is transferred through an interface.

When multiple data sources are needed by the project implementation, several interfaces are defined, one for each data source. It is therefore natural for a project to have multiple interfaces. 
Fabric interfaces are defined as [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md), whereby they can be accessed by any objects in a project, such as [LUs](/articles/03_logical_units/01_LU_overview.md), [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md#web-services-overview), [Translations](/articles/09_translations/01_translations_overview_and_use_cases.md) or [Reference tables](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md).

### DB Interfaces and Non-DB Interfaces

Fabric distinguishes between DB interfaces and Non-DB interfaces:

**DB Interfaces**

DB interfaces enable Fabric server connections to databases such as SQL Server, PostgreSQL or Oracle. They are used to access database data and metadata.

[Click for more information about Database Interfaces.](/articles/05_DB_interfaces/03_DB_interfaces_overview.md)



**Non-DB Interfaces**

Non-DB interfaces are used to define a connection with data sources that are not databases. For example, search engines, FTP servers or message queues like JMS or Kafka. 

[Click for more information about Non-DB Interfaces](/articles/24_non_DB_interfaces/01_nondb_interfaces_overview.md).

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_interfaces_source_analysis_guidelines.md) 
