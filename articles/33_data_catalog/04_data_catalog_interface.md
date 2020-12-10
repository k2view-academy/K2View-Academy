# Data Catalog Interface

Fabric requires the definition of an Interface type = **Data Catalog** for enabling the Data Catalog functionality. At least one interface of this type must be defined in the project. The name of the default Data Catalog interface must be **catalogdb**, while the name of additional interfaces of this type is not restricted to any specific value.

To create a new Data Catalog interface, do the following:

1. Go to **Project Tree** > **Shared Objects**, right click **Interfaces**, select **New Interface** and then select **Data Catalog** from the **Interface Type** dropdown menu to open the **New Interface** window.

   ![image](images/33_06_interface.PNG)

2. Populate the connection's settings and click **Save**.

#### Connection Settings

<table style="height: 116px;">
<tbody>
<tr style="height: 18px;">
<td style="height: 18px; width: 179px;"><strong>Parameter</strong></td>
<td style="height: 18px; width: 318px;"><strong>Description</strong></td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 179px;"><strong>Server</strong></td>
<td style="height: 18px; width: 318px;">IP address of the OrientDB server</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 179px;"><strong>Port</strong></td>
<td style="height: 18px; width: 318px;">Port of the OrientDB server</td>
</tr>
<tr style="height: 28px;">
<td style="width: 179px; height: 28px;"><strong>Database</strong></td>
<td style="width: 318px; height: 28px;">
<p>OrientDB database name. Provide a name of the existing database or a new one.</p>
<p>If new name is provided, the Orient DB database is created during Write Catalog.</p>
</td>
</tr>
<tr style="height: 18px;">
<td style="height: 18px; width: 179px;"><strong>User</strong>&nbsp;</td>
<td style="height: 18px; width: 318px;">Username</td>
</tr>
<tr style="height: 16px;">
<td style="height: 16px; width: 179px;"><strong>Password&nbsp;</strong></td>
<td style="height: 16px; width: 318px;">Password&nbsp;</td>
</tr>
<tr>
<td style="width: 179px;"><strong>Transactional</strong></td>
<td style="width: 318px;">True/False</td>
</tr>
</tbody>
</table>







[![Previous](/articles/images/Previous.png)](03_build_and_write_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_data_catalog_navigation.md) 