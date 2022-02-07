# Example of Frequently Used Actors


### ![info](/academy/images/example.png)Reading and Parsing a File

Let's create a new Broadway flow that reads data from a CSV file, parses it and prints it into a log. Use the CSV file you created in the [Building a Simple Broadway Flow](05a_create_broadway_flow_example.md#example---building-a-simple-broadway-flow) example as an input file.

1. Create a new Broadway flow as explained in the [Building a Simple Broadway Flow](05a_create_broadway_flow_example.md#example---building-a-simple-broadway-flow) example.

2. Add a **FileRead** Actor to Stage 1 of the flow and populate its input arguments as follows:

   * Set **Interface** to **LocalFileSystem**. If this Interface has not been defined in Fabric, follow the instructions in the [Building a Simple Broadway Flow](05a_create_broadway_flow_example.md#example---building-a-simple-broadway-flow) example.

   * Change the **path** population type to **External**. 

   * To populate the **path** value, click **Actions** > **Debug / Run Arguments** in the [Main menu toolbar](/articles/19_Broadway/18_broadway_flow_window.md#main-menu). 

     <img src="images/09_debug_arg.PNG" alt="debug" style="zoom: 67%;" />

     To learn more, refer to [Setting Run and Debug Arguments](/articles/19_Broadway/25_broadway_flow_window_run_and_debug_flow.md#setting-run-and-debug-arguments).

3. Add a **CsvParser** Actor to Stage 2 of the flow and connect its input argument to the previous Actor's output. 

4. Make sure the input file exists in the designated working directory and run the flow in Debug mode when Debug is set to <img src="images/debug_on.png" alt="debug on" style="zoom:80%;" /> ON.

   * The **CsvParser** Actor's output datatype is object. However after running the flow in the Debug mode, Broadway learns the output structure and updates the schema. 
   * Click ![image](images/red_cross.png)that now appears adjacent to the **CsvParser** Actor's output argument to open the yellow Data Inspection segment and display the Schema on the left and the data values on the right. 
   
   ​	<img src="images/09_read_and_parse_0.PNG" alt="flow" style="zoom:80%;" />
   
5. Add a **Logger** Actor to Stage 3 and populate its input arguments as follows:

   * Change the **message** population type to **Const**. 
   * Set the **message** value to: *There are ${NUM_OF_CASES} cases with Status = ${CASE_STS}*.
   * Check that the new input arguments are added to the Actor: NUM_OF_CASES and CASE_STS.

6. Connect the fields in the yellow segment to the **Logger** Actor's new input arguments and set their **Link Type** to **Iterate**. 

7. The flow is ready! Run the flow in Debug mode when Debug is set to <img src="images/debug_on.png" alt="debug on" style="zoom:80%;" /> ON.

   <img src="images/09_read_and_parse.PNG" alt="flow" style="zoom:80%;" />

    To learn more about Debug options, refer to [Run and Debug Broadway Flow](/articles/19_Broadway/25_broadway_flow_window_run_and_debug_flow.md).

8. Check the output log area to see the printed results.

9. Change the following:

  * Set the **message** value of  the **Logger** Actor to: *There are ${0} cases with Status = ${1}*.
  * Connect the **[object]** output argument of the **CsvParser** Actor directly to the **[params]** input argument of the **Logger** Actor. 
  * Set the **Link Type** to **Iterate**.

10. Run the flow again and verify that the result is identical.

   ![flow](images/09_read_and_parse_2.PNG)



[![Previous](/articles/images/Previous.png)](09_frequently_used_actor_types.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_using_various_actors_exercise.md)
