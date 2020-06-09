# Creating a New Database Interface

### How Do I Create a Database Interface?

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces** and select **New Interface**.\
The following screenshot displays a list of supported built-in interface types.
     * ![image](/articles/05_DB_interfaces/images/05_04_icon1.png) = DB interfaces.
     * ![image](/articles/05_DB_interfaces/images/05_04_icon2.png) = Non-DB interfaces.

![image](/articles/05_DB_interfaces/images/05_04_01%20interface%20types.png)

2. Click **Interface Type** and select the **Type** value to open the **DB Interface** window. The **DB Interface** window displays the Connection IDs and Connection Details to be populated. 

![image](/articles/05_DB_interfaces/images/05_04_02%20DB%20Interface.png)

3. Populate the **DB Connection Name** and set the **Connection** to **Active**.

4. Populate the [**Connection Settings**](/articles/05_DB_interfaces/03_DB_interfaces_overview.md#database-connection-settings)

    * For DB Interface types other than Oracle, PostgreSQL or SQL Server, go to the project's **Lib directory** in Windows at:
    **[Your PC Folder]\K2View Fabric Studio\Projects\\[Project Name]\lib** and add a **JDBC driver jar** of this DB type there.   
   
    * To check if the connection settings are correct, click the **Test Connection String**
       * If the connection is OK, the **Connection is OK** notification is displayed on the bottom of the window.
       * If the connection fails, a description of the problem is displayed on the bottom of the window. 
       
5. Optional: Edit the **Pool Properties**.

6. Click **Save**.

    
Note that if required, a [new Database Type] can be defined or an existing Database Type can be overwritten as a part of a product package.

Click for more information about Generic DB Solution, DB Drivers Jars in Fabric Studio and Server.

### DB Interface Window

The DB Interface window enables you to define DB interfaces for your project. By default, new DB interfaces are created in the generic DB interface format. 


**Generic Interface Definition**

![image](/articles/05_DB_interfaces/images/05_04_03%20Generic%20Interface%20Definition.png)

Interfaces created using previous Fabric versions remain as is and can be converted to the generic interface.
 

**Previous Fabric Version Interface Definition**

The following screenshot displays an interface configuration in an older format which uses ADO.NET/ODBC drivers.

![image](/articles/05_DB_interfaces/images/05_04_04%20Previous%20Fabric%20Version%20Interface%20Definition.png)


**When are Interfaces Created in an Older Format in the Current Fabric Version?**

In the current Fabric version, if the project already has at least one interface created in the older format, Fabric preserves this interface format. All new interfaces of the same type are also created in the same format (ADO.NET/ODBC drivers) by default.

An additional reason for an interface to be created in a legacy format in the current Fabric version is the definition of a DB type in the Fabric Studio Config file. The **k2FabricStudio.exe.config** marks a specific DB type as **Legacy** whereby all new interfaces of this type are created using ADO.NET/ODBC drivers.  

The **k2FabricStudio.exe.config** setting is created as follows and can be manually edited by the user at any time:

 ```<add key="UseFabricLegacyAdoDatabaseTypesForNewInterfaces" value="Cassandra"/>``` 


In this setting, value = a list of DB types to be created in an old format, separated by a comma.

Note that it is **recommended** that you convert the existing interface into a generic format to avoid the need for ADO.NET/ODBC drivers. To do so, click the **Convert to Generic DB Interface** link.

New generic interfaces cannot be converted to older interface format based on ADO.NET/ODBC drivers. 

[![Previous](/articles/images/Previous.png)](/articles/05_DB_interfaces/03_DB_interfaces_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/05_DB_interfaces/05_adding_a_fabric_and_remote_fabric_interface_type.md)
