# Insights Database Schema Reference

AI Fusion stores session-level Observation data in the **insights database** — a relational database that dashboards, BI tools, reporting systems and data pipelines can query directly. This article describes its tables and their relationships, and gives example queries for common reports.

The insights database holds one row per session plus aggregates. The detailed per-turn data (messages, executions, tool calls, token usage per call) is stored in the `aifusion` Data Product (LU), one instance per session; it is described at the end of this article.


## Connecting to the Insights Database

The database is set by three shared globals:

<table>
<tbody>
<tr>
<td><strong>Global</strong></td>
<td><strong>Default</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>INSIGHTS_INTERFACE</code></td>
<td><code>assurance</code></td>
<td>The DB interface. The default <code>assurance</code> interface is a SQLite file in the Fabric workspace.</td>
</tr>
<tr>
<td><code>INSIGHTS_INTERFACE_TYPE</code></td>
<td><code>sqlite</code></td>
<td>The dialect: <code>sqlite</code> or <code>postgres</code>. It also decides which SQL variant the dashboard widgets run.</td>
</tr>
<tr>
<td><code>INSIGHTS_SCHEMA</code></td>
<td><code>main</code></td>
<td>The schema holding the tables, for example <code>public</code> on PostgreSQL.</td>
</tr>
</tbody>
</table>

Use SQLite for development in Studio and PostgreSQL for QA and production, where concurrent writes and large volumes matter and where BI tools can connect directly. See [Installation and Setup](/articles/AI_fusion/04_installation_and_setup.md).

The tables are created by the deploy flow of the `aifusion` LU on every deploy (`CREATE TABLE IF NOT EXISTS`), so a new table reaches existing environments automatically. Column changes to `ACTVSESSION` on environments that predate them (see below) are applied by the same flow.


## Observation Tables

### ACTVSESSION

The central monitoring table: one row per conversation session.

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>session_id</code></td>
<td>TEXT (PK)</td>
<td>Unique session identifier; also the IID of the session's <code>aifusion</code> LU instance. Join key for <code>INSIGHTS</code> and <code>INSTANCES</code>.</td>
</tr>
<tr>
<td><code>creation_date</code></td>
<td>TIMESTAMP</td>
<td>When the session started.</td>
</tr>
<tr>
<td><code>last_modified_date</code></td>
<td>TIMESTAMP</td>
<td>The session's last activity. The dashboard widgets, the analysis and the cleanup select sessions by this column.</td>
</tr>
<tr>
<td><code>agent_name</code></td>
<td>TEXT</td>
<td>The agent that handled the session. Replaces the 2.x <code>app_id</code> column; on environments upgraded from 2.x, <code>app_id</code> is renamed so the history is kept.</td>
</tr>
<tr>
<td><code>data_product</code></td>
<td>TEXT</td>
<td>The agent's LU, so that agents with the same name in different LUs are kept apart. NULL on sessions recorded before this column was added.</td>
</tr>
<tr>
<td><code>chat_name</code></td>
<td>TEXT</td>
<td>The automatically generated chat title.</td>
</tr>
<tr>
<td><code>chat_summary</code></td>
<td>TEXT</td>
<td>The LLM-generated conversation summary. Written by the analysis.</td>
</tr>
<tr>
<td><code>suggestion</code></td>
<td>TEXT</td>
<td>Reserved; not populated.</td>
</tr>
<tr>
<td><code>input_tokens</code>, <code>output_tokens</code></td>
<td>INTEGER</td>
<td>Input and output tokens across all LLM calls of the session. <code>input_tokens</code> includes cache-read tokens. Written by the analysis.</td>
</tr>
<tr>
<td><code>cache_read_tokens</code>, <code>cache_write_tokens</code></td>
<td>INTEGER</td>
<td>Tokens read from and written to the provider's prompt cache. Written by the analysis.</td>
</tr>
<tr>
<td><code>total_tokens</code></td>
<td>INTEGER</td>
<td>Total tokens of the session. Written by the analysis.</td>
</tr>
<tr>
<td><code>conversation_pairs</code></td>
<td>INTEGER</td>
<td>Number of user–assistant turns. Written by the analysis.</td>
</tr>
<tr>
<td><code>avg_response_time</code></td>
<td>INTEGER</td>
<td>Average turn duration, in seconds. Written by the analysis.</td>
</tr>
<tr>
<td><code>feedback</code></td>
<td>TEXT</td>
<td>Feedback indicator written by the analysis. For reporting, use the feedback counters in <code>INSIGHTS</code>.</td>
</tr>
<tr>
<td><code>snap_mode</code></td>
<td>TEXT</td>
<td>The agent's data snapshot mode for the session (from its <code>snap_session_mode</code> setting).</td>
</tr>
<tr>
<td><code>submitted_by</code></td>
<td>TEXT</td>
<td>The user who ran the session.</td>
</tr>
<tr>
<td><code>status</code></td>
<td>TEXT</td>
<td><code>IN_PROGRESS</code>, <code>OPEN</code>, <code>ANALYZING</code>, <code>CLOSED</code>, <code>ABORTED</code> or <code>FAILED</code>. See <a href="/articles/AI_fusion/05_observation/03_configuring_observation.md#session-lifecycle">Session lifecycle</a>.</td>
</tr>
<tr>
<td><code>process_id</code>, <code>node_id</code></td>
<td>TEXT</td>
<td>Internal: the Fabric process and node running the current turn, used to stop a running turn.</td>
</tr>
</tbody>
</table>

> **Notes:**
>
> * The token, turn, response-time and summary columns are NULL until the session is analyzed. They reflect the conversation as of its last analysis.
> * The 2.x `customer_id` column is no longer created or used. A session's LU instances are in the `INSTANCES` table.

### INSTANCES

The LU instances a session ran against. A session can have zero, one or several rows.

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>session_id</code></td>
<td>TEXT (PK)</td>
<td>Foreign key to <code>ACTVSESSION.session_id</code>.</td>
</tr>
<tr>
<td><code>lu_name</code></td>
<td>TEXT (PK)</td>
<td>The LU of the instance.</td>
</tr>
<tr>
<td><code>iid</code></td>
<td>TEXT (PK)</td>
<td>The instance ID. An IID is unique only within its LU, so always filter on <code>lu_name</code> and <code>iid</code> together.</td>
</tr>
<tr>
<td><code>created_at</code></td>
<td>TIMESTAMP</td>
<td>When the row was recorded.</td>
</tr>
</tbody>
</table>

### INSIGHTS

One row per tag per session. Each analyzed session has several rows: one per single-value signal, one per selected value of a multi-select signal (such as Topic), and the feedback counters.

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>session_id</code></td>
<td>TEXT</td>
<td>Foreign key to <code>ACTVSESSION.session_id</code>.</td>
</tr>
<tr>
<td><code>tag_name</code></td>
<td>TEXT</td>
<td>The signal name, or a feedback counter name.</td>
</tr>
<tr>
<td><code>tag_value</code></td>
<td>TEXT</td>
<td>The selected value, or the count for a feedback counter.</td>
</tr>
</tbody>
</table>

Tags present for every analyzed session:

<table>
<tbody>
<tr>
<td><strong>tag_name</strong></td>
<td><strong>tag_value</strong></td>
</tr>
<tr>
<td><code>Resolution Status</code></td>
<td>Resolved, Pending, Escalated</td>
</tr>
<tr>
<td><code>Sentiment</code></td>
<td>Frustration, Satisfaction, Neutral, Urgency, Happiness, Anger, Concern, Appreciation, Discontent, Disappointment</td>
</tr>
<tr>
<td><code>Topic</code></td>
<td>The agent's Topic values (one row per selected topic), or the generic values when the agent does not define them</td>
</tr>
<tr>
<td><code>Positive Feedback</code>, <code>Negative Feedback</code>, <code>Feedback Notes</code></td>
<td>Counts of thumbs up, thumbs down and notes in the session</td>
</tr>
</tbody>
</table>

Additional `tag_name` values come from each agent's `Observation/signals.yaml` — for example `Urgency Level`, `Follow-up` or `Customer Lifetime Value Risk` for the banking sample agent. See [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md#signals).

> **Note:** `tag_name` / `tag_value` is a key-value structure. To produce one row per session for a BI tool, pivot it (see the example queries below).

### invoke_observation

Telemetry for every LLM and embedding call — from agents as well as from the LLM actors in Broadway flows. Use it for performance profiling and cost attribution at the flow level. It has no agent or session column, so it cannot be filtered by agent.

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>flow</code></td>
<td>TEXT</td>
<td>The flow (or agent component) that made the call.</td>
</tr>
<tr>
<td><code>actor</code></td>
<td>TEXT</td>
<td>The actor within the flow.</td>
</tr>
<tr>
<td><code>interface</code></td>
<td>TEXT</td>
<td>The LLM interface used.</td>
</tr>
<tr>
<td><code>timestamp</code></td>
<td>TIMESTAMP</td>
<td>When the call started.</td>
</tr>
<tr>
<td><code>duration</code></td>
<td>INTEGER</td>
<td>Duration in milliseconds.</td>
</tr>
<tr>
<td><code>total_tokens</code>, <code>input_tokens</code>, <code>output_tokens</code>, <code>cache_read_tokens</code>, <code>cache_write_tokens</code></td>
<td>INTEGER</td>
<td>Token usage of the call.</td>
</tr>
<tr>
<td><code>status</code></td>
<td>TEXT</td>
<td><code>IN_PROGRESS</code>, then the final status of the call.</td>
</tr>
</tbody>
</table>

### model_token_usage

Cumulative token consumption per LLM interface and model, updated after each call. Use it for cost reporting by model.

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>interface</code></td>
<td>TEXT (PK)</td>
<td>The LLM interface name.</td>
</tr>
<tr>
<td><code>model</code></td>
<td>TEXT (PK)</td>
<td>The model identifier.</td>
</tr>
<tr>
<td><code>total_tokens</code>, <code>input_tokens</code>, <code>output_tokens</code>, <code>cache_read_tokens</code>, <code>cache_write_tokens</code></td>
<td>INTEGER</td>
<td>Cumulative token counts.</td>
</tr>
<tr>
<td><code>last_update</code></td>
<td>TIMESTAMP</td>
<td>When the row was last updated.</td>
</tr>
</tbody>
</table>


## Other Tables in the Insights Database

The same database also holds tables used by other AI Fusion processes. You normally do not query them for Observation reporting.

<table>
<tbody>
<tr>
<td><strong>Table</strong></td>
<td><strong>Used by</strong></td>
<td><strong>Contents</strong></td>
</tr>
<tr>
<td><code>evaluation_chat</code></td>
<td>Evaluation</td>
<td>One row per evaluated conversation: <code>sessionid</code>, <code>initParams</code>, <code>createtime</code>, <code>result</code>, <code>threshold</code>, <code>overallrating</code></td>
</tr>
<tr>
<td><code>evaluation_details</code></td>
<td>Evaluation</td>
<td>Per-step evaluation results: <code>sessionid</code>, <code>step</code>, <code>question</code>, <code>expected</code>, <code>answer</code>, <code>rating</code>, <code>performance_stats</code></td>
</tr>
<tr>
<td><code>evaluation_job</code></td>
<td>Evaluation</td>
<td>Evaluation jobs: <code>type</code>, <code>name</code>, <code>uid</code>, <code>status</code>, <code>start_time</code>, <code>end_time</code>, <code>evaluation_result</code>, <code>sessionid</code>, <code>conversation_link</code></td>
</tr>
<tr>
<td><code>BATCH</code></td>
<td>Batch analysis</td>
<td>LLM batch jobs submitted by <code>BatchObservationAnalyzer</code>: <code>batch_id_aifusion</code>, <code>batch_id_llm</code>, <code>creation_time</code>, <code>completion_time</code>, <code>input_file_id</code>, <code>output_file_id</code>, <code>status</code>, <code>note</code>, <code>interface</code>, <code>response_handler</code></td>
</tr>
</tbody>
</table>


## Table Relationships

```text
ACTVSESSION (session_id)
    ├── INSIGHTS (session_id)            signals and feedback counters, one row per tag
    ├── INSTANCES (session_id)           LU instances the session ran against
    └── aifusion LU instance (IID = session_id)
                                         turns, messages, executions, tool calls, usage, feedback

invoke_observation                       per-call LLM telemetry (correlate by timestamp)
model_token_usage                        cumulative totals per interface + model
```


## Example Queries

The queries below are written for SQLite (the default). For PostgreSQL, replace date expressions such as `DATE('now', '-30 days')` with `(now() - INTERVAL '30 days')::date`, and `GROUP_CONCAT` with `string_agg`. Prefix table names with your schema when it is not the default one. The overall dashboard widgets in the `widgets` MTable of the `aifusion` LU are further examples.

### Daily resolution rate per agent (last 30 days)

```sql
SELECT
    DATE(a.last_modified_date)   AS day,
    a.agent_name,
    COUNT(DISTINCT a.session_id) AS analyzed_sessions,
    ROUND(
        100.0
        * COUNT(DISTINCT CASE WHEN i.tag_value = 'Resolved' THEN a.session_id END)
        / NULLIF(COUNT(DISTINCT a.session_id), 0)
    , 1)                         AS resolution_rate_pct
FROM ACTVSESSION a
JOIN INSIGHTS i
  ON i.session_id = a.session_id
 AND i.tag_name   = 'Resolution Status'
WHERE a.last_modified_date >= DATE('now', '-30 days')
GROUP BY DATE(a.last_modified_date), a.agent_name
ORDER BY day, a.agent_name;
```

### Sentiment breakdown by agent

```sql
SELECT
    a.agent_name,
    a.data_product,
    i.tag_value  AS sentiment,
    COUNT(*)     AS session_count
FROM ACTVSESSION a
JOIN INSIGHTS i
  ON i.session_id = a.session_id
 AND i.tag_name   = 'Sentiment'
WHERE a.status = 'CLOSED'
GROUP BY a.agent_name, a.data_product, i.tag_value
ORDER BY a.agent_name, session_count DESC;
```

### All conversations of one customer

Find every session that ran against a given LU instance, across all agents:

```sql
SELECT
    a.session_id,
    a.agent_name,
    a.creation_date,
    a.submitted_by,
    a.chat_summary
FROM ACTVSESSION a
JOIN INSTANCES n
  ON n.session_id = a.session_id
WHERE n.lu_name = 'customer_bank'
  AND n.iid     = '1001'
ORDER BY a.creation_date DESC;
```

### Session detail with key signals (pivot)

One row per session, with the main tags as columns:

```sql
SELECT
    a.session_id,
    a.agent_name,
    a.data_product,
    (SELECT GROUP_CONCAT(n.lu_name || '/' || n.iid, ', ')
       FROM INSTANCES n WHERE n.session_id = a.session_id)                      AS instances,
    a.creation_date,
    a.conversation_pairs,
    a.avg_response_time,
    a.total_tokens,
    a.submitted_by,
    MAX(CASE WHEN i.tag_name = 'Resolution Status' THEN i.tag_value END)       AS resolution_status,
    MAX(CASE WHEN i.tag_name = 'Sentiment'         THEN i.tag_value END)       AS sentiment,
    GROUP_CONCAT(CASE WHEN i.tag_name = 'Topic'    THEN i.tag_value END, ', ') AS topics,
    MAX(CASE WHEN i.tag_name = 'Positive Feedback' THEN i.tag_value END)       AS positive_feedback,
    MAX(CASE WHEN i.tag_name = 'Negative Feedback' THEN i.tag_value END)       AS negative_feedback
FROM ACTVSESSION a
LEFT JOIN INSIGHTS i ON i.session_id = a.session_id
WHERE a.status = 'CLOSED'
GROUP BY a.session_id, a.agent_name, a.data_product, a.creation_date,
         a.conversation_pairs, a.avg_response_time, a.total_tokens, a.submitted_by
ORDER BY a.creation_date DESC;
```

### Token cost by model

```sql
SELECT
    interface,
    model,
    total_tokens,
    input_tokens,
    output_tokens,
    cache_read_tokens,
    last_update
FROM model_token_usage
ORDER BY total_tokens DESC;
```

### Response time and token usage by agent (last 30 days)

```sql
SELECT
    agent_name,
    COUNT(session_id)                AS sessions,
    ROUND(AVG(avg_response_time), 1) AS avg_response_sec,
    ROUND(AVG(total_tokens), 0)      AS avg_tokens_per_session,
    SUM(total_tokens)                AS total_tokens
FROM ACTVSESSION
WHERE last_modified_date >= DATE('now', '-30 days')
  AND status = 'CLOSED'
GROUP BY agent_name
ORDER BY total_tokens DESC;
```


## Per-Session Tables in the aifusion LU

The detailed record of each session is stored in the `aifusion` LU, in one instance per session (IID = `session_id`). These tables are not in the insights database and are not queried by direct SQL against it; they are read through Fabric — by the conversation review, the trace panel and the analysis process.

<table>
<tbody>
<tr>
<td><strong>Table</strong></td>
<td><strong>Key columns</strong></td>
<td><strong>Contents</strong></td>
</tr>
<tr>
<td><strong>CHAT</strong></td>
<td><code>sessionid</code>, <code>createtime</code>, <code>synopsis</code></td>
<td>The session record</td>
</tr>
<tr>
<td><strong>STEP</strong></td>
<td><code>step_id</code>, <code>user</code>, <code>assistant</code>, <code>timestamp</code>, <code>duration</code>, <code>time_to_first_token</code></td>
<td>Each user–assistant turn: the question, the answer and its latency</td>
</tr>
<tr>
<td><strong>EXECUTIONS</strong></td>
<td><code>execution_id</code>, <code>step_id</code>, <code>name</code>, <code>status</code>, <code>duration</code>, <code>tool_call_id</code></td>
<td>The agent and sub-agent executions in each turn</td>
</tr>
<tr>
<td><strong>MESSAGES</strong></td>
<td><code>message_id</code>, <code>execution_id</code>, <code>role</code>, <code>content</code>, <code>tool_calls</code></td>
<td>The full message sequence sent to and received from the LLM, including tool messages</td>
</tr>
<tr>
<td><strong>TOOL_CALLS</strong></td>
<td><code>tool_call_id</code>, <code>function</code>, <code>arguments</code>, <code>response</code>, <code>duration</code></td>
<td>Each tool invocation with its input, output and latency</td>
</tr>
<tr>
<td><strong>USAGE</strong></td>
<td><code>execution_id</code>, <code>interface</code>, <code>input_tokens</code>, <code>output_tokens</code>, <code>cache_read_tokens</code>, <code>cache_write_tokens</code>, <code>total_tokens</code>, <code>duration</code></td>
<td>Token usage of each LLM call</td>
</tr>
<tr>
<td><strong>FEEDBACK</strong></td>
<td><code>id</code>, <code>step_id</code>, <code>reaction</code>, <code>note</code>, <code>submitted_by</code></td>
<td>User ratings (<code>1</code> = thumbs up, <code>-1</code> = thumbs down) and notes per turn</td>
</tr>
<tr>
<td><strong>SNAPS</strong></td>
<td><code>lu_name</code>, <code>iid</code>, <code>step_id</code>, <code>snap_file</code>, <code>mode</code></td>
<td>Data snapshots of the LU instances taken during the session</td>
</tr>
<tr>
<td><strong>COMPACTION</strong>, <strong>SUMMARY</strong></td>
<td><code>execution_id</code>, <code>summary</code></td>
<td>Context-compaction and history summaries used to keep long sessions within the model's context</td>
</tr>
<tr>
<td><strong>PLANS</strong>, <strong>TASKS</strong></td>
<td><code>execution_id</code>, <code>plan_id</code> / <code>task_id</code>, <code>status</code></td>
<td>Plans and to-do items of agents that use planning</td>
</tr>
<tr>
<td><strong>MEMORY_BLOCKS</strong>, <strong>AGENT_MEMORY</strong></td>
<td><code>execution_id</code>, <code>label</code> / <code>uuid</code></td>
<td>Memory blocks and saved memories of agents with memory enabled</td>
</tr>
</tbody>
</table>

The cleanup process deletes the session's `aifusion` LU instance together with its insights rows when the session passes the retention period. See [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md#session-retention-and-cleanup).

**Next article:** [Observation Best Practices](/articles/AI_fusion/05_observation/05_best_practices.md)
