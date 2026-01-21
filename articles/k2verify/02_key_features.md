# Key Features

- **Comprehensive Mismatch Detection:**
  
  Identifies and reports all types of data discrepancies, including:
  - Value mismatches – Fields with differing values between source and target.
  - Missing records – Records present in the source but missing from the target.
  - Extra records – Records present in the target but not in the source.
  - Failed transformations – Data that was not transformed according to defined rules.
  - PII masking failures – Sensitive fields that are not properly masked in the target system.

- **User-Defined and Configurable Parameters:**
  - Allows users to configure verification parameters and adjust comparison behavior to fit different use cases.

- **Selective Data Comparison:**
  - Enables users to choose which tables, records, and fields to verify, providing flexibility and control.

- **User-Friendly Web-Based Execution:**
  - Supports task creation, execution, and monitoring through a web-based UI, with no need for scripts or command-line operations.

- **Parallel Data Verification:**
  - Improves performance by verifying multiple tables concurrently and processing records in parallel within each table.

- **Post-Transformation Data Comparison:**
  - Verifies data after transformation logic has been applied, ensuring transformations were executed correctly.

- **Extensible Bucket Distribution Logic:**
  - Uses a default method to distribute records across parallel buckets and allows custom logic when needed.

- **Real-Time Execution Monitoring:**
  - Provides visibility into task progress with table-level and bucket-level execution details.

- **Multi-Level Results Summarization:**
  - Displays clear hierarchical results, from high-level summaries to detailed record and field views.

- **Summary Results Export:**
  - Allows exporting results as PDF reports for table summaries and CSV files for record- and field-level analysis.

- **Operational and Historical Tracking:**
  - Stores execution metadata and historical results in PostgreSQL for auditing and analysis