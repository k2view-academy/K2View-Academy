# Data Product MCP Server: Reference

This article covers the MCP protocol objects exposed by each K2view Fabric Data Product: Resources, built-in and custom Tools, and Prompts.



## MCP Resources

Resources expose LU table metadata. Use the `resources/read` method with `fabric://` URIs:

| Resource URI | Returns |
|---|---|
| `fabric://{data_product}` | Full schema metadata, includes all tables with their descriptions, columns, data types, primary keys, and constraints |
| `fabric://{data_product}/tables` | Table list with names and descriptions |
| `fabric://{data_product}/tables/{table_name}` | Specific table metadata, following [Resource Template](https://modelcontextprotocol.io/specification/2025-03-26/server/resources#resource-templates) mechanism, including the [Completion](https://modelcontextprotocol.io/specification/2025-03-26/server/utilities/completion) capability, where Fabric returns table list options based on partial table name provided by the the client |

**Example request:**

```json
POST https://your-fabric-host.com/mcp/customer_bank/1
Content-Type: application/json

{
  "jsonrpc": "2.0",
  "id": 5,
  "method": "resources/read",
  "params": {
    "uri": "fabric://customer_bank/table/CCMS_Credit_Card_Payments"
  }
}
```

**Example response (markdown format):**
```json
[
  {
    "uri": "fabric://customer_bank",
    "mimeType": "text/markdown",
    "text": "\tName: CCMS_CREDIT_CARD_PAYMENTS\n\n\tDescription: Tracks payments made towards credit card statements\n\n\tColumns:\n\t\t- [PK] PAYMENT_ID: INTEGER NOT NULL - Unique identifier for the payment\n\t\t- CARD_ID: INTEGER - Reference to the credit card for the payment\n\t\t..."
  }
]
```

Requesting a non-existent data product resource (e.g., `fabric://invalid_name`) returns a "Resource not found" error. 

## MCP Tools

### Built-in Tools

Every data product MCP server includes the following built-in tools:

#### query

Execute custom SQL queries against the entity's MicroDB.

| Parameter | Type | Required | Description |
|---|---|---|---|
| `sqlQuery` | string | Yes | SQL query to execute against the LU |

```json
{"name": "query", "arguments": {"sqlQuery": "SELECT * FROM TAX_PAYMENTS"}}
```

Returns a JSON array of result rows. Returns an error message for syntax errors, missing tables, or empty `sqlQuery`.

#### readTable

Read rows from a specific table with optional filtering.

| Parameter | Type | Required | Description |
|---|---|---|---|
| `tableName` | string | Yes | Name of the table to read from |
| `whereClause` | string | No | SQL WHERE condition (without the `WHERE` keyword) |
| `fields` | string | No | Comma-separated list of fields to select (default: `*`) |
| `limit` | integer | No | Maximum number of rows to return (default: 1000, 0 for unlimited) |

```json
{"name": "readTable", "arguments": {"tableName": "ORDERS", "whereClause": "status='ACTIVE'", "fields": "order_id,amount", "limit": 100}}
```

#### describeTables

Describe table columns in the data product. At least one parameter must be provided.

| Parameter | Type | Required | Description |
|---|---|---|---|
| `pattern` | string | No | Pattern with `%` wildcard to filter tables (e.g., `customer%`) |
| `tables` | string | No | Comma-separated list of explicit table names |

```json
{"name": "describeTables", "arguments": {"pattern": "CCMS%"}}
```

#### listTables

Get a list of all tables with their names and descriptions. No parameters required.

```json
{"name": "listTables", "arguments": {}}
```

#### attach

Attach to a specific entity instance for subsequent data operations. When a URL already contains an IID, the URL IID takes precedence.

| Parameter | Type | Required | Description |
|---|---|---|---|
| `iid` | string | Yes | Instance ID to attach to |

```json
{"name": "attach", "arguments": {"iid": "1"}}
```

On success, the response includes:

- `action`: `attached` (new), `updated` (changed IID), or `noop` (URL IID takes precedence)
- `previousIid`: returned when updating from a previous IID

> **NOTE**: If the MCP base URL includes an IID, it cannot be replaced during the session, as this would break the security restrictions intentionally set at the beginning of the session. In such a case you will get: `"URL IID takes precedence over attach tool"`.

---

### Session Variables

Session variables let an MCP client add parameters into a Fabric session that affect how LUI data is retrieved. They map directly to Fabric's native `SET` command mechanism, the same one used throughout Fabric to parameterize LU population logic, Broadway flows, and LU functions.

#### How they work

Before executing a `query` or `readTable` tool call, Fabric translates all stored session variables into `SET` commands and executes them on the Fabric session:

```
SET date_threshold = '2024-01-01'
SET currency = 'USD'
```

This runs before the `GET` command that loads the entity's MicroDB, so the data product's population logic can read those variables — for example, to filter records by date range, apply a currency, or scope results to a tenant.

Only `query` and `readTable` apply session variables before execution. Tools like `listTables`, `describeTables`, and `attach` do not.

#### How to set them

On any request that already carries a `Mcp-Session-Id` header (i.e., after the MCP session has been established), any URL query parameter — except `token` and `iid` — is automatically stored as a session variable:

```
POST /mcp/customer/123?date_threshold=2024-01-01&currency=USD
Mcp-Session-Id: <session-id>
```

Session variables persist for the duration of the session. To remove a variable, pass it again with an empty value.

> **Note:** Query parameters on the initial `initialize` request (before a session ID has been issued) are not stored.

---

### Broadway Flows as Custom Tools

Any Broadway flow can be exposed as an MCP tool by tagging it with the `mcp-tool` tag. When the LU is deployed, tagged flows are automatically registered with:
- Tool name derived from the flow name
- Input schema generated from the flow's parameters (externals)
- Description populated from the flow's metadata

**Lifecycle:**

- Adding the `mcp-tool` tag and deploying registers the flow
- Removing the tag and redeploying unregisters the flow
- On redeploy, new tagged flows are discovered dynamically

This enables extending the MCP server's capabilities with custom business logic — for example, a flow that calculates customer risk score or triggers a notification.

**IID Usage:**

If your Broadway flow tool requires a Fabric `GET` command to read or update the session's attached LUI, and therefore needs the IID, you must define an `iid` external to the flow. Fabric automatically and implicitly provides the IID to the tool flow, meaning the IID is not exposed as a tool argument. This preserves the high level of security and authorization for MCP calls.



## MCP Prompts

**Prompts** provide guided workflows to MCP clients.

### Built-in Prompts

#### queryAssistant prompt 

The built-in `queryAssistant` prompt returns a comprehensive guide for interacting with the data product. It takes no arguments. Its content is generated dynamically on the server side from the current session context (data product name and attached IID):

```json
{"promptName": "queryAssistant", "arguments": []}
```

The `queryAssistant` prompt includes:
- MicroDB explanation - How the Fabric MicroDB works (private SQLite database, data pre-scoped to the entity instance)
- Workflow guide - Step-by-step instructions: (1) discover tables with `listTables`, (2) query data with `readTable` or `query`, (3) use URL query parameters to pass session variables
- Best practices - When to use `readTable` vs. `query`, no need for entity ID filtering, using session variables
- Current context - The data product name and instance ID

### Custom Prompts

Any Broadway flow can be exposed as an MCP prompt by tagging it with the `mcp-prompt` tag.

Their settings, lifecycle, and IID usage are similar to the [custom tools described above](#broadway-flows-as-custom-tools).	
