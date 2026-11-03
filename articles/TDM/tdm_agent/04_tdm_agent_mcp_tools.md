# TDM MCP Tools

The TDM Agent extension exposes TDM capabilities as MCP (Model Context Protocol) tools. Any MCP client, such as an AI assistant or an IDE, can call them to:

- **Look up** the TDM setup: Business Entities, environments, parameters, Custom Logic flows, tasks and execution status.
- **Prepare and start executions**: save an execution draft of a task with overrides, and start a task execution.

The lookup tools take the same inputs and return the same results as the tools the TDM Agent itself uses.

> **Note:** Unlike the TDM Agent chat and REST API, which only prepare executions, an MCP client can **start** a task execution using the **StartTaskExecution** tool. The execution is validated by TDM like any other execution started by the user.

## How the Tools Are Exposed

Starting from Fabric 8.4, every deployed Data Product (LU) is exposed as an MCP server, and Broadway flows tagged `mcp-tool` become its tools. The TDM MCP tools are Broadway flows in the **TDM** LU, tagged `mcp-tool`. They are registered when the TDM LU is deployed with the TDM Agent extension.

MCP clients connect to the TDM LU's MCP server:

```
{fabric-url}/mcp/TDM
```

See [Data Product MCP](/articles/46_MCP/README.md) for the MCP server URL patterns, built-in tools, session handling and configuration.

## Available Tools

### Lookup Tools

<table>
  <thead>
    <tr>
      <th>Tool</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>ListBusinessEntities</strong></td>
      <td>Lists the Business Entities in TDM with their ID, name, description and status.</td>
    </tr>
    <tr>
      <td><strong>ListEnvironments</strong></td>
      <td>Lists the TDM environments with their names, IDs and types.</td>
    </tr>
    <tr>
      <td><strong>GetLUsForBE</strong></td>
      <td>Gets the LUs of a Business Entity.</td>
    </tr>
    <tr>
      <td><strong>GetBEParameters</strong></td>
      <td>Lists the business-parameter names available for a Business Entity in a source environment, for example <code>CUSTOMER.STATE</code>. Returns names only.</td>
    </tr>
    <tr>
      <td><strong>ListCustomLogicFlows</strong></td>
      <td>Lists the Custom Logic flows available for a Business Entity and source environment.</td>
    </tr>
    <tr>
      <td><strong>GetCustomLogicParams</strong></td>
      <td>Returns the input parameters of a Custom Logic flow, including the valid values of parameters backed by a lookup table.</td>
    </tr>
    <tr>
      <td><strong>SearchTasks</strong></td>
      <td>Searches TDM tasks by free text, with optional filters such as task type, source and target environment, Business Entity and selection method. Returns only tasks in the task groups the calling user is permitted to see.</td>
    </tr>
    <tr>
      <td><strong>GetTaskDetails</strong></td>
      <td>Gets the full details of one or more tasks: type, Business Entity, environments, selection method, parameters and the fields that can be overridden at execution.</td>
    </tr>
    <tr>
      <td><strong>GetTaskGroups</strong></td>
      <td>Lists the task groups available to the calling user.</td>
    </tr>
    <tr>
      <td><strong>GetTasksPerTaskGroup</strong></td>
      <td>Lists the tasks of a task group.</td>
    </tr>
    <tr>
      <td><strong>GetTaskExecutionStatus</strong></td>
      <td>Gets the execution status of a task execution: per-LU status and entity counts.</td>
    </tr>
  </tbody>
</table>

### Execution Tools

<table>
  <thead>
    <tr>
      <th>Tool</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>CreateExecutionDraft</strong></td>
      <td>
        Saves an execution draft of a TDM task: the task and a set of execution overrides, stored without starting anything. Returns the <strong>draftExecutionId</strong>, a <strong>status</strong> (SUCCESS or FAILED), a <strong>message</strong>, and a <strong>deeplink</strong> that opens the draft in the TDM App for review.<br>
        Read the task with <strong>GetTaskDetails</strong> first; its selection method, parameters and override fields decide which override keys the task accepts. Creating the draft only checks that the task is active and that every key is a valid override key. Values and permissions are checked when the draft is started.
      </td>
    </tr>
    <tr>
      <td><strong>StartTaskExecution</strong></td>
      <td>
        Starts an execution of a TDM task. <strong>This runs the task for real</strong> and provisions data into the target environment. Without a <strong>draftExecutionId</strong>, the task runs with its own saved settings. With a draft created by <strong>CreateExecutionDraft</strong>, it runs with the draft's overrides; a new execution is created each time and the draft is not consumed.<br>
        Before starting, TDM validates the execution: the task's environments, permissions, Business Entity and LUs, reservation limits and retention periods, and that the same execution is not already running. If any check fails, nothing starts. The optional <strong>forced</strong> input skips only the connection tests of the source and target environments.<br>
        Returns the <strong>taskExecutionId</strong>, a <strong>status</strong> (SUCCESS, FAILED or WARNING), a <strong>message</strong>, and <strong>details</strong> with the validation errors on failure. Track the execution with <strong>GetTaskExecutionStatus</strong>.
      </td>
    </tr>
  </tbody>
</table>

## Security

- **Authentication** - MCP clients authenticate with the same Fabric authentication methods as Fabric web services, for example an API key or OAuth.
- **Authorization** - Fabric RBAC authorizes MCP access at the Data Product (LU) level and at the entity-instance level. Permissions cannot be set per tool, so a role with access to the TDM LU's MCP server can call all its tools.
- **What the tools can change** - The lookup tools only read TDM definitions and execution status. **CreateExecutionDraft** writes an execution draft. **StartTaskExecution** starts a task execution with the calling user's identity, subject to TDM's permission and execution validations. No tool creates or changes task definitions.
- **Access to execution** - Because permissions cannot be set per tool, any user or AI client with MCP access to the TDM LU can also start executions that the user's TDM permissions allow. Grant MCP access to the TDM LU only to approved roles and approved AI clients.
- **Data and LLM** - The tool results are returned to the MCP client and processed by the client's own LLM, not by the LLM interface defined in Fabric. The customer's policy for that AI client applies.
- **Enabling** - Fabric MCP is enabled by default. It can be turned off with the <code>ENABLE_MCP_SERVLET</code> setting in <code>config.ini</code>. See [MCP Operations](/articles/46_MCP/03_data-product-mcp-operations.md).

For the full security model of the TDM Agent, see [TDM Agent Security and Privacy](05_tdm_agent_security_and_privacy.md).



[![Previous](/articles/images/Previous.png)](03_tdm_agent_api.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_tdm_agent_security_and_privacy.md)
