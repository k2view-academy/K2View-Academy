# Custom Interface

The Custom interface type is used to interact with interface types that are not defined as a dedicated interface type in Fabric, such as SSH or any other interface that requires user, password, port and host. For example, to connect to Email server. 

You can also use Custom interface type in order to store an encrypted password. For example, to connect to a website using a user and a password. Then, in order to get the password's original value, use getCustomProperties API in the user code.

To create a new interface:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select the **Custom** Interface Type to open the **New Interface** window.

   ![image](images/custom_1.PNG)

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
<td>&nbsp;<strong>Host</strong></td>
<td>&nbsp;Hostname or IP address of the server.</td>
</tr>
<tr>
<td>&nbsp;<strong>Port</strong></td>
<td>&nbsp;Port ID.</td>
</tr>
<tr>
<td><strong>&nbsp;User</strong></td>
<td>&nbsp;User name.</td>
</tr>
<tr>
<td><strong>&nbsp;Password</strong></td>
<td>&nbsp;Password.</td>
</tr>
<tr>
<td><strong>&nbsp;Data</strong></td>
<td>&nbsp;Any additional parameters defined for the customer interface.</td>
</tr>
</tbody>
</table>



[![Previous](/articles/images/Previous.png)](06_local_file_sys.md)
