**Verify** is a data verification library that validates integrity, consistency, and privacy compliance between source and target systems during data movement, migration, platform upgrades, or replication.

It compares data at three levels — **tables/files**, **records**, and **fields** — and is PII-aware, meaning it applies different comparison rules depending on whether data has been masked. Tasks are created and executed through a **web-based GUI** — no scripting or command-line work is needed.

### Core Capabilities

<table>
  <thead>
    <tr>
      <th align="left">Capability</th>
      <th align="left">Details</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Exact field matching</td>
      <td valign="top">For non-PII columns</td>
    </tr>
    <tr>
      <td valign="top">Masking validation</td>
      <td valign="top">For PII-designated fields</td>
    </tr>
    <tr>
      <td valign="top">Parallel execution</td>
      <td valign="top">Across tables and record partitions</td>
    </tr>
    <tr>
      <td valign="top">Wide datatype support</td>
      <td valign="top">Including BLOB and CLOB</td>
    </tr>
    <tr>
      <td valign="top">CSV file comparison</td>
      <td valign="top">Via File System interfaces</td>
    </tr>
    <tr>
      <td valign="top">Multi-level reporting</td>
      <td valign="top">From execution summary to individual field mismatches</td>
    </tr>
    <tr>
      <td valign="top">Export</td>
      <td valign="top">PDF (summary), CSV (record/field detail)</td>
    </tr>
  </tbody>
</table>

### What Verify Detects

- Value mismatches
- Missing records (present in source, absent in target)
- Extra records (present in target, absent in source)
- Failed transformations
- PII masking failures

### PII Comparison Logic

| Source Sensitive | Target Sensitive | Expected Result |
|:---:|:---:|:---|
| ✅ | ✅ | Values must match exactly → **PASSED** |
| ❌ | ❌ | Values must match exactly → **PASSED** |
| ✅ | ❌ | Target is expected to be masked → **PASSED** without direct equality check |
| ❌ | ✅ | Source is expected to be masked → **PASSED** without direct equality check |

### Verify Process Architecture

![Verify Execution Flow](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/VerifyArchitecture.png)

