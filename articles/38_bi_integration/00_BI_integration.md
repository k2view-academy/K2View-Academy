# Fabric - BI Integration Overview

Fabric includes a business intelligence application called **Fabric BI**. Fabric BI enables the design and execution of reports and dashboards, and can connect to various data sources to read the data. The reports metadata is kept in an external Storage Management database. Fabric BI can be accessed via the [K2View Web Framework](https://support.k2view.com/Academy_6.5/articles/30_web_framework/01_web_framework_overview.html). Fabric integration with Fabric BI is described in the following diagram:

![arc](images/bi_integration_architecture.PNG)



The **Fabric BI** application includes the following modules:

* **BI Admin** - system configuration and initial setup module which enables the user to do the following: 

  - Create data sources and define their metadata (objects and joins).
  - Create parameters for applying a filter within reports.
  - Update various system configurations (advanced).

  Access to Fabric BI is restricted based on the Fabric user role. The explanation about how to control access and define user roles is described at this 
  [link]( https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.5/articles/38_bi_integration/02_Permissions_Setup.md).

* **BI Designer** - content creation module which enables the user to do the following:

  * Create various types of reports and dashboards. 
  * Generate the reports / dashboards and download the extract files.

  Note that the reports can also be generated from Fabric user code. The way to do it is described is described at this 
  [link]( https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.5/articles/38_bi_integration/05_report_creation_guidelines.md) 
 

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](00_BI_user_guide_overview.md) 
