# **Fabric Jobs Flow** 

As for most Fabric entities, a job's flow consists of the following 3 stages: DEFINE, CONFIGURE and DEPLOY

- DEFINE: a function needs to be defined as a job function under a specific LU - it needs to be defined and attached to a specific LU type under the LU Utilities folder.
- CONFIGURE: a new job entry must be added to the jobs'table that features under the LU Type tree, with a number of configuration parameters and a method, which is basically one of the functions previosuly defined as a job function
- DEPLOY: the LU, its associated job functions and the jobs table are processed by the the Fabric Node onto which the deployment was performed. The job will be triggered either automatically or manually depending on the parameter specified during the configuration phase.


# **Fabric Jobs Status** 

All Fabric jobs will go through different stages, each of which indicates a specific step in the job's handling process:

***Scheduled***
This stage is reserved for the following types of schedules:

    ****Time interval**** – when a recurring job is scheduled to run at every given time. If the job is scheduled to run every 60 minutes, it will do so but coubnt 60 minutes from the time the previous occurrence of the job finished.

    ****Timestamp**** – when a specific job is scheduled to run only once at a given date and time

    ****Cron**** – when a series of the same job needs to be run according to the crontab scheduling format. Note that the actual execution of the job will be handled by Fabric's own scheduler.

***Waiting***
When a job is in this particular stage, it is waiting to be executed by the appropriate fabric node to which it will be allocated. 


For the previous stages, it is important to note that if a (same) job is redeployed, and already registered by Fabric, i.e. the row already exists in the Jobs table, then it will not be overridden.
This means that if a recurring job is redeployed after it has been run one or more times, the schedule for the next run will not be affected or reset.

***In process***
This is the actual stage when the execution happens.

***Processed***
Indicates that a job has been executed successfully.

***Stopping***
Indicates that either a user or a system action has triggered a stopjob command and that the job is being terminated.

***Terminated***
Describes the end of the stopping process. The job in process is no longer being executed or requested to stop.

***Failed***
Describes that the job failed to run and that the process did not run.

***Restart***
Indicates that a job has been actively restarted.


The image below illustrates the different stages of a job's lifecycle and the different types of actions that can get a specific job to transit from one particular state to another:
The blue arrows show the natural path of a job during it's life cycle in Automatic Execution mode.
The other dotted or plain lines show the transition between stages in manual execution mode, when applying one of the following commands:
***startjob***
***stopjob***
***restartjob***
***resumejob***


[<img src="/articles/20_jobs_and_batch_services/images/01_jobs_and_batch_services_status_flow.PNG">]




[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/01_fabric%20jobs_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/03_)
