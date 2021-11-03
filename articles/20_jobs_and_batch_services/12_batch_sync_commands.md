# Batch Commands

Note that all the following commands are also used and executed by the [Batch Monitor](/articles/20_jobs_and_batch_services/18_batch_monitor.md) dashboard to provide real-time visual insights on current or completed processes .

The Fabric runtime environment enables the execution of the following sets of Batch commands:

**Instances Migration**

```BATCH LU ('LUI','LUI2','LUI3','LUI4') FABRIC_COMMAND="sync_instance LU.?" with ASYNC='true';```


## Batch Commands Summary

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="300pxl">
<p><strong>Command Name</strong></p>
</td>
<td valign="top" width="400pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Example</strong></p>
</td>
</tr>
<td valign="top" width="500pxl">
<h6>BATCH &ltLU&gt[@&ltDC&gt] FABRIC_COMMAND='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLE=true/false] [MAX_WORKERS_PER_NODE=&ltnumber&gt]]; 
</td>
<td valign="top" width="400pxl">

<p>
Start the Batch process and sync all LU instances:

- LU, name of the Logical Unit
- DC, specifies the DC name to force the Batch process in the specified DC. 
- FABRIC_COMMAND, Fabric command to be executed by the Batch process. This command can be any command that includes a '?' to represent a singular Entity ID. One of the following commands must be set: (for Migration, "sync_instance <LU>.?", for Broadway, "broadway LU.SampleFlow SampleIID=?", for CDC republish, "cdc_republish_instance Customer.?")
- AFFINITY, list of nodes and DCs to be used during the Batch process execution.
- JOB_AFFINITY, affinity for the Batch process Job.
- ASYNC, defines whether the Batch process should run in a sync or async mode. Default is False (async).
- GENERATE_ENTITIES_FIRST, if set to True, generate all entities before processing them.
- ALLOW_MULTIPLE, when set to True, multiplies executions of the same Batch process command. Default is False.
- MAX_WORKERS_PER_NODE, enables setting a lower number of maximum workers to run on each node than the maximum number of workers defined in the config.ini file (MAX_NO_OF_WORKERS parameter). The number of workers allocated by a Fabric node cannot exceed the maximum number of workers set in the config.ini file. </p>

</td>
<td valign="top" width="300pxl">
<p>BATCH CUSTOMER FABRIC_COMMAND="sync_instance Customer.?" with async=’true’;

This command migrates all customers from the source systems into the Fabric CUSTOMER keyspace in the Fabric database.</p>

</td>
</tr>  

<tr>
<td valign="top" width="300pxl">

<h6>BATCH &ltLU>[@&ltDC&gt].&ltIG&gt FABRIC_COMMAND='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLE=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];
</td>
<td valign="top" width="400pxl">

<p>Batch-processes a subset of the LUI based on the <a href="/articles/20_jobs_and_batch_services/14_instances_groups.md#how-do-i-invoke-an-instance-group-from-the-batch-command">Instance Group</a> specified by the &ltIG&gt parameter.

   Optional parameters are the same as described above.</p>

</td>
<td valign="top" width="300pxl">
<p>BATCH CUSTOMER.ig10CustomersList FABRIC_COMMAND="sync_instance CUST.?" with async=’true’;</p>
<p>This command migrates the customers defined in the ‘ig10CustomersList’ <a href="/articles/20_jobs_and_batch_services/14_instances_groups.md#how-do-i-invoke-an-instance-group-from-the-batch-command">Instance Group</a> into the CUSTOMER keyspace in the Fabric database.

   </p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h6>BATCH &ltLU&gt[@&ltDC&gt] from &ltdb_interface&gt using ('&ltSQL&gt') fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt' [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLE=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</h6>

</td>
<td valign="top" width="400pxl">

<p>Batch-processes a subset of the LUI based on a query to a source interface defined in the &ltdb_interface&gt parameter.

   Optional parameters are the same as described above.</p>
<td valign="top" width="300pxl">

<p>BATCH CUSTOMER FROM CRM_DB USING (‘select customer_id from CUSTOMER where customer_id <= 1000’) FABRIC_COMMAND="sync_instance CUSTOMER.?" with async=’true’;
</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h6>BATCH &ltLU>[@&ltDC&gt] from fabric fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLE=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</h6>

</td>
<td valign="top" width="400pxl">

<p>Batch-processes a subset of the LUI based on existing instances in Fabric in the entity table. 

   Optional parameters are the same as described above.</p>
</td>
<td valign="top" width="300pxl">

<p>BATCH Customer from fabric fabric_command='sync_instance Customer.?';</p>

</td>
</tr> 


<tr>
<td valign="top" width="300pxl">

<h6>BATCH &ltLU>[@&ltDC&gt].(&ltinstance 1,instance 2,...&gt) fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLE=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]]</h6>

</td>
<td valign="top" width="400pxl">

<p>Batch-processes a subset of the LUI based on a list of instances defined in the &ltinstance 1,instance 2,...&gt parameters list.

   Optional parameters are the same as described above.</p>
</td>
<td valign="top" width="300pxl">

<p>BATCH Customer.('100', '101', '102','103') FABRIC_COMMAND="sync_instance CUSTOMER.?" with async=’true’;</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h6>BATCHF </h6>

</td>
<td valign="top" width="400pxl">

<p>Runs the Batch process using a function that returns the LUI (IID). For example if LUI is stored in Fabric as a combination of ID + Environment name, use a function to concatenate the ID and Environment name. </p>
<p>
The BATCHF command uses the same parameters as the BATCH command described above. 
</p>
</td>

<td valign="top" width="300pxl">
<p>
1) BATCHF Customer.batchFtest4().ig20 FABRIC_COMMAND='sync_instance Customer.?';
</p>
<p>   
2) BATCHF Customer@DC1.batchFtest4() from HIS_DB using ('select customer_id from invoice where balance=12894') FABRIC_COMMAND='sync_instance Customer.?';
</p>
<p>
3) BATCHF Customer.batchFtest4().(‘1’,’2’,’3’) FABRIC_COMMAND='sync_instance Customer.?';
</p>
</td>
</tr>





<tr>
<td valign="top" width="300pxl">
<h6>BATCH_RETRY '&ltbatch_id&gt' [allow_cancelled=true/false]</h6>

</td>
<td valign="top" width="400pxl">

<p>If the Batch process has not been completed, resumes a previous Batch process by reprocessing all failed or unhandled entities. Otherwise, it retries the failed entities only.

   If the Batch process is completed before the Retry command, Fabric gets the list of instances from the source DB. 

   If the Batch process is completed before the Retry command, Fabric gets the list of failed entities from the batchprocess_entities_errors Cassandra table.

   allow_cancelled - indicates if the command should enable a retry of cancelled batch process. By default, this parameter is set to false.
</p>
</td>
<td valign="top" width="300pxl">
<p>
BATCH_RETRY ‘161f9717-bd93-4882-a3aa-7b58c1f61b27’; 
</p>
</td>
</tr> 



<tr>
<td valign="top" width="300pxl">
<h6>CANCEL BATCH ['&ltbatch_id&gt']</h6>
</td>
<td valign="top" width="400pxl">
<p>
Cancels the last started Batch process coordinated by the current node. The Cancel command must be executed from the node that started the operation.

When adding the '&ltbatch_id&gt' parameter, the Batch process with the defined batch_ID is cancelled. Note that in this case, the Cancel command does not need to be run from the node coordinating the specific Batch process.

</p>
</td>
<td valign="top" width="300pxl">
<p>
CANCEL BATCH;
</p>
CANCEL BATCH ‘568114fe-9ec8-4c9e-af11-6e3348eff6e9’;  
</p>

</p>

Note that the KILL command can be used as an alternative, using the batch ID (*bid*):
KILL ‘568114fe-9ec8-4c9e-af11-6e3348eff6e9’; 
</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h6>BATCH_PAUSE ['&ltbatch_id&gt']</h6>
</td>
<td valign="top" width="400pxl">
<p>
Pause a batch process operation.

Introduced in Fabric 6.5.1
</p>
</td>
<td valign="top" width="300pxl">
<p>
BATCH_PAUSE ‘568114fe-9ec8-4c9e-af11-6e3348eff6e9’;  
</p>
Pause the async batch process with the defined batch id.
</p>
</p>
BATCH_PAUSE;  
</p>
</p>
Pause the last async batch process that was created in the current session.
</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h6>BATCH_RETRY ['&ltbatch_id&gt'] [allow_cancelled=true/false]</h6>
</td>
<td valign="top" width="400pxl">
<p>
If a batch process execution was not completed: retry a batch process by processing all failed or not handled entities.

or 

If a batch process execution was completed, retry only failed entities.


**allow_cancelled** , allows a retry on a cancelled batch process. Default is false.

Introduced in Fabric 6.5.1
</p>
</td>
<td valign="top" width="300pxl">
<p>
BATCH_RETRY ‘568114fe-9ec8-4c9e-af11-6e3348eff6e9’;  
</p>
Retry a batch process according to the given input batch id.
</p>
</p>
BATCH_RETRY ‘568114fe-9ec8-4c9e-af11-6e3348eff6e9’ allow_cancelled=true;  
</p>
</p>
Retry a batch process according to the given input batch id even if the original batch process was cancelled.
</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h6>BATCH_CANCEL ['&ltbatch_id&gt']</h6>
</td>
<td valign="top" width="400pxl">
<p>
Cancel a batch process operation.

Introduced in Fabric 6.5.1
</p>
</td>
<td valign="top" width="300pxl">
<p>
BATCH_CANCEL ‘568114fe-9ec8-4c9e-af11-6e3348eff6e9’;  
</p>
Cancel the async batch process according to the defined batch id. 
</p>
</p>
BATCH_CANCEL
</p>
</p>
Cancel the last async batch process that was created in the current session.
</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h6>BATCH_EDIT '&ltparam1=value1'&gt param2='&ltvalue2'&gt</h6>
</td>
<td valign="top" width="400pxl">

<p>
Modify the specified parameters for the specified batch process.
MAX_WORKERS_PER_NODE - Set the maximum workers per node for this batch. Note that this will not surpass the configured value
                            for MAX_WORKERS_PER_NODE in the config.ini.
   </p>

   <p>
Introduced in Fabric 6.5.1
</p>
</td>
<td valign="top" width="300pxl">
<p>
BATCH_EDIT ‘568114fe-9ec8-4c9e-af11-6e3348eff6e9’ MAX_WORKERS_PER_NODE=10 ;  
Modify MAX_WORKERS_PER_NODE for the given batch id to 10. 
</p>
</td>
</tr> 

<tr>

<td valign="top" width="300pxl">

###### BATCH_INFO ['<batch_id>']

</td>
<td valign="top" width="400pxl">

<p>
Returns static information about the execution of the input batch ID (such as the batch command or the Fabric command). 
</p>


<p>
Introduced in Fabric 6.5.2
</p>

</td>
<td valign="top" width="300pxl">

<p>
BATCH_INFO ‘568114fe-9ec8-4c9e-af11-6e3348eff6e9’;        
</p>

</td>
</tr> 

</table>


## Batch Monitoring Commands Summary

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="300pxl">
<p><strong>Command Name</strong></p>
</td>
<td valign="top" width="400pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Example</strong></p>
</td>
</tr>


<tr>
<td valign="top" width="300pxl">
<h6>BATCH_LIST [STATUS='&ltstatus&gt'] [FROM_DATE='&ltfrom_date&gt' [TO_DATE='&ltto_date&gt']] [FILTER=&ltfilter criteria&gt]</h6>

</td>
<td valign="top" width="400pxl">

<p>
If there are no arguments, lists all active Batch processes together with their respective status:

- NEW, GENERATE_IID_LIST, IN_PROGRESS, FAILED, CANCELLED, DONE, ALL
- FROM/TO_DATE, support DATE_FORMAT/DATETIME_FORMAT according to the configuration in the config.ini.
- FILTER, filters Batch processes. The filter field must be populated by a string and can be reffered to the Batch command or the execution id. 

Note that the filter supports regex.
</p>
</td>
<td valign="top" width="300pxl">
   <p>BATCH_LIST STATUS='ALL'; – list the history of all batch processes.</p>
   <p>BATCH_LIST STATUS='ALL' FILTER='sync_instance';  list the history of all batch processes. This command returns the same results as the migrate_list STATUS = ‘ALL’; command.</p>
</td>
</tr> 
<tr>
<td valign="top" width="300pxl">
<h6>BATCH_INFO '&ltbatch_id&gt'</h6>

</td>
<td valign="top" width="400pxl">

<p>Returns the general information of the batch process such as the batch command, Fabric command, LU name, Environment...
</p>
</td>
<td valign="top" width="300pxl">
<p>BATCH_INFO '35408af6-b26a-4243-bc95-f114335bfa5e'</p>
</td>
</tr> 
<tr>
<td valign="top" width="300pxl">
<h6>BATCH_SUMMARY '&ltbatch_id&gt'</h6>

</td>
<td valign="top" width="400pxl">

<p>This report brings a table holding a summary about node, DC and cluster levels.                                    
</p>
</td>
<td valign="top" width="300pxl">
<p>BATCH_SUMMARY '35408af6-b26a-4243-bc95-f114335bfa5e'</p>
</td>
</tr> 



<tr>
<td valign="top" width="300pxl">
<h6>BATCH_IN_PROCESS filter='&ltfilter regex&gt'</h6>

</td>
<td valign="top" width="400pxl">

<p>Lists all currently running instances by batch processes in the cluster level and returns the following information: 
   <li>Node ID</li>  <li>Batch process ID</li>
    <li>Entity ID</li>
    <li>LU type</li>
    <li>Time at work (ms)</li>  
    <li>exeid</li>
    <li>command</li>
<li>Filter, must be a regex compatible argument.</li>
</p>
</td>
<td valign="top" width="300pxl">
<p>BATCH_IN_PROCESS filter='^(cust)*$'</p>
<p>This command will return all running batch processes with names containing the "cust" pattern</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h6>BATCH_DETAILS '&ltbatch_id&gt' [STATUS='&ltstatus&gt'] [ENTITIES='&ltentity 1,entity 2,...&gt'] [AFFINITY='&ltAffinity&gt'] [LIMIT=&ltlimit&gt] [SORT_BY_PROCESS_TIME=&lttrue/false&gt]</h6>

</td>
<td valign="top" width="400pxl">

<p>
Displays the status of instances of a given Batch process ID:

- STATUS, which can be either WAITING, COMPLETED, FAILED.
- ENTITIES, lists of entities separated by a comma.
- AFFINITY, DCs or nodes.
- SORT_BY_PROCESS_TIME, if True, shows only the entities with the highest process time. If set, ignore all other parameters.
- LIMIT, default limit defined in the config.ini if no limit is provided as an argument. </p>
</td>
<td valign="top" width="300pxl">
<p>BATCH_DETAILS 'a4587541-b12d-4329-affd-7c25516c9cde';</p>
</td>
</tr> 

</table>

## Batch Commands Examples

### BATCH_LIST

Command  

``` BATCH_LIST status='all'```

Result 
```
|Id                                  |Command                                                                                                                                                                |Start date         |End date           |Status|Created by|Completion %|Execution id                        |Error|
+------------------------------------+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+-------------------+-------------------+------+----------+------------+------------------------------------+-----+
|35408af6-b26a-4243-bc95-f114335bfa5e|BATCH AUTODATA_DELTA FROM idsFile USING ('select id from ids  limit 200') FABRIC_COMMAND="sync_instance AUTODATA_DELTA.?" with JOB_AFFINITY='10.21.2.102' ASYNC='true';|2020-08-12 12:20:07|2020-08-12 12:20:09|DONE  |          |100         |75fd2291-36d1-451c-8916-e60320a4e76f|null |
```


### BATCH_SUMMARY

Command 

``` BATCH_SUMMARY '35408af6-b26a-4243-bc95-f114335bfa5e' ```

Result

<img src="/articles/20_jobs_and_batch_services/images/22_jobs_and_batch_services_commandsExamples.PNG">

This command returns execution information and statistics for a given bid on each node in the execution:
- Name, the Node ID.
- %Completed, percentage of executions run on each node.
- Ent/sec, average entities executed per seconds (pace - counting the average on the last 100 sec).

Note that all other fields are self-explanatory. 




## Batch Command with Embedded SQL Statements
Instead of referring to an Instance Group, the Batch command can embed an SQL statement to select the entities on which the Batch command is executed:
```
BATCH <LU> from <db_interface> using ('<SQL>') fabric_command='<fabric command> ?'
```

Example 

```
BATCH Customer from CRM_DB USING('select customer_id from Customer where customer_id <=10') FABRIC_COMMAND="sync_instance CUSTOMER.?";
```


```
|Batch id                            |Execution id                        |Execution succeeded|Execution failed|Total|Duration|
+------------------------------------+------------------------------------+-------------------+----------------+-----+--------+
|83fade2f-2ae6-4359-8b7a-bdd1866d2191|7b7f5a4b-2e2c-4f0e-90d5-865bac3484ee|10                 |0               |10   |1       |
```


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/11_batch_process_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/13_migrate_commands.md) 
