# Capabilities Overview



## Test Creation

* **Create tests through a chat experience** - Creating a test feels like having a conversation - you interact with the agent through a chat interface similar your customers use, and save the result as a test case.

* **Manual and AI-assisted** - Create tests by writing your own questions and expected answers, or let the AI Copilot generate realistic multi-turn conversations automatically. The Copilot can run a full conversation on autopilot, simulate specific customer personas (confused, tech-savvy, frustrated), and focus on topics you choose (billing, loans, account inquiries).

* **Multi-turn conversations** - Tests support multiple question-answer turns, validating that the agent maintains context, handles follow-ups correctly, and provides consistent information across the conversation.



## Quality Scoring

* **Multi-dimensional evaluation.** Every response is scored independently on each quality dimension:

<table>
<tbody>
<tr>
<td><strong>Criterion</strong></td>
<td><strong>What it measures</strong></td>
</tr>
<tr>
<td><strong>Accuracy</strong></td>
<td>Is the information factually correct and complete?</td>
</tr>
<tr>
<td><strong>Relevance</strong></td>
<td>Does the response answer what was asked?</td>
</tr>
<tr>
<td><strong>Clarity</strong></td>
<td>Is it well-organized and easy to understand?</td>
</tr>
<tr>
<td><strong>Politeness</strong></td>
<td>Is the tone professional and appropriate?</td>
</tr>
</tbody>
</table>

* **Custom criteria** - Define additional dimensions for your domain, like Compliance, Security Awareness, HIPAA adherence, Empathy, or anything your business requires.

* **Configurable thresholds** - For each criterion, set a minimum acceptable score (0-5 scale) and choose how it's enforced:

  - **MIN** - every single response must meet this score (use for non-negotiable requirements like accuracy)

  - **AVERAGE** - the average across the conversation must meet this value (use for stylistic qualities like clarity)


* **Organization-level defaults.** Set default thresholds and dimensions as a template for all new tests, so creators don't configure criteria from scratch each time. Defaults can be customized per test case when needed.

* **Explainable scoring.** Every score includes a written rationale - not just a number, but *why* the evaluator gave that score and what was missing or incorrect. This tells you exactly what to fix.



## Test Management

* **Reusable and iterative** - Saved tests are not static. Load any test case, examine it as a live chat, edit questions or expected answers, continue the conversation with additional turns, or duplicate it as a starting point for a new test.

* **Organized into suites** - Group tests by business domain, test type, or customer journey. Enable or disable entire suites or individual tests within a suite.

* **Actions** - Duplicate, export, move, enable, or disable individual test cases.

* **Data snapshots** - Optionally freeze the customer data a test was created against, so the test produces consistent results regardless of environment changes. Share a test with a colleague and both get the same results.

* **Version-controlled and shareable** - Test suites are tracked in Git as part of the project, giving you change history and team collaboration. Suites can also be exported as ZIP archives and imported into other environments.



## Test Execution

* **Three modes:**

<table>
<tbody>
<tr>
<td><strong>Mode</strong></td>
<td><strong>How it works</strong></td>
<td><strong>Best for</strong></td>
</tr>
<tr>
<td><strong>Interactive</strong></td>
<td>Run a single test on demand, watch results in real time</td>
<td>Debugging, validating new tests</td>
</tr>
<tr>
<td><strong>Batch</strong></td>
<td>Execute all enabled tests across all suites via pipeline</td>
<td>Regression testing, pre-deployment</td>
</tr>
<tr>
<td><strong>Scheduled</strong></td>
<td>Automated execution on a recurring schedule (nightly, weekly)</td>
<td>Continuous monitoring, CI/CD</td>
</tr>
</tbody>
</table>

* **Status tracking at all levels.** Monitor pass/fail status across suites and individual test cases.



## Results and Analysis

**Deep-dive view** - Drill down from suite-level results to individual conversations, then to per-turn scores broken out by dimension and threshold, with the evaluator's written verdicts.

**Execution comparison over time** - Compare runs side by side — after agent changes, prompt updates, or model upgrades — down to the individual turn and criterion level. Verify whether a change improved quality or introduced regressions.

**Export** - Download results for analysis by developers, and for reporting and compliance.



## Production Integration

**Import production conversations** - Bring real conversations from production into the evaluation workspace. Evaluate them against quality criteria, and optionally save them as new test cases. This bridges the gap between production monitoring and design-time testing.

