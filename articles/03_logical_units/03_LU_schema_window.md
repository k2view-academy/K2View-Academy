# LU Schema Window

### Logical Unit (LU) - Related Objects
A Fabric project has a tree-like structure known as the Project Tree which displays entities in a hierarchical order. The Project Tree is displayed on the left side of the K2View Fabric Studio window where its Logical Units (LU) are located under the Logical Units branch.

The following objects are located under each [Logical Unit (LU)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/01_LU_overview.md) in the Project Tree:
* [Schema](https://github.com/k2view-academy/K2View-Academy/wiki/Logical-Unit-Schema-Window#logical-unit-lu-schema)
* Java - Globals and Functions
* Resources, files that can be saved as part of a project. For example, an Excel file
* Translations
* Tables
* Parsers
* Instance Groups: lists of instances to be migrated into Fabric for the LU 
* Broadway
* Jobs
* IIDFinder 

### Opening the Logical Unit (LU) Window
Go to the **Project Tree**, click the **LU Name** and then click the **Schema** to open the **Logical Unit** window.

### Logical Unit (LU) Window
![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/images/1.3_LU_Schema_WIndow.PNG)


The Logical Unit window has three main sections:
* [Logical Unit schema.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-schema)
* [Logical Unit tabs.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-tabs)
* [Logical Unit toolbar.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md#logical-unit-lu-schema-window-toolbar) 

### Logical Unit (LU) Schema
The Logical Unit Schema is a work area where you can define the logical unit’s structure. 
* A Logical Unit Schema displays a hierarchical representation of all business entities related to a Root Table. 
* Each table can have one or several populations where each population is linked to its parent table.


[Click for more information about Logical Unit Tables.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/02_LU_tables/01_LU_tables_overview.md)

[Click for more information about LU Table Population.](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/12_LU_hierarchy_and_linking_table_population.md)

### Customer LU Schema Example 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/images/1.4_LU_schema_example.png)

The Logical Unit Schema window shows the Root Table and hierarchy of the tables included in the Logical Unit and the relationships between them:
* The Root Table of the LU Schema is Customer. 
* Each table is displayed with all of its fields in a scrollable table.
* Each field is displayed in a separate line, by name with a small colored icon. The icon’s color indicates the data type of the field:

    * Grey: Blob
    * Red: Text
    * Green: Real
    * Blue: Integer

To make the structure of the table schema clearer, group or ungroup a list of tables. For example, group all Billing Tables in the Customer LU.

[Click for more information about Grouping and Ungrouping LU Tables](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/16_LU_schema_group_and_ungroup_tables.md).

### Logical Unit (LU) Tabs

<table style="width: 547px;">
<tbody>
<tr style="mso-yfti-irow: 0; mso-yfti-firstrow: yes; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/04_LU_properties.md"><b>Properties Tab<b></a>
</td>
<td style="width: 414px;">
<p>Contains the properties on an LU level.</p>
</td>
</tr>
<tr style="mso-yfti-irow: 1; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><strong><a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/15_LU_schema_edit_reference_tab.md">References Tab</a></p>
</td>
<td style="width: 414px;">
<p>Used to configure the Reference Tables that are accessible from this LU.</p>
</td>
</tr>
<tr style="mso-yfti-irow: 2; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/14_edit%20enrichment%20order.md"><b>Enrichment Order Tab<b></p>
</td>
<td style="width: 414px;">
<p>Defines the execution order of enrichment functions  using the up and down arrows. Only enrichment functions that are directly related to specific LU tables are included in the display. </p>

</td>
</tr>
<tr style="mso-yfti-irow: 3; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><strong>DB Objects Tab</strong></p>
</td>
<td style="width: 414px;">
<p>&middot;&nbsp;&nbsp;&nbsp; Displays the list of objects in the source database according to the DB Interface selected in the <a href="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/06_auto_discovery_wizard.md#step-1-define-the-db-interface">DB Connection &nbsp;</a> field.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; DB objects can be refreshed by clicking the Refresh icon adjacent to the DB Connection dropdown list.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; Dragging an object to a diagram creates a new table in the schema. &nbsp;</p>
</td>
</tr>
<tr style="mso-yfti-irow: 4; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><strong>Objects Tab</strong></p>
</td>
<td style="width: 414px;">
<p>Displays the list of all  LU tables  defined for the LU. Drag the table into the Logical Unit diagram area to add it to the LU Schema.</p>
</td>
</tr>
<tr style="mso-yfti-irow: 5; mso-yfti-lastrow: yes; mso-prop-change: 'Einav Velan' 20200412T1629;">
<td style="width: 119px;">
<p><strong>Diagram Outline Tab</strong></p>
</td>
<td style="width: 414px;">
<p>Displays an overview of the LU structure and enables searching for a subset of tables included in the schema.</p>
</td>
</tr>
</tbody>
</table>



### Logical Unit (LU) Schema Window Toolbar
By default, the LU Diagram window displays the following toolbar:

![image](https://k2vacademy.s3.amazonaws.com/Fabric/1_LU_Schema_and_Overview/1.3_Logical_Unit_Schema_Window/1.3_LU_Schema_Icons.png)

You can customize your window by adding or deleting toolbars to/from diagram windows:

<table>
<tbody>
<tr>
<td width="60">&nbsp; <img src="https://k2vacademy.s3.amazonaws.com/Fabric/1_LU_Schema_and_Overview/1.3_Logical_Unit_Schema_Window/1.3_logical_unit_schema_window_table_icon_1.png" alt="" /></td>
<td width="557">
<p>Group SubGraph.</p>
</td>
</tr>
<tr>
<td width="60">&nbsp; <img src="https://k2vacademy.s3.amazonaws.com/Fabric/1_LU_Schema_and_Overview/1.3_Logical_Unit_Schema_Window/1.3_logical_unit_schema_window_table_icon_2.png"/></td>
<td width="557">
<p>Ungroup SubGraph.</p>
</td>
</tr>
<tr>
<td width="60">&nbsp;<img src="https://k2vacademy.s3.amazonaws.com/Fabric/1_LU_Schema_and_Overview/1.3_Logical_Unit_Schema_Window/1.3_logical_unit_schema_window_table_icon_3.png" alt="" /></td>
<td width="557">
<p>Update Tables from Database.</p>
<p>When clicked, the LU Tables Schema is refreshed from the source database. This action is needed if the LU Tables Schema has been updated in the source database.</p>
<p>Note: only current tables are updated. Tables are added or deleted from an LU manually.</p>
</td>
</tr>
<tr>
<td width="60">&nbsp; <img src="https://k2vacademy.s3.amazonaws.com/Fabric/1_LU_Schema_and_Overview/1.3_Logical_Unit_Schema_Window/1.3_logical_unit_schema_window_table_icon_4.png" alt="" </td>
<td width="557">
<p>Refresh Items, refreshes items from an implementation into the LU schema.</p>
</td>
</tr>
</tbody>
</table>


[Click for more information about Group and Ungroup SubGraph Objects.](https://github.com/k2view-academy/K2View-Academy/wiki/LU-Schema---Group-and-Ungroup-Tables)

Click for more information about Fabric Studio Diagrams Toolbars.

[Click for more information about LU Refresh Options.](https://github.com/k2view-academy/K2View-Academy/wiki/LU-Schema-Refresh-Options)
