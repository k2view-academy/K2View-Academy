<studio>

# Build or Update LU Schema Using Auto Discovery

The Fabric Studio [**Auto Discovery Wizard**](/articles/03_logical_units/06_auto_discovery_wizard.md) enables you to automatically generate or edit an LUDB schema based on predefined database constraints. 

### How Do I Define a New Logical Unit (LU) Using the Auto Discovery Wizard? 

1. Go to  **Project Tree** > right click **Logical Units** > **New Logical Unit** > **Logical Unit** dialog box. 
2. Complete the **Name** field.
3. By default the **Open LU Auto Discovery** option is checked whereby the [**Auto Discovery Wizard**](/articles/03_logical_units/06_auto_discovery_wizard.md) automatically opens. To manually create an **LU**, uncheck **Open LU Auto Discovery**.  
4. Click **OK**. 

### How Do I Add the Auto Discovery Functionality to an Existing Schema?
Auto Discovery allows you to append or override existing LU and can be useful when there is a need to add additional tables to an existing Schema from a different interface. 

To use the [**Auto Discovery Wizard**](/articles/03_logical_units/06_auto_discovery_wizard.md) to add tables to an LU schema, do either:

**Option 1**
1. Go to **Project Tree** > right click **LU Name** > **Auto Discovery Wizard**.
2. Run the **Auto Discovery Wizard**.
3. When the **Studio** asks you if you want to **override the LU schema**, do the following: \
  a. Click **YES** to override the existing schema and create a new one based on Auto Discovery.\
  b. Click **NO** to add the tables to the existing schema.

**Option 2**
1. Open  **LU Schema** > right click a **column** in an **LU table**.
2. Select **Auto Discovery Wizard**.

![image](/articles/03_logical_units/images/03_07_01_option2.png)

3. Run the [**Auto Discovery Wizard**](/articles/03_logical_units/06_auto_discovery_wizard.md). A new [**Grouped SubGraph**](/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md) of tables is added to the selected column.

![image](/articles/03_logical_units/images/1.7_pic_2.png) 



[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/06_auto_discovery_wizard.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/08_define_root_table_and_instance_ID_LU_schema.md)

</studio>
