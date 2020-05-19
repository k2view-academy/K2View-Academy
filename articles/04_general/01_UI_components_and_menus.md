# UI Components and Menus

### What is the Fabric Studio User Interface?
Fabric Studio is a designer tool that manages the construction of Digital Entities. As an integrated tool, Fabric allows you to implement mapping rules into a Project and to deploy your implementation to the Fabric Server.

Built over a Windows-based platform, Fabric Studio offers a user friendly look-and-feel development environment known as the Implementation Layer. Each Project is initiated and maintained through this platform and working space.\
As an integrated tool, Fabric Studio allows you to:
* Design Digital Entities and their associated Logical Units (LUs).
* Use robust ETL options to define the mapping logic on each LU Table using various transformation objects like Functions, Globals or Translations.
* Deploy a Fabric Project or selected components of a Project to the Fabric runtime environment. 
* Debug and view mapped data, data flow and data exposure. 

Fabric Studio also enables you to integrate your Projects into Source Version Control systems based on Git or the Apache Sub-version (SVN) Standard.

**Click for more information about Best Practices for Working with SVN and Git.** 

### How Can I Use the Fabric Studio Start Page?

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/images/04_01_01_start_a_page.png)

The Start Page has links to different K2View Fabric Projects, including: 
* Start a new Project, with or without configuration control. 
* Open an existing Project.
* Checkout a Project from the configuration control.
* Quick links to recently opened projects. 
* Help and User Guide. 

**Click for more information about Creating a New Project.**

Click the K2View icon on the top left corner of the Studio to restore, move, size, minimize, maximize or close the Fabric Studio.

### Fabric Studio Toolbar Tabs

<table>
<tbody>
<tr>
<td width="57">&nbsp;<img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/04_general/images/04_01_02_icon1.png" alt="" /></td>
<td width="161">
<p>file</p>
</td>
</tr>
<tr>
<td width="57">&nbsp;</td>
<td width="161">
<p>New Item (CTRL+N)</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="57">&nbsp;</td>
<td width="161">
<p>Project Tree (CTRL+T)</p>
</td>
</tr>
<tr>
<td width="57">&nbsp;</td>
<td width="161">
<p>Query Builder</p>
</td>
</tr>
<tr>
<td width="57">&nbsp;</td>
<td width="161">
<p>Tools</p>
</td>
</tr>
<tr>
<td width="57">&nbsp;</td>
<td width="161">
<p>Server Activity</p>
</td>
</tr>
<tr>
<td width="57">
<p>&nbsp;</p>
</td>
<td width="161">
<p>Help (F1 for User Guide)</p>
</td>
</tr>
</tbody>
</table>


**Click for more information about the Query Builder.**\
**Click for more information about the Studio Debug and Log Capabilities.**

### What are the File Tab Options?
The File tab has the following options:
* New Project. 
* Open Project. 
* Checkout Project (from SVN or Git repository). 
* Close Project. 
* Recent Projects.

### New Item Tab Options
Click **New Item** to open a **New Item** under a selected **LU** or Shared **Objects**:
* Select a **LU** from the current **Logical Unit** dropdown list to create the new item under the selected **LU**. For example, a new _Function_, LU Table, _Interface_ or _Global_.
* To create a new item under _Shared Objects_, the current Logical Unit dropdown list should be blank.

Note that this area allows you to open a new item only for LU or Shared Objects. 

### What are the Main Elements of the Project Tree? 
The Project Tree displays the components of the current Project as a hierarchy of entities. The main elements of the hierarchy include:
* Project, the top-level of the hierarchy. Each Project has the following branches (sub-objects): 
  * Shared Objects used by all Logical Units and Project references and may include a sub-hierarchy, as follows:
    * Globals. 
    * Environments. 
    * Database types. 
    * Interfaces. 
    * Functions. 
    * Java, Java files and resource files. 
    * Translations. 
* _References_, reference information that may be used throughout the instance. 
* Web Services, a collection of all functions that are defined as Web Services for this Project.
* Logical Units, a collection of all Logical Units defined in the Project.

**Note:** All operations performed in the Project Tree’s options are accessible from the context menus of the different components in the Project Tree. For example, creating a new function is accessed from the context menu when you right click the Functions object in the Project Tree.







