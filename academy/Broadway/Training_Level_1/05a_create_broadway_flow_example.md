# Example of Creating a Broadway Flow 
### ![](/academy/images/example.png)Example - Building a Simple Broadway Flow

Let's create a new Broadway flow that selects data from a DB table and creates a JSON file based on the selected DB records:

#### Step 1 - Create a New Broadway Flow

1. Download and open the [Demo Project](/articles/demo_project) in the Fabric Studio. 
2. Go to the **project tree** > **Shared Objects**, right click **Broadway** > **New Flow** to open the Flow Name window.
3. Populate the **Flow Name** and click **OK** to open an empty flow.

#### Step 2 - Populate Stage 1 of the Flow

1. Select the number of cases for each case status from **CASES** table in **CRM_DB**  interface. Use the following SQL query:

```sql
Select CASES.STATUS, Count(*) AS NUMBER_OF_CASES
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

   Read [linking Actors in a Broadway Flow](/articles/19_Broadway/20_broadway_flow_linking_actors.md) to learn how to link Actors in a Broadway flow.

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

   Read [Broadway Actor's Window](/articles/19_Broadway/03_broadway_actor_window.md) to learn about the Actor window and setting the Actor's parameters. 

9.  The **FileWrite** Actor executes the following activities:

   - Creates a new file named **customer_list.json** under the working directory defined in the **LocalFileSystem** interface object.
   - Appends each JSON object sent for each selected customer to the file.

10. Link the **JsonStringify** Actor's **string** output parameter to the **stream** input parameter of the **FileWrite** Actor.

11. Close the loop after executing Stage 3: 

      - Click ![three dots](images/three_dots_icon.png) in the right corner of the **Stage** to open the **Stage context menu**. Select **Iterate Close** to close the loop after the execution of the Stage.

        Read [Stage Context Menu](/articles/19_Broadway/18_broadway_flow_window.md#stage-context-menu) to learn more about editing this Stage's settings.




You are ready to run your flow! Note that you need to deploy the interfaces prior to running the flow. It can be done by deploying one of the Logical Units in your project.



#### Step 4 - Flow Execution, Debug and Update the Output Schema

The **DbCommand** Actor returns  a complex schema.  Broadway Debug process *learns* the Schema of complex output parameters and can suggest how to update it based on a parameter's value. To update the output parameter of the **DbCommand**, do the following:

   - Run the flow in Debug mode when Debug is set to <img src="images/debug_on.png" alt="debug on" style="zoom:80%;" /> ON.
   - Click the red port next to the **[result]** output of the **DbCommand**.  The **Compare Schema** window is opened. Click the **UPDATE** to update the schema.

       ![Update Schema](images/MyFirstFlow_DbCommand_Update_Schema.png) 

   - Click ![image](images/red_cross.png) adjacent to the Actor's output argument to open the Data Inspection and display the Schema on the left and the data values on the right.

   - Add a  **Breakpoint** to **Stage 1** and run the debug again. Click <img src="images/debug_step_icon.png" alt="Debug Step" style="zoom:80%;" /> to execute the steps after the breakpoint step. The input and output values are displayed for each iteration in the flow.

   - Click each one of the debug values (marked by blue) to open the **Data Viewer** window for the selected parameter. See example below:

     ![image](images/MyFirstFlow_Example_debug.png)

     

For more information, read [Run and Debug Broadway Flow](/articles/19_Broadway/25_broadway_flow_window_run_and_debug_flow.md) and the [Broadway Data Inspector](/articles/19_Broadway/27_broadway_data_inspection.md).
   #### Step 5 - Check the Flow's Execution Results

Check your local directory (C:\k2view\Broadway_Training) and open the new JSON file that contains the list of cases selected from CASE table.

  

Congratulations! You've just created your first Broadway flow. 

Let's continue to the next item to learn more about adding loops and conditions to the Broadway flow.

[![Previous](/articles/images/Previous.png)](05_create_broadway_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_broadway_flow_adding_loops_and_conditions.md)