# TDM Sequence Implementation

The [TDM task](/articles/TDM/tdm_gui/17a_task_target_component_entities.md#load) enables the user to replace the sequences (IDs) of all selected entities before loading them into the target environment. This option is required in order to avoid key duplications if the testing environment is not empty and contains entities. The task execution also replaces the entity's sequences (IDs) when generating clones for an entity: the task execution replaces the sequences (IDs) of each replica in order to avoid duplicated sequences in the target environment.

The sequence replacement must be implemented in advance.

The sequence implementation is required for the [rule-based data generation implementation](16_tdm_data_generation_implementation.md) as well in order to keep the referential integrity of the synthetic entity's IDs.

TDM 9.3 and onwards support the following two sequence methods: 

I. [Sequence handling based on Catalog](11a_tdm_sequence_implementation_based_on_catalog.md).

II. [Sequence handling without Catalog](11b_tdm_sequence_implementation_without_catalog.md). 

## How to Set the Sequence Method per LU

A new shared Global has been introduced in TDM 9.3, named **TDM_USING_CATALOG_SEQUENCES**. Its purpose is to set the default **sequence handling behavior** of TDM to either Catalog-based sequence or sequence handling without Catalog. This Global (which uses true/false setting) can be **added to an LU** for establishing the **LU's behavior**.

Example:

- The TDM project has the CRM, Billing, and Ordering LUs.
- By default, the sequences are handled without the Catalog, except for the Billing LU for which sequences are Catalog-based.
- The TDM_USING_CATALOG_SEQUENCES Global must be set as follows:
  - Shared Global – set to false.
  - Billing LU – set to true.

## Generating Sequence Actor and Flows

Both methods of sequence handling require the execution of [TDMLuInitBasedOnFabric flow](05_tdm_lu_implementation_general.md#ii-adding-the-tdm-setup-to-the-lu) in order to create:

- Sequence flows and Actors - a pair of sequence flow and Actor is created under the Shared Broadway flows for each record in the [TDMSeqList Actor](11b_tdm_sequence_implementation_without_catalog.md#generate-the-sequence-actors).

- Load flows - 

  - Each load flow invokes the **HandleMaskAndSeqFields** flow to handle the masking and sequence replacements on each record before loading it to the target environment.  The sequence handling supports either of the sequence methods. The selected sequence method is defined by the **TDM_USING_CATALOG_SEQUENCES** Global.

  - If the table has records in the [TDMSeqSrc2TrgMapping Actor](11b_tdm_sequence_implementation_without_catalog.md#populate-the-sequence-mapping-table) , a new sequence flow is created for the table with the following naming convention: [table name]_sequences. The sequence flows are created in the **Broadway/SequencesFlows** directory for each LU. The sequence flow invokes the sequence Actors for the table's sequence fields. See an example below:

    ![seq flow example](images/sequence_flow_example.png)

     

- [Rule-based data generation flows](16_tdm_data_generation_implementation.md) - adding the relevant sequence IDs to the generated entities in order to keep their referential integrity.

  

  Note that you must set the **OVERRIDING_EXISTING_FLOWS** [TDMLuInitBasedOnFabric flow's](05_tdm_lu_implementation_general.md#ii-adding-the-tdm-setup-to-the-lu) input parameter to **true** in order to generate properly all the sequence flows and actors.

## Data Consistency of Generated Sequences

- A sequence can be shared between tables and multiple LUs. For example, the subscriber_id is shared between the CRM and Billing LUs. In order to ensure referential integrity, all shared LUs must have the same sequence name regardless of their sequence handling method.

- Both of the above-mentioned sequence methods require the creation of the **k2masking** schema. The k2masking schema is created by the TDM deploy flow. Alternatively, creating the k2masking schema can be done by running the **masking-create-cache-table.flow** from the Broadway Examples (found in the Broadway Flow window, Main Menu > Actions > Examples and select this flow). Before deploying the TDM LU, verify that the **SEQ_CACHE_INTREFACE** Shared Global is set with the proper interface name.

  Click [here](/articles/98_installation_and_upgrade/Install_TDM/TDM_Installation_V9.4.md) for more information about the TDM installation and the k2masking schema creation.

## Set the Sequence Report Global

A new Global - **TDM_SEQ_REPORT** - has been added to the Shared Globals in TDM 8.1. When set to **true** (the default value), the task execution populates the **TDM_SEQ_MAPPING** table and adds the **Replace Sequence Summary Report** to the [task execution report](/articles/TDM/tdm_gui/27_task_execution_history.md#generating-a-task-execution-summary-report).

For better performance, set the **TDM_SEQ_REPORT** Global to **false** to prevent populating the TDM_SEQ_MAPPING table and generating the **Replace Sequence Summary Report**. Note that the Replace Sequence Summary Report would not be available for a task that is executed with the TDM_SEQ_REPORT Global set as **false**.
