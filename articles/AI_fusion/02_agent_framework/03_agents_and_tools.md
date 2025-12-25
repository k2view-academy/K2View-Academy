# Agent Framework Components: Agents, Tools and Utilities



## Understanding AI Agents

An AI agent is a module that receives data, context, and resources, then uses an AI model for reasoning, planning, and decision-making to achieve specific goals.

### Agent Resources

Agents can leverage:

- **Tools** - Broadway flows that perform specific actions
- **Context** - Business entity data, conversation history, and domain information
- **Procedures** - Corporate guidelines indexed in vector repositories



## Agent Types

The GenAI Data Fusion framework includes two categories of agents, both represented as Broadway actors and flows.

### Built-in Agents

#### Workflow Orchestrating Agents

| Agent            | Description                                                  |
| ---------------- | ------------------------------------------------------------ |
| **Orchestrator** | Manages the overall agentic flow and coordinates all processing |
| **Reflector**    | Part of the Orchestrator; evaluates and refines user queries based on context |
| **Planner**      | Part of the Orchestrator; creates and executes step-by-step task plans |
| **Refiner**      |                                                              |

#### Data Agents

| Agent             | Description                                                  |
| ----------------- | ------------------------------------------------------------ |
| **Data Retrieve** | Handles queries against Fabric Data Products using dynamic SQL generation |

### Project Implementation Agents

Implementation agents extend the built-in agents to accomplish domain-specific goals. These are Broadway flows tagged to handle specific domains or request types.

Example: A `loans_subagent` tag identifies an agent specialized for banking loan inquiries.

A typical subagent includes a combination of logic steps along with LLMAgent usage. In this way a better controlled and reliable flow is achieved.

The LLMAgent decides on tools activation and response formulation according to:

1. **Predefined Domain Data** - Retrieved using a DBCommand actor
2. **Schema Information** - Relevant tables for SQL crafting
3. **System Prompt** - Agent goals and behavioral guidelines
4. **User Prompt** - The refined user query
5. **Tool List** - Available tools the AI can invoke



> Use Broadway [flow properties](/articles/19_Broadway/33_flow_properties.md) to add tags and descriptions to subagent flows. The framework uses these to select the appropriate agent for each request.



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

AIFusion uses several utility actors for working with AI, like LLMConst, LLAppend and LLInvoke.

For more information read [here]().

