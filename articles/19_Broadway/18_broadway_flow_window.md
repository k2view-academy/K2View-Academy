# Broadway Flow Window

The **Broadway flow** window is the main Broadway interface that enables the definition and execution of business processes including [Stages](19_broadway_flow_stages.md), [Actors](03_broadway_actor.md) and [links between the Actors](07_broadway_flow_linking_actors.md). 

To create a new Broadway flow:
1. Go to **Project Tree** > **Shared Objects**, right click **Broadway** > **New Flow** to open the Flow Name window.
2. Populate the **flow name** and click **OK** to open an empty Flow area.

![image](images/99_18_01_main_flow_area.PNG)

The **Broadway flow** window includes the following elements:

- The flow area, which displays Stages and their Actors. 
  - To [add a Stage](19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage), either click the big **+**, or go to the [Stage context menu](18_broadway_flow_window.md#stage-context-menu) > **Insert After** or **Insert Before**. 
  - To [add Actors to a Stage](03_broadway_actor.md#how-do-i-add-actor-to-stage), click in the empty Stage area.
- [Main menu](18_broadway_flow_window.md#main-menu), which enables running the flow and executing additional actions like **Save as Actor**.
- [Stage context menu](18_broadway_flow_window.md#stage-context-menu), which provides Stage activities like **Merge** or **Delete**.
- [Actor context menu](18_broadway_flow_window.md#actor-context-menu), which provides Actor activities like **Add**, **Link** or **Export Actor**. 
- [Actor Properties window](18_broadway_flow_window.md#actor-properties-window), which enables setting and editing the properties of a selected object. 

### Main Menu

The Main menu is a toolbar located at the top of the window. It has the following options:

![image](images/99_18_01_main_menu.PNG)

<table style="width: 900px;">
<tbody>
<tr>
<td width="170pxl">Actions</td>
<td width="630pxl">
<ul>
<li><a href="17_tutorial_and_flow_examples.md">Examples</a>, open the tutorial or an example flow.</li>
<li><a href="22_broadway_flow_inner_flows.md">Save as Actor</a>, save the current flow as an Actor and use its logic as an inner flow in another Broadway flow.</li>
<li><a href="25_broadway_flow_window_run_and_debug_flow.md">Debug/Run Arguments</a>, if the flow's population type is External, click to open the popup window to define the input arguments for its execution.</li>
<li>Flip Scroll-wheel, to move the mouse over the flow's scrollbar horizontally instead of vertically.</li>
</ul>
</td>
</tr>
<tr>
<td width="200">Run Flow / Stop Run</td>
<td style="width: 465px;">
<p>Run the entire flow or stop the run.</p>
</td>
</tr>
<tr>
<td width="200">Resume Debug</td>
<td style="width: 465px;">
<p>Resume the flow until the next breakpoint.</p>
</td>
</tr>
<tr>
<td width="200">Debug Step</td>
<td style="width: 465px;">
<p>Execute the next step of the flow.</p>
</td>
</tr>
<tr>
<td width="200"><a title="Debug" href="/articles/19_Broadway/25_broadway_flow_window_run_and_debug_flow.md#running-and-debugging-a-broadway-flow">Debug ON / OFF / Live</a></td>
<td style="width: 465px;">
<p>Enable the Debug mechanism.</p>
<p>When Debug is ON or Live, the flow can be debugged even if it was tirggered from another Fabric object, for example a job.</p>
</td>
</tr>
<tr>
<td width="200">Search Actor</td>
<td style="width: 465px;">
<p>Search the Actor by name, parameter name, schema, external name or const value.</p>
</td>
</tr>
<tr>
<td width="200">Undo/Redo</td>
<td style="width: 465px;">
<p>Click to undo or redo the last activity.</p>
</td>
</tr>
<tr>
<td width="200">Remarks</td>
<td style="width: 465px;">
<p>Click to display all remarks in the flow.</p>
</td>
</tr>
</tbody>
</table>



[Click for more information about how to run and debug the Broadway flow](25_broadway_flow_window_run_and_debug_flow.md).

### Stage Context Menu

To open the Stage context menu, click ![image](images/99_19_dots.PNG) in the right corner of the Stage. This menu is dynamic, whereby some items are displayed only when they are applicable to the selected Stage. 

<table style="width: 900px;">
<tbody>
<tr>
<td rowspan="14" width="400pxl">
<p><img src="images/99_18_02_stage_menu_up.PNG" alt="Stage context menu" /></p>
</td>
<td width="80pxl">Paste Selection</td>
<td width="420pxl">Paste the copied selection, for example an Actor from another Stage.</td>
</tr>
<tr>
<td width="80pxl"><a href="19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage ">Insert After</a></td>
<td width="420pxl">Add a new Stage after the selected one.</td>
</tr>
<tr>
<td width="200"><a href="19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage ">Insert Before</a></td>
<td style="width: 465px;">Add a new Stage before the selected one.</td>
</tr>
<tr>
<td width="200"><a href="19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage "> Delete</a></td>
<td style="width: 465px;">Delete the selected Stage and its dependent branch.</td>
</tr>
<tr>
<td width="200"><a href="19_broadway_flow_stages.md#how-do-i-split-or-merge-the-stages"> Split</a></td>
<td style="width: 465px;">Split the selected Stage.</td>
</tr>
<tr>
<td width="200"><a href="19_broadway_flow_stages.md#how-do-i-split-or-merge-the-stages"> Merge</a></td>
<td style="width: 465px;">Merge the selected Stage.</td>
</tr>
<tr>
<td width="200"><a href="19_broadway_flow_stages.md#how-do-i-split-or-merge-the-stages">Iterate Close</a></td>
<td style="width: 465px;">Close the iteration scope. The Iteration scope starts where the Iterate line type originates in the Stage.</td>
</tr>
<tr>
<td width="200"><a href="19_broadway_flow_stages.md#what-is-a-stage-condition">Stage Condition</a></td>
<td style="width: 465px;">Click to open a popup window and select an Actor for the Stage. The Actor will serve as a Stage condition. Note that Stage condition Actors are grey.</td>
</tr>
<tr>
<td width="200"><a href="19_broadway_flow_stages.md#what-is-a-stage-condition">Else</a></td>
<td style="width: 465px;">Click to mark the Stage to be executed if none of the conditions on the same level are true.</td>
</tr>
<tr>
<td width="200"><a href="24_error_handling.md">Error Handler</a></td>
<td style="width: 465px;">Click to open a popup window and select the Stage's Error Handler Actor. Note that Error Handler Actors are red.</td>
</tr>
<tr>
<td><a href="19_broadway_flow_stages.md#support-parallel-actors-execution">Parallel</a></td>
<td style="width: 465px;">
<p>Click to set a number of Actors to be executed in parallel within a Stage.</p>
</td>
</tr>
<tr>
<td width="200"><a href="23_transactions.md">Transaction</a></td>
<td style="width: 465px;">Click to open or close the transaction.</td>
</tr>
<tr>
<td width="200">Breakpoint</td>
<td style="width: 465px;">Click to set a breakpoint for the Stage. A breakpoint can be also set by clicking on the left of the Stage title.</td>
</tr>
<tr>
<td width="200">Remark</td>
<td style="width: 465px;">Click to add a remark to the Stage. If the remark already exists, a <img src="images/99_17_green_ast.PNG" alt="green asterisk" /> is displayed on the left of the three dots and the Remark popup is open displaying its text.&nbsp;</td>
</tr>
</tbody>
</table>





### Actor Context Menu

To open the Actor's context menu, click ![image](images/99_19_dots.PNG) in the right corner of the Actor. This menu is dynamic, whereby some items are displayed only when they are applicable to the selected Actor.

<table style="width: 900px;">
<tbody>
<tr>
<td rowspan="10" width="400pxl">
<p><img src="images/99_18_03_actor_menu_up.png" alt="Actor's context menu" /></p>
</td>
<td width="80pxl">Collapse / Expand</td>
<td width="420pxl">Collapse or expand the Actor and display its title.</td>
</tr>
<tr>
<td width="200"><a href="08_show_only_connected_actors.md">Show Only Connected</a></td>
<td style="width: 465px;">Mark to display the selected Actor and its connections.</td>
</tr>
<tr>
<td width="200">Copy Selection</td>
<td style="width: 465px;">Copy the selected Actor.</td>
</tr>
<tr>
<td width="200">Disable Actor</td>
<td style="width: 465px;">Click to disable / enable the Actor in the flow.</td>
</tr>
<tr>
<td width="200">Delete Actor</td>
<td style="width: 465px;">Click to delete the selected Actor from the flow.</td>
</tr>
<tr>
<td width="200"><a href="07_broadway_flow_linking_actors.md">Link</a></td>
<td style="width: 465px;">Click to connect the selected Actor and the required target Actor.</td>
</tr>
<tr>
<td width="200"><a href="03_broadway_actor.md#how-do-i-add-actor-to-stage">Add Actor</a></td>
<td style="width: 465px;">Click to open a popup window and select an Actor for the Stage.&nbsp;</td>
</tr>
<tr>
<td width="200"><a href="06_export_actor.md">Export Actor</a></td>
<td style="width: 465px;">Click to save an Actor to inherit the selected Actor. The Actor is then added to the list of <a href="04_built_in_actor_types.md">built-in Actors</a> and can be used in other flows.&nbsp;</td>
</tr>
<tr>
<td width="200"><a href="03_broadway_actor.md#actor-description-and-remark"> Description</a></td>
<td style="width: 465px;">Product Actor's description.</td>
</tr>
<tr>
<td width="200"><a href="03_broadway_actor.md#actor-description-and-remark"> Remark</a></td>
<td style="width: 465px;">Additional info added to the Actor instance.</td>
</tr>
</tbody>
</table>






### Actor Properties Window

The Actor's Properties in the Broadway flow window dynamically adjusts its layout to display data based on the structure of the selected Actor. For example, the number of input and output parameters, their data types and default values. To hide the Properties window, click anywhere in the empty Stage area. 

**Example of Actor's Properties Window**

![image](images/99_18_04_properties.PNG)

[Click for more information about the Actor Properties window](03_broadway_actor_window.md#broaway-actors-properties-window).

Note that if the [link between two Actors](07_broadway_flow_linking_actors.md) is selected, the window's layout is adjusted to display the names of the From and To Actors, their parameters and the Link type.


[![Previous](/articles/images/Previous.png)](17_tutorial_and_flow_examples.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](19_broadway_flow_stages.md)
