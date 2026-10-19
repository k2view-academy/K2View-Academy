# AI Fusion 3.x Release Notes

Release notes of the **aifusion** extension, versions 3.x. The newest version is listed first.

For compatibility with Fabric and with the other AI Fusion extensions, see [AI Fusion Versions](/articles/Product_Versions/AI_Fusion_versions.md).

<ul>
<li><a href="#v3.0.8">3.0.8</a> — September 29, 2026</li>
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



<a id="v3.0.5"></a>
## Version 3.0.5

**Released:** August 24, 2026

### New Features

- **`AgentInvoke` actor.** Runs an AI Fusion agent turn from any flow in any Data Product and returns its final answer. Set `agent`, `luName` and `interface` on the actor; link `content`, `sessionId` and `initParams`. Leave `luName` empty for the `aifusion` Data Product and `interface` empty to use the agent's configured interface. The invoked agent runs a full turn — session state, history, hooks, tools and observability persist under `sessionId`, as for an API call. See [Invoking Agents](/articles/AI_fusion/02_agents/09_invoking_agents.md).
- **New thinking levels `xhigh` and `max`**, in addition to `off`, `low`, `medium` and `high`, for agent `settings.thinking`. Each connector maps the level to its provider's API. `xhigh` is usually the best choice for agentic, tool-using agents on Claude. Requires connector versions that support them (see [LLM Connectors Release Notes](/articles/AI_fusion/release_notes/llm_connectors.md)); older connectors treat the new levels as `off`.
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
