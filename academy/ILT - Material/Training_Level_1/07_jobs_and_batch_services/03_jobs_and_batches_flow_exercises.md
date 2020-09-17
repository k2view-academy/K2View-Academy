![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) 

### Jobs & Batch - Exercise 1

In this exercise you will use the Batch command to sync Customer LU instances.

**Step 1.**

Which Batch command is used to sync instances 996, 997, 998 and 999?

Run the batch_summary command with the appropriate **Batch ID** parameter. 

How many entries have been synced per second? 

**Step 2.**

Create a new instance group where all customers live in NY State.

Run the appropriate **Batch** command to sync all customers residing in NY State.
Using the **batch_summary** command, how many instances have been retrieved?

**Step 3.**

Create a new instance group where all customers live in CA State.

Run the appropriate **migrate** command to sync all customers residing in CA State. 





![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) 

### Jobs & Batch - Exercise 2

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





![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) 
### Jobs & Batch - Exercise 3
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




[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/07_jobs_and_batch_services/02_jobs_and_batches_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/07_jobs_and_batch_services/04_jobs_and_batches_flow_solutions.md)

------




