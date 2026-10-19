# What's New in AI Fusion 3

AI Fusion 3 is a re-architecture of the platform. The infrastructure, the agent model, and the development experience in Studio have all changed. Observation keeps its concepts but becomes agent-aware.

This article summarizes the changes and what they mean if you are moving from AI Fusion 1.x or 2.x. The documentation of earlier versions remains available in the [legacy section](/articles/AI_fusion/legacy_v1_v2/README.md).



## At a Glance

<table>
<tbody>
<tr>
<td><strong>Area</strong></td>
<td><strong>AI Fusion 1.x / 2.x</strong></td>
<td><strong>AI Fusion 3</strong></td>
</tr>
<tr>
<td>Agent definition</td>
<td>Broadway agentic flows built from actors such as <code>Chat</code>, <code>ChatPromptBuilder</code> and <code>LLMAgent</code>; orchestrator, planner and refiner flows.</td>
<td>Declarative agents: an <code>agent.yaml</code> file plus folders for skills, sub-agents, references, evals and observation, inside a Data Product (<code>&lt;LU&gt;/AI/Agents/&lt;agent&gt;/</code>). The runtime runs the reasoning loop.</td>
</tr>
<tr>
<td>Runtime entry point</td>
<td>The <code>chat</code> web service and the <code>Chat</code> actor.</td>
<td>The streaming <code>agent</code> web service (Server-Sent Events), the <code>aifusion.Agent</code> flow and the <code>AgentInvoke</code> actor.</td>
</tr>
<tr>
<td>Knowledge and procedures</td>
<td>Vector-DB pipelines for KB, procedures and plan examples.</td>
<td>Markdown reference packs under <code>References/</code>, searched by a built-in reference-search sub-agent; the <code>Procedures</code> pack guides plan-then-execute mode. No vector database is required.</td>
</tr>
<tr>
<td>Data access</td>
<td>Custom SQL-generation and describe-domain flows.</td>
<td>The built-in <code>query</code> tool over the entity micro-database, guided by schema skills that can embed the live LU schema.</td>
</tr>
<tr>
<td>Development environment</td>
<td>Broadway editor and the Chat Playground web app.</td>
<td>The <strong>AI Fusion - Studio</strong> extension: AI Explorer, form-based Agent Editor, Preview chat with Trace, Eval Editor and AI-assisted agent building.</td>
</tr>
<tr>
<td>Evaluation</td>
<td>The web Evaluation workspace, app-scoped suites in MTables, <code>aifusion.evaluatorPipeline</code>.</td>
<td>Per-agent suites in <code>Evals/*.csv</code>, authored and run in Studio, executed by the Pipeline extension. New performance dimensions (tokens and response time).</td>
</tr>
<tr>
<td>Observation</td>
<td>Global signals and widgets.</td>
<td>Core signals and widgets plus per-agent <code>signals.yaml</code> and <code>widgets.yaml</code>; agent selector on the dashboard; feedback filters; conversation navigation.</td>
</tr>
<tr>
<td>Packaging</td>
<td>One <code>aifusion</code> extension including demos and vector pipelines.</td>
<td>Core <code>aifusion</code>, <code>aifusion-studio</code>, separate LLM connector extensions, optional <code>mcp</code> and <code>aifusion-demo</code>.</td>
</tr>
</tbody>
</table>



## New Capabilities

- **Declarative agents** — define role, rules and capabilities in YAML; no flow orchestration to maintain. See [Agents Overview](/articles/AI_fusion/02_agents/01_agents_overview.md).
- **Sub-agents and delegation** — split work across specialist agents, use them as helpers (`task`) or hand them the whole turn (`delegate`), and link agents from other Data Products. See [Sub-Agents and Delegation](/articles/AI_fusion/02_agents/06_subagents_and_delegation.md).
- **Skills** — on-demand Markdown instructions and schema descriptions, loaded only when needed to keep prompts small. See [Skills](/articles/AI_fusion/02_agents/04_skills.md).
- **Planning and reference search** — plan-then-execute mode guided by written procedures, with optional user approval, and cited answers from reference documents. See [References and Planning](/articles/AI_fusion/02_agents/05_references_and_planning.md).
- **Hooks** — Broadway flows that run before the user message, after the answer, around tool calls and on errors. Their inputs define the agent's inputs. See [Hooks and Agent Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md).
- **Response guidelines and constraints** — house style per turn, and non-negotiable rules that survive delegation.
- **Interactive turns** — the agent can ask the user structured questions (`ask_user`), show a live to-do list, and render charts, tables and links (widgets).
- **Context management** — automatic context compaction for long conversations, prompt caching, configurable reasoning (thinking) levels from `off` to `max`.
- **MCP tools** — attach any MCP server, including Fabric Data Products exposed over MCP, as agent tools.
- **AI-assisted development** — Studio AI can build an agent from a Data Product schema, generate test cases, and diagnose a bad answer from its trace.



## Removed or Replaced

- The `Chat` actor, the `chat` web service and the actors `ChatPromptBuilder`, `ChatSessionParams`, `SetChatSessionParam`, `WriteToOutputStream`, `PrependParamsToStream` and `SaveState` were removed (deprecated in 3.0.0, deleted in 3.0.4). Use the `agent` web service or the `AgentInvoke` actor instead.
- The vector ingestion pipelines, the chunker and the domain demos are no longer part of the core extension. The banking demo ships in the `aifusion-demo` extension.
- `.agent` files and earlier agent keys (`input_params`, `content_flows`, `hooks.fabric_get`, nested `tools: {built_in, custom}`, `search_reference.dir`) are obsolete. Agent inputs are now inferred from the hook flows.
- The web Evaluation workspace, `aifusion.evaluatorPipeline` and MTable-based suites are replaced by per-agent eval suites in Studio. AI-assisted "Copilot" and "Autopilot" test generation are replaced by the Studio AI test generator.
- In the insights (assurance) database, `ACTVSESSION.app_id` was renamed to `agent_name` and `customer_id` was replaced by the `INSTANCES` table. The aifusion deploy migrates existing tables.



## Moving from AI Fusion 2.x

AI Fusion 3 is not a drop-in upgrade for 2.x implementations. Agents built as Broadway agentic flows must be re-implemented as declarative agents. A typical migration path:

1. Install the AI Fusion 3 extensions — see [Installation and Setup](/articles/AI_fusion/04_installation_and_setup.md).
2. For each 2.x application, create an agent in the Data Product that holds the entity data (AI Explorer → **New Agent...**).
3. Move the system prompt and business rules into `system_prompt`, `response_prompt` and `constraints`.
4. Replace the customer-profile / data-loading logic with a `step_start` hook flow that loads the entity and declares its inputs (for example `customerId`).
5. Replace SQL-generation flows with the built-in `query` tool and schema skills; keep business operations as flow tools.
6. Convert KB and procedure documents to Markdown reference packs (`References/KB`, `References/Procedures`).
7. Recreate evaluation suites by saving conversations from the Studio Preview as test cases, or generate them with Studio AI.
8. Move custom Observation tags into the agent's `Observation/signals.yaml`.

The [Agent Builder](/articles/AI_fusion/03_studio/04_ai_assisted_development.md) in Studio AI can scaffold most of steps 2–5 from the Data Product schema.



**Next article:** [Architecture](/articles/AI_fusion/03_architecture.md)
