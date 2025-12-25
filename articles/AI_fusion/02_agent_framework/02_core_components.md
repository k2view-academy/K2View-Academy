# Agent Framework Core Components

The aifusion implementation relies on several key components that work alongside Fabric's Data Products (Logical Units) and their logical functionalities.

In addition to the below components, some of the stuff is located at Shared Objects LU, for cross LUs usage tools and utilities.



## aifusion Logical Unit

The **aifusion LU** is the main component of the aifusion extension. Each instance (LUI) represents a single conversation session—typically a chat session that a user conducts through a web interface.

### Session Management

Each session (like a chat) creates a new aifusion LUI with the session ID as the instance identifier (IID).

As the conversation progresses, Fabric stores the user's questions and agent's responses within the dedicated instance. 

This mechanism maintains ongoing conversation context, enabling the AI agent to correctly interpret follow-up questions and references to earlier exchanges.

### Data Stored Per Session

The LUI tables capture:

| Data Type                | Description                                                  |
| ------------------------ | ------------------------------------------------------------ |
| **Conversation History** | User questions and agent responses                           |
| **Execution Traces**     | Activated agents, tools, and their execution duration for each step |
| **Token Usage**          | Detailed consumption metrics (stored in the assurance database) |

This data provides the conversation context but also supports debugging, optimization, and compliance requirements.

### Core Flows and Actors

The aifusion LU provides the actors and flows necessary to orchestrate agent processing. Most components are located under the `aifusion/Broadway/ai`



## LLM Interfaces

LLMs are invoked at various points in the agent workflow to handle different tasks:

- **Reflecting** on user queries to determine the appropriate response path
- **Generating SQL** queries based on natural language requests
- **Planning** **& executing** multi-step execution strategies
- **Formulating** natural-language answers for users

LLM interfaces are installed via the K2Exchange, offering various LLM provides, like OpenAI, Anthropic, AWS Bedrock, Google/Gemini.

Read [here](05_llm_interfaces.md) more information of provisioning LLM interfaces.



## Vector Data Stores & RAG

The aifusion framework supports vector databases for semantic search, enabling Retrieval-Augmented Generation (RAG). This is essential for organizations with unstructured data such as:

- Knowledge base articles
- Corporate procedures
- Policy documents

### How It Works

1. Documents are pre-converted into vector embeddings that capture semantic meaning
2. User queries are also converted to vectors
3. The system retrieves items whose meaning is closest to the query, when it identifies that information from vector store DB is relevant

The platform provides actors and embedding interfaces that help you to interact with vector stores. 

For additional information, see [Vector Data Stores](07_vector_db.md).

### Vector Data Store at Fabric Storage

You can use vector stores within Fabric storage, meaning as LUI within LUs. This is maintained SQLite-based vector stores with dedicated tables with a special form.

Fabric's built-in vector store is recommended for framework operational tasks like plans, procedures and base KB. For complex documents like tariff plans, device support guides, or marketing materials, consider dedicated vector DB services such as AWS Bedrock Knowledge Base.



## Metrics DB (Assurance)

The Metrics DB collects data for tracking, analysis, and optimization across three perspectives:

### Data Categories

| Category        | Metrics Collected                                            |
| --------------- | ------------------------------------------------------------ |
| **Operational** | LLM calls, models used, prompts sent, responses received, timing, tool calls and agents usage |
| **Financial**   | Token usage and cost tracking                                |
| **Behavioral**  | Agent triggering sequences, context, compliance, and satisfaction indicators |

### Supported Databases

- **SQLite** - For development environments
- **PostgreSQL** - For production deployments



The collected data powers customizable dashboards for tracking and improving the solution. This database also supports the Evaluation platform for testing and validation.

