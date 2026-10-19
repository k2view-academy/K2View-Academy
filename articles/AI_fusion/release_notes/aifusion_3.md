# AI Fusion 3.x Release Notes

Release notes of the **aifusion** extension, versions 3.x. The newest version is listed first.

For compatibility with Fabric and with the other AI Fusion extensions, see [AI Fusion Versions](/articles/Product_Versions/AI_Fusion_versions.md).

<ul>
<li><a href="#v3.0.8">3.0.8</a> — September 29, 2026</li>
<li><a href="#v3.0.7">3.0.7</a> — September 28, 2026</li>
<li><a href="#v3.0.5">3.0.5</a> — August 24, 2026</li>
<li><a href="#v3.0.4">3.0.4</a> — August 18, 2026</li>
<li><a href="#v3.0.0">3.0.0</a> — August 2026</li>
</ul>



<a id="v3.0.8"></a>
## Version 3.0.8

**Released:** September 29, 2026 · **Minimum Fabric version:** 8.4.0 (8.5.2 recommended)

### New Features

- **Agent delegation.** A new `delegate` tool hands the whole turn to a named sub-agent. The sub-agent's reply goes to the user as the final answer, so a specialist's answer is no longer written twice — once by the specialist and again by its parent. Enable it per agent with `delegate: true`; it is off by default, and existing agents are not affected. `task` is unchanged and remains the right route when the parent needs the result to continue its own work. See [Sub-Agents and Delegation](/articles/AI_fusion/02_agents/06_subagents_and_delegation.md).
- **Per-sub-agent routing.** Each entry under `sub_agents:` accepts `invoke: both | task | delegate` (default `both`). The sub-agent catalog marks entries that accept only one route, and calling a sub-agent by a route it does not accept fails with a message naming the allowed one. An agent whose enabled sub-agents are all `invoke: task` is not offered the `delegate` tool.
- **`response_prompt`** — house style for the user-facing reply, as a top-level key, appended under `## Response Guidelines`. It applies to the whole turn: on `delegate` it replaces the sub-agent's own guidelines, and along a chain of delegations the original turn owner's guidelines are forwarded. A `task` sub-agent does not receive it.
- **`constraints`** — non-negotiable domain rules ("never state an APR without its effective date"). They apply on every route, and on `delegate` the caller's constraints are added to the sub-agent's own, so a delegation can narrow what may be said but never widen it. Rendered last in the system prompt. Accepts a YAML list or a text block. The planner and widget sub-agents inherit the chain.

### Improvements and Fixes

- The terminal `done` event now carries the turn's final answer, so clients no longer have to reconstruct it from the streamed text. Saved test cases, shared chats and eval transcripts contain the reply alone.
- Every SSE event is tagged with the ID of the agent execution that produced it (`agent_id`), so clients can attribute events to the main agent or to a sub-agent.
- A delegation is retried only when the sub-agent produced no output, so a partial specialist answer is never repeated.
- The answer produced by a terminal tool (such as `delegate`) is saved in the history, so the next turn can see it.
- An agent whose own configuration cannot be read now fails clearly instead of running with partial settings.



<a id="v3.0.7"></a>
## Version 3.0.7

**Released:** September 28, 2026 · **Minimum Fabric version:** 8.4.0 · Released together with [AI Fusion Studio 1.0.0](/articles/AI_fusion/release_notes/aifusion_studio.md#v1.0.0)

### New Features

- **Planning from written procedures.** The planner reads the agent's `References/Procedures` pack (`find_procedures` / `load_procedure`, with a catalog of the available procedures in its prompt) instead of searching the agent's own earlier plans. With no Procedures pack, it plans directly from the agent's capabilities, without an extra round trip. See [References and Planning](/articles/AI_fusion/02_agents/05_references_and_planning.md).
- **Persisted plans and one live to-do list.** Plans and to-dos are stored with the session; the client shows a single live to-do list per turn.
- **Skills catalog as the single skill surface.** The `## Available Skills` catalog in the prompt is the allow-list for `load_skill`. Agents with the `query` tool are told to load the relevant schema skills before writing SQL, and to answer cross-domain questions with one JOIN. See [Skills](/articles/AI_fusion/02_agents/04_skills.md).
- **`testRun` web service** (`POST agent/{luName}/{agentName}/testRun`, Studio only) — runs one question through an agent and returns the session ID and answer. Used by the Studio test auto-generator.

### Fixes

- **`settings.llm_interface` is honored.** Agents ran on the default interface even when `agent.yaml` named another one. The interface is now resolved in this order: the per-turn `interface` input, then `settings.llm_interface`, then `llm://default`. A blank `llm_interface` falls back to the default for sub-agents too.
- `tool_use_start` is sent before a flow tool runs (not after it), and tool cards left open are closed when the stream ends.
- A `task` fork of the main agent no longer writes into the client stream or ends the turn early.
- Resuming a turn paused on `ask_user` is safer when answers arrive concurrently: only one answer resumes the turn, and a failed status read is no longer reported as "already answered".
- Evaluation: fixed custom dimension names, overlapping results windows and the evaluation status query.
- Evaluation flows no longer run a soft deploy on the server. Studio triggers the soft deploy of the agent's Data Product before runs and after suite changes, so evaluation also works in environments without Studio.
- The aifusion deploy creates missing insights tables on every deploy (not only the first), so tables added in new versions reach existing environments, and the column migrations no longer log errors.
- Importing a shared conversation duplicates its snapshots under the new session.
- The chat widget calls the agent directly and is isolated in a shadow DOM, so host-page styles do not affect it.
- Security update of a web-application library.



<a id="v3.0.5"></a>
## Version 3.0.5

**Released:** August 24, 2026

### New Features

- **`AgentInvoke` actor.** Runs an AI Fusion agent turn from any flow in any Data Product and returns its final answer. Set `agent`, `luName` and `interface` on the actor; link `content`, `sessionId` and `initParams`. Leave `luName` empty for the `aifusion` Data Product and `interface` empty to use the agent's configured interface. The invoked agent runs a full turn — session state, history, hooks, tools and observability persist under `sessionId`, as for an API call. See [Invoking Agents](/articles/AI_fusion/02_agents/09_invoking_agents.md).
- **New thinking levels `xhigh` and `max`**, in addition to `off`, `low`, `medium` and `high`, for agent `settings.thinking`. Each connector maps the level to its provider's API. `xhigh` is usually the best choice for agentic, tool-using agents on Claude. Requires connector versions that support them (see [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md)); older connectors treat the new levels as `off`.
- The `thinking` dropdown of the `LLMInvoke`, `LLMAgentInvoke` and `LLMAgent` actors offers the two new levels.



<a id="v3.0.4"></a>
## Version 3.0.4

**Released:** August 18, 2026

### Changes

- The extension now ships complete folders (`common/ai`, `common/batch`, the aifusion Broadway and Java folders) instead of hand-maintained file lists.
- Vector pipeline flows moved to `Broadway/vectors`, and the `fileToEmbedding` flow to `Broadway/chunker`.
- Helpers shared with the LLM connectors moved to a new `common/llmprovider` package. The core extension and the connectors no longer ship any common file, so they can be installed and upgraded at different versions without overwriting each other.

### Removals

- Retired 1.x/2.x code is deleted: the legacy `Chat` implementation and the `ChatPromptBuilder`, `ChatSessionParams`, `SetChatSessionParam`, `WriteToOutputStream`, `PrependParamsToStream` and `SaveState` actors.
- **Breaking:** `SaveState` and `PrependParamsToStream` were still shipped in 3.0.0–3.0.3. Flows that call them no longer resolve. Version 3.0.3 is the last version that contains them.

### Fixes

- The `EvalRead` and `SuiteRead` actors now ship with their implementation. Previously, evaluation worked only when an LLM connector extension happened to supply these classes.
- The `LLMBatchStart` actor now ships with its implementation and resolves in all installations.



<a id="v3.0.0"></a>
## Version 3.0.0

**Released:** August 2026

The first release of the AI Fusion 3 agent platform: declarative agents (`agent.yaml`) with tools, skills, sub-agents, reference packs, planning, hooks and the streaming `agent` web service. See [What's New in AI Fusion 3](/articles/AI_fusion/02_whats_new_in_v3.md).

### Packaging

- Demo content ships in the separate **aifusion-demo** extension. The core extension no longer includes the vector ingestion and search pipelines, the vector example flows, the `fileToEmbedding` flow or the domain demos.
- The chunker and its Flexmark dependency were removed from the extension.

### Deprecations

- The `Chat` actor and the `chat` web service are superseded by the agent platform. The `chat` web service is disabled, and the `Chat`, `ChatPromptBuilder`, `ChatSessionParams`, `SetChatSessionParam`, `WriteToOutputStream` and `PrependParamsToStream` actors are excluded from the extension.

### Breaking Changes

- The `Chat` actor and the `chat` endpoint are no longer available. Use the `agent` web service or the agent actors.
- Vector pipelines, the chunker and the domain demos are no longer part of `aifusion`. Install **aifusion-demo** to keep them.
- Agents built as Broadway agentic flows in 2.x must be re-implemented as declarative agents. See [Moving from AI Fusion 2.x](/articles/AI_fusion/02_whats_new_in_v3.md#moving-from-ai-fusion-2x).
