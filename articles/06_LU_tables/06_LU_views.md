# LU Views

### Overview

Starting from Fabric V6.5.8, LU views are introduced to Fabric. 

**LU view** is the result set of a stored query on Fabric DB, which can be queried just as any persistent [LU table](01_LU_tables_overview.md). This pre-established query command is kept in the Fabric MicroDB dictionary and allows easy access to the required data collection.

The SQL statement of the LU view can include a select from one LU table or several joint LU tables. It also supports using [LUDB functions](/articles/07_table_population/11_3_creating_an_LUDB_function.md). 

LU views are part of the [Logical Unit](/articles/03_logical_units/01_LU_overview.md) definition, however they are not part of the LU Schema. They are populated with data at the end of the sync process, when the population of all LU tables is completed. 

LU view's column data type is the same as the respective LU table's column data type. LU views don't have their own properties and can't be indexed. 

The LU view's data can be accessed either via the direct querying of the instance's MicroDB or through the Declarative field level authorization mechanism where the query on LU table is replaced by a query on LU view based on the Fabric user's Security profile.

Click for more information about Declarative field level authorization mechanism.

### How Do I Create a New LU View?

1. Go to **Project Tree** > **Logical Units** > [**LU Name**], right click **Views** > **New View** to display the **View** window.
2. Create an SQL statement which represents the view. You can do it by either clicking **Open Query Builder** or writing the query manually.
3. Once the query is ready, click **Validate Query** to validate the syntax. 
4. Save the view. 

![](images/lu_views_1.PNG)



[![Previous](/articles/images/Previous.png)](05_business_tables.md)