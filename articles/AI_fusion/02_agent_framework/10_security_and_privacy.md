# Privacy, Security & Agent Monitoring

AI Fusion is built on top of K2view Fabric - the Data Product platform - and inherits its data-governance model. Agents created in AI Fusion run inside that model: they cannot reach beyond the data the platform allows them to see, every LLM interaction is bounded by runtime guardrails, and every step is recorded for review.

This article describes the two complementary halves of that model:

1. **Guardrails** - the structural and runtime limits applied to agents so they cannot exceed their intended scope.
2. **Monitoring & Auditing** - the persistent record of what each agent did, surfaced in the Observation module and stored in the AI Fusion data model for review, evaluation, and compliance.



## Guardrails on Agents

### Entity-Level Access Control

AI Fusion agents operate against Fabric **Micro-Databases** (Logical Unit Instances, or LUIs) rather than against an organization-wide data lake. Each chat session is bound to a single entity - typically a customer, account, employee, or device - identified by an Instance ID (`iid`). Every tool the agent invokes runs in the context of that single LUI.

This isolation is structural, not a prompt-level instruction. The agent has no addressable path to data belonging to other entities, regardless of what the LLM is asked or instructed to do. A customer using a chatbot cannot retrieve another customer's records; an HR assistant cannot reach a colleague's profile.

The entity binding is established when the chat session is created and is carried through every downstream LLM call, tool execution, and trace record.

### Session Isolation

Beyond the per-entity binding above, **each chat session is itself an LUI** - an instance of the `aifusion` Logical Unit. The conversation history, execution trace, messages, tool calls, and token usage that the agent relies on as its working memory are persisted in that session's Micro-Database, identified by the session `iid`.

This matters because the agent's memory across a conversation is reconstructed from the persisted session data on every turn. The `aifusion` LU defines the schema for these tables (`MESSAGES`, `STEP`, `EXECUTIONS`, `TOOL_CALLS`, `USAGE`), and each chat session gets its own instance of them inside its own Micro-Database. An agent handling one session can only read the records of that session's LUI - it has no addressable path to another session's history, just as it has no path to another customer's business data.

### Role-Based Permissions and Data Partitioning

On top of entity binding, Fabric's role-based security profiles determine which **fields, tables, and operations** are visible inside each Micro-Database. The same role definitions that govern human and application access to Fabric data govern what an agent's tools can read and write.

Concretely:

- A role that hides salary fields from a frontline support representative (using [security profiles](articles/17_fabric_credentials/05_security_profiles.md) and *Declarative Field Level Authorization* mechanism) also hides those fields from any agent invoked under that role.
- A role that disallows updates to a particular table also blocks any tool the agent might attempt that would perform such an update.
- Schema filtering ensures the LLM is only made aware of the tables and columns that the active role is permitted to see - sensitive structures are not even named in the context passed to the model.

This means agent authorization is not maintained in a separate, parallel system. It is the same Fabric authorization model that governs the rest of the platform.

### Bounded Agent Execution

Agents in AI Fusion are not given unbounded latitude to call tools or loop indefinitely. Every agent invocation is wrapped in execution limits enforced by the framework:

| Limit | Purpose |
|---|---|
| **Tool calls per agent invocation** | Hard-coded ceiling on tool calls within a single agent invocation. Prevents runaway agents that loop forever or chain too many tool calls in a single turn. The agent invocation is terminated with an explicit error if exceeded. |
| **Concurrent LLM calls per interface** | Throttle, not a hard cap: when 20 calls are already in-flight against the same LLM Interface, additional calls wait and poll until a slot frees (up to ~50 seconds) before proceeding. Prevents bursty load from overwhelming a single LLM provider. |

When the tool-call limit is reached, the agent invocation terminates with an explicit error rather than silently truncating - the failure is visible in the trace and the Observation module.

### Tool Allowlisting

An agent in AI Fusion does not have access to "every tool in the project." Each agent is configured with a specific list of tools that it is allowed to invoke. Tools not present in that list are not exposed to the LLM in the function-calling schema and cannot be invoked from that agent.

This allowlist is part of the agent definition itself and is reviewed during implementation. It is the primary mechanism for narrowing an agent's capability surface to only what its intended use case requires.



## Monitoring, Tracing & Auditing

Every agent execution in AI Fusion is recorded in detail. The persistence model serves three purposes: live debugging during development (the **Trace** pane), longitudinal review in production (the **Observation** module), and durable audit storage for compliance and evaluation workflows.

### The Trace Pane - Live Execution Detail

The Trace pane, available next to any chat in the Chat Playground and the production chat UI, exposes the full internal flow of each agent turn in real time. For details on the Trace UI itself, see [Chat Playground, Trace and Debug](09_chat_playground.md).

For each conversation turn, the Trace records:

- **The plan of stages executed** - for example: Reflect → Goal Refiner → Domain Agent → Responder.
- **System prompts, goals, and instructions** delivered to each agent.
- **User input** and **LLM responses** at every iteration, including intermediate model reasoning.
- **Tool calls** made by the LLM - function name, arguments, and the LLM's intent.
- **Tool execution results** - what the tool returned, and how long the execution took.
- **Token consumption** per stage, split into input, output, and cached tokens.
- **Latency** per stage, in milliseconds.

The Trace is a complete reconstruction of how the agent arrived at its response. Nothing the LLM saw or produced is hidden from this view.

### The Observation Module - Historical Review

The **Observation** module is the production-facing surface for reviewing conversations and aggregate metrics over time. It is reachable from the top navigation of the AI Fusion web application.

#### Conversation Review

Observation lists every conversation that has run against the deployed agent, filterable by date range, customer (IID), application, and feedback signal. Opening a conversation reveals:

- The full conversation transcript with all user and assistant turns.
- The full Trace for every turn (same detail as the live Trace pane).
- Any feedback submitted against any turn (thumbs up/down, free-text note, submitter).
- Token and duration metrics per turn and per conversation.

This is the primary surface QA engineers and supervisors use to investigate problematic interactions and to extract conversations for evaluation pipelines.

#### Dashboard Widgets

The Observation dashboard aggregates activity into configurable widgets, showing trends across:

- **Conversation volume** - chats per day/week, filterable by application or customer segment.
- **Token consumption** - total tokens, average tokens per conversation, breakdown by interface and model, including cached-token ratios.
- **Latency** - average duration per conversation, time-to-first-token distribution.
- **Feedback distribution** - counts of helpful / not-helpful / confused / great reactions over time.
- **Cost metrics** - derived from token usage and per-model pricing.

Widgets can be filtered by time window (7-day, 30-day, custom ranges) and by attributes such as customer or application.

#### Conversation Export

Any conversation in Observation can be exported as a ZIP package - including the transcript, trace, metadata, and optionally a snapshot of the LUI (the entity's Micro-Database state at the time of the conversation). This export feeds the Evaluation workspace, turning real conversations into reproducible test cases. See [Feedback Integration](14_feedback_integration.md) for the end-to-end workflow.

### What Is Persisted - The Audit Trail

All trace data is persisted in the AI Fusion data model. The same tables that power the Trace pane and Observation module are queryable as Fabric tables for compliance reporting, external BI tools, or custom dashboards.

| Table | What It Captures |
|---|---|
| **`CHAT`** | One row per chat session - session ID, create time, synopsis. |
| **`STEP`** | One row per conversation turn - user input, assistant output, duration, time-to-first-token. |
| **`EXECUTIONS`** | One row per agent/actor execution within a turn - status (DONE/ERROR/IN_PROGRESS), name, duration, parent tool-call linkage. |
| **`MESSAGES`** | One row per LLM message - role (system/user/assistant/tool), content, associated tool calls, timestamp. |
| **`TOOL_CALLS`** | One row per tool invocation - function name, arguments, response, duration. |
| **`USAGE`** | One row per LLM call - input tokens, output tokens, cache-read tokens, cache-write tokens, model, interface, duration. |
| **`FEEDBACK`** | One row per user feedback action - reaction, note, submitter, timestamps. |
| **`SUMMARY`** | Per-session aggregate analytics. |

Because these are Fabric tables, the same role-based access controls that govern agent data access also govern who can read the audit history.

### LLM-Interface-Level Observation

In addition to the agent-level trace, AI Fusion records every LLM call independently at the LLM Interface layer. This **invoke observation** stream captures, per call:

- The flow, actor, and interface that originated the call.
- Timestamp, status, and duration.
- Total, input, output, and cache token counts.

This stream is the basis for the cross-cutting cost and latency reporting in the Observation dashboard. It is also the foundation for capacity monitoring - surfacing, for example, if a particular agent or model is generating outsized cost or unusually high latency.

### Evaluation as a Quality Control Loop

The same persisted trace data flows into the **Evaluation** workspace, where:

- Real conversations exported from Observation become test cases with declared ground-truth expectations.
- Each agent change is re-run against the suite, producing performance and quality regression reports.
- Agent ratings, latency, and accuracy metrics are tracked per evaluation run.

This closes the loop between production monitoring and pre-production quality control: a real customer turn that received negative feedback can be promoted into a reproducible test in minutes, and any future regression that reintroduces the same failure is caught before redeployment. See [Feedback Integration](14_feedback_integration.md) for the full workflow.



## Summary

| Concern | Mechanism |
|---|---|
| **Cross-entity data leakage** | Entity-level binding of every chat session to a single LUI; no addressable path to other entities. |
| **Sensitive field exposure** | Fabric role-based permissions; schema filtering before LLM exposure. |
| **Runaway agents** | Hard tool-call limit per agent invocation, parallel-call cap, configurable token limits. |
| **Out-of-scope tool use** | Per-agent tool allowlist; tools not in the list are not exposed to the LLM. |
| **Lack of transparency into agent behavior** | Full Trace pane during execution; Observation module for historical review. |
| **Long-term auditability** | All conversations, messages, tool calls, token usage, and feedback persisted in Fabric tables, queryable under the same RBAC model. |
| **Continuous quality control** | Conversations exportable from Observation into the Evaluation workspace as reproducible test cases. |

Together, these mechanisms ensure that agents created with AI Fusion operate within the same governance, security, and observability envelope as the rest of the K2view Data Product platform - not as a separate, less-governed AI surface bolted on top of it.
