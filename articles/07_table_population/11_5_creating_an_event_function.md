# Creating an Event Function

### What Is an Event Function?

<web>

An Event function is a Project function invoked from the [Sync LU Schema tab](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-tabs).

</web>

<studio>

An Event function is a Project function invoked from the **Events** [LU Schema property](/articles/03_logical_units/04_LU_properties.md).

</studio>

* An Event function enables running user code upon the completion of a Sync or Delete Instance.
* The function is triggered on one of the following events:
  * Post Sync success, post Sync failure, post Delete Instance success.
* Any Event function on a shared or LU level can be attached to an LU Schema.
* The same Event function can be attached to several LU Schema events.

Notes:

*  Event functions run synchronously. If needed, an event function can be defined to run asynchronously in the Event function's code. 
* The implementer is responsible for handling Event function exceptions. It will decide whether to catch an exception or to display it to the user if the Event function fails.

### How Do I Create or Edit an Event Function?

To create an Event function, refer to the steps in [How to Create Project Functions](10_creating_a_project_function.md).

<studio>

![image](images/07_11_5_01.png)

</studio>

When creating an Event function, make sure that:

*	**Function Type = Event Function.** 
*	The function has an  **eventDataContext** input parameter with a **EventDataContext** data type.

The **EventDataContext** data type exposes a set of methods which allow getting additional information about the change such as:

* Instance ID
* LU Type name
* Exception

Using the above information the function can execute any required business logic, for example, to write the Instance ID and the exception into a log table or publish a message to Kafka. 

<web>

### How Do I Attach or Remove an Even Function?

1. To attach an Event function to the LU Schema, go to **Project Tree** > **Logical Units** > [**LU Name**] > **Schema** to display the **LU Schema** window.
2. In the **Sync** Schema Properties tab, click <img src="images/web/plus.PNG" style="zoom:80%;" /> icon to add an existing Even function from the list. 
3. Select an Event Type.
4. To remove an Event function, click <img src="images/web/trash.PNG" style="zoom:67%;" /> icon next to the Even function definition in the Schema Properties.
5.  **Save** the LU Schema.

 </web>

<studio>

### How Do I Attach an Event Function to an LU Schema?

1. Go to **Project Tree** > **Logical Units** > [**LU Name**] > **Schema** to display the **LU Schema** window.

2. To attach the Event function using the LU Schema [Properties tab](/articles/03_logical_units/04_LU_properties.md), click the **three dots** next to the **Events** option to open the **Event Collection Editor**. 

   ![image](images/07_11_5_02.png)

3. Click **Add** and then click the area next to **Name** to display the list of Event functions. Select the function from the list and select the Event Type.

   ![image](images/07_11_5_03.PNG)

4. (Optional) To add more **Event functions** to the same LU, click **Add** again, select the additional functions and select an event type for each function.

5. Click **OK** to close the Editor and then **Save** the LU Schema.

### How Do I Remove an Event Function from an LU Schema? 

1. Go to **Project Tree** > **Logical Units** > [**LU Name**] > **Schema** to display the **LU Schema** window.
2. In the [Properties tab](/articles/03_logical_units/04_LU_properties.md), click the **three dots** next to the **Events** option to open the **Event Collection Editor**.
3. Select the function and click **Remove**.
4. Click **OK** to close the Editor and then **Save** the LU Schema.

</studio>

### Example of an Event Function

1. Create a new function with **Function Type = Event Function**.

2. Write the business logic, for example populate the IID, LU name and a message into a specific log table defined in the Fabric Common DB. In addition, publish a message to Kafka using a Broadway flow. Note that you need to add the exception handling to the function.

   ~~~java
   try {
   	String IID = eventDataContext.getInstanceId();
   	String luTypeName = eventDataContext.getLuTypeName();
   	
   	log.info("Event function - instance ID = "+ IID);
   	log.info("Event function - LU type = "+ luTypeName);
   	
   	Db ci = db("fabric");
   	ci.beginTransaction();
   	ci.execute("insert into DATA_CHANGES values (?,?,?,?)", IID, luTypeName, "NA", "Post Sync Success");
   	ci.commit();
   	
   	fabric().execute("broadway CRM.publishCustomer iid="+ IID);
   } catch (Exception  e) {
   	log.error("The function eventFuncSuccess failed: "+e.getMessage(),e);	
   }
   ~~~
   
3. Create a Broadway flow that publishes a message to Kafka.

   ![image](images/07_11_5_04.PNG)

4. Open the LU Schema's properties window and attach the Event function to the **Events** property.

Full example of an Event Function can be found in the Demo project.

[![Previous](/articles/images/Previous.png)](11_4_creating_a_trigger_function.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_lookup_tables.md)

