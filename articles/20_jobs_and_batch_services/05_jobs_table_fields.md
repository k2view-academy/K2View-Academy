# Jobs Management & Configuration

Fabric provides a range of tools and informative data for configuring and managing Jobs and their processes.

## How Do I Monitor Jobs at a Fabric Cluster Level?
Run the JOBSTATUS command from your fabric runtime environment to get a full list of scheduled Jobs for any given LU.

Example:

```jobstatus 90 days ago;```

Result:
<img src="/articles/20_jobs_and_batch_services/images/06_jobs_and_batch_services_create_a_job_jobstatus.PNG"></img>

<table style="width: 592px;">
<tbody>
<tr>
<td style="width: 144.091px;">Type</td>
<td style="width: 444.909px;">The Job's type like user_job, process or Broadway</td>
</tr>
<tr>
<td style="width: 144.091px;">Status</td>
<td style="width: 444.909px;">One of the following statuses defined&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP3_20_Jobs_and_Batches_Services_Greg/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md#fabric-jobs-status">here</a></td>
</tr>
<tr>
<td style="width: 144.091px;">Time</td>
<td style="width: 444.909px;">Provides the timeline of the Job's lifecycle in the Creation Time,&nbsp;Start Time&nbsp;and&nbsp;End Time&nbsp;fields.</td>
</tr>
<tr>
<td style="width: 144.091px;">Affinity</td>
<td style="width: 444.909px;">Displays the IP or name of the node/cluster or DC responsible for the execution of the Job.</td>
</tr>
<tr>
<td style="width: 144.091px;">Next Run</td>
<td style="width: 444.909px;">
<p>Displays the timestamp for the execution of a Job or the next scheduled execution of a recurring Job.</p>
</td>
</tr>
<tr>
<td style="width: 144.091px;">Notes</td>
<td style="width: 444.909px;">
<p>Displays messages relevant to the job.</p>
</td>
</tr>
<tr>
<td style="width: 144.091px;">&nbsp;isArchived</td>
<td style="width: 444.909px;">Is set automatically to&nbsp;True&nbsp;once the last run of a Job has reached a&nbsp;terminated,&nbsp;failed or&nbsp;processed state.</td>
</tr>
<tr>
<td style="width: 144.091px;">Ownership Candidates Num</td>
<td style="width: 444.909px;">Displays the number of Fabric nodes that can handle the job.</td>
</tr>
</tbody>
</table>
 
For more information, go to:

## How Do I Monitor Jobs from the Cassandra Keyspace?
Each job is recorded in the k2_jobs table of the k2system keyspace.
Open Cassandra CQLSH using the relevant credentials, and execute a Selct query:
``` select * from k2system.k2tables ```

The table contains the following fields:
type, name, uid, affinity, archived, arguments, creation_time, end_time, error_msg, execution_interval, output, start_time, status, tries, worker_id

<img src="/articles/20_jobs_and_batch_services/images/07_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>
<img src="/articles/20_jobs_and_batch_services/images/08_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>



### Worker_ID
<img src="/articles/20_jobs_and_batch_services/images/10_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>



### Error_msg
<img src="/articles/20_jobs_and_batch_services/images/11_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>

[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/04_jobs_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/06_jobs_configuration.md)
