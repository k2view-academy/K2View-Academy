# Using the AI Chat

The AI Chat panel is the primary interface for Studio AI. This article covers all of its features: the toolbar, composing messages, attaching context, session settings, session history, and more.

## Opening AI Chat

Open the AI Chat panel via **View > AI Chat** or press **Ctrl+Alt+I**. It appears as a panel on the right side of the Studio.

## The AI Chat Toolbar

The toolbar at the top of the AI Chat panel is intentionally minimal. Most actions live under **More Actions...**.

<table>
  <thead>
    <tr>
      <th>Icon</th>
      <th>Action</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>History (clock)</td>
      <td><strong>Browse all chats...</strong> (<code>Ctrl+Alt+L</code>)</td>
      <td>Opens a searchable "Select chat" picker listing your persisted sessions. See <a href="#chat-session-history">Chat Session History</a> below.</td>
    </tr>
    <tr>
      <td>Window</td>
      <td><strong>Move View to Secondary Window</strong></td>
      <td>Pops the AI Chat panel out into its own browser window.</td>
    </tr>
    <tr>
      <td><strong>…</strong></td>
      <td><strong>More Actions...</strong></td>
      <td>Opens a menu with the four items below.</td>
    </tr>
  </tbody>
</table>

The **More Actions...** menu contains:

<table>
  <thead>
    <tr>
      <th>Item</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Open AI Agent History</strong></td>
      <td>Opens the AI Agent History panel in the bottom panel area (alongside Problems/Terminal). See <a href="08_token_consumption_and_ai_history.md">Viewing Token Consumption and AI History</a>.</td>
    </tr>
    <tr>
      <td><strong>Open AI Configuration</strong></td>
      <td>Opens the AI Configuration panel as a full editor tab. See <a href="06_ai_configuration_and_settings.md">AI Configuration: Managing Agents and Settings</a>.</td>
    </tr>
    <tr>
      <td><strong>Open AI Settings</strong></td>
      <td>Opens the Studio Settings dialog, scoped to AI Features. Equivalent to <a href="01_getting_started_with_studio_ai.md#enabling-studio-ai">Enabling Studio AI</a> via the gear icon.</td>
    </tr>
    <tr>
      <td><strong>Set Session Settings...</strong></td>
      <td>Opens per-session overrides. See <a href="#session-settings">Session Settings</a> below.</td>
    </tr>
  </tbody>
</table>

> Earlier versions of Studio AI exposed a dedicated **New Chat**, **Summarize Session**, **Auto-scroll toggle**, and **JSON View** as separate toolbar icons. These are no longer present as toolbar buttons in the current version. See [Starting a New Chat](#starting-a-new-chat) for how to start fresh, and [Session Settings](#session-settings) for what replaced the `{ }` icon.

## Starting a New Chat

There is no dedicated "New Chat" toolbar button anymore. To start a fresh conversation:

- Open the command palette (**F1**) and run **Chat: New Chat**, or
- Run **Chat: Home** (`Ctrl+Shift+L`) to return to the AI Chat welcome screen, from which typing a new message starts a new session.

## Session Settings

**Set Session Settings...** (in the **More Actions...** menu) opens a dialog with per-session overrides:

- **Confirmation Timeout**: when enabled, automatically denies tool confirmations after a specified number of seconds, overriding the global preference for this session only.
- **Server-Side Compaction**: overrides the global and per-provider compaction settings for this session (Compaction mode, Token threshold, and an Advanced Settings JSON block), for providers that support it. See [AI Features Settings](15_ai_features_settings.md#server-side-compaction) for how the feature works and where its global and per-provider defaults live.

These settings apply only to the current session and do not persist to new chats.

## Composing Messages

Type your question or instruction in the **"Ask a question"** input at the bottom of the panel. Press **Enter** to send, or press **↑** to recall a previous message from history.

To address a specific agent, start your message with `@AgentName`. Once an agent is addressed, it is **pinned** for the rest of the session, so subsequent messages automatically go to the same agent without the `@` prefix. To switch agents, type a new `@AgentName`. For most tasks you can skip this entirely, see [Just Start Asking](01_getting_started_with_studio_ai.md#just-start-asking).

### Chat Modes (Legacy Coder Agents)

The chat input area does not always show a mode selector. It appears only when the currently addressed agent supports it, such as `@Coder`, and offers **Edit Mode**, **Agent Mode**, and **Agent Mode (Next)**. With the default `@k2-assistant` flow there is no manual mode to pick; routing happens automatically. See [AI Code Editing: Reviewing and Applying Changes](04_ai_code_editing_and_changesets.md) for what each mode does.

### Chat Input Controls

Below the input field, the chat input area has:

<table>
  <thead>
    <tr>
      <th>Control</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Attach elements to context</strong></td>
      <td>Browse and attach files. This is the paperclip button described in <a href="#attaching-context">Attaching Context</a> below.</td>
    </tr>
    <tr>
      <td><strong>@Agent</strong></td>
      <td>Opens the agent picker, equivalent to typing <code>@</code>.</td>
    </tr>
    <tr>
      <td><strong>Toggle Capabilities Configuration</strong></td>
      <td>Opens the Generic Capabilities Panel. See <a href="10_agent_capabilities.md">Agent Capabilities</a>.</td>
    </tr>
    <tr>
      <td><strong>Reasoning effort selector</strong></td>
      <td>Dropdown (default <strong>Auto</strong>) with levels Off / Minimal / Low / Medium / High / Auto, controlling how much reasoning effort the model applies to your request. Persists as the session default until changed.</td>
    </tr>
    <tr>
      <td><strong>Model selector</strong></td>
      <td>Dropdown (default <strong>Default</strong>, mapped to the agent's configured model alias) letting you pick a specific model for the session, for example a Claude Opus, Sonnet, Haiku, or Fable variant, or a model from another connected provider. Once changed, the selection <strong>persists as the default for the rest of that chat session</strong> (it does not reset per message); switching to a new chat resets it back to <strong>Default</strong>. See <a href="06_ai_configuration_and_settings.md#model-aliases-tab">AI Configuration: Managing Agents and Settings</a> for how aliases map to models.</td>
    </tr>
  </tbody>
</table>


Above the input, agents that advertise optional capabilities (such as **Shell Execution** or **GitHub**) show toggle chips. See [Agent Capabilities](10_agent_capabilities.md).

## Attaching Context

The quality of AI responses depends heavily on the context you provide. You can attach relevant files and selections in several ways.

### Using Context Variables (Recommended)

Type `#` in the chat input to see a list of all available context variables. The most commonly used are:

<table>
  <thead>
    <tr>
      <th>Variable</th>
      <th>Shortcut</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>#file:path/to/file</code></td>
      <td><code>#filename</code></td>
      <td>Attaches a specific file. After typing <code>#file:</code>, autocomplete suggestions appear based on recently opened files.</td>
    </tr>
    <tr>
      <td><code>#currentRelativeFilePath</code></td>
      <td><code>#_f</code></td>
      <td>Attaches the file currently open in the editor</td>
    </tr>
    <tr>
      <td><code>#currentRelativeDirPath</code></td>
      <td>-</td>
      <td>Attaches the directory of the currently open file</td>
    </tr>
    <tr>
      <td><code>#selectedText</code></td>
      <td>-</td>
      <td>Attaches the text currently highlighted in the editor</td>
    </tr>
  </tbody>
</table>

Using context variables inside your prompt lets you also describe *why* a file is relevant. All files added via variables appear in the context overview below the chat input field.

### Drag and Drop / Browse-to-add

You can also drag files from the File Explorer directly into the chat input, or click the paperclip button at the bottom-left of the input area to browse and attach files. You can then type the file name among the project files.

This method does not let you annotate why the file is relevant, but is convenient for quick attachments.

### Image Support

You can attach images to your messages. Use the context variable `#imageContext` or drag an image file into the chat input. Images pasted inline are displayed as thumbnails at their insertion point; images attached via the paperclip button are grouped below the message text. This is useful for sharing diagrams, schema screenshots, or UI mockups with the agent.

## Agent Pinning

When you address an agent with `@AgentName`, it is pinned for the ongoing session. This means you only need to type the agent name once: all subsequent messages in that session go to the same agent automatically. To switch to a different agent, simply type `@NewAgentName`.

## Editing Sent Messages

You can edit a previously sent message in the chat. Click the edit icon next to any previous message, make your changes, and resend.

When you edit and resend a message this way, Studio AI **automatically branches the conversation** into a new session, preserving the original thread exactly as it was. This makes it easy to explore alternative approaches, for example trying a different prompt phrasing or changing a requirement, without losing your current work. The original session remains accessible in [Chat Session History](#chat-session-history).

## Starting Chat from the Editor

You can initiate a chat message directly from the editor. Right-click on selected code or a file and choose **Ask AI** (or similar) from the context menu. This pre-populates the chat input with the selected content as context, saving you the manual step of adding `#selectedText`.

## Chat Session History

Every conversation is automatically saved. Press `Ctrl+Alt+L` or click the history icon in the toolbar to open **Browse all chats...**, a searchable "Select chat" picker listing your sessions, each with its title, addressed agent, and last-active time. From here you can:

- Click a session to restore and resume it.
- Rename a session using the pencil icon next to it.
- Delete a session using the **×** icon next to it.

The AI Chat welcome screen also shows a **Restored** list of your most recent sessions directly, for quick resumption without opening the picker.

For more on AI history and token monitoring, see [Viewing Token Consumption and AI History](08_token_consumption_and_ai_history.md).

## Agent Completion Notifications

When an agent finishes a task, particularly when running in the background on a larger delegated task, a notification appears in the Studio. The notification includes the agent name, the session it completed, and a **"Show Chat"** button that takes you directly to that session to review the result.

You can configure whether each agent sends completion notifications from the [AI Configuration](06_ai_configuration_and_settings.md) view.

You can also control overall behavior via **Settings > Ai-features > Notifications: Default** (Default is *Off*)
