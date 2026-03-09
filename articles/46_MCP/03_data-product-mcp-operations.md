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

| Setting | Description | Default |
|---|---|---|
| `ENABLE_MCP_SERVLET` | Enable or disable MCP servlet creation. When set to `false`, no MCP servers are created on Fabric startup. | `true` |
| `MCP_SESSION_CACHE_SIZE` | Maximum number of active MCP sessions held in cache. When exceeded, oldest sessions are evicted (cause: SIZE). | `10000` |
| `MCP_SESSION_EXPIRATION_MINUTES` | Session expiration time in minutes. Idle sessions are automatically removed after this period (cause: EXPIRED). | `30` |

Changes to these settings require a Fabric restart to take effect.



## Observability

Fabric provides observability for MCP server activity.

### Statistics (JMX)

MCP activity is tracked via JMX statistics:

| Metric | Description |
|---|---|
| `mcpActiveSessions` | Number of active MCP sessions |
| `mcpSessionDuration` | Duration of MCP sessions |
| `mcpToolCalls` | Duration and count of MCP tool invocations |
| `mcpToolErrors` | Count of failed MCP tool invocations |
| `mcpResourceReads` | Duration and count of MCP resource reads |
| `mcpPromptRequests` | Duration and count of MCP prompt requests |



## MCP Runtime Behavior

### Server Notifications

Fabric's MCP server sends standard MCP protocol notifications to connected clients when server state changes. Clients will receive these automatically and react accordingly (e.g., refreshing the tool list).

| Notification | When sent | Client behavior |
|---|---|---|
| `notifications/tools/list_changed` | When Broadway tools are added or removed during LU redeployment | Client should re-fetch `tools/list` |

Notifications are only sent for tools. Prompt and resource change notifications are intentionally disabled, so that prompt changes on redeployment are not broadcast to connected clients.

The `tools/list_changed` capability is declared in the server's MCP capabilities on initialization, so compliant clients know to expect it. When an LU is redeployed and tagged Broadway flows change, every `addTool` or `removeTool` operation on an active session triggers this notification to all connected clients.



## Error Handling

MCP separates errors into two layers: **transport-level errors** (HTTP status codes) and **application-level errors** (JSON-RPC responses with `isError: true`). Understanding this distinction is important for building robust client integrations.

### Transport-Level Errors

Transport-level errors indicate that the request did not reach the MCP tool logic. These are returned as standard HTTP error codes with no JSON-RPC body.

| Scenario | HTTP Response |
|---|---|
| No authentication token provided | 401 Unauthorized |
| Invalid or expired token | 401 Unauthorized |
| Unknown or unauthorized data product | 403 Forbidden |
| Insufficient permissions | 403 Forbidden |
| No MCP server registered for the data product | 404 Not Found |
| Malformed JSON-RPC request | 400 Bad Request |

### Application-Level Errors

Application-level errors occur when the HTTP request is valid and the MCP server successfully processes it, but the tool execution itself fails. These always return **HTTP 200 OK** with a JSON-RPC response containing `isError: true`.

| Scenario | Error Message |
|---|---|
| Non-existent tool name | `Unknown tool: {tool_name}` |
| Invalid or missing tool arguments | `Error: {param} parameter is required`, or error with validation details |
| SQL syntax error | `Error executing SQL query: [SQLITE_ERROR] ...` |
| No instance in context | `Error: No instance in context` |
| Non-existent resource URI | `Resource not found` |

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
