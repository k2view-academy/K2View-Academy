# Redis Interface

The Redis interface type defines the connection to Redis in-memory storage to be used as key-value data store and enable fast access to data sets.

Redis interface connection is used by K2View TDM or by Broadway Actors for handling the sequences or for the masking of sensitive data, which is required for data uniqueness and consistency across multiple nodes or the Fabric cluster.  

To create a new Redis interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces** and select **New Interface** and then select the **Redis** Interface Type to open the **New Interface** window.

   ![image](images/09_redis_1.PNG)

2. Populate the connection settings and **Save**.

### Connection Settings

The connection settings are:

<table>
<tbody>
<tr>
<td width="300pxl"><strong>Parameter</strong></td>
<td width="600pxl"><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Servers</strong></td>
<td>Comma delimited servers list</td>
</tr>
<tr>
<td><strong>Password&nbsp;</strong></td>
<td>Password&nbsp;</td>
</tr>
<tr>
<td><strong>Connection Timeout</strong></td>
<td>How long to allow for new connections to be established (in milliseconds)</td>
</tr>
<tr>
<td><strong>Socket Timeout</strong></td>
<td>How long to wait for a response from Redis (in milliseconds)</td>
</tr>
<tr>
<td><strong>Max Attempts</strong></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><strong>Max Total</strong></td>
<td>Max number of connections that can be created at a given time</td>
</tr>
<tr>
<td><strong>Max Idle</strong></td>
<td>Max number of connections that can be idle in the pool without being immediately evicted</td>
</tr>
<tr>
<td><strong>Min Idle</strong></td>
<td>Number of ready for immediate use&nbsp; connections that remain in the pool even when load had reduced</td>
</tr>
<tr>
<td><strong>Test On Borrow</strong></td>
<td>Controls whether or not the connection is tested before it is returned from the pool</td>
</tr>
<tr>
<td><strong>Test on Return</strong></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><strong>Test While Idle</strong></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><strong>Min Evictable Idle</strong></td>
<td>Specifies the minimus amount of time a connection may be idle in the pool before it is eligible for eviction due to idle time</td>
</tr>
<tr>
<td><strong>Eviction Run Intervals</strong></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><strong>Test Per Eviction</strong></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><strong>Block When Exhaused</strong></td>
<td>&nbsp;</td>
</tr>
</tbody>
</table>



### Example of Using Redis Interface in Broadway Flow

![image](images/09_redis_2.PNG)

The above Broadway flow uses a **MaskingSequence** Actor to mask an input list of customer IDs. The Actor connects to predefined Redis interface populated in the Actor's **redisInterface** input argument.



[![Previous](/articles/images/Previous.png)](08_SMTP_interface.md)