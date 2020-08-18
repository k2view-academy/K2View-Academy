# Creating a New Job

Jobs are defined in the Fabric Studio and can be saved to a project file and be deployed to the Fabric Server. A Job can be a Java function or a simple command.

# How Do I Create a New User Job?

1. Go to the **Project Tree** > **LUT** > **Java** > **Utilities** and right click **New Function** to display the **Function Editor** window.

   <img src="/articles/20_jobs_and_batch_services/images/03_jobs_and_batch_services_create_a_job_userjob.PNG">     

2. Write the **User Job function** either:
   -    In the **Function Editor** window.
   -    Using **IntelliJ**.
3. Set the **Function Type** to the **User Job** value in the right panel. 

   Once the job is triggered, the following Java user code writes a line into a new file job_test.txt every second until the counter test reaches the value 5. The file is located in the Fabric Home directory. 

```java
//writing into a file
int test = 1;

while (test<5 && !isAborted()){
	test=test+1;
	sleep(1000);
	FileWriter myWriter = new FileWriter("job_test.txt", true);
	myWriter.write("TEST Number: "+ test + "::-> " + 10*test + " seconds have been going on since start/n");
	myWriter.close();
	}
```
  
4. Name and save the **function**.

   <img src="/articles/20_jobs_and_batch_services/images/04_jobs_and_batch_services_create_a_job_userjob.PNG">
   

5. Go to the **Project Tree** > your **LUT** > **Jobs** > **User Jobs**.

   <img src="/articles/20_jobs_and_batch_services/images/05_jobs_and_batch_services_create_a_job_userjob.PNG">  
   

6. In the **Job table**, enter the values pertaining to the Job.

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
<p>There are three schedule modules:</p>
<ul class="unchanged rich-diff-level-one">
<li class="unchanged">
<p class="unchanged">CRON, runs the Job according to the frequency described in the Cron description tab. <br />For example, set the execution to:&nbsp;<code>30 0/2 8-18 5 3 ? 2021&nbsp;</code>at 30 seconds past the minute (30), every 2 minutes (0/2), between 08:00 and 18:59 (8-18), on day 5 of the month (5), only in March (3), only in year 2021 (2021).</p>
</li>
<li class="unchanged">
<p class="unchanged">TimeStamp, schedules a Job to run once on a given date and time. For example, set the execution to&nbsp;<code>2022-08-20 10:32:12.</code></p>
</li>
<li class="unchanged">
<p class="unchanged">Time Interval, schedules the Job to run at the frequency specified in the&nbsp;<strong>Execute Every</strong>&nbsp;field: For example, set the execution to:&nbsp;<code>10.11:22:33.</code>&nbsp;the jobT runs every 10 days, 11 hours, 22 minutes, 33 seconds. If the time interval is set to 00:00:00, the job runs once automatically.</p>
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
<p class="unchanged rich-diff-level-one">Example:&nbsp;<code>AFFINITY=&rsquo;DC1&rsquo;, &rsquo;10.21.1.121</code></p>
</td>
</tr>
</tbody>
</table>
<p class="unchanged rich-diff-level-one">&nbsp;</p>

## How Do I Create a New Process Job?
Process Jobs are batch files or scripts stored in the Fabric server and triggered manually.
1. Create a bash script and save it in /home/k2view/ directory.
2. Invoke the **startjob** command to trigger the job with the relevant parameters. 
   For example, save the following code into /home/k2view/echoArg.sh

```bash
#!/bin/bash
echo "Total arguments : $#"
echo "1st Argument = $1"
echo "2nd argument = $2"
```

   -  Go to the Fabric runtime command line and execute the following command:
```startjob process NAME='/home/k2view/echoArg.sh' UID='processJobtest' ARGS='{"0":"ARG 1 value","1":"ARG 2 value"}' EXEC_INTERVAL='00:00:03';```

   Where:
   
  - Process, defines the type of Job; in this case a process job.
  - ID, defines the unique name of the processed job.
  - ARGS, defines a list of parameters to be parsed to the script when executed.
  - INTERVAL, refers to the frequency of the job's occurence; in this case every 3 seconds.


### How Do I Create a New Broadway Job?
The Fabric Jobs mechanism also enables running a Broadway flow.
Set the **Job** type to **broadway_job** and the name of the flow with a list of its arguments.

Example: 
```startjob broadway_job name='<lu>.<flow>' [args='{"key":"value"}'];```

### How Do I Create a New CDC Job?
Fabric can execute CDC Jobs (Change Data Capture) to notify external systems about data changes. 
Jobs can also execute cross-instance searches using Fabric's Search capability.
 

[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/04_jobs_commands.md)


