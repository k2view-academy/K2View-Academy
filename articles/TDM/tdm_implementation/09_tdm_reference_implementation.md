# TDM - Reference Implementation

<a href="https://www.k2view.com/products/test-data-management/" target="_blank">TDM</a> enables users to extract Reference tables from several source environments and to save them into the Cassandra DB. It also enables saving different versions of a Reference table and source environment into the Cassandra DB and creating and executing TDM load tasks to copy them from the Cassandra DB into the target environment. 

Note that the TDM Reference solution is not based on [Fabric Reference tables](/articles/22_reference(commonDB)_tables/01_fabric_commonDB_overview.md) and that the tables are saved in Cassandra. 

A TDM implementation has the following steps:

### Step 1 - Populate trnRefList Translation

The list of Reference tables available for [TDM extract tasks](/articles/TDM/tdm_gui/24_task_reference_tab.md#reference-tab---extract-task) is populated in the [trnRefList](04_fabric_tdm_library.md#trnreflist) translation object. Populate trnRefList with the list of available Reference tables for each LU. The following settings must be populated for each record:

- **lu_name**, populated by the LU name.
- **ID**, populated by a sequence.
- **reference_table_name**, populated by the Reference table.
- **schema_name**, populated by the source DB schema name that stores the Reference table.
- **interface_name**, the Reference table's source interface.
- **target_schema_name**, populated by the target DB Schema's name that stores the Reference table.
- **target_interface_name**, name of the Reference table's target interface. 

Example:

<table width="900pxl">
<thead>
<tr>
<td colspan="2" width="150pxl">
<p><strong>Input</strong></p>
</td>
<td colspan="5" width="750pxl">
<p><strong>Output</strong></p>
</td>
</tr>
<tr>
<td width="100pxl">
<p><strong>lu_name</strong></p>
</td>
<td width="50pxl">
<p><strong>ID</strong></p>
</td>
<td width="150pxl">
<p><strong>reference_table_name</strong></p>
</td>
<td width="150pxl">
<p><strong>schema_name</strong></p>
</td>
<td width="150pxl">
<p><strong>interface_name</strong></p>
</td>
<td width="150pxl">
<p><strong>target_schema_name</strong></p>
</td> 
<td width="150pxl">
<p><strong>target_interface_name</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="127">
<p>Customer</p>
</td>
<td width="42">
<p>1</p>
</td>
<td width="199">CUSTOMER_TYPE</td>
<td width="124">CUST</td>
<td width="112">CRM_DB</td>
<td width="124">TAR_CUST</td>
<td width="112">CRM_DB</td>    
</tr>
<tr>
<td width="127">
<p>Customer</p>
</td>
<td width="42">
<p>2</p>
</td>
<td width="199">ADDRESS_ZIP_CODES</td>
<td width="124">CUST</td>
<td width="112">CRM_DB</td>
<td width="124">TAR_CUST</td>
<td width="112">CRM_DB</td>     
</tr>
<tr>
<td width="127">
<p>Billing</p>
</td>
<td width="42">
<p>3</p>
</td>
<td width="199">BILL_TYPE</td>
<td width="124">BILL</td>
<td width="112">BILLING_DB</td>
<td width="124">TAR_BILL</td>
<td width="112">BILLING_DB</td>    
</tr>
</tbody>
</table> 
Note that is it also possible to populate the list of target's PK fields in trnRefList. These fields can be later used to customized the load flow to run an Upsert on the target reference table.

### Step 2 - Creating Cassandra Tables for Reference Tables

Run the [fnValidateAndRebuildRefTables](/articles/TDM/tdm_architecture/05_tdm_reference_processes.md#tdm-lu---fnvalidateandrebuildreftables-job) job to create an empty Cassandra table for each Reference table in **trnRefList**. The tables are created in the **k2view_tdm** keyspace.

### Step 3 - Create and Execute TDM Extract Tasks

TDM extract tasks store the selected Reference data in the related Cassandra DB table.

Click for more information on how the [TDM stores Reference tables in the Cassandra DB](/articles/TDM/tdm_architecture/05_tdm_reference_processes.md#reference-cassandra-table).

### Step 4 - Create and Execute TDM Load Tasks

The TDM GUI enables creating [TDM load tasks](/articles/TDM/tdm_gui/24_task_reference_tab.md#reference-tab---load-task) to copy Reference tables that have been successfully extracted into the Cassandra DB. The execution of a load task runs the **TDMReferenceLoader** Broadway flow to copy the task's reference tables to the target environment.

Click for more information about generic [TDM Broadway flows](10_tdm_generic_broadway_flows.md).

[![Previous](/articles/images/Previous.png)](08_tdm_implement_delete_of_entities.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_tdm_generic_broadway_flows.md)





