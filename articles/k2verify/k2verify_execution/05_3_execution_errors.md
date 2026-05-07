# Verify Execution Errors

Review the execution errors - If any:
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
      <td valign="top">
        Critical — stops execution immediately.
        Manual intervention required before retrying.
      </td>
      <td valign="top">
        Connection failures, interface issues
      </td>
    </tr>
    <tr>
      <td valign="top"><strong>Data Errors</strong></td>
      <td valign="top">
        Handled per configured thresholds.
        Execution may continue.
      </td>
      <td valign="top">
        Value mismatches, missing/extra records
      </td>
    </tr>
  </tbody>
</table>

> **Note:** Some errors do not stop execution because they fall within the allowed thresholds. This is expected behavior.