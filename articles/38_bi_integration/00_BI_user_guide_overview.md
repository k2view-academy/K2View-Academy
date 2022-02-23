# BI Implementation Steps 

Working with Fabric's BI can be divided into three logical steps:

1. Setup the BI (mostly one-time activities).
2. Develop the BI.
3. Deploy the BI to PROD.

To implement BI you must first install it and perform other one-time steps. Once the installation and configuration is complete, you work with the **Designer** module. This module allows you to create and generate reports. When the reports are tested and ready for execution, they can be moved from a development into QA or Production environment.

### Step 1 - Setup BI

This user guide provides the step-by-step description of how to set up BI:

* [Installation and Configuration](01_Installation.md).
* [Permissions setup](02_Permissions_Setup.md).
* [Metadata setup](03_Metadata_Setup.md) using the **Admin** module via the **Web Framework > BI**.
* [Parameters definition](04_parameters.md) using the **Admin** module via the **Web Framework > BI**.

### Step 2 - Develop BI

Once the setup is complete, you can start using the BI application by creating various types of reports and dashboards based on your requirements. 

The links below summarize important points to take into consideration when creating and running the reports over Fabric using the **Designer** via the **Web Framework > BI**:

* [Reports creation guidelines](05_report_creation_guidelines.md).
* [Reports execution guidelines](06_report_execution_guidelines.md).

Fabric's BI application is based on ExagoBI. More information on how to create, design, and generate reports can be found in the [Exago Support Center](https://exagobi.com/support/).

### Step 3 - Deploy BI to PROD

The procedures of moving the implementation between different environments are described below:

* [Moving files between Storage Management DBs](07_moving_reports_between_env.md).
* [BI Configuration Deployment to Production](08_moving_from_dev_to_prod.md).



[![Previous](/articles/images/Previous.png)](00_BI_integration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](01_Installation.md) 
