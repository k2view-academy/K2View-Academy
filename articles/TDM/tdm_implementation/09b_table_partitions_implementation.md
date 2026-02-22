#  Concurrency Processing of  Table Partitions 

- Starting with **TDM 9.5** onwards, the  performance of table-level tasks has been improved by processing table partitions in parallel, significantly reducing execution time for large tables.

- Each table partition is handled as a **separate LUI** by the task execution batch process for extract or extract & load processes.

  ## TableLevelDefinitions MTable 

- The following fields have been added to **TableLevelDefinitions** to support table concurrency:

  - **partition_count_source** - the number of the table's partitions. Populated with a numeric value or a name of the flow that returns the number of table partitions. 
  - **partition_records_flow** - populated with the flow that returns all table records for a given partition.

### TableLevelDefinitions MTable  - Default Records

TableLevelDefinitionsInterfacesTypesDefaults