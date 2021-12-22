# Restrict Access to BI Admin

To prevent access to sensitive information in a Production environment, the user access to the **Admin** module should be restricted and the **WebReports.XML** file should be removed, keeping the encrypted file only.

Restrict the access to the **Admin** by doing one of the following:

- Run the [REVOKE command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#additional-commands)

  Or

- Via the Web Framework by opening **Admin** > **Security** > **Permissions**, and selecting a Role:

<img src="images/permissions_setup_2.PNG" alt="image" />

Then click the <img src="images/dots_icon.PNG" alt="image" /> icon and click **Revoke Resources**.