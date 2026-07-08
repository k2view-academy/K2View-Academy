# TDM Sequence Implementation Steps

The [TDM task](/articles/TDM/tdm_gui/17a_task_target_component_entities.md#load) provides an option to replace the sequences (IDs) of all selected entities before loading them into the target environment. This is required in order to prevent key duplication when the testing environment is not empty (i.e., already contains entities). In addition, the task execution replaces an entity's sequences when generating clones to ensure uniqueness in the target environment.

Notes:

- Sequence replacement must be set up in advance.
- Sequence implementation is also required for [rule-based data generation flows](16_tdm_data_generation_implementation.md) to maintain referential integrity of synthetic entity IDs.

Starting with TDM V9.3, the following **two sequence handling methods** are supported: 

I. [Sequence handling based on Catalog](11a_tdm_sequence_implementation_based_on_catalog.md).

II. [Sequence handling without Catalog](11b_tdm_sequence_implementation_without_catalog.md). 

## Configuring the Sequence Method

A new **shared Global** variable, **TDM_USING_CATALOG_SEQUENCES**, was introduced in TDM V9.3.

This Global is defined in the following file:

*Implementation/SharedObjects/Java/src/com/k2view/cdbms/usercode/common/TDM/SharedGlobals.java*

It controls the default sequence handling method for all LUs in the project:

- **true** — Use Catalog-based sequences
- **false** — Use sequences without a Catalog

By default, this Global is set to **false**. If you change its value, update the Environments file accordingly, verify that the new value is reflected in the environments, and redeploy the environments.

### Configuring the Sequence Method per LU

You can **override the default behavior for a specific LU** by defining the same Global in the LU’s *Globals.java* file and setting it to the required value.

**Example LU-specific path:**

Implementation/LogicalUnits/Billing/Java/src/com/k2view/cdbms/usercode/lu/Billing/Globals.java

**Example Scenario:**

A TDM project contains the following LUs:

- CRM
- Billing
- Ordering

The project requirement is:

- CRM and Ordering use sequences without a Catalog
- Billing uses Catalog-based sequences

Configuration:

- In SharedGlobals.java: TDM_USING_CATALOG_SEQUENCES = false

- In Billing's Globals.java: TDM_USING_CATALOG_SEQUENCES = true

This configuration sets the default behavior for all LUs to non-Catalog sequences while overriding the behavior for the Billing LU only.

After adding the Global to a specific LU, update the Environments file accordingly, verify that the new value is reflected in the environments, and redeploy the environments.

## Generating Sequence Flows and Actors

Both sequence handling methods require execution of the [TDMLuInitBasedOnFabric flow](05_tdm_lu_implementation_general.md#ii-adding-the-tdm-setup-to-the-lu) to create:

- Sequence flows and Actors — for each record in the [TDMSeqList Actor](11b_tdm_sequence_implementation_without_catalog.md#generate-the-sequence-actors), a pair consisting of a sequence flow and an Actor is created under the Shared Broadway flows.

- Load flows — 

  - Each load flow invokes the **HandleMaskAndSeqFields** flow, which applies masking and sequence replacements to every record prior to loading it into the target environment. The **TDM_USING_CATALOG_SEQUENCES** Global specifies which of the two sequence handling methods is used.

  - For tables with records in the [TDMSeqSrc2TrgMapping Actor](11b_tdm_sequence_implementation_without_catalog.md#populate-the-sequence-mapping-table), a new sequence flow, **[table name]_sequences**, is created under the **Broadway/SequencesFlows** directory of each LU. This flow invokes the sequence Actors for the table's sequence fields. Example:

    ![seq flow example](images/sequence_flow_example.png)

     

- [Rule-based data generation flows](16_tdm_data_generation_implementation.md) — data generation flows with sequence generation added for ensuring consistency and referential integrity.

  

  Note that the input parameter **OVERRIDING_EXISTING_FLOWS** of the [TDMLuInitBasedOnFabric flow](05_tdm_lu_implementation_general.md#ii-adding-the-tdm-setup-to-the-lu) must be set to **true** as a way to properly generate all sequence flows and Actors.

## Data Consistency of Generated Sequences

- A sequence can be shared across tables and multiple LUs. For example, the subscriber_id sequence is shared between the CRM and Billing LUs. To maintain consistenct and referential integrity, the same sequence name must be used across all shared LUs, regardless of the applied sequence handling method.

- The two sequence handling methods — Catalog-based or without Catalog — require creation of the **k2masking** schema, which is done by the TDM deploy flow. Alternatively, creating the k2masking schema can be done by running the **masking-create-cache-table.flow** from the Broadway Examples (found in the Broadway Flow window, Main Menu > Actions > Examples... and select this flow). Before deploying the TDM LU, verify that the **SEQ_CACHE_INTREFACE** shared Global is set with the correct interface name.

  Click [here](/articles/98_installation_and_upgrade/Install_TDM/TDM_Installation_V9.4.md) for more information about the TDM installation and the k2masking schema creation.

## Setting the Sequence Report Global

A new Global — **TDM_SEQ_REPORT** — has been added to the Shared Globals in TDM V8.1. When set to **true** (the default value), the task execution populates the **TDM_SEQ_MAPPING** table and adds the **Replace Sequence Summary Report** to the [task execution report](/articles/TDM/tdm_gui/27_task_execution_history.md#generating-a-task-execution-summary-report).

For better performance, set the **TDM_SEQ_REPORT** Global to **false** to prevent populating the TDM_SEQ_MAPPING table and generating the **Replace Sequence Summary Report**. Note that the Replace Sequence Summary Report would not be available for tasks that are executed with the TDM_SEQ_REPORT Global set to **false**.



[![Previous](/articles/images/Previous.png)](05c_tdm_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdm_implementation_support_hierarchy.md)
