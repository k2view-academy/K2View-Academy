# Invoking Agents

Once an agent is deployed, any client can run it. AI Fusion offers two entry points:

- The **REST API** — `/api/v1/agent` streams the answer to the client as Server-Sent Events (SSE). This is what the Studio Preview uses, and what you use to embed an agent in your own application.
- **Broadway** — the `aifusion.Agent` flow and the `AgentInvoke` actor run an agent turn from inside a flow and return the final answer, without streaming.

Both run the same runtime: sessions, history, hooks, tools and observability behave identically. This article describes the API, the stream format, the supporting endpoints and the Broadway options.



## Authentication and Permissions

The AI Fusion endpoints are standard Fabric web services, deployed in the `k2_ws` LU under the `/api/v1/` path. They use Fabric's web-service authentication — a Fabric session (for example, a logged-in browser), an API key sent as `Authorization: Bearer <key>`, a JWT, OAuth or Basic authentication. See [Fabric Web Services Security](/articles/26_fabric_security/05_fabric_webservices_security.md).

The endpoints run with the **caller's** permissions — none of them elevates privileges. Grant the calling user or API key's role permission to the web services it needs, and to the Data Products the agent's flows read. Calls made from a multi-tenant space use the tenant prefix in the path, as for any Fabric web service. See [Security and Privacy](/articles/AI_fusion/02_agents/11_security_and_privacy.md).



## Running an Agent: /api/v1/agent

`GET` or `POST /api/v1/agent` runs one turn of an agent and streams the result.

<table>
<tbody>
<tr>
<td><strong>Parameter</strong></td>
<td><strong>Required</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>agent</code></td>
<td>Yes</td>
<td>The agent name — its folder name under <code>&lt;LU&gt;/AI/Agents/</code>.</td>
</tr>
<tr>
<td><code>lu</code></td>
<td>Yes</td>
<td>The Data Product (LU) that holds the agent.</td>
</tr>
<tr>
<td><code>sessionId</code></td>
<td>Yes</td>
<td>The conversation key. Send a new ID to start a conversation and the same ID to continue it. See <a href="/articles/AI_fusion/02_agents/08_sessions_and_context.md">Sessions and Context</a>.</td>
</tr>
<tr>
<td><code>content</code></td>
<td>Yes</td>
<td>The user message. When the session is paused on <code>ask_user</code>, this is the answer.</td>
</tr>
<tr>
<td><code>initParams</code></td>
<td>No</td>
<td>A JSON object, passed as a string, with the agent's inputs, for example <code>{"customerId":"1001"}</code>. Inputs that the agent's hook flows mark as mandatory must be present, or the request fails before the stream opens with <code>Missing required input(s): ...</code>. Get each agent's inputs from the <a href="#agents-and-skills">agents</a> endpoint.</td>
</tr>
</tbody>
</table>

`initParams` may not contain the key `now`. A pinned date and time is honored only for Evaluation runs; this endpoint rejects it with HTTP 400.

The response has content type `text/event-stream`. Do not send an `Accept: text/event-stream` header — Fabric's content negotiation rejects it with HTTP 406. Omit the `Accept` header, or use the default.

### Errors Before and After the Stream Opens

- A failure **before** any event is sent — a missing parameter or required input, `now` in `initParams`, an unknown agent, or `A request is already being processed for this session` — is returned as a regular HTTP error.
- A failure **after** the stream opened is reported in the stream: an `error` event with a user-facing message, followed by `done`. The technical cause is written only to the server log. An agent's `on_error` hook can replace the message (see [Hooks and Agent Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md)).

### Example: curl

```bash
curl -N -G \
  -H "Authorization: Bearer $FABRIC_API_KEY" \
  --data-urlencode "agent=banking" \
  --data-urlencode "lu=customer_bank" \
  --data-urlencode "sessionId=6f1c2e7a-5b1d-4c39-9d0e-3a2b8f1e4c55" \
  --data-urlencode "content=What is my current checking balance?" \
  --data-urlencode 'initParams={"customerId":"1001"}' \
  "https://<fabric-host>/api/v1/agent"
```

`-G` sends the URL-encoded parameters in the query string of a `GET` request; `-N` disables output buffering so events print as they arrive.



## The Event Stream

Each event is a standard SSE frame — an `event:` line with the event name and a `data:` line with a JSON object:

```text
event: text_delta
data: {"text":"Your checking balance is ","agent_id":"6f1c2e7a-5b1d-4c39-9d0e-3a2b8f1e4c55"}

```

Every frame carries `agent_id`, the ID of the agent run that produced it. For the main agent this is the sessionId; each sub-agent run has its own ID. Use it to attribute text and tool activity to the right agent when sub-agents run. Most structured events also carry a `type` field equal to the event name and an ISO-8601 `timestamp`.

<table>
<tbody>
<tr>
<td><strong>Event</strong></td>
<td><strong>When</strong></td>
<td><strong>Payload fields</strong></td>
</tr>
<tr>
<td><code>agent_start</code></td>
<td>An agent run (main or sub-agent) begins.</td>
<td><code>agent_id</code>, <code>agent_name</code>, <code>timestamp</code></td>
</tr>
<tr>
<td><code>text_delta</code></td>
<td>The model streams answer text. Concatenate the deltas.</td>
<td><code>text</code></td>
</tr>
<tr>
<td><code>thinking_delta</code></td>
<td>The model streams reasoning text (when <code>thinking</code> is not <code>off</code>).</td>
<td><code>text</code></td>
</tr>
<tr>
<td><code>thinking_end</code></td>
<td>A reasoning block ended.</td>
<td>none besides <code>agent_id</code></td>
</tr>
<tr>
<td><code>tool_use_start</code></td>
<td>A tool call starts.</td>
<td><code>tool_name</code>, <code>tool_call_id</code>, <code>iteration</code>, <code>args</code>, <code>timestamp</code></td>
</tr>
<tr>
<td><code>tool_use_end</code></td>
<td>A tool call ends.</td>
<td><code>tool_name</code>, <code>tool_call_id</code>, <code>duration_ms</code>, <code>timestamp</code>; <code>error: true</code> when an announced tool turned out not to exist</td>
</tr>
<tr>
<td><code>tool_usage</code></td>
<td>After a tool call that itself used an LLM (for example a sub-agent or a flow with an LLM actor).</td>
<td><code>tool_call_id</code>, <code>usage</code> (<code>input_tokens</code>, <code>output_tokens</code>, <code>cache_read_tokens</code>, <code>cache_write_tokens</code>, <code>total_tokens</code>), <code>invoke_duration_ms</code></td>
</tr>
<tr>
<td><code>subagent_start</code></td>
<td>A sub-agent is spawned through <code>task</code> or <code>delegate</code>. Its events follow until <code>subagent_end</code>.</td>
<td><code>name</code>, <code>agent_id</code> (the sub-agent's), <code>tool_call_id</code> (the spawning call), <code>handoff</code> (<code>true</code> for <code>delegate</code> — the sub-agent writes the turn's answer), <code>timestamp</code></td>
</tr>
<tr>
<td><code>subagent_end</code></td>
<td>The sub-agent finished.</td>
<td><code>name</code>, <code>agent_id</code>, <code>handoff</code>, <code>timestamp</code></td>
</tr>
<tr>
<td><code>todo</code></td>
<td>The main agent's todo list changed (planning).</td>
<td><code>tasks</code>: a list of <code>{id, description, status}</code></td>
</tr>
<tr>
<td><code>ask_user</code></td>
<td>The turn pauses for the user's answer.</td>
<td><code>questions</code> (list of <code>{question, header, options[{label, description}], multiSelect}</code>), <code>question</code> (plain-text rendering), <code>tool_call_id</code>, <code>timestamp</code></td>
</tr>
<tr>
<td><code>widget</code></td>
<td>The agent rendered a UI widget (when <code>widget: true</code>).</td>
<td>The widget specification. Its <code>type</code> field is the widget type (<code>table</code>, <code>bar</code>, <code>line</code>, <code>area</code>, <code>stackedbar</code>, <code>pie</code>, <code>link</code>, <code>simplelink</code>), followed by the type's fields — for example <code>title</code>, <code>data</code>, <code>xKey</code>, <code>series</code>, <code>columns</code>, or <code>url</code> and <code>label</code> for links.</td>
</tr>
<tr>
<td><code>agent_stop</code></td>
<td>An agent run completed.</td>
<td><code>agent_id</code>, <code>iterations</code>, <code>duration_ms</code>, <code>usage</code> (the run's own LLM calls, same fields as above), <code>timestamp</code></td>
</tr>
<tr>
<td><code>error</code></td>
<td>The turn failed after the stream opened. Always followed by <code>done</code>.</td>
<td><code>message</code> (user-facing)</td>
</tr>
<tr>
<td><code>done</code></td>
<td>The turn is over — always the last event, sent exactly once per turn.</td>
<td><code>timestamp</code>; <code>answer</code> — the turn's final answer text, when there is one; <code>aborted: true</code> when the turn was stopped (then <code>answer</code> holds the partial text)</td>
</tr>
</tbody>
</table>

Notes for client developers:

- **Read the stream until `done`.** Sub-agents emit `agent_start` and `agent_stop` too, but never `done`.
- **Use `done.answer` as the answer of record.** The streamed text can include narration ("let me check the balance first…") and a delegated sub-agent's commentary. `done.answer` is exactly the text stored as the turn's answer.
- **Turn token total.** Sum the main agent's `agent_stop.usage` and every `tool_usage` event.
- **A paused turn** ends with `ask_user` and then the regular `agent_stop` and `done`. Send the user's answer as the next `content` on the same sessionId.

### Example: JavaScript Consumer

```javascript
async function runAgent({ agent, lu, sessionId, content, initParams, onEvent }) {
  const query = new URLSearchParams({ agent, lu, sessionId, content });
  if (initParams) query.set('initParams', JSON.stringify(initParams));

  // No Accept header: Fabric answers 406 to "Accept: text/event-stream".
  const response = await fetch(`/api/v1/agent?${query}`, {
    method: 'POST',
    credentials: 'include',            // or headers: { Authorization: 'Bearer <api key>' }
  });
  if (!response.ok) throw new Error(`HTTP ${response.status}`);

  const reader = response.body.getReader();
  const decoder = new TextDecoder();
  let buffer = '';

  const emit = (frame) => {
    let event = 'message', data = '';
    for (const line of frame.split('\n')) {
      if (line.startsWith('event:')) event = line.slice(6).trim();
      else if (line.startsWith('data:')) data = line.slice(5).trim();
    }
    if (data) onEvent(event, JSON.parse(data));
  };

  while (true) {
    const { done, value } = await reader.read();
    if (done) break;
    buffer += decoder.decode(value, { stream: true });
    const frames = buffer.split('\n\n');
    buffer = frames.pop();            // keep an incomplete frame for the next chunk
    frames.filter((f) => f.trim()).forEach(emit);
  }
  if (buffer.trim()) emit(buffer);
}

// Usage
const sessionId = crypto.randomUUID();   // reuse it for the next message of the conversation
let answer = '';
await runAgent({
  agent: 'banking', lu: 'customer_bank',
  sessionId,
  content: 'What is my current checking balance?',
  initParams: { customerId: '1001' },
  onEvent: (event, data) => {
    if (event === 'text_delta' && data.agent_id === sessionId) answer += data.text;
    if (event === 'ask_user') console.log('Agent asks:', data.question);
    if (event === 'error') console.error(data.message);
    if (event === 'done') console.log('Final answer:', data.answer ?? answer, data.aborted ? '(stopped)' : '');
  },
});
```



## Session Endpoints

### Stop a Turn

`POST /api/v1/stop-session?sessionId=<id>` stops the turn that is running for the session. The open stream ends with `done` carrying `"aborted": true`; the partial answer is kept. Returns `{"STATUS":"OK"}`. See [Stopping a Turn](/articles/AI_fusion/02_agents/08_sessions_and_context.md#stopping-a-turn).

### Session Status

`GET /api/v1/session-status?sessionId=<id>` reports whether the session is paused on `ask_user`. Use it to restore the question UI after a page reload.

<table>
<tbody>
<tr>
<td><strong>Field</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>sessionId</code></td>
<td>The session.</td>
</tr>
<tr>
<td><code>status</code></td>
<td>The status of the session's latest execution, for example <code>PENDING_USER_INPUT</code>, <code>DONE</code> or <code>ERROR</code>.</td>
</tr>
<tr>
<td><code>pending</code></td>
<td><code>true</code> when the session waits for an answer. While it is <code>true</code>, any message sent to the session is taken as the answer.</td>
</tr>
<tr>
<td><code>questions</code></td>
<td>The structured questions, when they can be recovered.</td>
</tr>
<tr>
<td><code>question</code></td>
<td>A plain-text rendering; <code>null</code> when the wording cannot be recovered.</td>
</tr>
<tr>
<td><code>toolCallId</code></td>
<td>The pending <code>ask_user</code> call.</td>
</tr>
<tr>
<td><code>source</code></td>
<td>Where the question was recovered from: <code>tool_call</code>, <code>subagent</code> or <code>fallback</code>.</td>
</tr>
</tbody>
</table>

The probe never creates a session, so it is safe to call with an unknown ID.

### Conversation History

`GET /api/v1/conversation-history?sessionId=<id>` returns the session's steps — the user messages and final answers — for rendering a transcript.

### Chats of the Current User

`GET /api/v1/chats?agentName=<agent>&limit=<n>` lists the calling user's recent conversations with one agent, newest first. The user is taken from the authenticated session and cannot be passed as a parameter. `limit` defaults to 20 and is capped at 100. Each entry has `sessionId`, `name` (the generated chat name), `agentName`, `createdAt`, `lastModifiedAt` and `status`.

### Trace

Two GraphIt endpoints return the execution trace that the Studio trace panel shows — executions, messages, tool calls and token usage:

- `GET /api/v1/trace-history?chatid=<sessionId>` — the trace of a session; the optional `stepId` and `messageId` parameters narrow it to one step or message.
- `GET /api/v1/trace-conversation?chatid=<sessionId>` — the trace of all steps of the conversation.

Call them only for sessions that have run: looking up an unknown ID creates an empty session instance.

### Feedback

`/api/v1/feedback` records the end user's thumbs-up or thumbs-down and notes per answer (`POST` to add, `PUT` to change, `DELETE` to remove, `GET` to read). The submitting user is recorded automatically. See [Feedback](/articles/AI_fusion/05_observation/06_feedback.md).

### Legacy: sessionids

`/api/v1/sessionids` (parameters `luName`, `iid`) is kept from AI Fusion 2.x. To find agent conversations, use `chats` for the current user, or the Observation conversation list.



## Agents and Skills

`GET /api/v1/agents` lists the deployed agents across all Data Products; add `luName` to filter by one LU. Each entry contains:

<table>
<tbody>
<tr>
<td><strong>Field</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>name</code>, <code>lu</code></td>
<td>The agent and its Data Product — the <code>agent</code> and <code>lu</code> parameters for <code>/api/v1/agent</code>.</td>
</tr>
<tr>
<td><code>description</code></td>
<td>The agent's description.</td>
</tr>
<tr>
<td><code>llm_interface</code>, <code>thinking</code></td>
<td>The configured LLM interface and thinking level.</td>
</tr>
<tr>
<td><code>flows</code>, <code>mcp_interfaces</code>, <code>subagents</code>, <code>skills</code></td>
<td>The agent's flow tools, MCP interfaces, sub-agents and skills.</td>
</tr>
<tr>
<td><code>planning</code>, <code>memory</code></td>
<td>Whether planning and the memory setting are on.</td>
</tr>
<tr>
<td><code>input_params</code></td>
<td>The agent's inputs, inferred from its hook flows: a list of <code>{name, type, required}</code>. Send them in <code>initParams</code>. See <a href="/articles/AI_fusion/02_agents/07_hooks_and_inputs.md">Hooks and Agent Inputs</a>.</td>
</tr>
</tbody>
</table>

`GET /api/v1/skills` (optional `luName`) lists the LU-wide skills — those under `<LU>/AI/Skills/` and shared skills — with `name`, `lu`, `description` and `shared`. Agent-private skills are not listed.



## Invoking Agents from Broadway

### The aifusion.Agent Flow

The REST API runs the `aifusion.Agent` flow, which wraps the **Agent** actor. You can run the same flow yourself, for example from a Fabric command:

```text
broadway aifusion.Agent 'agent'='banking' 'luName'='customer_bank' 'sessionId'='6f1c2e7a-5b1d-4c39-9d0e-3a2b8f1e4c55' 'content'='What is my balance?' 'initParams'='{"customerId":"1001"}'
```

<table>
<tbody>
<tr>
<td><strong>Input</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>agent</code></td>
<td>The agent name. Required.</td>
</tr>
<tr>
<td><code>sessionId</code></td>
<td>The session ID (an <code>aifusion</code> instance ID). Required.</td>
</tr>
<tr>
<td><code>content</code></td>
<td>The user message. Required.</td>
</tr>
<tr>
<td><code>luName</code></td>
<td>The agent's Data Product. Defaults to <code>aifusion</code>.</td>
</tr>
<tr>
<td><code>interface</code></td>
<td>Optional. An LLM interface that overrides the agent's <code>settings.llm_interface</code> for this turn (main agent only).</td>
</tr>
<tr>
<td><code>initParams</code></td>
<td>Optional. The agent's inputs, as a map or a JSON string.</td>
</tr>
</tbody>
</table>

The flow returns the turn's final answer (the actor's `response` output, exposed by the flow as `result`). When the turn pauses on `ask_user`, the returned value is the question. Outside an HTTP request there is no event stream, and a failure during the run is not raised: the answer comes back empty and the error is recorded in the session. Check the session's status — for example with the session-status endpoint — when you need to tell the outcomes apart.

### The AgentInvoke Actor

`aifusion.Agent` lives in the `aifusion` Data Product, so an `InnerFlow` actor in another Data Product cannot reach it. The **AgentInvoke** actor (AI Fusion extension 3.0.5 and later) runs an agent turn from a flow in **any** Data Product and returns its final answer.

- Set `agent`, `luName` (a Data Product picker; empty means `aifusion`) and, optionally, `interface` on the actor.
- Link `content`, `sessionId` and `initParams` (a map, for example from `MapCreate`, or a JSON string). Use a `RandomUUID` actor for a new session, or reuse a sessionId to continue a conversation.
- The output `response` is the final answer.

The invoked agent runs a full turn — session state, history, hooks, tools and observability are recorded under the sessionId exactly as for an API call. If the calling flow runs inside a streaming request, the agent's events go to that request's stream.

### Lower-Level LLM Actors

The Broadway LLM actors are still shipped for custom flows that need a direct LLM call:

- **LLMInvoke** — a single LLM call (messages in, content, usage and optional tool calls out).
- **LLMAgent** — an LLM tool-calling loop over Broadway flow and MCP tools that you assemble in the flow.
- **LLMToolInvoke** — executes the tool calls returned by an LLM call.

These actors are **not** the agent runtime: they do not read `agent.yaml` and have no sessions, hooks, skills, sub-agents or Studio support. Use them inside tool or hook flows — for example a flow tool that classifies text — not as a substitute for an agent. The prompt-construction actors that go with them (`LLMConst`, `LLMAppend`) are described in the legacy article [LLM Calls Utility Actors](/articles/AI_fusion/legacy_v1_v2/02_agent_framework/06_llm_calls_utility_actors.md); its parameter tables predate AI Fusion 3, so check each actor's documentation in Studio for the current inputs and outputs.

**Next article:** [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md)
