# How Is Storage Managed During Sync?



<table>
<tbody>
<tr>
<td width="450pxl">
<p><span class="md-plain md-expand">When an instance needs to be brought to Fabric, the </span><span class="md-meta-i-c  md-link"><a spellcheck="false" href="/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands"><span class="md-plain">GET</span></a></span><span class="md-plain md-expand"> command is invoked.</span></p>
<p><span class="md-plain md-expand">The GET command is used to synchronize one or multiple instance IDs (LUI) from the source systems to Fabric and to load them into the Fabric's memory. </span></p>
<p><span class="md-plain md-expand">During GET, Fabric either retrieves the data from the source (if Sync is needed), or brings the latest version of the LUI from Cassandra DB. The LUI version number is kept in <span class="md-pair-s "><strong><span class="md-plain">k2view_[LU_name].entity</span></strong></span> table together with the corresponding MicroDB blob.</span></p>
<p><span class="md-plain md-expand">To optimize the LUI retrieval process, the latest version of LUI is saved in the Fabric cache in addition to the Cassandra DB. However the cache size is limited per the configuration in config.ini file.</span></p>
</td>
<td width="450pxl"><img src="images/32_01_diagram.PNG" alt="Storage" /></td>
</tr>
</tbody>
</table>





