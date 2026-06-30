# Security and Privacy

Studio AI is an in-Studio assistant for K2View project implementation and coding productivity. This article describes how it handles data - what reaches the LLM, where the request goes, and the controls available to security and compliance teams evaluating Studio AI before enabling it for their organization.



## Intended Use

Studio AI assists developers with implementation tasks inside a Fabric project. In normal use, the content sent to the LLM provider is project implementation material - schemas and table definitions, Broadway flow YAML, Java enrichment code, Logical Unit configuration, Graphit services, and similar - not customer end-user data or production business records. 

The project artifacts that may reach the LLM provider are relevant project files the AI needs in order to examine the project and suggest changes or fixes - either attached by the user via `#file`, `#_f`, `#selectedText`, drag-and-drop, or the paperclip button (see [Using the AI Chat](03_using_the_ai_chat.md)), or read by the agent on demand through its tools.

If a project's working tree happens to contain business data - sample data files, fixtures, or test datasets checked into the project - that data can be sent to the provider if the user or an agent reads it. There is no built-in scrubbing of file content; see [Controls](#controls) below for ways to restrict this, including running fully on-premises with a local model provider.



## Where Requests Go

Studio AI runs inside the K2View Web Studio, served from a **Fabric Dev environment** deployed in K2View cloud or in your infrastructure. The user accesses the Studio in a web browser, but the AI logic runs in the Studio backend on this Fabric Dev server.

The request path is:

1. The user types a message in the AI Chat panel in the browser.
2. The browser forwards the message and any attached context references to the server running inside your Fabric Dev environment.
3. The server assembles the full prompt (system prompt, conversation history, attached files, tool-call results, and auto-injected content from the agent's prompt template; it may also contain K2View-provided skills and reference content, when relevant).
4. The server makes an outbound HTTPS call directly to the configured LLM provider (for example, Anthropic, OpenAI, Google) using the API key provisioned in Studio settings.
5. The provider's response streams back to the server, which forwards it to the browser to display in the chat.

K2View does not host or proxy LLM traffic. The API key is held by your Fabric Dev environment and is used to authenticate directly against the chosen provider. Network egress from the Fabric Dev environment to the provider must be permitted by your network policy.

For fully on-premises operation, Studio AI supports **local model providers** such as Ollama. When configured like that, no data leaves your environment - the model runs on the Fabric Dev server (or a network-reachable host) and there is no outbound call to a third-party provider. See [Choose and Provision LLM](01_getting_started_with_studio_ai.md#choose-and-provision-llm) for the full list of supported providers. The same applies if you choose a self-hosted LLM at your organization's private premises or cloud, using the *Custom Models* settings. 

### API Keys

API keys are entered in Studio AI settings (see [Getting Started with Studio AI](01_getting_started_with_studio_ai.md#choose-and-provision-llm)) and are stored as part of the Studio setup on the Fabric Dev server. They are not sent to K2View and are not transmitted to the browser after being saved.



## What Each Request Contains

Every request that Studio AI sends to the LLM contains, at minimum:

- The **system prompt** of the addressed agent - a static text describing the agent's role, capabilities, and behavior rules. You can view and edit any agent's system prompt; see [Agents Prompt Customization](07_ai_configuration_and_prompts.md).
- The **conversation history** of the current chat session (all prior user messages and agent responses). Sessions are local to a Studio user.
- The **current user message** and any context the user attached to it.
- The **tool definitions** the agent is allowed to call (their names, descriptions, and parameter schemas - not their implementations or results).

The following are **not** sent automatically:

- Files outside the current workspace, unless an agent's prompt template explicitly references them.
- Files inside the workspace that are not attached by the user, not read by an agent tool call, and not referenced by the agent's prompt template.
- Files matched by standard ignore patterns the agent respects (for example, `.git/`, `node_modules/`).
- Credentials, API keys, or environment variables of the Fabric Dev server.

Additional data is included in a request only when one of the following happens:

### 1. The user attaches context explicitly

Anything the user attaches via `#file:`, `#_f`, `#selectedText`, `#imageContext`, drag-and-drop, or the paperclip button is included verbatim in the prompt. This is the most common way project data reaches the LLM, and it is fully under the user's control.

### 2. The agent calls a tool

Most agents have access to tools that can read files, list directories, query workspace metadata, or run commands. When an agent calls a tool, the tool's output is appended to the conversation and becomes part of subsequent requests. For example, if @Coder calls a `readFile` tool to inspect a Java file, the file content becomes part of the request to the next model turn.

Tool availability per agent is visible in the **Tools** tab of AI Configuration. See [AI Configuration: Managing Agents and Settings](06_ai_configuration_and_settings.md#tools-tab).

### 3. The agent's prompt template auto-injects content

Prompt templates support `~{readFile('...')}`, `~{fragment('...')}`, and other function references that pull content into the prompt every time the agent runs. See [Agents Prompt Customization](07_ai_configuration_and_prompts.md#function-tool-references-functionname). This is how some K2View agents (for example, @LU or @Interfaces) include project metadata by default.

### 4. A capability is enabled

[Agent Capabilities](10_agent_capabilities.md) such as **AppTester** or shell-command execution can introduce additional tools - and therefore additional data sources - for the duration of a session. Capabilities are off by default and explicitly enabled by the user.

### 5. An MCP server is connected

[MCP Servers](12_mcp_servers.md) expose external tools to agents. When a connected MCP tool is called, its inputs and outputs flow through the prompt. Each MCP server has its own data-handling characteristics; see the security notes in the MCP article.



## Per-Agent Data Profile

The table below summarizes, for each built-in agent, the kinds of project information that may be sent to the LLM as part of doing its job. The list reflects each agent's tools and prompt template - to see them yourself, open the **Agents** tab in AI Configuration and click **Edit Prompt** next to an agent.

<table>
  <thead>
    <tr>
      <th>Agent</th>
      <th>Project information that may be sent to the LLM</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>@Architect</strong></td>
      <td>Project structure and the content of files the agent decides are relevant for producing a plan.</td>
    </tr>
    <tr>
      <td><strong>@Coder</strong></td>
      <td>Content of workspace files; in Agent Mode also terminal output, test results, and build logs.</td>
    </tr>
    <tr>
      <td><strong>@Code Reviewer</strong></td>
      <td>The current Git diff, the content of files referenced by the diff, and any available build/lint/test output.</td>
    </tr>
    <tr>
      <td><strong>@Universal</strong></td>
      <td>Only what the user attaches in the message - no automatic workspace access.</td>
    </tr>
    <tr>
      <td><strong>@GitHub</strong></td>
      <td>Current branch and Git diff; issues, pull requests, and other repository data fetched from the configured Git remote; commit messages.</td>
    </tr>
    <tr>
      <td><strong>@Studio-Commands</strong></td>
      <td>The list of registered Studio commands and the output of any command the agent invokes on the user's behalf.</td>
    </tr>
    <tr>
      <td><strong>@Broadway-Explain</strong></td>
      <td>The list of Broadway core actors (Fabric framework reference); content of the active Broadway flow or any flow/actor file the agent reads; flow validation errors and warnings.</td>
    </tr>
    <tr>
      <td><strong>@Broadway-Edit</strong></td>
      <td>The list of Broadway core actors (Fabric framework reference); the live in-memory state of the active Broadway flow - stages, actors, links, and port values.</td>
    </tr>
    <tr>
      <td><strong>@Graphit</strong></td>
      <td>The JSON of the active Graphit file.</td>
    </tr>
    <tr>
      <td><strong>@Interfaces</strong></td>
      <td>The list of interfaces in the project; the list of schemas for an interface; the table and column metadata of a schema. Does <strong>not</strong> read or send any row data from the source databases.</td>
    </tr>
    <tr>
      <td><strong>@LU</strong></td>
      <td>LU metadata - the list of LUs and for selected LU its tables.</td>
    </tr>
    <tr>
      <td><strong>@KB</strong></td>
      <td>Nothing from the workspace. Sends the user question and retrieves content from K2View product documentation.</td>
    </tr>
    <tr>
      <td><strong>@ClaudeCode</strong></td>
      <td>Content of workspace files; in long autonomous sessions also terminal output and test results.</td>
    </tr>
  </tbody>
</table>



Custom agents created by your team (see [Creating Custom Agents](09_creating_custom_agents.md)) follow the same rules but may send different content depending on how they are configured.

### Agents and Skills from the AI Studio Core Artifacts Extension

K2View publishes a Studio extension - the **AI Studio Core Artifacts** - that currently adds builder agents and reusable skills tailored to Fabric project work. After installation, some of the artifacts are copied and located  `.prompts/`, `.agents/` and `.fabric-wiki/`.

The extension currently contributes two additional agents:

<table>
  <thead>
    <tr>
      <th>Agent</th>
      <th>Project information that may be sent to the LLM</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>@Broadway-Flow-Builder</strong></td>
      <td>The list of available skills and a summary of recent project activity (recently changed files, project info, task context); content of workspace files the agent reads to build or modify <code>.flow</code> YAML files; bundled K2View reference content from <code>.fabric-wiki/</code> (~260 Broadway actor schemas, example flow patterns, Fabric Java API docs).</td>
    </tr>
    <tr>
      <td><strong>@Broadway-Actor-Builder</strong></td>
      <td>Same recent-activity summary as @Broadway-Flow-Builder; content of workspace files the agent reads to build or modify Broadway actor files (Java implementation and <code>.actor</code> JSON); bundled K2View reference content from <code>.fabric-wiki/</code> (actor schemas and Fabric Java API docs); Java compile diagnostics.</td>
    </tr>
  </tbody>
</table>

In addition to those two agents, the extension imports a set of **skills** that any agent can call via slash command (see [Skills and Slash Commands](11_skills_and_slash_commands.md)): `fabric-orchestrator`, `broadway-flow-builder`, `broadway-actor-builder`, `lu-builder`, `interface-builder`, `web-service-builder`, `fabric-project-helper`, and `fabric-java-api`. Each skill is a markdown file that the agent reads when activated; when the agent calls a skill, the skill's content is appended to the prompt and the agent then reads any further reference files the skill points to.

Two important properties of the imported plugin content for security review:

- **The bundled `.fabric-wiki/` content is K2View Fabric reference material** - actor schemas, Java API documentation, example flow patterns. It does not contain customer project content.
- **None of the skills or agents auto-inject customer data**. They auto-inject only metadata about themselves (skill list, product info) and read reference or workspace files on demand through the same tool mechanism described in [What Each Request Contains](#what-each-request-contains).



## Audit and Visibility

Studio AI provides full visibility into every request and response. For any chat message, you can view the **complete prompt** that was sent to the LLM, including the system prompt, all auto-injected content, the conversation history, the user message, and tool-call results.

Open the **AI Agent History** panel from **View > AI Agent History** and select the agent and request you want to inspect. The panel shows the full request body and the full response, with a unique request ID for support correlation. See [Viewing Token Consumption and AI History](08_token_consumption_and_ai_history.md).

This is the authoritative source of truth for what left your environment on any given request, and it is the recommended tool for security teams who want to audit Studio AI activity in a specific Fabric Dev environment.

## Controls

Studio AI provides several layered controls. Use them in combination to match your organization's data-handling policy.

### Enable or disable AI globally

AI features are off until an administrator explicitly enables them and provisions an LLM provider. With AI disabled, no data is sent to any provider. See [Enabling Studio AI](01_getting_started_with_studio_ai.md#enabling-studio-ai).

### Choose where data goes

The LLM provider is configured per Fabric Dev environment. To keep all data on-premises, configure a local hosted LLM provider instead of a foundation cloud provider. To restrict to a specific cloud provider that has an enterprise data-handling agreement with your organization, configure only that provider.

### Disable individual agents

In the **Agents** tab of AI Configuration, you can disable any built-in agent. A disabled agent does not appear in the chat and cannot be invoked, so no data is ever sent through it. See [AI Configuration: Managing Agents and Settings](06_ai_configuration_and_settings.md#agents-tab).

### Customize agent prompts

Edit an agent's prompt template to remove or change any auto-injected content you do not want sent. For example, you can remove a `~{readFile(...)}` reference from the template to stop a specific file from being included on every request. See [Agents Prompt Customization](07_ai_configuration_and_prompts.md).

### Capabilities and MCP are opt-in

[Agent Capabilities](10_agent_capabilities.md) reset to off at the start of every session, and [MCP Servers](12_mcp_servers.md) must be explicitly added by an administrator. Neither adds data sources silently.

### Audit with AI Agent History

Use the **AI Agent History** panel (described above) to periodically review what is actually being sent - both during a rollout and as part of ongoing security monitoring.



## At a Glance

- **Where requests go:** outbound HTTPS from the Fabric Dev environment directly to the configured LLM provider. No K2View proxy.
- **Who holds the API key:** the customer's Fabric Dev environment.
- **What is sent by default:** the agent's system prompt, the conversation history, and the user's current message. Some agents additionally auto-inject project structure or configuration metadata (see the per-agent table above).
- **What is sent on demand:** files the user attaches, files an agent reads via tools, and outputs of any enabled capabilities or MCP servers.
- **What is not sent:** API keys, credentials, server environment variables, or any data outside the workspace.
- **How to audit:** every request is fully visible in the AI Agent History panel inside the Studio.
- **How to restrict:** disable AI globally, choose a local LLM, disable specific agents, edit agent prompts to remove auto-injected content, or leave capabilities and MCP servers turned off.

