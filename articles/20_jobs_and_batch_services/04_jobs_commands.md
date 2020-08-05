## Jobs Commands
 
 The following Jobs commands are available:
The following table lists the GET commands:

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="100pxl">
<p><strong>Command Name</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Description</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Syntax</strong></p>
</td>
<td valign="top" width="250pxl">
<p><strong>Example</strong></p>
</td>
</tr>



<tr>
<td valign="top" width="100pxl">
<h5>JOBSTATUS [x days ago]</h5>
</td>
<td valign="top" width="250pxl">
<p>When days are provided- returns the status for all jobs that were created during the last X days, including archived jobs. When daysare not provided – returns all active (not archived) jobs.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>JOBSTATUS;</p>
<p>JOBSTATUS 2 days ago;</p>
</td>
</tr>  



<tr>
<td valign="top" width="100pxl">
<h5>STARTJOB</h5>
</td>
<td valign="top" width="250pxl">
<p>Brings information for a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a>, or multiple LUIs of different LUs. Fabric checks if the LUI needs to be synced from the source system, syncs the LUI if needed, or brings the latest version of the LUI from Fabric.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>get &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>get &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td valign="top" width="250pxl">
<p>get Customer.1;</p>
<p>Get instance ID 1 of Customer LU.</p>
<p>get Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and instance ID 34 of CRM LU.</p>
</td>
</tr>



<tr>
<td valign="top" width="100pxl">
<h5>STARTJOB</h5>
</td>
<td valign="top" width="250pxl">
<p>Brings information for a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a>, or multiple LUIs of different LUs. Fabric checks if the LUI needs to be synced from the source system, syncs the LUI if needed, or brings the latest version of the LUI from Fabric.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>get &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>get &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td valign="top" width="250pxl">
<p>get Customer.1;</p>
<p>Get instance ID 1 of Customer LU.</p>
<p>get Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and instance ID 34 of CRM LU.</p>
</td>
</tr>  



<tr>
<td valign="top" width="100pxl">
<h5>STOPJOB</h5>
</td>
<td valign="top" width="250pxl">
<p>Brings information for a specific <a href="/articles/01_fabric_overview/02_fabric_glossary.md#lui">LUI</a>, or multiple LUIs of different LUs. The instance is returned by an <a href="/articles/07_table_population/09_creating_an_LUDB_function.md">LUDB function</a>.</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>GETF &lt;LUT_NAME&gt;.&lt;function name&gt;(arg...)[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>GET &lt;LUT_NAME&gt;.&lt;function name&gt;(arg...)@&lt;DC&gt;,&lt;LUT_NAME_2&gt;.&lt;function name&gt;(arg...);</p>
</td>
<td valign="top" width="250pxl">
<p>getf Customer.fnCreateInstId(235);</p>
<p>This function adds 1000 to the input value and returns the value 1235, Fabric gets Customer # 1235.</p>
</td>
</tr>




<tr>
<td valign="top" width="100pxl">
<h5>RESUMEJOB</h5>
</td>
<td valign="top" width="250pxl">
<p>USE command is an alias of GET command.</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>use &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>use &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td valign="top" width="250pxl">
<p>use Customer.1;</p>
<p>Get Instance ID 1 of Customer LU.</p>
<p>use Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and Instance ID 34 of CRM LU.</p>
</td>
</tr>



<tr>
<td valign="top" width="100pxl">
<h5>RESUMEJOB</h5>
</td>
<td valign="top" width="250pxl">
<p>USE command is an alias of GET command.</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>use &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>use &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td valign="top" width="250pxl">
<p>use Customer.1;</p>
<p>Get Instance ID 1 of Customer LU.</p>
<p>use Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and Instance ID 34 of CRM LU.</p>
</td>
</tr>




<tr>
<td valign="top" width="100pxl">
<h5>RESTARTJOB</h5>
</td>
<td valign="top" width="250pxl">
<p>USE command is an alias of GET command.</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>use &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>use &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td valign="top" width="250pxl">
<p>use Customer.1;</p>
<p>Get Instance ID 1 of Customer LU.</p>
<p>use Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and Instance ID 34 of CRM LU.</p>
</td>
</tr>




<tr>
<td valign="top" width="100pxl">
<h5>RESUMEJOB</h5>
</td>
<td valign="top" width="250pxl">
<p>USE command is an alias of GET command.</p>
</td>
<td valign="top" width="300pxl">
<p>Get an LUI:</p>
<p>use &nbsp;&lt;LUT_NAME&gt;.'&lt;INSTANCE_ID&gt;'[@&lt;DC&gt;];</p>
<p>Get multiple instances of different LUs:</p>
<p>use &lt;LUT_NAME&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;], &lt;LUT_NAME_2&gt;.&lt;INSTNACE_ID&gt;'[@&lt;DC&gt;];</p>
</td>
<td valign="top" width="250pxl">
<p>use Customer.1;</p>
<p>Get Instance ID 1 of Customer LU.</p>
<p>use Customer.1, CRM.34;</p>
<p>Get instance ID 1 of Customer LU and Instance ID 34 of CRM LU.</p>
</td>
</tr>



</tbody>
</table>
