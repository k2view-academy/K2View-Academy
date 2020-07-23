# Broadway Flow 

You are now familiar with Broadway concept the the main components. In addition, you viewed and executed the Broadway Tutorial flow. Now you are ready to create your first Broadway flow. Broadway has various of examples for Broadway flows. 

### What Will You Experience In This Learning Item?

By the end of the Broadway Flows learning item you will know:

- How to create your first Broadway flow.
- View, run, and debug Broadway the newly created Broadway flow.

A **Broadway Flow** represents a business process Binding other objects into the same flow, a Broadway flow acts as a graph or a tree and is built from several [Stages](/articles/99_Broadway/19_broadway_flow_stages.md) where each Stage includes one or more [Actors](/articles/99_Broadway/03_broadway_actor.md):

- Stages are executed consecutively from left to right.
- Actors in each Stage of the flow are executed top-down.

To learn more about our Broadway flow, refer to [Broadway Flow Overview](/articles/99_Broadway/16_broadway_flow_overview.md) .

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Building a Simple Broadway Flow

Now, let's start creating a new Broadway flow which selects a data from a DB table and creates a Json file, based on the selected DB records:

#### Step 1- Creating a New Broadway Flow

1. Download and open the [demo project](/articles/demo_project) in the Fabric Studio. 

2. Go to **Project Tree** > **Shared Objects**, right click **Broadway** > **New Flow** to open the Flow Name pop-up window.

3. Populate the flow name and press **Ok**. The empty flow will open.

#### Step 2- Populating the 1st Stage of the Flow

1. Get the list of customers,  that purchased more that one new subscription (contract) during the last 42 months. The data needs to be selected from **CONTRACT** table of **CRM_DB** interface based on the following SQL query:

     ```
     Select count(*) As no_of_contracts,
     max(CONTRACT.FROM_DATE) As from_date,
     CONTRACT.CUSTOMER_ID
     From CRM_DB.CONTRACT
     Where CONTRACT.FROM_DATE >= add_months(sysdate, -42)
     Group By CONTRACT.CUSTOMER_ID
     Having count(*) > 2;
     ```

 2. Add a **DbCommand** Actor to run the select statement above to the 1st stage: 

       -  Read about [Adding or Deleting an Actor] (/articles/99_Broadway/03_broadway_actor.md#how-can-i-add-or-delete-an-actor-to-a-stage) to learn how you can add the **DbCommand **Actor to the 1st Stage.

       - Edit the **DbCommand** Actor in the 1st Stage-

         - Select CRM_DB for the interface parameter.

         - Click the **QB** icon in the **sql** parameter value to open the [Query Builder window](/articles/11_query_builder/02_query_builder_window.md), populate the SQL above in the Query Builder window and click OK to populate the sql parameter.

           ![DbCommand-Example](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_Example_Stage1.png)

#### Step 3- Reading the Customer's List and Creating a File
1. The next Stages need to run a loop on the list of selected customers. For each selected record: 

   - Build a Json object.

   - Write the Json object to an output file.

2. Click the ![plus](/academy/Training_Level_1/99_Broadway/images/plus_icon.png) icon in the right side of the flow window to create the 2nd Stage. The 2nd Stage will build a Json object for each select customer's record.

3. Add a **JsonStringify** Actor to the 2nd Stage and link the **[result]** output parameter of the **DbCommand** Actor  to the **object** input parameter of the **JsonStringify** Actor. Click the link and set the **Link Type** to **Iterate** to get the selected records, returned by the **DbCommand** by a loop:

   ![JsonStringify](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_Example_Stage2.png)

   Read about [linking Actors in a Broadway Flow](/articles/99_Broadway/20_broadway_flow_linking_actors.md) to learn how link Actors in a Broadway flow.

4. Add the 3rd Stage to the flow. Both - the 2nd and the 3rd Stages- are now marked by grey, since they are included in the loop, that was opened by link of the 1st Stage to the 2nd Stage. 

   Read about [Broadway Loops] to learn more about handling loops by Broadway Flows. 

5. Open the **LocalFileSystem** interface in the Fabric Project. The **Working Directory** property of this interface is **C:\k2view\Broadway_Training**. Please create this directory in your local windows server.

6. Add a **FileWrite** Actor to the 3rd Stage. Edit the **FileWrite**, added to this Stage as follows:

   - Set the **Interface** parameter to **LocalFileSystem**. 

   - Set the population type of the **path** to be **Const** instead of **Link**.

   - Set the value of the **path** to **customer_list.json**. This parameter is populated by the new file name, created by the **FileWrite** Actor.

   - Set the **append** Boolean parameter to **false** to rewrite into the file by each flow execution.

     ![FileWrite](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_Example_Stage3.png)

   Read about [Broadway Actors](/articles/99_Broadway/03_broadway_actor.md) to learn about the Actor window and setting the Actor's parameters. 

7. Link the **string** output parameter of the **JsonStringify** Actor to the **[stream]** input parameter of the **FileWrite** Actor. The **FileWrite** Actor executes the following activities:

   - Creating a new file, named **customer_list.json** under the working directory, defined in the **LocalFileSystem** interface object.
   - Appending each Json object, sent for each selected customer, to the file.

8. Close the loop after executing the 3rd Stage:

   - Click the three dots in the right corner of the Stage to open the Stage context menu. Select the **Iterate Close** option to close the loop after the execution of the Stage.

    Read about [Stage Context Menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu)  to learn more about editing the Stage's settings.

9. The 2nd and 3rd Stages are marked by grey, and are framed by black lines that mark the open and close of the loop:

   ![image](/academy/Training_Level_1/99_Broadway/images/MyFirstFlow_Example_Stage3_close_loop.png)

#### Step 4- Flow Execution

1. Run the flow and check your local directory and open the newly created Json file. The file contains the list of customers, selected from CONTRACT table.



Congratulations. You've just created your first Broadway flow. Let's continue to the next item to learn more about adding loops and conditions to the Broadway flow.

[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/99_Broadway/04_broadway_tutorials.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/99_Broadway/06_broadway_flow_adding_loops_and_conditions.md)
