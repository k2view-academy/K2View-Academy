# CDC Configuration

Fabric [config.ini file](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) has several sections for CDC configuration:

<table style="width: 886px;" width="900pxl">
<tbody>
<tr>
<td style="width: 200px;" valign="top">
<p><strong>Section Name</strong></p>
</td>
<td style="width: 350px;">
<p><strong>Parameter Name</strong></p>
</td>
<td style="width: 350px;">
<p><strong>Parameter Description</strong></p>
</td>
</tr>
<tr>
<td style="width: 178.906px;" rowspan="7" valign="top">
<h5>cdc</h5>
</td>
<td style="width: 349.531px;" valign="top">
<p>#CDC_PUBLISH_MODE=ON</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>This parameter controls whether a CDC message is published to external systems. The following values can be set:</p>
<ul>
<li>ON (default), when implemented, publish the CDC message.</li>
<li>OFF, never publish the CDC message.</li>
<li>IF_SETUP, (Studio Debug default) when implemented, publish the CDC message only if the CDC publisher has been configured in the config.ini file.</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 349.531px;" valign="top">
<p>#MAX_CONCURRENT_TRX=2147483647</p>
<p>&nbsp;</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>The number of parallel CDC transactions allowed at any one given time.</p>
</td>
</tr>
<tr>
<td style="width: 349.531px;" valign="top">
<p>#TRX_MAX_WAIT_MS=60000</p>
<p>&nbsp;</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>Max wait time to create a new transaction (in milliseconds). Since the transaction creation process might be waiting for the transactions pool to become available, this parameter puts a limit on such wait time.</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td style="width: 349.531px;" valign="top">
<p>#TRANSACTION_ACKNOWLEDGE_TIME_SEC=60</p>
<p>&nbsp;</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>A maximum number of seconds to acknowledge an idle transaction that has not yet been saved to Cassandra.</p>
<p><a href="02_cdc_process_architecture.md#transaction_acknowledge_time_sec-parameter">Click for more information about this parameter.</a></p>
</td>
</tr>
<tr>
<td style="width: 349.531px;" valign="top">
<p>#TRX_MAX_DURATION_SEC=300</p>
<p>&nbsp;</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>Max duration time for a transaction, when the time elapsed, a transaction will be dropped.</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td style="width: 349.531px;" valign="top">
<p>#CDC_PUBLISHER_JOB_AFFINITY=</p>
<p>&nbsp;</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>Affinity for CDC publisher job</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td style="width: 349.531px;" valign="top">
<p>#CDC_CONSUMER_JOB_AFFINITY=</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>Affinity for CDC consumer job</p>
</td>
</tr>
<tr>
<td style="width: 178.906px;" valign="top">
<h5>cdc_data_publish</h5>
</td>
<td style="width: 349.531px;" valign="top">
<p>Parameters for the CDC_TRANSACTION_PUBLISHER job.</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>Populate the #BOOTSTRAP_SERVERS by IP address of the Kafka servers.</p>
<p>It is possible to populate several IP addresses separated by a comma.</p>
</td>
</tr>
<tr>
<td style="width: 178.906px;" valign="top">
<h5>cdc_data_publish_ssl</h5>
</td>
<td style="width: 349.531px;" valign="top">
<p>SSL connection parameters when connecting the Kafka servers.</p>
</td>
<td style="width: 335.562px;" valign="top">&nbsp;</td>
</tr>
<tr>
<td style="width: 178.906px;" valign="top">
<h5>cdc_data_consume</h5>
</td>
<td style="width: 349.531px;" valign="top">
<p>Parameters for the CDC_TRANSACTION_CONSUMER job.</p>
</td>
<td style="width: 335.562px;" valign="top">
<p>Populate the #BOOTSTRAP_SERVERS by IP address of the Kafka servers.</p>
<p>It is possible to populate several IP addresses separated by a comma.</p>
</td>
</tr>
<tr>
<td style="width: 178.906px;" valign="top">
<h5>cdc_data_consume_ssl</h5>
</td>
<td style="width: 349.531px;" valign="top">
<p>SSL connection parameters when connecting the Kafka servers.</p>
</td>
<td style="width: 335.562px;" valign="top">&nbsp;</td>
</tr>
</tbody>
</table>






[![Previous](/articles/images/Previous.png)](05_cdc_consumers_implementation.md)
