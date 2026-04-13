# Observation Best Practices

This article describes workflows and strategies for getting the most out of the Observation module - from daily monitoring habits to connecting production data back into the quality improvement cycle.



## Establish a Monitoring Routine

Observation data is most useful when reviewed consistently rather than reactively. A structured cadence prevents issues from accumulating undetected.

**Recommended schedule:**

<table>
<tbody>
<tr>
<td><strong>Cadence</strong></td>
<td><strong>Focus</strong></td>
<td><strong>Duration filter</strong></td>
</tr>
<tr>
<td><strong>Daily</strong></td>
<td>New high-risk or unresolved conversations since yesterday</td>
<td>Last 24 Hours</td>
</tr>
<tr>
<td><strong>Weekly</strong></td>
<td>Overall quality trends, sentiment shifts, topic patterns</td>
<td>Last 7 Days</td>
</tr>
<tr>
<td><strong>Monthly</strong></td>
<td>Long-term performance, resolved rate trajectory, cost review</td>
<td>Last 30 Days</td>
</tr>
</tbody>
</table>

A daily review using the **High Risk** and **Unresolved** filters takes only a few minutes and catches critical issues before they affect more users.



## Prioritize the Right Conversations

Not every conversation requires review. Use filter modes to focus effort on the sessions most likely to surface actionable issues:

- **High Risk** - the highest-priority filter; these conversations may involve incorrect information, compliance exposure, or frustrated users
- **Unresolved** - conversations where the agent did not meet the user's need; these often reveal gaps in agent knowledge or tool coverage
- **By Customer ID** - use when investigating a complaint from a specific customer; retrieves the full history for that IID

Avoid reviewing conversations in **Latest** order by default - recency alone is not a good proxy for importance.



## Act on High-Risk Conversations

When a high-risk conversation is identified, follow this workflow:

1. **Open the conversation review** - read the full transcript and check the trace panel for execution anomalies
2. **Identify the root cause** - is the issue a data gap, a reasoning failure, a tool error, or an ambiguous user request?
3. **Export the conversation** - use the Export button to download the conversation as a ZIP
4. **Import into Evaluation** - bring the conversation into the Evaluation workspace as a test case (see [Data Snapshots and Production Import](/articles/AI_fusion/03_evaluation/09_data_snapshots_and_production_import.md))
5. **Fix and re-test** - update the agent, run the new test case, verify the issue is resolved

This converts a production failure into a permanent regression test - ensuring the same problem cannot recur undetected.



## Connect Observation to Evaluation

The Observation → Evaluation feedback loop is one of the most powerful quality practices available in AI Fusion. Production conversations are the best source of realistic test cases because they reflect actual user behavior, not assumed scenarios.

Build a habit of periodically exporting interesting conversations - not just failures, but also strong positive examples - and saving them as test cases. Over time, this builds a test suite grounded in real-world usage rather than hypothetical questions.

For the full import workflow, see [Data Snapshots and Production Import](/articles/AI_fusion/03_evaluation/09_data_snapshots_and_production_import.md).



## Use Custom Tags Strategically

The default tag set (Topic, Sentiment, Resolution, Risk, Follow-up) covers general quality monitoring. Custom tags become valuable when your use case has specific classification needs that the defaults do not capture.

**Good candidates for custom tags:**

- **Compliance flag** - mark conversations that touched a regulated topic (e.g., credit decisions, medical advice)
- **Product area** - classify by product line when one application serves multiple business domains
- **Escalation category** - indicate the type of escalation needed (billing, technical, fraud)
- **Language** - track non-native language sessions if multilingual support is in scope

Avoid creating tags that duplicate existing ones or that cannot be reliably inferred from the conversation text - low-confidence tags reduce the usefulness of the dashboard filters.



## Manage Observation Costs

LLM-as-a-Judge tagging is the primary cost driver in Observation. Several practices keep costs controlled:

- **Schedule the analysis flow** - analysis does not run automatically; configure a Fabric scheduled job to run `RunAnalyze.flow` (or `runBatchChatAnalyzePipeline.flow` for high volume) at a regular interval (e.g., nightly). Without a schedule, the dashboard will show no data.
- **Use batch mode for high volume** - `runBatchChatAnalyzePipeline.flow` uses the OpenAI Batch API and costs ~50% less per session than standard mode; use it for production environments with significant conversation volume
- **Scope observation to production apps** - disable the `observation` flag for development or staging environments where volume is high but data is not meaningful
- **Tune the signal set** - each additional signal in `chat_signals.csv` adds to the LLM prompt and increases cost; keep only signals that drive real actions or decisions
- **Adjust analysis frequency** - for low-volume applications, running analysis once daily is sufficient; for high-traffic applications, consider more frequent runs or the batch pipeline



## Integrate with External BI Tools

For organizations with existing analytics infrastructure, Observation data can be accessed beyond the built-in dashboard:

- **CSV export** from the dashboard provides a quick way to share conversation summaries with stakeholders
- **Direct database access** - the Metrics/Assurance database (PostgreSQL in production) can be connected to any BI tool (Tableau, Power BI, Looker) for custom reporting
- **API access** - Observation data is also accessible via Fabric web services for programmatic integration

Use external BI tools when you need cross-application aggregation, custom visualization, or integration with organizational reporting systems that span beyond AI Fusion.


