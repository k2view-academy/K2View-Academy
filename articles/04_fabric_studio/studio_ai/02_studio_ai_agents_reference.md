# Studio AI Agents Reference

Studio AI provides a set of specialized agents, each focused on a specific area of K2View project implementation. To invoke an agent, prefix your message with `@AgentName` in the AI Chat input. After the first use in a session, the agent is pinned and you can continue chatting without the prefix.

## K2View Implementation Agents

### @Architect

**Focus:** Planning and project structure analysis.

@Architect is the recommended starting point for any complex or multi-step development task. When you describe a goal, it generates a **structured implementation plan** — a document listing steps, file changes, and verification criteria — and presents it with an **"Execute with Coder"** button. You can refine the plan through follow-up messages before handing it off; the agent keeps the whole document consistent as you iterate.

The plan is saved as a file in your workspace, so it persists across sessions. If the chat is closed, you can reopen the file and use **F1 > Execute Plan with Coder** to start a fresh implementation session from it.

**Example prompts:**
- `@Architect Plan a new enrichment that pulls customer risk scores from the Oracle CRM interface`
- `@Architect Analyze the dependencies between the customer_bank and aifusion LUs`
- `@Architect What shared objects does this project expose to Web Services?`

For a full walkthrough of the plan-first workflow, see [Plan-First Development with the Architect Agent](05_plan_first_development.md).

### @Coder

**Focus:** Code writing and editing.

@Coder is the primary agent for generating, modifying, and fixing code. It can browse your workspace, read file content, propose changes as reviewable diffs, and apply them directly. It supports both guided (Edit Mode) and autonomous (Agent Mode) operation.

In **Agent Mode**, @Coder works independently: it reads files, writes changes, runs tests, starts or stops a development server if needed, and iterates until the task is complete. A visual task list in the chat tracks its progress through multi-step work.

**Example prompts:**
- `@Coder Write a Java enrichment function that joins customer data with the Orders table`
- `@Coder Fix all issues in #_f`
- `@Coder Refactor this function to use try-with-resources: #selectedText`

For details on how code changes are proposed and applied, see [AI Code Editing: Reviewing and Applying Changes](04_ai_code_editing_and_changesets.md).

### @Code Reviewer

**Focus:** Analyzing code changes

@Code Reviewer is a code review assistant that analyzes code changes and returns structured verdicts. Checks completion criteria, build/lint/test evidence, and code quality. this is a read only agent.

### @Universal

**Focus:** General programming questions.

Use @Universal for language questions, patterns, algorithms, and conceptual topics that are not specific to a particular K2View entity. It does not modify files — it answers and explains.

**Example prompts:**

- `@Universal What is the difference between a Fabric Decision function and a Trigger function?`
- `@Universal Explain the Builder pattern and when to use it in Java`

---

### @GitHub

**Focus:** Repository management.

@GitHub integrates with your Git repository. It can read issues, inspect the current diff, help draft commit messages, and perform other repository-related tasks.

**Example prompts:**
- `@GitHub Summarize the changes in my current working branch`
- `@GitHub Create a pull request description for my latest commits`

---

### @Studio-Commands

**Focus:** k2Studio operations.

Use @Studio-Commands to trigger Studio actions through natural language — deploying LUs, running Fabric commands, and other IDE-level operations.

**Example prompts:**
- `@Studio-Commands Deploy all updated Logical Units`
- `@Studio-Commands Open the Fabric terminal`

### @Broadway-Explain

**Focus:** Broadway flow explanations.

@Broadway-Explain reads and describes Broadway flows in plain language. Use it to understand an unfamiliar flow, or to generate documentation for an existing one.

**Example prompts:**

- `@Broadway-Explain Explain what this flow does step by step: #_f`
- `@Broadway-Explain What actors are used in the enrichment flows of the customer_bank LU?`

### @Broadway-Edit

**Focus:** Broadway flow editing.

@Broadway-Edit can propose modifications to Broadway flows: adding actors, error handling, conditions, and more.

**Example prompts:**
- `@Broadway-Edit Add an error handler to this flow: #_f`
- `@Broadway-Edit Insert a DbLoad actor after the existing transformation step`

### @Graphit

**Focus:** Graphit web services.

Use @Graphit for help designing, generating, and reviewing Graphit-based web services.

**Example prompts:**

- `@Graphit Generate a Graphit service that returns customer policy data with nested claims`
- `@Graphit Review my Graphit file for missing field mappings: #_f`

### @Interfaces

**Focus:** Database interface help.

@Interfaces helps with DB interface design, schema exploration, and SQL generation in the context of your Fabric project's configured data sources.

**Example prompts:**
- `@Interfaces Show me the schema for the Oracle CRM interface`
- `@Interfaces Generate a SELECT query that joins customer and address tables from the CRM source`

### @LU

**Focus:** Logical Unit management.

@LU assists with LU design: tables, populations, enrichment functions, globals, and the overall structure of a Logical Unit.

**Example prompts:**
- `@LU Add a new table to the customer_bank LU that stores transaction history`
- `@LU What populations are defined in the aifusion LU?`

### @KB

**Focus:** K2View knowledge base.

Use @KB to ask product questions — how features work, recommended patterns, and configuration guidance — drawing on K2View's documentation.

**Example prompts:**
- `@KB How do I configure an MTable in Fabric?`
- `@KB What is the difference between a Reference table and a Shared Object?`

---

### @ClaudeCode

**Focus:** Advanced coding assistance.

@ClaudeCode provides deep, sophisticated coding help for complex scenarios, large refactors, and nuanced implementation challenges. Unlike @Coder, it handles long-running autonomous sessions particularly well and can ask clarifying questions mid-task when it needs more information before proceeding.

**Example prompts:**
- `@ClaudeCode Refactor this complex population logic to be more readable and efficient: #_f`
- `@ClaudeCode Review this Java class for potential thread-safety issues: #file:src/MyService.java`

---

## Switching Between Agents

You can switch agents at any point in a conversation by typing `@AgentName` again. The new agent becomes pinned for subsequent messages.

