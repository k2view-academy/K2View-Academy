# Metadata and Parameters Setup

### Data Source Definition

Exago supports definition of data sources of various types, such as relational databases (e.g. Oracle, MySQL, PostgreSQL), non-relational databased (e.g. MongoDB), DWH (e.g. Snowflake) or other types (e.g. XML files). Data sources are defined via the BI Admin. 

[Click for more information about Data Sources definition in Exago](https://support.exagoinc.com/hc/en-us/articles/214571638-Data-Sources). 

Note that one Exago installation can accommodate several Fabric projects because Exago can connect to multiple data sources.  

### How Do I Define Fabric as a Data Source?

Fabric can be defined as a data source in Exago. Fabric data source type is supported in Exago by the invocation of the **FabricAdoDriver** as the SQLite Data Provider. 

To setup Fabric as a data source:

1. Open the BI Admin.
2. Right click on **Sources > Add** and define the following:

* Name, a unique name.

* Type, select Fabric.

* Connection String, define as follows:

  ~~~
  urls=<host>:<port>;user=<user>;password=<password>;AUTO_MDB_SCOPE=true
  ~~~

Exago that is running on a Windows docker can be connected to a local Fabric. In this case, set your local IP as a host in the connection string.

![image](images/bi_setup_1.PNG)

**Note**: 

* Setting **AUTO_MDB_SCOPE=true** in Fabric connection string enables the implicit invocation of the Fabric GET command and allows querying the Logical Unit's data. [Click to get more information about AUTO_MDB_SCOPE.](https://support.k2view.com/Academy_6.5/articles/02_fabric_architecture/04_fabric_commands.html)

### Objects and Joins Definition

Once the data source is created, you need to define its objects and joins. Objects and joins can be created either manually one by one or via the Discover Object/Join Metadata function, both are done using the BI Admin. 

To start the objects creation:

1. Open the BI Admin.
2. Right click on the data source under Sources and either click **Discover Object/Join Metadata** to initiate the automatic discovery or click **Add** to create a single object:

![image](images/bi_setup_2.PNG)

3. When **Discover Object/Join Metadata** is initiated, the list of all tables will be displayed. Select the tables requires for report creation, then click **Add Objects and Joins** to create objects and joins in Exago.

![image](images/bi_setup_3.PNG)

4. When the objects and joins creation is done, the list of created / skipped objects and joins is displayed.

![image](images/bi_setup_4.PNG)

5. **Incomplete** objects are the objects which don't have a primary key in the data source. Since primary key is mandatory in Exago, open these objects and manually define their **Unique Key Fields**.

6. All new objects are created without their metadata. They are marked with![image](images/bi_setup_sign.PNG)icon. To complete the metadata, do either:

   * Right click on the data source name > **Bulk Metadata** to create each object's metadata within Exago. 

     ![image](images/bi_setup_5.PNG)

   * Alternatively you can open each object and click![image](images/bi_setup_metadata.PNG)icon in the **Column Metadata** field > **Read Schema** > **Okay**.

7. Verify each new join's Join Columns and update if needed. By default, the joins are created with the following definitions and can be updated if needed:

   * Join Type = Inner.
   * Relation Type = One To One.

   ![image](images/bi_setup_6.PNG)

**Note**:

* If a table doesn’t have a primary key defined in the data source, the object is created by the Auto Discovery process as **Incomplete**. Then the user has to setup the primary key manually. The LU's Root Table must have a primary key defined in Fabric schema. 
* The joins between Incomplete tables is not created by the Auto Discovery process and need to be created manually.
* If a selected table or join already exist in Exago, it is **Skipped** by the process.
* It is not required to define all data source's objects and joins in Exago, but only those which are required for reports creation. However the LU's Root Table and its respective joins must always be included in the data source metadata definition, even if it is not required for the report creation. *More details about the report creation based on Fabric LU tables are provided here - TBD.*
* In order to include data across several data sources in the same report (for example, the data from Fabric and from PostgreSQL DB), you must manually create a join between the respective objects of these data sources. The same should be done when you need to include the LU and common tables data of the same Fabric in the report (since Fabric doesn't have a foreign key relation between the LU and common tables). 


[Click for more information about Automatic Database Discovery in Exago](https://support.exagoinc.com/hc/en-us/articles/216000567-Automatic-Database-Discovery).