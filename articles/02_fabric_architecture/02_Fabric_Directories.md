
# Fabric Server- Main Directories
Fabric server has the several directories under its home directory: $K2_HOME. Below is the list of the main fabric directories under the home directory: 
[fabric overview](01_what%20is%20fabric.md)</strong></p>
</td>
<td style="height: 62px; width: 463.2px;">
<p><strong>Directory Content</strong></p>
</td>
</tr>
<tr style="height: 62px;">
<td style="height: 62px; width: 101.6px;">
<p>fabric</p>
</td>
<td style="height: 62px; width: 463.2px;">
<p>This directory and its sub-directories contain fabric code, scripts, templates, and resources.</p>
</td>
</tr>
<tr style="height: 45px;">
<td style="height: 45px; width: 101.6px;">
<p>logs</p>
</td>
<td style="height: 45px; width: 463.2px;">
<p>&nbsp;Fabric log files.</p>
</td>
</tr>
<tr style="height: 19px;">
<td style="height: 19px; width: 101.6px;">
<p>config</p>
</td>
<td style="height: 19px; width: 463.2px;">
<p>Fabric configuration files.</p>
</td>
</tr>
<tr style="height: 32px;">
<td style="height: 32px; width: 101.6px;">monitor</td>
<td style="height: 32px; width: 463.2px;"> Contains Prometheus JMX exporter for Fabric and Cassandra execution monitoring.</td>
</tr>
<tr style="height: 17px;">
<td style="height: 17px; width: 101.6px;">apps</td>
<td style="height: 17px; width: 463.2px;">Third party Applications and tools, used by Fabric, like Java, Cassandra, Grafana, Redis...</td>
</tr>
<tr style="height: 72px;">
<td style="height: 72px; width: 101.6px;">storage</td>
<td style="height: 72px; width: 463.2px;">storage of the data on each Fabric server: contains the ludb.jar artifact files for each deployed LU, Staging XMLs for iidFinder, and Reference (common) files.</td>
</tr>
<tr style="height: 72px;">
<td style="height: 72px; width: 101.6px;">/dev/shm/fdb_cache</td>
<td style="height: 72px; width: 463.2px;">This directory holds the cached database files for the LU instances.</td>
</tr> 
<tr style="height: 84px;">
<td style="height: 84px; width: 101.6px;">ExternalJars</td>
<td style="height: 84px; width: 463.2px;">It contains the .jar files of all database jars that we not provided as a product out of the box, and external jars used for the Fabric implementation.</td>
</tr>
<tr style="height: 32.8px;">
<td style="height: 32.8px; width: 101.6px;">export</td>
<td style="height: 32.8px; width: 463.2px;">It contains the output files of  <strong>set output</strong> command.</td>
</tr>
<tr style="height: 17px;">
<td style="height: 17px; width: 101.6px;">trace</td>
<td style="height: 17px; width: 463.2px;">Trace files.</td>
</tr>
<tr style="height: 17px;">
<td style="height: 17px; width: 101.6px;">webserver</td>
<td style="height: 17px; width: 463.2px;">The home directory of Fabric web admin. This directory can also contain manipulations (rewrite) on the URL when invoking Fabric web-services.</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>
