# Broadway Flow Window

**Broadway Flow** window is the main Broadway UI component that enables the definition and execution of business processes  including all the [Stages](<!--Link to 18-Flow Stages-->), [Actors](/articles/99_Broadway/04_built_in_actor_types.md) and the [links between the Actors](<!--Link to 21-Links-->). 

The Broadway flow window includes the following elements:

- [Main menu](/articles/99_Broadway/18_broadway_flow_window.md#main-menu), which enables to run the flow and to execute additional actions
- [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu), which provides Stage activities, such as Split, Merge or Delete.
- [Actor context menu](/articles/99_Broadway/18_broadway_flow_window.md#actor-context-menu), which provides Actor activities, such as Add, Link or Export Actor. 
- [Properties tab](/articles/99_Broadway/18_broadway_flow_window.md#properties-tab), which allows to set and edit the properties of the selected object. 

### Main Menu

The Main menu of the Broadway flow is located on top of the window and allows to perform the following activities:

![image](/articles/99_Broadway/images/99_18_01_main_menu.PNG)

<table style="width: 900px;">
<tbody>
<tr>
<td width="170pxl">Actions</td>
<td width="630pxl">
<p>Drop-down menu, to perfom the actions:</p>
<ul>
<li><a href="/articles/99_Broadway/17_tutorial_and_flow_examples.md">Examples</a> - open the tutorial or an example flow.</li>
<li><a href="/articles/99_Broadway/23_xxxx.md">[Save as Actor]</a> - save the current flow as an Actor, to enable logic re-use.</li>
<li><a href="/articles/99_Broadway/26_xxxx.md">[Debug/Run Arguments]</a></li>
<li>Reset Parameters Schemas - reset Actor parameter schemas to the Actor original state. Removing the output schema for complex types will arease all the lines originating from the schema.</li>
<li>Flip Scroll-wheel - command to the mouse scroll wheel to move the window scroll bar horizontally instead of vertically.</li>
</ul>
</td>
</tr>
<tr>
<td width="200">Run/Stop Flow</td>
<td style="width: 465px;">
<p>Run the flow or to stop the run.</p>
</td>
</tr>
<tr>
<td width="200">Debug Play/Step/Stop</td>
<td style="width: 465px;">
<p>Run the flow in a debug mode.</p>
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
<p>Click to undo or redo the latest activity.</p>
</td>
</tr>
<tr>
<td width="200">Remarks</td>
<td style="width: 465px;">
<p>Click to display all the flow remarks.</p>
</td>
</tr>
</tbody>
</table>

### Stage Context Menu

To open the Stage context menu, click the three dots in the right corner of the Stage. This menu is dynamic, whereby some of the items are displayed only when they are applicable to the selected Stage. 

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
<td width="200">Insert After / Insert Before</td>
<td style="width: 465px;">Add a new Stage after or before the selected one.</td>
</tr>
<tr>
<td width="200">Delete</td>
<td style="width: 465px;">Delete the selected Stage.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages">Split / Merge</a></td>
<td style="width: 465px;">Split or Merge the selected Stage.</td>
</tr>
<tr>
<td width="200">[Iterate Close]</td>
<td style="width: 465px;">Close the iteration scope.&nbsp;The iteration scope starts at the stage where the Iterate line type originates from.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages">Stage Condition</a></td>
<td style="width: 465px;">Click to open a popup window to select an Actor to the Stage. The Actor will serve as a stage condition. Any Actor can serve as a condition. Note that Stage condition Actors are colored in grey.</td>
</tr>
<tr>
<td width="200"><a href="/articles/99_Broadway/19_broadway_flow_stages">Else</a></td>
<td style="width: 465px;">Click to mark the Stage to be executed if none of the conditions on the same level are true.</td>
</tr>
<tr>
<td width="200">[Error Handler]</td>
<td style="width: 465px;">Click to open a popup window to select an Actor to the Stage. The Actor will serve as Error Handler. Note that Error Handler Actors are colored in red.</td>
</tr>
<tr>
<td width="200">Transaction</td>
<td style="width: 465px;">Click to open or close the transaction.</td>
</tr>
<tr>
<td width="200">Breakpoint</td>
<td style="width: 465px;">Click to set the breakpoint on the Stage. The breakpoint can be also set by clicking on the left of the Stage title.</td>
</tr>
<tr>
<td width="200">Remark</td>
<td style="width: 465px;">Click to add a remark to the Stage. If the remark already exists, the green asterisk will be displayed on the left of the three dots and the Remark pop-up will be open with its text.&nbsp;</td>
</tr>
</tbody>
</table>

### Actor Context Menu

To open the Actor's context menu, click the three dots in the right corner of the Actor. 

<table style="width: 900px;">
<tbody>
<tr>
<td rowspan="9" width="400pxl">
<p><img src="/articles/99_Broadway/images/99_18_03_actor_menu.png" alt="Actor's context menu" /></p></td>
<td width="80pxl">Collapse / Expand</td>
<td width="420pxl">Collapse or Expand the Actor, keeping visible only the Actor's title.</td>
</tr>
<tr>
<td width="200">Show Only Connected</td>
<td style="width: 465px;">Mark to display only the selected Actor with its connections.</td>
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
<td width="200">[Link]</td>
<td style="width: 465px;">Click to connect the selected Actor with the required target Actor.&nbsp;&nbsp;</td>
</tr>
<tr>
<td width="200">Add Actor Below</td>
<td style="width: 465px;">Click to open a popup window to select an Actor to the Stage. The Actor will be added below the selected Actor.</td>
</tr>
<tr>
<td width="200">[Export Actor]</td>
<td style="width: 465px;">Click to save an Actor (as inheritance of the selected Actor).&nbsp; Then the Actor will be added to the list of <a href="/articles/99_Broadway/04_built_in_actor_types.md">built-in Actors</a> and can be re-used in other flows.</td>
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

The Broadway flow Properties tab  dynamically adjusts itself to display the data based on the Actor's structure, such as the number of [input and output parameters](<!--Link to 5-Input-Output types-->), their types and default values if exist. It can also display the properties of the connection between two Actors. When the link is selected, it will show the names of From and To Actors and parameters, as well as the [Link type](<!--Link to 21-Links-->)
If the Properties tab is displayed and the user clicks anywhere on the empty Stage area, the Properties tab will disappear. 

**Example of Actor's Properties tab**

![image](/articles/99_Broadway/images/99_18_04_properties.PNG)
