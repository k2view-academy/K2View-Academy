# AI Data Fusion Modules and Apps

K2view's GenAI Data Fusion platform, **AI Fusion**, is an enterprise-grade framework for building, testing, deploying, and governing AI-powered agents and apps. Designed to work seamlessly with Data Products and operational data at scale, AI Fusion delivers reliability, predictability, and observability to AI systems.

The platform streamlines the full lifecycle of enterprise GenAI agents, from design and orchestration of complex multi-agent flows, through evaluation and testing for continuous monitoring of live executions. As a result, this lifecycle ensures accuracy, context-awareness, compliance, and cost efficiency in production environments.

## Core Modules

The platform consists of three core modules:

### 1. Agents Builder & Framework

A low-code, Studio-integrated environment for creating agents, defining tools, and orchestrating flows with Broadway. Key features include:

- Full step-by-step debugging capabilities
- LLM-agnostic architecture supporting multiple providers
- Connectors for various LLM and vector search services
- Built-in chat playground for testing apps and agents
- Comprehensive Trace view showing agent and tool execution flows, token usage, and response times of each level within a conversation step

Learn [here](/articles/AI_fusion/02_agent_framework/01_intro_agent_framework.md) about the Agent Builder and Framework.

### 2. Evaluation

A comprehensive testing and assessment system for validating AI agent performance before production deployment. Capabilities include:

- Multiple assessment methodologies, including LLM-as-judge with configurable thresholds
- Multi-dimensional scoring (accuracy, relevance, clarity, politeness, and more)
- Robust testing solution for multi-turn AI conversations
- Complete workflow covering test creation, regression testing and results analysis
- Detailed drill-down views for thorough analysis

This core module ensures that agents and apps meet predefined quality standards across various criteria prior to production deployment.

### 3. Observation

A production monitoring and analytics platform providing visibility into agent behavior, performance metrics, and operational health. Features include:

- Tracking of key indicators such as token usage, response quality, sentiment, risk level, and system performance
- LLM-scored indicators that are fully configurable
- Customizable dashboard widgets defined by type and query
- Deep-dive capabilities for reviewing individual conversation threads

This enables teams to identify issues, optimize costs, and continuously improve agent effectiveness based on production data.



## Getting Started

The AI Fusion platform is delivered as a solution built on K2view's Fabric framework. To get started in Studio:

1. Install the **aifusion** extension from [K2exchange](/articles/04_fabric_studio/28_web_k2exchange.md#install-an-extension)
2. Install the relevant LLM connector extension from K2exchange according to your organization's guidelines and licenses
3. For Evaluation regression test execution, install and deploy the **Pipeline** module from K2exchange
4. *Recommended:* Install the **aifusion-demo** extension. It includes three complete end-to-end demo apps that serve as implementation references for various business types and use cases.

### Installed Components

These extensions add all utilities required to implement GenAI agents and run related execution processes, including:

#### Logical Units

| LU Name | Description |
|-----------|-------------|
| **aifusion** | This is the main LU. It contains AI-related flows, actors, and utilities. LU tables store chat memory, traces, and token usage, where each LUI represents a single AI session (e.g., a chat). |
| **PipelineLu** | This LU provides a framework for organizing and executing Broadway flows in a structured hierarchy: pipelines → suites → cases. It includes utility actors for dynamic loading, file scanning, labeling, and linking. |

#### Additional Components

- Web Services and Shared Objects as cross-project utilities
- Interface metadata, code and library JARs (located in the `lib` folder)

### Metrics/Assurance DB

This database collects agents' functionality, token usage, and other metrics during agent sessions. SQLite (for development) and PostgreSQL databases are supported out-of-the-box. The Metrics database tables are created automatically during aifusion LU deployment. For more information, see [here]().



## Next Steps

- 
- [Agent Framework: Core Components](/articles/AI_fusion/02_agent_framework/02_core_components.md)
- [Building Agentic Workflow](/articles/AI_fusion/05_examples/01_agent_workflow_example.md) 

