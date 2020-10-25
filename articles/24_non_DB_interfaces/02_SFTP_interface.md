# SFTP Interface

The SFTP interface type defines the connections between the FTP Server and a stream of file content and is used when creating an [Interface Listener for a Broadway flow](/articles/19_Broadway/09_broadway_integration_with_Fabric.md#interface-listener-for-broadway-flows).

To create a new SFTP interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select the **SFTP** Interface Type to open the **New Interface** window.

   ![image](images/02_sftp_1.PNG)

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
<td><strong>IP</strong></td>
<td>Hostname or IP address of the FTP server</td>
</tr>
<tr>
<td><strong>User</strong>&nbsp;</td>
<td>Username</td>
</tr>
<tr>
<td><strong>Password&nbsp;</strong></td>
<td>Password&nbsp;</td>
</tr>
<tr>
<td><strong>Remote Directory</strong></td>
<td>The directory where the files are stored</td>
</tr>
<tr>
<td><strong>Files Filter</strong></td>
<td>Type of file</td>
</tr>
<tr>
<td><strong>Actions</strong></td>
<td>
<p>Test Connection. Click to test the connection.</p>
<p>Add an Interface Listener as a Broadway job. Click to create an Interface Listener job under the specified Logical Unit.</p>
</td>
</tr>
</tbody>
</table>


### How Do I Create a Listener Job on SFTP Interface?

To create an Interface Listener Job on an SFTP interface: 

1. Click the **Add interface listener as Broadway job** link and select the [Logical Unit](/articles/03_logical_units/01_LU_overview.md) to open the Jobs window. 
2. Select the Broadway flow, Execution Mode and save the job.

![images](images/02_sftp_2.PNG)



[![Previous](/articles/images/Previous.png)](01_nondb_interfaces_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_kafka_interface.md) 
