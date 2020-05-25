# Adding a Fabric / Remote Fabric Interface Type

A new DB interface can be created in Fabric based on **Internal Fabric** or **Remote Fabric** interface types. 
* An **Internal Fabric** interface type refers to a local Fabric Server and holds settings like User, Password, and Debug Server. It does not include a Host and a Port since this interface refers to the local Fabric server.
* A **Fabric Remote** interface type refers to a remote Fabric server that belongs to a different Fabric cluster. Unlike an internal (local) Fabric DB interface, its settings include the Host and Port of the remote Fabric server.

An **Internal Fabric** (local Fabric) DB interface type is defined in a project in the following cases:
* When defining a different local Fabric user for a specific Fabric activity or command. 
* When using the data as input for a Parser or any Fabric Project function.
* When building an LU based on another [LU](/articles/03_logical_units/01_LU_overview.md). For example, when creating LU Tables in an Order LU based on a Customer LU.

A **Fabric Remote** DB interface type is defined in a project in the following cases: 
* When getting data from a remote Fabric cluster.
* When using a Fabric Remote interface to run the [Query Builder] on Fabric. 

### How Do I Create an Internal Fabric Interface?

1. Go to the **Project Tree**, click **Shared Objects**, right click **Interfaces**, and then select **New Interface**.
2.	Click **Interface Type** to open the dropdown list and then select **Fabric**.
3.	Define the **User** and **Password** of the internal Fabric interface.
4.	Click **Test Connection** to verify that the connection settings are correct:
       * If the connection is OK, the Connection is OK notification is displayed on the bottom of the window.
       * If the connection fails, a description of the problem is displayed on the bottom of the window. 
5.	Click **Save**.

### How Do I Create a Remote Fabric Interface?

1.	Go to the **Project Tree**, click **Shared Objects**, right click **Interfaces**, and then select **New Interface**.
2.	Click **Interface Type** to open the dropdown list and then select **Fabric Remote**.
3.	Populate the [**Connection Settings**](/articles/05_DB_interfaces/03_DB_interfaces_overview.md#database-connection-settings) fields.
4.	Click **Test Connection** to verify that the connection settings are correct:
       * If the connection is OK, the Connection is OK notification is displayed on the bottom of the window.
       * If the connection fails, a description of the problem is displayed on the bottom of the window. 
5.	Click **Save**.


[![Previous](/articles/images/Previous.png)](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/05_DB_interfaces/06_editing_interface_settings.md)
