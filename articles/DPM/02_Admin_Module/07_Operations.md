## “Operations” Tab:

The Operations tab allows the Admin to add an automated Activity that should be executed to perform the Task. 

The user selects an Operation from the operation dropdown list. As a result, the list of parameters that should be defined for the selected Operation are dynamically presented in the “inputs” section of the tab. Some operations may not need any parameters. 

As an example, for a Task that should execute the Operation “SendMail” the system expects the parameters “Message”, “Subject” and “to” to be configured.

 ![image](/articles/DPM/images/Figure_17_Task_configuration_Operation_tab.png)

Configuring operation’s parameters: 
When an operation requires the configuration of one or more parameters, the value of those parameters can be defined in several different ways. Each parameter can be one of the following types: 

- Value
- Link
- Input

 ![image](/articles/DPM/images/Figure_18_Operation_parameters_types.png)

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



[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/06_Reminders.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/08_Regulations.md)

