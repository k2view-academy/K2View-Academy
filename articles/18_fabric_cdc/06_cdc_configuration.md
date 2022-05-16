# CDC Configuration

Fabric [config.ini file](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) has several sections for CDC configuration:

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="200pxl">
<p><strong>Section Name</strong></p>
</td>
<td colspan="2" width="700pxl">
<p><strong>Parameters</strong></p>
</td>
</tr>
<tr>
<td rowspan="8" valign="top" width="200pxl">
<h5>cdc</h5>
</td>
<td valign="top" width="300pxl">
<p><strong>Parameter Name</strong></p>
</td>
<td valign="top" width="400pxl">
<p><strong>Parameter Description</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>#CDC_PUBLISH_MODE=ON</p>
</td>
<td valign="top" width="400pxl">
<p>This parameter controls whether a CDC message is published to external systems. The following values can be set:</p>
<ul>
<li>ON (default), when implemented, publish the CDC message.</li>
<li>OFF, never publish the CDC message.</li>
<li>IF_SETUP, (Studio Debug default) when implemented, publish the CDC message only if the CDC publisher has been configured in the config.ini file.</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>#MAX_CONCURRENT_TRX=2147483647</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="400pxl">
<p>The number of parallel CDC transactions allowed at any one given time.</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>#TRX_MAX_WAIT_MS=60000</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="400pxl">
<p>Max wait time to create a new transaction (in milliseconds). Since the transaction creation process might be waiting for the transactions pool to become available, this parameter puts a limit on such wait time.</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>#TRANSACTION_ACKNOWLEDGE_TIME_SEC=60</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="400pxl">
<p>A maximum number of seconds to acknowledge an idle transaction that has not yet been saved to Cassandra.</p>
<p><a href="02_cdc_process_architecture.md#transaction_acknowledge_time_sec-parameter">Click for more information about this parameter.</a></p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>#TRX_MAX_DURATION_SEC=300</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="400pxl">
<p>Max duration time for a transaction, when the time elapsed, a transaction will be dropped.</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>#CDC_PUBLISHER_JOB_AFFINITY=</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="400pxl">
<p>Affinity for CDC publisher job</p>
<p>&nbsp;</p>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">
<p>#CDC_CONSUMER_JOB_AFFINITY=</p>
</td>
<td valign="top" width="400pxl">
<p>Affinity for CDC consumer job</p>
</td>
</tr>
<tr>
<td rowspan="2" valign="top" width="200pxl">
<h5>cdc_data_publish</h5>
</td>
<td valign="top" width="300pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="400pxl">
<p><strong>Instructions</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="250pxl">
<p>Parameters for the CDC_TRANSACTION_PUBLISHER job.</p>
</td>
<td valign="top" width="450pxl">
<p>Populate the #BOOTSTRAP_SERVERS by IP address of the Kafka servers.</p>
<p>It is possible to populate several IP addresses separated by a comma.</p>
</td>
</tr>
<tr>
<td rowspan="2" valign="top" width="200pxl">
<h5>cdc_data_publish_ssl</h5>
</td>
<td valign="top" width="250pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="450pxl">
<p><strong>Instructions</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="250pxl">
<p>SSL connection parameters when connecting the Kafka servers.</p>
</td>
<td valign="top" width="450pxl">&nbsp;</td>
</tr>
<tr>
<td rowspan="2" valign="top" width="200pxl">
<h5>cdc_data_consume</h5>
</td>
<td valign="top" width="250pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="450pxl">
<p><strong>Instructions</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="250pxl">
<p>Parameters for the CDC_TRANSACTION_CONSUMER job.</p>
</td>
<td valign="top" width="450pxl">
<p>Populate the #BOOTSTRAP_SERVERS by IP address of the Kafka servers.</p>
<p>It is possible to populate several IP addresses separated by a comma.</p>
</td>
</tr>
<tr>
<td rowspan="2" valign="top" width="200pxl">
<h5>cdc_data_consume_ssl</h5>
</td>
<td valign="top" width="250pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="400pxl">
<p><strong>Instructions</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="250pxl">
<p>SSL connection parameters when connecting the Kafka servers.</p>
</td>
<td valign="top" width="400pxl">&nbsp;</td>
</tr>
<tr>
<td rowspan="2" valign="top" width="200pxl">
<h5>&nbsp;</h5>
<h5>search_engine</h5>
</td>
<td>
<p><strong>Description</strong></p>
</td>
<td>
<p><strong>Instructions</strong></p>
</td>
</tr>
<tr>
<td valign="top" width="450pxl">
<p>Supported providers: ElasticSearchProvide</p>
</td>
<td valign="top" width="400pxl">
<p>A maximum number of concurrent threads to process the bulk actions:</p>
<p>#BULK_PROCESSOR_MAX_CONCURRENT_WORKERS=5</p>
<p>Bulk size. The maximum number of actions (requests) in one bulk:</p>
<p>#BULK_PROCESSOR_MAX_ACTIONS=1000</p>
</td>
</tr>
</tbody>
</table>





[![Previous](/articles/images/Previous.png)](05_cdc_consumers_implementation.md)
