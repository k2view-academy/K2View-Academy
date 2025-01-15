# How Do I Execute a New Broadway Job ?

## From Broadway Flows

As explained [here](03_create_a_new_user_job.md#how-do-i-create-a-job-using-broadway-), Broadway jobs are triggered from the **deploy.flow** of the required LU or another Broadway flow using the **BroadwayJob** actor. 



## Fabric Command

You can run the Broadway job by using the [startjob](/articles/20_jobs_and_batch_services/07_jobs_commands.md#startjob-jobtype-namename-uiduid-affinityaffinity-argsargs-exec_intervalexecinterval) Fabric command and setting the job type to **BROADWAY_JOB**. The NAME parameter should include the name of the Broadway flow with a list of its arguments.

**Syntax:**

```
startjob BROADWAY_JOB NAME='<lu_name.flow_name>' [UID='<uid>'] [AFFINITY='<affinity>'] [ARGS='<args>'] [EXEC_INTERVAL='<execInterval>'];
```

where ARGS consists of a JSON-type format string containing the parameters to be parsed to Broadway: 

```
{"first_param":"first_value","second_param":"second_value"}
```

and where EXEC_INTERVAL describes the job schedule pattern. 

**Example:**

~~~
startjob broadway_job name='Customer.Flow1' ARGS={a:10,b:20} EXEC_INTERVAL='00:00:10';
~~~



## 






[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/04_create_a_new_process_job.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/07_jobs_commands.md)
