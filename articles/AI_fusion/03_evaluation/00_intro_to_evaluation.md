# AI Fusion Evaluation Framework Introduction

The AI Fusion Evaluation Framework is an automated quality assurance system for your AI agents. It lets you define what "good" looks like for your agent's responses, run tests against real customer data, and get scored, explainable results, all through a web interface that requires no coding or technical skills.

This framework is built for anyone responsible for AI agent quality, including QA teams, business analysts, customer service managers, domain experts, and product managers who need measurable, business-aligned evaluation of agent performance.



## Why AI Agents Need a Different Kind of Testing

Traditional software gives the same output every time for the same input. AI agents don't. They generate natural-language responses that can vary in wording, structure, and completeness,  even when answering the same question twice.

**Manual spot-checking doesn't scale.** A person can review a handful of conversations a day. Reviewers are inconsistent. There's no systematic record of what was checked or against what standard.

**Standard automation tests miss the point.** Traditional tests check for exact string matches. But a balance inquiry might return "Your balance is $1,234.56" or "You currently have $1,234.56 in your checking account" - both correct, both different. Rule-based checks can't evaluate whether a response is complete, well-organized, or actually answers what was asked.

**What's needed** is a system that understands the *meaning* of a response, scores quality across multiple dimensions, explains why a score was given, and runs consistently at scale. That is what this framework provides.



## Objectives

1. **Ensure quality before customers see it** - catch accuracy problems, incomplete answers, and tone issues in a controlled environment rather than in production.

2. **Measure quality across multiple dimensions** - a response can be correct but confusing, or clearly written but off-topic. The framework scores Accuracy, Relevance, Clarity, and Politeness independently, plus any custom criteria you define.

3. **Provide explainable, actionable results** - every score comes with a written explanation: "The response correctly identified the account but omitted the balance amount." This makes it clear what to fix.

4. **Enable continuous monitoring** - run the same tests on a schedule, track scores over time, and spot regressions early.

5. **Make testing accessible to business users** - the entire workflow happens through a web interface. Domain experts can create and manage tests without writing code.



## How It Works

### Step 1: Define test conversations

Create test cases with questions a customer would ask, expected answers describing what a good response should include (the **ground truth**), and quality criteria with minimum score thresholds. Tests use real customer data for realistic scenarios.

### Step 2: Execute the tests

The framework sends each question to your AI agent and collects its responses, exactly as if a real customer were chatting. By this, you can test the entire application end-to-end.

### Step 3: Evaluate with an AI judge

A separate AI evaluator reviews each response against the expected answer and scores it on every quality dimension you selected - a numeric score (1-5) plus a written explanation for each. This is the **LLM-as-a-Judge** approach: more nuanced than rule-based checking because the evaluator understands meaning, not just keywords.

### Step 4: Review results and act

Results appear in the Pipeline web interface app,with pass/fail status, per-criterion score breakdowns, color-coded conversation views, and detailed explanations for every score.



## Terminology

| Concept              | Definition                                                   |
| -------------------- | ------------------------------------------------------------ |
| **Test Case**        | A conversation scenario with questions, expected answers, and quality criteria |
| **Test Suite**       | A named collection of related test cases                     |
| **Ground Truth**     | The expected answers that define what a correct response looks like |
| **Quality Criteria** | The dimensions used to score responses (Accuracy, Relevance, Clarity, Politeness, or custom) |
| **Threshold**        | The minimum acceptable score for a criterion, enforced as MIN (per-response) or AVERAGE (across conversation) |
| **Evaluator**        | The AI judge that scores agent responses against expected answers |

