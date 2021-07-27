# LDAP Interface

The LDAP interface type defines the communication details with LDAP service provider.

To create a new LDAP interface, do the following:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select **LDAP** from the **Interface Type** dropdown menu to open the **New Interface** window.

   ![image](images/11_ldap_1.PNG)

2. Populate the connection's settings and click **Save**.

### Connection Settings

<table>
<tbody>
<tr>
<td width="300pxl"><strong>Parameter</strong></td>
<td width="600pxl"><strong>Description</strong></td>
</tr>
<tr>
<td><strong>Active</strong></td>
<td>Indication whether the connection is active. When set to False, the interface is ignored by the server side.</td>
</tr>
<tr>
<td><strong>URL</strong></td>
<td>LDAP server URL.</td>
</tr>
<tr>
<td><strong>User</strong>&nbsp;</td>
<td>Username.</td>
</tr>
<tr>
<td><strong>Password</strong></td>
<td>Password.</td>
</tr>
</tr>
<tr>
<td><strong>Securiy Level</strong></td>
<td>JNDI environment property <i>java.naming.security.authentication</i> that specifies the authentication mechanism(s) for the provider to use. The following values are defined for this property: 
    <li><strong>none</strong> - use no authentication (anonymous bind).</li> 
    <li><strong>simple</strong> - use simple authentication (a cleartext password). </li> 
    <li>A space-separated list of one or more SASL mechanism names.</li>         
    If the value is not specified, the behaviour is determined by the service provider.
</td>
</tr>
</tbody>
</table>








[![Previous](/articles/images/Previous.png)](10_SSH_interface.md)

