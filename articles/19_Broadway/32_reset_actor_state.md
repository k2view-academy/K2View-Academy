# Reset Actor State

Starting from Fabric 6.5.1, Broadway enables resetting the Actor's state within the flow. This functionality might be needed when your flow has a loop within a loop. For example, you need to aggregate a string across multiple iterations using the **StringBuild** Actor. The aggregation is done in the inner loop and should be reset on each inner loop start.

### How Do I Reset the Actor State?

The reset can be performed by one of the following options:

1. Click ![dots](images/99_19_dots.PNG) > **Reset on iteration 0** in the [Actor's context menu](18_broadway_flow_window.md#actor-context-menu) to reset the Actor's state on the inner loop start (zero iteration). 
   - The menu option is visible only when the Actor is inside an internal iteration (level 2 or more).
   - Once menu option is selected, the <img src="images/99_32_reset.PNG" style="zoom: 80%;" /> icon will appear on the Actor's badge. Its state is reset during the flow run just before the Actor is executed. 
2. Use **ResetActors** Actor that receives a list of Actor IDs (names) and resets their state.

**Example of Reset on Iteration Zero** 

![](images/99_32_1.PNG)

**Example of ResetActors**

![](images/99_32_2.PNG)



[![Previous](/articles/images/Previous.png)](31_broadway_profiler.md)