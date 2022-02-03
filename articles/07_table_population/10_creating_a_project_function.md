# Creating a Project Function

### How Do I Create a Project Function?

Fabric Project functions are user-defined Java functions that are added to the project implementation to perform complex data manipulations or to execute queries on a specific [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id). 

Functions can be edited in the Fabric Studio by opening the function’s code from the Project Tree.

Functions can be created in [Logical Units](/articles/03_logical_units/01_LU_overview.md), References, Web Services, [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md) or from existing [Table Populations](/articles/07_table_population/01_table_population_overview.md) (using an **LuFunction** Actor).

<web>

To create a new function, click on **Logical Units** > [**LU Name**] > **Java** > right click on **src** > **New Java File**. 

<img src="images/web/10_create_func_java.PNG" style="zoom:80%;" />

The Web Studio provides a snippet syntax helper: type **Ctrl+Space** and then type **fabric** for Fabric snippets. For functions you can type FF. This will show you several function types, such as WS. Once you have selected a function, the syntax helper will offer you the syntax along with some options.

<img src="images/web/10_create_func.PNG" style="zoom:80%;" />

</web>

<studio>

1.	To create a function from an existing **Table Population**, go to **Project Tree** > **Logical Units** > [**LU Name**] > **Tables** > [**Table Name**] and double click  [**Population Name**] to open the existing population. Then do either:
    * Right click in the working area and select **Insert New Function**. 
    * Click the **Objects tab** in the right panel of the working area, select **Functions** and double click **Create New Function**.
 2. To create a function from the **Project Tree** do either:
    *	Click **Logical Units** > [**LU Name**], right click **Java** > **New Function**. 
    *	Click **Shared Objects** > [**LU Name**], right click **Java** > **New Function**.
    

![image](images/07_10_01_screen1.png)

3.	Do the following to complete the function's creation: 
    *	Populate the **Category** or select the existing value from the dropdown list. The [**Category**](/articles/04_fabric_studio/09_logic_files_and_categories.md) defines the function’s Java Logic file.
    *	Populate the **Function Type** by selecting the value from the dropdown list.
    *	(Optional) Populate the **Description**.
    *	Define the function’s **Input** and **Output** settings either [manually](/articles/07_table_population/10_creating_a_project_function.md#how-do-i-manually-define-functions-parameters)  or [automatically](/articles/07_table_population/10_creating_a_project_function.md#how-do-i-automatically-define-functions-parameters).
    *	Type the function’s **Java code** to generate the [code](/articles/07_table_population/10_creating_a_project_function.md#how-do-i-automatically-generate-code-for-a-function).
    *	Click **Save** to display the **New Item** dialog box. 
    *	Complete the **Name** field and then click **OK**.
    

### How Do I Define a Function's Parameters Manually? 

The Function Manager window displays the following panes on the right of the window:
*	Input Parameters.
*	Output Parameters.

Both panes include the following components:
*	**List panel**, displays a list of the parameter’s Name, Data Type and Comments.
*	**Text entry fields**, displays the parameter’s Name, Data Type and Comments.

To define Input and Output settings manually, do the following:
1.	Complete the **Name** field.
2.	In the **Data Type** field either, select the **Data Type** from the dropdown list or type it in manually if the type is not displayed in the list. 
3.	Optional: type comments in the **Comment** field.

### How Do I Bulk Load Parameters to a Function Manually?
The Bulk Load Parameters option can be used to upload multiple parameters to a function.

1.	Right click the **Input / Output Parameters** pane to display the **Input / Output Parameters** dialog box. 

![image](images/07_10_02_bulk_load.png)

2.	Click **Bulk Load Parameters** to display the **Bulk Load Parameters** dialog box. 
3.	Enter the **parameters** and click **OK** to load them to the Parameters pane. 

![image](images/07_10_03_bulk_load2.png)

4.	(Optional) Modify the **Data Type**.

![image](images/07_10_04_data_type.png)

### How Are a Function's Parameters Defined Automatically?

Input / Output settings can be automatically defined based on DB objects either from a source DB or LU objects using the Objects / Database pane in the Function Manager window.
1.	Go to the **Objects / Database** pane (left side) and click the **Database** tab.
2.	Click **DB Connection** and select the **Interface** from the dropdown menu (top of the pane).
3.	Click a **Table** in the database hierarchy.
4.	Right click the selected **Column Names** or the **Table** and select either **Add Selected to Input Parameters** or Add **Selected to Output Parameters**. New parameters are added to the parameters list in the Parameters pane. The new parameter’s name is the name of the column and data type is the column data type.

![image](images/07_10_05_datatype2.png)

### How Do I Update a Function's Parameters?

1.	Go to the **Function Manager** window.
2.	Click the **Parameter** in the **Parameters List** to edit the parameter’s **Name**, **Type** and **Comments** fields. The updated Input / Output parameter names are automatically applied to all occurrences of the parameter in the function’s Java code.


### How Do I Delete a Function's Parameters? 

1.	Select a **parameter** in the **Parameter List**.
2.	Press **Delete** on your keyboard or click the **Delete** icon next to the parameter to remove it from the parameters list.

### How is Code Generated for a Function Automatically?

1.	Go to the **Objects / Database** pane (left of the Function Manager) and click the **Database** tab.
2.	Click **DB Connection** and select an **Interface** from the dropdown menu (top of the pane).
3.	Click a **Table** in the database hierarchy.
4.	Right click the **Column Name(s)** to use as the basis for the **generated code** and then select **Generate Code**.

Example of the code generated for the function with **Input = CUSTOMER_ID** from the CUSTOMER table:
~~~java
String sql = "SELECT CUSTOMER_ID FROM CUSTOMER";
db("CRM_DB").fetch(sql, <val1>, <val2>, ...).each(row->{
});
~~~

**Notes** 
*	The code generated in the above example acts as the basis for a function and should be further updated according to the function’s requirements.
*	When writing Java code, other Java methods can be used in addition to Fabric methods. 
*	It is recommended to use binding parameters in SQL statements for prepared statements.


Click to display the Fabric API list: http://[Fabric IP address]:3213/static/doc/user-api/index.html

### How Do I Invoke a Project Function From Another Category?

To invoke a project function from another category in the same LU, do the following to add an import:
1. Open the function using IntelliJ.
2. Position the mouse on the invoked function (marked in red), press **ALT+ENTER** and select the option to add the required import.
3. Save the function.

To see the import, open the source file of the function's category. For example, when adding an invocation to the fnCheckSourceEnv() function which belongs to the DECISION category in the CRM Logical Unit, the following import is added:

~~~java
import static com.k2view.cdbms.usercode.lu.CRM.DECISION.Logic.fnCheckSourceEnv;
~~~

To invoke several functions in the same category, modify the import as follows:

~~~java
import static com.k2view.cdbms.usercode.lu.CRM.DECISION.Logic.*;
~~~



[![Previous](/articles/images/Previous.png)](/articles/07_table_population/08_project_functions.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md)

</studio>
