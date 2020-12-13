# TDM Database

 The TDM settings and TDM tasks are kept in a dedicated  PostgreSQL DB. Both TDM components - TDM GUI and the task execution processes - connects the TDM DB to get or update the TDM settings or the TDM tasks.

The table below lists the TDM tables and their description.



<table width="900 pxl">
<tbody>
<tr>
<td valign="top" width="200 pxl"><strong>Table Name</strong></td>
<td valign="top" width="500 pxl"><strong>Description</strong></td>
<td valign="top" width="200 pxl"><strong>Table Category</strong></td>
</tr>
<tr>
<td><h4>business_entities</td>
<td>Business Entities list.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>tdm_lu_type_relation_eid</td>
<td>TDM relation table. This table maps the source parent entity ID to his source children entity IDs per source environment. For example Customer 1 have orders 56, 63, and 73 in Production environment. This table needs to be populated by the Sync of the parent LU, and is used to build the entities list of the children LUs on Load (Copy) tasks.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>tdm_lu_type_rel_tar_eid</td>
<td>TDM relation table for target IDs. This table maps the target parent entity ID to his target children entity IDs per target environment. This table needs to be populated by the Sync of the parent LU, and is used to build the entities list of the children LUs on Delete Only tasks, when the TDM task deletes the parent entites and their related data from the target environment.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>[LU_NAME]_params</td>
<td>Parameters table. Contains the list of all entities, migrated into Fabric per LU. Each combination of entity and source envirtonment has its own record. Each reconrds contains the entity ID (IID), the source environment name, and the list of parameters, defined on the LU. For example, Customert Type. This table is created by Fabric Sync on each LU and is used to support the following Task's selection methods: Random Selection, and select by Parameters.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>tdm_be_post_exe_process</td>
<td>The list of post execution process attached to each Business Entity. A post execution process is executed in the end of the task execution. For example- sending a mail to the user.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>product_logical_units</td>
<td valign="top" width="500 pxl">
<ul>
<li>Map the list of LUs to each Business Entity.</li>
<li>Map the relation of LUs within a Business Entity: parallel LUs, or parent-child LUs.</li>
<li>Map the combination of Business Entity and LU to a Product&nbsp;</li>
</ul>
</td>
<td>Business Entity/Product</td>
</tr>
<tr>
<td><h4>products</td>
<td>Products (applications) list.</td>
<td>Product</td>
</tr>
<tr>
<td><h4>tasks</td>
<td>Tasks list.&nbsp;</td>
<td>Task</td>
</tr>
<tr>
<td><h4>task_globals</td>
<td>The list of Global parameters, set on the Task level.</td>
<td>Task</td>
</tr>
<tr>
<td><h4>task_ref_tables</td>
<td>The list of Reference Tables included in each TDM task.</td>
<td>Task</td>
</tr>
<tr>
<td><h4>tasks_logical_units</td>
<td>The list of LUs included in each TDM task.</td>
<td>Task</td>
</tr>
<tr>
<td><h4>instance_table_count</td>
<td>This table contains the number of records, added to each LU table on each LU and LUI. The table is populated by Fabric Sync and is used to generate the TDM Statistics report.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_exe_error_detailed</td>
<td>TDM Execution error table.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_exe_error_summary</td>
<td>TDM Execution error table.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_execution_entities</td>
<td>The detailed&nbsp; list of entities and their execution status per each task execution.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_execution_list</td>
<td>The table holds the list of execution requests of each task execution. A separate record is created for each LU and post execution process.&nbsp;</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_execution_summary</td>
<td>Summary information of each task execution. One record is created per each task execution.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_ref_exe_stats</td>
<td>The list of Reference Table to be processed by a given task execution.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>tasks_post_exe_process</td>
<td>The list of post execution processes to be executed per each task execution.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>tdm_seq_mapping</td>
<td>Mapping of source and target sequences.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_exe_stats_summary</td>
<td>Summary statistics on each task execution. One record is created on each task_Execution_id.</td>
<td>Task Execution Statistics</td>
</tr>
<tr>
<td><h4>activities</td>
<td>TDM activities log.</td>
<td>TDM Activities</td>
</tr>
<tr>
<td><h4>environments</td>
<td>TDM environments.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>environment_owners</td>
<td>The list of environment owner users of each TDM environment.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>environment_products</td>
<td>The list of products (applications), attached to each LU.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>environment_roles</td>
<td>The list of roles and the permissions of each role per each TDM environment.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>environment_role_users</td>
<td>The list of users, attached to a role.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>tdm_be_env_exclusion_list</td>
<td>Exclusion lists per Business Entities and TDM environment</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>tdm_env_globals</td>
<td>The list of Global parameters, set on the Environment level.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>tdm_general_parameters</td>
<td>TDM general parameters.</td>
<td>TDM General Parameters</td>
</tr>
</tbody>
</table>





[![Previous](/articles/images/Previous.png)](01_tdm_architecture.md)
