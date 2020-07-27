# Editing and Adding a Table to an LU Schema

An [LU table](/articles/06_LU_tables/01_LU_tables_overview.md)  is a basic building block in a Logical Unit (LU).

To add a table to an [LU schema](/articles/03_logical_units/03_LU_schema_window.md), do either: 

1. Use the [Auto Discovery Wizard](/articles/03_logical_units/06_auto_discovery_wizard.md) to create or edit an LU whereby the tables and their populations are automatically created and added to the LU schema.
2. Drag a **DB Table** into the **LU Schema window**:\
   a. Go to the [DB Objects tab](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-tabs) of the [LU schema](/articles/03_logical_units/03_LU_schema_window.md). \
   b. Click **DB Connection** > [**DB interface**](/articles/05_DB_interfaces/03_DB_interfaces_overview.md).\
   c. Click **Tables** and then drag them into the **LU schema**. You can drag several tables.\
   d. Check either: 
    * **Create Table Based DB Query.**
    * **Create Table Based Root Function.**

The selected tables are automatically created with the selected type of [population](/articles/07_table_population/01_table_population_overview.md) and added to the LU schema.

![image](/articles/03_logical_units/images/03_09_01_tables1.png)


3. Drag a **Table** into the **LU Schema window**:\
    a. Go to the [Objects tab](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-tabs) of the [LU schema](/articles/03_logical_units/03_LU_schema_window.md).\
    b. Select a **Table** and drag it into the **LU Schema window**.


![image](/articles/03_logical_units/images/03_09_02_tables2.png)

4. Right click the **Schema window** and select one of the following options:\
    a. **New Table from SQL Based DB Query.**\
    b. **New Table from SQL Based Root Function**.\
Both options open the Query Builder. The LU table and its population are automatically generated based on the SQL query defined in the Query Builder.

![image](/articles/03_logical_units/images/03_09_03_tables3.png)


[Click for more information about LU Table Creation.](/articles/06_LU_tables/02_create_an_LU_table.md)

[Click for more information about LU Tables and Table Population. ](/articles/07_table_population/01_table_population_overview.md)

[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/08_define_root_table_and_instance_ID_LU_schema.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/10_delete_table_from_a_schema.md)

