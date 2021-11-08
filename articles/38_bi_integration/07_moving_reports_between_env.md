# Moving Reports Between Storage Management DBs

### Overview

When working on the implementation of BI reports, it is recommended to have separate BI installations and separate Storage Management DBs for different environment types (DEV/QA/PROD), so that you can develop and test the reports without impacting other stages of the project.

At any time, you might need to move reports between different environments, for example from one DEV environment to another, from DEV to QA, etc.

The BI application provides the utility to move your files (reports and dashboards) between different Storage Management DBs or even between different table prefixes within the same Storage Management Database. You can move either one or several reports at a time. 

### How Do I Move Files Between Storage Management DBs?

**Step 1:  Create and Edit the Config JSON File**

Open a command line interface, connect to the BI server and run the following command from the  Application Binaries directory **/opt/apps/exago/bin**.
(Note that the utility can be run only by a user with **root** or **sudo** permissions)

~~~
> sudo mono ImportExportStorageMgmt.exe -f ./reports.json
[sudo] password for k2view:
11:15:07 INFO  - ExportStorageMgmt -f ./reports.json
11:15:07 ERROR - Specified configuration file not found - I'll try and create one for you
11:15:08 INFO  - Created Config File at ./reports.json
~~~

The **reports.json** is a configuration file that includes the source and target DB connection details and the list of folders to be copied. Any name file can be used. If the file with the given name does not exist, it is created with default settings as follows:

~~~json
{
  "SourceDb": {
    "DbType": "SQLite",
    "DbProvider": "SQLite",
    "ConnectionString": "Data Source=SourceDb.sqlite",
    "TablePrefix": null
  },
  "TargetDb": {
    "DbType": "SQLite",
    "DbProvider": "SQLite",
    "ConnectionString": "Data Source=TargetDb.sqlite",
    "TablePrefix": null
  },
  "JsonDirectory": "Json",
  "FieldSpecification": null,
  "BlockList": [
    "Public",
    "My Reports"
  ],
  "AllowList": null,
  "AutoMatch": [
    "Public",
    "My Reports"
  ]
}
~~~

Update the default settings to include the required information. For example, the configuration file shown below defines the source DB to be the default **StorageMgmt.sqlite** DB, the tables with **new_** prefix. The target DB is the PostgreSQL DB, the tables with **demo_** prefix. The only folder included in this operation is **Demo Proj**. The folders excluded from the operation are **Public** and **My Reports**.

~~~json
{
  "SourceDb": {
    "DbType": "SQLite",
    "DbProvider": "SQLite",
    "ConnectionString": "Data Source=/opt/apps/exago/Config/StorageMgmt.sqlite",
    "TablePrefix": "new_"
  },
  "TargetDb": {
    "DbType": "PostgreSQL",
    "DbProvider": "Npgsql",
    "ConnectionString": "Server=<Storage Management host>; Port=5432; Database=StorageMgmt; User Id=<user>; Password=<password>",
    "TablePrefix": "demo_"
  },
  "JsonDirectory": "Json",
  "FieldSpecification": null,
  "BlockList": [
    "Public",
    "My Reports"
  ],
  "AllowList": [
    "Demo Proj"
  ],
  "AutoMatch": null
}
~~~

**Step 2:  Run the Utility**

Once the configuration file is updated, run the utility as follows:

~~~
> sudo mono ImportExportStorageMgmt.exe -f ./reports.json -E -I
12:15:20 INFO  - ExportStorageMgmt -f dev-prod.json -E -I
12:15:20 INFO  - Starting Export
12:15:21 INFO  - Processing Complete, write 2 records
12:15:21 INFO  - Exported    1 records of type 0
12:15:21 INFO  - Exported    1 records of type 1
12:15:21 INFO  - Starting Import
12:15:21 INFO  - Completed Loading Content Records: Inserted 2 content records, and updated 0 content records
12:15:21 INFO  - Completed Loading Access Records: Inserted 2 access records, and updated 0 access records
12:15:21 INFO  - No Orphan Records Located
~~~

**Note:**

* You can run only the Export, only the Import or both Export and Import by specifying the **-E** and **-I** flags as needed.
* The Export process creates JSON files in the **"JsonDirectory"** specified in the configuration file. These files include the definition of the exported reports.  The files are not cleaned, so if they are not required, you will have to remove them manually in order not to be included in the next utility run.



[Click for more information about how to move files between Storage Management DBs in Fabric BI (ExagoBI)](https://exagobi.com/support/administrators/installation-and-configuration/moving-files-between-storage-management-databases/).

