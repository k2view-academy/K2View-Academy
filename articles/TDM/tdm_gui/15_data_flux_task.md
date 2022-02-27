# TDM Data Versioning Tasks

The Data Versioning mode enables saving backup versions (snapshots) of data during Functional tests and to then reload the latest saved version to the target environment if it becomes corrupted. Once created, an extract task can be executed multiple times to create different data versions where each version is saved in Fabric.   

This functionality is useful when running a complex testing calendar in a testing environment. Backing up data every X steps or every X times enables testers to reload the latest version to their environment and repair data without returning to the original state and losing their updates. 

Note that the testing environment is often used as a source and target environment for Data Versioning tasks. Therefore, the [Environment Type](/articles/TDM/tdm_gui/08_environment_window_general_information.md#environment-type) must be set to **Both** to enable Data Versioning in an environment.



## How do I Create a Data Flux Task?

Go to the **Task** window, select **General tab** and then check **Entity Versioning**.



## Who Can Create a Data Flux Task?

The following users can create a Data Versioning task:

1. Admin users.
2. Environment owner users who can create a Data Flux task for their environment.
3. Testers who can create a TDM task for environments with **Entity Versioning** permissions that are attached to their [TDM Environment role](/articles/TDM/tdm_gui/10_environment_roles_tab.md).  



## TDM Task Types and Modes - Summary Table

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="150pxl">
<p><strong>Task Type</strong></p>
</td>
<td valign="top" width="150pxl">
<p><strong>Entity Versioning</strong></p>
</td>
<td valign="top" width="600pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Extract</p>
</td>
<td valign="top" width="150pxl">
<p>true</p>
</td>
<td valign="top" width="600pxl">
<p>Extract the data of the selected entities from the source environment and save it as a separate version in Fabric.</p>
<p>The extracted data gets the execution datetime. Each entity gets the following Instance ID in Fabric:</p>
<p>&lt;Source env name&gt;_&lt;entity id&gt;_&lt;task title&gt;_&lt;datetime&gt;</p>
<p>For example, ENV1_100_extractTest3_20210218082453</p>
<p>Since the execution datetime is concatenated to the LUI, each task execution creates a different set of LUIs.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Extract</p>
</td>
<td valign="top" width="150pxl">
<p>false</p>
</td>
<td valign="top" width="600pxl">
<p>Extract the data of the selected entities from the source environment and save it in Fabric.</p>
<p>Each entity is saved in Fabric with the following instance ID:</p>
<p>&lt;Source env name&gt;_&lt;entity id&gt;</p>
<p>For example, ENV1_100.</p>
<p>Note that each task execution may update the LUIs in Fabric.</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Load</p>
</td>
<td valign="top" width="150pxl">
<p>true</p>
</td>
<td valign="top" width="600pxl">
<p>Load a selected version created on the task's LUs and source environment into the selected target environment. The required entities are deleted from the target and reloaded according to the selected version.</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Load</p>
</td>
<td valign="top" width="150pxl">
<p>false</p>
</td>
<td valign="top" width="600pxl">
<p>Regular TDM load task.&nbsp; Get a list of entities from a source environment and copy them into the target environment.</p>
</td>
</tr>
</tbody>
</table>





 [![Previous](/articles/images/Previous.png)](14_task_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](16_extract_task.md)

