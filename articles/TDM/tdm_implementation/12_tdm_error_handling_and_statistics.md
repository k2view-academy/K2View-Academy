# TDM Error Handling and Statistics Flows

The TDM library includes a set of generic flows for error handling and statistics gathering that are based on Broadway capabilities and are tailored for TDM business requirements. 

These generic flows gather errors and statistics during task execution and populate them into dedicated tables. This data is used for monitoring  TDM tasks and creating TDM execution reports.

### How Do I Perform Error Handling in TDM?

The TDM library includes two utility flows that handle errors during the execution of TDM tasks:

* PopulateTableErrorsWithFailed.flow
* PopulateTableErrorsWithReject.flow

Both utilities invoke the internal **PopulateTableErrors.flow** to populate data about errors into the **task_exe_error_detailed** table. The difference between the utilities is that PopulateTableErrorsWithFailed.flow sets on a session level:

~~~
ENTITY_STATUS = failed 
~~~

PopulateTableErrorsWithFailed.flow also sets the error category as *Entity Failed* in the **task_exe_error_detailed** table, while PopulateTableErrorsWithReject.flow sets a record as *Record Rejected*.

The error handling utility is invoked from each Load flow's **Load Data To Target** Stage. An error is suppressed in order to continue a task execution and reach the statistics gathering step.

By default, the **PopulateTableErrorsWithFailed** is invoked and the **Suppress** setting is unchecked, i.e. the entity is rejected due to the error:

![image](images/12_tdm_err_stat_01.PNG)

 If a record needs to be rejected instead of failing an entire entity, replace the Inner flow name with **PopulateTableErrorsWithReject** and check the **Suppress** setting. 

[Click to learn how to use the ErrorHandling Actor](/articles/19_Broadway/actors/06_error_handling_actors.md#how-do-i-use-the-errorhandler-actor).

### How Do I Gather Statistics in TDM?

The TDM library includes the PopulateTableStats.flow that gathers statistics during a TDM task execution:

PopulateTableStats.flow populates statistics data into the **task_exe_stats_detailed** table, including the number of records in the source and target tables. 

PopulateTableStats.flow is invoked from each Load flow's **Get Statistics** Stage to gather statistics and the **Report Statistics** Stages to be loaded into the TDM DB tables. 

![image](images/12_tdm_err_stat_02.PNG)



[![Previous](/articles/images/Previous.png)](11_tdm_implementation_using_generic_flows.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](13_tdm_implementation_supporting_different_product_versions.md)
