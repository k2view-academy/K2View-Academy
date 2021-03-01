## Customer View Requests  

The customer can view requests by either selecting the “Requests List” menu option, or by selecting the “View Requests” options from the Dashboard. The requests listed include both completed requests and in-progress requests.

 ![image](/articles/DPM/images/Figure_38_Customer_views_requests.png)

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
<p>A progress bar is displayed beneath the Request ID. The progress is calculated based on the Request open date and the SLA for this request. The related bar is displayed in green while the Request is within the SLA period (or extension period). The bar is displayed in red when the Request is overdue.
</p>
</td>
</tr>
<tr>
<td width="100">
<p>Regulation</p>
</td>
<td width="800">
<p>The Regulation for which the request was created.</p>
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
<p>The Request status. Status values may be: In Progress, Overdue, Completed, Stopped. A Stopped Request is a Request whereby, during its execution, the system identified it should stop the Request execution. For example, a customer attempts to open a second request that is equal to the first request while the first request is in progress. The validation of such a second request attempt will reject the second request for this reason. The rules and validations that can result in stopping a request are defined by configuration.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Created</p>
</td>
<td width="800">
<p>The date and time the Request was submitted.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Completed</p>
</td>
<td width="800">
<p>The date and time the completed Request was completed (completed requests only).</p>
</td>
</tr>
</tbody>
</table>


Additional details about a specific request can be viewed by clicking the request row:

 ![image](/articles/DPM/images/Figure_39_Customer_Requests_details.png)

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
<p>The Regulation for which the request was created.</p>
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
<p>The Request status (in-progress, completed, etc.).</p>
</td>
</tr>
<tr>
<td width="100">
<p>Created on</p>
</td>
<td width="800">
<p>The date and time that the Request was submitted</p>
</td>
</tr>
<tr>
<td width="100">
<p>Last Update Date</p>
</td>
<td width="800">
<p>The last time this request was updated. The Request is updated during the progression of its execution – in effect, this date indicates the last date a Task was executed in the Flow fulfilling the Request.</p>
</td>
</tr>
<tr>
<td width="100">
<p>Estimated completion</p>
</td>
<td width="800">
<p>The number of days the request is expected to be completed. The number of days is based upon the calculation from the Request creation date and the Activity SLA. </p>
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
<p>Indicates if the Request SLA can be extended. Extendable is dependent upon the configuration of the Activity of this Request.</p>
</td>
</tr>
</tbody>
</table>


[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/04_Customer_Direct_Requests/03_Customer_Direct_Requests_Submit.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/04_Customer_Direct_Requests/05_Customer_Direct_Requests_APIs.md)

