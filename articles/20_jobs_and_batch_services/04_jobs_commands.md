## Jobs Commands

 The following Jobs commands are available:
The following table lists the GET commands:

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
<p>When days are provided- returns the status for all jobs that were created during the last X days, including archived jobs. When daysare not provided – returns all active (not archived) jobs.</p>
<p>&nbsp;</p>
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

<p>Returns the status of all running jobs according to jobType.</p>
<p>&nbsp;</p>
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

<p>Returns the status of all running jobs that match the given type and name.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">

<p>JOBSTATUS USER_JOB'TDM.fnValidateAndRebuildRefTables';</p>
</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>JOBSTATUS &ltJOBTYPE&gt '&ltNAME&gt' WITH UID='&ltUID&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>Returns the status of all jobs that match the give type, name and uid.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">

<p>JOBSTATUS PROCESS TestJob2 WITH UID='CUST-TestJob2';</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>STARTJOB &ltJOBTYPE&gt NAME='&ltname&gt' [UID='&ltuid&gt'] [AFFINITY='&ltaffinity&gt'] [ARGS='&ltargs&gt'] [EXEC_INTERVAL='&ltexecInterval&gt'];</h5>

</td>
<td valign="top" width="400pxl">

<p>
Starts the job using arguments.
- Affinity, UID and other Arguments are optional inputs.
- Jobtype – BROADWAY, PROCESS, USER_JOB
- Name- job’s name
- * args: String which represents a json to pass additional arguments to the start job command. e.g. {"jobName":"jobTest"}
- Affinity: Comma separated list of DCs, IPs and Node IDs
- EXEC_INTERVAL: definition of job scheduling execution interval, supports three formats:
    timestamp - 'yyyy-MM-dd HH:mm:ss' - a datetime to schedule job execution - one time
    Time interval - 'HH:MM:SS' - run every X time
    Cron - crontab command combined. e.g. DC1,DC2...,IP1,IP2...</p>
<p>&nbsp;</p>

</td>
<td valign="top" width="300pxl">

<p>STARTJOB USERJOB NAME=’CUST-TestJob1' UID='CUST-TestJob1' AFFINITY='10.21.1.85' ARGS={"testName":"Test"} EXEC_INTERVAL='00:00:30';</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>STOPJOB &ltJOBTYPE&gt NAME='&ltname&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>
Stops all matching jobs with this name and type.
</p>
<p>&nbsp;</p>

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
Stops a specific job matching a UID.
</p>
<p>&nbsp;</p>
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
Restarts all matching jobs with this name and type.
</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">

<p>RESTARTJOB USERJOB NAME='CUST-TestJob1';</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>RESTARTJOB &ltJOBTYPE&gt NAME='&ltname&gt' UID='&ltuid&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>
Restarts a specific job matching a UID.
</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">

<p>RESTARTJOB USERJOB NAME='CUST-TestJob1' UID='CUST-TestJob1';</p>

</td>
</tr> 

<tr>
<td valign="top" width="300pxl">

<h5>RESUMEJOB &ltJOBTYPE&gt NAME='&ltname&gt' UID='&ltuid&gt'</h5>

</td>
<td valign="top" width="400pxl">

<p>
Resumes a specific matching job. This command applies only to an existing job.
</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">

<p>RESUMEJOB PROCESS NAME='CUST-TestJob2' UID='CUST-TestJob2';</p>

</td>
</tr> 

 <tr>
<td valign="top" width="300pxl">

<h5>UPDATE &ltjobType&gt NAME='&ltname&gt' [UID='&ltuid&gt'] [AFFINITY='&ltaffinity&gt'] [ARGS='&ltargs&gt'] [EXEC_INTERVAL='&ltexecInterval&gt'] [RESET_END_TIME=true/false]</h5>

</td>
<td valign="top" width="400pxl">

<p>
Updates properties of existing job.
- AFFINITY: Comma-separated list of DCs and IPs combined. e.g. DC1,DC2...,IP1,IP2...
- *EXEC_INTERVAL: definition of job scheduling execution interval, supports three formats:
    timestamp - 'yyyy-MM-dd HH:mm:ss' - a datetime to schedule job execution - one time
    Time interval - 'HH:MM:SS' - run every X time
    Cron - crontab command
- *ARGS: String which represents a json to pass additional arguments to the update job command. e.g. {"userJobName":"userTest"}

NOTE: in order to update cron job to one time job, use EXEC_INTERVAL=''
</p>
<p>&nbsp;</p>

</td>
<td valign="top" width="300pxl">

<p>UPDATEJOB USERJOB NAME=’CUST-TestJob2' UID='CUST-TestJob2' AFFINITY='10.21.1.85' ARGS={"userJobName":"userTest"} EXEC_INTERVAL='00:00:30';
</p>

</td>
</tr> 

</tbody>
</table>
