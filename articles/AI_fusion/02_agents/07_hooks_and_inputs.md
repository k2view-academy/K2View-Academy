# Hooks and Agent Inputs

**Hooks** are Broadway flows that the runtime runs at defined points of a turn. They let you prepare the context, enforce rules around tool calls, validate the answer and control error messages — with the full power of Broadway and Fabric, and without changing the agent's prompt.

Hook flows also define the agent's **inputs**: the values a client must provide when it starts a conversation, such as the customer ID.



## Hook Points

```yaml
hooks:
  step_start: Banking_CustomerStory
  step_end: CheckAnswer
  pre_tool_call: GuardTools
  post_tool_call: AuditTools
  on_error: FriendlyError
```

Each value is the name of one Broadway flow in the agent's Data Product, or `LU__<lu>__<flow>` for a flow in another Data Product.

<table>
<tbody>
<tr>
<td width="140pxl"><strong>Hook</strong></td>
<td width="200pxl"><strong>Runs</strong></td>
<td width="230pxl"><strong>Flow receives</strong></td>
<td><strong>Flow returns</strong></td>
</tr>
<tr>
<td><code>step_start</code></td>
<td>Before the LLM sees the user's message. Not run when a paused turn resumes.</td>
<td><code>query</code> (the user message), <code>step_id</code>, <code>sessionId</code>, <code>agentName</code></td>
<td><code>result</code>: a rewritten message for the LLM. Empty leaves the message unchanged. The original text is kept as the turn's user message.</td>
</tr>
<tr>
<td><code>step_end</code></td>
<td>After the final answer. Not run when the turn paused on a question to the user.</td>
<td><code>final_answer</code>, <code>query</code>, <code>step_id</code>, <code>sessionId</code>, <code>agentName</code></td>
<td><code>result</code>: when not empty, the agent continues working with this text as a follow-up instruction (up to <code>max_step_continuations</code>, default 3).</td>
</tr>
<tr>
<td><code>pre_tool_call</code></td>
<td>Before every tool call.</td>
<td><code>tool_name</code>, <code>arguments</code>, <code>tool_call_id</code>, <code>iteration</code></td>
<td><code>decision</code>: <code>ALLOW</code> (default) or <code>BLOCK</code>.</td>
</tr>
<tr>
<td><code>post_tool_call</code></td>
<td>After every tool call.</td>
<td><code>tool_name</code>, <code>tool_call_id</code>, <code>result</code>, <code>duration_ms</code></td>
<td><code>stop: true</code> ends the run; the optional <code>result</code> becomes the answer. The hook cannot change the tool result seen by the LLM.</td>
</tr>
<tr>
<td><code>on_error</code></td>
<td>When the agent run fails (not for tool errors, which are returned to the LLM).</td>
<td><code>error</code> (<code>message</code>, <code>type</code>, <code>stackTrace</code>), <code>default_error_message</code>, <code>agentName</code>, <code>sessionId</code>, <code>step_id</code>, <code>iteration</code></td>
<td><code>result</code>: the message shown to the user.</td>
</tr>
</tbody>
</table>

In addition, every hook flow receives the agent inputs as externals.

Per-tool `before` and `after` hooks, configured on a single entry of `tools:`, use the `pre_tool_call` and `post_tool_call` contracts. See [Tools](/articles/AI_fusion/02_agents/03_tools.md#per-tool-hooks).

### Failure Behavior

Hooks fail closed: if a hook flow fails, the turn fails. A failing `pre_tool_call` hook blocks the tool call rather than allowing it. Keep hook flows simple and fast, and handle expected conditions inside the flow.



## Building a Hook Flow

Each hook type has a pair of Broadway actors, found under the **agent_hooks** tag:

<table>
<tbody>
<tr>
<td><strong>Hook</strong></td>
<td><strong>First actor (inputs)</strong></td>
<td><strong>Last actor (outputs)</strong></td>
</tr>
<tr>
<td><code>step_start</code></td>
<td><code>StepStartHookInput</code></td>
<td><code>StepStartHookOutput</code> — <code>message</code> → external <code>result</code></td>
</tr>
<tr>
<td><code>step_end</code></td>
<td><code>StepEndHookInput</code></td>
<td><code>StepEndHookOutput</code> — <code>continuation</code> → external <code>result</code></td>
</tr>
<tr>
<td><code>pre_tool_call</code></td>
<td><code>PreToolCallHookInput</code></td>
<td><code>PreToolCallHookOutput</code> — <code>decision</code> → external <code>decision</code></td>
</tr>
<tr>
<td><code>post_tool_call</code></td>
<td><code>PostToolCallHookInput</code></td>
<td><code>PostToolCallHookOutput</code> — <code>stop</code>, <code>result</code> → matching externals</td>
</tr>
<tr>
<td><code>on_error</code></td>
<td><code>OnErrorHookInput</code></td>
<td><code>OnErrorHookOutput</code> — <code>message</code> → external <code>result</code></td>
</tr>
</tbody>
</table>

Place the input actor first and the output actor last, and map the output actor's ports to the flow's output externals as shown.



## Agent Inputs

There is no input-parameter list in `agent.yaml`. Instead, the **external inputs declared by the agent's hook flows are the agent's inputs**:

- An external marked **mandatory** becomes a **required** input. A request without it fails ("Missing required input(s)").
- The inputs are listed by the `agents` web service (`input_params`), shown in the Studio **New chat** dialog (**Agent inputs**).
- Clients pass them as the `initParams` JSON object when calling the agent, for example `{"customerId":"1001"}`.
- They are injected as externals into every hook flow and every tool flow the agent calls, and forwarded to sub-agents.

Do not declare an external with a reserved name — `query`, `step_id`, `sessionId`, `agentName`, `final_answer`, `tool_name`, `arguments`, `tool_call_id`, `iteration`, `result`, `duration_ms`, `error`, `default_error_message`. Such inputs are ignored, with a warning in the log.

### Trusted Inputs: the AgentInitParams Actor

For a sub-agent, an input may also be supplied by the calling LLM in the `task` or `delegate` arguments, so hook externals are not a trustworthy source for authorization scope. The **`AgentInitParams`** actor returns the inputs that the client passed to the top-level agent, which the model can never change:

- `initParams` — the full map; add an output named after a key (for example `customerId`) to read it directly.
- `sessionId`, `stepId`, `agentName`, `luName`.

Use `AgentInitParams` in any hook or tool flow that needs the customer identity for data access. It works in flows that the runtime calls directly, not in their inner flows.



## Example: Loading the Customer in step_start

The most common hook loads the entity the conversation is about, so the `query` tool works on its data. In the banking demo:

- The main agent's `step_start` flow, `Banking_CustomerStory`, declares the mandatory external `customerId`, loads the customer instance with the `GetWithSnap` actor (which also supports evaluation snapshots), builds a short customer profile, and returns it together with the user's message as `result`.
- Each sub-agent's `step_start` flow, `LoadCustomerScope`, declares the mandatory `customerId` and loads the instance with `FabricGet`.

Because both flows declare `customerId` as mandatory, the client must choose the customer before the first message, and the main agent's sub-agent catalog shows `inputs: customerId` for every specialist.

> To make conversations reproducible in [Evaluation](/articles/AI_fusion/04_evaluation/README.md), load the entity in the main agent's `step_start` hook with `GetWithSnap`, so that snapshots of the data are captured with the test case.



## More Hook Examples

- **Guard a privileged tool** — a `pre_tool_call` flow returns `BLOCK` when `tool_name` is `fabric` and the command is not on an allow list.
- **Audit operations** — an `after` hook on `updateCreditCardLimit` writes the arguments and result to an audit table.
- **Check the answer** — a `step_end` flow verifies that the answer does not contain account numbers and, if it does, returns "Rewrite the answer without account numbers" as a continuation.
- **Friendly errors** — an `on_error` flow returns a localized message with a reference number.



**Next article:** [Sessions and Context](/articles/AI_fusion/02_agents/08_sessions_and_context.md)
