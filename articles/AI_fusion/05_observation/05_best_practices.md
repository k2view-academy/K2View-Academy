# Observation Best Practices

This article collects practical advice for getting value from Observation — from setting it up for a new agent, through daily monitoring, to feeding production findings back into evaluation.


## Make the Analysis Run

The dashboard only shows tags, summaries and session metrics for analyzed sessions, and AI Fusion does not schedule the analysis for you.

- **Schedule it from day one.** Set up a recurring run of `aifusion.RunAnalyze` (or one of the analyzer pipelines) as part of deploying an environment. A dashboard full of **Unknown** resolutions usually means the analysis is not running. See [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md#running-the-analysis).
- **Match the frequency to volume.** Once a day is enough for low-traffic agents. For high-traffic agents run more often, or use `BatchObservationAnalyzer` with a provider that supports batch inference.
- **Check the run result.** `RunAnalyze` returns `total`, `succeeded` and `failed`. Failed sessions return to `OPEN` and are retried next time; a persistent failure count points at a problem such as an unreachable LLM interface or an invalid `signals.yaml`.
- **Schedule the cleanup too.** Run `aifusion.cleanupJob` once to register the daily cleanup, and set `RETENTION_DAYS` to your data-retention policy.


## Configure Each Agent's Observation

Every agent ships with a starter `Observation/` folder. Treat it as part of building the agent, not an afterthought:

- **Replace the placeholder Topic values** with the agent's real themes — one value per domain or question theme, typically 5–12 values plus `Other`. Topics are the only per-agent view of what users ask about.
- **Derive signals from what the business cares about:** outcomes (resolved, escalated, sale, retention risk), actions the agent performs (for example `Action Taken: [Card Blocked, Payment Scheduled, None]`), and compliance constraints (for example `Policy Risk: [None, Low, High]`).
- **Keep the signal set small.** Three to five signals besides Topic is a good default. Every three signals add an LLM call per analyzed conversation.
- **Make values short and mutually exclusive** (unless the signal is `multi_select`), and add a `classification` only where the LLM would otherwise guess the meaning of a value.
- **Avoid signals that cannot be inferred from the conversation text.** Low-confidence tags make filters and charts misleading.
- **Give every signal a widget.** A signal that no widget or filter reads costs money without being used.
- **Dry-run the widgets** with `aifusion.ObservationWidgetDryRun` before deploying, and deploy the LU after every change to the `Observation/` files.

For customer-facing agents, start from the banking sample agent's `signals.yaml` and `widgets.yaml`: its **Customer Lifetime Value Risk** signal is what drives the conversation table's **Risk** column and **High Risk Conversations** filter.


## Establish a Monitoring Routine

Observation is most useful when you review it consistently rather than reactively:

<table>
<tbody>
<tr>
<td><strong>Cadence</strong></td>
<td><strong>Focus</strong></td>
<td><strong>Time range</strong></td>
</tr>
<tr>
<td><strong>Daily</strong></td>
<td>New high-risk, unresolved and negatively rated conversations</td>
<td>Last 24 Hours</td>
</tr>
<tr>
<td><strong>Weekly</strong></td>
<td>Quality trends, sentiment shifts, topic patterns per agent</td>
<td>Last 7 Days</td>
</tr>
<tr>
<td><strong>Monthly</strong></td>
<td>Long-term resolution rate, token consumption and cost per agent</td>
<td>Last 30 Days</td>
</tr>
</tbody>
</table>

Use **All Agents** to compare agents — **Conversations by Agent**, **Resolution Rate by Agent (%)** and **Token Consumption by Agent** show quickly which agent needs attention — then select that agent to look at its own widgets and conversations.


## Prioritize the Right Conversations

You do not need to read every conversation. Use the table filters to focus on the sessions most likely to surface actionable issues:

- **Unresolved Conversations** — the agent did not meet the user's need; these often reveal gaps in knowledge, tools or data access.
- **High Risk Conversations** — for agents that define Customer Lifetime Value Risk; the highest-priority list for customer-facing agents.
- **Feedback → Has negative** — the users themselves told you the answer was wrong; read the notes with **Has notes**.
- **Instance (LU / IID)** — when you investigate a complaint about a specific customer, retrieves all the sessions that touched that instance.

Then use **Prev** / **Next** (or the arrow keys) in Conversation Review to work through the filtered list without returning to the dashboard.


## Act on Problem Conversations

When you find a problem conversation:

1. **Read the transcript and the trace.** Check the trace panel for failed tool calls, missing sub-agent calls, unexpected data or unusually high token usage.
2. **Identify the root cause** — a data gap, a reasoning failure, a tool error, unclear instructions, or an ambiguous user request.
3. **Export the conversation** with **Export with snap**, so it can be replayed on the same data.
4. **Turn it into a test case.** Import the ZIP in the Studio Preview and click **Save as test case**. See [Snapshots and Production Import](/articles/AI_fusion/04_evaluation/04_snapshots_and_production_import.md).
5. **Fix and re-test** — update the agent, run the test case, and confirm the issue is resolved before you deploy.

Do this for strong positive conversations too: a test suite built from real usage covers what users actually ask, not only what you assumed they would.


## Manage Observation Costs

LLM-as-a-judge analysis is the main cost of Observation:

- **Use batch analysis for high volume.** `BatchObservationAnalyzer` uses the provider's batch API, which is typically much cheaper per request than real-time calls.
- **Trim the signal set.** Remove signals that no widget, filter or decision uses.
- **Turn off recording where it adds nothing.** Set `observability: false` in `agent.yaml` for agents whose conversations you do not need to monitor; their conversations are then not persisted for review or analysis.
- **Analyze only what you need.** Run the analysis with a `day` window that matches how far back you actually look.


## Integrate with External BI Tools

When you need cross-environment reporting or custom visualizations, use the data outside the dashboard:

- **Export List** on the dashboard downloads the current conversation list as CSV.
- **Direct database access** — on PostgreSQL, connect a BI tool (for example Tableau, Power BI or Looker) to the insights database. Pivot `INSIGHTS` into one row per session and join `INSTANCES` for the customers involved. See [Insights Database Schema Reference](/articles/AI_fusion/05_observation/04_insights_db_schema.md) for the tables and example queries.
- **Web services** — the dashboard's own services (`retrieve-conversations`, `widget-details`, `chat-review`) are available for programmatic access to the same data.



**Next article:** [Collecting Feedback](/articles/AI_fusion/05_observation/06_feedback.md)
