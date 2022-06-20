# TDM Glossary



<table width="900 pxl">
<tbody>
<tr>
<td valign="top" width="300pxl">
<p><strong>Item</strong></p>
</td>
<td valign="top" width="600pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>TDM (Test Data Management)</h4>
</td>
<td valign="top" width="600pxl">
<p>TDM offers an automated <a href="https://cubesolve.com/" rel="nofollow">solution</a> for copying subsets of Business Entities (like customer, order, patient, product and household) from source systems into selected testing environments. The solution provides real, high-quality data for testing teams.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>Fabric</h4>
</td>
<td valign="top" width="600pxl">
<p>K2view Fabric is a data management platform that provides access to data where and when you need it. Acting as a new data layer above existing data sources, Fabric controls data using a patented Digital Entity approach offering multiple and diverse built-in integrated data management capabilities for end-to-end management of the data lifecycle. For more details see <a href="/articles/01_fabric_overview/01_what%20is%20fabric.md">Fabric Overview</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>Broadway</h4>
</td>
<td valign="top" width="600pxl">
<p>Broadway is a Fabric module that is used to design data movement, its transformation, and the orchestration of business flows. Featuring a powerful user interface for creating and debugging business and data flows, Broadway also provides a high-performance execution engine that can be activated by Fabric. For more details see <a href="/articles/19_Broadway/01_broadway_overview.md">Broadway Overview</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>TDM GUI</h4>
</td>
<td valign="top" width="600pxl">
<p>The self-service web application of the TDM product. Used for TDM setup and for creating, executing, and monitoring TDM tasks.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>Digital Entity</h4>
</td>
<td valign="top" width="600pxl">
<p>A digital version of a person, place, or a thing that is usually correlated to a Business Entity.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>LU / LUT</h4>
</td>
<td valign="top" width="600pxl">
<p>A&nbsp;<a href="/articles/03_logical_units/01_LU_overview.md">Logical Unit (LU)</a> or Logical Unit Type (LUT) is a blueprint holding a set of definitions/instructions used to create and maintain the data of a Digital Entity.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>LUI</h4>
</td>
<td valign="top" width="600pxl">
<p>A Logical Unit Instance (LUI) is a specific instance of a Logical Unit. For example, the data for a specific Customer ID.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4><a href="/articles/02_fabric_architecture/01_fabric_architecture_overview.md#21-fabric-storage">MDB / MicroDB</a></h4>
</td>
<td valign="top" width="600pxl">
<p>Micro-database, a small SQL database used to store Digital Entity Instance (LUI) data. An MDB is stored as an SQLite file and as a Blob field in the Cassandra Entity table, depending on the saved property's definition in the LU Schema.</p>
</td>
</tr>
<tr>
<td width="300pxl">
<h4>Fabric Role</h4>
</td>
<td width="600pxl">
<p><span class="text-bold hx_keyword-hl rounded-1 d-inline-block">Fabric provides role</span>-based access control management. Fabric permissions are granted to each given role. Each Fabric user can be assigned to one or multiple roles.</p>
<p>For more information see <a href="/articles/17_fabric_credentials/01_fabric_credentials_overview.md">Fabric Credentials Overview</a>.</p>
</td>
</tr>
<tr>
<td width="300pxl">
<h4>TDM Permission Group</h4>
</td>
<td width="600pxl">
<p>There are three main types of TDM users, each with different permissions for different activities. Each type is called a&nbsp;<strong>Permission Group</strong>. The following Permission Groups are supported by TDM: Admin, Owner, and Tester.&nbsp;</p>
<p>For more information see <a href="/articles/TDM/tdm_gui/02_tdm_gui_user_types.md">TDM GUI - Permission Groups</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4><a href="/articles/TDM/tdm_overview/03_business_entity_overview.md">Business Entity / BE</a></h4>
</td>
<td valign="top" width="600pxl">
<p>A Business Entity (BE) represents the main entity of the selected data for provisioning by TDM. A Business Entity can have multiple LUs in a hierarchical structure. For example, a Customer Business Entity consists of Customer Care, Billing, Ordering, and Usage LUs.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>Environment</h4>
</td>
<td valign="top" width="600pxl">
<p>A logical definition of a source or target environment. For example, Production, UAT1, UAT2, etc. An environment can contain multiple systems and multiple data sources. The list of source and target environments available for TDM must be defined in the TDM GUI and in <a href="/articles/25_environments/02_create_new_environment.md">Fabric</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>System</h4>
</td>
<td valign="top" width="600pxl">
<p>A system (product) or application that is installed in the source or target environment. For example, the UAT1 environment contains CRM and Billing products. Each product can have multiple data sources.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>Task</h4>
</td>
<td valign="top" width="600pxl">
<p>Data provisioning is implemented by creating and executing TDM tasks. TDM tasks are created via the TDM GUI. The following task types are supported by the TDM:</p>
<ul>
<li>Extract tasks, the selected entities are extracted from the source environment and migrated into Fabric. The batch process initiates a&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.5_TDM_7.4/articles/14_sync_LU_instance/01_sync_LUI_overview.md">Sync</a>&nbsp;of the selected entities into Fabric.</li>
<li>Load task, the batch process initiates a&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.5_TDM_7.4/articles/19_Broadway/02a_broadway_flow_overview.md">Broadway flow</a>&nbsp;which&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.5_TDM_7.4/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands">retrieves the LUIs</a> from Fabric, transforms the data if needed, and loads the entities to the selected target environment.</li>
<li>Delete task, the batch process initiates a <a href="https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.5_TDM_7.4/articles/19_Broadway/02a_broadway_flow_overview.md">Broadway flow</a>&nbsp;which&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.5_TDM_7.4/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands">retrieves the LUIs</a>&nbsp;from Fabric to sync the target keys for delete into Fabric and delete the entities from the selected target environment.</li>
<li>Reserve task, the batch process initiates a&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.5_TDM_7.4/articles/19_Broadway/02a_broadway_flow_overview.md">Broadway flow</a>&nbsp;which marks the entities are reserved entities in the TDM DB.</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<h4>Data Versioning</h4>
</td>
<td valign="top" width="600pxl">
<p>Data Versioning enables users to keep versions (backups) of data during functional tests and restore the latest saved version of the data when needed. Users can create an Extract task to create a version of the data and save it in Fabric. To get the extracted version in the testing environment, the tester can create a load task, select the required version and re-load the selected version of the data to the environment instead of the corrupted data.</p>
</td>
</tr>
<tr>
<td>
<h4>Entity Reservation</h4>
</td>
<td>
<p>The Entity Reservation feature is made to enable a user to reserve (lock) entities in the testing environment and prevent other users from re-provisioning these entities into the testing environment until the user completes the functional tests and can release these entities.</p>
<p>For more information see <a href="/articles/tdm_architecture/08_entity_reservation.md">Entity Reservation</a>.</p>
</td>
</tr>
</tbody>
</table>




 [![Previous](/articles/images/Previous.png)](01_tdm_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_business_entity_overview.md)
