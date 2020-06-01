# Fabric Commands

Fabric includes a large set of commands to view Fabric configuration , update Fabric settings, and run Fabric processes. 

You can either run Fabric commands from Fabric console, or [run Fabric commands in the implementation code](/articles/05_DB_interfaces/09_fabric_API_for_DB_interfaces.md#execute-fabric-command).

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


### Release LU

Fabric **release** command is used to detach the [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) from the session for a list of LUs or for all LUs.

Click for more information about LUI storage and management.

<!--Drop 2- Add a link to LU storage and management--> 

### Fabric View

Fabric has commands that gives a view on the Fabric configuration and settings. For example:

- Fabric cluster information:

  - **clusterid**
  - **clusterstatus**
  - **time**
  - **version info**

- - CLUSTERID
  - CLUSTERSTATUS
  - TIME
  - VERSION INFO- the version of the installed Fabric. Note that you can get Fabric version outside outside Fabric server using [k2fabric -version](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#get-fabric-version) command.

- Information of the [deployed implementation](/articles/16_deploy_fabric/01_deploy_Fabric_project.md):

  - **describe**- query Fabric's metadata structure.
  - **list**- list of [deployed objects](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-are-deployed-objects-reflected-in-the-fabric-server).

- General Information:

  - **set** -  displays the current session’s settings: [Sync Mode](/articles/14_sync_LU_instance/02_sync_modes.md#sync-modes-1), the LU instances in the scope (the latest LU instance, get on each LU), the [deployed project name](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-do-i-check-which-project-is-deployed-to-fabric), [Globals' values](/articles/08_globals/01_globals_overview.md#globals-overview), and the active Environment. 

    <!--Drop 2- Add a link to Environments--> 

### Fabric Setting

#### Fabric Setting- Session Level

Fabric **set** command enables update Fabric setting on the session level:

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

You can reset all the set related parameters on the session level to their default value using the following command: **set default;**

#### Fabric Setting- Cluster Level

**set_global** command enables setting an active environment or a [global value](/articles/08_globals/03_set_globals.md#how-do-i-use-set_global-global-command) on Fabric cluster level.

### Fabric Security and Credentials

#### Fabric Security Commands

Master key generation commands. The master key is used to encrypt the LU instance data as well as encryption of the [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md)  details.

Click for more information about Fabric security hardening.

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

Example:

`fabric>cql select * from k2view_customer.entity;`

[Click for more information about Cassandra Basic Commands.](/articles/02_fabric_architecture/07_cassandra_basic_commands.md)

### Jobs Commands

Get Fabric jobs' list and status, start, stop, update, and resume jobs.

Click for more information about Fabric jobs.

<!--Drop 2- add a link to Fabric jobs-->

### Batch Process Commands

The batch process mechanism allows executing different types of fabric commands in a batch mode on the remote Fabric nodes.

Fabric provides commands to start execution, retry, and cancel execution of a batch process as well as commands to monitor the execution on batch processes on Fabric.

Note that the migrate commands are used as aliases to the batch commands.

Click for more information about Fabric batch process mechanism.

<!--Drop 2- add a link to Batch Process-->

### Process Control

#### Ps and Kill Commands

**ps** command displays the current running tasks on Fabric like Fabric command, Fabric Job, Web service and Graphit, [Sync process](/articles/14_sync_LU_instance/01_sync_LUI_overview.md),   Broadway actor, parser ...

<!--Drop 2- add a links to jobs, parsers, graphit, broadway-->

<!--Drop 1- add a link to WS-->

**kill** command enables you to kill  any running task as displayed by the **ps** command.

### Execution Monitoring

The trace mechanism (**trace** command) enables the tracing of Fabric internal operations by request and writing them into tracing files.

<!--Drop 3- add a links to trace-->

### Common (Reference) Tables

Fabric enables creating Common (Reference) Tables which can be used by all LUs or Web services. A Common Table typically contains metadata. For example, a postal-code table that identifies the postal code of customer addresses.

Common Tables' commands enable synchronizing and get the sync status of the Common tables. 

Click for more information about Common (Reference) Tables.

<!--Drop 2- add a link to Common (reference) tables -->

### Fabric Transactions

Fabric System of Record (SOR) functionality enables running a single transaction on a specific [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) of the [Instance ID](/articles/01_fabric_overview/02_fabric_glossary.md#instance-id), or on the (common) Reference table. This functionality enables Fabric to become the master of the data rather than [syncing data](/articles/14_sync_LU_instance/01_sync_LUI_overview.md) from external systems. This way, Fabric can get transactions feeds and update the related instance IDs/Common Tables accordingly. You must start (**begin**) a transaction before running the **insert**, **update**, or **delete** commands and end (**commit** or **rollback**) to commit or rollback the updates.

Fabric provides a set to commands to **begin** transaction, run **select**, **insert**, **update**, **delete** on the [LUI](/articles/01_fabric_overview/02_fabric_glossary.md#lui) or Common Table data, and **commit** or **rollback** the updates. 

Click for more information about Fabric as System of Record.

<!--Drop 2- add links to SOR + Common (reference) tables -->

### CDC and Search

Fabric has a full CDC (Change Data Capture) solution to notify external systems on data changes. Fabric CDC solution has a built-in integration with **Elasticsearch** to enable a cross [LU instance](/articles/01_fabric_overview/02_fabric_glossary.md#lui) search.

For example:  search all customers called “John Doe” and live in “New-York”. 

Fabric provides a **search** command to initiate a search on the **Elasticsearch**. In addition, Fabric provides the **cdc_republish_instance** command to republish CDC data on LU instances.

Click for more information about Fabric CDC and Search.

<!--Drop 2- add a link to CDC and Search -->

### Fabric Broadway

Fabric **broadway** command runs a [Broadway flow]().

<!--Drop 2- add a link to Broadway -->

### Queries Helpers

An SQL statement can be preceded by the keyword **EXPLAIN** or by the phrase **EXPLAIN QUERY PLAN**. This modification causes the SQL statement to behave like a query and to return information about how the SQL statement will operate if the **EXPLAIN** keyword or phrase is omitted.

The output from **EXPLAIN** and **EXPLAIN QUERY PLAN** is intended for interactive analysis and troubleshooting only.

Example:

![Query Helpers](/articles/02_fabric_architecture/images/04_fabric_command_query_helpers.png)



## Execute Fabric Commands in the Implementation Code

You can execute the Fabric commands either 