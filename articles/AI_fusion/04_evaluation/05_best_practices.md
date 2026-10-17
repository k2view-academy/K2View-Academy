# Evaluation Best Practices

A test suite is only as useful as the scenarios and expectations in it. This article collects practical guidance for writing test cases that catch real problems, setting thresholds you can trust, and keeping suites healthy as the agent evolves.



## Start Small, Grow Deliberately

- **Begin with 5–10 high-value cases** covering the questions users ask most and the ones where a wrong answer costs the most. Add cases as you learn, not all at once.
- **Run them, then calibrate.** Look at the actual scores before tightening thresholds. If the agent consistently scores 3.8 on accuracy, either improve the agent or temporarily lower the threshold and raise it gradually.
- **Add a case for every bug you fix.** A production conversation that exposed a problem, saved as a test case with corrected expected answers, is the cheapest regression protection there is. See [Data Snapshots and Production Import](/articles/AI_fusion/04_evaluation/04_snapshots_and_production_import.md).



## Writing Good Test Cases

### Use Real User Language

Users do not talk like database queries. Prefer "How much money do I have in all my accounts?" over "Aggregate account balance summation". Cover the variations real users bring:

- Casual, formal and vague phrasings ("What's my balance?", "Could you tell me my account balance, please?", "How am I doing?").
- Typos and non-native grammar ("I want know how many money in account?").
- Indirect questions ("Do I owe anything on my credit card?" instead of "What's my credit card balance?").

### Test Complete Interactions

Single-turn cases are fine for simple lookups and smoke tests. Multi-turn cases are better for realistic scenarios, because they also test context:

```text
User:  What's my checking account balance?
Agent: (exact balance with account number)
User:  And my savings?
Agent: (understands "my savings" in context and gives the savings balance)
User:  Which one has more money?
Agent: (compares the two and names the higher one)
```

Follow-ups with pronouns ("which one", "the second one") are where agents most often lose context.

### Write Specific Expected Answers — Not Exact Wording

The judge rates the new answer against the expected answer, so the expected answer defines what "correct" means.

- **Too vague:** "The agent should provide balance information." — anything passes.
- **Too strict:** "Your checking account number 1234 has a balance of $1,234.56 as of December 3rd, 2024." — written as if wording mattered; harmless rephrasings may lose points.
- **Right:** the required facts and elements — "States the checking balance ($1,234.56), identifies the account by name or last four digits, and gives the as-of date."

Remember that the judge treats an expected **refusal** as authoritative: if the expected answer declines to share some data, an answer that shares it is rated inaccurate. Use this to test guardrails.

### One Journey per Case

A case that checks balances, credit score, loan status and mortgage rates at once tells you little when it fails. Keep each case to one user journey; several turns are fine when they build on each other ("check balances → compare → transfer"). As a guideline: 1–2 turns for simple lookups, 3–5 for moderate scenarios, rarely more than 10.

### Cover the Unhappy Paths

Include cases for:

- **Missing data** — "What's my retirement account balance?" for a customer who has none.
- **Ambiguity** — "How much do I owe?" when the customer has a card, a loan and a mortgage. Expect a clarifying question or a complete list.
- **Unusual values** — a negative (overdrawn) balance should be called out, not just shown.
- **Out-of-scope questions** — "What's the weather in New York?" should be politely redirected.
- **Forbidden requests** — requests for data the agent must not share.
- **Different personas** — confused, expert, frustrated and non-native users.

### Name and Describe Cases Clearly

"Test 1" and "Banking test" help nobody six months later. Use names like "Balances - compare accounts (cust 1)" and descriptions like "Verify that the agent lists all account balances and identifies the highest one when asked to compare."



## Setting Thresholds

- **Use Minimum for what must never fail** — accuracy, compliance, data protection. One bad answer should fail the case.
- **Use Average for style** — clarity and politeness, where some variation is acceptable.
- **Avoid perfection thresholds.** Setting every dimension to Minimum 5 produces noise, not quality.
- **Watch the performance dimensions.** Token and response-time limits catch a prompt change that silently doubles cost or latency. If you change the model or the agent's design on purpose, expect deviations, and record the affected cases again so they get a new baseline.

Recommended starting points (the threshold sliders in the Eval Editor move in whole points for quality and in 5% steps for performance):

<table>
<tbody>
<tr>
<td><strong>Dimension</strong></td>
<td><strong>Typical</strong></td>
<td><strong>Critical systems</strong></td>
</tr>
<tr>
<td>Accuracy</td>
<td>Minimum 4</td>
<td>Minimum 4, plus a custom guardrail dimension (for example, Data protection) at Minimum 5</td>
</tr>
<tr>
<td>Relevance</td>
<td>Minimum 4</td>
<td>Minimum 4</td>
</tr>
<tr>
<td>Clarity</td>
<td>Average 4</td>
<td>Average 4</td>
</tr>
<tr>
<td>Politeness</td>
<td>Average 3–4</td>
<td>Average 4</td>
</tr>
<tr>
<td>Token Consumption</td>
<td>MAX +20%</td>
<td>MAX +10%</td>
</tr>
<tr>
<td>Response Time</td>
<td>MAX +15%</td>
<td>MAX +10%</td>
</tr>
</tbody>
</table>

If your organization needs different defaults for new cases, define them once in the `evaluationDefaults` MTable rather than adjusting every case. See [Quality Criteria and Thresholds](/articles/AI_fusion/04_evaluation/02_quality_criteria.md).



## Making Results Repeatable

- **Save cases with a snapshot** whenever the agent reads business data, and make sure the agent loads its entity with `GetWithSnap`.
- **Pin the date** with the `now` initialization parameter for agents that answer relative-date questions on snapshot data.
- **Expect some variation.** Two runs of the same case can differ slightly. A case that flips between pass and fail is usually right at a threshold, or its expected answer is ambiguous — make the expectation more specific or adjust the threshold, and only then look for an agent problem.



## Organizing Suites

Each agent has its own suites. Common ways to split them:

- **By business domain** — Billing, Usage, Plans. This mirrors how agents are usually split into sub-agents and schema skills, and it is how Studio AI groups generated cases.
- **By purpose** — a small smoke suite run after every change, and fuller regression suites run before a release.
- **By journey** — Onboarding, Bill dispute, Plan change.

Name suites by content (`Billing`, `Smoke`), not by owner or date. Disable cases instead of deleting them when they fail for a known reason under investigation, and re-enable them when the fix lands. Rename and move cases with the AI Explorer commands rather than by editing files, so that Studio can keep the suite consistent.



## Running Regularly

<table>
<tbody>
<tr>
<td><strong>When</strong></td>
<td><strong>What to run</strong></td>
</tr>
<tr>
<td>After creating or editing a case</td>
<td>That case, to confirm it is valid.</td>
</tr>
<tr>
<td>During development</td>
<td>The suite of the domain you are changing, after each meaningful change.</td>
</tr>
<tr>
<td>Before a release</td>
<td>All of the agent's suites.</td>
</tr>
<tr>
<td>After a model or LLM interface change</td>
<td>All suites — model changes affect everything.</td>
</tr>
<tr>
<td>On a schedule</td>
<td>All suites, triggered from your CI/CD or job scheduler through the evaluation web service. See <a href="/articles/AI_fusion/04_evaluation/03_running_and_results.md">Running Evaluations and Reading Results</a>.</td>
</tr>
</tbody>
</table>



## Acting on Results

- **Read the verdicts, not just the scores.** The explanation tells you what was wrong.
- **Look for patterns.** One failure can be noise; the same dimension failing across several cases points to a real issue.
- **Decide honestly whether to fix the agent or the test.** Ask: would this answer be good enough for a real customer? If yes, fix the expected answer or threshold. If no, fix the agent.
- **Fix the most serious failures first.** Accuracy and guardrail failures before clarity and politeness.
- **Re-run the whole suite after a fix** to make sure it did not break something else.
- **Track trends over time** — pass rate and average score per dimension per release. A slow downward trend is easy to miss in individual runs.



## Maintaining Suites

- **Review cases when the business changes** — new products, new policies, new data — and update expected answers that are no longer true.
- **Remove obsolete cases** that test features the agent no longer supports.
- **Refresh old snapshots** when the data model of the LU changes significantly, by recording the case again.
- **Keep suites in source control** with the rest of the LU, and review suite changes like code.

This is the last article of the Evaluation section. Continue with [Observation](/articles/AI_fusion/05_observation/README.md) to monitor the agent's quality in production.
