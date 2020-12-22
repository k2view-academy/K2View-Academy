## Customer View Requests  

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


[![Previous](/articles/images/Previous.png)](/articles/DPM/DPM_User_Guide/04_Customer_Direct_Requests/03_Customer_Direct_Requests_Submit.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/DPM/DPM_User_Guide/04_Customer_Direct_Requests/05_Customer_Direct_Requests_APIs.md)

