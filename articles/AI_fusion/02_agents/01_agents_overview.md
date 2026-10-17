# Agents Overview

In AI Fusion 3, an **agent** is a declarative definition — a YAML file and a few folders of Markdown — stored inside the Data Product (LU) whose data it works on. You describe *what* the agent is and *what it may use*; the AI Fusion runtime takes care of *how*: running the reasoning loop with the LLM, calling tools, delegating to sub-agents, streaming the answer and recording every step.



## Core Concepts

<table>
<tbody>
<tr>
<td><strong>Concept</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Agent</strong></td>
<td>A top-level assistant defined by <code>&lt;LU&gt;/AI/Agents/&lt;agent&gt;/agent.yaml</code>. It has a role (system prompt), rules, tools, skills and settings. Clients call it by name and Data Product.</td>
</tr>
<tr>
<td><strong>Tool</strong></td>
<td>An action the LLM can take: the built-in <code>query</code> tool (SQL over the entity's data), a Broadway flow, an MCP server tool, or platform tools such as <code>ask_user</code>. See <a href="/articles/AI_fusion/02_agents/03_tools.md">Tools</a>.</td>
</tr>
<tr>
<td><strong>Skill</strong></td>
<td>A Markdown document of instructions or schema description that the agent loads on demand, or always (auto-load). See <a href="/articles/AI_fusion/02_agents/04_skills.md">Skills</a>.</td>
</tr>
<tr>
<td><strong>Reference pack</strong></td>
<td>A folder of Markdown documents (knowledge base, procedures) that the agent can search and cite. The <code>Procedures</code> pack also guides planning. See <a href="/articles/AI_fusion/02_agents/05_references_and_planning.md">References and Planning</a>.</td>
</tr>
<tr>
<td><strong>Sub-agent</strong></td>
<td>A specialist agent the main agent can call for part of the work (<code>task</code>) or hand the whole turn to (<code>delegate</code>). See <a href="/articles/AI_fusion/02_agents/06_subagents_and_delegation.md">Sub-Agents and Delegation</a>.</td>
</tr>
<tr>
<td><strong>Hook</strong></td>
<td>A Broadway flow that runs at a point of the turn: before the user message, after the answer, before or after a tool call, or on error. Hook flows also define the agent's inputs. See <a href="/articles/AI_fusion/02_agents/07_hooks_and_inputs.md">Hooks and Agent Inputs</a>.</td>
</tr>
<tr>
<td><strong>Session</strong></td>
<td>A conversation, identified by a session ID and stored as an instance of the aifusion LU. Each user message is a <strong>turn</strong> (step). See <a href="/articles/AI_fusion/02_agents/08_sessions_and_context.md">Sessions and Context</a>.</td>
</tr>
<tr>
<td><strong>Agent inputs</strong></td>
<td>Values the caller passes when a conversation starts (<code>initParams</code>), such as the customer ID. They reach every hook and tool flow, and the LLM cannot change them.</td>
</tr>
</tbody>
</table>



## Folder Layout

```text
<LU>/AI/
├── Skills/<name>.md              LU-wide skills — available to agents that list them
└── Agents/<agent>/
    ├── agent.yaml                the agent definition
    ├── Skills/<name>.md          agent-private skills — discovered automatically
    ├── Subagents/<name>.yaml     sub-agents — discovered automatically, same schema as agent.yaml
    ├── References/<pack>/*.md    reference packs, e.g. KB/ and Procedures/
    ├── Evals/<suite>.csv         evaluation suites (authored in Studio)
    └── Observation/              signals.yaml and widgets.yaml for the Observation dashboard
```

Skills can also be defined under Shared Objects (`SharedObjects/AI/Skills/`) and shared across Data Products.

Agents are always defined in a Data Product, not in Shared Objects. Place an agent in the LU that holds the entity it serves — for example a customer-service agent in the customer LU — so the entity data, the flows it uses as tools and the agent are versioned and deployed together.



## How an Agent Works

When a message arrives, the runtime:

1. Opens the session and records a new turn.
2. Loads the agent definition from the deployed Data Product.
3. Runs the `step_start` hook — typically loading the customer's LU instance so the `query` tool can read it.
4. Builds the system prompt from the agent's `system_prompt`, the catalog of its sub-agents and skills, auto-loaded skills, planning instructions, `response_prompt` and `constraints`.
5. Runs the reasoning loop: the LLM either answers or calls tools; tool results are returned to the LLM until it produces a final answer or reaches `max_iterations`.
6. Streams the answer and the activity to the client, runs the `step_end` hook, and records messages, tool calls and token usage.



## A Minimal Agent

```yaml
description: "Answers questions about a customer's accounts and transactions"

system_prompt: |
  You are a banking assistant for one customer. Answer only from the customer's data,
  using the `query` tool. If the data does not contain the answer, say so.

hooks:
  step_start: LoadCustomerScope      # loads the customer instance; declares the customerId input

tools:
  - query
  - current_datetime

skills:
  - dda-schema:                      # describes the account tables
      auto_load: true

settings:
  llm_interface: default
  thinking: low
```

With this file deployed, a client starts a conversation with `initParams` `{"customerId":"1001"}` and asks "What is my savings balance?". The hook loads customer 1001, the skill tells the model which tables exist, and the model answers by running SQL with `query`.

The [banking agent in the aifusion-demo extension](/articles/AI_fusion/04_installation_and_setup.md#extensions) is a complete example with six sub-agents, reference packs, planning and observation configuration.



## Creating and Changing Agents

- **In Studio** — use the AI Explorer (**New Agent...**) and the form-based Agent Editor. See [AI Fusion Studio](/articles/AI_fusion/03_studio/README.md). Studio AI can also build an agent for you from the Data Product schema.
- **As files** — the definitions are plain YAML and Markdown, so you can edit them in any editor and keep them in Git with the rest of the project.

Fabric reads agent definitions from the deployed Data Product. **After changing an agent, deploy its LU** (a soft deploy is enough) for the change to take effect.



## Design Guidelines

- **One agent per audience and entity.** A customer-service agent on the customer LU; a separate agent for internal staff if their permissions and tone differ.
- **Keep the main prompt short.** Put table descriptions and long procedures in skills and reference packs that load only when needed.
- **Split by domain with sub-agents** when a single prompt would need many tools and schemas. Each specialist gets only the tools and skills of its domain.
- **Put rules in `constraints`, style in `response_prompt`.** Constraints are never dropped when a turn is delegated.
- **Load data in hooks, not prompts.** Use the `step_start` hook to load the entity and pass trusted inputs; never ask the LLM to supply identifiers it should not choose.
- **Test as you build.** Save good conversations from the Preview as test cases and run them after every change.



**Next article:** [agent.yaml Reference](/articles/AI_fusion/02_agents/02_agent_yaml_reference.md)
