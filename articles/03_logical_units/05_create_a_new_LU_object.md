# Create a New Logical Unit (LU) Object

A new [Logical Unit (LU)](/articles/03_logical_units/01_LU_overview.md) can be added by either:

* Creating a new Logical Unit.
* Duplicating a Logical Unit. 

### How Do I Create a New Logical Unit (LU)?

<studio>

1. Go to **Project Tree** > right click **Logical Units** > **New Logical Unit** > **Logical Unit** dialog box. 
2. Enter the **Name** of the new **Logical Unit**. 
3. Do either: 
    * Check **Open LU Auto Discovery** to create the LU using the [**Auto Discovery Wizard.**](/articles/03_logical_units/06_auto_discovery_wizard.md) 
    * Do not check **Open LU Auto Discovery** and manually build the **Logical Unit**. 
4. Click **OK**.

</studio>

<web>

1. Go to **Project Tree** > right click **Logical Units / Data Products** > **New Data Product**. 

2. Populate the **Name** of the new **Logical Unit** and click **Enter**.

3. Proceed to manual creation of the LU schema:

   * Open the **Schema** of the newly created Logical Unit and switch from the Project tree to the **DB Interface Explorer** by clicking the <img src="../04_fabric_studio/images/web/datasource_explorer.png" style="zoom:67%;" /> icon on the left panel.

   * Click on the relevant interface, select the required tables and add them to the schema using a right click.

     ![](images/web/5_create_lu_schema.PNG)

   * Click on the table that should be an LU Root Table and set it as root using the table context menu. Only a table with no input connections can be set as root.

     ![](images/web/5_create_lu_schema_set_root.PNG)

   * After the table is set as root, the table properties tab is opened to set the required Instance ID column. In addition, the color of the root table's header is changed to dark blue.

   * Save your changes once all the required tables are added to the LU Schema.

</web>

[Click here for more information about the LU Creation Steps.](/articles/03_logical_units/02_create_a_logical_unit_flow.md)

### How Do I Duplicate a Logical Unit (LU)?

1. Go to **Project Tree** > right click the origin logical unit > **Duplicate**. 
2. Enter the **Name** of the new (duplicated) **Logical Unit**. 
3. Click **OK**.  

<studio>

[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/04_LU_properties.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/06_auto_discovery_wizard.md)

</studio>

<web>

[![Previous](/articles/images/Previous.png)](03_LU_schema_window.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09_add_table_to_a_schema.md)

</web>

