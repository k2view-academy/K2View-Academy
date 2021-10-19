# Moving to Production

### Overview

**WebReports.XML** is the main Fabric BI configuration file which stores all the BI configurations, including  some sensitive data, such as the data sources connection strings. When the **BI Admin** is updated by the user, the **WebReports.XML** file is updated and the encrypted **WebReports.XML.enc** file is created in the same location on Fabric BI server.

To prevent access to sensitive information in Production environment, the user access to **BI Admin** module should be restricted and the **WebReports.XML** file should be removed, keeping the encrypted file only.

The following article describes the semi-automatic process of moving the configuration from DEV or QA environment to Production.

### How Do I Deploy BI to Production?



