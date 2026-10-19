# LLM Connectors Release Notes

Release notes of the AI Fusion LLM connector extensions. Each connector is a separate extension that adds the AI interface types of one provider. The newest version is listed first for each connector. See [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md) for the documentation.

Connectors do not depend on the `aifusion` extension and run on base Fabric. When `aifusion` is installed alongside a 3.x connector, use `aifusion` 3.0.4 or later.

<ul>
<li><a href="#anthropic">Anthropic</a></li>
<li><a href="#aws-bedrock">AWS Bedrock</a></li>
<li><a href="#openai">OpenAI</a></li>
<li><a href="#google-genai">Google GenAI</a></li>
<li><a href="#ollama">Ollama</a></li>
</ul>



<a id="anthropic"></a>
## Anthropic

<a id="anthropic-3.0.4"></a>
### 3.0.4 — September 1, 2026

- Packaging only: the bundled `httpclient5` and `httpcore5` libraries were upgraded (5.6.4 and 5.4.3) to match the libraries resolved for all extensions.

<a id="anthropic-3.0.3"></a>
### 3.0.3 — August 24, 2026

- **Fixed** `400 invalid_request_error: "thinking.type.enabled" is not supported for this model` on newer Claude models (such as Claude Sonnet 5, Opus 5, Opus 4.8 and Fable 5). Only pre-4.6 models use `budget_tokens`; newly released models work without a connector update.
- `thinking: off` now explicitly disables thinking on Claude 4.6+ models, which otherwise reason by default and consume `max_tokens`.
- New thinking levels `xhigh` and `max`, mapped to the API's effort setting.
- Thinking no longer overrides the configured temperature; thinking requests omit it. With thinking off, the configured temperature is sent as is.
- The sample interface uses the `-1` "do not send" temperature value, as required by newer models.
- An unrecognized thinking level is logged and treated as `off`.

<a id="anthropic-3.0.2"></a>
### 3.0.2 — August 23, 2026

- Temperature is marked as deprecated; its default is `-1` (not sent).

<a id="anthropic-3.0.0"></a>
### 3.0.0 — August 17, 2026

- The connector no longer bundles AI Fusion framework files; it ships only the shared `common/llmprovider` package, so it no longer overwrites `aifusion` files.
- Version aligned with `aifusion` 3.x.

<a id="anthropic-2.0.3"></a>
### 2.0.3 — August 2, 2026

- Fixed the client cache, so updating an interface no longer reuses a client built with old settings.

<a id="anthropic-2.0.2"></a>
### 2.0.2 — May 25, 2026

- Fixed a tool-call loop with Claude Sonnet 4.6 (the same tool called repeatedly).

<a id="anthropic-2.0.1"></a>
### 2.0.1 — May 11, 2026

- Fixed a Jackson dependency issue.
- Anthropic SDK upgraded from 2.4.0 to 2.30.0.

<a id="anthropic-2.0.0"></a>
### 2.0.0 — February 2026

- Updated to match `aifusion` 2.0.0.



<a id="aws-bedrock"></a>
## AWS Bedrock

<a id="aws-bedrock-3.0.2"></a>
### 3.0.2 — September 1, 2026

- **Fixed** interface failures on Fabric 8.5 (`ServiceConfigurationError: Provider ...Apache5SdkHttpService not found`), which affected deployments without static AWS credentials (IRSA / web identity).
- AWS SDK upgraded from 2.44.4 to 2.54.9; the extension ships the `apache5-client` library.

<a id="aws-bedrock-3.0.1"></a>
### 3.0.1 — August 24, 2026

- **Fixed** `400 "thinking.type.enabled" is not supported for this model` for newer Anthropic models on Bedrock.
- `thinking: off` explicitly disables thinking on Claude 4.6+ models.
- New thinking levels `xhigh` and `max`.
- An unrecognized thinking level is logged and treated as `off`.

<a id="aws-bedrock-3.0.0"></a>
### 3.0.0 — August 17, 2026

- The connector no longer bundles AI Fusion framework files; it ships only the shared `common/llmprovider` package.
- Version aligned with `aifusion` 3.x.

<a id="aws-bedrock-2.0.2"></a>
### 2.0.2 — August 2, 2026

- Fixed the client cache, so updating an interface no longer reuses a client built with old settings.

<a id="aws-bedrock-2.0.1"></a>
### 2.0.1 — May 11, 2026

- Fixed a Jackson dependency issue.
- AWS SDK upgraded from 2.31.78 to 2.44.4.

<a id="aws-bedrock-2.0.0"></a>
### 2.0.0 — February 2026

- Updated to match `aifusion` 2.0.0.

<a id="aws-bedrock-1.0.5"></a>
### 1.0.5 — December 2025

- Bedrock Lambda LLM and Bedrock Lambda Embedding interfaces.
- `MaskStream` actor for stream masking and unmasking.
- Improved error handling.
- New configuration options for LLM agents (`interface`, `useCache`, `name`).

<a id="aws-bedrock-1.0.3"></a>
### 1.0.3

- Support for Bedrock Guardrails.



<a id="openai"></a>
## OpenAI

<a id="openai-3.1.0"></a>
### 3.1.0 — October 15, 2026

- **Azure OpenAI support:** new **API Type** (`OpenAI` / `Azure OpenAI`) and **API Version** properties. With Azure, requests use deployment-scoped URLs on any host, including API gateways in front of Azure, and API keys are sent in the `api-key` header.
- **OAuth 2.0 client-credentials authentication** (for Microsoft Entra ID or another identity provider). The token is obtained from the tenant ID or token URL, client ID, secret and scope, cached per interface and refreshed before it expires.
- New **API** property: the Responses API (default) or the Chat Completions API, for endpoints that expose only `/chat/completions`.
- These options apply to the `OpenAI` LLM interface; `OpenAIEmbedding` is unchanged.

<a id="openai-3.0.1"></a>
### 3.0.1 — August 24, 2026

- `thinking: xhigh` maps to OpenAI's `xhigh` reasoning effort, and `max` is clamped to `xhigh`. Previously, levels other than low/medium/high sent no reasoning effort.
- An unrecognized thinking level is logged and treated as `off`.

<a id="openai-3.0.0"></a>
### 3.0.0 — August 17, 2026

- The connector no longer bundles AI Fusion framework files; it ships only the shared `common/llmprovider` package.
- Version aligned with `aifusion` 3.x.

<a id="openai-2.0.2"></a>
### 2.0.2 — August 2, 2026

- Fixed the client cache, so updating an interface no longer reuses a client built with old settings.

<a id="openai-2.0.1"></a>
### 2.0.1 — May 11, 2026

- Fixed a Jackson dependency issue.
- OpenAI SDK upgraded from 4.17.0 to 4.35.0.

<a id="openai-2.0.0"></a>
### 2.0.0 — February 2026

- Migrated to the official OpenAI Java SDK (4.17.0).
- Updated to match `aifusion` 2.0.0.
- Dimension setting for embeddings on supported models.
- **Breaking:** the `OpenAI` and `OpenAIEmbedding` interface definitions changed; existing interfaces must be recreated.

<a id="openai-1.0.3"></a>
### 1.0.3 — September 25, 2025

- Embedding support with a new interface type.
- Tool usage support.



<a id="google-genai"></a>
## Google GenAI

<a id="google-genai-3.0.1"></a>
### 3.0.1 — August 24, 2026

- **Fixed** `thinking: xhigh` and `max`, which disabled thinking. They now request Gemini's dynamic thinking budget.
- An unrecognized thinking level is logged and treated as `off`.

<a id="google-genai-3.0.0"></a>
### 3.0.0 — August 17, 2026

- The connector no longer bundles AI Fusion framework files; it ships only the shared `common/llmprovider` package.
- Version aligned with `aifusion` 3.x.
- Uses the Gson library provided by the Fabric runtime.

<a id="google-genai-2.0.2"></a>
### 2.0.2 — August 2, 2026

- Removed the `credentials-file` authentication option (credential files were stored unencrypted). `credentials-json`, stored in the secret manager, is the default; `credentials-file` returns an explicit "no longer supported" error.
- Fixed the client cache, so updating an interface no longer reuses a client built with old settings.

<a id="google-genai-2.0.1"></a>
### 2.0.1 — May 11, 2026

- Fixed a Jackson dependency issue.
- Google GenAI SDK upgraded from 1.28.0 to 1.53.0.

<a id="google-genai-2.0.0"></a>
### 2.0.0 — February 2026

- Migrated from the Vertex AI SDK to the Google GenAI SDK.
- New `GoogleEmbedding` interface.
- Updated to match `aifusion` 2.0.0.



<a id="ollama"></a>
## Ollama (Preview)

<a id="ollama-3.0.1"></a>
### 3.0.1 — August 24, 2026

- Uses the shared thinking-level definition. Behavior is unchanged: any level other than `off` enables thinking.

<a id="ollama-3.0.0"></a>
### 3.0.0 — August 17, 2026

- The connector no longer bundles AI Fusion framework files; it ships only the shared `common/llmprovider` package.
- Version aligned with `aifusion` 3.x.

<a id="ollama-2.0.0"></a>
### 2.0.0 — February 2026

- Updated to match `aifusion` 2.0.0.
