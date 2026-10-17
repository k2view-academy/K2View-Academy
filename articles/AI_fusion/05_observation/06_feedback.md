# Collecting Feedback

Agents improve fastest when the people who use them say which answers were good and which were not. AI Fusion lets users rate each agent reply with a thumbs-up or thumbs-down and attach a free-text note that explains the rating. The feedback is stored against the exact turn of the conversation, so a supervisor or QA engineer can later open the conversation, see the rated reply in context, and decide what to fix.

A thumbs-down alone tells you *that* something went wrong. A note such as "quoted the wrong account balance" tells you *what* went wrong, and saves the reviewer from re-reading the whole conversation. Encourage users to add notes, especially on negative ratings.


## Where Feedback Is Collected and Shown

<table>
<tbody>
<tr>
<td><strong>Surface</strong></td>
<td><strong>Feedback</strong></td>
</tr>
<tr>
<td>Preview in AI Fusion Studio</td>
<td>Collected. <strong>Good response</strong>, <strong>Bad response</strong> and <strong>Note</strong> buttons under each reply. See <a href="/articles/AI_fusion/03_studio/03_preview_and_trace.md">Preview and Trace</a>.</td>
</tr>
<tr>
<td>Your own front end</td>
<td>Collected through the <code>feedback</code> web service. See <a href="#the-feedback-web-service">The Feedback Web Service</a>.</td>
</tr>
<tr>
<td><a href="/articles/AI_fusion/05_observation/README.md">Observation</a></td>
<td>Shown, read-only. The conversation review shows the rating and note of each turn, and the conversation list can be filtered by feedback.</td>
</tr>
</tbody>
</table>

All surfaces read and write the same records, so feedback given in Studio Preview or through your own front end appears in Observation.


## Giving Feedback in Studio Preview

When you test an agent in the Preview of AI Fusion Studio, each reply has **Good response**, **Bad response** and **Note** buttons. The two ratings exclude each other, clicking an active rating removes it, and **Note** opens a box where you can save or remove a note. Developers can use this while testing to mark answers to come back to. See [Preview and Trace](/articles/AI_fusion/03_studio/03_preview_and_trace.md).


## Reviewing Feedback in Observation

Observation is where supervisors and QA engineers act on feedback:

- The conversation list shows positive and negative feedback counts per conversation.
- The **Feedback** filter narrows the list to **Any feedback**, **No feedback**, **Has positive**, **Has negative**, **Has notes**, **Positive only**, **Negative only**, or **Mixed (positive + negative)**.
- The conversation review shows each turn's rating, and its note behind the note icon. Feedback cannot be changed there.

See [Observation Dashboard](/articles/AI_fusion/05_observation/01_observation_dashboard.md) and [Conversation Review](/articles/AI_fusion/05_observation/02_conversation_review.md).


## From Feedback to a Better Agent

A practical loop for turning negative feedback into durable improvements:

1. **Find the bad answers.** In Observation, filter conversations by **Has negative** or **Has notes**, and start with the ones whose notes point to a concrete error.
2. **Review in context.** Open the conversation and read the turns before the rated reply. Decide whether the cause is missing data, a wrong tool result, a prompt or reasoning issue, a question outside the agent's scope, or a reasonable answer that did not match the user's expectation.
3. **Capture it as a test.** Turn the conversation into an evaluation test case so that the failure is reproducible. See [Snapshots and Production Import](/articles/AI_fusion/04_evaluation/04_snapshots_and_production_import.md) and [Authoring Test Cases](/articles/AI_fusion/04_evaluation/01_authoring_test_cases.md).
4. **Fix and verify.** Change the agent, rerun the test case to confirm the fix, and run the agent's suites to check for regressions. See [Running Evaluations and Results](/articles/AI_fusion/04_evaluation/03_running_and_results.md).

Because the case now lives in a suite, a later change that brings the problem back is caught by the evaluation, not by the next unhappy user.


## The Feedback Web Service

Use the `feedback` web service of the Web Services LU to collect feedback from your own front end, for example a rating widget in a mobile app that talks to an agent through the `agent` web service (see [Invoking Agents](/articles/AI_fusion/02_agents/09_invoking_agents.md)). The service uses the same records as Studio Preview, so feedback you submit appears in Observation like any other.

### Identifying the Reply

Feedback is attached to one turn of one session:

<table>
<tbody>
<tr>
<td><strong>Parameter</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>session_id</code></td>
<td>The session ID you passed as <code>sessionId</code> to the <code>agent</code> web service.</td>
</tr>
<tr>
<td><code>message_id</code></td>
<td>The turn number within the session, starting at <code>1</code>: the reply to the first user message is <code>1</code>, the reply to the second is <code>2</code>, and so on.</td>
</tr>
</tbody>
</table>

### Feedback Values

<table>
<tbody>
<tr>
<td><strong>Parameter</strong></td>
<td><strong>Values</strong></td>
</tr>
<tr>
<td><code>reaction</code></td>
<td><code>"1"</code> for positive, <code>"-1"</code> for negative, <code>""</code> (empty) for no rating, for example a note without a rating.</td>
</tr>
<tr>
<td><code>note</code></td>
<td>Free text. Optional.</td>
</tr>
</tbody>
</table>

The user who submitted the feedback is not a parameter. The service records the user of the calling session.

### Operations

<table>
<tbody>
<tr>
<td><strong>Verb</strong></td>
<td><strong>Parameters</strong></td>
<td><strong>Use it to</strong></td>
</tr>
<tr>
<td><code>POST</code></td>
<td><code>session_id</code>, <code>message_id</code>, <code>reaction</code>, <code>note</code></td>
<td>Create the feedback of a turn. Fails if that turn already has feedback.</td>
</tr>
<tr>
<td><code>PUT</code></td>
<td><code>session_id</code>, <code>message_id</code>, <code>reaction</code>, <code>note</code></td>
<td>Replace the rating and note of a turn that already has feedback.</td>
</tr>
<tr>
<td><code>DELETE</code></td>
<td><code>session_id</code>, <code>message_id</code></td>
<td>Remove the feedback of a turn.</td>
</tr>
<tr>
<td><code>GET</code></td>
<td><code>chat_id</code> (the session ID)</td>
<td>Return all feedback records of the session. Returns an empty list for a session that has not produced its first turn yet.</td>
</tr>
</tbody>
</table>

Each turn has at most one feedback record. Studio Preview follows this pattern, which you can reuse:

- **POST** when the user gives the first feedback on a turn (a rating or a note).
- **PUT** when the user changes it, and still has a rating or a note.
- **DELETE** when the user clears both the rating and the note.

Call the service like any other Fabric web service, with the same authentication your front end uses for the `agent` web service (see [Web Services](/articles/15_web_services_and_graphit/README.md)). For example, to rate the second reply of a session as negative with a note:

```bash
curl -X POST "https://<server>/api/feedback?format=json" \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"session_id": "6f1c2a7e-…", "message_id": 2, "reaction": "-1", "note": "Quoted the wrong account balance"}'
```

A successful POST or PUT returns:

```json
{ "STATUS": "OK" }
```

Invalid requests return HTTP 400, for example when the session ID does not exist, or on a POST for a turn that already has feedback.

### What Is Stored

Feedback is stored in the `FEEDBACK` table of the session's instance of the aifusion LU, one row per rated turn:

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Content</strong></td>
</tr>
<tr>
<td><code>id</code></td>
<td>A generated record ID.</td>
</tr>
<tr>
<td><code>step_id</code></td>
<td>The turn (agent step) the feedback refers to.</td>
</tr>
<tr>
<td><code>reaction</code></td>
<td><code>1</code>, <code>-1</code> or empty.</td>
</tr>
<tr>
<td><code>note</code></td>
<td>The note text.</td>
</tr>
<tr>
<td><code>creation_date</code>, <code>change_date</code></td>
<td>When the feedback was created and last changed.</td>
</tr>
<tr>
<td><code>submitted_by</code></td>
<td>The user who submitted or last changed it.</td>
</tr>
</tbody>
</table>

The `GET` operation returns these columns.
