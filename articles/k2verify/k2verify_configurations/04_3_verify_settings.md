# Verify Settings

Verify settings define the customized configurations to use for the table or file pairs. All settings are managed from the **Settings** page in the Verify web interface — click **Settings** in the top navigation to get there.

Click **New Configuration** to add a table or file pair. The fields you need to fill in differ slightly depending on whether you're working with database tables (JDBC) or CSV files.

**For database table comparisons (JDBC):**

| Field | Description | Example |
|:---|:---|:---|
| Source Environment | Source environment name | `Production` |
| Source Table Name | Source table to verify | `SRC_CUSTOMER` |
| Source Interface | Interface where the source table lives | `Oracle_PROD` |
| Source Schema | Schema of the source table | `CUSTOMER` |
| Target Environment | Target environment name | `UAT` |
| Target Table Name | Target table to compare against | `TAR_CUSTOMER` |
| Target Interface | Interface where the target table lives | `Oracle_UAT` |
| Target Schema | Schema of the target table | `CUSTOMER` |
| Partition Count | Number of partitions for this table. Set to `Auto` to calculate automatically from table size. | `10` / `Auto` |
| Partitions Assignment Method | Broadway flow responsible for assigning rows to partitions. Defaults to `bwGetBucketRows`. | `bwGetBucketRows` |
| Active | Set to `FALSE` to exclude this rule from comparisons | `TRUE` / `FALSE` |

**For CSV file comparisons:**

| Field | Description | Example |
|:---|:---|:---|
| Source Environment | Source environment name | `Production` |
| Source Interface | Interface for source CSV files | `LocalFS_PROD` |
| Target Environment | Target environment name | `UAT` |
| Target Interface | Interface for target CSV files | `LocalFS_UAT` |
| File Filter | Regex pattern to match files by name | `customerFile.*\.csv` |
| Partition Count | Number of partitions for this table. Set to `Auto` to calculate automatically from table size. | `10` / `Auto` |
| Partitions Assignment Method | Broadway flow responsible for assigning rows to partitions. Defaults to `bwGetBucketRows`. | `bwGetBucketRows` |
| Active | Set to `FALSE` to exclude this rule | `TRUE` / `FALSE` |

You can expand **Advanced Fields** to fine-tune the comparison behavior for each configuration. These apply to both JDBC and CSV unless noted otherwise.

| Field | Description | Example |
|:---|:---|:---|
| Comparison Keys | Pipe-delimited columns used to match source and target records. If empty, primary key columns are resolved from the Catalog. | `customer_id\|ssn` |
| PII Column Names | Pipe-delimited PII columns. If empty, resolved from the Catalog. | `first_name\|ssn` |
| Column Name Mapping | Maps source column names to target names when they differ. | `customer_id:cust_id` |
| Excluded Column Names | Columns to skip during comparison. | `misc_details\|ignore_col` |
| Rows Filter Condition | SQL WHERE condition to limit which records are verified. Not applicable for CSV. | `customer_id > 50` |
| Source Transformation Flow | Broadway flows to transform a source field value before comparison. Format: `column:FlowName`. | `ssn:Mask_SSN` |
| Target Transformation Flow | Broadway flows to transform a target field value before comparison. | `ssn:Mask_SSN` |
| Source Ignore-Null Columns | Columns where a null source value is treated as a match regardless of the target value. | `secondary_phone` |
| Target Ignore-Null Columns | Columns where a null target value is treated as a match regardless of the source value. | `secondary_phone` |

Click **Submit** to save the configuration. To edit an existing configuration, click its edit icon, make your changes, and click **Submit** again. To remove a configuration, click its delete icon.

## Advanced settings

The **Advanced Settings** tab on the Settings page controls global parameters that apply across all tasks. These govern how Verify handles errors and how it sizes partitions.

| Parameter | Category | Description | Range |
|:---|:---|:---|:---|
| `MIN_PROCESSED_THRESHOLD_PCT` | Error handling | Minimum percentage of records that must be processed before other thresholds are evaluated. | 0–100 |
| `MAX_COMPARISON_FAILURE_PCT` | Error handling | Maximum percentage of records allowed to fail comparison before execution stops. | 0–100 |
| `MAX_RECORD_MISMATCH_PCT` | Error handling | Maximum percentage of records allowed to have field-level mismatches before execution stops. | 0–100 |
| `PARTITION_SIZE` | Partitioning | Records per partition. Used to calculate partition count when `DEFAULT_PARTITION_COUNT` is `Auto`. | 0–1,000,000 |
| `DEFAULT_PARTITION_COUNT` | Partitioning | Default partition count per table pair. Set to `Auto` to calculate from `PARTITION_SIZE`, or enter a fixed number. For CSV files, `Auto` defaults to 1. | `Auto` / 1–1,000,000 |