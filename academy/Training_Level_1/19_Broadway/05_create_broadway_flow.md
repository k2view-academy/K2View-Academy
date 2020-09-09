# Creating a Broadway Flow 

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

1. Download and open the [Demo Project](/articles/demo_project) in the Fabric Studio. 
2. Go to the **project tree** > **Shared Objects**, right click **Broadway** > **New Flow** to open the Flow Name window.
3. Populate the **Flow Name** and click **OK** to open an empty flow.

#### Step 2 - Populate Stage 1 of the Flow

1. Select the number of cases for each case status from **CASES** table in **CRM_DB**  interface. Use the following SQL query:

```sql
Select Count(*) AS NUMBER_OF_CASES,
CASES.STATUS
From CASES
Group By CASES.STATUS
```

2. Add a **DbCommand** Actor to run the above **SELECT statement** in Stage 1: 

   - Read How [Do I Add Actor to Stage](/articles/19_Broadway/03_broadway_actor.md#how-do-i-add-actor-to-stage) to learn how to add an Actor to the Broadway flow.
   - Read more about [DbCommand](/articles/19_Broadway/04_built_in_actor_types.md#db) Built-In Actor. 

3. Edit the **DbCommand** Actor in Stage 1. Add the DB interface and the SQL above to the Actor:

   - Select **CRM_DB** as the **Interface**.
- Click **QB** in the **SQL** parameter value to open the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md), populate the **SQL** in the **Query Builder** window and click **OK** to populate the **SQL** parameter: 
  

![DbCommand-Example](images/MyFirstFlow_Example_Stage1_DbCommand.png)

#### Step 3 - Read the Customer's List and Create a File
1. The SQL query, executed by the **DbCommand**, returns several records. The next Stages run a loop on the list of  the selected records. For each selected record you need to:

   - Build a JSON object.
   - Write the JSON object to an output file.

   ##### Building a JSON Object for Each Selected Record

2. Click ![plus](images/plus_icon.png) in the right of the Flow window to create Stage 2 which builds a JSON object for each selected customer record.

3. Add a **JsonStringify** Actor to Stage 2.

4. Link the **result** output parameter of the **DbCommand** Actor to the **object** input parameter of the **JsonStringify** Actor. Click the link and set the **Link Type** to **Iterate** to get the selected records returned by the **DbCommand** by a loop.

   

   ![](images/information.png)Read [linking Actors in a Broadway Flow](/articles/19_Broadway/20_broadway_flow_linking_actors.md) to learn how to link Actors in a Broadway flow.

   

   ##### Writing the JSON Object to an Output File for Each Selected Record

5. Add Stage 3 to the flow. Both Stages 2 and 3 are marked in grey since they are included in the loop that has been opened by linking Stages 1 and 2. Read [Broadway Iterations](/articles/19_Broadway/21_iterations.md) to learn more about handling loops on Broadway flows. 

6. Open the **LocalFileSystem** interface in the Fabric Project. The **Working Directory** property of this interface is **C:\k2view\Broadway_Training**.

7.  Create the **C:\k2view\Broadway_Training** directory in your local Windows server.

8. Add a **FileWrite** Actor to **Stage 3** and edit it as follows:

   - Set **Interface** to **LocalFileSystem**.
   - Set the **path's Population Type** to **Const** instead of **Link**.
   - Set the value of the **Path** to **customer_list.json**. This parameter is populated by the new filename created by the **FileWrite** Actor.
   - Set the **Append** Boolean parameter to **false** to rewrite each flow execution into the file.

    ![FileWrite](images/MyFirstFlow_Example_Stage3_FileWrite.png) 

   ![](images/information.png)Read [Broadway Actor's Window](/articles/19_Broadway/03_broadway_actor_window.md) to learn about the Actor window and setting the Actor's parameters. 

9.  The **FileWrite** Actor executes the following activities:

   - Creates a new file named **customer_list.json** under the working directory defined in the **LocalFileSystem** interface object.
   - Appends each JSON object sent for each selected customer to the file.

10. Link the **JsonStringify** Actor's **string** output parameter to the **stream** input parameter of the **FileWrite** Actor.

11. Close the loop after executing Stage 3: 

      - Click ![three dots](images/three_dots_icon.png) in the right corner of the **Stage** to open the **Stage context menu**. Select **Iterate Close** to close the loop after the execution of the Stage.

        
    
        ![](images/information.png)Read [Stage Context Menu](/articles/19_Broadway/18_broadway_flow_window.md#stage-context-menu) to learn more about editing this Stage's settings.
    
    ​      

#### Step 4 - Flow Execution and Debug

##### Updating the Output Schema of the DbCommand Actor

1. The **DbCommand** Actor returns  a complex schema.  Broadway Debug process *learns* the Schema of complex output parameters and can suggest how to update it based on a parameter's value. To update the output parameter of the **DbCommand**, do the following:

   - Run the flow in a debug mode when the debug is set to ON ![debug on](images/debug_on.png).

   - Click the red port next to the **[result]** output of the **DbCommand**.  The **Compare Schema** window is opened. Click the UPDATE to update the schema.

       ![Update Schema](images/MyFirstFlow_DbCommand_Update_Schema.png) 

   - Click ![image](images/red_cross.png) adjacent to the Actor's output argument to open the [Data Inspection]() and display the Schema on the left and the data values on the right.

   - Add a  **Breakpoint** to **Stage 1** and run again the debug. Click ![Debug Step](images/debug_step_icon.png) to execute the next steps after the breakpoint step.

   - Now you see the input and output values are displayed for each iteration in the flow.

   - Click each one of the debug values (marked by blue) to open the **Data Viewer** window for the selected parameter. See example below:

     ![image](images/MyFirstFlow_Example_debug.png)

     

   Read about [Run and Debug Broadway Flow](/articles/19_Broadway/25_broadway_flow_window_run_and_debug_flow.md) and [Broadway Data Inspector](/articles/19_Broadway/27_broadway_data_inspection.md).

   ##### Checking the Flow's Execution Results

2. Check your local directory (C:\k2view\Broadway_Training) and open the new JSON file that contains the list of cases selected from CASE table.

   

Congratulations! You've just created your first Broadway flow. 

Let's continue to the next item to learn more about adding loops and conditions to the Broadway flow.

[![Previous](/articles/images/Previous.png)](04_broadway_tutorials.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_broadway_flow_adding_loops_and_conditions.md)