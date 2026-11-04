# TDM Agent – AI-Assisted Task Search and Execution

## Overview

The **TDM Agent** is an AI-assisted task search capability available in the **TDM App**. It makes it easier to find and prepare TDM tasks using natural language.

Instead of browsing or filtering through the task list and then manually provisioning the data, testers can simply describe what they want to do. The TDM Agent identifies the most appropriate task, opens it, and **pre-fills its editable execution attributes based on the user's request**. When needed and allowed, it also adds new attributes. The tester can then review the suggested values, make any necessary changes, and execute the task.

This makes task execution faster and easier, particularly for users who may not be familiar with the available tasks or their configuration options.

The TDM Agent is available **in addition to** the existing search option in the **Manage Your Tasks** window. It does not replace the existing search functionality.

The TDM Agent can be used through the following channels:

<table>
  <thead>
    <tr>
      <th>Channel</th>
      <th>Used by</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Chat in the TDM App</strong></td>
      <td>Testers and developers</td>
      <td>Describe a data need in the chat, for example "I need 6 VIP customers with an address in New York City". See <a href="06_tdm_agent_ui.md">Using the TDM Agent in the TDM App</a>.</td>
    </tr>
    <tr>
      <td><strong>REST API</strong></td>
      <td>CI/CD pipelines and test-management tools</td>
      <td>Submit a user story with its test cases and get back a prepared execution per test case. Available starting from TDM 10.1. See <a href="03_tdm_agent_api.md">Invoking the TDM Agent via API</a>.</td>
    </tr>
    <tr>
      <td><strong>MCP tools</strong></td>
      <td>MCP clients, such as AI assistants and IDEs</td>
      <td>TDM lookups (Business Entities, environments, parameters, tasks and execution status), and tools to save an execution draft and start a task execution. See <a href="04_tdm_agent_mcp_tools.md">TDM MCP Tools</a>.</td>
    </tr>
  </tbody>
</table>

For how the TDM Agent is built, see [TDM Agent Architecture](02_tdm_agent_architecture.md). For the data it sends to the LLM and its security controls, see [TDM Agent Security and Privacy](05_tdm_agent_security_and_privacy.md).

## Prerequisite
Install Fabric 8.5.x or later.

## Installation

To enable the TDM Agent, complete the following steps:

1. **Install the TDM Agent extension.** Verify that the **aifusion** LU (Data Product) is added to the project.
2. **Install an LLM extension** (e.g. Anthropic).
3. **Define an LLM interface** on the installed LLM extension.
4. **Edit the `apps.json` file.** In the **TDM** application entry, set the `showChat` attribute to `true`. This attribute is set to `false` by default.
5. **Redeploy the TDM LU.**

> **Important notes:**
>
> * The TDM deployment is required to run the `initRefreshTaskDesc` Broadway job, which is configured during the TDM deployment. This job uses the LLM to scan the TDM tasks and update their descriptions in a dedicated table. The TDM Agent uses this information to match tasks to user requests. Deleted tasks (tasks with an **Inactive** status) are ignored by this job.
> * If you upgrade your TDM project from a version earlier than 10.0.1, verify that the `java.project.referencedLibraries` attribute in the workspace `settings.json` file contains the following JAR path: `/opt/apps/fabric/workspace/fabric/lib/fabric/fabric-task*.jar`.
> * The TDM Agent writes its log messages at the **DEBUG** level. To view the TDM Agent messages in the Fabric log, set the log level to DEBUG.


## How It Works

1. The user describes what they need in plain language. The TDM Agent can ask for clarification or additional details when necessary.
2. The TDM Agent searches for the most appropriate task and proposes it to the user. The suggestion logic is also based on the availability of candidate entities in the source environment.
3. In some cases, the agent proposes a few candidate tasks, usually when there is no clear match or when more than a single task is required to fulfill the tester's request. The agent proposes only tasks that match the user's request, without additional parameters that the user did not ask for. Tasks that can only be executed as configured, without modifications (for example, tasks whose execution parameters are all locked), can also be returned by the agent.
4. The TDM Agent makes the required changes and overrides, such as pre-filling the editable execution attributes or adding new ones, based on the user's request.
5. The user can click **Execute Task** to run the task as-is, or manually edit the pre-filled execution attributes before executing. Execution attributes added by the agent can be changed or deleted by the user. The user can also ask the agent to fix the task execution parameters.
6. Starting from TDM 10.1, the user can **save the execution parameters as a draft** and return to it later. This option is available when the task execution window is opened by the TDM Agent.

### Permissions

The TDM Agent evaluates permissions based on the identity and Fabric roles of the user who activates the agent. The agent proposes and prepares only tasks that the user is permitted to execute, and the execution is validated against the user's TDM permissions.


## Scope and Limitations

- The TDM Agent currently searches **Business Entity (BE)-based tasks only**. It does not search table-level tasks.

- The TDM Agent currently supports the following task actions:

<table>
  <thead>
    <tr>
      <th>Task Action</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Extract</strong></td>
      <td>Read entities from a source environment into Fabric</td>
    </tr>
    <tr>
      <td><strong>Load</strong></td>
      <td>Provision pre-extracted entities to a target environment</td>
    </tr>
    <tr>
      <td><strong>Extract &amp; Load</strong></td>
      <td>Copy entities from a source to a target environment</td>
    </tr>
    <tr>
      <td><strong>Reserve</strong></td>
      <td>Lock entities in a target environment</td>
    </tr>
    <tr>
      <td><strong>Delete</strong></td>
      <td>Remove entities from a target environment</td>
    </tr>
  </tbody>
</table>

- The TDM Agent does not currently support searching for **Synthetic Generation**, **Entity Clone**, or **Entities & Referential Data** tasks. These tasks can be searched for using the **Search** window.
- **Chat History** — The TDM Agent does not currently support viewing or reopening previous conversations. Users must start a new chat each time they access the agent. Execution drafts saved from the task execution window can be reopened later.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_agent_architecture.md)
