# TDM Agent – Architecture and Security

The TDM Agent runs entirely inside the customer's K2view Fabric deployment and only prepares TDM task executions — a person always reviews and runs them. This article describes how the TDM Agent is built on top of Fabric and AI Fusion, what data reaches the LLM, and which security controls apply. It is intended for information-security and architecture reviewers.

For what the TDM Agent does and how to install it, see [TDM Agent Overview](01_tdm_agent_overview.md).



## Solution Overview

A tester describes a user story and its test cases, or a specific data need, in plain language. For each case, the agent finds an existing TDM task that can satisfy it, builds the override parameters, and returns a link a person clicks to review and run it.

The TDM Agent can be reached through three channels:

<table>
<thead>
<tr>
<th>Channel</th>
<th>Used by</th>
<th>Typical input</th>
<th>Output</th>
</tr>
</thead>
<tbody>
<tr>
<td><strong>Chat in the TDM UI</strong></td>
<td>Tester or developer, interactively</td>
<td>Usually one request, such as "I need 6 VIP customers with an address in New York City"</td>
<td>A plain-English answer and one review/run link. The agent asks when something is ambiguous.</td>
</tr>
<tr>
<td><strong>REST API</strong> (<code>createTask</code>, <code>status/{jobId}</code>). See <a href="02_tdm_agent_api.md">Invoking the TDM Agent via API</a>.</td>
<td>Test-management tool or CI pipeline</td>
<td>A user story with its test cases. For example, story: a CRM service shows special offers by customer status and location. Cases: 6 VIP customers in NYC, 6 Regular customers in NYC, 4 VIP customers outside NYC.</td>
<td>One prepared execution per case, READY or FAILED with the reasoning. The agent never asks questions.</td>
</tr>
<tr>
<td><strong>MCP tools</strong></td>
<td>An MCP client, such as an AI assistant or IDE</td>
<td>A lookup, such as "which tasks load customers to UAT?"</td>
<td>Lookup results only: business entities, environments, parameters, tasks, execution status. Nothing is prepared or started.</td>
</tr>
</tbody>
</table>

In chat and the REST API, the agent works out the Business Entity, the environments and the action, picks an existing task and builds its override. The MCP tools are read-only lookups; they never prepare or start an execution.

### Core Control Principle: It Prepares, It Never Executes

<table>
<thead>
<tr>
<th>The agent will</th>
<th>The agent will not</th>
</tr>
</thead>
<tbody>
<tr>
<td>Reuse existing, administrator-defined TDM tasks</td>
<td>Create new tasks from scratch</td>
</tr>
<tr>
<td>Prepare a task execution with overrides</td>
<td>Start an execution — a user clicks the link</td>
</tr>
<tr>
<td>Ask when the entity, environment or action is ambiguous</td>
<td>Guess an environment from its type</td>
</tr>
<tr>
<td>Explain each condition it will select by</td>
<td>Show raw override JSON or internal IDs</td>
</tr>
</tbody>
</table>

Every data movement therefore still passes through TDM's existing task permissions, environment permissions and execution controls.



## Architecture

The TDM Agent is a set of AI Fusion agents and Broadway tool flows deployed on the customer's own K2view Fabric, beside TDM. It adds no new external service. The only outbound call is to the LLM provider the customer configures.

![TDM Agent architecture on K2view Fabric](images/tdm_agent_architecture.svg)

Requests enter only through authenticated Fabric web services and MCP. The agents reach TDM only through allow-listed tool flows. Data moves between environments only when a person starts the prepared TDM execution.

<table>
<thead>
<tr>
<th>Layer</th>
<th>Components</th>
</tr>
</thead>
<tbody>
<tr>
<td><strong>Clients</strong></td>
<td>TDM chat UI; CI or test-management tools through the REST API; MCP clients</td>
</tr>
<tr>
<td><strong>Access</strong></td>
<td>Fabric web services and MCP servers, Fabric authentication and RBAC</td>
</tr>
<tr>
<td><strong>Agents</strong></td>
<td>Specialized AI Fusion agents for request handling, task search and override building, deployed in the TDM Data Product</td>
</tr>
<tr>
<td><strong>Tools</strong></td>
<td>A fixed set of tools to search tasks, read task and parameter definitions, check execution status and prepare a draft execution (allow-listed per agent)</td>
</tr>
<tr>
<td><strong>TDM</strong></td>
<td>Task catalog, environments, draft executions, permissions, execution engine</td>
</tr>
<tr>
<td><strong>Platform</strong></td>
<td>K2view Fabric: flow engine, background jobs, secret manager, LLM interfaces</td>
</tr>
<tr>
<td><strong>Storage</strong></td>
<td>Conversation traces, session records, API requests and draft executions, all in the customer's Fabric and TDM databases</td>
</tr>
</tbody>
</table>



## Agentic Flow

Each chat or REST API request passes through a fixed chain of specialized agents. A trusted hook sets the scope, the LLM works only through allow-listed tools, and the run always ends at a draft that a person must start.

![TDM Agent agentic flow](images/tdm_agent_flow.svg)

In chat, when several good matches exist, they are shown to the user to choose from. Through the REST API, each test case runs as its own audited session and never asks questions. It ends READY with a review link, or FAILED with the agent's reasoning.



## Data Sent to the LLM

The LLM sees TDM configuration and selection metadata, plus value lists and counts. It never receives source records: the agent has no SQL or free-query tool, and no tool returns entity rows.

<table>
<thead>
<tr>
<th>Data category</th>
<th>Example</th>
<th>Sent to LLM</th>
</tr>
</thead>
<tbody>
<tr>
<td>User request</td>
<td>"6 Gold customers from Production to UAT"</td>
<td>Yes</td>
</tr>
<tr>
<td>TDM task definitions</td>
<td>Task name, action, selection method, editable fields</td>
<td>Yes</td>
</tr>
<tr>
<td>Entity IDs in an Entity List task</td>
<td>The task's stored ID list</td>
<td>Yes, when such a task is a candidate. This applies when the user's request contains IDs, and is under the user's responsibility.</td>
</tr>
<tr>
<td>Business Entities and environments</td>
<td>BE names, environment names and descriptions</td>
<td>Yes</td>
</tr>
<tr>
<td>Business-parameter metadata</td>
<td>Parameter names, types, ranges</td>
<td>Yes</td>
</tr>
<tr>
<td>Business-parameter value examples</td>
<td>Distinct values such as tier = Gold, Silver</td>
<td>Yes</td>
</tr>
<tr>
<td>Entity counts</td>
<td>"412 customers match"</td>
<td>Yes</td>
</tr>
<tr>
<td>Custom Logic flow parameters</td>
<td>Parameter names and lookup values</td>
<td>Yes</td>
</tr>
<tr>
<td>Source-environment entity records (rows)</td>
<td>Customer name, address, balance</td>
<td><strong>No</strong></td>
</tr>
<tr>
<td>Credentials, API keys, connection strings</td>
<td>—</td>
<td><strong>No</strong></td>
</tr>
</tbody>
</table>

**LLM provider and data residency.** The agent calls the environment's default LLM interface. The customer chooses and configures the provider:

- Anthropic
- AWS Bedrock (with optional Bedrock Guardrails for PII redaction)
- Azure OpenAI or OpenAI
- Google Gemini / Vertex AI
- A self-hosted model through Ollama (preview)

With a private endpoint or a self-hosted model, no data leaves the customer's network. K2view does not host or proxy the LLM traffic. See [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md).

**MCP tools.** Results returned to an MCP client go to that client's own model, not to the LLM configured in Fabric. They are the same kinds of lookup results listed in the table above. The customer's policy for that AI client applies.

**Reducing exposure further.** Value examples come from business-parameter definitions. If parameter values are sensitive (for example, if they hold personal identifiers), mask them in the parameter definition or exclude them from the parameter list.



## Security Controls

The agent has no privileges of its own. It runs as the calling user, cannot run queries or Fabric commands, and can only write a draft execution that TDM must still authorize and a person must start.

<table>
<thead>
<tr>
<th>Area</th>
<th>Control</th>
</tr>
</thead>
<tbody>
<tr>
<td><strong>Authentication</strong></td>
<td>Standard Fabric authentication: SSO/OAuth, JWT, API key, Fabric session or Basic. See <a href="/articles/26_fabric_security/05_fabric_webservices_security.md">Fabric Web Services Security</a>.</td>
</tr>
<tr>
<td><strong>Authorization</strong></td>
<td>Fabric RBAC on each web service. Chat and REST API web services run without elevated permissions.</td>
</tr>
<tr>
<td><strong>TDM permissions</strong></td>
<td>Task search applies the user's TDM permission group. Starting an execution goes through TDM's normal role and environment checks.</td>
</tr>
<tr>
<td><strong>API identity</strong></td>
<td>Each test case runs with the submitting user's name and Fabric roles.</td>
</tr>
<tr>
<td><strong>Tool surface</strong></td>
<td>An explicit tool allow-list per agent. Built-in SQL and Fabric-command tools are disabled. Calls to tools outside the list are blocked.</td>
</tr>
<tr>
<td><strong>Write scope</strong></td>
<td>One draft execution record per prepared case, marked as AI-created and linked to the agent session, only on Active tasks. Internal-only override keys are rejected.</td>
</tr>
<tr>
<td><strong>Execution</strong></td>
<td>None by the agent. A user starts the run from the review or execution link.</td>
</tr>
<tr>
<td><strong>Guardrail hooks</strong></td>
<td>A pre-tool hook blocks an incomplete preparation. Post-tool hooks end the run once preparation completes or an edit fails.</td>
</tr>
<tr>
<td><strong>Run limits</strong></td>
<td>A capped number of LLM iterations per turn, continuations and sub-agent depth.</td>
</tr>
<tr>
<td><strong>Secrets</strong></td>
<td>LLM credentials live in the Fabric interface, kept in the Fabric secret manager or provided by cloud identity (IAM role, ADC), and never in the agent or the prompt. See <a href="/articles/26_fabric_security_sm/04a_secret_manager.md">Secrets Management</a>.</td>
</tr>
<tr>
<td><strong>Encryption in transit</strong></td>
<td>HTTPS/TLS between client and Fabric, and between Fabric and the LLM provider</td>
</tr>
<tr>
<td><strong>Encryption at rest</strong></td>
<td>Inherited from the Fabric storage and TDM database configuration</td>
</tr>
</tbody>
</table>

Prompt rules (never execute, never reveal override JSON) are a second layer. The hard boundaries are the tool allow-list, the hooks and TDM's own permissions.

**MCP access.** Fabric exposes each deployed Data Product as an MCP server, and this is enabled by default. MCP clients authenticate with the same Fabric methods as the web services, and Fabric RBAC authorizes them at Data Product and entity-instance level. Permissions cannot be set per tool. The TDM MCP tools are read-only, and task search returns only tasks in groups the user may see. See [TDM MCP Tools](03_tdm_agent_mcp_tools.md) and [Data Product MCP](/articles/46_MCP/README.md).



## Retention and Audit Trail

Every conversation is recorded inside the customer's Fabric deployment and deleted by a scheduled cleanup after a configurable window.

**Retention**

<table>
<thead>
<tr>
<th>Setting (aifusion LU global)</th>
<th>Default</th>
<th>Range</th>
<th>Applies to</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>RETENTION_DAYS</code></td>
<td>30 days</td>
<td>7–365</td>
<td>Closed conversations: session records, traces, snapshots</td>
</tr>
<tr>
<td><code>EVALUATION_STORAGE_RETENTION_DAYS</code></td>
<td>90 days</td>
<td>30–365</td>
<td>Evaluation snapshot files no longer referenced</td>
</tr>
</tbody>
</table>

Cleanup is a built-in job that the customer turns on and schedules as needed.

**Audit trail**

<table>
<thead>
<tr>
<th>What is recorded</th>
<th>Where</th>
</tr>
</thead>
<tbody>
<tr>
<td>Each request and answer, with timestamps</td>
<td>Session step records</td>
</tr>
<tr>
<td>Every LLM message, tool call (arguments and result) and token usage</td>
<td>Session trace tables (aifusion LU instance per session)</td>
</tr>
<tr>
<td>Who started each conversation, with which agent</td>
<td>Session record in the Insights database</td>
</tr>
<tr>
<td>Each API request, its test cases and the per-case agent session ID</td>
<td>Request status records in the TDM database</td>
</tr>
<tr>
<td>Each prepared TDM execution</td>
<td>TDM execution records, under TDM's own audit</td>
</tr>
<tr>
<td>Runtime errors</td>
<td>Fabric server log (the user sees a generic message)</td>
</tr>
</tbody>
</table>

The trace holds what the agent saw. Access to trace endpoints and the Insights database should be limited to operators.



## Shared Responsibility

K2view delivers the agent and its built-in controls. The customer owns the deployment, the identities and the choice of LLM.

<table>
<thead>
<tr>
<th>Area</th>
<th>K2view</th>
<th>Customer</th>
</tr>
</thead>
<tbody>
<tr>
<td>Agent logic, tool allow-list, hooks</td>
<td>Delivers and maintains</td>
<td>Deploys the extension</td>
</tr>
<tr>
<td>Hosting, network, encryption at rest</td>
<td>—</td>
<td>Operates Fabric and TDM in its own environment</td>
</tr>
<tr>
<td>Identity and roles</td>
<td>Fabric RBAC and TDM permission model</td>
<td>Maps users to roles and grants the agent web services only to approved roles</td>
</tr>
<tr>
<td>LLM provider</td>
<td>Connectors for the supported providers</td>
<td>Selects the provider, region and endpoint, and holds the provider contract</td>
</tr>
<tr>
<td>Sensitive parameter values</td>
<td>Masking capabilities in Fabric</td>
<td>Decides which business parameters expose values</td>
</tr>
<tr>
<td>Retention</td>
<td>Configurable cleanup jobs</td>
<td>Sets retention windows and schedules cleanup</td>
</tr>
<tr>
<td>Running executions</td>
<td>Never starts them</td>
<td>Reviews and starts each execution</td>
</tr>
</tbody>
</table>

### Recommended Configuration

1. Use a private or in-region LLM endpoint (for example Bedrock in the customer's AWS account or Azure OpenAI), or a self-hosted model.
2. Grant the chat and `test-data-agent/*` web services only to tester and pipeline roles. Limit trace and observation endpoints to operators.
3. Give each CI pipeline or test-management tool that calls the REST API its own Fabric user or API key, with only the REST API role. Its requests are then traceable to that tool and can be revoked separately.
4. Turn off Fabric MCP if it is not used. Otherwise, give MCP access to the TDM Data Product only to approved roles and approved AI clients.
5. Review which business parameters expose value lists. Mask or remove any that hold personal data.
6. Set the retention window to match the customer's policy, and turn on the cleanup schedule.



## Related Articles

- [TDM Agent Overview](01_tdm_agent_overview.md)
- [Invoking the TDM Agent via API](02_tdm_agent_api.md)
- [TDM MCP Tools](03_tdm_agent_mcp_tools.md)
- [AI Fusion Security and Privacy](/articles/AI_fusion/02_agents/11_security_and_privacy.md)
- [AI Fusion Architecture](/articles/AI_fusion/03_architecture.md)
- [Fabric Security Overview](/articles/26_fabric_security/01_fabric_security_overview.md)
- [Data Product MCP](/articles/46_MCP/README.md)
- [TDM Permission Group Mapping](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md)



[![Previous](/articles/images/Previous.png)](03_tdm_agent_mcp_tools.md)
