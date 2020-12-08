# Jobs Monitoring

Fabric provides a range of tools and informative data for configuring and managing Jobs and their processes.

## How Do I Monitor Jobs at a Fabric Cluster Level?


### JOBSTATUS

Run the JOBSTATUS command from your fabric runtime environment to get a full list of scheduled Jobs for any given LU.

Example:

```jobstatus 90 days ago;```

Result:

```
|Type     |Name                |UID                                    |Status    |Creation Time      |Start Time         |End Time           |Affinity           |Is Archived|Next Run                   |Ownership Candidates Num|Notes|Owner       |
+---------+--------------------+---------------------------------------+----------+-------------------+-------------------+-------------------+-------------------+-----------+---------------------------+------------------------+-----+------------+
|USER_JOB |Customer.numberOfIDs|checkNumberIDs                         |SCHEDULED |2020 08 20 11:10:50|2020 08 20 14:01:53|2020 08 20 13:56:13|                   |true       |no more execution scheduled|1                       |     |fabric_debug|
|USER_JOB |Customer.testJOB2   |9704d0e3-7247-4b6c-83a7-2c9028dea496   |TERMINATED|2020 07 30 11:05:59|2020 07 30 12:14:02|2020 07 30 12:15:31|ANY                |true       |no more execution scheduled|0                       |     |fabric_debug|
|USER_JOB |Customer.testJOB2   |testJOB2                               |FAILED    |2020 08 04 16:05:58|2020 08 19 13:50:45|1970 01 01 00:00:00|ANY                |true       |no more execution scheduled|0                       |     |fabric_debug|
|USER_JOB |Customer.testJob    |c5163cf1-2f4b-434d-8508-40b53b303ee6   |PROCESSED |2020 08 02 06:25:12|2020 08 03 13:30:37|2020 08 04 10:18:43|ANY                |true       |no more execution scheduled|0                       |     |fabric_debug|
|USER_JOB |Customer.testJob    |c5844330-07c8-4448-967e-da9d0a64c22e   |TERMINATED|2020 07 28 15:39:13|2020 07 30 08:49:06|2020 07 30 08:49:07|ANY                |true       |no more execution scheduled|0                       |     |fabric_debug|
|USER_JOB |Customer.testJob    |testJOB1                               |RESTART   |2020 08 20 11:10:50|2020 08 13 10:02:34|1970 01 01 00:00:00|’DC1’, ’10.21.1.121|false      |2021-03-05 08:00:30        |0                       |     |            |
|USER_JOB |Customer.testJob    |testJob2G                              |TERMINATED|2020 07 30 10:59:46|2020 07 30 10:59:48|2020 07 30 12:08:09|ANY                |true       |no more execution scheduled|0                       |     |fabric_debug|
|USER_JOB |Customer.testJob    |testJobGreg                            |TERMINATED|2020 07 28 15:26:04|2020 07 28 15:38:32|2020 07 28 15:38:33|ANY                |true       |no more execution scheduled|0                       |     |fabric_debug|


(8 rows)

```

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
<p>Displays the last error message relevant to the job in case the job was failed.</p>
</td>
</tr>
<tr>
<td style="width: 144.091px;">&nbsp;isArchived</td>
<td style="width: 444.909px;">Is set automatically to&nbsp;True&nbsp;once the last run of a Job has reached a&nbsp;terminated,&nbsp;failed or&nbsp;processed state. When not specified, the TTL default value will be used (default set to 30 days).</td>
</tr>
<tr>
<td style="width: 144.091px;">Owner</td>
<td style="width: 444.909px;">Displays the name of the node handling the job.</td>
</tr>
</tbody>
</table>
 
### PS & KILL Commands

Fabric Process Commands can also be used as described [here](/articles/02_fabric_architecture/04_fabric_commands.md#ps-and-kill-commands).





[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/07_jobs_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/09_jobs_configuration.md)
