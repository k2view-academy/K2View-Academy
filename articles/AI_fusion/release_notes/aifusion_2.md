# AI Fusion 2.x Release Notes

Release notes of the **aifusion** extension, versions 2.x. Documentation for this version is in the [legacy section](/articles/AI_fusion/legacy_v1_v2/README.md).

For compatibility with Fabric and with the other AI Fusion extensions, see [AI Fusion Versions](/articles/Product_Versions/AI_Fusion_versions.md).



<a id="v2.0.0"></a>
## Version 2.0.0

**Released:** February 2026 · **Minimum Fabric version:** 8.4.0

### New Features and Changes

- Re-designed backend for LLM invocation and embedding, for better maintainability and support for new features. Custom LLM and embedding interfaces can be implemented by extending the `BaseIoLLMInvoke` and `BaseIoLLMEmbed` classes, with the `LLMResult`, `EmbeddingResult`, `UsageStats`, `ToolDefinition` and `LLMError` records.
- Reworked streaming: responses are written immediately to the output stream provided by the web service.
- New `Chat` actor that wraps customer chat flows and manages the chat session context and state: Fabric GET of the session, STEP table updates, session monitoring and the output stream.
- New `ChatSessionParams` actor that exposes the chat session parameters (session ID, customer ID, app ID, user query and custom parameters).
- New `WriteToOutputStream` actor that writes JSON to the output stream at any point of a flow.
- **`LLMInvoke` and `LLMAgent`** — new outputs: `content` (final text response), `duration` (ms), `usage` (input, output, cache read, cache write and total tokens), `messages` (full conversation) and `tool_calls` (flows or MCP tools requested by the LLM).
- **`Embed`** — `input` accepts a string or an array of strings (batched when the interface supports it); new `dimensionsOverride` input; new `embeddings` output with the vectors, their string representation, the original text and dimensions.
- Actor descriptions and remarks were updated.

### Breaking Changes

- The LLM actors no longer return a stream. The final result is returned in `content`. The `stream` output is kept for backward compatibility, but it is removed when a flow using `LLMInvoke` or `LLMAgent` is opened — reconnect its links to `content`.
- `Embed`: flows that used the single `embedding` string must use the `embeddingString` key of the first element of `embeddings`, or iterate over the list.
- The `PrependParamsToStream` actor is no longer needed and no longer works as expected with the new streaming.
