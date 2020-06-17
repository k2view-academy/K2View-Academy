# Sync - Decision Functions Checks and Considerations Table

When a Decision function returns False it always skips a sync. Therefore, when writing Decision function code, always refer to the Decision Function Checks and Considerations Table below.

<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Check</strong></p>
</td>
<td width="700pxl">
<p>Should the sync on the <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a> be skipped?</p>
</td>
</tr>
<tr>
<td width="132">
<p><strong>Consideration</strong></p>
</td>
<td width="472">
<p>When defining a Decision function on the <a href="/articles/03_logical_units/03_LU_schema_window.md">LU schema</a>, the Decision function runs on every <a href="/articles/07_table_population/01_table_population_overview.md"> population </a> in the LU schema.</p>
<p>If the Decision function returns the same result for each population, it is recommended to set it on the <a href="/articles/03_logical_units/08_define_root_table_and_instance_ID_LU_schema.md">Root Table</a>&rsquo;s population. Then invoke the <a href="/articles/14_sync_LU_instance/09_skip_sync.md"><strong>skipSync()</strong> </a> method in the Decision function code to skip the sync of the LUI if the conditions of the sync are not met. This way, Fabric performs a one-time execution of the Decision function on each LUI instead of executing the Decision function on each population.</p>
<p>For example, setting the Decision functions to check whether the current time is during peak or off-peak hours.</p>
</td>
</tr>
<tr>
<td width="132">
<p><strong>Fabric Function</strong></p>
</td>
<td width="472">public static&nbsp;void&nbsp;skipSync()</td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Check</strong></p>
</td>
<td width="700pxl">
<p>Is it a first sync?</p>
</td>
</tr>
<tr>
<td width="132">
<p><strong>Consideration</strong></p>
</td>
<td width="472">
<p>If the LUI does not exist in Fabric and this is the first sync, consider enabling the sync on the LUI.</p>
</td>
</tr>
<tr>
<td width="132">
<p><strong>Fabric Function</strong></p>
</td>
<td width="472">
<p>public static&nbsp;boolean&nbsp;isFirstSync()</p>
<p>Returns:</p>
<p>&middot;&nbsp;&nbsp;&nbsp; First sync = True.</p>
<p>&middot;&nbsp;&nbsp;&nbsp; Subsequent sync = False.</p>
</td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Check</strong></p>
</td>
<td width="700pxl">
<p>What is the sync mode?</p>
</td>
</tr>
<tr>
<td width="132">
<p><strong>Consideration</strong></p>
</td>
<td width="472">
<p>Check the session&rsquo;s sync mode. Consider enabling a sync on the LUI when the sync mode is set to FORCE.</p>
</td>
</tr>
<tr>
<td width="132">
<p><strong>Fabric Function</strong></p>
</td>
<td width="472">public static&nbsp;String&nbsp;getSyncMode()
<p>Returns:</p>
<p>Current sync mode.</p>
</td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
<td width="150pxl">
<p><strong>Check</strong></p>
</td>
<td width="700pxl">
<p>Has the LU Schema Changed Since the Last Sync?</p>
</td>
</tr>
<tr>
<td width="132">
<p><strong>Consideration</strong></p>
</td>
<td width="472">
<p>If the LU schema has changed since the last sync of the LUI, consider synchronizing the instance to include the latest LU Schema changes.</p>
</td>
</tr>
<tr>
<td width="132">
<p><strong>Fabric Function</strong></p>
</td>
<td width="472">public static&nbsp;boolean&nbsp;isStructureChanged()
<p>Returns:</p>
<p>True / False.</p>
</td>
</tr>
</tbody>
</table>



[Click to go to the Decision Functions Overview.](/articles/14_sync_LU_instance/05_sync_decision_functions.md)\
[Click for more information about Sync Modes.](/articles/14_sync_LU_instance/02_sync_modes.md)\
[Click for more information about Sync Behavior.](/articles/14_sync_LU_instance/10_sync_behavior_summary.md)\
[Click for more information about the Skip Sync Method.](/articles/14_sync_LU_instance/09_skip_sync.md)

Click to display a list of Fabric APIs: **http://[Fabric IP address]:3213/static/doc/user-api/index.html**

### Decision Functions - Code Examples

**Example 1**

Add a Decision function on the LU schema level to check the source environment and enable the sync of the LUI in the following scenarios:
* First sync of the LUI.
* LU schema has been changed.
* The source environment is Production.

<pre><code>
// Init the Boolean by true\
Boolean syncInd = true;\
// Check the source environment\
String env = getActiveEnvironmentName();

// If the active environment is not production, this sync is not the first sync of the instance, and the schema is not changed => do not sync the instance\
if(!env.toLowerCase().equals("prod") && !isFirstSync() && !isStructureChanged())\
	syncInd = false;

// DEBUG- note- the output of getTableName() was added to the log, since when setting 
// a decision function on the LU schema- it runs on each population of every LU table of the
// schema

log.info("fnCheckSourceEnv- table name: " + getTableName() + ", active env: " + env.toLowerCase() + ", isFirstSync: " + isFirstSync() +
", isStructureChanged: " +  isStructureChanged() + ", Sync indicator: " + syncInd.toString());\
return syncInd;
</code></pre>

**Example 2**

Add a Decision function on the population level in an LU table which is populated only when run on a Development source environment. When run on a Production environment, do not populate this table, since this table does not exist in the Production DB.

<pre><code>
// Init the Boolean by true\
Boolean syncInd = true;

// Check the product version (application version) global. Populate ACTIVITY table only of the source version is 'dev'\
String prodVer = SOURCE_PRODUCT_VERSION;

if(!prodVer.toLowerCase().equals("dev") )\
	syncInd = false;

// DEBUG
log.info("fnCheckSourceVersion- product version: " + prodVer + ", isFirstSync: " + isFirstSync() + ", isStructureChanged: " +  isStructureChanged() + ", Syc indicator: " + syncInd.toString());

return syncInd;
</code></pre>

**Example 3**

Add a Decision function on the CASE LU table to check if the CONTRACT LU table has been updated. The check is based on a [session level Global](/articles/08_globals/03_set_globals.md) (key) which is set to True by the population of the CONTRACT LU table. 

<pre><code>
// Init the Boolean by true\
Boolean syncInd = true;\
// Check the UPDATE_CONTRACT session level key.\
syncInd = UPDATE_CONTRACT ;\
return syncInd;
</code></pre>

**Example 4**

Add a Decision function on the LU schema level to check if the current time is during peak hours or if this sync is the first sync of the LUI. If the current time is during peak hours and the instance already exists in Fabric, skip the sync of the LUI.

```java
Boolean syncInd = false;\
Boolean offPeak=true;\
LocalTime start = LocalTime.parse( "09:00:00" );\
LocalTime end = LocalTime.parse( "17:00:00" );\
LocalTime now = LocalTime.now();

if(now.isAfter(start) && now.isBefore(end))\
	offPeak=false;

if(isFirstSync() || offPeak)\
	syncInd = true;

// DEBUG\
log.info("now: " + now.toString()+ ", is offPeak: " + offPeak + ", now: " + now.toString() + " , isFirstSync(): " + isFirstSync() + " sync ind: " + syncInd);

if(!syncInd)\
     skipSync();

return syncInd;
```
Note that the fnCheckSourceEnv, fnCheckSourceVersion, and fnCheckOffPeak functions are displayed in the Demo Fabric Project under the Code tab.

[![Previous](/articles/images/Previous.png)](/articles/14_sync_LU_instance/05_sync_decision_functions.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/14_sync_LU_instance/07_sync_levels.md)













