# Fabric project Tree

A Fabric project has a tree-like structure known as the Project Tree which displays components in a hierarchical order. Each entity in the tree initiates different actions.\
To initiate an action, right click a component and select the action. For example, to create a new function right click Functions in the Project Tree.\
The Project Tree is displayed on the left side of the Fabric Studio window.

**Click for more information about UI Components and Menus.**\
**Click for more information about Creating a New Project.** 

### Project Tree Components
 
The name of the current active project in the studio is displayed at the top of the project’s tree and the related Git/Svn branch, in the example above master is used.
Each project has the following main components: 
* **Shared Objects** used by all Logical Units and project references and may include the following sub-branches:
  * Globals 
  * Environments 
  * Database types 
  * Interfaces 
  * Functions 
  * Java 
  * Resources, files that can be saved as part of a project. For example, an Excel file.
  * Translations 
  * Broadway

* **References**, reference information that can be used throughout the LU instances and may include the following sub-branches:
  * Java, Globals and Functions
  * Resources
  * Translation
  * Tables
  * IID Finder

* **Web Services**, a collection of functions that can be exposed through Fabric’s Web Service layer which may include the following sub-components:
  * Java
  * Resources, files and Graphit objects.

* **Logical Units**, a collection of all Logical Units defined in the project.


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
