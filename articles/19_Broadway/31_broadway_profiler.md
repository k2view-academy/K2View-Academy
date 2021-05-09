# Broadway Profiler

When running a Broadway flow, the Profiler can be enabled, so that results breakdown per Stage / Actor / Iteration can be seen. The Profiler is enabled either via the Fabric Studio or using the **broadway** command. 

### How Do I Enable the Profiler in the Studio?

To enable the Profiler, click **Actions** > **Profiler** in the [Main menu](18_broadway_flow_window.md#main-menu) toolbar and run the flow. Once the flow is completed, the results are displayed in the [Run Results](18_broadway_flow_window.md#run-results-window) window. When the Profiler is enabled, it adds "Click on the Viewer icon" line after all flow results. 

![image](images/99_31_01.PNG)

Click on the <img src="images/99_31_02.PNG" alt="image" style="zoom:67%;" /> icon to view the Profiler results:

![image](images/99_31_03.PNG)

### How Do I Run the Profiler Via the Broadway Command?

To invoke the Profiler when running the flow using the **broadway** command, set the **profilerTelemetry** argument to **true** . This will add the Profiler results to the command results.

~~~
fabric>broadway CRM.callGraphIt profilerTelemetry=true;
|column           |value                                                       |
+-----------------+------------------------------------------------------------+
|profilerTelemetry|callGraphIt 37ms
   Stage 1 6ms
      Http1 4ms
   Stage 2 18ms
      Http2 18ms|
(1 rows)
~~~



[![Previous](/articles/images/Previous.png)](30_support_parallel_execution.md)
