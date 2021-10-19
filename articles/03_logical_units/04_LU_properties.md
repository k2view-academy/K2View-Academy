<studio>
  
# LU Properties

The Properties tab in the LU schema window contains the following properties:

<table style="width: 900px;">
<tbody>
<tr>
<td width="170pxl">
<p><strong>Sync Properties</strong></p>
</td>
<td width="630pxl">
<p>Sets the <a href="/articles/14_sync_LU_instance/01_sync_LUI_overview.md">sync properties</a> and <a href="/articles/14_sync_LU_instance/04_sync_methods.md">sync method</a> on an LU level.</p>
</td>
</tr>
<tr>
<td>
<p><strong>Events</strong></p>
</td>
<td>
<p>Sets the <a href="/articles/07_table_population/08_project_functions.md#event-function">Event functions</a> that are triggered upon a Sync's success or failure or after a successful Delete instance.</p>
<p>To select an Event function, click the three dots next to the Events property and select the function name. Only Event functions are displayed.</p>
</td>
</tr>
<tr>
<td width="236">
<p><strong>Storage</strong></p>
</td>
<td width="368">
<p>Enables setting the <a href="/articles/32_LU_storage/01_LU_storage_overview.md#storage-types">storage type</a> of the LU instances and overrides the default settings defined in the config.ini file.</p>
</td>
</tr>
<tr>
<td>
<p><strong>Cache Location</strong></p>
</td>
<td>
<p>Enables modification of the default <a href="/articles/32_LU_storage/02_storage_management.md#changing-the-location-of-the-cache">cache location</a> of the LU instances.</p>
<p>If the default path in the config.ini is updated to another path, the cache is always located according to the config.ini, regardless of this property's settings in the Fabric Studio.</p>
</td>
</tr>
<tr>
<td width="236">
<p><strong>Enable Data Encryption Property</strong></p>
</td>
<td width="368">
<p>Enables encrypting LU data in Fabric.</p>
</td>
</tr>
<tr>
<td width="200">
<p><strong>IID Finder Properties</strong></p>
</td>
<td width="700">
<p>Enables the IID Finder to proactively perform a sync on an LU.</p>
</td>
</tr>
<tr>
<td width="236">
<p><strong>Cassandra Configuration Properties</strong><strong>&nbsp;</strong></p>
</td>
<td width="368">
<p>Enables overriding the default Cassandra configuration on an LU schema during the first deployment of the LU.</p>
</td>
</tr>
</tbody>
</table>






[![Previous](/articles/images/Previous.png)](03_LU_schema_window.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_create_a_new_LU_object.md)

</studio>
