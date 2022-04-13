# Creating New User & Broadway Jobs

Jobs are defined in the Fabric Studio and can be saved to a project file and be deployed to the Fabric Server. A Job can be a Java function or a simple command.

## How Do I Create a New User Job using Java code?

### Step 1  
Go to the **Project Tree** > **LU** > **Java** > **Category** and right click **New Function** to display the **Function Editor** window.

### Step 2  
Write the **User Job function** either from:
   -    the **Function Editor** window
        
        or
   
   -    the **IntelliJ** IDE.
   
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

### Step 3  
Set the **Function Type** to the **User Job** value in the right panel. 
Once the job is triggered, the following Java user code writes a line into a new file job_test.txt every second until the counter test reaches the value 5. The output file is located in the Fabric Home directory. 

<img src="/articles/20_jobs_and_batch_services/images/04_jobs_and_batch_services_create_a_job_userjob.PNG">

Note that the loop control parameter (variable *test*) is defined as an input parameter of the User Job function, and that a second variable *inSecTime* is also defined as an output parameter. As such, its value is recorded in the *argument* field of the k2_jobs table in the k2system keyspace.

Name and save the **function**.
   

### Step 4  
Go to the **Project Tree** > your **LU** > **Jobs**.

<img src="/articles/20_jobs_and_batch_services/images/05_jobs_and_batch_services_create_a_job_userjob.PNG">  
   
Jobs can be created also as References regardless of the specific LU. In the **Job table**, enter the values pertaining to the Job.

<table style="width: 900px; height: 193px;">
<tbody>
<tr>
<td style="width: 300px"><strong>Method</strong></td>
<td style="width: 600px">The name of the User Job Function (defined in Step 4) associated with the Job.</td>
</tr>
<tr>
<td style="width: 300px;"><strong>Unique Job Name</strong></td>
<td style="width: 600px;">The unique name of the Job service used for all manual or system operations executed on this job.</td>
</tr>
<tr>
<td style="width: 300px;"><strong>Execution Mode</strong></td>
<td style="width: 600px;">Can be set either <strong>automatically</strong> or <strong>manually</strong> depending on whether the Job service is started from the Command Line or job will start automatically upon deployment.</td>
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
<p class="unchanged">Time Interval, schedules the Job to run at the frequency specified in the&nbsp;<strong>Execute Every</strong>&nbsp;field: For example, set the execution to:&nbsp;<code>10.11:22:33.</code>&nbsp; . The job runs every 10 days, 11 hours, 22 minutes, 33 seconds. If the time interval is set to 00:00:00, the job runs once automatically.</p>
<p> It is important to note that if a scheduled job is started manually (before its scheduled occurance) then its next scheduled occurance will still start on time, unless the previous manual occurance of the job is still running.</p>	
</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 300px;"><strong>Affinity</strong></td>
<td style="width: 600px;">
<p class="unchanged rich-diff-level-one">The details of the node assigned to the job. Affinity can be set using either one of the following options:</p>
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
<p class="unchanged rich-diff-level-one">Defines where the values of the input parameters are initialized. In this case the variable *test* is set to 0.</p>
<p class="unchanged rich-diff-level-one">(The output parameter value is recorded in the argument field of the k2_jobs table in the k2system keyspace.)</p>
</td>
</tr>
	
	
</tbody>
</table>
<p class="unchanged rich-diff-level-one">&nbsp;</p>



### Step 5  
Right click the **LU** in the **Project Tree**, deploy and then search for the file in the **Fabric Home directory**.


## How Do I Create a Broadway Job using the BroadwayJob Actor ?

The *BroadwayJob* actor provides the ability to trigger a *Fabric job* that will in turn execute another broadway flow once or multiple times depending upon the configuration of the job.

To use this capability, simply select the actor from the **Add Actors To Stage** menu in the Broadway Flow panel, as illustrated below:

<img src="/articles/20_jobs_and_batch_services/images/37_jobs_and_batch_services_broadwayJobActor1.PNG">


The following parameters are to be filled in the properties tab:
- Name: name of the flow to be triggered by this actor. 
- UID: unique ID for this job.

Note that both name and UID can either be chosen by the user, attributed automatically by Fabric, or parsed from a previous actor.

- Schedule: the execution frequency of the job, which can be either one of the following:
	- Immediate
	- Interval
	- Date/Time
	- CRON schedule

- Affinity: this sets which node/DC name IP address is to be used to run the Broadway job.

- Params: This refers to the arguments that can be parsed to the Broadway flow. For example, multiple parameters can be parsed as a key/value object from an external link.
 
Please refer to this [section](/articles/20_jobs_and_batch_services/03_create_a_new_user_job.md#step-4) in order to learn how to setup the schedule type and the affinity appropriately.

[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/04_create_a_new_process_job.md)


