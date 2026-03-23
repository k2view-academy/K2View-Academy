# LLM Calls Utility Actors



## Invoking an LLM

The `LLMInvoke` actor is the primary method through which requests are sent to an LLM. It sends structured messages and receives responses.

### Message Roles

When sending a prompt to an LLM, the messages are structured with distinct roles to indicate the purpose of each message:

<table>
<tbody>
<tr>
<td><strong>Role</strong></td>
<td><strong>Purpose</strong></td>
<td><strong>Example</strong></td>
</tr>
<tr>
<td><strong>System</strong></td>
<td>High-level instructions, tone, and behavioral guidelines</td>
<td>"You are an AI assistant for Elite Bank. Always answer in a friendly, professional tone."</td>
</tr>
<tr>
<td><strong>User</strong></td>
<td>The actual question or input submitted by the end user</td>
<td>"What is the interest rate on a 30-year fixed mortgage?"</td>
</tr>
<tr>
<td><strong>Assistant</strong></td>
<td>Previous model outputs for conversation continuity</td>
<td>[Previous response in multi-turn conversation]. The assistance</td>
</tr>
</tbody>
</table>

### LLMInvoke Actor Parameters

<table>
<tbody>
<tr>
<td><strong>Parameter</strong></td>
<td><strong>Type</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>interface</code></td>
<td>string</td>
<td>Interface identifier (e.g., <code>llm://default</code>)</td>
</tr>
<tr>
<td><code>messages</code></td>
<td>object</td>
<td>Array of role-based messages</td>
</tr>
<tr>
<td><code>stream</code></td>
<td>boolean</td>
<td>Determines whether the response is streamed during generation</td>
</tr>
</tbody>
</table>

### Output Parameters

<table>
<tbody>
<tr>
<td><strong>Output</strong></td>
<td><strong>Description</strong></td>
</tr>
<tr>
<td><code>stream</code></td>
<td>The response content</td>
</tr>
<tr>
<td><code>total_tokens</code></td>
<td>Total tokens used</td>
</tr>
<tr>
<td><code>input_tokens</code></td>
<td>Tokens in the prompt</td>
</tr>
<tr>
<td><code>output_tokens</code></td>
<td>Tokens in the response</td>
</tr>
<tr>
<td><code>cache_read_tokens</code></td>
<td>Tokens read from cache</td>
</tr>
<tr>
<td><code>cache_write_tokens</code></td>
<td>Tokens written to cache</td>
</tr>
</tbody>
</table>



## Prompt Construction Actors

Fabric provides utility actors that simplify and streamline the process of building prompts:

### LLMConst

This actor constructs LLM prompt messages for any role and is used for defining the static portions of a prompt.

> The content can include parameter references in the form of \${value}.
>
> If the value key is not found, the parameter reference will remain in the text for later phases.



### LLMAppend

This actor merges multiple message arrays into a single array before passing them to the LLMInvoke actor.

**Use Case:** Combine system instructions, few-shot examples, and user query into one prompt.

**Example Flow:**

```
LLMConst (System) ────┐
                      │
LLMConst (Examples) ──┼──▶ LLMAppend ──▶ LLMInvoke
                      │
LLMConst (User) ──────┘
```
