# BI Configuration Deployment to Production

BI configuration is stored in the **WebReports.XML** file and it includes the following data:

* Data sources definition (including sensitive data, such as the connection strings).
* Metadata definition (object and joins).
* Session parameters.

When moving the BI configuration from a non-production to a production environment, you want to keep the sensitive information safe. 

The E2E process of deployment to production includes moving both the configuration and the content (reports, dashboards) and the steps are:

1. Move the reports to the production Storage Management DB. This is performed by running a BI utility as explained [here](07_moving_reports_between_env.md).
2. Update the BI Configuration in the Production ENV with the relevant connection details. This is performed by running a Fabric command **MOVE_TO_PROD_BI**.
3. Restrict the user access to the BI's Admin module.





[![Previous](/articles/images/Previous.png)](07_moving_reports_between_env.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_update_BI_configuration.md)
