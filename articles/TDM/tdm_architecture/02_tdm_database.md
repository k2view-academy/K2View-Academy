# TDM Database

TDM settings and TDM tasks are kept in a dedicated PostgreSQL DB. The TDM, TDM GUI and task execution processes connect to the TDM DB to get or update TDM settings or  tasks.

The following table lists the TDM tables and their description.



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
<td>TDM relationships table. This table maps the source parent entity ID to its source children entity IDs per source environment. For example Customer 1 has orders 56, 63 and 73 in the Production environment. This table is populated by a sync of the parent LU and is used to build the entities list of the children LUs during Load (copy) tasks.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>tdm_lu_type_rel_tar_eid</td>
<td>TDM relationship table for target IDs. This table maps the target parent entity ID to its target children entity IDs per target environment. This table is populated by a sync of the parent LU. The table is used to build the entities list of the children LUs for Delete Only tasks when the TDM task deletes parent entities and their related data from a target environment.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>[LU_NAME]_params</td>
<td>Parameters table. Contains the list of all entities migrated into Fabric per LU. Each combination of an entity and source environment has a specific record which holds the entity ID (IID), source environment name and the list of parameters defined for the LU. For example, Customer Type. This table is created by a Fabric sync on each LU and is used to support random selection and select by parameters task selection methods.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>tdm_be_post_exe_process</td>
<td>List of post-execution processes attached to each Business Entity. A post-execution process is executed at the end of the task execution process. For example, sending a mail to a user.</td>
<td>Business Entity</td>
</tr>
<tr>
<td><h4>product_logical_units</td>
<td valign="top" width="500 pxl">
<ul>
<li>Maps a list of LUs to each Business Entity.</li>
<li>Maps the relationship of LUs within parallel LUs or parent-child LUs in a Business Entity.</li>
<li>Maps the combined Business Entity and LU to a Product.</li>
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
<td>List of Global parameters set on a task level.</td>
<td>Task</td>
</tr>
<tr>
<td><h4>task_ref_tables</td>
<td>List of Reference tables included in each TDM task.</td>
<td>Task</td>
</tr>
<tr>
<td><h4>tasks_logical_units</td>
<td>List of LUs included in each TDM task.</td>
<td>Task</td>
</tr>
<tr>
<td><h4>instance_table_count</td>
<td>Holds the records added to each LU table in each LU and LUI. The table is populated by a Fabric sync and is used to generate the TDM Statistics report.</td>
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
<td>Detailed list of entities and their execution status per each task execution.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_execution_list</td>
<td>Holds the list of execution requests of each task execution. A separate record is created for each LU and post execution process.&nbsp;</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_execution_summary</td>
<td>Summary information of each task execution. One record is created per each task execution.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_ref_exe_stats</td>
<td>List of Reference Table to be processed by a given task execution.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>tasks_post_exe_process</td>
<td>List of post execution processes to be executed per each task execution.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>tdm_seq_mapping</td>
<td>Mapping of source and target sequences.</td>
<td>Task Execution</td>
</tr>
<tr>
<td><h4>task_exe_stats_summary</td>
<td>Summary statistics on each task execution. A record is created for each task_Execution_id.</td>
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
<td>List of environment owner users of each TDM environment.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>environment_products</td>
<td>List of products (applications) attached to each LU.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>environment_roles</td>
<td>List of roles and their permissions per TDM environment.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>environment_role_users</td>
<td>List of users attached to a role.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>tdm_be_env_exclusion_list</td>
<td>Exclusion lists per Business Entities and TDM environment</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>tdm_env_globals</td>
<td>List of Global parameters set on an Environment level.</td>
<td>TDM Environments</td>
</tr>
<tr>
<td><h4>tdm_general_parameters</td>
<td>TDM general parameters.</td>
<td>General TDM parameters</td>
</tr>
</tbody>
</table>





[![Previous](/articles/images/Previous.png)](01_tdm_architecture.md)
