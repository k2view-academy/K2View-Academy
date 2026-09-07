# Other Studio AI Agents

> This article covers the longer tail of Studio AI agents beyond the core set in [Studio AI Agents Reference](02_studio_ai_agents_reference.md) (@k2-assistant, @k2-worker, @Interfaces, @Data-Product-Explorer, @KB). Some of these agents are being superseded by @k2-worker's expanded capabilities or folded into skills over time. Check the [AI Configuration](06_ai_configuration_and_settings.md) Agents tab for each agent's current **Enabled** / **Show in Chat** status before relying on one.

## Still Commonly Used

### @Architect

**Focus:** Read-only project orientation and Q&A.

@Architect is a read-only assistant that can access your workspace, list files and folders, and retrieve their content, but it cannot modify files. Use it to ask about the current project, project files, and source code: how to build the project, where to put source code, where to find specific code or configurations, and similar orientation questions.

**Example prompts:**
- `@Architect Where should a new enrichment function for the customer_bank LU go?`
- `@Architect How is this project structured?`

> Earlier versions of Studio AI had @Architect generate structured implementation plans with an "Execute with Coder" button, described in [Plan-First Development with the Architect Agent](05_plan_first_development.md). That workflow does not match @Architect's current description and should be treated as deprecated/unconfirmed rather than relied upon.

### @GitHub

**Focus:** Repository management.

@GitHub integrates with your Git repository. It can read issues, inspect the current diff, help draft commit messages, and perform other repository-related tasks.

**Example prompts:**
- `@GitHub Summarize the changes in my current working branch`
- `@GitHub Create a pull request description for my latest commits`

### @ClaudeCode

**Focus:** Advanced coding assistance.

@ClaudeCode provides deep, sophisticated coding help for complex scenarios, large refactors, and nuanced implementation challenges. It handles long-running autonomous sessions particularly well and can ask clarifying questions mid-task when it needs more information before proceeding.

**Example prompts:**
- `@ClaudeCode Refactor this complex population logic to be more readable and efficient: #_f`
- `@ClaudeCode Review this Java class for potential thread-safety issues: #file:src/MyService.java`

## Coder: Superseded by @k2-worker

### @Coder

**Focus:** Code writing and editing. *(Its capabilities now live in @k2-worker.)*

@Coder is the original agent for generating, modifying, and fixing code, with reviewable diffs in Edit Mode and autonomous multi-step work in Agent Mode. It is still valid and enabled, but @k2-assistant now delegates the same kind of file-editing work to @k2-worker by default, so there is usually no need to address @Coder directly anymore. See [AI Code Editing: Reviewing and Applying Changes](04_ai_code_editing_and_changesets.md) for how the review/diff workflow works either way.

**Example prompts:**
- `@Coder Write a Java enrichment function that joins customer data with the Orders table`
- `@Coder Fix all issues in #_f`

## Other K2View Implementation Agents

### @Code Reviewer

**Focus:** Analyzing code changes.

@Code Reviewer is a read-only code review assistant that analyzes code changes and returns structured verdicts, checking completion criteria, build/lint/test evidence, and code quality.

### @Universal

**Focus:** General programming questions.

Use @Universal for language questions, patterns, algorithms, and conceptual topics that are not specific to a particular K2View entity. It does not modify files; it answers and explains.

**Example prompts:**
- `@Universal What is the difference between a Fabric Decision function and a Trigger function?`

### @Studio-Commands

**Focus:** k2Studio operations.

Use @Studio-Commands to trigger Studio actions through natural language: deploying LUs, running Fabric commands, and other IDE-level operations.

**Example prompts:**
- `@Studio-Commands Deploy all updated Logical Units`

### @Broadway-Explain

**Focus:** Broadway flow explanations.

@Broadway-Explain reads and describes Broadway flows in plain language. Use it to understand an unfamiliar flow, or to generate documentation for an existing one.

**Example prompts:**
- `@Broadway-Explain Explain what this flow does step by step: #_f`

### @Broadway-Edit

**Focus:** Broadway flow editing.

@Broadway-Edit can propose modifications to Broadway flows: adding actors, error handling, conditions, and more.

**Example prompts:**
- `@Broadway-Edit Add an error handler to this flow: #_f`

### @Graphit

**Focus:** Graphit web services.

Use @Graphit for help designing, generating, and reviewing Graphit-based web services.

**Example prompts:**
- `@Graphit Generate a Graphit service that returns customer policy data with nested claims`

## Meta Agents

These agents don't work on your project directly; they help you build and manage the other agents, skills, and requests around them.

### @Agent-Builder

**Focus:** Scaffolding new Fabric agents.

@Agent-Builder builds a new Fabric agent under a Data Product/Logical Unit. It understands the Data Product's schema, splits it into business domains, scaffolds the agent, and fills in its `agent.yaml`, sub-agents, skills, references, and evals. It delegates to @Data-Product-Explorer, @Interfaces, and @KB for schema and documentation context.

**Example prompts:**
- `@Agent-Builder Create a new agent for the customer_bank Data Product`

### @CreateSkill

**Focus:** Authoring new skills. *(Alpha)*

@CreateSkill helps you create well-structured skills (reusable instructions and domain knowledge for AI agents) in either the `.agents/skills` or `.prompts/skills` directory, with proper YAML frontmatter and markdown content. See [Skills and Slash Commands](11_skills_and_slash_commands.md) for the skill file format.

### @Explore

**Focus:** Codebase exploration.

@Explore is a codebase exploration assistant that extracts and distills information from the codebase. It reports facts about what exists, provides code excerpts, and describes observed patterns. It does not modify files.

### @Orchestrator

**Focus:** Automatic agent routing.

@Orchestrator analyzes your request against the descriptions of all available chat agents and selects the best-fitting agent to handle it, using AI, then delegates directly without further confirmation.

## Background and Delegate Agents

A number of additional agents exist in the [AI Configuration](06_ai_configuration_and_settings.md) Agents tab but are not intended to be addressed directly with `@`. They support other agents or Studio features in the background:

- **AppTester**: runs end-to-end UI verification; see [Agent Capabilities](10_agent_capabilities.md).
- **PR Reviewer**, **ProjectInfo**, **Codex**, **Terminal Assistant**: specialized delegate agents used by other agents and Studio features.
- **Chat Session Naming**, **Chat Session Summary**, **Code Completion**: internal agents that name sessions, generate summaries, and power inline code completion.
