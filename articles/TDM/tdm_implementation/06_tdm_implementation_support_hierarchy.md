# TDM - LU Implementation - Support Hierarchy 

## TDM Relation Tables

To support  hierarchy of LUs and extracting or copying the selected business entities and their related data, the TDM must "know" that are the children entities related to each parent entity.

For example, if the uses asks to copy Customer 1 from Production environment, the TDM must "know" what are the IDs of the Billing Accounts and the Orders of Customer 1 to copy them as well. 

The TDM relation tables keep the link between the parent ID to its children IDs. There are two TDM relation tables in the TDM DB:

1. TDM_LU_TYPE_RELATION_EID.
2. TDM_LU_TYPE_REL_TAR_EID.

### TDM_LU_TYPE_RELATION_EID

#### Overview

This table keeps the link between the parent-child **source IDs**. The relation is kept per source environment. In addition, each [DataFlux](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux) extract task creates separate records in the TDM_LU_TYPE_RELATION_EID table with the version name and version date time of the extracted version of entities.

This table is used to build a list of entities (entity inclusion) on the child LUs when executing a task. In addition this table is used to display the hierarchy of the processed entities by the task execution. 

#### Which Process Populates the TDM_LU_TYPE_RELATION_EID? 

The TDM_TYPE_RELATION_EID is populated by the Sync on the parent LUI:  **fnEnrichmentChildLink** enrichment function is attached to each parent LU, and populates TDM_TYPE_RELATION_EID table.  This function runs on the parent LU the sql queries that are populated in [trnChildLink](/articles/TDM/tdm_implementation/05_tdm_lu_implementation_general.md#trnchildlink) translation to get the child IDs of each parent LUI.

#### TDM_LU_TYPE_RELATION_EID Structure

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="200pxl"><strong>Column Name</strong></td>
<td valign="top" width="770pxl"><strong>Description</strong></td>
</tr>
<tr>
<td valign="top" width="200pxl">source_env</td>
<td valign="top" width="700pxl">The name of the source environment of the TDM extract task.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type_1</td>
<td valign="top" width="700pxl">The name of the parent LU. For example, Customer.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type_2</td>
<td valign="top" width="700pxl">The name of the child LU. For example, Order.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type1_eid</td>
<td valign="top" width="700pxl">The entity id (IID) of the parent LU. For example, 1.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type2_eid</td>
<td valign="top" width="700pxl">The entity id (IID) of the child LU. For example, 12.</td>
</tr>
<tr>
<td valign="top" width="200pxl">creation_date</td>
<td valign="top" width="700pxl">Creation date of the record.</td>
</tr>
<tr>
<td valign="top" width="200pxl">version_name</td>
<td valign="top" width="700pxl">Populated by the task name (version name) of DataFlux extract tasks. On regular extract tasks, this column is populated by an empty String.</td>
</tr>
<tr>
<td valign="top" width="200pxl">version_datetime</td>
<td valign="top" width="700pxl">Populated by the execution time (version DateTime) of DataFlux extract tasks. On regular extract tasks, this&nbsp; column is populated by a default value: 1/1/1970.</td>
</tr>
</tbody>
</table>

**Example1:**

- Customer 1 has Orders 10, 12, and 13 in Production environment. The **LUI** of Customer 1 is **Production_1**.The Sync of **Production_1** LUI of Customer LU populates the TDM_LU_TYPE_RELATION_EID by the following records:

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
<td valign="top" width="150pxl">1/8/2021 13:31</td>
<td valign="top" width="100pxl">&nbsp;</td>
<td valign="top" width="150pxl">1/1/1970 00:00</td>
</tr>
<tr>
<td valign="top" width="100pxl">Production</td>
<td valign="top" width="100pxl">Customer</td>
<td valign="top" width="100pxl">Order</td>
<td valign="top" width="100pxl">1</td>
<td valign="top" width="110pxl">12</td>
<td valign="top" width="150pxl">1/8/2021 13:31</td>
<td valign="top" width="100pxl">&nbsp;</td>
<td valign="top" width="150pxl">1/1/1970 00:00</td>
</tr>
<tr>
<td valign="top" width="100pxl">Production</td>
<td valign="top" width="100pxl">Customer</td>
<td valign="top" width="100pxl">Order</td>
<td valign="top" width="100pxl">1</td>
<td valign="top" width="100pxl">13</td>
<td valign="top" width="150pxl">1/8/2021 13:31</td>
<td valign="top" width="100pxl">&nbsp;</td>
<td valign="top" width="150pxl">1/1/1970 00:00</td>
</tr>
</tbody>
</table>

**Example2:**

- Customer 1 has Orders 10, 12, and 13 in Production environment. The user creates and executes a TDM extract [DataFlux task](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux) to save a version of Customer 1 on Production. The execution time is 8-Jan-2021 at 14:15:30 PM.  The **LUI** of Customer 1 is **Production_1_saveCust1_20210108141530**.
- The Sync of **Production_1_saveCust1_20210108141530** LUI of Customer LU populates the TDM_LU_TYPE_RELATION_EID by the following records:

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

#### Overview

This table keeps the link between the parent-child **target IDs**. The relation is kept per target environment. 

This table is used to build a list of entities (entity inclusion) on the child LUs when executing a delete task. 

#### Which Process Populates the TDM_LU_TYPE_REL_TAR_EID? 

The TDM_TYPE_REL_TAR_EID is populated by [Broadway Flow] of the parent LU: this flow populates the related child IDs on each parent entity before deleting the parent entity  from the target environment. 

#### TDM_LU_TYPE_RELATION_EID Structure

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="200pxl"><strong>Column Name</strong></td>
<td valign="top" width="770pxl"><strong>Column Name</strong></td>
</tr>
<tr>
<td>target_env</td>
<td valign="top" width="700pxl">The name of the target environment of the TDM task.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type_1</td>
<td valign="top" width="700pxl">The name of the parent LU. For example, Customer.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type_2</td>
<td valign="top" width="700pxl">The name of the child LU. For example, Order.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type1_eid</td>
<td valign="top" width="700pxl">The target entity id (IID) of the parent LU. For example, 30.</td>
</tr>
<tr>
<td valign="top" width="200pxl">lu_type2_eid</td>
<td valign="top" width="700pxl">The target entity id (IID) of the child LU. For example, 101.</td>
</tr>
<tr>
<td valign="top" width="200pxl">creation_date</td>
<td valign="top" width="700pxl">Creation date of the record</td>
</tr>
</tbody>
</table>

## Parent LU - Implementation Guidelines 

Although the definition of the Business Entities is implemented in the TDM GUI, you must fulfill the following guidelines to support the parent-child LU hierarchy:

- Populate [trnChildLink](/articles/TDM/tdm_implementation/05_tdm_lu_implementation_general.md#trnchildlink) [translation object](/articles/09_translations/01_translations_overview_and_use_cases.md). Note that a parent LU can have several children LUs. Populate a separate record on each child LU. 
-  Add the **fnEnrichmentChildLink** function as enrichment function (in addition to **fnCheckInsFound**) to the root LU table: FABRIC_TDM_ROOT. The enrichment function runs the sql queries populated in the **trnChildLink** translation, on the LU data an  populates [TDM_LU_TYPE_RELATION_EID](#tdm_lu_type_relation_eid)  table in the TDM DB by the link of the parent IID to its children IIDs.



[![Previous](/articles/images/Previous.png)](05_tdm_lu_implementation_general.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_tdm_implementation_parameters_handling.md)
