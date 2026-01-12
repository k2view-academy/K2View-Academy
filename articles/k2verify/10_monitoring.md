## k2verify Monitoring

#### Monitor the k2verify task
Monitor the task execution from the page displayed after task initiation, or by clicking the Monitor icon on the task record in the main `Tasks` page
![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/monitorTask.png)

The Execution Details (Monitor) screen provides real-time visibility into the progress of a running k2verify task, at both table level and bucket level.

**Table-Level Execution Summary**:

The upper section displays the execution status for each table included in the task.

Displayed columns:
* Table Name – Name of the source/target table pair being verified.
* Batch ID – Unique identifier for the execution batch of the table.
* Status – Current execution state (e.g., In Progress, Completed, Failed).
* Start Time – Timestamp when verification for the table started.
* End Time – Timestamp when verification for the table completed.
* Bucket Completion Summary – Progress indicator showing completed buckets out of total buckets (e.g., 0/10).
* Processed Records – Number of records processed so far.
* Failed Records – Number of records that their verification process failed.

Clicking the “i” (information) icon next to a table opens the detailed bucket-level view for that table.

**Bucket-Level Execution Details**:

The lower section displays execution details per bucket for the selected table.
Displayed columns:
* Bucket ID – Identifier of the bucket.
* Status – Current execution state of the bucket.
* Start Time – Timestamp when processing of the bucket started.
* End Time – Timestamp when processing of the bucket completed.
* Total Records – Total number of records assigned to the bucket.
* Processed Records – Number of records processed within the bucket.
* Failed Records – Number of records that failed verification in the bucket.
* Error Info – Error details, if any occurred during bucket execution.

![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/monitorTable.png)