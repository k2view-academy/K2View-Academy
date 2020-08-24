# Legacy Support

## Migrate Commands

The Migrate command is a specific use-case of the Batch command which deals exclusively with the migration of instances into the Fabric database.
Instances Migration

```MIGRATE LUT[@<DC>] with ASYNC='true';```

Behind the scenes, Fabric activates the Batch command when running the Migrate command. 
All the verbose defined for the [Batch process commands](/articles/20_jobs_and_batch_services/11_batch_process_commands.md#batch-commands-summary) can be applied to the Migrate command without specifying the FABRIC_COMMAND parameter.

For example:
The following two commands are equivallent.

``` BATCH <LUT>[@<DC>] FABRIC_COMMAND='<fabric command> ?' ``` is the same as ```migrate <LUT>[@<DC>]```

Using the same example as in previous article, using the same Instance Group pertaining to the Customer LU **customer_IG_600To700**:
```
MIGRATE Customer.customer_IG_600To700;
```
The results are the same as when running the Batch command: 

```
BATCH Customer.customer_IG_600To700 FABRIC_COMMAND="sync_instance PATIENT.?";``` 
```
```

|Added|Updated|Unchanged|Failed|Total|Duration|
+-----+-------+---------+------+-----+--------+
|99   |0      |0        |0     |99   |875     |
```

## Migrate and Migrate Monitoring Commands 
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
<h6>migrate &ltLUT&gt[@&ltDC&gt] [WITH [AFFINITY='&ltaffinity&gt'] [ASYNC=true/false] [JOB_AFFINITY='&ltjob affinity&gt']];</h6>
</td>
<td valign="top" width="400pxl">
<p>
Migrates all instances of a Logical Unit.
DC, - specify dc name to force the migration within specified dc, can also be defined on the affinity parameter, remained for backward compatibility
AFFINITY, - is an optional parameter for choosing list of nodes and DCs to be involved in the migrate command
ASYNC, - is an optional parameter that overrides whatever was defined on the session by default it is set to false for backward compatibility
JOB_AFFINITY, - Affinity for the migrate job
</p>
</td>
<td valign="top" width="300pxl">
<p> MIGRATE CUSTOMER with async=’true’; </p>
</td>
</tr> 


<tr>
<td valign="top" width="300pxl">
<h6>migrate &ltLUT&gt[@&ltDC>].&ltIG&gt [WITH [AFFINITY='&ltaffinity&gt'] [ASYNC=true/false] [JOB_AFFINITY='&ltjob affinity&gt']];</h6>
</td>
<td valign="top" width="400pxl">
<p>
Migrates a subset of Logical Unit Instances, based on an Instance Group
DC, AFFINITY, ASYNC, JOB_AFFINITY - as above
</p>
</td>
<td valign="top" width="300pxl">
<p>MIGRATE CUSTOMER.ig10CustomersList with async=’true’; </p>
</td>
</tr> 


<tr>
<td valign="top" width="300pxl">
<h6>migrate &ltLUT&gt[@&ltDC&gt] from &ltdb_interface&gt using ('&ltSQL&gt') [WITH [AFFINITY='&ltaffinity&gt'] [ASYNC=true/false] [JOB_AFFINITY='&ltjob affinity&gt']];</h6>
</td>
<td valign="top" width="400pxl">
<p>
Migrates a subset of Logical Unit Instances, based on a query to one of the source interfaces.
DC, AFFINITY, ASYNC, JOB_AFFINITY - as above
</p>
</td>
<td valign="top" width="300pxl">
<p> MIGRATE Customer from fabric</p>
</td>
</tr>

<tr>
<td valign="top" width="300pxl">
<h6>migrate &ltLUT&gt[@&ltDC&gt].(&ltinstance 1,instance 2,etc...&gt) [WITH [AFFINITY='&ltaffinity&gt'] [ASYNC=true/false] [JOB_AFFINITY='&ltjob affinity&gt']];</h6>
</td>
<td valign="top" width="400pxl">
<p>
Migrates a subset of Logical Unit Instances, based on list of instances.
DC, AFFINITY, ASYNC, JOB_AFFINITY - as above

</p>
</td>
<td valign="top" width="300pxl">
<p> MIGRATE Customer.('100', '101', '102','103') with async=’true’; </p>
</td>
</tr> 



<tr>
<td valign="top" width="300pxl">
<h6>migrate_list [STATUS='&ltstatus&gt' [FROM_DATE='&ltfrom date&gt' [TO_DATE='&ltto date&gt’]]];</h6>
</td>
<td valign="top" width="400pxl">
<p>
List of active migrates if no arguments provided
STATUS, - run with STATUS='' where STATUS belongs to one of the following: NEW,SYNC_REFERENCES, GENERATE_IID_LIST, IN_PROGRESS, FAILED, CANCELLED,DONE.
FROM/TO_DATE, - support DATE_FORMAT/DATETIME_FORMAT, according to the configuration in config.ini

In order to get all the migration processes run in the past for all statuses, refer to from Cassandra table k2migrate.migration_list


</p>
</td>
<td valign="top" width="300pxl">
<p>migrate_list status=‘ALL’</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h6>migrate_summary ‘&ltmig_id&gt’</h6>

</td>
<td valign="top" width="400pxl">

<p>
The report will bring the following summary in table structure format for the following levels: Cluster, DC, Node. 
The following information will be dispayed:
   
Level – Node, DC or Cluster
Name – Node ID, or DC name or empty in case of Cluster
Status – NEW,SYNC_REFERENCES,GENERATE_IID_LIST,IN_PROGRESS,FAILED,CANCELLED,DONE,  or ALL
Start Time – datetime
End  Time – datetime
Duration – running duration in HH:MM:SS format
Remaining Duration – expected remaining duration for ending the migrate in HH:MM:SS format
Remaining – remaining entities to be handled
Total to handle – total number of entities to be handled
Failed – number of failed entities
Added – number of added entities
Updated – number of updated entities
Unchanged – number of unchanged entities
%Completed - % of processed entities out of the total in case of node, DC level will show the load balance between the nodes/DCs
Entities/sec (avg.) – number of processed entities per second in the Entities/secrelevant level</p>
</td>
<td valign="top" width="300pxl">
<p>migrate_summary '4ddeed1a-0951-42ed-b2d0-709a36215f42';</p>
</td>
</tr> 


<tr>
<td valign="top" width="300pxl">
<h6>migrate_details '&ltmig_id&gt' [STATUS='&ltstatus&gt' [AFFINITY='&ltAffinity&gt']] [LIMIT &ltlimit&gt];</h6>
</td>
<td valign="top" width="400pxl">
<p>
Show instances status for a given migrate_id (mig_id).
STATUS, - define STATUS='' where STATUS belongs to one of the following: NEW,SYNC_REFERENCES, GENERATE_IID_LIST, IN_PROGRESS, FAILED, CANCELLED,DONE.
AFFINITY - DCs or nodes
LIMIT - default LIMIT is defined in config.ini (MIGRATE_DETAIL_MAX_ROWS_SIZE 10000) if no LIMIT is provided as an argument

In order to get all the migration processes run in the past for all migration IDs refer to Cassandra table k2migrate.migration_iid_info

</p>
</td>
<td valign="top" width="300pxl">
<p>migrate_list status=‘ALL’</p>
</td>
</tr> 

<tr>
<td valign="top" width="500pxl">
<h6>
cancel batch '&ltmig_id&gt';
</h6>
</td>
<td valign="top" width="400pxl">
   
<p>
Cancels a specific migrate command, using the BATCH command 
Note: The stopjob command can be also used, according to the job created for the migrate by the coordinator node.

</p>
   
</td>
<td valign="top" width="300pxl">
<p>
CANCEL batch ‘161f9717-bd93-4882-a3aa-7b58c1f61b27’;
</p>
</td>
</tr>

<tr>
<td valign="top" width="500pxl">
<h6>
migrate_resume '&ltmig_id&gt';
</h6>
</td>
<td valign="top" width="400pxl">
   
<p>
Resumes a Migrate command by processing all failed or unhandled instances from previous executions.
</p>
   
</td>
<td valign="top" width="300pxl">
<p>
migrate_resume ‘161f9717-bd93-4882-a3aa-7b58c1f61b27’;
</p>
</td>
</tr>
</tbody>
</table>



## migratef Command 

The migratef command enables the migration of a selective list of instances using a function. 

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
<h6>
MigrateF
</h6>
</td>
<td valign="top" width="400pxl">
   
<p>
Use this command to migrate a selective list of instances defined by a function.
   
1) migratef &ltLUT>[@&ltDC&gt].&ltfunction&gt().&ltIG&gt [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_IIDS_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</p>

<p>2) migratef &ltLUT&gt[@&ltDC&gt].&ltfunction&gt() from &ltdb_interface&gt using ('&ltSQL&gt') [WITH [AFFINITY='&ltaffinity&gt'] [ASYNC=true/false] [GENERATE_IIDS_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</p>
<p>  
3) migratef &ltLUT&gt[@&ltDC&gt].&ltfunction&gt().(&ltInstance 1, Instance 2, etc...&gt) [WITH [AFFINITY='&ltaffinity&gt'] [ASYNC=true/false] [GENERATE_IIDS_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</p>
   
</td>
<td valign="top" width="300pxl">
<p>
1) migratef Customer.migrateFtest4().ig20;
</p>
<p>   
2) migratef Customer@DC1.migrateFtest4() from HIS_DB using ('select patient_id from invoice where balance=12894');
</p>
<p>
3) migratef Customer.migrateFtest4().(‘1’,’2’,’3’);
</p>
</td>
</tr>  

</tbody>
</table>

