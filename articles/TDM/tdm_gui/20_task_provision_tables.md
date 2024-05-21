# How to Provision Tables from a Source Environment

The following table describes how to create a task to provision tables from a selected source environment. For example: Extract 5 tables from Production and load them to the UAT environment:



<table width="900pxl">
<tbody>
<tr>
<td width="200pxl">
<p><strong>Scenario</strong></p>
</td>
<td width="200pxl">
<p><strong>Example</strong></p>
</td>
<td width="150pxl">
<p><a href="14a_task_source_component.md"><strong>Source</strong></a></p>
</td>
<td width="100pxl">
<p><a href="15_task_subset_component.md"><strong>Subset</strong></a></p>
</td>
<td width="150pxl">
<p><a href="16_task_test_data_store_component.md"><strong>Test Data Store</strong></a></p>
</td>
<td width="100pxl">
<p><a href="17_task_target_component.md"><strong>Target</strong></a></p>
</td>
</tr>
<tr>
<td width="200pxl">
<p>Extract Business entities and referential-related tables</p>
</td>
<td width="200pxl">
<p>Extract 10 customers and the customer_type table from Production</p>
</td>
<td width="200pxl">
<p>Select the <strong>Entities & referential data</strong> option </p>
</td>
<td width="100pxl">
<p>N/A</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>N</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p>Extract tables only</p>
</td>
<td width="200pxl">
<p>Extract 5 tables from Production</p>
</td>
<td width="200pxl">
<p>Select the <strong>Tables</strong> option</p>
</td>
<td width="100pxl">
<p>Optional</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>N</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p>Extract and load Business entities and referential-related tables</p>
</td>
<td width="200pxl">
<p>Extract 10 customers and the customer_type table from Production and load them to the UAT environment</p>
</td>
<td width="200pxl">
<p>Select the <strong>Entities & referential data</strong> option</p>
</td>
<td width="100pxl">
<p>N/A</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p>Extract and load tables only</p>
</td>
<td width="200pxl">
<p>Extract 5 tables from Production and load them to the UAT environment</p>
</td>
<td width="200pxl">
<p>Select the <strong>Tables</strong> option</p>
</td>
<td width="100pxl">
<p>Optional</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p>Load Business entities and referential-related tables</p>
</td>
<td width="200pxl">
<p>Get from the Test Data Store 5 entities and one table pre-extracted from Production and load them to the UAT environment</p>
</td>
<td width="200pxl">
<p>Select the <strong>Entities & referential data</strong> option</p>
</td>
<td width="100pxl">
<p>N/A</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
</tr>
<tr>
<td width="200pxl">
<p>Load tables only</p>
</td>
<td width="200pxl">
<p>Get from the Test Data Store 2 tables pre-extracted from Production and load them to the UAT environment</p>
</td>
<td width="200pxl">
<p>Select the <strong>Tables</strong> option</p>
</td>
<td width="100pxl">
<p>Optional</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
</tr>
</tbody>
</table>





 [![Previous](/articles/images/Previous.png)](19_task_synthetic_data_generation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](25_task_tdmdb_tables.md)

