# Table Population Overview

### What Is a Table Population in Fabric? 
A **Table Population** is a component that defines and executes the mapping and data transformation rules from a data source, like a DB table or Input file, into a target [Logical Unit (LU) Table](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/06_LU_tables/01_LU_tables_overview.md). The population acts as a map that renders a graphical display of the transformation’s business logic from the source object to the target LU Table. Source data can be mapped directly to LU Table columns and Fabric transformation objects like [Translations](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/09_translations/01_translations_overview_and_use_cases.md), [Functions](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/08_project_functions.md) or [Globals](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/01_globals_overview.md), and can be added to define the mapping logic into the LU Table. 

Each table can have one or several Table Populations that can be executed simultaneously or according to a predefined [execution order](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/13_LU_table_population_execution_order.md) .
Each Table Population extracts data from a data source, transforms it when needed and then populates the data into an LU Table.
There are two types of source objects for a **Table Population** object:
*	[DB query](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/02_source_object_types.md), (default) that executes an SQL Select query on a predefined DB interface. 
*	[Root function](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/02_source_object_types.md), that can run various SQL Select queries and execute complex logic using Java code, including data manipulations, Fabric APIs, Fabric commands and calculations. All records yielded from the function are inserted into the table. 
Note that tables can also be populated or updated by [enrichment functions] which, unlike root functions, are executed after all LU Tables are populated.

### Table Population in an LU Schema 
An [LU Schema structure](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md)  displays a hierarchical representation of the data related to the root table. Parent-child links in LU Tables are created via their Table Population objects:
*	Each [LU Table](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/06_LU_tables/01_LU_tables_overview.md) can have one or several Table Population objects. 
*	Each Table Population object, apart from the Table Population of the root LU Table, must be linked to a parent table via its Input columns.
*	Each Table Population object can be linked to a different parent LU Table.
Note that an LU Table can be added to an [LU Schema](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md)  without a Table Population object. This table is not populated by the [sync](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#sync) of the [LU Instance](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#lui) but can be populated by a separate transaction .

[Click for more information about Building an LU Hierarchy and Linking Table Populations.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/12_LU_hierarchy_and_linking_table_population.md)

### Table Population Window
The Table Population window is used to define and display the transformation rules that are applied to data when it is loaded into a Fabric database. 

[Click for more information about How to Create a New Population.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/03_creating_a_new_table_population.md)

The following is an example of the Table Population window. 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_01_01_table_pop_window.png)

The Table Population window has the following sections:
*	[Population Header](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md#population-window-header). 
*	[Working Area](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md#population-window-working-area).
*	[Properties Tab](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md#properties-tab).
*	[Objects Tab](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md#objects-tab).

### Population Window Header
The Population window header holds the **Debug** and **Options** toolbars where additional toolbars like **Edit**, **Zoom**, **Alignment** or **Export** can be added when needed. 

Click for more information about Additional Toolbars.

### Debug Toolbar
The [**Debug Toolbar**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/13_LUDB_viewer_and_studio_debug_capabilities/03_debug_table_population.md) holds options for testing the Population process by executing its population logic on a selected [Instance ID](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#instance-id).
*	When the execution is successful, the values of the Input and Output fields are displayed in the working area above the connection lines between the source and the target. 
*	If there is an error, it is displayed in the Fabric Studio Log section.
*	If the outcome has more than one row, you can navigate between the different rows.

[Click for more information about the Fabric Studio Log.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md)

### Options Toolbar
The **Options toolbar** includes **Refresh** and **Group** / **Ungroup** options.
<table style="width: 606px;">
<tbody>
<tr>
<td width="200">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_01_02_refresh.png" alt="" /><img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_01_03_refresh2.PNG" alt="" /></td>
<td width="700pxl">
<p>There are a number of <strong>Refresh</strong> options for updating changes in a population. For example, if a function used in the current population is modified, click <strong>Refresh Functions</strong> or <strong>Refresh All</strong> to make the changes available for the population.</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="200"><img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_01_04_refresh3.png" alt="" /></td>
<td style="width: 343px;">
<p>Click <strong>Group</strong> / <strong>Ungroup SubGraph</strong> to group / ungroup several objects in the population.</p>
</td>
</tr>
</tbody>
</table>

### Population Window Working Area
The Table Population **Working Area** acts as a workspace for mapping the Source Object to the target LU Table. Transformation objects can also be added to the working area map. 

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
<p>A target LU Table in the LU Schema.</p>
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

[Click for more information about Table Population Transformation Rules.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/06_table_population_transformation_rules.md)

### Properties Tab
The **Properties Tab** in a Table Population window (right pane) displays the properties of each selected object in the Table Population object. For example, source table, target LU Table or a translation. Note that some properties are editable. 

[Click for more information about Table Population Properties Tab.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/04_table_population_properties_tab.md)

### Objects Tab
The **Objects Tab** in a **Table Population** window (right pane) displays all available Fabric objects. For example, [Databases](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/02_interfaces_source_analysis_guidelines.md), [Globals](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/01_globals_overview.md), [Built-In Functions](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/07_fabric_built_in_functions.md) or [Project Functions](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/08_project_functions.md). 
Objects can be included in a population map by dragging and dropping them into the working area.

[Click for more information about Table Population Transformation Rules.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/06_table_population_transformation_rules.md)

### Diagram Outline Link
The Expand / Collapse and Refresh links are displayed above the Objects tab.
<table style="width: 566px;">
<tbody>
<tr>
<td width="200pxl"><img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_01_05_object_icon1.PNG" alt="" /></td>
<td width="700pxl">
<p>Expand all.</p>
</td>
</tr>
<tr>
<td width="200"><img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_01_06_object_icon2.PNG" alt="" /></td>
<td style="width: 465px;">
<p>Collapse all.</p>
</td>
</tr>
<tr>
<td width="200"><img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/images/07_01_07_object_icon_3.PNG" alt="" /></td>
<td style="width: 465px;">
<p>Refresh Tree, refreshes the Objects tree. If a new object is created after the Population window is opened, click Refresh Tree to display the object in the tree.</p>
</td>
</tr>
</tbody>
</table>

[Click for more information about Table Population Diagram Outline.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/12_table_population_diagram_outline.md)

[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/02_source_object_types.md)

