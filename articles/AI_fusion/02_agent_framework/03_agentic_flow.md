# The Agentic Flow: Core Concepts and Execution Model

An agentic workflow is typically composed of several core steps. This article describes a recommended flow within the AI Fusion agent framework, where the flow is built as a Broadway flow as part of the project implementation.

The flow's high-level steps are as follows (letters, numbers and labeled terms correspond to the below diagram):

* [A] **Scoping** — associates the base business entity and the specific AI Fusion conversation with each session request/step.

* [B] **Foundational Context** — establishes the deterministic, pre-AI context layer — including the business entity story and conversation history — which forms the foundation for all subsequent reasoning and context expansion.

* [C] **AI Reasoning and Action**

  * Reflect on Query — determines the appropriate response path.

  * Execute — delegates responsibilities to the relevant execution sub-agent.

* [D] **Respond** — crafts and formulates the final response.



## Scoping

The purpose of this initial step is to bind the session to a specific base business entity's LUI and a specific AI Fusion conversation LUI.

> Note: *Base* refers to the leading LU, from which other LUs can be retrieved on demand.

> For example, the base LU can be the customer LU. Later, during request processing, sub-agents may require additional LUs, such as the orders LU.

By explicitly establishing this association (using the Fabric `GET` action), the system ensures that every request is strictly constrained to the relevant business entity and conversation scope. This prevents cross-entity data leakage, reduces the risk of context drift, and mitigates issues such as prompt hijacking or hallucinations caused by out-of-scope information.

As a result, all subsequent reasoning and actions are constrained to a well-defined and isolated scope.



> Notes:
>
> * To ensure security, the LUI of any additional LU should be attached based on the base business entity's LUI. See below for more information.
> * The Session ID is provided by the client (the caller of the main conversation flow), who should carefully manage it to ensure continuity of the conversation history.



## Foundational Context

This step establishes the initial, deterministic context layer by fetching the relevant business entity and conversation LUIs. It provides the baseline information that the AI will rely on before any dynamic reasoning, tool usage, or context expansion takes place.

> While additional context may be dynamically introduced later in the flow, this step guarantees that the system always starts from a reliable and controlled reference point.



This step is typically composed of two main actions:

### Retrieve the business entity story (Long-term memory)

A concise profile of the business entity is retrieved to serve as foundational knowledge for the AI. This may include structured attributes, historical summaries, past events, or other relevant descriptors. The information may provide background for explaining the reason for the current conversation.

This information is also known, in the agentic AI terminology, as Long-term memory (LTM) or “Persistent Knowledge”.

This retrieval is usually implemented through multiple queries across several LU tables. 

It is important to perform this step selectively and efficiently, by bringing in only the data that is beneficial for reasoning while avoiding excessive or irrelevant information. Overloading the context can confuse or mislead LLMs and degrade response quality.

### Retrieve conversation history (Short-term memory)

Several prior steps of the current conversation are retrieved to preserve continuity and intent. When the conversation becomes long, older interactions can be summarized to maintain coherence while keeping the context compact.

This information is also known, in the agentic AI terminology, as Short-term memory (STM) or “Working Memory”.

> NOTES:
>
> * The conversation lifetime should be carefully defined.
>   For example, in a CRM scenario, a new conversation may be initiated for each incoming customer call. In other cases, organizations may treat an entire day as a single conversational session, preserving context across multiple interactions.
>
>   Similar considerations also apply to non-chat and non-call-based sessions.
>
> * The number of prior steps can be set; usually, 4–5 steps provide sufficient context. When conversations grow long, earlier steps are summarized to maintain the context window without overloading it.



## AI Reasoning and Action

The AI Reasoning and Action phase is responsible for AI-driven decision-making and executions within the agentic workflow. During this phase, the framework evaluates the user’s request, determines the most appropriate response strategy, dynamically expands context as needed, and executes the required actions to produce a final answer.

This phase is not a single operation, but a controlled reasoning loop that combines reflection, decision-making, and execution.

### Reasoning Flow: Reason → Decide → Act

At a high level, the framework performs the following steps:

* **Reason** — analyzes the user’s request using the available foundational and accumulated context.

* **Decide** — selects the most suitable response path based on the request’s complexity and available information.

* **Act** — executes the selected path, which may involve calling tools, invoking sub-agents, or running multi-step plans.

As execution progresses, additional information may be retrieved or generated and added to the context, enabling more informed decisions in subsequent steps.

### Response Paths

As part of the reasoning process, the framework determines the best response path for the current request:

| Path                                      | Description                                                  | When to Use                                                  | Performance                   |
| ----------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ----------------------------- |
| **Have All Required Information** (1)     | Proceed directly to response formulation                     | Information exists in the business entity story, conversation history, or general knowledge | **Fastest**                   |
| **Call Specialized Worker Sub-agent** (2)  | Route the request to a domain-specific sub-agent              | The request requires specialized knowledge or tools (e.g., billing, payments) | **Faster and more accurate**  |
| **Build and Execute a Plan** (3)          | Create and execute a multi-step plan involving multiple actions or agents | The request requires coordination of several steps or tool invocations | Most **flexible**, but slower |
| **Clarify the Request** (4)               | Ask the user for additional information                      | The request cannot be resolved from the current context      | Fast                          |

### Flow Delegation and Ownership

Once a response path is selected, handling of the request is delegated to the appropriate execution logic, which is responsible for:

- Executing the required actions
- Expanding context as needed (via tools or sub-agents)
- Producing a complete and coherent result

Flow delegation ensures clear ownership and prevents overlapping responsibilities between agents.

Regardless of the selected path, all flows eventually converge at the Responder, which aggregates outputs and formulates the final response. At this stage, organizations can enforce formatting, compliance, and legal rules according to business directives.

### Agentic Flow Agents

The behaviors described above are implemented by a set of workflow orchestration agents and worker sub-agents, represented as Broadway actors and flows within the AI Fusion framework.

Together, these flows implement the reasoning, decision-making, and execution semantics of the AI Reasoning and Action phase, while keeping the workflow modular, extensible, and easy to evolve.  



The following diagram illustrates a typical agent flow; letters, numbers and labeled terms correspond to those mentioned at the beginning of this article:



![aifusion agentic flow](images/AIFusion-agnet-flow.jpg)





## Debug, Trace and Control

While the agentic workflow is in process, the platform logs and collects information about the agent usage within it for:

- **Comprehensive Auditing** — ensures every agent interaction and processing step can be audited and traced.  
- **Cost Management** — provides tracking and monitoring of token usage for cost control, in vast granularity information, by models and agents and for input, output and cached tokens.
- **Maintainability and Continuous Improvement** — each agent and tool call is logged for later analysis, enabling improvements to agent performance. 

The information is gathered into the aifusion LU tables, allowing tracing of a specific conversation session, as well as into the Metrics/Assurance DB, for overall monitoring and insights.

The information is also accessible in the **Trace pane** of the **Chat Playground**, part of the AI Fusion app. Here, you can create chats and view a detailed trace showing what the user asked, the inputs and outputs of each called agent and tool, and the number of tokens consumed at each step.

The Trace pane is available in addition to the comprehensive debugging capabilities and visibility of Broadway flows and the Java code within the Studio.
