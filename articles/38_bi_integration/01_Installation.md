# Installation and Initialization

### Installation Steps

* Install **the latest available Exago version**, that includes the server and client Exago applications. 
  * Download the Exago package or docker image from the K2View download page.
  * *Link to the K2View DevOps document explaining how to install the Exago components - TBD.*

* Install **Storage Management DB**, the database that keeps the report definition such as report type and metadata, currency, decimal setting, fonts, colors and more. [Click for more information about Exago Storage Management](https://support.exagoinc.com/hc/en-us/articles/360042587313-Storage-Management-Introduction).

* (Optional) Install **Storage Management Utility**, the UI tool that allows setting up the folders' access permissions. [Click for more information about the Exago Storage Management Utility](https://support.exagoinc.com/hc/en-us/articles/360053801773-Storage-Management-Utility-v2021-1-).

* Update the parameters under the [bi] section of **Fabric config.ini** with Exago host and Storage Management details:

  ~~~
  [bi]
  ## Listener port for bi, default = 5432
  #BI_PORT=
  ## BI host
  BI_HOST=
  ## BI Storage Management name, default = StorageMgmt for SQLite / PostgreSQL
  STORAGE_MGMT_DB_NAME=
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
  ~~~


### Project Initialization in Exago

Upon the completion of installation and configuration setup, deploy the Fabric project. 

As a result, the <project name> folder is created in Exago Storage Management DB, with the default (read-only) access permissions. Continue to the next article or click [here](02_Permissions_Setup.md) to setup the full access permissions. 

### Installation Recommendations

Following are the installation and setup recommendations:

- It is recommended to have 3 separate Exago installations. One each for Dev, QA and Production envrionments.
  - Linux installation is a must for UAT / Production environments.
  - Installation on Windows (as a docker) can be done for development or demo purposes.
- The recommended Storage Management DB type is PostgreSQL.
  - PostgreSQL is a must for UAT / Production environments, but it is preferable to use it for Dev and QA as well.
  - Default SQLite DB can be used for demo purposes. 
- To re-use the same Storage Management DB for several environments of the same type (for example, several QA environments), define a unique Table Prefix as part of the Storage Management DB details in config.ini.


[![Previous](/articles/images/Previous.png)](00_BI_Overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_Permissions_Setup.md) 



