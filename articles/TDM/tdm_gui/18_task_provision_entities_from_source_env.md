# How to Provision Entities from a Source Environment

The following table describes how to create a task to provision entities from a selected source environment. For example: Extract 10 customers from Production and load them to the UAT environment:



<table width="900pxl">
<tbody>
<tr>
<td width="250pxl">
<p><strong>Scenario</strong></p>
</td>
<td width="250pxl">
<p><strong>Example</strong></p>
</td>
<td width="100pxl">
<p><a href="14a_task_source_component.md"><strong>Source</strong></a></p>
</td>
<td width="100pxl">
<p><a href="15_task_subset_component.md"><strong>Subset</strong></a></p>
</td>
<td width="100pxl">
<p><a href="16_task_test_data_store_component.md"><strong>Test Data Store</strong></a></p>
</td>
<td width="100pxl">
<p><a href="17_task_target_component.md"><strong>Target</strong></a></p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>Extract</p>
</td>
<td width="250pxl">
<p>Extract 10 customers from Production.</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>N</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>Extract and load.</p>
<p>Optional additional actions -</p>
<ul>
<li>Reserve the loaded entities.</li>
<li>Delete the entities from the target before loading them.</li>
</ul>
</td>
<td width="250pxl">
<p>Extract 10 customers from Production and load them to the UAT environment.</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>Load.</p>
<p>Optional additional actions -</p>
<ul>
<li>Reserve the loaded entities.</li>
<li>Delete the entities from the target before loading them.</li>
</ul>
</td>
<td width="250pxl">
<p>Get from the Test Data Store entities that were pre-extracted from Production, and load them to the UAT environment.</p>
</td>
<td width="100pxl">
<p>&nbsp;</p>
</td>
<td width="100pxl">
<p>&nbsp;</p>
</td>
<td width="100pxl">
<p>&nbsp;</p>
</td>
<td width="100pxl">
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>Delete</p>
</td>
<td width="250pxl">
<p>Delete a customer from the UAT environment.</p>
</td>
<td width="100pxl">
<p>N</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
<td width="100pxl">
<p>Y</p>
</td>
</tr>
<tr>
<td width="250pxl">
<p>Reserve</p>
</td>
<td width="250pxl">
<p>Reserve entities on the UAT environment.</p>
</td>
<td width="100pxl">
<p>N</p>
</td>
<td width="100pxl">
<p>Y</p>
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





 [![Previous](/articles/images/Previous.png)](17_task_target_component.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](19_task_synthetic_data_generation.md)

