# LU Storage Management

The Sync mechanism synchronizes data between the data sources and the Fabric database. During the sync, extraction and transformation processes are executed on an [LU Instance (LUI)](/articles/01_fabric_overview/02_fabric_glossary.md#lui) whereby data is retrieved from the source systems and then loaded into the Fabric database.

The GET command is used to synchronize one or multiple instance IDs (LUI) from the source systems to Fabric. The response time of the GET command is highly important due to multiple requests that Fabric receives concurrently. To optimize the LUI retrieval process, Fabric has a cache mechanism that holds a pre-defined number of instances and enables faster load of the instance into the memory. 

The below diagram describes how GET process is executed on the Fabric server.

<table>
<tbody>
<tr>
<td width="400pxl">
<p><span class="md-plain md-expand">Invoke a&nbsp;<span class="md-meta-i-c  md-link"><a spellcheck="false" href="/articles/02_fabric_architecture/04_fabric_commands.md#get-lui-commands"><span class="md-plain">GET</span></a></span> command to bring an instance ID to Fabric.&nbsp;</span></p>
<p><span class="md-plain md-expand">During GET, Fabric checks the Sync mode (ON, OFF or Force) to decide whether the data should be retrieved from the source. </span></p>
<p><span class="md-plain md-expand">If Sync is needed, the data is brought from the source and saved to Cassandra's&nbsp;<span class="md-pair-s "><strong><span class="md-plain">entity</span></strong></span> table as MicroDB blob together with the latest version number.&nbsp;</span><span class="md-plain md-expand">In addition, the latest version of LUI is saved to the Fabric cache. </span></p>
<p><span class="md-plain md-expand">If the Sync is not needed, Fabric will attempt to bring the MDB file from the cache prior to going to Cassandra DB.&nbsp;</span></p>
<p><span class="md-plain md-expand">The cache is always syncronized with Cassandra DB but since it has limited size, it might not include the MDB file for the required instance. Then the MDB file is created and saved to the Fabric cache.</span></p>
<p><span class="md-plain md-expand">The cache size and location on Fabric server are defined in the&nbsp;<strong>[fabricdb]</strong> section of the <a href="TO_DO">config.ini</a> file.</span></p>
<p><span class="md-plain md-expand">Eventually the MDB file is loaded to Fabric's memory. Note that only one LUI can be kept in the memory - per session and LU.</span></p>
</td>
<td width="500pxl"><img src="images/32_01_diagram.PNG" alt="Storage" /></td>
</tr>
</tbody>
</table>


[![Previous](/articles/images/Previous.png)](01_LU_storage_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_big_lu_storage.md) 



