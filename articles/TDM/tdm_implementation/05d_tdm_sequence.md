# TDM Sequence Implementation

## Step 2 - Create Sequences

[Add an explanation about the new inner flow in the load. Rerun of the TDMLuInitBasedOnFabric requires to override exsisting flows in order to generate the missing sequence flows]

It may be required to replace the loaded IDs (sequences) when populating a target database as a way to avoid collision with existing IDs. Setting and initiating sequences is mandatory in order to enable the [IDs' replacement](/articles/TDM/tdm_gui/17a_task_target_component_entities.md#replace-ids-for-the-copied-entities) in TDM tasks.

Fabric V8.2 has added the [**Catalog**'s sequence settings](/articles/39_fabric_catalog/10_catalog_settings.md) feature. TDM 9.3 and onwards supports the following two sequence methods: 

I. [Sequence handling based on Catalog](11a_tdm_sequence_implementation_based_on_catalog.md).

II. [Sequence handling without Catalog](11b_tdm_sequence_implementation_without_catalog.md). 

A new shared Global has been introduced in TDM 9.3, named **TDM_USING_CATALOG_SEQUENCES**. Its purpose is to set the default **sequence handling behavior** of TDM to either Catalog-based sequence or sequence handling without Catalog. This Global (which uses true/false setting) can be **added to an LU** for establishing the **LU's behavior**.

Example:

- The TDM project has the CRM, Billing, and Ordering LUs.
- By default, the sequences are handled without the Catalog, except the Billing LU for which sequences are Catalog-based.
- The TDM_USING_CATALOG_SEQUENCES Global must be set as follows:
  - Shared Global – set to false.
  - Billing LU – set to true.

Notes: 

- A sequence can be shared between multiple LUs. For example, the subscriber_id is shared between the CRM and Billing LUs. In order to ensure referential integrity, all shared LUs must have the same sequence name regardless of their sequence handling method.

- Both of the above-mentioned sequence methods require the creation of the **k2masking** schema. The k2masking schema is created by the TDM deploy.flow. Alternatively, creating the k2masking schema can be done by running the **masking-create-cache-table.flow** from the Broadway Examples (found in the Broadway Flow window, Main Menu > Actions > Examples and select this flow). 

- Starting from Fabric V7.2, SQLite and PostgreSQL are also supported as System DBs. Setting them is done via the [internal_db section](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md#how-to-switch-to-sqlite-or-postgresql) of the Fabric config.ini file. Before deploying the TDM LU, verify that the **SEQ_CACHE_INTREFACE** Shared Global is set with the proper interface. By default, it is populated with **DB_CASSANDRA**. If you wish to create the k2masking schema on a PostgreSQL DB, set a PG DB interface name in the SEQ_CACHE_INTREFACE Global.

### Set the Sequence Report Global

A new Global - **TDM_SEQ_REPORT** - has been added to the Shared Globals in TDM 8.1. When set to **true** (the default value), the task execution populates the **TDM_SEQ_MAPPING** table and adds the **Replace Sequence Summary Report** to the [task execution report](/articles/TDM/tdm_gui/27_task_execution_history.md#generating-a-task-execution-summary-report).

For better performance, set the **TDM_SEQ_REPORT** Global to **false** to prevent populating the TDM_SEQ_MAPPING table and generating the **Replace Sequence Summary Report**. Note that the Replace Sequence Summary Report would not be available for a task that is executed with the TDM_SEQ_REPORT Global set as **false**.