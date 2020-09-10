# Reference Tables Runtime Commands


<table width="900pxl">
<tbody>
  
<tr>
<td valign="top" width="300pxl"><p><strong>Command Name</strong></p></td>
<td valign="top" width="400pxl"><p><strong>Description</strong></p></td>
<td valign="top" width="300pxl"><p><strong>Example</strong></p></td>
</tr>



<tr>
<td valign="top" width="300pxl"><h5>REF_SYNC [LU_NAME='lu name'] [TABLES='ALL' or '&lttable 1,table 2,etc...&gt'] [FORCE=true/false];</h5></td>
<td valign="top" width="400pxl"><p>Start sync job for the specified common tables.</p></td>
<td valign="top" width="300pxl"><p>REF_SYNC TABLES=’ALL’;</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>REF_SYNC_WAIT [LU_NAME='lu name'] [TABLES='ALL' or '&lttable 1,table 2,etc...&gt'];</h5></td>
<td valign="top" width="400pxl"><p>Waits for list of tables to be synced or transaction to be completed on the session, until a pre-defined timeout.</p><p>Should run after REF_SYNC command or after insert/delete/update on the common table.</p></td>
<td valign="top" width="300pxl"><p>REF_SYNC_WAIT TABLES=’ALL’;</p></td>
</tr> 


<tr>
<td valign="top" width="300pxl"><h5>REF_STATUS [LU_NAME='lu name'] [TABLES='ALL' or '&lttable 1,table 2,etc...&gt'] [SCOPE='table' or 'population'];</h5></td>
<td valign="top" width="400pxl"><p>REF_STATUS provides the tables sync status for the specified reference tables across all nodes.</p><p>In addition, it provides the ‘Current Session Transaction’, i.e. the status of the latest transaction, executed on the table in the current session.</p><p>Default scope is table.</p></td>
<td valign="top" width="300pxl"><p>REF_STATUS;</p><p>REF_STATUS TABLES=’ALL’ SCOPE=’population’;</p></td>
</tr> 

</tbody>
</table>


# Reference Tables Synchronization Supported statuses



<table width="900pxl">
<tbody>
  
<tr>
<td valign="top" width="300pxl"><p><strong>Status</strong></p></td>
<td valign="top" width="400pxl"><p><strong>Description</strong></p></td>
</tr>

<tr>
<td valign="top" width="300pxl"><h5>WAITING_FOR_SYNC</h5></td>
<td valign="top" width="400pxl"><p>Request for sync was issued but sync didn’t start yet</p></td>
</tr> 


<tr>
<td valign="top" width="300pxl"><h5>IN_SYNC</h5></td>
<td valign="top" width="400pxl"><p>In sync process</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>READY</h5></td>
<td valign="top" width="400pxl"><p>Sync completed</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>READY_WITH_BACKLOG</h5></td>
<td valign="top" width="400pxl"><p>Sync completed, be notified that there are still messages waiting in the backlog queue</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>IN_BACKGROUND_SYNC</h5></td>
<td valign="top" width="400pxl"><p>Sync in background is currently running</p><p>Current Session Transaction values</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>STARTED</h5></td>
<td valign="top" width="400pxl"><p>The transaction has started in the current session, but is not committed yet.</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>COMMIT IN PROGRESS/COMPLETED</h5></td>
<td valign="top" width="400pxl"><p>Commit is in progress/completed</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>ROLLBACK IN PROGRESS/ROLLED BACK</h5></td>
<td valign="top" width="400pxl"><p>Rollback is in progress/completed</p></td>
</tr> 


