# Reference Tables Runtime Commands

The following commands are available from the Fabric Command Line.

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


# Reference Tables Synchronization Statuses


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

</tbody>
</table>


# Reference Tables Runtime Examples

```fabric>REF_STATUS TABLES='ALL' SCOPE='table';```

```
|table_name |status          |backlog|node                                |current_session_transaction|sync_error|notes|
+-----------+----------------+-------+------------------------------------+---------------------------+----------+-----+
|REFNUMBERS |READY           |0      |bac20345-74b0-4d45-baea-746bef4af388|                           |          |     |
|REFSIMPLE  |IN_SYNC         |0      |bac20345-74b0-4d45-baea-746bef4af388|                           |          |     |
|REF_INVOICE|WAITING_FOR_SYNC|0      |bac20345-74b0-4d45-baea-746bef4af388|                           |          |     |


```

```fabric>REF_STATUS TABLES=’ALL’ SCOPE=’population’```

```
|table_name |population            |verified_time          |start_sync_time        |end_sync_time          |start_write_time       |last_write_time        |next_planned_sync      |sync_error  |node                                |notes|
+-----------+----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+------------+------------------------------------+-----+
|REFNUMBERS |refpop1               |2020-09-10 17:23:52.204|2020-09-10 17:23:52.204|2020-09-10 17:25:32.212|2020-09-10 17:25:32.275|2020-09-10 17:25:32.276|1970-01-01 02:00:00.000|null        |bac20345-74b0-4d45-baea-746bef4af388|     |
|REFSIMPLE  |refpop2               |2020-09-10 17:23:52.199|2020-09-10 17:23:52.199|1970-01-01 02:00:00.000|2020-09-10 17:25:32.262|1970-01-01 02:00:00.000|1970-01-01 02:00:00.000|null        |bac20345-74b0-4d45-baea-746bef4af388|     |
```





[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/04_fabric_commonDB_configuration.md)
