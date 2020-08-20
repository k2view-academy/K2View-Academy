# Jobs and Batch Services Quiz

## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png)
Excellent! 
You have completed the Jobs and Batch services items.


Take the following quiz to see what you have learnt. The Quiz consists of a number of multiple-choice questions, each providing a number of possible answers. Click the answer you think is correct. 



#### Question 1: Jobs Services

A Fabric Jobs is a Fabric utility that can be used to:

A: Develop new skills and send your resume.

B: A rich, resilient and scalable mechanism that can be used to run any script or executable.

C: A tool to create Instances Groups.

D: A tool to schedule Logical Units creation.

(**Solution 1: B**).


#### Question 2: Jobs Configuration

In order to create a new user job you need to define:

A: A method written in Java to be saved as a Utility Function in Fabric Studio .

B: An dedicated entry in the Jobs Table of a given LU.

C: A recurring CRON schedule, Time Interval or One-time only parameter.

D: All the above

(**Solution 2: D**).


#### Question 3: Jobs Commands

Jobs can be stopped using the following command:

A: ```STOP JOB NAME='<name>' UID='<uid>';```

B: ```JOBSTOP UID='<uid>' NAME='<name>';```

C: ```STOPJOB <JOBTYPE> NAME='<name>' UID='<uid>';```
  
D: All the above.


(**Solution 3: C**).


#### Question 4: Jobs Commands

A Joc can be scheduled using the Crontab syntax. Which of the options is valid and describes following schedule: 
"Every third hour at half past, from 04:00 till 16:00 on day-of-month 5 and on Friday in November"?
  
A: ```30 04-16/3 05 11 5```

B: ```04-16/3 1/2 05 11 5```

C: ```5 11 05 04-16/3 30```

D: Fabric does not support CronTab.


(**Solution 4: A**).


#### Question 5: Jobs Commands

When using the UpdateJob command, you can update:

A: The affinity of the Job (on which node it will be run).

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

If no affinity is specified when configuring a job then

A: The job is run on the next available node.

B: On a node chosen by Cassandra quorum consistency logic.

C: The job wont run because an affinity must be specified.

D: The job will be queued until a node specifically requests for it.

(**Solution 7: A**)


#### Question 8: Jobs Types 

The following types of jobs can be defined:

A: User jobs.

B: Process jobs.

C: Broadway flows.

D: Parser executions.

E: All the above.

(**Solution 8: E**)


#### Question 9: Batch Processes

A Batch process is a Fabric utility that can be used to run multiple commands on a population's instances such as:

A: Sync instances, to perform multiple syncs on all (or a subset of) instances for a specific Logical Unit. 

B: Broadway flows.

C: Fabric data changes publishing. 

D: All the above.

(**Solution 9: D**)


#### Question 10: Batch Commands 

Which commands can be used to sync the entire population of Customer LU ?

A: ```BATCH CUSTOMER FABRIC_COMMAND="sync_instance Customer.?" with async=’true’;```

B: ```IGRATE CUSTOMER FABRIC_COMMAND="sync_instance Customer.?" with async=’true’;```

C: ```MIGRATE CUSTOMER MIGRATE_COMMAND="sync_instance Customer.?" with async=’true’;```

D: ```MIGRATE CUSTOMER with async=’true’;```

E: A & C

F: All the above.

(**Solution 10: E**)


#### Question 11: Batch Monitoring Commands

To display the status of instances of a given Batch process ID, you will use:

A: The BATCH_LIST command.

B: The BATCH_SUMMARY command.

C: The BATCH_DETAILS command.

(**Solution 11: C**)


#### Question 12: Batch Command & SQL

SQL statements can be embedded as a parameter parsed into a Batch command as followed:

A: ```from CRM_DB USING('select customer_id from Customer where customer_id <=10')"```

B: ```USING SQL ('select customer_id from CRM_DB.Customer where customer_id <=10')```

C: ```USING ('select customer_id from CRM_DB.Customer where customer_id <=10')```

D: The Batch command does not support SQL statements. Instance Groups must be defined for this purpose.

(**Solution 12: A**)


[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/07_jobs_and_batch_services/02_jobs_and_batches_flow.md)

