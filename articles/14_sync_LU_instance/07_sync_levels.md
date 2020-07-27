# Sync Method Levels

### What Are Sync Method Levels?

[Sync properties](/articles/14_sync_LU_instance/04_sync_methods.md) can be defined on [LU schema,](/articles/03_logical_units/03_LU_schema_window.md) [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) or [Table Population](/articles/07_table_population/01_table_population_overview.md) levels. By default, LU tables, Table Population objects and Enrichment functions inherit the sync properties defined for the LU schema.\
The sync properties of an LU schema can be overridden, as follows:
* Setting sync properties on the LU Table. By default, the related Table Population objects and the Enrichment functions attached to the LU table inherit the sync properties of the LU table.
* Setting sync properties on each Table Population object. 

![image](/articles/14_sync_LU_instance/images/6_6_1_sync_levels.png)

## Overriding Sync Properties in an LU Schema - Use Cases

You may need to override the LU schema's sync properties on LU tables or Table Populations that require specific handling. The following table describes use cases for overriding the sync properties on each object:
* LU Table.
* Table Population.

<table width="800">
<tbody>
<tr>
<td width="400pxl">
<p><strong>LU Table Use Case</strong></p>
</td>
<td width="400pxl">
<p><strong>Table Population Use Case</strong></p>
</td>
</tr>
<tr>
<td width="274">
<p>Override the LU schema's sync properties and set specific sync properties on an LU table in either of the following cases:</p>
<ul>
<li>The LU table has only one population.</li>
<li>All populations in the LU table require the same sync method.</li>
<li>The LU table has an Enrichment function which requires a specific sync method.</li>
</ul>
<p>For example, an Eenrichment function of an LU table initiates a purging (cleaning) process on a DB table to remove old data from the DB table. Since the Enrichment function needs to be executed frequently, set a specific sync method on the related LU table.</p>
</td>
<td width="386">
<p>The table has several populations where each population requires a different sync method.</p>
<p><strong>Example 1 </strong></p>
<p>When migrating customers from two different Billing systems (System A and System B) into Fabric, both systems are migrated into the same Billing LU.</p>
<p>Each LU table has two table populations:</p>
<ul>
<li>Population 1, extracts data from System A.</li>
<li>Population 2, extracts data from System B.</li>
</ul>
<p>When getting Customer 1 data from System A, Fabric only needs to execute the sync on Population 1. &nbsp;</p>
<p>When getting Customer 2 data from System B, Fabric only needs to execute the sync on Population 2.</p>
<p>Therefore, set a <a href="/articles/14_sync_LU_instance/05_sync_decision_functions.md">Decision function</a> for each population to check the source system for each execution and return True or False accordingly.</p>
<p><strong>Example 2:</strong></p>
<p>In the Table Population of the CUSTOMER LU table set the CUSTOMER_TYPE Global variable according to the predefined mapping logic. The INVOICE LU table has two table populations:</p>
<ul>
<li>Population 1, populates data for business customers.</li>
<li>Population 2, populates data for private customers.</li>
</ul>
<p>Therefore, set a <a href="/articles/14_sync_LU_instance/05_sync_decision_functions.md">Decision function</a> for each population to check the CUSTOMER_TYPE <a href="/articles/08_globals/01_globals_overview.md">Global variable</a> to execute the correct Table Population based on the customer type of each LUI.</p>
</td>
</tr>
</tbody>
</table>


[![Previous](/articles/images/Previous.png)](/articles/14_sync_LU_instance/06_sync_decision_functions_recommendations.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/14_sync_LU_instance/08_sync_timeout.md)





 
