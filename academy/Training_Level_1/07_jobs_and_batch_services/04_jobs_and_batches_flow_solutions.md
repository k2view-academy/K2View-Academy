
![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) 

### Jobs & Batch - Exercise 1 Solution


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

Answer: 569. (Please note that if you are working on a local environment with limited resources, not all instances might have been successfully fetched with a few failed values in the table above. You can re-run this exercise using a select with a where statement pointing to a smaller state with fewer customers) 

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



![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) 

### Jobs & Batch - Exercise 2 Solution 

**Step 1.**

```java
String TS_job = k2_currentDateTime();
String number_of_iDs = db("CRM_DB").fetch("SELECT count(*) FROM CUSTOMER").firstValue().toString();

FileWriter myWriter = new FileWriter("list_entities.txt", true); 
//set to true to enable append mode

myWriter.write("At this Date & Time :: "+ TS_job + ", there are: "+number_of_iDs+" entities in CRM_DB." );
myWriter.close();
```

**Step 2.**

Jobs table in the Fabric Studio:

<img src="/academy/Training_Level_1/07_jobs_and_batch_services/images/JobsAndBatch_Exercise1Step2.PNG">


**Step 3.**

Answer: 10,003.




![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) 

### Jobs & Batch - Exercise 3 Solution

**Step 1.**

Command: ```jobstatus 2 days ago;```

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

Command: ```resumejob USER_JOB NAME='Customer.numberOfIDs' UID='checkNumberIDs';```

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







[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/07_jobs_and_batch_services/03_jobs_and_batches_flow_exercises.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/07_jobs_and_batch_services/05_quiz_jobs_and_batch_services.md)

------



