# TDM Installation and Initial Configuration

This document outlines the installation guidelines and initial configuration activities required for a new TDM installation or an upgrade to the current TDM version.

## Table of Contents

- [TDM Development Environment Installation](#tdm-development-environment-installation)
    - [TDM On-Prem Installation - Desktop Studio](#tdm-on-prem-installation---desktop-studio)
    - [TDM On-Prem Installation - Web Studio](#tdm-on-prem-installation---web-studio)
    - [TDM K2view Cloud Installation](#k2view-cloud-development-environment-installation)     
- [TDM Non-Development Environment Installation](#tdm-non-development-environment-installation)
    - [On-Prem VM Installation](#on-prem-vm-installation) 
    - [K2view Cloud Installation](#k2view-cloud-installation)

- [TDM Initial setup](#tdm-initial-setup)
- [Optional-TDM AI Installation](#optional---tdm-ai-installation)

## TDM Development Environment Installation

### TDM On-Prem Installation - Desktop Studio

#### Prerequisites

- Download and install Fabric 8.3.x studio.
- Create a project in the Fabric studio for the TDM. It is recommended to maintain this project in a pre-configured GitHub repository.
- Create a PostgreSQL DB - a  PostgreSQL DB is required for Fabric System (operational) DB and TDM operational DB (TDM 9.4 was certified based on V17). Note that you can download a PG image  from k2view download page. For more details, read [here](/articles/98_maintenance_and_operational/Installations/Linux/PGSQL_setup.md).

#### TDM Library Installation

- Download the TDM Library export files from the links provided by your K2view representative. 

<ul>
    <li><a href="https://k2view.sharepoint.com/:f:/r/sites/KS/Releases/K2V%20Product%20Documents/TDM/v9.x/V9.4?csf=1&web=1&e=jANmIa">Download Links for K2view Representatives</a></li>
</ul>

- Once downloaded, import the TDM Library export file using the **Import All** option — right-click on the root of the Project Tree, click on Import, and then choose Import All to open the File Browser and select the export file to be imported — to import the TDM LU export file. The following LUs would then be imported into your project: TDM, TDM_LIBRARY, and the TDM_TableLevel.

Click [here](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) for more information about the TDM Library.

Click [here](/articles/04_fabric_studio/11_fabric_studio_exporting_and_importing%20a_fabric_project.md#how-can-i-import-a-k2export-file-into-my-project) for more information about the import options.

#### TDM Deployment

- If you use **Cassandra** as Fabric’s system DB, you must edit the **SEQ_CACHE_INTERFACE** Global and update its value to **DB_CASSANDRA**.
- Do the following in order to use the **PostgreSQL** DB as the Fabric system DB:
  - Open Fabric’s **config.ini** file and edit the **[system_db]** section’s attributes, including the SYSTEM_DB_DATABASE attribute, to be aligned with the **POSTGRESQL_ADMIN** DB interface. 

- Set the **POSTGRESQL_ADMIN interface** to be **active**.
- Edit the **TDM** and **POSTGRESQL_ADMIN** interfaces with the installed PostgreSQL connection details.
- Set the **CREATE_TDMDB** TDM LU's Global to be **true**.
- Optional - edit the **TDMDB_SCHEMA** shared Global if you wish to create a different schema than **public** for the TDM DB. Restart Fabric after updating this Global.
- Deploy the TDM LU. The TDM LU deployment create the TDM DB and the k2masking schema. Note that the k2masking schema can also be created by the **masking-create-cache-table.flow** execution (taken from the Broadway examples).
- After the TDM DB is created, set the **CREATE_TDMDB** TDM LU's Global back to **false**.

### TDM On-Prem Installation - Web Studio

#### Prerequisites

- [Install **Docker Compose** container](https://github.com/k2view/blueprints/blob/main/Studio/Docker/README.md) to host the Web studio.

  [Install the Fabric 8.3.x web studio](/articles/98_maintenance_and_operational/Installations/Fabric_Web_Studio/version_2-1/README.md). Use the **studio_pg** profile: a Web Studio with PostgreSQL for use with its System DB and TDM.

#### TDM Library Installation

**An internet access is available**: open the Fabric Web studio, click the [Extensions](/articles/04_fabric_studio/28_web_k2exchange.md) icon, and select the **TDM** to install the TDM library.

**An internet access is unavailable**:  

- Download the VSIX file from the download page and upload it to the TDM project: Right-click the project-resources from the Project tree > Upload Files… > select the downloaded TDM VSIX file. - 
- Click the Extension icon then click the three dots icon (top-right of the pane) and select the *Install from VSIX…* option. A pop-up window opens: select the uploaded TDM VSIX file and click the *Install from VSIX* button. 

#### TDM Deployment

- Set the **POSTGRESQL_ADMIN interface** to be **active**.
- Edit the **TDM** and **POSTGRESQL_ADMIN** interfaces with the installed PostgreSQL connection details.
- Do the following in order to use the **PostgreSQL** DB as the Fabric system DB:
  - Open Fabric’s **config.ini** file and edit the **[system_db]** section’s attributes, including the SYSTEM_DB_DATABASE attribute, to be aligned with the **POSTGRESQL_ADMIN** DB interface. 
- Set the **CREATE_TDMDB** TDM LU's Global to be **true**.
- Optional - edit the **TDMDB_SCHEMA** shared Global if you wish to create a different schema than **public** for the TDM DB. Restart Fabric after updating this Global.
- Deploy the TDM LU. The TDM LU deployment create the TDM DB and the k2masking schema. Note that the k2masking schema can also be created by the **masking-create-cache-table.flow** execution (taken from the Broadway examples).
- After the TDM DB is created, set the **CREATE_TDMDB** TDM LU's Global back to **false**. 

### K2view Cloud Development Environment Installation

- Create a new space on K2view cloud. Select the **TDM Dev** Project and **TDM-9.4** Profile.
- Set the **CREATE_TDMDB** TDM LU's Global to be **true**.
- Optional - edit the **TDMDB_SCHEMA** shared Global if you wish to create a different schema than **public** for the TDM DB. Restart Fabric after updating this Global.
- Deploy the TDM LU. The TDM LU deployment creates the TDM DB and the k2masking schema. Note that the k2masking schema can also be created by the **masking-create-cache-table.flow** execution (taken from the Broadway examples).
- After the TDM DB is created, set the **CREATE_TDMDB** TDM LU's Global back to **false**.

## TDM Non-Development Environment Installation

### On-Prem VM Installation 

#### Prerequisites

- Install VMs (virtual machines) for Fabric server and the PostgreSQL DB. The PostgreSQL DB is required for Fabric System (operational) DB and TDM operational DB.

- Install Fabric 8.3.x and PostgreSQL DB (TDM 9.4 was certified based on V17).

- Note that Kafka installation is not required for a TDM project.

- For more information see:

  [Fabric 8 Setup Guide](/articles/98_maintenance_and_operational/Installations/Linux/02_Fabric_8.x.x_Setup.md) 

  [Installation Requirements for Kubernetes, Linux, and Docker Fabric & TDM](/articles/98_maintenance_and_operational/Hardware/2_All_Environments/README.md)

  [PostgreSQL Setup](/articles/98_maintenance_and_operational/Installations/Linux/02.2_Fabric_8.x.x_PG_setup.md)

#### Git Clone

- It is recommended to use separate Git branches for Dev, Testing (SIT), and Production environments. Dev changes are merged to the Testing branch, and tested changes are merged from the Testing branch to the Production branch.
- Edit the following Globals in the relevant branch **before** the clone in order to create the TDM DB and k2masking schema in the first TDM LU deployment:
  - **CREATE_TDMDB** TDM LU's Global must be set to **true**
  - Optional - edit the **TDMDB_SCHEMA** shared Global if you wish to create a different schema than **public** for the TDM DB. 
- Clone the relevant GitHub branch. 

#### Build and Deploy the Environments to Fabric

- Build and deploy the [Environments](/articles/25_environments/04_offline_deployment.md) to Fabric. The Environments must be deployed before deploying the TDM project to Fabric. Use the [deploy-environment.sh](https://github.com/K2view-LTD/fabric/blob/fabric-8.2/scripts/deploy-environment.sh) script to deploy the Environments file. 
- Note that the **POSTGRESQL_ADMIN interface** must be **active**.

#### Build and Deploy the TDM Project to Fabric

- Deploy the TDM LU. The TDM LU deployment create the TDM DB and the k2masking schema. After the TDM DB is created, set the **CREATE_TDMDB** TDM LU's Global back to **false**.

- Build and deploy the remaining TDM project to Fabric.

- Click [here](/articles/16_deploy_fabric/03_offline_deploy.md) for more information about offline deployment.

  

### K2view Cloud Installation

- Create a Project with Fabric 8.3.x and PostgreSQL DB.
- Attach the relevant GitHub branch to this project. 
- Edit the following Globals in the relevant branch before the clone in order to create the TDM DB and k2masking schema in the first TDM LU deployment:
  - **CREATE_TDMDB** TDM LU's Global must be set to **true**
  - Optional - edit the **TDMDB_SCHEMA** shared Global if you wish to create a different schema than **public** for the TDM DB. 
- Create a space based on this Project. Deploy the project to Fabric.



## TDM Initial Setup

The following activities must be performed after deploying the TDM project to Fabric:

- [Define Fabric roles](/articles/TDM/tdm_configuration/03_tdm_fabric_credentials.md) - a role for each user group as set in the external IDP. Grant permissions to each role.

- [TDM DB - General Parameters setup](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md).

- TDM self-service application setup:

  - [Permission group mapping](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) - map the Fabric roles related to the TDM users to the TDM permission group (admin/owner/user).

  - [Business entities creation](/articles/TDM/tdm_gui/04_tdm_gui_business_entity_window.md). Note that all LUs must be deployed to Fabric before creating Business Entities (Bes).

  - [Systems creation](/articles/TDM/tdm_gui/05_tdm_gui_product_window.md).

  - [Environments creation and setup](/articles/TDM/tdm_gui/07_tdm_gui_environment_overview.md) - create all environments in TDM self-service application. Optional - add permission sets to the environments to assign testers to these environments and define their TDM permissions.
    Note that the environments must be deployed to Fabric before creating the environments in the TDM self-service application.

    

## Optional - TDM AI Installation

The TDM equips your QA and development teams with cutting-edge AI-driven synthetic data generation, transforming test data creation from manual rule-based scripts into intelligent automation:

- **AI-First Data Synthesis**: TDM seamlessly integrates with AI models to train on your existing data schema and generate realistic, production-grade synthetic entities—all within the platform.
- **One-Click AI Workflows**: Select a business entity, choose your training model, specify volume, and launch a “generate new data” task. The system handles model selection, data ingestion into Fabric, and optional direct loading into test environments 
- **Robust Implementation Controls**: Easily configure AI endpoints using global settings—like AI_DB_INTERFACE, AI_ENVIRONMENT, and AI_EXECUTION—so teams can tailor connectivity, environments, and cleanup protocols 
- **Hybrid, Business-Ready Approach**: Choose between rule-based or AI-based generation per scenario—ideal for use cases ranging from edge-case testing to large-scale synthetic population 
- **Seamless Integration & Compliance**: Generated entities come with built-in handling for sequence IDs, LUI mapping, and referential integrity. All data is cataloged in Fabric and masked as needed. 

The [TDM AI installation guide](TDM_AI_Installation_V9.x.md) outlines the key infrastructure and application setup steps required to integrate K2View TDM with AI-powered capabilities, covering everything from GPU-enabled environment provisioning to project configuration, cleanup processes, and performance testing.





