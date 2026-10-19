# MCP Client and AI Fusion Demo Release Notes

Release notes of the **mcp** (MCP Client) and **aifusion-demo** extensions. The newest version is listed first.

For compatibility with Fabric and with the other AI Fusion extensions, see [AI Fusion Versions](/articles/Product_Versions/AI_Fusion_versions.md).



<a id="mcp"></a>
## MCP Client (Preview)

<a id="mcp-1.0.1"></a>
### 1.0.1 — May 11, 2026

- Fixed a Jackson dependency issue.
- MCP SDK upgraded from 0.15.0 to 1.1.2.

<a id="mcp-1.0.0"></a>
### 1.0.0 — April 2026

- Initial release: the `Mcp` interface type and the MCP Broadway actors (list and call tools, prompts and resources).



<a id="aifusion-demo"></a>
## AI Fusion Demo

<a id="aifusion-demo-3.0.0"></a>
### 3.0.0 — August 17, 2026

- First release on the 3.x line, aligned with `aifusion` 3.x, which it requires.
- The banking demo with the `banking` declarative agent, its sub-agents, reference packs and hook flows.
- Vector pipeline flows moved from `SharedObjects/Broadway/aifusion/vectors/` to `SharedObjects/Broadway/vectors/`.
- The `fileToEmbedding` flow is no longer included; it is delivered with the chunker.

<a id="aifusion-demo-1.0.3"></a>
### 1.0.3 — September 23, 2024

- Declares a dependency on the `aifusion` extension, which is installed automatically.
- General availability (preview tag removed).

<a id="aifusion-demo-1.0.2"></a>
### 1.0.2 — September 15, 2024

- Customization of styling and texts.

<a id="aifusion-demo-1.0.1"></a>
### 1.0.1 — August 13, 2024

- Fixed duplicate queries during the user story.
- Switched to the product-supplied `LLMConst` and `LLMAppend` actors.

<a id="aifusion-demo-1.0.0"></a>
### 1.0.0 — August 2024

- Initial release.
