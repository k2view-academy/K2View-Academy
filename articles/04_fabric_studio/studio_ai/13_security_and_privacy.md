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
4. The server makes an outbound HTTPS call to the configured LLM provider (for example, Anthropic, OpenAI, Google) using the API key provisioned in Studio settings. When [Fabric is configured as the LLM provider](/articles/24_non_DB_interfaces/15_LLM_interface.md#fabric-as-an-llm-provider), the call goes to Fabric instead, and Fabric calls the upstream provider using the credentials held in the project's AI LLM interface.
5. The provider's response streams back to the server, which forwards it to the browser to display in the chat.

K2View does not host or proxy LLM traffic. The API key is held by your Fabric Dev environment - either in the Studio settings, or, when Fabric is the provider, in the project's AI LLM interface - and is used to authenticate against the chosen provider. Network egress to the provider must be permitted by your network policy.

The one K2view-hosted service Studio AI calls is the **K2view knowledge-base service** (`askme.k2view.com`), used by the `kbSearch` tool of @KB. It receives only the search text, never workspace content, and returns public K2view documentation. Because the knowledge base is public, the tool runs without a confirmation prompt; disable @KB if search text must not leave your environment.

For fully on-premises operation, Studio AI supports **local model providers** such as Ollama, and self-hosted models - used directly or behind the Fabric AI LLM interface. When configured like that, no data leaves your environment, apart from @KB's knowledge-base searches (disable @KB to prevent them) - the model runs on the Fabric Dev server (or a network-reachable host) and there is no outbound call to a third-party provider. See [Choose and Provision LLM](01_getting_started_with_studio_ai.md#choose-and-provision-llm) for the full list of supported providers. The same applies if you choose a self-hosted LLM at your organization's private premises or cloud, using the *Custom Models* settings. 

### API Keys

API keys are entered in Studio AI settings (see [Getting Started with Studio AI](01_getting_started_with_studio_ai.md#choose-and-provision-llm)) and are stored in clear text in the Studio's settings file on the Fabric Dev server. They are not sent to K2View. Like any other setting, a key entered this way is visible to whoever opens that Studio's Settings.

To keep keys out of the settings, prefer one of:

- The provider's environment variable on the Studio server (for example `ANTHROPIC_API_KEY`), where the provider supports it.
- For custom providers, a `${env:...}` or `${file:...}` reference in `apiKey` or `headers`, which the Studio server resolves and never sends to the browser - see [Secret References](16_custom_llm_providers.md#secret-references).
- Fabric as the provider, where no key is held by the Studio at all.

Alternatively, configure [Fabric as the LLM provider](16_custom_llm_providers.md#fabric-as-a-provider). Users then enter no API key at all: the provider credentials are defined once on the Fabric [AI LLM interface](/articles/24_non_DB_interfaces/15_LLM_interface.md), and access to the endpoint is controlled by the **LLM_INVOKE** permission granted to Fabric roles. The Studio authenticates to Fabric with the signed-in user's Fabric session token (`${fabric:jwt}`), which the Studio server resolves for each request. The token is never written to the Studio settings.



## What Each Request Contains

Every request that Studio AI sends to the LLM contains, at minimum:

- The **system prompt** of the addressed agent - a static text describing the agent's role, capabilities, and behavior rules. You can view and edit any agent's system prompt; see [Agents Prompt Customization](07_ai_configuration_and_prompts.md).
- The **conversation history** of the current chat session (all prior user messages and agent responses). Sessions are local to a Studio user.
- The **current user message** and any context the user attached to it.
- The **tool definitions** the agent is allowed to call (their names, descriptions, and parameter schemas - not their implementations or results).
- For @k2-assistant, from V8.5.2, an **IDE context** block with each user message: the path of the active file, the list of open editors, and the list of files attached to the chat. It carries paths only - a file's *content* is sent only when the file is attached or read by a tool. (It used to be part of the system prompt; it moved to the message so that switching editor tabs does not defeat prompt caching.)

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

Tool availability per agent is visible in the **Tools** category of AI Configuration. See [AI Configuration: Managing Agents and Settings](06_ai_configuration_and_settings.md#tools).

### 3. The agent's prompt template auto-injects content

Prompt templates support `~{readFile('...')}`, `~{fragment('...')}`, and other function references that pull content into the prompt every time the agent runs. See [Agents Prompt Customization](07_ai_configuration_and_prompts.md#function-tool-references-functionname). This is how some K2View agents (for example, @Data-Product-Explorer or @Interfaces) include project metadata by default.

### 4. A capability is enabled

[Agent Capabilities](10_agent_capabilities.md) such as **AppTester** or shell-command execution can introduce additional tools - and therefore additional data sources - for the duration of a session. Capabilities are off by default and explicitly enabled by the user, with one exception: **Shell Execution is on by default in @k2-assistant's Act mode** (it is off in Ask and Plan mode). Shell commands are still subject to the tool confirmation and the shell allow/deny lists - see [AI Features Settings](15_ai_features_settings.md#security-relevant-settings).

For agents that run on an Anthropic model, such as @k2-assistant, the agent's page in AI Configuration also lists **Server Tools** - **Web Fetch** and **Web Search**, which run on Anthropic's infrastructure. They are off by default. When selected, they are auto-approved: the model may fetch URLs or run web searches at any time, and the search text or URL is handled by Anthropic.

### 5. An MCP server is connected

[MCP Servers](12_mcp_servers.md) expose external tools to agents. When a connected MCP tool is called, its inputs and outputs flow through the prompt. Each MCP server has its own data-handling characteristics; see the security notes in the MCP article.



## Per-Agent Data Profile

The table below summarizes, for each commonly used agent, the kinds of project information that may be sent to the LLM as part of doing its job. The list reflects each agent's tools and prompt template - to see them yourself, open the **Agents** category in AI Configuration, open the agent, and look at its **Prompts** and **Used Tools** sections. See [Customizing Agent Prompts](07_ai_configuration_and_prompts.md#opening-the-prompt-editor).

<table>
  <thead>
    <tr>
      <th>Agent</th>
      <th>Project information that may be sent to the LLM</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>@k2-assistant</strong></td>
      <td>Project structure and the content of files it reads directly to answer or make a small edit; the paths of the active file, open editors and attached files (IDE context, with each message); Fabric logs and the results of Fabric commands and REST calls it runs. For larger requests in Act mode it delegates to @k2-worker, which then applies to the same request.</td>
    </tr>
    <tr>
      <td><strong>@k2-worker</strong></td>
      <td>Content of workspace files it reads or edits, plus terminal output, test results, and build logs when it runs commands as part of a delegated task. See [Studio AI Agents](02_studio_ai_agents_reference.md#k2-worker).</td>
    </tr>
    <tr>
      <td><strong>@Interfaces</strong></td>
      <td>The list of interfaces in the project; the list of schemas for an interface; the table and column metadata of a schema. Does <strong>not</strong> read or send any row data from the source databases.</td>
    </tr>
    <tr>
      <td><strong>@Data-Product-Explorer</strong></td>
      <td>Data Product / Logical Unit metadata: the list of LUs or Data Products and, for a selected one, its tables and schema. <strong>When asked for an instance (IID), also the real instance data, and common/reference table rows</strong> - fetching an instance can trigger a sync from the source systems. These tools ask for confirmation before each call (see <a href="#tool-confirmations">Tool Confirmations</a>). Supersedes the earlier <code>@LU</code> agent, which no longer exists.</td>
    </tr>
    <tr>
      <td><strong>@KB</strong></td>
      <td>Nothing from the workspace. The search text is sent to the public K2view knowledge-base service (<code>askme.k2view.com</code>), and the retrieved K2view documentation content is added to the conversation.</td>
    </tr>
    <tr>
      <td><strong>@Architect</strong></td>
      <td>Project structure and the content of files relevant to answering a question about the project. Earlier documentation described @Architect as also generating implementation plans; that capability is unconfirmed in the current version. See [Other Studio AI Agents](14_other_studio_ai_agents.md#architect).</td>
    </tr>
    <tr>
      <td><strong>@Coder</strong></td>
      <td>Content of workspace files; in Agent Mode also terminal output, test results, and build logs. See [Other Studio AI Agents](14_other_studio_ai_agents.md#coder-superseded-by-k2-worker).</td>
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
      <td><strong>@Broadway-Explain</strong> <em>(disabled by default)</em></td>
      <td>The list of Broadway core actors (Fabric framework reference); content of the active Broadway flow or any flow/actor file the agent reads; flow validation errors and warnings.</td>
    </tr>
    <tr>
      <td><strong>@Broadway-Edit</strong> <em>(disabled by default)</em></td>
      <td>The list of Broadway core actors (Fabric framework reference); the live in-memory state of the active Broadway flow - stages, actors, links, and port values.</td>
    </tr>
    <tr>
      <td><strong>@Graphit</strong> <em>(disabled by default)</em></td>
      <td>The JSON of the active Graphit file.</td>
    </tr>
    <tr>
      <td><strong>@ClaudeCode</strong> <em>(disabled by default)</em></td>
      <td>Content of workspace files; in long autonomous sessions also terminal output and test results.</td>
    </tr>
  </tbody>
</table>



Agents marked *disabled by default* - together with AppTester, ProjectInfo and Codex - send nothing unless someone enables them in the **Agents** category of the Studio's AI Configuration. See [Other Studio AI Agents](14_other_studio_ai_agents.md#agents-disabled-by-default).

Custom agents created by your team (see [Creating Custom Agents](09_creating_custom_agents.md)) follow the same rules but may send different content depending on how they are configured.

### Agents and Skills from the Studio AI Core Artifacts Extension

K2View publishes a Studio extension, the **Studio AI Core Artifacts** extension (available on K2Exchange), that supplies the Fabric **skills** described throughout this article set. See [Getting Started with Studio AI](01_getting_started_with_studio_ai.md#install-the-studio-ai-core-artifacts-extension). After installation, its artifacts are located under `.agents/skills/` in the project, with some reference content under `.fabric-wiki/`.

From V8.5.2, @k2-assistant and @k2-worker are built into the Studio rather than supplied by the extension, so their prompts are part of the Studio release. Copies of these two agents that earlier versions installed under `.agents/agents/` are ignored.

The extension imports a set of **skills** that any agent can call via slash command (see [Skills and Slash Commands](11_skills_and_slash_commands.md)). At the time of writing, the installed skill catalog was: `broadway-actor-builder`, `broadway-flow-builder`, `data-product-builder`, `data-product-cowork`, `debug-broadway-flow`, `fabric-commands`, `fabric-java-docs`, `fabric-overview`, `fabric-project-helper`, `fabric-troubleshooting`, `interface-builder`, `report-builder`, and `web-service-builder`. Each skill is a `SKILL.md` file that the agent reads when activated; when the agent calls a skill, the skill's content is appended to the prompt and the agent then reads any further reference files the skill points to.

Two important properties of the imported plugin content for security review:

- **The bundled `.fabric-wiki/` content is K2View Fabric reference material** - actor schemas, Java API documentation, example flow patterns. It does not contain customer project content.
- **None of the skills or agents auto-inject customer data**. They auto-inject only metadata about themselves (skill list, product info) and read reference or workspace files on demand through the same tool mechanism described in [What Each Request Contains](#what-each-request-contains).



## Audit and Visibility

Studio AI provides full visibility into every request and response. For any chat message, you can view the **complete prompt** that was sent to the LLM, including the system prompt, all auto-injected content, the conversation history, the user message, and tool-call results.

Open the **AI Agent History** panel via **More Actions...** ("…") in the AI Chat panel toolbar, then **Open AI Agent History**, and select the agent and request you want to inspect. The panel shows the full request body and the full response, with a unique request ID for support correlation. See [Viewing Token Consumption and AI History](08_token_consumption_and_ai_history.md).

This is the authoritative source of truth for what left your environment on any given request, and it is the recommended tool for security teams who want to audit Studio AI activity in a specific Fabric Dev environment.

## Controls

Studio AI provides several layered controls. Use them in combination to match your organization's data-handling policy.

> **Scope of these controls.** Studio AI settings - including AI enablement, provider API keys, enabled agents, MCP servers and tool confirmations - are **Studio user and workspace settings**. They apply to the Studio where they are set, and a developer with access to that Studio's Settings can change them. Studio AI does not currently provide a central, administrator-enforced policy that applies across all Studios. The controls an organization holds centrally are: which LLM provider credentials it issues to developers, and access to the LLMs it configures in Fabric (see [Choose where data goes](#choose-where-data-goes)).

### AI enablement is a per-user setting

AI features are off by default. They become active only when **Enable AI** is turned on in the Studio's Settings and an LLM provider is available. With AI disabled, no data is sent to any provider. See [Enabling Studio AI](01_getting_started_with_studio_ai.md#enabling-studio-ai).

This is a Studio user/workspace setting, not a global switch: each developer can turn it on or off in their own Studio. Without provider credentials - neither an API key of their own nor access to an LLM configured in Fabric - enabling it has no effect, since there is no model to send requests to.

### Choose where data goes

The request goes to whichever LLM provider the Studio is configured with - see [Where Requests Go](#where-requests-go).

**Fabric as the provider centralizes access.** With [Fabric as the provider](16_custom_llm_providers.md#fabric-as-a-provider), the model and its credentials are defined once, by an administrator, on the Fabric AI LLM interface. Developers need no API key, and the **LLM_INVOKE** permission controls which Fabric roles may call the Fabric LLM endpoint.

Centralized access is not exclusive routing: the Fabric entry is one provider among the others, and a developer who has their own API key can still configure another provider in their Studio settings. To limit which providers are used, do not issue provider API keys to developers, and give them access to LLMs only through Fabric.

**Keeping data in your environment.** Configuring Fabric as the provider does not by itself keep data in your environment: Fabric forwards each request to the upstream provider that the AI LLM interface points to. To keep prompts and project content from leaving your environment, **host the LLM within your environment** - a local model provider such as Ollama, or a self-hosted OpenAI-compatible model - and use it either directly (see [Custom LLM Providers](16_custom_llm_providers.md)) or as the target of the Fabric AI LLM interface. Note that @KB's knowledge-base search still calls the K2view knowledge-base service; disable @KB to prevent that.

To restrict usage to a specific cloud provider that has an enterprise data-handling agreement with your organization, issue credentials for that provider only - directly, or through the Fabric AI LLM interface.

### Disable individual agents

In the **Agents** category of AI Configuration, you can disable any built-in agent (a Studio setting, like the others in this section). A disabled agent does not appear in the chat and cannot be invoked, so no data is ever sent through it. See [AI Configuration: Managing Agents and Settings](06_ai_configuration_and_settings.md#agents).

### Customize agent prompts

Edit an agent's prompt template to remove or change any auto-injected content you do not want sent. For example, you can remove a `~{readFile(...)}` reference from the template to stop a specific file from being included on every request. See [Agents Prompt Customization](07_ai_configuration_and_prompts.md).

### Capabilities and MCP are opt-in

[Agent Capabilities](10_agent_capabilities.md) reset to their defaults at the start of every session - off, except Shell Execution in @k2-assistant's Act mode - and [MCP Servers](12_mcp_servers.md) must be explicitly added in the Studio's AI Configuration or settings. Neither adds data sources silently.

### Use Ask or Plan mode for read-only work

In @k2-assistant's **Ask** and **Plan** modes, the assistant cannot edit project files, delegate to @k2-worker, or run commands that change the Fabric server, and Shell Execution is off. It moves to Act mode only after the user accepts a switch. See [Ask, Plan and Act Modes](05_plan_first_development.md).

### Tool Confirmations

In the Studio, the default tool confirmation (`ai-features.chat.defaultToolConfirmation`) is **Always Allow**: most tool calls run without a per-call prompt. From V8.5.2, the tools that read the Data Product schemas and data from the Fabric server ask for confirmation before each call, and choosing "Always Allow" for them shows an explicit warning of what the tool does:

<table>
  <thead>
    <tr><th>Tool</th><th>Why it asks</th></tr>
  </thead>
  <tbody>
    <tr><td><code>getLuSchema</code></td><td>Reads the Data Product schema (table, column and relationship metadata) from the Fabric server into the chat.</td></tr>
    <tr><td><code>getLuInstanceData</code>, <code>getLuInstanceTableData</code></td><td>Read real Data Product instance data into the chat; fetching an instance can trigger a sync from the source systems.</td></tr>
    <tr><td><code>getCommonSchema</code></td><td>Reads the common/reference schema from the Fabric server into the chat.</td></tr>
    <tr><td><code>getCommonTableData</code></td><td>Reads real common/reference table data into the chat.</td></tr>
  </tbody>
</table>

The tools that run Fabric commands and Studio commands (which can deploy, change data on the Fabric server, compile or restart) carry the same kind of warning. To require confirmation for more tools, set per-tool entries in `ai-features.chat.toolConfirmation`, or change the default - see [AI Features Settings](15_ai_features_settings.md#chat-behavior).

### Audit with AI Agent History

Use the **AI Agent History** panel (described above) to periodically review what is actually being sent - both during a rollout and as part of ongoing security monitoring.



## At a Glance

- **Where requests go:** outbound HTTPS from the Fabric Dev environment directly to the configured LLM provider. No K2View proxy. The only K2view-hosted service called is the knowledge-base search of @KB, which receives the search text only.
- **Who holds the API key:** the customer's Fabric Dev environment - in Studio settings, or in the project's AI LLM interface when Fabric is the provider (the Studio then authenticates with the user's Fabric session token).
- **What is sent by default:** the agent's system prompt, the conversation history, and the user's current message; for @k2-assistant also the paths of the active file, open editors and attached files. Some agents additionally auto-inject project structure or configuration metadata (see the per-agent table above).
- **What is sent on demand:** files the user attaches, files an agent reads via tools, Data Product instance and reference data the user confirms, and outputs of any enabled capabilities or MCP servers.
- **What is not sent:** API keys, credentials, server environment variables, or any data outside the workspace.
- **How to audit:** every request is fully visible in the AI Agent History panel inside the Studio.
- **Who controls the settings:** each Studio's user and workspace settings. There is no central, administrator-enforced policy across Studios; central control comes from which provider credentials are issued, and from LLM_INVOKE on the Fabric LLM endpoint.
- **How to keep data in your environment:** host the LLM within your environment (directly, or behind the Fabric AI LLM interface). Fabric as the provider alone still forwards requests to the upstream provider.
- **How to restrict:** issue provider credentials only through Fabric, host the LLM locally, leave AI disabled, disable specific agents, use Ask or Plan mode, require tool confirmations, edit agent prompts to remove auto-injected content, or leave capabilities and MCP servers turned off.

