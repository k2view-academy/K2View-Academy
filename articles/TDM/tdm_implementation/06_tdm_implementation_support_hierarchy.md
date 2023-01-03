# TDM LU Implementation - Hierarchy Support 

## TDM Relationship Tables

To support LU hierarchy and extracting or copying selected business entities and their related data, TDM must identify which child entities are related to each parent entity. 

For example, to copy Customer 1 from the Production environment, TDM must identify the Billing Accounts and the Orders of Customer 1. 

TDM relationship tables hold the links between the parent ID and their children IDs. There are two TDM relationship tables in the TDM DB:
- [TDM_LU_TYPE_RELATION_EID](#tdm_lu_type_relation_eid).
- [TDM_LU_TYPE_REL_TAR_EID](#tdm_lu_type_rel_tar_eid).

### TDM_LU_TYPE_RELATION_EID

This table holds the link between the **parent-child source IDs**. The relationship is saved per source environment. In addition, each [Data versioning](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-versioning) extract task creates separate records in the TDM_LU_TYPE_RELATION_EID table with the version name, date and the time of the extracted version of entities.

This table is used for the following:
- [Building the list of entities of child LUs](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#children-lus) when executing TDM tasks on a [BE with a hierarchical structure](/articles/TDM/tdm_overview/03_business_entity_overview.md#task-execution-of-hierarchical-business-entities). The TDM execution process gets the children entities of the parent entities that have been successfully processed by the executed task.
- Displays the hierarchy of the processed entities by the executed task.

#### Which Process Populates the TDM_LU_TYPE_RELATION_EID? 

The TDM_LU_TYPE_RELATION_EID is populated by carrying out a sync on the parent LUI. The **fnEnrichmentChildLink** enrichment function must be attached to the root (FABRIC_TDM_ROOT) or TDM_LU_TYPE_RELATION_EID LU tables of each parent LU. It populates the TDM_LU_TYPE_RELATION_EID table with the parent-child link IDs except for [delete only tasks](/articles/TDM/tdm_gui/19_delete_only_task.md) or [reserve only tasks](/articles/TDM/tdm_gui/20_reserve_only_task.md)  where no data is extracted from the data sources. This function runs on the parent LU. The SQL queries are populated in the [trnChildLink](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnchildlink) translation to get the child IDs of the task's child LUs. The related child IDs are populated on each parent LUI.

#### TDM_LU_TYPE_RELATION_EID Structure

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="200pxl"><strong>Column Name</strong></td>
<td valign="top" width="770pxl"><strong>Description</strong></td>
</tr>
<tr>
<td valign="top" width="200pxl">source_env</td>
<td valign="top" width="700pxl">Name of the source environment of the TDM extract task.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type_1</td>
<td valign="top" width="700pxl">Name of the parent LU. For example, Customer.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type_2</td>
<td valign="top" width="700pxl">Name of the child LU. For example, Order.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type1_eid</td>
<td valign="top" width="700pxl">Entity ID (EID) of the parent LU. For example, 1.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type2_eid</td>
<td valign="top" width="700pxl">Entity ID (EID) of the child LU. For example, 12.</td>
</tr>
<tr>
<td valign="top" width="200pxl">creation_date</td>
<td valign="top" width="700pxl">Creation date of the record.</td>
</tr>
<tr>
<td valign="top" width="200pxl">version_name</td>
<td valign="top" width="700pxl">Populated by the task name (version name) of Data Versioning extract tasks. During regular extract tasks, this column is populated by an empty string.</td>
</tr>
<tr>
<td valign="top" width="200pxl">version_datetime</td>
<td valign="top" width="700pxl">Populated by the execution time (version DateTime) of the Data Versioning extract tasks. During regular extract tasks, this column is populated by a default value: 1/1/1970.</td>
</tr>
</tbody>
</table>

**Example 1:**

Customer 1 has orders 10, 12, and 13 in the Production environment. The **LUI** of Customer 1 is **Production_1**. The Sync of the **Production_1** LUI of the Customer LU populates the TDM_LU_TYPE_RELATION_EID with the following records:

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="100pxl"><strong>source_env</strong></td>
<td valign="top" width="100pxl"><strong>lu_type_1</strong></td>
<td valign="top" width="100pxl"><strong>lu_type_2</strong></td>
<td valign="top" width="100pxl"><strong>lu_type1_eid</strong></td>
<td valign="top" width="100pxl"><strong>lu_type2_eid</strong></td>
<td valign="top" width="150pxl"><strong>creation_date</strong></td>
<td valign="top" width="100pxl"><strong>version_name</strong></td>
<td valign="top" width="150pxl"><strong>version_datetime</strong></td>
</tr>
<tr>
<td valign="top" width="100pxl">Production</td>
<td valign="top" width="100pxl">Customer</td>
<td valign="top" width="100pxl">Order</td>
<td valign="top" width="100pxl">1</td>
<td valign="top" width="100pxl">10</td>
<td valign="top" width="150pxl">1/8/2021 13:31:00</td>
<td valign="top" width="100pxl">&nbsp;</td>
<td valign="top" width="150pxl">1/1/1970 00:00:00</td>
</tr>
<tr>
<td valign="top" width="100pxl">Production</td>
<td valign="top" width="100pxl">Customer</td>
<td valign="top" width="100pxl">Order</td>
<td valign="top" width="100pxl">1</td>
<td valign="top" width="110pxl">12</td>
<td valign="top" width="150pxl">1/8/2021 13:31:00</td>
<td valign="top" width="100pxl">&nbsp;</td>
<td valign="top" width="150pxl">1/1/1970 00:00:00</td>
</tr>
<tr>
<td valign="top" width="100pxl">Production</td>
<td valign="top" width="100pxl">Customer</td>
<td valign="top" width="100pxl">Order</td>
<td valign="top" width="100pxl">1</td>
<td valign="top" width="100pxl">13</td>
<td valign="top" width="150pxl">1/8/2021 13:31:00</td>
<td valign="top" width="100pxl">&nbsp;</td>
<td valign="top" width="150pxl">1/1/1970 00:00:00</td>
</tr>
</tbody>
</table>

**Example 2:**

Customer 1 has orders 10, 12 and 13 in the Production environment. The user creates and executes a TDM extract [Data Versioning task](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux) to save a version of Customer 1 in Production. The execution date and time is 8-Jan-2021 at 14:15:30 PM. The **LUI** of Customer 1 is **Production_1_saveCust1_20210108141530**. The sync of the **Production_1_saveCust1_20210108141530** LUI of the Customer LU populates the TDM_LU_TYPE_RELATION_EID with the following records:

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="100pxl"><strong>source_env</strong></td>
<td valign="top" width="100pxl"><strong>lu_type_1</strong></td>
<td valign="top" width="100pxl"><strong>lu_type_2</strong></td>
<td valign="top" width="100pxl"><strong>lu_type1_eid</strong></td>
<td valign="top" width="100pxl"><strong>lu_type2_eid</strong></td>
<td valign="top" width="150pxl"><strong>creation_date</strong></td>
<td valign="top" width="100pxl"><strong>version_name</strong></td>
<td valign="top" width="150pxl"><strong>version_datetime</strong></td>
</tr>
<tr>
<td valign="top" width="100pxl">Production</td>
<td valign="top" width="100pxl">Customer</td>
<td valign="top" width="100pxl">Order</td>
<td valign="top" width="100pxl">1</td>
<td valign="top" width="100pxl">10</td>
<td valign="top" width="150pxl">1/8/2021 14:15:30</td>
<td valign="top" width="100pxl">saveCust1</td>
<td valign="top" width="150pxl">1/8/2021 14:15:30</td>
</tr>
<tr>
<td valign="top" width="100pxl">Production</td>
<td valign="top" width="100pxl">Customer</td>
<td valign="top" width="100pxl">Order</td>
<td valign="top" width="100pxl">1</td>
<td valign="top" width="100pxl">12</td>
<td valign="top" width="150pxl">1/8/2021 14:15:30</td>
<td valign="top" width="100pxl">saveCust1</td>
<td valign="top" width="150pxl">1/8/2021 14:15:30</td>
</tr>
<tr>
<td valign="top" width="100pxl">Production</td>
<td valign="top" width="100pxl">Customer</td>
<td valign="top" width="100pxl">Order</td>
<td valign="top" width="100pxl">1</td>
<td valign="top" width="100pxl">13</td>
<td valign="top" width="150pxl">1/8/2021 14:15:30</td>
<td valign="top" width="100pxl">saveCust1</td>
<td valign="top" width="150pxl">1/8/2021 14:15:30</td>
</tr>
</tbody>
</table>



### TDM_LU_TYPE_REL_TAR_EID

This table holds the link between the **parent-child target IDs**. The relationship is maintained per target environment. The table is used to [build a list of entities of child LUs](/articles/TDM/tdm_architecture/03a_task_execution_building_entity_list_on_tasks_LUs.md#children-lus) when executing a delete or [Data Versioning](/articles/TDM/tdm_gui/18_load_task_data_versioning_mode.md) load task. 

#### Which Process Populates the TDM_LU_TYPE_REL_TAR_EID? 

The TDM_LU_TYPE_REL_TAR_EID is populated by the sync of the parent LU which populates the related child IDs on each parent entity before deleting the parent entity from the target environment.  The **fnEnrichmentChildLink** enrichment function is attached to the root table of each parent LU and populates the TDM_LU_TYPE_REL_TAR_EID table only if the TDM task deletes entities from the target environment. The SQL queries are populated in the [trnChildLink](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnchildlink) translation and are run on the parent LU to get the target child IDs of the task's child LUs. The related child IDs are populated on each parent LUI.


#### TDM_LU_TYPE_RELATION_EID Structure

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="200pxl"><strong>Column Name</strong></td>
<td valign="top" width="770pxl"><strong>Column Name</strong></td>
</tr>
<tr>
<td>target_env</td>
<td valign="top" width="700pxl">Name of the target environment of the TDM task.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type_1</td>
<td valign="top" width="700pxl">Name of the parent LU. For example, Customer.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type_2</td>
<td valign="top" width="700pxl">Name of the child LU. For example, Order.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type1_eid</td>
<td valign="top" width="700pxl">Target Entity ID (EID) of the parent LU. For example, 30.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type2_eid</td>
<td valign="top" width="700pxl">Target Entity ID (EID) of the child LU. For example, 101.</td>
</tr>
<tr>
<td valign="top" width="200pxl">creation_date</td>
<td valign="top" width="700pxl">Creation date of the record.</td>
</tr>
</tbody>
</table>


## Parent LU - Implementation Guidelines 

Although Business Entities are defined in the TDM GUI, the following guidelines must be implemented to support parent-child LU hierarchy:

- Populate the [trnChildLink](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnchildlink) translation object. Note that a parent LU can have several child LUs. Populate a separate record for each child LU with the SQL queries to select the source and the target child IDs.
-  Add the **fnEnrichmentChildLink** function as an enrichment function to the FABRIC_TDM_ROOT root LU table or TDM_LU_TYPE_RELATION_EID LU table. The enrichment function runs the SQL queries populated in the **trnChildLink** translation on the LU data and populates the [TDM_LU_TYPE_RELATION_EID](#tdm_lu_type_relation_eid) table in the TDM DB using the link of the parent IID to its children IIDs.



[![Previous](/articles/images/Previous.png)](05_tdm_lu_implementation_general.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_tdm_implementation_parameters_handling.md)

