# AI Fusion 3 Architecture

AI Fusion 3 runs entirely inside K2view Fabric. Agents are defined as files in your Data Products, executed by an agent runtime delivered in the `aifusion` Data Product, and reached through Fabric web services. LLM providers are accessed through Fabric AI interfaces, and every conversation is persisted for trace, evaluation and observation.

This article describes the components and how a request flows through them.



## Components

```text
 ┌──────────────────────────┐   ┌──────────────────────────┐   ┌─────────────────────┐
 │ AI Fusion Studio         │   │ Observation web app      │   │ Your application /  │
 │ (Agent Editor, Preview,  │   │ (dashboard, conversation │   │ channel (REST, SSE) │
 │  Eval Editor)            │   │  review)                 │   │                     │
 └────────────┬─────────────┘   └────────────┬─────────────┘   └──────────┬──────────┘
              └───────────────┬──────────────┴────────────────────────────┘
                              ▼
               Fabric web services (k2_ws):  agent (SSE), stop-session, agents,
               chats, feedback, observation, evaluation, trace-history ...
                              ▼
 ┌────────────────────────────────────────────────────────────────────────────────────┐
 │ Agent runtime  (aifusion Data Product, flow aifusion.Agent)                         │
 │  session = aifusion LU instance · hooks · tools · sub-agents · skills · references   │
 └───────┬───────────────────┬──────────────────────┬──────────────────────┬──────────┘
         ▼                   ▼                      ▼                      ▼
  Agent definitions    Business Data Products   LLM interfaces          MCP servers
  <LU>/AI/Agents/...   (entity micro-DB, flows) (Anthropic, Bedrock,    (Mcp interface)
                                                 OpenAI, Google, Ollama)
         │
         ▼
  Persistence: aifusion LU (per-session trace) + insights DB (sessions, signals, evaluation)
```

<table>
<tbody>
<tr>
<td><strong>Component</strong></td>
<td><strong>Role</strong></td>
</tr>
<tr>
<td><strong>Agent definitions</strong></td>
<td>Files under <code>&lt;LU&gt;/AI/</code> in each Data Product: <code>agent.yaml</code>, sub-agents, skills, reference packs, eval suites and observation configuration. Fabric captures them when the LU is deployed. See <a href="/articles/AI_fusion/02_agents/01_agents_overview.md">Agents Overview</a>.</td>
</tr>
<tr>
<td><strong>aifusion Data Product</strong></td>
<td>Delivered by the <code>aifusion</code> extension. Contains the agent runtime, the session tables, the observation and evaluation flows, and the Observation web application.</td>
</tr>
<tr>
<td><strong>Web services</strong></td>
<td>Delivered in the <code>k2_ws</code> LU. They expose the agent API, session management, feedback, observation, evaluation and trace endpoints. See <a href="/articles/AI_fusion/02_agents/09_invoking_agents.md">Invoking Agents</a>.</td>
</tr>
<tr>
<td><strong>Business Data Products</strong></td>
<td>The LUs that hold your entity data (customer, account, policy). A hook flow loads the relevant instance into the session, and the agent queries it with the built-in <code>query</code> tool or calls its Broadway flows as tools.</td>
</tr>
<tr>
<td><strong>LLM connectors</strong></td>
<td>Separate extensions that add AI interface types for each provider. Agents refer to an interface by name. See <a href="/articles/AI_fusion/02_agents/10_llm_connectors.md">LLM Connectors</a>.</td>
</tr>
<tr>
<td><strong>MCP client</strong></td>
<td>The optional <code>mcp</code> extension adds the MCP interface type, so agents can use tools from MCP servers.</td>
</tr>
<tr>
<td><strong>Pipeline</strong></td>
<td>The <code>pipeline</code> extension executes evaluation runs and observation analysis pipelines.</td>
</tr>
<tr>
<td><strong>AI Fusion Studio</strong></td>
<td>The <code>aifusion-studio</code> extension for K2view Web Studio. See <a href="/articles/AI_fusion/03_studio/README.md">AI Fusion Studio</a>.</td>
</tr>
</tbody>
</table>



## The Life of a Request

1. **A client sends a message.** Studio Preview or your own application calls the `agent` web service with the agent name, its Data Product, a session ID, the user's message and, optionally, the agent inputs (`initParams`, for example `{"customerId":"1001"}`). The response is a Server-Sent Events stream.
2. **The session is opened.** The runtime gets the aifusion LU instance whose ID is the session ID, and claims the session so that only one request at a time is processed per conversation. A new turn (step) is recorded.
3. **The agent is loaded.** The runtime reads the agent's `agent.yaml` from the deployed Data Product, resolves its tools, skills, sub-agents and hooks, and builds the system prompt.
4. **Hooks prepare the context.** The `step_start` hook flow typically loads the customer's instance of the business Data Product, so its micro-database is available to the `query` tool, and may enrich the user message.
5. **The reasoning loop runs.** The runtime calls the LLM through the agent's interface. The model answers or calls tools: SQL queries over the entity data, Broadway flow tools, MCP tools, skills, reference search, planning, widgets, or sub-agents. Each tool result is fed back until the model produces a final answer, or a limit is reached.
6. **The answer is streamed.** Text, reasoning, tool activity, sub-agent activity, to-do lists, questions to the user and widgets are streamed as SSE events, ending with a `done` event.
7. **Everything is recorded.** Messages, executions, tool calls and token usage are written to the session's aifusion LU instance; session-level metrics are written to the insights database. The `step_end` hook can post-process the answer.
8. **Later, Observation analyzes the conversation.** An analysis run summarizes the session and classifies it on the configured signals for the dashboard. See [Observation](/articles/AI_fusion/05_observation/README.md).



## Data Stored

### Per Session — the aifusion LU

Each conversation is one instance of the **aifusion** LU; the instance ID is the session ID. Its main tables are:

<table>
<tbody>
<tr>
<td><strong>Table</strong></td>
<td><strong>Content</strong></td>
</tr>
<tr>
<td><code>chat</code></td>
<td>Root table of the session.</td>
</tr>
<tr>
<td><code>step</code></td>
<td>One row per turn: the user message, the final answer, duration and time to first token.</td>
</tr>
<tr>
<td><code>executions</code></td>
<td>Each agent and sub-agent execution within a turn, with its status and inputs.</td>
</tr>
<tr>
<td><code>messages</code></td>
<td>All system, user, assistant and tool messages — the conversation history replayed on the next turn.</td>
</tr>
<tr>
<td><code>tool_calls</code></td>
<td>Executed tool calls with arguments, responses and durations.</td>
</tr>
<tr>
<td><code>usage</code></td>
<td>Token usage per LLM call (input, output, cache read/write) and the interface used.</td>
</tr>
<tr>
<td><code>feedback</code></td>
<td>User ratings and notes per turn.</td>
</tr>
<tr>
<td><code>compaction</code>, <code>plans</code>, <code>tasks</code>, <code>summary</code>, <code>snaps</code>, <code>memory_blocks</code>, <code>agent_memory</code></td>
<td>Context-compaction checkpoints, plans and to-dos, summaries, snapshot catalog and memory records.</td>
</tr>
</tbody>
</table>

### Across Sessions — the Insights Database

Session-level data used by Observation and Evaluation is kept in a relational database, SQLite by default (`assurance` interface) or PostgreSQL. The tables are created by the deploy of the aifusion LU. See [Insights Database Schema](/articles/AI_fusion/05_observation/04_insights_db_schema.md).



## Where Agents Live

Agents belong to the Data Product whose data they work on:

```text
<LU>/AI/
├── Skills/                     LU-wide skills
└── Agents/<agent>/
    ├── agent.yaml              the agent definition
    ├── Skills/                 agent-private skills
    ├── Subagents/              sub-agent definitions (*.yaml)
    ├── References/<pack>/      reference documents (e.g. KB/, Procedures/)
    ├── Evals/                  evaluation suites (*.csv)
    └── Observation/            signals.yaml, widgets.yaml
```

Because the definitions are files in the project, they are version-controlled with the rest of the implementation and promoted between environments with a regular deploy. Changes take effect after the LU is deployed (a soft deploy is enough).



**Next article:** [Installation and Setup](/articles/AI_fusion/04_installation_and_setup.md)
