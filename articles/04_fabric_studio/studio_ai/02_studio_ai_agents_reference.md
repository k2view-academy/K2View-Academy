# Studio AI Agents

By default, Studio AI routes every message to @k2-assistant, which answers directly, loads the relevant skill, or delegates to a specialist agent automatically. Just open AI Chat and start asking - see [Getting Started with Studio AI](01_getting_started_with_studio_ai.md#just-start-asking).

This reference covers the small set of agents worth knowing by name. For the longer tail of specialized and legacy agents, see [Other Studio AI Agents](14_other_studio_ai_agents.md).

Studio AI is built from a set of specialized agents behind the scenes. To invoke one directly, prefix your message with `@AgentName` in the AI Chat input. After the first use in a session, the agent is pinned and you can continue chatting without the prefix.

## The Core Duo

### @k2-assistant

**Focus:** General-purpose entry point for Fabric project work - this is who you're already talking to by default.

@k2-assistant is the default agent shown on the AI Chat welcome screen ("Ask K2assistant"). It knows the project's available skills and puts the relevant one to work, creates and edits Fabric artifacts (Data Products/Logical Units, Broadway flows and actors, interfaces, web services, MTables, globals), consults specialist agents (@KB, @Data-Product-Explorer, @Interfaces) for knowledge, delegates larger or under-specified coding work to its worker sub-agent (@k2-worker), and can operate the Fabric runtime directly (commands, REST APIs, logs, deploy).

@k2-assistant routes work by scope: a small, fully-specified change (two or three files at most, every edit statable up front) it applies itself; anything larger, multi-step, or requiring exploration is handed off to @k2-worker, run in parallel when the work can be split.

You never need to type `@k2-assistant` explicitly - it's the default.

### @k2-worker

**Focus:** File editing and code writing - the execution arm behind @k2-assistant.

@k2-worker is the sub-agent @k2-assistant delegates hands-on file editing to, and where the code-writing capability formerly associated with @Coder now lives. Given one self-contained task, it loads the skill the task names (or the closest match from the skill catalog), creates or edits exactly the files in its assigned scope - Fabric artifacts (LU tables, Broadway flows and actors, interfaces, web services, MTables, globals) or project Java/generic code - verifies the result with diagnostics, and reports the changes back in a fixed format.

@k2-worker has no shell, runtime, user-interaction, or delegation tools of its own - anything beyond workspace file edits is reported back to @k2-assistant to handle. In practice, you will rarely address `@k2-worker` directly; it's documented here because understanding it explains how @k2-assistant actually gets implementation work done, and because it is the modern equivalent of what @Coder used to do.

## Specialist Agents

These three are still commonly worth addressing directly for read-only lookups, even though @k2-assistant can also reach them on your behalf.

### @Interfaces

**Focus:** Database interface lookup.

@Interfaces is aware of all Fabric DB interfaces and can answer questions about any DB interface and its schema and table metadata.

**Example prompts:**
- `@Interfaces Show me the schema for the Oracle CRM interface`
- `@Interfaces What tables does the aifusion interface expose?`

### @Data-Product-Explorer

**Focus:** Read-only Data Product (Logical Unit) schema and instance lookup.

@Data-Product-Explorer is a read-only expert on Fabric Data Products (Logical Units / LUs). It explains a Data Product's schema - tables, columns, relationships - by name or from the currently open schema file, and fetches instance data by IID as well as common/reference table data. For design and editing work (adding tables, populations, enrichment functions), ask @k2-assistant instead.

**Example prompts:**
- `@Data-Product-Explorer What is the schema of the customer_bank Data Product?`
- `@Data-Product-Explorer Show me the instance data for IID 100234 in the Employee LU`

### @KB

**Focus:** K2View knowledge base.

@KB answers knowledge-base questions about K2View Fabric and Fabric Studio - it draws on the fabric issues/Q&A knowledge base and Fabric's help topics, so it can answer questions about most fabric commands and modules: Data Product/LU schema, LU tables and views, common (reference) tables, Broadway flows and actors, Graphit, interfaces, environments, Query Builder, web services and user Java functions, globals, instance groups, IID finder, MTable, reports, security profiles, web apps, templates, deploy, LU instances, sync methods/modes, micro DB (MDB), TDM, and more.

**Example prompts:**
- `@KB How do I configure an MTable in Fabric?`
- `@KB What is the difference between a Reference table and a Shared Object?`

## Switching Between Agents

You can switch agents at any point in a conversation by typing `@AgentName` again. The new agent becomes pinned for subsequent messages.
