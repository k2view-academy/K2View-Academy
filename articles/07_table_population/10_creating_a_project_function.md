# Creating a Project Function

### How Can I Create a Project Function?

Fabric Project functions are user-defined Java functions that are added to the project implementation to perform complex data manipulations or to execute queries on a specific [Instance ID](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#instance-id). 
In Fabric Studio functions can be edited either by opening the function’s code from the Project Tree, opening the specific category’s **Logic.java** source file or opening the function in [**IntelliJ** - an Integrated Development Environment (IDE)] for developing computer software. 
Functions can be created in [Logical Units](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/01_LU_overview.md), References, Web Services, [Shared Objects](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/12_shared_objects.md) or from existing [Table Populations](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md).
1.	Go to the **Project Tree** and do either:
    *	Click either **Logical Units** or **Shared Objects**,[ **LU Name**], right click **Java** and then select **New Function**. 
    *	Click **Logical Units**, [**LU Name**], **Tables**, [**Table Name** ]and double click [**Population Name**] to open the existing population. Do either:
Right click in the workspace and select **Insert New Function**. 

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Or,</p>

   * In the **Table Population workspace**, click the **Objects** tab, click **Functions** and double click **Create New Function** to open the **New Functions window**. 


![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_10_01_screen1.png)

2.	Do the following:
    *	Populate the **Category** or select the existing value from the dropdown list. The [**Category**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/09_logic_files_and_categories.md) defines the function’s Java Logic file.
    *	Populate the **Function Type** by selecting the value from the dropdown list.
    *	(Optional) Populate the **Description**.
    *	Define the function’s **Input** and **Output** settings either m[anually]  or [automatically].
    *	Type the function’s **Java code** to generate the [code].
    *	Click Save to display the **New Item** dialog box. 
    *	Complete the **Name** field and then click **OK**.
    
### How Do I Manually Define Function's Parameters? 

The Function Manager window displays the following panes on the right of the window:
*	Input Parameters.
*	Output Parameters.

Both panes include the following components:
*	**List panel**, displaying a list of the parameter’s Name, Data Type and Comments.
*	**Text entry fields**, displaying the parameter’s Name, Data Type and Comments.

To define Input and Output settings manually, do the following:
1.	Complete the **Name** field.
2.	In the **Data Type** field either, select the **Data Type** from the dropdown list or type it in manually if the type is not displayed in the list. 
3.	Optional: type comments in the **Comment** field.

**Bulk-loading Parameters to a Function Manually** 
When multiple parameters need to be uploaded, they can be loaded using the Bulk Load Parameters option. 
1.	Right click the **Input / Output Parameters** pane to display the **Input / Output Parameters** dialog box. 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_10_02_bulk_load.png)

2.	Click **Bulk Load Parameters** to display the **Bulk Load Parameters** dialog box. 
3.	Enter the **parameters** and click **OK** to load them to the Parameters pane. 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_10_03_bulk_load2.png)

4.	(Optional) Modify the **Data Type**.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_10_04_data_type.png)

### How Do I Automatically Define Function's Parameters?

Input / Output settings can be automatically defined based on DB objects either from a source DB or LU objects using the Objects / Database pane of the Function Manager window.
1.	Go to the **Objects / Database** pane (left side) and click the **Database** tab.
2.	Click **DB Connection** and select the **Interface** from the dropdown menu (top of the pane).
3.	Click a **Table** in the database hierarchy.
4.	Right click the selected **Column Names** or the **Table** and select either **Add Selected to Input Parameters** or Add **Selected to Output Parameters**. New parameters are added to parameters list in the Parameters pane. The new parameter’s name is the name of the column and data type is the column data type.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_10_05_datatype2.png)

### How Do I Update Function's Parameters?

1.	Go to the **Function Manager** window.
2.	Click the **Parameter** in the **Parameters List** to edit the parameter’s **Name**, **Type** and **Comments** fields. The updated Input / Output parameter names are automatically applied to all occurrences of the parameter in the function’s Java code.


### How Do I Delete Function's Parameters? 

1.	Select a **parameter** in the **Parameter List**.
2.	Press **Delete** on your keyboard or click the **Delete** icon next to the parameter to remove it from the parameters list.

### How Do I Automatically Generate Code for a Function?

1.	Go to the **Objects / Database** pane (left of the Function Manager) and click the **Database** tab.
2.	Click **DB Connection** and select an **Interface** from the dropdown menu (top of the pane).
3.	Click a **Table** in the database hierarchy.
4.	Right click the **Column Name(s)** to use as a basis for the **generated code** and select Generate Code.

Example of generated code of the function with **Input = CUSTOMER_ID** from the CUSTOMER table:
<pre><code>
String sql = "SELECT CUSTOMER_ID FROM CRM_DB.CUSTOMER";
db("CRM_DB").fetch(sql, <val1>, <val2>, ...).each(row->{
});
</code></pre>

**Notes** 
*	The code generated in the above example acts as the basis for a function and should be further updated according to the function’s requirements.
*	When writing Java code, you can use any Java methods and are not limited to Fabric methods. 
*	It is recommended to use binding parameters in SQL statements for prepared statements.


Click to display the Fabric API list: **http://<Fabric IP address>:3213/static/doc/user-api/index.html**


[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/09_creating_an_LUDB_function.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/11_lookup_tables.md)
