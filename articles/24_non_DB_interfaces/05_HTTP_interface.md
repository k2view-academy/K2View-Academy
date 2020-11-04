# HTTP Interface

The HTTP interface type defines the connections to an HTTP/s host and can be used by Broadway Actors.

To create a new HTTP interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select **HTTP** from the **Interface Type** dropdown menu to open the **New Interface** window.


   ![image](images/03_http_1.png)

2. Populate the connection's settings and click **Save**.

### Connection Settings


<table>
<tbody>
<tr>
<td width="300pxl">&nbsp;<strong>Parameter</strong></td>
<td width="600pxl">&nbsp;<strong>Description</strong></td>
</tr>
<tr>
<td>&nbsp;<strong>Host</strong></td>
<td>&nbsp;Hostname or IP address of the HTTP server</td>
</tr>
<tr>
<td>&nbsp;<strong>Port</strong></td>
<td>&nbsp;Port of the HTTP server</td>
</tr>
<tr>
<td><strong>&nbsp;Protocol Type</strong></td>
<td>&nbsp;HTTP or HTTPS</td>
</tr>
<tr>
<td>&nbsp;<strong>User</strong>&nbsp;</td>
<td>&nbsp;Username</td>
</tr>
<tr>
<td>&nbsp;<strong>Password&nbsp;</strong></td>
<td>&nbsp;Password&nbsp;</td>
</tr>
</tbody>
</table>


### Example of Using an HTTP Interface in a Broadway Flow

![image](images/03_http_2.PNG)

The above Broadway flow uses an **Http** Actor to connect to the HTTP server that populates the predefined HTTP interface into the **interface** input argument. The **path** input argument must be populated by the path relative to the interface.



[![Previous](/articles/images/Previous.png)](04_JMS_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_local_file_sys.md) 
