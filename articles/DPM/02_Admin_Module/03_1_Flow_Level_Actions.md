## Flow Level Actions

The set of icons to the right of the Flow name are actions for that Flow. Each icon and its corresponding description are provided by the following table. 

 ![image](/articles/DPM/images/Figure_7_Flow_icons.png)

<table>
<tbody>
<tr>
<td width="100">
<p><strong>Icon</strong></p>
</td>
<td width="800">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="100"><img src="/articles/DPM/images/Figure_7a_edit_flow_icon.png" alt=""/></td>
<td width="800">
<p><u>Edit this Flow<u/>: Edit Flow Name and Description. The Flow name and the Flow description can be updated as long as the Flow is not marked as <em>Completed</em>.</p>
</td>
</tr>
<tr>
<td width="100"><img src="/articles/DPM/images/Figure_7b_mark_flow_as_complete_icon.png" alt=""/></td>
</td>
<td width="800">
<p><u>Mark as Complete</u>: Once the Flow configuration is completed, use this icon to mark the Flow as Completed. No further changes can be made to a flow that is marked as Completed. The Flow is now available to  be associated with an Activity in the Activity configuration screens.</p>
</td>
</tr>
<tr>
<td width="100"><img src="/articles/DPM/images/Figure_7c_duplicate_this_flow.png" alt=""/></td>
<td width="800">
<p><u>Duplicate this Flow</u>: this icon is used in order to create a new version of the Flow, starting as a clone from a Draft or Completed Flow. The new version is created in Draft status and allows the user to change the details of the Flow in the new version.</p>
</td>
</tr>
<tr>
<td width="100"><img src="/articles/DPM/images/Figure_7d_save_flow_as.png" alt=""/></td>
<td width="800">
<p><u>Save Flow As</u>: Use the “save as” icon to create a new Flow in Draft status, that initially has the same details of the original Flow. The new Flow created is independent from the source Flow.</p>
</td>
</tr>
<tr>
<td width="100"><img src="/articles/DPM/images/Figure_7_export_flow_icon.png" alt=""/></td>
<td width="800">
<p><u>Export Flow</u>: This icon serves in order to export the flow to a file. Use this option when you want to recreate this Flow in another environment, for example, when a Flow that was created in testing environment should be imported at the production environment. Read more about the Export/Import option at the Export and Import Flows section.</p>
</td>
</tr>
</tbody>
</table>

At the upper-right corner of the Flow screen, the system shows general Flow information. 

 ![image](/articles/DPM/images/Figure_8_Flow_information.png)

Each information item is described by the following table. 

<table>
<tbody>
<tr>
<td width="100">
<p><strong>Information</strong></p>
</td>
<td width="800">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="100">
<p>Version</p>
</td>
<td width="800">
<p>The Flow Version.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Status</p>
</td>
<td width="800">
<p>The Flow Status can be &ldquo;Draft&rdquo; or &ldquo;Completed&rdquo;.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Business Days</p>
</td>
<td width="800">
<p>The total Flow duration &ndash; how many business days are required in order to perform all tasks of the Flow. This number is based on the deadlines of the Tasks included in the Flow and is rounded up to present a whole number of days.</p>
</td>
</tr>
<tr>
<td width="100">
<p>(x business Hours)</p>
</td>
<td width="800">
<p>Indicates the total number of hours to process the Flow, according to the deadline information of all its Tasks. The unit of time measure can be configured as hours or minutes.  Note that changing the configuration from Hours to Minutes alters the expected duration of the Flow, and not only the units. For example: A Task with the deadline configured in hours and noted as 1, the task is understood to take one hour to complete.  When the configuration is set to &ldquo;Minutes&ldquo;, the task is set with deadline of one minute duration. The option of &ldquo;Minutes&rdquo; is usually utilized for testing purposes, when the tester wishes to have the Flows executed faster.</p>
</td>
</tr>
</tbody>
</table>


### Add a Stage

Use the New Stage ![image](/articles/DPM/images/Figure_8a_plus_icon.png) icon on the left side of the Flow screen to add a new Stage. As a result, the user is prompted to define a Stage name and description, as shown by the following figure and description table.

 ![image](/articles/DPM/images/Figure_9_Adding_a_new_Stage.png)

<table>
<tbody>
<tr>
<td width="85">
<p><strong>Property</strong></p>
</td>
<td width="30">
<p><strong>M/O</strong></p>
</td>
<td width="785">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="85">
<p>Stage Name</p>
</td>
<td width="30">
<p>M</p>
</td>
<td width="785">
<p>The name of this Stage.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Stage Description</p>
</td>
<td width="30">
<p>O</p>
</td>
<td width="785">
<p>The Stage description.</p>
</td>
</tr>
</tbody>
</table>

Saving the new Stage opens the screen to define tasks in this stage. 

 ![image](/articles/DPM/images/Figure_9_Flow_with_a_new_stage_screen.png)

While the Flow is not marked Completed, additional Stages can be added, or updated by adding or modifying Tasks. 

[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/03_Flows.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/03_2_Flow_Export_Import.md)
