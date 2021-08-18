# Fabric Studio Log Files

Fabric Studio enables you to develop, test and monitor projects within the Studio using Fabric’s test and log capabilities. 

To display a list of recent messages from the server, click
<img src="images/13_02_01%20Server%20icon.png" alt="image" style="zoom:75%;" /> Server / Activity Logs (left menu). By default, this window only displays warnings and error messages, however additional messages and notices can also be reviewed.

The following are the main log options:
* Server logs.
* Activity logs.
* Compilation errors.

[Click for more information about UI Components and Menus.](/articles/04_fabric_studio/01_UI_components_and_menus.md)

### How do I Review Server / Activity Logs and Compilation Errors?
**Server Logs**
1. Click <img src="images/13_02_02%20Server%20Logs%20icon.png" alt="image" style="zoom:75%;" />  **Server Logs** and then click either **Errors**, **Warnings** or **Messages** to display the relevant server logs list.

![image](images/13_02_03%20server%20logs%20list.jpg)

2. Right click in the **Log’s list** area to display the following context menu options:
    * **Select Open Selected Items Data in Notepad**, to review the entire error or notification message.
    * **Select Copy Selected Items Data**, to copy the entire text onto the clipboard.
    * **Clear List** to remove the listed logs. Clear list can also be accessed by clicking the **Clear List** icon (top pane).

**Activity Logs**  

3. Click <img src="images/13_02_04%20Activity%20Logs%20ICON.png" alt="image" style="zoom:75%;" /> **Activity Logs** to open the Activity Logs window.

![image](images/13_02_05%20Activity%20Logs%20window.jpg) 

4. Follow **Step 2** to select and review the log. 

**Compilation Errors**

5. Click <img src="images/13_02_06%20Compilation%20Errors%20ICON.png" alt="image" style="zoom:75%;" />  **Compilation Errors** to open the following window. 

![image](images/13_02_07%20window.jpg)

6. Follow **Step 2** to select and review the log. 

**Output**

7. Click <img src="images/13_02_08%20Output%20ICON.jpg" alt="image" style="zoom:67%;" /> **Output** to open the following window where you can review the K2Fabric log:

![image](images/13_02_09%20K2Fabric%20log.jpg)

### Logs Structure:

Each log entry consists of several fields and is constructed as follows:
<Type> <TimeStamp> <Tag ID> <Thread Name> <Class Name> <Message Content>. 
	
```INFO  2021-08-18 08:49:31,400 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.f.s.FabricSession - START - sync Patient.1514294103```


	
The table below describes each field:	
	
#### Message Components

<table>
<tbody>

<tr>
<td width="200"><strong>Field Type</strong></td>
<td width="400">
<strong>Field Description</strong>
</td>
<td width="400">
<strong>Example</strong>
</td>
</tr>
	

<tr>
<td width="200">Type</td>
<td width="400">One of the following types: ERROR/TRACE/WARN/INFO/DEBUG. Read more on these status and how to configure them <a href="/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md#log-level">here</a>.
</td>
<td width="400">INFO 2021-08-18 06:22:59,057</td>
</tr>

	
<tr>
<td width="200">Time Entry</td>
<td width="400">
<p>Timestamp for the process execution with millisec precision</p>
</td>
<td width="400">WARN  2021-08-18 08:49:31,265</td>
</tr>	

<tr>
<td width="200">Message Tag ID</td>
<td width="400">
<p>ID given by the system for the process being executed. This ID will be used across the session for subsequent messages referring to this thread</p>
</td>
<td width="400">
<p>[LID10100000000001b]</p> 
</td>
</tr>
	
<tr>
<td width="200">Thread Name</td>
<td width="400">
<p>The name of the thread currently running</p>
</td>
<td width="400">
<p>[JdbcEx Server-4/127.0.0.1:61448] - JDBC server with IP/Port to which it connects</p> 
</td>
</tr>	

<tr>
<td width="200">Class Name</td>
<td width="400">
<p>The name of the class being invoked</p>
</td>
<td width="400">
<p>c.k.f.s.FabricSession - including the full path for the class location - i.e. c.k.f stands for com.k2view.fabric.</p> 
</td>
</tr>		

<tr>
<td width="200">Message</td>
<td width="400">
<p>Description of the message as defined by the class's developer. Note that the duration of the process can be added as part of the message content.</p>
</td>
<td width="400">
<p>End operation 'Sync Patient.1514294103' successfully.  [1292ms]</p> 
</td>
</tr>	
	
</tbody>
</table>



#### Examples

Below, you can see an extract of log messages for the ```JdbcEx Server``` thread (to which was attributed Tag ID [LID10100000000001d]) while performing an Instance GET operation between Timestamp 08:49:31,256 and Timestamp 08:49:32,516 - in total 260 millisecs:

```
INFO  2021-08-18 08:49:31,256 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.d.d.c.ClockFactory - Using java.lang.System clock to generate timestamps.
WARN  2021-08-18 08:49:31,265 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.d.d.c.ReplicationStrategy$NetworkTopologyStrategy - Error while computing token map for keyspace cov19_data with datacenter datacenter1: could not achieve replication factor 1 (found 0 replicas only), check your keyspace replication settings.
WARN  2021-08-18 08:49:31,276 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.d.d.c.ReplicationStrategy$NetworkTopologyStrategy - Error while computing token map for keyspace cov_data with datacenter datacenter1: could not achieve replication factor 3 (found 0 replicas only), check your keyspace replication settings.
INFO  2021-08-18 08:49:31,390 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.f.s.FabricSession - START - ATTACH Patient.1514294103
INFO  2021-08-18 08:49:31,392 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.f.s.FabricSession - Access to [Patient.1514294103] by user admin is authorized.
INFO  2021-08-18 08:49:31,400 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.f.s.FabricSession - START - sync Patient.1514294103
INFO  2021-08-18 08:49:31,400 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.f.s.FabricSession - local get request
INFO  2021-08-18 08:49:31,406 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.f.s.s.l.SyncExecution - Start operation 'Sync Patient.1514294103'
INFO  2021-08-18 08:49:31,416 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.c.l.m.BroadwayFlowMapObject - Start operation 'Broadway population: PATIENT:population.flow'
INFO  2021-08-18 08:49:32,427 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.c.l.m.BroadwayFlowMapObject - End operation 'Broadway population: ALLERGIES:population.flow' successfully.  [31ms]
INFO  2021-08-18 08:49:32,458 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.c.l.m.BroadwayFlowMapObject - End operation 'Broadway population: hospitals_facility:population.flow' successfully.  [20ms]
INFO  2021-08-18 08:49:32,478 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.c.l.m.BroadwayFlowMapObject - Start operation 'Broadway population: insurance_companies:population.flow'
INFO  2021-08-18 08:49:32,480 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.c.l.m.BroadwayFlowMapObject - End operation 'Broadway population: insurance_companies:population.flow' successfully.  [17ms]
INFO  2021-08-18 08:49:32,497 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.c.l.m.BroadwayFlowMapObject - Start operation 'Broadway population: staff:population.flow'
INFO  2021-08-18 08:49:32,500 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.c.l.m.BroadwayFlowMapObject - End operation 'Broadway population: staff:population.flow' successfully.  [16ms]
INFO  2021-08-18 08:49:32,516 [LID10100000000001d] [JdbcEx Server-7/127.0.0.1:56672] c.k.f.s.s.l.SyncExecution - End operation 'Sync Patient.1514294103' successfully.  [260ms]
```


### Debugging Logs and Messages

Logs and messages can also be used for debugging purposes. The **log.info ()** method is available to enable Fabric’s runtime Debug options. This method can be added to any Java code and its output can be viewed after deployment and during runtime. 

For example, see the **fnCreateInstID** function under Customer LU in the demo project:

~~~java
if (i_id!=null && !i_id.isEmpty()){
// Increase the input by 10 and return
   log.info("o_id: "(Integer.sum(Integer.valueOf(i_id),10)+""));
	return Integer.sum(Integer.valueOf(i_id),10)+"";
}
return "0";
~~~



The runtime log (k2fabric.log) can be reviewed in the Fabric Server’s Logs directory or in the Studio’s Output logs.

![image](images/13_02_10%20Debug.jpg)



[![Previous](/articles/images/Previous.png)](01_data_viewer.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_debug_table_population.md)
