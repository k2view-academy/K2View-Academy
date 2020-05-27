
# Fabric Server- Main Directories
Fabric server has the several directories under its home directory: $K2_HOME. Below is the list of the main fabric directories:

<table width="900pxl">
<tbody>
<tr>
<td width="300pxl">
<p><h4><strong>Directory Name</strong></p>
</td>
<td width="600pxl">
<p><h4><strong>Directory Content</strong></p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/fabric</p>
</td>
<td width="600">
<p>This directory and its sub-directories contain Fabric code, scripts, templates, and resources.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/fabric/scripts</p>
</td>
<td width="600">
<p>Fabric Scripts:&nbsp;</p>
<ul>
<li>Deployment scripts</li>
<li>iidFinder scripts</li>
<li>Fabric execution monitoring scripts</li>
<li>Reset scripts</li>
<li>Stop and start scripts</li>
<li>Fabric status scripts</li>
</ul>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/fabric/upgrade</p>
</td>
<td width="600">
<p>Fabric upgrade scripts. Each Fabric version has its own sub-directory. For example: If you upgrade Fabric 5.5.x to Fabric 6.x, you need to update the scripts under <strong>toV6.0 </strong>sub-directory<strong>.</strong></p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/logs</p>
</td>
<td width="471">
<p>Fabric log files.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/config</p>
</td>
<td width="600">
<p>Fabric configuration files.</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/monitor</p>
</td>
<td width="600">
<p>Holds the Prometheus JMX exporter for execution monitoring.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/apps</p>
</td>
<td width="600">
<p>Third-party applications and tools used by Fabric. For example, Java, Cassandra, Grafana, or Redis.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/storage</p>
</td>
<td width="600">
<p>Stores the data on each Fabric Server and holds the <strong>ludb.jar</strong> artifact files for each deployed LU or Web Service, Staging XMLs for iidFinder, and Reference (common) files.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>/dev/shm/fdb_cache</p>
</td>
<td width="600">
<p>Holds the cached database files for the LU Instances.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/ExternalJars</p>
</td>
<td width="600">
<p>Holds the .jar files of all database jars that have not been provided as an out-of-the box product and external jars used for the Fabric implementation.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/export</p>
</td>
<td width="471">
<p>Holds the output files of the <strong>set</strong> <strong>output</strong> Fabric command.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/trace</p>
</td>
<td width="600">
<p>Trace files.</p>
</td>
</tr>
<tr>
<td width="300">
<p><h5>$K2_HOME/webserver</p>
</td>
<td width="600">
<p>The Home directory of the Fabric web admin. This directory can also hold manipulations (rewrites) on the URL when invoking Fabric Web Services.</p>
</td>
</tr>
</tbody>
</table>

<!--Add links:-->

<!--Drop 1- deployment, fabric architecture, fabric logs, fabric commands, fabric basic commands and scripts-->

<!--Drop 2- iidFinder, Fabric execution monitoring, reference (common) tables, LU storage-->