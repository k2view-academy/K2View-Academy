
# Fabric Server - Main Directories
There are several directories under the Fabric server $K2_HOME directory. The following table lists the main ones:

<table width="900pxl">
<tbody>
<tr>
<td width="300pxl" valign="top">
<h4><strong>Directory Name</strong></h3>
</td>
<td width="600pxl" valign="top">
<h4><strong>Directory Content</strong></h3>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/fabric</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds Fabric code, scripts, templates and resources.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/fabric/scripts</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds the following Fabric scripts:&nbsp;</p>
<ul>
<li><a href="/articles/16_deploy_fabric/03_offline_deploy.md#deployment-scripts-syntax-and-options">Deployment</a></li>
<li>IIDFinder</li>
<li>Fabric execution monitoring</li>
<li><a href="/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#reset-fabric">Reset</a></li>
<li>Stop and start (executed by <a href="/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#start-and-stop-fabric-commands">k2fabric stop and k2fabric start commands).</a></li>
<li>Fabric status</li>
</ul>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/fabric/upgrade</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds Fabric upgrade scripts. Each Fabric version has its own sub-directory. For example, when upgrading Fabric 5.5.x to Fabric 6.x, run the scripts under the  <strong>toV6.0</strong> sub-directory.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/logs</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds Fabric <a href="/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md">log files.</a></p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/config</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds<a href="/articles/02_fabric_architecture/05_fabric_main_configuration_files.md"> Fabric configuration files.</a></p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/monitor</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds the Prometheus JMX exporter for execution monitoring.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/apps</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds third-party applications and tools used by Fabric. For example, Java, Cassandra, Grafana, or Redis.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/storage</h4>
</td>
<td width="600pxl" valign="top">
<p>Stores data on each Fabric Server and holds the <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md">ludb.jar artifact files for each deployed LU or Web Service</a>, IIDFinder XMLs, and Reference (common) files.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>/dev/shm/fdb_cache</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds the cached database files of the LUI. This directory can be modified in config.ini setup.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/ExternalJars</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds the .jar files of all database jars that have not been provided as an out-of-the box product and also external jars used for the Fabric implementation.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/export</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds the output files of the <a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-setting">set output Fabric command.</a></p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/trace</h4>
</td>
<td width="600pxl" valign="top">
<p>Holds Trace files.</p>
</td>
</tr>
<tr>
<td width="300pxl" valign="top">
<h5>$K2_HOME/webserver</h4>
</td>
<td width="600pxl" valign="top">
<p>The Home directory of the Fabric Web Admin. This directory can also hold manipulations (rewrites) on the URL when invoking Fabric Web Services.</p>
</td>
</tr>
</tbody>
</table>

## **Fabric Local Server Main Directories**

As of version 6.1, the Fabric server is automatically installed on Windows when a new Fabric Studio version is installed. The Cassandra installation has also been added to the Fabric Studio.

Fabric and Cassandra are installed under the following directories:

- Fabric Studio, C:\K2View\<Fabric version>\Studio.
- Fabric server, C:\K2View\<Fabric version>\Server.
- Apache-cassandra-3.11.x, C:\K2View\Fabric_6.1\Server and C:\K2View\<Fabric version>\ apache-cassandra-3.11.x

### Fabric Server Directories

The Fabric server directory has the following sub-directories:

- **Fabric**, this directory and its sub-directories hold Fabric code, scripts, templates and resources. Note that the Fabric Studio setup adds a shortcut to the fabric.bat file under the fabric\bin folder. This file opens the Fabric console CMD in the local Fabric server and can be used only after running the Fabric server from the Studio. The fabric.bat file connects to the Fabric server via the default JDBC port.
- **BroadwayUI**, Broadway directory.
- **Webserver**, the Home directory of the Fabric Web Admin. This directory can also hold manipulations (rewrites) on the URL when invoking Fabric Web Services.

### FabricHome Directory

The Fabric Studio creates a **FabricHome** directory under each Fabric project's directory and holds the following sub-directories:

- [config](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeconfig)
- [export](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeexport)
- [logs](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homelogs)
- [storage](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homestorage)


<!--Add links:-->
<!-- Drop 2- Broadway -->
<!-- Drop 3- iidFinder, LU storage -->
<!-- Drop 4- Trace files -->

[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/01_fabric_architecture_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md)
