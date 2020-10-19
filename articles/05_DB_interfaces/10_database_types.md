# Database Types

### Overview

Fabric supports creating a new database type based on JDBC driver specifications or overriding the existing database types without the need for product enhancement. 

When introducing a new database type, the JDBC driver is saved under:

**[Fabric Project's Directory]\\[Project Name]\lib\\[new database type]**.

Each database type is kept in a separate folder in order to avoid overlapping or conflicts with other drivers (dynamically loaded).

Note that the JDBC drivers are not part of the [Fabric Deployment](/articles/01_fabric_overview/02_fabric_glossary.md#deployment), thus the drivers must be copied to the Fabric server to the following folder prior to running of the Linux server:

~~~
/home/k2view/ExternalJars/
~~~

### How Do I Create a Database Type?

To create a new database type, do the following:

1. Go to **Project Tree** > **Shared Objects**, right click **Database Types** and select **New Database Type**.

   ![image](images/05_10_1.PNG)

2. Populate the **Driver's Settings**: 

   * **Name**. Database type unique name.
   * **Class Name**, **URL Template** and **Default Port** (optional). Settings from the JDBC driver.

3. Optional: Edit the **Pool Properties** and **Fabric Properties** fields. 
   * Note that the Pool Properties and Fabric Properties should be modified only by advanced users. During initial setup, use the default values.

4. Optional: Populate the **Studio – Metadata** settings (to be used by the Fabric Studio only):
   * **SQL Query for Tables List** and **SQL Query for Column List** - with SQL queries to retrieve the list of table and the list of columns from the schema.
   * **SQL Query for FKs List** and **SQL Query for PKs List** - with SQL queries to retrieve the list of foreign keys (FK) and primary keys (PK) of the tables. These keys can be used by the [Auto Discovery Wizard](/articles/03_logical_units/06_auto_discovery_wizard.md) when creating a new [Logical Unit](/articles/03_logical_units/01_LU_overview.md). 
   * Note that if the JDBC driver does not enable access to metadata and the above settings were not populated, you can still work with this DB and run queries on it. However the Fabric Studio's Auto Discovery Wizard, DB Query and [Query Builder](/articles/11_query_builder/02_query_builder_window.md) schemas will not work on top of it. The implementer will need to write the Root functions and all SQL queries manually. 
   * Populating the above queries can be also done when you need to customize the list of tables or columns to be returned by the schema. For example, when the schema includes many tables and only a small subset of tables is required.
5. Set **Query Builder: identifiers Case Sensitivity** as either:
   * Insensitive (default).
   * SensitiveUpperCase.
   * SensitiveLowerCase.
6. Set **JDBC Connection Properties** as follows:
   * Set a **Name** and a **Value** for parameters to be used as a part of JDBC connection properties.
   * When setting the **Enable Interface Override**, the parameters are displayed under the Connection Properties section of the [Interface window](03_DB_interfaces_overview.md) and you can update the parameters values. 
   * When setting the **Connection Property**, the connection properties map is sent to the JDBC driver.
7. **Save** the database type.



#### Example of JDBC Connection Properties Definition

- Create a new Database type **MySQL2** which includes the **useSSL** JDBC Connection Property and add it to the URL Template as shown below:

~~~
&abc=[useSSL]
~~~

![image](images/05_10_3.PNG)



- Create a new Interface using the Database type **MySQL2**. Note that **useSSL** JDBC Connection Property is added to the [Interface window](03_DB_interfaces_overview.md) under Connection Properties section and the following is added to the Connection String:

~~~
abc=false
~~~

![image](images/05_10_4.PNG)

* If needed, you can update the value of **useSSL** property. If updated, is it reflected in the Connection String too.

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
