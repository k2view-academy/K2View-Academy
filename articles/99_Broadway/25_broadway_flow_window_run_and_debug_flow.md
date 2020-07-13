# Broadway Flow Window- Run and Debug Flow

Broadway flow window enables running and debugging the flow.

The Main menu of the Broadway flow is located on top of the window and allows to perform the following activities:

![](/articles/99_Broadway/images/flow_tool_bar_run_and_debug.png)



## Set Run or Debug Arguments

Click Actions > Debug/Run Arguments to set values for external input arguments whose [population type](/articles/99_Broadway/03_broadway_actor.md#input-parameter-attributes) is **External**.

A popup window is opened with the list of external input arguments:

1. Set the types of the external input arguments:

   ![](/articles/99_Broadway/images/flow_set_run_or_debug_param_set_type1.png)

   

   Select the type for each input argument:

   ![](/articles/99_Broadway/images/flow_set_run_or_debug_param_set_type2.png)

2. After selecting the types, set the value for each input argument. The value's format is defined by the selected type of each argument:

   ![](/articles/99_Broadway/images/flow_set_run_or_debug_param_set_value1.png)

   ![](/articles/99_Broadway/images/flow_set_run_or_debug_param_set_value2.png)

3. Click OK.

### Reset Run of Debug Arguments

Click Actions > Debug/Run Arguments and click the X icon next to the argument to redefine its type and value.

## Run and Stop Broadway Flow

Click the play or stop icons to run or stop the Broadway Flow.

## Debug Broadway Flow

- Click the Debug Play icon to run the entire process in a debug mode:

  ![](/articles/99_Broadway/images/debug_play_icon.png)

  The debug process runs the Broadway flow and stops in the  Stages that have Breakpoints. To add a Breakpoint to a Stage, click the three dots icon on the Stage > Breakpoint.

- Click the Debug Step icon to debug the current step and move to the next step:

  ![](/articles/99_Broadway/images/debug_step_icon.png)

- Click the Stop Debug icon to stop the debug process.

### Debug- Display Input and Output Data

The input and output data of every executed step is displayed and marked by blue. You can click each input or output parameter to view its data. A popup window is opened with the data and the displayed format. You can click on the format and select another format for the data display:

![](/articles/99_Broadway/images/flow_debug_display_data.png)

### Update Schema

Broadway debug "learns" the schema of complex output parameter and can suggest the update of the schema based on the parameter value:

- When debugging an actor with a complex output parameter, the parameter port is marked by red:

  ![](/articles/99_Broadway/images/debug_update_schema.png)

- Click the port of the parameter (marked by red). The Compare Schema window is opened:

  ![](/articles/99_Broadway/images/compare_schema.png)

- Click the **Update** to update the schema of the output parameter. The [data inspector](/articles/99_Broadway/27_broadway_data_inspection.md) can be open using a small **plus sign** next to the Actor's output argument. When pressing on it, the **yellow segment** is expanded and it displays the schema on its left side and the data values on its right side.

- Now you can [link](/articles/99_Broadway/20_broadway_flow_linking_actors.md) the output parameters of the schema to another actor:

  ![](/articles/99_Broadway/images/data_insepction_debug.png)

Note:
- Click Actions > Reset Parameters Schemas option reset the Actor parameter schemas to the Actor original state. Removing the output schema for complex types will erase all the lines originating from the schema.

