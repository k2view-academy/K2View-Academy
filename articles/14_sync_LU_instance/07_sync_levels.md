# Sync Method Levels

### What are Sync Method Levels?

[Sync properties](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/04_sync_methods.md) can be defined on [LU Schema,](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md) [LU Table](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/06_LU_tables/01_LU_tables_overview.md) or [Table Population](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md) levels. By default, LU Tables, Table Population objects and Enrichment Functions inherit the Sync properties defined for the LU Schema.\
The Sync properties of an LU Schema can be overridden, as follows:
* Setting Sync properties on the LU Table. By default, the related Table Population objects and the enrichment functions attached to the LU Table inherit the Sync properties of the LU Table.
* Setting Sync properties on each Table Population object. 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/images/6_6_1_sync_levels.png)

## Overriding Sync Properties in an LU Schema - Use Cases

You may need to override the LU Schema Sync properties on LU Tables or Table Populations that require specific handling. The following table describes use cases for overriding the Sync properties on each object:
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
<p>Override the LU Schema Sync properties and set specific Sync properties on an LU Table in either of the following cases:</p>
<ul>
<li>The LU table has only one population.</li>
<li>All populations in the LU Table require the same Sync method.</li>
<li>The LU Table has an enrichment function which requires a specific sync method.</li>
</ul>
<p>For example, an enrichment function of an LU Table initiates a purging (cleaning) process on a DB table to remove old data from the DB table. Since the enrichment function needs to be executed frequently, set a specific Sync method on the related LU Table.</p>
</td>
<td width="386">
<p>The table has several populations where each population requires a different Sync method.</p>
<p><strong>Example 1 </strong></p>
<p>When migrating customers from two different Billing systems (System A and System B) into Fabric, both systems are migrated into the same Billing LU.</p>
<p>Each LU Table has two table populations:</p>
<ul>
<li>Population 1, extracts data from System A.</li>
<li>Population 2, extracts data from System B.</li>
</ul>
<p>When getting Customer 1 data from System A, Fabric only needs to execute the Sync on Population 1. &nbsp;</p>
<p>When getting Customer 2 data from System B, Fabric only needs to execute the Sync on Population 2.</p>
<p>Therefore, set a <a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/05_sync_decision_functions.md">decision function</a> for each population to check the source system for each execution and return True or False accordingly.</p>
<p><strong>Example 2:</strong></p>
<p>In the table population of the CUSTOMER LU Table set the CUSTOMER_TYPE Global variable according to the predefined mapping logic. The INVOICE LU Table has two table populations:</p>
<ul>
<li>Population 1, populates data for business customers.</li>
<li>Population 2, populates data for private customers.</li>
</ul>
<p>Therefore, set a <a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/05_sync_decision_functions.md">decision function</a> for each population to check the CUSTOMER_TYPE <a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/01_globals_overview.md">Global variable</a> to execute the correct Table Population based on the customer type of each LUI.</p>
</td>
</tr>
</tbody>
</table>


[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/06_sync_decision_functions_recommendations.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/14_sync_LU_instance/08_sync_timeout.md)





 
