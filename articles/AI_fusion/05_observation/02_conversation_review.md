# Conversation Review

The **Conversation review** page is a drill-down into a single production session. It shows the session's tags, its summary, the full transcript with the user's feedback, and the execution trace behind every answer — everything you need to understand what happened and decide what to do about it.

To open it, click a row in the **Conversation Details** table of the [Observation Dashboard](/articles/AI_fusion/05_observation/01_observation_dashboard.md). Each review has its own URL (`.../observation/conversation/<sessionId>`), so you can share a link to a specific conversation.

<!-- TODO screenshot: Conversation review page with header, tags, summary, transcript and trace panel -->


## Page Layout

From top to bottom:

1. **Header** — the title, Prev/Next navigation, session information and the export button
2. **Tags** — the signals the analysis assigned to the session
3. **Conversation Summary** and the transcript
4. **Trace panel** — the execution detail behind each turn


## Navigating Between Conversations

When you open a conversation from the dashboard, the header shows where you are in the dashboard's list, for example **Conversation 3 of 42**, with **Prev** and **Next** buttons. They step through the same list the dashboard showed — the same scope, time range and filter, in the same order — so you can review all the unresolved or high-risk conversations one after another without returning to the dashboard.

- A filter label next to the buttons shows which list you are walking through, for example **Last 7 Days · Agent: banking (customer_bank) · Unresolved Conversations**.
- Hovering over **Prev** or **Next** shows a preview of that conversation: its position, time and first user question.
- Use the **←** and **→** keys as shortcuts for **Prev** and **Next**.
- The browser's Back button returns you to the dashboard, not through every conversation you visited.

If you open a review from a shared link, the page rebuilds the list from the dashboard settings saved in your browser.

<!-- TODO screenshot: Prev/Next navigation with filter label and preview tooltip -->


## Session Information

Below the title, the header shows:

- **Agent** — the agent that handled the session, as **<agent> (<LU>)**. Sessions recorded before AI Fusion stored the agent's Data Product show the agent name only.
- **Instances** — the LU instances the session ran against, as **<LU> / <IID>**. A session can involve more than one instance.
- **Date/time** — when the session started.


## Conversation Tags

The tags strip shows every tag the analysis stored for the session, as **<signal>: <value>**. This includes:

- The core signals — **Resolution Status**, **Sentiment** and **Topic** (one tag per selected topic)
- The agent's own signals from its `Observation/signals.yaml`, for example **Urgency Level** or **Customer Lifetime Value Risk**
- Feedback counters — **Positive Feedback**, **Negative Feedback** and **Feedback Notes** — when the session received feedback

A session that has not been analyzed yet has no tags. See [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md#running-the-analysis) for how to run the analysis.


## Conversation Summary and Transcript

The **Conversation Summary** panel shows the short LLM-generated summary written by the analysis process, so you can grasp the session's content and outcome before reading it.

Next to it, the transcript renders the full exchange of user and assistant messages, in the same format the user saw. The transcript is read-only.

### User feedback

Where the user rated an answer, the rating is shown on that assistant turn: thumbs up or thumbs down, and a note indicator when the user also wrote a comment. Open the note indicator to read the comment. Feedback is read-only here; see [Collecting Feedback](/articles/AI_fusion/05_observation/06_feedback.md) for how users and applications submit it.


## Trace Panel

The trace panel shows the execution behind each assistant answer: the agent and sub-agent executions, the LLM calls with their responses and token usage (including cached tokens), and the tool calls with their inputs and outputs. It is the same trace view used by the Studio Preview; see [Preview and Trace](/articles/AI_fusion/03_studio/03_preview_and_trace.md).

Use it to diagnose why an answer was wrong or slow — for example, a tool that returned unexpected data, a sub-agent that was not called, or a step that consumed far more tokens than expected.

<!-- TODO screenshot: trace panel expanded on one assistant turn -->


## Exporting a Conversation

Click the export button in the header to open the **Export Chat** dialog, then click **Export** to download the conversation as a ZIP file.

Select **Export with snap** to include the data snapshots of the session's LU instances that were recorded during the session, so the conversation can be replayed against the same data state it originally ran on. Snapshots are recorded only when the agent's `snap_session_mode` setting is not `none` (see [agent.yaml Reference](/articles/AI_fusion/02_agents/02_agent_yaml_reference.md)).

<table>
<tbody>
<tr>
<td><strong>Export option</strong></td>
<td><strong>Use case</strong></td>
</tr>
<tr>
<td>Without snap</td>
<td>Share the transcript for review; lightweight</td>
</tr>
<tr>
<td><strong>Export with snap</strong></td>
<td>Reproduce the conversation on the exact data it saw, for debugging or as a regression test</td>
</tr>
</tbody>
</table>

The ZIP also records the agent and the session's LU instances.


## From Observation to Evaluation

A production conversation can become an evaluation test case:

1. In Conversation review, export the conversation (with **Export with snap** for full reproducibility).
2. In AI Fusion Studio, open the agent's Preview and click **Import** to load the ZIP as a new chat.
3. Click **Save as test case** and complete the test case — for example, edit the expected answers.

This closes the quality loop: a real production failure becomes a regression test, so the same issue cannot return unnoticed. See [Snapshots and Production Import](/articles/AI_fusion/04_evaluation/04_snapshots_and_production_import.md) for the full workflow.

**Next article:** [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md)
