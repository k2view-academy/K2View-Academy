# Jobs Management & Configuration

Fabric provides a range of tools and informative data to manage jobs and configure Jobs management processes.

### How Do I Monitor Jobs at Fabric cluster level ?

As described in the previous article, you can run the JOBSTATUS command from your fabric runtime environment to collect the full list of scheduled jobs for any given LU.

Example:

```jobstatus 90 days ago;```

Result:
<img src="/articles/20_jobs_and_batch_services/images/06_jobs_and_batch_services_create_a_job_jobstatus.PNG"></img>

- *Type:* Refers to the job's type (user_job, process, broadway)
- *Status:* One of the following statuses defined [here](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md#fabric-jobs-status).
- *Time:* *Creation Time*, *Start Time* and *End Time* fields will provide the full timeline of the job's lifecycle.
- *Affinity:* Displays the IP or name of the node/cluster or DC in charge of the execution of the Job.
- *Next Run:* Displays the timestamp for the execution of a job or the next scheduled execution of a recurring job
- *Notes:* Displays messages relevant to the job
- *isArchived:* Is set automatically to *true* once the last run of a job has reached one of the following states: *terminated*, *failed*, *processed*
- *Ownership Candidates Num:* Displays the number of Fabric nodes that can handle the job.



### How Do I Monitor Jobs from Cassandra Keyspace ?
Each job is recorded in the k2_jobs table of the k2system keyspace.
To do this, open up Cassandra CQLSH with the appropriate credentials, and execute a Select query:
``` select * from k2system.k2tables ```

The table contains the following fields:
type, name, uid, affinity, archived, arguments, creation_time, end_time, error_msg, execution_interval, output, start_time, status, tries, worker_id

<img src="/articles/20_jobs_and_batch_services/images/07_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>
<img src="/articles/20_jobs_and_batch_services/images/08_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>
<img src="/articles/20_jobs_and_batch_services/images/09_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>
<img src="/articles/20_jobs_and_batch_services/images/10_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>
<img src="/articles/20_jobs_and_batch_services/images/11_jobs_and_batch_services_create_a_job_k2JobsTable.PNG"></img>













