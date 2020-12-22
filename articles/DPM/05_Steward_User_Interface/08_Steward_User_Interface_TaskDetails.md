## Task Details

The Task Details screen is where the Steward can view the task details, add notes and mark a task as complete. 

 ![image](/articles/DPM/images/Figure_45_Task_Details.png)

In this screen, the Steward can add notes to the Task, and if the Task is assigned to him, then he can use the “mark as complete” to complete the Task. If the Task is unassigned, he can also use the “Get” button on the top of the screen to take ownership on the Task.  

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
<p>The Regulation that this request was created for.</p>
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
<p>Indicates the user that this Task is assigned to. Filled only for assigned Tasks.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Task Completed On</p>
</td>
<td width="800">
<p>The date and time that the Task was completed. Filled only for a Task that was already completed.</p>
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

The actions that can be done in this screen: 

- Add a Note - The Steward and Case Owner can both add notes to a Task.  To add a note, type the text in the textbox next to the button, and then click the “Add Note” to submit it. The notes appear at the textbox just below the “Add Note” line, in chronological order.

- Get or Release a Task – use the    ![image](images/Figure_45_a_release_get_icon.png) buttons to either “Get” a Task or “Release a Task. Getting a Task is enabled only if this Task is not yet assigned to another user. Releasing a Task is only enabled if the Task is assigned to the user currently viewing the screen. Both buttons are disabled if the Task is not expecting to be executed (for example, the Task was already completed, waived or not yet ready for execution since the Flow had not yet reached the point where it should be executed).

- Mark a Task as Complete: if the Task is assigned to this user, then this user can mark it as Complete by using the  ![image](/articles/DPM/images/Figure_45_b_mark_as_complete_icon.png)  button. Naturally a Task should be marked as complete after the user had performed the action that is expected in this Task. 

- Close the screen – the button ![image](/articles/DPM/images/Figure_45_c_close_icon.png)  will close the screen of the Task and present to the user again the Task List screen.

Information about the overall Flow of the Request is provided to the user in the progress bar: 

![image](/articles/DPM/images/Figure_45_d_Flow_detail.png)

In this bar, the Stages of the Flow that were already completed appear in green, the current Stage of the Request appears in blue if it is on track and in red if it is past due. The Stage that the specific Task you are looking at makes part, is marked with two black lines. 

In the example above, the Stages of “Case Opening”, “Data Collection” and “Data Review” were already completed. The Flow is currently at one of the Tasks that comprise the “Review and Approve” Stage. The Task which screen the user is viewing makes part of the “Data Review” Stage.

[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/05_Steward_User_Interface/07_Steward_User_Interface_Execution.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/05_Steward_User_Interface/README.md)
