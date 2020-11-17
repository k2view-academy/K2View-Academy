# Representative User Interface

A Customer Service Representative that supports the DPM processes should have his user assigned to a corporate role that is mapped to the Representative DPM Application role. 
A Representative can:

- Search for Requests that were previously submitted.
- Submit a new Request. 

 ![image](images/Figure_32_Representative_menu.png)

## Representative Search a Request

Under the Representative menu, select the “Requests List” menu option. The screen that is presented includes the list of existing requests and allows searching for a specific request based on multiple parameters.

![](images/Figure_33_Representative_Search_Request.png)

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
<p>Status</p>
</td>
<td width="800">
<p>The Request status. Values may be: In Progress, Completed, Stopped. A Stopped Request is a Request that during its execution the system identified it should stop the execution. For example, if there is a validation that the same customer cannot open two equal Requests, and there is already another Request for this same customer and Activity which is still in progress</p>
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



For the search criteria that are selected by a drop-down list or selecting a date: Regulation, Activity, Request Status, Created and Completed – the search is executed as soon as a value is selected. 
For the search criteria which allow typing: customer ID and Request ID, the search is invoked as soon as the representative types 3 characters or more. 
Once the search results are presented, the representative can view further details of a specific request by clicking the row of this request:

![](images/Figure_34_Request_Details.png)

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
<p>Regulation name</p>
</td>
<td width="800">
<p>The Regulation that this request was created for.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Activity name</p>
</td>
<td width="800">
<p>The Activity that this Request executes.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Request Status</p>
</td>
<td width="800">
<p>The Request status.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Created on</p>
</td>
<td width="800">
<p>The date and time that the Request was submitted.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Last Update Date</p>
</td>
<td width="800">
<p>The last time this request was updated. The Request is updated as its execution is progressing &ndash; so this date indicates the last date that a Task was executed in the Flow that is fulfilling this Request.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Estimated completion in</p>
</td>
<td width="800">
<p>The number of days till the request is expected to be completed. The number of days is calculated based on the Request creation date and the Activity SLA.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Extended</p>
</td>
<td width="800">
<p>Indicates if the Request SLA was extended. The values are true/false</p>
</td>
</tr>
<tr>
<td width="100">
<p>Extendable</p>
</td>
<td width="800">
<p>Indicates if the Request SLA can be extended. This depends on the configuration of the Activity of this task.</p>
</td>
</tr>
</tbody>
</table>


 ## Representative Submits a Request

A representative can submit a request on behalf of a customer, using the “Submit a Request” button that is located on the search screen or by using the menu on the left. The list of requests type and the information that should be provided for each of them is defined by implementation using the Admin module.

![](images/Figure_35_Representative_submits_a_new_Request.png)

When submitting a new request, the representative first selects the regulation that applies to the customer out of the Regulation dropdown list and fills the customer number at the Customer field. 
The representative can add notes to the Request before submitting it. 
The list of Request Types options that is presented at the bottom of the submission form changes according to the selected Regulation.
Once the Request type is selected, the right side of the form presents the list of fields that should be completed before submitting the request. This list is automatically adapted in accordance with the Request type.

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
<p>Regulation</p>
</td>
<td width="800">
<p>The Regulation that applies to the customer that is asking to submit a request.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Customer</p>
</td>
<td width="800">
<p>The customer identification of the customer that is asking to submit the request</p>
</td>
</tr>
<tr>
<td width="100">
<p>Notes</p>
</td>
<td width="800">
<p>Free text. Comments that the representative would like to be registered as part of the request.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Request Type</p>
</td>
<td width="800">
<p>The specific Request that should be submitted. The options in this section depend on the selected Regulation.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Request Form Fields</p>
</td>
<td width="800">
<p>Depending on the selected Request Type, different fields will be presented in this section, and the representative should populate them according to the information of the requesting customer.</p>
</td>
</tr>
</tbody>
</table>

[![Previous](/articles/images/Previous.png)](/articles/00_DPM_User_Guide/04_4_DPM_Main_Menu.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/00_DPM_User_Guide/06_Customer_Direct_Requests.md)

