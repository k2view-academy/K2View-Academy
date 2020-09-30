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


## Status per table, on all tables
```
fabric>REF_STATUS TABLES='ALL' SCOPE='table';```
```

```
|table_name|status          |backlog|node                                |current_session_transaction|sync_error|notes|
+----------+----------------+-------+------------------------------------+---------------------------+----------+-----+
|REF_USAGE |WAITING_FOR_SYNC|0      |b028b082-4ad3-477a-8188-c07bbeecc6e1|                           |          |     |
|REF_USAGE |WAITING_FOR_SYNC|0      |cde47595-5222-4642-8d37-1757d6719693|                           |          |     |
|REF_USAGE |WAITING_FOR_SYNC|0      |809f729c-9334-4565-825b-3daf026ba34d|                           |          |     |
|REF_USAGE |WAITING_FOR_SYNC|0      |13dfb26f-f6d7-42f7-96d6-6d45742964be|                           |          |     |
|REF_USAGE |WAITING_FOR_SYNC|0      |f550f905-777f-43e5-a96a-ea8a673a5a81|                           |          |     |
|REF_USAGE |WAITING_FOR_SYNC|0      |80ca785a-82c5-4cbf-9fce-b9d27b3c1f63|                           |          |     |

```

Note that, in this example 6 different Nodes are waiting to sync the same REF_USAGE table

- table_name: - the name of the Reference Table
- status: - one of the statuses defined [here](/articles/22_reference(commonDB)_tables/03_fabric_commonDB_runtime.md#reference-tables-synchronization-statuses)
- node: The ID of the node operating the sync


## Status per population, on all tables
```
fabric>REF_STATUS TABLES=’ALL’ SCOPE=’population’
```

```
|table_name |population            |verified_time          |start_sync_time        |end_sync_time          |start_write_time       |last_write_time        |next_planned_sync      |sync_error  |node                                |notes|
+-----------+----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+------------+------------------------------------+-----+
|REFNUMBERS |refpop1               |2020-09-10 17:23:52.204|2020-09-10 17:23:52.204|2020-09-10 17:25:32.212|2020-09-10 17:25:32.275|2020-09-10 17:25:32.276|1970-01-01 02:00:00.000|null        |bac20345-74b0-4d45-baea-746bef4af388|     |
|REFSIMPLE  |refpop2               |2020-09-10 17:23:52.199|2020-09-10 17:23:52.199|1970-01-01 02:00:00.000|2020-09-10 17:25:32.262|1970-01-01 02:00:00.000|1970-01-01 02:00:00.000|null        |bac20345-74b0-4d45-baea-746bef4af388|     |
```

- table_name: - the name of the Reference Table
- population: - the name of the Population querying the External Sources
- node: The ID of the node operating the sync



## Sync Wait
```
fabric>REF_SYNC_WAIT TABLES='ALL';
```
```
|Table name|Required sync time|Current session transaction|
+----------+------------------+---------------------------+
|REF_USAGE |null              |                           |
|T1        |null              |                           |
|T2        |null              |                           |
```


[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/02_reference_table_fabric_studio.md)

[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/04_fabric_commonDB_sync.md)
