# Security and Privacy

An AI Fusion agent acts on real customer data and decides for itself which tools to call. Securing it means controlling three things: **who** can run the agent and read its conversations, **what data** each conversation can reach, and **what the model is allowed to do** with it. This article describes the controls AI Fusion gives you for each, what it deliberately does not do (such as masking), and how conversation data is retained and audited.



## Authentication and Authorization

All AI Fusion endpoints are Fabric web services and use Fabric's standard authentication — a Fabric session, an API key, a JWT, OAuth or Basic authentication. See [Fabric Web Services Security](/articles/26_fabric_security/05_fabric_webservices_security.md).

The agent and Observation endpoints run with the **caller's permissions**; none of them runs with elevated privileges. Authorization is therefore Fabric role-based access control:

- Grant each role only the web services it needs. End-user applications typically need `agent`, `stop-session`, `session-status`, `chats`, `conversation-history` and `feedback`. Observation endpoints such as `retrieve-conversations`, `chat-review`, the widget endpoints and the trace endpoints are for operators and analysts.
- Grant the role access to the Data Products the agent's flows read.
- To restrict a caller to a single entity instance, use Fabric's IID-based access control. See [JWT Custom Claims and IID Access Control](/articles/26_fabric_security/06_jwt-custom-claims-and-iid-access-control.md).

### Session-Level Access

Endpoints that take a sessionId — `conversation-history`, `trace-history`, `trace-conversation`, `session-status`, `stop-session` and `feedback` — do not check that the session belongs to the caller. Anyone allowed to call them can read or stop a session whose ID they know. Always use unguessable session IDs (random UUIDs), and do not expose the trace endpoints to end users.

`chats` is the exception: it always lists the **authenticated user's** own chats; the user cannot be passed as a parameter.

`retrieve-conversations` (the Observation conversation list) accepts a `submitted-by` filter with **any** user name, so a caller with access to it can list other users' conversations. Restrict it, and the rest of the Observation module, to the roles that review conversations. See [Observation](/articles/AI_fusion/05_observation/README.md).

### Studio-Only Endpoints

Endpoints that change evaluation assets or snapshots — `agent/case/save`, `agent/case/update`, `agent/case/duplicate`, `agent/case/load`, `agent/{luName}/{agentName}/testRun`, `suite/enabled` and `case/history/delete` — work only in AI Fusion Studio. In any other environment they return HTTP 403. Path-like names (`/`, `\`, `..`) are rejected with HTTP 400.



## Trusted Inputs

The values that decide **whose** data a conversation may access — typically a customer ID — must come from your application, never from the model.

- **initParams are the trusted channel.** Your application sends the agent's inputs in `initParams` on every request (see [Invoking Agents](/articles/AI_fusion/02_agents/09_invoking_agents.md)). Derive them from the authenticated user, not from anything the end user types.
- **Read them with the AgentInitParams actor.** In any hook or tool flow the runtime invokes directly, the `AgentInitParams` actor returns the main agent's init parameters, session ID, step ID, agent name and LU. These values are immutable — the model cannot change them, even inside a sub-agent. See [Hooks and Agent Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md).
- **Hook externals are inputs, not proof.** A hook flow's `external:` declarations become the agent's inputs, and `mandatory: true` makes one required — a request without it fails before the turn starts. But a sub-agent's inputs can be supplied by the parent model through `task` arguments, so a sub-agent flow that enforces authorization must read the scope from `AgentInitParams`, not from its own external.
- **Reserved names cannot be spoofed through delegation.** Runtime field names such as `query`, `sessionId`, `tool_name` or `result` are dropped from the arguments a parent model passes to a sub-agent, so the model cannot override the values the runtime supplies to hook flows.
- **`now` is privileged.** A pinned date and time is written into the persisted step record, so the REST API rejects an `initParams` containing `now` with HTTP 400. Only Evaluation runs may pin it.



## Entity Scoping

An agent works on one entity at a time. The `step_start` hook loads the entity's instance (for example with `FabricGet`, or `GetWithSnap` when snapshots are wanted) using the trusted customer ID, and the built-in `query` tool then runs SQL against that instance's micro-database. Each micro-database holds the data of a single entity, so a correctly scoped agent cannot see another customer's rows. See [Tools](/articles/AI_fusion/02_agents/03_tools.md).

Design points:

- **Load the entity from the trusted input** in `step_start`, on every turn and in every sub-agent that queries data.
- **`query` has no read-only enforcement.** The model writes the SQL, and the statement is executed on the agent's Fabric session as written; it is not limited to `SELECT`. If that is not acceptable, attach a `before` hook to `query` that blocks anything other than a read-only statement, or turn `query` off (list `tools:` without it) and give the agent purpose-built flow tools instead.
- **Flow tools receive model-supplied arguments.** Validate them in the flow, and take identity and scope from `AgentInitParams`.
- **Keep the `fabric` tool off.** The built-in `fabric` tool runs arbitrary Fabric commands with the session's permissions. It is off by default; do not enable it in a customer-facing agent. If an internal agent needs it, attach a `before` hook that allows only the commands you expect.



## Guardrails on Agent Behavior

<table>
<tbody>
<tr>
<td><strong>Control</strong></td>
<td><strong>What it does</strong></td>
</tr>
<tr>
<td><code>pre_tool_call</code> hook</td>
<td>Runs before every tool call and returns <code>ALLOW</code> or <code>BLOCK</code>. A blocked call is not executed; the model receives "Tool call blocked by agent policy". <strong>Fail-safe:</strong> if the hook flow itself fails, the call is blocked.</td>
</tr>
<tr>
<td>Per-tool <code>before</code> hook</td>
<td>The same contract, scoped to one tool. The global <code>pre_tool_call</code> runs first, and a global <code>BLOCK</code> wins.</td>
</tr>
<tr>
<td><code>post_tool_call</code> hook and per-tool <code>after</code> hook</td>
<td>Inspect a tool's result; returning <code>stop: true</code> ends the run, optionally with your own result. Use it to stop on sensitive output or audit tool use.</td>
</tr>
<tr>
<td><code>step_end</code> hook</td>
<td>Validates the final answer and can send the agent back for a correction (bounded by <code>max_step_continuations</code>).</td>
</tr>
<tr>
<td><code>constraints</code></td>
<td>Non-negotiable rules appended last to the system prompt. When a turn is delegated, the caller's constraints are <strong>added</strong> to the sub-agent's own, so a chain of delegations can only narrow what may be said. See <a href="/articles/AI_fusion/02_agents/06_subagents_and_delegation.md">Sub-Agents and Delegation</a>.</td>
</tr>
<tr>
<td>Tool-name guard</td>
<td>Always on. A tool call whose name was not advertised to the agent is blocked, so the model cannot invoke a flow just by guessing its name.</td>
</tr>
<tr>
<td><code>max_iterations</code></td>
<td>Caps the LLM iterations per turn (default 20). At the limit, tools are blocked and the model must answer.</td>
</tr>
<tr>
<td>Delegation depth</td>
<td>Sub-agents can be nested at most 3 levels deep.</td>
</tr>
</tbody>
</table>

Prompt instructions alone are not a security boundary. Put anything that must hold — authorization, data scope, forbidden actions — in hooks and flows, and use `constraints` and `response_prompt` for how the agent speaks. See [Hooks and Agent Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md).



## Masking and Data Sent to the Model

**AI Fusion does not mask data in the agent pipeline.** Whatever a tool returns — query results, flow output, MCP responses — is sent to the LLM provider as is, and is stored in the session's trace.

To keep sensitive values away from the model:

- **Mask inside your tool and hook flows**, before the data is returned to the agent, using Fabric's masking capabilities. See [Data Masking](/articles/41_masking/README.md).
- **Return only what the agent needs.** A flow tool that returns a few columns exposes less than open SQL over the whole micro-database; a skill can steer which tables and columns the agent queries.
- **Use provider-side controls where available.** On AWS Bedrock, set **GuardrailID** (and **GuardrailVersion**) on the interface to apply a Bedrock Guardrail — content filters, denied topics or PII redaction — to every call. See [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md).
- **Keep data in your network** when required by running models through a private endpoint, or on your own infrastructure with the Ollama connector (preview).



## Secrets

Do not keep provider API keys or cloud credentials in plain text in the project. Use Fabric's secret manager and environment-level interface settings, and prefer identity-based authentication — the instance IAM role for Bedrock, Application Default Credentials for Google. Google's **CREDENTIAL** property is stored through the secret manager. See [Secrets Management](/articles/26_fabric_security_sm/04a_secret_manager.md).



## Data Retention

Conversations, traces and snapshots are kept until a cleanup process removes them. Two globals of the `aifusion` LU control the retention windows:

<table>
<tbody>
<tr>
<td><strong>Global</strong></td>
<td><strong>Default</strong></td>
<td><strong>Range</strong></td>
<td><strong>Applies to</strong></td>
</tr>
<tr>
<td><code>RETENTION_DAYS</code></td>
<td>30</td>
<td>7–365</td>
<td>Conversations: closed sessions older than this are deleted — their Insights rows, session and instance records, local snapshots and the session's <code>aifusion</code> instance.</td>
</tr>
<tr>
<td><code>EVALUATION_STORAGE_RETENTION_DAYS</code></td>
<td>90</td>
<td>30–365</td>
<td>Evaluation storage: snapshot files older than this that no evaluation suite references any more.</td>
</tr>
</tbody>
</table>

Values outside the range are clamped. A session counts as closed once the Observation analyzer has processed it; sessions that were never analyzed are not removed by the cleanup.

To run the conversation cleanup on demand, call `POST /api/v1/cleanup/start` (optional `retention_days`). To schedule it, run the `aifusion.cleanupJob` flow once: it registers a Broadway job that runs the cleanup daily at 02:00. `aifusion.cleanupEvaluationStorageJob` likewise registers the evaluation-storage cleanup weekly (Sunday 03:00). Because `cleanup/start` deletes data, grant it only to administrators.



## Audit Trail

<table>
<tbody>
<tr>
<td><strong>What</strong></td>
<td><strong>Where</strong></td>
</tr>
<tr>
<td>Every question and answer, with timestamps</td>
<td>The session's step records; also used for <code>conversation-history</code>.</td>
</tr>
<tr>
<td>Every message, tool call (with arguments and result), agent and sub-agent execution, and token usage</td>
<td>The session's trace tables, written when <code>observability</code> is on. Read them with the trace endpoints or the Studio trace panel. See <a href="/articles/AI_fusion/02_agents/08_sessions_and_context.md#observability">Sessions and Context</a>.</td>
</tr>
<tr>
<td>Who started each conversation, with which agent and Data Product</td>
<td>The session record in the Insights database, used by the Observation module and the <code>chats</code> API.</td>
</tr>
<tr>
<td>End-user feedback</td>
<td>The feedback table; the submitting user is taken from the authenticated session (<code>submitted_by</code>). See <a href="/articles/AI_fusion/05_observation/06_feedback.md">Feedback</a>.</td>
</tr>
<tr>
<td>Stopped turns</td>
<td>The partial answer followed by <code>&lt;ABORTED BY USER&gt;</code> in the step record.</td>
</tr>
<tr>
<td>Runtime errors</td>
<td>The Fabric server log. The end user only sees a generic message (or the one your <code>on_error</code> hook returns).</td>
</tr>
</tbody>
</table>

The trace contains the data the agent saw, including any unmasked values. Protect the trace endpoints, the Insights database and the retention settings accordingly. For the overall runtime picture, see [Architecture](/articles/AI_fusion/03_architecture.md).
