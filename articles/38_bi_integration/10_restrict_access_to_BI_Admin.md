# Restrict Access to BI Admin

To prevent access to sensitive information in a Production environment, carry out these two actions: 

a) Remove the **WebReports.XML** file (keeping the encrypted file only)

b) Restrict the user access to the **BI Admin** module 

Restrict the access to the **BI Admin** module by doing one of the following:

- Run the [REVOKE command](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#additional-commands)

  Or

- Via the Web Framework by opening **Admin** > **Security** > **Permissions**, and selecting a Role:

<img src="images/permissions_setup_2.PNG" alt="image" />

Then click the <img src="images/dots_icon.PNG" alt="image" /> icon and click **Revoke Resources**.


[![Previous](/articles/images/Previous.png)](09_update_BI_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](99_bi_admin_config.md)
