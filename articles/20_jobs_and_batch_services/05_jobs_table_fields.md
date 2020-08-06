# Jobs Management & Configuration

Fabric provides a range of tools and informative data to manage jobs and configure Jobs management processes.

### How Do I Monitor Jobs at Fabric session level ?

As described in the previous article, you can run the JOBSTATUS command from your fabric runtime environment to collect the full list of scheduled jobs for any given LU.

Example:

```jobstatus 90 days ago;```

Result:
<img src="/articles/20_jobs_and_batch_services/images/06_jobs_and_batch_services_create_a_job_jobstatus.PNG"></img>

Type: Refers to the job's type (user_job, process, broadway)
Status: One of the following statuses defined [here](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md#fabric-jobs-status).
Time: *Creation Time*, *Start Time* and *End Time* fields will provide the full timeline of the job's lifecycle.
Affinity: Displays the IP or name of the node/cluster or DC in charge of the execution of the Job.
Next Run: Displays the timestamp for the execution of a job or the next scheduled execution of a recurring job
Notes: 






