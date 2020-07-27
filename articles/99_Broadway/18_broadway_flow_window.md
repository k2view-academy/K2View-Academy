# Broadway Flow Window

The **Broadway Flow** window is the main Broadway interface and is where business processes including [Stages](/articles/99_Broadway/19_broadway_flow_stages.md), [Actors](/articles/99_Broadway/03_broadway_actor.md) and [links between the Actors](<!--Link to 21-Links-->) are defined and executed. 

To create a new Broadway flow:
1. Go to **Project Tree** > **Shared Objects**, right click **Broadway** > **New Flow** to open the Flow Name window.
2. Populate the **flow name** and click **OK** to open an empty Flow area.

![image](/articles/99_Broadway/images/99_18_01_main_flow_area.PNG)

The **Broadway Flow** window includes the following elements:

- Main flow area where Stages are added. To add a Stage, either click the big **+** in the flow area, or go to the [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu) and select **Insert After** or **Insert Before**. To add Actors to a Stage, click in the empty Stage area.
- [Main menu](/articles/99_Broadway/18_broadway_flow_window.md#main-menu), which enables running the flow and executing additional actions like **Save as Actor**.
- [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu), which provides Stage activities like **Merge** or **Delete**.
- [Actor context menu](/articles/99_Broadway/18_broadway_flow_window.md#actor-context-menu), which provides Actor activities like **Add**, **Link** or **Export Actor**. 
- [Properties tab](/articles/99_Broadway/18_broadway_flow_window.md#properties-tab), which enables setting and editing the properties of a selected object. 

### Main Menu

The Main menu is at the top of the window and has the following options:

![image](/articles/99_Broadway/images/99_18_01_main_menu.PNG)

<table style="width: 900px;">
<tbody>
<tr>
<td width="170pxl">Actions</td>
<td width="630pxl">
<p>When clicked displays the following options:</p>Tutorial or an example flow.</li>
<li><a href="/articles/99_Broadway/23_xxxx.md">[Save as Actor]</a>, save the current flow as an Actor and use its logic as an inner flow in another Broadway flow.</li>
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

To open the Stage context menu, click the three dots in the right corner of the Stage. This menu is dynamic, whereby some items are displayed only when they are applicable to the selected Stage. 

<table style="width: 900px;">
<tbody>
<tr>
<td rowspan="12" width="400pxl">
<p><img src="/articles/99_Broadway/images/99_18_02_stage_menu.png" alt="Stage context menu" /></td>
<td width="80pxl">Paste Selection</td>
<td width="420pxl">Paste the copied selection, for example an Actor from another Stage.</td>
</tr>
<tr>
<td width="200">Collapse</td>
<td style="width: 465px;">Collapse the Stage.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage "> Insert After / Insert Before</a></td>
<td style="width: 465px;">Add a new Stage after or before the selected one.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-add-or-delete-a-stage "> Delete</a></td>
<td style="width: 465px;">Delete the selected Stage and its dependent branch.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-split-or-merge-the-stages"> Split / Merge</a></td>
<td style="width: 465px;">Split or Merge the selected Stage.</td>
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
<td style="width: 465px;">Click to add a remark to the Stage. If the remark already exists, a green asterisk is displayed on the left of the three dots and the Remark popup is open displaying its text.&nbsp;</td>
</tr>
</tbody>
</table>

### Actor's Context Menu

To open the Actor's context menu, click the three dots in the right corner of the Actor. This menu is dynamic, whereby some items are displayed only when they are applicable to the selected Stage.

<table style="width: 900px;">
<tbody>
<tr>
<td rowspan="9" width="400pxl">
<p><img src="/articles/99_Broadway/images/99_18_03_actor_menu.png" alt="Actor's context menu" /></p></td>
<td width="80pxl">Collapse / Expand</td>
<td width="420pxl">Collapse or expand the Actor and display its title.</td>
</tr>
<tr>
<td width="200">Show Only Connected</td>
<td style="width: 465px;">Mark to display the selected Actor and its connections.</td>
</tr>
<tr>
<td width="200">Copy Selection</td>
<td style="width: 465px;">Copy the selected Actor.</td>
</tr>
<tr>
<td width="200">Delete Actor</td>
<td style="width: 465px;">Click to delete the selected Actor.&nbsp;</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/21_broadway_flow_linking_actors.md#how-do-i-add-links-to-the-flow"> Link</a></td>
<td style="width: 465px;">Click to connect the selected Actor and the required target Actor.&nbsp;&nbsp;</td>
</tr>
<tr>
<td width="200">Add Actor Below</td>
<td style="width: 465px;">Click to open a popup window and select an Actor for the Stage. The Actor is added under the selected Actor.</td>
</tr>
<tr>
<td width="200">[Export Actor]</td>
<td style="width: 465px;">Click to save an Actor to inherit the selected Actor. The Actor is then added to the list of <a href="/articles/99_Broadway/04_built_in_actor_types.md">built-in Actors</a> and can be used in other flows.</td>
</tr>
<tr>
<td width="200">Description</td>
<td style="width: 465px;">Product Actor's description.</td>
</tr>
<tr>
<td width="200">Remark</td>
<td style="width: 465px;">Additional info added to the Actor instance.&nbsp;</td>
</tr>
</tbody>
</table>

### Properties Tab

The Properties tab in the Flow window dynamically adjusts its layout to display data based on the Actor's structure. For example, the number of [input and output parameters](/articles/99_Broadway/03_broadway_actor.md#data-input-parameters), their data types and default values. The tab can also display the properties of a connection between two Actors. When the link is selected, it displays the names of the From and To Actors and their parameters and the [Link type](<!--Link to 21-Links-->).
To hide the Properties tab, click anywhere in the empty Stage area. 

**Example of Actor's Properties Tab**

![image](/articles/99_Broadway/images/99_18_04_properties.PNG)

[![Previous](/articles/images/Previous.png)](/articles/99_Broadway/17_tutorial_and_flow_examples.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/19_broadway_flow_stages.md)
