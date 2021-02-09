# Flows

A Flow is an entity that defines the sequence of actions to be executed in order to fulfill a specific DPM request type. For example, the actions to fulfil the request to “get my data” or “forget me”. 
Flow is the main component in the DPM request definition and can be viewed as the “brain” of a DPM request. 

A Flow is composed of <i>Stages</i>, and Stages include one or multiple tasks. 

Once a Flow’s configuration is complete, it can be linked to a DPM Activity. From that point on, any time a customer request for this DPM Activity is processed, the stages and tasks defined in this Flow are followed.  

The Admin can view the list of existing flows by selecting the “Flows List” menu option at the left frame of the application screen. 
The right frame displays the details for each listed flow and allows the Admin to search for a specific Flow, review any particular flow, and create a new Flow.

## Flows List

The list of existing Flows provides the high-level information about each of the configured Flows. 

 ![image](/articles/DPM/images/Figure_4_Flows_List_screen.png)

The preceding figure is an example of listed flows. Information about each Flow includes:  

- Name: The Flow name
- Description: Identifies the purpose of this Flow
- Version: A Flow may have more than one version. All versions are presented.
- Status: A Flow is created as a <i>Draft</i>, and then changed into <i>Completed</i>. Only Draft Flows can be altered. Completed Flows cannot be changed and are  assigned to Activities.
- Regulation/Activity: List of all the Activities that use this Flow.
- Actions: Provide the option to delete a Flow. 

The following sections provide additional information about the options and information in the “Flows List” screen. 

### View Flow Details

View the details of a specific Flow by clicking the line of information displayed.

### Delete a Flow
 
Delete a Flow by selecting the "Delete" button under the <i>Actions</i> column. Only Flows not linked to an Activity can be deleted.

### Flow Status

Flow Status - When a new Flow is created, its status is defined as <i>Draft</i>. In this status, the DPM Administrator can perform any configuration on this Flow; such as creating new stages, adding new tasks, and more.  
Once the Administrator completes the configuration, the Flow can be marked as <i>Completed</i>. Marking the Flow as Completed blocks further changes to the Flow. At this point, this Flow can be linked to a DPM Activity for execution as part of a DPM fulfillment process. The same Flow can be linked to more than one DPM Activity. 

### Flow Version

Any Flow can have multiple versions. A new version should be created when a change to an existing Completed Flow is required. The version number is determined by the Administrator when the new Flow version is created. The Administrator can accept the system’s version or modify the version as desired.  Creating a new version of a Flow is described in the section “Flow Details”.

### Create New Flow

The Administrator creates a new Flow by selecting the “Add a Flow” button at the top-right corner of the screen. 
The Administrator is then prompted to define the Flow name, version, and description, as shown by the next image and defined by the subsequent table.

 ![image](/articles/DPM/images/Figure_5_New_Flow.png)

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
<p>Flow Name</p>
</td>
<td width="30">
<p>M</p>
</td>
<td width="785">
<p>The name of this Flow</p>
</td>
</tr>
<tr>
<td width="85">
<p>Flow Version</p>
</td>
<td width="30">
<p>O</p>
</td>
<td width="785">
<p>[Optional] The Flow version can be specified by the user or is automatically assigned by the system. The logic for the automatic version number allocation is as follows:</p>
<p>&middot;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; If there is no other Flow with the same name, the system will automatically set it to 1.</p>
<p>&middot;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; If there is a Flow with the same name, the system will set the Flow version of the new Flow to one higher than the version number of the Flow with the same name.</p>
<p>The system will not allow the creation of two Flows with the same name and the same version.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Flow Description</p>
</td>
<td width="30">
<p>O</p>
</td>
<td width="785">
<p>[Optional] Description of the Flow&rsquo;s purpose.</p>
</td>
</tr>
</tbody>
</table>
 
Once the Flow information is defined and the new Flow is saved, the system displays the "Flow Editing screen." The Administrator can add Stages and Tasks under those Stages. 
The Flow name and the Flow description can be updated at anytime by using the edit (pencil) icon next to the Flow name.

 ![image](/articles/DPM/images/Figure_6_Configuration_of_a_New_Flow_Initial_screen.png)

## Flow Level Actions

The set of icons to the right of the Flow name are actions for that Flow. Each icon and its corresponding description are provided by the following table. 

 ![image](/articles/DPM/images/Figure_7_Flow_status_icons.png)

<table>
<tbody>
<tr>
<td width="100">
<p><strong>Icon</strong></p>
</td>
<td width="800">
<p><strong>Description</strong></p>
<u><strong>Property</strong></u>
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
<p>Indicates the total number of hours to process the Flow, according to the deadline information of all its Tasks. The unit of time measure can be configured as hours or minutes.  Note that changing the configuration from Hours to Minutes alters the expected duration of the Flow, and not only the units. For example: A Task with the deadline configured in hours and noted as 1 , the task is understood to take one hour to complete.  When the configuration is set to &ldquo;Minutes&ldquo; the task is set with deadline of one minute duration. The option of &ldquo;Minutes&rdquo; is usually utilized for testing purposes, when the tester wishes to have the Flows executed faster.</p>
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

 ![image](/articles/DPM/images/Figure_10_New_Flow_initial_state.png)

While the Flow is not marked <i>Completed</i>, Stages can be added, or updated by added or modified Tasks. 


[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/02_DPM_Configuration.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/04_Stages.md)
