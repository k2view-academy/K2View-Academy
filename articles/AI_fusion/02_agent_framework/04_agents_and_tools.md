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
| **Refiner**      | When sub-agent path is chosen, Refines the user’s request into a concise, actionable goal for a selected sub-agent |
| **Planner**      | Builds and executes multi-step task plans                    |

#### Data Agents

| Agent             | Role                                                         |
| ----------------- | ------------------------------------------------------------ |
| **Data Retrieve** | Handles queries against Fabric Data Products using dynamic SQL generation |



### Project Implementation Agents - Worker Sub-Agents

Implementation agents - taking the role of the worker sub-agents at the agentic workflow - are aimed to accomplish domain-specific goals. These are Broadway flows tagged to handle specific domains or request types.

Example: A `loans_subagent` tag identifies an agent specialized for banking loan inquiries.



Read [here](04a_agentic_flow_agents.md) more practice information about the agentic workflow built-in agents as well as worker sub agents.



## Data Retrieve & Domain List

Domain list is useful when an LU contains many tables, which not all might be relevant for the current This attribute is used by the Reflector and is added to its context. 

A good practice for provisioning and maintaining it is to create an MTable at the leading business entity LU, with the following recommended columns:

* Domain - name of domain 
* Description
* Rules
* Tables
* Goal_Description





## Tools

Tools are Broadway flows designed to perform specific actions. They can be fully automated or LLM-assisted.

### When to Use Tools

While the generic Data Retrieve agent handles most queries using schema descriptions, tools become essential for:

| Scenario                         | Reason                                                       |
| -------------------------------- | ------------------------------------------------------------ |
| **Sophisticated Queries**        | Complex business rules requiring predefined SQL statements crafted by data specialists |
| **Actions with Business Impact** | Controlled flows for operations involving monetization, compliance, or auditing requirements |
| **External System Integration**  | Dedicated calls to services outside Fabric's responsibility  |

### Tool Identification

Tools are identified by:

- **Tags** - One or more tags for discoverability
- **Description** - Clear explanation of the tool's purpose
- **Parameter Remarks** - Documentation on input/output parameters

This information is passed to the LLM to aid in tool selection during plan execution.

### Common Tools

| Tool                   | Purpose                                            |
| ---------------------- | -------------------------------------------------- |
| `queryExecute`         | Runs structured data queries                       |
| `searchKBDataRetrieve` | Retrieves knowledge base information               |
| `describeDomain`       | Provides data model information for SQL generation |

> **Note:** Tools typically do not involve AI unless an agent is tagged as a tool.



## Utility Actors

AI Fusion uses several utility actors for working with AI, like LLMConst, LLAppend and LLInvoke.

For more information read [here](06_llm_calls_utility_actors.md).

