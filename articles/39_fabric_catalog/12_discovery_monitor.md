# Discovery Job Execution Monitor

The purpose of the Discovery job execution monitor is to track the progress of the Discovery process. The monitoring provides valuable insights that can help to follow-up the job execution, displaying the progress per each step. 

The monitor is split into the following areas:

* The **General Info** area allows to select the Data Platform in order to retrieve the monitoring details. 
  * The monitor shows the last execution for the given data platform, either in progress or completed.

* The **Execution Time** area shows the job's start time and its duration. If the job has been completed, the end time is displayed as well. 
* The **Error / Warnings** area displays the error (in case of a job failure) or the number of warnings received during the job execution.
* The **Execution Progress** is the main monitor area. It shows the progress of the job, including the percent of completion of each step and the number of elements found.
  * The steps displayed in this area are dynamic, and they depend on the job configuration. The disabled plugins are not displayed.

The monitor allows to stop the Discovery job, when it is in progress, or to start the job execution for the selected data platform.

Upon the job completion, the monitor displays the version number and indicates whether a new version has been created or not.

![](images/monitor.png)







[![Previous](/articles/images/Previous.png)](11_catalog_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](20_catalog_APIs.md) 



