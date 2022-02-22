# Building Entity Lists on a Task's LUs

The task execution process executes the task's LUs from parent to child. 

Click for more information about the [execution order of hierarchical LUs](/articles/TDM/tdm_overview/03_business_entity_overview.md#task-execution-of-hierarchical-business-entities).

The task execution process builds an entity list for each LU, as described below. Root LUs and Children LUs require different steps:  

## Root LUs

The entity list of the root LUs is based on the task's selection method:

<table width="900pxl">
<tbody>
<tr>
<td width="300pxl">
<p><strong>Entity Selection Method</strong></p>
</td>
<td width="400pxl">
<p><strong>Where the Entities are Taken From</strong></p>
</td>
<td width="200pxl">
<p><strong>Task Types</strong></p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Entity list</p>
</td>
<td width="400pxl">
<p>Run the task on the list of entities, set in the task itself or in the task overridden execution parameters</p>
</td>
<td width="200pxl">
<p>All task types</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Custom logic</p>
</td>
<td width="400pxl">
<p>Run the selected Broadway flow to get the entity list</p>
</td>
<td width="200pxl">
<p>All task types</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Select a predefined entity list</p>
</td>
<td width="400pxl">
<p>Run the SQL query or the Broadway flow defined in the <a href="/articles/TDM/tdm_implementation/04_fabric_tdm_library.md#trnmigratelist">trnMigrateList</a> translation object for the LU.</p>
</td>
<td width="200pxl">
<p>Extract tasks</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Select all entities of the selected version</p>
</td>
<td width="400pxl">
<p>Select all entities that are successfully extracted by the selected data version. The entities are selected from TASK_EXECUTION_ENTITIES TDM DB table based on the task_execution_id of the selected data version.</p>
</td>
<td width="200pxl">
<p>Load data versioning tasks</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Parameters</p>
</td>
<td width="400pxl">
<p>Select the entities based on the task's parameters from a <a href="07_tdm_parameters_handling.md">DB view</a>, created in the TDM DB for each BE and source environment combination.</p>
</td>
<td width="200pxl">
<p>Regular load tasks (Data Versioning is cleared)</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Entity clone</p>
</td>
<td width="400pxl">
<p>Create duplications on the target environment of the Entity ID, set in the task. Note that only one LUI is created on the entity ID.</p>
</td>
<td width="200pxl">
<p>Regular load tasks (Data Versioning is cleared)</p>
</td>
</tr>
<tr>
<td width="300pxl">
<p>Random selection</p>
</td>
<td width="400pxl">
<p>Randomly select the entities from the <a href="/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md#tdm-parameter-tables">&lt;LU Name&gt;_&lt;params&gt;</a> table.</p>
</td>
<td width="200pxl">
<p>Regular load tasks (Data Versioning is cleared)</p>
</td>
</tr>
</tbody>
</table>



## Children LUs

The entity list of a child LU must include all IDs related to parent IDs that have been successfully processed by the task execution.

Click for an [execution of hierarchical BE](/articles/TDM/tdm_overview/03_business_entity_overview.md#task-execution-of-hierarchical-business-entities) example.

The generated entity list is based on a JOIN of the [task_execution_entities](02_tdm_database.md#task_execution_entities) and the [TDM relationship tables](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm-relationship-tables):

### Insert without Delete Load Task

Select the children IDs from the task_execution_entities and [tdm_lu_type_relation_eid](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm_lu_type_relation_eid) tables:

```sql
SELECT rel.lu_type2_eid as child_entity_id
FROM task_execution_entities t, tdm_lu_type_relation_eid rel 
where t.task_execution_id= <task execution id> 
and t.execution_status = 'completed' 
and t.lu_name = <parent lu name> 
and t.source_env  = rel.source_env 
and t.lu_name = rel.lu_type_1 
and t.iid = rel. lu_type1_eid 
and rel.lu_type_2= <child lu name> 
and rel.version_name = <empty string on a regular task and the selected version name on a Data Flux task>;
```



### Delete before Load Task

Select the children IDs from the task_execution_entities and TDM relationship tables: [tdm_lu_type_relation_eid](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm_lu_type_relation_eid) and [tdm_lu_type_rel_tar_eid](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm_lu_type_rel_tar_eid) to get the children IDs from the source and target environments: 

```sql
SELECT rel.lu_type2_eid as child_entity_id
FROM task_execution_entities t, tdm_lu_type_relation_eid rel 
where t.task_execution_id= <task execution id> 
and t.execution_status = 'completed' 
and t.lu_name = <parent lu name> 
and t.source_env  = rel.source_env 
and t.lu_name = rel.lu_type_1 
and t.iid = rel. lu_type1_eid 
and rel.lu_type_2= <child lu name> 
and rel.version_name = <empty string on a regular task and the selected version name on a Data Flux task>
UNION
SELECT rel.lu_type2_eid as child_entity_id
FROM task_execution_entities t, tdm_lu_type_rel_tar_eid rel 
where t.task_execution_id= <task execution id> 
and t.execution_status = 'completed' 
and t.lu_name = <parent lu name> 
and rel.target_env = <target environment name> 
and t.lu_name = rel.lu_type_1 
and t.iid = rel. lu_type1_eid 
and rel.lu_type_2= <child lu name>; 
```



### Delete Entity without Load Task

Select the children IDs from the task_execution_entities and [tdm_lu_type_rel_tar_eid](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md#tdm_lu_type_rel_tar_eid) to get the target children IDs: 

```sql
SELECT rel.lu_type2_eid as child_entity_id
FROM task_execution_entities t, tdm_lu_type_rel_tar_eid rel 
where t.task_execution_id= <task execution id> 
and t.execution_status = 'completed' 
and t.lu_name = <parent lu name> 
and rel.target_env = <target environment name> 
and t.lu_name = rel.lu_type_1 
and t.iid = rel. lu_type1_eid 
and rel.lu_type_2= <child lu name>; 
```





[![Previous](/articles/images/Previous.png)](03_task_execution_processes.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_task_execution_overridden_parameters.md)





