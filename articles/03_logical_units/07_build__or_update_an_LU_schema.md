# Using Auto Discovery to Build or Update an LU Schema

The Fabric Studio [**Auto Discovery Wizard**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/06_auto_discovery_wizard.md) enables you to automatically generate or edit an LU DB Schema based on predefined database constraints. 

### How Do I Define a New Logical Unit (LU) Using the Auto Discovery Wizard? 

1. Go to the **Project Tree**, right click **Logical Units** and then click **New Logical Unit** to display the **Logical Unit** dialog box. 
2. Complete the **Name** field.
3. By default the **Open LU Auto Discovery** option is checked whereby the [**Auto Discovery Wizard**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/06_auto_discovery_wizard.md) automatically opens. To manually create an **LU**, uncheck **Open LU Auto Discovery**.  
4. Click **OK**. 

### How Do I Add the Auto Discovery Functionality to an Existing Schema?
Auto Discovery allows you to append or override existing LU and can be useful when there is a need to add additional tables to an existing Schema from a different interface. 

To use the [**Auto Discovery Wizard**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/06_auto_discovery_wizard.md) to add tables to an LU schema, do either:

**Option 1**
1. Go to the **Project Tree**, right click the **LU Name** and the click **Auto Discovery Wizard**.
2. Run the **Auto Discovery Wizard**.
3. When the **Studio** asks you if you want to **override the LU schema**, do the following: \
  a. Click **YES** to override the existing schema and create a new one based on Auto Discovery.\
  b. Click **NO** to add the tables to the existing schema.

**Option 2**
1. Open the **LU Schema** and right click a **column** in an LU  **table**.
2. Select **Auto Discovery Wizard**.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/images/03_07_01_option2.png)

3. Run the [**Auto Discovery Wizard**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/06_auto_discovery_wizard.md). A new [**Grouped SubGraph**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md) of tables is added to the selected column.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/images/03_07_02_new_grouped_subgraph.png)
