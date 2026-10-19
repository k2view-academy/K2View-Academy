# Running Evaluations and Reading Results

An evaluation run replays test cases through the current version of the agent, has the judge score the new answers, and checks the scores against the thresholds. You can run one case, a suite, or all of an agent's suites — interactively from Studio, or from any automation through the evaluation web service.



## Prerequisites

- The **Pipeline extension** is installed and deployed. Runs are executed by the `aifusion.AgentEvaluator` pipeline. See [Installation and Setup](/articles/AI_fusion/04_installation_and_setup.md).
- The `aifusion.AgentEvaluator` pipeline is not disabled or paused in the Pipeline application.
- The agent's LU, including its `Evals` folder, is deployed. Studio deploys it automatically before each run; in other environments, deploy the LU as part of your release.
- Each case to run has been uploaded (it has a baseline session) and is enabled.



## Running from Studio

<table>
<tbody>
<tr>
<td><strong>To run</strong></td>
<td><strong>Do this</strong></td>
</tr>
<tr>
<td>One case, and watch the results</td>
<td>Open the case in the Eval Editor and click <strong>Run Evaluation</strong>.</td>
</tr>
<tr>
<td>One case, in the background</td>
<td>In the AI Explorer, right-click the case and select <strong>Run Evaluation</strong>.</td>
</tr>
<tr>
<td>A whole suite</td>
<td>In the AI Explorer, right-click the suite and select <strong>Run Evaluation</strong>. Every enabled case in the suite runs.</td>
</tr>
</tbody>
</table>

While a run is in progress, the case's bullet in the AI Explorer spins and the editor button shows **Running…**. When the run ends, the bullet shows the result:

<table>
<tbody>
<tr>
<td><strong>Bullet</strong></td>
<td><strong>Meaning</strong></td>
</tr>
<tr>
<td>Blue, spinning</td>
<td>Running.</td>
</tr>
<tr>
<td>Green</td>
<td>Passed — all thresholds were met.</td>
</tr>
<tr>
<td>Orange</td>
<td>Completed, but one or more dimensions missed their threshold.</td>
</tr>
<tr>
<td>Red</td>
<td>The run failed technically (for example, the agent or the judge returned an error, or the run was stopped) and was not scored.</td>
</tr>
<tr>
<td>Gray, crossed</td>
<td>The case is disabled.</td>
</tr>
</tbody>
</table>

Studio stops waiting for a case after about five minutes without a result and marks it failed.



## What Happens During a Run

1. **Prepare.** The case is read from the deployed suite. AI Fusion creates a new evaluation session and an isolated copy of the case's snapshots, so the run cannot change the baseline.
2. **Replay.** Each `user` turn of the transcript is sent to the agent, in order and in the same conversation, with the case's initialization parameters. If the case was saved with a snapshot, the agent reads the snapshot data instead of live data. If the case pins the date with `now`, the agent's `current_datetime` tool returns that date.
3. **Judge.** The judge compares each new answer with the expected answer and scores every quality dimension, with an explanation. See [Quality Criteria and Thresholds](/articles/AI_fusion/04_evaluation/02_quality_criteria.md).
4. **Aggregate.** AI Fusion computes the minimum and average score of each quality dimension, and the token and time deviations from the baseline session.
5. **Decide.** The aggregates are checked against the case's thresholds. The case passes only if none is breached.
6. **Store.** The run's conversation and ratings are stored in the insights DB (`evaluation_chat`, `evaluation_details`), and the run status is recorded by the pipeline (`case_execution`).



## Reading the Results

<!-- TODO screenshot: Eval Editor Evaluation tab with threshold pills, overall score, score chips under an answer and an open verdict popover -->

When a run completes, the Eval Editor switches to the **Evaluation** tab. The tab is available once the case has at least one run.

- **Threshold pills** — one per dimension, with the aggregate value for each configured direction, for example **Accuracy MIN: 4.00 AVERAGE: 4.50** or **Token Consumption MAX: +12%**. Green means better than the threshold, amber exactly at the threshold, red a breach.
- **Overall score** — the average of all quality scores in the run.
- **Run stepper** — the run date and time, with **Previous run** and **Next run** arrows to browse the case's earlier and later runs.
- **Conversation** — the replayed conversation with the agent's *new* answers. Under each answer:
  - A **score chip** per quality dimension. Click a chip to see the **Expected answer** and the judge's **Verdict** (the explanation for the score).
  - **Performance badges** — the token and time deviation of that answer from the baseline. Hover a badge to see the actual and baseline values and the limit.

The chips above the conversation show the case's initialization parameters and the run's own session ID (different from the case's baseline session).

### Investigating a Failure

1. Find the red pill to see which dimension and which direction failed.
2. Find the answers with red chips for that dimension and read the verdict next to the expected answer.
3. Decide whether the agent or the test is wrong:

<table>
<tbody>
<tr>
<td><strong>Cause</strong></td>
<td><strong>Fix</strong></td>
</tr>
<tr>
<td>The agent lacks data, or queried the wrong data</td>
<td>Fix the hook, tool, skill or schema description. Reproduce the question in Preview and check its trace.</td>
</tr>
<tr>
<td>The agent misunderstood the question</td>
<td>Improve the system prompt, skills or examples.</td>
</tr>
<tr>
<td>The expected answer is wrong, outdated or too strict</td>
<td>Edit the expected answer (<strong>Edit</strong>, then <strong>Upload Artifacts</strong>).</td>
</tr>
<tr>
<td>The threshold is unrealistic</td>
<td>Adjust it under <strong>Test Evaluation Configuration</strong>.</td>
</tr>
<tr>
<td>Token or time deviation after an intended change (new model, richer answers)</td>
<td>Relax the performance threshold, or record the case again so it gets a new baseline session.</td>
</tr>
</tbody>
</table>

4. Run the case again, then run the rest of the suite to make sure the fix did not break anything else.

Click **Analyze** to ask Studio AI to analyze the failed run and suggest a fix. See [AI-Assisted Development](/articles/AI_fusion/03_studio/04_ai_assisted_development.md).



## Running from CI/CD or Another Job

Evaluation runs are started and monitored through the evaluation web services, so you can trigger them from a CI/CD pipeline, a Fabric job, or any scheduler after a deployment. AI Fusion does not schedule evaluation runs by itself.

### Start a Run

```text
POST /api/agent/{luName}/{agentName}/evaluate
```

<table>
<tbody>
<tr>
<td><strong>Parameter</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>luName</code></td>
<td>The LU that contains the agent and its suites.</td>
</tr>
<tr>
<td><code>agentName</code></td>
<td>The agent to evaluate.</td>
</tr>
<tr>
<td><code>suiteName</code> (optional)</td>
<td>Run only this suite. Omit it to run all of the agent's suites.</td>
</tr>
<tr>
<td><code>caseId</code> (optional)</td>
<td>Run only this case. Requires <code>suiteName</code>.</td>
</tr>
</tbody>
</table>

The response contains a `runId` that identifies this run:

```json
{
  "message": "Pipeline started successfully: aifusion.AgentEvaluator",
  "runId": "3f0c2a8e-..."
}
```

Each call gets its own `runId`, so several runs can be in progress at the same time.

<table>
<tbody>
<tr>
<td><strong>HTTP status</strong></td>
<td><strong>Meaning</strong></td>
</tr>
<tr>
<td>400</td>
<td><code>caseId</code> was passed without <code>suiteName</code>.</td>
</tr>
<tr>
<td>409</td>
<td>The pipeline was not started: <code>aifusion.AgentEvaluator</code> was not found, is disabled, or is paused (resume it in the Pipeline application).</td>
</tr>
<tr>
<td>503</td>
<td>"Pipeline extension is not installed or not deployed".</td>
</tr>
</tbody>
</table>

### Poll the Status

```text
GET /api/agent/case/evaluation-status?luName=<LU>&agentName=<agent>&suiteName=<suite>&runId=<runId>
```

Add `id=<caseId>` for a single case; omit it to get a row for every case of the run. Each row carries `process_status` (`completed` when the case is done; `fail`, `stopped` or `unknown` for a technical failure), `evaluation_result` (`passed` or `failed`, once completed) and the run's `sessionid`.

### Get the Details

```text
GET /api/case/conversation?sessionid=<sessionid>
```

Returns the replayed conversation with the per-answer ratings, expected answers, explanations and performance figures, plus the aggregate ratings and the overall score — the same data the Evaluation tab shows.

**Next article:** [Data Snapshots and Production Import](/articles/AI_fusion/04_evaluation/04_snapshots_and_production_import.md)
