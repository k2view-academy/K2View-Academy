# Search a Request

Search a request from the Representative menu by selecting the “Requests List” menu option. The displayed screen includes the list of existing requests and allows searching for a specific request based on multiple parameters.

<img src="../images/Figure_33_Representative_Search_Request.png" width="100%" height="100%">


<table>
<tbody>
<tr>
<td width="100">
<p><strong>Column</strong></p>
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
<p>The Activity this Request executes. </p>
</td>
</tr>
<tr>
<td width="100">
<p>Status</p>
</td>
<td width="800">
<p>The Request status. Values may be: In Progress, Completed, Stopped. A Stopped Request is a Request,  during its execution the system identified the request should stop its execution. For example, if there is a validation, the same customer cannot open two equal Requests, and there is already an in-progress Request for this same customer and Activity. </p>
</td>
</tr>
<tr>
<td width="100">
<p>Created</p>
</td>
<td width="800">
<p>The date and time that the Request was submitted.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Completed</p>
</td>
<td width="800">
<p>The date and time that the Request was completed (completed requests only).</p>
</td>
</tr>
</tbody>
</table>

For the search criteria selected by a drop-down list or by selecting a date: Regulation, Activity, Request Status, Created and Completed – the search is executed upon a value selected.  

For the search criteria entry fields: Customer ID and Request ID, typing in either the customer ID and Request ID fields, the search is invoked upon the first three stated characters or more.  

Once the search results are presented, the representative can view further details of a specific request by clicking the row of this request, as shown in the next image.

<img src="../images/Figure_34_Request_Details.png" width="100%" height="100%">

The top section of the screen includes the high level request information: 

<img src="../images/Figure_34a_Representative_Request_header.png" width="100%" height="100%">

<table>
<tbody>
<tr>
<td width="200">
<p><strong>Field</strong></p>
</td>
<td width="700">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="200">
<p>Request ID</p>
</td>
<td width="700">
<p>The unique identification of the request.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Regulation name</p>
</td>
<td width="700">
<p>The Regulation for which the request was created.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Activity name</p>
</td>
<td width="700">
<p>The Activity this Request executes.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Request Status</p>
</td>
<td width="700">
<p>The Request status.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Created on</p>
</td>
<td width="700">
<p>The date and time the Request was submitted.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Last Update Date</p>
</td>
<td width="700">
<p>The last time this request was updated. The Request is updated as its execution is progressing; this date indicates the last date that a Task was executed in the Flow fulfilling this Request.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Estimated completion</p>
</td>
<td width="700">
<p>The number of days the request is expected to complete. The number of days calculation is based on the Request creation date and the Activity SLA.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Extended</p>
</td>
<td width="700">
<p>Indicates if the Request SLA was extended. The values are true/false</p>
</td>
</tr>
<tr>
<td width="200">
<p>Extendable</p>
</td>
<td width="700">
<p>Indicates if the Request SLA can be extended. This depends on the configuration of the Activity of this task.</p>
</td>
</tr>
</tbody>
</table>



## Request Timeline

The section that appears below the header, on the left side of the screen provides the main milestones of the request:

<img src="../images/Figure_34b_Representative_Request_timeline.png" width="50%" height="50%"> 

The timeline presents information such the date the request was submitted, the dates when notifications were sent to the customer, and the date when the request was completed.

## Request Data

The section to the right of the screen shows detailed data originating from the request input and output attributes. <img src="../images/Figure_34c_Representative_Request_info.png" width="50%" height="50%">

The information in this section includes the the inputs that were requested from the customer at the moment of the request submission, as well as information that was generated as part of the request fulfilment process and that were marked as "show to customer" at the Task configuration screen. 

<table>
<tbody>
<tr>
<td width="200">
<p><strong>Field</strong></p>
</td>
<td width="700">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="200">
<p>Label</p>
</td>
<td width="700">
<p>The Label that was defined for this attribute in the Task configuration.</p>
<p>The labels are defined in the Tabs "Operations", "Additional Info" and "Steward Input" of the Task configuraiton.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Info</p>
</td>
<td width="700">
<p>The actual content of the attribute as was provided by the customer or generated as part of the reqeust fulfilment process.</p>
</td>
</tr>
<tr>
<td width="200">
<p>Customer Input</p>
</td>
<td width="700">
<p>Set to "on" if the information was provided by the customer, and "off" if it was generated as part of the request fulfilment process.</p>
</td>
</tr>
<tr>
</tbody>
</table>

More details about the way to configure parameters so that they would appear on the Request Data section can be found in the [Task Configuration](02_Admin_Module/05_Tasks.md) section of the Administrator user guide. 



[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/03_Representantive_User_Interface/01_Representative_User_Interface_Overview.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/03_Representantive_User_Interface/03_Representative_User_Interface_Submit.md)

