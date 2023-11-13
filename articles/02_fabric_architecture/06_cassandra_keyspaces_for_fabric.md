# Fabric System Database

Fabric System Database is used by Fabric internal processes to monitor, secure, control, configure, audit and operate the application.

Fabric supports several types of Databases as System Database storage:

- NoSQL distributed database, such as Cassandra DB
  - Pros
    - Scalable
    - Distributed
    - Built-in TTL mechanism on row level
    - If Cassandra is used as a MicroDB storage, there is no need to introduce additional DBs
    - Managed services (such as AWS Keyspaces or Astra) are supported
    - Supported by the iidFinder solution
    - Built-in mechanism for managing parallel threads during bulk instance loading.
  - Cons
    - Consistency
    - Not easy to operate and maintain
- Relational database, such as PostgreSQL
  - Pros
    - Consistency
    - In case of the TDM solution, PostgreSQL is already introduced
    - Compliance with services such as Cloud Spanner, AlloyDB
    - Easy to maintain 
  - Cons
    - Single point of failure
    - Not supported by the iidFinder solution
- Sqlite
  - Pros
    - Development and single-node environments

Fabric uses the Cassandra DB as its default system management database. 

Fabric creates several keyspaces or schemas (in case of Sqlite or PostgreSQL) for its operation, where each starts with the **k2** prefix.

Each deployed LU creates an additional **k2view_[LU Name]** keyspace or schema, such as **k2view_customer**.

**Notes:**

-  When set in the [node.id](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#nodeid) configuration file, the cluster_id is concatenated to each keyspace or schema name. For example, if the cluster_id is set to crm1, the created keyspace or schema is **k2view_customer_crm1**.
- When a Fabric project opens in the Fabric Studio, it concatenates the project name to the schema name.
- A <strong>k2view_k2_ws</strong> keyspace or schema is created for the deployed WS.



When Fabric is configured to run a System DB type - other than Cassandra - an internal product job runs, scans the tables to be cleaned and cleans them, using TTL concept. The definition of each table's TTL value is tracked in the k2_table_level_ttl table.

### List of Fabric-Related System Keyspaces or Schemas 

The following table lists the keyspaces or schemas created by Fabric:

<table style="width: 900px;">
<tbody>
<tr style="height: 46px;">
<td style="height: 46px; width: 300px;">
<p><strong>Keyspace/Schema Name and Description</strong></p>
</td>
<td style="height: 46px; width: 600px;" colspan="2">
<p><strong>Keyspace/Schema Tables</strong></p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 538px;" rowspan="6" valign="top">
<p><strong>k2system</strong> - Fabric main system keyspace/schema.</p>
<p>&nbsp;</p>
</td>
<td style="height: 46px;" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td style="height: 46px;" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr style="height: 100px;">
<td style="height: 100px; width: 208.925px;" valign="top">
<p>k2_lut_info&nbsp;</p>
<p>&nbsp;</p>
</td>
<td style="height: 100px; width: 203.863px;" valign="top">
<p>Holds the metadata of the LUs, Common (reference) tables and Web Services <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md">deployed to Fabric.</a></p>
</td>
</tr>
<tr style="height: 78px;">
<td style="height: 78px; width: 208.925px;" valign="top">
<p>k2_jobs</p>
<p>&nbsp;</p>
</td>
<td style="height: 78px; width: 203.863px;" valign="top">
<p>Holds information on the execution of all Fabric jobs.</p>
</td>
</tr>
<tr style="height: 78px;">
<td style="height: 78px; width: 208.925px;" valign="top">
<p>nodes</p>
<p>&nbsp;</p>
</td>
<td style="height: 78px; width: 203.863px;" valign="top">
<p>List of all Fabric nodes in the cluster.</p>
</td>
</tr>
<tr style="height: 172px;">
<td style="height: 172px; width: 208.925px;" valign="top">
<p>global_settings</p>
</td>
<td style="height: 172px; width: 203.863px;" valign="top">
<p>List of all Globals and Environments whose default value has been overidden using the <a href="/articles/08_globals/03_set_globals.md#how-do-i-use-set_global-global-command">SET_GLOBAL command</a>. This table is used to identify the overridden value of Globals or Environments when restarting Fabric.</p>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 208.925px;">
<p>broadway_recovery_point</p>
</td>
<td style="height: 64px; width: 203.863px;">
<p>Holds information on Broadway flows with recovery points.</p>
<p><a href="/articles/19_Broadway/29_recovery_point.md">Click here for more information about Broadway Recovery.</a></p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 574px; width: 313.812px;" rowspan="7" valign="top">
<p><strong>k2auth</strong> - Fabric security and <a href="/articles/17_fabric_credentials/01_fabric_credentials_overview.md">credentials</a> keyspace</p>
</td>
<td style="height: 46px; width: 208.925px;" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td style="height: 46px; width: 203.863px;" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 208.925px;" valign="top">
<p>user_credentials</p>
</td>
<td style="height: 64px; width: 203.863px;" valign="top">
<p>List of Fabric users and their assigned roles.</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 46px; width: 208.925px;" valign="top">
<p>roles</p>
</td>
<td style="height: 46px; width: 203.863px;" valign="top">
<p>List of role definitions.</p>
</td>
</tr>
<tr style="height: 82px;">
<td style="height: 82px; width: 208.925px;" valign="top">
<p>credentials</p>
</td>
<td style="height: 82px; width: 203.863px;" valign="top">
<p>List of tokens with assigned roles. The tokens are encrypted.</p>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 208.925px;" valign="top">
<p>permissions</p>
</td>
<td style="height: 64px; width: 203.863px;" valign="top">
<p>List of permissions for a given role.</p>
</td>
</tr>
<tr style="height: 208px;">
<td style="height: 208px; width: 208.925px;" valign="top">
<p>stripe_key_storage</p>
</td>
<td style="height: 208px; width: 203.863px;" valign="top">
<p>Holds encrypted information about the master key. This table contains the key description, index, and value fields. The Master Key is broken into bytes, where each byte is stored in a separate record.
<a href="/articles/26_fabric_security/02_fabric_entities_design.md"> Click here for more information about Fabric Security.</a></p> 
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 208.925px;" valign="top">
<p>keys_descriptions</p>
</td>
<td style="height: 64px; width: 203.863px;" valign="top">
<p>Holds a description of the master key.</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 388px; width: 313.812px;" rowspan="5" valign="top">
<p><strong>k2batchprocess</strong> - Fabric batch processes information</p>
</td>
<td style="height: 46px; width: 208.925px;" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td style="height: 46px; width: 203.863px;" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 208.925px;" valign="top">
<p>batchprocess_list</p>
</td>
<td style="height: 64px; width: 203.863px;" valign="top">
<p>List of the entire history of the batch process commands.</p>
</td>
</tr>
<tr style="height: 78px;">
<td style="height: 78px; width: 208.925px;" valign="top">
<p>batchprocess_node_info</p>
<p>&nbsp;</p>
</td>
<td style="height: 78px; width: 203.863px;" valign="top">
<p>Summary of handled entities per batch process per node.</p>
</td>
</tr>
<tr style="height: 82px;">
<td style="height: 82px; width: 208.925px;" valign="top">
<p>batchprocess_entities_info</p>
</td>
<td style="height: 82px; width: 203.863px;" valign="top">
<p>Detailed information about an execution of a given entity per batch process command.</p>
</td>
</tr>
<tr style="height: 118px;">
<td style="height: 118px; width: 208.925px;" valign="top">
<p>batchprocess_entities_errors</p>
</td>
<td style="height: 118px; width: 203.863px;" valign="top">
<p>Detailed information about failed entities per batch process command. This table simplifies the analysis of failed entities.</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 128px; width: 313.812px;" rowspan="2" valign="top">
<p><strong>k2audit</strong> - Fabric auditing</p>
</td>
<td style="height: 46px; width: 208.925px;" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td style="height: 46px; width: 203.863px;" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr style="height: 82px;">
<td style="height: 82px; width: 208.925px;" valign="top">
<p>k2_auditing</p>
</td>
<td style="height: 82px; width: 203.863px;" valign="top">
<p>Saves all activities performed on Fabric, when AUDIT is set to ON in the config.ini file.</p>
</td>
</tr>
<tr style="height: 164px;">
<td style="height: 164px; width: 313.812px;" valign="top">
<p><strong>k2staging</strong> - Staging tables for the IIDFinder mechanism:&nbsp;</p>
<p><span data-contrast="auto">A different set of caching, delta, solo and orphans&rsquo; tables is created for each LU that is synchronized by the IIDFinder (proactive sync) mechanism. </span></p>
<p>&nbsp;</p>
</td>
<td style="height: 164px; width: 208.925px;">&nbsp;</td>
<td style="height: 164px; width: 203.863px;">&nbsp;</td>
</tr>
<tr style="height: 46px;">
<td style="height: 260px; width: 313.812px;" rowspan="3" valign="top">
<p><strong>K2view_&lt;LU Name&gt;</strong> - A new keyspace is created for each <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-are-deployed-objects-reflected-in-cassandra">deployed LU.</a></p>
<p>Note that when deploying the LU to the Fabric debug server, Fabric also concatenates the Fabric version and the project name to the keyspace of each LU. For example: k2view_test_cust_6_2_kb_fabric_project.</p>
</td>
<td style="height: 46px; width: 208.925px;" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td style="height: 46px; width: 203.863px;" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr style="height: 64px;">
<td style="height: 64px; width: 208.925px;" valign="top">
<p>entity</p>
</td>
<td style="height: 64px; width: 203.863px;" valign="top">
<p>Stores the list of all <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUIs</a> and their MicroDB.</p>
</td>
</tr>
<tr style="height: 150px;">
<td style="height: 150px; width: 208.925px;" valign="top">
<p>entity_chunks</p>
</td>
<td style="height: 150px; width: 203.863px;" valign="top">
<p>Stores large LUIs. The <a href="/articles/01_fabric_overview/02_fabric_glossary.md#mdb--microdb">MicroDB </a> of a Large LUI is divided into chunks and is stored in entity_chunks.</p>
<p><a href="/articles/02_fabric_architecture/01_fabric_architecture_overview.md#21-fabric-storage">Click here for more information about Fabric storage.</a></p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 184px; width: 313.812px;" rowspan="4" valign="top">
<p><strong>k2masking</strong> - Tables that support Broadway masking mechanism.</p>
<p>The <strong>k2masking</strong> keyspace can be created using the installation SQL script provided as part of the Masking library.</p>
<p><a href="/articles/19_Broadway/actors/07_masking_and_sequence_actors.md">Click here for more information about Broadway Masking.</a></p>
</td>
<td style="height: 46px; width: 208.925px;" valign="top">
<p><strong>Table Name</strong></p>
</td>
<td style="height: 46px; width: 203.863px;" valign="top">
<p><strong>Table Description</strong></p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 46px; width: 208.925px;">
<p>masking_cache</p>
</td>
<td style="height: 46px; width: 203.863px;">
<p>Stores the cached masked values.</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 46px; width: 208.925px;">
<p>uniqueness</p>
</td>
<td style="height: 46px; width: 203.863px;">
<p>Supports the uniqueness of the masked value per execution and masking ID.</p>
</td>
</tr>
<tr style="height: 46px;">
<td style="height: 46px; width: 208.925px;">
<p>TDM_SEQ_MAPPING</p>
</td>
<td style="height: 46px; width: 203.863px;">
<p>Keeps the masked values per execution ID, including the additional information such as LU, table, IID to enable the creation of reports.</p>
</td>
</tr>
</tbody>
</table>



[Click here for more information about Fabric Architecture overview.](/articles/02_fabric_architecture/01_fabric_architecture_overview.md)

By default, the system DB it is set to Cassandra. 

When working with a non-Cassandra System DB, like SQLite or PostgreSQL, the follows shall be set at config.ini:

* Populate the parameter values included in `[system_db]` section, which introduced in Fabric V7.2. If you use Cassandra as system DB, then you can ignore this section.

* Set the `SERVER_AUTHENTICATOR` config parameter's value to be 'fabric' (default value is 'cassandra') . For more information about `SERVER_AUTHENTICATOR` config options read [here](/articles/26_fabric_security/13_user_IAM_configiration.md#server_authenticator-configuration). Note: When using Cassandra as the System DB, 'fabric' may also be used as an authenticator.

   

The ```DEFAULT_GLOBAL_STORAGE_TYPE``` parameter in the [fabric] section is set to SYSTEM_DB. This means that by default, the Fabric storage type is the same as the Fabric System DB. You can either update the [system_db] settings only, impacting both the Storage and System DB types together, or define each one of them to have a different DB type.



[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/07_cassandra_basic_commands.md)

