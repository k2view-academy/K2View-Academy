# Batch Commands

The following Batch commands are available in the Fabric Runtime environment:

**Migrate**

BATCH LUT ('*LUI1*','*LUI2*','*LUI3*','*LUI4*') *FABRIC_COMMAND*="sync_instance LUT.?" with ASYNC='true';


**Broadway**

BATCH LUT *fabric_command*="broadway *LUT*.SampleFlow SampleIID=?" with async=true;


**CDC Republish**

BATCH LUT from *fabric* *fabric_command*="cdc_republish_instance OracleLU.?" with async=true;


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

<tr>
<td valign="top" width="500pxl">
<h5>batch &ltLUT&gt[@&ltDC&gt] FABRIC_COMMAND='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_WORKERS_PER_NODE=&ltnumber&gt]]; </h5>
</td>
<td valign="top" width="400pxl">
   
<p>
Start the Batch process and sync all LU instances:
   
- DC, specify the DC name to force the Batch process within the specified DC. This can also be defined in the Affinity parameter:  
  - AFFINITY, list of nodes and DCs to be involved in the Batch process command.
- JOB_AFFINITY, affinity for the Batch process Job.
- ASYNC, defines whether the Batch process should run in a sync or async mode. Default is false.
- GENERATE_ENTITIES_FIRST, if set to true, generate all entities before processing them.
- FABRIC_COMMAND, Fabric command to be executed by the Batch process which can be any command that includes one '?' that represents an Entity ID. 

The following commands must be set:
  - Migration, "sync_instance <LUT>.?"
  - Broadway flow, ...
  - CDC republish, ...
- ALLOW_MULTIPLY, when set to true, multiplies executions of the same Batch process command. Default is false.
- MAX_NODES, the maximum nodes participating in the Batch process (random nodes).
- MAX_WORKERS_PER_NODE, enables setting a lower number of maximum workers to run on each node than the maximum number of workers defined in the config.ini file (MAX_WORKERS_PER_NODE parameter).</p>

</td>
<td valign="top" width="300pxl">
<p></p>
<p></p>
</td>
</tr>  

<tr>
<td valign="top" width="300pxl">

<h5>batch &ltLUT>[@&ltDC&gt].&ltIG&gt fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</h5>
</td>
<td valign="top" width="400pxl">

<p>Batch-processes, a subset of the LUI based on the Instance Group specified by the &ltIG&gt parameter.</p>

</td>
<td valign="top" width="300pxl">
<p></p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>batch &ltLUT&gt[@&ltDC&gt] from &ltdb_interface&gt using ('&ltSQL&gt') fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt' [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</h5>

</td>
<td valign="top" width="400pxl">

<p>Batch process, a subset of the LUI based on a query to a source interface defined in the &ltdb_interface&gt parameter.</p>
<td valign="top" width="300pxl">

<p>
</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>batch &ltLUT>[@&ltDC&gt] from fabric fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</h5>

</td>
<td valign="top" width="400pxl">

<p>Batch-processes, a subset of the LUI based on existing instances in Fabric in the entity table. </p>
</td>
<td valign="top" width="300pxl">

<p></p>

</td>
</tr> 


<tr>
<td valign="top" width="300pxl">

<h5>batch &ltLUT>[@&ltDC&gt].(&ltinstance 1,instance 2,...&gt) fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]]</h5>

</td>
<td valign="top" width="400pxl">

<p>Batch-processes, a subset of the LUI based on a list of instances defined in the &ltinstance 1,instance 2,...&gt parameters list.</p>
</td>
<td valign="top" width="300pxl">

<p></p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h5>batch_details '&ltbatch id&gt' [STATUS='&ltstatus&gt'] [ENTITIES='&ltentity 1,entity 2,...&gt'] [AFFINITY='&ltAffinity&gt'] [LIMIT=&ltlimit&gt] [SORT_BY_PROCESS_TIME=&lttrue/false&gt]</h5>

</td>
<td valign="top" width="400pxl">

<p>
Displays the status of instances of a given Batch process ID:
   
- STATUS, shows one of the following states: WAITING, COMPLETED, FAILED.
- ENTITIES, lists of entities separated by a comma.
- AFFINITY, DCs or nodes.
- SORT_BY_PROCESS_TIME, if true, shows only the entities with the highest process time. If set, ignore all other parameters.
- LIMIT, default LIMIT defined in the config.ini if no LIMIT is provided as an argument. </p>
</td>
<td valign="top" width="300pxl">
<p></p>
</td>
</tr> 


<tr>
<td valign="top" width="300pxl">
<h5>batch_in_process filter='&ltfilter regex&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>Lists all running Batch processes and returns the following information: 
   <li>Node ID</li>  <li>Batch process ID</li>
    <li>Entity ID</li>
    <li>LU type</li>
    <li>Time at work (ms)</li>  
    <li>exeid</li>
    <li>command|</li>
<li>Filter, must be a regex compatible argument.</li>
</p>
</td>
<td valign="top" width="300pxl">
<p></p>
</td>
</tr> 



<tr>
<td valign="top" width="300pxl">
<h5>batch_list [STATUS='&ltstatus&gt' [FROM_DATE='&ltfrom date&gt' [TO_DATE='&ltto date&gt']] [FILTER=&ltfilter criteria&gt]</h5>

</td>
<td valign="top" width="400pxl">

<p>
If there are no arguments, lists all active Batch processes together with their respective status:
   
- NEW, GENERATE_IID_LIST, IN_PROGRESS, FAILED, CANCELLED, DONE, ALL
- FROM/TO_DATE, support DATE_FORMAT/DATETIME_FORMAT according to the configuration in the config.ini.
- FILTER, filters Batch processes. The filter field must be populated by a string in a Fabric command in the Batch process. 

Note that the filter supports regex.
</p>
</td>
<td valign="top" width="300pxl">
<p>
   - batch_list STATUS='ALL'; – list the history of all the batch processes
   - batch_list status='ALL' FILTER='sync_instance';  list the history of all batch processes. (This command returns the same results as migrate_list STATUS = ‘ALL’; command)
</p>
</td>
</tr> 



<tr>
<td valign="top" width="300pxl">
<h5>batch_retry '&ltbatch id&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>Resumes a previous Batch process by reprocessing all failed or unhandled entities if the Batch process has not been completed. Otherwise, it retries the failed entities only.
</p>
</td>
<td valign="top" width="300pxl">
<p>
  
</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h5>batch_summary '&ltbatch id&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>This report brings a table holding a summary about node, DC and cluster levels.                                    
</p>
</td>
<td valign="top" width="300pxl">
<p>batch_summary '35408af6-b26a-4243-bc95-f114335bfa5e'</p>
</td>
</tr> 

</table>


## Batch Commands Examples

### batch_list

Command  

``` batch_list status='all'```

Result 
```
|Id                                  |Command                                                                                                                                                                |Start date         |End date           |Status|Created by|Completion %|Error|
+------------------------------------+-----------------------------------------------------------------------------------------------------------------------------------------------------------------------+-------------------+-------------------+------+----------+------------+-----+
|35408af6-b26a-4243-bc95-f114335bfa5e|BATCH AUTODATA_DELTA FROM idsFile USING ('select id from ids  limit 200') FABRIC_COMMAND="sync_instance AUTODATA_DELTA.?" with JOB_AFFINITY='10.21.2.102' ASYNC='true';|2020-08-12 12:20:07|2020-08-12 12:20:09|DONE  |          |100         |null |
```


### batch_summary

Command 

``` batch_list status='all'```

Result

<img src="/articles/20_jobs_and_batch_services/images/22_jobs_and_batch_services_commandsExamples.PNG">

This command returns execution information and statistics for a given bid on each node in the execution:
- Name, the Node Id.
- %Completed, percentage of executions run on each node.
- Ent/sec, average entities executed per seconds (pace).

Note that all other fields are self-explanatory. 


## Instance Groups

### How Do I Create a New Instance Group?
1. Go to the **Fabric Studio**, select the **LU** > **Instance Groups** and right click and select **New Instance Group**.
2. Write a valid **SQL query** to select the instances to be included in the Instance Group.
3. Save the **Instance Group**.

<img src="/articles/20_jobs_and_batch_services/images/23_jobs_and_batch_services_commandsExamples.PNG">

The Instance Group (referred to as **customer_IG_600To700** in the illustration above) is deployed together with its LUT.

### How Do I Invoke an Instance Group from the Batch Command

Example 

    batch Customer.customer_IG_600To700 FABRIC_COMMAND="sync_instance PATIENT.?" with JOB_AFFINITY='10.21.2.102' async='true';

The Instance Group is the one defined from Fabric Studio - *customer_IG_600To700*

Result:

All instances with ID values between 600 and 700 are synced into Fabric.

fabric>batch Customer.customer_IG_600To700 FABRIC_COMMAND="sync_instance PATIENT.?";
```
|Added|Updated|Unchanged|Failed|Total|Duration|
+-----+-------+---------+------+-----+--------+
|99   |0      |0        |0     |99   |875     |
```

## Batch Command with Embedded SQL Statements
Instead of referring to an *Instance Group*, the batch command can embed an SQL statement that will select the entities on which the batch command needs to be executed:
```
batch <LUT> from <db_interface> using ('<SQL>') fabric_command='<fabric command> ?'
```

Example:

fabric>batch Customer from CRM_DB USING('select customer_id from Customer where customer_id <=10') FABRIC_COMMAND="sync_instance CUSTOMER.?";
```
|Batch id                            |Execution succeeded|Execution failed|Total|Duration|Batch id                            |
+------------------------------------+-------------------+----------------+-----+--------+------------------------------------+
|83fade2f-2ae6-4359-8b7a-bdd1866d2191|10                 |0               |10   |1       |83fade2f-2ae6-4359-8b7a-bdd1866d2191|
```

## Migrate Commands - Legacy Support
The Migrate command is a particular use-case of the batch command dealing with the synchronization of instances.
It is equivallent to running the batch command without the *FABRIC_COMMAND* parameter:
``` batch <LUT>[@<DC>] FABRIC_COMMAND='<fabric command> ?' ``` is the same as ```migrate <LUT>[@<DC>]```

Using the same example as above:

fabric>migrate Customer.customer_IG_600To700;
```
|Added|Updated|Unchanged|Failed|Total|Duration|
+-----+-------+---------+------+-----+--------+
|99   |0      |0        |0     |99   |875     |
```

## batchf & migratef commands 

migratef              |



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/07_batch_process_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/09_batch_process_flow.md)
