# Broadway Flow Window

The **Broadway flow** window is the main Broadway interface that enables the definition and execution of business processes including [Stages](/articles/99_Broadway/19_broadway_flow_stages.md), [Actors](/articles/99_Broadway/03_broadway_actor.md) and [links between the Actors](<!--Link to 21-Links-->). 

To create a new Broadway flow:
1. Go to **Project Tree** > **Shared Objects**, right click **Broadway** > **New Flow** to open the Flow Name window.
2. Populate the **flow name** and click **OK** to open an empty Flow area.

![image](/articles/99_Broadway/images/99_18_01_main_flow_area.PNG)

The **Broadway flow** window includes the following elements:

- The flow area, which displays Stages and their Actors. 
  - To [add a Stage](/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage), either click the big **+**, or go to the [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu) > **Insert After** or **Insert Before**. 
  - To [add Actors to a Stage](/articles/99_Broadway/03_broadway_actor.md#how-do-i-add-actor-to-stage), click in the empty Stage area.
- [Main menu](/articles/99_Broadway/18_broadway_flow_window.md#main-menu), which enables running the flow and executing additional actions like **Save as Actor**.
- [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu), which provides Stage activities like **Merge** or **Delete**.
- [Actor context menu](/articles/99_Broadway/18_broadway_flow_window.md#actor-context-menu), which provides Actor activities like **Add**, **Link** or **Export Actor**. 
- [Actor Properties window](/articles/99_Broadway/18_broadway_flow_window.md#actor-properties-window), which enables setting and editing the properties of a selected object. 

### Main Menu

The Main menu is a toolbar located at the top of the window. It has the following options:

![image](/articles/99_Broadway/images/99_18_01_main_menu.PNG)

<table style="width: 900px;">
<tbody>
<tr>
<td width="170pxl">Actions</td>
<td width="630pxl">
<li><a href="/articles/99_Broadway/17_tutorial_and_flow_examples.md">Examples</a> - open the tutorial or an example flow.</li>
<li><a href="/articles/99_Broadway/22_xxxx.md">Save as Actor (add link!)</a>, save the current flow as an Actor and use its logic as an inner flow in another Broadway flow.</li>
<li>Debug/Run Arguments, if the flow's population type is External, click to open the popup window to define the input arguments for its execution.</li>
<li>Reset Parameters Schemas, reset these settings to the Actor's original status. Removing the output Schema of complex types erases all lines originating from the Schema.</li>
<li>Flip Scroll-wheel, command the mouse to move the window scrollbar horizontally instead of vertically.</li>
</ul>
</td>
</tr>
<tr>
<td width="200">Run/Stop Flow</td>
<td style="width: 465px;">
<p>Run the flow or stop the run.</p>
</td>
</tr>
<tr>
<td width="200">Debug Play/Step/Stop</td>
<td style="width: 465px;">
<p>Run the flow in Debug mode.</p>
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

### Stage Context Menu

To open the Stage context menu, click ![image](/articles/99_Broadway/images/99_19_dots.PNG) in the right corner of the Stage. This menu is dynamic, whereby some items are displayed only when they are applicable to the selected Stage. 

<table style="width: 900px;">
<tbody>
<tr>
<td rowspan="12" width="400pxl">
<p><img src="/articles/99_Broadway/images/99_18_02_stage_menu_up.PNG" alt="Stage context menu" /></p>
</td>
<td width="80pxl"><a href="/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage ">Insert After</a></td>
<td width="420pxl">Add a new Stage after the selected one.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage ">Insert Before</a></td>
<td style="width: 465px;">Add a new Stage before the selected one.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage "> Delete</a></td>
<td style="width: 465px;">Delete the selected Stage and its dependent branch.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-split-or-merge-the-stages"> Split</a></td>
<td style="width: 465px;">Split the selected Stage.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-split-or-merge-the-stages"> Merge</a></td>
<td style="width: 465px;">Merge the selected Stage.</td>
</tr>
<tr>
<td width="200">[Iterate Close]</td>
<td style="width: 465px;">Close the iteration scope. The Iteration scope starts where the Iterate line type originates in the Stage.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages">Stage Condition</a></td>
<td style="width: 465px;">Click to open a popup window and select an Actor for the Stage. The Actor will serve as a stage condition. Any Actor can serve as a condition. Note that Stage condition Actors are grey.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages">Else</a></td>
<td style="width: 465px;">Click to mark the Stage to be executed if none of the conditions on the same level are true.</td>
</tr>
<tr>
<td width="200">[Error Handler]</td>
<td style="width: 465px;">Click to open a popup window and select the Stage's Error Handler Actor. Note that Error Handler Actors are red.</td>
</tr>
<tr>
<td width="200">Transaction</td>
<td style="width: 465px;">Click to open or close the transaction.</td>
</tr>
<tr>
<td width="200">Breakpoint</td>
<td style="width: 465px;">Click to set a breakpoint for the Stage. A breakpoint can be also set by clicking the left of the Stage title.</td>
</tr>
<tr>
<td width="200">Remark</td>
<td style="width: 465px;">Click to add a remark to the Stage. If the remark already exists, a <img src="/articles/99_Broadway/images/99_17_green_ast.PNG" alt="green asterisk" /> is displayed on the left of the three dots and the Remark popup is open displaying its text.&nbsp;</td>
</tr>
</tbody>
</table>


### Actor Context Menu

To open the Actor's context menu, click ![image](/articles/99_Broadway/images/99_19_dots.PNG) in the right corner of the Actor. This menu is dynamic, whereby some items are displayed only when they are applicable to the selected Actor.

<table style="width: 900px;">
<tbody>
<tr>
<td rowspan="8" width="400pxl">
<p><img src="/articles/99_Broadway/images/99_18_03_actor_menu_up.png" alt="Actor's context menu" /></p>
</td>
<td width="80pxl"><a href="/articles/99_Broadway/21_broadway_flow_linking_actors.md#how-do-i-add-links-to-the-flow">Link</a></td>
<td width="420pxl">Click to connect the selected Actor and the required target Actor.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/03_broadway_actor.md#how-do-i-add-actor-to-stage">Add Actor</a></td>
<td style="width: 465px;">Click to open a popup window and select an Actor for the Stage.&nbsp;</td>
</tr>
<tr>
<td width="200">[Export Actor]</td>
<td style="width: 465px;">Click to save an Actor to inherit the selected Actor. The Actor is then added to the list of <a href="/articles/99_Broadway/04_built_in_actor_types.md">built-in Actors</a> and can be used in other flows.</td>
</tr>
<tr>
<td width="200">Delete</td>
<td style="width: 465px;">Click to delete the selected Actor.&nbsp;</td>
</tr>
<tr>
<td width="200">Collapse / Expand</td>
<td style="width: 465px;">Collapse or expand the Actor and display its title.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/03_broadway_actor.md#actor-description-and-remark"> Description</a></td>
<td style="width: 465px;">Product Actor's description.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/03_broadway_actor.md#actor-description-and-remark"> Remark</a></td>
<td style="width: 465px;">Additional info added to the Actor instance.</td>
</tr>
<tr>
<td width="200">Show Only Connected</td>
<td style="width: 465px;">Mark to display the selected Actor and its connections.</td>
</tr>
</tbody>
</table>

### Actor Properties Window

The Actor's Properties in the Broadway flow window dynamically adjusts its layout to display data based on the structure of the selected Actor. For example, the number of input and output parameters, their data types and default values. To hide the Properties window, click anywhere in the empty Stage area. 

**Example of Actor's Properties Window**

![image](/articles/99_Broadway/images/99_18_04_properties.PNG)

[Click for more information about Actor Properties window](/articles/99_Broadway/03_broadway_actor_window.md#broaway-actors-properties-window).

Note that if the link between two Actors is selected, the window's layout is adjusted to display the names of the [From and To Actors, their parameters and the Link type](/articles/99_Broadway/20_broadway_flow_linking_actors.md#link-object-properties).


[![Previous](/articles/images/Previous.png)](/articles/99_Broadway/17_tutorial_and_flow_examples.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/19_broadway_flow_stages.md)
