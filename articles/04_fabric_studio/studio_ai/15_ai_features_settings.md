# AI Features Settings (Preferences)

Studio AI has three separate configuration surfaces, and it is easy to confuse them:

<table>
  <thead>
    <tr>
      <th>Surface</th>
      <th>Scope</th>
      <th>Covered in</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>AI Configuration panel</strong></td>
      <td>Agents, MCP servers, token usage history, prompt fragments, tools, skills, model aliases. Opened via the AI Chat toolbar's <strong>More Actions...</strong> ("…") menu.</td>
      <td><a href="06_ai_configuration_and_settings.md">AI Configuration: Managing Agents and Settings</a></td>
    </tr>
    <tr>
      <td><strong>Session Settings</strong></td>
      <td>Overrides that apply only to the current chat session (Confirmation Timeout, Server-Side Compaction).</td>
      <td><a href="03_using_the_ai_chat.md#session-settings">Using the AI Chat</a></td>
    </tr>
    <tr>
      <td><strong>AI Features settings (this article)</strong></td>
      <td>Global and per-provider preferences: enablement, LLM provider connections, chat behavior, compaction, security-relevant defaults, and more. Part of Studio's standard Settings UI.</td>
      <td>This article</td>
    </tr>
  </tbody>
</table>

This article covers the third surface: a large set of preferences (77 at the time of writing) under the **AI Features** category of Studio's standard Settings UI, most of which are not exposed anywhere in the AI Configuration panel.

## Opening AI Features Settings

1. Open the command palette (**F1**) and run **Preferences: Open Settings (UI)**, or press **Ctrl+,**.
2. In the left navigation, expand **AI Features**. It contains around 28 sub-sections (Agent Mode, Agent Settings, AI Enablement, Anthropic, Chat, Code Completion, and more).
3. Alternatively, type a keyword (for example, "compaction" or "token usage") into the **Search settings** box at the top; matching settings from any category are shown directly.

Settings here apply globally to your Studio user/workspace, unlike the per-session overrides in article 03.

## AI Enablement and LLM Providers

Turning AI on and connecting a provider is covered in [Getting Started with Studio AI](01_getting_started_with_studio_ai.md#enabling-studio-ai). The same **AI Enablement** section lives under AI Features in this Settings UI.

Each supported LLM provider has its own sub-section here for API keys, model lists, and provider-specific options:

<table>
  <thead>
    <tr>
      <th>Sub-section</th>
      <th>What it configures</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>Anthropic / Anthropic Custom</td><td>Anthropic API key, the list of Anthropic models available to agents, and custom (self-defined) Anthropic model entries. See <a href="16_custom_llm_providers.md">Custom LLM Providers</a>.</td></tr>
    <tr><td>Open AI Official Models / Open AI Custom Models</td><td>OpenAI API key, official model list, custom model entries, and whether to use the Responses API (<code>useResponseApi</code>). See <a href="16_custom_llm_providers.md">Custom LLM Providers</a>.</td></tr>
    <tr><td>Google</td><td>API key, model list, and retry/backoff settings for rate-limit and other errors.</td></tr>
    <tr><td>Hugging Face</td><td>API key and model list.</td></tr>
    <tr><td>Ollama</td><td>Ollama host URL and model list, for a locally or network-hosted Ollama server.</td></tr>
    <tr><td>Llamafile</td><td>Configured Llamafile executables.</td></tr>
    <tr><td>Claude Code</td><td>API key and executable path for the bundled <code>@anthropic-ai/claude-agent-sdk</code>, used by the <code>@ClaudeCode</code> agent.</td></tr>
    <tr><td>Codex</td><td>API key for OpenAI Codex.</td></tr>
    <tr><td>GitHub Copilot</td><td>Enable/disable, enterprise URL, and model overrides.</td></tr>
    <tr><td>Fabric (via Open AI Custom Models)</td><td>Fabric as the LLM provider: a custom model entry whose URL is the Fabric <code>/api/v1</code> base URL and whose API key is empty. The provider credentials sit in the Fabric <a href="/articles/24_non_DB_interfaces/15_LLM_interface.md">AI LLM interface</a>. See <a href="16_custom_llm_providers.md#fabric-as-a-provider">Custom LLM Providers</a>.</td></tr>
  </tbody>
</table>

**Model Selection** and **Language Model Aliases** also appear here, but both are thin pointers into the AI Configuration panel's **Agents** and **Model Aliases** tabs (see article 06) rather than separate editors. **Model Settings** does add two standalone knobs: `maxRetries` (retry attempts on a failed model call) and `requestSettings` (provider request tuning).

## Server-Side Compaction

When a conversation grows past a model's context limit, server-side compaction has the provider itself summarize older turns so the session keeps working, instead of the request failing outright. It is a genuine safeguard against the kind of hard failure some users have hit ("prompt is too long: N tokens > maximum"), but it only works when the model/provider supports it, and only if it is actually enabled for that session.

Support is currently limited to Anthropic (recent Opus/Sonnet models, via a Beta Messages API) and OpenAI official models using the Responses API (Chat Completions is not supported). Unsupported models and providers silently ignore the setting.

Activation is layered, each level overriding the one before it:

<table>
  <thead>
    <tr>
      <th>Level</th>
      <th>Setting</th>
      <th>Notes</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Global default</td>
      <td><code>ai-features.chat.serverSideCompaction</code> (checkbox, on by default) and <code>ai-features.chat.serverSideCompactionTokenThreshold</code> (minimum 50,000 tokens; unset means the provider's own default applies)</td>
      <td>Applies to all chat requests unless overridden</td>
    </tr>
    <tr>
      <td>Per-provider override</td>
      <td><code>ai-features.anthropic.serverSideCompaction</code> / <code>...TokenThreshold</code>, and <code>ai-features.openAiOfficial.serverSideCompaction</code> / <code>...TokenThreshold</code></td>
      <td>Each can be set to follow the global default, or forced on/off; overrides the global default for that provider only</td>
    </tr>
    <tr>
      <td>Per-session override</td>
      <td>Session Settings dialog, "Server-Side Compaction"</td>
      <td>Highest precedence; see <a href="03_using_the_ai_chat.md#session-settings">Using the AI Chat</a></td>
    </tr>
  </tbody>
</table>

When compaction fires, an inline marker appears in the chat and the token-usage tooltip shows a "compacted N×" count; this is persisted with the session and restored on reload. If you see a hard token-limit error despite compaction being enabled, check that the session's model is actually one of the supported ones (Anthropic Opus/Sonnet or an OpenAI Responses-API model); on any other model, compaction has no effect and the raw context limit still applies.

## Token Usage Visibility (Experimental)

Separately from compaction, three experimental settings control whether you get any visibility into approaching a token limit at all:

<table>
  <thead>
    <tr>
      <th>Setting</th>
      <th>What it does</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>ai-features.chat.tokenUsageIndicator.enabled</code></td>
      <td>Shows a running token-usage indicator in the chat view. Marked experimental; counts may be inaccurate depending on model/provider.</td>
    </tr>
    <tr>
      <td><code>ai-features.chat.tokenUsageWarning.enabled</code></td>
      <td>Shows a notification when a session's token usage crosses a configured threshold. Requires the model provider to report token usage.</td>
    </tr>
    <tr>
      <td><code>ai-features.chat.tokenUsageWarning.defaultThresholdPercentage</code></td>
      <td>The percentage of the model's context window at which the warning fires; also drives the indicator's warning/error color bands. At the time of writing this resolves against an assumed 200k-token context window rather than each model's real context size.</td>
    </tr>
  </tbody>
</table>

This is the closest equivalent Studio AI currently has to a "your session is getting long, consider starting fresh" warning. It is off by default in some deployments and marked experimental, so if you want a proactive nearing-the-limit warning rather than relying on compaction alone, check that both `tokenUsageIndicator.enabled` and `tokenUsageWarning.enabled` are turned on.

## Chat Behavior

<table>
  <thead>
    <tr>
      <th>Setting</th>
      <th>What it does</th>
    </tr>
  </thead>
  <tbody>
    <tr><td><code>defaultChatAgent</code></td><td>Which agent handles a message when none is explicitly addressed.</td></tr>
    <tr><td><code>defaultToolConfirmation</code></td><td>Default confirmation behavior for tools without a tool-specific entry (see below). Some tools always require confirmation regardless of this setting.</td></tr>
    <tr><td><code>toolConfirmation</code></td><td>Per-tool confirmation overrides (e.g. force a specific tool to always ask, or always allow).</td></tr>
    <tr><td><code>toolConfirmationTimeout</code></td><td>Seconds before a pending tool confirmation is auto-denied. <code>0</code> (default) disables the timeout. Also settable per session; see article 03.</td></tr>
    <tr><td><code>pinChatAgent</code></td><td>Keeps a mentioned agent active across prompts so you don't need to repeat <code>@Agent</code> every message.</td></tr>
    <tr><td><code>persistedSessionLimit</code></td><td>Maximum chat sessions to persist; <code>-1</code> unlimited, <code>0</code> disables persistence. Oldest sessions are pruned first.</td></tr>
    <tr><td><code>sessionStorageScope</code></td><td>Persist sessions per-workspace or in a single global store.</td></tr>
    <tr><td><code>welcomeScreenSessions</code></td><td>How many sessions to show on the chat welcome/home view before overflowing to "Browse all chats...".</td></tr>
    <tr><td><code>bypassModelRequirement</code></td><td>Skips the language-model-configured check, for external agents (e.g. <code>@ClaudeCode</code>) that don't need a Theia-managed model.</td></tr>
    <tr><td><code>allowedResourceUrls</code></td><td>Controls which external resources chat markdown is allowed to load (a security hardening setting).</td></tr>
  </tbody>
</table>

If tool confirmations are set to auto-allow (globally or by default), review that choice with your security team: it means agent tool calls, including shell commands and file writes, proceed without a per-action prompt. See [Security and Privacy](13_security_and_privacy.md).

## Agent Mode and Agent Settings

**Agent Mode: Enabled** (`ai-features.agentMode.enabled`) is the global default for whether `@Coder`'s Agent Mode is available without an extra first-use confirmation dialog. See [AI Code Editing and Changesets](04_ai_code_editing_and_changesets.md#edit-mode-vs-agent-mode).

**Agent Settings** is a pointer, not a separate editor: it directs you to the AI Configuration panel's Agents tab for enablement, LLM selection, prompt customization, and custom agent creation (articles 06 and 09).

## Code Completion

<table>
  <thead>
    <tr><th>Setting</th><th>What it does</th></tr>
  </thead>
  <tbody>
    <tr><td>Automatic Code Completion</td><td>Auto-triggers inline AI completions while editing (any Monaco editor). Otherwise, trigger manually via the "Trigger Inline Suggestion" command (Ctrl+Alt+Space).</td></tr>
    <tr><td>Cache Capacity</td><td>Max completions cached; minimum 10, recommended 50-200.</td></tr>
    <tr><td>Debounce Delay</td><td>Milliseconds to wait after an edit before triggering a completion; requires automatic completion to be on.</td></tr>
    <tr><td>Excluded File Extensions</td><td>File types where completions are disabled.</td></tr>
    <tr><td>Max Context Lines</td><td>Lines of surrounding code used as completion context.</td></tr>
    <tr><td>Strip Backticks</td><td>Whether to strip Markdown code fences from returned completions.</td></tr>
  </tbody>
</table>

## Prompt Templates and Skills

These settings back the file-location behavior described in [Customizing Agent Prompts](07_ai_configuration_and_prompts.md) and [Skills and Slash Commands](11_skills_and_slash_commands.md). Live-checked in this project:

<table>
  <thead>
    <tr><th>Setting</th><th>Confirmed live value / behavior</th></tr>
  </thead>
  <tbody>
    <tr><td><code>promptTemplatesFolder</code></td><td>Confirmed set to <code>&lt;workspace root&gt;/.prompts</code> in this project (falls back to the user config directory if not customized).</td></tr>
    <tr><td><code>taskContextStorageDirectory</code></td><td>Workspace-relative path for persisted task-context descriptions; confirmed as <code>.prompts/task-contexts</code> here. Empty means in-memory only.</td></tr>
    <tr><td><code>WorkspaceTemplateDirectories</code> / <code>WorkspaceTemplateFiles</code></td><td>Additional folders/files scanned for workspace-specific prompt templates, resolved by priority when IDs collide.</td></tr>
    <tr><td><code>TemplateExtensions</code></td><td>File extensions treated as prompt templates.</td></tr>
    <tr><td><code>skills.skillDirectories</code></td><td>Confirmed: <code>.prompts/skills</code> and <code>.agents/skills</code> in the workspace, plus <code>.agents/skills</code> in the user's home directory, and the product's own skills folder, are <strong>always</strong> included. This setting adds further directories on top. Live value in this project: <code>&lt;workspace root&gt;/.agents/skills</code>.</td></tr>
  </tbody>
</table>

This directly confirms the workspace-vs-global custom-agent/skill location question left open in article 09: skills (and, by the same convention, agents) resolve from both a workspace-level `.agents/` folder and a user-home-level `~/.agents/` folder, with workspace taking precedence.

## MCP, Notifications, Reasoning, Orchestrator

<table>
  <thead>
    <tr><th>Sub-section</th><th>What it does</th></tr>
  </thead>
  <tbody>
    <tr><td>MCP</td><td><code>mcpServers</code> and <code>useWorkspaceAsRoot</code>. See <a href="12_mcp_servers.md">MCP Servers</a> for the friendlier AI Configuration panel editor over the same data.</td></tr>
    <tr><td>Notifications</td><td><code>notifications.default</code>: the default way an agent gets your attention when it finishes or needs input (individual agents can override). See <a href="08_token_consumption_and_ai_history.md">Viewing Token Consumption and AI History</a>.</td></tr>
    <tr><td>Reasoning</td><td><code>reasoning.defaults</code>: default value for the chat input's reasoning-effort selector, for models that support it. See <a href="03_using_the_ai_chat.md">Using the AI Chat</a>.</td></tr>
    <tr><td>Orchestrator</td><td><code>orchestrator.excludedAgents</code>: agent IDs the built-in <code>@Orchestrator</code> agent is not allowed to delegate to. See <a href="14_other_studio_ai_agents.md">Other Studio AI Agents</a>.</td></tr>
  </tbody>
</table>

## Security-Relevant Settings

These directly affect what AI agents and tools can access or execute without your explicit per-action approval; see also [Security and Privacy](13_security_and_privacy.md).

<table>
  <thead>
    <tr><th>Setting</th><th>What it does</th></tr>
  </thead>
  <tbody>
    <tr><td><code>terminal.shellCommandAllowlist</code></td><td>Shell command patterns that are auto-allowed without confirmation (supports a trailing <code>*</code> wildcard). Commands containing <code>$</code> or backticks are never auto-allowed regardless.</td></tr>
    <tr><td><code>terminal.shellCommandDenylist</code></td><td>Shell command patterns always auto-rejected. Ships with default patterns covering dangerous commands (<code>eval</code>, <code>exec</code>, <code>sudo</code>, <code>rm -rf</code>, and similar).</td></tr>
    <tr><td><code>workspaceFunctions.allowedExternalPaths</code></td><td>Absolute paths or file URIs outside the workspace that AI tools may read. Empty (no access) by default; opt-in only.</td></tr>
    <tr><td><code>workspaceFunctions.considerGitIgnore</code></td><td>Whether AI file-search/read tools respect <code>.gitignore</code>.</td></tr>
    <tr><td><code>workspaceFunctions.fileContentMaxSizeKB</code> / <code>searchMaxResults</code> / <code>userExcludes</code></td><td>Caps on how much file content and how many search results AI tools can pull in per call, plus a user-defined exclude list.</td></tr>
    <tr><td><code>SCANOSS.apiKey</code> / <code>SCANOSS.mode</code></td><td>Optional integration that sends a hash of AI-suggested code snippets to the SCANOSS service (Software Transparency Foundation) to check for license/provenance issues.</td></tr>
    <tr><td><code>registry.githubToken</code></td><td>Optional GitHub personal access token used when downloading skills from GitHub, raising the rate limit from 60 to 5,000 requests/hour.</td></tr>
  </tbody>
</table>

> API keys and tokens entered in these fields are stored in clear text on the machine running Studio unless you use the corresponding environment variable instead (for example, `SCANOSS_API_KEY`). Prefer environment variables for anything sensitive.
