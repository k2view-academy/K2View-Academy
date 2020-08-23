#   Jobs and Batch Process
                           

 

## Jobs - Types and Purposes

Let's take a look at the different types of jobs that can be used to execute specific commands or code:

- [Jobs Overview](/articles/20_jobs_and_batch_services/01_fabric%20jobs_overview.md)


Through the following articles, you will understand Jobs' flows and their executions' environment:

- [Jobs' Lifecycle](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md)

- [How to Create a Job](/articles/20_jobs_and_batch_services/03_create_a_job.md)


The next articles will get you acquainted with Jobs' management and configuration:

- [Jobs' Commands](/articles/20_jobs_and_batch_services/04_jobs_commands.md)

- [Jobs' Monitoring](/articles/20_jobs_and_batch_services/05_jobs_table_fields.md)

- [Jobs' Configuration](/articles/20_jobs_and_batch_services/06_jobs_configuration.md)




## Batch Processes - Types and Purposes

Let's take a look at the different types of Batch processes that Fabric offers for running automated processes. 

First, please read the [Batch Processes Overview](/articles/20_jobs_and_batch_services/07_batch_process_overview.md) followed by [Batch Processes Commands](/articles/20_jobs_and_batch_services/08_batch_process_commands.md) and [Batch Flow](/articles/20_jobs_and_batch_services/09_batch_process_flow.md) to learn how to run new Batch processes and their execution flow.


## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Jobs & Batch - Exercise 1

In this exercise you will create a scheduled Fabric user Job that runs daily and that creates the list_entities.txt file where the following information is appended  every 5 minutes:
- Current date and time. 
- Number of Customer records in the CRM_DB.

**Step 1.**
Create the relevant Java file.

**Step 2.**
Create a Job Entry in the JOBS table under Customer LU that runs the job every 5 minutes.

**Step 3.**
Deploy Customer LU.
Check in Fabric Home Directory. Can you see the list_entities.txt file?
What is the Timestamp of the first entry? How many Customers are there in CRM_DB ?


## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) Jobs & Batch - Solution Exercise 1

**Step 1.**

```java
String TS_job = k2_currentDateTime();
String number_of_iDs = db("CRM_DB").fetch("SELECT count(*) FROM CRM_DB.CUSTOMER").firstValue().toString();

FileWriter myWriter = new FileWriter("list_entities.txt", true); 
//set to true to enable append mode

myWriter.write("At this Date & Time :: "+ TS_job + ", there are: "+number_of_iDs+" entities in CRM_DB." );
myWriter.close();
```

**Step 2.**

Jobs table in Fabric Studio:

<img src="/academy/Training_Level_1/07_jobs_and_batch_services/images/JobsAndBatch_Exercise1Step2.PNG">


**Step 3.**

Ans: 10,000


## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Jobs & Batch - Exercise 2
In this exercise we will interact with the job created above from the command line.

**Step 1.**
Open up Fabric console.
Which command will you use to list all jobs created over the last 48 hours ?
Which date was this job created? Which affinity has been set for this job ?
When is next run scheduled for?

**Step 2.**
Which command will you use to stop this job ?
To which status is the job set once you have run the previous command?

**Step 3.**
Delete or rename the list_entities.txt file in Fabric Home Direcory.
Which command needs to be run to resume the job ?
What is the status of the job in the table displayed after running the command ?

**Step 4.**
Re-run the command listing all the jobs created over the last 2 days. What is the new status of the job?



## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) Jobs & Batch - Solution Exercise 2

**Step 1.**

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


**Step 2.**

Command: ```stopjob USER_JOB NAME='Customer.numberOfIDs';```

Response:

```
fabric>stopjob USER_JOB NAME='Customer.numberOfIDs';
|Type    |Name                |UID           |Status    |Notes|
+--------+--------------------+--------------+----------+-----+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|TERMINATED|     |

```

Status has been set to: TERMINATED

**Step 3.**

Command: ```resumejob USER_JOB NAME='Customer.numberOfIDs';```

Response:

```
fabric>resumejob USER_JOB NAME='Customer.numberOfIDs';
|Type    |Name                |UID           |Status |Notes|
+--------+--------------------+--------------+-------+-----+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|WAITING|null |

```

Status has been set to: WAITING

**Step 4.**
Status has been set back to: SCHEDULED

```
fabric>jobstatus 1 days ago;
|Type    |Name                |UID           |Status   |Creation Time      |Start Time         |End Time           |Affinity           |Is Archived|Next Run           |Ownership Candidates Num|Notes|Owner       |
+--------+--------------------+--------------+---------+-------------------+-------------------+-------------------+-------------------+-----------+-------------------+------------------------+-----+------------+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|SCHEDULED|2020 08 20 08:47:30|2020 08 20 08:47:32|2020 08 20 08:47:32|                   |false      |2020-08-20 08:48:32|1                       |     |fabric_debug|
|USER_JOB|Customer.testJob    |testJOB1      |RESTART  |2020 08 20 08:12:03|2020 08 13 10:02:34|1970 01 01 00:00:00|’DC1’, ’10.21.1.121|false      |2021-03-05 08:00:30|0                       |     |            |
```



## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Jobs & Batch - Exercise 3

In this exercise we will use the batch command to sync instances of Customer LU.

**Step 1.**
Which BATCH command do I need to type to sync instances 996, 997, 998, 999 ?
Run the batch_summary command with the appropriate *bid* parameter. 
How many entries have been sync-ed per second? 

**Step 2.**
Create a new instance group where by all customers live in NY State
Run the appropriate *batch* command to sync all customers residing in NY State.
Using the *batch_summary* command, how many instances were retrieved?

**Step 3.**
Create a new instance group where by all customers live in CA State
Run the appropriate *migrate* command to sync all customers residing in CA State 



## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) Jobs & Batch - Solution Exercise 3


**Step 1.**

Command: ```BATCH Customer.('996','997','998','999') FABRIC_COMMAND="sync_instance Customer.?" with ASYNC='true';```

Response:
```
|Batch id                            |Notes|
+------------------------------------+-----+
|6145b8cc-f050-4776-b1d2-f4798283e663|null |
```

Command: ```batch_summary '6145b8cc-f050-4776-b1d2-f4798283e663';```

Response:
```
|Level  |Name        |Status|Start time         |End time           |Duration|Remaining dur.|Remaining|Total|Succeeded|Failed|Added|Updated|Unchanged|% Completed|Ent./sec (pace)|Ent./sec (avg.)|
+-------+------------+------+-------------------+-------------------+--------+--------------+---------+-----+---------+------+-----+-------+---------+-----------+---------------+---------------+
|Node   |fabric_debug|      |2020-08-20 10:31:51|2020-08-20 10:31:52|00:00:01|00:00:00      |0        |--   |4        |0     |0    |4      |0        |100        |3.94           |3.94           |
|DC     |DC1         |      |2020-08-20 10:31:51|2020-08-20 10:31:52|00:00:01|00:00:00      |0        |--   |4        |0     |0    |4      |0        |100        |3.94           |3.94           |
|Cluster|--          |DONE  |2020-08-20 10:31:51|2020-08-20 10:31:52|00:00:01|00:00:00      |0        |4    |4        |0     |0    |4      |0        |100        |3.94           |3.94           |
```

Answer:

Ent./sec (avg.): 3.94


**Step 2.**

Create the following Instance Group: *NY_IG_toSync*, in Fabric Studio, under the Customer LU section:

<img src="/academy/Training_Level_1/07_jobs_and_batch_services/images/JobsAndBatch_Exercise3Step2.PNG">


Command: ```BATCH Customer.NY_IG_toSync FABRIC_COMMAND="sync_instance Customer.?" with ASYNC='true';```

Response:
```
|Batch id                            |Notes|
+------------------------------------+-----+
|fe051b44-413c-42de-83da-c2158747a844|null |
```

Command: ```batch_summary 'fe051b44-413c-42de-83da-c2158747a844';```

Response:

```
|Level  |Name        |Status|Start time         |End time           |Duration|Remaining dur.|Remaining|Total|Succeeded|Failed|Added|Updated|Unchanged|% Completed|Ent./sec (pace)|Ent./sec (avg.)|
+-------+------------+------+-------------------+-------------------+--------+--------------+---------+-----+---------+------+-----+-------+---------+-----------+---------------+---------------+
|Node   |fabric_debug|      |2020-08-20 10:52:54|2020-08-20 10:52:58|00:00:04|00:00:00      |0        |--   |569      |0     |0    |569    |0        |100        |160.64         |160.64         |
|DC     |DC1         |      |2020-08-20 10:52:54|2020-08-20 10:52:58|00:00:04|00:00:00      |0        |--   |569      |0     |0    |569    |0        |100        |160.64         |160.64         |
|Cluster|--          |DONE  |2020-08-20 10:52:54|2020-08-20 10:52:58|00:00:04|00:00:00      |0        |569  |569      |0     |0    |569    |0        |100        |160.64         |160.64         |
```

Answer:
569

Command: ```batch_details 'fe051b44-413c-42de-83da-c2158747a844';```

Response:

```
|Entity ID|Node id     |Status   |Error|Results                              |
+---------+------------+---------+-----+-------------------------------------+
|9419     |fabric_debug|COMPLETED|     |{"Added":1,"Updated":0,"Unchanged":0}|
|6737     |fabric_debug|COMPLETED|     |{"Added":1,"Updated":0,"Unchanged":0}|
|2380     |fabric_debug|COMPLETED|     |{"Added":1,"Updated":0,"Unchanged":0}|
|3449     |fabric_debug|COMPLETED|     |{"Added":1,"Updated":0,"Unchanged":0}|
|6824     |fabric_debug|COMPLETED|     |{"Added":1,"Updated":0,"Unchanged":0}|
........................................
........................................
|3336     |fabric_debug|COMPLETED|     |{"Added":1,"Updated":0,"Unchanged":0}|
|1013     |fabric_debug|COMPLETED|     |{"Added":1,"Updated":0,"Unchanged":0}|
```



**Step 3.**

Create the following Instance Group: *CA_IG_toSync*, in Fabric Studio, under the Customer LU section:

<img src="/academy/Training_Level_1/07_jobs_and_batch_services/images/JobsAndBatch_Exercise3Step3.PNG">


Command: ```migrate Customer.CA_IG_toSync with ASYNC='true';```

Response:
```
|Batch id                            |Notes|
+------------------------------------+-----+
|991a1199-1a81-41d9-b4f6-3abb587bb99d|null |
```

Command: ```migrate_summary '991a1199-1a81-41d9-b4f6-3abb587bb99d';```

Response:
```
|Level  |Name        |Status|Start time         |End time           |Duration|Remaining dur.|Remaining|Total|Failed|Added|Updated|Unchanged|% Completed|Ent./sec (pace)|Ent./sec (avg.)|
+-------+------------+------+-------------------+-------------------+--------+--------------+---------+-----+------+-----+-------+---------+-----------+---------------+---------------+
|Node   |fabric_debug|      |2020-08-20 11:10:59|2020-08-20 11:11:04|00:00:05|00:00:00      |0        |--   |0     |0    |577    |0        |100        |124.11         |124.11         |
|DC     |DC1         |      |2020-08-20 11:10:59|2020-08-20 11:11:04|00:00:05|00:00:00      |0        |--   |0     |0    |577    |0        |100        |124.11         |124.11         |
|Cluster|--          |DONE  |2020-08-20 11:10:59|2020-08-20 11:11:04|00:00:05|00:00:00      |0        |577  |0     |0    |577    |0        |100        |124.11         |124.11         |
```

Answer:
577

Command: ```migrate_details '991a1199-1a81-41d9-b4f6-3abb587bb99d';```

Response:

```
|Node id     |Instance ID|Status |Error|
+------------+-----------+-------+-----+
|fabric_debug|9165       |Updated|     |
|fabric_debug|7822       |Updated|     |
|fabric_debug|1749       |Updated|     |
|fabric_debug|7229       |Updated|     |
........................................
........................................
|fabric_debug|9634       |Updated|     |
|fabric_debug|9914       |Updated|     |
```











[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/07_jobs_and_batch_services/01_jobs_and_batch_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/07_jobs_and_batch_services/03_quiz_jobs_and_batch_services.md)
