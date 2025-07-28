# SSH Interface

The SSH interface type defines the communication details with a remote machine using SSH protocol.

To create a new SSH interface, do the following:

<studio>
1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select **SSH** from the **Interface Type** dropdown menu to open the **New Interface** window.

   ![image](images/10_ssh_1.png)

2. Populate the connection's settings and click **Save**.

</studio>


<web>
1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select **SSH** from the **File System** section to open the **New Interface** window.

2. Enter a suitable name for your new SSH Interface, then click **Create**: 
   
   ![image](images/10_ssh_1WEB.PNG)

3. Populate the connection's settings and click **Save**.
    
   ![image](images/10_ssh_2WEB.PNG)

</web>


### Connection Settings

<table>
<tbody>
<tr>
<td width="300pxl"><strong>Parameter</strong></td>
<td width="600pxl"><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Host</strong></td>
<td>Hostname or IP address of the remote server.</td>
</tr>
<tr>
<td><strong>Port</strong></td>
<td>Port of the remote server.</td>
</tr>
<tr>
<td><strong>User</strong>&nbsp;</td>
<td>Username.</td>
</tr>
<tr>
<td><strong>Password&nbsp;</strong></td>
<td>Password.&nbsp;</td>
</tr>
</tbody>
</table>


### Example of Using an SSH Interface

To create a Broadway Flow that runs on an SSH interface, do the following: 

1. Create an interface using **SSH** interface type.

2. Create a Broadway flow either under Shared Objects or under a Logical Unit. Add a new Actor called SSH Actor, use the Interface defined in Step one and use the ls command.

   ![images](images/10_ssh_2.png)


### SSH Actor Error Handling in Broadway Automation

When using the SSH actor in Broadway automation to execute shell commands on remote systems, the following logic governs how output and errors are handled:

**Command Completion and Exception Handling**

* **Successful Completion**:
If the executed command exits with code 0, the actor does not raise an exception, regardless of whether any output is present on STDERR.

* **Failure Handling**:
If the command exits with a non-zero exit code, the actor raises an exception. The exception message includes:

   * The command’s exit code (e.g., Error code 4)
   * The full contents of STDERR, to assist with diagnostics

**Output Stream Behavior**
* When the command completes successfully (exit code = 0), the contents of STDERR (if any) are merged into the STDOUT stream. This ensures that all command output, including non-critical diagnostics, is available for processing in subsequent steps.

This behavior ensures reliable automation by distinguishing between actual failures and informational output, while preserving all relevant command output for review and processing.


   [![Previous](/articles/images/Previous.png)](09_redis_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_LDAP_interface.md)

