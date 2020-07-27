# Globals - Code Examples

### Example of Creating a New Global and Using it in a Function

![image](/articles/08_globals/images/08_04_01%20A%20NEW%20GLOBAL.png)

Open the **Globals** window under a **Logical Unit** and [define a new **Global**](/articles/08_globals/01_globals_overview.md#how-do-i-create-or-edit-a-global).

After the Global is saved, its definitions are kept in the Globals.java file under the same LU and its initial value = Y. This variable can be used by all functions under this LU.

<pre><code>
if (CUSTOMER_CHECKS_ENABLED.equals("Y")) {
	//do something
}
</code></pre>

This example shows how to check the value of a Global variable and to determine whether to perform or to skip specific business logic (validation checks).

### Example of Using a Global in a Table Population
![image](/articles/08_globals/images/08_04_02%20TABLE%20POPULATION.png)

Open the **Globals** window under a **Logical Unit** and [define two new **Globals**](/articles/08_globals/01_globals_overview.md) and then create a new [**Table Population**](/articles/07_table_population/01_table_population_overview.md) and add the **Globals** to it.

![image](/articles/08_globals/images/08_04_03%20new%20Table%20Population.png)

This example shows how a Global can be used in a [Table Population](/articles/07_table_population/01_table_population_overview.md). Since the SOURCE_PRODUCT_VERSION and ACTIVITY_NOTE Globals are defined in the LU’s Globals list in the **Globals.java** file, they are part of the LU’s scope and therefore can be used by the Table Population in the same LU.

[Click to display an example of Globals under a Logical Unit in the Demo project.](/articles/demo_project)

### Example of Using a Global in a Fabric Web Service

![image](/articles/08_globals/images/08_04_04%20GLOBAL%20IN%20A%20FABRIC%20WEB.png)

Open the **Globals** window under **Shared Objects** and [define new **Globals**](/articles/08_globals/01_globals_overview.md#how-do-i-create-or-edit-a-global) and then [create a **Web Service**](/articles/15_web_services/03_create_a_web_service.md#creating-a-web-service) and use the Globals.

This example shows how Globals can be used within a Fabric Web Service.

Three Globals are defined under Shared Objects Globals in the SharedGlobals.java file and therefore can be used by the Fabric Web Service.

<pre><code>
if (contrID == "" && adrID == "") {
	result = fnErrorCheck(MISSING_INPUT); //Missing input
	return result;	
} else if (contrID != "" && adrID != "") {
	result = fnErrorCheck(TOO_MANY_INPUTS); //Too many inputs
	return result;
}
</code></pre>

[Click to display an example of Globals under Shared Objects in the Demo project.](/articles/demo_project)

### Example of Overriding a Global in a Function or a Web Service

![image](/articles/08_globals/images/08_04_05%20FUNCTION%20OR%20A%20WEB%20SERVICE.png)

Open the **Globals** window under **Shared Objects** and define a [new **Global**](/articles/08_globals/01_globals_overview.md#how-do-i-create-or-edit-a-global) and then [create a **Web Service**](/articles/15_web_services/03_create_a_web_service.md#creating-a-web-service) or a [**Project function**](/articles/07_table_population/08_project_functions.md) that will override the initial value of this Global.

The following examples show how a Global can be overridden in a cluster and per session.

#### Override the Global Per Cluster
The RECEIVED_ERROR Global is created with an initial value of 0 and in a function its value is overridden per cluster.

<pre><code>
if (...) {
	...
	fabric().execute("set_global global '*.RECEIVED_ERROR="+anotherValue+"'");
}
</code></pre>

#### Set the Global Value on a Session Level

The RECEIVED_ERROR Global is created with an initial value of 0 and in a function its value is overridden on a session level. Check the value of the overridden variable in another function or a WS, for example to perform business logic. 

In **fnErrorCheck:** override the global or a variable **per session**:

<pre><code>
if (...) {
	...
        fabric().execute("set RECEIVED_ERROR="+ anotherValue);
}
</code></pre>

The **wsGetCustomerDetails** Web Service calls the **fnErrorCheck** function and then checks the value of this variable set by the **fnErrorCheck** function:

<pre><code>
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
</code></pre>


[![Previous](/articles/images/Previous.png)](/articles/08_globals/03_set_globals.md)




