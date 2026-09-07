# AI LLM Interface

The **AI LLM** interface type defines the connection to a Large Language Model (LLM) provider. Like any other Fabric interface, it is a [Shared Object](/articles/04_fabric_studio/12_shared_objects.md): the provider endpoint, the model and the API credentials are defined once, in the project, and are then used by every Fabric component that needs an LLM.

An AI LLM interface is consumed by:

* [GenAI Data Fusion](/articles/AI_fusion/02_agent_framework/02_core_components.md) agents - see [Use in GenAI Data Fusion](#use-in-genai-data-fusion).
* Broadway [LLM utility Actors](/articles/AI_fusion/02_agent_framework/06_llm_calls_utility_actors.md), addressed as `llm://<tag>`.
* External AI clients, through Fabric's [OpenAI-compatible provider endpoint](#fabric-as-an-llm-provider) - including [Studio AI](/articles/04_fabric_studio/studio_ai/01_getting_started_with_studio_ai.md).



## Creating an AI LLM Interface

### Step 1: Install the Provider Extension

LLM provider connectors are delivered as K2exchange extensions. Examples of supported providers are OpenAI, Anthropic, AWS Bedrock and Google Vertex AI.

1. Open **K2exchange** in Fabric Studio.
2. Choose the desired LLM connector extension and click **Install**.
3. Deploy all changes.

> Note: as with any installed extension, add its files to your project's Git repository.

### Step 2: Create the Interface

<studio>

Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select **AI LLM** from the **Interface Type** dropdown menu. Populate the connection settings and click **Save**.

</studio>

<web>

Go to **Project Tree** > **Implementation** > **Shared Objects**, right click **Interfaces** and select **New Interface**. Or: Go to the **Top Menu**, then select > **Fabric** > **New Interface**

Select an AI LLM from the **AI** section, enter a name for the interface and click **Create**. Populate the connection settings and click **Save**.

</web>

### Connection Settings

<table>
<tbody>
<tr>
<td width="200pxl"><strong>Parameter</strong></td>
<td width="500pxl"><strong>Description</strong></td>
<td width="200pxl"><strong>Example</strong></td>
</tr>
<tr>
<td><strong>Tag</strong></td>
<td>Identifier used to select this interface. An interface tagged <strong>default</strong> serves as the project's default LLM engine.</td>
<td><code>default</code></td>
</tr>
<tr>
<td><strong>Model</strong></td>
<td>The specific model to be used.</td>
<td><code>gpt-4.1</code></td>
</tr>
<tr>
<td><strong>Temperature</strong></td>
<td>Controls response randomness (0-1).</td>
<td><code>0</code></td>
</tr>
<tr>
<td><strong>Max Tokens</strong></td>
<td>Maximum response length.</td>
<td><code>4096</code></td>
</tr>
<tr>
<td><strong>Host</strong></td>
<td>API endpoint.</td>
<td><code>api.openai.com</code></td>
</tr>
<tr>
<td><strong>Port</strong></td>
<td>API port.</td>
<td><code>443</code></td>
</tr>
<tr>
<td><strong>Path</strong></td>
<td>API path.</td>
<td><code>/v1/chat/completions</code></td>
</tr>
<tr>
<td><strong>Token</strong></td>
<td>API authentication token.</td>
<td><code>sk-...</code></td>
</tr>
</tbody>
</table>

## Selecting an Interface

A project can hold several AI LLM interfaces - for example a lighter model for simple tasks and a stronger model for complex reasoning, or a secondary provider used as a fallback. Consumers select one by name or by tag, and Fabric resolves the reference in the following order:

1. An interface whose **name** matches the given identifier.
2. An interface carrying a matching **tag**.
3. Otherwise, the interface tagged **default**.

In a Broadway flow, set the LLM Actor's `interface` parameter to `llm://<tag>`, for example `llm://sql-generator`.

### Scenarios for Multiple Interfaces

<table>
<tbody>
<tr>
<td width="250pxl"><strong>Scenario</strong></td>
<td width="650pxl"><strong>Consider</strong></td>
</tr>
<tr>
<td><strong>Cost optimization</strong></td>
<td>The use of a lighter model for simple tasks and a more powerful model for complex reasoning</td>
</tr>
<tr>
<td><strong>Specialized tasks</strong></td>
<td>The use of different models for SQL generation, analyzing user queries to determine the appropriate response path,planning, and formulating natural language responses</td>
</tr>
<tr>
<td><strong>Fallback</strong></td>
<td>Secondary interface if primary provider is unavailable</td>
</tr>
<tr>
<td><strong>Testing</strong></td>
<td>Comparing responses across different models</td>
</tr>
</tbody>
</table>


When an LLM task is triggered, Fabric looks up the configured AI LLM interface and uses it to communicate with the underlying model, using the credentials defined in that interface. Tagging separate interfaces per task - for example a `sql-generator` interface alongside the `default` one - is what lets an implementation choose a different model for each of these steps.



## Use in GenAI Data Fusion

The [GenAI Data Fusion](/articles/AI_fusion/02_agent_framework/01_intro_agent_framework.md) agent framework invokes LLMs at several points in the agent workflow:

<table>
<tbody>
<tr>
<td width="250pxl"><strong>Task</strong></td>
<td width="650pxl"><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Reflecting</strong></td>
<td>Analyzing user queries to determine the appropriate response path</td>
</tr>
<tr>
<td><strong>SQL Generation</strong></td>
<td>Building database queries from natural language requests</td>
</tr>
<tr>
<td><strong>Planning</strong></td>
<td>Creating step-by-step execution strategies</td>
</tr>
<tr>
<td><strong>Answering</strong></td>
<td>Formulating natural-language responses for users</td>
</tr>
</tbody>
</table>

When an LLM task is triggered, Fabric looks up the configured AI LLM interface and uses it to communicate with the underlying model, using the credentials defined in that interface. Tagging separate interfaces per task - for example a `sql-generator` interface alongside the `default` one - is what lets an implementation choose a different model for each of these steps.

## Fabric as an LLM Provider

Starting from V8.5.1, Fabric exposes its configured AI LLM interfaces through an **OpenAI-compatible REST endpoint**. Any client that can talk to the OpenAI chat completions API - including the Studio AI assistant - can therefore point at Fabric instead of at a foundation model provider.

This means that:

* Users do not need a personal API key, and the organization does not need to distribute provider keys.
* The provider credentials stay in the Fabric project's AI LLM interface.
* Access is granted and audited with standard Fabric authorization.
* The model behind the endpoint can be swapped by editing the interface, without touching any client.

### Endpoints

The base URL is the Fabric web services URL, followed by `/api/v1`, for example `http://<fabric-host>:3213/api/v1`.

<table>
<tbody>
<tr>
<td width="120pxl"><strong>Verb</strong></td>
<td width="250pxl"><strong>Path</strong></td>
<td width="530pxl"><strong>Description</strong></td>
</tr>
<tr>
<td>POST</td>
<td><code>/api/v1/chat/completions</code></td>
<td>OpenAI-compatible chat completions. Supports streaming (SSE) and function tool calls.</td>
</tr>
<tr>
<td>GET</td>
<td><code>/api/v1/models</code></td>
<td>OpenAI-compatible model list. Fabric exposes the pseudo-model <code>default</code>; the actual provider and model are those configured on the AI LLM interface.</td>
</tr>
</tbody>
</table>

The `model` field of the request selects the interface: use `default` for the default LLM engine, or the name or tag of a specific AI LLM interface.

### Authorization

Requests are authenticated with a Fabric user or API token, passed in the standard `Authorization: Bearer` header, and require the **LLM_INVOKE** permission (or **ALL**):

~~~
GRANT LLM_INVOKE ON * TO <role>;
~~~

For more information about roles and permissions, see [Fabric Authorization Commands](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-command).

### Example

~~~bash
curl -s -X POST http://localhost:3213/api/v1/chat/completions \
  -H "Authorization: Bearer <fabric-token>" \
  -H "Content-Type: application/json" \
  -d '{"model":"default","messages":[{"role":"user","content":"Say hi in three words."}],"stream":false}'
~~~

### Connecting a Client

Any client that speaks the OpenAI chat completions API can be pointed at this endpoint by setting its base URL to the Fabric `/api/v1` URL and leaving its API key empty.

For the Fabric Studio AI assistant, see [Custom LLM Providers](/articles/04_fabric_studio/studio_ai/16_custom_llm_providers.md#fabric-as-a-provider).

[![Previous](/articles/images/Previous.png)](14_gcs_interface.md)
