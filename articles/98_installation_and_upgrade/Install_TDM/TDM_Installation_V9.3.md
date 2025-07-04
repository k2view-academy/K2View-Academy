# TDM Installation and Initial Configuration

This document outlines the installation guidelines and initial configuration activities required for a new TDM installation or an upgrade to the current TDM version.

## Table of Contents

  - [Using Pre-created DB Schema for the TDMDB Creation](#using-pre-created-db-schema-for-the-tdmdb-creation)
  - [Deployment Order](#deployment-order)
  - [TDM On-Premises Installation](#tdm-on-premises-installation)
    - [Prerequisites](#prerequisites)
      - [Linux Installation](#linux-installation)
      - [Docker Installation](#docker-installation)
    - [Fabric Studio (Desktop Studio) — Import the TDM Library](#fabric-studio-desktop-studio--import-the-tdm-library)
    - [TDM Web Application](#tdm-web-application)
    - [Create the TDM PostgreSQL DB (in case of a new installation)](#create-the-tdm-postgresql-db-in-case-of-a-new-installation)
    - [Upgrade the TDM PostgreSQL DB (if not a new installation)](#upgrade-the-tdm-postgresql-db-if-not-a-new-installation)
    - [Create K2masking Schema](#create-k2masking-schema)
    - [Add Permission Groups Mapping to the TDM](#add-permission-groups-mapping-to-the-tdm)
  - [TDM Cloud Installation](#tdm-cloud-installation)
    - [TDM AI Installation](#tdm-ai-installation)
  - [TDM Initial Configuration](#tdm-initial-configuration)
    - [Configure the Fabric's System DB including the Masking DB Global](#configure-the-fabrics-system-db-including-the-masking-db-global)
    - [Increase the Maximum Number of Records for TDM Tasks](#increase-the-maximum-number-of-records-for-tdm-tasks)


## Using Pre-created DB Schema for the TDMDB Creation 
If you've pre-created a database schema for TDM DB instead of using the default, do the following:
- Ensure the schema name is configured in both objects, the **TDMDB_SCHEMA** Global variable and the **TDMDBSchema** Actor.
- Complete this configuration **before deploying** the TDM Logical Unit.

## Deployment Order
 - Edit and deploy the Environments before the TDM LU.

## TDM On-Premises Installation 

### Prerequisites

#### Linux Installation 

The following components must be installed:

- **Fabric Server** — TDM 9.3.x is compatible with Fabric 8.2.x and later versions. 
- **PostgreSQL DB** — the TDM DB tables are created on a PostgreSQL DB. PostgreSQL versions 15 or 17 are certified. For more details, read [here](/articles/98_maintenance_and_operational/Installations/Linux/PGSQL_setup.md).

#### Docker Installation 

Click here to open the [TDM 9.3.x Docker Installation document](/articles/98_maintenance_and_operational/Installations/Docker/TDM/TDM_Docker_Installation_V9.3.md).

### Fabric Studio (Desktop Studio) — Import the TDM Library

Both TDM layers — the backend and frontend — are included in the TDM library starting from version 7.6.

Download the TDM Library export files from the link you  can obtain from your K2view representative. 
<ul>
    <li><a href="https://k2view.sharepoint.com/:f:/r/sites/KS/Releases/K2V%20Product%20Documents/TDM/v9.x/V9.3?csf=1&web=1&e=jANmIa">Internal Download Links for K2view Representatives</a></li>
</ul>

Once downloaded, import and deploy them:

- Use the **Custom Import** option — right-click on the root of the Project Tree, click on Import, and then click Custom Import — to import the TDM Library export file, which imports the TDM utilities into your project.
- Use the **Import All** option — right-click on the root of the Project Tree, click on Import, and then choose Import All to open the File Browser and select the export file to be imported — to import the TDM LU export file. The following LUs would then be imported into your project: TDM, TDM_LIBRARY, and the TDM_TableLevel.

Click [here](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) for more information about the TDM Library.

Click [here](/articles/04_fabric_studio/11_fabric_studio_exporting_and_importing%20a_fabric_project.md#how-can-i-import-a-k2export-file-into-my-project) for more information about the import options.

### TDM Web Application

Import the TDM LU to the Fabric project and deploy it to Fabric. Use the *Import All* option to import the TDM Portal (self-service) code (stored in the web subfolder) into the TDM LU. The TDM LU's deployment also deploys the TDM Portal to Fabric and adds it to the web applications in your Fabric web framework.

Click for more information about [Fabric Web Framework](/articles/30_web_framework/01_web_framework_overview.md).


### Create the TDM PostgreSQL DB (in case of a new installation)

**For new installations**:

- From TDM 7.6 onwards, TDM creates the TDM DB objects by the TDM LU's deploy flow:

  - It creates the TDM DB tables, sequences, views, and functions.

**Note**: 
  - You must set the **POSTGRESQL_ADMIN interface** to be **active** to **create the TDMDB database and 'tdm' user (role) in the PostgreSQL DB**.

### Upgrade the TDM PostgreSQL DB (if not a new installation)

**For existing installations**:

- Deploy the TDM LU to the Fabric server. The TDM deploy flow runs the Run the **RunTDMDBUpgradeScripts** flow. Before the deploy, verify that the TDM interface is updated with the TDM DB connection details.

For more details, read [TDM Upgrade Document](/Release_Notes_And_Upgrade/TDM-V9.3/TDM_Upgrade_Procedure_to_V9.3.pdf).


### Create K2masking Schema

The **k2masking** schema is needed for a TDM implementation in order to support masking or sequence handling. The **k2masking** schema is **automatically created** by the **TDM LU's deploy.flow** upon the deployment of the TDM LU to Fabric. Alternatively, you can run the **masking-create-cache-table.flow** from the library of Broadway examples or run the **create_masking_cache_table.sql** of the [TDM Library](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) to create the **K2masking** schema if needed.

### Add Permission Groups Mapping to the TDM

The TDM Portal application is pre-integrated with the [Fabric Web Framework](/articles/30_web_framework/02_preintegrated_apps_overview.md). The user logs into the Fabric Web Framework, and **Fabric authenticates the user**. The TDM Portal gets the **user id** and the user's **Fabric roles** from the user's session and **identifies the user type (Permission Group) by their Fabric roles**.

The mapping of each Fabric role to a TDM Permission Group is done by the [Permission Groups Mapping](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) TDM window and is kept in [permission_groups_mapping TDM DB table](/articles/TDM/tdm_architecture/02_tdm_database.md#permission_groups_mapping).

After installing TDM, the admin user must [log into the TDM Portal](/articles/TDM/tdm_gui/01_tdm_gui_overview.md#tdm-gui---login), open the [Permission Groups Mapping window](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) and define the Permission Group mapping of each user's group (= Fabric role). This process is done in order to enable the TDM users to use the TDM Portal based on their Permission Groups.



## TDM Cloud Installation 

The TDM project's profile must contain Fabric + PostgreSQL. Open the Fabric **Web Studio**, click the **Extensions** icon, and select TDM to install the TDM library.

Click [here](/articles/04_fabric_studio/28_web_k2exchange.md) for more information about Fabric extensions. 



### TDM AI Installation

K2View’s Test Data Management (TDM) 9.0 equips your QA and development teams with cutting-edge AI-driven synthetic data generation—transforming test data creation from manual rule-based scripts into smart automation:

- **AI-First Data Synthesis**: TDM seamlessly integrates with AI models to train on your existing data schema and generate realistic, production-grade synthetic entities—all within the platform.
- **One-Click AI Workflows**: Simply select a business entity, choose your training model, specify volume, and launch a “generate new data” task. The system handles model selection, data ingestion into Fabric, and optional direct loading into test environments 
- **Robust Implementation Controls**: Easily configure AI endpoints using global settings—like AI_DB_INTERFACE, AI_ENVIRONMENT, and AI_EXECUTION—so teams can tailor connectivity, environments, and cleanup protocols 
- **Hybrid, Business-Ready Approach** : Choose between rule-based or AI-based generation per scenario—ideal for use cases ranging from edge-case testing to large-scale synthetic population 
- **Seamless Integration & Compliance**: Generated entities come with built-in handling for sequence IDs, LUI mapping, and referential integrity. All data is cataloged in Fabric and masked as needed. 

The [TDM AI installation guide](TDM_AI_Installation_V9.x.md) outlines the key infrastructure and application setup steps required to integrate K2View TDM 9.0 with AI-powered capabilities, covering everything from GPU-enabled environment provisioning to project configuration, cleanup processes, and performance testing.


## TDM Initial Configuration

### Configure the Fabric's System DB including the Masking DB Global

- TDM can work on either 1 of the following 2 Fabric system DBs: 

  - Cassandra
  - PostgreSQL

- A new Global has been added in TDM 8.1 — SEQ_CACHE_INTERFACE. This Global is populated with the DB interface of the k2masking DB (PostgreSQL or Cassandra) and must be aligned with Fabric’s system DB. TDM 9.x sets the **POSTGRESQL_ADMIN** as a **default** value in this Global:

  - If you use **Cassandra** as Fabric’s system DB, you must edit the SEQ_CACHE_INTERFACE Global and update its value to **DB_CASSANDRA**.

  - If you wish to use the **PostgreSQL** DB as the Fabric system DB, do the following:
    - Open Fabric’s **config.ini** file and edit the **[system_db]** section’s attributes, including the SYSTEM_DB_DATABASE attribute, to be aligned with the **POSTGRESQL_ADMIN** DB interface. 

### Increase the Maximum Number of Records for TDM Tasks

- The default limitation on the number of processed records is 100K records. If your tables have a higher number of records, do the following:

  - Open the **config.ini** file and edit the **[broadway]** section — add the **MAX_CONCRETE_ARRAY_SIZE** attribute and set its value with a value higher than 100,000; for example: 

    ```
    MAX_CONCRETE_ARRAY_SIZE=50000000
    ```

    

  - Restart Fabric.
