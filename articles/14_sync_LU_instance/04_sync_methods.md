# Sync Methods

## Sync Properties
 Sync properties can be defined on an [LU schema](/articles/03_logical_units/03_LU_schema_window.md), [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) and/or [Table Population](/articles/07_table_population/01_table_population_overview.md) level.

**A Sync property contains the following settings:**
<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Timeout (sec)<strong></p>
</td>
<td width="700pxl">
<p>The timeout in seconds for syncing the <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui"> LUI. </a></p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Sync Method<strong></p>
</td>
<td width="500">
<p>None, Time Interval, Inherited and Decision function.</p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Parameters<strong></p>
</td>
<td width="500">
<p>Settings of the selected sync method. For more details see the <a href="/articles/14_sync_LU_instance/04_sync_methods.md#sync-methods-1">Sync Methods section below. &nbsp;</a></p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Delete Instance if Not Exists<strong></p>
</td>
<td width="500">
<p>When marked as True, Fabric deletes the <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui"> LUI </a> if the LUI is not found in the source system.</p>
<p>When marked as False (default), the instance is retained in Fabric even if the instance is not found in the source system.</p>
</td>
</tr>
</tbody>
</table>

[Click for more information about Set Timeout for Sync](/articles/14_sync_LU_instance/08_sync_timeout.md). 

## Sync Methods 
### None 
<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Sync Method<strong></p>
</td>
<td width="700pxl">
<p>None</p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Description<strong></p>
</td>
<td width="500">
<p>Do not sync</p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Parameters<strong></p>
</td>
<td width="500">
<p>N/A</p>
</td>
</tr>
</tbody>
</table>

### Time Interval 
<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Sync Method</strong></p>
</td>
<td width="700pxl">
<p>Time interval.</p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Description</strong></p>
</td>
<td width="500">
<p>Synchronization is implemented automatically after each predefined time interval in order to check whether the latest update of the LU instance in Fabric occurred before the time interval of the specific instance.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; If it has, data is extracted from the source and reloaded to Fabric.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; If it hasn't, the current data in Fabric is used.</p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Parameters</strong></p>
</td>
<td width="500">
<p>Perform sync every:&nbsp;</p>
<p>Format = D.HH:MM:SS.</p>
<p> When set to 00:00:00 - if sync mode is force or on - always sync. <p>
<p>Default = 1 day (1:00:00:00).</p>
</td>
</tr>
</tbody>
</table>

### [Decision Function](/articles/14_sync_LU_instance/05_sync_decision_functions.md)
<table>
<thead>
<tr>
<td width="150pxl">
<p><strong>Sync Method</strong></p>
</td>
<td width="700pxl">
<p>Decision</p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="104">
<p><strong>Description</strong></p>
</td>
<td width="500">
<p>Synchronization is implemented according to the Decision function, which returns a Boolean (True/False) parameter. If the Decision function returns False, do not sync the object.</p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Parameters</strong></p>
</td>
<td width="500">
<p>Decision function: select the Decision function from the Predefined Decision Functions dropdown list.</p>
</td>
</tr>
</tbody>
</table>

### Inherited 
<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Sync Method</strong></p>
</td>
<td width="700pxl">
<p>Inherited.</p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Description</strong></p>
</td>
<td width="500">
<p>Synchronization of each level inherits the sync rule of its direct parent branch according to the following hierarchy:</p>
<p>&middot;&nbsp;&nbsp;&nbsp; LU table inherits from the LU schema.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; Table Population inherits from the LU table.</p>
</td>
</tr>
<tr>
<td width="104">
<p><strong>Parameters</strong></p>
</td>
<td width="500">
<p>N/A</p>
</td>
</tr>
</tbody>
</table>

[Click for more information about LU Sync Levels](/articles/14_sync_LU_instance/07_sync_levels.md)
 
## Delete Mode and Truncate Before Sync properties

There are several ways to define the delete mode of the previous records in the LU table (populated prior to the current sync) before the related LU table populations are executed for this table. Therefore, there is a logical dependency between the delete mode and the sync mode.


### 1. LU Table Settings
The **Delete Mode** property is set on the LU table and defines the delete mode of the previous records in the LU table (populated prior to the current sync). The values are All (default value), Off, or NonUpdated:

 - All - the entire LU table is truncated before the populations are executed.
 - Off - the previous records are not deleted.
 - NonUpdated - delete only the previous records that are not updated by the current sync (old data).

Notes: 
- It is recommended to set the NonUpdated value when the LU table has CDC fields in order to send CDC messages only for the updated records. If the Delete Mode is set to All, Fabric sends delete messages for all the truncated records and inserts messages for the newly inserted records.
- If the Delete Mode is NonUpdated, it is recommended to define a PK on the LU table and set the LU table population mode to Upsert or Update in order to delete only the old data. If the LU table does not have a PK, new records are added to the LU table and all the previous records are deleted.


### 2. LU Table Population Settings

#### 2.1 Table Population Object
A table population object that is [based on a source object](/articles/07_table_population/01_table_population_overview.md) contains the following property: **Truncate Before Sync**.

This property is set on the [LU table population target properties](/articles/07_table_population/04_table_population_properties_tab.md#target-lu-table-properties). The Truncate Before Sync = True is equivalent to the **All** value in the Delete Mode LU table's property.

Notes:

- When the Truncate Before Sync is set on True on a given LU table population, it truncates the entire LU table before populating it. Setting the Truncate Before Sync to True overrides the LU table's Delete Mode. 
- When the Truncate Before Sync is set on False, the delete mode it taken from the LU table. 

#### 2.2. [Table Populations Based on Broadway Flows](/articles/07_table_population/14_table_population_based_Broadway.md)

The following Broadway actor can be added to the flow in order to override the LU table's Delete Mode: 

- XXXXXXXXXX

 
## Sync Methods - Use Cases
<table style="width: 850pxl">
<tbody>
<tr>
<td style="width: 850pxl" colspan="2">
<p><strong>When is a sync method selected?</strong></p>
</td>
</tr>
<tr>
<td style="width: 150pxl">
<p><strong>None</strong></p>
</td>
<td style="width: 700pxl">
<p>The source either does not change, or becomes unavailable, and therefore requires a one-time only load.</p>
<p>After it is loaded, Fabric becomes the System of Record for the data and may get <a href="/articles/23_fabric_transactions/01_fabric_transactions_overview.md#fabric-transactions">update transactions</a> on the data. For example, add a new payment.</p>
</td>
</tr>
<tr>
<td style="width: 155px;">
<p><strong>Time Interval</strong></p>
</td>
<td style="width: 395px;">
<p>Syncs every X times to ensure the data is up to date.</p>
</td>
</tr>
<tr>
<td style="width: 155px;">
<p><strong><a href="/articles/14_sync_LU_instance/05_sync_decision_functions.md">Decision Function &nbsp;</a></strong></p>
</td>
<td style="width: 395px;">
<p>Requires a specific logic to check whether the data needs to be synced from the source.</p>
</td>
</tr>
</tbody>
</table>

### EXAMPLES
<table width="705">
<thead>
<tr>
<td width="150pxl">
<p><strong>Sync Method</strong></p>
</td>
<td width="350pxl">
<p><strong>Example 1</strong></p>
</td>
<td width="350pxl">
<p><strong>Example 2</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="72">
<p><strong>None</strong></p>
</td>
<td width="252">
<p>Load task execution statistics. Once the execution is finished, load its statistics. The statistics are not updated.</p>
</td>
<td width="294">
<p>Get initial Customer data loaded from the Billing system.</p>
<p>Additional payment transactions may then be sent by the Payment Gateway system to update the Payment LUI table for the customer. There is no need to re-load the entire Customer object from the Billing system.</p>
</td>
</tr>
<tr>
<td width="72">
<p><strong>Time Interval</strong></p>
</td>
<td width="252">
<p>Sync the data every day or every week.</p>
</td>
<td width="294">
<p>Sync the data every day.</p>
</td>
</tr>
<tr>
<td width="72">
<p><strong><a href="/articles/14_sync_LU_instance/05_sync_decision_functions.md#decision-functions-for-lui-sync--example-use-cases">Decision Function</strong></a></p>
</td>
<td width="252">
<p>Check the source environment: Do not run a sync on the Production environment. However, if the source environment is a Testing environment, run a sync.</p>
</td>
<td width="294">
<p>An LU that can be populated by different source systems.</p>
<p>Set a different population with a different logic for each table and then check the environment on the decision function of each population to decide whether the population needs to be executed based on the environment.</p>
</td>
</tr>
</tbody>
</table>

[![Previous](/articles/images/Previous.png)](/articles/14_sync_LU_instance/03_sync_ignore_source_exception.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/14_sync_LU_instance/05_sync_decision_functions.md)
