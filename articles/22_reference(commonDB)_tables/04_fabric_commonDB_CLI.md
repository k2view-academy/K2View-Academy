<table width="900pxl">
<tbody>
  
<tr>
<td valign="top" width="300pxl"><p><strong>Command Name</strong></p></td>
<td valign="top" width="400pxl"><p><strong>Description</strong></p></td>
<td valign="top" width="300pxl"><p><strong>Example</strong></p></td>
</tr>



<tr>
<td valign="top" width="300pxl"><h5>REF_SYNC [LU_NAME='lu name'] [TABLES='ALL' or '<table 1,table 2,etc...>'] [FORCE=true/false];</h5></td>
<td valign="top" width="400pxl"><p>Start sync job for the specified common tables.</p></td>
<td valign="top" width="300pxl"><p>REF_SYNC TABLES=’ALL’;</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>REF_SYNC_WAIT [LU_NAME='lu name'] [TABLES='ALL' or '<table 1,table 2,etc...>'];</h5></td>
<td valign="top" width="400pxl"><p>Waits for list of tables to be synced or transaction to be completed on the session, until a pre-defined timeout. Should run after REF_SYNC command or after insert/delete/update on the common table.</p></td>
<td valign="top" width="300pxl"><p>REF_SYNC_WAIT TABLES=’ALL’;</p></td>
</tr> 


<tr>
<td valign="top" width="300pxl"><h5>REF_STATUS [LU_NAME='lu name'] [TABLES='ALL' or '<table 1,table 2,etc...>'] [SCOPE='table' or 'population'];</h5></td>
<td valign="top" width="400pxl"><p>REF_STATUS provides the tables sync status for the specified reference tables across all nodes. In addition, it provides the ‘Current Session Transaction’, i.e. the status of the latest transaction, executed on the table in the current session.</p></td>
<td valign="top" width="300pxl"><p>REF_STATUS;</p><p>REF_STATUS TABLES=’ALL’ SCOPE=’population’;</p></td></tr> 

</tbody>
</table>



Default scope is table.
Supported statuses:
WAITING_FOR_SYNC
Request for sync was issued but sync didn’t start yet
IN_SYNC
In sync process
READY
Sync completed
READY_WITH_BACKLOG
Sync completed, be notified that there are still messages waiting in the backlog queue
IN_BACKGROUND_SYNC
Sync in background is currently running
Current Session Transaction values:
STARTED
The transaction has started in the current session, but is not committed yet.
COMMIT IN PROGRESS
COMPLETED
Commit is completed
ROLLBACK IN PROGRESS
ROLLED BACK
Rollback is completed
