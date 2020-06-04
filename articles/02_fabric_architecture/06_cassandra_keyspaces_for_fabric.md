# Cassandra Keyspaces for Fabric

Fabric uses Cassandra DB as a storage layer. Fabric creates several keyspaces for its operation. Each Fabric keyspace starts by **k2**. In addition- each deployed LU creates an additional Cassandra keyspace: **k2view_[LU Name]**. For example: k2view_customer.

Below the the list of Cassandra keyspaces, created Fabric:

<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Keyspace Name</strong></p>
</td>
<td width="250pxl">
<p><strong>Keyspace Description</strong></p>
</td>
<td colspan="2" width="500pxl">
<p><strong>Keyspace Tables</strong></p>
</td>
</tr>
<tr>
<td rowspan="5" width="150pxl">
<p>k2system</p>
</td>
<td rowspan="5" width="250pxl">
<p>Fabric main system keyspace</p>
</td>
<td width="150pxl">
<p><strong>Table Name</strong></p>
</td>
<td width="350pxl">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>k2_lut_info&nbsp;</p>
<p>&nbsp;</p>
</td>
<td width="350pxl">
<p>This table holds definitions of LUs, Common (reference) tables and Web Services <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md">deployed to Fabric.</a></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>k2_jobs</p>
<p>&nbsp;</p>
</td>
<td width="350pxl">
<p>Fabric Jobs table. This table contains information on the history of all the jobs run on Fabric</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>nodes</p>
<p>&nbsp;</p>
</td>
<td width="350pxl">
<p>List of all Fabric nodes in the cluster</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>global_settings</p>
</td>
<td width="350pxl">
<p>List of all Globals/Environments that were overridden from the default value using <a href="/articles/08_globals/03_set_globals.md#how-do-i-use-set_global-global-command">set_global command</a>. This table is used to identify the overridden value of Globals or Environments when restarting Fabric.</p>
</td>
</tr>
<tr>
<td rowspan="7" width="150pxl">
<p>k2auth</p>
</td>
<td rowspan="7" width="250pxl">
<p>Fabric security and credentials keyspace</p>
</td>
<td width="150pxl">
<p><strong>Table Name</strong></p>
</td>
<td width="350pxl">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>user_credentials</p>
</td>
<td width="350pxl">
<p>List of Fabric users with the assigned roles.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>roles</p>
</td>
<td width="350pxl">
<p>List of role definitions.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>credentials</p>
</td>
<td width="350pxl">
<p>List of tokens with assigned roles. The tokens are encrypted.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>permissions</p>
</td>
<td width="350pxl">
<p>List of permissions for a given role.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>stripe_key_storage</p>
</td>
<td width="350pxl">
<p>It contains the stripped master key encrypted info.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>keys_descriptions</p>
</td>
<td width="350pxl">
<p>It contains master key descriptions.</p>
</td>
</tr>
<tr>
<td rowspan="5" width="150pxl">
<p>k2batchprocess</p>
</td>
<td rowspan="5" width="250pxl">
<p>Fabric batch processes information</p>
</td>
<td width="150pxl">
<p><strong>Table Name</strong></p>
</td>
<td width="350pxl">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>batchprocess_list</p>
</td>
<td width="350pxl">
<p>List of the entire history of the batch process commands.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>batchprocess_node_info&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
<p>&nbsp;</p>
</td>
<td width="350pxl">
<p>Summary of handled entities per batch process per node.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>batchprocess_entities_info</p>
</td>
<td width="350pxl">
<p>Detailed execution information for a given entity per batch process command.</p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>batchprocess_entities_errors&nbsp;</p>
</td>
<td width="350pxl">
<p>Detailed entities information for failed entities per batch process command. This table is intended to simplify the analysis of failed entities.</p>
</td>
</tr>
<tr>
<td rowspan="2" width="150pxl">
<p>k2audit</p>
</td>
<td rowspan="2" width="250pxl">
<p>Fabric auditing</p>
</td>
<td width="150pxl">
<p><strong>Table Name</strong></p>
</td>
<td width="350pxl">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>k2_auditing</p>
</td>
<td width="350pxl">
<p>The auditing saves all activities, performed on Fabric, in k2_auditing table</p>
</td>
</tr>
<tr>
<td rowspan="3" width="150pxl">
<p>K2view_&lt;LU Name&gt;</p>
</td>
<td rowspan="3" width="250pxl">
<p>A new keyspace is created for each <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-are-deployed-objects-reflected-in-cassandra">deployed LU</a></p>
</td>
<td width="150pxl">
<p><strong>Table Name</strong></p>
</td>
<td width="250pxl">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>entity</p>
</td>
<td width="350pxl">
<p>Stores the list of all <a href= "/articles/01_fabric_overview/02_fabric_glossary.md#lui">LU instances and their micro database.</a></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>entity_chunks</p>
</td>
<td width="350pxl">
<p>Used to store large LU instances.</p>
</td>
</tr>
</tbody>
</table>
<p>_</p>

Click for more information about Fabric Architecture overview.

<!-- Add links- drop 1- WS, Fabric architecture, Fabric credentials-->

<!-- Add links- next drops- Jobs, Batch processes, audit, LU storage, security hardening-->