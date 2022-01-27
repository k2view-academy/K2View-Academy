# LU Schema Window

### Logical Unit (LU) - Related Objects
A [Fabric project](/articles/04_fabric_studio/08_fabric_project_tree.md) has a tree-like structure known as the Project Tree which displays entities in a hierarchical order. The Project Tree is displayed on the left side of the K2View Fabric Studio window and its Logical Units (LU) are located under the Logical Units branch.

The following objects are located under each [Logical Unit (LU)](/articles/03_logical_units/01_LU_overview.md) in the Project Tree:
* [Schema](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-schema).
* Java - [Globals](/articles/08_globals/01_globals_overview.md) and [Functions](/articles/07_table_population/08_project_functions.md).
* Resources, files that can be saved as part of a project. For example, an Excel file.
* [Tables](/articles/06_LU_tables/01_LU_tables_overview.md).
* [Broadway](/articles/19_Broadway/01_broadway_overview.md).

<studio>
    
* [Translations](/articles/09_translations/01_translations_overview_and_use_cases.md).
* Parsers.
* Instance Groups: lists of instances to be migrated into Fabric for the LU. 
* Jobs.
* IIDFinder. 
    
</studio>

### Opening the Logical Unit (LU) Window
Go to the **Project Tree**, click the **LU Name** and then click the **Schema** to open the **Logical Unit** window.

### Logical Unit (LU) Window

<studio>

![image](images/1.3_LU_Schema_WIndow.PNG)

</studio>

<web>

![image](images/web/3_lu_schema_window.PNG)

</web>

The Logical Unit window has three main sections:
* [Logical Unit schema.](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-schema)
* [Logical Unit tabs.](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-tabs)
* [Logical Unit toolbar.](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-schema-window-toolbar) 

### Logical Unit (LU) Schema
The Logical Unit schema is a work area where you can define the Logical Unit’s structure. 
* A Logical Unit schema displays a hierarchical representation of all business entities related to a Root Table. 
* Each table can have one or several populations where each population is linked to its parent table.

[Click for more information about Logical Unit Tables.](/articles/06_LU_tables/01_LU_tables_overview.md)

[Click for more information about LU Table Population.](/articles/07_table_population/01_table_population_overview.md)

### Customer LU Schema Example 

<studio>

![image](images/1.4_LU_schema_example.png)

</studio>

<web>

![image](images/web/1_web_lu_overview.PNG)

</web>

The Logical Unit Schema window shows the Root Table and hierarchy of the tables included in the Logical Unit and the relationships between them:
* The Root Table of the LU schema is Customer. 

* Each table is displayed with all of its fields in a scrollable table.

    <studio>

* Each field is displayed in a separate line, by name with a small colored icon. The icon’s color indicates the data type of the field:

    * Grey: Blob.
    * Red: Text.
    * Green: Real.
    * Blue: Integer.
    
    </studio>

To make the structure of the table schema clearer, group or ungroup a list of tables. For example, group all Billing Tables in the Customer LU.

[Click for more information about Grouping and Ungrouping LU Tables](/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md).

### Logical Unit (LU) Tabs

<studio>

<table style="width: 900px;">
<tbody>
<tr>
<td width="170pxl">
<p><a href="04_LU_properties.md"><strong>Properties Tab</strong></a></p>
</td>
<td width="630pxl">
<p>Contains the properties on an LU level.</p>
</td>
</tr>
<tr>
<td style="width: 119px;">
<p><strong><a href="15_LU_schema_edit_reference_tab.md">References Tab</a></strong></p>
</td>
<td style="width: 414px;">
<p>Used to configure the Reference Tables that are accessible from this LU.</p>
</td>
</tr>
<tr>
<td style="width: 119px;">
<p><a href="14_edit%20enrichment%20order.md"><strong>Enrichment Order Tab</strong></a></p>
</td>
<td style="width: 414px;">
<p>Defines the execution order of Enrichment functions using the up and down arrows. Only Eenrichment functions that are directly related to specific LU tables are included in the display.</p>
</td>
</tr>
<tr>
<td style="width: 119px;">
<p><a href="/articles/05_DB_interfaces/03_DB_interfaces_overview.md"><strong>DB Objects Tab</strong></a></p>
</td>
<td style="width: 414px;">
<p>Displays the list of objects in the source database according to the DB Interface selected in the DB Connection field.</p>
<p>DB objects can be refreshed by clicking the Refresh icon adjacent to the <a href="/articles/05_DB_interfaces/04_creating_a_new_database_interface.md">DB Connection</a> dropdown list.</p>
<p>Dragging an object to a diagram creates a new table in the schema. &nbsp;</p>
</td>
</tr>
<tr>
<td style="width: 119px;">
<p><strong>Objects Tab</strong></p>
</td>
<td style="width: 414px;">
<p>Displays the list of all <a href="/articles/06_LU_tables/01_LU_tables_overview.md">LU tables </a> defined for the LU. Drag the table into the Logical Unit diagram area to add it to the LU schema.</p>
</td>
</tr>
<tr>
<td style="width: 119px;">
<p><strong><a href="/articles/12_LU_navigation/01_Navigating_an_LU_schema.md#how-do-i-use-the-diagram-outline">Diagram Outline Tab</a></strong></p>
</td>
<td style="width: 414px;">
<p>Displays an overview of the LU structure and enables searching for a subset of tables included in the schema.</p>
</td>
</tr>
</tbody>
</table>

</studio>

<web>

The LU Tabs display the schema properties and they can be collapsed or expanded by clicking the <img src="images/web/show_properties.PNG" style="zoom:50%;" /> icon in the right upper corner of the schema window.

<table style="width: 900px;">
<tbody>
<tr>
<td width="170pxl">
<p><strong>Sync</strong></p>
</td>
<td width="630pxl">
<p>Sets the sync properties and <a href="/articles/14_sync_LU_instance/04_sync_methods.md">sync method</a> on an LU level.</p>
<p>Sets the <a href="/articles/07_table_population/08_project_functions.md#event-function">Event functions</a> that are triggered upon a Sync's success or failure or after a successful Delete instance.</p>
</td>
</tr>
<tr>
<td>
<p><strong>Misc</strong></p>
</td>
<td>
<p>Contains various settins such as Storage, Cache location type and Enable data encryption.</p>
</td>
</tr>
<tr>
<td style="width: 119px;">
<p><strong><a href="15_LU_schema_edit_reference_tab.md">Dependent References</a></strong></p>
</td>
<td style="width: 414px;">
<p>Used to configure the Reference Tables that are accessible from this LU.</p>
</td>
</tr>
<tr>
<td style="width: 119px;">
<p><a href="14_edit%20enrichment%20order.md"><strong>Enrichment Order List</strong></a></p>
</td>
<td style="width: 414px;">
<p>Defines the execution order of Enrichment functions using the up and down arrows. Only Eenrichment functions that are directly related to specific LU tables are included in the display.</p>
</td>
</tr>
</tbody>
</table>


</web>



### Logical Unit (LU) Schema Window Toolbar
<studio>

By default, the LU Diagram window displays the following toolbar:

![image](images/1.3_LU_window_icons.png)

You can customize your window by adding or deleting toolbars to/from diagram windows:

<table>
<tbody>
<tr>
<td width="60">&nbsp; <img src="images/1.3_logical_unit_schema_window_table_icon_1.png" alt="" /></td>
<td width="557">
<p>Group SubGraph.</p>
</td>
</tr>
<tr>
<td width="60">&nbsp; <img src="images/1.3_logical_unit_schema_window_table_icon_2.png"/></td>
<td width="557">
<p>Ungroup SubGraph.</p>
</td>
</tr>
<tr>
<td width="60">&nbsp;<img src="images/1.3_logical_unit_schema_window_table_icon_3.png" alt="" /></td>
<td width="557">
<p>Update Tables from Database.</p>
<p>When clicked, the LU tables schema is refreshed from the source database. This action is needed if the LU tables schema has been updated in the source database.</p>
<p>Note: only current tables are updated. Tables are added or deleted from an LU manually.</p>
</td>
</tr>
<tr>
<td width="60">&nbsp; <img src="images/1.3_logical_unit_schema_window_table_icon_4.png" alt="" </td>
<td width="557">
<p>Refresh Items, refreshes items from an implementation into the LU schema.</p>
</td>
</tr>
</tbody>
</table>



[Click for more information about Group and Ungroup SubGraph Objects.](/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md)

[Click for more information about Fabric Studio Diagrams Toolbars.](/articles/04_fabric_studio/03_diagram_and_toolbars.md)

[Click for more information about LU Refresh Options.](/articles/03_logical_units/18_LU_schema_refresh_LU_options.md)

[![Previous](/articles/images/Previous.png)](02_create_a_logical_unit_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_LU_properties.md)

</studio>

<web>

The LU Schema toolbar includes the following elements:

![image](images/web/3_lu_schema_panel.PNG)

<table style="width: 900px;">
<tbody>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/group.PNG" alt="" /></td>
<td width="630pxl">
<p>Group the selected tables.</p>
</td>
</tr>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/ungroup.PNG" /></td>
<td width="630pxl">
<p>Remove the selected tables from the group.</p>
</td>
</tr>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/new_table.PNG" alt="" /></td>
<td width="630pxl">
<p>Add new table to the schema by either creating it or selecting from the list.</p>
</td>
</tr>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/auto_layout.PNG" alt="" /></td>
<td width="630pxl">
<p>Schema auto-layout.</p>
</td>
</tr>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/data_viewer.PNG" alt="" /></td>
<td width="630pxl">
<p>Open the Data Viewer to run queries on IID of the current LU.</p>
</td>
</tr>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/table_data_viewer.PNG" alt="" /></td>
<td width="630pxl">
<p>Open the Data Viewer in a context of a selected table of the current LU. You can switch between the schema tables. The data will be displayed according to the selected table.</p>
</td>
</tr>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/navigation.PNG" alt="" /></td>
<td width="630pxl">
<p>Open the navigation pane of the LU schema.</p>
</td>
</tr>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/zoom.PNG" alt="" /></td>
<td width="630pxl">
<p>Zoom in / zoom out.</p>
</td>
</tr>
<tr>
<td style="text-align: center;" width="170pxl"><img src="images/web/search_t.PNG" alt="" /></td>
<td width="630pxl">
<p>Search a table in the LU schema.</p>
</td>
</tr>
</tbody>
</table>




[![Previous](/articles/images/Previous.png)](03_LU_schema_window.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_create_a_new_LU_object.md)

</web>
