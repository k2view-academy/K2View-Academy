# Local File System Interface

This interface type supports processing files from a local directory of a specific Fabric server and can be used when creating an [Interface Listener for a Broadway flow](/articles/19_Broadway/09_broadway_integration_with_Fabric.md#interface-listener-for-broadway-flows). The directory can be either Window or Linux. 

To create a new interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select the **Local File System** Interface Type to open the **New Interface** window.

   ![image](images/local_1.PNG)

2. Populate the connection settings and **Save**.

### Connection Settings

The connection settings are:

<table>
<tbody>
<tr>
<td width="200pxl">&nbsp;<strong>Parameter</strong></td>
<td width="700pxl">&nbsp;<strong>Description</strong></td>
</tr>
<tr>
<td>&nbsp;<strong>Working Directory&nbsp;</strong></td>
<td>&nbsp;The directory where files are stored - Windows or Linux.</td>
</tr>
<tr>
<td>&nbsp;<strong>Files filter</strong></td>
<td>&nbsp;The filter of the files.</td>
</tr>
</tbody>
</table>



### Example of Using Local File System Interface

To create an Interface Listener Job on a local file system interface: 

1. Create an interface using **Local File System** interface type.

2. Create a Broadway flow that reads data from a file using the predefined interface and populates it into the DB.

   ![images](images/broadway_file_read.PNG)

3. Click the **Add interface listener as Broadway job** link on the Interface window and select the [Logical Unit](/articles/03_logical_units/01_LU_overview.md) to open the Jobs window. 

4. Select the Broadway flow, Execution Mode and save the job.

   ![images](images/02_sftp_2.PNG)



[![Previous](/articles/images/Previous.png)](05_HTTP_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_custom_interface.md) 