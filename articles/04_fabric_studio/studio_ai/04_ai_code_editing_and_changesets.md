# AI Code Editing: Reviewing and Applying Changes

When @Coder proposes changes to your code, Studio AI gives you full control over how those changes are reviewed and applied. This article covers the two operating modes, Edit Mode and Agent Mode, and how to manage the resulting diffs, including accepting, rejecting, and iterating on changes.

## Edit Mode vs Agent Mode

@Coder supports two modes, selectable from the **mode selector dropdown** in the chat input area, or by pressing **Shift+Tab** to toggle between them.

**Edit Mode** is the default. In this mode, every change @Coder proposes is presented as a diff for your review before anything is written to disk. You can inspect exactly what will change, accept or reject individual files, and ask for revisions, all without modifying your project until you explicitly approve.

**Agent Mode** gives @Coder full autonomy. It reads files, writes changes, runs tests, and iterates until the task is complete, without pausing for approval at each step. Use this for larger tasks where you trust the agent to work through a multi-step implementation on its own.

For most focused edits and targeted fixes, Edit Mode gives you the control you need. For broader tasks, implementing a new feature end-to-end, for example, Agent Mode is more efficient.

## Edit Mode: Reviewing Diffs

When @Coder proposes a change in Edit Mode, a **diff view** appears in the chat. The diff highlights:

- Lines added (shown in green)
- Lines removed (shown in red)
- Unchanged surrounding context (shown in neutral)

Each proposed file change includes the full file path so you know exactly which file is affected. Multiple file changes from a single request are grouped together in a **changeset**, so you can review them all at once.

### Accepting and Rejecting Changes

At the bottom of each changeset, you will find action buttons:

- **Accept All**, applies every proposed change in the changeset to your workspace files.
- **Reject All**, discards all proposed changes without modifying any file.

If you want to accept changes for some files but not others, click the individual **Accept** or **Reject** button that appears alongside each file's diff block. This lets you cherry-pick which parts of a proposal to keep.

Once you accept a change, the affected file is updated on disk immediately. The diff view in the chat remains visible so you can refer back to what was changed.

### Requesting Revisions

If the proposed change is close but not quite right, you do not need to reject it and start over. Simply type a follow-up message describing what to adjust, for example:

```
The condition on line 12 should also handle null values. Please update.
```

@Coder will issue a new proposal taking the previous change and your feedback into account. You can iterate this way as many times as needed before accepting.

## Agent Mode: Autonomous Implementation

In Agent Mode, @Coder works independently. It reads the relevant files, writes changes, and verifies its work, without stopping to present diffs.

As it works, @Coder displays a **visual task list** in the chat showing each step of the implementation. The list is updated in real time: completed steps are checked off, the current step is highlighted, and upcoming steps are listed ahead.

This gives you a clear view of where the agent is in a multi-step task, even when it is running in the background.

When the task is complete, a **notification** appears with the agent name, session, and a **"Show Chat"** button that takes you directly to the result.

### Optional Capabilities in Agent Mode

When using @Coder in Agent Mode, three optional capability toggles are available as chips in the chat input area. Click a chip to enable it before sending your message:

- **Shell Execution**, allows @Coder to run shell commands on the server, such as builds and tests. A confirmation dialog appears before each command is executed.
- **GitHub**, lets @Coder delegate GitHub-related work to @GitHub: reading issues, querying the repository, and creating pull request descriptions.
- **AppTester**, after @Coder finishes an implementation, it automatically hands off to the AppTester agent to run end-to-end UI verification. If tests fail, the agents iterate to fix the issues.

All three capabilities are off by default. For full details, see [Agent Capabilities](10_agent_capabilities.md).

## Fixing Diagnostics

When @Coder writes or modifies code, Studio AI monitors the diagnostics (errors and warnings) produced by the language server. If issues are detected in the files that were changed, @Coder can be instructed to address them:

```
@Coder Fix the compilation errors introduced in the last change
```

In Agent Mode, @Coder may automatically attempt to resolve diagnostics as part of its task loop, without waiting to be asked.

## Starting a Code Edit from the Editor

You do not need to describe the code in the chat, you can start directly from the editor:

1. Select the code you want to modify.
2. Right-click and choose **Ask AI** (or a similar option) from the context menu.
3. The selected code is automatically attached as context in the chat input.
4. Describe the change you want, and press **Enter**.

This is equivalent to typing your message with `#selectedText` as context, but more convenient when you are already looking at the code you want to change.
