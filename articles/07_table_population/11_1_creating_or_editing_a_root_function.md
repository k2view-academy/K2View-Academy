# Creating or Editing a Root Function

### What is a Root Function?

A Root Function is a specific type of Fabric function that is used as a Source Object to trigger the [Table Population](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md) mechanism.
There are two types of **Source Objects** for a Table Population object:
* DB Query, (default) an SQL SELECT query with one or several tables that is executed on a predefined [DB interface](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/03_DB_interfaces_overview.md). 
* Root Function, which can run various SQL SELECT queries and execute complex logic including data manipulations and calculations using Java code. Data returned by a Root Function is populated into the target LU Table of the Table Population object. 

A Root Function is used when a table population requires complex logic. For example, when a population requires data from multiple DB interfaces or non-DB interfaces. 

[Click for more information about Using Root Functions in a Population.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/03_creating_a_new_table_population.md#how-do-i-create-a-new-table-population-from-a-root-function)

### How can I Create or Edit a Root Function?
There are several ways to create a Root Function: 
* In the LU Schema window, by using the **New Table from SQL Based Root Function** option. Using this option, a new [LU table](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/06_LU_tables/02_create_an_LU_table.md), new population and new Root Function are created by the Fabric Studio. The Root Function’s code is generated based on the selected SQL. It is recommended to edit the generated function and add a **WHERE** clause to the generated SQL to retrieve only the relevant data of the [LU Instance](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#lui).
* Dragging the LU Table from the DB Objects tab in the LU Schema window and selecting **Create Table Based Root** Function. A new LU Table, new population and new Root Function and its generated code are created by the Fabric Studio. 
* Creating a new Table Population map, and then selecting **Create a New Root Function** from the **Objects tab** in the Table Population window. Using this option, the **New Function** window opens where you can write the required business logic.
* Creating a new function from the Project Tree.

The steps for creating a Root Function in Fabric Studio are the same as those for a regular function. Set the **Function Type** to **Root Function**. It is recommended to include all Root Functions under one category file named Root.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/4_1_1%20file%20named%20root.png)

[Click for more information about How to Create a Project Function.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/08_project_functions.md)

### Input and Output Parameters of a Root Function

A Root Function must have at least one Input parameter and at least one Output parameter. The Input parameter connects the Root Function of an LU with its parent object in the [LU Schema](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md). 
The Root Table in an LU Schema is always populated by the [Instance ID](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#lui). The Output of a Root Function serves as Input for the Table Population. 
A Root Function must have a **yield()** command to return the array of Objects (with the type Object []). All records yielded from the function are inserted into the target table.
The Input and Output parameters can be added to the function automatically using the Objects / Database pane in the Function Manager window.

**Example of the Input and Output Parameters Settings**

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/4_1_1_1_first_image.png)
![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/4_1_2%20%20Parameters%20settings.png)

[Click for more information about How to Define a Function’s Parameters Automatically.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/10_creating_a_project_function.md#how-do-i-automatically-define-functions-parameters) 

### Function Body

The Root Function’s main SELECT statement and loop over the SELECT results can be [automatically generated](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/10_creating_a_project_function.md#how-do-i-automatically-define-functions-parameters)  to act as the basis for a function

**Example of the Generated Code of a Root Function** 

<pre><code>
String sql = "SELECT SSN, FIRST_NAME, LAST_NAME FROM CRM_DB.CUSTOMER";
db("CRM_DB").fetch(sql, <val1>, <val2>, ...).each(row->{
	yield(row.cells());
});
</code></pre>


The Root Function should be edited as follows:
* Add the **WHERE** clause to the above SELECT statement using binding parameters, otherwise the query will select all records from the table rather than selecting the relevant data for the LU Instance. 
* [* <val1>, <val2>,…] are place holders for the query’s Input parameters. Either remove them from the **fetch()** command if they are not needed, or replace them with the SQL query’s Input parameters. 
* Add the required business logic to the Root Function, including additional SELECT statements and the execution of Fabric commands. 

**Example of the Edited Code where Input is a Value of a Root Function’s Input Parameter** 

<pre><code>
String sql = 
"SELECT SSN, FIRST_NAME, LAST_NAME FROM CUSTOMER WHERE CUSTOMER_ID = ?";
db("CRM_DB").fetch(sql, i_customer_id).each(row->{
	//do something
	yield(row.cells());
}
</code></pre>
 
[Click to go to the Root Function’s Code Example.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/11_02_root_functions_code_examples.md)


### How do I Delete a Root Function?
 
1.	Go to **Project Tree > Logical Units > [LU Name]**.
2.	Click **Tables > [Table Name] > [Population Name] > Delete Selected Items**.
3.	Click **Yes** in the confirmation pop-up message.

Note that if a deleted Root Function is used by a population, update the population to include a different source object.



