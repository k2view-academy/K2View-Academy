# Job Commands

The following Job commands are available in the Fabric runtime environment:

## Job Execution Commands 
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

<h5>STARTJOB &ltJOBTYPE&gt NAME='&ltname&gt' [UID='&ltuid&gt'] [AFFINITY='&ltaffinity&gt'] [ARGS='&ltargs&gt'] [EXEC_INTERVAL='&ltexecInterval&gt'];</h5>

</td>
<td valign="top" width="400pxl">

<p>
Starts the Job using arguments:
   
- Affinity, UID and other arguments are optional inputs.
- Jobtype, BROADWAY, PROCESS, USER_JOB
- Name, Job’s name.
- ARGS, a string represeting a JSON that passes additional arguments to the **start Job** command. For example, {"jobName":"jobTest"}.
- EXEC_INTERVAL, definition of Jobs scheduling execution interval that supports three formats:
  - Timestamp, yyyy-MM-dd HH:mm:ss, a one time only timedatetime that schedules a Job's execution. 
  - Time interval, HH:MM:SS to run the Job every X time.
  - Cron, combined **crontab** command. For example, 23 0-20/2 03 12 2. At minutes 23 past every 2nd hour from 0 through 20 on day-of-month 3 and on Tuesday in December.</p>

</td>
<td valign="top" width="300pxl">

<p>STARTJOB USER_JOB NAME=’CUST-TestJob1' UID='CUST-TestJob1' AFFINITY='10.21.1.85' ARGS={"testName":"Test"} EXEC_INTERVAL='00:00:30';</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>STOPJOB &ltJOBTYPE&gt NAME='&ltname&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>
Stops all Jobs matching this name and type.
</p>

</td>
<td valign="top" width="300pxl">

<p>STOPJOB PROCESS NAME='CUST-TestJob2';</p>

</td>
</tr> 

 <tr>
<td valign="top" width="300pxl">

<h5>STOPJOB &ltJOBTYPE&gt NAME='&ltname&gt' UID='&ltuid&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>
Stops a specific Job matching an UID.
</p>

</td>
<td valign="top" width="300pxl">

<p>STOPJOB PROCESS NAME='CUST-TestJob2' UID='CUST-TestJob2';</p>

</td>
</tr> 

 

<tr>
<td valign="top" width="300pxl">

<h5>RESTARTJOB &ltJOBTYPE&gt NAME='&ltname&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>
Restarts all matching Jobs with this name and type.
</p>
</td>
<td valign="top" width="300pxl">

<p>RESTARTJOB USER_JOB NAME='CUST-TestJob1';</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>RESTARTJOB &ltJOBTYPE&gt NAME='&ltname&gt' UID='&ltuid&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>
Restarts a specific Job matching an UID.
</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">

<p>RESTARTJOB USER_JOB NAME='CUST-TestJob1' UID='CUST-TestJob1';</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>RESUMEJOB &ltJOBTYPE&gt NAME='&ltname&gt' UID='&ltuid&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>
Resumes a specific matching Job. This command applies only to an existing Job.
</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">

<p>RESUMEJOB PROCESS NAME='CUST-TestJob2' UID='CUST-TestJob2';</p>

</td>
</tr> 

 <tr>
<td valign="top" width="300pxl">

<h5>UPDATEJOB &ltjobType&gt NAME='&ltname&gt' [UID='&ltuid&gt'] [AFFINITY='&ltaffinity&gt'] [ARGS='&ltargs&gt'] [EXEC_INTERVAL='&ltexecInterval&gt'] [RESET_END_TIME=true/false]</h5>

</td>
<td valign="top" width="400pxl">

<p>
Updates properties of an existing Job:
   
- AFFINITY, combined comma-separated list of DCs and IPs. For example, DC1,DC2...,IP1,IP2...
- EXEC_INTERVAL, definition of Job's scheduling execution interval, supports three formats:
  - Timestamp, yyyy-MM-dd HH:mm:ss a one-time only datetime for scheduling the Job's execution.
  - Time interval, HH:MM:SS, run every X time.
  - Cron, <strong>crontab</strong> command: e.g. 23 0-20/2 03 12 2. At minute 23 past every 2nd hour from 0 through 20 on day of month 3 and on Tuesday in December.
- ARGS, string representing a JSON that passes additional arguments to the update Job command. For example {"userJobName":"userTest"}
- RESET_END_TIME, only when using the recurring CRON job mode:
   - set to TRUE - the next execution immediately.
   - set to FALSE - the next execution as previously scheduled.
   
NOTE that to update a cron job to a one-time job, use EXEC_INTERVAL=''

</p>

</td>
<td valign="top" width="300pxl">

<p>UPDATEJOB USER_JOB NAME=’CUST-TestJob2' UID='CUST-TestJob2' AFFINITY='10.21.1.85' ARGS={"userJobName":"userTest"} EXEC_INTERVAL='00:00:30';
</p>

</td>
</tr> 
</tbody>
</table>



## Job Monitoring Commands

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
<p>
   When days are not provided, returns all active (not archived) Jobs.</p>
   <p>When days are provided, returns the status of all Jobs that have been executed over the last X days, including archived Jobs. 
   </p>

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

<h5>JOBSTATUS USER_JOB</h5>

</td>
<td valign="top" width="400pxl">

<p>Returns the status of all running Jobs that match the given type and name.</p>
<td valign="top" width="300pxl">

<p>JOBSTATUS USER_JOB 'TDM.fnValidateAndRebuildRefTables';</p>
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
</tbody>
</table>



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/05_create_a_new_broadway_job.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/08_jobs_table_fields.md)
