# Jobs and Batch Services Quiz

## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png)
Excellent! 
You have completed the Jobs and Batch Services items.


Take the following Quiz to see what you have learnt. 

The Quiz consists of of multiple-choice questions, each providing a number of possible answers. 

Click the answer you think is correct. 


#### Question 1: Jobs Services

Fabric Jobs is a Fabric utility that is:

A: A career development and jobs search tool.

B: A rich, resilient and scalable mechanism that can be used to run any script or java functions.

C: A tool that creates instances groups.

D: A tool that schedules the creation of LUs.

(**Solution 1: B**).


#### Question 2: Jobs Configuration

To create a new User Job, you need to define:

A: A method written in Java that is saved as a Java User Job function in the Fabric Studio.

B: A dedicated entry in the Jobs table of a given LU.

C: A recurring CRON schedule, time interval or one-time only parameter.

D: All the above.

(**Solution 2: D**).


#### Question 3: Jobs Commands

Jobs can be stopped using the following command:

A: ```STOP JOB NAME='<name>' UID='<uid>';```

B: ```JOBSTOP UID='<uid>' NAME='<name>';```

C: ```STOPJOB <JOBTYPE> NAME='<name>' UID='<uid>';```
  
D: All the above.


(**Solution 3: C**).


#### Question 4: Jobs Commands

A Job can be scheduled using Crontab syntax. Which of these options is valid and describes the following schedule?

"Every third hour at half past, from 04:00 till 16:00 on day-of-month 5 and on Friday in November"
  
A: ```30 04-16/3 05 11 5```

B: ```04-16/3 1/2 05 11 5```

C: ```5 11 05 04-16/3 30```

D: Fabric does not support CronTab.


(**Solution 4: A**).


#### Question 5: Jobs Commands

When using the UpdateJob command, you can update:

A: The affinity of the Job. (The node where it is run).

B: The scheduling scheme of the Job.

C: Arguments to be parsed to the script or function that will be run.

D: The name of the Job.

E: Answers A, B & C.


(**Solution 5: E**).


#### Question 6: Jobs Commands

To list all the Jobs that have been created over the last day, you will type the following command:

A: ```JOBSTATUS 1 day ago;```

B: ```STATUSJOB 1 days ago;```

C: ```JOBSTATUS 1 days ago;```

D: ```JOBSTATUS --24;```

(**Solution 6: C**)


#### Question 7: Jobs Affinity

If no affinity is specified when configuring a Job then:

A: The Job is run on the next available node.

B: The Job is run on a node chosen by Cassandra quorum consistency logic.

C: The Job won't run because an affinity must be specified.

D: The Job will be queued until a node specifically requests for it.

(**Solution 7: A**)


#### Question 8: Jobs Types 

The following types of Jobs can be defined:

A: User Job.

B: Process Jobs.

C: Broadway flows.

D: All the above.

(**Solution 8: D**)


#### Question 9: Batch Processes

A Batch process is a Fabric utility that can be used to run multiple commands on a population's instances, such as:

A: Sync instances, to perform multiple syncs on all (or a subset of) instances for a specific Logical Unit. 

B: Broadway flows.

C: Re-publishing Fabric data changes using the CDC mechanism. 

D: All the above.

(**Solution 9: D**)


#### Question 10: Batch Commands 

Which commands can be used to sync the entire population of a Customer LU?

A: ```BATCH CUSTOMER FABRIC_COMMAND="sync_instance Customer.?" with async=’true’;```

B: ```MIGRATE CUSTOMER FABRIC_COMMAND="sync_instance Customer.?" with async=’true’;```

C: ```MIGRATE CUSTOMER MIGRATE_COMMAND="sync_instance Customer.?" with async=’true’;```

D: ```MIGRATE CUSTOMER with async=’true’;```

E: A, D

F: All the above.

(**Solution 10: E**)


#### Question 11: Batch Monitoring Commands

To display the status of instances of a given Batch process ID, you will use:

A: The BATCH_LIST command.

B: The BATCH_SUMMARY command.

C: The BATCH_DETAILS command.

(**Solution 11: C**)


#### Question 12: Batch Command & SQL

An SQL statement can be embedded as a parameter parsed into a Batch command as follows:

A: ```from CRM_DB USING('select customer_id from Customer where customer_id <=10')"```

B: ```USING SQL ('select customer_id from Customer where customer_id <=10')```

C: ```USING ('select customer_id from Customer where customer_id <=10')```

D: The Batch command does not support SQL statements. Instance Groups must be defined for this purpose.

(**Solution 12: A**)


#### Question 13: Batch,Jobs & Nodes

Affinity is a Fabric parameter used to:

A: Allocate a specific node to a given Job and is set during the Job's definition phase

B: Allocate a specific batch process to a job process using the Job Allocation parameter.

C: Allocate a schedule to a job or batch process

D: Link 2 or more jobs together to optimize execution

E: Answer A & B

(**Solution 13: E**)


#### Question 14: Batch,Jobs & Nodes

If one of the nodes of a given Fabric Cluster is down, Jobs with affinity allocated to this node will:

A: Need to be restarted.

B: Be automatically handled by a different node since the failing node will be deemed unavailable by the other Fabric sessions

C: Need to be re-defined alltogether.

D: No such a thing, Fabric nodes never fail


(**Solution 14: B**)



[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/07_jobs_and_batch_services/02_jobs_and_batches_flow.md)

------
