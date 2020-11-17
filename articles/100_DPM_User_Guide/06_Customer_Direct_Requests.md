

# Customer Direct Requests

The DPM system provides different ways to allow a customer to submit a DPM request, search for a request previously submitted and view its details:

- DPM includes built-in user interface for the use of a customer.
- DPM provides a set of APIs that can be integrated in the customer portal of each corporate that uses the system. 

## DPM Customer’s built-in user interface

DPM includes a set of screens that allow a customer to submit DPM Requests, as well as view his own request’s details.

### Customer Dashboard

When a customer logs into the DPM user interface, the initial screen that is presented is the customer’s Dashboard screen, which includes the following items:

- Submit a new request button: the option to submit a request.
- Open Requests: the number of requests that are in processing for this customer.
- View Requests: view the list of this customer’s requests.

 ![image](images/Figure_36_customer_landing_page.png)

The same options can be reached also from the menu items on the left side of the screen.

### Customer submits a request

When a customer selects the option to create a new request, the system directs the customer to a screen where the request details are collected. The list of requests type and the information that should be provided for each of them is defined by implementation using the Admin module. 

 ![image](images/Figure_37_Customer_submits_a_Reqeust.png)

When submitting a new request, the customer first selects the relevant regulation out of the Regulation dropdown list. 
The customer can add notes to the Request before submitting it. 
The list of Request Types options that is presented at the bottom of the submission form changes according to the selected Regulation.
Once the Request type is selected, the right side of the form presents the list of fields that should be completed before submitting the request. This list is automatically built in accordance with the Request type. 

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
<p>Notes</p>
</td>
<td width="800">
<p>Free text. Comments that the customer would like to be registered as part of the request.</p>
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
<p>Depending on the selected Request Type, different fields will be presented in this section, and the customer should populate them before submitting the request.</p>
</td>
</tr>
</tbody>
</table>

The customer number is not requested when submitting the request, since the customer had already provided this identification when entering the system. 

### Customer view requests  

The customer can view his own requests by either selecting the “Requests List” menu option, or by clicking the “View Requests” options on the Dashboard. 
The requests that are presented include both completed requests as well as requests that are still in-progress.

 ![image](images/Figure_38_Customer_views_requests.png)

<table width="594">
<tbody>
<tr>
<td width="103">
<p><strong>Column</strong></p>
</td>
<td width="491">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="103">
<p>Request ID</p>
</td>
<td width="491">
<p>The unique identification of the request.</p>
<p>A progress bar is displayed below the Request ID. This progress is calculated based on the Request open date and the SLA for this request. It is presented in green as long as the Request is within the SLA period (or extension period), and in red if the Request is overdue.</p>
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
<p>The Request status. Values may be: In Progress, Overdue, Completed, Stopped. A Stopped Request is a Request that during its execution the system identified it should stop the execution. For example, if there is a validation that the same customer cannot open two equal Requests, and there is already another Request for this same customer and Activity which is still in progress</p>
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

Further details about a specific request can be viewed by clicking the request row:

 ![image](images/Figure_39_Customer_Requests_details.png)

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
<p>Indicates if the Request SLA was extended. The values are true/false.</p>
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

## DPM APIs for customer requests

For companies that prefer to integrate the DPM requests in their own customer portal using their standard user interface design, DPM provides a set of APIs to be used by any client application.  
For detailed documentation of those APIs access the swagger documentation of the DPM system.

[![Previous](/articles/images/Previous.png)](/articles/00_DPM_User_Guide/05_Representative_User_Interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/00_DPM_User_Guide/07_Steward_User_Interface.md)

