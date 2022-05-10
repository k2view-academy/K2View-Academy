# Fabric Project Tree

A Fabric project has a tree-like structure known as the Project Tree which displays components in a hierarchical order. Each entity in the tree initiates different actions.

The Project Tree is displayed on the left side of the Fabric Studio window.

To initiate an action, right click a component and select the action. For example, to create a new flow right click Broadway in the Project Tree.

![image](/articles/13_LUDB_viewer_and_studio_debug_capabilities/images/Logical_Units_Tree.png)

### Project Tree Components

The name of the currently active project in the Studio is displayed at the top of the project’s tree and the related Git/SVN branch. In the example above the main branch is used.

Each project has the following main components: 

* [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md) used by all Logical Units and project references and may include the following sub-branches:
  * [Broadway](/articles/19_Broadway/01_broadway_overview.md)

  * [Database types](/articles/05_DB_interfaces/03_DB_interfaces_overview.md), [Interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md) 

  * [Java Functions](/articles/07_table_population/08_project_functions.md), [Globals](/articles/08_globals/01_globals_overview.md) 

  * Resources, files that can be saved as part of a project. 

  * Environments 

  * Templates

  * [Translations](/articles/09_translations/01_translations_overview_and_use_cases.md) 


* **Logical Units**, a collection of all [Logical Units](/articles/03_logical_units/01_LU_overview.md) defined in the project.

* **References**, reference information that can be used throughout the LU instances and may include the following sub-branches:
  * Broadway

  * Java, Functions, Globals, Resources

  * [Tables](/articles/07_table_population/01_table_population_overview.md)

  * Translation

  * IID Finder

* **Web Services**, a collection of functions that can be exposed through Fabric’s Web Service layer which may include the following sub-components:

  * Broadway
  * Java
  * Resource files and Graphit objects.

### Project Components Prefix Conventions 

Using a common prefix for each project component improves maintenance throughout a shared implementation.

The following lists the recommended project component prefixes conventions:

<table>
<tbody>
<tr>
<td width="200">
<p><Strong>Component</Strong></p>
</td>
<td width="250">
<p><Strong>Recommended Prefix</Strong></p>
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
<p>Input Parameter</p>
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
<p>Web Services</p>
</td>
<td width="136">
<p>ws%</p>
</td>
</tr>
<tr>
<td width="166">
<p>Table Population</p>
</td>
<td width="136">
<p>pop%</p>
</td>
</tr>
<tr>
<td width="166">
<p>Instance Group</p>
</td>
<td width="136">
<p>Ig%</p>
</td>
</tr>
</tbody>
</table>


Note that when using the above prefix conventions, it is also recommended to use meaningful names for the project’s components.

<studio>

[![Previous](/articles/images/Previous.png)](05_creating_a_new_project.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_adding_fabric_projects_to_version_control.md)

</studio>

<web>

[![Previous](/articles/images/Previous.png)](05_creating_a_new_project.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_fabric_studio_exporting_and_importing%20a_fabric_project.md.md)

</web>

