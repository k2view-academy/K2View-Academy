# Verify Execution Retry

You can re-run only the failed partitions of a previous execution — without creating a new task.

1. Open **Task History** for the relevant task.
2. Click the **Execution ID** of the failed execution.
3. Click **Retry Task**.

![Monitor Table](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/retryTask.png)

Verify re-executes only the failed partitions. Results from successful partitions are preserved and combined with the retry results in the final report.

> The **Retry Task** button is available when: the execution has at least one failed partition and a valid batch ID exists.
> 
> The **Retry Task** button is **disabled** when: an execution is In Progress, all partitions succeeded, or the batch has been purged.