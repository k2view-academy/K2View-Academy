#   Jobs and Batch Process
                           

 

## Jobs - Types and Purposes

Let's take a look at the different types of jobs that can be used to execute specific commands or code. 

Read about [Jobs Overview](/articles/20_jobs_and_batch_services/01_fabric%20jobs_overview.md)


Through the following articles, you will understand Jobs' flows and their executions' environment:

[Jobs' Lifecycle](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md)

[How to Create a Job](/articles/20_jobs_and_batch_services/03_create_a_job.md)


The next articles will get you acquainted with Jobs' management and configuration:

[Jobs' Commands](/articles/20_jobs_and_batch_services/04_jobs_commands.md)

[Jobs' Monitoring](/articles/20_jobs_and_batch_services/05_jobs_table_fields.md)

[Jobs' Configuration](/articles/20_jobs_and_batch_services/06_jobs_configuration.md)




## Batch Processes - Types and Purposes

Let's take a look at the different types of Batch processes that Fabric supplies in its arsenal to run automated processes. 

First, read about [Batch Processes Overview](/articles/20_jobs_and_batch_services/07_batch_process_overview.md)

The following articles, explain how to run new batch processes and their execution flow:

[Batch Processes' Commands](/articles/20_jobs_and_batch_services/08_batch_process_commands.md)

[Batch Flow](/articles/20_jobs_and_batch_services/09_batch_process_flow.md)



## Jobs & Batch - Exercise 1

In this exercise we will create a Fabric Scheduled User Job, designed to run daily and that creates a file (list_entities.txt) in which will be appended, every 5 minutes, the following information:
- Current date and time, 
- Number of Customers records in CRM_DB

Step 1.
Create the relevant Java file.

Step 2.
Create a Job Entry in the JOBS table under Customer LU that runs the job every 5 minutes.

Step 3.
Deploy Customer LU.
Check in Fabric Home Directory. Can you see the list_entities.txt file?
What is the Timestamp of the first entry? How many Customers are there in CRM_DB ?


## Jobs & Batch - Solution 1

Step 1.

```java
String TS_job = k2_currentDateTime();
String number_of_iDs = db("CRM_DB").fetch("SELECT count(*) FROM CRM_DB.CUSTOMER").firstValue().toString();

FileWriter myWriter = new FileWriter("list_entities.txt", true); 
//set to true to enable append mode

myWriter.write("At this Date & Time :: "+ TS_job + ", there are: "+number_of_iDs+" entities in CRM_DB." );
myWriter.close();
```

Step 2.

<img src="/academy/Training_Level_1/07_jobs_and_batch_services/01_jobs_and_batch_services_overview.PNG">


Step 3.
Ans: 10,000


## Jobs & Batch - Exercise 2
In this exercise we will interact with the job created above from the command line.

Step 1.
Open up Fabric console.
Which command will you use to list all jobs created over the last 48 hours ?
Which date was this job created? Which affinity has been set for this job ?
When is next run scheduled for?

Step 2.
Which command will you use to stop this job ?
To which status is the job set once you have run the previous command?

Step 3.
Delete or rename the list_entities.txt file in Fabric Home Direcory.
Which command needs to be run to resume the job ?
What is the status of the job in the table displayed after running the command ?

Step 4.
Re-run the command listing all the jobs created over the last 2 days. What is the new status of the job?



## Jobs & Batch - Solution 2

Step 1.

Command: ```jobstatus 1 days ago;```

Response:

```
fabric>jobstatus 1 days ago;
|Type    |Name                |UID           |Status   |Creation Time      |Start Time         |End Time           |Affinity           |Is Archived|Next Run           |Ownership Candidates Num|Notes|Owner       |
+--------+--------------------+--------------+---------+-------------------+-------------------+-------------------+-------------------+-----------+-------------------+------------------------+-----+------------+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|SCHEDULED|2020 08 20 08:12:03|2020 08 20 08:38:10|2020 08 20 08:38:10|                   |false      |2020-08-20 08:39:10|1                       |     |fabric_debug|
|USER_JOB|Customer.testJob    |testJOB1      |RESTART  |2020 08 20 08:12:03|2020 08 13 10:02:34|1970 01 01 00:00:00|’DC1’, ’10.21.1.121|false      |2021-03-05 08:00:30|0                       |     |            |
```

Creation Time: ```2020 08 20 08:12:03```

Affinity: ``` ``` (none has been defined)

Next scheduled run: ```2020-08-20 08:39:10```


Step 2.

Command: ```stopjob USER_JOB NAME='Customer.numberOfIDs';```

Response:

```
fabric>stopjob USER_JOB NAME='Customer.numberOfIDs';
|Type    |Name                |UID           |Status    |Notes|
+--------+--------------------+--------------+----------+-----+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|TERMINATED|     |

```

Status has been set to: TERMINATED

Step 3.

Command: ```resumejob USER_JOB NAME='Customer.numberOfIDs';```

Response:

```
fabric>resumejob USER_JOB NAME='Customer.numberOfIDs';
|Type    |Name                |UID           |Status |Notes|
+--------+--------------------+--------------+-------+-----+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|WAITING|null |

```

Status has been set to: WAITING

Step 4.
Status has been set back to: SCHEDULED

```
fabric>jobstatus 1 days ago;
|Type    |Name                |UID           |Status   |Creation Time      |Start Time         |End Time           |Affinity           |Is Archived|Next Run           |Ownership Candidates Num|Notes|Owner       |
+--------+--------------------+--------------+---------+-------------------+-------------------+-------------------+-------------------+-----------+-------------------+------------------------+-----+------------+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|SCHEDULED|2020 08 20 08:47:30|2020 08 20 08:47:32|2020 08 20 08:47:32|                   |false      |2020-08-20 08:48:32|1                       |     |fabric_debug|
|USER_JOB|Customer.testJob    |testJOB1      |RESTART  |2020 08 20 08:12:03|2020 08 13 10:02:34|1970 01 01 00:00:00|’DC1’, ’10.21.1.121|false      |2021-03-05 08:00:30|0                       |     |            |
```


[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/07_jobs_and_batch_services/01_jobs_and_batch_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/07_jobs_and_batch_services/03_quiz_jobs_and_batch_services.md)
