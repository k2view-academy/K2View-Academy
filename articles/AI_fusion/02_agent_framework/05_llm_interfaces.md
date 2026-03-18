# LLM Interfaces

LLM interfaces are the connection layer between the AI Fusion framework and Large Language Model (LLM) providers. This article explains how to configure and use LLM interfaces effectively.

## Overview

The AI Fusion implementation invokes LLMs at multiple points in the agent workflow to execute specific tasks:

<table>
<tbody>
<tr>
<td><strong>Task</strong></td>
<td><strong>Description</strong></td>
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

When an LLM task is triggered, Fabric looks up the configured LLM interface and uses it to communicate with the underlying model, using the credentials defined in that interface.

## Supported Providers

LLM interfaces are installed via K2exchange. Some of the foundation model providers function as infrastructure platforms and host also models that they do not own. The following are a few examples: 

* OpenAI
* Anthropic
* AWS Bedrock
* Google's Vertex AI



## Creating an LLM Interface

### Step 1: Install the Provider Extension

1. Open K2exchange in Fabric Studio
2. Choose the desired LLM connector extension
3. Click **Install**
4. Deploy all changes

> Note: As with any installed extension added to your project, you should add its files into your project's Git repository.

### Step 2: Create the Interface

1. Navigate to **Shared Objects > Interfaces > Other**
2. Create a new interface of type AI LLM
3. Configure the required parameters

### Interface Configuration Parameters

<table>
<tbody>
<tr>
<td><strong>Parameter</strong></td>
<td><strong>Description</strong></td>
<td><strong>Example</strong></td>
</tr>
<tr>
<td><strong>Tag</strong></td>
<td>Identifier used to select this interface</td>
<td><code>default</code></td>
</tr>
<tr>
<td><strong>Model</strong></td>
<td>The specific model to be used</td>
<td><code>gpt-4.1</code></td>
</tr>
<tr>
<td><strong>Temperature</strong></td>
<td>Controls response randomness (0-1)</td>
<td><code>0</code></td>
</tr>
<tr>
<td><strong>Max Tokens</strong></td>
<td>Maximum response length</td>
<td><code>4096</code></td>
</tr>
<tr>
<td><strong>Host</strong></td>
<td>API endpoint</td>
<td><code>api.openai.com</code></td>
</tr>
<tr>
<td><strong>Port</strong></td>
<td>API port</td>
<td><code>443</code></td>
</tr>
<tr>
<td><strong>Path</strong></td>
<td>API path</td>
<td><code>/v1/chat/completions</code></td>
</tr>
<tr>
<td><strong>Token</strong></td>
<td>API authentication token</td>
<td><code>sk-...</code></td>
</tr>
</tbody>
</table>



## Using Multiple Interfaces

You can create multiple LLM interfaces for different purposes:

### Scenarios for Multiple Interfaces

<table>
<tbody>
<tr>
<td><strong>Scenario</strong></td>
<td><strong>Consider</strong></td>
</tr>
<tr>
<td><strong>Cost optimization</strong></td>
<td>The use of a lighter model for simple tasks and a more powerful model for complex reasoning</td>
</tr>
<tr>
<td><strong>Specialized tasks</strong></td>
<td>The use of different models for SQL generation and natural language responses</td>
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

### Configuring Interface Selection

To use a specific interface in your flow:

1. Create the interface with a unique tag (e.g., \`sql-generator\`)
2. In your Broadway flow, configure the LLM actor's \`interface\` parameter
3. Use the format \`llm://[tag]\` (e.g., \`llm://sql-generator\`)
