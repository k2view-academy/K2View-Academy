# Skills and Slash Commands

Skills are reusable, structured workflows that you can invoke from the AI Chat using a **slash command**. Instead of typing a detailed prompt from scratch for a recurring task, you define the task once as a skill and trigger it with `/skillName` whenever you need it.

> **Note:** Skills are currently in **Alpha**. The interface and file format may change in future releases.

## What Is a Skill?

A skill is a `.prompttemplate` file stored in a designated skills directory. It contains:

- A **YAML frontmatter** block with a `name`, `description`, and the slash command trigger
- A **prompt body** that describes the task in detail, with the same `{{variable}}` and `~{function}` syntax used in agent prompts

When you type the matching slash command in the chat input, Studio AI loads the skill's prompt and executes it with the currently addressed agent.

## Using Slash Commands in the Chat

Type `/` in the chat input to see a list of available slash commands. Both built-in commands and your custom skills appear in this list. Select one or type the full command name and press **Enter**.

```
/analyze-gh-ticket
```

Slash commands can also accept arguments. Refer to the specific command's description for its expected input.

## Built-In Slash Commands

Studio AI includes a set of built-in slash commands for common development workflows:

| Command | Description |
|---|---|
| `/remember` | Saves a piece of information — a convention, a preference, or a fact about the project — for the current agent to retain across the session. Useful for establishing context once without repeating it in every message. |
| `/with-apptester` | Triggers the current task with the AppTester capability enabled, equivalent to enabling the AppTester chip before sending your message. |
| `/analyze-gh-ticket` | Fetches a GitHub issue and asks the agent to analyze it in the context of your current project — summarizing the problem, identifying affected files, and suggesting an implementation approach. |
| `/fix-gh-ticket` | Fetches a GitHub issue and instructs @Coder to implement the fix directly, starting from the issue description. |
| `/address-gh-review` | Takes a GitHub pull request review and instructs @Coder to address each review comment in the codebase. |

## Creating Custom Skills

To create a skill, add a `.prompttemplate` file to the skills discovery directory. Studio AI automatically discovers skills from `~/.theia/skills/` (global, available in all workspaces) and from `.theia/skills/` within your current project (workspace-specific, takes priority).

### Skill File Format

```
---
name: My Skill
description: A one-line description shown in the slash command list
command: my-skill
---

You are helping the developer with a specific, recurring task.

[Detailed prompt instructions here]

Current file: {{currentRelativeFilePath}}
```

The `command` field in the frontmatter defines the slash command trigger. In the example above, the skill would be invoked with `/my-skill`.

### Skill Discovery

Skills are loaded when the Studio starts. After adding a new skill file, reload the Studio window (**F1 > Developer: Reload Window**) to make it available.

Skills in `.theia/skills/` (workspace-level) take precedence over skills in `~/.theia/skills/` (global) when both define a command with the same name.

## Example: A Code Review Skill

Here is an example skill that performs a code review on the current file, checking for K2View-specific best practices:

```
---
name: K2View Code Review
description: Review the current file for K2View best practices and common issues
command: k2review
---

Review the code in {{currentRelativeFilePath}} for the following issues:

1. JDBC resource management — ensure all connections, statements, and result sets are
   closed, preferably with try-with-resources.
2. SQL injection risks — verify that all SQL uses parameterized queries.
3. Exception handling — check that exceptions are caught at the appropriate level and
   logged correctly per our conventions.
4. Naming conventions — verify method and variable names follow our team standards.

For each issue found, explain the problem and provide a corrected version of the relevant code.
If no issues are found in a category, say so explicitly.
```

With this skill saved, typing `/k2review` in the chat triggers the full review prompt against the currently open file.
