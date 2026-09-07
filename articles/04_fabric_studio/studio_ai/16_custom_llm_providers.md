# Custom LLM Providers

Besides the built-in provider entries (Anthropic, OpenAI, Google, Hugging Face, Ollama and others described in [AI Features Settings](15_ai_features_settings.md#ai-enablement-and-llm-providers)), Studio AI can talk to any endpoint that implements the OpenAI or Anthropic API. This is what a **custom provider** is: an entry that names the endpoint URL and the model, instead of relying on the vendor's default endpoint.

Custom providers are used to connect Studio AI to:

* An **LLM gateway or proxy** operated by your organization, which enforces routing, quota or logging policies.
* A **self-hosted model** running in your own premises or cloud.
* **Fabric itself**, so that developers consume LLMs through a Fabric AI LLM interface and need no API key of their own. See [Fabric as a Provider](#fabric-as-a-provider).

Custom providers are defined in the Studio **Settings** UI, under **AI Features**, in the **Open AI Custom Models** or **Anthropic Custom** sub-sections. Each is a JSON array of model entries.

## Custom OpenAI Providers

Setting key: `ai-features.openAiCustom.customOpenAiModels`.

~~~json
"ai-features.openAiCustom.customOpenAiModels": [
  {
    "id": "my-openai-compatible-endpoint",
    "model": "gpt-4.1",
    "url": "https://llm-gateway.example.com/v1",
    "apiKey": "",
    "enableStreaming": true,
    "useResponseApi": false,
    "supportsStructuredOutput": false,
    "developerMessageSettings": "system"
  }
]
~~~

<table>
<tbody>
<tr>
<td width="270pxl"><strong>Field</strong></td>
<td width="630pxl"><strong>Description</strong></td>
</tr>
<tr>
<td><code>id</code></td>
<td>Identifier of the entry. This is the name the model is offered under in the chat model selector and in model aliases.</td>
</tr>
<tr>
<td><code>model</code></td>
<td>The model name sent to the endpoint.</td>
</tr>
<tr>
<td><code>url</code></td>
<td>Base URL of the OpenAI-compatible endpoint.</td>
</tr>
<tr>
<td><code>apiKey</code></td>
<td>API key sent to the endpoint. Leave empty when the endpoint does not require one.</td>
</tr>
<tr>
<td><code>enableStreaming</code></td>
<td>Whether responses are streamed back to the chat as they are generated.</td>
</tr>
<tr>
<td><code>useResponseApi</code></td>
<td>Whether to call the OpenAI Responses API instead of chat completions. Set to <code>false</code> for endpoints that implement chat completions only.</td>
</tr>
<tr>
<td><code>supportsStructuredOutput</code></td>
<td>Whether the endpoint supports structured (schema-constrained) output.</td>
</tr>
<tr>
<td><code>developerMessageSettings</code></td>
<td>How the system prompt is delivered - for example <code>system</code> to send it as a system message.</td>
</tr>
<tr>
<td><code>headers</code></td>
<td>Optional additional HTTP headers. See <a href="#custom-headers">Custom Headers</a>.</td>
</tr>
</tbody>
</table>

## Custom Anthropic Providers

Setting key: `ai-features.anthropicCustom.customAnthropicModels`. The entry names the model, the endpoint URL and an identifier, in the same way as a custom OpenAI entry.

~~~json
"ai-features.anthropicCustom.customAnthropicModels": [
  {
    "id": "gateway-test-claude",
    "model": "claude-sonnet-5",
    "url": "http://127.0.0.1:3999",
    "apiKey": true,
    "headers": {
      "Wm-Llmgw-User-Type": "",
      "Wm-Llmgw-User-Name": ""
    }
  }
]
~~~

## Custom Headers

Starting from V8.5.1, both custom OpenAI and custom Anthropic entries accept a `headers` object. Every key/value pair in it is added to each request sent to that endpoint. This is typically required by an LLM gateway that identifies the calling user or team through its own headers.

A header value can be given in three forms:

<table>
<tbody>
<tr>
<td width="270pxl"><strong>Form</strong></td>
<td width="630pxl"><strong>Description</strong></td>
</tr>
<tr>
<td>A literal string</td>
<td>The value is sent as written.</td>
</tr>
<tr>
<td><code>${env:&lt;envName&gt;}</code></td>
<td>The value is read from the named environment variable of the Fabric Dev server.</td>
</tr>
<tr>
<td><code>${file:&lt;pathToFile&gt;}</code></td>
<td>The value is read from the contents of the named file.</td>
</tr>
</tbody>
</table>

~~~json
"headers": {
  "X-Team": "data-platform",
  "X-Gateway-Token": "${env:LLM_GATEWAY_TOKEN}",
  "X-Client-Cert": "${file:/etc/k2view/llm-gateway/client.token}"
}
~~~

The `${env:...}` and `${file:...}` forms keep secrets out of the Studio configuration, so that the configuration can be shared or version-controlled while the secret stays on the server.

## Fabric as a Provider

Starting from V8.5.1, Fabric exposes an OpenAI-compatible endpoint of its own, backed by the project's [AI LLM interface](/articles/24_non_DB_interfaces/15_LLM_interface.md). Pointing Studio AI at it means that:

* Developers enter no API key at all - the provider credentials are defined once, on the Fabric interface.
* The organization controls which models are reachable, and which roles may use them.
* The model can be swapped by editing the Fabric interface, without changing any Studio setting.

Because the Fabric endpoint is OpenAI-compatible, it is configured as a **custom OpenAI provider**: set `url` to the Fabric `/api/v1` base URL and leave `apiKey` empty.

~~~json
"ai-features.openAiCustom.customOpenAiModels": [
  {
    "id": "fabric-openai-compatible-llm-endpoint",
    "model": "default",
    "url": "http://localhost:3213/api/v1",
    "apiKey": "",
    "enableStreaming": true,
    "useResponseApi": false,
    "supportsStructuredOutput": false,
    "developerMessageSettings": "system"
  }
]
~~~

Here `model` selects which LLM Fabric uses: `default` for the project's default LLM engine, or the name or tag of a specific AI LLM interface. The Fabric role of the calling user must be granted the **LLM_INVOKE** permission.

For the endpoint reference, the authorization details and the interface settings themselves, see [Fabric as an LLM Provider](/articles/24_non_DB_interfaces/15_LLM_interface.md#fabric-as-an-llm-provider).

## Using a Custom Provider

Once an entry is saved, its `id` appears wherever a model can be chosen:

* In the chat toolbar's model dropdown, for a single session. See [Using the AI Chat](03_using_the_ai_chat.md#the-ai-chat-toolbar).
* In the **Agents** tab of AI Configuration, as an agent's default model.
* In a **Model Alias** priority list, which is the recommended way to move all agents onto a provider at once. See [AI Configuration: Managing Agents and Settings](06_ai_configuration_and_settings.md#model-aliases-tab).
