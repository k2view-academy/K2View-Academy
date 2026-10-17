# Agent Editor

The Agent Editor is the visual editor for agent definitions. It opens automatically for `<LU>/AI/Agents/<agent>/agent.yaml` and for sub-agent files under `<LU>/AI/Agents/<agent>/Subagents/*.yaml`, whether you open them from AI Explorer, the file Explorer, or a **New Agent...** / **New Sub-Agent...** command.

The editor gives you a structured form for every key of the agent schema, so you do not have to remember key names or YAML syntax. It writes back to the same `agent.yaml` file, so the file in Git stays the source of truth, and you can always switch to the raw YAML.

<!-- TODO screenshot: Agent Editor, Editor tab, Form view with all cards collapsed -->


## Editor Layout

The top strip of the editor has two tabs:

<table>
<tbody>
<tr>
<td><strong>Tab</strong></td>
<td><strong>Purpose</strong></td>
</tr>
<tr>
<td><strong>Editor</strong></td>
<td>Edit the agent definition, as a form or as YAML source.</td>
</tr>
<tr>
<td><strong>Preview</strong></td>
<td>Chat with the deployed agent and inspect the trace. See <a href="/articles/AI_fusion/03_studio/03_preview_and_trace.md">Preview and Trace</a>. Shown for top-level agents only; sub-agents cannot be run on their own.</td>
</tr>
</tbody>
</table>

When you open a top-level agent, the editor lands on **Preview**. Click **Editor** to edit the definition.

On the **Editor** tab, a **Form** / **Source** toggle switches between the two views:

- **Form** — collapsible cards, one per area of the definition. All cards start collapsed and show a short summary (for example, word counts or how many tools are configured), so the form reads as an overview. Use **Expand all** / **Collapse all** to open or close every card.
- **Source** — the raw YAML in an embedded code editor. Changes made in either view are reflected in the other.

If the file has a YAML syntax error, the editor switches to **Source** and shows a banner (*YAML syntax error — fix it here in Source view*) with the parser message. The form is unavailable until the file parses again.

### How the Editor Writes the File

The form only updates the keys you change. Comments, key order and keys the editor does not know about are preserved, so a hand-edited `agent.yaml` survives a round trip through the form. Settings equal to their default are not written to the file.

You can still open the file as plain text with **Open With... > Text Editor** from the file's context menu.


## Description

A short, human-readable summary of the agent. It is also the delegation hint: when this agent is used as a sub-agent, the parent agent reads the description to decide when to call it. YAML key: `description`.


## System Prompt

The agent's main instructions, in Markdown. YAML key: `system_prompt`.

The field is a Markdown editor with syntax highlighting, **edit** and **preview** tabs, a heading **Outline** that jumps to a section, and a word count. The same editor is used for **Response guidelines** and **Constraints**.


## Response Guidelines and Constraints

Both fields hold Markdown and both affect the final reply, but they behave differently when a turn is handed to a sub-agent:

<table>
<tbody>
<tr>
<td><strong>Field</strong></td>
<td><strong>YAML key</strong></td>
<td><strong>Use it for</strong></td>
<td><strong>Across a <code>delegate</code> handoff</strong></td>
</tr>
<tr>
<td><strong>Response guidelines</strong></td>
<td><code>response_prompt</code></td>
<td>House style for the user-facing reply: tone, formatting, what must never be exposed.</td>
<td>The turn owner's guidelines travel to the sub-agent and <strong>replace</strong> its own.</td>
</tr>
<tr>
<td><strong>Constraints</strong></td>
<td><code>constraints</code></td>
<td>Non-negotiable domain rules, such as "last four digits only".</td>
<td>The caller's constraints are <strong>added to</strong> the sub-agent's own; both apply.</td>
</tr>
</tbody>
</table>

Click the **?** on either card to open the **Guidelines and constraints** matrix, which shows what happens to each on every route: when the agent answers itself, when it calls a sub-agent with `task`, and when it hands the turn over with `delegate`.

In a sub-agent file, **Response guidelines** shows a *Not in use yet* note: a sub-agent never owns a turn, so its response guidelines take no effect. Use **Constraints** for rules that must hold whoever calls the sub-agent. See [Sub-Agents and Delegation](/articles/AI_fusion/02_agents/06_subagents_and_delegation.md).


## Tools & Interfaces

This card controls which tools the LLM can call. YAML keys: `tools`, `mcp_interfaces`.

### Built-in Tools

Three built-in tools can be switched on or off:

<table>
<tbody>
<tr>
<td><strong>Tool</strong></td>
<td><strong>Default</strong></td>
<td><strong>What it does</strong></td>
</tr>
<tr>
<td><code>query</code></td>
<td>On</td>
<td>Runs an SQL query against the entity's Fabric micro-database (SQLite).</td>
</tr>
<tr>
<td><code>fabric</code></td>
<td>Off</td>
<td>Runs Fabric commands. This is a privileged tool — enable it only when the agent really needs it.</td>
</tr>
<tr>
<td><code>current_datetime</code></td>
<td>On</td>
<td>Returns the current date and time (UTC, ISO-8601) for the turn.</td>
</tr>
</tbody>
</table>

The core tools — `task`, `ask_user`, the skill tools, the reference-search tools and, for top-level agents, the planning tools — are added automatically and are not listed here.

### Flow Tools

Click **+ Add flow tool** to expose a Broadway flow as a tool. The picker lists the flows of the agent's LU and of Shared Objects; table population flows are excluded. Click the flow name's open icon to open the flow itself.

The extension has no wizard for creating tools: a tool is an existing Broadway flow, which you build with the regular Studio flow editor. See [Tools](/articles/AI_fusion/02_agents/03_tools.md).

### Per-Tool Hooks

Every built-in or flow tool row has a **Before** and an **After** selector. A **Before** flow runs just before that tool is invoked (to mask arguments, validate, or block the call); an **After** flow runs on its result (to audit, redact or cache). These hooks apply to that one tool only; the agent-level `pre_tool_call` / `post_tool_call` hooks still run for every tool. In YAML, a tool with hooks is written as a single-key map:

```yaml
tools:
  - query
  - current_datetime
  - getAccounts:
      before: MaskPII
      after: AuditLog
```

### MCP Interfaces

Type the names of MCP interfaces defined in the project. Their tools become available to the agent. See [Tools](/articles/AI_fusion/02_agents/03_tools.md).


## Hooks

Agent-level lifecycle hooks, each a single Broadway flow. YAML key: `hooks`. Each row shows the flow's inputs and what it may return.

<table>
<tbody>
<tr>
<td><strong>Label</strong></td>
<td><strong>YAML key</strong></td>
<td><strong>Purpose</strong></td>
</tr>
<tr>
<td><strong>Before the user message</strong></td>
<td><code>step_start</code></td>
<td>Inspect or rewrite the turn's user message before the model sees it. This is also where an entity-scoped agent loads its entity instance.</td>
</tr>
<tr>
<td><strong>After the answer</strong></td>
<td><code>step_end</code></td>
<td>Validate the answer and optionally trigger a follow-up run.</td>
</tr>
<tr>
<td><strong>Before a tool call</strong></td>
<td><code>pre_tool_call</code></td>
<td>Inspect and optionally block any tool call (<code>ALLOW</code> / <code>BLOCK</code>).</td>
</tr>
<tr>
<td><strong>After a tool call</strong></td>
<td><code>post_tool_call</code></td>
<td>Observe or log a completed tool call, or end the run early.</td>
</tr>
<tr>
<td><strong>When the run errors</strong></td>
<td><code>on_error</code></td>
<td>Customize the error message shown when an agent run fails. Tool errors are handled by the after-a-tool-call hook.</td>
</tr>
</tbody>
</table>

The external parameters of the hook flows are the agent's inputs — they are what the Preview **New chat** dialog asks for. See [Hooks and Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md).


## Subagents

This card is shown for top-level agents only. YAML keys: `sub_agents`, `delegate`.

- **Folder sub-agents** — every file in the agent's `Subagents/` folder is listed automatically and is available by default. Switch one off to hide it from the agent without deleting it.
- **Linked agents** — click **+ Add subagent**, choose a Data Product (LU) under **DATA PRODUCT**, and pick a top-level agent from that LU. This lets an agent reuse another agent, including one from a different LU.
- **Allow handing a turn to a subagent** — off by default. When on, the agent gets the `delegate` route: a sub-agent can answer the user directly, and the parent's response guidelines travel with the turn.
- **Route per sub-agent** — enabled once handing off is allowed: **Both** (the agent decides per request; the recommended default), **Task only** (the sub-agent always returns its result to the parent, which writes the reply), or **Delegate only** (the sub-agent answers the user and ends the turn). YAML key per entry: `invoke`.

### Sub-Agent Validation

The editor checks every `sub_agents:` entry and reports broken references in the **Problems** panel as errors, and the agent's `agent.yaml` (and its node in AI Explorer) is decorated in red. The checks are:

- A folder entry must match a file `Subagents/<name>.yaml` of this agent.
- A linked entry must name an existing LU, and an existing top-level agent in that LU.

In the form, a broken entry is tagged **missing**.


## Planning

Shown for top-level agents only (sub-agents never plan). YAML key: `planning`.

- **Plan-then-execute** — enables planning mode.
- **Require user approval of the plan** — the agent waits for the user to approve the plan before executing it.
- **Custom planning prompt** — optional; leave blank to use the default.

See [References and Planning](/articles/AI_fusion/02_agents/05_references_and_planning.md).


## Reference Search

**Reference-document search** lets the agent search the reference packs under its `References/` folder (for example, `References/KB` and `References/Procedures`). Packs are discovered automatically. An optional **Custom ref-search prompt** replaces the default instructions. YAML key: `search_reference`.


## Widgets

**Enable widget building** lets the agent produce charts and tables in the chat. YAML key: `widget`.


## Skills

Skills in the agent's own `Skills/` folder are discovered automatically and do not need to be listed. Use this card to:

- **Add LU-wide or shared skills** — click **+ Add skill** to pick a skill from the LU's `AI/Skills/` or from Shared Objects (each entry shows its origin), or type a name.
- **Auto-load** — switch on to inject a skill into the system prompt from the start of every conversation, instead of letting the agent load it on demand.

YAML key: `skills`. See [Skills](/articles/AI_fusion/02_agents/04_skills.md).


## Settings

Every setting is optional. Each row shows the default it falls back to; a changed value shows a reset button, and values equal to the default are not written to the file. In a sub-agent, unset values are **inherited** from the parent agent instead.

<table>
<tbody>
<tr>
<td><strong>Label</strong></td>
<td><strong>YAML key (under <code>settings</code>)</strong></td>
<td><strong>Default</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>LLM interface</strong></td>
<td><code>llm_interface</code></td>
<td><code>default</code></td>
<td>The LLM interface used for the agent's LLM calls.</td>
</tr>
<tr>
<td><strong>Thinking</strong></td>
<td><code>thinking</code></td>
<td><code>off</code></td>
<td>Extended-reasoning level: <code>off</code>, <code>low</code>, <code>medium</code>, <code>high</code>, <code>xhigh</code>, <code>max</code>.</td>
</tr>
<tr>
<td><strong>Prompt caching</strong></td>
<td><code>use_cache</code></td>
<td><code>true</code></td>
<td>Caches prompt prefixes at the provider. Pays off for multi-turn and high-volume agents.</td>
</tr>
<tr>
<td><strong>Logging level</strong></td>
<td><code>logging_level</code></td>
<td><code>info</code></td>
<td><code>off</code>, <code>debug</code>, <code>info</code> or <code>warn</code>.</td>
</tr>
<tr>
<td><strong>Snap session mode</strong></td>
<td><code>snap_session_mode</code></td>
<td><code>none</code></td>
<td>Which sessions are snapshotted: <code>none</code>, <code>all</code>, <code>frequency</code> (about one session in N, <code>snap_frequency_limit</code>, default 10) or <code>period</code> (at most one session per interval, <code>snap_period_value</code> / <code>snap_period_unit</code>, default 15 minutes).</td>
</tr>
<tr>
<td><strong>Evaluation defaults</strong></td>
<td><code>eval_defaults_name</code></td>
<td><code>evalDefaults</code></td>
<td>The named default set (from the evaluation defaults MTable) that new test cases take their dimensions and thresholds from.</td>
</tr>
<tr>
<td><strong>Max iterations</strong></td>
<td><code>max_iterations</code></td>
<td><code>20</code></td>
<td>Tool-call loop cap within a single turn.</td>
</tr>
<tr>
<td><strong>Max retries</strong></td>
<td><code>max_retries</code></td>
<td><code>1</code></td>
<td>Automatic retries when a tool call fails.</td>
</tr>
<tr>
<td><strong>Max step continuations</strong></td>
<td><code>max_step_continuations</code></td>
<td><code>3</code></td>
<td>How many follow-up runs are allowed after an answer (0 disables).</td>
</tr>
<tr>
<td><strong>Memory</strong></td>
<td><code>memory</code></td>
<td><code>false</code></td>
<td>Persistent memory blocks across turns and sessions.</td>
</tr>
<tr>
<td><strong>Observability</strong></td>
<td><code>observability</code></td>
<td><code>true</code></td>
<td>Persists messages, executions, token usage and tool calls. The Preview trace is read from this data.</td>
</tr>
<tr>
<td><strong>Threshold fraction</strong></td>
<td><code>compaction_threshold_fraction</code></td>
<td><code>0</code></td>
<td>Compact the context when it reaches this fraction of the window (for example, 0.8).</td>
</tr>
<tr>
<td><strong>Context window (tokens)</strong></td>
<td><code>compaction_context_window_tokens</code></td>
<td><code>0</code></td>
<td>The model's context window (for example, 200000).</td>
</tr>
<tr>
<td><strong>Keep recent messages</strong></td>
<td><code>compaction_keep_recent_messages</code></td>
<td><code>20</code></td>
<td>Most recent messages kept verbatim; older ones are summarized.</td>
</tr>
<tr>
<td><strong>Compaction interface</strong></td>
<td><code>compaction_interface</code></td>
<td><code>default</code></td>
<td>A separate LLM interface for the summarization call, for example a cheaper model.</td>
</tr>
</tbody>
</table>

Context compaction is off by default and runs only when **both** the threshold fraction and the context window are greater than 0. Note that the **New Agent...** template sets several of these explicitly (for example, `thinking: medium`, `memory: true`, `max_iterations: 30` and compaction at 0.8 of 200000 tokens) — see [AI Fusion Studio Overview](/articles/AI_fusion/03_studio/01_studio_overview.md#what-the-new-agent-template-contains). For details on each setting, see the [agent.yaml Reference](/articles/AI_fusion/02_agents/02_agent_yaml_reference.md) and [Sessions and Context](/articles/AI_fusion/02_agents/08_sessions_and_context.md).


## Editing Sub-Agents

A file under `Subagents/` opens in the same editor, with these differences:

- There is no **Preview** tab — sub-agents run only through their parent.
- The **Subagents** and **Planning** cards are hidden (delegation is single-level, and sub-agents never plan).
- **Response guidelines** carries the *Not in use yet* note.
- **Settings** fall back to the parent agent's effective values, labeled **inherited**.


## After You Edit

Saving `agent.yaml` changes the file only. Deploy the agent's LU (a soft deploy is enough) before testing the change in **Preview** — Preview talks to the deployed agent. See [When Changes Take Effect](/articles/AI_fusion/03_studio/01_studio_overview.md#when-changes-take-effect).

**Next article:** [Preview and Trace](/articles/AI_fusion/03_studio/03_preview_and_trace.md)
