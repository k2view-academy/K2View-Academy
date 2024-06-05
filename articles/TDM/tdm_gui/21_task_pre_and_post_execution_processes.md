# Task - Pre and Post Execution Processes

The **Pre-execution process** and **Post-execution process** tabs in the task's **Advanced settings** enable adding pre and post execution processes to the task. The pre-execution processes run in the beginning of the task's execution, before all the related LUs have been executed. The post-execution processes run at the end of the task's execution, after all the related LUs have been executed.

Examples:

- Running a cleanup flow before executing the task's LUs.
- Sending an email to the tester to notify that the execution of a task has ended.

The pre and post execution processes are available for tasks that are based on [Business entity (BE)](/articles/TDM/tdm_overview/03_business_entity_overview.md) and must be [added to the BE by the admin user](04_tdm_gui_business_entity_window.md#pre-and-post-execution-processes-tabs). You can select only pre and post execution processes from the list of processes added to the task's BE.  Note that a given flow can be attached as both - pre and post execution process - to a BE and a task with this BE.

The [task execution process](/articles/TDM/tdm_architecture/03_task_execution_processes.md) executes the [BATCH command](/articles/20_jobs_and_batch_services/15_batch_broadway_commands.md) on each pre and post execution process attached to the task. The execution order is set according to the execution order defined in the BE.

The pre and post execution processes are **optional**. A task can be created and executed without any post-execution processes.
