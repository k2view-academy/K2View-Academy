# Data Viewer

The Data Viewer enables you to view a [Logical Unit](/articles/03_logical_units/01_LU_overview.md) database (LUDB), add debugging capabilities and improve testing and defect resolution times. 

Since an LUDB is in-memory, it can be viewed by dumping it into an SQLite file which can be shared via email or a common file directory for additional investigations using the Fabric Studio. This file can also be used to execute SQL queries and for analysis. 
Data Viewer files are saved under the LU VirtualDB_Data directory in:   \Fabric\\[project name]\\Implementation\LogicalUnits\\[LU name]\VirtualDB_Data.

[Click for more information about Logical Units.](/articles/03_logical_units/01_LU_overview.md)

Note that the Data Viewer runs on the Fabric local server. Therefore you must deploy the LU to the Fabric local server before running the Data Viewer on this LU.

[Click for more information about deploying LU to the Fabric debug server](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md). 

## How Do I View Data in a Logical Unit?

![image](images/Logical_Units_Tree.png)

1. Go to the **Project Tree**, click **Logical Units** and verify that the LU has a green icon, indicating that it is deployed to the debug server. If the LU has a white icon, deploy it  to the debug server before running the Data Viewer on it. Do either:

   - Right click the **LU** > **Deploy To debug**.
   - Click the **Deploy** icon on the top of the [Fabric Debug panel](/articles/04_fabric_studio/01_UI_components_and_menus.md#fabric-studio-debug-panel).

2. Click  <img src="images/13_01_02%20icon%201.jpg" alt="drawing" width="25"/> next to the selected LU to open the **Data Viewer** window.

3. Set the [Sync Mode](/articles/14_sync_LU_instance/02_sync_modes.md) in the top left pane. The default Sync mode is ON.

4. In the **Instance ID** field (top central pane) enter an Instance ID (an integer). 

5. Click ![image](images/13_01_02%20icon%202%20Play.jpg) **Play** to generate a new **Data Viewer file**. 
    Fabric runs the [GET LUI command](/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands) on the debug server of the selected Instance ID. Each [sync of LUI](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) creates a new *.db SQLite file for the LU instance. The LU instance is displayed in the Instances Tree. 

  	Note that if you set the Sync Mode to **OFF** and the Instance ID does not exist in the debug server, the following error message is displayed:
  	
  	 *Instance '[LU Name]:[Instance ID]' was not found and sync is disabled.*

6. Click the **Instance ID** to open the **Instances Tree** dropdown list.

![image](images/13_01_03%20instances%20tree.jpg)

7. Click the **Instance DB file** to display its **tables** under the **Instance DB tree**.

![image](images/13_01_04_Instance_DB_tree.jpg)

8. Double-click a **table** to display its data and then right click the **table** to open a context menu with the following options: 

   a. **Show Data**, displays the table’s data.
   
   b. **Show Schema**, displays the table’s structure.
   
   c. **Show Indexes**, displays the table’s indexes, if defined.

[Click for more information about Logical Units.](/articles/03_logical_units/01_LU_overview.md)

## What Are the Data Viewer Components?

The Logical Unit Data Viewer has the following areas:
* Sync Mode and Instance ID.
* Instances Tree.
* Instance DB Tree.
* Scripting Area.
* Results Pane and Toolbar.

![image](images/data_viewer_window.png)

### Sync Mode and Instance ID

This area has the following components:

#### Import DB File ![image](images/13_01_06%20IMPORT%20DB%20FILE%20icon.jpg)

When clicked, allows you to browse to an external Data Viewer file and load it. 

#### Sync Mode

Set the [Sync Mode](/articles/14_sync_LU_instance/02_sync_modes.md) for the [GET LUI command](/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands), initiated by the execution of the Data Viewer. The options are **On**, **Off**, and **Force**. The default mode is **On**.

#### Instance ID

To complete this field, do either:
* Enter a specific Instance ID value.
* Select a previously stored Instance ID from the dropdown list.
* Write a [project (java) function](/articles/07_table_population/08_project_functions.md) to generate the Instance ID . Note that this function must return a string as an output.


  For example:


  Create an instance ID by using the function: **fnCreateInstID**. This function takes an input value and adds 10:

  ```java
  if (i_id!=null && !i_id.isEmpty()){
	 return Integer.sum(Integer.valueOf(i_id),10)+"";
   }
  return "0";
  ```
  More complicated functions can be written, of course, such as generating a random instance ID, or an instance ID that complies with other criteria (such as certain values). 


#### Play

When ![image](images/13_01_07%20play%20icon.jpg) icon is clicked, the data file of the Instance ID is retrieved and saved for debugging.
	
### Instances Tree
The Instance Tree area (top left) displays a tree of available data files in the following order: 
* LU.
* Instance. 
* Dated file name.

### Instance DB Tree

The Instance DB Tree area (bottom left) displays the Table Tree which includes: 
* **k2_delta_errors**, holds information on errors, including when each error occured.
* **k2_main_info**, holds basic information about the LU like LU Name and Instance ID.
* **k2_objects_info**, holds information for each of the objects in the selected instance.
* **k2_transactions_info**, holds basic information about each transaction (ID and timestamp).

* **Reference tables under k2_Ref**. These are only displayed as part of the Instance DB tree when the [reference object](/articles/03_logical_units/15_LU_schema_edit_reference_tab.md) is enabled in the LU schema properties.

To display the values of a table in the tree, right click the table and select either:
* **Show Data**, to display the table or view it in the Results pane.
* **Show Schema**, to display the table structure in the Results pane.
* **Show Indexes**, to display the table indexes in the Results pane.

### Results Pane and Toolbar
(Bottom right) Displays the data or schema requested with the row count.
 <table>
<tbody>
<tr>
	<td width="60"><p><img src="images/13_01_08%20PANE%20AND%20TOOLBAR%20icon%201.jpg" alt="" /></p></td>
<td width="274">
<p>Print results.</p>
</td>
</tr>
<tr>
	<td width="60"><p><img src="images/13_01_08%20PANE%20AND%20TOOLBAR%20icon%202.jpg" alt="" /></p></td>
<td width="274">
<p>Export results as an Excel file.</p>
</td>
</tr>
<tr>
	<td width="60"><p><img src="images/13_01_08%20PANE%20AND%20TOOLBAR%20icon%203.jpg" alt="" /></p></td>
<td width="274">
<p>Filter results by one or more columns.</p>
</td>
</tr>
<tr>
	<td width="60"><p><img src="images/13_01_08%20PANE%20AND%20TOOLBAR%20icon%204.jpg" alt="" /></p></td>
<td width="274">
<p>Toggle groupings.</p>
</td>
</tr>
<tr>
	<td width="60"><p><img src="images/13_01_08%20PANE%20AND%20TOOLBAR%20icon%205.jpg" alt="" /></p></td>
<td width="274">
<p>Toggle summaries.</p>
</td>
</tr>
        <td width="60"><p><img src="images/13_01_08 PANE AND TOOLBAR icon 6.png" alt="" /></p></td>
<td width="274">
<p>Refresh view.</p>
</td>
</tr>	
</tbody>
</table>



### Scripting Area
The SQL scripting area is used to write and run SQL statements on a selected LUDB (upper right pane).

![image](images/data_viewer_scripting_area.png)


The following options are supported:
<table>
<tbody>
<tr>
<td width="200pxl" valign="top">
<p><strong>Run or Run on New Tab</strong></p>
<p><strong>&nbsp;</strong></p>
</td>
<td width="650pxl" valign="top">
<ul>
<li>Click Run to execute the given SQL statement.</li>
<li>Click Run on New Tab to open a new Results tab.</li>
</ul>
</td>
</tr>
<tr>
<td width="236" valign="top">
<p><strong>Explain query</strong></p>
</td>
<td width="368" valign="top">
<p>Description of the strategy or plan that SQLite uses to implement a specific SQL query (e.g. SCAN TABLE)</p>
</td>
</tr>
<tr>
<td width="236" valign="top">
<p><strong>Dropdown menu of special Run options</strong></p>
</td>
<td width="368" valign="top">
<ul>
<li>On current DB file: The SQL is executed on the currently selected instance file.</li>
<li>On newest DB file for each instance: The SQL is executed on the newest instance file of each instance in the Instances tree.</li>
<li>On selected DB files: The SQL is executed on the selected instance's files. Click and press <strong>CTRL </strong>to select the required files.</li>
<li>On all existing DB files: The SQL is executed on all files in the Instances tree.</li>
</ul>
<p>Note that when <strong>On Newest DB file</strong> or <strong>On All Existing DB files </strong>are selected, the Rows Limit dropdown list opens where you can define the number of results displayed.</p>
</td>
</tr>
<tr>
<td width="236" valign="top">
<p><strong>Save SQL to File</strong></p>
</td>
<td width="368" valign="top">
<p>Saves the current SQL statements to a file.</p>
</td>
</tr>
<tr>
<td width="236" valign="top">
<p><strong>Load SQL from File</strong></p>
</td>
<td width="368" valign="top">
<p>Retrieves an SQL statement from a file previously created in the Scripting area.</p>
</td>
</tr>
</tbody>
</table>

### How Do I Run an SQL Statement in the Data Viewer? 

Run and execute the SQL statement from the scripting area on the selected DB file:
1. Enter the **SQL statement** using **SQLite syntax** into the Scripting area. 
2. Select the **DB file** to be used to run the statement from the dropdown list. 
3. Do the following:\
    a. Click **Run** or **Run on New Tab** under the **Scripting area**.\
    b. Press **F5** or **Ctrl + Enter**. Separate multiple queries with ‘;’.
4. View results in the **Results pane**.

### How Do I Export a Logical Unit Data File?
1. Go to the **Instance DB Tree** and right click the **DB File**. 
2. Click **Export Selected DB Files** and select the **Location** and **File Name** of the exported file. 
3. **Save** your changes. 

### Additional (Right Click) DB File Options
* **Open DB** opens the **Instance DB Tree** of the selected DB files. 
* **Delete Selected DB Files**, deletes the selected **Instance DB files** from the **project folder**:
   Fabric\\[project name]\Implementation\LogicalUnits\\[LU name]\VirtualDB_Data.

**Notes**

The latest Data Viewer file can be used in the following components:

* New functions / Web Services, the latest Data Viewer is displayed in the Databases dropdown list whereby the LU table can be invoked on the code. 
[Click for more information on How to Create a New Function.](/articles/07_table_population/10_creating_a_project_function.md)
* LU Schema, create a new table based on SQL Options to open the DB query where you can select the latest Data Viewer file. [Click for more information about Adding a Table to a Schema.](/articles/03_logical_units/09_add_table_to_a_schema.md)
* Population object / DB query, to display the latest Data Viewer file in the Database dropdown list. [Click for more information about Creating a New Table Population.](/articles/07_table_population/03_creating_a_new_table_population.md)
* Debugging population objects. [Click for more information about Debugging a Table Population.](/articles/07_table_population/01_table_population_overview.md#debug-toolbar) 



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_fabric_studio_log_files.md)





