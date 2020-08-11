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

<h5>JOBSTATUS &ltJOBTYPE&gt</h5>
</td>
<td valign="top" width="400pxl">

<p>Returns the status of all Jobs running according to the <strong>jobType</strong>.</p>

</td>
<td valign="top" width="300pxl">
<p>JOBSTATUS TestJob1;</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>JOBSTATUS &ltJOBTYPE&gt '&ltNAME&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>Returns the status of all running Jobs that match the given type and name.</p>
<td valign="top" width="300pxl">

<p>JOBSTATUS USER_JOB'TDM.fnValidateAndRebuildRefTables';</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>JOBSTATUS &ltJOBTYPE&gt '&ltNAME&gt' WITH UID='&ltUID&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>Returns the status of all Jobs that match the give type, name and UID.</p>
</td>
<td valign="top" width="300pxl">

<p>JOBSTATUS PROCESS TestJob2 WITH UID='CUST-TestJob2';</p>

</td>
</tr> 
