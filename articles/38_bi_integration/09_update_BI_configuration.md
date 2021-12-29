# Update BI Configuration by Fabric Command

Updating the BI configuration in production can be performed manually, in the same way that it is updated in any other environment. However, to keep sensitive information safe, Fabric provides a command that automates this process.

Before starting this process make sure that PHP is installed on your Fabric server, as described in the [Installation guide](\98_maintenance_and_operational\BI_Installation\01_BI_Installation_on_Linux.md).

Do the following:

* Go to the Configuration folder on the Production BI server: /opt/apps/exago/Config.

* Backup the current **WebReports.xml** and **WebReports.xml.enc** files.

* Copy the **WebReports.xml** from your source BI server (e.g. QA) here:  /opt/apps/exago/Config.

* Change the file ownership to apache by running the following command:

  ~~~
  chown apache:apache WebReports.xml
  ~~~

To start the configuration update process, run the command as follows:

~~~
MOVE_TO_PROD_BI ENV = [ENV NAME]
~~~

This command carries out the following actions:

* Matches the BI's data sources to the Fabric interfaces on the given [Environment](/articles/25_environments/01_environments_overview.md) by its name.
  * Note that if BI is using a Fabric as a data source, it must be defined as an interface.
  * Example: if BI includes two data sources named Fabric-Local and Postgres-Local, you must have Fabric interfaces with the same names.
* Data sources and interfaces that are not matched are skipped. 
* For each match, take the connection details from the Fabric interface per the input Environment and populate them into the BI's configuration file **WebReports.xml**.
  * If the Environment name is not provided in the command input, the connection details are taken from the active Environment. 
* Once the connection details of all matched data sources are updated, create the updated encrypted **WebReports.xml.enc** file.
* Delete the unencrypted **WebReports.xml** file.

After the command execution is completed, restart the BI server.




[![Previous](/articles/images/Previous.png)](08_moving_from_dev_to_prod.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_restrict_access_to_BI_Admin.md)
