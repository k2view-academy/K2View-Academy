# Cassandra Keyspaces for Fabric

Fabric uses the Cassandra DB as a storage layer and also as a Fabric application management database. 

Fabric creates several keyspaces for its operation:
-  Each Fabric keyspace starts with a **k2**.
-  Each deployed LU creates an additional **k2view_[LU Name** Cassandra keyspace. For example: **k2view_customer**.

**Notes:**

-  When set in the [node.id](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#nodeid) configuration file, the cluster_id is concatenated to each keyspace name. For example: if the cluster_id is set to crm1, the keyspace k2view_customer_crm1 is created.
- When a Fabric project is opened in the Fabric Studio, it creates the keyspaces of the project in the local Cassandra server and concatenates the project name to the keyspace name.
- A <strong>k2view_k2_ws</strong> keyspace is created for deployed WS.

## Login Cassandra DB

Use the following command to connect to the Cassandra DB from the Cassandra server:

**cqlsh -u `<username>` -p `<password>` <ip_address>**;

Note that if the **ip_address** is not populated, the login command connects to the local host of the Fabric server.

### List of Cassandra Fabric-Related Keyspaces 

The following table lists the Cassandra keyspaces created by Fabric:



<table>
<tbody>
<tr>
<td width="150pxl valign="top"">
<p><strong>Keyspace Name</strong></p>
</td>
<td width="250pxl" valign="top">
<p><strong>Keyspace Description</strong></p>
</td>
<td colspan="2" width="500pxl" valign="top">
<p><strong>Keyspace Tables</strong></p>
</td>
</tr>
<tr>
<td rowspan="5" width="150pxl" valign="top">
<p>k2system</p>
</td>
<td rowspan="5" width="250pxl" valign="top">
<p>Fabric main system keyspace.</p>
</td>
<td width="150pxl" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td width="350pxl" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>k2_lut_info&nbsp;</p>
<p>&nbsp;</p>
</td>
<td width="350pxl" valign="top">
<p>Holds the metadata of the LUs, Common (reference) tables and Web Services <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md">deployed to Fabric.</a></p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>k2_jobs</p>
<p>&nbsp;</p>
</td>
<td width="350pxl" valign="top">
<p>Holds information on the execution of all Fabric jobs.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>nodes</p>
<p>&nbsp;</p>
</td>
<td width="350pxl" valign="top">
<p>List of all Fabric nodes in the cluster.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>global_settings</p>
</td>
<td width="350pxl" valign="top">
<p>List of all Globals and Environments whose default value has been overidden using the <a href="/articles/08_globals/03_set_globals.md#how-do-i-use-set_global-global-command">SET_GLOBAL command</a>. This table is used to identify the overridden value of Globals or Environments when restarting Fabric.</p>
</td>
</tr>
<tr>
<td rowspan="7" width="150pxl" valign="top">
<p>k2auth</p>
</td>
<td rowspan="7" width="250pxl" valign="top">
<p>Fabric security and <a href="/articles/17_fabric_credentials/01_fabric_credentials_overview.md">credentials</a> keyspace</p>
</td>
<td width="150pxl" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td width="350pxl" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>user_credentials</p>
</td>
<td width="350pxl" valign="top">
<p>List of Fabric users and their assigned roles.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>roles</p>
</td>
<td width="350pxl" valign="top">
<p>List of role definitions.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>credentials</p>
</td>
<td width="350pxl" valign="top">
<p>List of tokens with assigned roles. The tokens are encrypted.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>permissions</p>
</td>
<td width="350pxl" valign="top">
<p>List of permissions for a given role.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>stripe_key_storage</p>
</td>
<td width="350pxl" valign="top">
<p>Holds encrypted information about the  master key. This table contains the key description, index, and value fields. The Master Key is broken into bytes, where each byte is stored in a separate record. 
Click for more information about Fabric Security Hardening.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>keys_descriptions</p>
</td>
<td width="350pxl" valign="top">
<p>Holds a description of the master key.</p>
</td>
</tr>
<tr>
<td rowspan="5" width="150pxl" valign="top">
<p>k2batchprocess</p>
</td>
<td rowspan="5" width="250pxl" valign="top">
<p>Fabric batch processes information</p>
</td>
<td width="150pxl" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td width="350pxl" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>batchprocess_list</p>
</td>
<td width="350pxl" valign="top">
<p>List of the entire history of the batch process commands.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>batchprocess_node_info&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
<p>&nbsp;</p>
</td>
<td width="350pxl" valign="top">
<p>Summary of handled entities per batch process per node.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>batchprocess_entities_info</p>
</td>
<td width="350pxl" valign="top">
<p>Detailed information about an execution of a given entity per batch process command.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>batchprocess_entities_errors&nbsp;</p>
</td>
<td width="350pxl" valign="top">
<p>Detailed information about failed entities per batch process command. This table simplifies the analysis of failed entities.</p>
</td>
</tr>
<tr>
<td rowspan="2" width="150pxl" valign="top">
<p>k2audit</p>
</td>
<td rowspan="2" width="250pxl" valign="top">
<p>Fabric auditing</p>
</td>
<td width="150pxl" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td width="350pxl" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>k2_auditing</p>
</td>
<td width="350pxl" valign="top">
<p>Saves all activities performed on Fabric, when AUDIT is set to ON in the config.ini file.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>k2staging</p>
</td>
<td width="350pxl" valign="top">
<p>Staging tables for IIDFinder mechanism:&nbsp;</p>
<p><span data-contrast="auto">A different set of caching, delta, solo and orphans&rsquo; tables is created for each LU that is synchronized by the IIDFinder (proactive sync) mechanism. </span></p>
<p>&nbsp;</p>
</td>
    <td></td>  
    <td></td>
</tr>
<tr>
<td rowspan="3" width="150pxl" valign="top">
<p>K2view_&lt;LU Name&gt;</p>
</td>
<td rowspan="3" width="250pxl" valign="top">
<p>A new keyspace is created for each <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-are-deployed-objects-reflected-in-cassandra">deployed LU.</a></p>
</td>
<td width="150pxl" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td width="250pxl" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>entity</p>
</td>
<td width="350pxl" valign="top">
    <p>Stores the list of all <a href= "/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUIs</a> and their MicroDB.</p>
</td>
</tr>
<tr>
<td width="150pxl" valign="top">
<p>entity_chunks</p>
</td>
<td width="350pxl" valign="top">
<p>Stores large LUIs. The <a href="/articles/01_fabric_overview/02_fabric_glossary.md#mdbmicrodb">MicroDB </a> of a Large LUI is divided into chunks and stored in entity_chunks.</p>
<p><a href="/articles/02_fabric_architecture/01_fabric_architecture_overview.md#microdb-">Click for more information about LU storage.</a></p>
</td>
</tr>
</tbody>
</table>



[Click for more information about Fabric Architecture overview.](/articles/02_fabric_architecture/01_fabric_architecture_overview.md)

[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/07_cassandra_basic_commands.md)

<!-- Add links- drop 1- WS, Fabric architecture, Fabric credentials-->

<!-- Add links- next drops- Jobs, Batch processes, audit, LU storage, security hardening, IIDFinder-->
