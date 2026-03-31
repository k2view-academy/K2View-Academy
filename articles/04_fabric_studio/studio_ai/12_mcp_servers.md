# MCP Servers

Model Context Protocol (MCP) is an open standard that lets AI agents connect to external tools and data sources. By adding an MCP server to Studio AI, you give agents access to new capabilities — querying a database, reading from a document store, calling an API, or integrating with other enterprise systems — without modifying the agents themselves.

## What MCP Servers Enable

An MCP server exposes a set of **tools** that agents can call during a conversation. When an MCP server is connected, its tools appear alongside the built-in agent tools and can be invoked automatically when the agent determines they are relevant.

Examples of what MCP servers can provide:

- **Database access** — query an external data source and return results to the agent
- **Documentation retrieval** — fetch content from an internal knowledge base or wiki
- **API integration** — call an enterprise service (ticketing, monitoring, catalog) and surface results in the chat
- **File system access** — read files from a location the Studio cannot directly access

## Managing MCP Servers

MCP servers are managed in the **MCP Servers** tab of the AI Configuration panel. Open AI Configuration via the icon in the AI Chat panel bar (or **View > AI Configuration**), then select the **MCP Servers** tab.

The tab shows a list of all configured servers. Each entry displays the server name, type, connection details, and status.

### Adding a Server

Click **Add MCP Server**. Fill in the following fields:

| Field | Description |
|---|---|
| **Name** | A display name for the server, shown in the list and in agent responses |
| **Type** | `stdio` for a local process, or `HTTP/SSE` for a remote server |
| **Command** *(stdio only)* | The command to launch the local MCP server process (e.g., `node /path/to/server.js`) |
| **URL** *(HTTP/SSE only)* | The full URL of the remote MCP endpoint |
| **Arguments** *(optional)* | Additional command-line arguments passed to the server process |
| **Environment variables** *(optional)* | Key-value pairs injected into the server process's environment (useful for API keys and secrets) |

Click **Save** to connect the server. Studio AI immediately attempts to connect and lists the tools it discovers.

### Editing a Server

Click the **Edit** button next to any server in the list to modify its configuration. Changes take effect on the next connection attempt.

### Removing a Server

Click **Remove** next to a server to disconnect it and remove it from the list. The tools it provided are immediately unavailable to agents.

## Server Types

### Local stdio Servers

A **stdio** server runs as a child process on the same machine as the Studio. Studio AI launches the process using the command you specify and communicates with it over standard input/output.

This type is suitable for servers that are installed locally — for example, an MCP server distributed as an npm package or a Python script.

**Example configuration:**

- Type: `stdio`
- Command: `node`
- Arguments: `/home/user/.mcp-servers/my-server/index.js`

### Remote HTTP/SSE Servers

An **HTTP/SSE** server runs as a standalone service (typically on your infrastructure or a cloud endpoint) and communicates over HTTP using Server-Sent Events. Studio AI connects to the server by URL.

This type is suitable for shared, centrally managed MCP servers — for example, a company-wide tool gateway that multiple developers connect to.

**Example configuration:**

- Type: `HTTP/SSE`
- URL: `https://mcp.internal.example.com/studio-tools`

## Security Considerations

MCP servers can execute code, access data, and call external services on behalf of agents. When adding a server, consider:

- **Source trust** — only connect to MCP servers from trusted sources. A malicious server can instruct agents to take unintended actions.
- **Scope** — prefer servers with narrowly defined tools over broad-access servers. The principle of least privilege applies here.
- **Credentials** — use environment variables to pass API keys or secrets rather than embedding them in URLs or commands. Environment variable values are stored in your Studio configuration and are not logged.
- **Network access** — HTTP/SSE servers must be reachable from the machine running the Studio. Ensure firewall and proxy settings allow the connection.
