# TDM Glossary

<table width="900 pxl">
<tbody>
<tr>
<td width="300pxl" valign="top">
<p><strong>Item</strong></p>
</td>
<td valign="top" width="600pxl">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>TDM (Test Data Management)</h4>
</td>
<td valign="top" width="600pxl">
<p>TDM offers an automated <a href="https://cubesolve.com/" rel="nofollow">solution</a> for copying subsets of Business Entities (Digital Entities) like Customer, Order, Patient, Product or Household from source systems into selected testing environments to provide real, high-quality data to testing teams.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>Fabric</h4>
</td>
<td valign="top" width="600pxl">
<p>K2view Fabric is a data management platform that provides access to data where and when you need it. Acting as a new data layer above existing data sources, Fabric controls data using a patented Digital Entity centric approach offering multiple and diverse built-in integrated data management capabilities for end-to-end management of the data lifecycle. For more details see <a href="/articles/01_fabric_overview/01_what%20is%20fabric.md">Fabric Overview</a>.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>Broadway</h4>
</td>
<td valign="top" width="600pxl">
<p>Broadway is a Fabric module that is used to design data movement, its transformation and the orchestration of business flows. Featuring a powerful user interface for creating and debugging business and data flows, Broadway also provides a high-performance execution engine that can be activated by Fabric. For more details see <a href="/articles/19_Broadway/01_broadway_overview.md">Broadway Overview</a>.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>TDM GUI</h4>
</td>
<td valign="top" width="600pxl">
<p>The self-service web application of the TDM product. Used for TDM setup and for creating, executing and monitoring TDM tasks.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>Digital Entity</h4>
</td>
<td valign="top" width="600pxl">
<p>A digital version of a person, place, or a thing which is usually correlated to a Business Entity.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>LU / LUT</h4>
</td>
<td valign="top" width="600pxl">
<p>A&nbsp;<a href="/articles/03_logical_units/01_LU_overview.md">Logical Unit (LU)</a> or Logical Unit Type (LUT) is a blueprint holding a set of definitions/instructions used to create and maintain the data of a Digital Entity.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>LUI</h4>
</td>
<td valign="top" width="600pxl">
<p>A Logical Unit Instance (LUI) is a specific instance of a Logical Unit. For example, the data for a specific Customer ID.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
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
<p><span class="text-bold hx_keyword-hl rounded-1 d-inline-block">Fabric provides a role</span>-based access control management. Fabric permissions are granted to each given role. Each Fabric user can be assign to one or multiple roles.</p>
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
<td width="300pxl" valign="top">
<h4><a href="/articles/TDM/tdm_overview/03_business_entity_overview.md">Business Entity / BE</a></h4>
</td>
<td valign="top" width="600pxl">
<p>A Business Entity (BE) represents the main entity of the selected data for provisioning by TDM. A Business Entity can have multiple LUs in a hierarchical structure. For example, a Customer Business Entity consists of Customer Care, Billing, Ordering and Usage LUs.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>Environment</h4>
</td>
<td valign="top" width="600pxl">
<p>A logical definition of a source or target environment. For example, Production, UAT1, UAT2, etc. An environment can contain multiple systems and multiple data sources. The list of source and target environments available for TDM must be defined in the TDM GUI and in <a href="/articles/25_environments/02_create_new_environment.md">Fabric</a>.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>Product</h4>
</td>
<td valign="top" width="600pxl">
<p>A system or application installed in the source or target environment. For example, the UAT1 environment contains CRM and Billing products. Each product can have multiple data sources.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>Task</h4>
</td>
<td valign="top" width="600pxl">
<p>Data provisioning is implemented by creating and executing TDM tasks. TDM tasks are created via the TDM GUI. The following task types are supported by the TDM:</p>
<ul>
<li>Extract task, extracts the selected entities or Reference tables from the selected source environment and saves this data in Fabric for later use.</li>
<li>Load task, extracts the selected entities or Reference tables from the selected source environment and copies (provisions) them to the selected target environment.</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h4>Data Flux</h4>
</td>
<td valign="top" width="600pxl">
<p>Data Flux mode enables users to keep versions (backups) of data during functional tests and return to the latest saved version of the data when needed. Users can create an Extract task to create a version of the data and save it in Fabric. To get the extracted version in the testing environment, the tester can create a load task, select the required version and re-load the selected version of the data to the environment instead of the corrupted data.</p>
</td>
</tr>
</tbody>
</table>





 [![Previous](/articles/images/Previous.png)](01_tdm_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_business_entity_overview.md)
