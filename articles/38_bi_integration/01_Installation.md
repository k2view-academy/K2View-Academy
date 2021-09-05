# Installation and Initialization

The installation and initialization includes the following steps:

1. [Installation of Exago server and Storage Management DB](01_Installation.md#installation).
2. [System parameters configuration](01_Installation.md#configuration).
3. [Storage Management Initialization](01_Installation.md#Storage-Management-Initialization).
4. [Project Initialization in BI](01_Installation.md#Project-Initialization-in-BI).

Refer to the [Installation Recommendations](01_Installation.md#installation-Recommendations) at the end of this article, for few important points related to different types of environments. 

### Installation

* Install **the latest available Exago version**, that includes the server and client Exago applications. 
  * Download the Exago package or docker image from the K2View download page.
  * *Link to the K2View DevOps document explaining how to install the Exago components - TBD.*
* Install **Storage Management DB**, the database that keeps the report definition such as report type and metadata, currency, decimal setting, fonts, colors and more. [Click for more information about Exago Storage Management](https://support.exagoinc.com/hc/en-us/articles/360042587313-Storage-Management-Introduction).


### Configuration

* Update the **[bi]** section parameters of the **config.ini** as follows:

  * **BI_PORT**, the listener port for BI. The default is 5432.
  * **BI_HOST**, the IP address of the Exago server.
  * **STORAGE_MGMT_DB_NAME**, the name of the Storage Management DB. The default is StorageMgmt.
  * **STORAGE_MGMT_HOST**, the IP address of the Storage Management DB. Empty for SQLite DB.
  * **STORAGE_MGMT_DB_TYPE**, the type of Storage Management DB. The default is SQLite. The recommended type is PostgreSQL.
  * **STORAGE_MGMT_DB_PROVIDER**, the Storage Management DB provider. The default is SQLite. When the Storage Management DB is PostgreSQL, the provider is Npgsql.
  * **STORAGE_MGMT_DB_USER** / **STORAGE_MGMT_DB_PASSWORD**, the Storage Management DB user and password. Empty for SQLite DB. The password is automatically encrypted upon saving of the config.ini.
  * **TABLE_PREFIX**, the Storage Management DB table prefix. Should be populated when you want to re-use the same Storage Management DB for several environments. For example, set TABLE_PREFIX=dev1_.
  * **BI_REST_KEY**, a key to be used to authenticate REST requests. The explanation how to setup the REST key is described further in this article. 

  ~~~
  [bi]
  ## Listener port for bi, default = 5432
  BI_PORT=5432
  ## BI host
  BI_HOST=
  ## BI Storage Management name, default = StorageMgmt for SQLite / PostgreSQL
  STORAGE_MGMT_DB_NAME=StorageMgmt
  ## BI Storage Management host, empty for SQLite
  STORAGE_MGMT_HOST=
  ## BI Storage Management type: SQLite / PostgreSQL
  STORAGE_MGMT_DB_TYPE=SQLite
  ## BI Storage Management provider: SQLite / Npgsql
  STORAGE_MGMT_DB_PROVIDER=SQLite
  ## BI Storage Management user
  STORAGE_MGMT_DB_USER=
  ## BI Storage Management password
  STORAGE_MGMT_DB_PASSWORD=
  ## BI Storage Management table prefix
  TABLE_PREFIX=
  ## BI REST Key
  BI_REST_KEY=
  ~~~

### Storage Management Initialization

[Click to get more information about the Storage Management DB initialization](https://support.exagoinc.com/hc/en-us/articles/360042229693).

### Project Initialization in BI

Upon the completion of installation and configuration setup, deploy the Fabric project. 

As a result, the <project name> folder is created in the Storage Management DB, with the default read-only access permissions to BI Designer - meaning that any user accessing this project can have read-only access to the project's reports within the BI Designer. Continue to the next article or click [here](02_Permissions_Setup.md) to setup the full access permissions. 

### Installation Recommendations

Following are the installation and setup recommendations:

- It is recommended to have 3 separate Exago installations. One each for Dev, QA and Production envrionments.
  - Linux installation is a must for UAT / Production environments.
  - Installation on Windows (as a docker) can be done for development or demo purposes.
- The recommended Storage Management DB type is PostgreSQL.
  - PostgreSQL is a must for UAT / Production environments, but it is preferable to use it for Dev and QA as well.
  - Default SQLite DB can be used for demo purposes. 
- To re-use the same Storage Management DB for several environments of the same type (for example, several QA environments), define a unique Table Prefix as part of the Storage Management DB details in config.ini.


[![Previous](/articles/images/Previous.png)](00_BI_user_guide_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_Permissions_Setup.md) 



