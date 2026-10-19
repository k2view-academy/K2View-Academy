# Sessions and Context

An agent conversation is a **session**. Everything the agent needs to continue a conversation — earlier messages, tool calls, the todo list, a pending question to the user — is stored under the session and reloaded on every turn. This article explains how sessions work, how the runtime keeps a long conversation within the model's context window, how a turn can pause for the user or be stopped, and which `settings:` keys control this behavior.

The keys mentioned here are set under `settings:` in `agent.yaml`. For the full key list, see the [agent.yaml Reference](/articles/AI_fusion/02_agents/02_agent_yaml_reference.md).



## Sessions

A session is identified by a **sessionId** that the caller supplies on every request. The sessionId is the instance ID of the `aifusion` Data Product (LU): the conversation's messages, executions, tool calls, token usage, plan and todos are all stored in that session's micro-database.

- **Start a new conversation** by sending a new, unique sessionId (a UUID is a good choice).
- **Continue a conversation** by sending the same sessionId again. The runtime loads the session's history and the model sees the earlier turns.
- A session belongs to one agent. Use a new sessionId when you switch agents.

### One Request at a Time

A session processes one request at a time. If a request arrives while another turn of the same session is still running, it is rejected with the error `A request is already being processed for this session`. On the REST API this is returned as an HTTP error before the stream opens. Wait for the running turn to finish (its `done` event), or stop it, before sending the next message.

If a previous turn was killed mid-flight (for example, the server restarted), the session is marked as aborted. The next request repairs the unfinished turn and proceeds normally, so a session never stays locked.

### Turns and Steps

Each request opens a **step** (a row in the session's `STEP` table) that records the user message, the final answer, the timestamp and the time to first token. A step is what the end user sees as one question and one answer; the [feedback API](/articles/AI_fusion/05_observation/06_feedback.md) addresses messages by their step order.

Within a step the agent may run many **iterations**: each iteration is one LLM call, optionally followed by tool calls. The loop ends when the model answers without calling a tool, when a tool ends the turn (such as `ask_user` or `delegate`), or when a limit is reached (see [Limits](#limits)).

The `step_start` hook runs once per step, before the model sees the user message. A `step_end` hook can ask for a continuation, which re-runs the loop within the same step. See [Hooks and Agent Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md).



## Conversation History

At the start of each turn the runtime rebuilds the conversation it sends to the model:

1. The system prompt, assembled from `agent.yaml` (including skills, sub-agent catalog, planning, response guidelines and constraints).
2. The session's stored messages from earlier turns.
3. The new user message.

### Lean History

With `lean_history: true` (the default), the replay of earlier turns keeps only the conversational messages — user messages and the assistant's text answers — and leaves out the tool-call requests and tool results of earlier turns. This keeps long sessions small and cheap. The full trail is still stored and is visible in the trace.

Leaving out earlier tool results means that facts the agent looked up in a previous turn are available to the model only as far as its earlier answer stated them. If your agent must reuse raw tool output across turns, set `lean_history: false`.

Lean history is never applied when the history ends with a tool call that has no result yet (for example, a turn paused on `ask_user`), so the turn can resume coherently.

### Context Compaction

For very long sessions you can enable **automatic compaction**: when the conversation approaches the model's context window, the older part of the history is summarized by an LLM call and replaced with one summary message. The system prompt and the most recent messages are kept verbatim.

<table>
<tbody>
<tr>
<td><strong>Setting</strong></td>
<td><strong>Default</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>compaction_threshold_fraction</code></td>
<td><code>0</code> (disabled)</td>
<td>Fraction of the context window at which compaction triggers, for example <code>0.8</code>.</td>
</tr>
<tr>
<td><code>compaction_context_window_tokens</code></td>
<td><code>0</code> (disabled)</td>
<td>The model's context window, in tokens, for example <code>200000</code>. Set it explicitly: when <code>llm_interface</code> is <code>default</code>, the runtime cannot know which model will answer.</td>
</tr>
<tr>
<td><code>compaction_keep_recent_messages</code></td>
<td><code>20</code></td>
<td>How many of the most recent messages are kept verbatim. Everything older is folded into the summary.</td>
</tr>
<tr>
<td><code>compaction_interface</code></td>
<td>The agent's <code>llm_interface</code></td>
<td>The LLM interface used for the summarization call. Point it at a cheaper model if you like.</td>
</tr>
</tbody>
</table>

How it behaves:

- Compaction runs **only when both** `compaction_threshold_fraction` and `compaction_context_window_tokens` are greater than 0. The trigger is `fraction × window` tokens, measured against the input size of the session's previous LLM call.
- It runs at the start of a turn, before the first LLM call. It does not run on a turn that resumes a paused `ask_user`.
- A tool call and its result are never split: the boundary between summarized and verbatim messages always falls between complete tool exchanges.
- A checkpoint is stored in the session, so later turns load the compacted view instead of summarizing again.
- Compaction is fail-open: if the summary call fails, the turn proceeds with the full history.
- Every compaction changes the prompt prefix and therefore misses the prompt cache for one turn. Keep the threshold high (for example `0.8`) so it fires rarely.

```yaml
settings:
  compaction_threshold_fraction: 0.8
  compaction_context_window_tokens: 200000
  compaction_keep_recent_messages: 30
  compaction_interface: summarizer      # optional: a cheaper llm:// interface
```



## Chat Naming

After the first assistant reply, the runtime generates a short title for the conversation with a separate LLM call and stores it with the session. The title is the chat name returned by the `chats` API and shown in chat history lists.

<table>
<tbody>
<tr>
<td><strong>Setting</strong></td>
<td><strong>Default</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>chat_naming</code></td>
<td><code>true</code></td>
<td>Generate and store a chat title. Set to <code>false</code> to skip the extra LLM call.</td>
</tr>
<tr>
<td><code>chat_name_interface</code></td>
<td>The agent's <code>llm_interface</code></td>
<td>The LLM interface used for the title call.</td>
</tr>
</tbody>
</table>

The title call runs after the turn's terminal `done` event, so it adds no latency to the answer. An existing name is never overwritten; if naming fails, the next turn tries again. Turns that pause on `ask_user` and sub-agent runs are not named. A naming failure never fails the turn.



## Asking the User: Pause and Resume

Every agent has the built-in `ask_user` tool. When the model needs a decision or a clarification, it calls `ask_user` and the turn **pauses**:

- The client receives an `ask_user` event with the questions, followed by the normal end of the turn.
- The session is recorded as waiting for user input (`PENDING_USER_INPUT`).
- The **next message** sent to the session — whatever its content — is taken as the answer. It is delivered to the model as the result of the pending `ask_user` call, not as a new user message, and the agent continues the same piece of work.

Question rules, enforced by the tool definition:

<table>
<tbody>
<tr>
<td><strong>Item</strong></td>
<td><strong>Rule</strong></td>
</tr>
<tr>
<td>Questions per call</td>
<td>1 to 4 independent questions.</td>
</tr>
<tr>
<td>Options per question</td>
<td>2 to 4 options, each with a short <code>label</code> and an optional <code>description</code>. The UI always adds a free-text "Other" choice, so the model must not add one.</td>
</tr>
<tr>
<td><code>header</code></td>
<td>A short chip label of at most 16 characters, without <code>:</code> or line breaks. It keys the answer; when omitted, Q1, Q2… are used.</td>
</tr>
<tr>
<td><code>multiSelect</code></td>
<td>When <code>true</code>, the user may pick several options; their labels arrive comma-joined.</td>
</tr>
</tbody>
</table>

The answer arrives as one line per question, in the form `<header>: <selected label(s) or free text>`.

Because the pause is stored server-side, a client that reloads can ask whether a session is waiting and what it asked, using `GET /api/v1/session-status?sessionId=...`. The response contains `pending`, the structured `questions`, a plain-text `question`, and the `toolCallId`. See [Invoking Agents](/articles/AI_fusion/02_agents/09_invoking_agents.md#session-status).

Only one answer is accepted per question. If two answers to the same question arrive at the same time, one proceeds and the other is rejected (for example with `The pending question has already been answered for this session`). Once the question is answered, the session is no longer waiting, and the next message starts a new turn.

A question asked by the planner or by a sub-agent that received the turn through `delegate` pauses the whole turn in the same way. A question from a sub-agent called through `task` is not surfaced to the user.



## Todos

When planning is enabled, the agent keeps a todo list of the steps it is executing (see [References and Planning](/articles/AI_fusion/02_agents/05_references_and_planning.md)). Todos belong to the **piece of work in flight**, not to the whole session:

- A new user request starts with an empty todo list; the previous request's todos are dropped.
- A turn that resumes after `ask_user` restores the todos, because it continues the same work.

Each change to the list is streamed to the client as a `todo` event (main agent only).



## Stopping a Turn

A client can stop a running turn with `POST /api/v1/stop-session?sessionId=...`. The stop is cooperative:

- The stream ends with a terminal `done` event carrying `"aborted": true`. If part of the answer had already streamed, the event's `answer` field holds that partial text. No `error` event is sent.
- The partial answer is kept in the session, followed by the marker `<ABORTED BY USER>`, so the transcript shows what the user saw.
- Tool calls that were in flight are closed with placeholder results, and the tokens consumed by completed iterations are recorded.
- The session is released, and the next message starts a new turn normally.



## Snapshots of Session Data

AI Fusion can capture snapshots of the Data Product instances an agent reads during a session, so a real conversation can later be turned into an evaluation case that replays against the same data. See [Snapshots and Production Import](/articles/AI_fusion/04_evaluation/04_snapshots_and_production_import.md).

`snap_session_mode` decides which sessions are snapshotted:

<table>
<tbody>
<tr>
<td><strong>Value</strong></td>
<td><strong>Behavior</strong></td>
</tr>
<tr>
<td><code>none</code> (default)</td>
<td>No snapshots.</td>
</tr>
<tr>
<td><code>all</code></td>
<td>Every session is snapshot-enabled.</td>
</tr>
<tr>
<td><code>frequency</code></td>
<td>Sampling by session count: roughly one session in every <code>snap_frequency_limit</code> sessions (default <code>10</code>) is snapshot-enabled.</td>
</tr>
<tr>
<td><code>period</code></td>
<td>Sampling by time: at most one snapshot-enabled session per period of <code>snap_period_value</code> (default <code>15</code>) <code>snap_period_unit</code> (<code>minutes</code> or <code>hours</code>, default <code>minutes</code>).</td>
</tr>
</tbody>
</table>

Two environments override the setting: sessions run from AI Fusion Studio always use `all`, and evaluation runs always use `none`. If the sampling decision cannot be made, snapshots are skipped for that turn.

The snapshot itself is taken where the session loads the entity's data — the `GetWithSnap` actor in a `step_start` hook flow.



## Memory

AI Fusion has two memory-related features. Only the second is a working, model-facing mechanism today.

### settings.memory (reserved)

The `memory` setting (default `false`) enables a per-session store of memory blocks. In the current release no tool lets the model read or write these blocks and they are not added to the prompt, so turning the setting on has no visible effect on the agent's behavior. Treat it as reserved and leave it off.

### Agent Memory Flows

For memory that the model can use, give the agent the shared memory flows as tools. They are Broadway flows under `SharedObjects/Broadway/aifusion/memory` and can be listed under `tools:` like any flow tool:

<table>
<tbody>
<tr>
<td><strong>Flow</strong></td>
<td><strong>Purpose</strong></td>
</tr>
<tr>
<td><code>SaveAgentMemory</code></td>
<td>Saves one record — a user-facing <code>report</code> (markdown), an opaque <code>data</code> payload for downstream consumers, and an agent-facing <code>description</code> — and returns its <code>memory_id</code>.</td>
</tr>
<tr>
<td><code>LoadAgentMemoryReport</code></td>
<td>Returns a record's report only. The data payload is deliberately not returned, so verbose JSON and internal identifiers do not re-enter the model's context.</td>
</tr>
<tr>
<td><code>LoadAgentMemory</code></td>
<td>Returns the whole record, including the data payload.</td>
</tr>
<tr>
<td><code>GetRecentMemories</code></td>
<td>Lists the most recent records (ID and description only, default 5) so the model can choose which one to load.</td>
</tr>
</tbody>
</table>

```yaml
tools:
  - query
  - SaveAgentMemory
  - LoadAgentMemoryReport
  - GetRecentMemories
```

Records are stored in the session's `aifusion` instance (`agent_memory` table). They persist across the turns of that session and are removed with the session by the retention cleanup; they are not shared between sessions.



## Observability

With `observability: true` (the default), the runtime records each run in the session:

- **Executions** — one per agent or sub-agent run, with status (for example `DONE`, `ERROR`, `PENDING_USER_INPUT`), duration and the parent tool call that spawned it.
- **Messages** — every message exchanged with the model, which is also the history replayed on the next turn.
- **Tool calls** — name, arguments, result and duration.
- **Usage** — input, output, cache-read and cache-write tokens and duration for each LLM call.
- Session-level aggregates in the Insights database, used by the Observation dashboard.

This data feeds the trace view in Studio, the Observation module and Evaluation. Because the stored messages are also the conversation history, turning observability off means earlier turns are not replayed to the model and the session has no trace. Keep it on unless you have a specific reason. See [Observation](/articles/AI_fusion/05_observation/README.md).

`logging_level` (`off`, `debug`, `info`, `warn`; default `info`) controls the agent's server log output separately.



## Limits

<table>
<tbody>
<tr>
<td><strong>Setting</strong></td>
<td><strong>Default</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>max_iterations</code></td>
<td><code>20</code></td>
<td>Maximum LLM iterations per turn. When the limit is reached, further tool calls are blocked and the model is asked for its final answer; one extra iteration is allowed for that answer. A model that keeps calling tools after that fails the turn.</td>
</tr>
<tr>
<td><code>max_retries</code></td>
<td><code>1</code></td>
<td>How many times a failing tool call is retried automatically before the error is returned to the model.</td>
</tr>
<tr>
<td><code>max_step_continuations</code></td>
<td><code>3</code></td>
<td>Maximum number of times a <code>step_end</code> hook may re-run the loop in one turn. <code>0</code> disables continuations.</td>
</tr>
</tbody>
</table>

Sub-agents apply their own limits from their own `settings:`.

**Next article:** [Invoking Agents](/articles/AI_fusion/02_agents/09_invoking_agents.md)
