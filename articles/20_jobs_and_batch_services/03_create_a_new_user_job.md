# Creating a New User Job

Jobs are defined in the Fabric Studio and can be saved to a project file and be deployed to the Fabric Server. A Job can be a Java function or a simple command.

## How Do I Create a New User Job?

### Step 1. 
Go to the **Project Tree** > **LU** > **Java** > **Category** and right click **New Function** to display the **Function Editor** window.

### Step 2. 
Write the **User Job function** either from:
   -    the **Function Editor** window.
   -    **IntelliJ** IDE.
   
```java
//writing into a file;

while (test<5 && !isAborted()){
	test=test+1;
	sleep(10000);
	inSecTime=test*10; // value returned by user job function
	FileWriter myWriter = new FileWriter("job_test4.txt",true);
	myWriter.write("Test Number: "+ test +"::->" + 10*test + " seconds have passed since ...");
	myWriter.close();
	}
```

### Step 3. 
Set the **Function Type** to the **User Job** value in the right panel. 
Once the job is triggered, the following Java user code writes a line into a new file job_test.txt every second until the counter test reaches the value 5. The output file is located in the Fabric Home directory. 

Note that the loop control parameter (variable *test*) is defined as an input parameter of the User Job function, and that a second variable *inSecTime* is also defined as an output parameter. As such, its value is recorded in the *argument* field of the k2_jobs table in the k2system keyspace.

Name and save the **function**.

<img src="/articles/20_jobs_and_batch_services/images/04_jobs_and_batch_services_create_a_job_userjob.PNG">
   

### Step 4. 
Go to the **Project Tree** > your **LU** > **Jobs**.

<img src="/articles/20_jobs_and_batch_services/images/05_jobs_and_batch_services_create_a_job_userjob.PNG">  
   
 
In the **Job table**, enter the values pertaining to the Job.

<table style="width: 900px; height: 193px;">
<tbody>
<tr>
<td style="width: 300px"><strong>Method</strong></td>
<td style="width: 600px">Where the name of the User Job Function defined in Step 4 is associated to the Job.</td>
</tr>
<tr>
<td style="width: 300px;"><strong>Unique Job Name</strong></td>
<td style="width: 600px;">Where the unique name for the Job service is defined and used for all manual or system operations executed on this job.</td>
</tr>
<tr>
<td style="width: 300px;"><strong>Execution Mode</strong></td>
<td style="width: 600px;">Can be set to either&nbsp;<strong>automatically</strong>&nbsp;or&nbsp;<strong>manually</strong>&nbsp;depending on whether the Job service is started from the Command Line or is managed by Fabric.</td>
</tr>
<tr>
<td style="width: 300px;"><strong>Active</strong></td>
<td style="width: 600px;">When checked, the Job is run and deployed.</td>
</tr>
<tr>
<td style="width: 300px;"><strong>Schedule Type</strong></td>
<td style="width: 600px;">
<p>There are three schedule modes:</p>
<ul class="unchanged rich-diff-level-one">
<li class="unchanged">
<p class="unchanged">CRON, runs the Job according to the frequency described in the Cron description tab. <br />For example, set the execution to:&nbsp;<code>30 0/2 8-18 5 3 ? 2021&nbsp;</code>at 30 seconds past the minute (30), every 2 minutes (0/2), between 08:00 and 18:59 (8-18), on day 5 of the month (5), only in March (3), only in year 2021 (2021).</p>
</li>
<li class="unchanged">
<p class="unchanged">TimeStamp, schedules a Job to run once on a given date and time. For example, set the execution to&nbsp;<code>2022-08-20 10:32:12.</code></p>
</li>
<li class="unchanged">
<p class="unchanged">Time Interval, schedules the Job to run at the frequency specified in the&nbsp;<strong>Execute Every</strong>&nbsp;field: For example, set the execution to:&nbsp;<code>10.11:22:33.</code>&nbsp;the jobT runs every 10 days, 11 hours, 22 minutes, 33 seconds. If the time interval is set to 00:00:00, the job runs once automatically.</p>
<p> It is important to note that if a scheduled job is started manually (before its scheduled occurence) then the CRON-scheduled occurence will still start on time, unless the previous manual occurence of the job, is still running.</p>
<p> In the Time Interval case whereby a job is set to run every x minutes, the next occurence will be skipped and will happen again automatically x minutes after the end of the manually triggered job instance. In that case there will be no collusion between the manual and automated occurences.</p>	
</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 300px;"><strong>Affinity</strong></td>
<td style="width: 600px;">
<p class="unchanged rich-diff-level-one">Where the details of the node assigned to the job are entered. Affinity can be set using either one of the following options:</p>
<ul class="unchanged rich-diff-level-one">
<li class="unchanged">IP address of a Fabric node.</li>
<li class="unchanged">Data Center (DC) Name.</li>
<li class="unchanged">Node identifier, a logical identifier of Fabric node or a group of several Fabric nodes.</li>
</ul>
<p class="unchanged rich-diff-level-one">Example:&nbsp;<code>AFFINITY=&rsquo;DC1&rsquo;, &rsquo;10.21.1.121&rsquo;</code></p>
</td>
</tr>

<tr>
<td style="width: 300px;"><strong>Input parameters</strong></td>
<td style="width: 600px;">
<p class="unchanged rich-diff-level-one">Where the values of the input parameters are initialized. In this case the variable *test* is set to 0</p>
<p class="unchanged rich-diff-level-one">(The output parameter value is recorded in the argument field of the k2_jobs table in the k2system keyspace.)</p>
</td>
</tr>
	
	
</tbody>
</table>
<p class="unchanged rich-diff-level-one">&nbsp;</p>



### Step 5. 
Right-click on the **LU** in the **Project Tree**, deploy and check for the file in the **Fabric Home directory**.





[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/04_create_a_new_process_job.md)


