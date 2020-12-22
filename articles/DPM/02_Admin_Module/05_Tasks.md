## “Tasks” tab

The Tasks tab includes the configuration of parameters, such as the following:

- Task Name
- Task owner
- Define if the Task is mandatory
- Tasks execution order
- Dependencies on other Tasks
- Task expected execution time
- Task execution timing

 ![image](images/Figure_13_New_Task_first_tab.png)

The following table describes in detail the options in this tab:

<table>
<tbody>
<tr>
<td width="90">
<p><strong>Property</strong></p>
</td>
<td width="35">
<p><strong>M/O</strong></p>
</td>
<td width="775">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="90">
<p>Task Name</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="775">
<p>The Task name is used to identify the Task anywhere it will appear in the system.</p>
</td>
</tr>
<tr>
<td width="90">
<p>Role</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="775">
<p>The role that this Task should be assigned to. For manual Tasks, this is the Role that represents the Data Stewards&rsquo; group that should handle it. For automatic Tasks, the DPM system will inform an assigned role if the process fails or if the deadline of the Task was missed.</p>
</td>
</tr>
<tr>
<td width="90">
<p>Mandatory</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="775">
<p>Marking a Task as mandatory means that the flow will not move forward until this Task is completed.</p>
<p>&nbsp;</p>
<p>If the Mandatory property is not marked, then the flow will move forward when the deadline is reached, even if the Task has not been completed.&nbsp; In this case, the system will mark the Task as &ldquo;waived&rdquo;.&nbsp; If there are no further Tasks in the Request, the Request will be marked as &ldquo;completed&rdquo;.</p>
</td>
</tr>
<tr>
<td width="90">
<p>Task Start Time</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="775">
<p>This property allows for defining whether the task should be initiated immediately after the predecessor task is completed, or to initiate it only after a configurable time lag.</p>
<p>The time lag configuration:</p>
<p> ![image](images/Figure_14_task_schedule.png)</p>
<p>&nbsp;</p>
<p>The configuration depends on the selected time unit:</p>
<p>If &ldquo;hour&rdquo; was selected, then the user should define at what minute the task should start. Once the preceding task was completed, then this task will start in the defined minute of the following hour.</p>
<p>If &ldquo;day&rdquo; was selected then the hour of the day should be defined.</p>
<p>The configuration of other time unit choices of &ldquo;week&rdquo;, &ldquo;month&rdquo; and &ldquo;year&rdquo; follow the same logic.</p>
</td>
</tr>
<tr>
<td width="90">
<p>Task Order</p>
</td>
<td width="35">
<p>O</p>
</td>
<td width="775">
<p>This defines the order of the task within the Stage it was defined. In order to execute multiple parallel tasks, assign&nbsp; them to the same order number.&nbsp; The default value is 1</p>
</td>
</tr>
<tr>
<td width="90">
<p>Task Deadline</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="775">
<p>The task deadline defines the maximum duration that this task is expected to take. If the task execution exceeds this deadline, then this task is defined as &ldquo;past due&rdquo;.</p>
<p>If a Task reaches its Deadline, a mail will be sent to the e-mail address that is registered for the Role of the task. The configuration of the e-mail used for this purpose is done in the Role Management screen of the Admin module.</p>
</td>
</tr>
<tr>
<td width="90">
<p>Push Succeeding Tasks</p>
</td>
<td width="35">
<p>O</p>
</td>
<td width="775">
<p>This property is related to the overall order definitions of the tasks in the Stage. It is useful when creating a new Task in an already existing Stage and configuring this new Task to be executed before Tasks that were previously defined (&ldquo;push succeeding Tasks&rdquo; should be switched on), or without changing the other tasks (&ldquo;push succeeding Tasks&rdquo; should be switched off).</p>
<p>When a task is defined to be in an order number that was already assigned to another Task, then the &ldquo;push succeeding Tasks&rdquo; property defines whether to push the task that has the same order to be set to after the new task (pushing succeeding tasks as well), or if to only add the new Task to the same order as Tasks that are already in place.&nbsp;&nbsp;</p>
</td>
</tr>
<tr>
<td width="90">
<p>Conditional Dependent Task</p>
</td>
<td width="35">
<p>O</p>
</td>
<td width="775">
<p>When a task is marked as &ldquo;Conditional Dependent Task&rdquo;, it means this task is dependent on a previous task and should be executed only if the task it depends on was not completed within predefined time period. When this property is switched to &ldquo;on&rdquo;, the DPM will prompt the user to define the Task it depends on and after how many hours from the parent Task start execution, should the dependent Task start.</p>
<p>The parent Task can be any other task from the same Stage.</p>
<p>This property is presented only if there are more than one Tasks in the current Stage. When this property is switched &ldquo;on&rdquo;, the property of &ldquo;order&rdquo; and &ldquo;Deadline&rdquo; are not relevant. Instead, the user should populate two additional properties: &ldquo;Conditional Parent Task&rdquo; and: Start Task After X Hours&rdquo; (See details in next section).</p>
</td>
</tr>
</tbody>
</table>

##### Conditional Dependent Task: 

When the “Conditional Dependent Task” is switched on, the Tasks Tab changes to present the properties that should be configured in this case:

 ![image](images/Figure_15_Task_configuration_Task_tab.png)

The additional properties that can be configured when the “Conditional Dependent Task is switched on are:

<table>
<tbody>
<tr>
<td width="85">
<p><strong>Property</strong></p>
</td>
<td width="35">
<p><strong>M/O</strong></p>
</td>
<td width="780">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="85">
<p>Conditional Parent Task</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="780">
<p>This property is presented only when the Conditional Dependent Task is switched on. It defines the parent Task that this current Task depends on. It can be any other task from the same Stage that is earlier in its order than the dependent task.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Start Task After X Hours</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="780">
<p>This property is presented only when the Conditional Dependent Task is switched on. It indicates after how many hours should the dependent Task start executing, after the parent task start time.</p>
</td>
</tr>
</tbody>
</table>

When a Flow that includes a conditional Task is executed, the conditional Task will only be executed if the Task it depends on has been “in progress” for a longer duration than is defined for the conditional Task to start. If the parent Task was completed before the determined duration, then the conditional Task is marked as “skipped” and is not executed.



[![Previous](/articles/images/Previous.png)](/articles/DPM/DPM_User_Guide/02/Admin_Module_04_Stages.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/DPM/DPM_User_Guide/02_Admin_Module/06_Reminders.md)