# Creating Custom Agents

In addition to the built-in K2View agents, Studio AI lets you create your own agents. A custom agent has a name, a focused purpose, and a prompt you write. Once created, it appears in the chat alongside the built-in agents and can be addressed with `@AgentName` like any other.

Custom agents are useful when you have a repeated workflow that none of the built-in agents covers well: an agent specialized in a specific LU, a coding style enforcer, or an agent that always injects a particular set of project context.

> For agents scoped to a specific Data Product/Logical Unit, consider `@Agent-Builder` instead of the manual form below. It scaffolds a richer structure (`agent.yaml`, sub-agents, skills, references, and evals) by understanding your Data Product's schema. See [Studio AI Agents](02_studio_ai_agents_reference.md) and [Other Studio AI Agents](14_other_studio_ai_agents.md#agent-builder).

## Creating a Custom Agent

1. Open the **AI Configuration** panel: click **More Actions...** ("…") in the AI Chat panel toolbar, then **Open AI Configuration**. See [Using the AI Chat](03_using_the_ai_chat.md#the-ai-chat-toolbar).
2. Select the **Agents** tab.
3. Click **Add Custom Agent** at the bottom of the agent list.
4. Fill in the agent definition fields (described below).
5. Click **Save**.

The new agent is immediately available in the AI Chat.

## Agent Definition Fields

<table>
  <thead>
    <tr>
      <th>Field</th>
      <th>Required</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>ID</strong></td>
      <td>Yes</td>
      <td>A unique identifier used internally. Use lowercase with hyphens (e.g., <code>my-agent</code>). Must not conflict with existing agent IDs.</td>
    </tr>
    <tr>
      <td><strong>Name</strong></td>
      <td>Yes</td>
      <td>The display name shown in the chat and in AI Configuration. This is also the <code>@Name</code> used to address the agent in the chat.</td>
    </tr>
    <tr>
      <td><strong>Description</strong></td>
      <td>Yes</td>
      <td>A one-line summary of what the agent does. Appears in the agent list and on the AI Chat welcome screen.</td>
    </tr>
    <tr>
      <td><strong>Prompt</strong></td>
      <td>Yes</td>
      <td>The system prompt that defines the agent's behavior, persona, and knowledge. Supports <code>{{variable}}</code> and <code>~{function}</code> syntax. See <a href="07_ai_configuration_and_prompts.md">Customizing Agent Prompts</a>.</td>
    </tr>
    <tr>
      <td><strong>Default LLM</strong></td>
      <td>No</td>
      <td>The model or model alias the agent uses. If omitted, the agent uses the workspace default.</td>
    </tr>
  </tbody>
</table>

## Global vs Workspace-Specific Agents

Custom agents can be stored in two locations: a global, user-level location available in every workspace, and a workspace-specific location tied to the current project, which takes priority over a global agent with the same ID. This is useful for project-specific assistants, for example an agent pre-loaded with context about a particular Logical Unit.

The older claim that these locations are `~/.theia/` (global) and a project's `.theia/` folder (workspace) is confirmed outdated. The `skills.skillDirectories` setting (see [AI Features Settings](15_ai_features_settings.md#prompt-templates-and-skills)) explicitly documents that skills resolve from both a workspace-level `.agents/skills` folder and a user-home-level `~/.agents/skills` folder, with workspace taking precedence. Custom agents follow the same convention: workspace-level under `.agents/agents/<id>/`, global (user-level) under `~/.agents/agents/<id>/`.

When you create an agent via the UI, Studio AI prompts you to choose whether to save it globally or for the current workspace.

## Writing an Effective Custom Agent Prompt

The prompt is the most important part of a custom agent. A few guidelines:

**Start with identity and scope.** Tell the agent what it is and what it should focus on:

```
You are a specialized assistant for the customer_bank Logical Unit in this K2View project.
You help with designing and modifying tables, populations, enrichment functions, and globals
within this LU only.
```

**Include project-specific conventions.** If the agent should follow particular patterns:

```
All enrichment functions must follow the pattern established in
~{readFile('implementation/Shared Objects/functions/ParseDate.java')}.
Use try-with-resources for all database operations.
```

**Set boundaries.** Tell the agent what it should not do:

```
Do not modify any files outside the customer_bank LU directory. If a request requires
cross-LU changes, describe what is needed and ask the user to confirm.
```

**Use variables for dynamic context.** Inject the current file or project path automatically:

```
The project is located at {{projectRoot}}.
When the user asks about the current file, refer to {{currentRelativeFilePath}}.
```

## Example: A LU-Specific Coding Agent

Here is a complete example of a custom agent focused on a specific Logical Unit:

```yaml
---
name: CustomerBank-Dev
description: Coding assistant specialized in the customer_bank Logical Unit
---
You are a senior K2View developer specializing in the customer_bank Logical Unit.

Your responsibilities:
- Writing and modifying Java functions (enrichments, populations, decision functions)
- Designing LU table schemas
- Reviewing code for correctness and K2View best practices

Conventions to follow in this project:
~{fragment('shared/coding-standards')}

Current project: {{projectRoot}}
Current file: {{currentRelativeFilePath}}

Only modify files within the customer_bank LU. For cross-LU work, propose the changes
and ask the user to confirm before proceeding.
```

## Editing and Deleting Custom Agents

To edit a custom agent, open AI Configuration, find the agent in the Agents tab, and click **Edit**. You can modify any field and save.

To delete a custom agent, click **Delete** next to the agent in the list. Built-in agents cannot be deleted (only disabled).
