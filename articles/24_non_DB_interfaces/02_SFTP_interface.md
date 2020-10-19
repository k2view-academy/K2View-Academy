# SFTP Interface

SFTP interface type defines the ability to connect to FTP Server and stream a file content. It is used when creating a [Interface Listener for a Broadway flow](/articles/19_Broadway/09_broadway_integration_with_Fabric.md#interface-listener-for-broadway-flows).

To create a new SFTP interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces** to select **New Interface** and then select the **SFTP** Interface Type to open the **New Interface** window.

   ![image](images/02_sftp_1.PNG)

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
<td>&nbsp;<strong>IP</strong></td>
<td>&nbsp;Hostname or IP address of the FTP server</td>
</tr>
<tr>
<td>&nbsp;<strong>User</strong>&nbsp;</td>
<td>&nbsp;User name</td>
</tr>
<tr>
<td>&nbsp;<strong>Password&nbsp;</strong></td>
<td>&nbsp;Password&nbsp;</td>
</tr>
<tr>
<td>&nbsp;<strong>Remote Directory</strong></td>
<td>&nbsp;The directory where files are stored</td>
</tr>
<tr>
<td>&nbsp;<strong>Files Filter</strong></td>
<td>&nbsp;Type of file</td>
</tr>
<tr>
<td>&nbsp;<strong>Actions</strong></td>
<td>
<p>&nbsp;Test Connection - Click to test the connection.</p>
<p>&nbsp;Add interface listener as a Broadway job - Click to create an&nbsp; Interface Listener job under the specified Logical Unit.</p>
</td>
</tr>
</tbody>
</table>

### How Do I Create a Listener Job on SFTP Interface?

To create an Interface Listener Job on SFTP interface: 

1. Click **Add interface listener as Broadway job** link and select the [Logical Unit](/articles/03_logical_units/01_LU_overview.md) to open the Jobs window. 
2. Select the Broadway flow, Execution Mode and save the job.

![images](images/02_sftp_2.PNG)



[![Previous](/articles/images/Previous.png)](01_nondb_interfaces_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_HTTP_interface.md) 