#  Concurrent Processing of  Table Partitions 

- Starting with **TDM 9.5** onwards, the  performance of table-level tasks has been improved by processing table partitions in parallel, significantly reducing execution time for large tables.

- Each table partition is handled as a **separate LUI** by the task execution batch process for extract or extract & load processes.

## TableLevelDefinitions MTable

The following fields have been added to the **TableLevelDefinitions** MTable to support table concurrency:

- **partition_count_source** - Specifies the number of table partitions. Accepts either a numeric value or the name of a flow that dynamically returns the partition count.

- **partition_records_flow** - Specifies the flow used to retrieve all records for a given table partition.

- **partition_flow_inputs** - Defines the input parameters passed to the partition flows. Must be populated with a valid JSON object in the following format:

    ```json
    {"param_name": "param_value"}
    ```
    
    **Example:**
    
    ```json
    {"columnName": "ALLERGY_ID"}
    ```

## TableLevelDefinitionsInterfacesTypesDefaults MTable

- This table contains **default flows** for the following interface types:

  - PostgreSQL

  - Oracle

- Copy the relevant record from this MTable into the **TableLevelDefinitions MTable** to use the default partitioning flows for these databases.

