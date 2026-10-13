# Customizing Agent Prompts

Every agent in Studio AI is driven by a **prompt template**: a structured text file that tells the agent who it is, what it knows, and how it should behave. Studio AI lets you view and edit these templates directly, so you can tailor any agent's behavior to your team's conventions, K2View project structure, or coding standards.

## Opening the Prompt Editor

1. Open the **AI Configuration** panel: click **More Actions...** ("…") in the AI Chat panel toolbar, then **Open AI Configuration**. See [Using the AI Chat](03_using_the_ai_chat.md#the-ai-chat-toolbar).
2. Select the **Agents** category, and open the agent.
3. In the agent's **Prompts** section, open the prompt you want to customize. A prompt with several variants shows them there - for example `k2-assistant-system` has three variants, one per mode, with **Act Mode** as the default.

The prompt template opens in the Studio editor, where you can modify it like any other file.

## Prompt Template Syntax

Prompt templates are plain text files with a YAML frontmatter header and a body. The body can include two kinds of dynamic insertions:

### Variable References: `{{variableName}}`

Double-curly-brace syntax inserts the current value of a context variable at the point where the reference appears. Variables are defined in the **Variables** category of AI Configuration or come from the built-in set (such as `{{currentRelativeFilePath}}`).

**Example:**

```
You are working on the project located at {{projectRoot}}.
The current file is {{currentRelativeFilePath}}.
```

When the agent runs, these placeholders are replaced with the actual values for that session.

> **Prompt caching tip:** LLM providers cache the start of a request - tools, then system prompt, then messages - and charge much less for the cached part. A variable whose value changes often, such as `{{currentRelativeFilePath}}` or `{{openEditors}}`, changes the system prompt whenever you switch editor tabs, so the whole request is sent uncached again. Avoid such variables in a system prompt. @k2-assistant sends this editor state with each message instead (see [Security and Privacy](13_security_and_privacy.md#what-each-request-contains)).

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

The `name` and `description` fields appear in the AI Configuration panel and in the agent selector. They do not affect the agent's behavior; they are for identification only.

## Prompt Fragments

A **prompt fragment** is a reusable snippet of prompt content stored in a `.prompttemplate` file. Instead of duplicating the same instructions across multiple agent prompts, you write them once as a fragment and reference the fragment from each agent.

Fragments are managed in the **Prompt Snippets** category of AI Configuration. To use a fragment in an agent prompt template, reference it using the `~{fragment}` function call syntax:

```
~{fragment('shared/java-style-guide')}
```

When the agent prompt is built, the fragment content is inserted at that point.

Fragments support the same `{{variable}}` and `~{function}` syntax as regular prompt templates, so they can be dynamic as well.

## Saving and Resetting Prompts

Changes to a prompt template are saved when you save the file in the editor (Ctrl+S). The updated prompt takes effect for the next chat session that uses the agent; ongoing sessions continue with the prompt that was active when they started.

To reset a built-in agent's prompt to its original default, use the reset option of the prompt in the agent's **Prompts** section. Custom agents do not have a default to reset to.

## Where Prompt Files Are Stored

Confirmed live: a built-in agent's prompt template (for example `@Coder`'s, `coder-system-agent-mode.prompttemplate`) opens from a **`.prompts/`** folder in the project, not from inside the Studio application itself. Editing and saving it (Ctrl+S) edits that project file directly. The `promptTemplates.promptTemplatesFolder` setting confirms the full path: `<workspace root>/.prompts` (falling back to the user config directory if not customized). See [AI Features Settings](15_ai_features_settings.md#prompt-templates-and-skills).

### @k2-assistant and @k2-worker Prompts

From V8.5.2, the prompts of @k2-assistant - one per mode (Ask, Plan and Act, see [Ask, Plan and Act Modes](05_plan_first_development.md)) - and of @k2-worker are built into the Studio. Customize them in the same way as the prompts of other built-in agents. Copies of these agents under `.agents/agents/` in the workspace, installed by earlier versions of the Studio AI Core Artifacts extension, are ignored.

## Practical Customization Examples

**Enforcing a coding style:**

```
Always use try-with-resources for JDBC operations. Never use raw string concatenation for SQL; use parameterized queries.
```

**Adding project-specific context:**

```
The K2View project in this workspace follows the naming conventions defined in ~{readFile('.prompts/naming-conventions.md')}.
```

**Restricting scope:**

```
You only modify files within the `implementation/` directory. If a task would require changes outside this directory, describe what is needed and ask the user to confirm before proceeding.
```
