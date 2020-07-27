# Broadway Flow Window - Run and Debug Flow
The Broadway flow window can be used to run or debug a Broadway flow. 

Note: Always save the flow before executing the Run and Debug Flow processes. When a [flow example](/articles/99_Broadway/17_tutorial_and_flow_examples.md) is opened, run or debugged, a local copy of the flow is saved in the Fabric project.

The Main menu of the Broadway flow is at the top of the window and has the following options:

![](/articles/99_Broadway/images/flow_tool_bar_run_and_debug.png)


## Setting Run and Debug Arguments

1. Click **Actions** > **Debug/Run Arguments** to set an external input argument value with an **External** [population type](/articles/99_Broadway/03_broadway_actor.md#input-parameter-attributes). A window opens displaying a list of external input arguments.

2. Set the **Type** of th **external** input arguments.

   ![](/articles/99_Broadway/images/flow_set_run_or_debug_param_set_type1.png)

   
3. Select the **Type** of each input argument.

   ![](/articles/99_Broadway/images/flow_set_run_or_debug_param_set_type2.png)

4. Select the **value** of each input argument. The value's format is defined by the selected type of argument.

   ![](/articles/99_Broadway/images/flow_set_run_or_debug_param_set_value1.png)

   ![](/articles/99_Broadway/images/flow_set_run_or_debug_param_set_value2.png)

5. Click **OK**.

### Resetting Run/Debug Arguments

Click **Actions** > **Debug/Run Arguments** and then the X adjacent to the argument to redefine its **type** and **value**.

## Running and Stopping a Broadway Flow

Click **Play** or **Stop** to run or stop the Broadway flow.

## Debugging a Broadway Flow

- Click ![](/articles/99_Broadway/images/debug_play_icon.png) to run the entire process in Debug mode. The Debug process runs the Broadway flow and stops during Stages with breakpoints. 
To add a breakpoint to a Stage, click **...** on the Stage > Breakpoint.

- Click ![](/articles/99_Broadway/images/debug_step_icon.png) to debug the current step and move to the next step.  

- Click **Stop Debug** to stop the debug process.

### Debug: Displaying Input and Output Data

The input and output data of each executed step is displayed and highlighted blue. 
To view a step's data, click the input or output parameter to open the data and its displayed format. To set another data display format, click the format and make your selection.

![](/articles/99_Broadway/images/flow_debug_display_data.png)

### Updating a Schema

The Broadway Debug process *learns* the schema of complex output parameters and can suggest how to update a schema based on a parameter's value.

When debugging an Actor with a complex output parameter, the parameter port is red:

  ![](/articles/99_Broadway/images/debug_update_schema.png)

1. Click the **parameter's port** (marked by red) to open the Compare Schema window.

  ![](/articles/99_Broadway/images/compare_schema.png)

2. Click **Update** to update the output parameter's schema. To open the [Data Inspector](/articles/99_Broadway/27_broadway_data_inspection.md), click the **+** adjacent to the Actor's output argument. The **yellow segment** is expanded and displays the schema on the left side and the data values on the right.

3. [Link](/articles/99_Broadway/20_broadway_flow_linking_actors.md) the output parameters of the schema to another actor.

  ![](/articles/99_Broadway/images/data_insepction_debug.png)

Note:
- To reset an Actor's Schema settings, click **Actions** > **Reset Parameters Schemas**.  Removing the output schema of complex types erases the lines originating from the Schema. 

