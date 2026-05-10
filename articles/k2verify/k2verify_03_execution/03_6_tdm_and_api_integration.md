# TDM & API Integration

## **TDM Post-Execution Trigger**

Verify tasks can be triggered automatically as a TDM post-execution process using the `bwVerifyAsTDMPostExec` flow.

To configure Verify as a TDM post-execution process:

1. Create a new Verify task and define the tables to be verified.
2. Set `EXECUTE_VERIFY_TASK_ID` to the newly created task ID.
3. Add `bwVerifyAsTDMPostExec` as a post-execution flow in your TDM task.
4. Execute the TDM task.

After the TDM task completes, the Verify task starts automatically.

You can monitor the Verify task progress from the Verify web page.

## **Verify API Endpoints**

Verify tasks can be executed through APIs using the following endpoints:

<table>
  <thead>
    <tr>
      <th align="left">Endpoint</th>
      <th align="left">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top"><code>wsK2VerifyStartTask</code></td>
      <td valign="top">
        Initiates a Verify task execution.
      </td>
    </tr>
    <tr>
      <td valign="top"><code>wsK2VerifyMonitorTask</code></td>
      <td valign="top">
        Tracks task progress using the execution ID.
      </td>
    </tr>
    <tr>
      <td valign="top"><code>wsK2VerifyAgentDownloadResource</code></td>
      <td valign="top">
        Downloads the generated execution report.
      </td>
    </tr>
  </tbody>
</table>