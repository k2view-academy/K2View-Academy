# Installation

## Prerequisites

Before installing Verify, ensure the following requirements are met:

| Requirement | Details |
|:---|:---|
| **Fabric version** | 8.2 or higher (certified on 8.3.4) |
| **PostgreSQL** | Integrated and available in your project (certified on PostgreSQL 17) |

> ⚠️ **Important:** The project must have a working PostgreSQL integration before deploying Verify. This is used to store all operational data and report results.

## Installation
### **Install from K2 Exchange**

1. Open **Fabric Studio Web**.
2. Click the **Extensions** icon in the left sidebar.
3. Search for `Verify`.
4. Click **Install**.

![Install Verify from K2 Exchange](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/verifyInstall.png)

---

### **Post-Installation Validation**

After installation, confirm the following components are present in your Cloud Space:

1. Logical Units
   - `verify`

2. Shared Objects

   - **Broadway:**
      - Folder `k2verify` — contains all Broadway flows provided by the product.

   - **Java:**
      - Resource file `resources/k2verify` — SQL create statements for operational and report tables.
      - Java folder `common/k2verify` — Java logic used by the product.

   - **Interfaces:**

      | Interface | Type | Purpose |
      |:---|:---|:---|
      | `K2VERIFY_OPERATIONAL_DB` | JDBC | Operational tables and reporting data |
      | `K2VERIFY_SRC_CASS_DETAILS` | Other | Cassandra source connection details |
      | `K2VERIFY_TAR_CASS_DETAILS` | Other | Cassandra target connection details |

3. Libraries
   - `lib/postgresql-42.7.10.jar`

### **Web App Integration**

To add Verify to the studio navigation:

1. In your Cloud Studio, open the existing `apps.json` file.
2. Add the following entry to the JSON array:
   ```json
   { "name": "Verify", "appId": "verify" }
   ```
3. Save the file and confirm the JSON structure remains valid.
