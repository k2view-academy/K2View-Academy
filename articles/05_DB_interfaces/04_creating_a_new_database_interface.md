# Creating a New Database Interface

### How Do I Create a Database Interface?

1. Go to the **Project Tree**, click **Shared Objects**, right click **Interfaces** and select **New Interface**.\
The following screenshot displays a list of supported built-in interface types.
     * ![image] = DB interfaces.
     * ![image] = Non-DB interfaces.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/images/05_04_01%20interface%20types.png)

2. Click **Interface Type** and select the **Type** value to open the **DB Interface** window. The **DB Interface** window displays the Connection IDs and Connection Details to be populated. 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/images/05_04_02%20DB%20Interface.png)

3. Populate the **DB Connection Name** and set the **Connection** to **Active**.

4. Populate the [**Connection Settings**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/03_DB_interfaces_overview.md)

5. To check if the connection settings are correct, click the **Test Connection String**.

   a. If the connection is OK, the **Connection is OK** notification is displayed on the bottom of the window.
   
   b. If the connection fails, a description of the problem is displayed on the bottom of the window. 
6. Optional: Edit the **Pool Properties**.
7. Click **Save**.

For DB Interface types other than Oracle, PostgreSQL or SQL Server, go to the project's **Lib directory** in Windows at:\
**[your PC folder]\K2View Fabric Studio\Projects\[project name]\lib** and add a **JDBC driver jar** of this DB type there. 
    
Note that if required, a [new Database Type] can be defined or an existing Database Type can be overwritten as a part of a Product package.

Click for more information about Generic DB Solution, DB Drivers Jars in Fabric Studio and Server.

### DB Interface Window

The DB Interface window enables you to define DB Interfaces for your project. By default, new DB interfaces are created in the Generic DB Interface format. 

_Generic Interface Definition_

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/images/05_04_03%20Generic%20Interface%20Definition.png)

Interfaces created using previous Fabric versions remain as is and can be converted to the Generic interface.
 
_Previous Fabric Version Interface Definition_

The following screenshot displays an Interface configuration in an older format which uses\
 ADO.NET/ODBC drivers.

![image]

_When are Interfaces Created in an Older Format in the Current Fabric Version?_

In the current Fabric version, if the Project already has at least one interface created in the older format, Fabric preserves this interface format. All new interfaces of the same type are also created in the same format (ADO.NET/ODBC drivers) by default.

An additional reason for an interface to be created in a legacy format in the current Fabric version is the definition of a DB type in the Fabric Studio Config file. The **k2FabricStudio.exe.config** marks a specific DB type as **Legacy** whereby all new interfaces of this type are created using\
 ADO.NET/ODBC drivers.  

The **k2FabricStudio.exe.config** setting is created as follows and can be manually edited by the user at any time:

 ```java<add key="UseFabricLegacyAdoDatabaseTypesForNewInterfaces" value="Cassandra"/>``` 


In this setting, value = a list of DB types to be created in an old format, separated by a comma.

Note that it is **recommended** that you convert the existing interface into a generic format to avoid the need for ADO.NET/ODBC drivers. To do so, click the **Convert to Generic DB Interface** link.

New generic interfaces cannot be converted to older interface format based on ADO.NET/ODBC drivers. 

[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/03_DB_interfaces_overview.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/05_adding_a_fabric_and_remote_fabric_interface_type.md)
