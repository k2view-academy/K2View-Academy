# AI Fusion 3.x Release Notes

Release notes of the **aifusion** extension, versions 3.x. The newest version is listed first.

For compatibility with Fabric and with the other AI Fusion extensions, see [AI Fusion Versions](/articles/Product_Versions/AI_Fusion_versions.md).

<ul>
<li><a href="#v3.0.8">3.0.8</a> — October 19, 2026</li>
<li><a href="#v3.0.7">3.0.7</a> — September 28, 2026</li>
<li><a href="#v3.0.5">3.0.5</a> — August 24, 2026</li>
<li><a href="#v3.0.4">3.0.4</a> — August 18, 2026</li>
<li><a href="#v3.0.0">3.0.0</a> — August 2026</li>
</ul>



<a id="v3.0.8"></a>
## Version 3.0.8

**Released:** October 19, 2026 · **Minimum Fabric version:** 8.4.0 (8.5.2 recommended)

The `aifusion` extension no longer depends on the AI Fusion Studio and Pipeline extensions; install them separately. The Pipeline extension is needed to run evaluations.

### Agents

- **Agent delegation.** A new `delegate` tool lets an agent hand the turn to a sub-agent. The sub-agent's reply goes straight to the user as the final answer, so the parent no longer restates it. To enable it, set `delegate: true` at the top level of the agent; it is off by default. Use the existing `task` tool when the parent needs the sub-agent's result to finish the work itself. See [Sub-Agents and Delegation](/articles/AI_fusion/02_agents/06_subagents_and_delegation.md).
- **Choose how each sub-agent is called.** Under `sub_agents:`, set `invoke:` to `task`, `delegate` or `both` (default) to control which tools can call that sub-agent.
- **`response_prompt`.** A new top-level key for the reply's tone and format, so it no longer has to sit inside `system_prompt`. When an agent delegates, the sub-agent answers in the calling agent's style.
- **`constraints`.** A new top-level key for rules the agent must always follow, for example "show only the last four digits of a card number". Unlike `response_prompt`, constraints are never dropped: when an agent delegates, the sub-agent follows the caller's constraints as well as its own. Put tone and format in `response_prompt` and must-follow rules in `constraints`.
- **Follow-up turns after a delegation.** The sub-agent's answer is saved in the conversation history, so the next turn sees what was answered. Before this, the agent could ask again or mix up the earlier request with the new one.
- **Saved test cases, exports and eval transcripts** record the turn's actual final answer, not the agent's in-progress narration.
- **Planning agents** get the plan-execution rules automatically (work the to-dos in order, resume from the first pending to-do after asking the user). You no longer need to write them into `system_prompt`.

### Observation

- **Per-agent Observation.** The Observation dashboard has an agent selector. Choosing an agent scopes the dashboard and the conversations table to that agent's sessions, and shows the agent's own widgets, defined in `<LU>/AI/Agents/<agent>/Observation/widgets.yaml`. See [Observation Dashboard](/articles/AI_fusion/05_observation/01_observation_dashboard.md).
- **Per-agent analyzer signals.** The general signals that drive the overall dashboard (`Resolution Status`, `Sentiment`, `Topic`) still apply to every agent. An agent can add its own use-case signals in `<LU>/AI/Agents/<agent>/Observation/signals.yaml`, which its `widgets.yaml` can read, and can redefine the values of `Topic`. The analyzer no longer drops the last signal of a batch. See [Configuring Observation](/articles/AI_fusion/05_observation/03_configuring_observation.md).
- **Feedback filter.** You can filter the conversations list by user feedback.
- **Conversation review.** New navigation between conversations and an improved conversations table. The thumbs and note icons show correctly on reviewed messages. Notes open as an overlay when there is no room beside the message, follow it on scroll, and close on an outside click.
- **Failed chat analysis is reported.** A session whose analysis fails is marked as failed instead of being left in an unclear state.

### Evaluation

- **Starting an agent evaluation** gives clear errors: if the Pipeline extension is not installed, the API returns 503; if the pipeline could not be started, it returns 409.

### Breaking Changes

- `Mtable/chat_signals.csv` is renamed to `Mtable/observation_signals.csv`. If you customized `chat_signals.csv`, move those rows to `observation_signals.csv`, or to an agent's `Observation/signals.yaml` for agent-specific signals.
- Removed: the `chatSummary` web service, and the `ChatAnalytics`, `SentimentQuery`, `runChatAnalyzePipeline` and `RetrieveBatchMessages` flows.



<a id="v3.0.7"></a>
## Version 3.0.7

**Released:** September 28, 2026 · **Minimum Fabric version:** 8.4.0

- **Planner and to-do list:** plans are saved, with one live to-do list that stays in place.
- `settings.llm_interface` in the agent's YAML file is honored.
- The aifusion deploy flow no longer fails on `ALTER TABLE` errors.
- Resuming after `ask_user` is more reliable.



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
