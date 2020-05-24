# Creating an LUDB Function

### What Is an LUDB Function?

An LUDB function is a Project function invoked from an SQL query to perform more complex operations on LU data than those performed using standard SQL statements.
*	LUDB functions are invoked from an SQL statement.
*	LUDB functions must have at least one Output value.

### How Do I Create or Edit an LUDB Function? 
To create an LUDB function, refer to the steps in [How to Create Project Functions](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/08_project_functions.md).

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_09_01_screen1.png)

When creating an LUBD fuction, make sure that:
*	**Function Type = LUDB Function.** 
*	The LUDB function returns at least one Output parameter.

### Example of an LUDB Function

1.	Create a new function with **Function Type = LUDB Function**.
2.	Define the Input (optional) and **Output** parameters of the function.
3.	Create another function, for example a **Root function**, that invokes the **LUDB function** from the **SELECT** statement:

<pre><code>
String sql = 
"SELECT CUSTOMER_ID, ACTIVITY_ID, ACTIVITY_DATE, ACTIVITY_NOTE, fnCreateInstId(?) IID FROM Customer.ACTIVITY";
Db.Rows rows = ludb().fetch(sql,input);
for (Db.Row row:rows){
	yield(row.cells());
}
</code></pre>

Click [**fnCreateInstId.java**]  to open an example of a Fabric Root function which receives an Input parameter, performs business logic and returns an Output parameter.

[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/08_project_functions.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/10_project_functions.md)
