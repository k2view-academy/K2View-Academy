# Getting Started with Studio AI

Studio AI is built into the Web Studio and provides a set of AI-powered agents that can help you design Data Products, write and fix code, explain and edit Broadway flows, query interfaces, and more, all within the context of your open project.

Beyond this, Studio AI also provides infrastructure for managing and improving the AI elements that help you design and build your project: you can view and edit existing agents, create new ones, and manage the skills and tools they use, including built-in tools and MCP tools, which you can provision dynamically.

Studio AI also provides visibility into your AI activity through three dedicated views, aimed both for usability and auditing:

- Chat History - your past conversations (prompts and AI responses), which you can browse and reload into the chat panel.
- AI Agent History - the full content sent to and received from the LLM provider, filterable by agent.
- Token Usage - a breakdown of token consumption, divided into input, output, and cached tokens.



## Opening and Using the AI Chat

The AI Chat panel is docked on the right side of the Studio. To open it:

- Click the **AI Chat** icon in the far-right panel bar, or
- Use the menu: **View > AI Chat**, or
- Press **Ctrl+Alt+I**.

### The Welcome Screen

When you open AI Chat, what you see depends on your setup state:

- **AI disabled / no LLM provisioned** - The panel shows a setup guide prompting you to enable AI features and configure an LLM provider.

  Follow [Enabling Studio AI](#enabling-studio-ai) and [Choose and Provision LLM](#choose-and-provision-llm) below.
- **Ready to use** - The welcome screen reads **"Ask K2assistant"**, with a short hint on using `@agent` to call a specific agent and `#` (or the paperclip icon) to attach context. If you have previous sessions, a **Restored** list of your most recent chats appears below the hint for quick resumption. The chat input is at the bottom, with a compact toolbar at the top of the panel.

  *(Screenshot pending refresh for the current UI.)*



## Setup and Onboarding

The setup is built from 3 steps:

1. Enable AI Features in Settings.
2. Provision an LLM provider - either a provider API key, or Fabric itself (see [Using Fabric as the LLM Provider](#using-fabric-as-the-llm-provider)).
3. Install the **Studio AI Core Artifacts** extension.

### Enabling Studio AI

If AI features are disabled, you need to enable them and connect at least one LLM provider before the chat becomes active.

![](images/01_ai_chat_first_launch.png)

Enabling Studio AI can be done either by

1. Clicking the *settings menu* button on the AI panel's welcome message
2. Using Studio Settings:
   * Open **Settings** via the gear icon at the bottom-left of the Studio, or press **Ctrl+,**.
   * In the left navigation, expand **AI Features** and click **AI Enablement**.

Then: Check the **Enable AI** checkbox.

### Choose and Provision LLM

Once AI is enabled, you need to enter an API key for at least one LLM provider.

![](images/02_settings_enable_ai.png)

The left navigation lists all supported providers: Anthropic, OpenAI (Official and Custom Models), Google, Codex, Hugging Face, Ollama, Llamafile, and more. The Settings left navigation also exposes a much larger set of AI Feature sub-sections beyond enablement and provisioning: chat behavior, server-side compaction, token usage warnings, security-relevant defaults, and more. See [AI Features Settings](15_ai_features_settings.md) for the full map.

When provisioned, the AI Chat panel will refresh and show the chat interface.

### Using Fabric as the LLM Provider

From V8.5.1, Fabric can act as the LLM provider itself. Fabric exposes an OpenAI-compatible endpoint backed by the project's [AI LLM interface](/articles/24_non_DB_interfaces/15_LLM_interface.md), so the provider credentials are held in Fabric rather than by each developer. Users do not enter an API key, and the organization controls which models are reachable and who may use them.

Fabric is connected as a custom OpenAI provider - see [Fabric as a Provider](16_custom_llm_providers.md#fabric-as-a-provider) for the settings.

### Install the Studio AI Core Artifacts Extension

Studio AI's built-in agents, skills, and prompt templates - including **@k2-assistant**, the default agent and its companion **k2-worker** - are shipped by the **Studio AI Core Artifacts** extension on K2 Exchange. Without it installed and imported, the AI Chat panel has little to work with.

To install it:

1. Open the **Extensions** view (left activity bar) and search for **Studio AI Core Artifacts** under **K2 Exchange**.
2. Click **Install**.
3. This creates a `.agents/` folder in your workspace, with agents under `.agents/agents/` and skills under `.agents/skills/` (each skill is a self-contained folder with a `SKILL.md` entry point).

> The extension provides an **Init Claude Skills** command, which imports the same skills for use with Claude Code instead of (or alongside) the Studio AI.

Once imported, the agents and skills become available immediately - no restart required. See [Studio AI Agents Reference](02_studio_ai_agents_reference.md) for the agent roster and [Skills and Slash Commands](11_skills_and_slash_commands.md) for how skills work.



## Just Start Asking

You do not need to know which agent to use. By default, your message goes to **@k2-assistant**, the Fabric project assistant shown on the AI Chat welcome screen ("Ask K2assistant"). It figures out what you need and handles it: answering directly, loading a relevant skill, or delegating to a specialized agent behind the scenes - all without you having to pick anything.

Just type your question or task and press **Enter**. This is the recommended way to work with Studio AI for the vast majority of tasks.



## Addressing a Specific Agent (Optional)

Studio AI is built from a set of specialized agents behind the scenes, and you can address one directly by starting your message with `@` followed by its name - for example, `@Coder` or `@Architect`. This is useful if you already know exactly which specialist you want, or want to bypass automatic routing.

Once you address an agent, it becomes **pinned** for the rest of the session - you do not need to keep prefixing messages with `@AgentName`. Type a new `@AgentName` at any time to switch.

Direct agent addressing is an advanced/optional feature. If you want to explore it, see the [Studio AI Agents Reference](02_studio_ai_agents_reference.md).



## Attaching Context to Your Questions

You can attach files and selections to any message to help the agent give more accurate, targeted answers. The most common context shortcuts are:

- `#_f` - attaches the currently open file.
- `#selectedText` - attaches the text currently highlighted in the editor.
- `#file:path/to/file` - attaches a specific file by path.

For a complete guide to context, including drag-and-drop and the paperclip button, see [Using the AI Chat](03_using_the_ai_chat.md).



## Next Recommended Steps

- Understand all chat features: [Using the AI Chat](03_using_the_ai_chat.md)
- Learn how code changes are proposed and applied: [AI Code Editing: Reviewing and Applying Changes](04_ai_code_editing_and_changesets.md)
- Optional/advanced - address specific agents directly: [Studio AI Agents Reference](02_studio_ai_agents_reference.md)
