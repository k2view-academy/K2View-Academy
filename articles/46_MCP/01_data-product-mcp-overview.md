# Data Product as MCP Server: Overview

Starting with Fabric 8.4, every Data Product (Logical Unit) deployed is automatically exposed as an MCP (Model Context Protocol) server. This enables any MCP-compatible AI client to interact with the organization's enterprise data through a standardized, secure protocol, without requiring custom MCP integrations per data source.



## Overview

The [Model Context Protocol (MCP)](https://modelcontextprotocol.io/) is an open standard that defines how AI applications communicate with data sources. K2view Fabric leverages its data-product architecture to natively support MCP: each Logical Unit becomes an MCP server upon deployment, exposing its data and services as MCP resources, tools, and prompts.

Since each Logical Unit Instance (LUI) represents a specific business entity (e.g., customer #1, order #5042), the MCP server operates at the entity level — creating an on-the-fly, ad-hoc MCP server per entity instance. This follows Fabric's unique MicroDB architecture, which provides high security and data isolation by design, preventing cross-entity data leakage. In the AI world, this isolation becomes even more critical.

> **Notes:**
>
> * System Logical Units — `k2_ws` and `k2_ref` — are automatically excluded, so that MCP servers are not created for them.
> * While the higher security level and isolation are achieved when the MCP server endpoint is defined at the client with the LUI instance ID (IID) as part of the URL, this is not available in many AI applications and clients — hence the `attach` IID tool can be used.

### Key Concepts

<table>
  <thead>
    <tr>
      <th>Fabric Concept</th>
      <th>MCP Mapping</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Logical Unit (LU/LUT)</td>
      <td>MCP Server</td>
      <td>The data product definition becomes the server</td>
    </tr>
    <tr>
      <td>LU Tables</td>
      <td>MCP Resources</td>
      <td>Table metadata exposed as readable resources (markdown format)</td>
    </tr>
    <tr>
      <td>Broadway Flows (tagged <code>mcp-tool</code>)</td>
      <td>MCP Tools</td>
      <td>Flows tagged with <code>mcp-tool</code> become callable tools</td>
    </tr>
    <tr>
      <td>Broadway Flows (tagged <code>mcp-prompt</code>)</td>
      <td>MCP Prompts</td>
      <td>Flows tagged with <code>mcp-prompt</code> become callable prompts</td>
    </tr>
    <tr>
      <td>Built-in Operations</td>
      <td>MCP Tools</td>
      <td>query, readTable, describeTables, listTables, attach</td>
    </tr>
    <tr>
      <td>Prompts</td>
      <td>MCP Prompts</td>
      <td>Predefined prompts (e.g., <code>queryAssistant</code>)</td>
    </tr>
    <tr>
      <td>LUI (entity instance)</td>
      <td>MCP Session Scope</td>
      <td>Each session is scoped to a specific LU + entity ID</td>
    </tr>
  </tbody>
</table>


## How It Works

### Automatic Server Creation

When a Logical Unit is deployed, Fabric automatically:

1. Creates an MCP server for the LU on each Fabric node (during the LU init process)
2. Registers a servlet with appropriate rewrite rules
3. Publishes the server, making it available to MCP clients

On redeployment, the existing MCP server is updated in-place: Broadway *tools* and *prompts* are dynamically refreshed — newly tagged flows are registered and flows whose tag was removed are unregistered. The server itself and active sessions remain running; clients do not need to reconnect. The 5 built-in tools are unaffected by redeployment.

### Connection Flow

```
MCP Client                    Fabric (MCP Router)              MCP Server
    |                                |                              |
    |-- POST /mcp/customer/1 ------->|                              |
    |   {initialize}                 |-- Authenticate ------------->|
    |                                |-- Authorize (role check) --->|
    |                                |                              |-- Create session
    |                                |                              |   (LU=customer, IID=1)
    |<-- {Mcp-Session-Id=1234} ------|<-----------------------------|
    |                                |                              |
    |-- POST /mcp/customer/1 ------->|                              |
    |   Mcp-Session-Id=1234          |-- Authenticate ------------->|
    |   {tools/call...}              |-- Authorize (role check) --->|
    |                                |-- Route to session node ---->|
    |                                |                              |-- Execute tool
    |<-- {tool response} ------------|<-----------------------------|
```

Each request is authenticated and authorized. The Fabric router identifies the correct cluster node owning the session and routes accordingly.



## Access Patterns

### Base URL

The MCP server uses **Streamable HTTP** transport. The primary URL pattern is:

```
{fabric-url}/mcp/{data_product}/{iid}
```

**Example:**

```
https://your-fabric-host.com/mcp/customer/1
```

A single Fabric server hosts multiple MCP servers (one per deployed LU) and routes incoming requests to the appropriate one.

#### Alternative Access Modes

<table>
  <thead>
    <tr>
      <th>Mode</th>
      <th>URL Pattern</th>
      <th>Notes</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Standard (recommended)</strong></td>
      <td><code>/mcp/{data_product}/{iid}</code></td>
      <td>Entity ID in the URL path — most secure</td>
    </tr>
    <tr>
      <td><strong>IID as query parameter</strong></td>
      <td><code>/mcp/{data_product}?iid={iid}</code></td>
      <td>IID extracted from query param</td>
    </tr>
    <tr>
      <td><strong>Without IID</strong></td>
      <td><code>/mcp/{data_product}</code></td>
      <td>Only tools that don't require an instance will work; entity-scoped operations return errors. To associate an IID, use the <code>attach</code> command with the IID as variable</td>
    </tr>
    <tr>
      <td><strong>Without IID, with JWT claim</strong></td>
      <td><code>/mcp/{data_product}</code><br>JWT claim with patten: <code>k2_data_product_{lu_name}: "{instance_id}"</code></td>
      <td>Fabric extracts the IID from the user’s JWT claims and set it as the MCP session IID. Read <a href="/articles/26_fabric_security/06_jwt-custom-claims-and-iid-access-control.md">here</a> for more information about JWT claims</td>
    </tr>
  </tbody>
</table>

### Discovery Endpoint

`GET /mcp` is a plain HTTP endpoint (**not** an MCP protocol call) that returns a JSON listing of all active MCP servers and their capabilities — useful for discovering available data products before establishing an MCP connection.

```
GET /mcp
```

The response includes, for each data product server:
- `dataProduct` — data product name
- `endpoint` — the MCP protocol URL for this data product (e.g., `/mcp/customer`); this is the URL MCP clients connect to
- `tools` — available tools with names, descriptions, and input schemas
- `resources` — static resources (schema, tables)
- `resourceTemplates` — dynamic resource templates (e.g., per-table metadata)
- `prompts` — available prompts with names, descriptions, and arguments



## Operational Notes

- **Deployment**: MCP servers are created/updated automatically on LU deploy. No manual configuration is required.
- **Excluded LUs**: System LUs (`k2_ws`, `k2_ref`) are automatically filtered out and hence no MCP servers are created for them.
- **Multi-node**: Each Fabric node in the cluster exposes MCP servers. The router handles session affinity.
- **Redeployment**: The MCP server is updated in-place on LU redeploy. Active sessions remain alive and clients do not need to reconnect. Only Broadway tools and prompts are refreshed.
- **Common tables**: Reference/common tables are included in each LU's MCP server, providing access to shared lookup data alongside entity-specific data.
- **Session management**: Sessions are managed with configurable cache size and expiration. Reconnecting clients receive new sessions, and stale sessions are cleaned up.
