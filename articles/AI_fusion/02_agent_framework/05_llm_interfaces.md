# LLM Interfaces

LLM interfaces are the connection layer between the AI Fusion framework and Large Language Model (LLM) providers. This article explains how to configure and use LLM interfaces effectively.

## Overview

The AI Fusion implementation calls LLMs at various points in the agent workflow to handle different tasks:

| Task               | Description                                                  |
| ------------------ | ------------------------------------------------------------ |
| **Reflecting**     | Analyzing user queries to determine the appropriate response path |
| **SQL Generation** | Building database queries from natural language requests     |
| **Planning**       | Creating step-by-step execution strategies                   |
| **Answering**      | Formulating natural-language responses for users             |

When an LLM task is triggered, Fabric looks up the configured LLM interface and uses it to communicate with the underlying model, using the credentials defined in that interface.

## Supported Providers

LLM interfaces are installed via K2exchange. Some of the foundation model providers, such as AWS Bedrock and Google, behave like an infrastructure and host also models that they do not own. Following are few examples. 

* OpenAI
* Anthropic
* AWS Bedrock
* Google



## Creating an LLM Interface

### Step 1: Install the Provider Extension

1. Open K2exchange in Fabric Studio
2. Choose the desired LLM connector extension
3. Click **Install**
4. Deploy all changes

> Note: As with any installed extension added to your project, you should add its files into your project's Git repository.

### Step 2: Create the Interface

1. Navigate to **Shared Objects > Interfaces > Other**
2. Create a new interface of the installed provider type
3. Configure the required parameters

### Interface Configuration Parameters

| Parameter       | Description                              | Example                |
| --------------- | ---------------------------------------- | ---------------------- |
| **Tag**         | Identifier used to select this interface | `default`              |
| **Model**       | The specific model to use                | `gpt-4.1`              |
| **Temperature** | Controls response randomness (0-1)       | `0`                    |
| **Max Tokens**  | Maximum response length                  | `4096`                 |
| **Host**        | API endpoint                             | `api.openai.com`       |
| **Port**        | API port                                 | `443`                  |
| **Path**        | API path                                 | `/v1/chat/completions` |
| **Token**       | API authentication token                 | `sk-...`               |



## Using Multiple Interfaces

You can create multiple LLM interfaces for different purposes:

### Use Cases for Multiple Interfaces

| Scenario              | Approach                                                     |
| --------------------- | ------------------------------------------------------------ |
| **Cost Optimization** | Use a lighter model for simple tasks, powerful model for complex reasoning |
| **Specialized Tasks** | Different models for SQL generation vs. natural language responses |
| **Fallback**          | Secondary interface if primary provider is unavailable       |
| **Testing**           | Compare responses across different models                    |

### Configuring Interface Selection

To use a specific interface in your flow:

1. Create the interface with a unique tag (e.g., `sql-generator`)
2. In your Broadway flow, configure the LLM actor's `interface` parameter
3. Use the format `llm://[tag]` (e.g., `llm://sql-generator`)

