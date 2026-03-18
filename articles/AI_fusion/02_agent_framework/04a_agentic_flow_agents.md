# Agentic Flow & Agents in Practice

This article provides a practical, implementation-focused guide to the agents that participate in the Agentic Flow within the AI Fusion framework.

While [The Agentic Flow](04_agentic_flow.md) article explains the conceptual execution model — how decisions are made, how paths are selected, and how responsibilities are delegated — this article translates these concepts into actual agents, focusing on how to set built-in agent attributes and how to create, configure, and reference sub-agents.

The following diagram illustrates a typical agentic flow, as viewed from the perspective of AI Fusion agents and tools:



![aifusion agentic flow](images/AIFusion-agnet-flow-agents.jpg)



## The Orchestrator

When the conversation entry-point flow calls the Orchestrator actor, it passes several input parameters. These inputs and attributes are then used by the Orchestrator flow itself and by its complementary agents — Reflector, Refiner and Planner. All these agents can be considered building blocks that you do not need to touch. 

<table>
<tbody>
<tr>
<td><strong>Input</strong></td>
<td><strong>Description</strong></td>
<td><strong>Example</strong></td>
</tr>
<tr>
<td><code>userQuery</code></td>
<td>The user's question or request</td>
<td>"What is my credit card balance?"</td>
</tr>
<tr>
<td><code>synopsis</code></td>
<td>Business entity story/summary</td>
<td>"David Smith, Retail customer, 2 credit cards..."</td>
</tr>
<tr>
<td><code>toolMTable</code></td>
<td>The name of the MTable containing the list of tool tags and their definitions. <br/>It is used by the <em>Planner</em> to tighten and limit which tools can be used, while Planner agent evaluates whether and which tools to use.</td>
<td><code>Banking_tool_tags</code></td>
</tr>
<tr>
<td><code>subagents_tag</code></td>
<td>A tag used for identifying the sub-agents available for this flow. <br/>This is used by both the <em>Reflector</em>, to look for and identify matching sub-agents, and by the <em>Planner</em>, when evaluating whether and which sub-agent to use.</td>
<td><code>Banking_subagent</code></td>
</tr>
<tr>
<td><code>responderPrompt</code></td>
<td>Guidelines for grounding the final response to the conversation caller (end user). They usually include rules and instructions for formalizing the answer.</td>
<td><code>TBD</code></td>
</tr>
<tr>
<td><code>searchProceduresFlowName</code></td>
<td>A flow used for retrieving corporate procedures. <br/>The procedures are used by the <em>Planner</em>, where this flow is responsible to retrieve the relevant procedures to augment the Planner context prompt. Procedures are usually retrieved via a vector store search.</td>
<td><code>Banking_searchProcedures</code></td>
</tr>
<tr>
<td><code>searchPlanFlowName</code></td>
<td>A flow used for retrieving sample plans. <br/>These samples are used by the <em>Planner</em>, where this flow is responsible to retrieve the relevant samples to augment the Planner context prompt, helping it to build a plan. Plans are usually retrieved via a vector store search.</td>
<td><code>Banking_searchPlan</code></td>
</tr>
</tbody>
</table>



## The Planner

If a request requires gathering information or executing multiple steps but does not match a predefined sub-agent, the \`orchestrator_planner\` is triggered.

The LLM is tasked with generating a step-by-step execution plan using several resources:

1. **Tools and Agent List:** A list of available and relevant (by tags) Broadway flows, along with descriptions and remarks added to input parameters. The LLM uses these descriptions to choose the most-suitable tool and determine the required inputs. Relevant sub-agents (by tag) are also providers. 
2. **Sample Plans** (Optional): JSON files (e.g., \`Banking_plans.json\`) — containing pre-built templates — that show the LLM how to combine tools to accomplish similar objectives.
3. **Corporate Procedures** (Optional): Documents that define business rules or provide step-by-step instructions (e.g., verification criteria for credit limit reduction).

Once the plan steps are prepared, the Planner executes them step-by-step. 

> Note: It is a best practice to rely on sub-agents to achieve a higher performance, as the Planner approach is slower and less predictable due to the required plan-generation step.



## The Worker Sub-agents

A worker sub-agent is a tagged Broadway flow (for example, \`loans_subagent\`) designed to handle a specific domain or category of requests, such as banking loans. 

### Sub-agent Discovery

The Reflector agent:

1. Searches for flows with agent tags
2. Examines the flow descriptions
3. Identifies the specialized agent best suited for the current task

When an appropriate sub-agent is found, the Orchestrator calls the Refiner agent to prepare the sub-agent's goal based on the user request and context.

##### Notes:

> 1. Use the sub-agent [Broadway flow properties](/articles/19_Broadway/33_flow_properties.md) to add tags and descriptions.
> 2. Agent tags are specified as an attribute of the *Orchestrator* agent. Providing it with all flows tagged as sub-agents can confuse and overwhelm the agent when selecting the appropriate sub-agent.



### Sub-agent Card

#### Input

When invoking a sub-agent, it receives detailed context, including:

- Its **Role and Objectives** (as a system message).
- **Domain Data** (details on relevant LUs, reference tables, column names, and descriptions) to enable dynamic SQL query construction.
- A specific **List of Tools** (other Broadway flows) tailored to the domain.

#### Logic 

A typical sub-agent is composed of logic steps alongside LLMAgent actor usage, providing improved control and reliability.

The LLMAgent actor decides which tools to activate and how to formulate responses based on:

1. **Predefined Domain Data** — retrieved using a DBCommand actor
2. **Schema Information** — tables relevant for SQL crafting
3. **System Prompt** — contains agent goals and behavioral guidelines
4. **User Prompt** — the refined user query
5. **Tool List** — available tools that the AI can invoke

> Use Broadway [flow properties](/articles/19_Broadway/33_flow_properties.md) to add tags and descriptions to sub-agent flows. The framework uses these properties to select the appropriate agent for each request.

