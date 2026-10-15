#  Concurrent Processing of  Table Partitions 

- Starting with **TDM 9.5** onwards, the  performance of table-level tasks has been improved by processing table partitions in parallel, significantly reducing execution time for large tables.

- Each table partition is handled as a **separate LUI** by the task execution batch process for extract or extract & load processes.

## TableLevelDefinitions MTable

The following fields have been added to the **TableLevelDefinitions** MTable to support table concurrency:

- **partition_count_source** - Specifies the number of table partitions. Accepts either a numeric value or the name of a flow that dynamically returns the partition count.

- **partition_records_flow** - Specifies the flow used to retrieve all records for a given table partition.

- **partition_flow_inputs** - Defines the input parameters passed to the partition flows. Must be populated with a valid JSON object in the following format:

    ```json
    {"param_name1": "param_value1", "param_name2": "param_value2"}
    ```
    
    **Examples:**
    
    ```json
    {"columnName": "ALLERGY_ID"}
    ```
    If the partition flow has input parameters, the entire JSON is passed to the **flowInputs** external parameter. The partition flow can then use the relevant parameters from this JSON.

### Table-Level Task Record Sampling – Displaying the Number of Partitions

Starting with TDM 10.1, you can extract a random sample of records from a table, with or without applying filter conditions. Sampling limits the number of records extracted from the table.

When configuring or running a table-level task, specify the number of table partitions to sample and the number of records to extract from each selected partition.

The available number of partitions is retrieved from `TableLevelDefinitions.partition_count_source`. If a Broadway flow is used to calculate the number of partitions for a table, you must enable the flow to display the partition count in the task.

To enable the flow, set the `ENABLE_TABLE_LEVEL_PARTITION_FLOW` parameter in the `TDM_GENERAL_PARAMETERS` TDM DB table to **true**.

If this parameter is not enabled, the partition count will not be displayed in the task, and the task creator/runner will not be able to see the maximum number of partitions available for selection during random sampling.

For more details about record sampling in table-level tasks, see [Record Sampling in Table-Level Tasks](/articles/TDM/tdm_gui/15b_table_subset.md#record-sampling).



## TableLevelDefinitionsInterfacesTypesDefaults MTable

- This table contains **default flows** for the following interface types:

  - PostgreSQL

  - Oracle

- Copy the relevant record from this MTable into the **TableLevelDefinitions MTable** to use the default partitioning flows for these databases.

