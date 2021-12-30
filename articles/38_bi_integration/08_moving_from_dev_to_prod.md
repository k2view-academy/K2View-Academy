# BI Configuration Deployment to Production

When moving the BI configuration from a non-production to a production environment, you must keep the sensitive information safe. The BI configuration parameters are stored in the **WebReports.XML** file, and include the following:

* Data sources definition (including sensitive data, such as the connection strings).
* Metadata definition (object and joins).
* Session parameters.

The E2E process of deployment to a Production environment includes moving both the configuration parameters and the contents of the data (reports, dashboards). 

The steps to do so are as follows: 

1. Move the reports to the production Storage Management DB. 
   * This is performed by running a BI utility as explained [in the previous article](07_moving_reports_between_env.md).
2. Update the BI Configuration in the Production environment with the relevant connection details. The purpose of this step is to update the connection details of the data sources defined in the WebReports.XML file with the Production environment connection details.
   * This is performed by running a Fabric command **MOVE_TO_PROD_BI** described [in the next article](09_update_BI_configuration.md).
3. Restrict the user access to the BI's Admin module as described [here](10_restrict_access_to_BI_Admin.md).





[![Previous](/articles/images/Previous.png)](07_moving_reports_between_env.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_update_BI_configuration.md)
