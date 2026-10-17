# Authoring Test Cases

A test case is a recorded conversation with your agent that you want to keep answering correctly. Every AI Fusion test case starts from a **real run of the agent**: you hold the conversation (or let Studio AI hold it), check the answers, adjust any answer that is not what you expect, and save it. This article describes where test cases are stored and the four ways to create them in AI Fusion Studio.



## Suites and Test Cases

Test cases are grouped into **eval suites**. Each suite belongs to one agent and is a CSV file in the agent's `Evals` folder:

```text
<LU>/AI/Agents/<agent>/Evals/<suite>.csv
```

Each row of the file is one test case. You normally never edit these files by hand — the Eval Editor and the AI Explorer commands maintain them for you — but knowing the columns helps when you review changes in source control.

<table>
<tbody>
<tr>
<td><strong>Column</strong></td>
<td><strong>Content</strong></td>
</tr>
<tr>
<td><code>id</code></td>
<td>A stable internal row ID.</td>
</tr>
<tr>
<td><code>sessionid</code></td>
<td>The baseline session the case was saved from. Its snapshots and its token/time figures are what the case replays on and is compared with.</td>
</tr>
<tr>
<td><code>caseId</code></td>
<td>The test case name, unique within the suite.</td>
</tr>
<tr>
<td><code>initParams</code></td>
<td>JSON object of the agent inputs the conversation starts with, for example <code>{"customerId":"1"}</code>.</td>
</tr>
<tr>
<td><code>description</code></td>
<td>Free-text description of what the case checks.</td>
</tr>
<tr>
<td><code>disabled</code></td>
<td><code>1</code> when the case is excluded from runs.</td>
</tr>
<tr>
<td><code>threshold</code></td>
<td>JSON array of thresholds, for example <code>[{"defaultThreshold":{"MIN":"4.0"}},{"Tokens":{"MAX":"20"}}]</code>. See <a href="/articles/AI_fusion/04_evaluation/02_quality_criteria.md">Quality Criteria and Thresholds</a>.</td>
</tr>
<tr>
<td><code>dimension</code></td>
<td>JSON map of dimension name to the description the judge receives.</td>
</tr>
<tr>
<td><code>transcript</code></td>
<td>JSON array of <code>{"role","content"}</code> messages. <code>user</code> messages are the questions that are replayed; <code>assistant</code> messages are the expected answers.</td>
</tr>
<tr>
<td><code>transcript_edited</code></td>
<td><code>1</code> while the transcript has changes that were not yet uploaded with <strong>Upload Artifacts</strong>.</td>
</tr>
<tr>
<td><code>saved_with_snap</code></td>
<td><code>1</code> when a snapshot of the business data was captured with the case. Set once, when the case is first saved.</td>
</tr>
</tbody>
</table>

A case can be **single-turn** (one question, one answer) or **multi-turn**. In a multi-turn case, every `user` message is replayed in order within the same conversation, so the case also tests whether the agent keeps context across turns.

> **Suites are read from the deployed LU.** The evaluation runtime reads the copy of the suites that was deployed with the LU, not the files in your workspace. Studio takes care of this: it soft-deploys the agent's LU after every change it makes to a suite and before every run. If you change a CSV outside Studio (for example, by pulling from Git), deploy the LU before running.



## Managing Suites and Cases in the AI Explorer

<!-- TODO screenshot: AI Explorer context menu on an eval suite and on a test case row -->

In the **AI Explorer**, expand an agent and its **Evals** folder. Each suite is shown as a node, and its test cases are listed under it.

To create a suite, right-click the agent (or its **Evals** folder) and select **New Eval Suite...**. Enter a name; Studio creates the CSV with one test case named "New test" and opens it in the Eval Editor.

Right-click a **suite** to:

<table>
<tbody>
<tr>
<td><strong>Action</strong></td>
<td><strong>What it does</strong></td>
</tr>
<tr>
<td><strong>New Test...</strong></td>
<td>Adds a test case, seeded with the agent's default dimensions and thresholds, and opens it in the Eval Editor.</td>
</tr>
<tr>
<td><strong>Run Evaluation</strong></td>
<td>Runs every enabled case in the suite.</td>
</tr>
<tr>
<td><strong>Rename...</strong></td>
<td>Renames the suite file. Run history is kept per suite name, so the history of the old name is discarded.</td>
</tr>
<tr>
<td><strong>Enable / Disable</strong></td>
<td>If any case in the suite is disabled, enables all cases; otherwise disables all cases.</td>
</tr>
<tr>
<td><strong>Delete</strong></td>
<td>Deletes the suite file with all its cases, and clears their run history.</td>
</tr>
</tbody>
</table>

Right-click a **test case** to:

<table>
<tbody>
<tr>
<td><strong>Action</strong></td>
<td><strong>What it does</strong></td>
</tr>
<tr>
<td><strong>Run Evaluation</strong></td>
<td>Runs this case in the background. The status bullet in the tree shows progress and the result.</td>
</tr>
<tr>
<td><strong>Duplicate</strong></td>
<td>Copies the case (named "&lt;name&gt; (copy)") and captures a separate session and snapshot for the copy, so the two cases are independent.</td>
</tr>
<tr>
<td><strong>Rename...</strong></td>
<td>Renames the case. The run history of the old name is discarded.</td>
</tr>
<tr>
<td><strong>Move to Suite...</strong></td>
<td>Moves the case to another suite of the same agent. If the target suite already has a case with that name, the moved case is renamed. History does not move with the case.</td>
</tr>
<tr>
<td><strong>Enable / Disable</strong></td>
<td>Excludes the case from runs, or includes it again. Disabled cases are shown with a grayed-out icon.</td>
</tr>
<tr>
<td><strong>Delete</strong></td>
<td>Deletes the case and clears its run history.</td>
</tr>
</tbody>
</table>



## Method 1: Record a Case in the Eval Editor

Use this method when you want to design a specific scenario from scratch.

<!-- TODO screenshot: Eval Editor, Definition tab, with the conversation on the left and the Properties / Test Evaluation Configuration panel on the right -->

1. In the AI Explorer, right-click a suite and select **New Test...**. Enter a name.
2. The Eval Editor opens on the new case and shows the **New chat** dialog. Fill in the **Agent inputs** (for example, the customer ID) and, if needed, **Extra parameters (optional)**, then click **Start chat**. These values are saved as the case's initialization parameters.
3. Chat with the agent in the composer at the bottom of the conversation. The agent runs for real, on live data, exactly as in Preview. Ask the questions a real user would ask, including follow-ups.
4. In the **Properties** panel on the right, review the **Name**, add a **Description**, and check the **Location** (the suite the case belongs to).
5. Select **Save with Snap** if the agent reads business data and you want every run to replay on the data as it is now. This choice is only available before the first upload. See [Data Snapshots and Production Import](/articles/AI_fusion/04_evaluation/04_snapshots_and_production_import.md).
6. Adjust the dimensions and thresholds under **Test Evaluation Configuration**, or keep the defaults. See [Quality Criteria and Thresholds](/articles/AI_fusion/04_evaluation/02_quality_criteria.md).
7. Click **Upload Artifacts**. This stores the conversation's session as the case's baseline and, with **Save with Snap**, captures the data snapshot. **Edit** and **Run Evaluation** become available only after the first upload.

The suite file is edited like any other document in Studio: save it with **Ctrl+S**. Studio asks you to save or discard unsaved changes when you switch to another case.



## Method 2: Save a Preview Conversation as a Test Case

Use this method when you find an interesting conversation while testing the agent in Preview — a good answer you want to protect, or a bad one you want to fix and keep fixed.

1. In the agent's Preview chat, hold the conversation. See [Preview and Trace](/articles/AI_fusion/03_studio/03_preview_and_trace.md).
2. Click **Save as test case**.
3. In the dialog, enter a **Test case name**, choose an existing **Suite** or type a new suite name (a new suite file is created), and optionally add a **Description**.
4. Select **Save with Snap** to capture the business data the conversation used.
5. Click **Save**. The case is added with the default dimensions and thresholds of the agent, and the agent's answers become the expected answers.

Open the new case from the AI Explorer to review the answers and thresholds before you rely on it.



## Method 3: Import a Production Conversation

Conversations that real users had — especially those with negative feedback or unexpected behavior — make the most valuable test cases. You export them from Observation and import them into Studio Preview, then save them as test cases. See [Data Snapshots and Production Import](/articles/AI_fusion/04_evaluation/04_snapshots_and_production_import.md).



## Method 4: Generate Test Cases with Studio AI

The Agent Builder in Studio AI can build a suite for you from a list of business questions. Unlike the AI-assisted generation of AI Fusion 2.x, it does not invent answers: it runs **every question through the agent** and saves the agent's real answer as the expected answer.

- It asks you for the business questions (or proposes them from the agent's domains) and for the customer IDs (or other instance IDs) to use. It never invents IDs.
- It groups cases into one suite per business domain and gives each case a unique name.
- It can build **multi-turn** cases. Studio AI plays the customer, decides each follow-up from the agent's actual answer, and stops when the goal is met or a turn limit is reached (10 user/agent pairs by default). You can ask for a specific persona (for example, an impatient customer) to set the tone of the questions.
- Every generated case is saved with a snapshot and with the agent's default dimensions and thresholds. If the agent asks a clarifying question, Studio AI answers it and continues the conversation rather than saving the question as an answer.

For an entity-scoped agent, the agent needs a working `step_start` hook that loads the entity with the `GetWithSnap` actor; otherwise the agent has no customer data and the snapshots are empty. The Agent Builder checks this and offers to fix the hook first.

See [AI-Assisted Development](/articles/AI_fusion/03_studio/04_ai_assisted_development.md) for how to start the Agent Builder.

Review generated cases like any other: an answer captured from the agent is a baseline, not automatically a correct answer.



## Editing Expected Answers

The assistant answers in a case's transcript are what the judge compares new answers with. When a recorded answer is wrong or incomplete, correct it so the case describes what the agent *should* say.

1. Open the case in the Eval Editor (the case must already be uploaded).
2. Click **Edit**. The button changes to **Editing** and an edit banner appears.
3. Hover any question or answer:
   - Click the pencil icon to change its text, then **Save**.
   - Click the trash icon to delete the question/answer pair.
4. Click **Upload Artifacts** to store the edited case. Studio gives the edited case a new session ID with its own copy of the snapshots.
5. Save the suite.

Editing the user questions changes what is replayed; editing the assistant answers changes what is expected. Write expected answers that describe the required content, not exact wording — see [Evaluation Best Practices](/articles/AI_fusion/04_evaluation/05_best_practices.md).



## Disabling a Case

Toggle **Disabled** in the Eval Editor top bar (or use **Enable / Disable** in the AI Explorer) to keep a case in the suite without running it, for example while a known issue is being fixed. Disabled cases are skipped by suite runs, and **Run Evaluation** is not available for them.



## Advanced: Pinning the Current Date

Agents that answer relative-date questions ("my bill from last month") get today's date from the `current_datetime` tool. When a case replays a snapshot captured months ago, today's date no longer matches the data. To pin the date for a case, add a `now` key to the case's `initParams`, for example `{"customerId":"1","now":"2025-06-01T00:00:00Z"}` (a date-only value means midnight UTC).

`now` is honored only in evaluation runs. The interactive agent endpoints reject it, so you cannot enter it in the **New chat** dialog; add it to the `initParams` cell of the suite CSV and deploy the LU.

**Next article:** [Quality Criteria and Thresholds](/articles/AI_fusion/04_evaluation/02_quality_criteria.md)
