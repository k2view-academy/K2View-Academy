# Broadway Flow 

Now you are now familiar with Broadway and its main components and have checked out and executed the Broadway Tutorial flow, you are ready to create your first Broadway flow. 


### What Will You Experience In This Learning Item?

By the end of the Broadway Flow learning item you will:

- Know how to create your first Broadway flow.
- View, run and debug your Broadway flow.


A Broadway Flow:
-  Represents a business process that binds other objects into the same flow. 
-  Acts as a graph or a tree that has several [Stages](/articles/19_Broadway/19_broadway_flow_stages.md) where each Stage includes one or more [Actors](/articles/19_Broadway/03_broadway_actor.md). Stages are executed consecutively from left to right, where the Actors in each Stage of the flow are executed top-down.


To learn more about a Broadway flow, please refer to [Broadway Flow Overview](/articles/19_Broadway/02a_broadway_flow_overview.md).

### ![](/academy/images/example.png)Example - Building a Simple Broadway Flow

Let's create a new Broadway flow that selects data from a DB table and creates a JSON file based on the selected DB records:

#### Step 1 - Create a New Broadway Flow

1.1 Download and open the [Demo Project](/articles/demo_project) in the Fabric Studio. 

1.2 Go to the **project tree** > **Shared Objects**, right click **Broadway** > **New Flow** to open the Flow Name window.

1.3 Populate the **Flow Name** and click **OK** to open an empty flow.

#### Step 2 - Populate Stage 1 of the Flow

2.1. Get a list of customers purchasing one or more new subscriptions (contracts) over the last 24 months. The data must be selected from the **CONTRACT** table in the **CRM_DB** interface based on the following SQL query:

     ```
     Select count(*) As no_of_contracts,
     max(CONTRACT.FROM_DATE) As from_date,
     CONTRACT.CUSTOMER_ID
     From CRM_DB.CONTRACT
     Where CONTRACT.FROM_DATE >= add_months(sysdate, -24)
     Group By CONTRACT.CUSTOMER_ID
     Having count(*) > 2;
     ```

 2.2. Add a **DbCommand** Actor to run the above **SELECT statement** in Stage 1: 

      Read [Adding or Deleting an Actor] (/articles/99_Broadway/03_broadway_actor.md#how-can-i-add-or-delete-an-actor-to-a-stage) to learn how to add the **DbCommand** Actor to Stage 1.

       - Edit the **DbCommand** Actor in Stage 1.

         - Select **CRM_DB** as the **Interface**.

         - Click **QB** in the **SQL** parameter value to open the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md), populate the **SQL** in the **Query Builder** window and click **OK** to populate the **SQL** parameter.

           ![DbCommand-Example](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_Example_Stage1.png)

#### Step 3 - Read the Customer's List and Create a File
1. The next Stages run a loop on the list of selected customers. For each selected record: 

   - Build a JSON object.

   - Write the JSON object to an output file.

2. Click ![plus](/academy/Training_Level_1/99_Broadway/images/plus_icon.png) in the right of the Flow window to create Stage 2 which builds a JSON object for each selected customer record.

3. Add a **JsonStringify** Actor to Stage 2 and link the **result** output parameter of the **DbCommand** Actor to the **object** input parameter of the **JsonStringify** Actor. Click the link and set the **Link Type** to **Iterate** to get the selected records returned by the **DbCommand** by a loop:

   ![JsonStringify](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_Example_Stage2.png)

   Read [linking Actors in a Broadway Flow](/articles/99_Broadway/20_broadway_flow_linking_actors.md) to learn how to link Actors in a Broadway flow.

4. Add Stage 3 to the flow. Both Stages 2 and 3 are marked in grey since they are included in the loop that has been opened by linking Stages 1 and 2. 

   Read [Broadway Loops] to learn more about handling loops on Broadway flows. 

5. Open the **LocalFileSystem** interface in the Fabric Project. The **Working Directory** property of this interface is **C:\k2view\Broadway_Training**. Create this directory in your local Windows server.

6. Add a **FileWrite** Actor to **Stage 3** and edit it as follows:

   - Set **Interface** to **LocalFileSystem**. 

   - Set the path's **Population Type** to **Const** instead of **Link**.

   - Set the value of the **Path** to **customer_list.json**. This parameter is populated by the new filename created by the **FileWrite** Actor.

   - Set the **Append** Boolean parameter to **false** to rewrite each flow execution into the file.

     ![FileWrite](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_Example_Stage3.png)

  
  Read [Broadway Actors](/articles/99_Broadway/03_broadway_actor.md) to learn about the Actor window and setting the Actor's parameters. 

7. Link the **JsonStringify** Actor's **string** output parameter to the **[stream]** input parameter of the **FileWrite** Actor. The **FileWrite** Actor executes the following activities:

   - Creates a new file named **customer_list.json** under the working directory defined in the **LocalFileSystem** interface object.
   - Appends each JSON object sent for each selected customer to the file.

8. Close the loop after executing Stage 3: Click ***...*** in the right corner of the **Stage** to open the **Stage context menu**. Select **Iterate Close** to close the loop after the execution of the Stage.

    Read [Stage Context Menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu) to learn more about editing this Stage's settings.

9. Stages 2 and 3 have a grey background and a black frame to indicate that the loop has been opened and closed.

   ![image](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_Example_Stage3_close_loop.png)

#### Step 4 - Flow Execution

Run the flow, check your local directory and open the new JSON file that contains the list of customers selected from CONTRACT table.



Congratulations.

You've just created your first Broadway flow. Let's continue to the next item to learn more about adding loops and conditions to the Broadway flow.

[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/99_Broadway/04_broadway_tutorials.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/99_Broadway/06_broadway_flow_adding_loops_and_conditions.md)
