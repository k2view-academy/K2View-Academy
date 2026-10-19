# Sub-Agents and Delegation

A single agent with dozens of tools and schemas becomes slow, expensive and error-prone. AI Fusion 3 lets you split the work: a main agent owns the conversation, and **sub-agents** specialize in a domain — credit cards, loans, payroll — each with only the tools, skills and rules of that domain.

The main agent reaches a sub-agent in one of two ways:

- **`task`** — the sub-agent works on a sub-task and returns its result to the main agent, which continues and writes the answer.
- **`delegate`** — the main agent hands the whole turn to the sub-agent, whose reply goes to the user as the final answer.



## Defining Sub-Agents

### Folder Sub-Agents

Create a YAML file under the agent's `Subagents/` folder (in Studio: **New Sub-Agent...**). It uses the same schema as `agent.yaml`:

```text
AI/Agents/banking/
├── agent.yaml
└── Subagents/
    ├── credit-card.yaml
    ├── dda.yaml
    └── loan.yaml
```

```yaml
# Subagents/credit-card.yaml
description: "Credit cards — cards, statements, payments, transactions; tools: getCreditScore, updateCreditCardLimit, getAPRforCreditScore"

system_prompt: |
  # Credit Card Specialist
  You handle credit card questions and operations for one customer ...

constraints: |
  Never set a credit limit above the maximum the customer's credit score allows.

hooks:
  step_start: LoadCustomerScope

tools:
  - query
  - getCreditScore
  - updateCreditCardLimit
  - getAPRforCreditScore

skills:
  - credit-card-schema:
      auto_load: true
```

Folder sub-agents are discovered and enabled automatically. They inherit the parent's `settings` key by key (a value in the sub-agent wins). Planning is not available to sub-agents.

### Linked Agents from Other Data Products

A top-level agent of another Data Product can be used as a sub-agent by linking it:

```yaml
sub_agents:
  - name: support
    lu: customer_crm            # a top-level agent in another LU
```

A linked agent runs with its own definition and settings (it does not inherit the caller's settings).

### Adjusting Sub-Agents

Use `sub_agents:` to change the discovered set:

```yaml
sub_agents:
  - name: payroll
    enabled: false              # mute a folder sub-agent
  - name: credit-card
    invoke: delegate            # accept only whole-turn handoffs
  - name: dda
    invoke: task                # accept only sub-tasks
```

`invoke` is `both` (default), `task` or `delegate`. Calling a sub-agent by a route it does not accept fails with a message that names the allowed route. Broken references — a missing file or an unknown Data Product — fail the run, and Studio reports them in the **Problems** panel.



## How the Main Agent Sees Its Sub-Agents

The runtime adds an `## Available Sub-Agents` section to the main agent's prompt, listing each enabled sub-agent with its `description`, its required inputs (taken from its hook flows), and a `[task only]` or `[delegate only]` marker when relevant. Write sub-agent descriptions as routing hints: what the specialist covers and which operations it can perform.



## task: Sub-Tasks

```text
task(task_id, goal, agent?, lu?, args?)
```

- `goal` describes what the sub-agent must achieve; `args` passes values such as `customerId`.
- The sub-agent runs its own reasoning loop, with its own tools, skills and hooks, and returns its answer to the main agent as a tool result.
- Without `agent`, `task` runs a copy of the main agent itself on a focused goal — useful for parallelizable or isolated sub-problems.
- `task` is the right route when the main agent needs the result to continue: comparing data from several domains, or executing the steps of a plan.
- Sub-agents can call their own sub-agents, up to a nesting depth of 3.



## delegate: Whole-Turn Handoff

```yaml
delegate: true
```

```text
delegate(agent, goal, lu?, args?)
```

- The sub-agent's reply is sent to the user verbatim as the final answer; the main agent does not write anything after it. This avoids a specialist's answer being restated a second time.
- If the sub-agent asks the user a question (`ask_user`), the question is shown to the user, and the next message resumes the sub-agent.
- If the delegation fails, the error is returned to the main agent, which can recover.
- `delegate` is off by default and is offered only when `delegate: true` is set and at least one enabled sub-agent accepts the delegate route.

Use `delegate` for a single, self-contained request that one specialist can complete end-to-end ("raise my card limit to 8,000"). Use `task` when the main agent must combine results or follow a plan.



## Response Guidelines and Constraints Across Agents

Two keys control what reaches the user, and they travel differently between agents:

<table>
<tbody>
<tr>
<td width="180pxl"><strong>Key</strong></td>
<td width="240pxl"><strong>Purpose</strong></td>
<td><strong>Behavior across agents</strong></td>
</tr>
<tr>
<td><code>response_prompt</code></td>
<td>House style of the reply (tone, length, format).</td>
<td>Belongs to the turn. On <code>delegate</code>, the turn owner's response guidelines replace the sub-agent's own; along a chain of delegations, the original owner's guidelines are forwarded. A <code>task</code> sub-agent does not receive them, because its result is an intermediate value that the main agent restates.</td>
</tr>
<tr>
<td><code>constraints</code></td>
<td>Non-negotiable rules (what must never be said or done).</td>
<td>Additive. On <code>delegate</code>, the caller's constraints are added to the sub-agent's own, so a handoff can narrow what may be said but never widen it. A <code>task</code> sub-agent applies only its own constraints, because the main agent restates its result. The built-in planner and widget sub-agents inherit the chain.</td>
</tr>
</tbody>
</table>

Put style in `response_prompt` and hard rules in `constraints`. A rule written in the style section is dropped as soon as the turn is delegated.



## Passing Inputs to Sub-Agents

Sub-agents usually need the same entity as the main agent. Their required inputs come from their hook flows (for example `LoadCustomerScope` declares `customerId`). The runtime fills them from:

1. The `args` passed in the `task` or `delegate` call.
2. The main agent's inputs (`initParams`), which are forwarded automatically.

Values passed by the LLM in `args` can override forwarded inputs. When a flow must rely on a value the LLM cannot change, read it with the `AgentInitParams` actor — see [Hooks and Agent Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md).



## Design Tips

- **Split by business domain**, matching your schema skills — one specialist per domain.
- **Keep the main agent as the conversation owner.** It answers simple read-only questions itself and routes operations.
- **Tell specialists who reads their output.** A `task` result is read by the main agent; ask specialists to return facts and the IDs needed for the next step, and to report missing inputs instead of asking the user.
- **Mute what you don't need** with `enabled: false` rather than deleting files while experimenting.
- **Trace it.** The Studio Trace shows each sub-agent execution nested under the tool call that started it.



**Next article:** [Hooks and Agent Inputs](/articles/AI_fusion/02_agents/07_hooks_and_inputs.md)
