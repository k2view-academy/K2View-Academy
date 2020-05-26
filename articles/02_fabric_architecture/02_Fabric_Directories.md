
# Fabric Server- Main Directories
Fabric server has the several directories under its home directory: $K2_HOME. Below is the list of the main fabric directories:

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 300px;">
<p><strong>Directory Name</strong></p>
</td>
<td style="width: 600px;">
<p><strong>Directory Content</strong></p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/fabric</p>
</td>
<td style="width: 531.6px;">
<p>This directory and its sub-directories contain fabric code, scripts, templates, and resources.</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/fabric/scripts</p>
</td>
<td style="width: 531.6px;">
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
<td style="width: 189.4px;">
<p>$K2_HOME/fabric/scripts/upgrade</p>
</td>
<td style="width: 531.6px;">
<p>Fabric upgrade scripts. Each Fabric version has its own sub-directory. For example: If you upgrade Fabric 5.5.x to Fabric 6.x, you need to update the scripts under toV6.0.</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/logs</p>
</td>
<td style="width: 531.6px;">
<p>Fabric log files.</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/config</p>
</td>
<td style="width: 531.6px;">
<p>Fabric configuration files.</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/monitor</p>
</td>
<td style="width: 531.6px;">
<p>It contains Prometheus JMX exporter for Fabric and Cassandra execution monitoring.</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/apps</p>
</td>
<td style="width: 531.6px;">
<p>Third-party applications and tools used by Fabric, like Java, Cassandra, Grafana, Redis...</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/storage</p>
</td>
<td style="width: 531.6px;">
<p>The storage of the data on each Fabric server: contains the ludb.jar artifact files for each deployed LU or Web-Service, Staging XMLs for iidFinder, and Reference (common) files.</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>/dev/shm/fdb_cache</p>
</td>
<td style="width: 531.6px;">
<p>This directory holds the cached database files for the LU instances.</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/ExternalJars</p>
</td>
<td style="width: 531.6px;">
<p>It contains the .jar files of all database jars that we not provided as a product out of the box and external jars used for the Fabric implementation.</p>
</td>
</tr>
<tr>
<td style="width: 89.4px;">
<p>$K2_HOME/export</p>
</td>
<td style="width: 531.6px;">
<p>It contains the output files of the <strong>set output</strong> command.</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/trace</p>
</td>
<td style="width: 531.6px;">
<p>Trace files.</p>
</td>
</tr>
<tr>
<td style="width: 189.4px;">
<p>$K2_HOME/webserver</p>
</td>
<td style="width: 531.6px;">
<p>The home directory of Fabric web admin. This directory can also contain manipulations (rewrite) on the URL when invoking Fabric web-services.</p>
</td>
</tr>
</tbody>
</table>

Add links:

Drop 1- deployment, fabric architecture, fabric logs, fabric commands, fabric basic commands and scripts

Drop 2- iidFinder, Fabric execution monitoring, reference (common) tables, LU storage