# LU Schema Window

### Logical Unit (LU) - Related Objects
A [Fabric project](/articles/04_fabric_studio/08_fabric_project_tree.md) has a tree-like structure known as the Project Tree which displays entities in a hierarchical order. The Project Tree is displayed on the left side of the K2View Fabric Studio window and its Logical Units (LU) are located under the Logical Units branch.

The following objects are located under each [Logical Unit (LU)](/articles/03_logical_units/01_LU_overview.md) in the Project Tree:
* [Schema](/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-schema).
* Java - [Globals](/articles/08_globals/01_globals_overview.md) and [Functions](/articles/07_table_population/08_project_functions.md).
* Resources, files that can be saved as part of a project. For example, an Excel file.
* [Translations](/articles/09_translations/01_translations_overview_and_use_cases.md).
* [Tables](/articles/06_LU_tables/01_LU_tables_overview.md).
* Parsers.
* Instance Groups: lists of instances to be migrated into Fabric for the LU. 
* [Broadway](/articles/19_Broadway/01_broadway_overview.md).
* Jobs.
* IIDFinder. 

### Opening the Logical Unit (LU) Window
Go to the **Project Tree**, click the **LU Name** and then click the **schema** to open the **Logical Unit** window.

### Logical Unit (LU) Window
![image](images/1.3_LU_Schema_WIndow.PNG)

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

![image](images/1.4_LU_schema_example.png)

The Logical Unit Schema window shows the Root Table and hierarchy of the tables included in the Logical Unit and the relationships between them:
* The Root Table of the LU schema is Customer. 
* Each table is displayed with all of its fields in a scrollable table.
* Each field is displayed in a separate line, by name with a small colored icon. The icon’s color indicates the data type of the field:

    * Grey: Blob.
    * Red: Text.
    * Green: Real.
    * Blue: Integer.

To make the structure of the table schema clearer, group or ungroup a list of tables. For example, group all Billing Tables in the Customer LU.

[Click for more information about Grouping and Ungrouping LU Tables](/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md).

### Logical Unit (LU) Tabs

<table style="width: 900px;">
<tbody>
<tr style="mso-yfti-irow: 0; mso-yfti-firstrow: yes; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td width="170pxl">
<p><a href="04_LU_properties.md"><strong>Properties Tab</strong></a></p>
</td>
<td width="630pxl">
<p>Contains the properties on an LU level.</p>
</td>
</tr>
<tr style="mso-yfti-irow: 1; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><strong><a href="15_LU_schema_edit_reference_tab.md">References Tab</a></strong></p>
</td>
<td style="width: 414px;">
<p>Used to configure the Reference Tables that are accessible from this LU.</p>
</td>
</tr>
<tr style="mso-yfti-irow: 2; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><a href="14_edit%20enrichment%20order.md"><strong>Enrichment Order Tab</strong></a></p>
</td>
<td style="width: 414px;">
<p>Defines the execution order of Enrichment functions using the up and down arrows. Only Eenrichment functions that are directly related to specific LU tables are included in the display.</p>
</td>
</tr>
<tr style="mso-yfti-irow: 3; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><a href="/articles/05_DB_interfaces/03_DB_interfaces_overview.md"><strong>DB Objects Tab</strong></a></p>
</td>
<td style="width: 414px;">
<p>Displays the list of objects in the source database according to the DB Interface selected in the DB Connection field.</p>
<p>DB objects can be refreshed by clicking the Refresh icon adjacent to the <a href="/articles/05_DB_interfaces/04_creating_a_new_database_interface.md">DB Connection</a> dropdown list.</p>
<p>Dragging an object to a diagram creates a new table in the schema. &nbsp;</p>
</td>
</tr>
<tr style="mso-yfti-irow: 4; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><strong>Objects Tab</strong></p>
</td>
<td style="width: 414px;">
<p>Displays the list of all <a href="/articles/06_LU_tables/01_LU_tables_overview.md">LU tables </a> defined for the LU. Drag the table into the Logical Unit diagram area to add it to the LU schema.</p>
</td>
</tr>
<tr style="mso-yfti-irow: 5; mso-yfti-lastrow: yes; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><strong><a href="/articles/12_LU_navigation/01_Navigating_an_LU_schema.md#how-do-i-use-the-diagram-outline">Diagram Outline Tab</a></strong></p>
</td>
<td style="width: 414px;">
<p>Displays an overview of the LU structure and enables searching for a subset of tables included in the schema.</p>
</td>
</tr>
</tbody>
</table>




### Logical Unit (LU) Schema Window Toolbar
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
