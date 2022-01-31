<studio>

# Table Population Diagram Outline

The **Diagram Outline** of the population map is located in the **Objects tab** of the [Table Population](/articles/07_table_population/01_table_population_overview.md) window and displays all objects that are currently found in the population map. When clicking the object’s name, or object’s column name in the Diagram Outline, the matching object or column is marked in the Table Population working area.
For example, the following diagram outline displays a  list of objects  included in the table population:

![image](images/07_12_01_screen.png)

<table>
<tbody>
<tr>
<td width="114">
<p><strong>ADDRESS</strong></p>
</td>
<td width="491">
<p>Target LU table.</p>
</td>
</tr>
<tr>
<td width="114">
<p><strong>query_ADDRESS</strong></p>
</td>
<td width="491">
<p>Source DB query.</p>
</td>
</tr>
<tr>
<td width="114">
<p><strong>tr trnCountryCd</strong></p>
</td>
<td width="491">
<p>Translation in the population that transforms data from the source to the target.</p>
</td>
</tr>
<tr>
<td width="114">
<p><strong>CUSTOMER</strong></p>
</td>
<td width="491">
<p>LU lookup table.</p>
</td>
</tr>
<tr>
<td width="114">
<p><strong>c entity_type</strong></p>
</td>
<td width="491">
<p>Constant value used in the population of a target table.</p>
</td>
</tr>
<tr>
<td width="114">
<p><strong>fx k2_ifNull</strong></p>
</td>
<td width="491">
<p>Built-in function that transforms data from the source to the target.</p>
</td>
</tr>
</tbody>
</table>

When an object or a field of an object is selected in the Diagram Outline, it is also highlighted in the map. For example, in the above diagram the Translation object trnCountryCd is selected in the Diagram and it is highlighted in the map. 

[![Previous](/articles/images/Previous.png)](/articles/07_table_population/11_lookup_tables.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/07_table_population/13_LU_table_population_execution_order.md) 

</studio>
