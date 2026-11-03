# Invoking the TDM Agent via API (CI/CD Integration)

Starting from TDM 10.1, AI-assisted test data provisioning can be integrated into CI/CD pipelines. A CI/CD pipeline or an external application can provide a user story with its test cases to the TDM Agent. For each test case, the agent identifies the relevant TDM task and execution parameters, saves an execution draft, and returns a URL for executing the task with the required parameters.

**Note:** The API prepares the executions but does not execute them. The task is executed only when the returned `stagedExecutionUrl` is invoked, or when a user opens the draft in the TDM App and executes it.

The integration flow is as follows:

1. Submit the user story and its test cases using the **createTask** API. The API returns a job ID.
2. Poll the **status** API with the job ID until the job is completed.
3. For each test case with a **READY** status, either invoke the returned `stagedExecutionUrl` to start the task execution, or open the returned `reviewUrl` to review the execution parameters in the TDM App before executing the task.

The APIs run with the permissions of the calling user. Each test case is prepared with the identity and Fabric roles of the user who submitted the request.

## Submit a User Story - createTask API

### API URL

/test-data-agent/createTask

### HTTP Method

POST

### API Category

TDM_AI

### API Input

<table>
  <thead>
    <tr>
      <th>Parameter</th>
      <th>Mandatory</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>name</strong></td>
      <td>Yes</td>
      <td>User story name.</td>
    </tr>
    <tr>
      <td><strong>sourceEnvironment</strong></td>
      <td>Yes</td>
      <td>Source environment name. An unknown environment is rejected with an HTTP 400 error before any job is created.</td>
    </tr>
    <tr>
      <td><strong>targetEnvironment</strong></td>
      <td>No</td>
      <td>
        Target environment name. Omit this parameter for an Extract request; once the agent infers an Extract action, it does not ask for a target environment.<br>
        When the parameter is omitted and only one environment can be a target, that environment is used and returned as the job's <strong>targetEnvironment</strong>. When several environments can be a target, a test case that requires a target (Load, Delete, Reserve) fails with a reason stating that <strong>targetEnvironment</strong> is missing.
      </td>
    </tr>
    <tr>
      <td><strong>businessEntity</strong></td>
      <td>No</td>
      <td>An optional hint for the Business Entity. If it is not set, the agent resolves the Business Entity from the test case text.</td>
    </tr>
    <tr>
      <td><strong>externalUserStoryId</strong></td>
      <td>No</td>
      <td>The caller's own ID of the user story (for example, the ticket ID in the ALM tool). The ID is stored and returned by the status API as <strong>userStoryId</strong>, so a user story can be traced to its jobs and agent sessions. Resubmitting the same user story starts a new job with a new job ID.</td>
    </tr>
    <tr>
      <td><strong>description</strong></td>
      <td>No</td>
      <td>User story description.</td>
    </tr>
    <tr>
      <td><strong>cases</strong></td>
      <td>Yes</td>
      <td>
        An array of test cases (at least one). Each test case contains the following attributes:
        <ul>
          <li><strong>caseId</strong> - mandatory. Must be unique within the request; a request with duplicate case IDs is rejected with an HTTP 400 error before any job is created. The ID can contain only letters, digits, dots, underscores, and hyphens.</li>
          <li><strong>content</strong> - the test case text.</li>
          <li><strong>requiredCount</strong> - optional. The number of entities required by the test case. If it is not set, the agent infers the number of entities from the test case text. A vague amount, such as "a few customers", is not guessed: the test case fails with a reason asking for the number.</li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>

### Request Body Example

```json
{
  "externalUserStoryId": "1",
  "name": "Financial Risk Warning",
  "description": "Display Financial Risk Warning for High-Value Loan Customers...",
  "sourceEnvironment": "Production",
  "targetEnvironment": "UAT",
  "businessEntity": "Customer",
  "cases": [
    {
      "caseId": "1.1",
      "content": "Customer has a personal loan above 100,000...",
      "requiredCount": 5
    },
    {
      "caseId": "1.2",
      "content": "Customer has no outstanding loans..."
    }
  ]
}
```

### API Output

The API returns the job ID immediately and prepares the test cases in the background, in a Fabric batch with one entity per test case.

- **batchId** - the Fabric batch that runs the job's test cases.
- **batchUrl** - an absolute URL that opens the batch in the Fabric Batch Monitor.

The API waits a few seconds for the batch to start. If it has not started by then, both attributes are null, and the status API returns them once the batch has started.

### API Output Example

```json
{
  "jobId": "8f1b0c8e-6a1e-4a7b-9f1d-2c3e4f5a6b7c",
  "status": "ACCEPTED",
  "testCaseCount": 2,
  "submittedAt": "2026-08-31T09:14:22.031Z",
  "batchId": "tdmai_8f1b0c8e_3f0a9c1e-52d4-4b8e-9a6f-0d7c2e1b4a55",
  "batchUrl": "https://<host>/k2view/app/admin/batches/batch-monitor/tdmai_8f1b0c8e_3f0a9c1e-52d4-4b8e-9a6f-0d7c2e1b4a55",
  "message": "Request accepted. Preparing 2 test cases."
}
```

## Get the Job Status - status API

### API URL

/test-data-agent/status/{jobId}

### HTTP Method

GET

### API Category

TDM_AI

### API Output

The API returns the job attributes (**jobId**, **userStoryId**, **name**, **status**, **sourceEnvironment**, **targetEnvironment**, **startedAt**, **completedAt**, **batchId**, **batchUrl**) and a **cases** array with the following information per test case:

- **caseId** - the test case ID from the request.
- **status** - the test case status:
  - **PENDING** - the test case has not been picked up yet.
  - **RUNNING** - the agent is working on the test case.
  - **READY** - an execution draft is ready.
  - **FAILED** - no execution could be prepared. The **reasoning** attribute explains why.
- **reasoning** - the agent's explanation of the outcome. For a READY test case, it describes the prepared task: the action, entity count and environments, every condition it selects by and whether it was changed, added or kept, and any condition the task always applies that the test case did not ask for. For a FAILED test case, it explains what was missing or did not match, compares the candidate tasks that partly matched, and states what to change before resubmitting.
- **taskId** and **executionId** - the task and the execution draft prepared by the agent. Returned for a READY test case. The draft execution ID is stored in **task_execution_list.task_execution_id**, like any other task execution ID.
- **stagedExecutionUrl** - an absolute URL of the startTask API that runs the prepared draft. The draft ID is sent as the [taskExecutionId](/articles/TDM/tdm_gui/TDM_Task_Execution_Flows_APIs/04_execute_task_API.md#example-6---rerun-a-previous-execution) parameter, so the draft's parameters are used as the baseline of the execution. The URL can be added directly to a ticket. It is null when no draft was prepared.
- **reviewUrl** - an absolute URL that opens the draft in the TDM App task execution window for review before executing it. It is null when no draft was prepared.
- **agentSessionId** - the ID of the agent conversation behind the test case, for tracing it in the AI logs.
- **startedAt** and **completedAt** - when the agent started and finished the test case.

The job-level status is derived from the test case statuses:

- **PROCESSING** - the job has not yet recorded the outcome of every test case.
- **FAILED** - all test cases failed. A job-level **reasoning** attribute is returned when the job itself failed.
- **COMPLETED** - all other cases.

**Note:** A test case can show READY shortly before its **reasoning** is written. Read the reasoning once the job status is COMPLETED.

The status API reports on the preparation only. It does not report on executions started from the prepared drafts; use the TDM execution APIs to monitor them.

### API Output Example

```json
{
  "jobId": "8f1b0c8e-6a1e-4a7b-9f1d-2c3e4f5a6b7c",
  "userStoryId": "1",
  "name": "Financial Risk Warning",
  "status": "COMPLETED",
  "sourceEnvironment": "Production",
  "targetEnvironment": "UAT",
  "startedAt": "2026-08-31T09:14:22.031Z",
  "completedAt": "2026-08-31T09:17:40.114Z",
  "batchId": "tdmai_8f1b0c8e_3f0a9c1e-52d4-4b8e-9a6f-0d7c2e1b4a55",
  "batchUrl": "https://<host>/k2view/app/admin/batches/batch-monitor/tdmai_8f1b0c8e_3f0a9c1e-52d4-4b8e-9a6f-0d7c2e1b4a55",
  "cases": [
    {
      "caseId": "1.1",
      "status": "READY",
      "reasoning": "Prepared task 128 'Copy Gold customers' to extract and load 5 customers from Production to UAT...",
      "stagedExecutionUrl": "https://<host>/k2view/api/task/128/forced/false/startTask?taskExecutionId=5417",
      "reviewUrl": "https://<host>/k2view/app/TDM/tasks/execute?taskExecutionId=5417",
      "agentSessionId": "tdmai-8f1b0c8e-6a1e-4a7b-9f1d-2c3e4f5a6b7c-1-1",
      "taskId": 128,
      "executionId": 5417
    },
    {
      "caseId": "1.2",
      "status": "FAILED",
      "reasoning": "No existing task can satisfy this test case...",
      "agentSessionId": "tdmai-8f1b0c8e-6a1e-4a7b-9f1d-2c3e4f5a6b7c-1-2"
    }
  ]
}
```



[![Previous](/articles/images/Previous.png)](02_tdm_agent_architecture.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_tdm_agent_mcp_tools.md)
