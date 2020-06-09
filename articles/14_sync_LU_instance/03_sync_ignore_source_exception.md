# Sync - Ignore Source Exception

### How Do I Set Fabric to Ignore a Source Exception when Running a Sync on an LU Instance?

The Sync process retrieves source data from source systems. By default, an error message is displayed when Fabric fails to connect to a data source or if a data source is unavailable.

For example:

* 	Get Customer 46:
    * 	Customer LU source system = Oracle DB.
    * 	[DB interface name](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) = CRM_DB.
Run the following command on Fabric:  get Customer.46;
The following error is displayed:
“Failed to connect to interface CRM_DB: java.sql.SQLException: 
Listener refused the connection with the following error: 
ORA-12514, TNS: listener does not currently know of service requested in connect descriptor”

Click for more information about the Get Instance Fabric Command.

In specific cases, you may prefer to roll back the sync without getting an error (exception) when the source system cannot be accessed.
 
For example:

If the source DB is down for maintenance, you may prefer getting the latest version of the instance from Fabric instead of getting an error.

To do so, use the following Fabric command: set ignore_source_exception.

Syntax:

- set ignore_source_exception <true/false>;

Syntax-Examples:

-   set ignore_source_exception true;
-   set ignore_source_exception false;

When setting the ignore_source_exception to True, if Fabric fails to connect to the source system, Fabric does not throw an exception, but rolls back the changes of the LUI whereby the LUI version is not updated.\
Note that if the required LUI is not yet in Fabric, the ignore_source_exception is set to True and the following message is generated:\
“Not attached [LU Name]”.

Click for more information about LU Instance Storage and Versions.
 


[![Previous](/articles/images/Previous.png)](/articles/14_sync_LU_instance/02_sync_modes.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/14_sync_LU_instance/04_sync_methods.md)
