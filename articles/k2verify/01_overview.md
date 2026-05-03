**Verify** is a data verification library that validates integrity, consistency, and privacy compliance between source and target systems during data movement, migration, platform upgrades, or replication.

It compares data at three levels — **tables/files**, **records**, and **fields** — and is PII-aware, meaning it applies different comparison rules depending on whether data has been masked. Tasks are created and executed through a **web-based GUI** — no scripting or command-line work is needed.

### Core Capabilities

| Capability | Details |
|:---|:---|
| Exact field matching | For non-PII columns |
| Masking validation | For PII-designated fields |
| Parallel execution | Across tables and record partitions |
| Wide datatype support | Including BLOB and CLOB |
| CSV file comparison | Via File System interfaces |
| Multi-level reporting | From execution summary to individual field mismatches |
| Export | PDF (summary), CSV (record/field detail) |

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

