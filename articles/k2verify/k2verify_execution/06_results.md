# k2verify Execution Results

The Results page provides a comprehensive, multi-level view of verification outcomes for a completed k2verify task. It is designed to help users quickly understand overall status, identify problematic tables, and drill down to individual record and field discrepancies.

#### 1. Open the Results page
1. go to the results page
2. click on the execution ID of your task execution
3. Review and analyze the results

#### 2. Review and analyze results

**Execution Summary (Header Section)**:

The top section provides a high-level overview of the task execution:
* Total Tables – Number of table pairs included in the task.
* Failed Tables – Number of tables that did not pass verification.
* Failed % – Percentage of failed validations across the task.
* Start Time / End Time – Execution duration timestamps.
* Download Report – Allows exporting the summary results for offline review.

**Table-Level Summary**:

This section summarizes verification results per source–target table pair.
Displayed columns:
* Source Table / Target Table – Table pair being verified.
* Result – Overall verification result for the table (PASSED / NOT PASSED).
* Start Time / End Time – Execution time window for the table.
* Total Records – Number of records evaluated.
* Records Passed % – Percentage of records that passed verification.
* Records Only in Source – Records found only in the source table.
* Records Only in Target – Records found only in the target table.
* Failed Validation Records – Records with at least one failed field validation.
* Total Fields – Total number of fields evaluated.
* Fields Passed % – Percentage of fields that passed validation.

Clicking a table row (or the row action icon) loads the record-level details for that table.

**Record-Level Details**:

The Record Details section provides granular visibility into individual record and field-level validation results.
Displayed columns:
* Customized Key – Composite business key identifying the record (based on task configuration).
* Column Name – Column being validated.
* Match Result – Validation outcome for the specific field (PASSED / NOT PASSED).
* Source Column Value – Value retrieved from the source system.  
* Target Column Value – Value retrieved from the target system.  

*This section enables*:

* Root-cause analysis of failed validations
* Verification of PII masking behavior
* Identification of schema, data, or transformation issues

**How PII Fields Are Displayed in Results**: 

To protect sensitive data, k2verify never exposes actual PII values in the results view.

Display Rules
  * Source Column Value:
    * Always displayed as "*"

  * Target Column Value:
    * If verification PASSED → Displays the masked value (for example: XXX-XX-1234)
    * If verification FAILED → Displays "*"

*Why This Matters:*

This approach allows you to verify that PII masking was applied correctly while ensuring that sensitive data is never exposed in the UI or exported reports.




![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/resultPage.png)

#### 3. Download the report
Use the Download Report option to export the task results for offline review and deeper analysis.

**Generated File Formats**:

Two file formats are generated as part of the report download:

1. PDF Report
    * Contains the table-level summary of the verification results.
    * Provides a high-level overview suitable for sharing, auditing, and reporting.

2. ZIP Archive
    * Contains CSV files per table for detailed analysis:
      * Record-level summary CSV – Verification results per record.
      * Field-level summary CSV – Verification results per field.
    * These files enable detailed investigation, historical tracking, and external analysis of verification results.