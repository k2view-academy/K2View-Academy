# CDC Configuration

Fabric [config.ini file](/articles/02_fabric_architecture/05_fabric_main_configuration_files.md#configini) has the following section for CDC configuration:

<table style="width: 900px;">
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
<td style="width: 178.906px;" rowspan="2" valign="top">
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
</ul>
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
</tbody>
</table>

All of the Kafka connection settings are defined in the **[default_pubsub]** section of the config.ini and are applicable across various Fabric processes, including the CDC connection to Kafka.

When different Kafka settings for CDC are required, define them in the **[cdc]** section. This section does not have to include all the parameters, but only those that should override the default section's settings. 

[Click for more information about PubSub Configuration](/articles/24_non_DB_interfaces/02a_pubsub_config.md).



[![Previous](/articles/images/Previous.png)](05_cdc_consumers_implementation.md)
