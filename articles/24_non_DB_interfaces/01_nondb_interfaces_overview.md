# Non-DB Interfaces Overview

An interface is a data communication channel to an external system that enables users to define connection parameters for a data source. All data needed by Fabric from the data source is transferred through an interface.

When multiple data sources are needed by the project implementation, several interfaces are defined, one for each data source. It is therefore natural for a project to have multiple interfaces. Fabric interfaces are defined as [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md), whereby they can be accessed by any objects in a project, such as [LUs](/articles/03_logical_units/01_LU_overview.md), [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md), [Translations](/articles/09_translations/01_translations_overview_and_use_cases.md) or [Reference tables](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md).

### DB Interfaces and Non-DB Interfaces

Fabric distinguishes between DB interfaces and Non-DB interfaces.

**DB interfaces** enable Fabric server connections to databases like the SQL Server, PostgreSQL or Oracle. They are used to access database data and metadata.

[Click for more information about DB Interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md).

**Non-DB Interfaces** are used to define a connection with data sources that are not Databases. The following non-DB interfaces are described:

* [SFTP Interface](02_SFTP_interface.md)
* [PubSub Configuration](02a_pubsub_config.md)
  
* [Kafka Interface](03_kafka_interface.md)
  
* [JMS Interface](04_JMS_interface.md)
  
  [HTTP Interface](05_HTTP_interface.md)
  
* [Local File System Interface](06_local_file_sys.md)
* [Custom Interface](07_custom_interface.md)
* [SMTP Interface](08_SMTP_interface.md)
* [Redis Interface](09_redis_interface.md)
* [SSH Interface](10_SSH_interface.md)
* [LDAP Interface](11_LDAP_interface.md)
* [Amazon S3 Storage Interface](12_S3_interface.md)
* [Azure Blob Storage Interface](13_blob_interface.md)
* [Google Cloud Storage Interface](14_gcs_interface.md)
* Search Interface. This interface type supports cross-instance searches and is needed to enable Fabric to connect to the search engine when a Search is defined on LU tables. [Click for more information about Search Implementation Steps](/articles/18_fabric_cdc/cdc_consumers/search/02_search_implementation.md).
* Data Catalog Interface. This interface type is required for enabling Fabric Data Catalog. [Click for more information about Data Catalog Interface](/articles/33_data_catalog/02a_data_catalog_interface.md).

<studio>
From Fabric V6.5.1 and on a test connection and Active properties were added to all Non-DB interfaces.
</studio>


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_SFTP_interface.md) 
