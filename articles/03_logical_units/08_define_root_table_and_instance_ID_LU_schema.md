# Set Root Table and Instance ID

Each LU schema must have a Root Table and Instance ID (Instance ID Column). The Instance ID is the unique identifier of each LU instance.

<studio>

When creating a Logical Unit using the [Auto Discovery Wizard](/articles/03_logical_units/06_auto_discovery_wizard.md), the Root Table and the [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id) column are defined according to the selected Root Table and column in the Wizard. 

If you are not using the Auto Discovery Wizard, do the following:

1. Go to **Project Tree** > **Logical Unit** > double click the **schema** > **Logical Unit** screen.
2. Create the [**LU table**](/articles/06_LU_tables/01_LU_tables_overview.md) and add it to the [**LU schema**](/articles/03_logical_units/09_add_table_to_a_schema.md).
3. Go to **LU table** > right click **table header** > **Set as: Root:**

![image](images/03_08_01_tables.png)

4. Double click  **LU table** to open it.
5. In the **Table Properties**, click **Instance ID Column** > select the **table column** defining the **Main Identifier** of the table.

![image](images/03_08_02_tables.png)

Note that only one column can be defined as **True** in the [Input Arguments](/articles/03_logical_units/12_LU_hierarchy_and_linking_table_population.md#what-are-the-table-populations-input-arguments) of the Root Table. This field is populated by the [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id). 

</studio>

<web>

To set the Root Table and Instance ID do the following:

1. Go to **Project Tree** > **Logical Units / Data Products** > click and open the schema of the relevant Data Product.
2. Click on the table that should be the Root Table, open the table's menu and choose "set as root". Only a table with no input connections can be set as root. for others this option is disabled. 

	![](images/web/5_create_lu_schema_set_root1.png)

3. The table properties tab is automatically opened to set the required Instance ID column, at the top Root Table section. Note that the color of the root table's header is changed to dark blue.

4. Save your changes.

</web>



[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/07_build__or_update_an_LU_schema.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/09_add_table_to_a_schema.md)

