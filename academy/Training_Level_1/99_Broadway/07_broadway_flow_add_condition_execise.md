# ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Exercise – Add a Condition to a Broadway Flow 

You've just created and tested your first Broadway flow which selects a data from a DB table and creates a Json file, based on the selected DB records. You practiced adding Stages and Actors to a Broadway Flow and adding a loop into the flow. 

Now, let's practice an additional capability of the Broadway flow: adding conditions to the flow. A Broadway flow can be 'split' into different execution paths based on conditions. More than one stage can be executed in each fork in the path.

You need to add the following condition to your flow:

- Check if the number of selected customers >= 3:  

  - If this condition is fulfilled, then add a message to the log file.

  - Else- add an error message to the log file.

    

Before you start, please read [Broadway Flow - Stages](/articles/99_Broadway/19_broadway_flow_stages.md) to learn about split or merge of Stages in a flow and about adding conditions to the Stage. 

#### Step 1- Open the Broadway Flow

1. Go to **Project Tree** > **Shared Objects**> **Broadway** and click the flow, created by you in the [previous lesson](/academy/Training_Level_1/99_Broadway/05_create_broadway_flow.md).


#### Step 2- Adding the Counting of the Selected Customers

1. Add the **Count** Actor to **Stage2**. This Actor returns how many times it was called, so if you add it inside the loop on the selected customers, it counts the number of the selected customers.

#### Step 3- Add a Condition to the Flow

1. Add a new Stage to the flow, click on the ![three dots](/academy/Training_Level_1/99_Broadway/images/three_dots_icon.png) icon and select **Stage Condition**. A new popup window is added to add an Actor to the Stage. Select **GreaterThanEquals** Actor and click the **SUBMIT** to add this Actor to the Stage.

2. The **GreaterThanEquals** Actor returns true if the value if **a** parameter is greater than or equals to the value of  **b** parameter. This Actor is marked by a grey color, since it is added in a condition.

   Read [Broadway Actors](/articles/99_Broadway/03_broadway_actor.md) to learn more about Broadway Actors.

3. Link the output of the **Count** condition to **a** input parameter of **GreaterThanEquals** , set input **b** parameter to be a **Const** and set its value to 3:

   ![image](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_GreaterThanEqual_Actor.png)

   

4. Split **Stage4**:

   -  Click the ![three dots](/academy/Training_Level_1/99_Broadway/images/three_dots_icon.png) icon of the newly created **Stage5**, and select the **Else** option.

#### Step 4- Add the Next Stages

1. Add a Stage to the flow and split it to **Stage6** and **Stage7**.

2. Add the **Logger** Actors to both Stages- Stage6 and Stage7.

3. Set the Logger Actor parameters of **Stage6**:

   - Set the **message** input parameter as **Const**.

   - Set the value of **message** input parameter to:

     - The are ${0} customers in the list 

     The **${0}** is set for the first parameter of the **params** input parameter.

   - Set the **level** input parameter to **info**.

4. Link the **Count** Actor to the **Logger** Actor or **Stage6**.

5. Set the Logger Actor parameters of **Stage7**:

   - Set the **message** input parameter as **Const**.
   - Set the value of **message** input parameter to:
     - Error- there are not enough customers in the list
   - Set the **level** input parameter to **error**.

#### Step 5- Debug the Updated Flow

1. Click the the ![Debug Step](/academy/Training_Level_1/99_Broadway/images/debug_step_icon.png) icon to execute the flow steps on a debug mode:

   <ul>
   <pre><code>
   1.1 What is the output value of the <strong>Count</strong> Actor? 
   1.2 What is the output value of <strong>GreaterThanEquals</strong> Actor? 
   1.3 Which Stage was executed- <strong>Stage6</strong> or <strong>Stage7</strong>? Why?
   1.4 Check the Output of the <a href="/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md">Fabric Log File</a>. Which messge is given by the Logger Actor? What is the level of the message? 
   </code></pre>
   </ul>

2. Update the **GreaterThanEquals** Actor: set parameter **b** to 10 instead of 3.

3. Rerun the debug of the flow: 

   <ul>
   <pre><code>
   3.1 What is the output value of <strong>GreaterThanEquals</strong> Actor? Is it different now? Why? 
   3.2 Which Stage was executed- <strong>Stage6</strong> or <strong>Stage7</strong>? Why?
   3.3 Check the Output of the <a href="/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md">Fabric Log File</a>. Which messge is given by the Logger Actor? What is the level of the message? Is it different now? Why? 
   </code></pre>
   </ul>



### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)Solution- Add a Condition to a Broadway Flow Exercise 

 <ul>
 <pre><code> 
 1.1 The output value of the Count Actor is 3. This is the number of the records, returned by the <strong></strong>DbCommand</strong> Actor.
 1.2 The output value of <strong>GreaterThanEquals</strong> Actor is <strong>true</strong>.
 1.3 <strong>Stage6</strong> was executed, since the condition returned <strong>true</strong>.
 1.4 The following messsage was given by the Logger Actor of Stage6: 
 <strong>INFO: The are 3 customers in the list</strong>
 </code></pre>
 </ul>

<ul>
<pre><code>
 3.1 The output value of <strong>GreaterThanEquals</strong> Actor is <strong>false</strong>.
 3.2 <strong>Stage7</strong> was executed, since the condition returned <strong>false</strong>.
 3.3 The following messsage was given by the Logger Actor of Stage7: 
     <strong>ERROR: Error- there are not enough customers in the list</strong>. 
     The Logger Actors of Stage6 and Stage7 set a different level of message, since each one of them has a   different value of the the level input parameter.
 </code></pre>
 </ul>



[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/99_Broadway/06_broadway_flow_adding_loops_and_conditions.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">]()
