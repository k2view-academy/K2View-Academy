# Partition Distribution (Optional)

> 💡 **You can skip this section** if the default even distribution works for your environment.

Verify splits large tables/files into **partitions** processed in parallel — similar to dividing work across multiple workers. The default method distributes records based on key values:

<table>
  <thead>
    <tr>
      <th align="left">Key Type</th>
      <th align="left">Method</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td valign="top">Single numeric key</td>
      <td valign="top">
        <code>PartitionId = keyValue % PartitionsNum</code>
      </td>
    </tr>
    <tr>
      <td valign="top">Single string key</td>
      <td valign="top">
        <code>PartitionId = hash(keyValue) % PartitionsNum</code>
      </td>
    </tr>
    <tr>
      <td valign="top">Multiple key columns</td>
      <td valign="top">
        Keys concatenated, then
        <code>hash(concatenatedKeys) % PartitionsNum</code>
      </td>
    </tr>
  </tbody>
</table>

**For CSV files:**
- Single file → split into the configured number of partitions.
- Regex file filter → each matched file is treated as a separate unit, each split into partitions.

**To implement custom partition logic:**

1. Create a new Broadway flow.
2. Define the following **External** flow inputs:

	<table>
	  <thead>
		<tr>
		  <th align="left">Input</th>
		  <th align="left">Description</th>
		</tr>
	  </thead>
	  <tbody>
		<tr>
		  <td valign="top"><code>interface</code></td>
		  <td valign="top">Interface where the table is located</td>
		</tr>
		<tr>
		  <td valign="top"><code>schema</code></td>
		  <td valign="top">Schema name</td>
		</tr>
		<tr>
		  <td valign="top"><code>table</code></td>
		  <td valign="top">Table name</td>
		</tr>
		<tr>
		  <td valign="top"><code>PartitionsNum</code></td>
		  <td valign="top">Total number of partitions</td>
		</tr>
		<tr>
		  <td valign="top"><code>PartitionID</code></td>
		  <td valign="top">The partition to which rows should be assigned</td>
		</tr>
		<tr>
		  <td valign="top"><code>interfaceType</code></td>
		  <td valign="top">
			DB type: PostgreSQL, Oracle, Cassandra, DB2, MySQL
		  </td>
		</tr>
		<tr>
		  <td valign="top"><code>customizedKey</code></td>
		  <td valign="top">
			Key columns separated by the library delimiter
		  </td>
		</tr>
		<tr>
		  <td valign="top"><code>delimiter</code></td>
		  <td valign="top">Library delimiter</td>
		</tr>
		<tr>
		  <td valign="top"><code>excludeCondition</code></td>
		  <td valign="top">
			Condition from the <code>Excluded_Rows_Sql</code> field
		  </td>
		</tr>
	  </tbody>
	</table>

3. Define flow output `bucketRows` — an array of maps where each map uses `customizedKey` as the key and the combined key values as the value:
   ```json
   [
     { "CUSTOMER_ID|SSN": "3|948374937" },
     { "CUSTOMER_ID|SSN": "11|1706304573" }
   ]
   ```
4. In **Flow Properties**, tag the flow with `verify_partition_method`.
5. In the Settings page, set `Partitions Assignment Method` for the relevant configuration to this flow.
