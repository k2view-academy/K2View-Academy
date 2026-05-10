# Verify Monitoring

After initiating a task, you are taken to the monitoring page. You can also reach it by clicking the **Monitor** icon on the task in the Tasks page.

![Monitor Task](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/monitorTask.png)

**Table-Level Execution Summary:**

<table>
  <thead>
    <tr>
      <th align="left">Column</th>
      <th align="left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Table Name</td>
      <td valign="top">
        Source/target table pair being verified
      </td>
    </tr>
    <tr>
      <td valign="top">Batch ID</td>
      <td valign="top">
        Unique identifier for the execution batch
      </td>
    </tr>
    <tr>
      <td valign="top">Status</td>
      <td valign="top">
        Current state: In Progress, Completed, Failed
      </td>
    </tr>
    <tr>
      <td valign="top">Start Time</td>
      <td valign="top">
        When verification for the table began
      </td>
    </tr>
    <tr>
      <td valign="top">End Time</td>
      <td valign="top">
        When verification for the table completed
      </td>
    </tr>
    <tr>
      <td valign="top">Partition Completion Summary</td>
      <td valign="top">
        Progress: completed/total partitions (e.g., <code>4/10</code>)
      </td>
    </tr>
    <tr>
      <td valign="top">Processed Records</td>
      <td valign="top">
        Records processed so far
      </td>
    </tr>
    <tr>
      <td valign="top">Failed Records</td>
      <td valign="top">
        Records whose verification failed
      </td>
    </tr>
  </tbody>
</table>

Click the **ℹ** icon next to a table to drill into the Partition-Level view.

**Partition-Level Execution Details:**

<table>
  <thead>
    <tr>
      <th align="left">Column</th>
      <th align="left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Partition ID</td>
      <td valign="top">
        Identifier of the partition
      </td>
    </tr>
    <tr>
      <td valign="top">Status</td>
      <td valign="top">
        Current state of the partition
      </td>
    </tr>
    <tr>
      <td valign="top">Start Time</td>
      <td valign="top">
        When partition processing began
      </td>
    </tr>
    <tr>
      <td valign="top">End Time</td>
      <td valign="top">
        When partition processing completed
      </td>
    </tr>
    <tr>
      <td valign="top">Total Records</td>
      <td valign="top">
        Records assigned to this partition
      </td>
    </tr>
    <tr>
      <td valign="top">Processed Records</td>
      <td valign="top">
        Records processed within this partition
      </td>
    </tr>
    <tr>
      <td valign="top">Failed Records</td>
      <td valign="top">
        Records that failed verification
      </td>
    </tr>
    <tr>
      <td valign="top">Error Info</td>
      <td valign="top">
        Error details, if any
      </td>
    </tr>
  </tbody>
</table>

![Monitor Table](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/monitorTable.png)