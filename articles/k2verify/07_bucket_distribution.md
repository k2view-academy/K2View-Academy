# How To Use

## Bucket Distribution (Optional)

**What Are Buckets?**:

To improve performance, k2verify splits large tables into smaller groups called buckets and processes them in parallel—similar to dividing a large task among multiple workers.

**Default Bucket Distribution Method**:

By default, k2verify assigns records to buckets based on the table’s key values using a deterministic and evenly distributed method:

- **Single numeric key**
  - The record is assigned to a bucket using:
    ```
    bucketId = keyValue % bucketsNum
    ```

- **Single string key**
  - The key value is hashed, and the record is assigned to a bucket using:
    ```
    bucketId = hash(keyValue) % bucketsNum
    ```

- **Multiple key columns**
  - Key values are concatenated into a single value.
  - The concatenated value is hashed, and the record is assigned to a bucket using:
    ```
    bucketId = hash(concatenatedKeys) % bucketsNum
    ```

**When Should You Customize the Bucket Method?**:

You may consider customizing the bucket distribution logic when you need better control over how records are distributed across buckets.

**When Can You Skip This Section?**:

You can safely skip bucket customization if:
* The default even distribution provides acceptable performance
* You do not experience performance bottlenecks during execution

For most environments, the default bucket distribution is sufficient.

If you wish to implement custom logic for distributing table rows across buckets, follow the steps below.
1. Create a new Broadway flow that will be responsible for assigning rows to buckets.

2. Define Flow Inputs
    * interface – Interface where the table is located.
    * schema – Schema name of the table.
    * table – Table name.
    * bucketsNum – Total number of buckets.
    * bucketID – Identifier of the bucket to which rows should be assigned.
    * interfaceType – Type of the interface (PostgreSQL, Oracle, Cassandra, DB2).
    * customizedKey – List of comparison key columns, separated by the library delimiter.
    * delimiter – Delimiter defined by the library configuration.
    * excludeCondition – Condition defined in the Excluded_Rows_Sql field.

3. Define Flow Output
    * bucketRows – An array of maps representing the rows assigned to the bucket.
    Each map entry uses customizedKey as the key.
    The value is the combined customized key values, separated by the library delimiter. Example output:
    `[
      { "CUSTOMER_ID|SSN": "3|948374937" },
      { "CUSTOMER_ID|SSN": "11|1706304573" },
      { "CUSTOMER_ID|SSN": "18|5458187878" },
      { "CUSTOMER_ID|SSN": "95|6583418038" }
    ]`

4. Update the Buckets_Method value in the configuration MTable to reference the newly created Broadway flow.
