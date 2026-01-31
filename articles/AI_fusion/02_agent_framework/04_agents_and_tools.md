# Agent Framework Components: Agents, Tools and Utilities

An AI agent is a module that receives data, context, and resources, then uses an AI model for reasoning, planning, and decision-making to achieve specific goals.



## Agent Types

The GenAI Data Fusion framework includes two categories of agents, both represented as Broadway actors and flows.

### Built-in Agents

#### Workflow Orchestrating Agents

| Agent            | Role                                                         |
| ---------------- | ------------------------------------------------------------ |
| **Orchestrator** | Manages the overall agentic flow and coordination            |
| **Reflector**    | Evaluates the user’s request and selects the appropriate response path |
| **Refiner**      | When sub-agent path is chosen, it refines the user’s request into a concise, actionable goal for a selected sub-agent |
| **Planner**      | Builds and executes multi-step task plans                    |

#### Data Agents

| Agent             | Role                                                         |
| ----------------- | ------------------------------------------------------------ |
| **Data Retrieve** | Handles queries against Fabric Data Products by generating SQL dynamically |



### Project Implementation Agents — Worker Sub-Agents

The implementation agents act as worker sub-agents in the agentic workflow, with the aim of accomplishing domain-specific goals. These are Broadway flows tagged to handle specific domains or request types.

Example: The `loans_subagent` tag identifies an agent specialized in banking loan inquiries.

Read more practice information [here](04a_agentic_flow_agents.md) about the agentic workflow's built-in agents and worker sub-agents.



## Data Retrieve Agent & Domain List

While invoking a request to an AI model for generating SQL, as it is done at the Data Retrieve agent, it is important to provide it the right context, including the right scope, that is - only the relevant tables that shall be involved. 

When an LU contains many tables, not all of which may be relevant for the current request, it is useful to separate them into groups - domains. 

A good practice for provisioning and maintaining the domain list is to create an MTable at the leading business entity LU, including the following recommended columns:

* Domain — the domain's name 
* Description
* Rules
* Tables
* Goal_Description

According to this list, during the agentic flow, you can use an MTable actor to lookup for a specific domain and its content, augmenting the AI context.



## Tools

Tools are Broadway flows designed to perform specific actions. They can be fully automated or LLM-assisted.

### When to Use Tools

While the generic Data Retrieve agent handles most queries using schema descriptions, tools become essential for:

| Scenario                         | Reason                                                       |
| -------------------------------- | ------------------------------------------------------------ |
| **Sophisticated queries**        | Complex business rules requiring predefined SQL statements crafted by data specialists |
| **Actions with business impact** | Controlled flows for operations involving monetization, compliance, or auditing requirements |
| **External system integration**  | Dedicated calls to services outside Fabric's responsibility  |

### Tool Identification

Tools are identified by:

- **Tags** — one or more tags for discoverability
- **Description** — a clear explanation of the tool's purpose
- **Parameter Remarks** — documentation on input/output parameters

This information is passed to the LLM to aid in tool selection during plan execution.

### Common Tools

| Tool                   | Purpose                                            |
| ---------------------- | -------------------------------------------------- |
| `queryExecute`         | Runs structured data queries                       |
| `searchKBDataRetrieve` | Retrieves knowledge base information               |
| `describeDomain`       | Provides data model information for SQL generation |

> **Note:** Tools typically do not involve AI unless an agent is tagged as a tool.



## Utility Actors

The AI Fusion platform uses several utility actors for working with AI, such as LLMConst, LLAppend and LLInvoke.

For more information, read [here](06_llm_calls_utility_actors.md).

