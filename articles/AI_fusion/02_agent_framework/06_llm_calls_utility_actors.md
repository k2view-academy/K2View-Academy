# LLM Calls Utility Actors



## Invoking an LLM

The `LLMInvoke` actor is the primary method through which requests are sent to an LLM. It sends structured messages and receives responses.

### Message Roles

When sending a prompt to an LLM, the messages are structured with distinct roles to indicate the purpose of each message:

| Role          | Purpose                                                  | Example                                                      |
| ------------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| **System**    | High-level instructions, tone, and behavioral guidelines | "You are an AI assistant for Elite Bank. Always answer in a friendly, professional tone." |
| **User**      | The actual question or input submitted by the end user           | "What is the interest rate on a 30-year fixed mortgage?"     |
| **Assistant** | Previous model outputs for conversation continuity       | [Previous response in multi-turn conversation]. The assistance |

### LLMInvoke Actor Parameters

| Parameter   | Type    | Description                                  |
| ----------- | ------- | -------------------------------------------- |
| `interface` | string  | Interface identifier (e.g., `llm://default`) |
| `messages`  | object  | Array of role-based messages                 |
| `stream`    | boolean | Determines whether the response is streamed during generation               |

### Output Parameters

| Output               | Description             |
| -------------------- | ----------------------- |
| `stream`             | The response content    |
| `total_tokens`       | Total tokens used       |
| `input_tokens`       | Tokens in the prompt    |
| `output_tokens`      | Tokens in the response  |
| `cache_read_tokens`  | Tokens read from cache  |
| `cache_write_tokens` | Tokens written to cache |



## Prompt Construction Actors

Fabric provides utility actors that simplify and streamline the process of building prompts:

### LLMConst

This actor constructs LLM prompt messages for any role and is used for defining the static portions of a prompt.

> The content can include parameter references in the form of ${value}.
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

