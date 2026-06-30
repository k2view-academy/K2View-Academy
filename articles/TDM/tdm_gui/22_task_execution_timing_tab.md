# Task — Scheduler Tab 

The **Scheduler** tab in the task's **Advanced settings** enables setting a **scheduled execution**: 

- Check the **Set scheduling parameters** checkbox in order to define an automatic execution of a task via the TDM Scheduler process on predefined intervals. 

![scheduling](images/task_scheduling_parameters_example2.png)

- Set the timing interval for the automatic scheduling. It is also possible to define an end date for the automatic scheduling. For example, execute the task every 2 days at 02:00 until 31 Jul 2026.

![execution timing example1](images/task_scheduling_parameters_example1.png)

Note: Task execution time is based on the **UTC time zone**.

Notes:

- Testers can set an automatic scheduling for a task only when their [TDM Environment permission set](10_environment_roles_tab.md) has permission to select this method for the target environment. 
- A scheduled task can still be executed on demand. To execute a scheduled task, click ![execution](images/execute_task_icon.png) next to the task record in the Tasks window.

### Scheduled Execution Parameters

The Execution Time Interval is saved in TDM as a **crontab** value. Scheduling parameters can be populated by either:
 - Selecting the **Advanced** tab and populating the **crontab** value manually. Set a **Quartz crontab expression**. 
 - Selecting another tab and setting the scheduling parameters using the TDM Wizard. The following options are available:

 <table width="900pxl">
<tbody>
<tr>
<td valign="top" width="300pxl"><strong>Time Interval</strong></td>
<td valign="top" width="600pxl"><strong>Scheduling Parameters</strong></td>
</tr>
<tr>
<td valign="top" width="300pxl">Minutes</td>
<td valign="top" width="600pxl">
<ul>
<li>Minutes</li>
<li>Seconds</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">Hourly</td>
<td valign="top" width="600pxl">
<ul>
<li>Hours</li>
<li>Minutes</li>
<li>Seconds</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">Daily</td>
<td valign="top" width="600pxl">
<ul>
<li>Every N day(s) at a specified time. For example, every 2 days at 02:00.</li>
<li>Every week day (Monday through Friday) at a specified time.</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">Weekly</td>
<td valign="top" width="600pxl">
<ul>
<li>Day. For example, Monday</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">Monthly</td>
<td valign="top" width="600pxl">
<ul>
<li>A day in the month. For example, 1st, 2nd, First Monday, Second Thursday...</li>
<li>Months interval. For example, every 2 months</li>
<li>Hours</li>
<li>Minutes</li>
<li>Seconds</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="300pxl">Yearly</td>
<td valign="top" width="600pxl">
<ul>
<li>Month</li>
<li>A day in the month. For example, 1st, 2nd, First Monday, Second Thursday...</li>
<li>Months interval. For example, every 2 months</li>
<li>Hours</li>
<li>Minutes</li>
<li>Seconds</li>
</ul>
</td>
</tr>
</tbody>
</table>
​    

The **End by date** / **No end date** options control whether the scheduled execution runs indefinitely or stops on a specified date.
