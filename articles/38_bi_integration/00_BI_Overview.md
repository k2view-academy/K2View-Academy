# BI User Guide Overview

Fabric provides a built-in integration with [Exago BI](https://support.exagoinc.com/hc/en-us) - an external Business Intelligence (BI) system that enables the design and execution of reports and dashboards. The BI application can be access via the the [K2View Web Framework](https://support.k2view.com/Academy_6.5/articles/30_web_framework/01_web_framework_overview.html). Two applications are included:

* **BI Admin** - system configuration and initial setup. The access to this module is restricted based on Fabric user role. Prior to starting your work on the reports, perform the following setup steps using **BI Admin**. Each of these steps is described in more detail in subsequent articles.
  * [Installation and Storage Management setup](01_Installation.md).
  * [Access rules and permissions settings](02_Permissions_Setup.md).
  * [Metadata setup](03_Metadata_Setup).
  * [Parameters definition]()

* **BI Designer** - creation and generation of the reports. Any user can access this module however the access to various content elements (folders, reports, etc.) within this application can be restricted by setting up the access permission rules. 
  * [Reports creation guidelines](05_report_creation_guidelines.md).

When the report is created, tested and ready for execution, it can be invoked from the Fabric user code using a Fabric JOB framework. This can be done using a JOBTYPE = GENERATE_BI. See more at [Reports execution guidelines](06_report_execution_guidelines.md). 





[<img align="right" width="60" height="54" src="/articles/images/Next.png">](01_Installation.md) 
