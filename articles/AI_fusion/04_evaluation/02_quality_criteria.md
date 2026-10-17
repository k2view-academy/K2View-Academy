# Quality Criteria and Thresholds

Every test case is measured on a set of **dimensions**, and every dimension has one or more **thresholds**. A case passes only when all of its thresholds are met. This article explains the two kinds of dimensions, how thresholds are evaluated, where the defaults come from, and how the LLM judge scores answers.



## Two Kinds of Dimensions

<table>
<tbody>
<tr>
<td><strong>Kind</strong></td>
<td><strong>Measured by</strong></td>
<td><strong>Value</strong></td>
<td><strong>Threshold directions</strong></td>
</tr>
<tr>
<td>Quality</td>
<td>The LLM judge, per answer</td>
<td>A score out of 5</td>
<td><strong>Minimum</strong> (MIN) or <strong>Average</strong> (AVERAGE) — the score must be at least the threshold</td>
</tr>
<tr>
<td>Performance</td>
<td>Comparison with the case's baseline session</td>
<td>Percent deviation from the baseline</td>
<td><strong>Maximum</strong> (MAX) or <strong>Average</strong> (AVERAGE) — the deviation must not exceed the threshold</td>
</tr>
</tbody>
</table>



## Quality Dimensions

AI Fusion provides four built-in quality dimensions. You can use any of them, remove them, or add your own.

* **Accuracy** — is the information correct and complete compared with the expected answer? Inaccurate answers damage trust and can lead to wrong payments, missed deadlines or wrong decisions.

  Example: "What's my checking account balance?"

<table>
<tbody>
<tr>
<td></td>
<td><strong>Response</strong></td>
<td><strong>Score</strong></td>
</tr>
<tr>
<td><strong>High</strong></td>
<td>"Your checking account (ending in 1234) has a balance of $1,234.56 as of today."</td>
<td>5</td>
</tr>
<tr>
<td><strong>Low</strong></td>
<td>"Your checking account has about $1,200." (wrong amount, no details)</td>
<td>2</td>
</tr>
</tbody>
</table>

* **Relevance** — does the response address the user's question and intent?

<table>
<tbody>
<tr>
<td></td>
<td><strong>Response</strong></td>
<td><strong>Score</strong></td>
</tr>
<tr>
<td><strong>High</strong></td>
<td>"Your checking account (ending in 1234) has a balance of $1,234.56 as of today."</td>
<td>5</td>
</tr>
<tr>
<td><strong>Low</strong></td>
<td>"We offer many types of accounts including checking, savings, and CDs..."</td>
<td>1</td>
</tr>
</tbody>
</table>

* **Clarity** — is the response easy to understand and well organized? A list of balances with one account per line scores higher than the same numbers run together in one long sentence.

* **Politeness** — is the tone respectful, courteous and professional? This usually matters more for customer-facing agents than for agents used by internal staff.

<table>
<tbody>
<tr>
<td></td>
<td><strong>Response</strong></td>
<td><strong>Score</strong></td>
</tr>
<tr>
<td><strong>High</strong></td>
<td>"I'd be happy to help! Your checking account currently has $1,234.56. Is there anything else I can assist you with?"</td>
<td>5</td>
</tr>
<tr>
<td><strong>Low</strong></td>
<td>"Your balance is $1234.56. Next question."</td>
<td>1</td>
</tr>
</tbody>
</table>

### Custom Quality Dimensions

Add your own dimensions for what matters in your business, for example:

- **Compliance** — does the answer include the required regulatory disclosures?
- **Data protection** — does the agent avoid exposing full account numbers or other sensitive data?
- **Problem resolution** — does the agent actually solve the customer's issue?
- **Empathy** — does the agent acknowledge the customer's frustration?

A custom dimension is defined by its name and a **description for the evaluator** — the text the judge reads to understand what to rate. Write the description as a precise rating instruction; the judge is only as consistent as the description is clear.



## Performance Dimensions

Performance dimensions catch regressions in cost and speed, which quality scores do not show. Both compare the new run with the **baseline session** the case was saved from.

<table>
<tbody>
<tr>
<td><strong>Dimension</strong></td>
<td><strong>Name in the suite file</strong></td>
<td><strong>What is compared</strong></td>
</tr>
<tr>
<td><strong>Token Consumption</strong></td>
<td><code>Tokens</code></td>
<td>Tokens used by the agent's LLM calls.</td>
</tr>
<tr>
<td><strong>Response Time</strong></td>
<td><code>ConversationTime</code></td>
<td>Time the agent took to answer.</td>
</tr>
</tbody>
</table>

Each is reported as a percent deviation from the baseline (positive means more tokens or slower than the baseline):

- **MAX** — the worst single answer: the highest per-turn deviation in the conversation. Use it to catch one turn that suddenly became expensive or slow.
- **AVERAGE** — the whole conversation: total of the run compared with total of the baseline.

A threshold of 20 means "fail if the deviation is more than +20%". When there is no baseline data to compare with, the deviation is reported as 0 and the check passes.



## Thresholds

### MIN and AVERAGE for Quality Dimensions

- **Minimum** — the lowest score of any answer in the conversation must be at least the threshold. Use it for non-negotiable criteria such as accuracy, compliance or data protection.

  > Accuracy, Minimum 4 — every answer must score at least 4.

- **Average** — the mean score across all answers must be at least the threshold. Use it for stylistic criteria where some variation is acceptable, such as clarity or politeness.

  > Clarity, Average 4 — one answer can score 3 if another scores 5.

A dimension can have both a Minimum and an Average threshold.

### Default Threshold

The **Default Threshold** applies to every quality dimension that does not have its own threshold. With the default set to Minimum 4, every judged dimension must score at least 4 in every answer unless you customize it.

### Configuring a Case

<!-- TODO screenshot: Test Evaluation Configuration panel with Default Threshold, Dimensions list, + Add menu and a customized dimension -->

In the Eval Editor, open the **Definition** tab. The **Test Evaluation Configuration** section shows:

- **Default Threshold** — toggle it on or off and set its direction and value.
- **Dimensions** — the dimensions the judge rates for this case. Click **+ Add** to add **Accuracy**, **Relevance**, **Clarity**, **Politeness** or a **Custom dimension…**. Click the trash icon to remove one.
- **Customize** — turn it on for a dimension to give it its own threshold. For a quality dimension you can also edit its **Name** and **Description for evaluator**. Use **Add Threshold** to add a second threshold (for example, both Minimum and Average).

Quality thresholds are set on a 0–5 slider; performance thresholds on a 0–100% slider. A quality dimension whose **Customize** toggle is off is still rated, and is checked against the Default Threshold. Performance dimensions are always checked against their own thresholds.



## Default Dimensions and Thresholds

New test cases are seeded from a named set of defaults, stored in the `evaluationDefaults` MTable of the AI Fusion project. Each row has a `name`, a `threshold` JSON array, a `dimension` JSON map of quality dimensions, and a `performanceDimension` JSON map of performance dimensions. The default row, `evalDefaults`, contains:

<table>
<tbody>
<tr>
<td><strong>Setting</strong></td>
<td><strong>Default</strong></td>
</tr>
<tr>
<td>Default Threshold</td>
<td>Minimum 4.0</td>
</tr>
<tr>
<td>Quality dimensions</td>
<td>Accuracy, Relevance, Clarity, Politeness</td>
</tr>
<tr>
<td>Token Consumption (<code>Tokens</code>)</td>
<td>MAX 20%</td>
</tr>
<tr>
<td>Response Time (<code>ConversationTime</code>)</td>
<td>MAX 15%</td>
</tr>
</tbody>
</table>

To use different defaults for an agent, add a row to `evaluationDefaults` and set the agent's `settings.eval_defaults_name` in `agent.yaml` to the row's name (see [agent.yaml Reference](/articles/AI_fusion/02_agents/02_agent_yaml_reference.md)). The defaults are applied when a case is created; changing them later does not change existing cases. Studio reads the defaults from the `defaults` evaluation web service (`GET /api/defaults`).



## How the Judge Scores Answers

For each answer, the judge receives the user's question, the expected answer, the agent's new answer, and the name and description of every quality dimension. It returns a score out of 5 and a short explanation for each dimension. Answers are rated in batches of three turns per judge call.

The judge follows these rules:

1. **Rate against both the question and the expected answer**, so that an answer is judged on whether it addresses the user's intent, not only on its similarity to the expected text.
2. **Keep the criteria separate.** An issue that affects accuracy (for example, missing data) should not also lower relevance or clarity.
3. **Penalize an issue once**, on the criterion it affects most directly.
4. **Explain each score in one sentence**, with a suggestion for improvement where needed.
5. **The expected action is authoritative.** If the expected answer refuses to share certain data, an answer that shares it is rated inaccurate, even if the data is correct.

The judge uses the project's default LLM interface. See [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md).

**Next article:** [Running Evaluations and Reading Results](/articles/AI_fusion/04_evaluation/03_running_and_results.md)
