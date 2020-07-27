# Fabric Commands

Fabric includes a number of commands for viewing Fabric configurations, updating Fabric settings and running Fabric processes. Fabric commands can be executed from either the Fabric console or via user code (project implementation) that invokes Fabric commands in the [execute() and fetch() methods](/articles/05_DB_interfaces/09_fabric_API_for_DB_interfaces.md#execute-fabric-command).

Note that Fabric commands are **not** case sensitive. For example, a Get, get, or GET command can be run.

## Fabric Help

After [logging in to Fabric](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#login-fabric), type help/Help/HELP to view a list of available Fabric commands. To view the description and syntax of a specific command, type **help [command name]**.

For example: 

![help example](/articles/02_fabric_architecture/images/04_fabric_commands_help_example.png)

## Fabric Commands - Main Groups

 Fabric commands can be divided into the following groups: 

<table>
<tbody>
<tr>
<td width="350pxl" valign="top">
<p><strong>Command Group</strong></p>
</td>
<td width="550pxl" valign="top">
<p><strong>Group Description</strong></p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands">Get LUI</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Get an <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a> into Fabric.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href=/articles/02_fabric_architecture/04_fabric_commands.md#delete-lui-command>Delete LUI</a></p>
</td>
<td width="550pxl" valign="top">
<p>Delete an <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a> from Fabric.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#release-lu">Release LU</a></p>
</td>
<td width="550pxl" valign="top">
<p> Detaches the <a href="/articles/01_fabric_ov Dw/02_fabric_glossary.md#lui">LUI</a> from the session for a list of LUs or for all LUs.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-view">Fabric View</a></p>
</td>
<td width="550pxl" valign="top">
<p>View of the Fabric configurations and settings</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-settings">Fabric Settings</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Session and cluster levels settings.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-security-and-credentials">Fabric Security and Credentials</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Set the Master Key for an LUI or the encryption details of an interface.</p>
<p>Set users, roles, and permissions.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-deployment--deploy-and-drop-commands">Fabric Deployment and Drop</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Deploy and drop Fabric implementation commands.</p>
</td>
</tr>
<tr>
<td width="350pxl">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-environments-and-interfaces">Fabric Environments and Interfaces</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Deploy environments and test connections on an active environment.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#run-queries-on-cassandra">Run Queries on Cassandra</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Run CQL queries on Cassandra.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#jobs-commands">Jobs</a></p>
</td>
<td width="550pxl" valign="top">
<p>Fabric jobs execution and monitoring commands.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#batch-process-commands">Batch Process</a></p>
</td>
<td width="550pxl" valign="top">
<p>Batch processing execution and monitoring commands.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#process-control">Process Control</a></p>
</td>
<td width="550pxl">
<p>Check for running tasks and kill a task if needed.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#execution-monitoring">Execution Monitoring</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Trace Fabric operations and write the results to trace files.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#common-reference-tables">Common (Reference) Tables</a></p>
</td>
<td width="550pxl" valign="top">
<p>Commands for handling Common (reference) tables.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-transactions">Fabric Transactions</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Support transactions to update LUI or Common (reference) table data (Fabric as System of Record).</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#cdc-and-search">CDC and Search</a></p>
</td>
<td width="550pxl" valign="top">
<p>Support Change Data Capture (CDC) across all LUI search functionalities.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-broadway">Fabric Broadway</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Run Broadway flow.</p>
</td>
</tr>
<tr>
<td width="350pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/04_fabric_commands.md#queries-helpers">Queries Helpers</a></p>
<p>&nbsp;</p>
</td>
<td width="550pxl" valign="top">
<p>Use EXPLAIN and EXPLAIN QUERY PLAN to analyze SQL queries on Fabric data.</p>
</td>
</tr>
</tbody>
</table>

### Get LUI Commands

#### Get Instance

The **GET** command is used to bring information for a given [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) and to synchronize information from data sources if needed.

Note that you can get LUIs from multiple LUs using one GET command, but you cannot get multiple LUIs from the same LU using one GET command. 

The following message is displayed when trying to get multiple LUIs from the same LU using one GET command:

`Only single instance per LUT can be used on the same GET command.`

The following table lists the GET commands:

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="100pxl">
<p><strong>Command Name</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Syntax</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Example</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<h5>GET</h5>
</td>
<td valign="top" width="250pxl">
<p>Brings information for a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a>, or multiple LUIs of different LUs. Fabric checks if the LUI needs to be synced from the source system, syncs the LUI if needed, or brings the latest version of the LUI from Fabric.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>get &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>get &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td valign="top" width="250pxl">
<p>get Customer.1;</p>
<p>Get instance ID 1 of Customer LU.</p>
<p>get Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and instance ID 34 of CRM LU.</p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<h5>GETF</h5>
</td>
<td valign="top" width="250pxl">
<p>Brings information for a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a>, or multiple LUIs of different LUs. The instance is returned by an <a href="/articles/07_table_population/09_creating_an_LUDB_function.md">LUDB function</a>.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>GETF &lt;LUT_NAME&gt;.&lt;function name&gt;(arg...)[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>GET &lt;LUT_NAME&gt;.&lt;function name&gt;(arg...)@&lt;DC&gt;,&lt;LUT_NAME_2&gt;.&lt;function name&gt;(arg...);</p>
</td>
<td valign="top" width="250pxl">
<p>getf Customer.fnCreateInstId(235);</p>
<p>This function adds 1000 to the input value and returns the value 1235, Fabric gets Customer # 1235.</p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<h5>USE</h5>
</td>
<td valign="top" width="250pxl">
<p>USE command is an alias of GET command.</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>use &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>use &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td valign="top" width="250pxl">
<p>use Customer.1;</p>
<p>Get Instance ID 1 of Customer LU.</p>
<p>use Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and Instance ID 34 of CRM LU.</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

#### Remote GET and GETF Commands

**GET** and **GETF** commands can be executed from a [datacenter (DC)]() that is not connected to data  sources if other DCs are connected to the source interfaces. To do so, populate the DC parameter name of the GET and GETF commands to invoke the remote DC connected to the data source via the JDBC. The remote GET and GETF commands return the instances after executing the commands on the remote Fabric node. Cassandra then replicates the data between the nodes of the Cassandra cluster.

The remote GET and GETF commands run on a random Fabric node on the remote DC. Therefore, always verify the permissions for the GET and GETF commands’ execution on Fabric’s local and remote nodes.

Note that users are responsibile for identifying if a [sync](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) on an LUI is required, and to only then run the remote GET or GETF commands. This will prevent unnecessary calls to the remote Fabric node and getting the local LUI version instead.

### Delete LUI Command

The **DELETE INSTANCE** command deletes an LUI or multiple LUIs from Fabric. 

Unlike the GET command, several LUI from the same LU can be deleted using one DELETE command.

The following table lists the  DELETE commands:

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="100pxl">
<p><strong>Command Name</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="200pxl">
<p><strong>Syntax</strong></p>
</td>
<td valign="top" width="200pxl">
<p><strong>Example</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<h5>DELETE INSTANCE</h5>
</td>
<td valign="top" width="250pxl">
<p>Delete a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a> or a list of LUIs from Fabric.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="200pxl">
<p>Delete one instance:</p>
<p><h6>delete instance&nbsp;&lt;LUT_Name&gt;.'&lt;instance_id&gt;';&nbsp;</p>
<p>Delete multiple instances:</p>
<p><h6>delete instance &lt;LUT_Name&gt;.'&lt;instance_id&gt;',&lt;LUT_Name&gt;.'&lt;instance_id&gt;',...;</p>
</td>
<td valign="top" width="200pxl">
<p>delete CRM.10;</p>
<p>delete CRM.10, CRM.3;</p>
<p>delete CRM.5, Customer.30;</p>
</td>
</tr>
<tr>
<td valign="top" width="100pxl">
<h5>DELETE INSTANCES IF NOT EXIST</h5>
</td>
<td valign="top" width="250pxl">
<p>Delete all LUIs that do not exist in the source system. To run this command, set the config.ini file as follows:</p>
<ul>
<li><h6>Set DELETE_INSTANCES_IF_NOT_EXIST_COMMAND_ENABLED parameter to true</li>
<li><h6>Uncomment DELETE_INSTANCES_IF_NOT_EXIST_COMMAND_ENABLED parameter</li>
</ul>
</td>
<td valign="top" width="200pxl">
<p>delete instances if not exist &lt;LUT_Name&gt;;</p>
</td>
<td valign="top" width="200pxl">
<p>delete instances if not exist CRM;</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

### Release LU

The Fabric Release command is used to detach the [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) from the session on a list of LUs or all LUs.

<!--Drop 2- Add a link to LU storage and management--> 

### Fabric View

Fabric has commands that display a Fabric configuration and its settings. For example:

- Fabric cluster information:
  - CLUSTERID, returns the cluster identifier defined on the node.id.
  - CLUSTERSTATUS, returns the status of all Fabric nodes. Also includes: node_id, [logical IDs](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#nodeid), DC name, IP addresses.
  - TIME, gets the node system time.
  - VERSION INFO, the version of the installed Fabric. Note that to get the Fabric version when logged out of Fabric, use the [k2fabric -version](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#get-fabric-version) command.

- Information about the [deployed implementation](/articles/16_deploy_fabric/01_deploy_Fabric_project.md):
  - **DESCRIBE,** to query Fabric's metadata structure.
  - **LIST,** a list of [deployed objects](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-are-deployed-objects-reflected-in-the-fabric-server).

- General information:
  - **SET,** displays the current session’s settings: [Sync Mode](/articles/14_sync_LU_instance/02_sync_modes.md#sync-modes-1), the LUI in the scope (the latest LUI, get on each LU), the [deployed project name](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-do-i-check-which-project-is-deployed-to-fabric), [Globals' values](/articles/08_globals/01_globals_overview.md#globals-overview) and the active environment. 

<!--Drop 2- Add a link to Environments--> 

### Fabric Settings
 
#### Fabric Setting - Session Level

The Fabric **SET** command enables updating Fabric settings on a session level:

- Set [global variables](/articles/08_globals/03_set_globals.md#how-do-i-use-the-set-command).

- [Sync](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) settings:

  - Set [sync mode](/articles/14_sync_LU_instance/02_sync_modes.md).
  - Set [sync timeout](/articles/14_sync_LU_instance/08_sync_timeout.md).
  - Set [ignore source exception](/articles/14_sync_LU_instance/03_sync_ignore_source_exception.md).

- Set the active environment.

  <!--Drop 2- Add a link to Environments-->

- **SET OUTPUT** command, set the output format of query results. 

- **SET INSTANCE_TTL** command, set the time to live (TTL) in seconds for each [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui); the LUI is deleted automatically from Fabric after the TTL ends.

##### Reset Session Level Setting

Use the following command to reset all the related parameters set on a session level to their default value: **SET DEFAULT**;

#### Fabric Setting - Cluster Level

Use the **SET_GLOBAL** command to set an active environment or a [global value](/articles/08_globals/03_set_globals.md#how-do-i-use-set_global-global-command) on a Fabric cluster. 

The values are kept in the **global_settings** Cassandra table under [k2system keyspace.](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md)

### Fabric Security and Credentials

#### Fabric Security Commands

- Master key generation commands used to encrypt LUI data and to encrypt an [interface’s](/articles/05_DB_interfaces/01_interfaces_overview.md) details.\
  Click for more information about Fabric Security Hardening.

- Fabric Credentials Commands, a list of commands for setting Fabric credentials like, users, roles, tokens or permissions.

<!--Drop 3- Add a link to Fabric Security Hardening-->

<!--Drop 1- Add a link to Fabric Credentials-->

###  Deploy and Drop Commands

Fabric commands to deploy [Fabric implementation](/articles/16_deploy_fabric/03_offline_deploy.md) and Fabric Environments on the Fabric console.

<!--Drop 2- Add a link to Environments-->

#### Drop LU Command

The **DROP LUTYPE** command deletes [LU metadata (LU schema)](/articles/03_logical_units/01_LU_overview.md) and its [LUIs](/articles/01_fabric_overview/02_fabric_glossary.md#lui) from Fabric. The drop command also deletes the keyspace for the LU from Cassandra and the related LU entry from k2_lut_info in Cassandra. Once the LU is dropped it should be [redeployed to the Fabric server](/articles/16_deploy_fabric/01_deploy_Fabric_project.md).

[Click for more information about Cassandra Keyspaces.](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md)

Note that this command is used mainly in a Testing environment to restart deployment configurations. In Production, the DROP LUTYPE command and [reset.sh script](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#reset-fabric) are rarely used. A possible scenario is to clean the environment after a soft launch prior to starting an actual Production run. A Drop is followed by an initial load / migration of the data for the dropped LU.

##### Drop LU Syntax

DROP LUTYPE [LU Name];

Example: 

DROP LUTYPE Customer;

### Fabric Environments and Interfaces

Fabric enables the deployment of Fabric environments and setting active environments on [session](/articles/02_fabric_architecture/04_fabric_commands.md#fabric-setting---session-level) or [cluster levels](/articles/02_fabric_architecture/04_fabric_commands.md#fabric-setting---cluster-level).

The [DB interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) of an active environment can be tested using the **TEST_CONNECTION** command. To do so, run the **TEST_CONNECTION** command without parameters to test the connection of all DB interfaces in the active environment.

<!-- drop 3- add a link on environments -->

### Run Queries on Cassandra

CQL queries can be run on Cassandra in the Fabric server using the **CQL** command only for select statement.

Example:

`fabric>cql select * from k2view_customer.entity;`

[Click for more information about Cassandra Basic Commands.](/articles/02_fabric_architecture/07_cassandra_basic_commands.md)

### Jobs Commands

Get the Fabric jobs' list and status, start, stop, update and resume jobs.

Click for more information about Fabric jobs.

<!--Drop 2- add a link to Fabric jobs-->

### Batch Process Commands

The Batch process mechanism enables executing different types of Fabric commands in a batch mode on remote Fabric nodes.

Fabric has commands that start an execution, retry and cancel the execution of a batch process and commands that monitor an execution on batch processes in Fabric.

Note that MIGRATE commands are used as aliases to BATCH commands.

Click for more information about Fabric batch process mechanism.

<!--Drop 2- add a link to Batch Process-->

### Process Control

#### PS and Kill Commands

- The **PS** command displays the current tasks running on Fabric. For example, Fabric commands, Fabric Jobs, Web Service and Graphit, [Sync processes](/articles/14_sync_LU_instance/01_sync_LUI_overview.md), Broadway actor or parser.

- The **KILL** command is used to kill any running task displayed by the PS command.

<!--Drop 2- add a links to jobs, parsers, graphit, broadway-->

<!--Drop 1- add a link to WS-->

### Execution Monitoring

The **TRACE** command enables tracing internal Fabric operations by request and writing them into Tracing files.

<!--Drop 3- add a links to trace-->

### Common (Reference) Tables

Fabric enables creating Common (Reference) tables which can be used by all LUs or Web Services. A Common table typically contains metadata. For example, a Postal Code table that identifies the postal code of customer addresses.

Common table commands enable synchronizing, getting the sync status and waiting for sync processing of Common tables to be completed before continuing the workflow.

Click for more information about Common (Reference) Tables.

<!--Drop 2- add a link to Common (reference) tables -->

### Fabric Transactions

The Fabric System of Record (SOR) functionality enables running a single transaction on a specific [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) of the [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id) or on a (common) Reference table. When this functionality is used, Fabric becomes the master of the data rather than [syncing data](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) from external systems. This way, Fabric can get transaction feeds and update a related Instance ID or Common table accordingly. Always start a transaction with a **BEGIN** command before running INSERT, UPDATE or DELETE commands, and use **COMMIT** or **ROLLBACK** commands to commit or rollback the updates.

Fabric has a set of commands to support the SOR functionality:

- **BEGIN**, start a transaction.
- **SELECT**, **UPDATE**, **INSERT**, and **DELETE**, run Select, Insert, Update and Delete transactions on the [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) or Common table data.
- **COMMIT** and **ROLLBACK**, commit or rollback the updates.

Fabric also enables writing the transaction into a delta table using the **SET ASYNC_TRX=true** command.

Click for more information about Fabric as a System of Record.


<!--Drop 2- add links to SOR + Common (reference) tables -->

### CDC and Search

The Fabric CDC (Change Data Capture) solution notifies external systems about data changes and has built-in integration with Elasticsearch to enable a cross [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) search.

For example: search all customers called “John Doe” that live in “New-York”.

Fabric has a **SEARCH** command that initiates a search on Elasticsearch. In addition, the Fabric **CDC_REPUBLISH_INSTANCE** command can be used to republish CDC data on LUI.

Click for more information about Fabric CDC and Search.

<!--Drop 2- add a link to CDC and Search -->

### Fabric Broadway

Fabric **BROADWAY** command runs a Broadway flow.

<!--Drop 2- add a link to Broadway -->

### Queries Helpers

An SQL statement can be preceded by the **EXPLAIN** keyword or by the **EXPLAIN QUERY PLAN** phrase. The SQL statement then behaves like a query and returns information about the SQL statement’s operations if the EXPLAIN keyword or phrase is omitted. 

**EXPLAIN** and **EXPLAIN QUERY PLAN** are intended for interactive analysis and troubleshooting only.

Example:

![Query Helpers](/articles/02_fabric_architecture/images/04_fabric_command_query_helpers.png)

[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md)
