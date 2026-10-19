# Configuring Observation

Observation works out of the box: every agent is recorded and analyzed against the core signals, and the **All Agents** dashboard shows the overall widgets. This article explains how to adapt it to your use case — what the analysis tags (signals), what the dashboard shows (widgets), and how to run and schedule the analysis and the cleanup of old sessions.


## Prerequisites

- **Session recording.** An agent's conversations are recorded when its `observability` setting in `agent.yaml` is `true`, which is the default. With `observability: false` the conversation's messages, executions, token usage and tool calls are not persisted, so there is nothing to analyze or review. See [agent.yaml Reference](/articles/AI_fusion/02_agents/02_agent_yaml_reference.md).
- **Insights database.** Observation data is stored in the insights database set by the `INSIGHTS_INTERFACE`, `INSIGHTS_INTERFACE_TYPE` and `INSIGHTS_SCHEMA` globals (by default the SQLite `assurance` interface). Use PostgreSQL for QA and production environments. The tables are created by the deploy flow of the `aifusion` LU. See [Installation and Setup](/articles/AI_fusion/04_installation_and_setup.md) and [Insights Database Schema Reference](/articles/AI_fusion/05_observation/04_insights_db_schema.md).
- **LLM interface.** The analysis uses the environment's default LLM interface. See [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md).
- **Fabric version.** Per-agent configuration (`Observation/signals.yaml` and `Observation/widgets.yaml`) requires Fabric 8.5.2_84 or later.
- **Observation tab.** The **Observation** tab is enabled in `apps.json` by default. See [Opening Observation](/articles/AI_fusion/05_observation/00_intro_to_observation.md#opening-observation).


## Where Observation Configuration Lives

<table>
<tbody>
<tr>
<td><strong>File</strong></td>
<td><strong>Scope</strong></td>
<td><strong>Defines</strong></td>
</tr>
<tr>
<td><code>aifusion</code> LU, MTable <code>observation_signals</code></td>
<td>All agents</td>
<td>The core signals: Resolution Status, Sentiment, Topic</td>
</tr>
<tr>
<td><code>aifusion</code> LU, MTable <code>feedback_signals</code></td>
<td>All agents</td>
<td>The feedback counters stored as tags</td>
</tr>
<tr>
<td><code>aifusion</code> LU, MTable <code>widgets</code></td>
<td>All Agents dashboard</td>
<td>The overall widgets</td>
</tr>
<tr>
<td><code>&lt;LU&gt;/AI/Agents/&lt;agent&gt;/Observation/signals.yaml</code></td>
<td>One agent</td>
<td>The agent's own signals, and its Topic values</td>
</tr>
<tr>
<td><code>&lt;LU&gt;/AI/Agents/&lt;agent&gt;/Observation/widgets.yaml</code></td>
<td>One agent</td>
<td>The agent's entire dashboard when the agent is selected</td>
</tr>
</tbody>
</table>

Both per-agent files are optional. Without `signals.yaml` the agent is analyzed against the core signals only; without `widgets.yaml` the agent's dashboard shows only the conversation table.

A new agent created in AI Fusion Studio comes with both files. They are a starting point: the starter `signals.yaml` has generic placeholder Topic values (marked `TODO`) that you should replace with the agent's own themes, and the starter `widgets.yaml` has a baseline dashboard (header summary, four number cards, **Top Conversation Topics**, **Token Consumption** and an **Escalation Rate** card) that you extend with widgets on the agent's own signals.

> **Deploy to apply changes.** The runtime reads an agent's `Observation/` files from the copy Fabric captures when the LU is deployed — in Studio as well. After you edit `signals.yaml` or `widgets.yaml`, deploy the agent's LU (a soft deploy is enough). The [widget dry run](#testing-widgets-with-a-dry-run) is the only exception: it reads the project folder directly.


## Signals

A signal is a question the analysis LLM answers about each conversation by picking from a fixed list of values. Each answer is stored in the `INSIGHTS` table as one row per selected value: `tag_name` is the signal name and `tag_value` the value.

### Core signals

The core signals are defined in the `observation_signals` MTable of the `aifusion` LU and apply to every agent, with or without a `signals.yaml`. The overall widgets and the conversation table depend on them.

<table>
<tbody>
<tr>
<td><strong>name</strong></td>
<td><strong>value</strong></td>
<td><strong>Allow_Multi_Selection</strong></td>
<td><strong>agent_values</strong></td>
</tr>
<tr>
<td>Resolution Status</td>
<td>Resolved/Pending/Escalated</td>
<td>False</td>
<td>False</td>
</tr>
<tr>
<td>Sentiment</td>
<td>Frustration/Satisfaction/Neutral/Urgency/Happiness/Anger/Concern/Appreciation/Discontent/Disappointment</td>
<td>False</td>
<td>False</td>
</tr>
<tr>
<td>Topic</td>
<td>Information Request/Data Lookup/Troubleshooting/Account/Feedback/Other</td>
<td>True</td>
<td>True</td>
</tr>
</tbody>
</table>

The `value` column lists the allowed values separated by `/`. The `classification` column (not shown) holds guidance for the LLM — for example, the definitions of Resolved, Pending and Escalated. `agent_values` marks the core signals whose values an agent may replace; only **Topic** is marked.

### Agent signals: `signals.yaml`

An agent's `Observation/signals.yaml` adds signals for that agent's use case and sets its Topic values:

```yaml
signals:
  - name: Topic                      # core signal: this agent's themes replace the generic values
    values: [Loans, Cards, Payments, Other]
    multi_select: true

  - name: Urgency Level              # stored as INSIGHTS.tag_name
    values: [Low, Medium, High]      # the allowed INSIGHTS.tag_value values
    multi_select: false              # true = the LLM may pick several values
    classification: |                # optional guidance for the LLM
      High: the customer reports fraud or a blocked account.
```

<table>
<tbody>
<tr>
<td><strong>Key</strong></td>
<td><strong>Required</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>name</code></td>
<td>Yes</td>
<td>The signal name. Widgets match it exactly.</td>
</tr>
<tr>
<td><code>values</code></td>
<td>Yes</td>
<td>The allowed values. Widgets match them exactly.</td>
</tr>
<tr>
<td><code>multi_select</code></td>
<td>Yes</td>
<td><code>true</code> lets the LLM pick several values; <code>false</code> exactly one.</td>
</tr>
<tr>
<td><code>classification</code></td>
<td>No</td>
<td>Extra guidance, used when a value needs a definition the LLM would otherwise guess.</td>
</tr>
</tbody>
</table>

Rules:

- **Do not redefine Resolution Status or Sentiment.** An agent's definition of either is ignored with a warning, so the values the overall widgets match on stay consistent across agents.
- **Do define Topic.** Its values replace the generic ones for this agent; the name and `multi_select` stay those of the core signal, and a `classification` is optional. Topic values are what the agent's **Top Conversation Topics** chart shows.
- **Values must not contain `/`** — it is the internal separator.
- **Keep `name`, `values`, `multi_select` and `classification` at the same indentation** within each list entry. Prefer the inline form `values: [A, B, C]`; for a long block list, keep `multi_select` and `classification` at the `values:` level. A file that does not parse breaks the analysis of that agent.
- **Watch the cost.** Signals are classified three per LLM call, so every additional three signals add one LLM call per analyzed conversation. Three to five agent signals besides Topic is a sensible default; keep only signals that drive a decision or a widget.
- **Renaming breaks widgets.** If you rename a signal or a value, update every widget that filters on it.

For customer-facing agents, consider the customer-service signals that the banking sample agent (`customer_bank/AI/Agents/banking/Observation/signals.yaml`) uses together with matching widgets: **Follow-up** (`Required`, `Immediate`), **Customer Lifetime Value Risk** (`Low`, `Medium`, `High`) and **Resolution Pathway** (conversation stages such as `issue_understood`, `solution_offered`, `problem_solved`). **Customer Lifetime Value Risk** also feeds the conversation table's **Risk** column and **High Risk Conversations** filter.

### Feedback counters

During analysis, the user feedback a session received is counted and stored as tags:

- The `feedback_signals` MTable maps a feedback reaction to a tag name: reaction `1` is counted as **Positive Feedback** and reaction `-1` as **Negative Feedback**.
- Notes are counted as **Feedback Notes**.

These counts drive the **Feedback** column and filter of the conversation table.

> **Migrating from 2.x:** the `chat_signals` MTable is no longer used. Move custom tags into the relevant agents' `Observation/signals.yaml`.


## Overall Widgets: the `widgets` MTable

The **All Agents** dashboard shows the rows of the `widgets` MTable of the `aifusion` LU whose `display` is `true`. Its columns are:

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>id</code></td>
<td>Unique widget identifier</td>
</tr>
<tr>
<td><code>title</code></td>
<td>The title shown on the card</td>
</tr>
<tr>
<td><code>type</code></td>
<td><code>summary</code>, <code>box</code>, <code>bar</code>, <code>stackedbar</code>, <code>pie</code>, <code>line</code> or <code>area</code></td>
</tr>
<tr>
<td><code>xKey</code></td>
<td>The column used for the category or time axis</td>
</tr>
<tr>
<td><code>series</code></td>
<td>JSON array of the plotted series: <code>name</code>, <code>dataKey</code> (a returned column) and optional <code>color</code></td>
</tr>
<tr>
<td><code>action</code> / <code>pg_action</code></td>
<td>The SQL query for SQLite / PostgreSQL</td>
</tr>
<tr>
<td><code>action_aggregate</code> / <code>pg_action_aggregate</code></td>
<td>Optional per-day variants of the query, used for time ranges longer than 24 hours</td>
</tr>
<tr>
<td><code>display</code></td>
<td><code>true</code> to show the widget</td>
</tr>
</tbody>
</table>

The overall queries use the `${schema}` and `${days}` placeholders. The shipped widgets are listed in [Observation Dashboard](/articles/AI_fusion/05_observation/01_observation_dashboard.md#all-agents-widgets).


## Agent Dashboard Widgets (widgets.yaml)

When an agent is selected on the dashboard, its `Observation/widgets.yaml` is the agent's **entire** dashboard — header summary, number cards and charts. The overall widgets are not added, so the file must include everything worth seeing for that agent: its session averages, **Top Conversation Topics** over its own Topic values, token consumption, and views on its own signals. Seven to eleven widgets is a good size.

### Widget keys

```yaml
widgets:
  - id: bankingUrgency               # unique within this file
    title: Urgency Level
    type: pie                        # summary | box | bar | stackedbar | pie | line | area
    x_key: TAG_VALUE
    series:
      - { name: "", dataKey: TAG_COUNT }
    display: true
    sql:
      sqlite: |
        SELECT i.TAG_VALUE AS TAG_VALUE, COUNT(*) AS TAG_COUNT
        FROM ${schema}.INSIGHTS i
        JOIN ${sessions} a ON a.SESSION_ID = i.SESSION_ID
        WHERE TRIM(i.TAG_NAME) = 'Urgency Level'
          AND a.LAST_MODIFIED_DATE >= DATE('now', '-${days} days')
        GROUP BY i.TAG_VALUE;
      postgres: |
        SELECT i.tag_value AS "TAG_VALUE", COUNT(*) AS "TAG_COUNT"
        FROM ${schema}.insights i
        JOIN ${sessions} a ON a.session_id = i.session_id
        WHERE TRIM(i.tag_name) = 'Urgency Level'
          AND a.last_modified_date >= (now() - INTERVAL '${days} days')::date
        GROUP BY i.tag_value;
```

<table>
<tbody>
<tr>
<td><strong>Key</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>id</code></td>
<td>Unique within the file. Reusing an overall widget id is allowed.</td>
</tr>
<tr>
<td><code>title</code></td>
<td>The card title.</td>
</tr>
<tr>
<td><code>type</code></td>
<td>The widget type (see the next table).</td>
</tr>
<tr>
<td><code>x_key</code></td>
<td>The column used for categories, pie labels or the time axis.</td>
</tr>
<tr>
<td><code>series</code></td>
<td>The plotted values: <code>name</code>, <code>dataKey</code> (a returned column) and optional <code>color</code>.</td>
</tr>
<tr>
<td><code>display</code></td>
<td>Optional; <code>false</code> hides the widget. Default <code>true</code>.</td>
</tr>
<tr>
<td><code>sql.sqlite</code>, <code>sql.postgres</code></td>
<td>The query for each insights backend. The backend set by <code>INSIGHTS_INTERFACE_TYPE</code> decides which one runs, so write both.</td>
</tr>
<tr>
<td><code>sql.sqlite_aggregate</code>, <code>sql.postgres_aggregate</code></td>
<td>Optional per-day variants for time charts, used for ranges longer than 24 hours.</td>
</tr>
</tbody>
</table>

### Widget types and result shapes

<table>
<tbody>
<tr>
<td><strong>type</strong></td>
<td><strong>Rows the query must return</strong></td>
<td><strong>x_key / series</strong></td>
</tr>
<tr>
<td><code>summary</code></td>
<td>One row per header item, with the columns <code>TAG_VALUE</code> (label) and <code>TAG_COUNT</code> (value, shown as returned). At most one per file.</td>
<td>Not used for display</td>
</tr>
<tr>
<td><code>box</code></td>
<td>Exactly one row</td>
<td><code>series[0].dataKey</code> is the value column — a number or preformatted text such as <code>'42%'</code></td>
</tr>
<tr>
<td><code>bar</code>, <code>stackedbar</code></td>
<td>One row per category</td>
<td><code>x_key</code> is the category column; one <code>series</code> entry per value column</td>
</tr>
<tr>
<td><code>pie</code></td>
<td>One row per slice</td>
<td><code>x_key</code> is the label column, <code>series[0].dataKey</code> the value column; an optional <code>color</code> column per row</td>
</tr>
<tr>
<td><code>line</code>, <code>area</code></td>
<td>One row per time bucket, ordered</td>
<td><code>x_key: date</code>; add <code>*_aggregate</code> queries with per-day buckets</td>
</tr>
</tbody>
</table>

For a summary widget, keep the **Total** and **Not Resolved** rows of the overall `conversationTagSummary` widget and add the agent's own headline items as more `UNION ALL` rows — the banking agent adds **Follow-ups** and **At Risk**.

### What a widget can query

Widgets query the insights database only — never the LU's own data. The available tables are:

<table>
<tbody>
<tr>
<td><strong>Table</strong></td>
<td><strong>Columns</strong></td>
</tr>
<tr>
<td><code>ACTVSESSION</code> (read through <code>${sessions}</code>)</td>
<td><code>session_id</code>, <code>creation_date</code>, <code>last_modified_date</code>, <code>agent_name</code>, <code>data_product</code>, <code>chat_name</code>, <code>chat_summary</code>, <code>input_tokens</code>, <code>output_tokens</code>, <code>cache_read_tokens</code>, <code>total_tokens</code>, <code>conversation_pairs</code>, <code>avg_response_time</code>, <code>feedback</code>, <code>status</code>, <code>submitted_by</code></td>
</tr>
<tr>
<td><code>INSIGHTS</code></td>
<td><code>session_id</code>, <code>tag_name</code>, <code>tag_value</code></td>
</tr>
<tr>
<td><code>INSTANCES</code></td>
<td><code>session_id</code>, <code>lu_name</code>, <code>iid</code></td>
</tr>
</tbody>
</table>

### Placeholders

<table>
<tbody>
<tr>
<td><strong>Placeholder</strong></td>
<td><strong>Replaced with</strong></td>
</tr>
<tr>
<td><code>${sessions}</code></td>
<td>A derived table of this agent's <code>ACTVSESSION</code> rows (its name and Data Product). Always give it an alias: <code>FROM ${sessions} a</code>.</td>
</tr>
<tr>
<td><code>${days}</code></td>
<td>The selected time range in days (1, 7, 30 or 90)</td>
</tr>
<tr>
<td><code>${schema}</code></td>
<td>The insights schema (<code>INSIGHTS_SCHEMA</code>)</td>
</tr>
<tr>
<td><code>${agent}</code></td>
<td>The agent name</td>
</tr>
<tr>
<td><code>${lu}</code></td>
<td>The agent's LU</td>
</tr>
</tbody>
</table>

No other placeholders are substituted.

### Authoring rules

- **Read sessions through `${sessions}`.** It limits the query to the agent's sessions, so you need no agent filter of your own. Reach `INSIGHTS` and `INSTANCES` through a join to it. Do not write `${schema}.ACTVSESSION` in an agent widget: such references are rewritten as a fallback, but the rewrite can fail on unusual SQL. A query that does not read `${sessions}` is not scoped — its card is marked **Not filtered to this agent** and shows every agent's data.
- **Prefix every other table with `${schema}.`** in both dialects (`${schema}.INSIGHTS`, `${schema}.INSTANCES`).
- **Window on `LAST_MODIFIED_DATE` only.** SQLite: `a.LAST_MODIFIED_DATE >= DATE('now', '-${days} days')`; PostgreSQL: `a.last_modified_date >= (now() - INTERVAL '${days} days')::date`. The shipped widgets and the analysis all use this column; mixing in `CREATION_DATE` makes the header and the charts count different sets of sessions.
- **Guard every division with `NULLIF(<divisor>, 0)`.** On a period with no analyzed sessions, SQLite returns NULL but PostgreSQL fails the whole query with a division-by-zero error.
- **Quote the PostgreSQL aliases the widget reads** (`AS "TAG_VALUE"`). PostgreSQL folds unquoted names to lowercase, and the column would no longer match `x_key` or `dataKey`.
- **Match signal names and values exactly** as defined in `signals.yaml` or the core signals.
- **Read token usage from `${sessions}`**, not from `INVOKE_OBSERVATION`, which has no agent column and cannot be scoped.

The banking sample agent's `customer_bank/AI/Agents/banking/Observation/widgets.yaml` is a complete worked example, with both dialects and aggregate variants.

### Testing widgets with a dry run

Before you deploy, run every widget query against the real insights database with the `ObservationWidgetDryRun` flow of the `aifusion` LU. It reads `widgets.yaml` from the project folder, so it works before deployment, and it runs in Studio only. Run it as a Fabric command:

```bash
broadway aifusion.ObservationWidgetDryRun lu='customer_bank' agent='banking'
```

<table>
<tbody>
<tr>
<td><strong>Parameter</strong></td>
<td><strong>Required</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>lu</code></td>
<td>Yes</td>
<td>The agent's LU</td>
</tr>
<tr>
<td><code>agent</code></td>
<td>Yes</td>
<td>The agent folder name</td>
</tr>
<tr>
<td><code>widgetid</code></td>
<td>No</td>
<td>Run one widget only; empty runs every widget in the file</td>
</tr>
<tr>
<td><code>day</code></td>
<td>No</td>
<td>Look-back window in days (default 30)</td>
</tr>
<tr>
<td><code>maxRows</code></td>
<td>No</td>
<td>Rows returned per query (default 50, maximum 500)</td>
</tr>
</tbody>
</table>

The result lists, per widget and per query variant, whether it succeeded, the error if any, the SQL exactly as the dashboard runs it (agent filter applied, placeholders filled), the returned columns and sample rows, and warnings — for example an `x_key` or `dataKey` that is not a returned column, or a query that returned no rows and could not be checked. Only the active backend's dialect runs; review the other dialect by hand. Fix and re-run until every widget passes, then deploy the LU.


## Running the Analysis

Analysis is what turns recorded sessions into summaries, tags and session metrics. It does not run automatically at the end of a conversation, and AI Fusion does not schedule it — you run it on demand or schedule it yourself.

### Session lifecycle

Each session has a row in `ACTVSESSION` whose `status` tracks it:

<table>
<tbody>
<tr>
<td><strong>Status</strong></td>
<td><strong>Meaning</strong></td>
</tr>
<tr>
<td><code>IN_PROGRESS</code></td>
<td>A turn of the conversation is running</td>
</tr>
<tr>
<td><code>OPEN</code></td>
<td>Waiting for analysis — set when a turn ends</td>
</tr>
<tr>
<td><code>ANALYZING</code></td>
<td>Picked up by an analysis run</td>
</tr>
<tr>
<td><code>CLOSED</code></td>
<td>Analyzed</td>
</tr>
<tr>
<td><code>ABORTED</code></td>
<td>The user stopped the turn</td>
</tr>
<tr>
<td><code>FAILED</code></td>
<td>A batch analysis failed for the session; excluded from the conversation table</td>
</tr>
</tbody>
</table>

When a user continues a conversation that was already analyzed, the session returns to `OPEN` and is analyzed again on the next run, so its summary and tags reflect the whole conversation.

### What the analysis does

For each session, the `LLMChatAnalyze` flow:

1. Reads the conversation from the session's `aifusion` LU instance and asks the LLM for a summary.
2. Classifies the conversation against the core signals and the agent's signals, three signals per LLM call.
3. Counts the session's feedback.
4. Replaces the session's rows in `INSIGHTS` with the new tags and counters.
5. Updates the session's `ACTVSESSION` row — summary, token totals, number of turns (`conversation_pairs`), average response time in seconds — and sets its status to `CLOSED`.

### On demand: RunAnalyze

The `RunAnalyze` flow selects the `OPEN` sessions whose last activity is within the last `day` days, marks them `ANALYZING`, and analyzes them one at a time. It returns a summary of the run:

```json
{ "total": 12, "succeeded": 11, "failed": 1 }
```

A session whose analysis fails is returned to `OPEN`, so the next run retries it.

Run it in either of these ways:

```bash
broadway aifusion.RunAnalyze day=7
```

```text
GET /api/run-analytics?day=7
```

### Pipelines

Two pipelines in the `aifusion` LU analyze sessions in bulk. They are registered in the pipeline framework and run like the other AI Fusion pipelines:

<table>
<tbody>
<tr>
<td><strong>Pipeline</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>ObservationAnalyzer</code></td>
<td>Runs <code>LLMChatAnalyze</code> as a Fabric batch over all <code>OPEN</code> sessions.</td>
</tr>
<tr>
<td><code>BatchObservationAnalyzer</code></td>
<td>Submits the analysis requests through the LLM provider's batch API, waits for the batch to complete, and stores the results. Provider batch APIs are typically much cheaper than real-time calls, at the cost of latency, so this suits high-volume production environments. It uses the default LLM interface unless you set its <code>batchInterface</code>. If the batch fails, its sessions are marked <code>FAILED</code>.</td>
</tr>
</tbody>
</table>

Batch analysis requires an LLM connector that supports batch inference: Anthropic and OpenAI (no extra storage), Amazon Bedrock (input and output through an S3 bucket) and Google (through a GCS bucket). Ollama does not support it. For Bedrock and Google, the bucket is part of the LLM interface configuration; see [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md).

### Scheduling

To keep the dashboard current, schedule the analysis — for example a recurring Fabric Broadway job that runs `aifusion.RunAnalyze` nightly with `day=1` or `day=2`, or a scheduled run of one of the pipelines. Choose the frequency by volume: once a day is enough for low-traffic agents; high-traffic agents benefit from more frequent runs or the batch pipeline.


## Session Retention and Cleanup

The cleanup process deletes analyzed (`CLOSED`) sessions whose last activity is older than the retention period: their `ACTVSESSION`, `INSIGHTS` and `INSTANCES` rows, their data snapshots, and their `aifusion` LU instance.

- **Retention period.** Set by the `RETENTION_DAYS` global (default 30, allowed range 7–365 days).
- **Run it on demand** with the `cleanup/start` web service (`POST /api/cleanup/start`, optional `retention_days` parameter) or the `cleanupExecutor` pipeline.
- **Schedule it** by running the `aifusion.cleanupJob` flow once: it registers a Broadway job (uid `aifusion_cleanup_job`) that runs the cleanup every day at 02:00.

**Next article:** [Insights Database Schema Reference](/articles/AI_fusion/05_observation/04_insights_db_schema.md)
