# Ask, Plan and Act Modes

From V8.5.2, @k2-assistant - the default agent of the AI Chat - works in one of three **modes**. The mode decides what the assistant is allowed to do in the chat session: only read and answer, write a plan, or make changes.

Pick the mode in the **mode selector** of the chat input. It applies to the current chat session, and you can change it at any time.

<table>
  <thead>
    <tr>
      <th>Mode</th>
      <th>Use it to</th>
      <th>What it can change</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Ask Mode</strong></td>
      <td>Ask questions, explain code or flows, review, diagnose errors</td>
      <td>Nothing. Read-only.</td>
    </tr>
    <tr>
      <td><strong>Plan Mode</strong></td>
      <td>Design a change before it is made</td>
      <td>Only the plan itself (a task context). No project files, no server state.</td>
    </tr>
    <tr>
      <td><strong>Act Mode</strong> (default)</td>
      <td>Implement changes, run and deploy</td>
      <td>Project files, and the Fabric server, with confirmation where needed.</td>
    </tr>
  </tbody>
</table>

## Ask Mode

In Ask mode the assistant answers, explains, reviews and diagnoses. It reads the project, the Fabric logs, and the state of the Fabric server - for example with `list`, `describe` or `select` commands - and consults @KB, @Data-Product-Explorer and @Interfaces when needed.

It never:

- edits project files, or delegates to @k2-worker,
- runs a command that changes the Fabric server - such as `deploy`, `get` (which syncs an instance), `broadway`, `startjob` or `set_global`,
- runs a shell command that changes anything. **Shell Execution** is off by default and, when you turn it on, is used for inspection only.

When the answer is a change, the assistant describes it exactly - which files, and what each one gets - and offers to switch to Act mode to apply it, or to Plan mode when the change is larger than a few files.

**Example prompts:**

- `Why does the population of the CUSTOMER table fail? #_f`
- `Explain what this flow does step by step`

## Plan Mode

In Plan mode the assistant turns a request into an **implementation plan**. It explores the project, loads the skill that governs each part of the change, asks you about real decisions (for example, two valid approaches with a trade-off), and writes the plan as a **task context**. The plan lists the goal, every file the change touches, the route, and what will need your confirmation in Act mode.

Task contexts are saved in the workspace, under the folder set by the `taskContextStorageDirectory` setting (`.prompts/task-contexts` by default - see [AI Features Settings](15_ai_features_settings.md#prompt-templates-and-skills)). This means a plan persists after you close the chat, and you can open and edit it like any Markdown file.

To revise an existing plan, ask for the change in Plan mode: the assistant reads the existing task context and edits it rather than creating a second one.

**Example prompts:**

- `Plan a new enrichment that pulls customer risk scores from the Oracle CRM interface`
- `I need to add a transaction history table to the customer_bank Data Product and expose it through a web service`

## Act Mode

Act mode is the default, and works as @k2-assistant always has: a small, fully-specified change it applies itself; anything larger is delegated to @k2-worker. It can also run commands and deploy on the Fabric server. The **Shell Execution** capability is on by default in this mode (see [Agent Capabilities](10_agent_capabilities.md)). It asks you once per task before changing files, and tools that read real data or change the server ask for confirmation (see [Security and Privacy](13_security_and_privacy.md#tool-confirmations)).

When a plan exists for the work, Act mode reads it first and implements it without exploring the project again.

## Switching Modes

You can change the mode yourself at any time in the mode selector.

In Ask or Plan mode, the assistant can also **offer** a switch - for example, after writing a plan it offers to switch to Act mode with "Implement the plan '<name>'" as the next message:

1. The offer appears in the chat as a question with two buttons - for example **Switch to Act Mode** and **Stay in Plan Mode**.
2. If you switch, the mode selector moves to the new mode, and the assistant sends its follow-up in that mode once the current response has finished.
3. If you stay, nothing changes, and you can keep refining.

The assistant never switches mode on its own - only after you accept.

## A Typical Flow

1. **Ask** - understand the area: `How is the customer_bank Data Product populated?`
2. **Plan** - design the change, answer the assistant's questions, and review the plan.
3. **Act** - accept the offered switch to implement the plan.

For small, clear changes, you can stay in Act mode throughout.

## Tips

**Be specific about constraints.** State preferences about patterns or approaches up front - for example, "use try-with-resources for all DB operations" or "do not modify the existing population logic". In Plan mode they become part of the plan.

**Attach relevant context.** Use `#file:path/to/file` to point at an existing artifact the change should follow.

**Iterate before executing.** It is much cheaper to revise a plan than to revise implemented code.

> **Earlier versions:** this article used to describe a plan-first workflow with the @Architect agent and an "Execute with Coder" button. That workflow is no longer supported - use Plan mode instead. See [Other Studio AI Agents](14_other_studio_ai_agents.md#architect).
