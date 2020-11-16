# Flows, Stages, Tasks

A Flow is an entity that defines the sequence of actions to be executed in order to fulfill a specific DPM request type. For example, the actions to fulfil the request to “get my data” or “forget me”. 
Flow is the main component in the DPM request definition and can be viewed as the “brain” of a DPM request. 

A Flow is composed of Stages, and Stages include one or multiple tasks in them. 

Once a Flow’s configuration is complete, it can be linked to a DPM Activity. From that moment on, any time a customer request for this Activity is processed, the execution will follow the stages and tasks defined in this Flow.  

The Admin can view the list of existing flows by selecting the “Flows List” menu option at the left side of the application screen. 
The screen that is presented allows the Admin to search for a specific Flow, review any of the Flows, and create a new Flow.

## Flows List

The list of existing Flows provides the high-level information about each of the configured Flows. 

 ![image](images/Figure_4_Flows_List_screen.png)

In this screen, the information about each Flow includes: 

- Name: The Flow name.
- Description: describes the purpose of this Flow.
- Version: A Flow may have more than one version. All versions are presented.
- Status: A Flow is created as a Draft, and then changed into Completed. Only Draft Flows can be altered.

Only Completed Flows can be assigned to Activities.

- Regulation/Activity: List of all the Activities that uses this Flow.
- Actions: provide the option to delete a Flow. 

The following sections provide some additional information about the options and data in the “Flows List” screen. 

### View Flow Details

To view the details of a specific Flow, select the table line of the Flow, and the Flow details screen will be presented.

### Delete a Flow

To delete a Flow, click on the delete (x) button under the Action column. Only Flows that are not linked to an Activity can be deleted.

### Flow Status

Flow Status - When a new Flow is created, its status is defined as Draft. In this status, the DPM Administrator can perform any configuration on this Flow, such as creating new stages, adding new tasks, etc.  
Once the Administrator completes the configuration, the Flow is marked as Completed. Marking the Flow as Completed blocks further changes to the Flow. Afterward, this Flow can be linked to a DPM Activity so that it can be executed as part of a DPM fulfillment process. The same Flow can be linked to more than one DPM Activity. 

### Flow Version

Any Flow can have multiple versions. A new version should be created when a change to an existing Completed Flow is required. The version number is determined by the Administrator when the new Flow version is created. Creating a new version of a Flow is described in the sub-chapter about the “Flow Details” screen.

### Create New Flow

The Administrator can create a new Flow by using the “Add a Flow” button on the top-right corner of the screen. 
The Administrator is then prompted to define the Flow name, version, and description:

 ![image](images/Figure_5_New_Flow.png)

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
<p>The name of this Flow.</p>
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
<p>The Flow version field is optional, and the system will assign it automatically if it is not filled in by the user. The logic for the automatic version number allocation is as follows:</p>
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
<p>Description of the Flow&rsquo;s purpose.</p>
</td>
</tr>
</tbody>
</table>

Once the Flow information is defined and the new Flow is saved, the system presents the Flow Editing screen, where the Administrator can add Stages and Tasks under those Stages. 
The Flow name and the Flow description can be updated at any  time, by using the edit icon next to the Flow name.

 ![image](images/Figure_6_Configuration_of_a_new_Flow_initial_screen.png)

## Flow Level Actions

The set of icons next to the Flow name are Activities at Flow level:

 ![image](images/Figure_7_Flow_status_icons.png)

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
<td width="100"> ![image](images/Figure_7a_edit_flow_icon.png)</td>
<td width="800">
<p>Edit this Flow: Edit Flow Name and Description. The Flow name and the Flow description can be updated as long as the Flow is not marked as <em>Completed</em>.</p>
</td>
</tr>
<tr>
<td width="100"> ![image](images/Figure_7b_mark_flow_as_complete_icon.png)
</td>
<td width="800">
<p>Mark as Complete: Once the Flow configuration is complete, use this icon in order to mark the Flow as <em>Completed</em>. No further changes to the Flow are allowed after a Flow was marked as complete, and the Flow is made available to be associated with an Activity in the Activity configuration screens.</p>
</td>
</tr>
<tr>
<td width="100"> ![image](images/Figure_7c_duplicate_this_flow.png)</td>
<td width="800">
<p>Duplicate this Flow: this button is used in order to create a new version of the Flow. The new version is created in <em>Draft</em> status and allows the user to change the details of the Flow in the new version.</p>
</td>
</tr>
<tr>
<td width="100"> ![image](images/Figure_7d_save_flow_as.png)</td>
<td width="800">
<p>Save Flow As: Use the &ldquo;save as&rdquo; button to create a new Flow in <em>Draft</em> status, that initially has the same details of the original Flow. The new Flow that is created as a result is independent from the source Flow.</p>
</td>
</tr>
</tbody>
</table>

At the upper-right corner of the Flow screen, the system shows general Flow information: 

 ![image](images/Figure_8_Flow_information.png)

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
<p>The total Flow duration &ndash; how many business days are required in order to perform all the tasks of the Flow. This number is based on the deadlines of the Tasks included in the Flow and is rounded up to present a whole number of days.</p>
</td>
</tr>
<tr>
<td width="100">
<p>(x business Hours)</p>
</td>
<td width="800">
<p>This indicates the total number of hours that the Flow is expected to take, according to the deadline information of all its Tasks. The unit of the information can be changed by configuration from Hours to Minutes. Note that changing the configuration from Hours to Minutes alters the expected duration of the Flow, and not only the units. For example: a Task with the deadline set to 1 would be considered as 1 hour long when the configuration is &ldquo;Hours&rdquo; and as 1 minute long when the configuration is set to &ldquo;Minutes&rdquo;. The option of &ldquo;Minutes&rdquo; is usually utilized for testing purposes, when the tester would like to have the Flows executed faster.</p>
</td>
</tr>
</tbody>
</table>

### Add a Stage

Use the  ![image](images/Figure_8a_plus_icon.png) button on the left side of the Flow screen in order to add a new Stage. As a result, the user is prompted to define the Stage name and description:

 ![image](images/Figure_9_Adding_a_new_Stage.png)

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
<p>The Stage description</p>
</td>
</tr>
</tbody>
</table>

Saving the new Stage will open the screen to define tasks in this stage. 

 ![image](images/Figure_10_New_Flow_initial_state.png)

As long as the Flow is not marked  Completed, additional Stages can be added, and Tasks under them can be added or modified. 

### Stages Management

The Stages comprising a Flow are shown in the bar below the Flow header: 

 ![image](images/Figure_11_Flow_Stages.png)

- Use the + buttons to add more Stages
- Use the x buttons to delete a Stage

The duration of each Stage is marked in the yellow tag at the top of the Stage arrow. This duration is based on the SLAs of the tasks within the Stage and also takes into consideration their order and dependencies. 
The Stage name and description can be changed as long as the Flow is not marked as Completed. Edit this information by using the   ![image](images/Figure_11a_edit_stage_icon.png) button, located at the right side of the Stage header section. 

### Add/Edit a Task

A Stage is composed of one or more Tasks. Each Task performs a specific action in the Request Fulfilment process, for example: 

- Validate the customer request.
- Send an e-mail to the customer acknowledging the request was registered.
- Gather the required customer data.
- Review the gathered data.

To add a new Task under a specific Stage, select the Stage by clicking the Stage name on the Stage bar and use the  ![image](images/Figure_12a_new_task_icon.png) option that appears on the right side. 
The Task Configuration screen will be shown. This screen includes several tabs, each configuring a different aspect of the Task.

![image](images/Figure_12_Add_Edit_a_Task_screen.png)

The Task configuration includes the following tabs: Tasks, Reminders, Operations, and Associated Tasks. 

Tasks can be divided into two primary categories: Tasks that are designed to be executed manually, and Tasks that are configured to run automatically. An automatic Task is performed by the DPM system, while a manual Task will be allocated to the Stewards and their teams. 

The following sections describe the properties that should be configured when defining a Task, and the different execution configurations of automatic and manual tasks. 

### “Tasks” tab

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

#### “Reminders” Tab:

Reminders are used in order to preemptively call attention to Tasks that might not be progressing at the expected pace. 
In the Reminders tab, the user can define one or more reminders that should be sent after a predetermined time.

 ![image](images/Figure_16_Task_configuration_Reminders_tab.png)

Use the   ![image](images/Figure_16a_plus_minus_icon.png) control in order to add more than one reminder or remove unwanted reminder. 

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
<p>Reminder timing</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="780">
<p>Defines after how many hours after the Task had started a reminder should be sent. The reminder mail is sent only if by that time the task was not yet completed.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Reminder Audience</p>
</td>
<td width="35">
<p>M</p>
</td>
<td width="780">
<p>Defines to whom the reminder should be sent. The reminder can be sent to the email address of either one of the Roles or to one of the users that are defined in the system.</p>
<p>In the dropdown only roles and users that has email configured to them are presented.</p>
</td>
</tr>
</tbody>
</table>

Reminders are always sent to the current mail of the user or Role. This means that if the mail of a user or Role is changed, reminders that are generated by DPM from that point on will be sent to the new address.
The e-mail addresses used by the Reminders can be configured by the Administrator in the Role Management and User Management screens.

In addition to the reminders that are configured in this tab, the system also has an automatic reminder that is sent if the Task reaches its deadline and was not yet completed. This automatic Reminder mail is sent to the mail address of the “Task Owner” (that is configured in the “Task” tab). As this is an automatic e-mail and there is no need to configure a Reminder explicitly for this purpose. 

#### “Operations” Tab:

The Operations tab allows the Admin to add an automated Activity that should be executed to perform the Task. 

The user selects an Operation from the operation dropdown list. As a result, the list of parameters that should be defined for the selected Operation are dynamically presented in the “inputs” section of the tab. Some operations may not need any parameters. 

As an example, for a Task that should execute the Operation “SendMail” the system expects the parameters “Message”, “Subject” and “to” to be configured.

 ![image](images/Figure_17_Task_configuration_Operation_tab.png)

Configuring operation’s parameters: 
When an operation requires the configuration of one or more parameters, the value of those parameters can be defined in several different ways. Each parameter can be one of the following types: 

- Value
- Link
- Input

 ![image](images/Figure_18_Operation_parameters_types.png)

Each of those types allows the user to define a different way to obtain the information that is needed for the execution of the operation. The following table describes the different options:

<table>
<tbody>
<tr>
<td width="100">
<p><strong>Input Type</strong></p>
</td>
<td width="800">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="100">
<p>Value</p>
</td>
<td width="800">
<p>This input option should be used when the value that you want to use is fixed value for the given operation. Meaning, a value that is known at the time of the Task configuration and does not depend on the customer that makes the request.</p>
<p>The value should be specified in the &ldquo;value&rdquo; field that the system presents to the user when this option is selected.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Link</p>
</td>
<td width="800">
<p>This option enables you to use a value that is determined as a result of the execution of a previous Task. The previous Task can come from the same Stage of the current Task or from any of the previous Stages of the Flow. When selecting this option, the system enables the user to define which is the Stage, Task and Task-output that will be used. &nbsp;</p>
</td>
</tr>
<tr>
<td width="100">
<p>Input</p>
</td>
<td width="800">
<p>Use the &ldquo;input&rdquo; option when the Task requires a specific input from the user at the moment of opening a new DPM Request.</p>
<p>The system requires that you define the label of the field that the user will be requested to fill.</p>
<p>When a new DPM Request is submitted by a representative or customer, the system will present the list of parameters that were defined as &ldquo;input&rdquo; and the label you defined here will appear as the label of the field that should be populated.</p>
<p>For example, if the input parameter is the email address of the customer, then the label can be &ldquo;email address&rdquo; or &ldquo;Please provide your email address&rdquo;</p>
</td>
</tr>
</tbody>
</table>

The following image demonstrates the information that the DPM requests the Administrator to fill for each one of those input options. 



[![Previous](/articles/images/Previous.png)](/articles/00_DPM_User_Guide/04_0_Admin_Module.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/00_DPM_User_Guide/04_2_Regulations_and_Activities.md)