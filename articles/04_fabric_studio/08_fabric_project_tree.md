# Fabric Project Tree

A Fabric project has a tree-like structure known as the Project Tree which displays components in a hierarchical order. Each entity in the tree initiates different actions.\
To initiate an action, right click a component and select the action. For example, to create a new function right click Functions in the Project Tree.\
The Project Tree is displayed on the left side of the Fabric Studio window.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/images/04_08_01%20fabric%20studio%20window.jpg)

[Click for more information about UI Components and Menus.](https://github.com/k2view-academy/K2View-Academy/blob/fa76f26211c76653e78f8848f33146529ff2ce41/articles/04_fabric_studio/01_UI_components_and_menus.md)

[Click for more information about Creating a New Project.](https://github.com/k2view-academy/K2View-Academy/blob/fa76f26211c76653e78f8848f33146529ff2ce41/articles/04_fabric_studio/05_creating_a_new_project.md) 

### Project Tree Components
 
The name of the current active project in the studio is displayed at the top of the project’s tree and the related Git/Svn branch, in the example above master is used.
Each project has the following main components: 
* **[Shared Objects](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_fabric_studio/12_shared_objects.md)** used by all Logical Units and project references and may include the following sub-branches:
  * [Globals](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/01_globals_overview.md) 
  * Environments 
  * [Database types](https://github.com/k2view-academy/K2View-Academy/blob/fa76f26211c76653e78f8848f33146529ff2ce41/articles/05_DB_interfaces/03_DB_interfaces_overview.md) 
  * [Interfaces](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/05_DB_interfaces/01_interfaces_overview.md) 
  * [Functions](https://github.com/k2view-academy/K2View-Academy/blob/fa76f26211c76653e78f8848f33146529ff2ce41/articles/07_table_population/08_project_functions.md) 
  * [Java](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/06_table_population_transformation_rules.md) 
  * Resources, files that can be saved as part of a project. For example, an Excel file.
  * [Translations](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/09_translations/01_translations_overview_and_use_cases.md) 
  * Broadway

* **References**, reference information that can be used throughout the LU instances and may include the following sub-branches:
  * Java, Globals and Functions
  * Resources
  * Translation
  * [Tables](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md)
  * IID Finder

* **Web Services**, a collection of functions that can be exposed through Fabric’s Web Service layer which may include the following sub-components:
  * Java
  * Resources, files and Graphit objects.

* [**Logical Units**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/01_LU_overview.md), a collection of all Logical Units defined in the project.


### Project Components Prefix Coventions 
 
Using a common prefix for each project component improves maintenance throughout a shared implementation.\
The following lists the recommended project component prefixes conventions:

<table>
<tbody>
<tr>
<td width="200">
<p>Component</p>
</td>
<td width="250">
<p>Recommended Prefix</p>
</td>
</tr>
<tr>
<td width="166">
<p>Function</p>
</td>
<td width="136">
<p>fn%</p>
</td>
</tr>
<tr>
<td width="166">
<p>Input parameter</p>
</td>
<td width="136">
<p>i_%</p>
</td>
</tr>
<tr>
<td width="166">
<p>Output Parameters</p>
</td>
<td width="136">
<p>o_%</p>
</td>
</tr>
<tr>
<td width="166">
<p>web services</p>
</td>
<td width="136">
<p>ws%</p>
</td>
</tr>
<tr>
<td width="166">
<p>table Population</p>
</td>
<td width="136">
<p>pop%</p>
</td>
</tr>
<tr>
<td width="166">
<p>Parser</p>
</td>
<td width="136">
<p>par%</p>
</td>
</tr>
<tr>
<td width="166">
<p>translation</p>
</td>
<td width="136">
<p>trn%</p>
</td>
</tr>
<tr>
<td width="166">
<p>Instance group</p>
</td>
<td width="136">
<p>Ig%</p>
</td>
</tr>
</tbody>
</table>


Note that when using the above prefix conventions, it is also recommended to use meaningful names for the project’s components.


[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/07_best_practices_for_working_with_GIT_and_SVN.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/09_logic_files_and_categories.md)
