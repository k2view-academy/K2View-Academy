# Enrichment Function vs. Root Function - Comparison Analysis

Enrichment function and [Root function](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md) are both Fabric Java [Project functions](/articles/07_table_population/08_project_functions.md),  which can run a complex logic on the [LU tables](/articles/06_LU_tables/01_LU_tables_overview.md) like invoking Fabric commands <!--Add a link to Fabric commands KI-->, complex data manipulations, as well as  SQL statements on different [DB interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md). Both Enrichment functions and Root functions run during the [sync](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) of the [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui). Still, there are major differences between the Root functions and the Enrichment functions. It is important to understand these differences in order to select the proper solution for each scenario. 

The below comparison analyzes the differences and the similarities between the Enrichment functions and the Root functions, and provides an insight on how and when each type should be used.

<table>
<thead>
<tr>
<td width="95">
<p>&nbsp;</p>
</td>
<td width="241">
<p><strong>Enrichment Function</strong></p>
</td>
<td width="269">
<p><strong>Root Function</strong></p>
</td>
</tr>
</thead>
<tbody>
<tr>
<td width="95">
<p><strong>Function Type</strong></p>
</td>
<td width="241">
<p>Regular Function</p>
</td>
<td width="269">
<p>Root Function</p>
</td>
</tr>
<tr>
<td width="95">
<p><strong>Structure</strong></p>
</td>
<td width="241">
<p>The Enrichment function does not have any input/output parameters.</p>
</td>
<td width="269">
<p>The Root function must have at least one input parameter and yield an array of Objects (Object[]).</p>
</td>
</tr>
<tr>
<td width="95">
<p><strong>Execution Time</strong></p>
</td>
<td width="241">
<p>Executed after populating all <a href="/articles/06_LU_tables/01_LU_tables_overview.md">LU tables</a> in the <a hef="/articles/03_logical_units/01_LU_overview.md">Logical Unit</a>.</p>
</td>
<td width="269">
<p>Upon LU table population.</p>
</td>
</tr>
<tr>
<td width="95">
<p><strong>Operation</strong></p>
</td>
<td width="241">
<p>Performs insert, update, or delete of an LU table data after it has already been populated from a source object.</p>
</td>
<td width="269">
<p>Populating the LU table.</p>
</td>
</tr>
<tr>
<td width="95">
<p><strong>Number of Objects</strong></p>
</td>
<td width="241">
<p>Multiple Enrichment functions can be attached to an LU table. One Enrichment function can be attached to multiple LU tables.</p>
</td>
<td width="269">
<p>Only one Root function per one Table population object.</p>
</td>
</tr>
<tr>
<td width="95">
<p><strong>Execution Order</strong></p>
</td>
<td width="241">
<p>Enrichment execution order is defined at the LU Schema level.</p>
</td>
<td width="269">
<p>Population execution order is defined at the population level.</p>
</td>
</tr>
<tr>
<td width="95">
<p><strong>Access to LU Tables</strong></p>
</td>
<td width="241">
<p>Can extract data from any LU table within the Logical Unit because it is executed after the population of all LU&rsquo;s tables.</p>
</td>
<td width="269">
<p>Can access other LU tables if their population execution order is smaller than the execution order of the current population.</p>
</td>
</tr>
<tr>
<td width="95">
<p><strong>Number of Executions</strong></p>
</td>
<td width="241">
<p>The Enrichment function runs once per LU Table and LUI.&nbsp;</p>
<p>For example:</p>
<p>There are 1,500 subscribers for Customer no.1. Each subscriber has multiple services.</p>
<p>The Enrichment function runs once and updates all the services of Customer no. 1.</p>
</td>
<td width="269">
<p class="CellBodyLeft">The number of executions of a Root function equals the number of records in the parent table.</p>
<p>For example:</p>
<p>There are 1,500 subscribers for Customer no. 1. Each subscriber has multiple services.</p>
<p>The Root function on Services LU Table runs 1500 times and selects the services of each subscriber separately.</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

[![Previous](/articles/images/Previous.png)](/articles/10_enrichment_function/01_enrichment_function_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/10_enrichment_function/03_create_edit_enrichment_function.md)

