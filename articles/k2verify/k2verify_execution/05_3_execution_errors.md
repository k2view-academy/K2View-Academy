# Verify Execution Errors

Review the execution errors - If any:
1. Open the **Partition-Level Execution Details**.
2. Check the **Error Info** column for root cause details.
3. Identify the error type:

   | Error Type | Behavior | Examples |
   |:---|:---|:---|
   | **System Errors** | Critical — stops execution immediately. Manual intervention required before retrying. | Connection failures, interface issues |
   | **Data Errors** | Handled per configured thresholds. Execution may continue. | Value mismatches, missing/extra records |

> **Note:** Some errors do not stop execution because they fall within the allowed thresholds. This is expected behavior.