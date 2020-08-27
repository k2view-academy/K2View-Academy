#   Jobs and Batch Process
                           

 

## Jobs - Types and Purposes

Let's take a look at the different types of Jobs that can be used to execute specific commands or code. 

Please read:

- [Jobs Overview](/articles/20_jobs_and_batch_services/01_fabric_jobs_overview.md)


The following articles discuss Jobs flows and their execution environments:

- [Jobs Lifecycle](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md)

- [How to Create a new User Job](/articles/20_jobs_and_batch_services/03_create_a_new_user_job.md)

- [How to Create a new Process Job](/articles/20_jobs_and_batch_services/04_create_a_new_process_job.md)

- [How to Create a new Broadway Job](/articles/20_jobs_and_batch_services/05_create_a_new_broadway_job.md)

- [How to Create a new CDC Job](/articles/20_jobs_and_batch_services/06_create_a_new_CDC_job.md)



These articles introduce Jobs management and configuration:

- [Jobs' Commands](/articles/20_jobs_and_batch_services/07_jobs_commands.md)

- [Jobs' Monitoring](/articles/20_jobs_and_batch_services/08_jobs_table_fields.md)

- [Jobs' Configuration](/articles/20_jobs_and_batch_services/09_jobs_configuration.md)

- [Jobs and Batches Affinity](/articles/20_jobs_and_batch_services/10_jobs_and_batches_affinity.md)



## Batch Processes - Types and Purposes

Let's take a look at the different types of Batch processes that Fabric offers for managing automated processes and their lifecycle.
 

First, please read the [Batch Processes Overview](/articles/20_jobs_and_batch_services/11_batch_process_overview.md).

Batch process commands are fully detailed in the following articles:

- [Batch Sync Process Commands](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md)

- [Migrate Process Commands](/articles/20_jobs_and_batch_services/13_migrate_commands.md)

- [Batch Broadway Process Commands](/articles/20_jobs_and_batch_services/14_batch_broadway_commands.md)

- [Batch CDC Republish Commands](/articles/20_jobs_and_batch_services/15_batch_CDC_commands.md)


To understand Batch process flow: 
- [Batch Flow](/articles/20_jobs_and_batch_services/16_batch_process_flow.md). 



## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Jobs & Batch - Exercise 3

In this exercise you will use the Batch command to sync Customer LU instances.

**Step 1.**

Which Batch command is used to sync instances 996, 997, 998 and 999?

Run the batch_summary command with the appropriate **Bid** parameter. 

How many entries have been synced per second? 

**Step 2.**

Create a new instance group where all customers live in NY State.

Run the appropriate **Batch** command to sync all customers residing in NY State.
Using the **batch_summary** command, how many instances have been retrieved?

**Step 3.**

Create a new instance group where all customers live in CA State.

Run the appropriate **migrate** command to sync all customers residing in CA State. 



## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) Jobs & Batch - Exercise 3 Solution


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

Create the **NY_IG_toSync** instance group in the Fabric Studio under the Customer LU section:

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

Answer: 569.

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

Create the **CA_IG_toSync** instance group in the Fabric Studio under the Customer LU section:

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

Answer: 577.

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


## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Jobs & Batch - Exercise 1

In this exercise you will create a scheduled Fabric user Job that runs daily and that creates the list_entities.txt file where the following information is appended  every 5 minutes:
- Current date and time. 
- Number of customer records in the CRM_DB.

**Step 1.**

Create the relevant Java file.

**Step 2.**

Go to the Customer LU and in the JOBS table create a Job entry  that runs the Job every 5 minutes.

**Step 3.**

Deploy the Customer LU.

Check the Fabric Home directory. Can you see the list_entities.txt file?

What is the timestamp of the first entry? 

How many customers are there in the CRM_DB?


## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) Jobs & Batch - Exercise 1 Solution 

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

Jobs table in the Fabric Studio:

<img src="/academy/Training_Level_1/07_jobs_and_batch_services/images/JobsAndBatch_Exercise1Step2.PNG">


**Step 3.**

Answer: 10,000.


## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Jobs & Batch - Exercise 2
In this exercise you will interact with the above Job using the command line.

**Step 1.**

Open the Fabric Console.

Which command is used to list all Jobs created over the last 48 hours?

When was the Job created? 

Which affinity has been set for this Job?

When is the next run scheduled for?

**Step 2.**

Which command will you use to stop this Job?

What was the Job's status after you ran the previous command?

**Step 3.**

Delete or rename the list_entities.txt file in the Fabric Home directory.

Which command must be run to resume the Job?

What is the Job's status in the table after the command is run?

**Step 4.**

Rerun the command listing all Jobs created over the last 2 days. What is the new status of the Job?



## ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) Jobs & Batch - Exercise 2 Solution

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

Creation time: ```2020 08 20 08:12:03```

Affinity: ```(none has been defined)```

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

Status has been set to TERMINATED.

**Step 3.**

Command: ```resumejob USER_JOB NAME='Customer.numberOfIDs';```

Response:

```
fabric>resumejob USER_JOB NAME='Customer.numberOfIDs';
|Type    |Name                |UID           |Status |Notes|
+--------+--------------------+--------------+-------+-----+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|WAITING|null |

```

Status has been set to WAITING.

**Step 4.**
Status has been set back to SCHEDULED.

```
fabric>jobstatus 1 days ago;
|Type    |Name                |UID           |Status   |Creation Time      |Start Time         |End Time           |Affinity           |Is Archived|Next Run           |Ownership Candidates Num|Notes|Owner       |
+--------+--------------------+--------------+---------+-------------------+-------------------+-------------------+-------------------+-----------+-------------------+------------------------+-----+------------+
|USER_JOB|Customer.numberOfIDs|checkNumberIDs|SCHEDULED|2020 08 20 08:47:30|2020 08 20 08:47:32|2020 08 20 08:47:32|                   |false      |2020-08-20 08:48:32|1                       |     |fabric_debug|
|USER_JOB|Customer.testJob    |testJOB1      |RESTART  |2020 08 20 08:12:03|2020 08 13 10:02:34|1970 01 01 00:00:00|’DC1’, ’10.21.1.121|false      |2021-03-05 08:00:30|0                       |     |            |
```















[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/07_jobs_and_batch_services/01_jobs_and_batch_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/07_jobs_and_batch_services/03_quiz_jobs_and_batch_services.md)
