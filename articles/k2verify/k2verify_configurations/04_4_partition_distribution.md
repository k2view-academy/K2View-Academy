# Partition Distribution (Optional)

> 💡 **You can skip this section** if the default even distribution works for your environment.

Verify splits large tables/files into **partitions** processed in parallel — similar to dividing work across multiple workers. The default method distributes records based on key values:

| Key Type | Method |
|:---|:---|
| Single numeric key | `PartitionId = keyValue % PartitionsNum` |
| Single string key | `PartitionId = hash(keyValue) % PartitionsNum` |
| Multiple key columns | Keys concatenated, then `hash(concatenatedKeys) % PartitionsNum` |

**For CSV files:**
- Single file → split into the configured number of partitions.
- Regex file filter → each matched file is treated as a separate unit, each split into partitions.

**To implement custom partition logic:**

1. Create a new Broadway flow.
2. Define the following **External** flow inputs:

   | Input | Description |
   |:---|:---|
   | `interface` | Interface where the table is located |
   | `schema` | Schema name |
   | `table` | Table name |
   | `PartitionsNum` | Total number of partitions |
   | `PartitionID` | The partition to which rows should be assigned |
   | `interfaceType` | DB type: PostgreSQL, Oracle, Cassandra, DB2, MySQL |
   | `customizedKey` | Key columns separated by the library delimiter |
   | `delimiter` | Library delimiter |
   | `excludeCondition` | Condition from the `Excluded_Rows_Sql` field |

3. Define flow output `bucketRows` — an array of maps where each map uses `customizedKey` as the key and the combined key values as the value:
   ```json
   [
     { "CUSTOMER_ID|SSN": "3|948374937" },
     { "CUSTOMER_ID|SSN": "11|1706304573" }
   ]
   ```
4. In **Flow Properties**, tag the flow with `verify_partition_method`.
5. In the Settings page, set `Partitions Assignment Method` for the relevant configuration to this flow.
