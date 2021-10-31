# BI Configuration Deployment to Production

### Overview

**WebReports.XML** is the main Fabric BI configuration file which stores all the BI configurations, including  some sensitive data, such as the data sources connection strings. When the **BI Admin** is updated by the user, the **WebReports.XML** file is updated and the encrypted **WebReports.XML.enc** file is created in the same location on Fabric BI server.

To prevent access to sensitive information in Production environment, the user access to **BI Admin** module should be restricted and the **WebReports.XML** file should be removed, keeping the encrypted file only.

The following article describes the semi-automatic process of moving the configuration from DEV or QA environment to Production.

### How Do I Restrict the Access to BI Admin Module?

Restrict the access to **BI Admin** either by:

* Running the [REVOKE command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#additional-commands)

  Or

* Via the Web Framework by opening **Admin** > **Security** > **Permissions**, and selecting a Role:

<img src="images/permissions_setup_2.PNG" alt="image" />

Then click the <img src="images/dots_icon.PNG" alt="image" /> icon and click **Revoke Resources**.

### How Do I Deploy BI to Production?



TBD