# Table Population Overview

### What Is a Table Population? 
A **Table Population** defines and executes mapping and data transformation rules from a data source, like a DB table or Input file, into a target [Logical Unit (LU) table](/articles/06_LU_tables/01_LU_tables_overview.md). The population acts as a map that renders a graphical display of the transformation’s business logic from the source object to the target LU table. Source data can be mapped directly to LU table columns and Fabric transformation objects like <studio>[Translations](/articles/09_translations/01_translations_overview_and_use_cases.md), </studio>[Functions](/articles/07_table_population/08_project_functions.md) or [Globals](/articles/08_globals/01_globals_overview.md), and can be added to define the mapping logic into the LU table. 

Each table can have one or several table populations that can be executed simultaneously or according to a predefined [execution order](/articles/07_table_population/13_LU_table_population_execution_order.md).
Each table population extracts data from a data source, transforms it when needed and then populates the data into an LU table. Table populations can be categorized as follows:

<studio>

* Based on a source object:
  * DB query, (default) that executes an SQL Select query on a predefined DB interface. 
  * [Root function](/articles/07_table_population/11_1_creating_or_editing_a_root_function.md), that can run various SQL Select queries and execute complex logic using Java code, including data manipulations, Fabric APIs, Fabric commands and calculations. All records yielded from the function are inserted into the table. 
    Note that tables can also be populated or updated by [Enrichment functions](/articles/10_enrichment_function/01_enrichment_function_overview.md#enrichment-function-overview) which, unlike Root functions, are executed after all LU tables are populated.

  </studio>
  
* Based on a Broadway flow, whereby a Broadway Flow template is created to extract the data from the source and populate it into the target LU table. A Broadway Flow template can be enhanced with additional logic pre and post loading.

  [Click for more information about Table Population based on a Broadway flow](14_table_population_based_Broadway.md).

  

### Table Population In an LU Schema 
An [LU Schema](/articles/03_logical_units/03_LU_schema_window.md) displays a hierarchical representation of the data related to the Root Table. Parent-child links in LU tables are created via their Table Population objects:
*	Each [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) can have one or several Table Population objects. 
*	Each Table Population object, apart from the Table Population of the LU's Root Table, must be linked to a parent table via its Input columns.
*	Each Table Population object can be linked to a different parent LU table.
Note that an LU table can be added to an [LU Schema](/articles/03_logical_units/03_LU_schema_window.md)  without a Table Population object. This table is not populated by the [sync process](/articles/01_fabric_overview/02_fabric_glossary.md#sync) of the [LU Instance](/articles/01_fabric_overview/02_fabric_glossary.md#lui) but can be populated by a separate transaction.

[Click for more information about Building an LU Hierarchy and Linking Table Populations.](/articles/03_logical_units/12_LU_hierarchy_and_linking_table_population.md) 

<studio>

### Table Population Window

The Table Population window is used to define and display the transformation rules that are applied to data when it is loaded into a Fabric database. 

[Click for more information about How to Create a New Population.](/articles/07_table_population/03_creating_a_new_table_population.md)

The following is an example of the Table Population window. 

![image](images/07_01_01_table_pop_window.png)

The Table Population window has the following sections:
*	[Population Header](/articles/07_table_population/01_table_population_overview.md#population-window-header). 
*	[Working Area](/articles/07_table_population/01_table_population_overview.md#population-window-working-area).
*	[Properties Tab](/articles/07_table_population/01_table_population_overview.md#properties-tab).
*	[Objects Tab](/articles/07_table_population/01_table_population_overview.md#objects-tab).

### Population Window Header
The Population window header holds the **Debug** and **Options** toolbars where additional toolbars like **Edit**, **Zoom**, **Alignment** or **Export** can be added when needed. 

[Click for more information about Additional Toolbars.](/articles/07_table_population/01_table_population_overview.md#options-toolbar)

### Debug Toolbar
The **Debug toolbar** holds options for testing the Population process by executing its logic on a selected [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id).
*	When the execution is successful, the values of the Input and Output fields are displayed in the working area above the connection lines between the source and the target. 
*	If there is an error, it is displayed in the [Fabric Studio Log](/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md) section.
*	If the outcome has more than one row, you can navigate between the different rows.

[Click for more information about Debugging the Table Population.](/articles/13_LUDB_viewer_and_studio_debug_capabilities/03_debug_table_population.md)

### Options Toolbar
The **Options toolbar** includes **Refresh** and **Group** / **Ungroup** options.
<table style="width: 606px;">
<tbody>
<tr>
<td width="200">&nbsp; <img src="/articles/07_table_population/images/07_01_02_refresh.png" alt="" /><img src="/articles/07_table_population/images/07_01_03_refresh2.PNG" alt="" /></td>
<td width="700pxl">
<p>There are a number of <strong>Refresh</strong> options for updating changes in a population. For example, if a function used in the current population is modified, click <strong>Refresh Functions</strong> or <strong>Refresh All</strong> to make the changes available for the population.</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="200"><img src="/articles/07_table_population/images/07_01_04_refresh3.png" alt="" /></td>
<td style="width: 343px;">
<p>Click <strong>Group</strong> / <strong>Ungroup SubGraph</strong> to group / ungroup several objects in the population.</p>
</td>
</tr>
</tbody>
</table> 

### Population Window Working Area
The **Table Population Working Area** acts as a workspace for mapping the Source Object to the target LU table. Transformation objects can also be added to the working area map. 

The Working Area includes:
<table width="614">
<tbody>
<tr>
<td width="200pxl">
<p>Source Object (Left)</p>
</td>
<td width="700pxl">
<p>DB query or root function.</p>
</td>
</tr>
<tr>
<td width="179">
<p>Fabric LU table (right)</p>
</td>
<td width="435">
<p>A target LU table in the LU Schema.</p>
</td>
</tr>
<tr>
<td width="179">
<p>Population Working area</p>
</td>
<td width="435">
<p>Links, functions and translations that represent data transformation from a source to Fabric columns.</p>
</td>
</tr>
</tbody>
</table> 

[Click for more information about Table Population Transformation Rules.](/articles/07_table_population/06_table_population_transformation_rules.md)

### Properties Tab
The **Properties Tab** in a Table Population window (right pane) displays the properties of each selected object in the Table Population object. For example, source table, target LU table or a translation. Note that some properties are editable.  

[Click for more information about the Table Population Properties Tab.](/articles/07_table_population/04_table_population_properties_tab.md)

### Objects Tab
The **Objects Tab** in a Table Population window (right pane) displays all available Fabric objects. For example, [Databases](/articles/05_DB_interfaces/03_DB_interfaces_overview.md), [Globals](/articles/08_globals/01_globals_overview.md), [Built-In Functions](/articles/07_table_population/07_fabric_built_in_functions.md) or [Project Functions](/articles/07_table_population/08_project_functions.md). 
Objects can be included in a population map by dragging and dropping them into the working area.

[Click for more information about Table Population Transformation Rules.](/articles/07_table_population/06_table_population_transformation_rules.md)

### Diagram Outline Link
The **Expand / Collapse** and **Refresh** links are displayed above the **Objects tab**.
<table style="width: 566px;">
<tbody>
<tr>
<td width="200pxl"><img src="/articles/07_table_population/images/07_01_05_object_icon1.PNG" alt="" /></td>
<td width="700pxl">
<p>Expand all.</p>
</td>
</tr>
<tr>
<td width="200"><img src="/articles/07_table_population/images/07_01_06_object_icon2.PNG" alt="" /></td>
<td style="width: 465px;">
<p>Collapse all.</p>
</td>
</tr>
<tr>
<td width="200"><img src="/articles/07_table_population/images/07_01_07_object_icon_3.PNG" alt="" /></td>
<td style="width: 465px;">
<p>Refresh Tree, refreshes the Objects tree. If a new object is created after the Population window is opened, click Refresh Tree to display the object in the tree.</p>
</td>
</tr>
</tbody>
</table>
[Click for more information about the Table Population Diagram Outline.](/articles/07_table_population/12_table_population_diagram_outline.md)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_source_object_types.md)

</studio>

<web>

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_table_population_mode.md)

</web>
