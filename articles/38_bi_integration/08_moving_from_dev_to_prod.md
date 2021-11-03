# BI Configuration Deployment to Production

### Overview

**WebReports.XML** is the main BI configuration file which stores all the BI configurations, including  some sensitive data, such as the connection strings of the data sources. When updates in the **Admin** module are carried out by the user, the **WebReports.XML** file is updated and the encrypted **WebReports.XML.enc** file is created in the same location on the BI server.

To prevent access to sensitive information in a Production environment, the user access to the **Admin** module should be restricted and the **WebReports.XML** file should be removed, keeping the encrypted file only.

### How Do I Restrict the Access to the Admin Module?

Restrict the access to the **Admin** by doing one of the following:

* Run the [REVOKE command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#additional-commands)

  Or

* Via the Web Framework by opening **Admin** > **Security** > **Permissions**, and selecting a Role:

<img src="images/permissions_setup_2.PNG" alt="image" />

Then click the <img src="images/dots_icon.PNG" alt="image" /> icon and click **Revoke Resources**.

