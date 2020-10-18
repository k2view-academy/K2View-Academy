# Database Types

### Overview

Fabric supports creating a new database type according to a JDBC driver specifications or overriding the existing database types without the need for product enhancement. 

### How Do I Create a Database Type?

To introduce a new database type, save the JDBC driver under:

**[Your PC Folder]\K2View Fabric Studio\Projects\\[Project Name]\lib\\[new database type]**.

Then do the following:

1. Go to **Project Tree** > **Shared Objects**, right click **Database Types** and select **New Database Type**.

   ![image](images/05_10_1.PNG)

2. Populate the **Driver's Settings**: Name, Class Name, URL Template and Default Port (optional).

3. Optional: Edit the **Pool Properties** and **Fabric Properties** fields. Note that:
   * Pool and Fabric Properties should be modified only by advanced users. During initial setup, use the default values.
   * To connect a REAL datatype field (Oracle) to a TEXT datatype field (PostgreSQL) in the LU table, set the Fabric Properties **Support Implicit Conversions** parameter to **False** (the default value is True).

4. If the JDBC driver does not enable access to metadata, populate the **Studio – Metadata** settings as follows:
   * **SQL Query for Tables List** and **SQL Query for Column List** settings with a SQL query.
   * **SQL Query for FKs List** and **SQL Query for PKs List** with custom SQL queries to retrieve the list of foreign keys (FK) and primary keys (PK) of the tables. These keys can be used by the Auto-discovery Wizard when creating a new [Logical Unit](/articles/03_logical_units/01_LU_overview.md). 

5. Set **Query Builder: identifiers Case Sensitivity** as either:
   * Insensitive.
   * SensitiveUpperCase.
   * SensitiveLowerCase.

6. Set **JDBC Connection Properties** as follows:
   * Set a **Name** and a **Value** for parameters to be used as a part of JDBC connection properties.
   * When setting the **Enable Interface Override**, you can update parameter values in the interface so that they are displayed under the Connection Properties section. 
   * When setting the **Connection Property**, the connection properties map is sent to the JDBC driver.

7. **Save** the database type.

### How Do I Create a Database Type from a Template?

You can create a new database type from a template when it is required to customize a built-in database type. Do the following:

1. Go to **Project Tree** > **Shared Objects**, right click **Database Types** and select **New Database Type From Template** and then select the DB type, for example Oracle.

2. Edit the required properties. For example, add the following connection property for Oracle:

   ~~~
   oracle.jdbc.ReadTimeout=10000
   ~~~

   ![image](images/05_10_2.PNG)

3. **Save** the database type. 

Note that when creating a database type from a template based on an existing database type (for example, Oracle) make sure to save it with the same name (Oracle) in order to override default configurations, otherwise it will be handled as a new database type.



[![Previous](/articles/images/Previous.png)](09_fabric_API_for_DB_interfaces.md)
