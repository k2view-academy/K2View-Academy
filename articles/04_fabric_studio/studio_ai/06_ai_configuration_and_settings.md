# AI Configuration: Managing Agents and Settings

The AI Configuration view is the central place to manage every aspect of Studio AI: which agents are active, which language models they use, prompt customizations, token usage, MCP server connections, and more. This article covers the view's layout and each of its categories.

## Opening AI Configuration

Click the history icon's neighboring **More Actions...** ("…") in the AI Chat panel toolbar, then choose **Open AI Configuration**. It opens as a full editor tab (not a side panel). See [Using the AI Chat](03_using_the_ai_chat.md#the-ai-chat-toolbar) for the toolbar layout. The links in the [Get started with AI walkthrough](01_getting_started_with_studio_ai.md#the-get-started-with-ai-walkthrough) also open it at the relevant category.

## View Overview

From V8.5.2, AI Configuration is a single view: a list of **categories** on the left, and the selected category's details on the right. It replaces the row of tabs of earlier versions, and also gathers AI settings that used to be spread across the Settings UI.

<table>   <thead>     <tr>       <th>Category</th>       <th>Purpose</th>     </tr>   </thead>   <tbody>     <tr>       <td><strong>General</strong></td>       <td>General AI settings, such as enabling AI</td>     </tr>     <tr>       <td><strong>Providers &amp; Models</strong></td>       <td>Each provider's settings (such as its API key), the models discovered from it, and which of them appear in the chat; also general model settings</td>     </tr>     <tr>       <td><strong>Model Aliases</strong></td>       <td>Define named LLM aliases and priority lists used across agents</td>     </tr>     <tr>       <td><strong>Agents</strong></td>       <td>Enable or disable agents; open an agent to configure its prompts, model, capabilities and notifications</td>     </tr>     <tr>       <td><strong>Skills &amp; Slash Commands</strong></td>       <td>The skill directories, every installed skill, and the slash commands</td>     </tr>     <tr>       <td><strong>Prompt Snippets</strong></td>       <td>Manage reusable prompt fragment files</td>     </tr>     <tr>       <td><strong>Variables</strong></td>       <td>View and set global context variables available to all agents</td>     </tr>     <tr>       <td><strong>MCP Servers</strong></td>       <td>Add, edit, and remove Model Context Protocol server connections</td>     </tr>     <tr>       <td><strong>Tools</strong></td>       <td>View and manage the tools (functions) available to agents</td>     </tr>     <tr>       <td><strong>Token Usage</strong></td>       <td>Monitor cumulative token consumption by model</td>     </tr>   </tbody> </table>

*(Screenshot pending refresh for the V8.5.2 category layout.)*

## Providers and Models

The **Providers & Models** category starts with general model settings (custom request settings, maximum retries, reasoning defaults), followed by the list of **Providers**. Open a provider to see its settings - for example the Anthropic API key, `allowEnvironmentApiKey` and `modelOverrides` - and its **Model Discovery** section.

For Anthropic, OpenAI and Google, Studio reads the list of models from the provider itself: on startup, when the API key changes, and when you click **Refresh the model list**. The list is cached, so it is also available offline. Models are shown newest first, with their release date, and the provider's recommended models carry a **Default** badge. A counter shows how many of them appear in the chat - for example "5 of 15 shown in the AI chat input's model picker".

Use the show/hide button next to each model to choose whether it appears in the chat input's model selector, and the filter button to list only the models that are shown. The reset button restores the default selection.

The discovered list can be replaced with a fixed one using the `modelOverrides` setting (a Studio setting, which the user can change) - see [AI Features Settings](15_ai_features_settings.md#ai-enablement-and-llm-providers).

## Agents

The **Agents** category lists every agent available in Studio AI. Some agents are disabled by default - see [Other Studio AI Agents](14_other_studio_ai_agents.md#agents-disabled-by-default). For each agent you can:

- **Enable or disable** the agent using the toggle. A disabled agent does not appear in the chat and cannot be addressed.
- **Set the language model**: choose which model (or model alias) the agent uses by default. If your organization has configured multiple LLMs, you can assign different models to different agents. For example, a faster model for @Universal and a more capable model for @Coder.
- **Configure completion notifications**: toggle whether the agent sends a notification when it completes a task. This is particularly useful for long-running Agent Mode sessions.

Changes to agent settings take effect immediately and no restart required.

## Variables

The **Variables** category shows all context variables that are available in the chat via the `#` prefix. This includes both built-in variables (such as `#currentRelativeFilePath`) and any custom variables your organization has defined.

For custom variables, you can set or update their values here. Variables defined in this panel are injected into agent prompts automatically when referenced.

## MCP Servers

The **MCP Servers** category lets you manage connections to external tools and data sources through the Model Context Protocol. Studio AI supports both local stdio-based and remote HTTP/SSE MCP servers.

To add a new server, click **Add MCP Server** and fill in the connection details:

- **Name** - a display name for the server
- **Type** - `stdio` for a local process or `HTTP/SSE` for a remote endpoint
- **Command / URL** - the command to run (stdio) or the endpoint URL (HTTP/SSE)
- **Arguments and environment variables** - any additional parameters the server requires

Once connected, the tools provided by the MCP server become available to agents automatically. You can edit or remove any server using the action buttons in the list.

For full details, see [MCP Servers](12_mcp_servers.md).

## Token Usage

The **Token Usage** category shows a summary table of all LLM usage accumulated in the current Studio session, broken down by model:

<table>   <thead>     <tr>       <th>Column</th>       <th>Description</th>     </tr>   </thead>   <tbody>     <tr>       <td><strong>Model</strong></td>       <td>The LLM that handled the requests</td>     </tr>     <tr>       <td><strong>Input Tokens</strong></td>       <td>Total tokens sent to the model (prompts, context, files)</td>     </tr>     <tr>       <td><strong>Output Tokens</strong></td>       <td>Total tokens generated by the model (responses)</td>     </tr>     <tr>       <td><strong>Total Tokens</strong></td>       <td>Combined input + output</td>     </tr>     <tr>       <td><strong>Last Used</strong></td>       <td>Timestamp of the most recent request to this model</td>     </tr>   </tbody> </table>

Use this table to understand which models are consuming the most tokens, and to spot unexpectedly large requests (for example, attaching very large files as context).

For the full history of individual requests and responses, use the **AI Agent History** panel. See [AI History and Token Usage](08_token_consumption_and_ai_history.md).

## Prompt Snippets

The **Prompt Snippets** category lists reusable prompt snippet files (`.prompttemplate` files) that can be referenced across multiple agent prompt templates. Creating a fragment here makes it available to any agent prompt that imports it.

For details on writing and using prompt fragments, see [Customizing Agent Prompts](07_ai_configuration_and_prompts.md).

## Tools

The **Tools** category shows all the function tools registered with Studio AI: the capabilities agents can call during a conversation, such as reading files, running commands, or querying the project structure. You can review which tools are active and, where applicable, enable or disable individual tools.

## Skills and Slash Commands

The **Skills & Slash Commands** category shows the **Skill Directories** setting, lists every skill installed in the project with its name and description, and lists the slash commands. Open a skill to edit its `SKILL.md` (under `.agents/skills/<name>/`), or open a slash command to edit its template. For how skills are structured and invoked, see [Skills and Slash Commands](11_skills_and_slash_commands.md).

## Model Aliases

The **Model Aliases** category is where you define the named LLM identifiers that agents use. Instead of hardcoding a specific model name in every agent configuration, agents reference an alias such as `default/code` or `default/universal`, and this category defines what model (or ordered list of models) each alias maps to.

Each alias entry contains:

- **Alias name** - the identifier referenced in agent configurations (e.g., `default/code`)
- **Priority list** - an ordered list of LLM provider/model pairs. Studio AI uses the first available model in the list; if it is unavailable, it falls through to the next

**Default aliases in Studio AI:**

<table>   <thead>     <tr>       <th>Alias</th>       <th>Typical use</th>     </tr>   </thead>   <tbody>     <tr>       <td><code>default/code</code></td>       <td>@Coder and other code-focused agents</td>     </tr>     <tr>       <td><code>default/universal</code></td>       <td>@Universal and general question-answering agents</td>     </tr>     <tr>       <td><code>default/fabric</code></td>       <td>K2view-specific agents (e.g., @k2-assistant, @k2-worker, @Interfaces, @Data-Product-Explorer). From V8.5.2 it resolves to Claude Opus 5.5 (<code>claude-opus-5-5</code>) by default.</td>     </tr>     <tr>       <td><code>default/fast</code></td>       <td>Lightweight, latency-sensitive tasks</td>     </tr>     <tr>       <td><code>default/summarize</code></td>       <td>Session naming, summary, and background agents</td>     </tr>     <tr>       <td><code>default/code-completion</code></td>       <td>Inline code completion</td>     </tr>   </tbody> </table>

To change which model backs an alias, edit the priority list. This is the recommended way to swap LLM providers across all agents at once without updating each agent individually.
