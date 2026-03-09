# Data Product MCP Server - Operations Guide

This article covers security, configuration, observability, runtime behavior, and error handling for K2view Fabric MCP servers.



## Security & Authorization

MCP server security follows the same RBAC (Role-Based Access Control) model as Fabric APIs.

### Authentication

Clients authenticate using one of:
- **API Key** — The key maps to assigned roles
- **OAuth** — Token-based authentication with associated roles

  > Note: Fabric OAuth support is currently not RFC 9728 compliant.

Requests without authentication or with invalid/expired tokens receive a `401 Unauthorized` response.

### Authorization

Permissions are controlled at two levels:
- **LU** — Controls access to the entire MCP server (data product)
- **LUI** — Controls access to specific entity instances

> Tool-level permissions are currently not supported.

Users with no permissions on a data product receive `403 Forbidden`. Users with READ-only permissions can perform read operations only. IID-specific permissions restrict access to only the authorized entity instances.

Security profiles can also be applied to mask or anonymize sensitive data in MCP responses, supporting compliance with GDPR, CCPA, and other regulations.

### Data Isolation

Because each MCP session is scoped to a specific LU and IID, data isolation is enforced at the architecture level. The MicroDB contains only data for the attached entity instance. For example, a session connected to `customer/1` cannot access data from `customer/2`, preventing cross-entity data leakage.



## Configuration

MCP server behavior can be controlled via `config.ini` settings:

<table>
  <thead>
    <tr>
      <th>Setting</th>
      <th>Description</th>
      <th>Default</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>ENABLE_MCP_SERVLET</code></td>
      <td>Enable or disable MCP servlet creation. When set to <code>false</code>, no MCP servers are created on Fabric startup.</td>
      <td><code>true</code></td>
    </tr>
    <tr>
      <td><code>MCP_SESSION_CACHE_SIZE</code></td>
      <td>Maximum number of active MCP sessions held in cache. When exceeded, oldest sessions are evicted (cause: SIZE).</td>
      <td><code>10000</code></td>
    </tr>
    <tr>
      <td><code>MCP_SESSION_EXPIRATION_MINUTES</code></td>
      <td>Session expiration time in minutes. Idle sessions are automatically removed after this period (cause: EXPIRED).</td>
      <td><code>30</code></td>
    </tr>
  </tbody>
</table>
Changes to these settings require a Fabric restart to take effect.



## Observability

Fabric provides observability for MCP server activity.

### Statistics (JMX)

MCP activity is tracked via JMX statistics:	

<table>
  <thead>
    <tr>
      <th>Metric</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>mcpActiveSessions</code></td>
      <td>Number of active MCP sessions</td>
    </tr>
    <tr>
      <td><code>mcpSessionDuration</code></td>
      <td>Duration of MCP sessions</td>
    </tr>
    <tr>
      <td><code>mcpToolCalls</code></td>
      <td>Duration and count of MCP tool invocations</td>
    </tr>
    <tr>
      <td><code>mcpToolErrors</code></td>
      <td>Count of failed MCP tool invocations</td>
    </tr>
    <tr>
      <td><code>mcpResourceReads</code></td>
      <td>Duration and count of MCP resource reads</td>
    </tr>
    <tr>
      <td><code>mcpPromptRequests</code></td>
      <td>Duration and count of MCP prompt requests</td>
    </tr>
  </tbody>
</table>


## MCP Runtime Behavior

### Server Notifications

Fabric's MCP server sends standard MCP protocol notifications to connected clients when server state changes. Clients will receive these automatically and react accordingly (e.g., refreshing the tool list).

<table>
  <thead>
    <tr>
      <th>Notification</th>
      <th>When sent</th>
      <th>Client behavior</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>notifications/tools/list_changed</code></td>
      <td>When Broadway tools are added or removed during LU redeployment</td>
      <td>Client should re-fetch <code>tools/list</code></td>
    </tr>
  </tbody>
</table>
Notifications are only sent for tools. Prompt and resource change notifications are intentionally disabled, so that prompt changes on redeployment are not broadcast to connected clients.

The `tools/list_changed` capability is declared in the server's MCP capabilities on initialization, so compliant clients know to expect it. When an LU is redeployed and tagged Broadway flows change, every `addTool` or `removeTool` operation on an active session triggers this notification to all connected clients.



## Error Handling

MCP separates errors into two layers: **transport-level errors** (HTTP status codes) and **application-level errors** (JSON-RPC responses with `isError: true`). Understanding this distinction is important for building robust client integrations.

### Transport-Level Errors

Transport-level errors indicate that the request did not reach the MCP tool logic. These are returned as standard HTTP error codes with no JSON-RPC body.

<table>
  <thead>
    <tr>
      <th>Scenario</th>
      <th>HTTP Response</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>No authentication token provided</td>
      <td>401 Unauthorized</td>
    </tr>
    <tr>
      <td>Invalid or expired token</td>
      <td>401 Unauthorized</td>
    </tr>
    <tr>
      <td>Unknown or unauthorized data product</td>
      <td>403 Forbidden</td>
    </tr>
    <tr>
      <td>Insufficient permissions</td>
      <td>403 Forbidden</td>
    </tr>
    <tr>
      <td>No MCP server registered for the data product</td>
      <td>404 Not Found</td>
    </tr>
    <tr>
      <td>Malformed JSON-RPC request</td>
      <td>400 Bad Request</td>
    </tr>
  </tbody>
</table>
### Application-Level Errors

Application-level errors occur when the HTTP request is valid and the MCP server successfully processes it, but the tool execution itself fails. These always return **HTTP 200 OK** with a JSON-RPC response containing `isError: true`.

<table>
  <thead>
    <tr>
      <th>Scenario</th>
      <th>Error Message</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Non-existent tool name</td>
      <td><code>Unknown tool: {tool_name}</code></td>
    </tr>
    <tr>
      <td>Invalid or missing tool arguments</td>
      <td><code>Error: {param} parameter is required</code>, or error with validation details</td>
    </tr>
    <tr>
      <td>SQL syntax error</td>
      <td><code>Error executing SQL query: [SQLITE_ERROR] ...</code></td>
    </tr>
    <tr>
      <td>No instance in context</td>
      <td><code>Error: No instance in context</code></td>
    </tr>
    <tr>
      <td>Non-existent resource URI</td>
      <td><code>Resource not found</code></td>
    </tr>
  </tbody>
</table>
**Example:** Application-Level Error Response (HTTP 200 OK):

```json
{
  "content": [
    {
      "type": "text",
      "text": "Error reading table: [SQLITE_ERROR] SQL error or missing database (no such table: AAA)"
    }
  ],
  "isError": true
}
```
