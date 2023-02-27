# Globals - Code Examples

### Example of Creating a New Global and Using it in a Function

<studio>
	
Go to **Project Tree > Logical Units > [LU name] > Java** and then click Globals.java to open the Globals window.

![image](images/08_04_01_NEW_GLOBAL.png)
	
Populate the settings as follows:
  * Enter a **Global Name** in the **Name** column.
  * Enter a value in the **Value** column.
  * (Optional) Enter a **Category** in the **Category** column.
  * (Optional) Enter a **Comment** in the **Comment** column.
  * Check if the **Global** is **Final**.
	
Click **Save**.

</studio>

<web>
	
* Go to **Project Tree > Implementation > Logical Units/Data Products > [LU name]** .
* Expand **Java** .
* A hierarchy tree will open. Drill down until you see Globals.java, and double click on it.
* Edit the Java file to add and define the global variable.
* Save the file (File -> Save or CTRL-S). 

</web>

After the Global is saved, its definitions are kept in the Globals.java file under the same LU, and its initial value = Y. This variable can be used by all functions under this LU. The example below shows how to check the value of the Global variable named CUSTOMER_CHECKS_ENABLED in the CRM LU, and to determine whether to perform or to skip specific business logic (validation checks):

~~~java
if (UserCode.getGlobal("CUSTOMER_CHECKS_ENABLED", "CRM").equals("Y") {
	//do something
}
~~~

Notes: 
- The **getGlobal** method has 2 versions:
	- getGlobal(String globalName) - returns the global value for this session. In case of conflict values between LU's will throw an exception.
	- getGlobal(String globalName, String lu) - returns LU global value for this session.

- Invoking the Global directly by the user code returns the Global's value and does not return the overridden value if it exists. To get the Global's overriden value use either the **getGlobal** method or use the **set command**. For example:

~~~java
if (ludb().fetch("SET CRM.CUSTOMER_CHECKS_ENABLED").firstValue().toString().equals("Y")) {
	//do something
}
~~~

### Example of Using a Global in an SQL Statement in a Function

A global can be used in an SQL prepared statement in an LU function. The syntax is: '@[global_name]@'. For Integer value, the use of apostrophe is optional. Here is an example of using a global called NEW_IND: 

~~~java
String sql = "SELECT * From ACTIVITY WHERE CUSTOMER_ID = ? AND ACTIVITY_ID = ? AND NEW_NOTE_IND = @NEW_IND@";
ludb().fetch(sql, input1, input2).each(row->{
    yield(row.cells());
});
~~~

<studio>

### Example of Using a Global in a Table Population

![image](images/08_04_02_TABLE_POPULATION.png)

Open the **Globals** window under a **Logical Unit** and [define two new **Globals**](/articles/08_globals/01_globals_overview.md) and then create a new [**Table Population**](/articles/07_table_population/01_table_population_overview.md) and add the **Globals** to it.

![image](images/08_04_03_new_Table_Population.png)

This example shows how a Global can be used in a [Table Population](/articles/07_table_population/01_table_population_overview.md). Since the SOURCE_PRODUCT_VERSION and ACTIVITY_NOTE Globals are defined in the LU’s Globals list in the **Globals.java** file, they are part of the LU’s scope and therefore can be used by the Table Population in the same LU.

[Click to display an example of Globals under a Logical Unit in the Demo project.](/articles/demo_project/README.md)

</studio>

### Example of Using a Global in a Fabric Web Service

<studio>

![image](images/08_04_04_GLOBAL_IN_WS.png)

</studio>

This example shows how the Globals MISSING_INPUT and TOO_MANY_INPUTS can be used within a Fabric Web Service.

Three Globals are defined in the SharedGlobals.java file and therefore can be used by the Fabric Web Service.

~~~java
if (contrID == "" && adrID == "") {
	result = fnErrorCheck(getGlobal(MISSING_INPUT)); //Missing input
	return result;	
} else if (contrID != "" && adrID != "") {
	result = fnErrorCheck(getGlobal(TOO_MANY_INPUTS)); //Too many inputs
	return result;
}
~~~


[Click to display an example of Globals under Shared Objects in the Demo project.](/articles/demo_project/README.md)

### Example of Overriding a Global in a Function or a Web Service

<studio>

![image](images/08_04_05_FUNC_OR_WS.png)

</studio>

The following examples show how a Global can be overridden in a cluster and per session.

#### Override the Global Per Cluster
The RECEIVED_ERROR Global is created with an initial value of 0 and in a function its value is overridden per cluster.

~~~java
if (...) {
	...
	fabric().execute("set_global global '*.RECEIVED_ERROR="+anotherValue+"'");
}
~~~


#### Set the Global Value on a Session Level

The RECEIVED_ERROR Global is created with an initial value of 0 and in a function its value is overridden on a session level. Check the value of the overridden variable in another function or a WS, for example to perform business logic. 

In **fnErrorCheck:** override the global or a variable **per session**:

~~~java
if (...) {
	...
    fabric().execute("set RECEIVED_ERROR="+ anotherValue);
}
~~~


The **wsGetCustomerDetails** Web Service calls the **fnErrorCheck** function and then checks the value of this variable set by the **fnErrorCheck** function:

~~~java
Object val;
String receivedErr = "";
Map<String,Object> result = new HashMap<>(); 
//invoke fnErrorCheck() which sets the value of RECEIVED_ERROR
result = fnErrorCheck(input); 
...
//get the value of RECEIVED_ERROR as set by fnErrorCheck() and do something...
val = db("fabric").fetch("set RECEIVED_ERROR").firstValue();
receivedErr = k2_ifNull(val.toString(),"No error");
result.put("p_error", receivedErr);
~~~



[![Previous](/articles/images/Previous.png)](/articles/08_globals/03_set_globals.md)  [<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/08_globals/05_globals_overrides_priorities.md)



