# Introduction to Observation

**Observation** gives you production-level visibility into how your agents behave. [Evaluation](/articles/AI_fusion/04_evaluation/00_intro_to_evaluation.md) checks quality before you deploy. Observation shows what happens after the agents go live: it records every conversation, has an LLM classify it, and shows the results on an analytics dashboard that you can view for all agents or for one agent.

<!-- TODO screenshot: Observation dashboard, All Agents scope, with header summary, number cards, charts and the Conversation Details table -->


## Why Observation

Design-time tests cover the scenarios you thought of. Production brings the ones you did not: real users ask unexpected questions, data changes, and edge cases appear that no test suite anticipated. Without structured monitoring you cannot reliably detect quality regressions, spot systemic issues or measure improvement over time.

Reading conversations by hand does not scale. An agent that handles thousands of sessions a day cannot be monitored by sampling alone. Observation uses an LLM-as-a-judge approach: an analysis process reads each conversation, writes a summary and assigns structured tags (signals), so you can filter, chart and prioritize sessions without reading every one.


## What Observation Collects

Observation captures each production session from three perspectives.

### Operational

Every step of the agent's execution is recorded in the session's `aifusion` Data Product (LU) instance:

- **LLM calls** — the interface used, the messages sent, the responses and the latency of each call
- **Agent executions** — the agent and sub-agent executions in each turn, with their status and duration
- **Tool calls** — the tools invoked, their arguments, responses and duration
- **Turns** — each user question and assistant answer, with the turn duration and time to first token

You use this data for root-cause analysis when a conversation goes wrong, and to learn from conversations that went well.

### Cost

Token usage is recorded for each LLM call — input, output, cache-read and cache-write tokens — together with the LLM interface that produced it. The analysis process sums it per session, so you can compare token consumption across agents and over time.

### Behavior

The full conversation is stored and enriched with quality data:

- The complete message history (user and assistant turns)
- User feedback — thumbs up, thumbs down and free-text notes (see [Collecting Feedback](/articles/AI_fusion/05_observation/06_feedback.md))
- Session metadata — session ID, agent name and its Data Product, the LU instances the session ran against, who submitted it, and timestamps


## Signals: Automatic Conversation Tagging

When the analysis process runs, an LLM reads each conversation and picks a value for each **signal** from a fixed list of allowed values. The answers are stored as tags and drive the dashboard widgets, the conversation table and its filters.

Every agent is analyzed against three **core signals**:

<table>
<tbody>
<tr>
<td><strong>Signal</strong></td>
<td><strong>Values</strong></td>
<td><strong>What it captures</strong></td>
</tr>
<tr>
<td><strong>Resolution Status</strong></td>
<td>Resolved, Pending, Escalated</td>
<td>Whether the user's main request was answered, is still open, or was handed off or left unaddressed</td>
</tr>
<tr>
<td><strong>Sentiment</strong></td>
<td>Frustration, Satisfaction, Neutral, Urgency, Happiness, Anger, Concern, Appreciation, Discontent, Disappointment</td>
<td>The user's overall sentiment in the conversation</td>
</tr>
<tr>
<td><strong>Topic</strong></td>
<td>Generic values (Information Request, Data Lookup, Troubleshooting, Account, Feedback, Other), which each agent replaces with its own themes</td>
<td>The subjects the user asked about; several values can apply</td>
</tr>
</tbody>
</table>

On top of the core signals, each agent can define its own signals in its `Observation/signals.yaml` file — for example a customer-service agent can track **Follow-up**, **Customer Lifetime Value Risk** or **Resolution Pathway**. The analysis also counts the positive feedback, negative feedback and notes a session received and stores those counts as tags.

See [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md) for how signals are defined.


## Agent-Aware Observation

In AI Fusion 3, Observation is organized around agents:

- Every session records the **agent** that handled it and the agent's **Data Product (LU)**, so the same agent name in two LUs is kept apart.
- The dashboard has an agent selector. **All Agents** shows the overall widgets and cross-agent comparisons; selecting one agent shows only that agent's own widgets and conversations.
- Each agent carries its Observation configuration next to its `agent.yaml`, in `<LU>/AI/Agents/<agent>/Observation/`: `signals.yaml` (what the analyzer tags) and `widgets.yaml` (what the dashboard shows for that agent).

Session recording is controlled by the agent's `observability` setting in `agent.yaml` (default `true`). See [agent.yaml Reference](/articles/AI_fusion/02_agents/02_agent_yaml_reference.md).


## How Analysis Runs

Analysis is **not** triggered automatically at the end of a conversation, and AI Fusion does not schedule it for you. You run it on demand or schedule it yourself:

- **`RunAnalyze`** — a Broadway flow that analyzes the open sessions of the last N days, one at a time. Run it with `broadway aifusion.RunAnalyze day=7`, or call the `run-analytics` web service (`GET /api/run-analytics?day=7`).
- **`ObservationAnalyzer`** and **`BatchObservationAnalyzer`** — pipelines that analyze sessions in bulk. The batch pipeline submits the work through the LLM provider's batch API, which is cheaper for large volumes.

Until a session is analyzed it appears on the dashboard without a summary, topics or resolution status. When a user continues an analyzed conversation, the session is re-opened and is analyzed again on the next run.

See [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md#running-the-analysis) for the details, and for the cleanup process that removes old sessions.


## Where the Data Lives

<table>
<tbody>
<tr>
<td><strong>Store</strong></td>
<td><strong>Contents</strong></td>
<td><strong>Accessed by</strong></td>
</tr>
<tr>
<td><strong>Insights database</strong> (the <code>assurance</code> interface by default; SQLite or PostgreSQL)</td>
<td>One row per session (<code>ACTVSESSION</code>), the signal tags (<code>INSIGHTS</code>), the LU instances of each session (<code>INSTANCES</code>), LLM usage totals, evaluation results and batch jobs</td>
<td>Dashboard widgets, the conversation table, your own SQL and BI tools</td>
</tr>
<tr>
<td><strong><code>aifusion</code> LU</strong> — one instance per session</td>
<td>Turns, messages, executions, tool calls, token usage per call, feedback and data snapshots</td>
<td>Conversation review, the trace panel, the analysis process</td>
</tr>
</tbody>
</table>

See [Insights Database Schema Reference](/articles/AI_fusion/05_observation/04_insights_db_schema.md) for the tables and columns.


## Opening Observation

Observation is the **Observation** tab of the **AI Data Fusion** web application, registered in `apps.json` of the Web Services LU (`k2_ws/web/apps.json`) and enabled by default. Open it at:

```text
https://<server>[/<tenant>]/static/aifusion/observation
```

The tenant segment is present only on tenant-based deployments. Add `?agent=<lu>/<agent>` to open the dashboard scoped to one agent, for example `.../static/aifusion/observation?agent=customer_bank/banking`.

**Next article:** [Observation Dashboard](/articles/AI_fusion/05_observation/01_observation_dashboard.md)
