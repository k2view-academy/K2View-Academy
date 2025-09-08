# TDM Installation and Initial Configuration

This document outlines installation guidelines and initial configuration steps for a new TDM installation. The procedure for upgrading to TDM V9.4 is described in the [TDM upgrade document](/articles/98_installation_and_upgrade/Upgrade_TDM/TDM_Upgrade_Procedure_to_V9.4.pdf).

## Table of Contents

- [TDM Development Environment Installation](#tdm-on-prem-installation--desktop-studio)
    - [TDM On-Prem Installation — Desktop Studio](#tdm-on-prem-installation--desktop-studio)
    - [TDM On-Prem Installation — Web Studio](#tdm-on-prem-installation--web-studio)
    - [TDM K2view Cloud Installation](#k2view-cloud-development-environment-installation)     
- [TDM Non-Development Environment Installation](#tdm-non-development-environment-installation)
    - [On-Prem VM Installation](#on-prem-vm-installation) 
    - [K2view Cloud Installation](#k2view-cloud-installation)

- [TDM Initial Setup](#tdm-initial-setup)
- [Optional — TDM AI Installation](#optional--tdm-ai-installation)

## TDM Development Environment Installation

### TDM On-Prem Installation — Desktop Studio

#### Prerequisites

- Download and install Fabric V8.3.X Studio.
- Create a project in the Fabric Studio for TDM. It is recommended to maintain this project in a pre-configured GitHub repository.
- Create a PostgreSQL DB — a PostgreSQL DB is required for Fabric System (operational) DB and TDM operational DB (TDM V9.4 was certified based on PG V17). Note that you can download a PG image from the K2view download page. For more details, read [here](/articles/98_maintenance_and_operational/Installations/Linux/PGSQL_setup.md).

#### TDM Library Installation

- Download the TDM Library export files from the links provided by your K2view representative. 

<ul>
    <li><a href="https://k2view.sharepoint.com/:f:/r/sites/KS/Releases/K2V%20Product%20Documents/TDM/v9.x/V9.4?csf=1&web=1&e=jANmIa">Download Links for K2view Representatives</a></li>
</ul>

- Once downloaded, import the TDM Library export file using the **Import All** option: Right-click on the root of the Project tree, click on **Import**, and select **Import All...**, then in the File Browser, choose the export file to be imported. The following LUs would then be imported into your project: TDM, TDM_LIBRARY, and the TDM_TableLevel.

Click [here](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) for more information about the TDM Library.

Click [here](/articles/04_fabric_studio/11_fabric_studio_exporting_and_importing%20a_fabric_project.md#how-can-i-import-a-k2export-file-into-my-project) for more information about ***Import* options**.

#### TDM Deployment

- If you use **Cassandra** as the Fabric system DB, you must edit the **SEQ_CACHE_INTERFACE** Global and update its value to **DB_CASSANDRA**.
- Perform the following step in order to use the **PostgreSQL** DB as the Fabric system DB:
  - Open Fabric’s **config.ini** file and edit the **[system_db]** section’s attributes, including the SYSTEM_DB_DATABASE attribute, to be aligned with the **POSTGRESQL_ADMIN** DB interface. 

- Set the **POSTGRESQL_ADMIN interface** to **active**.
- Edit the **TDM** and **POSTGRESQL_ADMIN** interfaces with the installed PostgreSQL connection details.
- Set the **CREATE_TDMDB** Global in the TDM LU to **true**.
- Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then edit the **TDMDB_SCHEMA** shared Global. Restart Fabric after updating this Global.
- Deploy the TDM LU. This deployment creates the TDM DB and the k2masking schema. Note that the k2masking schema can also be created by running the **masking-create-cache-table.flow** from the Broadway Examples (found in the Broadway Flow window, Main Menu > Actions > Examples and select this flow).
- After the TDM DB is created, set the **CREATE_TDMDB** Global in the TDM LU back to **false**.

### TDM On-Prem Installation — Web Studio

#### Prerequisites

- [Install **Docker Compose** container](https://github.com/k2view/blueprints/blob/main/Studio/Docker/README.md) to host the Web Studio.

  [Install Fabric V8.3.X Web Studio](/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/README.md). Use the **studio_pg** profile, which is a Web Studio with PostgreSQL for use with its System DB and TDM.

#### TDM Library Installation

**If internet access is available**, perform the following steps: 

- Open the Fabric Web Studio.
- Click the [Extensions](/articles/04_fabric_studio/28_web_k2exchange.md) icon.
- Select **TDM** to install the TDM Library.

**If internet access is unavailable**, perform the following steps:  

- Download the VSIX file from the download page.
- Upload the file to the TDM project: 
  -  Right-click on **project-resources** from the Project tree.
  -  Select **Upload Files…** and choose the downloaded TDM VSIX file. 
- Click the **Extensions** icon.
- Click the three-horizontal-dots menu (top-right of the pane).
- Select *Install from VSIX…*.
- In the pop-up window, select the uploaded TDM VSIX file.
- Click *Install from VSIX*. 

#### TDM Deployment

- Set the **POSTGRESQL_ADMIN interface** to **active**.
- Edit the **TDM** and **POSTGRESQL_ADMIN** interfaces with the installed PostgreSQL connection details.
- Perform the following step in order to use the **PostgreSQL** DB as the Fabric system DB:
  - Open Fabric’s **config.ini** file and edit the **[system_db]** section’s attributes, including the SYSTEM_DB_DATABASE attribute, to be aligned with the **POSTGRESQL_ADMIN** DB interface. 
- Set the **CREATE_TDMDB** Global in the TDM LU to **true**.
- Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then Edit the **TDMDB_SCHEMA** shared Global. Restart Fabric after updating this Global.
- Deploy the TDM LU. This deployment creates the TDM DB and the k2masking schema. Note that the k2masking schema can also be created by running the **masking-create-cache-table.flow** from the Broadway Examples.
- After the TDM DB is created, set the **CREATE_TDMDB** Global in the TDM LU back to **false**. 

### K2view Cloud Development Environment Installation

- Create a new Space on K2cloud. Select the **TDM Dev** Project and **TDM-9.4** Profile.
- Set the **CREATE_TDMDB** Global in the TDM LU to **true**.
- Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then edit the **TDMDB_SCHEMA** shared Global. Restart Fabric after updating this Global.
- Deploy the TDM LU. This deployment creates the TDM DB and the k2masking schema. Note that the k2masking schema can also be created by running the **masking-create-cache-table.flow** from the Broadway Examples.
- After the TDM DB is created, set the **CREATE_TDMDB** Global in the TDM LU back to **false**.

## TDM Non-Development Environment Installation

### On-Prem VM Installation 

#### Prerequisites

- Install VMs (virtual machines) for the Fabric server and the PostgreSQL DB. The PostgreSQL DB is required for Fabric System (operational) DB and TDM operational DB.

- Install Fabric V8.3.X and PostgreSQL DB (TDM V9.4 was certified based on PG V17).

- Note that Kafka installation is not required for a TDM project.

- For further information, refer to the following articles:

  [Fabric 8 Setup Guide](/articles/98_installation_and_upgrade/Install_on_Linux/02_Fabric_8.x.x_Setup.md)
  
  [Kubernetes System Requirements](/articles/98_installation_and_upgrade/Hardware_K8s/README.md)

  [Linux / Docker Requirements](/articles/98_installation_and_upgrade/Hardware_Linux_Docker/README.md)

  [PostgreSQL Setup](/articles/98_installation_and_upgrade/Install_on_Linux/02.2_Fabric_8.x.x_PG_setup.md)

#### Git Clone

- It is recommended to use separate Git branches for development, testing (SIT), and production environments. Changes from the development branch are merged into the testing branch, and once tested, changes from the testing branch are merged into the production branch.
- Edit the following Globals in the relevant branch **before** cloning in order to create the TDM DB and k2masking schema during the first TDM LU deployment:
  - The **CREATE_TDMDB** Global in the TDM LU must be set to **true**.
  - Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then edit the **TDMDB_SCHEMA** shared Global. 
- Clone the relevant GitHub branch. 

#### Build and Deploy the Environments to Fabric

- Build the [environments](/articles/25_environments/04_offline_deployment.md) and deploy them to Fabric. Their deployment must take place before deploying the TDM project to Fabric. Use the [deploy-environment.sh](https://github.com/K2view-LTD/fabric/blob/fabric-8.2/scripts/deploy-environment.sh) script to deploy the *Environments* file. 
- Note that the **POSTGRESQL_ADMIN interface** must be **active**.

#### Build and Deploy the TDM Project to Fabric

- Deploy the TDM LU. This deployment creates the TDM DB and the k2masking schema. After the TDM DB is created, set the **CREATE_TDMDB** Global in the TDM LU back to **false**.

- Build and deploy the remaining TDM project components to Fabric.

- Click [here](/articles/16_deploy_fabric/03_offline_deploy.md) for more information about offline deployment.

  

### K2view Cloud Installation

- Create a Project with Fabric V8.3.X and PostgreSQL DB.
- Attach the appropriate GitHub branch to this project. 
- Edit the following Globals in the relevant branch **before** cloning in order to create the TDM DB and k2masking schema during the first TDM LU deployment:
  - The **CREATE_TDMDB** Global in the TDM LU must be set to **true**.
  - Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then Edit the **TDMDB_SCHEMA** shared Global. 
- Create a Space based on this Project. Deploy the project to Fabric.



## TDM Initial Setup

The following activities must be performed after deploying the TDM project to Fabric:

- [Define Fabric roles](/articles/TDM/tdm_configuration/03_tdm_fabric_credentials.md) — one for each user group as defined in the external IDP, and grant permissions to each role.

- [TDM DB — General Parameters setup](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md).

- TDM self-service application setup:

  - [Permission group mapping](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) — map the Fabric roles related to the corresponding TDM users to the TDM permission group (admin/owner/user).

  - [Creating Business Entities](/articles/TDM/tdm_gui/04_tdm_gui_business_entity_window.md). Note that all LUs must be deployed to Fabric before creating Business Entities (BEs).

  - [Creating Systems](/articles/TDM/tdm_gui/05_tdm_gui_product_window.md).

  - [Environment creation and setup](/articles/TDM/tdm_gui/07_tdm_gui_environment_overview.md) — create all environments in the TDM self-service application.
      - Optional: Add permission sets to the environments to assign testers to these environments and define their TDM permissions.
      - Note that the environments must be deployed to Fabric before creating the environments in the TDM self-service application.

    

## Optional — TDM AI Installation  

TDM equips your QA and development teams with cutting-edge AI-driven synthetic data generation, transforming test data creation from manual rule-based scripts into intelligent automation:

- **SDG (Synthetic Data Generation) based on AI**: TDM seamlessly integrates with AI models to train on the existing data schema and generate realistic, production-grade synthetic entities — all within the platform.
- **AI Workflows with One Click**: Select a Business Entity, choose your training model, specify the data volume, and launch a 'generate new data' task. The system handles model selection, data ingestion into Fabric, and optionally loads the data directly into test environments. 
- **Robust Implementation Controls**: Configure AI endpoints easily using global settings — such as AI_DB_INTERFACE, AI_ENVIRONMENT and AI_EXECUTION — allowing teams to customize connectivity, environments, and cleanup protocols. 
- **Hybrid, Business-Ready Approach**: Choose between rule-based or AI-based data generation for each scenario, which is an ideal approach for use cases ranging from edge-case testing to large-scale synthetic data population. 
- **Seamless Integration & Compliance**: Generated entities include built-in support for sequence IDs, LUI mapping, and referential integrity. All data is cataloged in Fabric and masked as required. 

The [TDM AI installation guide](TDM_AI_Installation_V9.x.md) outlines the essential infrastructure and application setup steps needed to integrate K2view's TDM with AI-powered capabilities. It covers everything from provisioning GPU-enabled environments to project configuration, cleanup processes, and performance testing.





