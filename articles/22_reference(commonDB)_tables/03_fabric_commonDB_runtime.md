# Reference Table Runtime Commands


## Synchronization Overview

This article discusses synchronization between any Fabric session and the external source from where the Reference table is populated. 
For information about the Cross-nodes Synchronization process within a Fabric Cluster, refer to this [article](04_fabric_commonDB_sync.md).   

## Use Cases

**Case 1 - Background Sync**

In this scenario a Fabric Session requests to update a Reference table from an external source according to a predefined interval. The [ref_sync and ref_sync_wait commands](/articles/22_reference(commonDB)_tables/03_fabric_commonDB_runtime.md#synchronization-commands) are triggered automatically in the background according to the [method](/articles/22_reference(commonDB)_tables/02_reference_table_fabric_studio.md#sync-method) selected in Fabric Studio (Time Interval or Decision Function).


**Case 2 - Pro-Active Sync**

In this scenario, for example, a customer service operative needs to get the most updated list of new services subscribed by a customer in real-time. The Web Service or Job request triggers the [ref_sync and ref_sync_wait commands](/articles/22_reference(commonDB)_tables/03_fabric_commonDB_runtime.md#ref_sync-lu_namelu-name-tablesall-or-table-1table-2etc-forcetruefalse).


**Case 3 - Scheduled Sync**

In this scenario a system needs to operate a synchronization process every day at 2 AM as a result of a maintenance task - i.e. get all new customers, or new transactions created over the last 24 hours. A recurring background sync can be scheduled for a specific day and time.


## Synchronization Commands


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
<td valign="top" width="400pxl"><p>Waits for the list of tables to be synced or a transaction to be completed on the session, until a pre-defined timeout.</p><p>Should be run after the REF_SYNC command or after insert/delete/update on the common table.</p></td>
<td valign="top" width="300pxl"><p>REF_SYNC_WAIT TABLES=’ALL’;</p></td>
</tr> 


<tr>
<td valign="top" width="300pxl"><h5>REF_STATUS [LU_NAME='lu name'] [TABLES='ALL' or '&lttable 1,table 2,etc...&gt'] [SCOPE='table' or 'population'];</h5></td>
<td valign="top" width="400pxl"><p>REF_STATUS provides the tables sync status for the specified reference tables across all nodes.</p><p>In addition, it provides the ‘Current Session Transaction’, i.e. the status of the latest transaction, executed on the table in the current session.</p><p>Default scope is table.</p></td>
<td valign="top" width="300pxl"><p>REF_STATUS;</p><p>REF_STATUS TABLES=’ALL’ SCOPE=’population’;</p></td>
</tr> 

</tbody>
</table>


## Reference Tables Synchronization Statuses Commands


<table width="900pxl">
<tbody>
  
<tr>
<td valign="top" width="300pxl"><p><strong>Status</strong></p></td>
<td valign="top" width="400pxl"><p><strong>Description</strong></p></td>
</tr>

<tr>
<td valign="top" width="300pxl"><h5>WAITING_FOR_SYNC</h5></td>
<td valign="top" width="400pxl"><p>Request for sync has been issued but the sync has not started yet</p></td>
</tr> 


<tr>
<td valign="top" width="300pxl"><h5>IN_SYNC</h5></td>
<td valign="top" width="400pxl"><p>Sync in process.</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>READY</h5></td>
<td valign="top" width="400pxl"><p>Sync completed</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>READY_WITH_BACKLOG</h5></td>
<td valign="top" width="400pxl"><p>Sync has been completed, and with messages still waiting in the backlog queue</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>IN_BACKGROUND_SYNC</h5></td>
<td valign="top" width="400pxl"><p>Sync in background is currently running.</p><p>Current Session's Transaction values</p></td>
</tr> 

<tr>
<td valign="top" width="300pxl"><h5>STARTED</h5></td>
<td valign="top" width="400pxl"><p>The transaction has started in the current session, but has not yet been committed.</p></td>
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


## Reference Tables Runtime Examples


### Status per table, on all tables
```
fabric>REF_STATUS TABLES='ALL' SCOPE='table';
```

```

|table name               |status|node        |backlog|offset|offset duration (min)|num of messages|transaction id|transaction type|sub status|current session transaction|sync error|notes|
+-------------------------+------+------------+-------+------+---------------------+---------------+--------------+----------------+----------+---------------------------+----------+-----+
|common.ref_Asset         |READY |fabric_debug|0      |0     |0                    |0              |              |IDLE            |          |                           |          |     |
|common.ref_Asset_Stations|READY |fabric_debug|0      |0     |0                    |0              |              |IDLE            |          |                           |          |     |
|common.ref_Asset_Type    |READY |fabric_debug|0      |0     |0                    |0              |              |IDLE            |          |                           |          |     |

```


- table_name: the name of the Reference Table.
- status: one of the statuses defined [here](/articles/22_reference(commonDB)_tables/03_fabric_commonDB_runtime.md#reference-tables-synchronization-statuses).
- node: the ID of the node operating the Reference table's synchronization.
- backlog: number of messages still to be processed during the synchronization process
- offset: total number of message retrieved during the synchronization
- offset duration: time to process all messages
- Transaction Id: current processed transaction id
          -   LONG_SNAPSHOT
          -   SHORT_SNAPSHOT
          -   SHORT_TRANSACTION
          -   LONG_TRANSACTION
          -   IDLE
- Sub_status:
          - In process
          - Index rebuild
          - Done                                                                                                            
- Current Session Transaction values:
          - Started: the transaction has started on the current session, but is not committed yet
          - Commit in progress
          - Completed - commit is completed
          - Rollback in progress
          - Rolled back
          - Rollback is completed                                                                             


### Status per population, on all tables
```
fabric>REF_STATUS TABLES=’ALL’ SCOPE=’population’
```

```

|table name               |population     |verified time          |start sync time        |end sync time          |start write time       |last write time        |next planned sync      |sync error|node        |notes|
+-------------------------+---------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+-----------------------+----------+------------+-----+
|common.REF_ASSET         |population.flow|2022-03-16 17:55:32.324|2022-03-16 11:59:13.048|2022-03-16 11:59:13.631|2022-03-16 11:59:13.639|2022-03-16 11:59:13.743|1970-01-01 02:00:00.000|null      |fabric_debug|     |
|common.REF_ASSET_STATIONS|population.flow|2022-03-16 17:55:32.346|2022-03-16 11:59:13.048|2022-03-16 11:59:14.460|2022-03-16 11:59:13.774|2022-03-16 11:59:14.476|1970-01-01 02:00:00.000|null      |fabric_debug|     |
|common.REF_ASSET_TYPE    |population.flow|2022-03-16 17:55:32.361|2022-03-16 11:59:13.049|2022-03-16 11:59:13.238|2022-03-16 11:59:13.293|2022-03-16 11:59:13.295|1970-01-01 02:00:00.000|null      |fabric_debug|     |


```

- table_name: the name of the reference table
- population: the name of the population querying the external sources
- verified time: timestamp when last sync was verified  
- start/end sync time: Sync time start and end times
- start/last write time: Write time of first and last message
- next planned sync: Timestamp for next sync
- sync error: error message 
- node: The ID of the node operating the sync
- notes: details 



### Sync Wait
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


## Reference Table Data Manipulations

  
- To select and modify data in Reference Tables set the ```common_local_trx``` flag to TRUE, before running a commit.
    ```fabric>set COMMON_LOCAL_TRX=true;```
  
  - Using the [example](/articles/22_reference(commonDB)_tables/02_reference_table_fabric_studio.md#how-do-i-create-a-new-reference-table-in-fabric) defined earlier, and the DEVICESTABLE2017 Reference Table:
  - Enable Reference Table modification: ```fabric>set COMMON_LOCAL_TRX=true;```
  - Use the ```select``` command to view the row to be modified:
  
    ```
    fabric>select TAC, BRANDMODEL  from DEVICESTABLE2017 where TAC=35156209;
    |TAC     |BRANDMODEL             |
    +--------+-----------------------+
    |35156209|GALAXY J3 2016 SM-J320F|
    ```

  - Start a transaction: ```fabric>begin;```
  - Using the ```update``` command, operate a change in the table: ```fabric>update DEVICESTABLE2017 set BRANDMODEL='GALAXY J3--2016 SM-J320F' where TAC=35156209;```
  - Check that the change has been committed:
  
    ```
    fabric>select TAC, BRANDMODEL  from DEVICESTABLE2017 where TAC=35156209;
    |TAC     |BRANDMODEL              |
    +--------+------------------------+
    |35156209|GALAXY J3--2016 SM-J320F|
    ```
  - Close the transaction: ```fabric>end;```
  - Note that if you forget to close the transaction, write sessions to the Reference Table (such as a scheduled Sync) will not work.
  



[<img align="left" width="60" height="54" src="/articles/images/Previous.png">](/articles/22_reference%28commonDB%29_tables/02_reference_table_fabric_studio.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/22_reference%28commonDB%29_tables/04_fabric_commonDB_sync.md)
