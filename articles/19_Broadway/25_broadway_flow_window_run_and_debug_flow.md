# Run and Debug Broadway Flow


### Setting Run and Debug Arguments
Prior to running or debugging a flow, set the types and values of input arguments that have an **External** [population type](03_broadway_actor_window.md#actors-inputs-and-outputs). Once they are set, the types and values are saved to the flow's cache. 

1. Click **Actions** > **Debug / Run Arguments** in the [Main menu](18_broadway_flow_window.md#main-menu) toolbar to open a window displaying a list of external input arguments.

2. Select the **Type** of each input argument.

   <img src="images/flow_set_run_or_debug_param_set_type2.png" alt="image" style="zoom:80%;" />

3. Set the **value** of each input argument. The value's format is defined by the selected argument type.

4. Click **OK** to save the types and their values.

### Running and Debugging a Broadway Flow

![image](images/99_25_tool_bar.PNG)

A Broadway flow can be executed from the [Main menu](18_broadway_flow_window.md#main-menu) of the flow's window by either:

- Running the entire flow and displaying the results.
- Debugging the flow by adding breakpoints. The debug can only be performed when Debug is set to ON or Live. To add a breakpoint, click ![image](images/99_19_dots.PNG) in the [Stage context menu](18_broadway_flow_window.md#stage-context-menu) > Breakpoint or click on the left of the Stage title.

Running the flow or debugging it saves a local copy of the flow in the Fabric project. If the changes have been made in the flow, they are automatically saved even if they have not been explicitly saved. For example, when a [Tutorial flow](17_tutorial_and_flow_examples.md) is run or debugged, a local copy of the flow is saved in the Fabric project.

To run the flow, click <img src="images/99_25_run.PNG" alt="image" style="zoom:80%;" /> **Run Flow** to run the entire flow and display the results. 

- If Debug mode is <img src="images/99_25_debug_on.PNG" style="zoom:80%;" /> ON or <img src="images/99_25_debug_live.PNG" style="zoom:80%;" /> Live Debug, the flow can be debugged.

- If Debug mode is <img src="images/99_25_debug_off.PNG" style="zoom:80%;" /> OFF, the flow cannot be debugged.
  

During the flow execution, the debug icon becomes purple <img src="images/99_25_debug_exe.PNG" style="zoom:80%;" />. Once the flow is completed, it goes back to either <img src="images/99_25_debug_on.PNG" style="zoom:80%;" /> or <img src="images/99_25_debug_off.PNG" style="zoom:80%;" />.

If the breakpoints have been added to the flow, the flow stops at the first breakpoint. 

If a flow has an inner flow with breakpoints, the execution of the flow stops at the inner flow’s breakpoints. The inner flow is then brought to the front and displayed at the top of the current flow in the Fabric Studio.

 - Click <img src="images/99_25_resume.PNG" alt="images" style="zoom:80%;" /> **Resume Debug** to continue the flow from where it stopped.

- Click <img src="images/99_25_step.PNG" alt="image" style="zoom:80%;" /> **Debug Step** to debug the current step and move to the next step.

- Click <img src="images/99_25_stop.PNG" alt="image" style="zoom:80%;" /> **Stop Run** to stop the flow.

When Debug is ON or Live Debug, the flow can be debugged when invoked by any Fabric entity and not necessarily by another Broadway flow. For example, when invoked by a Job.

Note that when Debug mode is <img src="images/99_25_debug_live.PNG" style="zoom:80%;" /> Live Debug, the flow runs automatically on every change in the flow except for changing the Debug / Run arguments. For example, when adding a new Actor, adding or removing a link or modifying a value.

### Displaying Input and Output Data During a Debug

When Debug is ON, the values of the input and output arguments of each executed step are displayed in blue balloons known as Data Viewers. A Data Viewer displays Java objects using a JSON-like visualization of a Broadway map, list and Java primitive data types.

<img src="images/99_25_blue_balloons.PNG" alt="image" style="zoom:80%;" />

To view a step's data, click the blue balloon to open the Data Viewer and its displayed format. To set another data display format, click the format dropdown and make your selection.

![](images/flow_debug_display_data.png)

### Updating a Schema

The Broadway Debug process *learns* the Schema of an Actor's arguments and can suggest how to update it based on a parameter's value.

1. When debugging an Actor, the parameter port turns red indicating that the Schema needs to be updated:

   <img src="images/debug_update_schema.png" style="zoom:80%;" />

2. Click the **parameter's port** (red) to open the Compare Schema window.

  <img src="images/compare_schema.png" style="zoom:67%;" />

3. Click **Update** to update the output parameter's Schema. 

<img src="images/debug_update_schema_1.png" style="zoom:80%;" />

Note that the Update Schema option can also be applied on complex structures.



[![Previous](/articles/images/Previous.png)](24_error_handling.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](27_broadway_data_inspection.md)

