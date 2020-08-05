# Creating a New Job

Fabric Studio supports defining a Job function, saving it to the project file and deploying it to the Fabric Server. A Job can be a Java function or a simple command .

### How Do I Create a New User Job?

The following steps discuss how a new User Job is created:

1. In the **Project Tree**, go to your LUT > **Java** > **Utilities** and right-click on **New Function** to display the **Function Editor** window.

   <img src="/articles/20_jobs_and_batch_services/images/03_jobs_and_batch_services_create_a_job_userjob.PNG">     

2. Write the User Job Function (or use IntelliJ to write it) and once done, set the Function Type to the **User Job** value in the right-hand panel.

3. Name and save your function.

   <img src="/articles/20_jobs_and_batch_services/images/04_jobs_and_batch_services_create_a_job_userjob.PNG">  

4. Then in the **Project Tree**, go to your LUT > **Jobs** > **User Jobs**

   <img src="/articles/20_jobs_and_batch_services/images/05_jobs_and_batch_services_create_a_job_userjob.PNG">  

5. In the **Job table** start filling in the details pertaining to the Job.


**Method:** 

This is where the name of the User Job Function defined in Step 3 is associated to the job.


**Unique Job Name:** 

The unique name for the Job service is defined here and will be used for all manual or system operations executed on thsi job.


**Execution Mode:**

Can be set to either **automatically** or **manually** depending on whether you intend to start the job service from the Command Line or let Fabric manage the service.


**Active:**

Must be check-in to deploy and run the Job


**Schedule Type:** 

3 schedules modes are avaialble:
- CRON
Example: Execution set to: ```30 0/2 8-18 5 3 ? 2021``` the job will run the job with the following frequency described in the CRON description tab:
At 30 sec past the minute (30), every 2 minutes (0/2), between 08:00 and 18:59 (8-18), on day 5 of the month (5), only in March (3), only in year 2021 (2021).

- TimeStamp
The job is scheduled to run one time on a given date and time
Example: Execution set to: ```2022-08-20 10:32:12``` - the job will run once at the date and time mentioned.

- Time Interval
The job is scheduled to run at the frequency specified in the **Execute Every** field:
Example: Execution set to: ```10.11:22:33``` - the job will run every 10 days, 11 hours, 22 minutes, 33 seconds. 


**Affinity:**

This is where the details of the node assigned to the job are entered.
Affinity can be set using either one of the following options:
- IP address of a Fabric node
- Data Center (DC) Name
- Node identifier- a logical identifier a Fabric node or a group of several Fabric nodes

Example:
AFFINITY=’DC1’, ’10.21.1.121

### How Do I Create a New Process Job?


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/02_web_services_properties.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/04_web_services_function_basic_structure.md)


