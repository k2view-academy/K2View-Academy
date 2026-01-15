# Agent Framework Core Components

The implementation of aifusion relies on several key components that work alongside Fabric's Data Products (Logical Units) and their logical functionalities.

In addition to the below components, some of the stuff is located at Shared Objects LU, for cross LUs usage tools and utilities.



## aifusion Logical Unit

The **aifusion LU** is the main component of the aifusion extension. Each instance (LUI) represents a single conversation session—typically a chat session that a user conducts through a web interface.

### Session Management

Each session (like a chat) creates a new aifusion LUI, with the session ID serving as the instance identifier (IID).

As the conversation progresses, Fabric stores the user's questions and the agent's responses within the dedicated instance. 

This mechanism maintains the ongoing conversation context, enabling the AI agent to correctly interpret follow-up questions and references to earlier exchanges. This capability is also known as Short-Term Memory (STM), or Working Memory, in agentic AI terminology.

### Data Stored Per Session

The LUI tables capture:

| Data Type                | Description                                                  |
| ------------------------ | ------------------------------------------------------------ |
| **Conversation History** | User questions and agent responses                           |
| **Execution Traces**     | Activated agents, tools, and their execution duration for each step |
| **Token Usage**          | Detailed consumption metrics (stored in the assurance database) |

This data provides the conversation context but also supports debugging, optimization, and compliance requirements.

### Core Flows and Actors

The aifusion LU provides the actors and flows needed to orchestrate agent processing. Most components are located under the `aifusion/Broadway/ai` path.



## LLM Interfaces

LLMs are invoked at various points in the agent workflow to handle different tasks:

- Reflecting on user queries to determine the appropriate response path
- Generating SQL queries based on natural-language requests
- Planning and executing multi-step execution strategies
- Formulating natural-language answers for users

LLM interfaces are installed via K2exchange, offering various LLM provides, like OpenAI, Anthropic, AWS Bedrock, Google/Gemini.

For more information on provisioning LLM interfaces, read [here](05_llm_interfaces.md).



## Vector Data Stores and RAG

The aifusion framework supports vector databases for semantic search, enabling Retrieval-Augmented Generation (RAG). This is essential for organizations with unstructured data such as knowledge base (KB) articles, corporate procedures, policy documents

### Vector Data Store in Fabric Storage

You can use vector stores within Fabric storage, meaning as tables within LUIs. This is maintained SQLite-based vector stores with dedicated tables with a special form.

Fabric's built-in vector store is mainly recommended for: 

* Unstructured data associated with the business entity LUI, such as personal contracts and agreements.
* Framework operational tasks, such as plans, procedures and the base KB. 

For complex documents like tariff plans, device support guides, or marketing materials, consider dedicated vector DB services such as AWS Bedrock Knowledge Base.

The platform provides actors and embedding interfaces that help you to interact with vector stores. 

For additional information, see [Vector Data Stores](07_vector_db_intro.md).



## Metrics DB (Assurance)

The Metrics DB collects data for tracking, analysis, and optimization across three perspectives:

### Data Categories

| Category        | Metrics Collected                                            |
| --------------- | ------------------------------------------------------------ |
| **Operational** | LLM calls, models used, prompts sent, responses received, timing, tool calls and agents usage |
| **Financial**   | Token usage and cost tracking                                |
| **Behavioral**  | Agent triggering sequences, context, compliance, and satisfaction indicators |

**Supported Databases:**

- **SQLite** - For development environments
- **PostgreSQL** - For production deployments



The collected data powers customizable dashboards for tracking and improving the solution. This database also supports the Evaluation platform for testing and validation.



## aifusion Web Application

The aifusion web app provides the tools that accompany the whole agent framework and builder lifecycle:

* **Chat Playground**, a chat user interface, which enables developer a quick, out of the box way, to interact with their agents. it also includes a Trace Panel that enable viewing a detailed trace of what are inputs and outputs of each called agent and tool and how many tokens were consumed in each step.

  >  This Trace panel is available in addition to the comprehensive debugging capabilities and visibility at the Broadway flows and Java code within the Studio.

* **Evaluation workspace**, where testers can design and easily build, with a native chat interface, test cases for your agents, saving them into test suites, with all evaluation dimensions. Having it fully integrated with the Fabric, as data product platform leverage the capability to build reliable agents for C360 enterprises needs.

  >  An accomplishing tool, for running regressions on these test cases, is the **Pipeline**.

* **Observation**, a production monitoring and analytics platform providing visibility into agent behavior, performance metrics, and operational health, via flexible dashboards, as well as with the ability to drill down, by picking a specific conversation and see its details.
