# Key Features

1. **Comprehensive Mismatch Detection:**

    Identifies and reports:
    - Value mismatches
    - Missing records (in target)
    - Extra records (in target)
    - Failed transformations
    - PII masking failures

2. **Environment-Aware Comparisons:**
  
    Select source and target databases using Fabric-defined environments. Supports comparing interfaces within the same environment or across different environments (e.g., Prod vs. UAT). Each environment includes an indicator to specify whether it contains sensitive data.

3. **PII Comparison Logic:**
  
    Verify applies different comparison rules based on data sensitivity flags:

    * If **both Source and Target contain sensitive data** → values must match exactly → PASSED
    * If **neither contains sensitive data** → values must match exactly → PASSED
    * If **only one contains sensitive data** → target is expected to be masked → PASSED without direct equality check

4. **User-Defined and Configurable Parameters:**
  
    Configure verification parameters and adjust comparison behavior per table, including column mappings, transformation functions, PII columns, excluded columns, and partition settings — all managed through the Settings page.

5. **Selective Data Comparison:**
  
    Choose which tables, records, and fields to verify. Add new table pairs at runtime from a JDBC source or the Catalog, without requiring pre-configured settings.

6. **User-Friendly Web-Based Execution:**
  
    Create, execute, and monitor tasks through a web-based UI with no need for scripts or command-line operations.

7. **Parallel Data Verification:**
  
    Improves performance by verifying multiple tables concurrently and processing records in parallel within each table using configurable partition counts—with support for automatic partition calculation.

8. **Pre-Comparison Transformation:**
  
    Applies predefined rules to source or target data before comparison, accommodating known structural or format differences between systems to ensure accurate validation.

9. **Extensible Partition Distribution Logic:**
  
    Uses a default method to distribute records across parallel partitions and allows custom logic when needed.

10. **Pre/Post Execution Flows:**
  
    Extend verification tasks with configurable Broadway flows that run before and after the core verification logic, without modifying the verification engine.

11. **Enhanced Error Handling:**
  
    A two-tier error model distinguishes physical infrastructure failures (which stop execution immediately) from data/comparison errors (which are evaluated against configurable thresholds).

12. **Retry Failed Partitions:**
  
    Re-execute only the failed partitions of a previous run from the Task History view, without creating a new task execution.

13. **Real-Time Execution Monitoring:**
  
    Provides visibility into task progress with table-level and Partition-level execution details.

14. **Multi-Level Results Summarization:**
  
    Displays clear hierarchical results, from high-level summaries to detailed record and field views.

15. **Summary Results Export:**
  
    Export results as PDF reports for table summaries and CSV files for record- and field-level analysis.

16. **TDM and API Integration:**
  
    Trigger Verify tasks automatically as a TDM post-execution process, or manage tasks programmatically via dedicated API endpoints.

17. **Operational and Historical Tracking:**
  
    Stores execution metadata and historical results in PostgreSQL for auditing and analysis.