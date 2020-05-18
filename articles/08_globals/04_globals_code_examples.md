# Globals - Code Examples

### Example of Creating a New Global and Using it in a Function

![image]

Open the **Globals** window under a **Logical Unit** and [define a new **Global**](https://github.com/k2view-academy/K2View-Academy/wiki/Globals-Overview).

After the Global is saved, its definition is kept in the Globals.java file under the same LU and its initial value = Y. This variable can be used by all the functions under this LU.

if (CUSTOMER_CHECKS_ENABLED.equals("Y")) {
	//do something
}


This example shows how to check the value of a Global variable and to determine whether to perform or to skip specific business logic (validation checks).

### Example of Using a Global in a Table Population
![image]

Open the **Globals** window under a **Logical Unit** and [define two new **Globals**](https://github.com/k2view-academy/K2View-Academy/wiki/Globals-Overview) and then create a new **Table Population** and add the **Globals** to it.

![image]

This example shows how a Global can be used in a Table Population. Since the SOURCE_PRODUCT_VERSION and ACTIVITY_NOTE Globals are defined in the LU’s Globals list in the **Globals.java** file, they are part of the LU’s scope and therefore can be used by the Table Population in the same LU.

**Click to open the Globals Definition in the [Globals.java] File.**

### Example of Using a Global in a Fabric Web Service

![image]

Open the **Globals** window under **Shared Objects** and [define new **Globals**](https://github.com/k2view-academy/K2View-Academy/wiki/Globals-Overview) and then [create a **Web Service**] and use the Globals.

This example shows how Globals can be used within a Fabric Web Service.

Three Globals are defined under Shared Objects Globals in the SharedGlobals.java file and therefore can be used by the Fabric Web Service.


if (contrID == "" && adrID == "") {
	result = fnErrorCheck(MISSING_INPUT); //Missing input
	return result;	
} else if (contrID != "" && adrID != "") {
	result = fnErrorCheck(TOO_MANY_INPUTS); //Too many inputs
	return result;
}


**Click to open the Globals Definition in [SharedGlobals.java].**\
**Click to open the Fabric WS [wsGetCustomerDetails.java] example that uses the above Globals.**

### Example of Overriding a Global in a Function or a Web Service

![image]

Open the **Globals** window under **Shared Objects** and [define a new **Global**] and then [create a **Fabric Web Service**] or a [**Project function**] that will override the initial value of this Global.\
The following examples show how a Global can be overridden in a cluster and per session.

### Override the Global Per Cluster
The RECEIVED_ERROR Global is created with an initial value of 0 and in a function its value is overridden per cluster.


if (...) {
	...
	fabric().execute("set_global global '*.RECEIVED_ERROR="+anotherValue+"'");
}

### Set the Global Value on a Session Level

The RECEIVED_ERROR Global is created with an initial value of 0 and in a function its value is overridden on a session level. Check the value of the overridden variable in another function or a WS, for example to perform business logic. 

In **fnErrorCheck:** override the global or a variable **per session**:

if (...) {
	...
fabric().execute("set RECEIVED_ERROR="+ anotherValue);
}

The WS **wsGetCustomerDetails** calls the **fnErrorCheck** function and then checks the value of this variable set by the **fnErrorCheck** function:


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







