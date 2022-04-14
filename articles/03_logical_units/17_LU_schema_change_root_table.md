# Change LU Root Table

Each LU schema must have a [Root Table](/articles/01_fabric_overview/02_fabric_glossary.md#root-table) and [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id) column.  

### How Can I Change the Root Table? 

<studio>

1.  Go to the [Schema window](/articles/03_logical_units/03_LU_schema_window.md) and right click the table is to be the new Root Table
1. Click **Set as: Root**.
1. Define the **Instance ID Column** of the new Root Table.

You must link the previous Root Table to a parent table. This can be the new Root Table.

</studio>

<web>

1.  Go to the [Schema window](/articles/03_logical_units/03_LU_schema_window.md) and click the table that is to be the new Root table.

2. Set it as root using the table context menu. Only a table with no input connections can be set as root.

   ![](images/web/5_create_lu_schema_set_root1.png)

3. After the table is set as root, the table properties tab is opened to set the required Instance ID column. In addition, the color of the root table's header is changed to dark blue.

3. link the previous Root Table to a parent table.

4. Save your changes once all the required tables are added to the LU Schema.

</web>

[Click for more information about Defining a Root Table and Instance ID Column.](/articles/03_logical_units/08_define_root_table_and_instance_ID_LU_schema.md)



[![Previous](/articles/images/Previous.png)](16_LU_schema_group_and_ungroup_tables.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](18_LU_schema_refresh_LU_options.md)
