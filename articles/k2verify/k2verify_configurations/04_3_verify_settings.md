# Verify Settings

Verify settings define the customized configurations to use for the table or file pairs. All settings are managed from the **Settings** page in the Verify web interface — click **Settings** in the top navigation to get there.

Click **New Configuration** to add a table or file pair. The fields you need to fill in differ slightly depending on whether you're working with database tables (JDBC) or CSV files.

**For database table comparisons (JDBC):**

<table>
  <thead>
    <tr>
      <th align="left">Field</th>
      <th align="left">Description</th>
      <th align="left">Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Source Environment</td>
      <td valign="top">Source environment name</td>
      <td valign="top"><code>Production</code></td>
    </tr>
    <tr>
      <td valign="top">Source Table Name</td>
      <td valign="top">Source table to verify</td>
      <td valign="top"><code>SRC_CUSTOMER</code></td>
    </tr>
    <tr>
      <td valign="top">Source Interface</td>
      <td valign="top">Interface where the source table lives</td>
      <td valign="top"><code>Oracle_PROD</code></td>
    </tr>
    <tr>
      <td valign="top">Source Schema</td>
      <td valign="top">Schema of the source table</td>
      <td valign="top"><code>CUSTOMER</code></td>
    </tr>
    <tr>
      <td valign="top">Target Environment</td>
      <td valign="top">Target environment name</td>
      <td valign="top"><code>UAT</code></td>
    </tr>
    <tr>
      <td valign="top">Target Table Name</td>
      <td valign="top">Target table to compare against</td>
      <td valign="top"><code>TAR_CUSTOMER</code></td>
    </tr>
    <tr>
      <td valign="top">Target Interface</td>
      <td valign="top">Interface where the target table lives</td>
      <td valign="top"><code>Oracle_UAT</code></td>
    </tr>
    <tr>
      <td valign="top">Target Schema</td>
      <td valign="top">Schema of the target table</td>
      <td valign="top"><code>CUSTOMER</code></td>
    </tr>
    <tr>
      <td valign="top">Partition Count</td>
      <td valign="top">
        Number of partitions for this table. Set to <code>Auto</code> to calculate automatically from table size.
      </td>
      <td valign="top"><code>10</code> / <code>Auto</code></td>
    </tr>
    <tr>
      <td valign="top">Partitions Assignment Method</td>
      <td valign="top">
        Broadway flow responsible for assigning rows to partitions. Defaults to <code>bwGetBucketRows</code>.
      </td>
      <td valign="top"><code>bwGetBucketRows</code></td>
    </tr>
    <tr>
      <td valign="top">Active</td>
      <td valign="top">Set to <code>FALSE</code> to exclude this rule from comparisons</td>
      <td valign="top"><code>TRUE</code> / <code>FALSE</code></td>
    </tr>
  </tbody>
</table>

**For CSV file comparisons:**

<table>
  <thead>
    <tr>
      <th align="left">Field</th>
      <th align="left">Description</th>
      <th align="left">Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Source Environment</td>
      <td valign="top">Source environment name</td>
      <td valign="top"><code>Production</code></td>
    </tr>
    <tr>
      <td valign="top">Source Interface</td>
      <td valign="top">Interface for source CSV files</td>
      <td valign="top"><code>LocalFS_PROD</code></td>
    </tr>
    <tr>
      <td valign="top">Target Environment</td>
      <td valign="top">Target environment name</td>
      <td valign="top"><code>UAT</code></td>
    </tr>
    <tr>
      <td valign="top">Target Interface</td>
      <td valign="top">Interface for target CSV files</td>
      <td valign="top"><code>LocalFS_UAT</code></td>
    </tr>
    <tr>
      <td valign="top">File Filter</td>
      <td valign="top">Regex pattern to match files by name</td>
      <td valign="top"><code>customerFile.*\.csv</code></td>
    </tr>
    <tr>
      <td valign="top">Partition Count</td>
      <td valign="top">
        Number of partitions for this table. Set to <code>Auto</code> to calculate automatically from table size.
      </td>
      <td valign="top"><code>10</code> / <code>Auto</code></td>
    </tr>
    <tr>
      <td valign="top">Partitions Assignment Method</td>
      <td valign="top">
        Broadway flow responsible for assigning rows to partitions. Defaults to <code>bwGetBucketRows</code>.
      </td>
      <td valign="top"><code>bwGetBucketRows</code></td>
    </tr>
    <tr>
      <td valign="top">Active</td>
      <td valign="top">Set to <code>FALSE</code> to exclude this rule</td>
      <td valign="top"><code>TRUE</code> / <code>FALSE</code></td>
    </tr>
  </tbody>
</table>

You can expand **Advanced Fields** to fine-tune the comparison behavior for each configuration. These apply to both JDBC and CSV unless noted otherwise.

<table>
  <thead>
    <tr>
      <th align="left">Field</th>
      <th align="left">Description</th>
      <th align="left">Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Comparison Keys</td>
      <td valign="top">
        Pipe-delimited columns used to match source and target records.
        If empty, primary key columns are resolved from the Catalog.
      </td>
      <td valign="top"><code>customer_id|ssn</code></td>
    </tr>
    <tr>
      <td valign="top">PII Column Names</td>
      <td valign="top">
        Pipe-delimited PII columns. If empty, resolved from the Catalog.
      </td>
      <td valign="top"><code>first_name|ssn</code></td>
    </tr>
    <tr>
      <td valign="top">Column Name Mapping</td>
      <td valign="top">
        Maps source column names to target names when they differ.
      </td>
      <td valign="top"><code>customer_id:cust_id</code></td>
    </tr>
    <tr>
      <td valign="top">Excluded Column Names</td>
      <td valign="top">
        Columns to skip during comparison.
      </td>
      <td valign="top"><code>misc_details|ignore_col</code></td>
    </tr>
    <tr>
      <td valign="top">Rows Filter Condition</td>
      <td valign="top">
        SQL WHERE condition to limit which records are verified.
        Not applicable for CSV.
      </td>
      <td valign="top"><code>customer_id &gt; 50</code></td>
    </tr>
    <tr>
      <td valign="top">Source Transformation Flow</td>
      <td valign="top">
        Broadway flows to transform a source field value before comparison.
        Format: <code>column:FlowName</code>.
      </td>
      <td valign="top"><code>ssn:Mask_SSN</code></td>
    </tr>
    <tr>
      <td valign="top">Target Transformation Flow</td>
      <td valign="top">
        Broadway flows to transform a target field value before comparison.
      </td>
      <td valign="top"><code>ssn:Mask_SSN</code></td>
    </tr>
    <tr>
      <td valign="top">Source Ignore-Null Columns</td>
      <td valign="top">
        Columns where a null source value is treated as a match regardless of the target value.
      </td>
      <td valign="top"><code>secondary_phone</code></td>
    </tr>
    <tr>
      <td valign="top">Target Ignore-Null Columns</td>
      <td valign="top">
        Columns where a null target value is treated as a match regardless of the source value.
      </td>
      <td valign="top"><code>secondary_phone</code></td>
    </tr>
  </tbody>
</table>

Click **Submit** to save the configuration. To edit an existing configuration, click its edit icon, make your changes, and click **Submit** again. To remove a configuration, click its delete icon.

## Advanced settings

The **Advanced Settings** tab on the Settings page controls global parameters that apply across all tasks. These govern how Verify handles errors and how it sizes partitions.

<table>
  <thead>
    <tr>
      <th align="left">Parameter</th>
      <th align="left">Category</th>
      <th align="left">Description</th>
      <th align="left">Range</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top"><code>MIN_PROCESSED_THRESHOLD_PCT</code></td>
      <td valign="top">Error handling</td>
      <td valign="top">
        Minimum percentage of records that must be processed before other thresholds are evaluated.
      </td>
      <td valign="top">0–100</td>
    </tr>
    <tr>
      <td valign="top"><code>MAX_COMPARISON_FAILURE_PCT</code></td>
      <td valign="top">Error handling</td>
      <td valign="top">
        Maximum percentage of records allowed to fail comparison before execution stops.
      </td>
      <td valign="top">0–100</td>
    </tr>
    <tr>
      <td valign="top"><code>MAX_RECORD_MISMATCH_PCT</code></td>
      <td valign="top">Error handling</td>
      <td valign="top">
        Maximum percentage of records allowed to have field-level mismatches before execution stops.
      </td>
      <td valign="top">0–100</td>
    </tr>
    <tr>
      <td valign="top"><code>PARTITION_SIZE</code></td>
      <td valign="top">Partitioning</td>
      <td valign="top">
        Records per partition. Used to calculate partition count when
        <code>DEFAULT_PARTITION_COUNT</code> is <code>Auto</code>.
      </td>
      <td valign="top">0–1,000,000</td>
    </tr>
    <tr>
      <td valign="top"><code>DEFAULT_PARTITION_COUNT</code></td>
      <td valign="top">Partitioning</td>
      <td valign="top">
        Default partition count per table pair. Set to <code>Auto</code> to calculate from
        <code>PARTITION_SIZE</code>, or enter a fixed number.
        For CSV files, <code>Auto</code> defaults to 1.
      </td>
      <td valign="top"><code>Auto</code> / 1–1,000,000</td>
    </tr>
  </tbody>
</table>