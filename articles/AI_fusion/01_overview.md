# GenAI Data Fusion Overview

K2view GenAI Data Fusion (**AI Fusion**) is an end-to-end platform for building, testing, running and governing enterprise AI agents on top of K2view Data Products. Agents built with AI Fusion answer from real-time, entity-level business data — a single customer, account, policy or member — retrieved securely from across your enterprise systems.

AI Fusion is purpose-built for high-scale 360° systems in industries such as telecommunications, finance, insurance and healthcare, where privacy, performance and precision are essential.

This documentation describes **AI Fusion 3**. If you are running AI Fusion 1.x or 2.x, see the [legacy documentation](/articles/AI_fusion/legacy_v1_v2/README.md).



## The Challenges Enterprises Face in Adopting AI

Foundation models are trained on extensive general knowledge, but they have no access to your organization's specific, real-time operational data — the data needed for meaningful business interactions.

Industry research shows that 50–70% of customer service inquiries require direct access to enterprise systems. Questions such as "What's my current account balance?" or "Why was my bill higher this month?" depend on real-time data that is spread across several business systems.

Meeting this need raises several data challenges:

- **Data Fragmentation** — enterprise data resides across dozens of disparate systems (CRM, Billing, ERP, support platforms), making it difficult to deliver complete, unified information to AI applications.
- **Real-time Requirements** — AI applications need quick access to up-to-date data to deliver accurate, responsive conversations.
- **Security & Privacy** — LLMs must access only the data relevant to the current user and context, under strict access control.
- **Scale & Performance** — thousands of simultaneous AI interactions must be served with fast responses across enterprise-scale data volumes.
- **Quality & Reliability** — responses must rely on complete, current and accurate data to prevent hallucinations and preserve user trust.



## How AI Fusion 3 Addresses Them

AI Fusion combines the [Data Product Platform](/articles/01_fabric_overview/01_what%20is%20fabric.md) with a declarative agent runtime, a dedicated development environment in Studio, and built-in evaluation and observation.

### Declarative Agents on Data Products

In AI Fusion 3, an agent is a set of files that lives inside a Data Product (LU): an `agent.yaml` definition, plus optional skills, sub-agents, reference documents, evaluation suites and observation configuration. There is no orchestration flow to build — the runtime runs the reasoning loop, calls tools, delegates to sub-agents, streams the answer, and records everything for trace and analysis.

Agents work directly on the entity's micro-database: a `step_start` hook loads the customer's data and the built-in `query` tool lets the LLM run SQL against it, guided by schema skills that describe the tables. Learn more in [Agent Platform](/articles/AI_fusion/02_agents/README.md).

### Quality & Accuracy

- **Entity-centric data** — a unified 360° view of each business entity (customer, order, product), consolidated from multiple source systems.
- **Live data synchronization** — current information through Fabric's synchronization with the source systems.
- **Schema-aware SQL generation** — schema skills can embed the live LU table definitions, so the LLM always generates SQL against the deployed structure.
- **Procedures and knowledge** — plan-then-execute mode guided by written procedures, and cited answers from reference documents.

### Privacy & Security

- **Entity-level access** — each conversation works on the micro-database of the entity it was started for.
- **Trusted inputs** — values such as the customer ID are passed as agent inputs that the LLM cannot change.
- **Guardrail hooks** — `pre_tool_call` hooks can block any tool call; `constraints` declare rules the agent must never break, and they cannot be dropped by delegation.
- **Data masking support** — PII can be masked inside the tool and hook flows that read it, using Fabric masking. Masking is configured per project and is not applied automatically.
- **Role-based permissions** — AI Fusion web services run under standard Fabric authentication and authorization.

### Control & Trace

- **Full execution trace** — every step, LLM call, tool call and sub-agent run, with inputs, outputs, durations and tokens.
- **Cost management** — token usage per LLM call, interface and agent.
- **Advanced observability** — LLM-scored signals per conversation, per-agent dashboards and conversation review.

### Maintainability

- **Unified platform** — one toolset for data retrieval, agent development, testing and monitoring, inside K2view Studio.
- **Form-based agent editor** — build and test agents in AI Fusion Studio, with AI-assisted agent building and answer diagnosis.
- **Flexible model management** — Anthropic, AWS Bedrock, OpenAI, Google GenAI and Ollama connectors; choose the model and reasoning level per agent.
- **Integrated evaluation** — regression test suites per agent, scored by LLM-as-a-judge on configurable dimensions and thresholds.



## Platform Modules

<table>
<tbody>
<tr>
<td><strong>Module</strong></td>
<td><strong>What it provides</strong></td>
</tr>
<tr>
<td><a href="/articles/AI_fusion/02_agents/README.md">Agent Platform</a></td>
<td>The declarative agent runtime: agent definitions, tools, skills, sub-agents, planning, reference search, hooks, sessions, the streaming Agent API and LLM connectors.</td>
</tr>
<tr>
<td><a href="/articles/AI_fusion/03_studio/README.md">AI Fusion Studio</a></td>
<td>The Studio extension for building agents: AI Explorer, Agent Editor, Preview chat with trace, Eval Editor and AI-assisted development.</td>
</tr>
<tr>
<td><a href="/articles/AI_fusion/04_evaluation/README.md">Evaluation</a></td>
<td>Per-agent regression test suites, LLM-as-a-judge scoring and performance baselines, run from Studio.</td>
</tr>
<tr>
<td><a href="/articles/AI_fusion/05_observation/README.md">Observation</a></td>
<td>Production monitoring: automatic conversation analysis, per-agent dashboards and conversation review.</td>
</tr>
</tbody>
</table>

Altogether, AI Fusion uses K2view's proven Data Product Platform to turn enterprise data into AI-ready resources, combining real-time data integration, enterprise-grade security and a governed agent lifecycle.



**Next article:** [What's New in AI Fusion 3](/articles/AI_fusion/02_whats_new_in_v3.md)
