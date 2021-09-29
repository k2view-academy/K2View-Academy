# Fabric - BI Integration Overview

[Exago BI](https://support.exagoinc.com/hc/en-us) is an external Business Intelligence (BI) system that enables the design and execution of reports and dashboards. It can connect to various data sources to read the data. The reports metadata is kept in an external Storage Management database. The BI application can be accessed via the [K2View Web Framework](https://support.k2view.com/Academy_6.5/articles/30_web_framework/01_web_framework_overview.html). Fabric integration with the BI is described in the following diagram:

![arc](images/bi_integration_architecture.PNG)



The **BI** includes the following modules:

* **BI Admin** - system configuration and initial setup module which allows to: 

  - Create data sources and define their metadata (objects and joins).
  - Create parameters for applying a filter within reports.
  - Update various system configurations (advanced).

  The access to this module is restricted based on Fabric user role. The explanation about how to restrict the access is described further in this User guide.

* **BI Designer** - content creation module which allows to:

  * Create various types of reports and dashboards. 
  * Generate the reports / dashboards and download the extract files.

  Note that the reports can also be generated from Fabric user code. The way to do it is described further in this User guide.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](00_BI_user_guide_overview.md) 
