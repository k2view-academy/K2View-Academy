# Data Snapshots and Production Import

Reproducibility is one of the hardest problems in AI evaluation. Unlike traditional software, where the same input produces the same output deterministically, AI agent responses are shaped by the data available at query time. A test that passes today may fail tomorrow — not because the agent changed, but because the underlying customer data changed. The **AI Fusion Evaluation Framework** addresses this through two complementary mechanisms: **data snapshots** that freeze the state of customer data at test creation time, and **production import** that brings real-world conversations from the **Observation** module into the evaluation workspace.

---

## Overview

When a test is executed weeks or months after it was created, the customer referenced in that test may have a different account balance, a resolved dispute, or a closed contract. An agent that correctly answers based on current data will appear to fail against expected answers written for earlier data — or pass for the wrong reason. Neither outcome is useful.

Data snapshots solve this by capturing and locking the relevant customer data at the moment the test is saved. Every subsequent execution of that test runs against the same frozen dataset, regardless of what has changed in production. This makes evaluation results comparable across time, and makes test cases portable across environments.

---

## Data Snapshots (LUI Snaps)

A **data snapshot**, also referred to as a **LUI Snap** (Logical Unit Instance Snapshot), is a point-in-time capture of a customer's **mDB** (micro-database) — the unified data product that aggregates all information about a specific entity from across source systems.

When you attach a snapshot to a test case, the evaluation framework stores the complete state of that entity's mDB alongside the test definition. On each subsequent execution, the framework loads from the snapshot rather than fetching live data. The agent responds as though the customer data is exactly as it was when the test was created.

### Why Snapshots Matter

- **Deterministic results** — the same test produces the same agent responses in every run, making pass/fail outcomes meaningful and comparable.
- **Environment independence** — a test created in a development environment runs identically in QA or production, because the data it depends on travels with it.
- **Regression safety** — when an agent is updated, you can confirm that it still handles a specific customer scenario correctly, without worrying that the customer's data has changed in the interim.
- **Offline and isolated testing** — tests can run even when source systems are unavailable or undergoing maintenance, because all required data is already captured in the snapshot.

---

## Attaching a Snapshot to a Test

Snapshots are attached at save time.

When you click **Save Test** at the end of a test creation or generation session, the save dialog presents snapshot options alongside the test name and suite assignment fields.

> **Note:** *Screenshot needed:* [The Save Test dialog showing the snapshot attachment section. The option to attach an LUI Snap should be visible, along with the test name field and suite selector. URL: `/evaluation/save-dialog`. Save as `images/09_snapshot_save_dialog.png`]

<img src="images/09_snapshot_save_dialog.png" alt="Snapshot attachment in the save dialog" style="zoom:80%;" />

Select **Attach Snapshot** to capture the current state of the customer's mDB. The platform records all data associated with the entity identified by the test's **Customer ID (IID)** at this moment.

> **Note:** If the test was created using SDG integration (IID set to `auto`), a snapshot of the synthetic entity is attached automatically. No additional action is required.

### What Gets Captured

The snapshot includes the full mDB for the entity: all tables, fields, and relationships that the agent may access during a conversation. This encompasses account details, transaction history, product subscriptions, support cases, and any other data domains modeled in the data product.

The snapshot does not capture external system state (for example, the source CRM database). It captures the unified view as assembled by the data product at the time of attachment.

---

## Sharing Tests Across Environments

A test case exported from the evaluation workspace is packaged as a **ZIP file** that includes:

- The conversation definition (turns, questions, ground truth).
- Quality criteria and threshold configuration.
- The attached data snapshot (if one was associated).

This means a colleague in a different environment — a different Fabric installation, a different data region, or a production deployment — can import the ZIP and execute the test against exactly the same data. The results will be directly comparable to those produced in the originating environment.

This is particularly valuable for:

- **Handoff between teams** — a QA engineer creates and validates a test in the QA environment; the same test is imported by a DevOps engineer to verify a production deployment.
- **Reproducing reported issues** — a customer scenario captured in production can be exported and imported into a development environment, allowing the developer to reproduce and debug the exact conversation.
- **Baseline establishment** — a reference execution can be saved and shared so that all team members are comparing results against a common baseline.

To export a test, select it in the test library and choose **Export**. To import, use the **Import** button in the evaluation workspace and select the ZIP file.

---

## Pipeline and Scheduled Execution

Individual test execution is useful during development, but systematic quality assurance requires running tests consistently and automatically. The **AI Fusion Evaluation Framework** provides a Broadway pipeline — `aifusion.evaluatorPipeline` — that executes all enabled tests across all configured suites.

### Running the Pipeline Manually

The pipeline can be triggered on demand from the **Fabric Studio** pipeline panel.

1. Open **Fabric Studio** and navigate to the **Broadway** section.
2. Locate the `aifusion.evaluatorPipeline` flow.
3. Click **Run** to execute all enabled tests.

> **Note:** *Screenshot needed:* [Fabric Studio Broadway panel showing the `aifusion.evaluatorPipeline` flow with the Run button visible. The flow diagram or pipeline list should be clearly visible. Save as `images/09_pipeline_fabric_studio.png`]

<img src="images/09_pipeline_fabric_studio.png" alt="Pipeline execution in Fabric Studio" style="zoom:80%;" />

### Scheduling Automatic Execution

The pipeline supports scheduled execution through Fabric's standard job scheduling mechanism. Common scheduling patterns include:

| Schedule | Typical Use Case |
|---|---|
| Nightly (e.g., 02:00) | Daily regression check during active development |
| Post-deployment | Automated validation after every agent deployment |
| Weekly (e.g., Monday 06:00) | Maintenance-phase quality monitoring |

To configure a schedule, define a Fabric job that invokes `aifusion.evaluatorPipeline` at the required interval. Refer to the Fabric Jobs documentation for scheduling syntax.

### Execution Scope

The pipeline executes only tests that are **enabled**. Tests and suites can be individually enabled or disabled from the evaluation workspace. Use this to:

- Exclude work-in-progress tests from automated runs.
- Run only a subset of suites for environment-specific validation.
- Temporarily disable a test that is failing due to a known agent issue under investigation.

---

## Comparing Executions Over Time

Each pipeline run produces a timestamped execution record. The evaluation workspace allows you to navigate between execution records and compare results side by side.

> **Note:** *Screenshot needed:* [The cross-execution comparison view showing two or more execution runs with scores visible for each test. Score changes between executions should be highlighted. Save as `images/09_execution_comparison.png`]

<img src="images/09_execution_comparison.png" alt="Cross-execution comparison view" style="zoom:80%;" />

### Navigating Between Executions

Use the **Execution History** selector in the results view to switch between past runs. Each execution record shows:

- The timestamp of the run.
- Pass/fail status per test.
- Individual criterion scores per turn.
- The overall pass rate for the suite.

### What to Look For

When comparing executions after an agent change, prompt update, or model upgrade:

- **Score improvements** in criteria directly targeted by the change confirm that the modification had the intended effect.
- **Score regressions** in other criteria signal unintended side effects — for example, a prompt change that improved accuracy but degraded politeness.
- **Consistent failures** across multiple executions of the same test indicate a systematic issue that requires agent-level attention.
- **Newly failing tests** that passed in previous runs pinpoint regressions introduced by a specific change.

The comparison view is the primary tool for validating that agent improvements are genuine and net-positive, rather than trading one quality dimension for another.

---

## Importing Production Conversations

The **Observation** module captures live conversations between real users and deployed agents. These conversations represent the actual distribution of customer intent — including phrasings, topics, and edge cases that no synthetic generation process can fully anticipate.

The **production import** capability allows you to bring these real conversations into the evaluation workspace, evaluate them, and optionally save them as permanent test cases.

### Import Workflow

1. In the **Observation** module, locate a conversation you want to import. This may be a conversation flagged by negative user feedback, one that exhibited unexpected behavior, or one that represents a scenario not yet covered by your test suite.
2. Export the conversation as a ZIP file using the **Export** option in the Observation conversation detail view.
3. In the **Evaluation** workspace, click **Import**.

> **Note:** *Screenshot needed:* [The Import button in the Evaluation workspace, showing the file selection dialog or import panel. URL: `/evaluation`. Save as `images/09_import_button.png`]

<img src="images/09_import_button.png" alt="Import button in the evaluation workspace" style="zoom:80%;" />

4. Select the exported ZIP file. The conversation is loaded into the evaluation workspace as a draft test.
5. Review the conversation. You can:
   - Run it through the evaluator immediately to score it against your quality criteria.
   - Edit the ground truth for each turn to establish what the correct response should have been.
   - Save it as a permanent test case to ensure the scenario is covered in all future pipeline executions.

### Closing the Quality Loop

Production import creates a direct connection between what happens in the field and what gets tested in the lab. A conversation that revealed a gap in agent quality in production becomes a regression test that prevents the same gap from reappearing after future changes.

Combined with the [Feedback Integration](../02_agent_framework/14_feedback_integration.md) capability — where users rate agent responses directly in the chat UI — this gives quality teams a systematic way to surface problematic conversations, evaluate them formally, and translate them into durable test assets.

---

## Related Articles

- [AI-Assisted Test Generation](./08_ai_assisted_test_generation.md)
- [Running Tests and Results](./04-running-tests-and-results.md)
- [Organizing Test Suites](./06-organizing-test-suites.md)
- [Feedback Integration](../02_agent_framework/14_feedback_integration.md)
