# Verify Execution Errors

Verify distinguishes between two categories of errors and handles them differently.

**System Errors** are critical infrastructure failures that stop execution immediately. These are problems Verify cannot work around on its own — such as a lost database connection or an unreachable interface. When a system error occurs, Verify automatically retries the connection up to three times. If all retries are exhausted without success, the partition is stopped and manual intervention is required. You will need to identify and resolve the underlying issue before using the Retry function to resume execution.

**Data Errors** are failures that occur at the record level during the verification process. These fall into two categories: **errors in the verification process itself — such as a failed data transformation** — and **comparison failures, where field values do not match between source and target**. Unlike system errors, data errors do not automatically stop execution. Instead, Verify evaluates them against the thresholds configured in Advanced Settings — if the failure rate stays within the allowed limits, execution continues. If the thresholds are breached, the partition execution stops. This means that seeing errors in the results without a full execution stop is expected behavior, not a malfunction.

To review the error details for each partition:
1. Open the **Partition-Level Execution Details**.
2. Check the **Error Info** column for root cause details.
3. Identify the error type:

<table>
  <thead>
    <tr>
      <th align="left">Error Type</th>
      <th align="left">Behavior</th>
      <th align="left">Examples</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top"><strong>System Errors</strong></td>
      <td valign="top">Critical — stops execution immediately. Manual intervention required before retrying.</td>
      <td valign="top">Connection failures, interface issues</td>
    </tr>
    <tr>
      <td valign="top"><strong>Data Errors</strong></td>
      <td valign="top">Handled per configured thresholds. Execution may continue.</td>
      <td valign="top">Value mismatches, missing/extra records, failed transformation logic</td>
    </tr>
  </tbody>
</table>

> **Note:** Some errors do not stop execution because they fall within the allowed thresholds. This is expected behavior.