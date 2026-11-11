# AI Versions Table

This article lists the versions of the K2view AI extensions that are installed from K2exchange. Studio AI itself, the AI assistant in the Web Studio, is part of Fabric and is released with the Fabric versions (see [Fabric Versions](/articles/Product_Versions/Fabric_versions.md)); only its skills and reference content are delivered as extensions.

The extensions are listed in three groups:

- **AI Fusion** (GenAI Data Fusion) - the runtime for building and running AI agents on Fabric, and the **AI Fusion Studio** extension that adds the Studio experience for managing AI Fusion agents.
- **Studio AI extensions** - skills and reference content that Studio AI uses to help implementors build Fabric projects. They are not part of AI Fusion.
- **AI Connectors** - the LLM and MCP connectors, shared by AI Fusion and other Fabric AI features.

For the TDM AI agent, see [TDM Versions](/articles/Product_Versions/TDM_versions.md).

Latest Release and Released Date are the versions published on K2exchange. Click a version to open its release notes. Please consult with us regarding the latest available and compatible Fabric version.



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
            <td><a href="/articles/AI_fusion/release_notes/aifusion_3.md#v3.0.10">3.0.10</a></td>
            <td>Nov 9, 2026</td>
            <td>8.5.0; 8.5.2 recommended</td>
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




### AI Fusion Related Modules and Extensions

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
            <td><a href="/articles/AI_fusion/release_notes/aifusion_studio.md#v1.0.3">1.0.3</a></td>
            <td>Nov 9, 2026</td>
            <td>8.5.2</td>
            <td>3.0.10</td>
            <td>Studio extension that provides the UI for managing AI Fusion agents: AI Explorer, Agent Editor, Preview and Trace, Eval Editor, AI-assisted agent building.</td>
        </tr>
        <tr>
            <td align="left">Pipeline</td>
            <td>pipeline</td>
            <td>3.0.7</td>
            <td>Aug 25, 2026</td>
            <td></td>
            <td>3.x</td>
            <td>Pipeline framework used to run evaluations analysis. Required for evaluations.</td>
        </tr>
    </tbody>
</table>




## Studio AI Extensions

Studio AI is built into the Fabric Web Studio; from Fabric 8.5.2 its default agents (**@k2-assistant** and **@k2-worker**) are part of the Studio. The extensions below add the Fabric skills and the reference content that the skills use. They are not related to AI Fusion. See [Getting Started with Studio AI](/articles/04_fabric_studio/studio_ai/01_getting_started_with_studio_ai.md) and [Skills and Slash Commands](/articles/04_fabric_studio/studio_ai/11_skills_and_slash_commands.md).

<table>
    <thead>
        <tr>
            <th align="left">Module</th>
            <th>Extension</th>
            <th>Latest Release</th>
            <th>Released Date</th>
            <th>Minimum Fabric Version</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td align="left">Studio AI Core Artifacts</td>
            <td>studio-ai-core-artifacts</td>
            <td>1.5.0</td>
            <td>Nov 2, 2026</td>
            <td>8.4.7</td>
            <td>The core Fabric skills for Studio AI (and for Claude Code, through the <strong>Init Claude Skills</strong> command): Broadway flows and actors, Logical Units, interfaces, web services, project helpers, troubleshooting, report and web app builders, security review and more. Before Fabric 8.5.2 it also adds the <strong>@k2-assistant</strong> and <strong>@k2-worker</strong> agents. Installs Studio AI Fabric Wiki as a dependency.</td>
        </tr>
        <tr>
            <td align="left">Studio AI Fabric Wiki</td>
            <td>studio-ai-fabric-wiki</td>
            <td>1.0.4</td>
            <td>Oct 20, 2026</td>
            <td>8.4.4</td>
            <td>Imports the Fabric Wiki into the project's <code>.fabric-wiki/</code> folder: the Broadway actors catalog, example Broadway flows and the Fabric Java API reference. The core skills use it as grounded reference content. Version 1.0.4 is generated from Fabric 8.5.3.</td>
        </tr>
        <tr>
            <td align="left">Studio AI Masking Skill</td>
            <td>studio-ai-masking-skill</td>
            <td>1.0.0</td>
            <td>Sep 14, 2026</td>
            <td>8.4.7</td>
            <td>A Studio AI skill for data masking: analysis, implementation, validation and troubleshooting, based on the project's requirements and existing configuration. Installs Studio AI Core Artifacts as a dependency.</td>
        </tr>
        <tr>
            <td align="left">Studio AI Migration Skills</td>
            <td>studio-ai-migration-skills</td>
            <td>1.3.0</td>
            <td>Nov 1, 2026</td>
            <td>8.4.4</td>
            <td>Studio AI skills that automate the data-transformation layer of a Fabric migration. Installs Studio AI Core Artifacts as a dependency.</td>
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
            <th>Latest Release</th>
            <th>Released Date</th>
            <th>Minimum Fabric Version</th>
            <th>Interface Types</th>
        </tr>
    </thead>
    <tbody>
        <tr><td align="left">Anthropic</td><td>anthropic</td><td>3.1.1</td><td>Nov 10, 2026</td><td>8.4.0</td><td>Anthropic</td></tr>
        <tr><td align="left">AWS Bedrock</td><td>aws-bedrock</td><td>3.0.3</td><td>Nov 1, 2026</td><td>8.4.0</td><td>AWSBedrockLLM, AWSBedrockEmbedding, AWSBedrockKnowledgeBase</td></tr>
        <tr><td align="left">OpenAI</td><td>openai</td><td>3.1.0</td><td>Oct 19, 2026</td><td>8.4.9</td><td>OpenAI (OpenAI or Azure OpenAI), OpenAIEmbedding</td></tr>
        <tr><td align="left">Google GenAI</td><td>google-genai</td><td>3.0.0</td><td>Aug 23, 2026</td><td>8.4.0</td><td>GoogleLLM, GoogleEmbedding</td></tr>
        <tr><td align="left">Ollama (preview)</td><td>ollama</td><td>Not on K2exchange</td><td></td><td>8.4.0</td><td>OllamaLLM, OllamaEmbedding</td></tr>
        <tr><td align="left">MCP Client (preview)</td><td>mcp</td><td>1.0.1</td><td>Apr 9, 2026</td><td>8.3.0</td><td>Mcp</td></tr>
    </tbody>
</table>

Connector notes:

- When a 3.x connector is installed alongside AI Fusion, use AI Fusion 3.0.4 or later.
- The `xhigh` and `max` thinking levels require anthropic 3.0.3 or later, and aws-bedrock, openai and google-genai 3.0.1 or later (google-genai 3.0.1 is not yet on K2exchange).
- Claude on Microsoft Foundry requires anthropic 3.1.0 or later. Taking the Anthropic API key from Fabric's secret manager requires anthropic 3.1.1. Azure OpenAI, the Chat Completions API option and OAuth 2.0 client-credentials authentication require openai 3.1.0.
