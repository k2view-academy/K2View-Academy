# JWT Custom Claims & IID-Based Access Control

## Overview

Fabric extends its JWT-based session tokens to support custom (non-standard) claims, enabling fine-grained authorization throughout the session lifecycle. Combined with a new `READ_WITH_CLAIM` permission, these claims can restrict a session to a single Logical Unit Instance (LUI/IID), so that any attempt to read a different instance is denied.



## Core Concepts

### Internal & Standard vs. Custom Claims

Fabric JWTs have a set of reserved internal claim keys that are always excluded from the custom claims mechanism:

| Claim key  | Meaning                  |
| ---------- | ------------------------ |
| `unm`      | Username                 |
| `bgr`      | Roles/groups             |
| `apk`      | API key                  |
| `authname` | Authenticator name       |
| `authtype` | Authentication type      |
| `authtime` | Authentication timestamp |
| `kid`      | OAuth2 key ID            |

* Any JWT claim not in the list above is treated as a custom claim. 
* Claim keys can be excluded via the `JWT_EXCLUDED_CLAIMS` configuration setting.
* The claims can be set at the JWT by its issuer:
  * An OAuth authorization server which provides the client the JWT that is sent to Fabric.
  * During Signed JWT generation
  * Using Fabric's `/authenticate` endpoint, where initiator can add and declare such claims

### Custom Claims Usage 

As described below, custom claims are used for session's IID-based access restriction as part of Fabric's core capabilities. 

Beyond that, they can also be used in project implementation code by examining the values of the `sessionUser().claims()` map, available at `UserCode`.

**Example:**

The following web service code looks for a claim named `subStatus` and returns `true` if its value is `"VIP"`:

```
  public static boolean isVipUser() throws Exception {
        Map<String, Object> claims = userSessionImp();
        Object subStatus = claims.get("subStatus");
        return "VIP".equals(subStatus);
    }
```
Here is an JWT payload example that match it:
```
{
  "unm":"test",
  "apk": "test-claims",
  "subStatus" :"VIP"
}
```



## Custom Claims Declaration on Session Initiation 

The `POST /authenticate` endpoint accepts an optional `claims` JSON parameter. When provided at login, the caller-supplied claims are merged into the JWT that Fabric issues.

**Endpoint:** `POST /authenticate`

| Parameter  | Type         | Description                                      |
|------------|--------------|--------------------------------------------------|
| `username` | String       | Fabric username                                  |
| `password` | String       | Fabric password                                  |
| `apikey`   | String       | API key (alternative to username/password)       |
| `claims`   | JSON object  | Custom claims to embed in the resulting JWT      |

**Example request:**

```http
POST /authenticate
Content-Type: application/json

{
  "username": "alice",
  "password": "secret",
  "claims": {
    "my-custom-claim-name" : "custom-claim-value"
  }
}
```

**Behavior:**

- Fabric authenticates the requester normally.
- It merges the provided `claims` into the authenticated user context (filtering out any internal claim keys if supplied).
- The issued JWT cookie contains the extra claims.
- On subsequent requests, these injected claims are accessible for implementation user code, for example for access restrictions.
  In addition, when a claim with the special LU claim key prefix is sent, it is later being used by Fabric for IID-based access restrictions.



## IID-Based Access Restriction

### Concept

A new Fabric permission, `READ_WITH_CLAIM`, enables **claim-gated instance access**. When a role carries this permission on a Logical Unit, Fabric verifies that the user's JWT contains a matching claim before allowing access to any instance of that LU.

The claim format is:
```
k2_data_product_<lu_name>: "<instance_id>"
```

For example, if the LU is named `Customer`, the expected claim is:
```json
"k2_data_product_customer": "12345"
```

This ties the JWT bearer to instance `12345` of the `Customer` LU. Any attempt to read a different instance fails.

### Setting up the permission

To grant READ_WITH_CLAIM permission method on a specific LU (.e.g. "customer") to a role (e.g. "customer_viewer"), use the GRANT permission command, for example:
```sql
GRANT READ_WITH_CLAIM ON customer TO customer_viewer;
```

Then ensure that any JWT issued with the `customer_viewer` role includes:
```json
"k2_data_product_customer": "<their_instance_id>"
```



> Notes: 
>
> * You can define more than a single *data product claim*, each for a different LU.
>
> * In case IID-based access restriction shall be applied only on specific LUs, and not on others, then add `READ` permissions on those other LUs. In such case do not use the "*" to apply on all resources.
>   For example: 
>   * "customer" is the main/leading LU and it is restricted by IID, using the claim and `READ_WITH_CLAIM` permission method. 
>   * "orders" LU is also being used, but it is not known ahead on which order user might ask. Accordingly, `READ` permission method shall be applied and granted on "order" resource.
>   




## Configuration

In `config.ini` (section `[fabric]`), you can set 2 attributes:

* Comma-separated list of JWT claim keys to suppress, so that will not be exposed to the `SessionUser` Broadway actor

  ```
  JWT_EXCLUDED_CLAIMS=
  ```

* Prefix for the data-product claims (default: k2_data_product_)

  ```
  DATA_PRODUCT_CLAIM_PREFIX=k2_data_product_
  ```



## MCP & IID-Based Access Restriction

[K2View’s MCP](/articles/46_MCP/01_data-product-mcp-overview.md) server provides IID-based access restriction by default, by specifying the IID in the MCP endpoint URL together with the data product. In this case, the first request that initiates the MCP session registers the data product and IID for that session. All subsequent requests using the same session ID must use the same data product and IID.

The MCP server can also be called without specifying the IID in the endpoint URL. In such cases, if an LUI is required, the `attach` tool must be called first.

The JWT custom claims mechanism is aligned with that concept and provides an additional way to enforce this behavior.

### IID Association by Claims

When a request arrives at an MCP endpoint (`/mcp/<data_product>`) **without** an explicit IID in the URL or query string, Fabric extracts the IID from the user’s JWT claims and set it as the MCP session IID

For example, a client with a JWT containing  `"k2_data_product_customer": "12345"`claim can call `/mcp/customer` without explicitly specifying the IID in the endpoint, because Fabric will resolve it from the token.

### Claims Validation in MCP Requests

If an IID **is specified in the URL**, and the user’s role has the `READ_WITH_CLAIM` permission for the requested data product, Fabric validates that the IID in the JWT claim **matches the IID specified in the URL**.

