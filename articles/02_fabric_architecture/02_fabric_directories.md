
# Fabric Server- Main Directories
There are several directories under the Fabric server $K2_HOME directory. The following table lists the main ones:

<table width="900pxl">
<tbody>
<tr>
<td width="300pxl">
<h4><strong>Directory Name</strong></h3>
</td>
<td width="600pxl">
<h4><strong>Directory Content</strong></h3>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/fabric</h4>
</td>
<td width="600">
<p>This directory and its sub-directories contain hold Fabric code, scripts, templates and resources.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/fabric/scripts</h4>
</td>
<td width="600">
<p>Fabric scripts:&nbsp;</p>
<ul>
<li><a href="/articles/16_deploy_fabric/03_offline_deploy.md#deployment-scripts-syntax-and-options">Deployment scripts</a></li>
<li>iidFinder scripts</li>
<li>Fabric execution monitoring scripts</li>
<li><a href="/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#reset-fabric">Reset scripts</a></li>
<li>Stop and start scripts, executed by <a href="/articles/02_fabric_architecture/03_fabric_basics_getting_started.md#start-and-stop-fabric-commands">k2fabric stop and k2fabric start commands.</a></li>
<li>Fabric status scripts</li>
</ul>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/fabric/upgrade</h4>
</td>
<td width="600">
<p>Fabric upgrade scripts. Each Fabric version has its own sub-directory. For example: when upgrading Fabric 5.5.x to Fabric 6.x, run the scripts under the  <strong>toV6.0</strong> sub-directory.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/logs</h4>
</td>
<td width="471">
<p>Fabric <a href="/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md">log files.</a></p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/config</h4>
</td>
<td width="600">
<p><a href="/articles/02_fabric_architecture/05_fabric_main_configuration_files.md">Fabric configuration files.</a></p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/monitor</h4>
</td>
<td width="600">
<p>Holds the Prometheus JMX exporter for for execution monitoring.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/apps</h4>
</td>
<td width="600">
<p>Third-party applications and tools used by Fabric. For example, Java, Cassandra, Grafana, or Redis.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/storage</h4>
</td>
<td width="600">
<p>Stores data on each Fabric Server and holds the <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md">ludb.jar  artifact files for each deployed LU or Web Service</a>, Staging XMLs for iidFinder, and Reference (common) files.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>/dev/shm/fdb_cache</h4>
</td>
<td width="600">
<p>Holds the cached database files for the LU Instances.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/ExternalJars</h4>
</td>
<td width="600">
<p>Holds the .jar files of all database jars that have not been provided as an out-of-the box product and external jars used for the Fabric implementation.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/export</h4>
</td>
<td width="471">
<p>Holds the output files of the <a href="/articles/02_fabric_architecture/04_fabric_commands.md#fabric-setting">set output Fabric command.</a></p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/trace</h4>
</td>
<td width="600">
<p>Trace files.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/webserver</h4>
</td>
<td width="600">
<p>The Home directory of the Fabric web admin. This directory can also hold manipulations (rewrites) on the URL when invoking Fabric Web Services.</p>
</td>
</tr>
</tbody>
</table>

## **Fabric Local Server Main Directories**

As of version 6.1, the Fabric server is automatically installed on Windows when a new Fabric Studio version is installed. 

The Cassandra installation has also been added to the Fabric Studio.

Fabric and Cassandra are installed under the following directories:

- Fabric Studio is installed under C:\K2View\<Fabric version>\Studio.
- The Fabric server is installed under C:\K2View\<Fabric version>\Server.
- Apache-cassandra-3.11.4 is installed under C:\K2View\Fabric_6.1\Server  C:\K2View\<Fabric version>\ apache-cassandra-3.11.4

### Fabric Server Directories

The Fabric server directory has the following sub-directories:

- **Fabric**, this directory and its sub-directories hold Fabric code, scripts, templates and resources. Note that the Fabric Studio setup adds a shortcut to the fabric.bat file under the fabric\bin folder. This file opens the Fabric console CMD in the local Fabric server and can be used only after running the Fabric server from the Studio. The fabric.bat file connects to the Fabric server via the default JDBC port.
- **BroadwayUI**, Broadway directory <!--add a link to Broadway items – drop 2 - ->.
- **Webserver**, the Home directory of the Fabric Web Admin. This directory can also hold manipulations (rewrites) on the URL when invoking Fabric Web Services.

### FabricHome Directory

The Fabric Studio creates the **FabricHome** directory under each Fabric project directory and holds the following sub-directories:

- [config](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeconfig)
- [export](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homeexport)
- [logs](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homelogs)
- [storage](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homestorage)


<!--Add links:-->

<!--Drop 1-  fabric architecture-->
[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/01_fabric_architecture.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md)