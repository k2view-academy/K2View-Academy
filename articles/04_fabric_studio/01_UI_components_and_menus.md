# UI Components and Menus

### What Is the Fabric Studio User Interface?
Fabric Studio is a designer tool that manages the construction of [Digital Entities](/articles/01_fabric_overview/02_fabric_glossary.md#digital-entity). As an integrated tool, Fabric allows you to implement mapping rules into a project and to deploy your implementation to the Fabric Server.

Built over a Windows-based platform, Fabric Studio offers a user friendly look-and-feel development environment known as the Implementation Layer. Each project is initiated and maintained through this platform and working space. 

As an integrated tool, Fabric Studio allows you to:
* Design [Digital Entities](/articles/01_fabric_overview/02_fabric_glossary.md#digital-entity) and their associated [Logical Units (LUs)](/articles/03_logical_units/01_LU_overview.md).
* Use robust ETL options to define the mapping logic on each LU table using various transformation objects like [Functions](/articles/07_table_population/08_project_functions.md), [Globals](/articles/08_globals/01_globals_overview.md) or [Translations](/articles/09_translations/01_translations_overview_and_use_cases.md).
* Deploy a [Fabric project](/articles/04_fabric_studio/05_creating_a_new_project.md) or selected components of a project to the Fabric runtime environment. 
* [Debug and view](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md) mapped data, data flow and data exposure. 

Fabric Studio also enables you to integrate your projects into Source Version Control systems based on Git or the Apache Sub-version (SVN) Standard.

[Click for more information about Best Practices for Working with SVN and Git.](/articles/04_fabric_studio/07_best_practices_for_working_with_GIT_and_SVN.md)

### How Can I Use the Fabric Studio Start Page?

![image](/articles/04_fabric_studio/images/04_01_01_start_a_page.png)

The Start Page has links to different K2View Fabric projects, including: 
* Start a new project, with or without configuration control. 
* Open an existing project.
* Checkout a project from the configuration control.
* Quick links to recently opened projects. 
* Help and Knowledge Base. 

[Click for more information about Creating a New Project.](/articles/04_fabric_studio/05_creating_a_new_project.md)

Click the K2View icon on the top left corner of the Studio to restore, move, size, minimize, maximize or close the Fabric Studio.

### Fabric Studio Toolbar Tabs

<table>
<tbody>
<tr>
 <td width="60pxl" valign="top"><p><img src="images/04_01_02_icon1.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>file</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p><img src="images/04_01_02_icon2.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>New Item (CTRL+N)</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p><img src="images/04_01_02_icon3.png" alt="" /></p></td>
<td width="600pxl" valign-"top">
<p>Project Tree (CTRL+T)</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p><img src="images/04_01_02_icon4.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Query Builder</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p><img src="images/04_01_02_icon5.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Tools</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p><img src="images/04_01_02_icon6.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Server Activity</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p><img src="images/04_01_02_icon7.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Help (link to the Knowledge Base)</p>
</td>
</tr>
</tbody>
</table>



[Click for more information about the Query Builder.](/articles/11_query_builder/01_query_builder_overview.md#query-builder-overview)

Click for more information [Fabric Studio log file](/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md).

### Fabric Studio Debug Panel

Fabric Studio has the debug panel in the left upper side of the window:

![debug toolbar](images/fabric_studio_debug_panel.png)

 The debug panel enables an easy access to the Fabric debug server, Cassandra debug server, and the Fabric Web Admin. The debug panel is divided into two main areas: Fabric and Cassandra:

<table>
<tbody>
<tr>
<td colspan="2" width="660pxl">
<p><h4>Fabric</p>
</td>
</tr>    
<tr>
<td width="60pxl" valign="top"><p>
    <img src="images/deploy_LUs_to_debug_server.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Deploy all updated LUs to Fabric debug server</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p>
    <img src="images/open_debug_server_or_cassandra.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Open Fabric console. Opens the console of the debug Fabric server with the appropriate port for each Fabric project.</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p>
    <img src="images/open_web_admin.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Open the Fabric Web Admin on the localhost.</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top">
<p><img src="images/start_fabric.png" alt="" /></p>
<p><img src="images/stop_cassandra_or_fabric.png" alt="" /></p>
</td>
<td width="600pxl" valign="top">
<p>Start and stop Fabric debug console.</p>
</td>
</tr>
<tr>
<td width="60pxl" valign="top"><p>
    <img src="images/run_reset_drop.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Run <a href="/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#reset-fabric">Reset Drop</a> on the Fabric debug server.</p>
</td>
</tr>
<tr>
<td colspan="2" width="660pxl">
<p><h4>Cassandra</p>
</td>
</tr>    
<tr>    
<tr>
<td width="60pxl" valign="top"><p>
    <img src="images/open_debug_server_or_cassandra.png" alt="" /></p></td>
<td width="600pxl" valign="top">
<p>Open Cassandra console for <a href="/articles/02_fabric_architecture/07_cassandra_basic_commands.md#cassandra-useful-commands">cqlsh commands.</a></p>
</td>
<tr>
<td width="60pxl" valign="top">
<p><img src="images/start_fabric.png" alt="" /></p>
<p><img src="images/stop_cassandra_or_fabric.png" alt="" /></p>
</td>
<td width="600pxl" valign="top">
<p>Start and stop Cassandra console.</p>
</td>
</tr>    
</tr>
</tbody>
</table>

### What Are the File Tab Options?

The File tab has the following options:
* New Project. 
* Open Project. 
* Checkout Project (from SVN or Git repository). 
* Close Project. 
* Recent Projects.

### New Item Tab Options
Click **New Item** to open a **New Item** under a selected **LU** or **Shared Objects**:
* Select an **LU** from the current **Logical Unit** dropdown list to create the new item under the selected **LU**. For example, a new [Function](/articles/07_table_population/11_3_creating_an_LUDB_function.md), [LU Table,](/articles/06_LU_tables/01_LU_tables_overview.md) [Interface](/articles/05_DB_interfaces/01_interfaces_overview.md) or [Global](/articles/08_globals/01_globals_overview.md).
* To create a new item under [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md), the current Logical Unit dropdown list should be blank.

Note that this area allows you to open a new item only for LU or Shared Objects. 

### What Are the Main Elements of the Project Tree? 
The Project Tree displays the components of the current project as a hierarchy of entities. The main elements of the hierarchy include:
* Project, the top-level of the hierarchy. Each project has the following branches (sub-objects): 
  * [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md) used by all Logical Units and project references and may include a sub-hierarchy, as follows:
    * [Globals](/articles/08_globals/01_globals_overview.md). 
    * Environments. 
    * Database types. 
    * [Interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md). 
    * [Functions](/articles/07_table_population/08_project_functions.md).
    * Java, [Java files](/articles/04_fabric_studio/09_logic_files_and_categories.md) and resource files. 
    * [Translations](/articles/09_translations/01_translations_overview_and_use_cases.md). 
    * [Broadway flows](/articles/19_Broadway/01_broadway_overview.md). 
* References, reference information that may be used throughout the instance. 
* [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md), a collection of all functions that are defined as Web Services for this project.
* [Logical Units](/articles/03_logical_units/01_LU_overview.md), a collection of all Logical Units defined in the project.

**Note:** All operations performed in the project tree’s options are accessible from the context menus of the different components in the project tree. For example, creating a new function is accessed from the context menu when you right click the Functions object in the project tree.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/04_fabric_studio/02_window_tab_context_menu.md)





