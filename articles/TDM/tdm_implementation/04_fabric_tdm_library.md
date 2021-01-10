# Fabric TDM Library

The TDM Library contains all the utilities, required to implement a TDM project and run the TDM execution processes.

The TDM Library must be imported to the Fabric project, created for the TDM.

The TDM Library contains the following:

- Shared Objects
- TDM LU
- TDM_LIBRARY LU.

### TDM Library - Shared Objects

<table width="950pxl">
<tbody>
<tr>
<td valign="top" width="150pxl">
<p><strong>Item&nbsp;</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Description&nbsp;</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Location&nbsp;</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Special instructions&nbsp;</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>TDM Web services</p>
</td>
<td valign="top" width="250pxl">
<p>TDM Web Services utilities.&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Web Services&nbsp;&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Import and deploy the Web Services to Fabric. Define the <strong>tdm-WS</strong> token in your Fabric for the WS.</p>
<p>Note that it is recommended to add the project&rsquo;s Web Services to a separate Category to simplify the upgrade of the TDM version since the TDM category contains the product&rsquo;s Web Services. &nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>DB_CASSANDRA&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Connection to Cassandra DB.&nbsp; This interface is used by the TDM utilities.</p>
</td>
<td valign="top" width="200pxl">
<p>Shared Objects/Interfaces&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Edit the IP address according to your environment.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>TDM&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>The connection for the TDMDB PostgreSQL DB.&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Shared Objects/Interfaces&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Edit the IP address according to your environment.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>REDIS&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Custom interface for Redis connection details including the Redis password.&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Shared Objects/Interfaces&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Edit the IP address- populate it with the IP address of the TDM server.&nbsp;</p>
<p>Edit the password- populate it with the Redis password on the TDM server.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>SharedGlobals&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>A list of shard global variables, required for the TDM execution&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>SharedObjects/Java/src/com/</p>
<p>k2view/cdbms/usercode/</p>
<p>common/SharedGlobals.java</p>
</td>
<td valign="top" width="300pxl">&nbsp;</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>Shared TDM Functions</p>
</td>
<td valign="top" width="250pxl">
<p>A list of TDM functions and utilities</p>
</td>
<td valign="top" width="250pxl">
<p>SharedObjects/Java/src/com/</p>
<p>k2view/cdbms/usercode/</p>
<p>common/TDM</p>
</td>
<td valign="top" width="300pxl">
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>trnMigrateList</p>
</td>
<td valign="top" width="250pxl">
<p>Define the query + Interface name to run <strong>extract task</strong> for all entities for each LU. One record per LU.</p>
</td>
<td valign="top" width="250pxl">
<p>SharedObjects/Translations</p>
</td>
<td valign="top" width="300pxl">
<p>Populate this translation for each one of your logical units. A separate record must be created for each logical unit in the Fabric project, except for TDM, TDM_LIBRARY, and the dummy LU of the post-execution processes. &nbsp;</p>
<p>If it is required to define a query per source environment, populate the source environment name and create a separate record for each combination of logical unit +_source_env_name. &nbsp;Otherwise, leave the source environment empty.</p>
<p>Examples:</p>
<p>1.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
<p>LU_NAME= ORDER</p>
<p>SOURCE_ENV_NAME = ENV1 INTERFACE_NAME = TDM</p>
<p>&nbsp;IG_SQL = Select lu_type2_eid from tdm_lu_type_relation_eid where lu_type_2 = &lsquo;ORDER&rsquo; and source_env = 'ENV1';</p>
<p>2.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</p>
<p>LU_NAME= CUSTOMER</p>
<p>SOURCE_ENV_NAME &nbsp;is empty INTERFACE_NAME = CRM_DN</p>
<p>&nbsp;IG_SQL = Select customer_id from customer limit 1000;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>trnMigrateListQueryFormats</p>
</td>
<td valign="top" width="250pxl">
<p>Supports special syntax for <strong>extract tasks </strong>when creating the LU instance query based on the trnMigrateList translation. Each LUI consists of a concatenation of source environment, IID, version name, and version datetime.</p>
<p><a href="01_tdm_set_instance_per_env_and_version.md">Click to read more about the LUI structure on TDM implementation</a>.</p>
<p>This translation is required for DBs that do not support the standard syntax of &lsquo;||&rsquo; to concatenate Strings. For example- sqlServer.</p>
</td>
<td valign="top" width="250pxl">
<p>SharedObjects/Translations</p>
</td>
<td valign="top" width="300pxl">
<p>Populate two records for each DB: one record with version_ind&nbsp;&lsquo;true&rsquo; and another one with&nbsp;version_ind&nbsp;&lsquo;false&rsquo;.&nbsp;</p>
<p>See the examples below:&nbsp;</p>
<p> 1. </p>
<p> <strong>interface_type</strong> = sqlserver </p>
<p> <strong>version_ind</strong> = true </p>
<p> <strong>query_format</strong> = CONCAT(&lt;source_env_name&gt;,'_',&lt;entity_id&gt;,'_',&lt;task_name&gt;,'_',&lt;timestamp&gt;)</p> 
<p> 2. </p>
<p> <strong>interface_type</strong> = sqlserver </p>
<p> <strong>version_ind</strong> = false </p>
<p> <strong>query_format</strong> = CONCAT(&lt;source_env_name&gt;,'_',&lt;entity_id&gt;)</p>    
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>trnRefList</p>
</td>
<td valign="top" width="250pxl">
<p>Define the list of available reference tables for <strong>extract tasks</strong>.</p>
</td>
<td valign="top" width="250pxl">
<p>SharedObjects/Translations</p>
</td>
<td valign="top" width="300pxl">
<p>Populate this translation for each one of the LUs. A separate record must be created for each reference table.</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>trnPostProcessList</p>
</td>
<td valign="top" width="250pxl">
<p>Define the list of post-processes to run at the end of the task execution. For example, a process that sends a mail to notify the user when the task execution ends.</p>
<p>Each process is implemented as a Broadway flow.</p>
</td>
<td valign="top" width="200pxl">
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Populate the list of the Broadway flows and the LU of the Broadway flow.</p>
</td>
</tr>
</tbody>
</table>

### TDM LU

The TDM logical unit must be added to the Fabric project and serves the following:

- Keep the execution information for the TDM task executions. The TDM GUI provides the execution statistics and reports based on the data of the TDM LU. The LUI of the TDM LU is the unique task_Execution_id, generated by the TDM GUI on each task execution. 
- Task execution utilities are defined and run under the TDM LU.
- TDM cleanup job which cleans the TDM DB is defined under the TDM LU. 

The TDM LU must be deployed to Fabric.

### TDM_LIBRARY LU

The TDM_LIBRARY LU contains utilities that need to be copied to the project LUs.  Below is the list of the LU level utilities:

<table width="950pxl">
<tbody>
<tr>
<td valign="top" width="150pxl">
<p><strong>Item&nbsp;</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Description&nbsp;</strong></p>
</td>
<td valign="top" width="200pxl">
<p><strong>Location&nbsp;</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Special instructions&nbsp;</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>trnChildLink&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Translation for the mapping of parent and child IDs.&nbsp;</p>
<p>Click for more information about TDM business entities and how to support a hierarchy when implementing the LUs.</p>
</td>
<td valign="top" width="200pxl">
<p>Translations&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>This translation must be added and populated for each parent LU. The child_lu field must be populated by the name of the child LU.&nbsp;&nbsp;</p>
<p>The&nbsp;child_lu_eid_sql&nbsp;field must be populated by the SQL which runs on the parent LU and gets the child IDs for each parent ID.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>&nbsp;</p>
<p>trnLuParams&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Translation for the population of LU_PARAMS table.&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p> Translations&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>This translation must be added and populated for each LU to enable a selection of entities by predefined parameters (for example, copy business customers).&nbsp;<br />COLUMN_NAME is populated by the name of the parameter and the SQL is populated by the SQL query that gets the values for the defined parameter.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>TDM_LU_TYPE_RELATION_EID&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Relation table. Note that the TDM_LU_TYPE_RELATION_EID is also created and populated in the TDM DB.&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Tables&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>This table must be added to each LU.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>FABRIC_TDM_ROOT&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Root table for each LU. Contains the entityID which has the source environment + IID, the source environment, and the IID.&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Tables&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>This table must be added to each LU as a root table.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>INSTANCE_TABLE_COUNT&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Statistics table&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Tables&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>This table must be added to each LU.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>LU_PARAMS&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Parameters table.</p>
<p>Click for more information about the TDM parameters setup.</p>
</td>
<td valign="top" width="200pxl">
<p>Tables&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>This table must be added to each LU. Each parameter, populated in the trnLuParams, must be added as a column to LU_PARAMS table.&nbsp;</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>trnParsList&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Translation for the split of large files.&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Translations&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Copy the translation to your LU if needed&rsquo; and populate it with the list of the large parsers.&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>parExecuteJob&nbsp;</p>
</td>
<td valign="top" width="250pxl">
<p>Split large files. The main parser that runs other parsers and split them to run in parallel trnParsList: a translation that holds the "large" parsers names that you wish to split.&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Parser&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Copy this parser to your LU if needed.&nbsp;</p>
</td>
</tr>
</tbody>
</table>


[![Previous](/articles/images/Previous.png)](03_tdm_fabric_implementation_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_tdm_lu_implementation_general.md)