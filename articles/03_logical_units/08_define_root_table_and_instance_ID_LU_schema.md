# Editing an LU Schema - Defining the Root Table and Instances ID Column

### How do I Define the Root Table and Instances ID Column in an LU Schema?

Each LU Schema must have a Root Table and Instance ID  (Instance PK Column). The instance ID is the unique identifier of each LU instance.
When creating a Logical Unit using the [Auto Discovery Wizard](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/06_auto_discovery_wizard.md), the Root Table and the [Instance ID](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#instance-id) column are defined according to the selected Root Table and column in the Wizard. 

If you are not using the Auto Discovery Wizard, do the following:

1. Go to the **Project Tree**, select the **Logical Unit** and double click the **Schema** to open the **Logical Unit** screen.
2. Create the [**LU table**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/02_LU_tables/01_LU_tables_overview.md) and add it to the [**LU schema**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/09_add_table_to_a_schema.md).
3. Go to the **LU Table**, right click the **table header** and then click **Set as: Root:**

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/images/03_08_01_tables.png)

4. Double click the **LU table** to open it.
5. In the **Table Properties**, click the **Instance PK Column** and then select the **table column** defining the **Main Identifier** of the table.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/images/03_08_02_tables.png)

Note that only one column can be defined as **True** in the [**Input Arguments**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/12_LU_hierarchy_and_linking_table_population.md#how-do-i-link-a-table-population-to-the-lu-schema)  of the Root Table. This field is populated by the **Instance ID**. 

