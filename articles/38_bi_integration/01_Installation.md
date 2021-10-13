# Installation and Configuration

The installation and configuration of Fabric BI includes the following steps:

- [Installation](01_Installation.md#installation) - Fabric BI is based on ExagoBI, so you must install the ExagoBI server and the Storage Management DB.
- [Configuration](01_Installation.md#configuration) - Configure parameters in the Fabric config.ini file.
- [Project Initialization with Fabric BI](01_Installation.md#Project-Initialization-in-BI). 

### Installation

* Install **the latest available Exago version** using the [ExagoBI Installation document](/articles/98_maintenance_and_operational/BI_Installation/01_ExagoBI_Installation.md).
* Install the **Storage Management DB** - the database that keeps the report definition, which includes the report type and metadata, currency, decimal setting, fonts, colors and more. The following types are supported:
  * **SQLite** (default), doesn't require an installation since it comes with the ExagoBI installation.
  * **PostgreSQL**, must be installed.

**Installation Recommendations**

- Have 3 separate ExagoBI installations - one each for Dev, QA and Production environments.

  - Linux installation is required for UAT / Production environments.
  - Installation on Windows (as a docker) can be done for development or demo purposes.

- The recommended Storage Management DB type is PostgreSQL.

  - PostgreSQL is required for UAT / Production environments, but it is preferable to use PostgreSQL for Dev and QA as well.
  - Default SQLite DB can be used for demo purposes. SQLite DB does not require an explicite installation as it comes as part of the Exago installation.
- Due to performance considerations, it is recommended to install the PostgreSQL on a different host than Exago.

### Configuration

Update the **[bi]** section parameters of the Fabric **config.ini** as follows:

* **BI_PORT**, the listener port for the Fabric BI. The default is 5432.
* **BI_HOST**, the IP address of the Fabric BI server.
* **STORAGE_MGMT_DB_NAME**, the name of the Storage Management DB. The default is StorageMgmt.
* **STORAGE_MGMT_HOST**, the IP address of the Storage Management DB. Empty for SQLite DB.
* **STORAGE_MGMT_DB_TYPE**, the type of Storage Management DB. The default is SQLite. The recommended Storage Management DB type is PostgreSQL.
* **STORAGE_MGMT_DB_PROVIDER**, the Storage Management DB provider. The default is SQLite. When the Storage Management DB type is PostgreSQL, the provider is Npgsql.
* **STORAGE_MGMT_DB_USER** / **STORAGE_MGMT_DB_PASSWORD**, the Storage Management DB user and password. Empty for SQLite DB. The password is automatically encrypted upon saving the config.ini.
* **TABLE_PREFIX**, the Storage Management DB table prefix. Should be populated when you want to re-use the same Storage Management DB for several environments. For example, set TABLE_PREFIX=dev1_.
* **BI_REST_KEY**, a key to be used to authenticate REST requests. You can use the default value during the development, however it is required to update it prior to moving the project to Production. [Click to get the explanation about how to setup the REST key](99_bi_admin_config.md#REST-Key-Initialization). 

~~~
[bi]
## Listener port for bi
#BI_PORT=5432
## Bi host
#BI_HOST=
## Bi Storage Management name, default = StorageMgmt for SQLite and PostgreSQL
#STORAGE_MGMT_DB_NAME=StorageMgmt
## Bi Storage Management host, empty for SQLite
#STORAGE_MGMT_HOST=
## Bi Storage Management type SQLite/PostgreSQL
#STORAGE_MGMT_DB_TYPE=SQLite
## Bi Storage Management provider SQLite/Npgsql
#STORAGE_MGMT_DB_PROVIDER=SQLite
## Bi Storage Management user
#STORAGE_MGMT_DB_USER=
## Bi Storage Management password
#STORAGE_MGMT_DB_PASSWORD=
## Bi Storage Management table prefix
#TABLE_PREFIX=dev_
## Bi rest key
#BI_REST_KEY=
~~~
### Project Initialization with Fabric BI

Upon the completion of installation and configuration setup, deploy the Fabric project. As a result, the Storage Management DB is initialized with 4 basic tables and the <project name> folder is created in the Storage Management DB metadata, with the default read-only access level.  

Now any user accessing this project has the default read-only access to the project's reports within the BI Designer. It is possible to setup different access permissions per Fabric role. The [Access Permissions Setup](02_Permissions_Setup.md) article explains how to do this. 

​

[![Previous](/articles/images/Previous.png)](00_BI_user_guide_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_Permissions_Setup.md) 



