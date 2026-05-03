# Verify Monitoring

After initiating a task, you are taken to the monitoring page. You can also reach it by clicking the **Monitor** icon on the task in the Tasks page.

![Monitor Task](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/monitorTask.png)

**Table-Level Execution Summary:**

| Column | Description |
|:---|:---|
| Table Name | Source/target table pair being verified |
| Batch ID | Unique identifier for the execution batch |
| Status | Current state: In Progress, Completed, Failed |
| Start Time | When verification for the table began |
| End Time | When verification for the table completed |
| Partition Completion Summary | Progress: completed/total partitions (e.g., `4/10`) |
| Processed Records | Records processed so far |
| Failed Records | Records whose verification failed |

Click the **ℹ** icon next to a table to drill into the Partition-Level view.

**Partition-Level Execution Details:**

| Column | Description |
|:---|:---|
| Partition ID | Identifier of the partition |
| Status | Current state of the partition |
| Start Time | When partition processing began |
| End Time | When partition processing completed |
| Total Records | Records assigned to this partition |
| Processed Records | Records processed within this partition |
| Failed Records | Records that failed verification |
| Error Info | Error details, if any |

![Monitor Table](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/monitorTable.png)