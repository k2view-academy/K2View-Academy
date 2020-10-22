# JMS Interface

The JMS interface type defines the connection to a JMS Queue or Topic using a JMS provider like RabbitMQ or Active MQ). The interface can be used by [Broadway Pub / Sub Actors](/articles/19_Broadway/actors/05_db_actors.md) or by [User Jobs](/articles/20_jobs_and_batch_services/01_fabric_jobs_overview.md).

To create a new JMS interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select the **JMS** Interface Type to open the **New Interface** window.

   ![image](images/jms_1.png)

2. Populate the connection settings and **Save**.

### Connection Settings

<table>
<tbody>
<tr>
<td width="200pxl">&nbsp;<strong>Parameter</strong></td>
<td width="700pxl">&nbsp;<strong>Description</strong></td>
</tr>
<tr>
<td>&nbsp;<strong>Provider Type</strong></td>
<td>
<p>&nbsp;Message provider types (brokers) that support:</p>
<ul>
<li>ActiveMQ, RabbitMQ, MQ - Queue or Topic.</li>
<li>Custom provider - Queue or Topic. In this scenario the implementer must write the provider class name and provide a path to the <strong>.bindings</strong> file.</li>
</ul>
</td>
</tr>
<tr>
<td>&nbsp;<strong>Bindings Factory&nbsp;</strong></td>
<td>&nbsp;Settings from the <strong>.bindings</strong> file that define the connection to the broker.</td>
</tr>
<tr>
<td>&nbsp;<strong>Bindings Source</strong></td>
<td>&nbsp;Settings from the <strong>.bindings</strong> file that define the parameters of a queue or a topic.</td>
</tr>
<tr>
<td>&nbsp;<strong>Password</strong></td>
<td>&nbsp;Password.</td>
</tr>
<tr>
<td>&nbsp;<strong>Group ID</strong></td>
<td>
<p>&nbsp;JMS queue / topic group ID.</p>
<p>&nbsp;Can be overriden by the Broadway Actor's settings.&nbsp;</p>
</td>
</tr>
<tr>
<td>&nbsp;<strong>Poll Timeout (ms)</strong></td>
<td>&nbsp;The time to wait for a new message.</td>
</tr>
<tr>
<td><strong>&nbsp;Data Type</strong></td>
<td>
<p>&nbsp;Supported data types: String, byte[], JSON, long.</p>
<p>&nbsp;The message type processed by Broadway Actors must be aligned with the Data type definition.</p>
</td>
</tr>
</tbody>
</table>

### Example of Publish to JMS Broadway Flow

![image](images/jms_2.PNG)

The above Broadway flow uses a **Publish** Actor to publish the data to the predefined JMS interface. When using a JMS Queue provider, the **topic** input argument indicates the queue name.



[![Previous](/articles/images/Previous.png)](03_kafka_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_HTTP_interface.md) 

