# Agentic Flow Agents

This article describes the agents that implement the Agentic Flow execution model within the AI Fusion framework.









## Utilizing Subagents

A worker subagent is a tagged Broadway flow (e.g., with `loans_subagent` tag) to handle a specific domain or request type (banking loans). 

### Subagent Discovery

The Reflector agent:

1. Looks for flows with agent tags
2. Examines their descriptions
3. Identifies the specialized agent best suited for the current task

Orchestrator then calls the Refiner agent to prepare the subagent's goal according to user request and context.

##### Notes:

> 1. Use the subagent [Broadway flow's properties](/articles/19_Broadway/33_flow_properties.md) to add tags and description.
> 2. Agent tags are specified as attribute of the *Orchestrator* agent. Providing it all flows which are tagged as subagents can confuse and overwhelmed the agent to choose the right sub-agent.



When invoking a subagent, it receives detailed context, including:

- Its **Role and Objectives** (as a system message).
- **Domain Data** (details on relevant LUs, reference tables, column names, and descriptions) to enable dynamic SQL query construction.
- A specific **List of Tools** (other Broadway flows) tailored to the domain.



## The Planner Approach

If a request requires gathering information or executing multiple steps but does not fit a predefined subagent, the `orchestrator_planner` is triggered.

The LLM is tasked with generating a step-by-step execution plan using several resources:

1. **Sample Plans:** JSON files (e.g., `Banking_plans.json`) containing pre-built templates showing the LLM how to combine tools to accomplish similar objectives.
2. **Tools List:** A full list of available Broadway flows, along with descriptions and remarks added to input parameters. The LLM uses these descriptions to choose the right tool and determine the required inputs. Tools can be also subagents. 
3. **Corporate Procedures:** Documents indexed in the vector repository that define business rules or step-by-step instructions (e.g., verification criteria for credit limit reduction).

Once the plan steps are prepared, it executes them step-by-step. 

> Note: It is a best practice to rely on subagents for high-performance needs, as the Planner approach is slower and less predictable due to the required plan generation step.


