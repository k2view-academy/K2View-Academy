## Add an Interface Configuration

Click the ![image](/articles/DPM/images/Figure_85_Discovery_AddInterface.png) button at the top-right of the screen in order to configure a new Interface.

![image](/articles/DPM/images/Figure_87_Discovery_AddInterface_Callout.png)

The following dialog box displays.

![image](/articles/DPM/images/Figure_81_Discovery_NewInterface.png)

<table>
<tbody>
<tr>
<td width="85">
<p><strong>Property</strong></p>
</td>
<td width="785">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="85">
<p> Interface</p>
</td>
<td width="785">
<p>Select an interface from the drop-down list. This list is populated by options configured in the Fabric Studio [Interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md).</p>
</td>
</tr>
<tr>
<td width="85">
<p>Catalog Pattern (Regex)</p>
</td>
<td width="785">
<p>This field allows you to configure a filter on the schemas/tables that are searched based on the database catalog, using regular expressions (regex).</p>
</td>
</tr>
<tr>
<td width="85">
<p>Schema Name Inclusion Pattern (Regex)</p>
</td>
<td width="785">
  <p>Define a filter based on the database schema name that you want to <b>include</b> in the discovery process.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Schema Name Exclusion Pattern (Regex)</p>
</td>
<td width="785">
<p>Define a filter based on the database schema name that you want to <b>exclude</b> in the discovery process.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Table Name Inclusion Pattern (Regex)</p>
</td>
<td width="785">
<p>Refine your search further by telling the system to <b>include</b> specific tables, using regex. For example, you can include the database tables for all tables that their name matches the regular expression “ACCOUNT.” </p>
</td>
</tr>
<tr>
<td width="85">
<p>Table Name Exclusion Pattern (Regex)</p>
</td>
<td width="785">
<p>Refine your search further by telling the system to <b>exclude</b> specific tables, using regex. For example, you can exclude searching the database tables for all instances of “ACCOUNT.” </p>
</td>
</tr>
<tr>
<td width="85">
<p>Active</p>
</td>
<td width="785">
<p>The “Active” slider is turned to “On” by default. Turn the slider to “Off” if you do not want the Interface to be submitted to the discovery process in the next executions. You can later switch it to "On" or "Off" at any point of time</p>
</td>
</tr>
</tbody>
</table>

###  Edit or Delete an Interface

Use the ![image](/articles/DPM/images/Figure_4_3_Delete.png) button to delete an Interface. 

**Note**: If you click the ![image](/articles/DPM/images/Figure_4_3_Delete.png) button, the system displays a dialog box, prompting “Are you sure you want to Delete Interface: [InterfaceName]?” Click the ![image](/articles/DPM/images/08_ICON_OK.png) button to remove the selected Interface from the system.

Click the ![image](/articles/DPM/images/Figure_4_2_Edit.png) button to edit the Interface name, Schema information, and table information.



[![Previous](/articles/DPM/images/Previous.png)]( /articles/DPM/02_Admin_Module/15_5_Discovery_Interfaces_Overview.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/15_7_Discovery_Submit_Discovery_Request.md)
