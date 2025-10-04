# TDM Installation and Initial Configuration

This document outlines installation guidelines and initial configuration steps for a new TDM installation. The procedure for upgrading to TDM V9.4 is described in the [TDM upgrade document](/articles/98_installation_and_upgrade/Upgrade_TDM/TDM_Upgrade_Procedure_to_V9.4.pdf).


## Table of Contents

- [TDM Development Environment Installation](#tdm-development-environment-installation)
  - [Fabric Web Studio for K2cloud, Docker, or Podman TDM Installation](#fabric-web-studio-for-k2cloud-docker-or-podman-tdm-installation)
  - [Desktop Studio TDM Installation](#desktop-studio-tdm-installation)
- [TDM Non-Development Environment Installation](#tdm-non-development-environment-installation)
  - [Non-Development Prerequisites](#non-development-prerequisites)
  - [About Git](#about-git)
  - [On-Prem VM Installation](#on-prem-vm-installation)
  - [K2cloud Installation](#k2cloud-installation)
- [TDM Initial Setup](#tdm-initial-setup)
- [TDM AI Installation](#tdm-ai-installation)


## TDM Development Environment Installation

### Fabric Web Studio for K2cloud, Docker, or Podman TDM Installation

#### Prerequisites

1. Verify that the respective Fabric and TDM versions conform to these version requirements: https://support.k2view.com/Academy/articles/Product_Versions/TDM_versions.html.
2. TDM requires Postgres as its database.
3. For installation of TDM on Docker or Podman, when performing the steps to [Create and Launch a Fabric Space](/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#step-7-create-and-launch-a-fabric-space), it is essential to use the **studio_pg** profile, which utilizes PostgreSQL for the System DB and TDM.

##### Docker or Podman-based Installations

You can install TDM within your K2cloud Kubernetes self-hosted environment, and also Docker or Podman container runtimes on a VM or a computer. 

Here are instructions links for installing Fabric Web Studio on Docker or Podman : 

1. [Install Fabric Web Studio for Docker Compose](/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md).
2. [Install Fabric Web Studio for Podman](/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation-podman.md).


#### TDM Library Installation

##### Internet Access is Available

If internet access is available, perform the following steps: 

- Open the Fabric Web Studio.
- Click the [Extensions](/articles/04_fabric_studio/28_web_k2exchange.md) icon.
- Select **TDM** to install the TDM Library.

##### Internet Access is Unavailable

If internet access is unavailable, follow the following steps:  

- Download the VSIX file from the download page. Please request a link to this file from your K2view representative.
  - <a href="https://k2view.sharepoint.com/:f:/r/sites/KS/Releases/K2V%20Product%20Documents/TDM/v9.x/V9.4?csf=1&web=1&e=jANmIa">Download Links for K2view Representatives</a>. This link is accessible only to K2view representatives.
    
- Upload the file to the TDM project: 
  -  Right-click on **project-resources** from the Project tree.
  -  Select **Upload Files…** and choose the downloaded TDM VSIX file. 
- Click the **Extensions** icon.
- Click the three-horizontal-dots menu (top-right of the pane).
- Select *Install from VSIX…*.
- In the pop-up window, select the uploaded TDM VSIX file.
- Click *Install from VSIX*. 

#### TDM Deployment

When using Fabric Web Studio with K2cloud, TDM uses a Postgres instance named `postgres-service`.

When using Fabric Web Studio with Docker Compose or Postman, TDM uses a Postgres database with an instance name generated from the space's name and `-postgres` appended to it. For example, if the space's name is `myspacepg`, the database instance's host name will be `myspacepg-postgres`.

To verify what is configured, please open Fabric’s `workspace/config/config.ini` file. In the [system_db] section, you will find various attributes, including the SYSTEM_DB_HOST attribute. This is the host to use when configuring the host value for the POSTGRESQL_ADMIN interface.

- For both the **TDM** and **POSTGRESQL_ADMIN** interfaces set the connection details as follows:

  - Set the Host value.
    - For K2cloud use: `postgres-service`.
    - For Docker Compose or Podman use: `{your spacename}-postgres`. Please review the note above.
    
  - Set the Port. By default, it is `5432`.
  - The database's name on the interface
    - For the **POSTGRESQL_ADMIN** interface use: `postgres`.
    - For the **TDM** interface use: `TDMDB`.
   
  - Set the User as: `postgres` with Password as: `postgres`.
  - Save the interface.
     - Don’t test the connection of the TDM interface because it hasn't been created yet. This will be performed in the next step.
       
  - Set the interface as **active**.
 
- Set the **CREATE_TDMDB** Global in the TDM LU to **true**.
  
    > You will find the Globals.java file at this location: `workspace/project/Implementation/LogicalUnits/TDM/Java/src/com/k2view/cdbms/usercode/lu/TDM/Globals.java`

  
    > Note. The default schema name of the TDM DB contains the cluster ID.
    > 
    > Changing the TDM DB schema name: If you would like to change the schema name for the TDM DB, please edit the **TDMDB_SCHEMA** shared Global. Restart Fabric after updating this Global.

- Deploy the TDM LU.
  - This deployment creates the TDM DB and the k2masking schema.
    
    > Note that the k2masking schema can also be created by running the **masking-create-cache-table.flow** from the Broadway Examples.

- After the TDM DB is created, set the **CREATE_TDMDB** Global in the TDM LU back to **false** in the Globals.java file.

- The deployment will add a *TDM* item to the list of Fabric applications in the top left main menu. 

    > In some situations, you may need to clear your browser's cache for the *TDM* item to become visible from the main menu.


### Desktop Studio TDM Installation

#### Prerequisites

- Download and install Fabric V8.3.X Studio.
- Create a project in the Fabric Studio for TDM. It is recommended to maintain this project in a pre-configured GitHub repository.
- Create a PostgreSQL DB — a PostgreSQL DB is required for Fabric System (operational) DB and TDM operational DB (TDM V9.4 was certified based on PG V17). Note that you can download a PG image from the K2view download page. For more details, read [here](/articles/98_maintenance_and_operational/Installations/Linux/PGSQL_setup.md).

#### TDM Library Installation

- Download the TDM Library export files from the links provided by your K2view representative.
  - <a href="https://k2view.sharepoint.com/:f:/r/sites/KS/Releases/K2V%20Product%20Documents/TDM/v9.x/V9.4?csf=1&web=1&e=jANmIa">Download Links for K2view Representatives</a>. This link is accessible only to K2view representatives.


- Once downloaded, import the TDM Library export file using the **Import All** option: Right-click on the root of the Project tree, click on **Import**, and select **Import All...**, then in the File Browser, choose the export file to be imported. The following LUs would then be imported into your project: TDM, TDM_LIBRARY, and the TDM_TableLevel.

Click [here](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) for more information about the TDM Library.

Click [here](/articles/04_fabric_studio/11_fabric_studio_exporting_and_importing%20a_fabric_project.md#how-can-i-import-a-k2export-file-into-my-project) for more information about ***Import* options**.

#### TDM Deployment

- If you use **Cassandra** as the Fabric system DB, you must edit the **SEQ_CACHE_INTERFACE** Global and update its value to **DB_CASSANDRA**.
- Perform the following step to use the **PostgreSQL** DB as the Fabric system DB:
  - Open Fabric’s **config.ini** file and edit the **[system_db]** section’s attributes, including the SYSTEM_DB_DATABASE attribute, to be aligned with the **POSTGRESQL_ADMIN** DB interface. 

- Set the **POSTGRESQL_ADMIN interface** to **active**.
- Edit the **TDM** and **POSTGRESQL_ADMIN** interfaces with the installed PostgreSQL connection details.
- Set the **CREATE_TDMDB** Global in the TDM LU to **true**.
- Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then edit the **TDMDB_SCHEMA** shared Global. Restart Fabric after updating this Global.
- Deploy the TDM LU. This deployment creates the TDM DB and the k2masking schema. Note that the k2masking schema can also be created by running the **masking-create-cache-table.flow** from the Broadway Examples (found in the Broadway Flow window, Main Menu > Actions > Examples and select this flow).
- After the TDM DB is created, set the **CREATE_TDMDB** Global in the TDM LU back to **false**.


## TDM Non-Development Environment Installation

This section outlines the steps for installing TDM in non-development environments.
Two deployment models are supported: On-Prem VM Installation and K2cloud Installation.

### Non-Development Prerequisites

The following prerequisites apply to both deployment models:
- Fabric: Install Fabric v8.3.x.
- PostgreSQL: Required for both the Fabric System DB (operational) and the TDM operational DB.
    - TDM v9.4 was certified against PostgreSQL v17.
- Kafka: Not required for TDM projects.
- Git: Recommended use of separate branches for development, testing (SIT), and production.
    - Development → Testing → Production merge flow.
    - Before cloning the branch, edit the following Globals to create the TDM DB and k2masking schema during the first TDM LU deployment:
        - CREATE_TDMDB Global must be set to true.
        - (Optional) If changing schema name: edit TDMDB_SCHEMA shared Global.

#### References
- VM References
    - [Fabric 8 Setup Guide](/articles/98_installation_and_upgrade/Install_on_Linux/02_Fabric_8.x.x_Setup.md)
    - [PostgreSQL Setup](/articles/98_installation_and_upgrade/Install_on_Linux/02.2_Fabric_8.x.x_PG_setup.md)
    - [VM Requirements](/articles/98_installation_and_upgrade/Hardware_Linux_Docker/README.md)
- K2cloud / Kubernetes Deployments
    - [Kubernetes System Requirements](/articles/98_installation_and_upgrade/Hardware_K8s/README.md)

 
### About Git

- It is recommended to use separate Git branches for development, testing (SIT), and production environments. Changes from the development branch are merged into the testing branch, and once tested, changes from the testing branch are merged into the production branch.
- Edit the following Globals in the relevant branch **before** cloning in order to create the TDM DB and k2masking schema during the first TDM LU deployment:
  - The **CREATE_TDMDB** Global in the TDM LU must be set to **true**.
  - Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then edit the **TDMDB_SCHEMA** shared Global. 
- Clone the relevant GitHub branch. 

### On-Prem VM Installation 

1. Provision VMs
    - One VM for Fabric Server.
    - One VM for PostgreSQL DB.
2. Install Fabric and PostgreSQL
    - Follow Fabric 8 setup guide and PostgreSQL setup guide.
3.	Clone Project Branch
    - Clone the relevant Git branch (after editing Globals as described above).
4.	Build and Deploy Environments
    - Build and deploy the environment configuration before deploying the TDM project. See [environments](/articles/25_environments/04_offline_deployment.md)
    - Use deploy-environment.sh.
    - Ensure the POSTGRESQL_ADMIN interface is active.
5.	Deploy the TDM Project
    - Deploy the TDM LU → creates the TDM DB and k2masking schema.
    - After creation, set CREATE_TDMDB Global back to false.
    - Build and deploy the remaining TDM components.
6.	Offline Deployment
    - Refer to [Offline Deployment Instructions](/articles/16_deploy_fabric/03_offline_deploy.md).



### K2cloud Installation

1. Create Project
    - With Fabric v8.3.x and PostgreSQL DB.
2.	Attach GitHub Branch
	- Edit Globals (CREATE_TDMDB, TDMDB_SCHEMA optional) before cloning to create the TDM DB and k2masking schema during the first TDM LU deployment:
        - The **CREATE_TDMDB** Global in the TDM LU must be set to **true**.
        - Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then Edit the **TDMDB_SCHEMA** shared Global. 
3.	Create a Space
	- Based on the project.
	- Deploy project to Fabric.


## TDM Initial Setup

The following activities must be performed after deploying the TDM project to Fabric:

- [Define Fabric roles](/articles/TDM/tdm_configuration/03_tdm_fabric_credentials.md) — one for each user group as defined in the external IDP, and grant permissions to each role.

- [TDM DB — General Parameters setup](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md).

- TDM self-service application setup:

  - [Permission group mapping](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) — map the Fabric roles related to the corresponding TDM users to the TDM permission group (admin/owner/user).

  - [Creating Business Entities](/articles/TDM/tdm_gui/04_tdm_gui_business_entity_window.md). Note that all LUs must be deployed to Fabric before creating Business Entities (BEs).

  - [Creating Systems](/articles/TDM/tdm_gui/05_tdm_gui_product_window.md).

  - [Environment creation and setup](/articles/TDM/tdm_gui/07_tdm_gui_environment_overview.md) — create all environments in the TDM self-service application.
      - Optional: Add permission sets to the environments to assign testers to these environments; this would define their TDM permissions.
      - Note that the environments must be deployed to Fabric before creating the environments in the TDM self-service application.

    

## TDM AI Installation  

TDM equips your QA and development teams with cutting-edge AI-driven synthetic data generation, transforming test data creation from manual rule-based scripts into intelligent automation:

- **SDG (Synthetic Data Generation) based on AI**: TDM seamlessly integrates with AI models to train on the existing data schema and generate realistic, production-grade synthetic entities — all within the platform.
- **AI Workflows with One Click**: Select a Business Entity, choose your training model, specify the data volume, and launch a 'generate new data' task. The system handles model selection, data ingestion into Fabric, and optionally loads the data directly into test environments. 
- **Robust Implementation Controls**: Configure AI endpoints easily using global settings — such as AI_DB_INTERFACE, AI_ENVIRONMENT, and AI_EXECUTION — allowing teams to customize connectivity, environments, and cleanup protocols. 
- **Hybrid, Business-Ready Approach**: Choose between rule-based or AI-based data generation for each scenario, which is an ideal approach for use cases ranging from edge-case testing to large-scale synthetic data population. 
- **Seamless Integration & Compliance**: Generated entities include built-in support for sequence IDs, LUI mapping, and referential integrity. All data is cataloged in Fabric and masked as required. 

The [TDM AI installation guide](TDM_AI_Installation_V9.x.md) outlines the essential infrastructure and application setup steps needed to integrate K2view's TDM with AI-powered capabilities. It covers everything from provisioning GPU-enabled environments to project configuration, cleanup processes, and performance testing.





