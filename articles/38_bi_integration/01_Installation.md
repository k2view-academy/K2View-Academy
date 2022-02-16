# Installation and Configuration

The installation and configuration of Fabric's BI includes the following steps:

- [Installation](01_Installation.md#installation) - Fabric's BI is based on ExagoBI, so you must install the ExagoBI server and the Storage Management DB.
- [Configuration](01_Installation.md#configuration) - Configure parameters in the Fabric config.ini file.
- [Project Initialization with BI](01_Installation.md#Project-Initialization-in-BI-on-Deploy). 

### Installation

* Install the **BI server** using one of the following installation packages:
  * [Installation on Linux](/articles/98_maintenance_and_operational/BI_Installation/01_BI_Installation_on_Linux.md).
  * [Docker installation](/articles/98_maintenance_and_operational/BI_Installation/02_BI_Docker_Installation.md).
* Install the **Storage Management DB** - the database that keeps the report definition, which includes the report type and metadata, currency, decimal setting, fonts, colors and more. The following types are supported:
  * **SQLite** (default), doesn't require an installation since it comes with the ExagoBI installation.
  * **PostgreSQL**, must be installed.

**Installation Recommendations**

- Have 3 separate BI installations - one each for Dev, QA and Production environments.

  - Linux installation is required for UAT / Production environments.
  - Installation on Windows (as a docker) can be done for development or demo purposes.

- The recommended Storage Management DB type is PostgreSQL.

  - PostgreSQL is required for UAT / Production environments, but it is preferable to use PostgreSQL for Dev and QA as well.
  - Default SQLite DB can be used for demo purposes. SQLite DB does not require an explicite installation as it comes as part of the Exago installation.
- Due to performance considerations, it is recommended to install the PostgreSQL on a different host than Exago.

### Configuration

The BI solution uses the Fabric **config.ini** to retrieve information about the BI host and port and the Storage Management DB connection details.

Update the **[bi]** section parameters of the Fabric **config.ini** as follows:

* **BI_HOST**, the IP address of the Fabric BI server. When BI runs on a docker, use the host IP. Do not use localhost or 127.0.0.1 as BI host.
* **STORAGE_MGMT_HOST**, the IP address of the Storage Management DB. Empty for SQLite DB.
* **BI_PORT**, the listener port for the Storage Management DB. The default is 5432.
* **STORAGE_MGMT_DB_NAME**, the name of the Storage Management DB. The default is StorageMgmt.
* **STORAGE_MGMT_DB_TYPE**, the type of Storage Management DB. The default is SQLite. The recommended Storage Management DB type is PostgreSQL.
* **STORAGE_MGMT_DB_PROVIDER**, the Storage Management DB provider. The default is SQLite. When the Storage Management DB type is PostgreSQL, the provider is Devart.Data.PostgreSql.
* **STORAGE_MGMT_DB_USER** / **STORAGE_MGMT_DB_PASSWORD**, the Storage Management DB user and password. Empty for SQLite DB. The password is automatically encrypted upon saving the config.ini.
* **TABLE_PREFIX**, the Storage Management DB table prefix. Should be populated when you want to re-use the same Storage Management DB for several implementers. For example, set TABLE_PREFIX=dev1_. Once the table prefix is set, the default tables are created in the Storage Management DB and are used each time you connect to Fabric BI. 
* **BI_REST_KEY**, a key to be used to authenticate REST requests from the Fabric server to Fabric BI. The default value must be set to **1234** and it is automatically encrypted upon saving the config.ini. You can use the default value during the development, however it is important to update it prior to moving the project to Production. The update should be done in both the config.ini and in the BI configuration file WebReports.xml. [Click to get the explanation about how to update the REST key](99_bi_admin_config.md#REST-Key). 

**Important**: the Admin module of BI has a Storage Management page which displays the default settings. These settings are <u>not</u> applicable in the BI solution, since Fabric retrieves the Storage Management connection details from the **config.ini**!

~~~
[bi]
## BI server host
#BI_HOST=
## BI Storage Management host, empty for SQLite
#STORAGE_MGMT_HOST=
## BI Storage Management Listener port
#BI_PORT=5432
## BI Storage Management name, default = StorageMgmt for both SQLite and PostgreSQL
#STORAGE_MGMT_DB_NAME=StorageMgmt
## BI Storage Management type, the values are SQLite, PostgreSQL
#STORAGE_MGMT_DB_TYPE=SQLite
## BI Storage Management provider, the values are SQLite (for SQLite), Devart.Data.PostgreSql (for PostgreSQL)
#STORAGE_MGMT_DB_PROVIDER=SQLite
## BI Storage Management user, empty for SQLite
#STORAGE_MGMT_DB_USER=
## BI Storage Management password, empty for SQLite
#STORAGE_MGMT_DB_PASSWORD=
## BI Storage Management table prefix
#TABLE_PREFIX=
## BI REST key
#BI_REST_KEY=~encs0~lH54NQEhYq/LCeBX+jQYCw==
~~~
### Project Initialization in BI on Deploy

Upon the completion of installation and configuration setup, deploy the Fabric project. 

* When the Storage Management DB is PostgreSQL, the DB is initialized by creating 4 tables (with  predefined table prefix) and the **project name** folder is created in the Storage Management DB metadata. 
* When a default SQLite DB is used the Storage Management DB and no table prefix is set, the DB initialization is not required and is skipped by Deploy.
* When a default SQLite DB is used with a table prefix, initialize the Storage Management DB manually with the desired prefix value as described [here](99_bi_admin_config.md#storage-management-initialization). Then set the TABLE_PREFIX in the config.ini to the same value and deploy your project.

Note that Full Deploy must be performed rather than [Soft Deploy](/articles/16_deploy_fabric/01_deploy_Fabric_project.md). To deactivate Soft Deploy if it was activated in the Fabric Studio, go to the [User Preferences > Server Configuration](/articles/04_fabric_studio/04_user_preferences.html#what-is-the-purpose-of-the-server-configuration-tab) window and uncheck the Soft Deploy checkbox.

Now any user accessing this project has the default read-only access to the project's reports within the Designer. To setup different access permissions per Fabric role, refer to the [Access Permissions Setup](02_Permissions_Setup.md) article which explains how to do this. 

### Can I Run Several Fabric Projects on the Same BI Installation?

It is possible to use the same BI host and the same Storage Management DB (even the same table prefix) by different Fabric Projects. Meaning both Fabric Projects have the same settings in the [bi] section of config.ini. In this case, upon deploying each project a folder is created in the Storage Management DB metadata and it gives the default read-only access to the users defined under each project. When logging into the Web Framework > BI, the project's users will see only the current project's folder and not the other one.

For example, two projects are deployed on the same Storage Management DB and use the same table prefix: Customer360 and TDM.

* When the users of a Customer360 project login into the Web Framework > BI, they can see the Customer360 folder. The TDM folder is hidden from them.
* When the users of a TDM project login into the Web Framework > BI, they can see the TDM folder. The Customer360 folder is hidden from them.



[![Previous](/articles/images/Previous.png)](00_BI_user_guide_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_Permissions_Setup.md) 



