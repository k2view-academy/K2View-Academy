# Batch Commands

The following Batch commands are available in the Fabric Runtime Environment:

*Migrate:*

BATCH *LUT*.('*LUI1*','*LUI2*','*LUI3*','*LUI4*') *FABRIC_COMMAND*="sync_instance LUT.?" with ASYNC='true';


*Broadway:*

BATCH *LUT* *fabric_command*="broadway *LUT*.SampleFlow SampleIID=?" with async=true;


*CDC Republish:*

BATCH *LUT* from *fabric* *fabric_command*="cdc_republish_instance OracleLU.?" with async=true;




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
Start batch process to sync all instances of a Logical Unit:
   
- DC, specify dc name to force the batch process within specified dc, can also be defined on the affinity parameter                                                   - - AFFINITY, list of nodes and DCs to be involved in the batch process command
- JOB_AFFINITY, affinity for the batch process job
- ASYNC, defines if batch process should run on sync or async mode, default is false
- GENERATE_ENTITIES_FIRST, if set to true - generate all entities before starting processing them
- FABRIC_COMMAND, fabric command to be executed by the batch process.  It can be any command that includes one '?' that will represent entity id.
  - for migration,  the following command must be set: "sync_instance <LUT>.?"
  - for broadway flow, ...
  - for CDC republish, ...
- ALLOW_MULTIPLY, set to true to allow multiply executions of the same batch process command (default is false)
- MAX_NODES, The maximum nodes that will be participated in the batch process (randomly nodes)
- MAX_WORKERS_PER_NODE, enables setting a smaller number of maximum workers to run on each node, than the maximum number of workers, defined in the config.ini file (MAX_WORKERS_PER_NODE parameter)./p>

</td>
<td valign="top" width="300pxl">
<p>JOBSTATUS;</p>
<p>JOBSTATUS 2 days ago;</p>
</td>
</tr>  

<tr>
<td valign="top" width="300pxl">

<h5>batch &ltLUT>[@&ltDC&gt].&ltIG&gt fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</h5>
</td>
<td valign="top" width="400pxl">

<p>Batch-processes a subset of Logical Unit Instances, based on the Instance Group specified with the &ltIG&gt parameter</p>

</td>
<td valign="top" width="300pxl">
<p>JOBSTATUS TestJob1;</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>batch &ltLUT&gt[@&ltDC&gt] from &ltdb_interface&gt using ('&ltSQL&gt') fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt' [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</h5>

</td>
<td valign="top" width="400pxl">

<p>Batch process a subset of Logical Unit Instances, based on a query to one of the source interfaces defined in the &ltdb_interface&gt parameter</p>
<td valign="top" width="300pxl">

<p>JOBSTATUS USER_JOB'TDM.fnValidateAndRebuildRefTables';</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>batch &ltLUT>[@&ltDC&gt] from fabric fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]];</h5>

</td>
<td valign="top" width="400pxl">

<p>Batch-processes a subset of Logical Unit Instances based on existing instances in fabric from entity table </p>
</td>
<td valign="top" width="300pxl">

<p>JOBSTATUS PROCESS TestJob2 WITH UID='CUST-TestJob2';</p>

</td>
</tr> 


<tr>
<td valign="top" width="300pxl">

<h5>batch &ltLUT>[@&ltDC&gt].(&ltinstance 1,instance 2,...&gt) fabric_command='&ltfabric command&gt ?' [WITH [AFFINITY='&ltaffinity&gt'] [JOB_AFFINITY='&ltjob affinity&gt'] [ASYNC=true/false] [GENERATE_ENTITIES_FIRST=true/false] [ALLOW_MULTIPLY=true/false] [MAX_NODES=&ltnumber&gt] [MAX_WORKERS_PER_NODE=&ltnumber&gt]]</h5>

</td>
<td valign="top" width="400pxl">

<p>Batch-processes a subset of Logical Unit Instances, based on list of instances defined in the &ltinstance 1,instance 2,...&gt parameters' list</p>
</td>
<td valign="top" width="300pxl">

<p>JOBSTATUS PROCESS TestJob2 WITH UID='CUST-TestJob2';</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">
<h5>batch_details '&ltbatch id&gt' [STATUS='&ltstatus&gt'] [ENTITIES='&ltentity 1,entity 2,...&gt'] [AFFINITY='&ltAffinity&gt'] [LIMIT=&ltlimit&gt] [SORT_BY_PROCESS_TIME=&lttrue/false&gt]</h5>

</td>
<td valign="top" width="400pxl">

<p>Show instances status for a given batch process id
- STATUS, shows one of the following states: WAITING, COMPLETED, FAILED
- ENTITIES, lists of entities separated by a comma
- AFFINITY, DCs or nodes
- SORT_BY_PROCESS_TIME, if true, shows only the entities with the highest process time. If set, ignore all other parameters
- LIMIT, default LIMIT is defined in config.ini if no LIMIT is provided as an argument </p>
</td>
<td valign="top" width="300pxl">
<p>JOBSTATUS PROCESS TestJob2 WITH UID='CUST-TestJob2';</p>
</td>
</tr> 


<tr>
<td valign="top" width="300pxl">
<h5>batch_in_process filter='&ltfilter regex&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>Lists of all batch processes at work and returns the following information: node id, batch process id, entity id, LU type, time at work (ms), exeid, command|
- filter, must be a regex compatible argument
</p>
</td>
<td valign="top" width="300pxl">
<p>JOBSTATUS PROCESS TestJob2 WITH UID='CUST-TestJob2';</p>
</td>
</tr> 



<tr>
<td valign="top" width="300pxl">
<h5>batch_list [STATUS='&ltstatus&gt' [FROM_DATE='&ltfrom date&gt' [TO_DATE='&ltto date&gt']] [FILTER=&ltfilter criteria&gt]</h5>

</td>
<td valign="top" width="400pxl">

<p>Lists all active batch processes if no arguments provided and their respective statuses: NEW, GENERATE_IID_LIST, IN_PROGRESS, FAILED, CANCELLED, DONE, ALL:
   - FROM/TO_DATE - support DATE_FORMAT/DATETIME_FORMAT, according to the configuration in config.ini
   - FILTER - filtering the batch processes. The filter parameter needs to be populated by a String, included in the Fabric Command of the batch process (note: the filter supports regex).
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

<p>Resumes a previous batch process by re-processing all failed or non-handled entities if the batch process was not completed. Otherwise, it only retries failed entities. 
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

<p>This report will bring the summary information in table structure format for the following levels:*node,* DC,* Cluster                                              
</p>
</td>
<td valign="top" width="300pxl">
<p>
  
</p>
</td>
</tr> 
