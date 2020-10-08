# Fabric Transaction Management

Fabric has a built-in Transaction management mechanism which handles the transaction during the data processing. The transaction handling differs between various data processing modules.

### Sync Process

During a synchronization of an [LU Instance (LUI)](/articles/01_fabric_overview/02_fabric_glossary.md#lui), the data is retrieved from the source systems and loaded into the Fabric database. Both the [LU tables population](/articles/07_table_population/01_table_population_overview.md) and the [enrichment functions](/articles/10_enrichment_function/01_enrichment_function_overview.md) are executed in a [Sync process](/articles/01_fabric_overview/02_fabric_glossary.md#sync). The Sync process is managed as a single transaction that starts at the beginning of the Sync process and finishes at its end. If the Sync is completed successfully, the data is committed to the Fabric database. If however an error occurs at any point of the Sync process, the transaction is rolled back. 

[Click for more information about Sync LUI](/articles/14_sync_LU_instance/01_sync_LUI_overview.md).

### Broadway Flows

[Broadway](/articles/19_Broadway/01_broadway_overview.md) flows can be split into two categories: [an LU table population based on Broadway flow](/articles/07_table_population/14_table_population_based_Broadway.md) and [a regular Broadway flow](/articles/19_Broadway/02a_broadway_flow_overview.md) executed not as part of a population. The transaction is managed differently in each of these categories:

* Broadway population flows are executed during the Sync process thus the data is committed or rolled back as part of the overall Sync process transaction.
* When a regular Broadway flow is executed not as part of a population, it needs to manage their own transaction. 

[Click for more information about Transactions in Broadway flows](/articles/19_Broadway/23_transactions.md).

### Fabric as a Master of Data

Fabric enables updating a specific [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) for the given [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id) in the Fabric database or an entry in the common table instead of synchronizing the entire instance ID or the common table from the source. This functionality enables Fabric to become the master of the data rather than synchronizing the data from external systems.

[Click for more information about Fabric as a master of data](02_fabric_master_of_data.md).



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_fabric_master_of_data.md)



