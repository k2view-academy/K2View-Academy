# TDM Installation and Initial Configuration

## Table of Contents

- [Before you begin](#before-you-begin)
- [Upgrading](#upgrading)
- [TDM Development Environment Installation](#tdm-development-environment-installation)
  - [Web Studio TDM Installation for K2cloud, Docker, or Podman](#web-studio-tdm-installation-for-k2cloud-docker-or-podman)
      _ [Prerequisites](#prerequisites)
      - [Docker or Podman-based Installations](#docker-or-podman-based-installations)
      - [TDM Library Installation](#tdm-library-installation)
      - [TDM Deployment](#tdm-deployment) 
  - [Desktop Studio TDM Installation](#desktop-studio-tdm-installation)
      - [Prerequisites](#prerequisites-1)
      - [TDM Library Installation](#tdm-library-installation-1)
      - [TDM Deployment](#tdm-deployment-1) 
- [Git Best Practices](#git-best-practices)
- [TDM Non-Development Environment Installation](#tdm-non-development-environment-installation)
  - [References](#references)
  - [Non-Development Prerequisites](#non-development-prerequisites)
  - [On-Prem VM Installation](#on-prem-vm-installation)
  - [K2cloud Installation](#k2cloud-installation) 
- [TDM Initial Setup](#tdm-initial-setup)
  - [1) Prepare access (Fabric roles & IDP groups)](#1-prepare-access-fabric-roles--idp-groups)
  - [2) Configure TDM DB general parameters](#2-configure-tdm-db-general-parameters)
  - [3) Bring the TDM self-service app online](#3-bring-the-tdm-self-service-app-online)
  - [4) Quick validation](#4-quick-validation)
  - [Notes & Tips](#notes--tips)


## Before you begin

This guide walks you through the installation of **K2view TDM 9.4** and performs the initial setup.

**1) Choose your Development setup**

* **Fabric Web Studio (containerized):** Run Fabric Web Studio in containers (Docker; Podman-compatible in many orgs) on a shared VM or a personal computer. Use the `studio_pg` profile so PostgreSQL backs both the System DB and TDM. Follow **TDM On-Prem — Web Studio** for instructions on installing the in-Studio library or importing a VSIX.
* **K2cloud (Dev):** Create a Dev Space from a TDM project/profile and deploy—quickest path to a working Dev environment. 
* **Desktop Studio:** Install Fabric Studio locally, import the TDM libraries (TDM, TDM_LIBRARY, TDM_TableLevel), then deploy. 

**2) Plan your Git strategy for smooth SIT/Prod rollouts**

Adopt **separate branches** per environment (e.g., `dev` → `sit` → `prod`). Review the [Git Best Practices](#git-best-practices) for guidance. 

**3) What’s required in non-Dev environments**

* **K2cloud:** Create a Fabric 8.3.x + PostgreSQL project, attach the correct Git branch, set the initial Globals, create a Space, and deploy. 
* **On-Prem VM:** Provision VMs for Fabric and PostgreSQL, install Fabric 8.3.x + PG (certified with PG v17). Then build and deploy **Environments** before deploying the TDM project. Offline deployment is supported. 


**4) Initial setup (post-deploy)**

* Define **Fabric roles** and permissions aligned to your IDP groups.
* Configure **TDM DB General Parameters**.
* In the TDM self-service app: map **permission groups**, create **Business Entities** and **Systems**, and create/setup **Environments** (deploy Environments in Fabric before creating them in the app). 

**5) Optional: AI-driven Synthetic Data (TDM-AI)**

If you plan to generate AI-based synthetic data, review the [TDM AI Installation](/articles/98_installation_and_upgrade/Install_TDM/TDM_AI_Installation_V9.x.md) guide. It covers prerequisites (e.g., GCP/GKE and GPU quotas), GPU cluster setup, project configuration, cleanup flows, and performance notes. 

## Upgrading

The procedure for upgrading to TDM V9.4 is described in the [TDM upgrade document](/articles/98_installation_and_upgrade/Upgrade_TDM/TDM_Upgrade_Procedure_to_V9.4.pdf).

## TDM Development Environment Installation

### Web Studio TDM Installation for K2cloud, Docker, or Podman 

#### Prerequisites

1. Verify that the respective Fabric and TDM versions conform to these version requirements: https://support.k2view.com/Academy/articles/Product_Versions/TDM_versions.html.
2. TDM requires Postgres as its database.
3. For installation of TDM on Docker or Podman, when performing the steps to [Create and Launch a Fabric Space](/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#step-7-create-and-launch-a-fabric-space), it is essential to use the **studio_pg** profile, which utilizes PostgreSQL for the System DB and TDM.

#### Docker or Podman-based Installations

You can install TDM within your K2cloud Kubernetes self-hosted environment, and also Docker or Podman container runtimes on a VM or a computer. 

Here are instructions links for installing Fabric Web Studio on Docker or Podman : 

1. [Install Fabric Web Studio for Docker Compose](/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md).
2. [Install Fabric Web Studio for Podman](/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation-podman.md).


#### TDM Library Installation

*Internet Access is Available*

If internet access is available, perform the following steps: 

- Open the Fabric Web Studio.
- Click the [Extensions](/articles/04_fabric_studio/28_web_k2exchange.md) icon.
- Select **TDM** to install the TDM Library.

*Internet Access is Unavailable*

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

*Overview*

Before configuring TDM, note that **Fabric Web Studio’s setup differs slightly depending on where it runs**:

   - **K2cloud (managed):** Fabric Web Studio is provisioned by K2cloud. The TDM project connects to a managed PostgreSQL service exposed in the Space as **`postgres-service`**.
   - **Docker/Podman (self-managed):** Fabric Web Studio runs in your containers. The PostgreSQL service name is derived from the Space name with **`-postgres`** appended. For example, if the Space is `myspacepg`, the Postgres host will be **`myspacepg-postgres`**.

To confirm what your environment is actually using:

   1. Open `workspace/config/config.ini`.
   2. In the `[system_db]` section, locate **`SYSTEM_DB_HOST`** — this is the host you should use when configuring the **`POSTGRESQL_ADMIN`** interface.

   ```ini
   [system_db]
   SYSTEM_DB_HOST=postgres-service            ; K2cloud example
   ; or
   SYSTEM_DB_HOST=myspacepg-postgres          ; Docker/Podman example
   ```
   3. Ensure the **Database** for the admin connection is **`postgres`**, and provide the matching port/credentials.

*Configuration Steps*

- For both the **TDM** and **POSTGRESQL_ADMIN** interfaces set the connection details as follows:

  - Set the Host value.
     - For K2cloud use: `postgres-service`.
     - For Docker Compose or Podman use: `{your spacename}-postgres`. Please consult the overview above.
    
  - Set the Port. By default, it is `5432`.
  - The database's name on the interface
     - For the **POSTGRESQL_ADMIN** interface use: `postgres`.
     - For the **TDM** interface use: `TDMDB`.
   
  - Set the User as: `postgres`
  - Set the Password as: `postgres`.
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

- You are now ready to proceed to the [TDM Initial Setup](#tdm-initial-setup) steps. 


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

 
## Git Best Practices

It is recommended to use separate Git branches for development, testing (SIT), and production environments. Changes from the development branch are merged into the testing branch, and once tested, changes from the testing branch are merged into the production branch.

To do so:
- Edit the following Globals in the relevant branch **before** cloning to create the TDM DB and k2masking schema during the first TDM LU deployment:
  - The **CREATE_TDMDB** Global in the TDM LU must be set to **true**.
  - Optional: If you wish to change the schema name for the TDM DB (the default schema name contains the cluster ID), then edit the **TDMDB_SCHEMA** shared Global. 
- Clone the relevant GitHub branch. 

## TDM Non-Development Environment Installation

This section outlines the steps for installing TDM in non-development environments.
Two deployment models are supported: On-Prem VM Installation and K2cloud Installation.

### References
- K2cloud / Kubernetes Deployments
    - [Kubernetes System Requirements](/articles/98_installation_and_upgrade/Hardware_K8s/README.md)
- VM References
    - [Fabric 8 Setup Guide](/articles/98_installation_and_upgrade/Install_on_Linux/02_Fabric_8.x.x_Setup.md)
    - [PostgreSQL Setup](/articles/98_installation_and_upgrade/Install_on_Linux/02.2_Fabric_8.x.x_PG_setup.md)
    - [VM Requirements](/articles/98_installation_and_upgrade/Hardware_Linux_Docker/README.md)

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

After the TDM project is deployed to Fabric, complete the following one-time setup to make the TDM self-service app usable for your teams. 

### 1) Prepare access (Fabric roles & IDP groups)

* [Create Fabric roles](/articles/TDM/tdm_configuration/03_tdm_fabric_credentials.md) that mirror your identity provider (IDP) groups (e.g., Admins, Owners, Testers) and grant the appropriate Fabric permissions to each role.
* These roles will later be mapped to TDM permission groups inside the TDM app. 

### 2) Configure TDM DB general parameters

* Open [TDM DB — General Parameters](/articles/TDM/tdm_configuration/02_tdmdb_general_parameters.md) and review/update values such as reservation/retention limits, LUI separator, parameter coupling, and portal defaults, according to your governance needs. 

### 3) Bring the TDM self-service app online

1. **Map permission groups**
   In the TDM app, map your Fabric roles to [TDM permission groups](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) (**admin / owner / tester**). This controls what each user can do in TDM. 
2. **Create Business Entities (BEs)**
   [Define BEs](/articles/TDM/tdm_gui/04_tdm_gui_business_entity_window.md) only **after all required LUs have been deployed** to Fabric, so entity definitions can reference deployed LUs. 
3. **Create Systems**
   [Register source/target systems](/articles/TDM/tdm_gui/05_tdm_gui_product_window.md) that BEs will use within tasks. 
4. **Create Environments in the TDM app**

   * Ensure the [environments are built and deployed](/articles/TDM/tdm_gui/07_tdm_gui_environment_overview.md) in Fabric before creating them in the TDM app (TDM references the Fabric environments).
   * (Optional) Add **permission sets** per environment to assign testers and scope their actions. 

### 4) Quick validation

* Sign in with a **tester** account and confirm the correct environment access and task permissions.
* Run a small **extract** or **extract and load** task to verify reservation/retention defaults and LU visibility align with your parameters and role mappings. (Relies on the configuration above.) 

### Notes & Tips

* **Order matters:** Roles/permissions → General parameters → TDM app mappings → Environments. This avoids rework and mismatches between Fabric and TDM. 
* **Environments:** If environments aren’t deployed in Fabric first, they cannot be created reliably in the TDM app. 
* **Next steps:** Once setup is complete, teams can create tasks (extract, load, data generation/versioning) and operate within their assigned environments. 





