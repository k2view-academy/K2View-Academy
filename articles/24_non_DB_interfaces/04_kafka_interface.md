# Kafka Interface

Kafka interface type defines the ability to connect to Apache Kafka which is used by the [Broadway Pub / Sub Actors](/articles/19_Broadway/actors/05_db_actors.md), iidFinder or the [CommonDB](/articles/22_reference(commonDB)_tables/05_commonDB_sync_modes_and_flow.md).

To create a new Kafka interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces** and select **New Interface** and then select the **Kafka** Interface Type to open the **New Interface** window.

   ![image](images/04_kafka_1.PNG)

2. Populate the connection settings and **Save**.

### Connection Settings

The connection settings are:

<table>
<tbody>
<tr>
<td width="300pxl">&nbsp;<strong>Parameter</strong></td>
<td width="600pxl">&nbsp;<strong>Description</strong></td>
</tr>
<tr>
<td>&nbsp;<strong>Topic</strong></td>
<td>
<p>&nbsp;Apache Kafka topic name.</p>
<p>&nbsp;Can be overriden by the Broadway Actor's setting.</p>
</td>
</tr>
<tr>
<td>&nbsp;<strong>Bootstrap Servers</strong></td>
<td>&nbsp;Server IP.</td>
</tr>
<tr>
<td><strong>&nbsp;Group ID</strong></td>
<td>
<p>&nbsp;Apache Kafka group ID.</p>
<p>&nbsp;Can be overriden by the Broadway Actor's setting.&nbsp;</p>
</td>
</tr>
<tr>
<td>&nbsp;<strong>Data Type</strong>&nbsp;</td>
<td>
<p>&nbsp;Supported data types: String, byte[], JSON, long.</p>
<p>&nbsp;The message type to be processed by the Broadway Pub / Sub functionality must be aligned with the Data type definition.</p>
</td>
</tr>
<tr>
<td>&nbsp;<strong>Max Poll Records</strong></td>
<td>&nbsp;Maximum poll records.</td>
</tr>
<tr>
<td><strong>&nbsp;Affinity</strong></td>
<td>&nbsp;(Optional) Populate this parameter by IP address of by a Fabric node, DC name, or logical identifier for Fabric nodes. Fabric allocates an execution server based on the populated affinity. If the affinity is not populated, Fabric allocates one of the available servers for the execution.
<p>&nbsp;Example of affinity: &rsquo;10.21.1.169&rsquo;, &lsquo;DC1&rsquo;.</p>
</td>
</tr>
<tr>
<td>&nbsp;<strong>SSL properties</strong></td>
<td>&nbsp;Mandatory properties if Kafka is defined with SSL.</td>
</tr>
<tr>
<td>&nbsp;<strong>SSL optional properties</strong></td>
<td>&nbsp;Optional properties if Kafka is defined with SSL.</td>
</tr>
</tbody>
</table>
### Example of Publish to Kafka Broadway Flow

![image](images/04_kafka_2.PNG)



The above Broadway flow is using a **Publish** Actor in order to publish the data to predefined Kafka interface. The data is published to the **Example** topic which overrides the topic in the Interface definition.





[![Previous](/articles/images/Previous.png)](03_HTTP_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_JMS_interface.md) 