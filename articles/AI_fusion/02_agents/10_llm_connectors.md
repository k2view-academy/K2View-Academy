# LLM Connectors

An agent never talks to a model provider directly. It calls a Fabric **interface** — `llm://<name or tag>` — and the interface's connector translates the call into the provider's API. Switching an agent from one model or provider to another is therefore a configuration change, not a code change.

This article lists the connector extensions, their interface types and key properties, and explains how agents select an interface, how thinking levels and prompt caching map to each provider, and how MCP interfaces add external tools. For creating an interface in Fabric Studio, see the Fabric article [AI LLM Interface](/articles/24_non_DB_interfaces/15_LLM_interface.md). For installation order and prerequisites, see [Installation and Setup](/articles/AI_fusion/04_installation_and_setup.md).



## Connector Extensions

Each provider is a separate K2exchange extension. Install the ones you need next to the `aifusion` extension, then create interfaces of the types they add.

<table>
<tbody>
<tr>
<td><strong>Extension</strong></td>
<td><strong>Interface types</strong></td>
<td><strong>Notes</strong></td>
</tr>
<tr>
<td>Anthropic (<code>anthropic</code>, 3.0.4)</td>
<td><code>Anthropic</code> (LLM)</td>
<td>Claude models through the Anthropic API. No embedding models.</td>
</tr>
<tr>
<td>AWS Bedrock (<code>aws-bedrock</code>, 3.0.2)</td>
<td><code>AWSBedrockLLM</code> (LLM), <code>AWSBedrockEmbedding</code> (Embedding), <code>AWSBedrockKnowledgeBase</code>, <code>BedrockLambdaLLM</code> (LLM), <code>BedrockLambdaEmbedding</code> (Embedding)</td>
<td>Foundation models through the Bedrock Converse API, including Bedrock Guardrails. The Knowledge Base type retrieves from an AWS Bedrock Knowledge Base. The BedrockLambda types reach Bedrock through an HTTP endpoint (for example an API gateway) instead of the AWS SDK.</td>
</tr>
<tr>
<td>Google GenAI (<code>google-genai</code>)</td>
<td><code>GoogleLLM</code> (LLM), <code>GoogleEmbedding</code> (Embedding)</td>
<td>Gemini and embedding models through Vertex AI.</td>
</tr>
<tr>
<td>OpenAI (<code>openai</code>, 3.0.1)</td>
<td><code>OpenAI</code> (LLM), <code>OpenAIEmbedding</code> (Embedding)</td>
<td>OpenAI models, and any OpenAI-compatible endpoint (for example Azure OpenAI or a self-hosted inference server) through the <strong>Host</strong> property.</td>
</tr>
<tr>
<td>Ollama (<code>ollama</code>, 3.0.1) — preview</td>
<td><code>OllamaLLM</code> (LLM), <code>OllamaEmbedding</code> (Embedding)</td>
<td>Open-source models served by Ollama on your own infrastructure.</td>
</tr>
<tr>
<td>MCP Client (<code>mcp</code>, 1.0.1) — preview</td>
<td><code>Mcp</code></td>
<td>Connects to MCP servers, whose tools agents can call. See <a href="#mcp-interfaces">MCP Interfaces</a>.</td>
</tr>
</tbody>
</table>

The LLM connectors require Fabric 8.4.0 or later. They do not depend on the `aifusion` extension, but when both are installed, `aifusion` must be 3.0.4 or later.

Each connector caches one client per interface configuration. Changing any property of an interface creates a new client on the next request; deploying any Data Product (LU) clears the cache.



## Key Interface Properties

All AI interface types have a **Tag** property, used to select the interface (see [Selecting an Interface](#selecting-an-interface)).

### Anthropic

<table>
<tbody>
<tr>
<td><strong>Property</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Model</strong></td>
<td>The Claude model ID. Required.</td>
</tr>
<tr>
<td><strong>API Key</strong></td>
<td>The Anthropic API key. Required.</td>
</tr>
<tr>
<td><strong>Max Tokens</strong></td>
<td>Maximum response length. Required; default <code>4096</code>. Reasoning and answer share this budget on Claude 4.6 and later — see <a href="#thinking-levels">Thinking Levels</a>.</td>
</tr>
<tr>
<td><strong>Temperature</strong></td>
<td>Default <code>-1</code>, which means "do not send". Newer Claude models reject a temperature value; requests with thinking on never send it.</td>
</tr>
<tr>
<td><strong>Host</strong>, <strong>Version (override)</strong></td>
<td>Optional endpoint and <code>anthropic-version</code> header overrides.</td>
</tr>
<tr>
<td><strong>Timeout (MS)</strong></td>
<td>Default <code>180000</code>.</td>
</tr>
</tbody>
</table>

### AWS Bedrock

<table>
<tbody>
<tr>
<td><strong>Property</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>AuthenticationType</strong></td>
<td><code>default</code> — the IAM role of the Fabric instance or pod (recommended on AWS); <code>credentials</code> — explicit <strong>AccessKey</strong> and <strong>SecretAccessKey</strong>.</td>
</tr>
<tr>
<td><strong>Model</strong>, <strong>Region</strong></td>
<td>The model ID or ARN, and the AWS region where it is enabled.</td>
</tr>
<tr>
<td><strong>MaxTokens</strong>, <strong>Temperature</strong>, <strong>Timeout</strong>, <strong>Host</strong></td>
<td>Response length, randomness, timeout, and an optional custom endpoint (for example a VPC endpoint).</td>
</tr>
<tr>
<td><strong>GuardrailID</strong>, <strong>GuardrailVersion</strong></td>
<td>Optional Bedrock Guardrail to apply to every call (version default <code>DRAFT</code>).</td>
</tr>
<tr>
<td><strong>s3BucketName</strong>, <strong>s3Interface</strong>, <strong>s3InputPrefix</strong>, <strong>s3OutputPrefix</strong>, <strong>roleArn</strong></td>
<td>Batch inference only — see <a href="#batch-inference">Batch Inference</a>.</td>
</tr>
</tbody>
</table>

The IAM identity needs `bedrock:InvokeModel` and `bedrock:InvokeModelWithResponseStream`, plus `bedrock:ApplyGuardrail` when a guardrail is set. `AWSBedrockEmbedding` takes **ModelType** (Amazon Titan Embed or Cohere Embed) or a custom **Model**, and **Region**. `AWSBedrockKnowledgeBase` takes **KbId** and **Region**.

### Google GenAI

<table>
<tbody>
<tr>
<td><strong>Property</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>AuthenticationType</strong></td>
<td><code>credentials-json</code> (default) — the service-account JSON in <strong>CREDENTIAL</strong>, stored through Fabric's secret manager; <code>credentials-env</code> — Google Application Default Credentials (GKE Workload Identity, Cloud Run and so on).</td>
</tr>
<tr>
<td><strong>Project_ID</strong>, <strong>Location_ID</strong></td>
<td>The GCP project ID and region.</td>
</tr>
<tr>
<td><strong>Model</strong>, <strong>MaxTokens</strong>, <strong>Temperature</strong></td>
<td>The Gemini model and generation settings.</td>
</tr>
<tr>
<td><strong>GCSBucketName</strong>, <strong>GCSInterface</strong>, <strong>GCSInputPrefix</strong>, <strong>GCSOutputPrefix</strong></td>
<td>Batch inference only.</td>
</tr>
</tbody>
</table>

`GoogleEmbedding` adds **Type**, the embedding task type (`RETRIEVAL_DOCUMENT`, `RETRIEVAL_QUERY`, `SEMANTIC_SIMILARITY`, `CLASSIFICATION`, `CLUSTERING`).

### OpenAI

<table>
<tbody>
<tr>
<td><strong>Property</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>AuthenticationType</strong>, <strong>Token</strong></td>
<td><code>credentials</code> — the API key in <strong>Token</strong>; <code>default</code> — credentials from Fabric's secret manager or environment variables.</td>
</tr>
<tr>
<td><strong>Model</strong>, <strong>MaxTokens</strong>, <strong>Temperature</strong>, <strong>Timeout</strong></td>
<td>The model and generation settings.</td>
</tr>
<tr>
<td><strong>Host</strong></td>
<td>Leave empty for the OpenAI API. Set it to use an OpenAI-compatible service.</td>
</tr>
</tbody>
</table>

`OpenAIEmbedding` takes **AuthenticationType**, **Token**, **Model** and **Host**.

### Ollama (preview)

`OllamaLLM` takes **HOST**, **PORT** (default `11434`), **Path** (default `/api/chat`), **ProtocolType**, optional HTTP authentication (Basic, Bearer or OAuth2) for an Ollama behind a gateway, **Model** (exactly as shown by `ollama list`), **Temperature** and **Think**. `OllamaEmbedding` uses the `/api/embed` path.



## Selecting an Interface

### The default Tag

Agents normally use the `default` interface. `llm://default` resolves to the interface whose **Tag** is `default`; more generally, `llm://<id>` resolves to the interface with that **name**, then to an interface with that **tag**, then to the `default` one (see [AI LLM Interface](/articles/24_non_DB_interfaces/15_LLM_interface.md#selecting-an-interface)).

- Keep exactly one LLM interface tagged `default` in each environment. New `Anthropic` and `OllamaLLM` interfaces are created with the Tag `default`, so change it when you add a second one.
- The `default` interface must be active in the environment the agent runs in. If none is, agent calls to `llm://default` fail with an unsupported-interface error.
- Because `default` is resolved at run time, you can point the same agents at a different model per environment (for example a cheaper model in QA) without editing any agent.

### Per Agent, Per Task and Per Turn

<table>
<tbody>
<tr>
<td><strong>Where</strong></td>
<td><strong>What it controls</strong></td>
</tr>
<tr>
<td><code>settings.llm_interface</code></td>
<td>The interface an agent uses for its reasoning loop. Default <code>default</code>. Sub-agents use their own <code>settings.llm_interface</code>, so a sub-agent can run on a different model from its parent.</td>
</tr>
<tr>
<td><code>settings.compaction_interface</code>, <code>settings.chat_name_interface</code></td>
<td>The interfaces for context-compaction summaries and chat titles. Both fall back to <code>llm_interface</code>. Point them at a cheaper model. See <a href="/articles/AI_fusion/02_agents/08_sessions_and_context.md">Sessions and Context</a>.</td>
</tr>
<tr>
<td><code>interface</code> input of the <code>aifusion.Agent</code> flow or <code>AgentInvoke</code> actor</td>
<td>Overrides the main agent's <code>llm_interface</code> for one turn. See <a href="/articles/AI_fusion/02_agents/09_invoking_agents.md">Invoking Agents</a>.</td>
</tr>
</tbody>
</table>

```yaml
settings:
  llm_interface: claude_sonnet        # an interface name or tag; "default" if omitted
  compaction_interface: claude_haiku
  chat_name_interface: claude_haiku
```



## Thinking Levels

`settings.thinking` sets how hard the model reasons before answering: `off` (default), `low`, `medium`, `high`, `xhigh` or `max`. It is a provider-neutral intensity, not a token budget; each connector maps it to its own API and clamps when the provider has no exact equivalent.

<table>
<tbody>
<tr>
<td><strong>Level</strong></td>
<td><strong>Anthropic / Bedrock (Claude 4.6 and later)</strong></td>
<td><strong>Anthropic / Bedrock (earlier Claude)</strong></td>
<td><strong>OpenAI</strong></td>
<td><strong>Google Gemini</strong></td>
</tr>
<tr>
<td><code>off</code></td>
<td>thinking explicitly disabled</td>
<td>no thinking</td>
<td>no reasoning</td>
<td>no thinking</td>
</tr>
<tr>
<td><code>low</code></td>
<td>effort <code>low</code></td>
<td>2,048-token budget</td>
<td><code>low</code></td>
<td>2,048-token budget</td>
</tr>
<tr>
<td><code>medium</code></td>
<td>effort <code>medium</code></td>
<td>8,192-token budget</td>
<td><code>medium</code></td>
<td>8,192-token budget</td>
</tr>
<tr>
<td><code>high</code></td>
<td>effort <code>high</code></td>
<td>16,384-token budget</td>
<td><code>high</code></td>
<td>24,576-token budget</td>
</tr>
<tr>
<td><code>xhigh</code></td>
<td>effort <code>xhigh</code></td>
<td>16,384-token budget</td>
<td><code>xhigh</code></td>
<td>dynamic (model decides)</td>
</tr>
<tr>
<td><code>max</code></td>
<td>effort <code>max</code></td>
<td>16,384-token budget</td>
<td><code>xhigh</code> (OpenAI has no <code>max</code>)</td>
<td>dynamic (model decides)</td>
</tr>
</tbody>
</table>

- **Ollama** has no intensity dial: any level other than `off` turns thinking on.
- **`xhigh` and `max`** need Anthropic connector 3.0.3 or later, or Bedrock, OpenAI or Google GenAI connector 3.0.1 or later. Older connectors ignore or mis-map them.
- **`xhigh` is the recommended level for agentic Claude agents** that loop over tools or delegate. `max` costs considerably more for a small gain; `high` suits simple single-domain agents.
- **Thinking shares the response budget.** On Claude 4.6 and later, reasoning and answer both come out of the max-tokens budget; the connectors raise it to at least 16,000 when thinking is on. A tight budget at a high level can produce a truncated answer.
- **`off` really is off.** Claude 4.6 and later reason by default, so the connectors send an explicit "disabled" for `off`.
- An unrecognized level (a typo) is logged and treated as `off`.
- Reasoning is streamed to the client as `thinking_delta` events. The level applies to the main agent's LLM calls.



## Prompt Caching

`settings.use_cache` (default `true`) asks the connector to cache the stable prompt prefix — the system prompt and tool definitions — so repeated calls in a session cost less and respond faster.

<table>
<tbody>
<tr>
<td><strong>Provider</strong></td>
<td><strong>Behavior</strong></td>
</tr>
<tr>
<td>Anthropic, AWS Bedrock</td>
<td>Explicit cache points on the system prompt and tools when <code>use_cache</code> is on. Cache reads and writes are reported in token usage.</td>
</tr>
<tr>
<td>Google GenAI</td>
<td>Implicit caching only, applied by Google. <code>use_cache</code> has no effect.</td>
</tr>
<tr>
<td>OpenAI, Ollama</td>
<td>The connector does not add cache points; <code>use_cache</code> has no effect.</td>
</tr>
</tbody>
</table>

Anything that changes the prompt prefix — a different skill set, a context compaction — misses the cache for that call.



## Embeddings

Embedding interfaces (`OpenAIEmbedding`, `AWSBedrockEmbedding`, `BedrockLambdaEmbedding`, `GoogleEmbedding`, `OllamaEmbedding`) are used through the **Embed** actor in Broadway flows — for example a flow tool that runs a semantic search. Embed takes the `interface`, an `input` that is either one string or an array of strings (batched in one request when the provider supports it), and an optional `dimensionsOverride`; it returns `embeddings`, `total_tokens` and `dimensions`. Anthropic offers no embedding models.



## Batch Inference

Several connectors support the providers' asynchronous batch APIs, for offline workloads where no one waits for the answer. Each needs different configuration on the LLM interface:

<table>
<tbody>
<tr>
<td><strong>Provider</strong></td>
<td><strong>Batch support</strong></td>
</tr>
<tr>
<td>Anthropic</td>
<td>Message Batches API. No external storage needed.</td>
</tr>
<tr>
<td>OpenAI</td>
<td>Files API upload plus the Batch API. No external storage needed.</td>
</tr>
<tr>
<td>AWS Bedrock</td>
<td>Batch inference jobs through S3: set <strong>s3BucketName</strong>, <strong>s3Interface</strong> (a Fabric S3 interface) and <strong>roleArn</strong> — a role Bedrock can assume (its trust policy must allow <code>bedrock.amazonaws.com</code>).</td>
</tr>
<tr>
<td>Google GenAI</td>
<td>Vertex AI batch prediction through GCS: set <strong>GCSBucketName</strong> and <strong>GCSInterface</strong> (a Fabric GCS interface); the service account needs <strong>Storage Object Admin</strong> on the bucket.</td>
</tr>
<tr>
<td>Ollama</td>
<td>Not supported.</td>
</tr>
</tbody>
</table>



## MCP Interfaces

The MCP Client extension (preview) adds the `Mcp` interface type, which connects Fabric to a server implementing the Model Context Protocol. An agent can use every tool the server exposes:

```yaml
mcp_interfaces:
  - crm_mcp            # the Mcp interface name
```

The server's tools are advertised to the model as `MCP__<interface>__<tool>` — for example `MCP__crm_mcp__get_open_tickets` — and called through the interface. Tool hooks and the tool-name guard apply to them like any other tool. See [Tools](/articles/AI_fusion/02_agents/03_tools.md).

<table>
<tbody>
<tr>
<td><strong>Property</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><strong>ProtocolType</strong>, <strong>HOST</strong>, <strong>PORT</strong>, <strong>Path</strong></td>
<td>The server's address and endpoint path. Use HTTPS outside local development.</td>
</tr>
<tr>
<td><strong>TransportType</strong></td>
<td><code>HTTP_STREAMABLE</code>.</td>
</tr>
<tr>
<td><strong>AuthenticationType</strong>, <strong>Token</strong></td>
<td><code>None</code>, <code>Basic</code>, <code>Bearer</code>, <code>PAT</code> or OAuth2 flows, with the matching credential fields.</td>
</tr>
<tr>
<td><strong>RequestTimeout</strong></td>
<td>Seconds; default <code>10</code>. Increase it for slow tools.</td>
</tr>
</tbody>
</table>

Fabric 8.4 and later can expose Data Products as MCP servers, so an agent in one environment can use another Data Product's capabilities as MCP tools. See [Data Product MCP](/articles/46_MCP/README.md).

The extension also provides Broadway actors (`McpListTools`, `McpCallTool`, `McpListPrompts`, `McpGetPrompt`, `McpListResources`, `McpReadResource`) for using MCP servers from your own flows.



## Credentials and Secrets

Store provider keys and credentials through Fabric's secret manager or environment-level interface overrides, not in plain text in the project. Prefer identity-based authentication where the platform offers it — the instance IAM role for Bedrock, Application Default Credentials for Google. See [Secrets Management](/articles/26_fabric_security_sm/04a_secret_manager.md) and [Security and Privacy](/articles/AI_fusion/02_agents/11_security_and_privacy.md).

**Next article:** [Security and Privacy](/articles/AI_fusion/02_agents/11_security_and_privacy.md)
