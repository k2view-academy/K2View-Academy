# TDM Agent – AI-Assisted Task Search and Execution

## Overview

The **TDM Agent** is an AI-assisted task search capability available in the **TDM App**. It makes it easier to find and prepare TDM tasks using natural language.

Instead of browsing or filtering through the task list and then manually provisioning the data, testers can simply describe what they want to do. The TDM Agent identifies the most appropriate task, opens it, and **pre-fills its editable execution attributes based on the user's request**. When needed and allowed, it also adds new attributes. The tester can then review the suggested values, make any necessary changes, and execute the task.

This makes task execution faster and easier, particularly for users who may not be familiar with the available tasks or their configuration options.

The TDM Agent is available **in addition to** the existing search option in the **Manage Your Tasks** window. It does not replace the existing search functionality.

Starting from TDM 10.1, the TDM Agent can also be used **beyond the TDM App**. It can be invoked via API, enabling external applications and CI/CD pipelines to leverage its AI-assisted task search and execution capabilities. See [Invoking the TDM Agent via API (CI/CD Integration)](#invoking-the-tdm-agent-via-api-cicd-integration).

## Prerequisite
Install Fabric 8.5.x or later.

## Installation Steps

To enable the TDM Agent, complete the following steps:

1. **Install the TDM Agent extension.** Verify that the **aifusion** LU (Data Product) is added to the project.
2. **Install an LLM extension** (e.g. Anthropic).
3. **Define an LLM interface** on the installed LLM extension.
4. **Edit the `apps.json` file.** In the **TDM** application entry, set the `showChat` attribute to `true`. This attribute is set to `false` by default.
5. **Redeploy the TDM LU.**

> **Important notes:**
>
> * The TDM deployment is required to run the `initRefreshTaskDesc` Broadway job, which is configured during the TDM deployment. This job uses the LLM to scan the TDM tasks and update their descriptions in a dedicated table. The TDM Agent uses this information to match tasks to user requests. Deleted tasks (tasks with an **Inactive** status) are ignored by this job.
> * If you upgrade your TDM project from a version earlier than 10.0.1, verify that the `java.project.referencedLibraries` attribute in the workspace `settings.json` file contains the following JAR path: `/opt/apps/fabric/workspace/fabric/lib/fabric/fabric-task*.jar`.
> * The TDM Agent writes its log messages at the **DEBUG** level. To view the TDM Agent messages in the Fabric log, set the log level to DEBUG.


## How It Works

1. The user describes what they need in plain language. The TDM Agent can ask for clarification or additional details when necessary.
2. The TDM Agent searches for the most appropriate task and proposes it to the user. The suggestion logic is also based on the availability of candidate entities in the source environment.
3. In some cases, the agent proposes a few candidate tasks, usually when there is no clear match or when more than a single task is required to fulfill the tester's request. The agent proposes only tasks that match the user's request, without additional parameters that the user did not ask for. Tasks that can only be executed as configured, without modifications (for example, tasks whose execution parameters are all locked), can also be returned by the agent.
4. The TDM Agent makes the required changes and overrides, such as pre-filling the editable execution attributes or adding new ones, based on the user's request.
5. The user can click **Execute Task** to run the task as-is, or manually edit the pre-filled execution attributes before executing. Execution attributes added by the agent can be changed or deleted by the user. The user can also ask the agent to fix the task execution parameters.
6. Starting from TDM 10.1, the user can **save the execution parameters as a draft** and return to it later. This option is available when the task execution window is opened by the TDM Agent.

### Permissions

The TDM Agent evaluates permissions based on the identity and Fabric roles of the user who activates the agent. The agent proposes and prepares only tasks that the user is permitted to execute, and the execution is validated against the user's TDM permissions.


## Invoking the TDM Agent via API (CI/CD Integration)

Starting from TDM 10.1, AI-assisted test data provisioning can be integrated into CI/CD pipelines. A CI/CD pipeline or an external application can provide a user story with its test cases to the TDM Agent. For each test case, the agent identifies the relevant TDM task and execution parameters, saves an execution draft, and returns a URL for executing the task with the required parameters.

**Note:** The API prepares the executions but does not execute them. The task is executed only when the returned execution URL is invoked, or when a user opens the draft in the TDM App and executes it.

The integration flow is as follows:

1. Submit the user story and its test cases using the **createTask** API. The API returns a job ID.
2. Poll the **status** API with the job ID until the job is completed.
3. For each test case with a **READY** status, either invoke the returned `executionUrl` to start the task execution, or open the returned `reviewUrl` to review the execution parameters in the TDM App before executing the task.

### Submit a User Story - createTask API

#### API URL

/test-data-agent/createTask

#### HTTP Method

POST

#### API Category

TDM_AI

#### API Input

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
      <td><strong>sourceEnvironment</strong></td>
      <td>Yes</td>
      <td>Source environment name.</td>
    </tr>
    <tr>
      <td><strong>targetEnvironment</strong></td>
      <td>No</td>
      <td>Target environment name. Omit this parameter for an Extract request. Once the agent infers an Extract action, it does not ask for a target environment.</td>
    </tr>
    <tr>
      <td><strong>businessEntity</strong></td>
      <td>No</td>
      <td>An optional hint for the Business Entity. If it is not set, the agent resolves the Business Entity from the test case text.</td>
    </tr>
    <tr>
      <td><strong>externalUserStoryId</strong></td>
      <td>No</td>
      <td>The caller's own ID of the user story (for example, the ticket ID in the ALM tool). The ID is stored and returned by the status API, so a user story can be traced to its jobs and agent sessions. Resubmitting the same user story starts a new job with a new job ID.</td>
    </tr>
    <tr>
      <td><strong>name</strong></td>
      <td>No</td>
      <td>User story name.</td>
    </tr>
    <tr>
      <td><strong>description</strong></td>
      <td>No</td>
      <td>User story description.</td>
    </tr>
    <tr>
      <td><strong>testCases</strong></td>
      <td>Yes</td>
      <td>
        An array of test cases. Each test case contains the following attributes:
        <ul>
          <li><strong>testCaseId</strong> - mandatory. Must be unique within the request; a request with duplicate test case IDs is rejected with an HTTP 400 error before any job is created. The ID can contain only letters, digits, dots, underscores, and hyphens.</li>
          <li><strong>content</strong> - the test case text.</li>
          <li><strong>requiredCount</strong> - optional. The number of entities required by the test case. If it is not set, the agent infers the number of entities from the test case text.</li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>

#### Request Body Example

```json
{
  "externalUserStoryId": "1",
  "name": "Financial Risk Warning",
  "description": "Display Financial Risk Warning for High-Value Loan Customers...",
  "sourceEnvironment": "Production",
  "targetEnvironment": "UAT",
  "businessEntity": "Customer",
  "testCases": [
    {
      "testCaseId": "1.1",
      "content": "Customer has a personal loan above 100,000...",
      "requiredCount": 5
    },
    {
      "testCaseId": "1.2",
      "content": "Customer has no outstanding loans..."
    }
  ]
}
```

#### API Output Example

```json
{
  "jobId": "8f1b0c8e-6a1e-4a7b-9f1d-2c3e4f5a6b7c",
  "status": "ACCEPTED",
  "testCaseCount": 2,
  "submittedAt": "2026-08-31T09:14:22.031Z",
  "message": "Request accepted. Preparing 2 test cases."
}
```

### Get the Job Status - status API

#### API URL

/test-data-agent/status/{jobId}

#### HTTP Method

GET

#### API Category

TDM_AI

#### API Output

The API returns the job-level status and the following information per test case:

- **status** - the test case status:
  - **PENDING** - the test case has not been picked up yet.
  - **RUNNING** - the agent is working on the test case.
  - **READY** - an execution draft is ready.
  - **FAILED** - no execution could be prepared. The **reasoning** attribute explains why.
- **reasoning** - the agent's explanation of the outcome.
- **taskId** and **draftExecutionId** - the task and the execution draft prepared by the agent. The draft execution ID is stored in **task_execution_list.task_execution_id**, like any other task execution ID.
- **executionUrl** - an absolute URL of the startTask API that runs the prepared draft. The draft ID is sent as the [taskExecutionId](/articles/TDM/tdm_implementation/74_tdm_api/04_execute_task_API.md#start-a-task-from-a-previous-execution) parameter, so the draft's parameters are used as the baseline of the execution. The URL can be added directly to a ticket. It is null when no draft was prepared.
- **reviewUrl** - an absolute URL that opens the draft in the TDM App task execution window for review before executing it. It is null when no draft was prepared.
- **agentSessionId** - the ID of the agent conversation behind the test case, for tracing it in the AI logs.
- **execution** - the live per-LU status of the execution. This attribute is returned only for a prepared test case that has already been executed.

The job-level status is derived from the test case statuses:

- **PROCESSING** - at least one test case is still PENDING or RUNNING.
- **FAILED** - all test cases failed.
- **COMPLETED** - all other cases.

#### API Output Example

```json
{
  "jobId": "8f1b0c8e-6a1e-4a7b-9f1d-2c3e4f5a6b7c",
  "userStoryId": "1",
  "status": "COMPLETED",
  "startedAt": "2026-08-31T09:14:22.031Z",
  "completedAt": "2026-08-31T09:17:40.114Z",
  "testCases": [
    {
      "testCaseId": "1.1",
      "status": "READY",
      "reasoning": "Prepared task 128 'Copy Gold customers', overriding the state filter to IN and the count to 5.",
      "executionUrl": "https://<host>/k2view/api/v2/task/128/startTask?taskExecutionId=5417",
      "reviewUrl": "https://<host>/k2view/app/TDM/tasks/execute?taskExecutionId=5417",
      "agentSessionId": "tdmai-8f1b0c8e-6a1e-4a7b-9f1d-2c3e4f5a6b7c-1-1",
      "taskId": 128,
      "draftExecutionId": 5417
    },
    {
      "testCaseId": "1.2",
      "status": "FAILED",
      "reasoning": "No existing task can satisfy this test case.",
      "agentSessionId": "tdmai-8f1b0c8e-6a1e-4a7b-9f1d-2c3e4f5a6b7c-1-2"
    }
  ]
}
```


## Scope and Limitations

- The TDM Agent currently searches **Business Entity (BE)-based tasks only**. It does not search table-level tasks.

- The TDM Agent currently supports the following task actions:

<table>
  <thead>
    <tr>
      <th>Task Action</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Extract</strong></td>
      <td>Read entities from a source environment into Fabric</td>
    </tr>
    <tr>
      <td><strong>Load</strong></td>
      <td>Provision pre-extracted entities to a target environment</td>
    </tr>
    <tr>
      <td><strong>Extract &amp; Load</strong></td>
      <td>Copy entities from a source to a target environment</td>
    </tr>
    <tr>
      <td><strong>Reserve</strong></td>
      <td>Lock entities in a target environment</td>
    </tr>
    <tr>
      <td><strong>Delete</strong></td>
      <td>Remove entities from a target environment</td>
    </tr>
  </tbody>
</table>

- The TDM Agent does not currently support searching for **Synthetic Generation**, **Entity Clone**, or **Entities & Referential Data** tasks. These tasks can be searched for using the **Search** window.
- **Chat History** — The TDM Agent does not currently support viewing or reopening previous conversations. Users must start a new chat each time they access the agent. Execution drafts saved from the task execution window can be reopened later.
