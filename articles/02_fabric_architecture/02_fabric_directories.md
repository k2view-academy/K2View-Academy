
# Fabric Server- Main Directories
Fabric server has the several directories under its home directory: $K2_HOME. Below is the list of the main fabric directories:

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
<p>This directory and its sub-directories contain Fabric code, scripts, templates, and resources.</p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/fabric/scripts</h4>
</td>
<td width="600">
<p>Fabric Scripts:&nbsp;</p>
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
<p>Fabric upgrade scripts. Each Fabric version has its own sub-directory. For example: If you upgrade Fabric 5.5.x to Fabric 6.x, run  the scripts under <strong>toV6.0 </strong>sub-directory<strong>.</strong></p>
</td>
</tr>
<tr>
<td width="300">
<h5>$K2_HOME/logs</h4>
</td>
<td width="471">
<p>Fabric log files.</p>
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
<p>Holds the Prometheus JMX exporter for execution monitoring.</p>
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
<p>Stores the data on each Fabric Server and holds the <a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md">ludb.jar  artifact files for each deployed LU or Web Service</a>, Staging XMLs for iidFinder, and Reference (common) files.</p>
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



<!--Add links:-->

<!--Drop 1- deployment, fabric architecture, fabric logs, fabric commands, fabric basic commands and scripts-->
[![Previous](/articles/images/Previous.png)](/articles/02_fabric_architecture/01_fabric_architecture.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md)
