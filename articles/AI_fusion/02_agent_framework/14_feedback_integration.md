# Feedback Integration

AI agents deployed in production rarely arrive at a final, stable quality level from day one. Real users surface edge cases, ambiguous phrasings, and domain gaps that no pre-deployment test suite fully anticipates. **Feedback Integration** in the AI Fusion platform provides a structured mechanism for capturing, storing, and acting on user assessments of agent responses — creating a continuous feedback loop that connects production usage directly to quality improvement workflows.

---

## Overview

The feedback system allows users interacting with the AI agent to rate each assistant response as positive or negative, and to attach a free-text note explaining their assessment. This signal is stored persistently against each conversation turn and surfaces in the **Observation** module, where supervisors and QA engineers can review it in context.

Feedback serves two distinct purposes in the platform's quality lifecycle. In the short term, it provides an immediate signal for identifying problematic agent responses that require investigation. Over time, accumulated feedback is intended to drive model and agent improvement — providing a grounded, usage-based dataset that reflects what real users consider helpful or unhelpful.

---

## Providing Feedback in the Chat UI

Feedback controls are embedded directly in the chat interface so that users can rate responses at the moment of interaction, without switching context.

### Thumbs Up / Thumbs Down

Each assistant response in the chat UI displays a **thumbs up** and **thumbs down** button. Clicking either button records a positive or negative rating for that specific response turn.

> **Note:** *Screenshot needed:* [The chat UI showing an assistant response with thumbs up and thumbs down buttons visible below it, along with the note/comment icon. The buttons should be in their default (unselected) state. Save as `images/14_feedback_buttons.png`]

<img src="images/14_feedback_buttons.png" alt="Feedback buttons in the chat UI" style="zoom:80%;" />

A rating can be changed after submission by clicking the opposite button. The most recently submitted rating is the one stored against the turn.

### Adding a Note

Adjacent to the thumbs buttons is a **note** icon. Clicking it opens a text input where the user can describe why they rated the response as they did. Notes are optional but valuable — a thumbs-down with the note "mentioned the wrong account number" provides an immediately actionable signal, whereas a thumbs-down alone requires a reviewer to re-read the full conversation to understand the issue.

> **Note:** *Screenshot needed:* [The feedback note input dialog open in the chat UI, showing a text field where the user can type an explanation. A Submit or Save button should be visible. Save as `images/14_feedback_note_dialog.png`]

<img src="images/14_feedback_note_dialog.png" alt="Feedback note input dialog" style="zoom:80%;" />

### Supported Chat Interfaces

Feedback controls are available in the following contexts:

| Interface | Feedback Supported |
|---|---|
| **Chat Playground** (Fabric Studio) | Yes |
| **Production chat UI** | Yes |
| **Observation conversation review** | Yes (during review, not at time of interaction) |

Feedback submitted from any of these interfaces is stored in the same underlying data structure and is visible across all review surfaces.

---

## Submitting Feedback via API

For integrations where the chat UI is embedded in a third-party application, or where feedback needs to be submitted programmatically from automated testing pipelines, the platform exposes a feedback API. The API accepts the conversation ID, the turn index, a rating value, and an optional note string.

This allows feedback to be collected through custom interfaces — for example, a post-chat survey in a mobile application — and written back into the AI Fusion feedback store using the same schema as UI-submitted feedback.

For API endpoint specifications, authentication requirements, and request/response examples, refer to the AI Fusion API Reference documentation.

---

## Where Feedback Appears

Feedback is stored in the **`FEEDBACK`** table within the AI Fusion data model. Each record is associated with a specific conversation and a specific turn within that conversation, enabling precise attribution of ratings and notes.

Once submitted, feedback becomes visible in two locations:

1. **The Observation module** — feedback ratings and notes appear inline within the conversation detail view, displayed alongside the assistant response they were submitted against.
2. **The chat UI** (for the submitting user) — the thumbs button corresponding to the active rating is highlighted, indicating that feedback has been recorded for that turn.

Feedback is not aggregated or summarized in a standalone dashboard in the current release. Access to feedback data is through the Observation conversation review flow or through direct query of the `FEEDBACK` table.

---

## Reviewing Feedback in Observation

The **Observation** module is the primary surface for supervisors and QA engineers to review feedback in context. When browsing conversation threads, feedback indicators show which turns received ratings and what those ratings were.

> **Note:** *Screenshot needed:* [The Observation conversation review page showing a conversation thread with feedback visible. At least one turn should show a thumbs-down rating and a note, displayed inline with the assistant response. Save as `images/14_feedback_in_observation.png`]

<img src="images/14_feedback_in_observation.png" alt="Feedback visible in the Observation conversation review" style="zoom:80%;" />

### Identifying Conversations with Negative Feedback

To focus review effort on the most problematic interactions, filter conversations in Observation by feedback rating. Conversations that contain one or more thumbs-down ratings represent candidate cases for deeper investigation. The associated notes, when present, narrow down which specific response turn the user found inadequate and why.

Reviewing negative-feedback conversations in context — seeing the full preceding dialogue, not just the rated response — is essential for accurate root-cause assessment. A response that appears unhelpful in isolation may reflect a well-handled edge case, or may reveal a systematic gap in the agent's data access or reasoning.

---

## Acting on Feedback

Feedback is only valuable if it drives action. The recommended workflow for translating negative feedback into agent improvements is as follows:

### Step 1: Identify Low-Rated Responses

Review conversations in the **Observation** module filtered by negative feedback. Prioritize conversations where the note content identifies a factual error, a missing data retrieval, or a consistently poor tone.

### Step 2: Review in Context

Open the full conversation in Observation. Assess whether the agent's response reflects:

- **A data access gap** — the agent did not retrieve information it should have.
- **A reasoning or prompt issue** — the agent retrieved the right data but interpreted or presented it incorrectly.
- **A scope issue** — the question fell outside the agent's intended domain, and the fallback behavior was inadequate.
- **A one-off edge case** — the response was reasonable given the input, and user expectations may have been misaligned.

### Step 3: Export to Evaluation

For cases that represent genuine agent quality issues, export the conversation from Observation as a ZIP file and import it into the **Evaluation** workspace. See [Data Snapshots and Production Import](../03_evaluation/09_data_snapshots_and_production_import.md) for the full import workflow.

In the Evaluation workspace:

- Set the ground truth for the problematic turn to the correct response.
- Run the test to confirm the failure is reproducible.
- Save the test to the appropriate suite so that it runs in all future pipeline executions.

### Step 4: Improve the Agent

With a reproducible test case established, make the targeted improvement to the agent — whether that means updating a system prompt, adjusting a tool configuration, modifying data product scope, or escalating a data quality issue upstream.

Re-run the test to confirm that the improvement resolves the failure. Run the full suite to confirm no regressions were introduced.

### Step 5: Monitor Recurrence

Because the conversation is now a permanent test case, any future agent change that reintroduces the same failure will be caught by the pipeline. The feedback signal from a single user interaction has been translated into a durable quality safeguard.

---

## Future: Feedback-Driven Tuning

The current release treats feedback as a review and routing mechanism: negative feedback surfaces conversations for human investigation, which in turn drives manual agent improvements.

A planned enhancement to the platform will introduce **feedback-driven fine-tuning** — an automated pipeline that accumulates rated conversation turns and uses them to improve model behavior directly. In this model, consistently thumbs-up responses contribute positive training signal, while consistently thumbs-down responses (with notes) contribute corrective signal. The accumulated dataset is used to fine-tune the underlying LLM or to update retrieval-augmented generation (RAG) configurations, with the goal of making agent improvements data-driven and continuous rather than episodic.

This capability is on the product roadmap. When available, it will be documented in a dedicated article under the Agent Framework section.

---

## Related Articles

- [Chat Playground](./09_chat_playground.md)
- [Data Snapshots and Production Import](../03_evaluation/09_data_snapshots_and_production_import.md)
- [AI-Assisted Test Generation](../03_evaluation/08_ai_assisted_test_generation.md)
- [Best Practices and Maintenance](../03_evaluation/07-best-practices.md)
