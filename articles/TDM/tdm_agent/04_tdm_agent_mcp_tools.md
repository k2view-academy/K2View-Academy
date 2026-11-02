# TDM MCP Tools

The TDM Agent extension exposes a set of read-only TDM lookups as MCP (Model Context Protocol) tools. Any MCP client, such as an AI assistant or an IDE, can call them to explore the TDM setup: Business Entities, environments, parameters, Custom Logic flows, tasks and execution status.

The tools take the same inputs and return the same results as the tools the TDM Agent itself uses. They do not prepare or start task executions.

## How the Tools Are Exposed

Starting from Fabric 8.4, every deployed Data Product (LU) is exposed as an MCP server, and Broadway flows tagged `mcp-tool` become its tools. The TDM MCP tools are Broadway flows in the **TDM** LU, tagged `mcp-tool`. They are registered when the TDM LU is deployed with the TDM Agent extension.

MCP clients connect to the TDM LU's MCP server:

```
{fabric-url}/mcp/TDM
```

See [Data Product MCP](/articles/46_MCP/README.md) for the MCP server URL patterns, built-in tools, session handling and configuration.

## Available Tools

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

## Security

- **Authentication** - MCP clients authenticate with the same Fabric authentication methods as Fabric web services, for example an API key or OAuth.
- **Authorization** - Fabric RBAC authorizes MCP access at the Data Product (LU) level and at the entity-instance level. Permissions cannot be set per tool, so a role with access to the TDM LU's MCP server can call all its tools.
- **Read-only** - The TDM MCP tools only read TDM definitions and execution status. They do not create, change or execute tasks.
- **Data and LLM** - The tool results are returned to the MCP client and processed by the client's own LLM, not by the LLM interface defined in Fabric. The customer's policy for that AI client applies.
- **Enabling** - Fabric MCP is enabled by default. It can be turned off with the <code>ENABLE_MCP_SERVLET</code> setting in <code>config.ini</code>. See [MCP Operations](/articles/46_MCP/03_data-product-mcp-operations.md).

For the full security model of the TDM Agent, see [TDM Agent Security and Privacy](05_tdm_agent_security_and_privacy.md).



[![Previous](/articles/images/Previous.png)](03_tdm_agent_api.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_tdm_agent_security_and_privacy.md)
