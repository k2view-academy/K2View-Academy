# Data Catalog Navigation

Data Catalog's UI enables the users to move through a data model’s graph, move from one level to another and filter specific elements. 

### How Do I Access the Data Catalog?

The Data Catalog is accessed from the [K2View Web Framework](/articles/30_web_framework/01_web_framework_overview.md). Select the **Data Catalog** application from the Web Framework context menu to display the circle representing your project. If multiple projects were deployed to the same OrientDB database, all of them will be displayed.

<img src="images/33_02_proj.PNG" alt="image" style="zoom:80%;" />

### Data Catalog's Legend

Legend is located in the down right corner of the screen and provides a visual description of each graph's component. The graph's data types can be split into the following categories:

<table style="height: 116px; width: 700px;">
<tbody>
<tr style="height: 18px;">
<td style="width: 100px;"><strong>Category</strong></td>
<td style="height: 18px; width: 200px;"><strong>Data Type</strong></td>
<td style="height: 18px; width: 400px;"><strong>Description</strong></td>
</tr>
<tr style="height: 18px;">
<td style="width: 120px;"><strong>Project</strong></td>
<td style="height: 18px; width: 226px;"><img src="images/project_icon.PNG" alt="Project" /></td>
<td style="height: 18px; width: 308px;">Project name</td>
</tr>
<tr style="height: 18px;">
<td style="width: 120px;"><strong>Source Components</strong></td>
<td style="height: 18px; width: 226px;">
<img src="images/interface_icon.PNG" alt="Interface" />
</td>
<td style="height: 18px; width: 308px;">Data Source components</td>
</tr>
<tr style="height: 28px;">
<td style="width: 120px;" rowspan="3"><strong>Target Components</strong><br /><strong><br /></strong><strong><br /></strong></td>
<td style="width: 226px; height: 28px;"><img src="images/schema_icon.PNG" alt="Schema" /></td>
<td style="width: 308px; height: 28px;">
<p>Project's entity (LU, Web Services or Common)</p>
</td>
</tr>
<tr>
<td style="width: 226px;"><img src="images/table_col_icon.PNG" alt="TableCol" /></td>
<td style="width: 308px;">
<p>LU's tables and columns</p>
</td>
</tr>
<tr>
<td style="width: 226px;"><img src="images/api_icon.PNG" alt="API" /></td>
<td style="width: 308px;">
<p>Web service and its fields</p>
</td>
</tr>
<tr style="height: 18px;">
<td style="width: 120px;" rowspan="3"><strong>Connection Lines</strong><br /><strong><br /></strong><strong><br /></strong></td>
<td style="height: 18px; width: 226px;"><img src="images/contained_icon.PNG" alt="CONTAINED" /></td>
<td style="height: 18px; width: 308px;">In which data entity the current entity is contained. For example, a table is contained schema (LU).</td>
</tr>
<tr style="height: 16px;">
<td style="height: 16px; width: 226px;"><img src="images/use_icon.PNG" alt="USE" /></td>
<td style="height: 16px; width: 308px;">Which data entity the current entity uses. For example, a TABLE (an LU table) uses an INTERFACE_TABLE.</td>
</tr>
<tr>
<td style="width: 226px;"><img src="images/pop_keys_icon.PNG" alt="POPULATION_KEYS" /></td>
<td style="width: 308px;">How the current table is populated in the LU schema. For example, when the relation is ACTIVITY -&gt; CUSTOMER, it means that the ACTIVITY table is populated using the key from CUSTOMER table.</td>
</tr>
</tbody>
</table>

To clear data elements from the tree or add them,  unselect or select the data types in the Legend. For example, if COLUMN is unselected, the Column data elements will disappear from the tree.

### How Do I Navigate Through the Data Catalog?

The navigation through the Data Catalog is performed using the following tools:

* Search the required data element using the **Search** area in the top left corner of the screen. The search is performed using the *search-as-you-type* field type. The search results are presented in a table, enabling the user to either add an entity to the tree or to replace the tree focus to the selected entity.

  ![image](images/33_02_search.PNG)

* Change the tree view by using the icons in the top right corner of the screen:

  ![image](images/33_02_view.PNG)

  * Display the tree horizontally, vertically or using a centralized view by clicking one of the <img src="images/33_02_tree.PNG" alt="image" style="zoom: 67%;" />icons. 
  * Zoom the tree to fit the screen by clicking the <img src="images/33_02_fit.PNG" alt="image" style="zoom: 67%;" /> icon.
  * Return to the collapsed project by clicking the <img src="images/33_02_home.PNG" alt="image" style="zoom: 67%;" /> icon.

* Move to lower hierarchy level by double-clicking on the data element. For example, double click on the Table data element will display all Column data elements related to it.

* Click on any of the data elements or the connection lines to display its properties, such as name, ID or type in the Properties screen. Click anywhere in the empty screen area to hide the Properties screen.

* Expand the tree, focus on specific node or clear the node or the connection line by selecting the option either from the Properties screen or from the right click context menu. Clear multiple data elements can be done once they are selected using right click > hold and drag the line.

  <img src="images/33_02_select.PNG" alt="image" style="zoom: 55%;" />

  


[![Previous](/articles/images/Previous.png)](04_data_catalog_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_override_data_catalog.md) 