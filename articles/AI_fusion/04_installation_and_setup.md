# Installation and Setup

AI Fusion 3 is delivered as a set of K2exchange extensions that you install into your Fabric project from Studio. This article lists the extensions, the order in which to install them, and the configuration needed before you build your first agent.



## Prerequisites

- **K2view Fabric and Web Studio.** The extension manifests require Fabric 8.4.0 or later (AI Fusion Studio requires 8.4.7). Several AI Fusion 3 capabilities require **Fabric 8.5.2** or later — in particular per-agent Observation configuration and the **Analyze** (Studio AI) actions in AI Fusion Studio. Fabric 8.5.2 or later is recommended.
- **An LLM provider account** — Anthropic, AWS Bedrock, OpenAI (or an OpenAI-compatible endpoint), Google Vertex AI, or Ollama — and the credentials your organization approved for it.
- **A database for insights** — SQLite (default, suitable for development) or PostgreSQL for shared and production environments.



## Extensions

<table>
<tbody>
<tr>
<td><strong>Extension</strong></td>
<td><strong>Required</strong></td>
<td><strong>What it provides</strong></td>
</tr>
<tr>
<td><strong>aifusion</strong></td>
<td>Yes</td>
<td>The core platform: the <code>aifusion</code> Data Product (agent runtime, session tables, observation and evaluation flows, Observation web application), the AI Fusion web services in <code>k2_ws</code>, shared actors such as <code>AgentInvoke</code>, <code>LLMInvoke</code> and <code>Embed</code>, the <code>Vectors</code> LU and SQLite vector library, and the <code>assurance</code>, <code>evaluation_storage</code> and <code>snapshot_storage</code> interfaces.</td>
</tr>
<tr>
<td><strong>aifusion-studio</strong> (AI Fusion - Studio)</td>
<td>Yes (dependency of aifusion)</td>
<td>The Studio development experience: AI Explorer, Agent Editor, Preview chat and Trace, Eval Editor, and the Studio AI skills for building agents and diagnosing answers.</td>
</tr>
<tr>
<td><strong>pipeline</strong></td>
<td>Yes (dependency of aifusion)</td>
<td>The pipeline framework (<code>PipelineLu</code>, Pipeline web app and services) used to run evaluations and observation analysis pipelines.</td>
</tr>
<tr>
<td>LLM connector: <strong>anthropic</strong>, <strong>aws-bedrock</strong>, <strong>openai</strong>, <strong>google-genai</strong> or <strong>ollama</strong></td>
<td>At least one</td>
<td>The AI interface types for the provider (LLM and, where supported, embedding). Install the connectors your organization uses.</td>
</tr>
<tr>
<td><strong>mcp</strong> (MCP Client)</td>
<td>Optional</td>
<td>The MCP interface type and actors, needed for agents that use tools from MCP servers.</td>
</tr>
<tr>
<td><strong>aifusion-demo</strong></td>
<td>Optional (recommended for learning)</td>
<td>A complete banking demo: the <code>customer_bank</code> Data Product with the <code>banking</code> agent, its sub-agents, reference packs and hook flows and the demo source databases.</td>
</tr>
</tbody>
</table>

Version notes:

- The `xhigh` and `max` thinking levels require recent connector versions (anthropic 3.0.3 or later; aws-bedrock, openai and google-genai 3.0.1 or later). Older connectors treat them as `off`.
- When a connector is installed alongside `aifusion`, use `aifusion` 3.0.4 or later. From that version the core and the connectors no longer share files, so they can be upgraded independently.



## Installation Steps

1. In Web Studio, open K2exchange and install the **aifusion** extension. Its dependencies, **aifusion-studio** and **pipeline**, are required — install them as well if they are not installed automatically. See [Install an Extension](/articles/04_fabric_studio/28_web_k2exchange.md#install-an-extension).
2. Install one or more **LLM connector** extensions.
3. Optionally install **mcp** and **aifusion-demo**.
4. Configure an LLM interface (below).
5. Configure the insights database (below), if you are not using the SQLite default.
6. **Deploy** the `aifusion`, `k2_ws` and `PipelineLu` Data Products, and every Data Product that contains agents. The first full (non-soft) deploy of `aifusion` creates the insights database tables.

After the deploy, open the AI Explorer in Studio to confirm that the installation works. If you installed the demo, the `banking` agent appears under the `customer_bank` Data Product.



## Configure an LLM Interface

Agents call LLMs through Fabric AI interfaces of the connector's type (for example `Anthropic`, `AWSBedrockLLM`, `OpenAI`, `GoogleLLM`, `OllamaLLM`).

1. In Studio, create a new interface of the connector type under **Interfaces**.
2. Set the model, the credentials and other provider settings. Store credentials using Fabric's secret management.
3. Set its **Tag** to `default` to make it the default LLM interface. Agents that do not name an interface use `llm://default`, which resolves to the interface tagged `default`.
4. Make sure the interface is active in the environment you run. An interface that is not listed in the active environment is not available, and agents fail with `Unsupported IO interface llm://default`.

An agent can use a different interface through `settings.llm_interface` in its `agent.yaml`, and a different, typically cheaper, interface for context compaction and chat naming. See [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md) and [AI LLM Interface](/articles/24_non_DB_interfaces/15_LLM_interface.md).



## Configure the Insights Database

The insights database stores session metrics, Observation signals, evaluation results and batch jobs. It is set by shared globals:

<table>
<tbody>
<tr>
<td><strong>Global</strong></td>
<td><strong>Default</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>INSIGHTS_INTERFACE</code></td>
<td><code>assurance</code></td>
<td>The DB interface of the insights database. The default <code>assurance</code> interface is a SQLite file in the Fabric workspace.</td>
</tr>
<tr>
<td><code>INSIGHTS_INTERFACE_TYPE</code></td>
<td><code>sqlite</code></td>
<td>The database dialect: <code>sqlite</code> or <code>postgres</code>.</td>
</tr>
<tr>
<td><code>INSIGHTS_SCHEMA</code></td>
<td><code>main</code></td>
<td>The schema that holds the tables (for example <code>public</code> on PostgreSQL).</td>
</tr>
<tr>
<td><code>RETENTION_DAYS</code></td>
<td><code>30</code></td>
<td>Session retention used by the cleanup process (7–365 days).</td>
</tr>
<tr>
<td><code>EVALUATION_STORAGE_INTERFACE</code></td>
<td><code>evaluation_storage</code></td>
<td>Storage for evaluation snapshots.</td>
</tr>
<tr>
<td><code>EVALUATION_STORAGE_RETENTION_DAYS</code></td>
<td><code>90</code></td>
<td>Retention of evaluation snapshot files (30–365 days).</td>
</tr>
<tr>
<td><code>SNAPSHOT_STORAGE_INTERFACE</code></td>
<td><code>snapshot_storage</code></td>
<td>Storage for session snapshots taken during development.</td>
</tr>
</tbody>
</table>

To use PostgreSQL:

1. Define a PostgreSQL DB interface.
2. Override `INSIGHTS_INTERFACE`, `INSIGHTS_INTERFACE_TYPE=postgres` and `INSIGHTS_SCHEMA` (for example in the environment of the space).
3. The pipeline extension has equivalent globals (`PIPELINE_INTERFACE`, `PIPELINE_SCHEMA`, `PIPELINE_DIALECT`) for its own database — point them to PostgreSQL as well.
4. Deploy the `aifusion` LU (full deploy) to create the tables.

The deploy is idempotent: it creates missing tables and migrates tables created by earlier versions.



## Observation

The Observation dashboard is a tab of the AI Data Fusion web application, enabled by default. See [Opening Observation](/articles/AI_fusion/05_observation/00_intro_to_observation.md#opening-observation).



## Next Steps

- Create your first agent in Studio — see [AI Fusion Studio Overview](/articles/AI_fusion/03_studio/01_studio_overview.md).
- Learn the agent model — see [Agents Overview](/articles/AI_fusion/02_agents/01_agents_overview.md).



**Next article:** [Agent Platform](/articles/AI_fusion/02_agents/README.md)
