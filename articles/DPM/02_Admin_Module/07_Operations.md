# Operations Tab

The Operations tab allows the Admin to add an automated Activity to be executed to perform the Task. 

The user selects an Operation from the operation dropdown list. As a result, the list of parameters to be defined for the selected Operation is dynamically presented in the “inputs” section of the tab. Some operations may not need any parameters. 

As an example, for a Task that should execute the Operation “SendMail” the system expects the parameters “Message”, “Subject” and “to” as configured items. 

 ![image](/articles/DPM/images/Figure_17_Task_configuration_Operation_tab.png)

<b>Configuring Parameters for an Operation</b>

<p>When an operation requires the configuration of one or more parameters, the value of those parameters can be defined in several different ways. Each parameter can be one of the following types:</p> 

- Value
- Link
- Input

 ![image](/articles/DPM/images/Figure_18_Operation_parameters_types.png)

Each of those types allows the user to define a different way to obtain information needed for the execution of the operation. The following table describes the available options.

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
<p>This input option is used when the value to be used is a fixed value for the given operation. It refers to a known value at the time of the Task configuration and does not depend on the customer that makes the request.</p>
<p>The value should be specified in the &ldquo;value&rdquo; field that the system presents to the user when this option is selected.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Link</p>
</td>
<td width="800">
<p>This option enables the use of a value determined as a result of the execution of a previous Task. The previous Task can be derived from the same Stage of the current Task or from any of the previous Stages of the Flow. When selecting this option, the system enables the user to define the Stage, Task and Task output to be used.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Input</p>
</td>
<td width="800">
<p>Use the &ldquo;input&rdquo; option when the Task requires a specific input from the user at the moment of opening a new DPM Request.</p>
<p>The system requires the user to define the label of the field that the user will be requested to fill. </p>
<p>When a new DPM Request is submitted by a representative or customer, the system will present to the user the list of parameters that were defined as “input” and the label defined here appears as the label of the field that should be populated. </p>
<p>For example, when the input parameter is the email address of the customer, the label can be defined to be “email address” or “Please provide your email address”.</p>
</td>
</tr>
</tbody>
</table>


The following image demonstrates the information the DPM requests the Administrator to fill for each input option. 

 ![image](/articles/DPM/images/Figure_16_Task_Operation_the_data_to_be_configured_for_each_input_option.png)

In this example of an operation that sends emails, three input parameters are required: Subject, Body and Recipients. To demonstrate the different types of input types, each of those three fields was defined as one of the three types: 

- The field "Subject” was defined as “Value”, meaning the administrator can define a fixed text that will be presented as the subject of the mail. The system presents to the administrator the field where the text should be written
- The field “Body” was defined as “Link”, and as a result the system presents to the administrator the fields Stage Name, Task Name and Output Name. The administrator can define from which previous Task the information for this field is to be received. 
- The field “Recipients” was defined as “input”, meaning the customer will be requested to provide this value at the moment of submitting a request. The system presents to the administrator the field “Label”, which defines the text that will be presented to the customer so he will know to provide the recipient email address.

[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/06_Reminders.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/08_Regulations.md)

