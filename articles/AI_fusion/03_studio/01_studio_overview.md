# AI Fusion Studio Overview

AI Fusion Studio is the development environment for AI Fusion agents inside K2view Web Studio. Because an AI Fusion 3 agent is a set of files inside a Data Product (LU) — an `agent.yaml`, skills, reference documents, sub-agents, evaluation suites and observation configuration — you build it the same way you build the rest of your Fabric project: as files in your project tree. AI Fusion Studio adds the tooling that makes those files easy to create, edit, test and evaluate without leaving Studio.

The tooling is delivered by the **AI Fusion - Studio** extension. It provides:

- **AI Explorer** — a dedicated tree of every agent, skill, reference pack, sub-agent and eval suite in the project, with context-menu actions to create and manage them.
- **Agent Editor** — a form-based editor for `agent.yaml` and sub-agent files. See [Agent Editor](/articles/AI_fusion/03_studio/02_agent_editor.md).
- **Preview and Trace** — a chat panel, inside the Agent Editor, that talks to the deployed agent and shows the full execution trace of each answer. See [Preview and Trace](/articles/AI_fusion/03_studio/03_preview_and_trace.md).
- **Eval Editor** — an editor for eval suites and their test cases. See [Authoring Test Cases](/articles/AI_fusion/04_evaluation/01_authoring_test_cases.md).
- **AI-assisted development** — Studio AI skills that build agents and diagnose bad answers. See [AI-Assisted Agent Development](/articles/AI_fusion/03_studio/04_ai_assisted_development.md).


## Installing the Extension

The extension is published on K2exchange as **AI Fusion - Studio** (version 1.0.1 at the time of writing) and requires Fabric 8.4.7 or later. Install it from the Extensions view of Web Studio, as described in [K2exchange](/articles/04_fabric_studio/28_web_k2exchange.md). The extension contributes no settings of its own — it talks to the Fabric server that hosts your Studio session.

Installing the extension also adds two Studio AI skills to your project, under `.agents/skills/`: `agent-builder` and `researcher`. They power the AI-assisted features described in [AI-Assisted Agent Development](/articles/AI_fusion/03_studio/04_ai_assisted_development.md). Commit them to Git together with the rest of the extension's files.

For the full list of AI Fusion prerequisites (the runtime extension, the LLM interface and the insights database), see [Installation and Setup](/articles/AI_fusion/04_installation_and_setup.md).


## Where Agent Files Live

AI Explorer and the editors work on a fixed folder layout. An agent always belongs to a Data Product (LU); skills can also be shared across all LUs from Shared Objects.

```text
Implementation/
  LogicalUnits/<LU>/AI/
    Agents/<agent>/
      agent.yaml              # the agent definition (opens in the Agent Editor)
      Skills/                 # skills private to this agent (*.md)
      References/<pack>/      # reference packs searched at runtime (*.md)
      Subagents/              # one <sub-agent>.yaml per folder sub-agent
      Evals/                  # eval suites (<suite>.csv)
      Observation/            # signals.yaml and widgets.yaml for the Observation dashboard
    Skills/                   # LU-wide skills, available to every agent in the LU
    References/               # LU-level reference folders
  SharedObjects/AI/
    Skills/                   # skills shared by all LUs
```

The file formats themselves are documented in the [Agent Platform](/articles/AI_fusion/02_agents/README.md) section, starting with the [agent.yaml Reference](/articles/AI_fusion/02_agents/02_agent_yaml_reference.md).


## AI Explorer

Open AI Explorer from its icon in the Activity Bar. The view has two roots, which are always shown so you can create the first asset even in an empty project:

<table>
<tbody>
<tr>
<td><strong>Root</strong></td>
<td><strong>Contents</strong></td>
</tr>
<tr>
<td><strong>Data Products (Logical Units)</strong></td>
<td>One node per LU that already has AI content. Under each LU: <strong>Agents</strong>, <strong>Skills</strong> and <strong>References</strong> (a category appears once it has content).</td>
</tr>
<tr>
<td><strong>Shared Objects</strong></td>
<td><strong>Skills</strong> only — shared skills that every LU can use.</td>
</tr>
</tbody>
</table>

Expanding an agent shows its folder as it is on disk: `agent.yaml`, `Skills/`, `References/<pack>/`, `Subagents/`, `Evals/` and `Observation/`. Clicking a node opens it in the right editor:

- `agent.yaml` and `Subagents/*.yaml` open in the [Agent Editor](/articles/AI_fusion/03_studio/02_agent_editor.md).
- An eval suite (`Evals/*.csv`) opens in the Eval Editor. A suite node also expands into its **test cases**; clicking a case opens the Eval Editor on that case.
- Any other file (skills, references, observation YAML) opens in the regular text editor.

### Test Case Status

Each test case under an eval suite carries a colored bullet that shows the result of its latest evaluation run. The color alone carries the meaning, so the tree stays easy to scan:

<table>
<tbody>
<tr>
<td><strong>Bullet</strong></td>
<td><strong>Meaning</strong></td>
</tr>
<tr>
<td>Blue spinner</td>
<td>An evaluation is running.</td>
</tr>
<tr>
<td>Green</td>
<td>The last run passed.</td>
</tr>
<tr>
<td>Orange</td>
<td>The last run completed, but one or more quality dimensions scored below their threshold.</td>
</tr>
<tr>
<td>Red</td>
<td>The last run failed or was stopped.</td>
</tr>
<tr>
<td>Gray slashed circle</td>
<td>The test case is disabled.</td>
</tr>
<tr>
<td>No bullet</td>
<td>The case has not been evaluated yet.</td>
</tr>
</tbody>
</table>

An agent whose `agent.yaml` references a missing or invalid sub-agent is decorated in red in the tree, and the problem is listed in the **Problems** panel. See [Agent Editor](/articles/AI_fusion/03_studio/02_agent_editor.md#sub-agent-validation).


## Commands

Right-click a node in AI Explorer to see the actions that apply to it. Each **New** command asks for a name (letters, digits, underscore and hyphen; it must start with a letter), creates the file from a built-in template, opens it, and selects it in the tree.

<table>
<tbody>
<tr>
<td><strong>Command</strong></td>
<td><strong>Available on</strong></td>
<td><strong>What it does</strong></td>
</tr>
<tr>
<td><strong>New Agent...</strong></td>
<td>Data Products root, an LU, an LU's Agents folder</td>
<td>Creates <code>AI/Agents/&lt;name&gt;/</code> from the agent template and opens <code>agent.yaml</code> in the Agent Editor. Agents can only be created under an LU, never under Shared Objects. From the Data Products root you are asked to choose the LU.</td>
</tr>
<tr>
<td><strong>New Skill...</strong></td>
<td>An agent, a Skills folder, an LU, either root</td>
<td>Creates a skill <code>.md</code> file. On an agent or its <code>Skills/</code> folder, the skill is private to the agent; on an LU it goes to <code>&lt;LU&gt;/AI/Skills/</code>; on Shared Objects it goes to <code>SharedObjects/AI/Skills/</code>.</td>
</tr>
<tr>
<td><strong>New Reference...</strong></td>
<td>An agent, a References folder or pack, an LU</td>
<td>Creates a reference <code>.md</code> file inside a reference pack. When invoked on a References root you choose an existing pack or <strong>New pack...</strong>, because a loose file directly under <code>References/</code> is never loaded. References are not offered under Shared Objects.</td>
</tr>
<tr>
<td><strong>New Sub-Agent...</strong></td>
<td>An agent, its Subagents folder</td>
<td>Creates <code>Subagents/&lt;name&gt;.yaml</code> (same template as an agent) and opens it in the Agent Editor.</td>
</tr>
<tr>
<td><strong>New Eval Suite...</strong></td>
<td>An agent, its Evals folder</td>
<td>Creates <code>Evals/&lt;name&gt;.csv</code> with one empty test case, seeded with the agent's evaluation defaults, and opens it in the Eval Editor.</td>
</tr>
<tr>
<td><strong>New Folder...</strong></td>
<td>A References folder or pack</td>
<td>Creates a sub-folder, for example a new reference pack.</td>
</tr>
<tr>
<td><strong>Delete</strong></td>
<td>An agent, skill, reference file or folder, sub-agent</td>
<td>Deletes the item after a confirmation that names its kind (for example, "delete agent 'billing'"). Deleting a folder deletes all its contents.</td>
</tr>
<tr>
<td><strong>Reveal in Explorer</strong></td>
<td>Any node except the roots and test cases</td>
<td>Selects the backing file or folder in the standard file Explorer.</td>
</tr>
<tr>
<td><strong>Refresh AI Explorer</strong></td>
<td>The view toolbar</td>
<td>Reloads the tree.</td>
</tr>
</tbody>
</table>

The **New Agent**, **New Skill**, **New Reference**, **New Sub-Agent** and **New Eval Suite** commands are also available in the standard file Explorer's context menu, on the matching folders: an LU's `AI` and `AI/Agents` folders, any `Skills` folder, `References` folders, an agent's `Subagents` folder and an agent's `Evals` folder.

### Eval Suite and Test Case Commands

Eval suites and test cases have their own context menus in AI Explorer:

<table>
<tbody>
<tr>
<td><strong>Node</strong></td>
<td><strong>Commands</strong></td>
</tr>
<tr>
<td>Eval suite (<code>.csv</code>)</td>
<td><strong>New Test...</strong>, <strong>Run Evaluation</strong> (runs every enabled case in the suite), <strong>Rename...</strong>, <strong>Enable / Disable</strong> (enables the whole suite if any case is disabled, otherwise disables all cases), <strong>Delete</strong></td>
</tr>
<tr>
<td>Test case</td>
<td><strong>Run Evaluation</strong>, <strong>Duplicate</strong>, <strong>Rename...</strong>, <strong>Move to Suite...</strong> (to another suite of the same agent), <strong>Enable / Disable</strong>, <strong>Delete</strong></td>
</tr>
</tbody>
</table>

These commands edit the suite file and then soft-deploy the agent's LU automatically, so the next evaluation run sees the change. Test case authoring and running are covered in the [Evaluation](/articles/AI_fusion/04_evaluation/README.md) section.

### What Is Not in AI Explorer

- **Tools.** There is no "new tool" command. An agent's tools are existing Broadway flows (in the LU or in Shared Objects) or MCP interfaces; you create those with the regular Studio tools and then select them in the Agent Editor. See [Tools](/articles/AI_fusion/02_agents/03_tools.md).
- **Deploy.** The extension has no deploy command of its own. Use the standard Studio **Deploy** or **Soft Deploy** on the LU, as described in [Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md).


## What the New Agent Template Contains

**New Agent...** copies a template that gives you a working starting point:

- `agent.yaml` with empty `description`, `system_prompt`, `response_prompt` and `constraints`, the built-in `query` and `current_datetime` tools, empty hook slots, planning enabled (without user approval), reference search enabled, and a `settings:` block. Comments in the template explain each key.
- Empty `Skills/`, `References/`, `Subagents/` and `Evals/` folders.
- `Observation/signals.yaml` and `Observation/widgets.yaml` with generic samples. Replace them with your agent's own signals and widgets — the samples are used as-is otherwise. See [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md).

The template's `settings:` block sets the following values, which differ from the runtime defaults that apply when a key is omitted (see [Agent Editor — Settings](/articles/AI_fusion/03_studio/02_agent_editor.md#settings)):

```yaml
settings:
  llm_interface: default
  snap_session_mode: none
  memory: true
  thinking: medium
  use_cache: true
  max_iterations: 30
  max_retries: 2
  logging_level: info
  observability: true
  max_step_continuations: 3
  compaction_threshold_fraction: 0.8
  compaction_context_window_tokens: 200000
  compaction_keep_recent_messages: 30
  compaction_interface: default
```


## When Changes Take Effect

The agent runtime serves the **deployed** LU, not the files on disk. After you change `agent.yaml`, a sub-agent, a skill, a reference or a tool flow, deploy the agent's LU (a soft deploy is enough while iterating) before testing the change in Preview. Until then, Preview keeps answering with the previously deployed definition.

Eval runs are the exception: running an evaluation, and editing suites or cases from AI Explorer or the Eval Editor, soft-deploys the agent's LU for you so the run reads the latest suite files. The automatic soft deploy works only where the project sources are available (a Studio development space); elsewhere the run uses what is already deployed.


## Typical Developer Workflow

The following walkthrough shows how the pieces fit together when you build a new agent.

1. **Create the agent.** In AI Explorer, right-click the LU (or the **Data Products (Logical Units)** root) and choose **New Agent...**. Enter a name. The agent folder is created and `agent.yaml` opens in the Agent Editor.
2. **Describe the agent.** In the Agent Editor (**Editor** tab, **Form** view), fill in **Description**, **System prompt**, and optionally **Response guidelines** and **Constraints**.
3. **Give it data access and tools.** In **Tools & interfaces**, keep `query` on for agents that answer from LU data, and use **+ Add flow tool** to expose Broadway flows. For entity-scoped agents, set a `step_start` flow in **Hooks** (**Before the user message**) that loads the entity instance — its external parameters become the agent's inputs. See [Hooks and Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md).
4. **Add skills and sub-agents.** Use **New Skill...** for schema and procedure skills, and **New Sub-Agent...** for specialists. Link skills and other LUs' agents in the **Skills** and **Subagents** sections of the form.
5. **Choose the model.** In **Settings**, set **LLM interface** to the LLM interface the agent should use (or keep `default`). See [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md).
6. **Deploy.** Soft-deploy the LU from the Studio project tree.
7. **Try it in Preview.** Switch to the **Preview** tab, start a **New chat** (filling in the agent inputs, such as a customer ID), and ask questions.
8. **Inspect the trace.** Open the **Trace** drawer, or click **Show in Trace** on a tool call, to see each LLM call, tool call, sub-agent run, duration and token count.
9. **Fix and repeat.** Adjust the prompt, skills or tools, soft-deploy again and start a new chat. Mark answers with **Good response** / **Bad response** and notes as you go, or click **Analyze** to let Studio AI diagnose a bad answer.
10. **Save test cases.** When a conversation shows the behavior you want to keep, click **Save as test case** and choose a suite.
11. **Run evaluations.** In AI Explorer, right-click the suite and choose **Run Evaluation**. Watch the bullets turn green or orange, and open a case in the Eval Editor to read the per-dimension verdicts. See [Running Tests and Results](/articles/AI_fusion/04_evaluation/03_running_and_results.md).

If you prefer to start from a conversation with Studio AI, the `agent-builder` skill can perform steps 1–6 for you and then generate a first eval suite. See [AI-Assisted Agent Development](/articles/AI_fusion/03_studio/04_ai_assisted_development.md).

**Next article:** [Agent Editor](/articles/AI_fusion/03_studio/02_agent_editor.md)
