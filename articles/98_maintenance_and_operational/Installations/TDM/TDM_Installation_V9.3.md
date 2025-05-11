# TDM Installation and Initial Configuration

This document describes the installation guidelines and the initial configuration activities required for a new TDM installation or an upgrade of TDM to the current version.

## TDM On-Prem Installation 

### Prerequisites

#### Linux Installation 

The following components must be installed as a prerequisite:

- **Fabric Server** - TDM 9.3.x works with Fabric 8.2.x and above. Read [here](/articles/98_maintenance_and_operational/Installations/Docker/Fabric/README.md).
- **PostgreSQL DB** - the TDM DB tables are created on a PostgreSQL DB. PostgreSQL v15 or v17 versions are certified. For more details, read [here](/articles/98_maintenance_and_operational/Installations/Linux/PGSQL_setup.md).

#### Docker Installation 

Click here to open the [TDM 9.3.x Docker Installation document](/articles/98_maintenance_and_operational/Installations/Docker/TDM/TDM_Docker_Installation_V9.3.md).

### Import the TDM Library

Both TDM layers - backend and frontend - are included in the [TDM library](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) from v7.6 onwards.
Download the TDM Library export files from the [download links](https://k2view.sharepoint.com/:f:/r/sites/KS/Releases/K2V%20Product%20Documents/TDM/v9.x/V9.3?csf=1&web=1&e=jANmIa), import and deploy them:
- Use the **Custom Import** option - right-click on the root of Project Tree, click on Import and then click Custom Import - to import the TDM Library export file in order to import the TDM utils into your project.
- Use the **Import All** option - right-click on the root of the Project Tree, click on Import, and then choose Import All to open the File Browser and select the export file to be imported - to import the TDM LU export file in order to import the following LUs into the project: TDM, TDM_LIBRARY, and the TDM_TableLevel.

Click [here](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) for more information about the TDM Library.

Click [here](/articles/04_fabric_studio/11_fabric_studio_exporting_and_importing%20a_fabric_project.md#how-can-i-import-a-k2export-file-into-my-project) for more information about the import options.

### TDM Web Application

Import the updated TDM LU to the Fabric project and deploy it to Fabric. Use the *Import All* option to import the TDM Portal (self-service) code (stored in the web subfolder) into the TDM LU. The TDM LU's deployment also deploys the TDM Portal to Fabric and adds it to the web applications in your Fabric web framework.

Click for more information about [Fabric Web Framework](/articles/30_web_framework/01_web_framework_overview.md).


### Create the TDM PostgreSQL DB (in case of a new installation)

- From TDM 7.6 onwards, TDM creates the TDM DB objects by the TDM LU's deploy flow:

  - It creates the TDM DB tables, sequences, views, and functions.

  Notes: 

  - You must set the **BUILD_TDMDB Global to true in order to create the TDM DB** by the TDM deploy flow.
  - You must set the **POSTGRESQL_ADMIN interface** to be **active** in order to **create the TDMDB database and 'tdm' user (role) in the postgreSQL DB**.

### Upgrade the TDM PostgreSQL DB (if not a new installation)

- Deploy the TDM LU to the Fabric server. The TDM deploy flow runs the Run the **RunTDMDBUpgradeScripts** flow. Before the deploy, verify that the TDM interface is updated with the TDM DB connection details.
- For more details, read [TDM Upgrade Document](/Release_Notes_And_Upgrade/TDM-V9.3/TDM_Upgrade_Procedure_to_V9.3.pdf).


### Create K2masking Schema

The **k2masking** schema is needed for a TDM implementation in order to support masking or sequence handling. The **k2masking** schema is **automatically created** by the **TDM LU's deploy.flow** upon the deployment of the TDM LU to Fabric. Alternatively, you can run the **masking-create-cache-table.flow** from the library of Broadway examples or run the **create_masking_cache_table.sql** of the [TDM Library](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) to create the **K2masking** keyspace if needed.

### Add Permission Groups Mapping to the TDM

The TDM Portal application is pre-integrated with [Fabric Web Framework](/articles/30_web_framework/02_preintegrated_apps_overview.md). The user logs into the Fabric Web Framework and **Fabric authenticates the user**. The TDM Portal gets the **user id** and the user's **Fabric roles** from the user's session and **identifies the user type (Permission Group) by their Fabric roles**.

The mapping of each Fabric role to a TDM Permission Group is done by the [Permission Groups Mapping](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) TDM window and is kept in [permission_groups_mapping TDM DB table](/articles/TDM/tdm_architecture/02_tdm_database.md#permission_groups_mapping).

After installing TDM, the admin user must [log into the TDM Portal](/articles/TDM/tdm_gui/01_tdm_gui_overview.md#tdm-gui---login), open the [Permission Groups Mapping window](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) and define the Permission Group mapping of each user's group (= Fabric role). This process is done in order to enable the TDM users to work properly on the TDM Portal, based on their Permission Groups.



## TDM Cloud Installation 

The TDM project's profile must contain Fabric  + PosgreSQL. Open the Fabric Web Studio, click the Extension icon, and select TDM to install the TDM library.

Click [here](/articles/04_fabric_studio/28_web_k2exchange.md) for more information about Fabric's extensions. 



### TDM AI Installation

Click [here](TDM_AI_Installation_V9.x.md) to open the TDM AI installation document.



## TDM Initial Configuration

### Configure the Fabric's System DB including the Masking DB Global

- TDM can work on either 1 of the following 2 Fabric system DBs: 

  - Cassandra
  - PostgreSQL

- A new Global has been added in TDM 8.1 - SEQ_CACHE_INTERFACE. This Global is populated with the DB interface of the k2masking DB (PostgreSQL or Cassandra) and must be aligned with Fabric’s system DB. TDM 9.x sets the **POSTGRESQL_ADMIN** as a **default** value in this Global:

  - If you use **Cassandra** as Fabric’s system DB, you must edit the SEQ_CACHE_INTERFACE Global and update its value to **DB_CASSANDRA**.

  - If you wish to use the **PostgreSQL** DB as Fabric system DB, do the following:
    - Open Fabric’s **config.ini** file and edit the **[system_db]** section’s attributes including the SYSTEM_DB_DATABASE attribute to be aligned with the **POSTGRESQL_ADMIN** DB interface. 

### Increase the Maximum Number of Records for TDM Tasks

- The default limitation on number of processed records is 100K records. If your tables have a higher number of records, do the following:

  - Open the **config.ini** file and edit the **[broadway]** section – add the **MAX_CONCRETE_ARRAY_SIZE** attribute and set its value with a value higher than 100,000; for example: 

    ```
    MAX_CONCRETE_ARRAY_SIZE=50000000
    ```

    

  - Restart Fabric.
