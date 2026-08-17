# AI-Assisted Test Generation

Building a comprehensive test suite manually is time-consuming, particularly when realistic multi-turn conversations must be crafted from scratch. The **AI Fusion Evaluation Framework** addresses this with built-in AI assistance that accelerates test creation without sacrificing quality. Rather than writing every question and expected answer by hand, QA engineers can instruct the platform to generate realistic customer conversations automatically — and then review, edit, and save them as reproducible test cases.

---

## Overview

Creating effective evaluation tests requires fluency in two domains simultaneously: the subject matter being tested (billing, account inquiries, service requests) and the realistic ways customers phrase questions. AI-assisted generation handles both. It uses the same LLM infrastructure that powers the agent under test to construct conversations that reflect how real users interact — including ambiguity, colloquial phrasing, and multi-step intent.

The result is a faster path from "we need test coverage for billing disputes" to a saved, executable test case. AI assistance also reduces the bias that arises when the person writing tests is the same person who built the agent: generated conversations surface unexpected phrasings and follow-up patterns that human authors often overlook.

---

## Three Assistance Modes

The framework offers three modes of AI assistance. Each serves a different working style and coverage goal.

### Copilot

**Copilot** mode operates alongside manual test creation. As you type and submit each question in the chat-based test builder, Copilot suggests what the next customer question might be, given the conversation so far. You remain in control of each turn — Copilot only proposes; you decide whether to accept, modify, or discard the suggestion.

Use Copilot when you want to author a test with a specific narrative in mind but need help sustaining conversational realism across multiple turns.

### Autopilot

**Autopilot** mode generates a complete conversation end-to-end without manual turn-by-turn input. You provide three configuration parameters — **Subject**, **Persona**, and **Max Steps** — and the platform constructs the entire dialogue: customer questions, agent responses, and the decision of when to conclude. The result is a ready-to-review conversation that can be saved immediately or edited before saving.

Use Autopilot when you need to rapidly expand test coverage across a subject area, or when you want to explore what question patterns emerge for a topic without pre-judging them.

### SDG Integration

**SDG (Synthetic Data Generation) integration** combines test generation with the creation of synthetic business entities. Rather than relying on existing customer records, the platform generates a fictional but structurally realistic customer — with accounts, transactions, and history — and then builds a test conversation grounded in that synthetic data.

Use SDG integration when suitable real customer records are unavailable, when privacy regulations prevent use of production data in test environments, or when you need deterministic data that will not change between test runs.

---

## Generating a Test with Autopilot

### Step 1: Open the Test Builder

Navigate to the **Evaluation** workspace and select **New Test**. The test builder opens in chat mode.

### Step 2: Switch to Auto-Generate Mode

In the test creation form, locate the generation mode toggle and select **Auto-generate** (Autopilot). The form expands to display the Autopilot configuration fields.

> **Note:** *Screenshot needed:* [The auto-generate configuration form with Subject, Persona, and Max Steps fields visible. The Application/Agent selector and IID field should also be visible. URL: `/evaluation/new-test`. Save as `images/08_autopilot_form.png`]

<img src="images/08_autopilot_form.png" alt="Autopilot configuration form" style="zoom:80%;" />

### Step 3: Configure the Generation Parameters

Complete the following fields:

| Field | Description | Example |
|---|---|---|
| **Application / Agent** | The agent to test | `customer-service-v2` |
| **Customer ID (IID)** | The customer record to use, or `auto` for SDG | `12345` or `auto` |
| **Subject** | The topic or scenario focus for the conversation | `billing discrepancy on last invoice` |
| **Persona** | The customer character the AI will simulate | `confused elderly customer` |
| **Max Steps** | Maximum number of conversation turns to generate | `6` |

The **Subject** and **Persona** fields are the primary levers for shaping generation quality. See [Configuring the Persona](#configuring-the-persona) and [Tips for Effective Generation](#tips-for-effective-generation) for guidance.

### Step 4: Review Generation in Progress

Click **Generate**. The platform begins constructing the conversation. An animation indicates that generation is underway. Depending on the number of steps and the complexity of data retrieval, this typically takes between 15 and 45 seconds.

> **Note:** *Screenshot needed:* [The test builder UI while generation is in progress, showing the animated indicator and any partial conversation turns that may already be visible. Save as `images/08_generation_progress.png`]

<img src="images/08_generation_progress.png" alt="Generation in progress" style="zoom:80%;" />

The platform executes the following sequence internally:

1. Checks whether a suitable business entity exists for the specified IID (or triggers SDG if `auto` is selected).
2. Generates a realistic customer question aligned with the subject and persona.
3. Sends the question to the configured agent and receives a response.
4. Evaluates whether to continue the conversation or conclude, based on natural stopping criteria.
5. Repeats until Max Steps is reached or the conversation reaches a natural end.

### Step 5: Review and Edit the Generated Conversation

Once generation completes, the full conversation is displayed in the test builder.

> **Note:** *Screenshot needed:* [The completed generated conversation in the test builder, showing several turns of customer questions and agent responses, with edit controls visible on each turn. Save as `images/08_generated_conversation.png`]

<img src="images/08_generated_conversation.png" alt="Generated conversation ready for review" style="zoom:80%;" />

Review each turn. You can:

- Edit any customer question to refine phrasing.
- Edit the **Ground Truth** (expected answer) for each agent response. See [Reviewing and Editing Generated Tests](#reviewing-and-editing-generated-tests).
- Delete individual turns that are not relevant.
- Add additional turns manually before saving.

### Step 6: Save the Test

When the conversation meets your quality bar, click **Save Test**. Provide a name and optionally assign the test to a suite. If you want to attach a data snapshot for reproducibility, do so at this point. See [Data Snapshots and Production Import](./09_data_snapshots_and_production_import.md) for details.

---

## Configuring the Persona

The **Persona** field instructs the AI on how to simulate the customer's communication style, vocabulary, emotional state, and level of technical familiarity. Persona selection directly influences the type of questions generated and the conversational dynamics.

| Persona Example | Resulting Behavior |
|---|---|
| `confused elderly customer` | Simple vocabulary, repeated clarifications, hesitation about technical terms |
| `tech-savvy millennial` | Precise language, abbreviations, higher tolerance for self-service steps |
| `frustrated customer` | Escalating tone, demands for explanation, skepticism of agent responses |
| `new account holder` | Introductory questions, unfamiliarity with product terminology |
| `business owner` | Focus on cost, efficiency, and multi-account management |

Personas do not change what the agent does — they change the conversational pressure the agent is evaluated against. A frustrated persona will surface whether the agent maintains a professional tone under adversarial phrasing. A confused persona will test whether the agent over-assumes prior knowledge.

You can define any persona in plain language. The more specific the description, the more consistent the generated questions will be. "A first-time customer who just received an unexpected charge and is anxious about it" produces more targeted results than "anxious customer."

---

## Using SDG Integration

**Synthetic Data Generation (SDG)** integration allows the platform to create a fictional but realistic business entity — a synthetic customer — and build a test conversation grounded in that entity's data.

### When to Use SDG

- Your test environment does not contain customer records that match the scenario you want to test.
- Privacy or compliance requirements prohibit using real customer identifiers in test cases.
- You need a stable, controlled dataset that will not change between test executions.
- You are building tests for a new product or data domain that has no production customers yet.

### SDG Workflow

1. Set the **Customer ID (IID)** field to `auto`.
2. Configure Subject, Persona, and Max Steps as usual.
3. Click **Generate**. The platform detects that no real IID is specified and invokes the SDG pipeline.
4. SDG generates a synthetic entity with plausible attributes (accounts, balances, transaction history) appropriate to the subject area.
5. The conversation is generated using the synthetic entity's data, so agent responses reference real-looking (but fictional) figures and dates.
6. The synthetic entity's data is captured in a **data snapshot (LUI Snap)** that is bundled with the test case on save, ensuring the same data is available in every subsequent execution.

> **Note:** The synthetic entity exists only within the test context. It is not written to production systems.

---

## Extending an Existing Test (Chat Expansion)

Once a test is saved, you can add more turns to it without recreating it from scratch. This is useful when you want to deepen coverage of a scenario — for example, following a billing inquiry with a dispute escalation — without generating an entirely new test.

To extend an existing test:

1. Open the test from the test library.
2. The conversation loads in the test builder in its saved state.
3. Continue the conversation by typing additional questions (manual mode) or by clicking **Continue Generating** (Autopilot mode).
4. New turns are appended to the existing conversation.
5. Save the test again. The extended version replaces the previous one.

Chat Expansion preserves the original data snapshot, so the extended conversation runs against the same frozen customer data.

---

## Reviewing and Editing Generated Tests

AI generation produces a first draft, not a final test. Reviewing the output before saving is essential.

### What to Check

- **Accuracy of agent responses** — did the agent retrieve the correct data and state it correctly?
- **Relevance of generated questions** — do the questions reflect the intended subject and persona?
- **Natural flow** — does each question logically follow from the previous agent response?
- **Ground truth completeness** — does the expected answer for each turn capture what a correct response must include?

### Editing Ground Truth

The **Ground Truth** is the expected answer against which the LLM-as-a-Judge evaluates agent responses during test execution. After generation, the ground truth for each turn is pre-populated with the agent's actual response. This is a starting point, not a definition of correctness.

Edit the ground truth to reflect what the agent *should* say, not just what it *did* say. If the agent's response was incorrect, set the ground truth to the correct answer so that subsequent executions detect the failure. If the agent's response was correct but incomplete, expand the ground truth to capture the full expectation.

### Common Adjustments

- Remove filler phrases from the ground truth that are stylistic rather than substantive.
- Specify numeric values explicitly when the agent should cite exact figures.
- Add boundary conditions — for example, note that the ground truth should only be satisfied if the agent references the correct account type.
- Delete turns where the agent's response was a system error or timeout rather than a meaningful answer.

---

## Tips for Effective Generation

**Be specific with Subject.** "Billing issues" generates broad conversations. "Why did my bill increase after changing from plan A to plan B last month" generates targeted ones that exercise specific data retrieval paths.

**Match Persona to scenario.** A frustrated persona on a routine balance inquiry adds noise. Reserve adversarial personas for scenarios where agent resilience under emotional pressure is genuinely important.

**Set realistic Max Steps.** Most customer interactions resolve in three to six turns. Setting Max Steps to 12 or higher often produces repetitive turns as the AI tries to sustain a conversation that has naturally concluded. Start at five to seven.

**Generate multiple variants.** Run Autopilot several times on the same subject and persona combination. Each generation will produce a different conversation, expanding your coverage without additional configuration effort.

**Review immediately.** The longer you wait between generation and review, the harder it is to evaluate whether the agent's responses were correct, since data may have changed. Review and save promptly after generation.

---

## Related Articles

- [Creating Your First Test](./02_creating_your_first_test.md)
- [Data Snapshots and Production Import](./09_data_snapshots_and_production_import.md)
- [Quality Criteria and Thresholds](./03_quality_criteria.md)
- [Best Practices and Maintenance](./07-best-practices.md)
