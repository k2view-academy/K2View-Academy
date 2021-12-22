# Update BI Configuration by Fabric Command

The process of updating the BI configuration in production can be performed manually, like it is performed in any other environment. However in order to keep the sensitive information safe, Fabric provides a command that automates this process.

The command pre-requisite is to have the updated WebReports.XML file in the production BI server. Do the following steps manually:

* Go to Configuration folder on the Production BI server: /opt/apps/exago/Config.

* Backup the current WebReports.xml and WebReports.xml.enc files.

* Copy the WebReports.xml from your source BI server (e.g. QA) here.

* Change the file ownership to apache by running the following command:

  ~~~
  chown apache:apache WebReports.xml
  ~~~

The system pre-requisite for running this process is to install PHP on your Fabric server. 

To start the automatic process, run the command as follows:

~~~
MOVE_TO_PROD_BI ENV = [ENV NAME]
~~~

The command performs the following:

* Match the BI's data sources to the Fabric interfaces on the given Environment by its name.
  * Note that if BI is using a Fabric as a data source, it must be defined as an interface.
  * Example: if BI includes two data sources named Fabric-Local and Postgres-Local, you must have Fabric interfaces with the same names.
* All not matched data sources and interfaces are disregarded. 
* For each match, take the connection details from the Fabric interface and populate them into the BI.
* Once all the matched interfaces were updated, trigger the creation of the encrypted WebReports.xml file.
* Then, delete unencrypted WebReports.xml file.

After the command execution is completed, restart the BI server manually.