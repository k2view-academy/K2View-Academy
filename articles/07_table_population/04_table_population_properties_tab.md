<studio>

# Table Population Properties Tab

The **Properties Tab** in the [Table Population](/articles/07_table_population/01_table_population_overview.md) window displays additional information about different objects in the Population window, as follows:
*	[DB query or Root function Source Objects](/articles/07_table_population/02_source_object_types.md#table-population---source-object-types).
*	[Target LU table](/articles/06_LU_tables/01_LU_tables_overview.md#lu-tables-overview).
* Other objects of a [Table Population transformation](/articles/07_table_population/06_table_population_transformation_rules.md) like a Translation or Global.
*	Properties of any selected field in a Population object.  

Since each object has different properties, the tab’s layout is updated to display them when an object is selected in the map. 

The properties include:
*	**Basic information** about the Population object. For example, Name or ID.
*	**Specific properties** of each object. For example, sync method of target LU table.
*	**Link to open the object**. For example, to edit the query or the function.
*	**Input arguments** (source only) or the **Inputs default value**, if applicable.  

### Source Object - DB Query Properties
<table width="660">
<tbody>
<tr>
<td width="312pxl"><img src="/articles/07_table_population/images/07_04_01_table.png" alt=""/></td>
<td width="600pxl">
<p><strong>Name</strong> - displays the DB query name. Editable field.</p>
<p><strong>ID</strong> - generated by Fabric.</p>
<p><strong>DB Connection</strong> - displays the DB interface of the Source Object. Non-editable.</p>
<p><strong>SQL</strong> - displays the query. To edit it, click the Edit Query link.</p>
<p><strong>Edit Query</strong> - a link to the <a href="/articles/11_query_builder/01_query_builder_overview.md#query-builder-overview"> Query Builder</a>.</p>
<p><strong>Input Arguments&nbsp;</strong>- displays a list of Input arguments which are all True by default.</p>
<p>The fields that should be linked to the <strong>parent table</strong> are set to <strong>True. </strong>Other fields that do not need to be linked to a parent table can be set to <strong>False</strong>. In the DB query of a root table, only one field can be defined as <strong>True</strong> and it is populated by the Instance ID.</p>
</td>
</tr>
</tbody>
</table>

### Function Properties
The Function Properties tab displays the properties of either a source [**Root function**](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md) or any [**Project function**](/articles/07_table_population/08_project_functions.md) in the population.
<table width="660">
<tbody>
<tr>
<td width="312pxl"><img src="/articles/07_table_population/images/07_04_02_functions.png" alt=""></td>
<td width="600pxl">
<p><strong>Name</strong> - displays the function name. Non-editable.</p>
<p><strong>ID</strong> - generated by Fabric.</p>
<p><strong>Category</strong> - Non-editable.</p>
<p><strong>Function Type</strong> - Non-editable.</p>
<p><strong>Description</strong> - Non-editable.</p>
<p><strong>Open Function</strong> - a link to the Function window.</p>
<p><strong>Inputs Default Value</strong> - displays the Input parameters of the function.</p>
<p>If the function object is a <strong>Root function</strong>, it must have at least one Input parameter.</p>
<p>If it is a Root function of a root table, it must have only one parameter.</p>
</td>
</tr>
</tbody>
</table>

### Target LU Table Properties
<table width="660">
<tbody>
<tr>
<td width="312pxl"><img src="/articles/07_table_population/images/07_04_03_target.png" alt=""></td>
<td width="600pxl">
<p><strong>Population Object Name</strong> - displays the object name. Non-editable field.</p>
<p><strong>Table Name</strong> - displays the name of the target table. Non-editable.</p>
<p><strong>ID </strong>and<strong> Root Item ID</strong> - generated by Fabric.</p>
<p><strong>Mode</strong> - displays the Table Population mode. There are 4 modes: Insert (default) / Upsert / Update / Delete.</p>
<p><strong>Sync Method</strong> - displays the Table Population Sync method. There are 4 Sync methods: Inherited (default) / None / Time Interval / Decision Function.</p>
<p><strong>Truncate Before Sync</strong> - True / False.</p>
<p>When Truncate Before Sync = True, the <strong>entire LU Table</strong> is truncated before the table population is executed. The Truncate Before Sync = True is equivalent to the All value in the <a href="/articles/06_LU_tables/04_table_properties.md#delete-mode">Delete Mode</a> LU table's property. Setting the Truncate Before Sync to True overrides the LU table's Delete Mode. When the Truncate Before Sync is set on False, the delete mode it taken from the LU table. 
Note that it is not recommended to set the Truncate Before Sync to True if the population mode is not Insert, since it truncates all records from the LU table before running the population.</p>
<p><strong>IID Finder</strong> - sets the IID Finder properties on a population level used when creating the IID Finder XML file.</p>
<p><strong>Open Table Schema</strong> - a link to the Table Schema window.</p>
</td>
</tr>
</tbody>
</table>

### Translation Properties
<table width="660">
<tbody>
<tr>
<td width="312pxl"><img src="/articles/07_table_population/images/07_04_04_translations.png" alt=""></td>
<td width="600pxl">
<p><strong>Name</strong> - displays the function name. Non-editable.</p>
<p><strong>ID</strong> - generated by Fabric.</p>
<p><strong>Input Not Found Action</strong> - indicates the system&rsquo;s behavior when a matching Translation is not found. There are 4 options: Use Default / Reject Record / Reject Instance / Report and Use Default.</p>
<p><strong>Is Import From SQL</strong> - False / True. Indicates whether the translation data was imported from SQL or not.</p>
<p><strong>Open Translation</strong> - a link to open the <a href="/articles/09_translations/01_translations_overview_and_use_cases.md#translations-overview"> Translation</a> window.</p>
<p><strong>Inputs Default Value</strong> - displays the Input parameters of the Translation. A default value can be defined for each Input field that can be used if there is no link connected to the field or when an Input value = Null.</p>
<p>To remove the default value, right-click the field and select either <strong>Clear Value</strong> or <strong>Set as Empty String</strong>.</p>
</td>
</tr>
</tbody>
</table>

### Global Properties
<table width="660">
<tbody>
<tr>
<td width="312pxl"><img src="/articles/07_table_population/images/07_04_05_globals.png" alt=""></td>
<td width="600pxl">
<p><strong>Name</strong> - displays the Global name. Non-editable.</p>
<p><strong>ID</strong> - generated by Fabric.</p>
<p><strong>Value</strong> - displays Global value. Non-editable.</p>
<p><strong>Comment</strong> - displays the Global's comment as populated in the globals definition.</p>
</td>
</tr>
</tbody>
</table>

### Constant Properties
<table width="660">
<tbody>
<tr>
<td width="312pxl"><img src="/articles/07_table_population/images/contacts.png" alt=""></td>
<td width="600pxl">
<p><strong>Name</strong> - displays the Constant name. Editable field.</p>
<p><strong>ID</strong> - generated by Fabric.</p>
<p><strong>Value</strong> - displays the Constant value. Editable field.</p>
</td>
</tr>
</tbody>
</table>


### Lookup Properties
[Click for more information about Lookups properties.](/articles/07_table_population/11_lookup_tables.md#lookup-properties-tab)

[![Previous](/articles/images/Previous.png)](05_table_population_mode.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_table_population_transformation_rules.md)

</studio>
