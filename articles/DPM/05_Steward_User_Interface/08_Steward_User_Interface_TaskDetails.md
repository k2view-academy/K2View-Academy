# Task Details

The Task Details screen provides the Steward a view of the task details, from which notes can be added or the task can be marked “Complete”.  

 ![image](/articles/DPM/images/Figure_45_Task_Details.png)

If the Task is unassigned, the Data Steward can also use the “Get” button on the top of the screen to take ownership on the Task.  

<table>
<tbody>
<tr>
<td width="100">
<p><strong>Field</strong></p>
</td>
<td width="800">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="100">
<p>Request ID</p>
</td>
<td width="800">
<p>The unique identification of the request.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Regulation</p>
</td>
<td width="800">
<p>The Regulation for which this request was created.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Activity</p>
</td>
<td width="800">
<p>The Activity that this Request executes.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Task Status</p>
</td>
<td width="800">
<p>The Task status.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Assigned To</p>
</td>
<td width="800">
<p>Indicates the user assigned to this Task. Filled only for assigned Tasks.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Task Completed On</p>
</td>
<td width="800">
<p>The date and time the Task was completed. Filled only for a Task that was already completed.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Task Name</p>
</td>
<td width="800">
<p>The name of this Task.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Task Description</p>
</td>
<td width="800">
<p>The Description of the Task.</p>
</td>
</tr>
</tbody>
</table>


The actions available from this screen include: 

- Add a Note - The Steward and Case Owner can each add notes to a Task.  To add a note, type text in the text box next to the button, then click “Add Note” to submit it. The notes appear chronologically at the text box, just below the “Add Note” line.

- Get or Release a Task – use the    ![image](/articles/DPM/images/Figure_45_a_release_get_icon.png) buttons to either “Get” a Task or “Release a Task. Getting a Task is enabled only when this Task is not yet assigned to another user. Releasing a Task is only enabled when the Task is assigned to the user currently viewing the screen. Both buttons are disabled when the Task is not to be executed (for example, the Task was already completed, waived or not yet ready for execution, since the Flow had not reached the point of execution) .

- Mark a Task as Complete – if the  Task is assigned to this user, the Task can be marked as Complete using the ![image](/articles/DPM/images/Figure_45_b_mark_as_complete_icon.png)  button. Naturally, a Task should be marked as complete after the user had performed the expected action for this Task. 

- Close the screen – the button ![image](/articles/DPM/images/Figure_45_c_close_icon.png) button removes the Task screen and returns to the Task List screen.

Information about the Flow of the Request is provided to the user through the progress bar. An example of the progress bar follows: 

![image](/articles/DPM/images/Figure_45_d_Flow_detail.png)

The progress bar displays the Flow Stage; several stages are represented by the same fill color; Completed Stage appears in green, a Processing Stage appears in blue, and an Overdue Stage appears in red.  
For the preceding example, the Stage of “Case Opening” appears in green, denoting completed. The stage “Data Collection” appears in blue, denoting it is the stage currently in progress. The Stages in gray are those not yet started.

[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/05_Steward_User_Interface/07_Steward_User_Interface_Execution.md)
