# TDM Agent – AI-Assisted Task Search and Execution

## Overview

The **TDM Agent** is an AI-assisted task search capability available in the **TDM App**. It makes it easier to find and prepare TDM tasks using natural language.

Instead of browsing or filtering through the task list and then manually provision them, testers can simply describe what they want to do. The TDM Agent identifies the most appropriate task, opens it, and **pre-fills its editable execution attributes based on the user's request**. When needed and allowed, it also adds new attributes. The tester can then review the suggested values, make any necessary changes, and execute the task.

This makes task execution faster and easier, particularly for users who may not be familiar with the available tasks or their configuration options.

The TDM Agent is available **in addition to** the existing search option in the **Manage Your Tasks** window. It does not replace the existing search functionality.

## Installation

To enable the TDM Agent, complete the following steps:

1. **Install the TDM Agent extension.**

2. **Install an LLM extension** (e.g. Anthropic)

3. **Define an LLM interface** upon the installed LLM extension.

4. **Edit the `apps.json` file.** In the **TDM** application entry, set the **`showChat`** attribute to `true`. This attribute is set to `false` by default.

5. **Redeploy the TDM LU.**

> **Important:** The TDM deployment is required to run the **`initRefreshTaskDesc`** Broadway job, which set during the TDM deploy. This job scans the TDM tasks and updates their descriptions in a dedicated table, which the TDM Agent uses to match tasks to user requests.



## How It Works

1. The user describes what they need in plain language. The TDM Agent can ask for clarification or additional details when necessary.
2. The TDM Agent searches for the most appropriate task and proposes it to the user. The suggestion logic based also on candidates availability at source.
3. In some cases, the agent will propose few candidate tasks, usually when there is no clear match or when more than a single task is requires to fulfill tester request.
4. The TDM Agent makes the required changes and overrides like pre-filling the editable execution attributes or adding new ones, based on the user's request.
5. The user can click **Execute Task** to run the task as-is, or manually edit the pre-filled execution attributes before executing.



## Scope and Limitations

The TDM Agent currently searches **Business Entity (BE)-based tasks only**. It does not search table-level tasks.

The TDM Agent currently supports the following task actions:

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

The TDM Agent does not currently support searching for Synthetic Generation tasks. You can search for them using the Search window.