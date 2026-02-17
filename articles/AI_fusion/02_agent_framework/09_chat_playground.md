# The Chat Playground

## Overview

The Chat Playground is a core component of the aifusion web application that provides developers with an intuitive, ready-to-use chat interface for testing and interacting with their AI agents. 

>  The Chat Playground user interface and actions can be customized and branded, as explained [here](13_chat_playground_customize.md).

## Accessing the Chat Playground

Navigate to the aifusion web application and select **Chat** from the top navigation menu. The Chat Playground is located alongside the **Evaluation** and **Observation** modules in the main navigation.

## User Interface Components

### Header Section

The header displays the demo branding (e.g., "EliteBank" for the banking demo) along with a welcome message. Few action buttons are available in the upper-left corner:

- **User Profile**: Opens a side panel displaying the current customer context. This panel demonstrates how organizations might apply a chat which is provided to support representatives at call centers, where they can see a brief information about a customer, crafted with LLM in a natural language. This can help them to predict why customer interacts with them.
- **Chat Summary**: Opens a side panel displaying summary of the chat so far.
- **Restart**: Resets the conversation to start a fresh session with the agent.

### Chat Area

The central chat window displays the conversation between the user and the AI assistant. Each message shows:

- The sender identity (user or assistant name, e.g., "Elite Assistant")
- The message content with formatted responses including bullet points, dates, and monetary values
- Feedback icons (thumbs up, thumbs down, comment) allowing users to rate and annotate responses

### Input Field

At the bottom of the chat area, an input field with placeholder text "Ask me anything..." allows users to type questions. Messages are submitted using the blue send button or by pressing Enter.

### Trace Pane

The **Trace** button on the right edge of the screen opens a detailed debugging panel that reveals the agent's internal processing flow.



## The Trace Pane: Understanding Agent Execution

The Trace pane is a powerful debugging feature that provides complete transparency into how the agent processes each query. It displays:

### Session Information

Session information appears at the top of the Trace pane.

- **Chat ID**: Unique identifier for the conversation session
- **Started**: Timestamp of when the conversation began
- **Customer ID**: The customer IID being used

### Execution Pipeline

Each query displays the processing pipeline of the agentic workflow with its stages. For example, when an subagent path is chosen the stages typically will be:

1. **Reflect: on user query** — Initial query reflection and understanding
2. **Goal Refiner** — Determines the intent and refines the goal for the subagent
3. **[Domain] Agent** — The specialized subagent handling the request (e.g., "Credit Card Agent")
4. **Responder** — Formats and delivers the final response

### Detailed Metrics

For each stage, the Trace shows:

- **Execution time** (e.g., 1.3s, 8.3s)
- **Token consumption** displayed as input tokens, cached tokens, and output tokens

### Expandable Details

Each of the main workflow stages, representing agents, can be expanded to reveal:

- **System (goals, instructions & context)**: The system prompt, role definition, and objectives given to the agent
- **User**: The user's input
- **LLM Response**: Each iteration of the language model's response, showing tool calls made
- **Tool Calls**: Specific tools invoked 
- **Tool Execution Results**: The outcome of each tool call with execution time

### Agentic Flow Visualization

The Trace pane demonstrates how agents operate iteratively, making multiple tool calls to gather required information before formulating a response. For example, answering "Can I increase my credit card balance?" may involve:

1. Retrieving customer identification (using getSSN)
2. Fetching credit score information (getCreditScore)
3. Executing queries to check current card limits (queryExecute)
4. Multiple LLM iterations to process results and determine the response

