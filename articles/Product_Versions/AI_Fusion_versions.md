# AI Fusion Versions Table

GenAI Data Fusion (AI Fusion) is delivered as a set of K2exchange extensions. The table below lists each AI Fusion product with its latest version and compatible Fabric version. Click a version to open its release notes; each release-notes page lists all the versions of that product.

Please consult with us regarding the latest available and compatible Fabric version.



## Current Products

<table>
    <thead>
        <tr>
            <th align="left">Product</th>
            <th>Extension</th>
            <th>Latest Version</th>
            <th>Released Date</th>
            <th>Minimum Fabric Version</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td align="left">AI Fusion 3</td>
            <td>aifusion</td>
            <td><a href="/articles/AI_fusion/release_notes/aifusion_3.md#v3.0.8">3.0.8</a></td>
            <td>Sep 29, 2026</td>
            <td>8.4.0 (8.5.2 recommended)</td>
            <td>GA</td>
        </tr>
        <tr>
            <td align="left">AI Fusion Studio</td>
            <td>aifusion-studio</td>
            <td><a href="/articles/AI_fusion/release_notes/aifusion_studio.md#v1.0.1">1.0.1</a></td>
            <td>Sep 29, 2026</td>
            <td>8.4.7</td>
            <td>GA</td>
        </tr>
        <tr>
            <td align="left">Anthropic Connector</td>
            <td>anthropic</td>
            <td><a href="/articles/AI_fusion/release_notes/llm_connectors.md#anthropic-3.0.4">3.0.4</a></td>
            <td>Sep 1, 2026</td>
            <td>8.4.0</td>
            <td>GA</td>
        </tr>
        <tr>
            <td align="left">AWS Bedrock Connector</td>
            <td>aws-bedrock</td>
            <td><a href="/articles/AI_fusion/release_notes/llm_connectors.md#aws-bedrock-3.0.2">3.0.2</a></td>
            <td>Sep 1, 2026</td>
            <td>8.4.0</td>
            <td>GA</td>
        </tr>
        <tr>
            <td align="left">OpenAI Connector</td>
            <td>openai</td>
            <td><a href="/articles/AI_fusion/release_notes/llm_connectors.md#openai-3.1.0">3.1.0</a></td>
            <td>Oct 15, 2026</td>
            <td>8.4.0</td>
            <td>GA</td>
        </tr>
        <tr>
            <td align="left">Google GenAI Connector</td>
            <td>google-genai</td>
            <td><a href="/articles/AI_fusion/release_notes/llm_connectors.md#google-genai-3.0.1">3.0.1</a></td>
            <td>Aug 24, 2026</td>
            <td>8.4.0</td>
            <td>GA</td>
        </tr>
        <tr>
            <td align="left">Ollama Connector</td>
            <td>ollama</td>
            <td><a href="/articles/AI_fusion/release_notes/llm_connectors.md#ollama-3.0.1">3.0.1</a></td>
            <td>Aug 24, 2026</td>
            <td>8.4.0</td>
            <td>Preview</td>
        </tr>
        <tr>
            <td align="left">MCP Client</td>
            <td>mcp</td>
            <td><a href="/articles/AI_fusion/release_notes/other_extensions.md#mcp-1.0.1">1.0.1</a></td>
            <td>May 11, 2026</td>
            <td>8.3.0</td>
            <td>Preview</td>
        </tr>
        <tr>
            <td align="left">AI Fusion Demo</td>
            <td>aifusion-demo</td>
            <td><a href="/articles/AI_fusion/release_notes/other_extensions.md#aifusion-demo-3.0.0">3.0.0</a></td>
            <td>Aug 17, 2026</td>
            <td>8.4.0</td>
            <td>GA</td>
        </tr>
    </tbody>
</table>

The **aifusion** extension also requires the **pipeline** extension, which is installed with it.



## Recommended Combination

<table>
    <thead>
        <tr>
            <th align="left">Component</th>
            <th>Version</th>
        </tr>
    </thead>
    <tbody>
        <tr><td align="left">Fabric and Web Studio</td><td>8.5.2 or later</td></tr>
        <tr><td align="left">aifusion</td><td>3.0.8</td></tr>
        <tr><td align="left">aifusion-studio</td><td>1.0.1</td></tr>
        <tr><td align="left">LLM connectors</td><td>Latest 3.x of each connector you use</td></tr>
        <tr><td align="left">mcp (optional)</td><td>1.0.1</td></tr>
    </tbody>
</table>

Compatibility notes:

- LLM connectors 3.x do not depend on `aifusion`. When installed alongside it, use `aifusion` 3.0.4 or later.
- The `xhigh` and `max` thinking levels (aifusion 3.0.5 and later) require anthropic 3.0.3, aws-bedrock 3.0.1, openai 3.0.1 or google-genai 3.0.1 or later. Older connectors treat them as `off`.
- The **Response guidelines**, **Constraints** and delegation settings of AI Fusion Studio 1.0.1 require aifusion 3.0.8.



## Previous AI Fusion Versions

<table>
    <thead>
        <tr>
            <th align="left">Product</th>
            <th>Latest Version</th>
            <th>Released Date</th>
            <th>Minimum Fabric Version</th>
            <th>Documentation</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td align="left">AI Fusion 2</td>
            <td><a href="/articles/AI_fusion/release_notes/aifusion_2.md#v2.0.0">2.0.0</a></td>
            <td>Feb 2026</td>
            <td>8.4.0</td>
            <td><a href="/articles/AI_fusion/legacy_v1_v2/README.md">Legacy documentation</a></td>
        </tr>
        <tr>
            <td align="left">AI Fusion 1</td>
            <td><a href="/articles/AI_fusion/release_notes/aifusion_1.md#v1.0.8">1.0.8</a></td>
            <td>Jan 12, 2026</td>
            <td>8.3.0 (1.0.0: 8.0.0)</td>
            <td><a href="/articles/AI_fusion/legacy_v1_v2/README.md">Legacy documentation</a></td>
        </tr>
    </tbody>
</table>
