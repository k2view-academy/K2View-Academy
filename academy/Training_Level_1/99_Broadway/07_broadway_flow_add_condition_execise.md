# ![](/academy/images/Exercise.png) Exercise – Adding a Condition to a Broadway Flow 

You've just created and tested your first Broadway flow that selects data from a DB table and creates a JSON file based on the selected DB records. You've also practiced adding Stages and Actors to a Broadway flow and then adding a loop to the flow. 

Now, let's practice an additional Broadway flow feature - adding conditions to a flow. 

A Broadway flow can be split into different execution paths based on conditions so that more than one Stage can be executed in each fork of the path.

In this exercise you will add a condition to your flow that does the following:

- Check if the number of selected customers >= 3:  

  - If this condition is fulfilled, then add a message to the log file.

  - Else, add an error message to the log file.

    

Before you start, please read [Broadway Flow - Stages](/articles/99_Broadway/19_broadway_flow_stages.md) to learn about spliting and merging the Stages of a flow and about adding conditions to a Stage. 

#### Step 1 - Open the Broadway Flow

Go to the **project tree** > **Shared Objects** > **Broadway** and click the flow you created in the [previous lesson](/academy/Training_Level_1/99_Broadway/05_create_broadway_flow.md).


#### Step 2 - Add Counting of Selected Customers

Add the **Count** Actor to **Stage2**. This Actor returns the number of times an Actor is called. When this Actor is added to the loop, it counts the number of the selected customers.

#### Step 3 - Add a Condition to the Flow

1. Add a new Stage to the flow, click ![three dots](/academy/Training_Level_1/99_Broadway/images/three_dots_icon.png) and select **Stage Condition**. A new popup window opens where you can add an Actor to the Stage. Select the **GreaterThanEquals** Actor and click  **SUBMIT**. 

The **GreaterThanEquals** Actor returns true if the value of the **a** parameter is greater than or equals the value of the **b** parameter. This Actor is marked in grey since it is added in a condition.

   Read [Broadway Actors](/articles/99_Broadway/03_broadway_actor.md) to learn more about Broadway Actors.

3. Link the output of the **Count** condition to the **GreaterThanEquals** **a** input parameter to set the **b** parameter to be a **Const** and set its value to 3:

   ![image](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_GreaterThanEqual_Actor.png)

   

4. Split **Stage4**:

  Click ![three dots](/academy/Training_Level_1/99_Broadway/images/three_dots_icon.png) in the newly created **Stage5** and select the **Else** option.

#### Step 4 - Add the Next Stages

1. Add a Stage to the flow and split it into **Stage6** and **Stage7**.

2. Add the **Logger** Actors to **Stage6** and **Stage7**.

3. Set the **Logger** Actor's parameters of **Stage6**:

   - Set the **message** input parameter as **Const**.

   - Set the **message** input parameter value to:

     - There are ${0} customers in the list 

     The **${0}** is set for the first parameter of the **params** input parameter.

   - Set the **level** input parameter to **info**.

4. Link the **Count** Actor to the **Logger** Actor or to **Stage6**.

5. Set the **Logger** Actor's parameters of **Stage7**:

   - Set the **message** input parameter as **Const**.
   - Set the **message** input parameter value to:
     - Error- there are not enough customers in the list
   - Set the **level** input parameter to **error**.

#### Step 5 - Debug the Updated Flow

1. Click ![Debug Step](/academy/Training_Level_1/99_Broadway/images/debug_step_icon.png) to execute the flow steps in Debug mode:

   <ul>
   <pre><code>
   A. What is the output value of the <strong>Count</strong> Actor? 
   B. What is the output value of the <strong>GreaterThanEquals</strong> Actor? 
   C. Which Stage has been executed - <strong>Stage6</strong> or <strong>Stage7</strong>? Why?
   D. Check the Output of the <a href="/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md">Fabric Log File</a>. Which message is given by the <strong>Logger</strong> Actor? What is the level of the message? 
   </code></pre>
   </ul>

2. Update the **GreaterThanEquals** Actor, set the **b** parameter to 10 instead of 3.

3. Rerun the Debug on the flow: 

   <ul>
   <pre><code>
   A. What is the output value of <strong>GreaterThanEquals</strong> Actor? Is it different now? Why? 
   B. Which Stage has been executed - <strong>Stage6</strong> or <strong>Stage7</strong>? Why?
   C. Check the output of the <a href="/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md">Fabric Log File</a>. Which message is given by the <strong>Logger</strong> Actor? What is the level of the message? Is it different now? Why? 
   </code></pre>
   </ul>



### ![](/academy/images/Solution.png)Solution - Add a Condition to a Broadway Flow Exercise 

 <ul>
 <pre><code> 
 Step 1.
 A. The output value of the <strong>Count</strong> Actor is 3. This is the number of the records returned by the <strong></strong>DbCommand</strong> Actor.
 B. The output value of <strong>GreaterThanEquals</strong> Actor is <strong>true</strong>.
 C. <strong>Stage6</strong> has been executed since the condition returned <strong>true</strong>.
 D. The following messsage has been given by the Logger Actor of Stage6: 
 <strong>INFO: The are 3 customers in the list</strong>
 </code></pre>
 </ul>

<ul>
<pre><code>
 Step 3.
 A.The output value of <strong>GreaterThanEquals</strong> Actor is <strong>false</strong>.
 B.<strong>Stage7</strong> has been executed since the condition returned <strong>false</strong>.
 C.The following messsage has been given by Stage7's Logger Actor: 
     <strong>ERROR: Error- there are not enough customers in the list</strong>. 
     Stage6 and Stage7  Logger Actors have set different message levels since each has a different value in the level input parameter.
 </code></pre>
 </ul>



[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/99_Broadway/06_broadway_flow_adding_loops_and_conditions.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">]()
