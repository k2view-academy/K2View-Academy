# Fabric Commands

Fabric includes a large set of commands to view Fabric configuration , update Fabric settings, and run Fabric processes. 

You can either run Fabric commands from Fabric console, or run Fabric commands in the implementation code.

Note the Fabric commands are case insensitive, e.g. you can run either **Get**, **get**, or **GET** command.

## Fabric Help

After you [log in to Fabric](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#login-fabric), type **help** to view the list of available Fabric commands.

To view the description and syntax of a specific command, type **help [command]**. 

For example: 

![help example](/articles/02_fabric_architecture/images/04_fabric_commands_help_example.png)

## Fabric Commands- Main Groups

 Fabric commands can be divided into the following groups:

### Get and Delete LU Instance Commands

The GET command is used to bring information for a given [LU instance]() and synchronize information from the data sources if needed.

Note that you can get LU instances of multiple LUs by one GET command, but you cannot get multiple LU instances of the same LU by one GET command. If you try to get multiple LU instances of the same LU by one GET command, you get the following error message:

`Only single instance per LUT can be used on the same GET command.`

The DELETE INSTANCE command deletes LU instance, or multiple LU instances from Fabric. 

Unlike the GET command, you can delete several LU instance of the same LU by one DELETE command.

Below is the list of GET and DELETE commands:

<table width="900pxl">
<tbody>
<tr>
<td width="100pxl">
<p><strong>Command Name</strong></p>
</td>
<td width="250pxl">
<p><strong>Description</strong></p>
</td>
<td width="300pxl">
<p><strong>Syntax</strong></p>
</td>
<td width="250pxl">
<p><strong>Example</strong></p>
</td>
</tr>
<tr>
<td width="100pxl">
<p><h5>GET</p>
</td>
<td width="250pxl">
<p>This command is used to bring information for a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LU instance</a>, or multiple LU instances of different LUs. Fabric checks if the LU instance needs to be synced from the source systems, and syncs the LU instance if needed, or brings the latest version of the LU instance from Fabric.</p>
<p>&nbsp;</p>
</td>
<td width="300pxl">
<p>Get an LU instance:</p>
<p>get &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>get &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td width="250pxl">
<p>get Customer.1;</p>
<p>Get instance ID 1 of Customer LU.</p>
<p>get Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and instance ID 34 of CRM LU.</p>
</td>
</tr>
<tr>
<td width="100pxl">
<p><h5>GETF</p>
</td>
<td width="250pxl">
<p>This command is used to bring information for a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LU instance</a>, or multiple LU instances of different LUs. The instance is returned by a <a href="/articles/07_table_population/09_creating_an_LUDB_function.md">LUDB function</a>.</p>
<p>&nbsp;</p>
</td>
<td width="300pxl">
<p>Get an LU instance:</p>
<p>GETF &lt;LUT_NAME&gt;.&lt;function name&gt;(arg...)[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>GET &lt;LUT_NAME&gt;.&lt;function name&gt;(arg...)@&lt;DC&gt;,&lt;LUT_NAME_2&gt;.&lt;function name&gt;(arg...);</p>
</td>
<td width="250pxl">
<p>getf Customer.fnCreateInstId(235);</p>
<p>This function adds 1000 to the input value and returns 1235 value. Fabric gets Customer no. 1235.</p>
</td>
</tr>
<tr>
<td width="100pxl">
<p><h5>USE</p>
</td>
<td width="250pxl">
<p>USE command is an alias of GET command.</p>
</td>
<td width="300pxl">
<p>Get an LU instance:</p>
<p>use &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>use &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td width="250pxl">
<p>use Customer.1;</p>
<p>Get instance ID 1 of Customer LU.</p>
<p>use Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and instance ID 34 of CRM LU.</p>
</td>
</tr>
<tr>
<td width="100pxl">
<p><h5>DELETE INSTANCE</p>
</td>
<td width="250pxl">
<p>Delete a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LU instance</a> or a list of LU instances from Fabric.</p>
<p>&nbsp;</p>
</td>
<td width="300pxl">
<p>Delete one instance:</p>
<p>delete instance &lt;LUT_Name&gt;.'&lt;instance_id&gt;'';&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
<p>Delete multiple instances:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
<p>delete instance &lt;LUT_Name&gt;.'&lt;instance_id&gt;',&lt;LUT_Name&gt;.'&lt;instance_id&gt;',...;</p>
</td>
<td width="250pxl">
<p>delete CRM.10;</p>
<p>delete CRM.10, CRM.3;</p>
<p>delete CRM.5, Customer.30;</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

#### Remote GET/GETF Commands

You can execute the GET command from a data center (DC) which is not connected to any data sources, while other DCs are connected to the source interfaces. In this case you need to populate the DC parameter of the GET/GETF commands to invoke the remote DC (connected to the source). Invoking the command on the remote DC will be done via JDBC. The remote GET/GETF commands return the instances after the GET/GETF command has finished execution on the remote Fabric node. Cassandra then, replicates the data between the nodes of the Cassandra cluster.

The remote GET/GETF command runs on a random Fabric node on the remote DC. You must verify the permissions for the GET/GETF execution on both Fabric nodes- the local and the remote nodes.

Note that  it is the user's responsibility to identify if the [sync](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) of the LU instance is required, and only then run the remote GET/GETF commands. This will prevent unnecessary calls to the remote Fabric node, and getting the local LU instance version instead.


### Fabric View

Fabric has commands that gives a view on the Fabric configuration and settings. For example:

- Fabric cluster information:

  - CLUSTERID
  - CLUSTERSTATUS
  - TIME
  - VERSION INFO- the version of the installed Fabric

- Information of the [deployed implementation](/articles/16_deploy_fabric/01_deploy_Fabric_project.md):

  - DESCRIBE- query Fabric's metadata structure.
  - LIST- list of [deployed objects](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-are-deployed-objects-reflected-in-the-fabric-server).

- General Information:

  - SET-  displays the current session’s settings: [Sync Mode](/articles/14_sync_LU_instance/02_sync_modes.md#sync-modes-1), the LU instances in the scope (the latest LU instance, get on each LU), the [deployed project name](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-do-i-check-which-project-is-deployed-to-fabric), [Globals' values](/articles/08_globals/01_globals_overview.md#globals-overview), and the active Environment. 

    <!--Drop 2- Add a link to Environments--> 

### Fabric Setting

#### Fabric Setting- Session Level

Fabric **SET** commands enables update Fabric setting on the session level:

- Set [global variables](/articles/08_globals/03_set_globals.md#how-do-i-use-the-set-command)

- [Sync](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) setting:

  - Set [sync mode](/articles/14_sync_LU_instance/02_sync_modes.md)
  - Set [sync timeout](/articles/14_sync_LU_instance/08_sync_timeout.md)
  - Set [ignore source exception](/articles/14_sync_LU_instance/03_sync_ignore_source_exception.md)

- Set the active environment.

  <!--Drop 2- Add a link to Environments-->

- Set output- set the output format of query results. 

- Set time to live (TTL) in seconds for each [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui):

  **set instance_ttl;**

  The LUI is deleted automatically from Fabric after the TTL passes.

##### Reset Session Level Setting

You can reset all the set related parameters on the session level to their default value using the following command:

**set default;**

#### Fabric Setting- Cluster Level

**set_global** command enables setting an active environment or a [global value](/articles/08_globals/03_set_globals.md#how-do-i-use-set_global-global-command) on Fabric cluster level.

### Fabric Security and Credentials

#### Fabric Security Commands

Master key generation commands. The master key is used to encrypt the LU instance data as well as encryption of the [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md)  details.

[Click for more information about Fabric security hardening.]

<!--Drop 3- Add a link to Fabric Security Hardening-->

#### Fabric Credentials Commands

List of commands to set Fabric credentials (users, roles, permissions...).

<!--Drop 1- Add a link to Fabric Credentials-->

### Fabric Deployment- Deploy and Drop Commands

Fabric commands to deploy [Fabric implementation](/articles/16_deploy_fabric/03_offline_deploy.md) and Fabric Environments on the Fabric console.

<!--Drop 2- Add a link to Environments-->

### Fabric Environments and Interfaces

Fabric enables a [deployment of Fabric environments](), and setting active environment on [session]() or [cluster levels]().

In addition, you can test the [DB interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) of the active environment using the **test_connection** command. You can send a DB interface parameter to test a specific interface or run the **test_connection** command without parameters to test the connection of all DB interfaces of the active environment.

### Run Queries on Cassandra

You can run CQL queries on Cassandra inside Fabric server using the **cql** command.

See example below:

`fabric>cql select * from k2view_customer.entity;`

[Click for more information about Cassandra Basic Commands.](/articles/02_fabric_architecture/07_cassandra_basic_commands.md)

### Jobs Commands

Get Fabric jobs' list and status, start, stop, update, and resume jobs.

Click for more information about Fabric jobs.

<!--Drop 2- add a link to Fabric jobs-->

Batch Process Commands

Process Control

Execution Monitoring

Common (Reference) Tables

Fabric Transactions

CDC and Search

Fabric Broadway

 

