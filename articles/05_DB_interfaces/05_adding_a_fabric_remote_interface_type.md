<studio>
      
# Adding a Remote Fabric Interface Type

A new DB interface can be created in Fabric based on **Fabric Remote** interface type. 

* A **Fabric Remote** interface type refers to a remote Fabric server that belongs to a different Fabric cluster. Unlike an internal (local) Fabric DB interface, its settings include the Host and Port of the remote Fabric server.

A **Fabric Remote** DB interface type is defined in a project in the following cases: 
* When getting data from a remote Fabric cluster.
* When using a Fabric Remote interface to run the [Query Builder](/articles/11_query_builder/01_query_builder_overview.md#query-builder-overview) on Fabric. 


### How Do I Create a Remote Fabric Interface?

1.	Go to the **Project Tree**, click **Shared Objects**, right click **Interfaces**, and then select **New Interface**.
2.	Click **Interface Type** to open the dropdown list and then select **Fabric Remote**.
3.	Populate the [Connection Settings](/articles/05_DB_interfaces/03_DB_interfaces_overview.md#database-connection-settings) fields.
4.	Click **Test Connection** to verify that the connection settings are correct:
       * If the connection is OK, the **Connection is OK notification** is displayed on the bottom of the window.
       * If the connection fails, a description of the problem is displayed on the bottom of the window. 
5.	Click **Save**.

[![Previous](/articles/images/Previous.png)](04_creating_a_new_database_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_editing_interface_settings.md)
      
</studio>
