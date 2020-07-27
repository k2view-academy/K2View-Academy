# Sync Modes

## Set Sync Command 
The Fabric **Set Sync** command is used to define the synchronization mode of an instance from source systems. The default value is ON.

SYNTAX: SET SYNC [SYNC MODE];

## Sync Modes
<table style="width: 560px;">
<tbody>
<tr>
<td style="width: 76px;">
<p><strong>Sync Mode</strong></p>
</td>
<td style="width: 146px;">
<p><strong>Description</strong></p>
</td>
<td style="width: 316px;">
<p><strong>When is an Instance Synced?</strong></p>
</td>
</tr>
<tr>
<td style="width: 76px;">
<p>ON</p>
</td>
<td style="width: 146px;">
<p>Run sync according the <a href="/articles/14_sync_LU_instance/04_sync_methods.md">Sync method&nbsp;</a> (None, Time Interval, Inherited and Decision Function)</p>
</td>
<td style="width: 316px;">
<ul>
<li>The <a href="/articles/03_logical_units/03_LU_schema_window.md"> LU Schema </a> has changed and is redeployed.</li>
<li>First sync, the instance does not yet exist in Fabric.</li>
<li>Sync the instance according to the pre-defined Sync method, set for each <a href="/articles/06_LU_tables/01_LU_tables_overview.md">LU table</a> and <a href="/articles/07_table_population/01_table_population_overview.md">table population object</a></li>
</ul>
</td>
</tr>
<tr>
<td style="width: 76px;">
<p>OFF</p>
</td>
<td style="width: 146px;">
<p>Don&rsquo;t sync</p>
</td>
<td style="width: 316px;">
<ul>
<li>Synchronization is not performed, however if the LU instance already exists in Fabric it will bring the existing LU instance data based on the mostly updated LU schema definition</li>
<li>If the LU instance does not yet exist in Fabric, &nbsp;the following warning message is displayed:</li>
<li>Instance '&lt;LU Name&gt;:&lt;Instance ID&gt;' was not found and sync is disabled</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 76px;">
<p>FORCE</p>
</td>
<td style="width: 146px;">
<p>Always sync</p>
</td>
<td style="width: 316px;">
<p>Synchronization is performed on every operation on the Fabric LU instance, regardless of the sync method definitions for the LU.</p>
<p>The only exception to this rule is when using a <a href="/articles/14_sync_LU_instance/05_sync_decision_functions.md">decision function</a>. If the decision function returns False, then the data will not be synced.</p>
</td>
</tr>
</tbody>
</table>

Note that the Sync returns an error message when a source is not available. To change this behavior, use [set ignore_source_exception true](/articles/14_sync_LU_instance/03_sync_ignore_source_exception.md) command.


## Fabric Studio Server Configuration - Force Upgrade Post Deploy Checkbox
The **Force Upgrade Post Deploy** checkbox is defined for each predefined Fabric server in the [Server Configuration](/articles/04_fabric_studio/04_user_preferences.md#what-is-the-purpose-of-the-server-configuration-tab) window:

![image](/articles/14_sync_LU_instance/images/6_2_server_configuration_window.png)

This checkbox defines the sync mode of the first get of each LU instance after the LU is deployed to the server:
* If checked, the Sync mode is set to FORCE.
* When unchecked, the Sync mode is set to ON.

**Notes:**
* The check/uncheck of the **Force Upgrade Post Deploy** checkbox impacts the LU only after the redeploy of the LU to the checked/unchecked Fabric server. It does not impact retroactively.
* The Sync mode is set to FORCE only for the first get of each LU instance after the re-deploy of the LU.  
* The Sync mode is set to FORCE for the first get of each LU instance even if the **Force Upgrade Post Deploy** checkbox is later unchecked, the LU is redeployed, and the instance was not synchronized when the **Force Upgrade Post Deploy** checkbox is still checked.

**Example 1:**
* Set the [Sync Method](/articles/14_sync_LU_instance/04_sync_methods.md) of Customer LU to **None**.
* Get Customer no. 1.
* Update the source DB for Customer no. 1. 
* Check the **Force Upgrade Post Deploy** checkbox for Fabric development server and redeploy Customer LU to this server. 
* Get again Customer no. 1:  the customer is synchronized, and his data is updated, since the  **Force Upgrade Post Deploy** checkbox set the sync mode to FORCE.
* Update again the source DB for customer no. 1 
* Get again Customer 1.  This time Customer 1 is **not** synchronized, since the sync mode is set back to ON for the Customer after their first sync**, initiated after checking the **Force Upgrade Post Deploy** checkbox.

**Example 2:**
* Set the [sync method](/articles/14_sync_LU_instance/04_sync_methods.md) of Customer LU to **None**.
* Get Customers no. 1 and 2.
* Update the source DB for Customers no. 1 and 2.
* Check the **Force Upgrade Post Deploy** checkbox for Fabric development server and redeploy Customer LU to this server. 
* Get again Customer no .2:  this Customer is synchronized, and their data is updated. 
* Uncheck the **Force Upgrade Post Deploy** checkbox for Fabric development server and redeploy Customer LU to this server. 
* Get again Customers no. 1 and 2- 
  * Customer no. 1 is synchronized, since this is the first get of this customer after checking the **Force Upgrade Post Deploy** checkbox, even though this checkbox was later unchecked.
  * Customer no. 2 is **not** synchronized, since it was already synchronized after checking the **Force Upgrade Post Deploy** checkbox

* Click for more information about the Get Instance Fabric Command.

## Get Sync Mode
The Fabric UserCode class holds the method that returns the sync mode set for the current session: 

public static String getSyncMode();

This method can be invoked by a [Decision function](/articles/14_sync_LU_instance/05_sync_decision_functions.md). For example:
If the sync mode is FORCE, then return True to sync the instance. Else, do not sync the instance.

Click to open the list of Fabric APIs: **http://[Fabric IP address]:3213/static/doc/user-api/index.html**


[![Previous](/articles/images/Previous.png)](/articles/14_sync_LU_instance/01_sync_LUI_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/14_sync_LU_instance/03_sync_ignore_source_exception.md)

