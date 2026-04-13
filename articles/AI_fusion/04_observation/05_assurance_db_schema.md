# Assurance Database Schema Reference

AI Fusion stores observation data in the **Assurance database** — a relational database that BI tools, reporting systems, and data pipelines can query directly. This article describes the table schemas, their relationships, and provides example queries for common reporting use cases.

The Assurance database holds session-level and aggregate data. Detailed per-turn data (individual messages, tool calls, execution traces) is stored in Fabric LU storage and is accessed via the AI Fusion REST API, not by direct SQL — this is described separately at the end of this article.



## Assurance DB Tables

### ACTVSESSION

This is the central table for monitoring, holding one row per conversation session. 

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
<td>Unique session identifier. Join key for all other tables.</td>
</tr>
<tr>
<td><code>creation_date</code></td>
<td>TIMESTAMP</td>
<td>When the session was created.</td>
</tr>
<tr>
<td><code>last_modified_date</code></td>
<td>TIMESTAMP</td>
<td>Timestamp of the last message in the session.</td>
</tr>
<tr>
<td><code>app_id</code></td>
<td>TEXT</td>
<td>AI Fusion application that handled the session (e.g., <code>banking</code>, <code>telecom</code>).</td>
</tr>
<tr>
<td><code>customer_id</code></td>
<td>TEXT</td>
<td>Customer identifier (IID — Logical Unit Instance ID). Correlates with CRM records.</td>
</tr>
<tr>
<td><code>chat_summary</code></td>
<td>TEXT</td>
<td>LLM-generated summary of the conversation. Populated after <code>RunAnalyze.flow</code> runs.</td>
</tr>
<tr>
<td><code>suggestion</code></td>
<td>TEXT</td>
<td>LLM-generated suggested next action or follow-up for this conversation. Currently reserved for future use.</td>
</tr>
<tr>
<td><code>input_tokens</code></td>
<td>INTEGER</td>
<td>Total input tokens consumed across all LLM calls in the session.</td>
</tr>
<tr>
<td><code>output_tokens</code></td>
<td>INTEGER</td>
<td>Total output tokens generated.</td>
</tr>
<tr>
<td><code>cache_read_tokens</code></td>
<td>INTEGER</td>
<td>Tokens served from the LLM provider's prompt cache (lower cost than input tokens).</td>
</tr>
<tr>
<td><code>cache_write_tokens</code></td>
<td>INTEGER</td>
<td>Tokens written to the prompt cache.</td>
</tr>
<tr>
<td><code>total_tokens</code></td>
<td>INTEGER</td>
<td>Sum of all token types for the session.</td>
</tr>
<tr>
<td><code>conversation_pairs</code></td>
<td>INTEGER</td>
<td>Number of user–assistant turn pairs.</td>
</tr>
<tr>
<td><code>avg_response_time</code></td>
<td>INTEGER</td>
<td>Average assistant response time across all turns, in milliseconds.</td>
</tr>
<tr>
<td><code>feedback</code></td>
<td>TEXT</td>
<td>Aggregated feedback indicator for the session.</td>
</tr>
<tr>
<td><code>submitted_by</code></td>
<td>TEXT</td>
<td>User or system identity that initiated the session.</td>
</tr>
<tr>
<td><code>snap_mode</code></td>
<td>TEXT</td>
<td>Data snapshot mode: <code>none</code>, <code>all</code>, <code>first</code>, or <code>first+last</code>.</td>
</tr>
<tr>
<td><code>status</code></td>
<td>TEXT</td>
<td><code>OPEN</code> (active or pending analysis) / <code>CLOSED</code> (analyzed) / <code>FAILED</code>.</td>
</tr>
</tbody>
</table>

> **Notes:** 
>
> * `chat_summary`, `suggestion`, and the `*_tokens` aggregates are populated only after `RunAnalyze.flow` or `runBatchChatAnalyzePipeline.flow` has processed the session. Unanalyzed sessions have `status = 'OPEN'` and NULL values for these columns.
> * `customer_id` maps to the IID of the relevant Logical Unit (e.g., the Customer LU). 



### INSIGHTS

Contains one row per insight tag per session. Each analyzed session produces multiple rows, one for each signal the LLM extracted.

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
<td>Foreign key → <code>ACTVSESSION.session_id</code>.</td>
</tr>
<tr>
<td><code>tag_name</code></td>
<td>TEXT</td>
<td>The signal category name.</td>
</tr>
<tr>
<td><code>tag_value</code></td>
<td>TEXT</td>
<td>The value classified for this signal.</td>
</tr>
</tbody>
</table>
**Default signals** (defined in the `chat_signals.csv` MTable, configurable per deployment):

<table>
<tbody>
<tr>
<td><strong>tag_name</strong></td>
<td><strong>tag_value options</strong></td>
</tr>
<tr>
<td><code>Sentiment</code></td>
<td>Satisfaction, Frustration, Neutral, Urgency, Happiness, Anger, Concern, Appreciation, Discontent, Disappointment</td>
</tr>
<tr>
<td><code>Resolution Status</code></td>
<td>Resolved, Pending, Escalated</td>
</tr>
<tr>
<td><code>Topic</code></td>
<td>Billing Inquiry, Account Update, Technical Support, Product Information, Complaint, Service Request, Fraud Report, Payment Issue, Contract Renewal, General Inquiry, Feedback, Escalation Request, Other</td>
</tr>
<tr>
<td><code>Urgency Level</code></td>
<td>Low, Medium, High</td>
</tr>
<tr>
<td><code>Follow-up</code></td>
<td>Required, Immediate</td>
</tr>
<tr>
<td><code>Complaint Severity</code></td>
<td>Low, Medium, High</td>
</tr>
<tr>
<td><code>Customer Lifetime Value Risk</code></td>
<td>Low, Medium, High</td>
</tr>
<tr>
<td><code>Upsell Opportunity</code></td>
<td>Low, Medium, High</td>
</tr>
<tr>
<td><code>Consistency</code></td>
<td>Low, Medium, High</td>
</tr>
<tr>
<td><code>Resolution Pathway</code></td>
<td>conversation_started, issue_understood, topic_classified, solution_offered, problem_solved, satisfaction_expressed, natural_closure <em>(and others)</em></td>
</tr>
</tbody>
</table>
>  Note: Since `tag_name` / `tag_value` is a key-value structure, pivot queries are needed to produce wide-format rows for BI tools. See the example queries below.



### INVOKE_OBSERVATION

Telemetry for every LLM AI invocation. Use this table for performance profiling and LLM cost attribution at the flow level. 

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
<td>Name of the Broadway flow executed.</td>
</tr>
<tr>
<td><code>actor</code></td>
<td>TEXT</td>
<td>Name of the actor within the flow.</td>
</tr>
<tr>
<td><code>interface</code></td>
<td>TEXT</td>
<td>LLM interface or service used (e.g., <code>gpt4</code>, <code>claude</code>).</td>
</tr>
<tr>
<td><code>timestamp</code></td>
<td>TIMESTAMP</td>
<td>When the invocation occurred.</td>
</tr>
<tr>
<td><code>duration</code></td>
<td>INTEGER</td>
<td>Execution duration in milliseconds.</td>
</tr>
<tr>
<td><code>total_tokens</code></td>
<td>INTEGER</td>
<td>Total tokens consumed by this invocation.</td>
</tr>
<tr>
<td><code>input_tokens</code></td>
<td>INTEGER</td>
<td>Input tokens.</td>
</tr>
<tr>
<td><code>output_tokens</code></td>
<td>INTEGER</td>
<td>Output tokens.</td>
</tr>
<tr>
<td><code>cache_read_tokens</code></td>
<td>INTEGER</td>
<td>Cache read tokens.</td>
</tr>
<tr>
<td><code>cache_write_tokens</code></td>
<td>INTEGER</td>
<td>Cache write tokens.</td>
</tr>
<tr>
<td><code>status</code></td>
<td>TEXT</td>
<td><code>IN_PROGRESS</code>, <code>DONE</code>, or <code>ERROR</code>.</td>
</tr>
</tbody>
</table>

> **Note:** This table mentions Broadway flow and actor names. Naming under agents and tools exist in the AIFusion LU tables.



### MODEL_TOKEN_USAGE

Aggregation of token consumption, grouped by LLM interface and model. Updated continuously. Use for cost reporting and model usage trend dashboards.

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
<td>LLM interface name (e.g., <code>gpt4</code>, <code>claude-sonnet</code>).</td>
</tr>
<tr>
<td><code>model</code></td>
<td>TEXT (PK)</td>
<td>Model identifier returned by the provider.</td>
</tr>
<tr>
<td><code>total_tokens</code></td>
<td>INTEGER</td>
<td>Cumulative total tokens since deployment.</td>
</tr>
<tr>
<td><code>input_tokens</code></td>
<td>INTEGER</td>
<td>Cumulative input tokens.</td>
</tr>
<tr>
<td><code>output_tokens</code></td>
<td>INTEGER</td>
<td>Cumulative output tokens.</td>
</tr>
<tr>
<td><code>cache_read_tokens</code></td>
<td>INTEGER</td>
<td>Cumulative cache read tokens.</td>
</tr>
<tr>
<td><code>cache_write_tokens</code></td>
<td>INTEGER</td>
<td>Cumulative cache write tokens.</td>
</tr>
<tr>
<td><code>last_update</code></td>
<td>TIMESTAMP</td>
<td>When this row was last updated.</td>
</tr>
</tbody>
</table>


## Table Relationships

```
ACTVSESSION (session_id)
    └── INSIGHTS (session_id)         — LLM-assigned tags, one row per tag per session

INVOKE_OBSERVATION                    — per-invocation telemetry (correlate via timestamp)
MODEL_TOKEN_USAGE                     — cumulative totals by interface + model
```



## Example Queries

The following queries target the Assurance DB and are compatible with both SQLite and PostgreSQL unless noted.

More examples can be found in the Observation Dashboard default widgets queries.

### Daily resolution rate (last 30 days)

```sql
SELECT
    DATE(a.creation_date)        AS day,
    COUNT(DISTINCT a.session_id) AS total_sessions,
    COUNT(DISTINCT CASE WHEN i.tag_value = 'Resolved'
          THEN a.session_id END) AS resolved,
    ROUND(
        100.0
        * COUNT(DISTINCT CASE WHEN i.tag_value = 'Resolved' THEN a.session_id END)
        / NULLIF(COUNT(DISTINCT a.session_id), 0)
    , 1)                         AS resolution_rate_pct
FROM ACTVSESSION a
LEFT JOIN INSIGHTS i
       ON a.session_id = i.session_id
      AND i.tag_name   = 'Resolution Status'
WHERE a.creation_date >= DATE('now', '-30 days')
  AND a.status         = 'CLOSED'
GROUP BY DATE(a.creation_date)
ORDER BY day;
```

### Sentiment breakdown by application

```sql
SELECT
    a.app_id,
    i.tag_value   AS sentiment,
    COUNT(*)      AS session_count
FROM ACTVSESSION a
JOIN INSIGHTS i
  ON a.session_id = i.session_id
 AND i.tag_name   = 'Sentiment'
WHERE a.status = 'CLOSED'
GROUP BY a.app_id, i.tag_value
ORDER BY a.app_id, session_count DESC;
```

### Session detail with key signals (pivot)

Produces one row per session with the most-used tags as columns.

```sql
SELECT
    a.session_id,
    a.app_id,
    a.customer_id,
    a.creation_date,
    a.conversation_pairs,
    a.avg_response_time,
    a.total_tokens,
    a.submitted_by,
    MAX(CASE WHEN i.tag_name = 'Sentiment'                     THEN i.tag_value END) AS sentiment,
    MAX(CASE WHEN i.tag_name = 'Resolution Status'             THEN i.tag_value END) AS resolution_status,
    MAX(CASE WHEN i.tag_name = 'Topic'                         THEN i.tag_value END) AS topic,
    MAX(CASE WHEN i.tag_name = 'Urgency Level'                 THEN i.tag_value END) AS urgency_level,
    MAX(CASE WHEN i.tag_name = 'Customer Lifetime Value Risk'  THEN i.tag_value END) AS clv_risk
FROM ACTVSESSION a
LEFT JOIN INSIGHTS i ON a.session_id = i.session_id
WHERE a.status = 'CLOSED'
GROUP BY a.session_id, a.app_id, a.customer_id, a.creation_date,
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
FROM MODEL_TOKEN_USAGE
ORDER BY total_tokens DESC;
```

### Average response time and token usage by application (last 30 days)

```sql
SELECT
    app_id,
    COUNT(session_id)                AS sessions,
    ROUND(AVG(avg_response_time), 0) AS avg_response_ms,
    ROUND(AVG(total_tokens), 0)      AS avg_tokens_per_session,
    SUM(total_tokens)                AS total_tokens
FROM ACTVSESSION
WHERE creation_date >= DATE('now', '-30 days')
  AND status = 'CLOSED'
GROUP BY app_id
ORDER BY total_tokens DESC;
```




## Fabric LU Tables

The following tables are stored inside the Fabric LU - one LUI per session (not accessible via direct SQL). 

<table>
<tbody>
<tr>
<td><strong>Table</strong></td>
<td><strong>Key columns</strong></td>
<td><strong>What it contains</strong></td>
</tr>
<tr>
<td><strong>STEP</strong></td>
<td><code>step_id</code>, <code>user</code>, <code>assistant</code>, <code>duration</code>, <code>time_to_first_token</code></td>
<td>Each user–assistant turn: raw question text, response text, latency</td>
</tr>
<tr>
<td><strong>MESSAGES</strong></td>
<td><code>message_id</code>, <code>execution_id</code>, <code>role</code>, <code>content</code>, <code>tool_calls</code></td>
<td>Full message sequence including tool-role messages; used to reconstruct the exact LLM context sent to the model</td>
</tr>
<tr>
<td><strong>EXECUTIONS</strong></td>
<td><code>execution_id</code>, <code>step_id</code>, <code>name</code>, <code>status</code>, <code>duration</code></td>
<td>The modules executions per turn, like agents and tools names</td>
</tr>
<tr>
<td><strong>TOOL_CALLS</strong></td>
<td><code>tool_call_id</code>, <code>function</code>, <code>arguments</code>, <code>response</code>, <code>duration</code></td>
<td>Individual tool/function invocations with inputs, outputs, and latency</td>
</tr>
<tr>
<td><strong>USAGE</strong></td>
<td><code>execution_id</code>, <code>interface</code>, <code>input_tokens</code>, <code>output_tokens</code></td>
<td>Token usage attributed to each LLM invocation within a turn</td>
</tr>
<tr>
<td><strong>FEEDBACK</strong></td>
<td><code>step_id</code>, <code>reaction</code>, <code>note</code>, <code>submitted_by</code></td>
<td>User ratings and free-text notes per turn</td>
</tr>
</tbody>
</table>

