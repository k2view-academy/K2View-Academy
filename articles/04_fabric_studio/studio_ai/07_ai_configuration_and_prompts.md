# Customizing Agent Prompts

Every agent in Studio AI is driven by a **prompt template** — a structured text file that tells the agent who it is, what it knows, and how it should behave. Studio AI lets you view and edit these templates directly, so you can tailor any agent's behavior to your team's conventions, K2View project structure, or coding standards.

## Opening the Prompt Editor

1. Open the **AI Configuration** panel (click the configuration icon in the AI Chat panel bar, or go to **View > AI Configuration**).
2. Select the **Agents** tab.
3. Click the **Edit Prompt** button next to the agent you want to customize.

The prompt template opens in the Studio editor, where you can modify it like any other file.

## Prompt Template Syntax

Prompt templates are plain text files with a YAML frontmatter header and a body. The body can include two kinds of dynamic insertions:

### Variable References: `{{variableName}}`

Double-curly-brace syntax inserts the current value of a context variable at the point where the reference appears. Variables are defined in the **Variables** tab of AI Configuration or come from the built-in set (such as `{{currentRelativeFilePath}}`).

**Example:**

```
You are working on the project located at {{projectRoot}}.
The current file is {{currentRelativeFilePath}}.
```

When the agent runs, these placeholders are replaced with the actual values for that session.

### Function Tool References: `~{functionName}`

The tilde-brace syntax calls a registered tool function at prompt execution time. Tool functions can read files, query the project structure, or fetch dynamic information. The result of the function call is inserted into the prompt.

**Example:**

```
~{readFile('implementation/Shared Objects/functions/globals.java')}
```

This inserts the current content of the specified file into the prompt every time the agent is invoked, ensuring the agent always sees up-to-date content without you having to attach it manually in the chat.

## YAML Frontmatter

Each prompt template begins with a YAML frontmatter block (delimited by `---`) that defines metadata about the template:

```yaml
---
name: My Custom Coder
description: A Coder variant tuned for our Java coding standards
---
```

The `name` and `description` fields appear in the AI Configuration panel and in the agent selector. They do not affect the agent's behavior — they are for identification only.

## Prompt Fragments

A **prompt fragment** is a reusable snippet of prompt content stored in a `.prompttemplate` file. Instead of duplicating the same instructions across multiple agent prompts, you write them once as a fragment and reference the fragment from each agent.

Fragments are managed in the **Prompt Fragments** tab of AI Configuration. To use a fragment in an agent prompt template, reference it using the `~{fragment}` function call syntax:

```
~{fragment('shared/java-style-guide')}
```

When the agent prompt is built, the fragment content is inserted at that point.

Fragments support the same `{{variable}}` and `~{function}` syntax as regular prompt templates, so they can be dynamic as well.

## Saving and Resetting Prompts

Changes to a prompt template are saved when you save the file in the editor (Ctrl+S). The updated prompt takes effect for the next chat session that uses the agent — ongoing sessions continue with the prompt that was active when they started.

To reset a built-in agent's prompt to its original default, click the **Reset to Default** button in the Agents tab next to the agent. Custom agents do not have a default to reset to.

## Where Prompt Files Are Stored

Prompt templates for built-in agents are stored within the Studio application. The Studio resolves prompt files in the following priority order:

1. **Workspace-level overrides** — files in your project's `.theia/` directory. These take precedence for anyone working in that workspace.
2. **Global user overrides** — files in your user-level Theia configuration directory (`~/.theia/`). These apply across all workspaces on your machine.
3. **Application defaults** — the built-in prompts shipped with Studio AI.

This means you can customize a prompt for a specific project by placing the override in the project's `.theia/` folder, without affecting other projects or other users.

## Practical Customization Examples

**Enforcing a coding style:**

```
Always use try-with-resources for JDBC operations. Never use raw string concatenation for SQL — use parameterized queries.
```

**Adding project-specific context:**

```
The K2View project in this workspace follows the naming conventions defined in ~{readFile('.theia/naming-conventions.md')}.
```

**Restricting scope:**

```
You only modify files within the `implementation/` directory. If a task would require changes outside this directory, describe what is needed and ask the user to confirm before proceeding.
```
