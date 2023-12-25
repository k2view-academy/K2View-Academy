# Create and Edit an Logical Unit Table

LU tables are the basic building blocks for creating [Logical Units](/articles/03_logical_units/01_LU_overview.md#logical-unit-lu-overview). There are several ways to create a new LU table.

There are 3 main methods for creating a new LU table:

1. Based on a Data Source table
2. Manually
3. By duplicating an existing table



## Create a Table Based on Data Source

From the [LU schema](/articles/03_logical_units/03_LU_schema_window.md):

<studio>

* Right-click on the working area and select either the **New Table From SQL Based DB Query**, the **New Table From SQL Based Root Function** or the **New Table From SQL Based Broadway Flow** option.

* From the Project Tree: 

  * Use the [**Auto Discovery Wizard**](/articles/03_logical_units/06_auto_discovery_wizard.md) to either create or edit the LU. The tables and their populations are automatically created and added to the LU schema. If a table already exists, it is possible to override the existing implementation.

  * Create a new table **manually**. In this option, the table population should be created separately. Once the population has been created, add the table to the LU schema. Note that if the table is used as the master data, there is no need to create the table population.

    

</studio>

<web>

* Create a table based on data source table, using the **DB Interface Explorer**:

  * Switch from the Project Tree to the **DB Interface Explorer** by clicking the <img src="../04_fabric_studio/images/web/datasource_explorer.png" style="zoom:67%;" /> icon on the left Activity Bar.

  * Click on the relevant interface data source and select the required table.

  * Right-click on the table and choose "Add Tables to Schema". The table will be created in Fabric according to the selected table definitions, along with its population, and will be added to the schema. 

    > Note that you can expand the table in the DB Interface Explorer and select specific columns. By doing so, the created Fabric table will contain only the columns that were selected.

* Create a table based on data source tables, using **Query Builder**:

  * At schema window top bar, click the <img src="/articles/03_logical_units/images/web/new-table_nobg.png" style="zoom: 70%;" /> icon to open the **Add New Table** popup window
  * Choose "Create New from source with SQL query".
  * Name the table and click on Create.
  * Query Builder popup window appears.
  * In the Query Builder window, select the required interface. Then either write the SQL query in the upper part in the Query Builder or expand the interface schema and its table list to find the relevant table and select it.
  * Once a query exists in the Query Builder, you can test it by clicking on **Execute** button. When done, click on **Create** button.

</web>

[Click for More Information about Adding a Table to a Schema](/articles/03_logical_units/09_add_table_to_a_schema.md).



## Create a Table Manually

1.	Go to **Project Tree** > **Logical Units** > [**LU Name**], right click **Tables** > **New Table** to display the **Columns tab** in the **Table Schema** window.
2.	Define the [**Table Schema**](/articles/06_LU_tables/02_create_an_LU_table.md#table-schema-definition):
    * Complete the settings of each table column like the **Name** or **Data Type**.
    * Optional: Define the table [**Indexes**](/articles/06_LU_tables/03_table_indexes.md).
    * Optional: Define the **CDC** and the table’s **Search** indexes.
3.	Define the [**Table Properties**](/articles/06_LU_tables/04_table_properties.md). 
4.	**Save** the table.



## Duplicate an existing table 

From the Project Tree:

<studio>

*	Copying an existing LU table from one LU to another. In this option if there is a Table Population it is also copied. 
*	Using the **Save As** option. Note that this option does not copy the Table Population.

</studio>

<web>

From the Project Tree:

1. Right-click on the table and at the opened context menu choose **Duplicate**.
2. At the opened dialog box give a name to the duplicated table and click OK.

New table is created along with duplicated table population flow.

</web>

## Edit an LU Table 

1.	Go to **Project Tree** > **Logical Units** > [**LU Name**] > **Tables** > [**Table Name**] to display the **Columns tab** in the **Table Schema** window.
2.	Optional: Edit the table’s **Columns**, **Indexes**, **Search Indexes** or **Table Properties**.
3.	**Save** the table.

Note that in specific cases the Data Type of a column that has been automatically created based on a DB table may need to be edited manually. For example, a **Number** column in Oracle is created in Fabric with **Data Type = Real**. This column might need to be modified to an **Integer** or **Text** in the LU table.



## Table Definition  

While creating or editing an LU table you can set the columns and their types, mark mandatory fields and set the default values. 

<table width="623">
<tbody>
<tr>
<td width="122">
<p><strong>Name</strong></p>
</td>
<td width="502">
<p>Column name.</p>
</td>
</tr>
<tr>
<td width="122">
<p><strong>Data Type</strong></p>
</td>
<td width="502">
<p>The Data Type of the values in the column. The following types are supported:</p>
<ul>
<li>Integer</li>
<li>Real</li>
<li>Text</li>
<li>Datetime, saved as java.sql.Timestamp class (supported till nano-seconds)</li>
<li>Date, saved as java.sql.Date class</li>
<li>Time, saved as java.sql.Time class</li>
<li>Blob</li>
</ul>
<p>Note that if the Oracle field type is <strong>Number</strong>, Fabric sets the <strong>Data Type</strong> of this field to <strong>Real</strong> in the <strong>LU table</strong> and the number in Fabric has a decimal point. Therefore, if a column in the <strong>LU table</strong> does not need a decimal point (for example, for the CUSTOMER ID), change the <strong>Data Type</strong> of the <strong>LU Table</strong> column to <strong>Integer</strong> or <strong>Text</strong>.</p>
</td>
</tr>
<tr>
<td width="122">
<p><strong>Mandatory</strong></p>
</td>
<td width="502">
<p>When checked, this column must be populated (not null), otherwise leave it unchecked.</p>
</td>
</tr>
<tr>
<td width="122">
<p><strong>Default Value</strong></p>
</td>
<td width="502">
<p>The value set in this column is used to populate the table when there is no input for this column.</p>
</td>
</tr>
<tr>
<td width="122">
<p><strong>Comment</strong></p>
</td>
<td width="502">
<p>Additional information.</p>
</td>
</tr>
</tbody>
</table>




[![Previous](/articles/images/Previous.png)](/articles/06_LU_tables/01_LU_tables_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/06_LU_tables/03_table_indexes.md)
