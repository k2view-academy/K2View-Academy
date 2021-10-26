# **Fabric Batch Monitoring Panel**

## **Overview**  

The batch monitoring dashboard has been added to Fabric version 6.5.2.

It can be accessed from Fabric's [Web Admin](/articles/30_web_framework/01_web_framework_overview.md) panel by doing the following steps:
- Navigate to **Admin > Processes > Batch**,
- Select the ```...``` button in the first column of the batch processes table - ![image](images/28_jobs_and_batch_services_batchMonitor4.PNG)
- Select the **Monitor** item of the *menu*. as illustrated in the picture below:

![image](images/25_jobs_and_batch_services_batchMonitor1.PNG)


You will need to ensure that a batch processes is being executed or has been previously executed. 

The picture below shows how to create a new batch process or how to access a previously executed batch process:

![image](images/26_jobs_and_batch_services_batchMonitor2.PNG)





## **Batch Monitor Dashboard** 

Once you clicked on the **Monitor** item of the *menu*, the following dashboard will appear:

![image](images/27_jobs_and_batch_services_batchMonitor3.PNG)


### **Batch Monitor**

The batch Monitor Control banner allows you to cancel, pause and resume the batch process currently in focus. 
It provides a progress bar that indicates the number of entities already processed and the number of entities yet to be processed.

![image](images/31_jobs_and_batch_services_batchMonitor7)


### **General Data**

Execution Status:
Provides the status of the current batch process as defined by the [batch_summary](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md#batch_summary) command

Fabric Command:
The actual batch command being processed. All details can be viewd by clicking on this sign: ![image](images/28_jobs_and_batch_services_batchMonitor4.PNG)

The fully detailed list of tha batch process (resulting from the execution of the [batch_list](/articles/20_jobs_and_batch_services/12_batch_sync_commands.md#batch_list) command) will be displayed as illustrated below:

![image](images/29_jobs_and_batch_services_batchMonitor5.PNG)

Max. No. of Workers:
This parameter can be modified during the execution time and defines the number of threads allocated to this batch process. The value can be adjusted by clicking on the following icon: ![image](images/30_jobs_and_batch_services_batchMonitor6.PNG).


### **Execution Time**









