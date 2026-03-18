# Agent Framework: Core Components

The implementation of aifusion relies on several key components that work alongside Fabric's Data Products (Logical Units) and their logical functionalities.

In addition to the components described below, certain tools and utilities are stored in the Shared Objects LU for use across multiple LUs.



## The aifusion Logical Unit

The **aifusion LU** is the main component of the aifusion extension. Each LU Instance (LUI) represents a single conversation session — typically a chat session — conducted by a user.

### Session Management

Each session (such as a chat) creates a new aifusion LUI with the session ID serving as the instance identifier (IID).

As the conversation progresses, Fabric stores the user's questions and the agent's responses within the dedicated instance. 

This mechanism maintains the ongoing conversation context, enabling the AI agent to correctly interpret follow-up questions and references to earlier exchanges. This capability is also known as Short-Term Memory (STM), or Working Memory, in agentic AI terminology.

### Data Stored Per Session

The LUI tables capture:

<table>
<tbody>
<tr>
<td><strong>Data Type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Conversation History</strong></td>
<td>User questions and agent responses</td>
</tr>
<tr>
<td><strong>Execution Traces</strong></td>
<td>Activated agents, tools, and their execution duration for each step</td>
</tr>
<tr>
<td><strong>Token Usage</strong></td>
<td>Detailed consumption metrics (stored in the assurance database)</td>
</tr>
</tbody>
</table>

The above data types not only provide the conversational context, but also support debugging, optimization, and compliance requirements.

### Core Flows and Actors

The aifusion LU provides the actors and flows needed to orchestrate agent processing. Most actor and flow components are located under the `aifusion/Broadway/ai` path.



## LLM Interfaces

LLMs are invoked at various points in the agent workflow to perform different tasks:

- Evaluating user queries to determine the appropriate response path
- Text-to-SQL query generation
- Planning and executing multi-step execution strategies
- Generating natural-language responses for users

LLM interfaces are installed via K2exchange, offering integrations with multiple LLM providers, such as OpenAI, Anthropic, AWS Bedrock, and Google’s Vertex AI.

For more information on provisioning LLM interfaces, read [here](05_llm_interfaces.md).



## Vector Data Stores and RAG

The aifusion framework supports vector databases for semantic search, enabling Retrieval-Augmented Generation (RAG). This is essential for organizations with unstructured data such as knowledge base (KB) articles, corporate procedures, policy documents

### Vector Data Store in Fabric Storage

Vector data stores can be used in Fabric storage, where where they will be embedded as tables inside LUIs. These vector stores are SQLite-based with dedicated tables in a special format.

Fabric's built-in vector store is mainly recommended for: 

* Unstructured data associated with the business entity LUI, such as personal contracts and agreements.
* Framework operational tasks, such as plans, procedures and the base KB. 

For complex documents including tariff plans, device support guides, or marketing materials, consider using dedicated vector database services, such as AWS Bedrock Knowledge Bases.

The GenAI Data Fusion platform, **aifusion**, provides actors and embedding interfaces to interact with vector stores. 

For additional information, see [Vector Data Stores](07_vector_db_intro.md).



## Metrics/Assurance DB

The Metrics/Assurance DB collects data for tracking, analysis, and optimization across three perspectives:

### Data Categories

<table>
<tbody>
<tr>
<td><strong>Category</strong></td>
<td><strong>Metrics Collected</strong></td>
</tr>
<tr>
<td><strong>Operational</strong></td>
<td>LLM calls, models used, prompts sent, responses received, timing, tool calls and agents usage</td>
</tr>
<tr>
<td><strong>Financial</strong></td>
<td>Token usage and cost tracking</td>
</tr>
<tr>
<td><strong>Behavioral</strong></td>
<td>Agent triggering sequences, context, compliance, and satisfaction indicators</td>
</tr>
</tbody>
</table>

**Supported Databases:**

- **SQLite** — for development environments
- **PostgreSQL** — for production deployments



The collected data powers customizable dashboards for tracking and improving the solution. This database also supports the Evaluation platform for testing and validation.



## The aifusion Web Application

The aifusion web app provides tools that accompany the full agent framework and agent-builder lifecycle:

* **Chat Playground** — a chat user interface that provides developers with a quick, out-of-the-box way to interact with their agents. It also includes a Trace pane that displays a detailed view the inputs and outputs of each invoked agent and tool, as well as the token consumption at each step.

  >  This Trace pane is available in addition to the comprehensive debugging capabilities and visibility provided by Broadway flows and Java code within the Studio.

* **Evaluation workspace** — where testers, via a native chat interface, can easily design and build test cases for your agents, organize and save them into test suites, and evaluate performance across multiple dimensions. Its full integration with Fabric as a data product platform enables the creation of reliable agents for enterprises.

  >  The **Pipeline** is an accompanying tool used to run regressions on these test cases.

* **Observation** — a production monitoring and analytics platform that provides visibility into agent behavior, performance metrics, and operational health through flexible dashboards, with the ability to inspect specific conversations in detail.
