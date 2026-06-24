# Agent Capabilities

Agent capabilities are optional extensions that expand what an agent can do during a conversation. They are designed to be opt-in: by default, agents operate in a focused mode without extended permissions, and you activate capabilities only when a specific task calls for them.

## Capabilities Overview

A **capability** is a bundle of additional tools and permissions granted to an agent for the duration of a session. Capabilities allow agents to do things they cannot do by default, such as running shell commands, or interacting with GitHub.

This design keeps agents predictable and contained in everyday use, while still giving you access to more powerful behaviors when you need them.



## The Generic Capabilities Panel

In addition to the three @Coder chips, Studio AI has a **Generic Capabilities Panel** that provides access to the full set of capabilities registered in the system, including capabilities not surfaced as chips in the chat input.

Open the panel with **Ctrl+Shift+.** (or via the command palette: **F1 > Open Capabilities Panel**).

The panel displays a searchable tree of all available capabilities. Each capability shows its name, description, and current status. You can enable or disable capabilities from this panel for the current session.

This panel is the primary place to manage capabilities when:

- You want to see everything that is available, not just the three @Coder chips
- You need to enable a capability for an agent other than @Coder
- You want to disable a capability you previously enabled

## Capability Persistence

Capabilities are **per-session**. When you start a new chat session, all capabilities reset to their defaults (off). This ensures that permissions do not silently carry over from one task to the next.

Within a session, once a capability is enabled it stays on for all subsequent messages in that session, unless you explicitly disable it.
