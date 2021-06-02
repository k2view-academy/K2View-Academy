# Stages

The Stages comprising a Flow are shown in the bar below the Flow header. An example follows.  

 ![image](/articles/DPM/images/Figure_11_Flow_Stages.png)

- Use the + buttons to add a Stage
- Use the x buttons to delete a Stage

The duration of each Stage is marked by the yellow tag at the top of the Stage arrow. This duration is based on the SLAs of the tasks within the Stage. The duration takes into consideration the order and dependencies of the tasks included in each Stage.
The Stage name and description can be changed as long as the Flow is not marked as Completed. Edit this information by using the   ![image](/articles/DPM/images/Figure_11a_edit_stage_icon.png) button, located at the right side of the Stage header section. 

## Add a Stage

Use the <b>New Stage</b> ![image](/articles/DPM/images/Figure_8a_plus_icon.png) icon on the left side of the Flow screen to add a new Stage. The user is prompted to define a <b>Stage Name</b> and <b>Stage Description</b>, as shown in the following figure and description table.

 <img src="../images/Figure_9_Adding_a_new_Stage.png" width="30%" height="30%">


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

Save the new Stage to open the following screen and define the Stage Tasks. 

 ![image](/articles/DPM/images/Figure_9_Flow_with_a_new_stage_screen.png)

While the <b>Status</b> is marked as a <b>Draft</b> (not <b>Completed</b>), additional Stages can be added or updated by adding or modifying Tasks. 


## Add/Edit a Task

A Stage is composed of one or more Tasks. Each Task performs a specific action in the Customer Request Fulfilment process. Examples of Tasks are provided next.

- Validates the customer’s request.
- Send an e-mail  to the customer acknowledging registration of the request.
- Gather the requested customer data.
- Review the gathered data.

To add a new Task under a specific Stage, click the Stage name on the Stage bar and access the  ![image](/articles/DPM/images/Figure_12a_new_task_icon.png) option from the right. 
The Task Configuration screen is displayed. This screen includes several tabs, each providing an aspect of Task configuration.

![image](/articles/DPM/images/Figure_12_Add_Edit_a_Task_screen.png)

The following tabs constitute the Task Configuration screen:
- Tasks
- Reminders
- Operations, and 
- Associated Tasks. 

Tasks can be divided into two primary categories: Manually executed Tasks, and Tasks configured to run automatically. An automatic Task is performed by the DPM system, while a manual Task is allocated to the Steward role and teams. 

The following sections describe the properties that should be configured when defining a Task, and the different configurations of automatic and manual tasks. 



[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/03_Flows.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/05_Tasks.md)
