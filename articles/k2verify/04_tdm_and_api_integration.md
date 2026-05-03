# TDM & API Integration

## TDM Post-Execution Trigger

Verify tasks can be triggered automatically as a TDM post-execution process via the `bwVerifyAsTDMPostExec` flow.

| Option | Configuration |
|:---|:---|
| Run an existing task | Set `EXECUTE_VERIFY_TASK_ID` to the existing task ID |
| Auto-create a new task | Set `EXECUTE_VERIFY_TASK_ID` to `NEW_TASK` |

## Verify API Endpoints

| Endpoint | Description |
|:---|:---|
| `wsK2VerifyStartTask` | Initiate a Verify task execution |
| `wsK2VerifyMonitorTask` | Track task progress using the Execution ID |
| `wsK2VerifyAgentDownloadResource` | Retrieve the completed report |