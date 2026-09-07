# Skills and Slash Commands

Skills are reusable, structured instructions and domain knowledge that agents can draw on. They are named, and each skill's name doubles as a **slash command** you can type in the AI Chat input to invoke it directly.

> This article reflects the current `SKILL.md`-based format, shipped via the **Studio AI Core Artifacts** extension. It replaces an earlier `.prompttemplate`-based format described in older versions of this article; if you have old-format skill files, they will not appear in the current Skills list.

## What Is a Skill?

A skill is a self-contained folder with a `SKILL.md` entry point, plus any supporting reference files (and, in some cases, evaluation examples). `SKILL.md` starts with a YAML frontmatter block and a markdown body:

```
---
name: my-skill
description: One or two sentences describing what this skill does and when to use it.
---

Detailed instructions for the task, written in markdown.
```

The skill's `name` is also its slash command trigger; the skill above would be invoked with `/my-skill`. Studio AI can also load a relevant skill automatically based on your request, without you typing the slash command yourself, when the addressed agent (such as `@k2-assistant`) is configured to do so.

## Using Slash Commands in the Chat

Type `/` in the chat input to see a list of available skills as slash commands. Select one, or type the full name and press **Enter**.

```
/fabric-commands
```

## Skills Tab (AI Configuration)

Every installed skill is listed in the **Skills** tab of [AI Configuration](06_ai_configuration_and_settings.md#skills-tab): its name, description, and the file path to its `SKILL.md`. Click **Open** next to a skill to view its file directly.

## Where Skills Are Stored

Skills live under `.agents/skills/<skill-name>/SKILL.md` in your project (imported by the Studio AI Core Artifacts extension; see [Getting Started with Studio AI](01_getting_started_with_studio_ai.md#install-the-studio-ai-core-artifacts-extension)). The `@CreateSkill` agent also supports an alternate `.prompts/skills/` location. Check the **Location** column in the Skills tab for the exact path of any given skill rather than assuming one location.

## Skills Available in This Project

At the time of writing, this project's Skills tab listed the following (yours may differ):

<table>
  <thead>
    <tr>
      <th>Skill</th>
      <th>Slash Command</th>
    </tr>
  </thead>
  <tbody>
    <tr><td>broadway-actor-builder</td><td><code>/broadway-actor-builder</code></td></tr>
    <tr><td>broadway-flow-builder</td><td><code>/broadway-flow-builder</code></td></tr>
    <tr><td>data-product-builder</td><td><code>/data-product-builder</code></td></tr>
    <tr><td>data-product-cowork</td><td><code>/data-product-cowork</code></td></tr>
    <tr><td>debug-broadway-flow</td><td><code>/debug-broadway-flow</code></td></tr>
    <tr><td>fabric-commands</td><td><code>/fabric-commands</code></td></tr>
    <tr><td>fabric-java-docs</td><td><code>/fabric-java-docs</code></td></tr>
    <tr><td>fabric-overview</td><td><code>/fabric-overview</code></td></tr>
    <tr><td>fabric-project-helper</td><td><code>/fabric-project-helper</code></td></tr>
    <tr><td>fabric-troubleshooting</td><td><code>/fabric-troubleshooting</code></td></tr>
    <tr><td>interface-builder</td><td><code>/interface-builder</code></td></tr>
    <tr><td>report-builder</td><td><code>/report-builder</code></td></tr>
    <tr><td>web-service-builder</td><td><code>/web-service-builder</code></td></tr>
  </tbody>
</table>

> Earlier built-in commands such as `/remember`, `/with-apptester`, `/analyze-gh-ticket`, `/fix-gh-ticket`, and `/address-gh-review` were not found in the current slash-command list and appear to have been replaced by this skill-based system.

## Creating Custom Skills

To create a skill manually, add a folder under `.agents/skills/<skill-name>/` containing a `SKILL.md` file with the frontmatter and body format shown above. Reload the Studio window (**F1 > Developer: Reload Window**) after adding a new skill file to make it available.

Alternatively, address `@CreateSkill` and describe the skill you want; it will scaffold a well-structured `SKILL.md` for you. See [Studio AI Agents](02_studio_ai_agents_reference.md) and [Other Studio AI Agents](14_other_studio_ai_agents.md#createskill).

### Example: A Code Review Skill

```
---
name: k2review
description: Review the current file for K2View best practices and common issues, such as JDBC resource management, SQL injection risks, exception handling, and naming conventions.
---

Review the code in {{currentRelativeFilePath}} for the following issues:

1. JDBC resource management: ensure all connections, statements, and result sets are
   closed, preferably with try-with-resources.
2. SQL injection risks: verify that all SQL uses parameterized queries.
3. Exception handling: check that exceptions are caught at the appropriate level and
   logged correctly per our conventions.
4. Naming conventions: verify method and variable names follow our team standards.

For each issue found, explain the problem and provide a corrected version of the relevant code.
If no issues are found in a category, say so explicitly.
```

With this skill saved under `.agents/skills/k2review/SKILL.md`, typing `/k2review` in the chat triggers the full review prompt against the currently open file.
