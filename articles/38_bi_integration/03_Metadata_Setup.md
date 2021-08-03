# Metadata Setup

### Data Source Definition

Exago supports definition of data source of various types, such as relational databases (e.g. Oracle, MySQL, PostgreSQL), non-relational databased (e.g. MongoDB), DWH (e.g. Snowflake) or other types (e.g. XML files). Data sources are defined via the Exago Admin console. 

[Click for more information about Data Sources definition in Exago](https://support.exagoinc.com/hc/en-us/articles/214571638-Data-Sources). 

Note that one Exago installation can accommodate several Fabric projects because Exago can connect to multiple data sources.  

### How Do I Define Fabric as a Data Source?

Fabric can be defined as a data source in Exago. Fabric data source type is supported in Exago by the invocation of the FabricAdoDriver as the SQLite Data Provider. 

To setup Fabric as a data source, define the following:

* Name - provide a unique name.

* Type - Fabric.

* Connection String - define as follows:

  * urls=<host>:<port>;user=<user>;password=<password>;AUTO_MDB_SCOPE=true

  * Exago that is running on a Windows docker can be connected to a local Fabric. In this case, set your local IP as a host in the connection string.

![image](images/bi_setup_1.PNG)

**Note**: 

* Setting AUTO_MDB_SCOPE=true in Fabric connection string enables the implicit invocation of the Fabric GET command and allows querying the Logical Unit's data. [Click to get more information about AUTO_MDB_SCOPE.](https://support.k2view.com/Academy_6.5/articles/02_fabric_architecture/04_fabric_commands.html)

### Objects and Joins Definition

Once the data source is created in Exago, you need to define its objects and joins. Objects and joins can be either created manually one by one or using the Discover Object/Join Metadata function, both are done via the Exago Admin console. 

To start the objects creation, right click on the data source in the left-side tree and either click **Discover Object/Join Metadata** to initiate the automatic discovery or click **Add** to create a single object:

![image](images/bi_setup_2.PNG)

[Click for more information about Automatic Database Discovery in Exago](https://support.exagoinc.com/hc/en-us/articles/216000567-Automatic-Database-Discovery).

**Note**:

* If a table doesn’t have a primary key in the data source, the object in Exago will be defined by the Auto Discovery Process as Incomplete. Then the user will have to setup the primary key manually. The LU's Root Table must have a primary key defined in Fabric schema. 
* It is not required to define all data source's objects and joins in Exago, but only those which are required for reports creation. However the LU's Root Table and its respective joins must always be included in the data source metadata definition, even if it is not required for the report creation. *More details about the report creation based on Fabric LU tables are provided here - TBD.*
* In order to include data across several data sources in the same report (for example, the data from Fabric and from PostgreSQL DB), you must manually create a join between the respective objects of these data sources. The same should be done when you need to include the LU and common tables data of the same Fabric in the report (since Fabric doesn't have a foreign key relation between the LU and common tables). 

