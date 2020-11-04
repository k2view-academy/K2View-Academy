# SFTP Interface

An SFTP interface defines the connections between a secured FTP server (a remote server with an SSH connection) and a stream of file content and is used when creating an [Interface Listener for a Broadway flow](/articles/19_Broadway/09_broadway_integration_with_Fabric.md#interface-listener-for-broadway-flows).

To create a new SFTP interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select **SFTP** from the **Interface Type** dropdown menu to open the **New Interface** window.

   ![image](images/02_sftp_1.PNG)

2. Populate the connection settings and **Save**.

### Connection Settings


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


### Example of Using an SFTP Interface

To create an Interface Listener Job on an SFTP interface: 

1. Create an interface using an **SFTP** interface.

2. Create a Broadway flow that reads data from a file using the predefined interface and populates it into the DB.

   ![images](images/broadway_file_read.PNG)

3. Click the **Add interface listener as Broadway job** link in the Interface window and select the [Logical Unit](/articles/03_logical_units/01_LU_overview.md) to open the Jobs window. 

4. Select the **Broadway flow** and **Execution mode** and then save the job.

![images](images/02_sftp_2.PNG)



[![Previous](/articles/images/Previous.png)](01_nondb_interfaces_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_kafka_interface.md) 
