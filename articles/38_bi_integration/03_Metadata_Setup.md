# Metadata Setup

### Overview

In order to start creating the reports, define the data sources (such as Fabric or a PostgreSQL DB), objects and joins in the **BI Admin**. This article explains how to do: 

* Creation of [Data sources](03_Metadata_Setup.md#data-sources).
* Creation of [Objects and Joins](03_Metadata_Setup.md#objects-and-joins). 

Follow each one of them to fully setup the metadata, as a preparation for report creation.

### Data Sources

Exago supports various types of data sources, such as relational databases (e.g. Oracle, MySQL, PostgreSQL), non-relational databased (e.g. MongoDB), DWH (e.g. Snowflake) or other types (e.g. XML files). Data sources are defined via the **BI Admin**. 

[Click for more information about Data Sources definition in Exago](https://support.exagoinc.com/hc/en-us/articles/214571638-Data-Sources). 

**How Do I Define Fabric as a Data Source?**

Fabric can be defined as a data source to allow creation of the reports based on Common or LUI data.

To setup Fabric as a data source, either use the Fabric-Connection template in the **BI Admin**:

1. Double click on **Sources** in the Report Tree and set the following:

   ![image](images/bi_setup_fabric.PNG)

   * Name - a unique name.
   * Type - select **Fabric**.
   * Connection String - define as follows:

  ~~~
  urls=<host>:<port>;user=<user>;password=<password>;AUTO_MDB_SCOPE=true
  ~~~

3. Click **Apply** to save the changes.

If Exago is running on a docker, it can be connected to a local Fabric. In this case, set the **IPv4 Address** of your internet connection (by checking your machine's IP address) as a host in the Connection String.

**Note**: 

* One Exago installation can accommodate several Fabric deployments by defining each deployment as a separate data source.  
* Setting **AUTO_MDB_SCOPE=true** in Fabric connection string is mandatory since it enables the implicit invocation of the Fabric GET command and allows querying the Logical Unit's data. [Click to get more information about AUTO_MDB_SCOPE setting.](/articles/02_fabric_architecture/04_fabric_commands.html)

**How Do I Define PostgreSQL as a Data Source?**

To setup PostgreSQL DB as a data source, either use the Postgres-Connection template in the **BI Admin** and update the connection string:

![image](images/bi_setup_postgres.PNG)

### Objects and Joins

Once the data source is created, you need to define its objects and joins. Objects and joins can be created either manually one by one or via the Discover Object/Join Metadata function. Both are done using the BI Admin. 

**How Do I Initiate Automatic Metadata Discovery?**

1. In the **BI Admin** right click the <Data Source Name> under **Sources** and then click **Discover Object/Join Metadata** to initiate the automatic discovery:

   ![image](images/bi_setup_2.PNG)
2. When **Discover Object/Join Metadata** screen is open, the list of all Fabric tables is displayed. It includes the Common area tables and all the tables of all Logical Units. 
3. Select the tables required for report creation, then click **Add Objects and Joins** to create objects and joins in Exago.

   ![image](images/bi_setup_3.PNG)

4. When the objects and joins creation is done, the list of created / skipped objects and joins is displayed on the right side of the screen.

   ![image](images/bi_setup_4.PNG)

   * **Complete** objects are the objects which were created successfully in Exago.
   * **Skipped** objects are the objects which were skipped for any reason, for example if they already exist in Exago.
   * **Incomplete** objects are the objects which don't have a primary key in the data source. Since a primary key is mandatory in Exago, you must open these objects and manually define their **Unique Key Fields**.

5. All new objects are created without their metadata. They are marked with![image](images/bi_setup_sign.PNG)icon. You must complete the object's metadata by doing one of the following:

   * Right click on the data source name > **Bulk Metadata** to create each object's metadata within Exago. 

   ![image](images/bi_setup_5.PNG)

   * Or, open each object and click![image](images/bi_setup_metadata.PNG)icon in the **Column Metadata** field > **Read Schema** > **Okay**.

6. Verify each new joins default settings:

   * Check the **Join Columns** and update if needed.
   * By default, **Join Type** = Inner and **Relation Type** = One To One. Update these settings to reflect the actual join and relation types.

   ![image](images/bi_setup_6.PNG)

**Important Notes for Successful Metadata Creation**

* If a table doesn’t have a primary key defined in Fabric, the object is created as **Incomplete** by the Auto Discovery process. In this case, you must setup the primary key manually. The LU's Root Table must have a primary key defined in Fabric schema. 
* The joins between Incomplete tables are not created by the Auto Discovery process and must be created manually.
* It is not required to define all data source's objects and joins in Exago, but only those which are required for reports creation. However the LU's **Root table** and its respective joins must always be included in the data source metadata definition, even if it is not required for the report creation. [Click to get more details about the report creation based on Fabric LU tables](04_report_creation_guidelines.md).
* In order to include data across several data sources in the same report (for example, the data from Fabric and from PostgreSQL DB), you must manually create a join between the respective objects of these data sources. The same should be done when you need to include the LU and common tables data of the same Fabric in the report (since Fabric doesn't have a foreign key relation between the LU and common tables). 


[Click for more information about Automatic Database Discovery in Exago](https://support.exagoinc.com/hc/en-us/articles/216000567-Automatic-Database-Discovery).





[![Previous](/articles/images/Previous.png)](02_Permissions_Setup.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_parameters.md)

