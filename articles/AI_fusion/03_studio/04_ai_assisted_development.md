# AI-Assisted Agent Development

Studio AI — the AI assistant built into K2view Web Studio — can do much of the agent development work for you. The AI Fusion - Studio extension adds two Studio AI skills for this:

<table>
<tbody>
<tr>
<td><strong>Skill</strong></td>
<td><strong>What it does</strong></td>
<td><strong>How you start it</strong></td>
</tr>
<tr>
<td><code>agent-builder</code></td>
<td>Builds a new agent over a Data Product (LU), or adds to or fixes an existing one: domain-schema skills, sub-agents, tools, hooks, observation signals and widgets. It can also generate an eval suite from the agent's business questions.</td>
<td>Ask Studio AI in the AI Chat, for example "Build an agent over the Customer LU", or type <code>/agent-builder</code>.</td>
</tr>
<tr>
<td><code>researcher</code></td>
<td>Diagnoses why an agent gave a bad answer and, with your approval, fixes it, verifies the fix and saves a regression test.</td>
<td>Click <strong>Analyze</strong> in the Preview or the Eval Editor, or ask Studio AI to "analyze this bad answer".</td>
</tr>
</tbody>
</table>

Both skills follow the same principles: they read your actual project files and the actual run data rather than guessing, they never invent schema, and they stop for your approval before side-effecting steps.

For general Studio AI usage — the AI Chat, agents, modes and skills — see [Getting Started with Studio AI](/articles/04_fabric_studio/studio_ai/01_getting_started_with_studio_ai.md) and [Skills and Slash Commands](/articles/04_fabric_studio/studio_ai/11_skills_and_slash_commands.md).


## Getting the Skills

Installing the **AI Fusion - Studio** extension from K2exchange adds both skills to your project under `.agents/skills/agent-builder/` and `.agents/skills/researcher/`, together with the extension's other files. Commit them to Git so every Studio space created from the project has them. If the skills are missing from the project (for example, they were deleted), run the **Import Artifacts** command of the extension (Command Palette, category **Fabric-aifusion-studio**) to bring them back.

Once installed, the skills are listed in the **Skills & Slash Commands** category of Studio AI's configuration.


## Building an Agent with Agent Builder

Agent Builder turns a request like "create a support agent over the `Customer` Data Product" into a complete, deployable agent. It works in the following stages, and asks you whenever a decision is yours to make.

1. **Resolve the Data Product.** It takes the LU from your request or from the file you have open, and checks that the LU is deployed. If it cannot determine the LU, it asks.
2. **Learn the schema and map domains.** It reads the LU's tables, columns and relationships — with the help of Studio AI's Data Product and Interfaces agents when available, or from the LU definition files — and splits the tables into business domains. It never invents schema.
3. **Collect the business questions.** It asks which questions the agent must answer. You can paste them, give a file path, or let it propose a set per domain.
4. **Decide the structure.** It prefers the simplest design that works: a single agent when the questions are few, and one sub-agent per domain only when a domain genuinely needs its own prompt or tools.
5. **Choose tools and hooks.** It picks existing Broadway flows worth exposing as tools, decides on the built-in tools (`query`, `current_datetime`, and `fabric` only if really needed), and makes sure an entity-scoped agent has a `step_start` hook that loads the entity instance. It creates that hook flow if none exists.
6. **Scaffold.** It proposes an agent name, confirms it with you, and runs the same **New Agent...** command you would use in AI Explorer. The new `agent.yaml` opens in the [Agent Editor](/articles/AI_fusion/03_studio/02_agent_editor.md).
7. **Fill the files.** It writes the `agent.yaml` (description, system prompt, response guidelines, tools, hooks, skills), one domain-schema skill per domain under `<LU>/AI/Skills/`, any sub-agents and reference packs, and the agent's `Observation/signals.yaml` and `Observation/widgets.yaml`. It dry-runs the widget queries and fixes them before deploying.
8. **Deploy.** It soft-deploys the LU so the agent goes live — after you approve the deploy.
9. **Report.** It summarizes what was created, the domain-to-skill map, the agent's input (for example `customerId`), the observation signals and widgets, and what it skipped.

After the build, open the agent's **Preview** tab to try it. See [Preview and Trace](/articles/AI_fusion/03_studio/03_preview_and_trace.md).

Agent Builder is also the right tool when an existing agent "returns no data" or "doesn't know the customer". The most common cause is a missing or unwired `step_start` hook, and Agent Builder checks for that first. See [Hooks and Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md).


## Generating Tests Automatically

Once the agent is deployed, Agent Builder offers to build its eval suite from the business questions. The key rule is that every test case holds a **real answer**: each question is actually run through the deployed agent, and the agent's answer becomes the case's expected baseline. Nothing is hand-written.

The process:

1. **Find the agent's inputs.** It reads the agent's `step_start` hook flow to learn which inputs (such as `customerId`) a session needs.
2. **Check existing coverage.** It reads the agent's existing `Evals/*.csv` suites and skips questions that are already covered.
3. **Ask you for the details.** It confirms the questions and asks you for **candidate entity IDs** to test with — it never invents an ID. It also asks whether to simulate a specific customer **persona** for the tone of the questions (default: a neutral customer), the **conversation-pairs limit** for multi-turn cases (default 10), and whether closely related questions should be combined into one conversation.
4. **Run and save.** It groups cases into suites (typically one per domain), runs each question through the agent, saves each case **with a snapshot** of the entity's data, and seeds each case's quality dimensions and thresholds from the agent's evaluation defaults (**Evaluation defaults** setting).
5. **Multi-turn conversations.** When a real customer would naturally follow up — the answer needs clarification, invites a next step, or the agent asks a question back — Agent Builder plays the customer, reads each real answer, and decides the next turn, until the goal is met or the pairs limit is reached.
6. **Verify and report.** It checks every saved row (a non-empty answer, dimensions and thresholds present, snapshot saved), rebuilds any row that fails, and reports the question → case → input → suite mapping.

The generated cases appear under the agent's `Evals/` folder in AI Explorer and can be run like any other case. See [Running Tests and Results](/articles/AI_fusion/04_evaluation/03_running_and_results.md).

Test generation runs the agent through the Studio, so it needs a Studio development space with the agent's LU deployed.


## Analyzing a Bad Answer with Researcher

When the agent gives a wrong, incomplete or unhelpful answer, the **Analyze** buttons hand the problem to Studio AI, which loads the `researcher` skill. There are three entry points:

<table>
<tbody>
<tr>
<td><strong>Where</strong></td>
<td><strong>What is analyzed</strong></td>
</tr>
<tr>
<td>Preview toolbar — <strong>Analyze</strong></td>
<td>The whole conversation.</td>
</tr>
<tr>
<td>Preview message <strong>Note</strong> popup — <strong>Analyze</strong></td>
<td>One answer, together with the note you typed (it does not need to be saved first).</td>
</tr>
<tr>
<td>Eval Editor — <strong>Analyze</strong></td>
<td>The latest evaluation run of a test case, including which quality dimensions fell below their threshold. Available once the case has been run.</td>
</tr>
</tbody>
</table>

Clicking **Analyze** opens a new Studio AI chat with the agent's LU and name, the session ID, the step (for a single answer), the session inputs, and your note or the failed dimensions. The Analyze buttons are shown only when Studio AI's Ask AI service is available — Web Studio 8.5.2 or later, with the AI features turned on. On older Studio versions the buttons are hidden.

### Diagnose

Diagnosis is read-only. The researcher:

1. Fetches the conversation history and the run trace for the session (for an eval run, also the judge's per-dimension verdicts).
2. Reads the agent's definition — `agent.yaml`, its skills, and the flows its hooks and tools use.
3. Reads the trace before the answer text, to see which tools and sub-agents ran and what they returned. It also considers causes upstream of the bad answer, such as an earlier turn.
4. Reports **what went wrong**, the single most likely **root cause** with its evidence (the exact file, prompt section, tool, hook or skill), and the **suggested fix**. Typical causes are the system prompt, a missing or wrong skill, the wrong tool or sub-agent choice, a tool or hook result, data scope (a missing `step_start` hook) or the model settings.

It then asks whether you want it to apply the fix.

### Fix and Verify

If you ask it to act, the researcher follows a gated workflow:

1. **Apply — needs your approval.** It shows the exact change and waits for your OK before writing. Simple text and configuration changes (prompt text, a `tools` or `skills` entry, a setting, a skill's body, wiring an existing flow into a hook) are edited directly; authoring or repairing a Broadway flow is delegated to the Broadway flow-building skill.
2. **Deploy.** It soft-deploys the LU, because an edit has no effect until the LU is deployed.
3. **Re-run.** It asks the original question again in a fresh session with the same inputs, a couple of times, since answers are not deterministic.
4. **Judge.** It compares the new answers with the original bad answer and your note, and reports **resolved**, **partial** or **not resolved**. If the fix did not work, it refines and retries a limited number of times, then hands back with what it learned.
5. **Regression test — needs your approval.** If you agree, it saves the verified conversation as an eval test case, so the problem cannot silently return. It makes sure the saved answer contains the decisive value that tells the fixed behavior from the buggy one, and continues the conversation over several turns if the agent only offered to look the value up.


## Approvals and Side Effects

Both skills stop for you at the points that change your project or your environment:

- Agent Builder confirms the agent name before scaffolding, asks for the business questions and test entity IDs, and asks before deploying.
- The researcher never edits a file without showing the change first, and never creates a test case without your approval. Deploying and re-running to verify an approved fix need no separate approval.

Review the changes Studio AI makes as you would any other change, and commit them to Git when you are satisfied.

<!-- TODO screenshot: Studio AI chat after clicking Analyze on a Preview note, showing the diagnosis report -->
