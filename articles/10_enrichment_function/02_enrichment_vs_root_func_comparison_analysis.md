<studio>

# Enrichment Function vs. Root Function - Comparison Analysis

Enrichment functions and [Root functions](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md) are both Fabric [Project functions](/articles/07_table_population/08_project_functions.md) that can run complex logic on [LU tables](/articles/06_LU_tables/01_LU_tables_overview.md) like invoking [Fabric commands](/articles/02_fabric_architecture/04_fabric_commands.md), complex data manipulations and SQL statements on different [DB interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md). Both Enrichment functions and Root functions run during an [LUI sync](/articles/14_sync_LU_instance/01_sync_LUI_overview.md).
There are major differences between Root functions and Enrichment functions and it is important to understand them in order to select the correct solution for each scenario. 

The following table displays the comparison analysis between Enrichment and Root functions and provides insight on how and when each function type should be used.

<table>
<thead>
<tr>
<td width="160px">
<p>&nbsp;</p>
</td>
<td width="370px">
<p><strong>Enrichment Function</strong></p>
</td>
<td width="370px">
<p><strong>Root Function</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="160px">
<p><strong>Function Type</strong></p>
</td>
<td width="370px">
<p>Regular function.</p>
</td>
<td width="370px">
<p>Root function.</p>
</td>
</tr>
<tr>
<td width="160px">
<p><strong>Structure</strong></p>
</td>
<td width="370px">
<p>The Enrichment function does not have any Input/Output parameters.</p>
</td>
<td width="370px">
<p>The Root function must have at least one Input parameter and yield an array of Objects (Object[]).</p>
</td>
</tr>
<tr>
<td width="160px">
<p><strong>Execution Time</strong></p>
</td>
<td width="370px">
<p>Executed after populating all <a href="/articles/06_LU_tables/01_LU_tables_overview.md">LU tables</a> in the <a hef="/articles/03_logical_units/01_LU_overview.md">Logical Unit</a>.</p>
</td>
<td width="370px">
<p>Upon <a href="/articles/07_table_population/01_table_population_overview.md">LU Table population.</a></p>
</td>
</tr>
<tr>
<td width="160px">
<p><strong>Operation</strong></p>
</td>
<td width="370px">
<p>Performs insert, update, or delete of an LU table's data after it has already been populated from a source object.</p>
</td>
<td width="370px">
<p>Populates the LU table.</p>
</td>
</tr>
<tr>
<td width="160px">
<p><strong>Number of Objects</strong></p>
</td>
<td width="370px">
<p>Multiple Enrichment functions can be attached to an LU table. One Enrichment function can be attached to multiple LU tables.</p>
</td>
<td width="370px">
<p>Only one Root function per one Table Population object.</p>
</td>
</tr>
<tr>
<td width="160px">
<p><strong>Execution Order</strong></p>
</td>
<td width="370px">
<p>Enrichment execution order is defined at the LU Schema level.</p>
</td>
<td width="370px">
<p>Population execution order is defined at the population level.</p>
</td>
</tr>
<tr>
<td width="160px">
<p><strong>Access to LU Tables</strong></p>
</td>
<td width="370px">
<p>Can extract data from any LU table within the Logical Unit since it is executed after the population of all LU tables.</p>
</td>
<td width="370px">
<p>Can access other LU tables if their population execution order is smaller than the execution order of the current population.</p>
</td>
</tr>
<tr>
<td width="160px">
<p><strong>Number of Executions</strong></p>
</td>
<td width="370px">
<p>The Enrichment function runs once per LU table and LUI.&nbsp;</p>
<p>For example:</p>
<p>There are 1,500 subscribers for Customer 1. Each subscriber has multiple services.</p>
<p>The Enrichment function runs once and updates all the services of Customer 1.</p>
</td>
<td width="370px">
<p class="CellBodyLeft">The number of executions of a Root function equals the number of records in the parent table.</p>
<p>For example:</p>
<p>There are 1,500 subscribers for Customer 1. Each subscriber has multiple services.</p>
<p>The Root function on the Services LU table runs 1,500 times and selects the services of each subscriber separately.</p>
</td>
</tr>
</tbody>
</table>


[![Previous](/articles/images/Previous.png)](/articles/10_enrichment_function/01_enrichment_function_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/10_enrichment_function/03_create_edit_enrichment_function.md)

</studio>
