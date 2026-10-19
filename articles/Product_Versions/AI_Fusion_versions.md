# AI Fusion Versions Table

GenAI Data Fusion (AI Fusion) is delivered as K2exchange extensions. The tables below list the AI Fusion versions, the related modules and extensions, and the AI connectors that AI Fusion uses. Click a version to open its release notes.

Please consult with us regarding the latest available and compatible Fabric version.



## AI Fusion Versions

<table>
    <thead>
        <tr>
            <th align="left">AI Fusion Version</th>
            <th>Latest Release</th>
            <th>Released Date</th>
            <th>Minimum Fabric Version</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td align="left">AI Fusion 3</td>
            <td><a href="/articles/AI_fusion/release_notes/aifusion_3.md#v3.0.8">3.0.8</a></td>
            <td>Oct 19, 2026</td>
            <td>8.4.0 (8.5.2 recommended)</td>
            <td>Current</td>
        </tr>
        <tr>
            <td align="left">AI Fusion 2</td>
            <td><a href="/articles/AI_fusion/release_notes/aifusion_2.md#v2.0.0">2.0.0</a></td>
            <td>Feb 2026</td>
            <td>8.4.0</td>
            <td>Legacy</td>
        </tr>
        <tr>
            <td align="left">AI Fusion 1</td>
            <td><a href="/articles/AI_fusion/release_notes/aifusion_1.md#v1.0.8">1.0.8</a></td>
            <td>Jan 12, 2026</td>
            <td>8.3.0</td>
            <td>Legacy</td>
        </tr>
    </tbody>
</table>



## Related Modules and Extensions

Extensions that are used together with AI Fusion. From AI Fusion 3.0.8 they are installed separately.

<table>
    <thead>
        <tr>
            <th align="left">Module</th>
            <th>Extension</th>
            <th>Latest Release</th>
            <th>Released Date</th>
            <th>Minimum Fabric Version</th>
            <th>Compatible AI Fusion</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td align="left">AI Fusion Studio</td>
            <td>aifusion-studio</td>
            <td><a href="/articles/AI_fusion/release_notes/aifusion_studio.md#v1.0.1">1.0.1</a></td>
            <td>Sep 29, 2026</td>
            <td>8.4.7</td>
            <td>3.x (1.0.1 requires 3.0.8)</td>
            <td>Studio development experience: AI Explorer, Agent Editor, Preview and Trace, Eval Editor, AI-assisted agent building.</td>
        </tr>
        <tr>
            <td align="left">Pipeline</td>
            <td>pipeline</td>
            <td></td>
            <td></td>
            <td></td>
            <td>3.x</td>
            <td>Pipeline framework used to run evaluations and observation analysis pipelines. Required for evaluations.</td>
        </tr>
    </tbody>
</table>



## AI Connectors

LLM and MCP connectors are general Fabric AI extensions. They are not specific to AI Fusion, but AI Fusion agents use them to reach LLM providers and MCP servers. Install the connectors that your organization uses; see [LLM Connectors](/articles/AI_fusion/02_agents/10_llm_connectors.md) and [AI LLM Interface](/articles/24_non_DB_interfaces/15_LLM_interface.md).

<table>
    <thead>
        <tr>
            <th align="left">Connector</th>
            <th>Extension</th>
            <th>Interface Types</th>
        </tr>
    </thead>
    <tbody>
        <tr><td align="left">Anthropic</td><td>anthropic</td><td>Anthropic</td></tr>
        <tr><td align="left">AWS Bedrock</td><td>aws-bedrock</td><td>AWSBedrockLLM, AWSBedrockEmbedding, AWSBedrockKnowledgeBase</td></tr>
        <tr><td align="left">OpenAI</td><td>openai</td><td>OpenAI, OpenAIEmbedding</td></tr>
        <tr><td align="left">Google GenAI</td><td>google-genai</td><td>GoogleLLM, GoogleEmbedding</td></tr>
        <tr><td align="left">Ollama (preview)</td><td>ollama</td><td>OllamaLLM, OllamaEmbedding</td></tr>
        <tr><td align="left">MCP Client (preview)</td><td>mcp</td><td>Mcp</td></tr>
    </tbody>
</table>

When a 3.x connector is installed alongside AI Fusion, use AI Fusion 3.0.4 or later.
