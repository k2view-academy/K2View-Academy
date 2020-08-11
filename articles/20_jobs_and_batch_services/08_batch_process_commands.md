# Batch Commands

The following Batch commands are available in the Fabric Runtime Environment:

*Migrate:*

BATCH OracleLU.('1','2','3','4') FABRIC_COMMAND="sync_instance OracleLU.?" with ASYNC='true';


*Broadway:*

BATCH OracleLU fabric_command="broadway OracleLU.SampleFlow SampleIID=?" with async=true;


*CDC Republish:*

BATCH OracleLU from fabric fabric_command="cdc_republish_instance OracleLU.?" with async=true;




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
<h5>JOBSTATUS [x days ago]</h5>
</td>
<td valign="top" width="400pxl">
<p>When days are provided, returns the status of all Jobs that have been created over the last X days, including archived Jobs. 
   
   When days are not provided, returns all active (not archived) Jobs.</p>

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
