# Database Types

### Overview

Sometimes in a project implementation it is required to define a database type according to the Database and JDBC driver specifications.

Creating a new database type is supported in Fabric and it allows supporting additional database types without Fabric product enhancement. It is also possible to override one of the database types provided as a part of the product package, if customizations are required.

### How Do I Create a Database Type?

When introducing a new database type, the user should locate the JDBC driver files under: 

**[Your PC Folder]\K2View Fabric Studio\Projects\\[Project Name]\lib\\[new database type]**.

To create a new database type, do the following:

1. Go to **Project Tree** > **Shared Objects**, right click **Database Types** and select **New Database Type**.

   ![image](images/05_10_1.PNG)

2. Populate the **Driver's Settings**: Name, Class Name, URL Template and Default Port (optional).

3. Optional: Edit the **Pool Properties** and the **Fabric Properties**.
   * The Pool and Fabric Properties should be modified only by advanced users. At initial setup, use the default values.
   * Note that in order to to connect a REAL datatype field (Oracle) to a TEXT datatype field (PostgreSQL) in the LU table, set the **Support Implicit Conversions** parameter of Fabric Properties to **False** (the default value is True).

4. In case the JDBC driver doesn’t provide access to meta data, populate the **Studio – Metadata** settings as follows:
   * Populate **SQL Query for Tables List** and **SQL Query for Column List** settings with a SQL query.
   * Populate **SQL Query for FKs List** and **SQL Query for PKs List with a custom SQL queries retrieving the list of foreign keys (FK) and primary keys (PK) of the tables. These keys can be used by the auto-discovery wizard when creating a new [Logical Unit](/articles/03_logical_units/01_LU_overview.md). 

5. Set **Query Builder: identifiers Case Sensitivity** as either:
   * Insensitive
   * SenstiveUpperCase
   * SensitiveLowerCase

6. Set **JDBC Connection Properties** as follows:
   * Set a **Name** and a **Value** for parameters to be used as a part of JDBC connection properties when defining the interface based on the new database type.
   * When setting the **Enable Interface Override**, user will be able to change the parameter value on the interface and it will appear under the Connection Properties section. 
   * When setting the **Connection Property**, the connection properties map will be sent to JDBC driver.

7. **Save** the database type.

### How Do I Create a Database Type from Template?

Creation of a new database type from template is required, when a user wants to customize a product build-in database type.

To create a new database type from template, do the following:

1. Go to **Project Tree** > **Shared Objects**, right click **Database Types** and select **New Database Type From Template** and then select the required DB type, for example Oracle.

2. Edit the required properties. for example, you can add the following connection for Oracle:

   ~~~
   oracle.jdbc.ReadTimeout=10000
   ~~~

   ![image](images/05_10_2.PNG)

3. **Save** the database type. 

   * Note that when creating a database type from template based on an existing one (for example, Oracle) make sure to save it with the same name (Oracle) in order to override the product default configuration, otherwise it will be treated as a new database type.



[![Previous](/articles/images/Previous.png)](09_fabric_API_for_DB_interfaces.md)
