# Fabric - Main Configuration Files

## Configuration Directory
**Important:**
- Fabric configuration files are located under the $K2_HOME/config directory. 
- Fabric configuration **template files** are located under the $K2_HOME/fabric/config.template. 


Make sure to edit the configuration files under the **$K2_HOME/config** directory and not under the template directory.

## Main Configuration Files

<table>
<tbody>
<tr>
<td width="300pxl" valign="top">
<p><strong>Configuration File</strong></p>
</td>
<td width="600pxl"  valign="top">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini">config.ini</a></p>
<p>&nbsp;</p>
</td>
<td width="600pxl" valign="top">
<p>Fabric's main configuration file holding different sections of parameters where each section has its own parameters. Default Fabric values are set for commented parameters.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p>iifConfig.ini</p>
</td>
<td width="600pxl" valign="top">
<p>The IIDFinder mechanism's main configuration file.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p><a href="/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#nodeid">node.id</a></p>
</td>
<td width="600pxl" valign="top">
<p>List of Fabric node identifiers for the Affinity mechanism. Supports several Fabric clusters on one Cassandra cluster.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p>logback.xml, logback-iid_finder.xml, and logback-init_finder.xml</p>
</td>
<td width="600pxl" valign="top">
<p><a href="/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md">Fabric logs</a> configuration files.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p>jvm.options</p>
</td>
<td width="600pxl" valign="top">Sets the flags used by Fabric to startup the JVM (Java Virtual Machine). For example:&nbsp; To use the machine's local timezone, uncomment the &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#set-fabric-time-zone"> -DFABRIC_LOCAL_TIMEZONE parameter</a> and set it to true to use the local time-zone of the Fabric server.</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p>jmxremote.access and jmxremote.password</p>
</td>
<td width="600pxl" valign="top">
<p>Remote JMX API access to monitoring.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<p>modules</p>
</td>
<td width="600pxl" valign="top">
<p>List of internal Fabric modules. Each module depends on the previous modules in the file. You can comment some internal Fabric modules and <a href="/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#k2fabric-restart">restart the Fabric node</a> to avoid starting the commented modules and have a lightweight start on Fabric. For example: comment the <strong>jobs</strong> module to avoid running jobs on the Fabric node. The following modules can be commented:</p>
<ul>
<li><strong>jobs</strong>,running Fabric jobs.</li>
<li><strong>webserver</strong>,connecting Fabric via http or https. For example: <a href="/articles/15_web_services/01_web_services_overview.md">invoke Fabric WS</a>, <a href="/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md">deploy Fabric implementation from Fabric Studio.</a></li>
<li><strong>commonarea</strong>, accessing common (reference) tables. </li>
<li><strong>dbserve</strong>, connecting Fabric via remote JDBC connection. For example: login Fabric console using <a href="/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#login-fabric">fabric command,</a> defining Fabric node as a <a href="/articles/05_DB_interfaces/05_adding_a_fabric_and_remote_fabric_interface_type.md">remote Fabric interface.</a></li>
<li><strong>clustertimecheck</strong>, comparing the time between the Fabric nodes, included in the Fabric cluster and throwing an error if a difference is identified.</li>
</ul>
</td>
</tr>
</tbody>
</table>



### config.ini

Fabric's main configuration file which holds different sections of parameters where each section has it own parameter. Default Fabric values for commented parameters.

<table width="900pxl">
<tbody>
<tr>
<td width="250pxl valign="top">
<p><strong>Parameters Category</strong></p>
</td>
<td width="200pxl" valign="top">
<p><strong>Section Names</strong></p>
</td>
<td width="450pxl" valign="top">
<p><strong>Main Parameters</strong></p>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Cassandra Connection</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>cassandra</li>
<li>default_session</li>
</ul>
</td>
<td width="450pxl" valign="top">
<p>Configurations for creating a connection to the Cassandra cluster:</p>
<ul>
<li>Replication options</li>
<li>Consistency level</li>
<li>Cassandra's connection details.</li>
<li>Set an SSL connection.</li>
</ul>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Fabric Settings</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>fabric</li>
<li>fabric_cluster</li>
<li>jdbc-server</li>
</ul>
</td>
<td width="450pxl" valign="top">
<ul>
<li>Date and time formats.</li>
<li>Storage and Export directories</li>
<li>Fabric auditing settings</li>
<li>Connection pools settings</li>
<li>The maximum number of concurrent connections that are allowed for a single data source.</li>
<li>Batch process settings</li>
<li>Default <a href="/articles/14_sync_LU_instance/02_sync_modes.md">Sync mode</a></li>
<li>Sync of Common (reference) tables</li>
<li>Parallel Sync on <a href="/articles/07_table_population/13_LU_table_population_execution_order.md#how-do-i-set-the-population-order">Table Populations with the same execution order</a>.</li>
<li>Enable running <a href="/articles/02_fabric_architecture/04_fabric_commands.md#delete-instances-if-not-exist">DELETE INSTANCES IF NOT EXIST</a> Fabric command.</li>
<li>LUI compression types when storing the LUI in Cassandra.</li>
<li>Web service parameters</li>
<li>Fabric interaction with Cassandra for adding, removing or editing user&rsquo;s activities.</li>
<li>Fabric Heartbeat mechanism for Fabric jobs</li>
</ul>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Fabric Jobs</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>jobs</li>
</ul>
</td>
<td width="450pxl" valign="top">
<ul>
<li>Thread pool size- set the maximum number of jobs to be executed in parallel in a single Fabric node.</li>
<li>Archiving time- the number of hours to delete the Job record from the k2_jobs table.</li>
</ul>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Batch Process</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>batch_process</li>
</ul>
</td>
<td width="450pxl" valign="top">
<ul>
<li>MAX_WORKERS_PER_NODE parameter sets the maximum number of threads&nbsp;that are used in all batch process units (executions) together on this node.&nbsp; &nbsp; &nbsp;</li>
<li>Support setting MAX_WORKERS_PER_NODE=0 per node to avoid running a batch&nbsp;process on a specific node.&nbsp; &nbsp;&nbsp;</li>
</ul>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Parsers</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>parsers</li>
</ul>
</td>
<td width="450pxl" valign="top">
<ul>
<li>
<p>Writing method into Cassandra- using Cassandra loader or JDBC driver for parsers. The PARSER_WRITING_TYPE parameter defines the method used to load the data into Cassandra by Parsers: Cassandra Loader or JDBC driver. The default value is JDBC.</p>
</li>
</ul>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>IIDFinder</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>finder</li>
</ul>
</td>
<td width="450pxl" valign="top">
<ul>
<li>Enable ORPHANAGE job, handles the Orphans record in the background.</li>
<li>Enable SWEEP job, sweeps the invalid cache entries.</li>
</ul>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Cassandra Loader- loader configuration</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>default_loader</li>
<li>parser_loader</li>
<li>batch_process_loader</li>
<li>iid_finder_loader</li>
</ul>
</td>
<td width="450pxl" valign="top">
<p>Cassandra Loader configuration.</p>
<p>The default setting of default_loader for parsers, batch processes, or IIDFinder activities can be overridden.</p>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Cassandra loader- session configuration</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>loader_session&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</li>
</ul>
</td>
<td width="450pxl" valign="top">
<p>Overrides the default_session for the sessions, created for Cassandra Loader operations (parsers, batch process, IIDFinder)</p>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Common (reference) tables</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>common_area_config</li>
<li>common_area_kafka_producer</li>
<li>common_area_kafka_consumer</li>
<li>kafka_ssl_properties</li>
<li>common_area_memory_queues_config&nbsp; &nbsp; &nbsp;</li>
</ul>
</td>
<td width="450pxl" valign="top">
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>LUI Storage</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>fabricdb</li>
<li>cassandra_entity_storage</li>
</ul>
</td>
<td width="450pxl" valign="top">
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Fabric Security Hardening</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>encryption</li>
</ul>
</td>
<td width="450pxl" valign="top">
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="250pxl" valign="top">
<p>Change Data Capture (CDC) and Search</p>
</td>
<td width="200pxl" valign="top">
<ul>
<li>cdc</li>
<li>cdc_data_publish</li>
<li>cdc_data_publish_ssl</li>
<li>cdc_data_consume</li>
<li>search_engine</li>
</ul>
</td>
<td width="450pxl" valign="top">
<p>&nbsp;</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>

#### Set Fabric Time Zone

Fabric is a multi-node, multi Datacenter (DC) system which interacts and exchanges data with target and source systems and exposes APIs. This may present a challenge when dealing with time zones.

By default, Fabric is configured to use the **Coordinated Universal Time (UTC)** zone, regardless of the time zone of the host server. UTC is roughly equivalent to **GMT time** but does not observe Day Light Saving.

When getting data from source systems and storing it in Fabric, the best practice is to normalize all timestamps (populated in Fabric) to UTC and store any additional time zone information in separate fields.

When exposing or exporting data to upstream systems that require a different time presentation, the conversion should be done during the data movement (not stored in Fabric) and planned per consumer. This behavior makes it easier to process logs and analyze data interchange of cross time zones datacenters, nodes and external systems.

Fabric does allow changing this default behavior and changing the process time zone from UTC to the server's local time:

- Go to the **jvm.options** file in the **config directory** and uncomment the following line (remove the # character) from the following parameter: **#-DFABRIC_LOCAL_TIMEZONE=true**.
This affects internal activities such as [log file reporting](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md) or trace files and can also change the behavior of some JDBC drivers against date fields that contain time zone information.

<!-- drop 4- add a link to trace -->

- To change how data is saved to Fabric, open the **config.ini** file in the **config folder** and change the **DATETIME_FORMAT_LOCAL_TIMEZONE** parameter as follows:

   **DATETIME_FORMAT_LOCAL_TIMEZONE=true**

   This affects the conversion of Date objects to a table field entry in the Fabric database and formats them according to the Wall Time of the local time zone. 

Note that you must restart Fabric after making these updates.

Since these entries affect different stages of data intake and exposure, it is important to test the Date format after changing them to make sure the required format is achieved against all sources and data types. Changes will not affect data already in the database.

### node.id

This file lists Fabric node identifiers for the Affinity mechanism. The following identifiers can be set in the node.id file:

-  **uuid**, if this parameter is not defined, Fabric automatically generates a value for the **uuid** during startup.
- **logical_id,** used to define an Affinity for the Fabric jobs mechanism. The logical_id contains only letters and numbers. Several nodes can share the same logical_id. In addition, several logical IDs can be set for one node. The priority of each logical_id can be defined by adding several threads that will be allocated for each logical node. The number is concatenated to the logical_id name by a colon. For example, the logical_id for a given node has the following values: A:2, B:3, and C:6. If there are 10 threads in the pool for this node, then the job using logical_id **C** as an Affinity will get higher priority on this node, as 6 out of the 10 threads will be allocated to this job.
- **cluster_id**, cluster identifier. The cluster_id is set to support a configuration of several Fabric clusters on one Cassandra cluster. The cluster_id is concatenated to each [keyspace](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP1_19_Fabric_Architecture_Tali/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) name. For example- if the cluster_id is set to “fabric1”, then Fabric concatenates “_fabric1” to each keyspace. 

## Update Configuration Files

### Update Config.ini, Node.id, and Logback Files

Fabric adds a notification to the k2fabric.log if the updates are loaded automatically to Fabric. If the changes require a restart of the Fabric node, Fabric adds a warning to the [log file](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md).

Examples:

- INFO [FileChangeMonitor] 2020-06-07 18:30:40,207 c.k.f.c.i.Configurator - [LID1000000000496] Configurator changed fabric.DELETE_INSTANCES_IF_NOT_EXIST_COMMAND_ENABLED from false to true INFO [FileChangeMonitor] 2020-06-07 18:30:40,207 c.k.f.c.i.FileChangeMonitor - [LID1000000000496] config.ini was reloaded
- WARN [FileChangeMonitor] 2020-06-07 18:38:33,270 c.k.f.c.i.Configurator - [LID1000000000496] Configurator will not update fabricdb.MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE from com.k2view.cdbms.dao.CassandraEntityStorage to NONE at runtime.

### Update Jvm.option, Jmxremote, and Modules Files
Fabric must be restarted to apply the updates. 

### Update iifConfig.ini 
When updating the iifConfig.ini, do the following:

- When updating the parameters under **orphanage_job** or **sweep_job** sections, or **ENABLE_KAFKA_DELTA** parameters, restart the Fabric node.
- Restart the IIDFinder process.

Click for more information about the IIDFinder Process.



[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/04_fabric_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md)

