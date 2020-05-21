# Navigating an LU Schema

When creating or editing a [Logical Unit Schema](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md) the following options are used to navigate, configure and display information:
* [Diagram Outline](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/12_LU_navigation/01_Navigating_an_LU_schema.md#how-do-i-use-the-diagram-outline) tab, a representation of the LU Schema table’s list.
* [Navigation pane](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/12_LU_navigation/01_Navigating_an_LU_schema.md#what-is-the-navigation-pane), an overview of the LU Schema located in the right corner of the working area.
* LU working area, where view options can be set.


### How do I Use the Diagram Outline?
 
The Diagram Outline displays an overview of the LU structure and can be used to search for a subset of tables included in the Schema. 

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/12_LU_navigation/images/10_01_01.jpg)

To search for an LU table in an LU Schema:
1.	Open the **LU Schema** window. 
2.	Click the **Diagram Outline** tab in the right panel.
3.	In the **Search** field, type the **name** of the **table** or **column** and then click it  in the displayed list. The table or column is highlighted with a green border.

### What is the Navigation Pane? 
 
The Navigation pane in the right corner of an LU schema is used to move between the different areas of a schema. This is useful when looking at large Logical Units with many tables or hierarchy levels.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/12_LU_navigation/images/10_01_02.jpg)

### What are the LU Wrorking Area View Options?
 
LU Tables relationship options:\
In the LU working area, right click an LU Table object to display a context menu with the following actions:

<table width="595">
<tbody>
<tr>
<td width="300pxl">
<p><strong>set as: root&nbsp;</strong></p>
</td>
<td width="600pxl">
<p>Sets the table object as the root of this LU.</p>
</td>
</tr>
<tr>
<td width="283">
<p><strong>show only Predecessor nodes</strong></p>
</td>
<td width="312">
<p>Shows only objects and links that are predecessors of this object / field.</p>
</td>
</tr>
<tr>
<td width="283">
<p><strong>show only successor nodes</strong></p>
</td>
<td width="312">
<p>Shows only objects and links that are successors of this object / field.</p>
</td>
</tr>
<tr>
<td width="283">
<p><strong>show only connected nodes</strong></p>
</td>
<td width="312">&nbsp;</td>
</tr>
<tr>
<td width="283">
<p><strong>show all nodes</strong></p>
</td>
<td width="312">
<p>Shows all objects and links in this table.</p>
</td>
</tr>
<tr>
<td width="283">
<p><strong>clear input links</strong></p>
</td>
<td width="312">
<p>Clears all input links from objects in the Schema.</p>
</td>
</tr>
<tr>
<td width="283">
<p><strong>clear output links</strong></p>
</td>
<td width="312">
<p>Clears all output links from this LU Table to its children Table Populations.</p>
</td>
</tr>
<tr>
<td width="283">
<p><strong>clear all links</strong></p>
</td>
<td width="312">
<p>Clears all input and output links in the LU Tables.</p>
</td>
</tr>
<tr>
<td width="283">
<p><strong>show data</strong></p>
</td>
<td width="312">
<p>When clicked, a Query Builder window opens automatically in the newest LU file.</p>
<p>Click for more information about the Data Viewer.</p>
</td>
</tr>
</tbody>
</table>


**For Example**


Taking the CONTRACT table from the CUSTOMER LU as an example, the following illustrates key table relationship view options:

Show only predecessor nodes

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/12_LU_navigation/images/10_01_03%20predecessor%20nodes.jpg)


Show only successor nodes
![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/12_LU_navigation/images/10_01_04%20successor%20nodes.jpg)


Show only connected nodes
![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/12_LU_navigation/images/10_01_05%20connected%20nodes.jpg)


**Display Options** 
 
In the LU working area right click anywhere to display a context menu with the following actions:

<table width="595">
<tbody>
<tr>
<td width="198pxl">
<p><strong>Collapse All Views</strong></p>
</td>
<td width="700pxl">
<p>Collapses the LU Tables columns.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Expand All Views</strong></p>
</td>
<td width="397">
<p>Expands the LU Tables display.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>show all nodes</strong></p>
</td>
<td width="397">
<p>Shows all objects and links in the LU Schema.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Automatic Layout</strong></p>
</td>
<td width="397">
<p>Arranges table items in an orderly manner.</p>
</td>
</tr>
<tr>
<td width="198">
<p><strong>Automatic Layout and Fit</strong></p>
<p>&nbsp;</p>
</td>
<td width="397">
<p>Arranges table items in an orderly manner and adjusts the Table Population resolution to fit the screen.</p>
</td>
</tr>
</tbody>
</table>

[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/12_LU_navigation/02_searching_a_fabric_project.md)
